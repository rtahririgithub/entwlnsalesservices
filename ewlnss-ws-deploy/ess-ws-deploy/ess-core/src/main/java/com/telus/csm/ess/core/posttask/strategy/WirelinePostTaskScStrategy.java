package com.telus.csm.ess.core.posttask.strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.telus.csm.ess.common.domain.MessageDetailVO;
import com.telus.csm.ess.common.domain.MessageVO;
import com.telus.csm.ess.core.posttask.IPostTaskStrategy;
import com.telus.csm.ess.core.posttask.node.AbstractNode;
import com.telus.csm.ess.core.posttask.node.AddNewVestaToSfdcStgExcecNode;
import com.telus.csm.ess.core.posttask.node.CreateFollowUpExecNode;
import com.telus.csm.ess.core.posttask.node.CreateOrderWLNExecNode;
import com.telus.csm.ess.core.posttask.node.InsertVestaCommentExecNode;
import com.telus.csm.ess.core.posttask.node.UpdateOMSOrderStatusExecNode;
import com.telus.csm.ess.core.posttask.node.UpdateVestaStatusExecNode;
import com.telus.csm.ess.core.posttask.workflow.NodeWrapper;
import com.telus.csm.ess.core.posttask.workflow.Workflow;
import com.telus.csm.ewlnsc.adapter.IWirelineSalesEJBAdapter;
import com.telus.csm.ewlnsc.delegate.ShoppingCartDelegate;
import com.telus.csm.ewlnsc.domain.OPShoppingCartDelegateResponseVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.helper.OperationHeaderUtil;
import com.telus.csm.ewlnsc.transformer.opshoppingcart.CreateOpOrderRequestWrapper;
import com.telus.csm.ewlnsc.transformer.opshoppingcart.OPShoppingCartTransformer;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.ShoppingCartStatus;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;
import com.telus.ucss.wirelinesales.domain.OMSOrderStatus;
import com.telus.ucss.wirelinesales.ejb.OrderManager;
import com.telus.ucss.wirelinesales.exception.WLNSalesApplicationException;

public class WirelinePostTaskScStrategy implements IPostTaskStrategy {
	private final LoggerUtil logger = LoggerUtil.getLogger(getClass());
	private List<MessageVO> messages = new ArrayList<MessageVO>();
	
	@Autowired
	IWirelineSalesEJBAdapter wirelineSalesEJBAdapter;

