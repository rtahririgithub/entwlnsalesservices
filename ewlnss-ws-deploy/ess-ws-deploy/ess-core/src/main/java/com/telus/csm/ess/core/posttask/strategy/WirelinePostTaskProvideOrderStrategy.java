package com.telus.csm.ess.core.posttask.strategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.BooleanHolder;

import com.telus.csm.ess.common.domain.MessageDetailVO;
import com.telus.csm.ess.common.domain.MessageVO;
import com.telus.csm.ess.core.posttask.node.AbstractNode;
import com.telus.csm.ess.core.posttask.node.AddNewVestaToSfdcStgExcecNode;
import com.telus.csm.ess.core.posttask.node.ConfirmOrderWLNBookingExecNode;
import com.telus.csm.ess.core.posttask.node.CreateOrderWLNInvoiceExecNode;
import com.telus.csm.ess.core.posttask.node.GetOrderWLNBookingRequirementExecNode;
import com.telus.csm.ess.core.posttask.node.GetOrderWLNDepositExecNode;
import com.telus.csm.ess.core.posttask.node.GetOrderWLNQuoteExecNode;
import com.telus.csm.ess.core.posttask.node.RetrieveOrderWLNExecNode;
import com.telus.csm.ess.core.posttask.node.SubmitOrderWLNExecNode;
import com.telus.csm.ess.core.posttask.node.SubmitPerkWLNExecNode;
import com.telus.csm.ess.core.posttask.node.UpdateDepositForInvoiceNumberExecNode;
import com.telus.csm.ess.core.posttask.node.UpdateOrderWLNExecNode;
import com.telus.csm.ess.core.posttask.node.ValidateOrderWLNExecNode;
import com.telus.csm.ess.core.posttask.workflow.Workflow;
import com.telus.csm.ewlnsc.delegate.OrderCommentDelegate;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.ExternalOrderDetail;
import com.telus.csm.ewlnsc.domain.OrderCommentVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.helper.EnterpriseWLNOrderUtil;
import com.telus.csm.ewlnsc.rules.shoppingcart.AccessoryOfferValidationRule;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class WirelinePostTaskProvideOrderStrategy extends AbstractNode<Boolean> {
	private final LoggerUtil logger = LoggerUtil.getLogger(getClass());
	private ShoppingCartContextVO shoppingCartContextVO;
	private Integer opOrderId;
	private Boolean orderSubmitted;

	protected WirelinePostTaskProvideOrderStrategy(ShoppingCartContextVO shoppingCartContextVO, Integer opOrderId) {
		super("WirelinePostTaskProvideOrderStrategy");
		this.shoppingCartContextVO = shoppingCartContextVO;
		this.opOrderId = opOrderId;
	}

	@Override
	public void run() {
		String functionName = "WirelinePostTaskProvideOrderStrategy.run";
		logger.enter(functionName);
		try {
			Workflow validateWorkflow = buildValidateWorkflow(shoppingCartContextVO, opOrderId);
			validateWorkflow.run();
			processWorkflowMessages(validateWorkflow);
			
			Workflow preSubmit1Workflow = buildPreSubmit1Workflow(shoppingCartContextVO, opOrderId, hasErrors());
			preSubmit1Workflow.run();
			processWorkflowMessages(preSubmit1Workflow);
			boolean createInvoice = false;
			if (preSubmit1Workflow.getProcessedNodes() != null) {
				for (AbstractNode<?> node: preSubmit1Workflow.getProcessedNodes()) {
					if (node instanceof GetOrderWLNDepositExecNode) {
						GetOrderWLNDepositExecNode getDepositNode = (GetOrderWLNDepositExecNode) node;
						if (getDepositNode.getResult() != null 
								&& getDepositNode.getResult().getTotalRequiredDeposit() != null
								&& getDepositNode.getResult().getTotalRequiredDeposit() > 0) {
							createInvoice = true;
						}
					}
				}
			}

			Workflow preSubmit2Workflow = buildPreSubmit2Workflow(shoppingCartContextVO, createInvoice);
			preSubmit2Workflow.run();
			processWorkflowMessages(preSubmit2Workflow);
            
			ValidateOrderWLNExecNode validateOrderNode = null;
			if (validateWorkflow.getProcessedNodes() != null) {
				for (AbstractNode<?> node: validateWorkflow.getProcessedNodes()) {
					if (node instanceof ValidateOrderWLNExecNode) {
						validateOrderNode = (ValidateOrderWLNExecNode) node;
						logger.debug("execute", "validateOrderNode was found");
						break;
					}
				}
			}

			
			/* Order Comments for Update - Start */
			GetOrderWLNDepositExecNode getDepositNode = null;
			if (preSubmit1Workflow.getProcessedNodes() != null) {
				for (AbstractNode<?> node: preSubmit1Workflow.getProcessedNodes()) {
					if (node instanceof GetOrderWLNDepositExecNode) {
						getDepositNode = (GetOrderWLNDepositExecNode) node;
						logger.debug("execute", "getDepositNode was found");
						break;
					}
				}
			}				

			GetOrderWLNBookingRequirementExecNode getBookingRequirementNode = null;
			if (preSubmit1Workflow.getProcessedNodes() != null) {
				for (AbstractNode<?> node: preSubmit1Workflow.getProcessedNodes()) {
					if (node instanceof GetOrderWLNBookingRequirementExecNode) {
						getBookingRequirementNode = (GetOrderWLNBookingRequirementExecNode) node;
						logger.debug("execute", "getBookingRequirementNode was found");
						break;
					}
				}
			}
			
			CreateOrderWLNInvoiceExecNode createInvoiceNode = null;
			if (preSubmit2Workflow.getProcessedNodes() != null) {
				for (AbstractNode<?> node: preSubmit2Workflow.getProcessedNodes()) {
					if (node instanceof CreateOrderWLNInvoiceExecNode) {
						createInvoiceNode = (CreateOrderWLNInvoiceExecNode) node;
						logger.debug("execute", "createInvoiceNode was found");
						break;
					}
				}
			}

			ConfirmOrderWLNBookingExecNode confirmOrderWLNBookingNode = null;
			if (preSubmit2Workflow.getProcessedNodes() != null) {
				for (AbstractNode<?> node: preSubmit2Workflow.getProcessedNodes()) {
				if (node instanceof ConfirmOrderWLNBookingExecNode) {
						confirmOrderWLNBookingNode = (ConfirmOrderWLNBookingExecNode) node;
						logger.debug("execute", "confirmOrderWLNBookingNode was found");
						break;
					}
				}
			}

			BooleanHolder forceBackOffice = new BooleanHolder(false);			
			//TODO move to an own node
			try {
				List<OrderCommentVO> createOrderComments = new OrderCommentDelegate().execute(shoppingCartContextVO, forceBackOffice);
				saveOrderComments(shoppingCartContextVO, createOrderComments, validateOrderNode, getDepositNode, getBookingRequirementNode, createInvoiceNode, confirmOrderWLNBookingNode,opOrderId);
			} catch(Exception e) {
				logger.error("", "execute", "Failed in creating comments.", e);
				MessageVO msgVO = new MessageVO();
				msgVO.setMessageType(MessageVO.TYPE_ERROR);
				MessageDetailVO msgDetailVO = new MessageDetailVO();
				msgDetailVO.setMessagetTxt("Failed in creating comments. Exception: " + e.getMessage());
				msgDetailVO.setLocaleCd(Locale.CANADA.toString());
				if(msgVO.getMessages() == null) {
					msgVO.setMessages(new ArrayList<MessageDetailVO>());
				}
				msgVO.getMessages().add(msgDetailVO);
				getMessages().add(msgVO);
			}
			
			if(!isGwpRulesSatisfied(shoppingCartContextVO)) {
				forceBackOffice.value |= true;//if gwp rules failed then force to the backoffice
				logger.info(functionName, "Will force to backoffice because GWP rules have failed");
			}
			
			Workflow submitWorkflow = buildSubmitWorkflow(shoppingCartContextVO, opOrderId, forceBackOffice.value);
			submitWorkflow.run();
			processWorkflowMessages(submitWorkflow);
			if (submitWorkflow.getProcessedNodes() != null) {
				for (AbstractNode<?> node: submitWorkflow.getProcessedNodes()) {
					if (node instanceof SubmitOrderWLNExecNode) {
						SubmitOrderWLNExecNode submitOrderNode = (SubmitOrderWLNExecNode) node;
						if (submitOrderNode.getMessages() == null || submitOrderNode.getMessages().isEmpty()) {
							orderSubmitted = true;
						}
					}
				}
			}

			Workflow postSubmitWorkflow = buildPostSubmitWorkflow(shoppingCartContextVO);
			postSubmitWorkflow.run();
			processWorkflowMessages(postSubmitWorkflow);
			
			
			//look for the ExternalOrderDetail object associated to the opOrderId
			ExternalOrderDetail extOrderDetail = EnterpriseWLNOrderUtil.getAssociatedExternalOrderDetailByOpId(opOrderId,shoppingCartContextVO);
			if(extOrderDetail!=null){
				String externalOrderStatus = null;
				if(!orderSubmitted) {
					externalOrderStatus = ExternalOrderDetail.OMS_STATUS_FAIL;
				}
				if(StringUtils.isEmpty(externalOrderStatus)) {
					externalOrderStatus = ExternalOrderDetail.OMS_STATUS_IN_PROGRESS;
				}
				extOrderDetail.setExternalOrderStatus(externalOrderStatus);
				logger.info("execute", "Shopping cart: " + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId() + " set ExternalOrderStatus to [" + externalOrderStatus + "] for OP order [" + extOrderDetail.getExternalOrderId() + "].");
			}
			

		
		} catch(Exception e) {
			String msg = "Failed in create an OP provide order. ";
			logger.error("", functionName, msg, e);
			handleException(e, msg);
		} finally {
			logger.exit(functionName);
		}
		
	}

	private Workflow buildPostSubmitWorkflow(ShoppingCartContextVO shoppingCartContextVO) {
		Workflow workflow = new Workflow();
		workflow.addNode(new SubmitPerkWLNExecNode(shoppingCartContextVO));
		workflow.addNode(new UpdateDepositForInvoiceNumberExecNode(shoppingCartContextVO));
		return workflow;
	}

	private Workflow buildPreSubmit2Workflow(ShoppingCartContextVO shoppingCartContextVO, boolean createInvoice) {
		Workflow workflow = new Workflow();
		if(createInvoice) {
			workflow.addNode(new CreateOrderWLNInvoiceExecNode(shoppingCartContextVO,opOrderId));
		}
		List<String> bookingIds = findBookingIds(shoppingCartContextVO);
		if(bookingIds != null && !bookingIds.isEmpty()) {
			for(String bookingId: bookingIds) {
				workflow.addNode(new ConfirmOrderWLNBookingExecNode(shoppingCartContextVO, bookingId,opOrderId));
			}
		}
		return workflow;
	}

	private List<String> findBookingIds(ShoppingCartContextVO shoppingCartContextVO) {
		HashSet<String> uniqueBookingIds = new HashSet<String>();
		if(shoppingCartContextVO.getShoppingCartVO().getCartItemList() != null) {
			for(CartItemVO itmVO: shoppingCartContextVO.getShoppingCartVO().getCartItemList()) {
				if(itmVO.getProducts() != null 
						&& itmVO.getProducts().getHomePhoneProduct() != null 
						&& itmVO.getProducts().getHomePhoneProduct().getAppointmentDetail() != null
						&& itmVO.getProducts().getHomePhoneProduct().getAppointmentDetail().getBookedAppointmentDate() != null
						&& !StringUtils.isEmpty(itmVO.getProducts().getHomePhoneProduct().getAppointmentDetail().getBookedAppointmentDate().getAppointmentId())) {
					uniqueBookingIds.add(itmVO.getProducts().getHomePhoneProduct().getAppointmentDetail().getBookedAppointmentDate().getAppointmentId());
				}
				if(itmVO.getProducts() != null 
						&& itmVO.getProducts().getInternetProduct() != null 
						&& itmVO.getProducts().getInternetProduct().getAppointmentDetail() != null
						&& itmVO.getProducts().getInternetProduct().getAppointmentDetail().getBookedAppointmentDate() != null
						&& !StringUtils.isEmpty(itmVO.getProducts().getInternetProduct().getAppointmentDetail().getBookedAppointmentDate().getAppointmentId())) {
					uniqueBookingIds.add(itmVO.getProducts().getInternetProduct().getAppointmentDetail().getBookedAppointmentDate().getAppointmentId());
				}
				if(itmVO.getProducts() != null 
						&& itmVO.getProducts().getTelevisionProduct() != null 
						&& itmVO.getProducts().getTelevisionProduct().getAppointmentDetail() != null
						&& itmVO.getProducts().getTelevisionProduct().getAppointmentDetail().getBookedAppointmentDate() != null
						&& !StringUtils.isEmpty(itmVO.getProducts().getTelevisionProduct().getAppointmentDetail().getBookedAppointmentDate().getAppointmentId())) {
					uniqueBookingIds.add(itmVO.getProducts().getTelevisionProduct().getAppointmentDetail().getBookedAppointmentDate().getAppointmentId());
				}
			}
		}
		return new ArrayList<String>(uniqueBookingIds);
	}

	private Workflow buildValidateWorkflow(ShoppingCartContextVO shoppingCartContextVO, Integer opOrderId) {
		Workflow workflow = new Workflow();
		workflow.addNode(new ValidateOrderWLNExecNode(shoppingCartContextVO, opOrderId));
		return workflow;
	}

	private Workflow buildPreSubmit1Workflow(ShoppingCartContextVO shoppingCartContextVO, Integer opOrderId, boolean skipQuote) {
		Workflow workflow = new Workflow();
		
		workflow.addNode(new GetOrderWLNBookingRequirementExecNode(shoppingCartContextVO,opOrderId));
		
		if(!skipQuote) {
			workflow.addNode(new GetOrderWLNQuoteExecNode(shoppingCartContextVO, opOrderId));
		}

		workflow.addNode(new GetOrderWLNDepositExecNode(shoppingCartContextVO,opOrderId));
		return workflow;
	}

	private void saveOrderComments(ShoppingCartContextVO shoppingCartContextVO,
			List<OrderCommentVO> createOrderComments, ValidateOrderWLNExecNode validateOrderNode,
			GetOrderWLNDepositExecNode getDepositNode,
			GetOrderWLNBookingRequirementExecNode getBookingRequirementNode,
			CreateOrderWLNInvoiceExecNode createInvoiceNode,
			ConfirmOrderWLNBookingExecNode confirmOrderWLNBookingNode,int opOrderId) {

		List<OrderCommentVO> updateOrderComments = null;

		if ((validateOrderNode != null) && (validateOrderNode.getResult() != null) && (getDepositNode != null)
				&& (getDepositNode.getResult() != null) && (getBookingRequirementNode != null)
				&& (getBookingRequirementNode.getResult() != null)) {

			if (createInvoiceNode == null) {
				logger.debug("execute", "Calling OrderCommentDelegate().execute() without createInvoiceNode.");

				if (confirmOrderWLNBookingNode == null) {
					logger.debug("execute",
							"Calling OrderCommentDelegate().execute() without confirmOrderWLNBookingNode.");

					updateOrderComments = new OrderCommentDelegate().execute(shoppingCartContextVO,
							validateOrderNode.getResult(), getDepositNode.getResult(), null,
							getBookingRequirementNode.getResult(), null, null);
				} else {
					logger.debug("execute",
							"Calling OrderCommentDelegate().execute() with confirmOrderWLNBookingNode.");

					updateOrderComments = new OrderCommentDelegate().execute(shoppingCartContextVO,
							validateOrderNode.getResult(), getDepositNode.getResult(), null,
							getBookingRequirementNode.getResult(), confirmOrderWLNBookingNode.getResult(),
							confirmOrderWLNBookingNode.getBookingId());
				}
			} else {
				logger.debug("execute", "Calling OrderCommentDelegate().execute() with createInvoiceNode.");

				if (confirmOrderWLNBookingNode == null) {
					logger.debug("execute",
							"Calling OrderCommentDelegate().execute() without confirmOrderWLNBookingNode.");

					updateOrderComments = new OrderCommentDelegate().execute(shoppingCartContextVO,
							validateOrderNode.getResult(), getDepositNode.getResult(), createInvoiceNode.getResult(),
							getBookingRequirementNode.getResult(), null, null);
				} else {
					logger.debug("execute",
							"Calling OrderCommentDelegate().execute() with confirmOrderWLNBookingNode.");

					updateOrderComments = new OrderCommentDelegate().execute(shoppingCartContextVO,
							validateOrderNode.getResult(), getDepositNode.getResult(), createInvoiceNode.getResult(),
							getBookingRequirementNode.getResult(), confirmOrderWLNBookingNode.getResult(),
							confirmOrderWLNBookingNode.getBookingId());
				}
			}
		}

		List<OrderCommentVO> comments = new ArrayList<OrderCommentVO>();
		if ((createOrderComments != null) && (!createOrderComments.isEmpty())) {
			comments.addAll(createOrderComments);
		}
		if ((updateOrderComments != null) && (!updateOrderComments.isEmpty())) {
			comments.addAll(updateOrderComments);
		}

		if (!comments.isEmpty()) {
			Workflow retrieveOrderWorkflow = buildRetrieveWorkFlow(shoppingCartContextVO);
			retrieveOrderWorkflow.run();
			processWorkflowMessages(retrieveOrderWorkflow);

			RetrieveOrderWLNExecNode retrieveOrderNode = null;
			if (retrieveOrderWorkflow.getProcessedNodes() != null) {
				for (AbstractNode<?> node : retrieveOrderWorkflow.getProcessedNodes()) {
					if (node instanceof RetrieveOrderWLNExecNode) {
						retrieveOrderNode = (RetrieveOrderWLNExecNode) node;
						break;
					}
				}
				if ((retrieveOrderNode != null) && (retrieveOrderNode.getResult() != null)
						&& (retrieveOrderNode.getResult().getProductOrder() != null)) {
					Workflow updateOrderWorkflow = buildUpdateWorkFlow(shoppingCartContextVO, comments,opOrderId);
					updateOrderWorkflow.run();
					processWorkflowMessages(updateOrderWorkflow);
				}
			}
		}
	}

	private Workflow buildRetrieveWorkFlow(ShoppingCartContextVO shoppingCartContextVO) {
		Workflow workflow = new Workflow();
		workflow.addNode(new RetrieveOrderWLNExecNode(shoppingCartContextVO,opOrderId));
		return workflow;
	}	

	private Workflow buildUpdateWorkFlow(ShoppingCartContextVO shoppingCartContextVO, List<OrderCommentVO> comments,int opOrderId) {
		Workflow workflow = new Workflow();
		workflow.addNode(new UpdateOrderWLNExecNode(shoppingCartContextVO, comments,opOrderId));
		return workflow;
	}	

	private Workflow buildSubmitWorkflow(ShoppingCartContextVO shoppingCartContextVO, Integer opOrderId, boolean forceBackOffice) {
		Workflow workflow = new Workflow();
		workflow.addNode(new SubmitOrderWLNExecNode(shoppingCartContextVO, opOrderId, forceBackOffice));
		return workflow;
	}

	private boolean isGwpRulesSatisfied(ShoppingCartContextVO shoppingCartContextVO) {
		boolean isSatisfied = true;
		for(CartItemVO cartItem : shoppingCartContextVO.getShoppingCartVO().getCartItemList()) {
			if(!isSatisfied) {
				break;
			}
			AccessoryOfferValidationRule gwpRule = new AccessoryOfferValidationRule(cartItem);
			ArrayList<ShoppingCartValidationTraceVO> traces = new ArrayList<ShoppingCartValidationTraceVO>();
			isSatisfied &= gwpRule.isSatisfiedBy(shoppingCartContextVO, traces);
		}
		return isSatisfied;
	}
	
	private void processWorkflowMessages(Workflow workflow) {
		if(workflow.getProcessedNodes() != null) {
			for(AbstractNode<?> node: workflow.getProcessedNodes()) {
				if(!node.getMessages().isEmpty()) {
					getMessages().addAll(node.getMessages());
				}
			}
		}
	}

	private boolean hasErrors() {
		for(MessageVO msg: getMessages()) {
			if(MessageVO.TYPE_ERROR.equalsIgnoreCase(msg.getMessageType())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Boolean getResult() {
		return orderSubmitted;
	}
}


