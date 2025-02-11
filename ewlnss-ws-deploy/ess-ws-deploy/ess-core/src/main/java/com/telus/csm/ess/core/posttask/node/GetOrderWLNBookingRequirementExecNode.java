package com.telus.csm.ess.core.posttask.node;

import com.telus.csm.ewlnsc.adapter.wbk.domain.GetBookingRequirementResponse;
import com.telus.csm.ewlnsc.delegate.OPShoppingCartDelegate;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class GetOrderWLNBookingRequirementExecNode extends AbstractNode<GetBookingRequirementResponse> {
	private final LoggerUtil logger = LoggerUtil.getLogger(getClass());
	private ShoppingCartContextVO shoppingCartContextVO;
	private Integer opOrderId;
	
	public GetOrderWLNBookingRequirementExecNode(ShoppingCartContextVO shoppingCartContextVO, Integer opOrderId) {		
		super("GetOrderWLNBookingRequirementExecNode");
		this.shoppingCartContextVO = shoppingCartContextVO;
		this.opOrderId = opOrderId;
	}
	
	@Override
	public void run() {
		try {
			logger.info("run", "Get Booking Requirement for the shopping cart [" + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId() + "]. OP orderId [" + opOrderId + "].");
			GetBookingRequirementResponse response = new OPShoppingCartDelegate().getBookingRequirement(shoppingCartContextVO,opOrderId);
			setResult(response);
			processMessages(response);
		} catch(Exception e) {
			String msg = "Failed to get Booking Requirement for the shopping cart [" + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId() + "]. OP orderIdList [" + opOrderId + "].";
			logger.error("", "run", msg, e);
			handleException(e, msg);
		}
	}
		
	private void processMessages(GetBookingRequirementResponse response) {
		if(response != null && response.getMessageList() != null && !response.getMessageList().isEmpty()) {
			processMessages(response.getMessageList());
		}
	}

}