	@Override
	public void execute(ShoppingCartContextVO shoppingCartContextVO) {
		try {
			logger.info("execute", "Starting post task execution for shopping cart: " + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId());
			
			OperationHeader operationHeader = OperationHeaderUtil.buildOperationHeader(shoppingCartContextVO.getShoppingCartVO().getOperationHeader().getSalesTransactionId(), shoppingCartContextVO.getShoppingCartVO());
			List<CreateOpOrderRequestWrapper> createOrderRequests = OPShoppingCartTransformer.transformToCreateOrderAdapterRequest(operationHeader, shoppingCartContextVO);
			
			//create the provision OP order
			CreateOpOrderRequestWrapper createProvisionOrderRequest = findCreateProvisionOrderRequest(createOrderRequests);			
			Integer opPrOrderId = null;
			String vestaOrderId = shoppingCartContextVO.getOrderId();
			OPShoppingCartDelegateResponseVO createOrderNodeResponse = null;
			if(createProvisionOrderRequest != null) {
				Workflow createWorkflow = buildCreateWorkflow(shoppingCartContextVO, createProvisionOrderRequest);
				createWorkflow.run();
				processWorkflowMessages(createWorkflow);
				if (!hasErrors()) {
					if (createWorkflow.getProcessedNodes() != null) {
						for (AbstractNode<?> node: createWorkflow.getProcessedNodes()) {
							if (node instanceof CreateOrderWLNExecNode) {
								CreateOrderWLNExecNode createOrderWLNExecNode = (CreateOrderWLNExecNode) node;
								if (createOrderWLNExecNode.getResult() != null && createOrderWLNExecNode.getResult().getOpShoppingCartId() > 0) {
									opPrOrderId = createOrderWLNExecNode.getResult().getOpShoppingCartId();
									
									createOrderNodeResponse = createOrderWLNExecNode.getResult();
								}
							}
						}
					}
				}else{
					if(StringUtils.isNotBlank(vestaOrderId) && opPrOrderId == null){
						Workflow vestaWorkflow = buildVestaComment(vestaOrderId,"OP Shopping Cart Creation  Failure",OrderManager.COMMENT_TYPE_USER);
						vestaWorkflow.run();
						processWorkflowMessages(vestaWorkflow);
						logger.info("execute", "adding comment to Vesta for scenario: ***When there is only 1 OP shopping cart required and failure to call OP cart creation (any error)***" );
						logger.error("Failure to create main provisioning order.");
					}				
				}
			}
			
			
			List<CreateOpOrderRequestWrapper> createDisconnectOrderRequests = findCreateDisconnectOrderRequests(createOrderRequests);
			if (opPrOrderId == null) {
				//failure to create an OP Provision order
				//TODO
			} else {
				Workflow workflow = new Workflow();
				if(opPrOrderId != null) {
					workflow.addParallelNode(new WirelinePostTaskProvideOrderStrategy(shoppingCartContextVO, opPrOrderId));
				}
				if(createDisconnectOrderRequests != null && !createDisconnectOrderRequests.isEmpty()) {
					for(CreateOpOrderRequestWrapper disconnectOrder: createDisconnectOrderRequests) {
						workflow.addParallelNode(new WirelinePostTaskDisconnectOrderStrategy(shoppingCartContextVO, disconnectOrder));
					}
				}
				if(workflow.getNodes() != null && !workflow.getNodes().isEmpty()) {
					workflow.run();
					processWorkflowMessages(workflow);
					
					Boolean provOrdResult = null;
					ArrayList<OrderStrategyResult> disconnOrdResults = new ArrayList<OrderStrategyResult>();
					for(NodeWrapper ndWr: workflow.getNodes()) {
						if(ndWr.getNode() instanceof WirelinePostTaskProvideOrderStrategy) {
							provOrdResult = ((WirelinePostTaskProvideOrderStrategy) ndWr.getNode()).getResult();
						} else if(ndWr.getNode() instanceof WirelinePostTaskDisconnectOrderStrategy) {
							disconnOrdResults.add(((WirelinePostTaskDisconnectOrderStrategy) ndWr.getNode()).getResult());
						}

					}
					
					doInsertVestaCommentsFlow(provOrdResult,disconnOrdResults,vestaOrderId);
					
					String vestaOrderStatus = findVestaStatus(provOrdResult, disconnOrdResults);
					Workflow postSubmitWorkflow = buildPostSubmitWorkflow(shoppingCartContextVO, vestaOrderStatus, createOrderNodeResponse, disconnOrdResults);
					postSubmitWorkflow.run();
					processWorkflowMessages(postSubmitWorkflow);
					
					ShoppingCartVO shoppingCartVO = shoppingCartContextVO.getShoppingCartVO();
					shoppingCartVO.setStatus(ShoppingCartStatus.COMPLETED);
					shoppingCartVO.setCartItemsStatus( ShoppingCartStatus.CartItemStatus.COMPLETED.getCode() );
					ShoppingCartDelegate.getInstance().updateShoppingCart(shoppingCartVO);
				}
			}
			
		} catch(Exception e) {
			logger.error("", "execute", "Failed to execute the strategy.", e);
			MessageVO msgVO = new MessageVO();
			msgVO.setMessageType(MessageVO.TYPE_ERROR);
			MessageDetailVO msgDetailVO = new MessageDetailVO();
			msgDetailVO.setMessagetTxt("Failed to execute the strategy. Exception: " + e.getMessage());
			msgDetailVO.setLocaleCd(Locale.CANADA.toString());
			if(msgVO.getMessages() == null) {
				msgVO.setMessages(new ArrayList<MessageDetailVO>());
			}
			msgVO.getMessages().add(msgDetailVO);
			messages.add(msgVO);
		} finally {
			if(hasErrors()) {
				Workflow followUpWorkflow = buildFollowUpWorkFlow(shoppingCartContextVO);
				followUpWorkflow.run();
			}
		}
	}

