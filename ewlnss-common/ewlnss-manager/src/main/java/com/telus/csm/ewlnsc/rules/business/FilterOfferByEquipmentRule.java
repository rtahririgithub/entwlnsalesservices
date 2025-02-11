package com.telus.csm.ewlnsc.rules.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.telus.csm.ewlnsc.adapter.scis.domain.AddOnPackInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.InternetInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductResponseVO;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineEquipment;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;

public class FilterOfferByEquipmentRule extends AbstractSpecification<Offer,TraceVO> {

	private static final LoggerUtil logger = LoggerUtil.getLogger(FilterOfferByEquipmentRule.class);

	private SalesOfferCommonVO salesOfferCommonVO;
	private Map<String, Integer> existingAccessoryEquipmentCountMap = new HashMap<String, Integer>();
	private List<SubscribedProductInfoRestVO> existingProducts;

	public FilterOfferByEquipmentRule(SalesOfferCommonVO salesOfferCommonVO) {
		this.salesOfferCommonVO = salesOfferCommonVO;
		
		// Initialize mapping
		for(String serviceType: WLNOfferUtil.ACCESSORY_EQUIPMENT_LIST) {
			existingAccessoryEquipmentCountMap.put(serviceType, 0);
		}
		
		// populate existing product list
		existingProducts = populateExistingProductsList();
		if(existingProducts != null) setExistingWifiPackCount(existingProducts);
	}
	@Override
	public boolean isSatisfiedBy(Offer o, List<TraceVO> traces) {

		logger.debug("isSatisfiedBy", WLNOfferUtil.getRuleContextInformation(o));
		
		/* 
		 * NWLN-10173 For Boost Accessory Offers, if existing starter/expansion packs >= maxQty of starter/expansion pack, filter out offer
		 * */
		if (existingProducts != null && !existingProducts.isEmpty()) {

			List<WirelineOfferProduct> oisProductList = o.getOfferProduct().getWirelineOfferProductList();
			for (WirelineOfferProduct tomProdElem : oisProductList) {
				if (EnterpriseWLNSalesServicesConstants.CPE.equalsIgnoreCase(tomProdElem.getProductTypeCode())) {
					
					List<WirelineEquipment> equipmentList = tomProdElem.getWirelineEquipmentList();
					List<WirelineEquipment> filteredBoostWifiEquipmentList = new ArrayList<WirelineEquipment>();
					int maxPackCount = 0;
					for (WirelineEquipment equipment : equipmentList) {
						
						String serviceType = WLNOfferUtil.getServiceType(equipment);
						if (serviceType != null && equipment.getMaxQty() != null) {
							if (existingAccessoryEquipmentCountMap.containsKey(serviceType)) {
								maxPackCount = equipment.getMaxQty().intValue();
								int existingPackCount = existingAccessoryEquipmentCountMap.get(serviceType);
								if ( existingPackCount < maxPackCount) {
									logger.info("FilterOfferByEquipmentRule", serviceType + " existing equipment count = " + existingPackCount + ", equipment maxQty = " +maxPackCount);
									filteredBoostWifiEquipmentList.add(equipment);
								} else {
									TraceVO t = TraceVO.newInstance(this);
									t.setAction(TraceVO.DELETED);
									t.setElementType(TraceVO.EQUIPMENT);
									t.setOffer(o);
									t.setReason("Existing " + serviceType + " existing equipment count = " + existingPackCount + " larger than or equal to maxQty = " +maxPackCount);
									traces.add(t);
									logger.error(serviceType
											+ " equipment: was filtered out, because existing equipment count = " + existingPackCount +" larger than or equal to maxQty = "+maxPackCount);
								}
							} else {
								logger.info("FilterOfferByEquipmentRule", "existing equipment filter map doesn't contain " + serviceType + ", Rule skipped");
								filteredBoostWifiEquipmentList.add(equipment);
							}
						} else {
							// No serviceType or MaxQty, skip filter
							logger.info("FilterOfferByEquipmentRule", "serviceType is " + serviceType + " equipment maxQty is "+equipment.getMaxQty() + ", equipment filtered out");
						}
					}
					if (filteredBoostWifiEquipmentList.isEmpty()) {
						TraceVO t = TraceVO.newInstance(this);
						t.setAction(TraceVO.DELETED);
						t.setElementType(TraceVO.OFFER);
						t.setOffer(o);
						t.setReason("Existing equipment is larger than or equal to maxQty "+ tomProdElem.getProductTypeCode());
						traces.add(t);
						logger.error("Offer id: " + o.getOfferId() + " was filtered out, because existing equipment is larger than or equal to maxQty");
						logger.debug("FilterOfferByEquipmentRule", "Failed");
						return false;
					} else {
						tomProdElem.setWirelineEquipmentList(filteredBoostWifiEquipmentList);
					}
				}
			}
		}
		
		//check if the Offer is re-contract eligible to skip the rule
		
		// 2018 June Exception release for TTV recontracting
		// skip the rule only for like-for-like recontract instead of all recontract scenarios
		//if(salesOfferCommonVO.isCustomerEligibleForRecontract() &&  WLNOfferUtil.isOfferForRecontracting(o, salesOfferCommonVO)){
		if(salesOfferCommonVO.isCustomerEligibleForRecontract() &&  WLNOfferUtil.isOfferForLikeForLikeRecontracting(o, salesOfferCommonVO)){
			return true;
		}
		
		// If all the remaining HS modems/TV STBs/PVRs after filtering is less than minimum quantity, filter out the offer.

		for (WirelineOfferProduct product : o.getOfferProduct().getWirelineOfferProductList()) {
			List<WirelineEquipment> equipmentList = product.getWirelineEquipmentList();
		    if (!WLNOfferUtil.isNoChangeOfferProduct(product)) {	
						
				for (WirelineEquipment equipment : equipmentList) {
					if(!WLNOfferUtil.isAccessoryEquipment(equipment)){
				
						if (equipment.getMinQty() != null) {
							int minEquipmentCount = equipment.getMinQty().intValue();
							int totalEquipmentCount = equipment.getPurchaseEquipmentList().size() + equipment.getRentalEquipmentList().size() + equipment.getByodEquipmentList().size();
							if (totalEquipmentCount < minEquipmentCount) {
								TraceVO t = TraceVO.newInstance(this);
								t.setAction(TraceVO.DELETED);
								t.setElementType(TraceVO.OFFER);
								t.setOffer(o);
								t.setReason("Below min equipment count for product " + product.getProductTypeCode());
								traces.add(t);

								logger.error("Offer id: " + o.getOfferId() + " was filtered out, because below min equipment count for product " + product.getProductTypeCode());

								logger.debug("FilterOfferByEquipmentRule", "Failed");

								return false; 
							}
						}
					}
				}
			}
		}
		

		logger.debug("FilterOfferByEquipmentRule", "Passed");

		return true;
	}
	
