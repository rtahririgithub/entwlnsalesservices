package com.telus.csm.ewlnsc.rules.shoppingcart;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.domain.ShoppingProfileVO;
import com.telus.csm.ewlnsc.helper.ValidationUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class UnsupportedElementRule extends AbstractSpecification<ShoppingCartContextVO,ShoppingCartValidationTraceVO>{
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(UnsupportedElementRule.class);
	
	

	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO contextVO, List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(contextVO, traces);
		
		boolean isSatisfied=true;
		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(UnsupportedElementRule.class.getName());
		
		String functionName="UnsupportedElementRule.isSatisfiedBy()";
		logger.enter(functionName);
		
		List<String> unsupportedFieldList = new ArrayList<String>();
		
		ShoppingCartVO shoppingCartVO = contextVO.getShoppingCartVO();
		if(shoppingCartVO!=null){
			
			if ( shoppingCartVO.getShoppingCartId()!=null ) {
				if ( shoppingCartVO.getShoppingCartId().startsWith(ESDG_INTERACTION_ID_PREFIX)==false ) {
					unsupportedFieldList.add("shoppingCart.shoppingCartId is in wrong pattern ("+ ESDG_INTERACTION_ID_PREFIX+"###" + "): " + shoppingCartVO.getShoppingCartId());
				}
			}

			
			/**
			 * Validate the following elements on this rule
			 * 		shoppingCart.shoppingProfile.userProfilesalesRepInternalId,shoppingCart.shoppingProfile.userProfile.channelOrganizationInternalId must be numeric and greater than zero
			 * 		validate the elements of the outletAssociatedProvinces if they are passed in the request.
			 */
			if(shoppingCartVO.getShoppingProfile()!=null){
				ShoppingProfileVO shoppingProfile = shoppingCartVO.getShoppingProfile();
				
				/**
				 * validate Agent profile fields.
				 */
				if(shoppingProfile.getAgentProfile()!=null){
					if(!StringUtils.isNumeric(shoppingProfile.getAgentProfile().getChannelOrganizationInternalId())){
						unsupportedFieldList.add("{shoppingCart.shoppingProfile.agentProfile.channelOrganizationInternalId} must be a valid number");
					}
					
					//validate the provinces to be valid if the element outletAssociatedProvinces is passed.
					if(!CollectionUtils.isEmpty(shoppingProfile.getAgentProfile().getOutletAssociatedProvinces())){
						for(String province : shoppingProfile.getAgentProfile().getOutletAssociatedProvinces()){
							if(!StringUtils.isEmpty(province)){
								boolean isValidProvince = ValidationUtil.validateProvinceCode(province);
								if(!isValidProvince){
									unsupportedFieldList.add("{shoppingCart.shoppingProfile.agentProfile.outletAssociatedProvinces} contains invalid province code");
								}
							}
						}
					}
				}
				
				/**
				 * validate user profile fields.
				 */
				if(shoppingProfile.getUserProfile()!=null){
					if(!StringUtils.isNumeric(shoppingProfile.getUserProfile().getChannelOrganizationInternalId())){
						unsupportedFieldList.add("{shoppingCart.shoppingProfile.userProfile.channelOrganizationInternalId} must be a valid number");
					}
					
					//validate the provinces to be valid if the element outletAssociatedProvinces is passed.
					if(!CollectionUtils.isEmpty(shoppingProfile.getUserProfile().getOutletAssociatedProvinces())){
						for(String province : shoppingProfile.getUserProfile().getOutletAssociatedProvinces()){
							if(!StringUtils.isEmpty(province)){
								boolean isValidProvince = ValidationUtil.validateProvinceCode(province);
								if(!isValidProvince){
									unsupportedFieldList.add("{shoppingCart.shoppingProfile.userProfile.outletAssociatedProvinces} contains invalid province code");
								}
							}
						}
					}
				}
				
				if(!CollectionUtils.isEmpty(shoppingCartVO.getCartItemList())) {
					
					for(CartItemVO cartItem : shoppingCartVO.getCartItemList()) {
						if ( cartItem.getCartItemId()!=null ) {
							if (cartItem.isSalesOrderItem() ) { //order type cartItem
								if ( cartItem.getCartItemId().startsWith(ESDG_CONTEXT_ID_PREFIX)==false ) {
									unsupportedFieldList.add("shoppingCart.CartItem.CartItemId is in wrong pattern ("+ ESDG_CONTEXT_ID_PREFIX+"###" + "): " + cartItem.getCartItemId() + ", for saleOrderType item" );
								}	
							} else {
								if ( cartItem.getCartItemId().startsWith(ESDG_SALES_ITEM_ID_PREFIX)==false ) {
									unsupportedFieldList.add("shoppingCart.CartItem.CartItemId is in wrong pattern ("+ ESDG_SALES_ITEM_ID_PREFIX+"###" + "): " + cartItem.getCartItemId() + ", for non saleOrderType item");
								}	
							}
						}
					}
					
				}				
				

				
				
			}
		}
		
		if(!unsupportedFieldList.isEmpty()){
			isSatisfied=false;
			trace.setValidationPassedInd(isSatisfied);
			ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.UNSUPPORTED_ELEMENTS_VALIDATION);
			shoppingCartValidationError.setMessage(this.getClass().getSimpleName() + ": The following elements are invalid from the request: " + unsupportedFieldList.toString());
			trace.setErrors(shoppingCartValidationError);
			traces.add(trace);
			logger.error(functionName + " failed due to following fields invalid from the request= " + unsupportedFieldList.toString()); 
		}
		
		logger.exit(functionName);
		
		return isSatisfied;
	}
	
}
