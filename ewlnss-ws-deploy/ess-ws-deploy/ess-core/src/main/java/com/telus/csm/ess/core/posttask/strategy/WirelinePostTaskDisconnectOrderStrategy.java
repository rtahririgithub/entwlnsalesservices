package com.telus.csm.ess.core.posttask.strategy;

import com.telus.csm.ess.common.domain.MessageVO;
import com.telus.csm.ess.core.posttask.node.AbstractNode;
import com.telus.csm.ess.core.posttask.node.CreateOrderWLNExecNode;
import com.telus.csm.ess.core.posttask.node.GetOrderWLNQuoteExecNode;
import com.telus.csm.ess.core.posttask.node.SubmitOrderWLNExecNode;
import com.telus.csm.ess.core.posttask.node.ValidateOrderWLNExecNode;
import com.telus.csm.ess.core.posttask.workflow.Workflow;
import com.telus.csm.ewlnsc.domain.OPShoppingCartDelegateResponseVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.transformer.opshoppingcart.CreateOpOrderRequestWrapper;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class WirelinePostTaskDisconnectOrderStrategy extends AbstractNode<OrderStrategyResult> {
	private final LoggerUtil logger = LoggerUtil.getLogger(getClass());
	private ShoppingCartContextVO shoppingCartContextVO;
	private CreateOpOrderRequestWrapper disconnectOrder;
	private Integer opOrderId;
	private Boolean orderSubmitted;
	private OPShoppingCartDelegateResponseVO createOrderNodeResponse;
	
	protected WirelinePostTaskDisconnectOrderStrategy(ShoppingCartContextVO shoppingCartContextVO, CreateOpOrderRequestWrapper disconnectOrder) {
		super("WirelinePostTaskDisconnectOrderStrategy");
		this.shoppingCartContextVO = shoppingCartContextVO;
		this.disconnectOrder = disconnectOrder;
	}

	@Override
	public void run() {
		String functionName = "WirelinePostTaskDisconnectOrderStrategy.run";
		logger.enter(functionName);
		try {
			Workflow createOrderWorkFlow = buildDisconnectOrderCreateWorkFlow();
			createOrderWorkFlow.run();
			processWorkflowMessages(createOrderWorkFlow);

			if (!hasErrors()) {
				if (createOrderWorkFlow.getProcessedNodes() != null) {
					for (AbstractNode<?> node: createOrderWorkFlow.getProcessedNodes()) {
						if (node instanceof CreateOrderWLNExecNode) {
							CreateOrderWLNExecNode createOrderWLNExecNode = (CreateOrderWLNExecNode) node;
							if (createOrderWLNExecNode.getResult() != null && createOrderWLNExecNode.getResult().getOpShoppingCartId() > 0) {
								opOrderId = createOrderWLNExecNode.getResult().getOpShoppingCartId();
								
								createOrderNodeResponse = createOrderWLNExecNode.getResult();
							}
						}
					}
				}
			}

			if (opOrderId != null) {
				Workflow validateOrderWorkFlow = buildValidateOrderWorkFlow();
				validateOrderWorkFlow.run();
				processWorkflowMessages(validateOrderWorkFlow);

				Workflow submitOrderWorkFlow = buildSubmitOrderWorkFlow(hasErrors());
				submitOrderWorkFlow.run();
				processWorkflowMessages(submitOrderWorkFlow);
				if (submitOrderWorkFlow.getProcessedNodes() != null) {
					for (AbstractNode<?> node: submitOrderWorkFlow.getProcessedNodes()) {
						if (node instanceof SubmitOrderWLNExecNode) {
							SubmitOrderWLNExecNode submitOrderNode = (SubmitOrderWLNExecNode) node;
							if (submitOrderNode.getMessages() == null || submitOrderNode.getMessages().isEmpty()) {
								orderSubmitted = true;
							}
						}
					}
				}
			}

		} catch(Exception e) {
			String msg = "Failed in create an OP disconnect order. ";
			logger.error("", functionName, msg, e);
			handleException(e, msg);
		} finally {
			logger.exit(functionName);
		}
		
	}

	private Workflow buildValidateOrderWorkFlow() {
		Workflow workflow = new Workflow();
		workflow.addNode(new ValidateOrderWLNExecNode(shoppingCartContextVO, opOrderId));
		return workflow;
	}

	private Workflow buildSubmitOrderWorkFlow(boolean skipQuote) {
		Workflow workflow = new Workflow();
		if(!skipQuote) {
			workflow.addNode(new GetOrderWLNQuoteExecNode(shoppingCartContextVO, opOrderId));
		}
		workflow.addNode(new SubmitOrderWLNExecNode(shoppingCartContextVO, opOrderId, false));
		return workflow;
	}

	private Workflow buildDisconnectOrderCreateWorkFlow() {
		Workflow workflow = new Workflow();
		workflow.addNode(new CreateOrderWLNExecNode(shoppingCartContextVO, disconnectOrder));
		return workflow;
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
	public OrderStrategyResult getResult() {
		return new OrderStrategyResult(opOrderId, createOrderNodeResponse, orderSubmitted);
	}

}