	private Workflow buildVestaComment(String vestaOrderId, String newComment, String commentType) {
		Workflow workflow = new Workflow();
		workflow.addNode(new InsertVestaCommentExecNode(vestaOrderId,newComment,commentType));
		return workflow;
	}

	private void doInsertVestaCommentsFlow(Boolean provOrdResult, ArrayList<OrderStrategyResult> disconnOrdResults, String vestaOrderId) throws WLNSalesApplicationException {
		String functionName = "doInsertVestaCommentsFlow";	
		if(StringUtils.isNotBlank(vestaOrderId)){
			Workflow workflow = new Workflow();
			
			/**
			 * When there is multiple OP shopping carts required and failure to call OP submit order (any error) for the main order (non CLEC disconnect Shopping Cart).
			 */
			if(Boolean.FALSE.equals(provOrdResult) && CollectionUtils.isNotEmpty(disconnOrdResults)){
				workflow.addParallelNode(new InsertVestaCommentExecNode(vestaOrderId,"Main order - submit error",OrderManager.COMMENT_TYPE_OMS));
				
				logger.info(functionName, "adding comment to Vesta for scenario: ***When there is multiple OP shopping carts required and failure to call OP submit order (any error) for the main order (non CLEC disconnect Shopping Cart) ***");
			}
			
			/**
			 * When there is multiple OP shopping carts required and failure to call OP create or submit order (any error) for the non main order (CLEC disconnect Shopping Cart).
			 */
			if(CollectionUtils.isNotEmpty(disconnOrdResults) && !successfulDisconectOrders(disconnOrdResults)){
				workflow.addParallelNode(new InsertVestaCommentExecNode(vestaOrderId, "Disconnect CLEC order - submit error", OrderManager.COMMENT_TYPE_OMS));
				
				logger.info(functionName, "adding comment to Vesta for scenario: ***When there is multiple OP shopping carts required and failure to call OP create or submit order (any error) for the non main order (CLEC disconnect Shopping Cart)***");
			}
			
			/**
			 * When ESS failed to submit OP order (both main and CLEC disconnect orders). When ESS failed to create the CLEC disconnect OP shopping carts.
			 */
			if(CollectionUtils.isNotEmpty(disconnOrdResults) && !successfulDisconectOrders(disconnOrdResults) && Boolean.FALSE.equals(provOrdResult)){
				workflow.addParallelNode(new InsertVestaCommentExecNode(vestaOrderId, "Check OMS", OrderManager.COMMENT_TYPE_OMS));				
				logger.info(functionName, "adding comment to Vesta for scenario: ***When ESS failed to submit OP order (both main and CLEC disconnect orders). When ESS failed to create the CLEC disconnect OP shopping carts***");
			}
			
			workflow.run();
			processWorkflowMessages(workflow);
			
		}else{
			logger.error("Missing update to Vesta comments since there is no Vesta Order Id");
		}

		
	}

	private boolean successfulDisconectOrders(ArrayList<OrderStrategyResult> disconnOrdResults) {
		if(disconnOrdResults != null && !disconnOrdResults.isEmpty()) {
			for(OrderStrategyResult disconnResult: disconnOrdResults) {
				if(disconnResult.getOrderSubmited() != null && !disconnResult.getOrderSubmited()) {
					return false;
				}
			}
		}
		return true;
	}


