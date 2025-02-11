package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.telus.csm.ewlnsc.domain.AgentProfileVO;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.domain.UserProfileVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class MandatoryElementValidationRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	private static final LoggerUtil logger = LoggerUtil.getLogger(MandatoryElementValidationRule.class);
	
	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO contextVO, List<ShoppingCartValidationTraceVO> traces){
		super.isSatisfiedBy(contextVO, traces);
		
		boolean isSatisfiedBy = true;
		String functionName = "MandatoryElementValidationRule.execute()";
		logger.enter(functionName);
		List<String> missingFieldList = new ArrayList<String>();
		
		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(MandatoryElementValidationRule.class.getName());
		
		//Operation Header
		if(contextVO.getShoppingCartVO()!=null){
			
			
			ShoppingCartVO shoppingCartVO = contextVO.getShoppingCartVO();
			
			/**
			 * from previous rule WirelineOrderWithoutServiceAddressRule
			 */
			
			validateOrderWithoutServiceAddress(shoppingCartVO,missingFieldList);
			
			
			if(shoppingCartVO.getOperationHeader()==null){
				missingFieldList.add("OperationHeader is missing from the request.");
			}else{
				if(StringUtils.isEmpty(shoppingCartVO.getOperationHeader().getSalesTransactionId())){
					missingFieldList.add("OperationHeader.salesTransactionId is missing from the request.");
				}
			}
			
			if(shoppingCartVO.getShoppingProfile()==null){
				missingFieldList.add("shoppingProfile is missing from the request");
			}else{
				if(shoppingCartVO.getShoppingProfile().getAgentProfile()==null && shoppingCartVO.getShoppingProfile().getUserProfile()==null){
					missingFieldList.add("shoppingCart.shoppingProfile.getAgentProfile and shoppingCart.getShoppingProfile.getUserProfile are missing from the request.");
				}else{
					if(shoppingCartVO.getShoppingProfile().getUserProfile()!=null){
						UserProfileVO userProfile = shoppingCartVO.getShoppingProfile().getUserProfile();
						validateUserProfile(userProfile,missingFieldList);
					}
					
					if(shoppingCartVO.getShoppingProfile().getAgentProfile()!=null){
						AgentProfileVO agentProfile = shoppingCartVO.getShoppingProfile().getAgentProfile();
						validateAgentProfile(agentProfile,missingFieldList);
					}
				}
			}
			
			if (shoppingCartVO.getBillingAccount()==null) {
				missingFieldList.add("shoppingCart.billingAccount is missing from the request.");
			} else {
				if(shoppingCartVO.getBillingAccount().getAccountMasterSourceTypeCd()==null) {
					missingFieldList.add("shoppingCart.billingAccount.accountMasterSourceTypeCd is missing from the request.");
				}
			}
				
			if (CollectionUtils.isEmpty(shoppingCartVO.getCartContextTypeList())) {
				missingFieldList.add("shoppingCart.CartContextTypeList is missing from the request");
			} else {

				if(!CollectionUtils.isEmpty(shoppingCartVO.getCartItemList())){
					for(CartItemVO cartItem : shoppingCartVO.getCartItemList()){
						if(CollectionUtils.isEmpty(cartItem.getCartItemContextTypeList())){
							missingFieldList.add("shoppingCart.CartItem.CartItemContextTypeList is missing from the request." + " cartItemRelationId: " + cartItem.getCartItemRelationId()); 
						}
						if (StringUtils.isBlank(cartItem.getCartItemRelationId())) {
							missingFieldList.add("shoppingCart.CartItem.CartItemRelationId is missing from the request."); 
						}
					}
					
					validateCartContextTypeAll( shoppingCartVO.getCartContextTypeList(), shoppingCartVO.getCartItemList(), missingFieldList);
					
				}				

			}
			
            if (shoppingCartVO.getFulfillmentOption()==null) {
            	missingFieldList.add("shoppingCart.FulfillmentOption is missing from the request");            	
            }
            
		}else{
			missingFieldList.add("shoppingCart element is missing from the request");
		}
		
		if(!missingFieldList.isEmpty()){
			//fail the rule here. 
			isSatisfiedBy=false;
			trace.setValidationPassedInd(isSatisfiedBy);
			ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.MISSING_INPUT_VALIDATION);
			shoppingCartValidationError.setMessage(this.getClass().getSimpleName() + ": Following Elements are missing from the request: " + missingFieldList.toString());
			trace.setErrors(shoppingCartValidationError);
			traces.add(trace);
			logger.error(functionName + " failed due to following fields missing from the request= " + missingFieldList.toString());
		}
		
		logger.exit(functionName);
		return isSatisfiedBy;
	}

	private void validateOrderWithoutServiceAddress(ShoppingCartVO shoppingCartVO, List<String> missingFieldList) {
		String functionName = "validateOrderWithoutServiceAddress";
		boolean hasWlnOrderedProduct=false;
		if(shoppingCartVO!=null && !CollectionUtils.isEmpty(shoppingCartVO.getCartItemList())){
			for(CartItemVO cartItem : shoppingCartVO.getCartItemList()){
				if(cartItem.isSalesOrderItem()) {
					if(cartItem.getProducts()!=null){
						if(cartItem.getProducts().getInternetProduct()!=null){
							hasWlnOrderedProduct=true;
							break;
						}
						if(cartItem.getProducts().getHomePhoneProduct()!=null){
							hasWlnOrderedProduct=true;
							break;
						}
						if(cartItem.getProducts().getTelevisionProduct()!=null){
							hasWlnOrderedProduct=true;
							break;
						}
					}
				}
				
			}
		}
		
		if(hasWlnOrderedProduct && (shoppingCartVO.getServiceAddress()==null || (StringUtils.isEmpty(shoppingCartVO.getServiceAddress().getCityCode())) ||
				StringUtils.isEmpty(shoppingCartVO.getServiceAddress().getProvinceCode()) || StringUtils.isEmpty(shoppingCartVO.getServiceAddress().getServiceAddressId()))){
			logger.error(functionName+  " service address is mandatory when cartItem includes wireline product(s)");
			missingFieldList.add("service address is mandatory when cartItem includes wireline product(s)");
		}
	}

	private void validateAgentProfile(AgentProfileVO agentProfile, List<String> missingFieldList) {
		if (StringUtils.isEmpty(agentProfile.getSalesRepInternalId())) {
			missingFieldList.add("shoppingCart.shoppingProfile.agentProfile.salesRepInternalId is missing from the request.");
		}
		if (StringUtils.isEmpty(agentProfile.getChannelOrganizationInternalId())) {
			missingFieldList.add("shoppingCart.shoppingProfile.agentProfile.channelOrganizationInternalId is missing from the request.");
		}
		if (agentProfile.getSalesRepAssociatedOutlet().isEmpty()
				|| StringUtils.isEmpty(agentProfile.getSalesRepAssociatedOutlet().get(0).getOutletInternalId())) {
			missingFieldList.add("shoppingCart.shoppingProfile.agentProfile.salesRepAssociatedOutlet.outletInternalId is empty");
		}
		if (agentProfile.getSalesRepAssociatedOutlet().isEmpty()
				|| StringUtils.isEmpty(agentProfile.getSalesRepAssociatedOutlet().get(0).getChannelOutletId())) {
			missingFieldList.add("shoppingCart.shoppingProfile.agentProfile.salesRepAssociatedOutlet.channelOutletId is empty");
		}

	}

	private void validateUserProfile(UserProfileVO userProfile, List<String> missingFieldList) {
		if (StringUtils.isEmpty(userProfile.getSalesRepInternalId())) {
			missingFieldList.add("shoppingCart.shoppingProfile.userProfile.salesRepInternalId is missing from the request.");
		}
		if (StringUtils.isEmpty(userProfile.getChannelOrganizationInternalId())) {
			missingFieldList.add("shoppingCart.shoppingProfile.userProfile.channelOrganizationInternalId is missing from the request.");
		}
		if (userProfile.getSalesRepAssociatedOutlet().isEmpty()
				|| StringUtils.isEmpty(userProfile.getSalesRepAssociatedOutlet().get(0).getOutletInternalId())) {
			missingFieldList.add("shoppingCart.shoppingProfile.userProfile.salesRepAssociatedOutlet.outletInternalId is empty");
		}
		if (userProfile.getSalesRepAssociatedOutlet().isEmpty()
				|| StringUtils.isEmpty(userProfile.getSalesRepAssociatedOutlet().get(0).getChannelOutletId())) {
			missingFieldList.add("shoppingCart.shoppingProfile.userProfile.salesRepAssociatedOutlet.channelOutletId is empty");
		}
	}
	
	private void validateCartContextTypeAll(List<String> cartContextTypeList, List<CartItemVO> cartItemList, List<String> missingFieldList) 	{
		for(String cartContextType: cartContextTypeList) {
			if (cartContextType.equalsIgnoreCase(EnterpriseWLNSalesServicesConstants.SHOPPING_CART_CTX_TYPE_ALL)) {
				for(CartItemVO cartItem : cartItemList){
					if(cartItem.isSalesOrderItem()) {
						boolean cartItemContextAllBoolean = false;
						for (String cartItemContextType: cartItem.getCartItemContextTypeList()) {
							if (cartItemContextType.equalsIgnoreCase(EnterpriseWLNSalesServicesConstants.SHOPPING_CART_ITEM_CTX_TYPE_ALL)) {						
								cartItemContextAllBoolean = true;
							}
						}
						if (!cartItemContextAllBoolean) {
							missingFieldList.add("shoppingCart.CartContextType contains ALL but shoppingCart.CartItem.ContextType doesn't contains ALL " + " cartItemRelationId: " + cartItem.getCartItemRelationId());	
						}
					}
				}
			}

		}
	}	
	
	
}
