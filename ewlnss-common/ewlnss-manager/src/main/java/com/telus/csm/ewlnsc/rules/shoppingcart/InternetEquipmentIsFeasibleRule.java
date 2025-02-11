package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnsc.delegate.GetAvailableProductItemDelegate;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.ExistingFFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.FFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.GetAvailableProductItemDelegateResponse;
import com.telus.csm.ewlnsc.domain.InternetProductTypeVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.domain.product.AvailableInternetProductItemVO;
import com.telus.csm.ewlnsc.helper.EnterpriseWLNOrderUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ServiceAccess;


public class InternetEquipmentIsFeasibleRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	private CartItemVO cartItem;

	public InternetEquipmentIsFeasibleRule(CartItemVO cartItem) {
		this.cartItem = cartItem;
	}
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(InternetEquipmentIsFeasibleRule.class);

	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO shoppingCartContextVO, List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(shoppingCartContextVO, traces);
		boolean isSatisfied=true;
		String functionName="InternetEquipentIsFeasibleRule.isSatisfied";
		logger.enter(functionName);
		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(InternetEquipmentIsFeasibleRule.class.getName());		
		List<String> equipmentValidationFailedList = new ArrayList<String>();
		
		if(cartItem!=null && cartItem.isSalesOrderItem()){
			if(cartItem.getProducts().getInternetProduct()!=null){
				InternetProductTypeVO internetProduct = cartItem.getProducts().getInternetProduct();				
				equipmentValidationFailedList.addAll(validateInternetEquipment(internetProduct.getEquipments(),shoppingCartContextVO,EnterpriseWLNSalesServicesConstants.HSIC));
			}
			
			if(!CollectionUtils.isEmpty(equipmentValidationFailedList)){
				isSatisfied = false;
				trace.setValidationPassedInd(isSatisfied);
				trace.setErrors(ShoppingCartValidationErrors.getErrorForCodeAndCustomeMessage(ShoppingCartValidationErrorCodes.INTERNET_EQUIPMENT_FEASIBLE, "The following Internet Equipment Material Item Code(s) are not a supported equipment according to the feasibility service." + EnterpriseWLNOrderUtil.getFormatedStringList(equipmentValidationFailedList)));
				trace.setCartItemRelationId(cartItem.getCartItemRelationId());
				traces.add(trace);
			}
			
		}
		
		logger.exit(functionName);
		return isSatisfied;
	}

	/**
	 * This method will validate the requested buyEquipment / rentEquipment MIC code against the FeasibilityResponse
	 * 
	 */
	private List<String> validateInternetEquipment(List<FFHEquipmentTypeVO> cartEquipmentList,ShoppingCartContextVO shoppingCartContextVO,String productType) {
		List<String> nonValidMicCodeList = new ArrayList<String>();
		CheckProductFeasibilityAdapterResponse feasibilityResponseVO = shoppingCartContextVO.getFeasibilityResponseVO();
		
		if(!CollectionUtils.isEmpty(cartEquipmentList)){
			//checking the buyEquipent or rentEquipment if the MIC code matches the MIC code returned by FeasibilityResponse
			for(FFHEquipmentTypeVO cartEquipment : cartEquipmentList){
				if(cartEquipment.getAcquisitionType()!=null && ((cartEquipment.getAcquisitionType().isBuyIndicator()!=null && cartEquipment.getAcquisitionType().isBuyIndicator()) || (cartEquipment.getAcquisitionType().isRentalEquipmentIndicator()!=null && cartEquipment.getAcquisitionType().isRentalEquipmentIndicator()))){
					//check FeasibilityResponse
					if(!micCodeIsFoundInFeasibilityEquipment(cartEquipment.getMaterialItemCode(),feasibilityResponseVO)){
						nonValidMicCodeList.add("Cart Equipment with material Item Code=" + cartEquipment.getMaterialItemCode() + " was not found among the equipments returned by Feasibility Service.");
					}
				}
			}
		}else{
			//check the existing equipment returned by AvailableItemDelegateResponse
			GetAvailableProductItemDelegate delegate = new GetAvailableProductItemDelegate();
			GetAvailableProductItemDelegateResponse availableProductItemDelegateResponse = delegate.execute(shoppingCartContextVO.getShoppingCartVO().getOperationHeader().getSalesTransactionId(), shoppingCartContextVO);
			if(availableProductItemDelegateResponse!=null && availableProductItemDelegateResponse.getResponse()!=null){
				
				if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(productType)){
					if(!CollectionUtils.isEmpty(availableProductItemDelegateResponse.getResponse().getInternetProductItems())){
						for(AvailableInternetProductItemVO availableInternetItem : availableProductItemDelegateResponse.getResponse().getInternetProductItems()){
							if(!CollectionUtils.isEmpty(availableInternetItem.getExistingEquipment())){
								for(FFHEquipmentTypeVO existingEquipment : availableInternetItem.getExistingEquipment()){
									if(!micCodeIsFoundInFeasibilityEquipment(existingEquipment.getMaterialItemCode(), feasibilityResponseVO)){
										nonValidMicCodeList.add("Existing Internet Equipment with material Item Code=" + existingEquipment.getMaterialItemCode() + " was not found among the equipments returned by Feasibility Service.");
									}
								}
							}
						}
					}
				}
			}
			
			
		}
		
		return nonValidMicCodeList;
	}

	private boolean micCodeIsFoundInFeasibilityEquipment(String micCode,CheckProductFeasibilityAdapterResponse feasibilityResponseVO) {
		boolean micCodeIsFound = false;
		
		if(feasibilityResponseVO!=null && feasibilityResponseVO.getServiceAccessList()!=null && !CollectionUtils.isEmpty(feasibilityResponseVO.getServiceAccessList().getServiceAccess())){
			for(ServiceAccess serviceAccess : feasibilityResponseVO.getServiceAccessList().getServiceAccess()){
				if(serviceAccess.getCustomerPremiseEquipmentManufacturerIdentifierCodeList()!=null && !CollectionUtils.isEmpty(serviceAccess.getCustomerPremiseEquipmentManufacturerIdentifierCodeList().getCustomerPremiseEquipmentManufacturerIdentifierCode())){
					for(String feasibilityMicCode : serviceAccess.getCustomerPremiseEquipmentManufacturerIdentifierCodeList().getCustomerPremiseEquipmentManufacturerIdentifierCode()){
						if(micCode.equalsIgnoreCase(feasibilityMicCode)){
							micCodeIsFound = true;
							break;
						}
					}
				}
			}
		}
		
		return micCodeIsFound;
	}
	
	
	
}
