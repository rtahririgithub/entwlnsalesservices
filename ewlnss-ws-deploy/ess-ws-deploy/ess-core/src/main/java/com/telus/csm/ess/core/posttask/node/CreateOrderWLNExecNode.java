package com.telus.csm.ess.core.posttask.node;

import com.telus.csm.ewlnsc.delegate.OPShoppingCartDelegate;
import com.telus.csm.ewlnsc.domain.ExternalOrderDetail;
import com.telus.csm.ewlnsc.domain.OPShoppingCartDelegateResponseVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.transformer.opshoppingcart.CreateOpOrderRequestWrapper;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class CreateOrderWLNExecNode extends AbstractNode <OPShoppingCartDelegateResponseVO> {
	private final LoggerUtil logger = LoggerUtil.getLogger(getClass());
	private ShoppingCartContextVO shoppingCartContextVO;
	private CreateOpOrderRequestWrapper createOrderAdapterRequest;

	public CreateOrderWLNExecNode(ShoppingCartContextVO shoppingCartContextVO, CreateOpOrderRequestWrapper createOrderAdapterRequest) {
		super("CreateOrderWLNExecNode");
		this.shoppingCartContextVO = shoppingCartContextVO;
		this.createOrderAdapterRequest = createOrderAdapterRequest;
	}

	@Override
	public void run() {
		try {
			logger.info("run", "Creating an OP order for the shopping cart [" + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId() + "].");
			OPShoppingCartDelegateResponseVO response = new OPShoppingCartDelegate().createOrder(shoppingCartContextVO, createOrderAdapterRequest.getCreateOrderAdapterRequest());
			shoppingCartContextVO.setOpShoppingCartDelegateResponseVO(response);
			if(response != null) {
				logger.info("run", "OP Shopping cart has been created with ID [" + response.getOpShoppingCartId() 
				+ "] for ESS Shopping cart [" + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId() + "]");
				ExternalOrderDetail externalOrderDetail = new ExternalOrderDetail();
				externalOrderDetail.setExternalOrderSystem(ExternalOrderDetail.EXTERNAL_SYSTEM_OMS);
				externalOrderDetail.setExternalOrderId(String.valueOf(response.getOpShoppingCartId()));
				externalOrderDetail.setRelatedCartItemList(createOrderAdapterRequest.getRelatedCartItemList());
				shoppingCartContextVO.getShoppingCartVO().getExternalOrderDetailList().add(externalOrderDetail);
			}
			setResult(response);
			processMessages(response);
			logger.info("run", "Created an OP order with orderId [" + response.getOpShoppingCartId() + "].");
		} catch(Exception e) {
			String msg = "Failed to create the shopping cart [" + shoppingCartContextVO.getShoppingCartVO().getShoppingCartId() + "]. ";
			logger.error("", "run", msg, e);
			handleException(e, msg);
		}
	}

	private void processMessages(OPShoppingCartDelegateResponseVO response) {
		if(response != null && response.getMessageList() != null && !response.getMessageList().isEmpty()) {
			processMessages(response.getMessageList());
		}
	}

}
