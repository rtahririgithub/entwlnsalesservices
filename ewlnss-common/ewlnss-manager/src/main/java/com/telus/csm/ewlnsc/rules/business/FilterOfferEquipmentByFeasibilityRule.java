package com.telus.csm.ewlnsc.rules.business;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnsc.domain.GetOffersRequestVO;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ServiceAccess;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OfferProductHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferFilter;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineEquipment;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineEquipmentItem;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;

public class FilterOfferEquipmentByFeasibilityRule extends AbstractSpecification<Offer,TraceVO> {

	private static final LoggerUtil logger = LoggerUtil.getLogger(FilterOfferEquipmentByFeasibilityRule.class);

	private SalesOfferCommonVO salesOfferCommonVO;

	public FilterOfferEquipmentByFeasibilityRule(SalesOfferCommonVO salesOfferCommonVO) {
		this.salesOfferCommonVO = salesOfferCommonVO;
	}

	@Override
	public boolean isSatisfiedBy(Offer o, List<TraceVO> traces) {
		logger.debug("isSatisfiedBy", WLNOfferUtil.getRuleContextInformation(o));
		
		CheckProductFeasibilityAdapterResponse checkProductFeasibilityAdapterResponse = salesOfferCommonVO.getCheckFeasibilityResponseVO();
		
		// If Feasibility is unavailable or fails, do not filter any modem.
		if (checkProductFeasibilityAdapterResponse == null) {
			// bypass this rule when the call to CheckFeasibility failed
			logger.debug("FilterOfferEquipmentByFeasibilityRule", "Passed");

			return true;
		}
		
		// If feasibility is not returning equipment, do NOT filter out any equipments
		if (checkProductFeasibilityAdapterResponse.getServiceAccessList() == null || checkProductFeasibilityAdapterResponse.getServiceAccessList().getServiceAccess().isEmpty()) {
			logger.debug("FilterOfferEquipmentByFeasibilityRule", "Passed");

			return true;
		}
		
		//Alejandro - May 23,2018 - we only apply this rule when we have both productList and productComponentList for HSIC
		if(salesOfferCommonVO.getOffersRequestVO()!=null){
			GetOffersRequestVO requestVO = salesOfferCommonVO.getOffersRequestVO();
			WirelineOfferFilter wlnOfferFilter = requestVO.getSalesOfferCriteria().getOfferFilter();
			if(wlnOfferFilter.getProductList()==null || wlnOfferFilter.getProductList().isEmpty()){
				logger.debug("FilterOfferEquipmentByFeasibilityRule", "Rule skipped since there is no ProductList in the request");
				return true;
			}else{
				//Checking if there is existing HSIC in the request and productComponentList is null
				for(OfferProductHeader product : wlnOfferFilter.getProductList()){
					if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(product.getProductTypeCd()) && (product.getProductComponentList()==null || product.getProductComponentList().isEmpty())){
						logger.debug("FilterOfferEquipmentByFeasibilityRule", "Skipping rule since there is HSIC product in the request but no productComponentList.");
						return true;
					}
				}
			}
		}
		
		ArrayList<String> validEquipments = new ArrayList<String>();
		for (ServiceAccess serviceAccess : checkProductFeasibilityAdapterResponse.getServiceAccessList().getServiceAccess()) {
			if (serviceAccess.getCustomerPremiseEquipmentManufacturerIdentifierCodeList() != null) {
				validEquipments.addAll(serviceAccess.getCustomerPremiseEquipmentManufacturerIdentifierCodeList().getCustomerPremiseEquipmentManufacturerIdentifierCode());
			}
		}
		
		
		
		if (validEquipments.isEmpty()) {
			logger.debug("FilterOfferEquipmentByFeasibilityRule", "Passed");

			return true; 
		}
		
		//check if the Offer is re-contract eligible to skip the rule
		// 2018 June Exception release for TTV recontracting
		// skip the rule only for like-for-like recontract instead of all recontract scenarios
		//if(salesOfferCommonVO.isCustomerEligibleForRecontract() &&  WLNOfferUtil.isOfferForRecontracting(offer, salesOfferCommonVO)){
		if(salesOfferCommonVO.isCustomerEligibleForRecontract() &&  WLNOfferUtil.isOfferForLikeForLikeRecontracting(o, salesOfferCommonVO)){
			logger.debug("FilterOfferEquipmentByFeasibilityRule", "Passed");
			return true;
		}

		//list of product names with install type = "FW"
		List<String> productsWithInstallTypeFW = new ArrayList<String>();
		if (salesOfferCommonVO.getCheckFeasibilityResponseVO() != null) {
			productsWithInstallTypeFW = salesOfferCommonVO.getCheckFeasibilityResponseVO().getInstallTypeFW();	
		}
		