	private String findVestaStatus(Boolean provOrdResult, ArrayList<OrderStrategyResult> disconnOrdResults) {
		boolean successFullySubmitted = true;
		if(provOrdResult != null) {
			successFullySubmitted &= provOrdResult;
		}
		if(disconnOrdResults != null && !disconnOrdResults.isEmpty()) {
			for(OrderStrategyResult disconnResult: disconnOrdResults) {
				if(disconnResult.getOrderSubmited() != null) {
					successFullySubmitted &= disconnResult.getOrderSubmited();
				}
			}
		}
		String vestaOrderStatus = successFullySubmitted? UpdateVestaStatusExecNode.VESTA_ORDER_STATUS_SUBMIT: UpdateVestaStatusExecNode.VESTA_ORDER_STATUS_UNASSIGN;
		return vestaOrderStatus;
	}

	private CreateOpOrderRequestWrapper findCreateProvisionOrderRequest(List<CreateOpOrderRequestWrapper> createOrderRequests) {
		if(createOrderRequests != null) {
			requests_loop:
			for(CreateOpOrderRequestWrapper req: createOrderRequests) {
				if(req.getCreateOrderAdapterRequest() != null 
						&& req.getCreateOrderAdapterRequest().getBody() != null 
						&& req.getCreateOrderAdapterRequest().getBody().getProductOrderItems() != null) {
					for(com.telus.csm.ewlnsc.adapter.woscs.domain.ProductOrderItem itm: req.getCreateOrderAdapterRequest().getBody().getProductOrderItems()) {
						if(EnterpriseWLNSalesServicesConstants.OP_CLEC.equalsIgnoreCase(itm.getAction())) {
							//this is a delete order
							continue requests_loop;
						} else {
							return req;//new have PR but update have nothing set
						}
					}
				}
			}
		}
		return null;
	}

	private List<CreateOpOrderRequestWrapper> findCreateDisconnectOrderRequests(List<CreateOpOrderRequestWrapper> createOrderRequests) {
		ArrayList<CreateOpOrderRequestWrapper> deleteOrders = new ArrayList<CreateOpOrderRequestWrapper>();
		if(createOrderRequests != null) {
			requests_loop:
			for(CreateOpOrderRequestWrapper req: createOrderRequests) {
				if(req.getCreateOrderAdapterRequest() != null &&
						req.getCreateOrderAdapterRequest().getBody() != null 
						&& req.getCreateOrderAdapterRequest().getBody().getProductOrderItems() != null) {
					for(com.telus.csm.ewlnsc.adapter.woscs.domain.ProductOrderItem itm: req.getCreateOrderAdapterRequest().getBody().getProductOrderItems()) {
						if(itm.getProduct() != null && EnterpriseWLNSalesServicesConstants.OP_CLEC.equalsIgnoreCase(itm.getProduct().getServiceType())) {
							//this is a delete order
							deleteOrders.add(req);
							continue requests_loop;
						}
					}
				}
			}
		}
		return deleteOrders;
	}

	private Workflow buildFollowUpWorkFlow(ShoppingCartContextVO shoppingCartContextVO) {
		Workflow workflow = new Workflow();
		workflow.addNode(new CreateFollowUpExecNode(shoppingCartContextVO, messages));
		return workflow;
	}

	private boolean hasErrors() {
		for(MessageVO msg: messages) {
			if(MessageVO.TYPE_ERROR.equalsIgnoreCase(msg.getMessageType())) {
				return true;
			}
		}
		return false;
	}

	private Workflow buildCreateWorkflow(ShoppingCartContextVO shoppingCartContextVO, CreateOpOrderRequestWrapper createOrderAdapterRequest) {
		Workflow workflow = new Workflow();
		workflow.addNode(new CreateOrderWLNExecNode(shoppingCartContextVO, createOrderAdapterRequest));
		return workflow;
	}
	
