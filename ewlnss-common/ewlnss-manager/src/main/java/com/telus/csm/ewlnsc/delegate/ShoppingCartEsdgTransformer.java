package com.telus.csm.ewlnsc.delegate;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.telus.csm.esdg.domain.EsdgInteractionDO_1;
import com.telus.csm.esdg.domain.EsdgOrderDO_1;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingProfileVO;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsc.util.ShoppingCartStatus;

/**
 * Transform from ShoppingCartVO / CartItemVO to Esgd Object
 * @author Michael Liao
 *
 */
public class ShoppingCartEsdgTransformer {

	/**
	 * Transform ShoppingCartVO to EsdgInteractionDO
	 * @param shoppingCartVO
	 * @param serializeToJson
	 * @return
	 */
	public static EsdgInteractionDO_1 transformToInteractionDO( ShoppingCartVO shoppingCartVO, boolean serializeToJson ) {
		
		EsdgInteractionDO_1 interactionDO = new EsdgInteractionDO_1();
		
		interactionDO.setSalesInteractionId( shoppingCartVO.getShoppingCartId() );
		interactionDO.setInteractionTypeCD( shoppingCartVO.getCartType() ); 
		
//		if(CollectionUtils.isNotEmpty(shoppingCartVO.getExternalOrderDetailList())){
//			interactionDO.setExternalSalesRefId(shoppingCartVO.getExternalOrderDetailList().get(0).getExternalOrderId());
//		}
		
//		if (StringUtils.isBlank(interactionDO.getExternalSalesRefId())) {
//			interactionDO.setExternalSalesRefId("NA");
//		}
		
		//interactionDO.setExternalTxnRefID("NA"); //TODO NO USE
		
		//BillingAccountNum is mandatory
		if (shoppingCartVO.getBillingAccount()!=null 
				&& StringUtils.isNotBlank(shoppingCartVO.getBillingAccount().getBillingAccountNumber() ) ) {
			//TODO - what about shoppingCartVO.getBillingAccount().accountMasterSourceTypeCd
			interactionDO.setBillingAccountNum( shoppingCartVO.getBillingAccount().getBillingAccountNumber() );
		} else {
			interactionDO.setBillingAccountNum("Unknown" ); 
		}
		
		ShoppingProfileVO profile = shoppingCartVO.getShoppingProfile();
		
		if (profile!=null && profile.getUserProfile()!=null ) {
			interactionDO.setChannelOrgID( profile.getUserProfile().getChannelOrganizationNumber());
			interactionDO.setChannelOrgTypeCD( profile.getUserProfile().getChannelOrganizationTypeCd());
			interactionDO.setChannelPersonID( profile.getUserProfile().getSalesRepId()); //TODO double check the mapping
		}
		
		if (serializeToJson) {
			interactionDO.setJsonSalesTXNData(JsonUtil.getJsonFromObject( shoppingCartVO) );
		}
		
		if ( StringUtils.isBlank(shoppingCartVO.getStatus())) {
			shoppingCartVO.setStatus( ShoppingCartStatus.PREPARED ); //default salesInteraction status
		} 
		interactionDO.setStatus( shoppingCartVO.getStatus() );
		
		interactionDO.setWriteToDatabase(true);
		
		return interactionDO;
	}
	
	/**
	 * Transform cartItemVO to EsdgOrderDO
	 * 
	 * @param cartItemVO
	 * @return
	 */
	public static EsdgOrderDO_1 transformToOrderDO( EsdgInteractionDO_1 interactionDO, CartItemVO cartItemVO ) {
		
		EsdgOrderDO_1 esdgOrder = new EsdgOrderDO_1();

		esdgOrder.setSalesInteractionId( interactionDO.getSalesInteractionId() );
		esdgOrder.setSalesContextId( cartItemVO.getCartItemId() );
		esdgOrder.setExternalKey( cartItemVO.getCartItemRelationId() );

		//this will be used to update sales_Interaction table during saving Order
		esdgOrder.setBillingAccountNumber( interactionDO.getBillingAccountNum() ); 
		
		esdgOrder.setSalesInteractionStartTimeInMills( 0 ); //TODO salesInteractionStartTimeInMills
		esdgOrder.setDataGenerationTimeInMills( System.currentTimeMillis() );  
		esdgOrder.setWriteToDatabase( true );  				

		esdgOrder.setBrandCd("TELUS"); //hard coded
		esdgOrder.setSalesContextExternalRefId("NAOrderExtRefId");
		esdgOrder.setSubscriberPhoneNumber("NAOrderTN");
		
		String status = esdgOrder.getOrderStatus();
		String newStatus = cartItemVO.getStatus();
		if( status == null || !status.equalsIgnoreCase(newStatus) ) {	
			esdgOrder.setOrderStatus(newStatus);
			esdgOrder.addOrderStatus(newStatus, esdgOrder.getDataGenerationTimeInMills());	
		}

		esdgOrder.setJsonOrder( JsonUtil.getJsonFromObject(cartItemVO) );
		return esdgOrder;
	}
	
	public static CartItemVO transformToCartItem(EsdgOrderDO_1 order) {
		CartItemVO cartItemVO = JsonUtil.parseJsonToObject( order.getJsonOrder(), CartItemVO.class );
		cartItemVO.setStatus( order.getOrderStatus() );
		return cartItemVO;
	}
	
	public static ShoppingCartVO transformToShoppingCart(EsdgInteractionDO_1 interaction ) {
		
		ShoppingCartVO shoppingCart = JsonUtil.parseJsonToObject( interaction.getJsonSalesTXNData(),  ShoppingCartVO.class );
		shoppingCart.setStatus( interaction.getStatus() );
		return shoppingCart;
	}
	
}
