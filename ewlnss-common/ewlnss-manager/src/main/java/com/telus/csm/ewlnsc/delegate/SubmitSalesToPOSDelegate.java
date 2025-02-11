package com.telus.csm.ewlnsc.delegate;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.telus.csm.ess.rest.domain.ShoppingCartIdentifier;
import com.telus.csm.ess.rest.domain.SubmitSalesToPOSRequest;
import com.telus.csm.ess.rest.domain.SubmitSalesToPOSResponse;
import com.telus.csm.ewlnsc.adapter.ICorporateStoresPOSServiceAdapter;
import com.telus.csm.ewlnsc.adapter.IWirelineSalesEJBAdapter;
import com.telus.csm.ewlnsc.adapter.ccm.domain.SubmitTransactionToPOSAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.SubmitTransactionToPOSAdapterResponse;
import com.telus.csm.ewlnsc.adapter.woscs.domain.SubmitOrderAdapterRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.SubmitOrderAdapterResponse;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.SubmitWirelineOrderRequestVO;
import com.telus.csm.ewlnsc.helper.ShoppingCartContextHelper;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.ucss.wirelinesales.domain.OrderStatus;
import com.telus.ucss.wirelinesales.ejb.OrderManager;
import com.telus.ucss.wirelinesales.exception.WLNSalesApplicationException;

@Component
@Scope(SCOPE_PROTOTYPE)
public class SubmitSalesToPOSDelegate  {

	private static final LoggerUtil logger = LoggerUtil.getLogger(SubmitSalesToPOSDelegate.class);

	private static final String OMS_ORDER_SUBMISSION = "OMS";
	
	
	/**
	 * 
	 * @param submitSalesToPOSRequestVO
	 * @return
	 * @throws WLNSalesApplicationException 
	 */
	public  SubmitSalesToPOSResponse execute(final SubmitSalesToPOSRequest submitSalesToPOSRequestVO)  {
		SubmitSalesToPOSResponse result = new SubmitSalesToPOSResponse();
			final SubmitTransactionToPOSAdapterRequest request = new SubmitTransactionToPOSAdapterRequest();


			request.setShoppingCartId(submitSalesToPOSRequestVO.getShoppingCartId());
			request.setPosToken(submitSalesToPOSRequestVO.getPointOfSaleIdentifier().getTokenCd());
			
		ICorporateStoresPOSServiceAdapter adapter = AdapterFactory.getAdapter(ICorporateStoresPOSServiceAdapter.class);
			
		SubmitTransactionToPOSAdapterResponse response = adapter.submitTransactionToPOS(request);
			if (response.isMsgHasError()){
				logger.error("Submit order failed.");
			} else {
				logger.info("submitOrder", "Order submitted successfully.");
//				try {
//					wirelineSalesEJBAdapter.updateOrderStatus(orderId, getOrderStatus(orderId, ORDER_STATUS_PROCESS_SUBMIT), ORDER_STATUS_PROCESS_PENDING );
//					wirelineSalesEJBAdapter.insertComment(orderId, "Automatic Order Status update to Submit", OrderManager.COMMENT_TYPE_USER);
//				} catch (WLNSalesApplicationException e) {
//					logger.error("Failed to update status in VESTA to SUBMIT for orderId=" + orderId + " " + e);
//				}
			}				
		
			ShoppingCartIdentifier shoppingCartIdentifier = new ShoppingCartIdentifier();
			shoppingCartIdentifier.setShoppingCartId(submitSalesToPOSRequestVO.getShoppingCartId());
			shoppingCartIdentifier.setSalesTransactionId(submitSalesToPOSRequestVO.getOperationHeader().getSalesTransactionId());
			
			result.setShoppingCartIdentifier(shoppingCartIdentifier);
			
		return result;
	}
	
}