	private Workflow buildPostSubmitWorkflow(ShoppingCartContextVO shoppingCartContextVO, String vestaOrderStatus, OPShoppingCartDelegateResponseVO opShoppingCartDelegateResponse, List<OrderStrategyResult> disconnOrdResults) {
	 	Workflow workflow = new Workflow();
		
		List<OMSOrderStatus> omsOrderStatusList = buildOmsOrderStatusList(opShoppingCartDelegateResponse, disconnOrdResults);
		Map<String, String> productActionMap = buildProductActionMap(opShoppingCartDelegateResponse, disconnOrdResults);
		workflow.addNode(new UpdateOMSOrderStatusExecNode(shoppingCartContextVO, omsOrderStatusList, productActionMap));
		
		workflow.addNode(new UpdateVestaStatusExecNode(shoppingCartContextVO, vestaOrderStatus));//set the status to SUBMIT or UNASSIGNED
		
		if (Constants.D2C_ORDER.equalsIgnoreCase(shoppingCartContextVO.getShoppingCartVO().getShoppingProfile().getUserProfile().getChannelOrganizationTypeCd())) {
			workflow.addNode(new AddNewVestaToSfdcStgExcecNode(shoppingCartContextVO));
		}
		return workflow;
	}

	private void processWorkflowMessages(Workflow workflow) {
		if(workflow.getProcessedNodes() != null) {
			for(AbstractNode<?> node: workflow.getProcessedNodes()) {
				if(!node.getMessages().isEmpty()) {
					messages.addAll(node.getMessages());
				}
			}
		}
	}

	private Map<String, String> buildProductActionMap(OPShoppingCartDelegateResponseVO opShoppingCartDelegateResponse, List<OrderStrategyResult> disconnOrdResults) {
		Map<String, String> productActionMap = new HashMap<String, String>();
		if (opShoppingCartDelegateResponse != null && CollectionUtils.isNotEmpty(opShoppingCartDelegateResponse.getProductOrderItems())) {
			
			List<com.telus.csm.ewlnsc.adapter.woscs.domain.ProductOrderItem> productOrderItems = opShoppingCartDelegateResponse.getProductOrderItems();
			
			for(com.telus.csm.ewlnsc.adapter.woscs.domain.ProductOrderItem productOrderItem : productOrderItems){
				com.telus.csm.ewlnsc.adapter.woscs.domain.Product product = productOrderItem.getProduct();
				productActionMap.put(product.getServiceType(), productOrderItem.getId());
			}			
		}
		if(disconnOrdResults != null) {
			for(OrderStrategyResult disconnResult: disconnOrdResults) {
				if(disconnResult.getOpShoppingCartDelegateResponse()!=null) {
					
					List<com.telus.csm.ewlnsc.adapter.woscs.domain.ProductOrderItem> productOrderItems = disconnResult.getOpShoppingCartDelegateResponse().getProductOrderItems();
					
					for(com.telus.csm.ewlnsc.adapter.woscs.domain.ProductOrderItem productOrderItem : productOrderItems){
						com.telus.csm.ewlnsc.adapter.woscs.domain.Product product = productOrderItem.getProduct();
						productActionMap.put(product.getServiceType(), productOrderItem.getId());
					}
					
				}
			}
		}
		return productActionMap;
	}

	/*
	 * OP Provision order to be first
	 */
	private List<OMSOrderStatus> buildOmsOrderStatusList(OPShoppingCartDelegateResponseVO opShoppingCartDelegateResponse, List<OrderStrategyResult> disconnOrdResults) {
		List<OMSOrderStatus> statusList = new ArrayList<OMSOrderStatus>();
		if (opShoppingCartDelegateResponse != null) {
			OMSOrderStatus status = new OMSOrderStatus();
			
			status.setOmsId(String.valueOf(opShoppingCartDelegateResponse.getOpShoppingCartId()));
			statusList.add(status);
		}
		if(disconnOrdResults != null) {
			for(OrderStrategyResult disconnResult: disconnOrdResults) {
				if(disconnResult.getOpShoppingCartDelegateResponse()!=null) {
					OMSOrderStatus status = new OMSOrderStatus();
					status.setOmsId(String.valueOf(disconnResult.getOpShoppingCartDelegateResponse().getOpShoppingCartId()));
					statusList.add(status);
				}
			}
		}
		return statusList;
	}
	
}
