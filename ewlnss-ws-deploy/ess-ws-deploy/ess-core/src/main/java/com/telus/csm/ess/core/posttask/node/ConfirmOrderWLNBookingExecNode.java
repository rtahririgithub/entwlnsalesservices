package com.telus.csm.ess.core.posttask.node;

import com.telus.csm.ewlnsc.adapter.wbk.domain.ConfirmBookingAdapterResponse;
import com.telus.csm.ewlnsc.delegate.OPShoppingCartDelegate;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class ConfirmOrderWLNBookingExecNode extends AbstractNode <ConfirmBookingAdapterResponse> {
	private final LoggerUtil logger = LoggerUtil.getLogger(getClass());
	private ShoppingCartContextVO shoppingCartContextVO;
	private String bookingId;
	private Integer opOrderId;
	
	public ConfirmOrderWLNBookingExecNode(ShoppingCartContextVO shoppingCartContextVO, String bookingId, Integer opOrderId) {
		super("ConfirmOrderWLNBookingExecNode");
		this.shoppingCartContextVO = shoppingCartContextVO;
		this.bookingId = bookingId;
		this.opOrderId = opOrderId;
	}

	@Override
	public void run() {
		try {
			logger.info("run", "Confirming booking for the shopping cart [" + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId() + "]. OP orderId [" + opOrderId + "]. bookingId [" + bookingId + "]");
			ConfirmBookingAdapterResponse response = new OPShoppingCartDelegate().confirmBooking(shoppingCartContextVO, bookingId,opOrderId);
			setResult(response);
			processMessages(response);
		} catch(Exception e) {
			String msg = "Failed to confirm booking for the shopping cart [" + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId() + "]. OP orderId [" + opOrderId + "].";
			logger.error("", "run", msg, e);
			handleException(e, msg);
		}
	}
	
	private void processMessages(ConfirmBookingAdapterResponse response) {
		if(response != null && response.getMessageList() != null && !response.getMessageList().isEmpty()) {
			processMessages(response.getMessageList());
		}
	}

	public String getBookingId() {
		return bookingId;
	}
}