	private void setExistingWifiPackCount(List<SubscribedProductInfoRestVO> existingProducts) {
		for (SubscribedProductInfoRestVO existingProduct : existingProducts) {
			if (EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(existingProduct.getProductTypeCd())) {
				if (existingProduct != null && existingProduct.getProductInstance() != null && !existingProduct.getProductInstance().isEmpty()) {
					InternetInfoRestVO internetComponent = existingProduct.getProductInstance().get(0).getInternetComponent();
					if (internetComponent != null && internetComponent.getAddOnPacks() != null && !internetComponent.getAddOnPacks().isEmpty()) {
						for (AddOnPackInfoRestVO addOnPack : internetComponent.getAddOnPacks()) {
							String serviceType = addOnPack.getComponentServiceTypeCd();
							if(serviceType != null && existingAccessoryEquipmentCountMap.containsKey(serviceType) && addOnPack.getEquipmentList() != null && !addOnPack.getEquipmentList().isEmpty()) {
								Integer count = existingAccessoryEquipmentCountMap.get(serviceType) + addOnPack.getEquipmentList().size();
								existingAccessoryEquipmentCountMap.put(serviceType, count);
							}
						}
					}
				}
			}
		}
	}
	
	private List<SubscribedProductInfoRestVO> populateExistingProductsList() {
		GetAssignedAndPendingProductResponseVO assignedAndPendingProduct = null;
		if (salesOfferCommonVO != null) {
			assignedAndPendingProduct = salesOfferCommonVO.getAssignedAndPendingProductsResponseVO();
		}
		if (assignedAndPendingProduct != null) {
			List<SubscribedProductInfoRestVO> assignedProducts = assignedAndPendingProduct.getAssignedProductListByServiceAddressAndServiceId(salesOfferCommonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress(), salesOfferCommonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier());
			List<SubscribedProductInfoRestVO> pendingProducts = assignedAndPendingProduct.getPendingProductListByServiceAddress(salesOfferCommonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress(),salesOfferCommonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier(),salesOfferCommonVO.getServiceAddressResponseVO());
			
			List<SubscribedProductInfoRestVO> existingProducts = new ArrayList<SubscribedProductInfoRestVO>();
			if (assignedProducts != null && !assignedProducts.isEmpty()) {
				existingProducts.addAll(assignedProducts);
			}
			if (pendingProducts != null && !pendingProducts.isEmpty()) {
				existingProducts.addAll(pendingProducts);
			}
			return existingProducts;
		}
		return null;
	}	
}