		//Filter out equipment with delivery method other than "INSTALLER" and product feasibility install type is "FW"
		for (WirelineOfferProduct product : o.getOfferProduct().getWirelineOfferProductList()) {
			for (WirelineEquipment equipment : product.getWirelineEquipmentList()) {
				filterPurchaseEquipmentByDeliveryMethod(traces, product, equipment, productsWithInstallTypeFW);
			}
		}

		// Modem filtering (Only HS) based on Feasibility
		for (WirelineOfferProduct product : o.getOfferProduct().getWirelineOfferProductList()) {
			if (!Constants.OMS_PRODUCT_HSIC.equals(product.getProductTypeCode())) {
				logger.debug("FilterOfferEquipmentByFeasibilityRule", "Passed");

				return true;
			}
			
			if (!WLNOfferUtil.isNoChangeOfferProduct(product)){
				logger.debug("FilterOfferEquipmentByFeasibilityRule", "Passed");

				return true;
			}
			
			List<WirelineEquipment> equipmentList = product.getWirelineEquipmentList();
			for (WirelineEquipment equipment : equipmentList) {
				//Wifi Boost - do not apply filter to equipment with serviceTypeCode in (DWSP, DWEP, CAXP, MAXP)
				if (!WLNOfferUtil.isAccessoryEquipment(equipment)) {

					processEquipment(o, traces, validEquipments, product, equipment);
				}

			}
		}

		logger.debug("FilterOfferEquipmentByFeasibilityRule", "Passed");

		return true;
	}

	private void filterPurchaseEquipmentByDeliveryMethod(List<TraceVO> traces, WirelineOfferProduct product,
			WirelineEquipment equipment, List<String> productsWithInstallTypeFW) {
		
		//only for purchase equipment   
		List<WirelineEquipmentItem> purchaseList = equipment.getPurchaseEquipmentList();
		equipment.setPurchaseEquipmentList(null);
		for (WirelineEquipmentItem purchaseItem : purchaseList) {
			//Filter out equipment with delivery method other than "INSTALLER" and product feasibility install type is "FW"
			if (!purchaseItem.getDeliveryMethodList().contains("INSTALLER") && productsWithInstallTypeFW.contains(product.getProductTypeCode())) {
				TraceVO t = TraceVO.newInstance(this);
				t.setAction(TraceVO.DELETED);
				t.setElementType(TraceVO.EQUIPMENT);
				t.setProduct(product);
				t.setEquipmentItem(purchaseItem);
				t.setReason("Purchase Equipment with INSTALLER delivery method not applicable to FW product install type");
				traces.add(t);
			} else {
				equipment.getPurchaseEquipmentList().add(purchaseItem);
			}
		}

	}

	private void processEquipment(Offer o, List<TraceVO> traces, ArrayList<String> validEquipments, WirelineOfferProduct product,
			WirelineEquipment equipment) {

		//filter byod equipment   
		List<WirelineEquipmentItem> byodList = equipment.getByodEquipmentList();
		equipment.setByodEquipmentList(null);
		for (WirelineEquipmentItem byodItem : byodList) {
			if (isSatisfiedBy(o, product, byodItem, validEquipments, traces)) {
				equipment.getByodEquipmentList().add(byodItem);
			}
		}
		
		//filter purchase equipment   
		List<WirelineEquipmentItem> purchaseList = equipment.getPurchaseEquipmentList();
		equipment.setPurchaseEquipmentList(null);
		for (WirelineEquipmentItem purchaseItem : purchaseList) {
			if (isSatisfiedBy(o, product, purchaseItem, validEquipments, traces)) {
				equipment.getPurchaseEquipmentList().add(purchaseItem);
			}
		}
		
		//filter rental equipment
		List<WirelineEquipmentItem> rentalList = equipment.getRentalEquipmentList();
		equipment.setRentalEquipmentList(null);
		for (WirelineEquipmentItem rentalItem : rentalList) {
			if (isSatisfiedBy(o, product, rentalItem, validEquipments, traces)) {
				equipment.getRentalEquipmentList().add(rentalItem);
			}
		}
	}

	private boolean isSatisfiedBy(Offer offer, WirelineOfferProduct product, WirelineEquipmentItem equipmentItem, ArrayList<String> validEquipments, List<TraceVO> traces) {
		
		if (validEquipments.contains(equipmentItem.getMaterialItemCode())) {
				return true;
		}
		
		TraceVO t = TraceVO.newInstance(this);
		t.setAction(TraceVO.DELETED);
		t.setElementType(TraceVO.EQUIPMENT);
		t.setOffer(offer);
		t.setProduct(product);
		t.setEquipmentItem(equipmentItem);
		t.setReason("Equipment failed checkProductFeasibility");
		traces.add(t);

		logger.error("Offer id: " + offer.getOfferId() + " was filtered out, because equipment failed checkProductFeasibility");

		return false;
	}
}
