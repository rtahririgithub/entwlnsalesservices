package com.telus.csm.ess.core.posttask.node;

import com.telus.csm.ewlnsc.adapter.domain.CreateInvoiceAdapterResponse;
import com.telus.csm.ewlnsc.delegate.OPShoppingCartDelegate;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class CreateOrderWLNInvoiceExecNode extends AbstractNode <CreateInvoiceAdapterResponse> {
	private final LoggerUtil logger = LoggerUtil.getLogger(getClass());
	private ShoppingCartContextVO shoppingCartContextVO;
	private Integer opOrderId;
	public CreateOrderWLNInvoiceExecNode(ShoppingCartContextVO shoppingCartContextVO, Integer opOrderId) {
		super("CreateOrderWLNInvoiceExecNode");
		this.shoppingCartContextVO = shoppingCartContextVO;
		this.opOrderId = opOrderId;
	}

	@Override
	public void run() {
		try {
			logger.info("run", "Creating an invoice for the shopping cart [" + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId() + "]. OP orderId [" + opOrderId + "].");
			CreateInvoiceAdapterResponse response = new OPShoppingCartDelegate().createInvoice(shoppingCartContextVO,opOrderId);
			shoppingCartContextVO.setCreateInvoiceAdapterResponse(response);
			setResult(response);
			processMessages(response);
			logger.info("run", "Created an invoice [" + response.getInvoiceNo() + "] for the shopping cart [" + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId() + "]. OP orderId List [" + opOrderId + "].");
		} catch(Exception e) {
			String msg = "Failed to create invoice for the shopping cart [" + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId() + "]. OP orderId [" + opOrderId + "].";
			logger.error("", "run", msg, e);
			handleException(e, msg);
		}
	}
	
	private void processMessages(CreateInvoiceAdapterResponse response) {
		if(response != null && response.getMessageList() != null && !response.getMessageList().isEmpty()) {
			processMessages(response.getMessageList());
		}
	}

}
