package com.telus.csm.ewlnsc.transformer.opshoppingcart;

import java.util.List;

import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductOrderItem;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.OrderCommentVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.TelevisionProductTypeVO;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;

public class OPTVUpdateTransformer extends OPCommonProvideTransformer {

	private static final LoggerUtil logger = LoggerUtil.getLogger(OPHomePhoneUpdateTransformer.class);
	private static final String SERVICE_TYPE = EnterpriseWLNSalesServicesConstants.TTV;
	
	/************************************************/
	/*  transform                                   */
	/************************************************/
	public static ProductOrderItem transform(ShoppingCartContextVO shoppingCartContextVO, 
			                                 CartItemVO cartItemVO, 
			                                 TelevisionProductTypeVO televisionProduct, 
			                                 com.telus.csm.ewlnsc.adapter.oqs.domain.ProductOrder shoppingCartProductOrder, 
			                                 List<OrderCommentVO> orderComments){
		
		logger.debug("transform", "start");
 		
		ProductOrderItem productOrderItem = null;
		
		WirelineOfferProduct wirelineOfferProduct = findWirelineOfferProduct(shoppingCartContextVO, 
				                                                             cartItemVO, 
				                                                             SERVICE_TYPE);

		if(wirelineOfferProduct == null) {
			String errorMsg = "Cannot find a " + SERVICE_TYPE + " WirelineOfferProduct for CartItemRelationId: " + cartItemVO.getCartItemRelationId();
			
			logger.error(errorMsg);
			throw new RuntimeException(errorMsg);
		}

		productOrderItem = populateCommonProductOrderItemForUpdateOrder(cartItemVO, 
				                                                        wirelineOfferProduct, 
				                                                        SERVICE_TYPE, 
				                                                        televisionProduct.getProductOrderType().getOrderTypeCd(), 
				                                                        shoppingCartProductOrder, 
				                                                        orderComments);


		logger.debug("transform", "completed");
		return productOrderItem;
	}
}
