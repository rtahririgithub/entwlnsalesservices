package com.telus.csm.ewlnsc.transformer;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.telus.csm.ess.rest.domain.AccessoryOffer;
import com.telus.csm.ess.rest.domain.AccessoryOfferHeader;
import com.telus.csm.ess.rest.domain.Address;
import com.telus.csm.ess.rest.domain.AppointmentDetailType;
import com.telus.csm.ess.rest.domain.AppointmentTimeSlot;
import com.telus.csm.ess.rest.domain.AppointmentType;
import com.telus.csm.ess.rest.domain.CartItem;
import com.telus.csm.ess.rest.domain.CartItemGiftWithPurchase;
import com.telus.csm.ess.rest.domain.CartItemRef;
import com.telus.csm.ess.rest.domain.CartItemRelationship;
import com.telus.csm.ess.rest.domain.ClientName;
import com.telus.csm.ess.rest.domain.DirectoryListingType;
import com.telus.csm.ess.rest.domain.DisconnectRequestType;
import com.telus.csm.ess.rest.domain.DisconnectServiceAddressType;
import com.telus.csm.ess.rest.domain.EquipmentAcquisitionType;
import com.telus.csm.ess.rest.domain.HomePhoneNumberDetailType;
import com.telus.csm.ess.rest.domain.OriginalCarrierInfoType;
import com.telus.csm.ess.rest.domain.PaymentOptionType;
import com.telus.csm.ess.rest.domain.PortRequestType;
import com.telus.csm.ess.rest.domain.ProductCatalogItemRef;
import com.telus.csm.ess.rest.domain.ProductCharacteristic;
import com.telus.csm.ess.rest.domain.ProductItemIdentifier;
import com.telus.csm.ess.rest.domain.ProductOfferingRef;
import com.telus.csm.ess.rest.domain.ProductOrderType;
import com.telus.csm.ess.rest.domain.ProductType;
import com.telus.csm.ess.rest.domain.RelatedImmediatePromotion;
import com.telus.csm.ess.rest.domain.SelectedCoupon;
import com.telus.csm.ess.rest.domain.SelectedSweetenerType;
import com.telus.csm.ess.rest.domain.ShipmentAddressType;
import com.telus.csm.ess.rest.domain.ShipmentDetailType;
import com.telus.csm.ess.rest.domain.SmartRingType;
import com.telus.csm.ess.rest.domain.SubscribedService;
import com.telus.csm.ess.rest.domain.TelephoneNumberType;
import com.telus.csm.ess.rest.domain.WirelineEquipmentType;
import com.telus.csm.ess.rest.domain.WirelineOfferHeader;
import com.telus.csm.ewlnsc.domain.AccessoryOfferHeaderVO;
import com.telus.csm.ewlnsc.domain.AccessoryOfferVO;
import com.telus.csm.ewlnsc.domain.AdditionalProductItemTypeVO;
import com.telus.csm.ewlnsc.domain.AddressVO;
import com.telus.csm.ewlnsc.domain.AppointmentDetailTypeVO;
import com.telus.csm.ewlnsc.domain.AppointmentTimeSlotVO;
import com.telus.csm.ewlnsc.domain.AppointmentTypeVO;
import com.telus.csm.ewlnsc.domain.CartItemRefVO;
import com.telus.csm.ewlnsc.domain.CartItemRelationshipVO;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.ClientNameVO;
import com.telus.csm.ewlnsc.domain.CompetitiveLocalExchageCarrierTypeVO;
import com.telus.csm.ewlnsc.domain.DirectoryListingTypeVO;
import com.telus.csm.ewlnsc.domain.DisconnectRequestTypeVO;
import com.telus.csm.ewlnsc.domain.DisconnectServiceAddressTypeVO;
import com.telus.csm.ewlnsc.domain.EquipmentAcquisitionTypeVO;
import com.telus.csm.ewlnsc.domain.FFHDiscountTypeVO;
import com.telus.csm.ewlnsc.domain.FFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.FFHFeatureTypeVO;
import com.telus.csm.ewlnsc.domain.FFHOfferHeaderVO;
import com.telus.csm.ewlnsc.domain.FFHProductPlanAddOnTypeVO;
import com.telus.csm.ewlnsc.domain.FFHSweetenerTypeVO;
import com.telus.csm.ewlnsc.domain.HomePhoneNumberDetailTypeVO;
import com.telus.csm.ewlnsc.domain.HomePhoneProductTypeVO;
import com.telus.csm.ewlnsc.domain.InternetProductTypeVO;
import com.telus.csm.ewlnsc.domain.OriginalCarrierInfoTypeVO;
import com.telus.csm.ewlnsc.domain.PaymentOptionVO;
import com.telus.csm.ewlnsc.domain.PortRequestTypeVO;
import com.telus.csm.ewlnsc.domain.ProductComponentVO;
import com.telus.csm.ewlnsc.domain.ProductOfferingRefVO;
import com.telus.csm.ewlnsc.domain.ProductOrderTypeVO;
import com.telus.csm.ewlnsc.domain.ProductTypeBaseVO;
import com.telus.csm.ewlnsc.domain.ProductTypeVO;
import com.telus.csm.ewlnsc.domain.RelatedImmediatePromotionVO;
import com.telus.csm.ewlnsc.domain.SelectedCouponVO;
import com.telus.csm.ewlnsc.domain.ShipmentAddressTypeVO;
import com.telus.csm.ewlnsc.domain.ShipmentDetailTypeVO;
import com.telus.csm.ewlnsc.domain.SmartRingTypeVO;
import com.telus.csm.ewlnsc.domain.SubscribedServiceVO;
import com.telus.csm.ewlnsc.domain.TelephoneNumberTypeVO;
import com.telus.csm.ewlnsc.domain.TelevisionProductTypeVO;
import com.telus.csm.ewlnsc.domain.AccessoryProductTypeVO;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnss.rest.time.DateTime;
import com.telus.csm.ewlnss.rest.time.LocalDate;

public class CartItemTransformer {
	private static final String CLEC_INDICATOR = "CLEC_INDICATOR";
	private static final String CLEC_ID = "CLEC_ID";
	private static final String CLEC_PHONE_NUMBER = "CLEC_PHONE_NUMBER";
	
	private String cartItemAction; 
	
	public String getCartItemAction() {
		return cartItemAction;
	}

	public void setCartItemAction(String cartItemAction) {
		this.cartItemAction = cartItemAction;
	}

	public List<CartItemVO> transformToCartItemVO(CartItem cartItem) {
		
		//As today we have just SalesOrderCartItems
		List<CartItemVO> cartItemsList = new ArrayList<CartItemVO>();
		if (cartItem.getCartItemSalesOrder() != null) {
			cartItemAction = cartItem.getCartItemSalesOrder().getActionCd();
			CartItemVO cartItemVO = new CartItemVO();
			cartItemVO.setSalesOrderItem(true);
			cartItemVO.setAction(cartItem.getCartItemSalesOrder().getActionCd());
			cartItemVO.setCartItemContextTypeList(cartItem.getCartItemSalesOrder().getCartItemContextTypeList());
			cartItemVO.setCartItemId(cartItem.getCartItemSalesOrder().getCartItemId());
			cartItemVO.setCartItemRelationId(cartItem.getCartItemSalesOrder().getCartItemRelationId());
			cartItemVO.setCartItemRelationship(getCartItemRelationShipFromRest(cartItem.getCartItemSalesOrder().getCartItemRelationship()));
			cartItemVO.setExistingServiceIdentifier(getExistingServiceIdentifierFromRest(cartItem.getCartItemSalesOrder().getExistingServiceIdentifier()));
			cartItemVO.setProductMarketOffering(getProductMarketOfferingFromRest(cartItem.getCartItemSalesOrder().getProductMarketOffering()));
			cartItemVO.setProducts(getProductsFromRest(cartItem.getCartItemSalesOrder().getProduct()));
			cartItemVO.setShipmentDetail(getShipmentDetailFromRest(cartItem.getCartItemSalesOrder().getShipmentDetail()));
			cartItemVO.setRelatedPromotionList(getRelatedPromotionListFromRest(cartItem.getCartItemSalesOrder().getRelatedPromotionList()));
			cartItemsList.add(cartItemVO);
		}

		if (cartItem.getCartItemDisconnectOrder() != null) {
			cartItemAction = cartItem.getCartItemDisconnectOrder().getActionCd();
			CartItemVO cartItemVO = new CartItemVO();
			cartItemVO.setDisconnectOrderItem(true);
			cartItemVO.setAction(cartItem.getCartItemDisconnectOrder().getActionCd());
			cartItemVO.setCartItemContextTypeList(cartItem.getCartItemDisconnectOrder().getCartItemContextTypeList());
			cartItemVO.setCartItemId(cartItem.getCartItemDisconnectOrder().getCartItemId());
			cartItemVO.setCartItemRelationId(cartItem.getCartItemDisconnectOrder().getCartItemRelationId());
			cartItemVO.setCartItemRelationship(getCartItemRelationShipFromRest(cartItem.getCartItemDisconnectOrder().getCartItemRelationship()));
			cartItemVO.setExistingServiceIdentifier(getExistingServiceIdentifierFromRest(cartItem.getCartItemDisconnectOrder().getExistingServiceIdentifier()));
			cartItemVO.setDisconnectRequestList(getFromRest(cartItem.getCartItemDisconnectOrder().getDisconnectRequestList()));
			cartItemsList.add(cartItemVO);
		}

		/*TODO:: Perk is moved under CartItemSalesOrder. This code needs to change accordingly.
		 * if (cartItem.getCartItemSalesPerk() != null) {
			CartItemVO cartItemVO = new CartItemVO();
			cartItemVO.setAction(cartItem.getCartItemSalesPerk().getActionCd());
			cartItemVO.setCartItemContextTypeList(cartItem.getCartItemSalesPerk().getCartItemContextTypeList());
			cartItemVO.setCartItemId(cartItem.getCartItemSalesPerk().getCartItemId());
			cartItemVO.setCartItemRelationId(cartItem.getCartItemSalesPerk().getCartItemRelationId());
			cartItemVO.setCartItemRelationship(getCartItemRelationShipFromRest(cartItem.getCartItemSalesPerk().getCartItemRelationship()));
			cartItemVO.setExistingServiceIdentifier(getExistingServiceIdentifierFromRest(cartItem.getCartItemSalesPerk().getExistingServiceIdentifier()));
			cartItemsList.add(cartItemVO);
		}
		*/

		if(cartItem.getCartItemGiftWithPurchase() != null) {
			cartItemAction = cartItem.getCartItemGiftWithPurchase().getActionCd();
			CartItemVO cartItemVO = new CartItemVO();
			cartItemVO.setGwpOrderItem(true);
			cartItemVO.setAction(cartItem.getCartItemGiftWithPurchase().getActionCd());
			cartItemVO.setCartItemContextTypeList(cartItem.getCartItemGiftWithPurchase().getCartItemContextTypeList());
			cartItemVO.setCartItemId(cartItem.getCartItemGiftWithPurchase().getCartItemId());
			cartItemVO.setCartItemRelationId(cartItem.getCartItemGiftWithPurchase().getCartItemRelationId());
			cartItemVO.setCartItemRelationship(getCartItemRelationShipFromRest(cartItem.getCartItemGiftWithPurchase().getCartItemRelationship()));
			cartItemVO.setExistingServiceIdentifier(getExistingServiceIdentifierFromRest(cartItem.getCartItemGiftWithPurchase().getExistingServiceIdentifier()));
			cartItemVO.setAccessoryOfferList(getFromRest(cartItem.getCartItemGiftWithPurchase()));
			cartItemVO.setShipmentDetail(getShipmentDetailFromRest(cartItem.getCartItemGiftWithPurchase().getShipmentDetail()));
			cartItemVO.setRelatedPromotionList(getRelatedPromotionListFromRest(cartItem.getCartItemGiftWithPurchase().getRelatedPromotionList()));
			cartItemsList.add(cartItemVO);
		}

		return cartItemsList;
	}

	private List<RelatedImmediatePromotionVO> getRelatedPromotionListFromRest(List<RelatedImmediatePromotion> relatedPromotionList) {
		List<RelatedImmediatePromotionVO> promos = null;
		if(relatedPromotionList != null) {
			promos = new ArrayList<RelatedImmediatePromotionVO>();
			for(RelatedImmediatePromotion pr: relatedPromotionList) {
				if(pr != null) {
					RelatedImmediatePromotionVO prVO = new RelatedImmediatePromotionVO();
					prVO.setId(pr.getId());
					prVO.setItemQualificationType(pr.getItemQualificationType());
					prVO.setItemStatus((pr.getItemStatus()));
					prVO.setPerspectiveDate(pr.getPerspectiveDate() != null? pr.getPerspectiveDate().toDate(): null);
				    prVO.setSelectedCoupon(getSelectedCouponFromRest(pr.getSelectedCoupon()));
					promos.add(prVO);
				}
			}
		}
		return promos;
	}

	private SelectedCouponVO getSelectedCouponFromRest(SelectedCoupon selectedCoupon) {
		SelectedCouponVO result = null;
		if(selectedCoupon != null) {
			result = new SelectedCouponVO();
			result.setId(selectedCoupon.getId());
			result.setPerspectiveDate(selectedCoupon.getPerspectiveDate() != null? selectedCoupon.getPerspectiveDate().toDate(): null);
			result.setValidationCode(selectedCoupon.getValidationCode());
		}
		return result;
	}

	private ShipmentDetailTypeVO getShipmentDetailFromRest(ShipmentDetailType shipmentDetail) {
		ShipmentDetailTypeVO shipmentDetailTypeVO = new ShipmentDetailTypeVO();
		if(shipmentDetail!=null && shipmentDetail.getShipmentAddress()!=null){
			shipmentDetailTypeVO.setShipmentAddress(getShipmentAddressFromRest(shipmentDetail.getShipmentAddress()));
		}
		return shipmentDetailTypeVO;
	}

	private ShipmentAddressTypeVO getShipmentAddressFromRest(ShipmentAddressType shipmentAddress) {
		ShipmentAddressTypeVO shipmentAddressTypeVO = new ShipmentAddressTypeVO();
		if(shipmentAddress!=null){
			
			
			shipmentAddressTypeVO.setxCoordinate(shipmentAddress.getXCoordinateId());
			shipmentAddressTypeVO.setyCoordinate(shipmentAddress.getYCoordinateId());
			shipmentAddressTypeVO.setGeocodingcalculationMethodCode(shipmentAddress.getGeocodingcalculationMethodCode());
			shipmentAddressTypeVO.setGeocodingMatchTypeCode(shipmentAddress.getGeocodingMatchTypeCode());
			shipmentAddressTypeVO.setConfidenceLevelCode(shipmentAddress.getConfidenceLevelCode());
			if(shipmentAddress.getAddressId()!=null){
				shipmentAddressTypeVO.setAddressId(Long.valueOf(shipmentAddress.getAddressId()));
			}
			shipmentAddressTypeVO.setAddressTypeCode(shipmentAddress.getAddressTypeCode());
			if(!CollectionUtils.isEmpty(shipmentAddress.getAdditionalAddressInformationList())){
				shipmentAddressTypeVO.setAdditionalAddressInformation(shipmentAddress.getAdditionalAddressInformationList());
			}
			if(!CollectionUtils.isEmpty(shipmentAddress.getRenderedAddressList())){
				shipmentAddressTypeVO.setRenderedAddress(shipmentAddress.getRenderedAddressList());
			}
			if(shipmentAddress.getAddressAssignmentId()!=null){
				shipmentAddressTypeVO.setAddrAssgnmtId(Long.valueOf(shipmentAddress.getAddressAssignmentId()));
			}
			shipmentAddressTypeVO.setAddressNumberPrefixTxt(shipmentAddress.getAddressNumberPrefixTxt());
			shipmentAddressTypeVO.setAddressAssignmentTypeCode(shipmentAddress.getAddressAssignmentTypeCd());
			shipmentAddressTypeVO.setAddressAssignmentSubTypeCode(shipmentAddress.getAddressAssignmentSubTypeCd());
			shipmentAddressTypeVO.setAddressMatchingStatusCode(shipmentAddress.getAddressMatchingStatusCd());
			shipmentAddressTypeVO.setAddressSearchText(shipmentAddress.getAddressSearchTxt());
			shipmentAddressTypeVO.setCountryCode(shipmentAddress.getCountryCode());
			shipmentAddressTypeVO.setEmailAddressText(shipmentAddress.getEmailAddressTxt());
			shipmentAddressTypeVO.setExternalAddressId(shipmentAddress.getExternalAddressId());
			if(!StringUtils.isEmpty(shipmentAddress.getExternalAddressSourceId()))
			shipmentAddressTypeVO.setExternalAddressSourceId(Long.valueOf(shipmentAddress.getExternalAddressSourceId()));
			shipmentAddressTypeVO.setExternalServiceAddressId(shipmentAddress.getExternalServiceAddressId());
			shipmentAddressTypeVO.setMailingTypeCode(shipmentAddress.getMailingTypeCode());
			shipmentAddressTypeVO.setMunicipalityName(shipmentAddress.getMunicipalityName());
			shipmentAddressTypeVO.setPostalZipCode(shipmentAddress.getPostalZipCode());
			shipmentAddressTypeVO.setProvinceStateCode(shipmentAddress.getProvinceStateCode());
			if(!StringUtils.isEmpty(shipmentAddress.getRelatedAddressAssignmentId()))
			shipmentAddressTypeVO.setRelateAddressAssignmentId(Long.valueOf(shipmentAddress.getRelatedAddressAssignmentId()));
			shipmentAddressTypeVO.setStreetDirectionPrefixCd(shipmentAddress.getStreetDirectionPrefixCd());
			shipmentAddressTypeVO.setStreetDirectionSuffixCd(shipmentAddress.getStreetDirectionSuffixCd());
			shipmentAddressTypeVO.setStreetNumber(shipmentAddress.getStreetNumber());
			shipmentAddressTypeVO.setStreetName(shipmentAddress.getStreetName());
			shipmentAddressTypeVO.setUnitNumber(shipmentAddress.getUnitNumber());
			shipmentAddressTypeVO.setUnitName(shipmentAddress.getUnitName());
			shipmentAddressTypeVO.setUnitTypeCode(shipmentAddress.getUnitTypeCode());
			shipmentAddressTypeVO.setBuildingName(shipmentAddress.getBuildingName());
			shipmentAddressTypeVO.setBuildingTypeCd(shipmentAddress.getBuildingTypeCd());
			if(shipmentAddress.getValidateAddressIndicator()!=null)
			shipmentAddressTypeVO.setValidateAddressIndicator(shipmentAddress.getValidateAddressIndicator().toString());
			shipmentAddressTypeVO.setCanadaPostBuildName(shipmentAddress.getCanadaPostBuildName());
			shipmentAddressTypeVO.setCanadaPostLocnName(shipmentAddress.getCanadaPostLocationnName());
			shipmentAddressTypeVO.setCanadaPostRecordType(shipmentAddress.getCanadaPostRecordTypeCode());
			shipmentAddressTypeVO.setCareOf(shipmentAddress.getCareOfTxt());
			shipmentAddressTypeVO.setCivicNumber(shipmentAddress.getCivicNumber());
			shipmentAddressTypeVO.setCivicNumberSuffix(shipmentAddress.getCivicNumberSuffixTxt());
			shipmentAddressTypeVO.setPostOfficeBoxNumber(shipmentAddress.getPostOfficeBoxNumber());
			shipmentAddressTypeVO.setRuralRouteNumber(shipmentAddress.getRuralRouteNumber());
			shipmentAddressTypeVO.setRuralRouteTypeCode(shipmentAddress.getRuralRouteTypeCode());
			shipmentAddressTypeVO.setStationName(shipmentAddress.getStationAreaName());
			shipmentAddressTypeVO.setStationQualifier(shipmentAddress.getStationQualifierCd());
			shipmentAddressTypeVO.setStationTypeCode(shipmentAddress.getStationTypeCode());
			shipmentAddressTypeVO.setFloorTypeCd(shipmentAddress.getFloorTypeCd());
			shipmentAddressTypeVO.setFloorName(shipmentAddress.getFloorName());
			shipmentAddressTypeVO.setLandmarkName(shipmentAddress.getLandmarkName());
			shipmentAddressTypeVO.setPlaceName(shipmentAddress.getPlaceName());
			
		}
		return shipmentAddressTypeVO;
	}

	private List<DisconnectRequestTypeVO> getFromRest(List<DisconnectRequestType> disconnectRequestList) {	
		List<DisconnectRequestTypeVO> disconnectRequestListVO = new ArrayList<DisconnectRequestTypeVO>();
		for (DisconnectRequestType disconnectRequestType : disconnectRequestList ) {
			DisconnectRequestTypeVO  disconnectRequestTypeVO = getFromRest(disconnectRequestType);
			disconnectRequestListVO.add(disconnectRequestTypeVO);
		}
	return disconnectRequestListVO;
}

	private DisconnectRequestTypeVO getFromRest(DisconnectRequestType disconnectRequestType) {
		DisconnectRequestTypeVO disconnectRequestTypeVO = new DisconnectRequestTypeVO();
		disconnectRequestTypeVO.setOriginalCarrierInfo(getFromRest(disconnectRequestType.getOriginalCarrierInfo()));
		disconnectRequestTypeVO.setProductServiceType(disconnectRequestType.getProductServiceType());
		disconnectRequestTypeVO.setRequestedDisconnectDate(disconnectRequestType.getRequestedDisconnectDate() != null? disconnectRequestType.getRequestedDisconnectDate().toDate(): null);
		return disconnectRequestTypeVO;
	}

	private OriginalCarrierInfoTypeVO getFromRest(OriginalCarrierInfoType originalCarrierInfo) {
		OriginalCarrierInfoTypeVO originalCarrierInfoTypeVO = new OriginalCarrierInfoTypeVO();
		originalCarrierInfoTypeVO.setAccountNumber(originalCarrierInfo.getAccountNumber());
		originalCarrierInfoTypeVO.setTelephoneNumber(getFromRest(originalCarrierInfo.getTelephoneNumber()));
		originalCarrierInfoTypeVO.setCustomerName(getFromRest(originalCarrierInfo.getCustomerName()));
		originalCarrierInfoTypeVO.setDisconnectServiceAddress(getFromRest(originalCarrierInfo.getDisconnectServiceAddress()));
		originalCarrierInfoTypeVO.setProviderId(originalCarrierInfo.getProviderId());
		originalCarrierInfoTypeVO.setReseller(originalCarrierInfo.getResellerName());
		
		return originalCarrierInfoTypeVO;
	}

	private DisconnectServiceAddressTypeVO getFromRest(DisconnectServiceAddressType disconnectServiceAddress) {
		DisconnectServiceAddressTypeVO disconnectServiceAddressTypeVO = new DisconnectServiceAddressTypeVO();
		disconnectServiceAddressTypeVO.setServiceAddressId(disconnectServiceAddress.getServiceAddressId());
		disconnectServiceAddressTypeVO.setFullAddress(getFromRest(disconnectServiceAddress.getFullAddress()));

		return disconnectServiceAddressTypeVO;
	}

	private AddressVO getFromRest(Address fullAddress) {
		if(fullAddress == null) {
			return null;
		}
		AddressVO addressVO = new AddressVO();
		addressVO.setAddressId(fullAddress.getAddressId() == null? null: Long.parseLong(fullAddress.getAddressId()));
		addressVO.setStreetNumber(fullAddress.getStreetNumber());
		addressVO.setStreetName(fullAddress.getStreetName());
		addressVO.setMunicipalityName(fullAddress.getMunicipalityName());
		addressVO.setProvinceStateCode(fullAddress.getProvinceStateCode());
		addressVO.setPostalZipCode(fullAddress.getPostalZipCode());
		return addressVO;
	}
	
	private TelephoneNumberTypeVO getFromRest(TelephoneNumberType telephoneNumber) {
		if (telephoneNumber != null) {
			TelephoneNumberTypeVO telephoneNumberTypeVO = new TelephoneNumberTypeVO();
			telephoneNumberTypeVO.setPhoneNumber(telephoneNumber.getPhoneNumber());
			telephoneNumberTypeVO.setPhoneNumberType(telephoneNumber.getPhoneNumberTypeCd());
			return telephoneNumberTypeVO;
		} else {
			return null;
		}
		
	}
	
	private ClientNameVO getFromRest(ClientName customerName) {
		ClientNameVO clientNameVO = new ClientNameVO();
		clientNameVO.setFirstName(customerName.getFirstName()); 
		clientNameVO.setLastName(customerName.getLastName());
		return clientNameVO;
	}

	private ProductTypeVO getProductsFromRest(List<ProductType> restProducts) {
		ProductTypeVO result = new ProductTypeVO();
		
		if (restProducts!=null) {
			for (ProductType restProduct : restProducts) {
				if (EnterpriseWLNSalesServicesConstants.HSIC.equals(restProduct.getProductTypeCd())) {
					result.setInternetProduct(getInternetProductFromRest(restProduct));
				} else if (EnterpriseWLNSalesServicesConstants.TTV.equals(restProduct.getProductTypeCd())) {
					result.setTelevisionProduct(getTelevisionProductFromRest(restProduct));
				} else if (EnterpriseWLNSalesServicesConstants.SL.equals(restProduct.getProductTypeCd()) || EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(restProduct.getProductTypeCd())) {
					result.setHomePhoneProduct(getHomePhoneProductFromRest(restProduct));
				//NWLN-1022
				} else if (EnterpriseWLNSalesServicesConstants.OIS_ACCESSORIES_CD.equals(restProduct.getProductTypeCd())) {
					result.setAccessoryProduct(getAccessoryProductFromRest(restProduct));
				}
			}
		}
		
		return result;
	}

	private TelevisionProductTypeVO getTelevisionProductFromRest(ProductType restProduct) {
		TelevisionProductTypeVO televisionProductVO = null;
		if(restProduct!=null){
			televisionProductVO = new TelevisionProductTypeVO();
			
			populateFromRest(televisionProductVO, restProduct);
			
			/* TODO:: CompetitiveLocalExchangeCarrierType moved under Internet Product now.
			if(televisionProduct.isCompetitiveExchangeCarrierInd()!=null){
				televisionProductVO.setCompetitiveExchangeCarrierInd(televisionProduct.isCompetitiveExchangeCarrierInd());
			}
			*/
		}
		return televisionProductVO;
	}
	
	//NWLN-10222
	private AccessoryProductTypeVO getAccessoryProductFromRest(ProductType restProduct) {
		AccessoryProductTypeVO accessoryProductVO = null;
		if(restProduct != null) {
			accessoryProductVO = new AccessoryProductTypeVO();
			
			populateFromRest(accessoryProductVO, restProduct);
		}
		return accessoryProductVO;
	}

	private InternetProductTypeVO getInternetProductFromRest(ProductType restProduct) {
		InternetProductTypeVO internetVO = null;
		if(restProduct!=null){
			internetVO = new InternetProductTypeVO();
			populateFromRest(internetVO, restProduct);

			//competitiveExchangeCarrierInfo is removed from v2.1 yaml 
			//if(restProduct.getCompetitiveExchangeCarrierInfo()!=null){
			//	internetVO.setCompetitiveExchangeCarrierInfo(getCompetitiveExchangeCarrierInfoFromRest(restProduct.getCompetitiveExchangeCarrierInfo()));
			//}
			CompetitiveLocalExchageCarrierTypeVO competitiveLocalExchageCarrierTypeVO = new CompetitiveLocalExchageCarrierTypeVO();
			competitiveLocalExchageCarrierTypeVO.setCompetitiveExchangeCarrierInd(Boolean.parseBoolean(findProductCharacteristicValue(restProduct.getProductCharacteristic(), CLEC_INDICATOR)));
			competitiveLocalExchageCarrierTypeVO.setCompetitiveLocalExchageCarrierId(findProductCharacteristicValue(restProduct.getProductCharacteristic(), CLEC_ID));
			String phoneAsStr = findProductCharacteristicValue(restProduct.getProductCharacteristic(), CLEC_PHONE_NUMBER);
			if(!StringUtils.isEmpty(phoneAsStr)) {
				TelephoneNumberTypeVO telephoneNumberTypeVO = new TelephoneNumberTypeVO();
				telephoneNumberTypeVO.setPhoneNumber(Long.parseLong(phoneAsStr));
				competitiveLocalExchageCarrierTypeVO.setClecPhoneNumber(telephoneNumberTypeVO);
			}
			internetVO.setCompetitiveExchangeCarrierInfo(competitiveLocalExchageCarrierTypeVO);
		}	
		return internetVO;
	}

	private String findProductCharacteristicValue(List<ProductCharacteristic> productCharacteristics, String name) {
		if(productCharacteristics != null && !StringUtils.isEmpty(name)) {
			for(ProductCharacteristic prdChar: productCharacteristics) {
				if(name.equalsIgnoreCase(prdChar.getName())) {
					return prdChar.getValue();
				}
			}
		}
		return null;
	}

	private void populateFromRest(ProductTypeBaseVO productTypeBase, ProductType restProduct) {
		
		productTypeBase.setProductOrderType(getProductOrderTypeFromRest(restProduct.getProductOrderType()));
		
		if(!StringUtils.isBlank(restProduct.getSelectedContractTermCd())){
			productTypeBase.setSelectedContractTermCd(restProduct.getSelectedContractTermCd());
		}
		
		productTypeBase.setProductComponents(getProductComponentListFromRest(restProduct.getProductCatalogItems()));
		productTypeBase.setEquipments(getEquipmentsFromRest(restProduct.getEquipments()));
		productTypeBase.setAddOns(getAddonsFromRest(restProduct.getProductCatalogItems()));
		productTypeBase.setDiscounts(getDiscountsFromRest(restProduct.getProductCatalogItems()));
		productTypeBase.setSweeteners(getSweetenersFromRest(restProduct.getSweeteners()));
		productTypeBase.setAppointmentDetail(getAppointmentDetailFromRest(restProduct.getAppointmentDetail()));
		productTypeBase.setWinback(restProduct.getWinbackInd());
		productTypeBase.setAdditionalProductItemList(getAdditionalProductItemsFromRest(restProduct.getProductCatalogItems()));

	}

	private HomePhoneProductTypeVO getHomePhoneProductFromRest(ProductType restProduct) {
		HomePhoneProductTypeVO homePhoneVO = null;
		if(restProduct!=null){
			homePhoneVO = new HomePhoneProductTypeVO();
			populateFromRest(homePhoneVO, restProduct);
			//TODO: validate with Aman
			//homePhoneVO.setEquipments(getEquipmentsFromRest(homePhoneProduct.getEquipments()));
			homePhoneVO.setPhoneNumber(getPhoneNumber(restProduct.getPhoneNumber()));
			homePhoneVO.setSmartRingPhoneList(getSmartRingPhoneList(restProduct.getSmartRingPhoneList()));
			homePhoneVO.setFeatures(getFeatures(restProduct.getProductCatalogItems()));
			homePhoneVO.setProductTypeCd(restProduct.getProductTypeCd());
		}
		return homePhoneVO;
	}

	private List<FFHFeatureTypeVO> getFeatures(List<ProductCatalogItemRef> list) {
	  
		List<FFHFeatureTypeVO> WirelineFeatureTypeListVO  = new ArrayList<FFHFeatureTypeVO>();
		if(list!=null && !list.isEmpty()){
			for (ProductCatalogItemRef featureType : list) {
				if (isMarketOfferClassification(featureType, EnterpriseWLNSalesServicesConstants.CLS_CALLING_FEATURE)) {
					WirelineFeatureTypeListVO.add(getFFHFeatureTypeVO(featureType));
				}
			}	
		}
		
		return WirelineFeatureTypeListVO;
	}

	private FFHFeatureTypeVO getFFHFeatureTypeVO(ProductCatalogItemRef featureType) {

		FFHFeatureTypeVO FFHFeatureTypeVO = new FFHFeatureTypeVO();
		FFHFeatureTypeVO.setProductCatalogueIdentifier(getProductCatalogueIdentifierFromRest(featureType.getProductCatalogueIdentifier(), getActionCd(featureType)));
		return FFHFeatureTypeVO;
	}

	private List<SmartRingTypeVO> getSmartRingPhoneList(List<SmartRingType> smartRingPhoneList) {
		
		List<SmartRingTypeVO> smartRingPhoneListVO  = new ArrayList<SmartRingTypeVO>();
		if(smartRingPhoneList!=null && !smartRingPhoneList.isEmpty()){
			for (SmartRingType smartRingType : smartRingPhoneList) {
				smartRingPhoneListVO.add(getSmartRingTypeVO(smartRingType));
			}
		}
		return smartRingPhoneListVO;
	}

	private SmartRingTypeVO getSmartRingTypeVO(SmartRingType smartRingType) {
		SmartRingTypeVO  smartRingPhoneListVO = new SmartRingTypeVO();
		smartRingPhoneListVO.setPhone(getPhoneNumber(smartRingType.getPhoneNumber()));
		return smartRingPhoneListVO;
	}

	private HomePhoneNumberDetailTypeVO getPhoneNumber(HomePhoneNumberDetailType phoneNumber) {
		
		HomePhoneNumberDetailTypeVO homePhoneNumberDetailTypeVO = new HomePhoneNumberDetailTypeVO();
		
		if(phoneNumber!=null){
			DirectoryListingType directoryListing = phoneNumber.getDirectoryListing();
			
			TelephoneNumberType telephoneNumber = phoneNumber.getTelephoneNumber();
			
			if (directoryListing != null) {
				DirectoryListingTypeVO directoryListingTypeVO = new DirectoryListingTypeVO();
				directoryListingTypeVO.setAddressListedInd(directoryListing.getAddressListedInd());
				directoryListingTypeVO.setListedInd(directoryListing.getListedInd());
				directoryListingTypeVO.setNameListedInd(directoryListing.getNameListedInd());
				homePhoneNumberDetailTypeVO.setDirectoryListing(directoryListingTypeVO);
			}
			if (telephoneNumber != null) {
				TelephoneNumberTypeVO telephoneNumberTypeVO = new TelephoneNumberTypeVO();
				telephoneNumberTypeVO.setPhoneNumber(telephoneNumber.getPhoneNumber());
				telephoneNumberTypeVO.setPhoneNumberType(telephoneNumber.getPhoneNumberTypeCd());
				homePhoneNumberDetailTypeVO.setTelephoneNumber(telephoneNumberTypeVO);
			}
			PortRequestType portRequestType = phoneNumber.getPortRequestType();
			if (portRequestType != null) {
				PortRequestTypeVO portRequestTypeVO = new PortRequestTypeVO();
				portRequestTypeVO.setPortinInd(portRequestType.getPortinInd());
				portRequestTypeVO.setTemporaryPhoneNumberRequiredInd(portRequestType.getTemporaryPhoneNumberRequiredInd());
				homePhoneNumberDetailTypeVO.setPortRequestType(portRequestTypeVO);
			}
		}
		return homePhoneNumberDetailTypeVO;
		
	}

	private List<FFHSweetenerTypeVO> getSweetenersFromRest(List<SelectedSweetenerType> sweeteners) {
		List<FFHSweetenerTypeVO> FFHSweetenerTypeVOList = null;
		if(sweeteners!=null && !sweeteners.isEmpty()){
			FFHSweetenerTypeVOList = new ArrayList<FFHSweetenerTypeVO>();
			for(SelectedSweetenerType sweetener : sweeteners){
				FFHSweetenerTypeVO sweetenerVO = new FFHSweetenerTypeVO();
				if (sweetener.getProductOffering() != null) {
					sweetenerVO.setOfferHeader(getOfferHeaderFromRest(sweetener.getProductOffering()));
				}
				if (sweetener.getProductCatalogItems() != null) {
					sweetenerVO.setDiscounts(getDiscountsFromRest(sweetener.getProductCatalogItems()));
				}
				sweetenerVO.setRelatedPromotionList(getRelatedPromotionListFromRest(sweetener.getRelatedPromotionList()));
				FFHSweetenerTypeVOList.add(sweetenerVO);
			}
		}
		return FFHSweetenerTypeVOList;
	}

	private List<AdditionalProductItemTypeVO> getAdditionalProductItemsFromRest(
			List<ProductCatalogItemRef> list) {
		
		List<AdditionalProductItemTypeVO> result  = new ArrayList<AdditionalProductItemTypeVO>();
		if(list!=null && !list.isEmpty()){
			for (ProductCatalogItemRef productItem : list) {
				if (isMarketOfferClassification(productItem, null) || isMarketOfferClassification(productItem, "")) {
					result.add(getAdditionalProductItemTypeVO(productItem));
				}
			}	
		}
		
		return result;

	}

	private AdditionalProductItemTypeVO getAdditionalProductItemTypeVO(ProductCatalogItemRef productItem) {
		
		AdditionalProductItemTypeVO result = new AdditionalProductItemTypeVO();

		ProductComponentVO productComponentVO = getProductCatalogueIdentifierFromRest(productItem.getProductCatalogueIdentifier(), getActionCd(productItem));
		if (productComponentVO != null) {
			result.setProductCatalogueIdentifier(productComponentVO);
		}

		return result;
	}

	private ProductOrderTypeVO getProductOrderTypeFromRest(ProductOrderType productOrderType) {
		ProductOrderTypeVO productOrderTypeVO = new ProductOrderTypeVO();
		if(!StringUtils.isBlank(productOrderType.getOrderTypeCd())){
			productOrderTypeVO.setOrderTypeCd(productOrderType.getOrderTypeCd());
		}
		if(!StringUtils.isBlank(productOrderType.getOrderSubTypeCd())){
			productOrderTypeVO.setOrderSubTypeCd(productOrderType.getOrderSubTypeCd());
		}
		return productOrderTypeVO;
	}

	private List<FFHEquipmentTypeVO> getEquipmentsFromRest(List<WirelineEquipmentType> equipments) {
		List<FFHEquipmentTypeVO> equipmentTypeList = new ArrayList<FFHEquipmentTypeVO>();
		if(equipments!=null && !equipments.isEmpty()){
			for(WirelineEquipmentType equipment : equipments){
				FFHEquipmentTypeVO equipmentVO = new FFHEquipmentTypeVO();
				equipmentVO.setAction(equipment.getActionCd());
				if(!StringUtils.isBlank(equipment.getDeliveryMethodTypeCd())){
					equipmentVO.setDeliveryMethodType(equipment.getDeliveryMethodTypeCd());
				}
				if(!StringUtils.isBlank(equipment.getMaterialItemCode())){
					equipmentVO.setMaterialItemCode(equipment.getMaterialItemCode());
				}
				if(equipment.getProductCatalogueIdentifier()!=null){
					equipmentVO.setProductCatalogueIdentifier(getProductCatalogueIdentifierFromRest(equipment.getProductCatalogueIdentifier(), equipment.getActionCd()));
				}
				if(equipment.getAcquisitionType()!=null){
					equipmentVO.setAcquisitionType(getAcquisitionTypeFromRest(equipment.getAcquisitionType()));
				}
				if(equipment.getPaymentOption() !=null){
					equipmentVO.setPaymentOption(getPaymentOptionFromRest(equipment.getPaymentOption()));
				}
				equipmentTypeList.add(equipmentVO);
			}
		}
		return equipmentTypeList;
	}

	private EquipmentAcquisitionTypeVO getAcquisitionTypeFromRest(EquipmentAcquisitionType acquisitionType) {
		EquipmentAcquisitionTypeVO equipmentAcquisitionVO = new EquipmentAcquisitionTypeVO();
		if(acquisitionType!=null){
			if(acquisitionType.getBuyIndicator()!=null){
				equipmentAcquisitionVO.setBuyIndicator(acquisitionType.getBuyIndicator());
			}
			if(acquisitionType.getCustomerOwnedIndicator()!=null){
				equipmentAcquisitionVO.setCustomerOwnedIndicator(acquisitionType.getCustomerOwnedIndicator());
			}
			if(acquisitionType.getRentalEquipmentIndicator()!=null){
				equipmentAcquisitionVO.setRentalEquipmentIndicator(acquisitionType.getRentalEquipmentIndicator());
			}
			
		}
		return equipmentAcquisitionVO;
	}
	
	//NWLN-10222
	private PaymentOptionVO getPaymentOptionFromRest(PaymentOptionType paymentOptionType) {
		PaymentOptionVO paymentOptionVO = new PaymentOptionVO();
		if(paymentOptionType!=null){
			if(paymentOptionType.getPayOptionTerm() !=null){
				paymentOptionVO.setPayOptionTerm(paymentOptionType.getPayOptionTerm());
			}
			if(paymentOptionType.getPayOptionType() !=null){
				paymentOptionVO.setPayOptionType(paymentOptionType.getPayOptionType());
			}
		}
		return paymentOptionVO;
	}

	private List<FFHDiscountTypeVO> getDiscountsFromRest(List<ProductCatalogItemRef> list) {
		
		Hashtable<String, FFHDiscountTypeVO> discountVOTable = new Hashtable<String, FFHDiscountTypeVO>();
		if(list!=null && !list.isEmpty()){
			for(ProductCatalogItemRef restProductItem : list){
				if (isMarketOfferClassification(restProductItem, EnterpriseWLNSalesServicesConstants.CLS_DISCOUNT) || 
						//QC-71329 - sweetener issue in the shopping cart that discount under sweetener is dropped
						isMarketOfferClassification(restProductItem, EnterpriseWLNSalesServicesConstants.CLS_SWEETENER)) {
					String discountCode = getCharacteristic(EnterpriseWLNSalesServicesConstants.DISCOUNT_CODE, restProductItem);
					if (StringUtils.isBlank(discountCode)) {
						discountCode = null;
						continue;
					}
					
					FFHDiscountTypeVO discountVO = discountVOTable.get(discountCode);
					if (discountVO == null) {
						discountVO = new FFHDiscountTypeVO();
						discountVOTable.put(discountCode, discountVO);
						discountVO.setDiscountCode(discountCode);
						discountVO.setProductCatalogueIdentifiers(new ArrayList<ProductComponentVO>());
					}
					ProductComponentVO productComponentVO = getProductCatalogueIdentifierFromRest(restProductItem.getProductCatalogueIdentifier(), getActionCd(restProductItem));
					if (productComponentVO != null) {
						discountVO.getProductCatalogueIdentifiers().add(productComponentVO);
					}
				}
			}
		}

		return new ArrayList<FFHDiscountTypeVO>(discountVOTable.values());
		
	}

	private List<ProductComponentVO> getProductComponentListFromRest(
			List<ProductCatalogItemRef> list) {
		List<ProductComponentVO> productComponentListVO = new ArrayList<ProductComponentVO>();
		if(list!=null && !list.isEmpty()){
			for(ProductCatalogItemRef restProductItem : list){
				if (isMarketOfferClassification(restProductItem, EnterpriseWLNSalesServicesConstants.CLS_PRODUCT_COMPONENT)) {				
					productComponentListVO.add(getProductCatalogueIdentifierFromRest(restProductItem.getProductCatalogueIdentifier(), getActionCd(restProductItem)));
				}
			}
		}
		return productComponentListVO;
	}
	
	private boolean isMarketOfferClassification(ProductCatalogItemRef restProductItem, String code) {
		return StringUtils.equals(getCharacteristic(EnterpriseWLNSalesServicesConstants.MARKET_OFFER_CLASSIFICATION, restProductItem), code);
	}

	private String getActionCd(ProductCatalogItemRef restProductItem) {
		return getCharacteristic(EnterpriseWLNSalesServicesConstants.ACTION_CD, restProductItem);
	}

	private String getCharacteristic(String marketOfferClassification, ProductCatalogItemRef restProductItem) {
		
		if (restProductItem != null && restProductItem.getProductItemCharacteristic() != null) {
			for (ProductCharacteristic x : restProductItem.getProductItemCharacteristic()) {
				if (marketOfferClassification.equals(x.getName())) {
					String value = x.getValue();
					if (value != null) {
						value = value.trim();
					}
					return value;
				}
			}
			
		}
		
		return null;
	}

	private AppointmentDetailTypeVO getAppointmentDetailFromRest(AppointmentDetailType appointmentDetail) {
		if(appointmentDetail==null){
			return null;
		}
		AppointmentDetailTypeVO appointmentDetailTypeVO = new AppointmentDetailTypeVO();

		appointmentDetailTypeVO.setBookedAppointmentDate(getBookedAppointmentDateFromRest(appointmentDetail.getBookedAppointment()));
		appointmentDetailTypeVO.setPreferredAppointmentDates(getPreferredAppointmentDateFromRest(appointmentDetail.getPreferredAppointmentDates()));

		return appointmentDetailTypeVO;
	}

	private List<AppointmentTypeVO> getPreferredAppointmentDateFromRest(
			List<AppointmentType> preferredAppointmentDates) {
		List<AppointmentTypeVO> appointmentTypeVOList = new ArrayList<AppointmentTypeVO>();
		if(preferredAppointmentDates!=null && !preferredAppointmentDates.isEmpty()){
			for(AppointmentType appointmentType : preferredAppointmentDates){
				AppointmentTypeVO appointmentTypeVO = new AppointmentTypeVO();
				if(appointmentType.getAppointmentDate()!=null){
					appointmentTypeVO.setAppointmentDate(appointmentType.getAppointmentDate() != null? appointmentType.getAppointmentDate().toDate(): null);
				}
				if(!StringUtils.isBlank(appointmentType.getAppointmentId())){
					appointmentTypeVO.setAppointmentId(appointmentType.getAppointmentId());
				}
				if(appointmentType.getAppointmentTimeSlot() != null) {
					appointmentTypeVO.setAppointmentTimeSlot(getTimeSlot(appointmentType.getAppointmentTimeSlot()));
				}
				appointmentTypeVOList.add(appointmentTypeVO);
			}
		}
		return appointmentTypeVOList;
	}

	private AppointmentTimeSlotVO getTimeSlot(AppointmentTimeSlot appointmentTimeSlot) {
		AppointmentTimeSlotVO timeSlot = new AppointmentTimeSlotVO();
		timeSlot.setStartTime(appointmentTimeSlot.getStartTime());
		timeSlot.setEndTime(appointmentTimeSlot.getEndTime());
		return timeSlot;
	}

	private AppointmentTypeVO getBookedAppointmentDateFromRest(AppointmentType bookedAppointmentDate) {
		AppointmentTypeVO appointmentTypeVO = new AppointmentTypeVO();
		if(bookedAppointmentDate!=null){
			if(bookedAppointmentDate.getAppointmentDate()!=null){
				appointmentTypeVO.setAppointmentDate(bookedAppointmentDate.getAppointmentDate() != null? bookedAppointmentDate.getAppointmentDate().toDate(): null);
			}
			if(!StringUtils.isBlank(bookedAppointmentDate.getAppointmentId())){
				appointmentTypeVO.setAppointmentId(bookedAppointmentDate.getAppointmentId());
			}
			if(bookedAppointmentDate.getAppointmentTimeSlot() != null) {
				appointmentTypeVO.setAppointmentTimeSlot(getTimeSlot(bookedAppointmentDate.getAppointmentTimeSlot()));
			}
		}
		return appointmentTypeVO;
	}

	private List<FFHProductPlanAddOnTypeVO> getAddonsFromRest(List<ProductCatalogItemRef> list) {
		List<FFHProductPlanAddOnTypeVO> ffhProductPlanAddOnList = new ArrayList<FFHProductPlanAddOnTypeVO>();
		
		if(list!=null && !list.isEmpty()){
			for(ProductCatalogItemRef restProductItem : list){
				if (isMarketOfferClassification(restProductItem, EnterpriseWLNSalesServicesConstants.CLS_ADD_ON)) {
					FFHProductPlanAddOnTypeVO addOnVO = new FFHProductPlanAddOnTypeVO();		
					addOnVO.setProductCatalogueIdentifier(getProductCatalogueIdentifierFromRest(restProductItem.getProductCatalogueIdentifier(), getActionCd(restProductItem)));
					addOnVO.setAction(getActionCd(restProductItem));
					ffhProductPlanAddOnList.add(addOnVO);
				}
			}
		}

		return ffhProductPlanAddOnList;
	}

	private ProductComponentVO getProductCatalogueIdentifierFromRest(
			ProductItemIdentifier productCatalogueIdentifier, String action) {

		ProductComponentVO productComponentVO = null;
		if(productCatalogueIdentifier!=null){
			productComponentVO = new ProductComponentVO();
			if(!StringUtils.isBlank(productCatalogueIdentifier.getParentProductCatalogueId())){
				productComponentVO.setParentProductCatalogueId(productCatalogueIdentifier.getParentProductCatalogueId());
			}
			if(!StringUtils.isBlank(productCatalogueIdentifier.getProductCatalogueId())){
				productComponentVO.setProductCatalogueId(productCatalogueIdentifier.getProductCatalogueId());
			}
			productComponentVO.setAction(action);
		}

		return productComponentVO;
	}

	/*
	private String mapAction(String action) {
		if(StringUtils.isEmpty(action)) {
			if(EnterpriseWLNSalesServicesConstants.ACTIVATION.equalsIgnoreCase(cartItemAction)) {
				return EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_ADD;
			}else {
				return EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_NO_CHANGE;
			}
		}
		return action;
	}
	*/

	private ProductOfferingRefVO getProductMarketOfferingFromRest(ProductOfferingRef productMarketOffering) {
		ProductOfferingRefVO productOfferingRefVO = new ProductOfferingRefVO();
		if(productMarketOffering!=null && productMarketOffering.getOfferHeader()!=null){
			productOfferingRefVO.setOfferHeader(getOfferHeaderFromRest(productMarketOffering.getOfferHeader()));
		}
		return productOfferingRefVO;
	}

	private FFHOfferHeaderVO getOfferHeaderFromRest(WirelineOfferHeader wirelineOfferHeader) {
		FFHOfferHeaderVO FFHOfferHeaderVO = new FFHOfferHeaderVO();
		if(wirelineOfferHeader!=null){
			FFHOfferHeaderVO.setOfferCode(wirelineOfferHeader.getOfferCode());
			//TODO: offerId is now defined as String. To validate with Aman
			FFHOfferHeaderVO.setOfferId(wirelineOfferHeader.getOfferId());
			FFHOfferHeaderVO.setOfferProgramId(wirelineOfferHeader.getOfferProgramId());
			FFHOfferHeaderVO.setPerspectiveDate(wirelineOfferHeader.getPerspectiveDate() != null? wirelineOfferHeader.getPerspectiveDate().toDate(): null);
			FFHOfferHeaderVO.setSystemId(wirelineOfferHeader.getSystemId());
		}
		return FFHOfferHeaderVO;
	}

	private List<SubscribedServiceVO> getExistingServiceIdentifierFromRest(
			List<SubscribedService> existingServiceIdentifierList) {
		List<SubscribedServiceVO> subscribedServiceVOList = new ArrayList<SubscribedServiceVO>();
		if(existingServiceIdentifierList!=null && !existingServiceIdentifierList.isEmpty()){
			for(SubscribedService subscribedService : existingServiceIdentifierList){
				SubscribedServiceVO subscribedServiceVO = new SubscribedServiceVO();
				if(subscribedService.getServiceId()!=null){
					subscribedServiceVO.setServiceId(subscribedService.getServiceId());
				}
				if(subscribedService.getServiceReferenceId()!=null){
					subscribedServiceVO.setServiceReferenceId(subscribedService.getServiceReferenceId());
				}
				subscribedServiceVOList.add(subscribedServiceVO);
			}
		}
		return subscribedServiceVOList;
	}

	private List<CartItemRelationshipVO> getCartItemRelationShipFromRest(
			List<CartItemRelationship> cartItemRelationship) {
		List<CartItemRelationshipVO> cartItemRelationShipVOList = new ArrayList<CartItemRelationshipVO>();
		if(cartItemRelationship!=null && !cartItemRelationship.isEmpty()){
			for(CartItemRelationship relationshipItem : cartItemRelationship){
				CartItemRelationshipVO cartItemRelationshipVO = new CartItemRelationshipVO();
				if(!StringUtils.isBlank(relationshipItem.getCartItemId())){
					cartItemRelationshipVO.setId(relationshipItem.getCartItemId());
				}
				if(!StringUtils.isBlank(relationshipItem.getType())){
					cartItemRelationshipVO.setType(relationshipItem.getType());
				}
				if(relationshipItem.getCartItem()!=null && !relationshipItem.getCartItem().isEmpty()){
					cartItemRelationshipVO.setCartItem(getCartItemRefFromRest(relationshipItem.getCartItem()));
				}
				cartItemRelationShipVOList.add(cartItemRelationshipVO);
			}
		}
		return cartItemRelationShipVOList;
	}

	private List<CartItemRefVO> getCartItemRefFromRest(List<CartItemRef> cartItemList) {
		List<CartItemRefVO> cartItemRefVOList = new ArrayList<CartItemRefVO>();
		if(cartItemList!=null && !cartItemList.isEmpty()){
			for(CartItemRef cartItemRef : cartItemList){
				CartItemRefVO cartItemRefVO = new CartItemRefVO();
				if(!StringUtils.isBlank(cartItemRef.getCartItemId())){
					cartItemRefVO.setId(cartItemRef.getCartItemId());
					cartItemRefVOList.add(cartItemRefVO);
				}
			}
		}
		return cartItemRefVOList;
	}
	
	public List<SubscribedService> getExistingServiceIdentifierToRest(
			List<SubscribedServiceVO> existingServiceIdentifierVOList) {
		List<SubscribedService> subscribedServiceList = new ArrayList<SubscribedService>();
		if(existingServiceIdentifierVOList!=null && !existingServiceIdentifierVOList.isEmpty()){
			for(SubscribedServiceVO subscribedServiceVO : existingServiceIdentifierVOList){
				SubscribedService subscribedService = new SubscribedService();
				if(subscribedServiceVO.getServiceId()!=null){
					subscribedService.setServiceId(subscribedServiceVO.getServiceId());
				}
				if(subscribedServiceVO.getServiceReferenceId()!=null){
					subscribedService.setServiceReferenceId(subscribedServiceVO.getServiceReferenceId());
				}
				subscribedServiceList.add(subscribedService);
			}
		}
		return subscribedServiceList;
	}
	
	public List<CartItemRelationship> getCartItemRelationShipToRest(
			List<CartItemRelationshipVO> cartItemRelationshipVOList) {
		if(cartItemRelationshipVOList==null){
			return null;
		}
		List<CartItemRelationship> cartItemRelationshipList = new ArrayList<CartItemRelationship>();
		if(!cartItemRelationshipVOList.isEmpty()){
			for(CartItemRelationshipVO cartItemRelationshipVO : cartItemRelationshipVOList){
				CartItemRelationship cartItemRelationship = new CartItemRelationship();
				cartItemRelationship.setCartItemId(cartItemRelationshipVO.getId());
				cartItemRelationship.setType(cartItemRelationshipVO.getType());
				cartItemRelationship.setCartItem(getCartItemRefToRest(cartItemRelationshipVO.getCartItem()));
				cartItemRelationshipList.add(cartItemRelationship);
			}
		}
		return cartItemRelationshipList;
	}

	private List<CartItemRef> getCartItemRefToRest(List<CartItemRefVO> cartItemVOList) {
		if(cartItemVOList==null){
			return null;
		}
		List<CartItemRef> cartItemRefList = new ArrayList<CartItemRef>();
		if(!cartItemVOList.isEmpty()){
			for(CartItemRefVO cartItemRefVO : cartItemVOList){
				CartItemRef cartItemRef = new CartItemRef();
				cartItemRef.setCartItemId(cartItemRefVO.getId());
				cartItemRefList.add(cartItemRef);
			}
		}
		return cartItemRefList;
	}

	public ProductOfferingRef getProductMarketOfferingToRest(ProductOfferingRefVO productMarketOfferingRefVO) {
		ProductOfferingRef productOfferingRef = new ProductOfferingRef();
		if(productMarketOfferingRefVO!=null && productMarketOfferingRefVO.getOfferHeader()!=null){
			productOfferingRef.setOfferHeader(getOfferHeaderToRest(productMarketOfferingRefVO.getOfferHeader()));
		}
		return productOfferingRef;
	}

	public WirelineOfferHeader getOfferHeaderToRest(FFHOfferHeaderVO offerHeaderVO) {
		WirelineOfferHeader WirelineOfferHeader = new WirelineOfferHeader();
		if(offerHeaderVO!=null){
			WirelineOfferHeader.setOfferCode(offerHeaderVO.getOfferCode());
			WirelineOfferHeader.setOfferId(offerHeaderVO.getOfferId());
			WirelineOfferHeader.setOfferProgramId(offerHeaderVO.getOfferProgramId());
			WirelineOfferHeader.setPerspectiveDate(offerHeaderVO.getPerspectiveDate() != null? new DateTime(offerHeaderVO.getPerspectiveDate()): null);
			WirelineOfferHeader.setSystemId(offerHeaderVO.getSystemId());
		}
		return WirelineOfferHeader;
	}

	public List<ProductType> getProductsToRest(ProductTypeVO productsVO) {
		List<ProductType> result = new ArrayList<ProductType>();

		if (productsVO != null) {
			if (productsVO.getHomePhoneProduct() != null) {
				result.add(getHomePhoneProductToRest(productsVO.getHomePhoneProduct()));
			}
			if (productsVO.getInternetProduct() != null) {
				result.add(getInternetProductToRest(productsVO.getInternetProduct()));
			}
			if (productsVO.getTelevisionProduct() != null) {
				result.add(getTelevisionProductToRest(productsVO.getTelevisionProduct()));
			}
			if (productsVO.getAccessoryProduct() != null) {
				result.add(getAccessoryProductToRest(productsVO.getAccessoryProduct()));
			}
			
			//TODO - mobility
			//if (productsVO.getMobilityProductVO() != null) {
			//	result.add(getMobilityProductToRest(productsVO.getMobilityProductVO()));
			//}
		}
		return result;
	}
	
	private ProductType getHomePhoneProductToRest(HomePhoneProductTypeVO homePhoneProductVO) {
		ProductType homePhone = null;
		if (homePhoneProductVO != null) {
			homePhone = getProductTypeToRest(homePhoneProductVO);
			homePhone.setProductTypeCd(homePhoneProductVO.getProductTypeCd());
			homePhone.setSmartRingPhoneList(getSmartRingPhoneListToRest(homePhoneProductVO.getSmartRingPhoneList()));
			homePhone.setPhoneNumber(getPhoneNumberToRest(homePhoneProductVO.getPhoneNumber()));
			
			addProductItems(homePhone, getFeaturesToRest(homePhoneProductVO.getFeatures()));
			
		}
		return homePhone;
	}

	
	private void addProductItems(ProductType product, List<ProductCatalogItemRef> items) {
		for (ProductCatalogItemRef item : items) {
			product.addProductCatalogItemsItem(item);
		}
	}

	private ProductType getProductTypeToRest(ProductTypeBaseVO productTypeBaseVO) {
		ProductType result = new ProductType();
		result.setProductOrderType(getProductOrderTypeToRest(productTypeBaseVO.getProductOrderType()));
		result.setSelectedContractTermCd(productTypeBaseVO.getSelectedContractTermCd());
		addProductItems(result, getProductCatalogueIdentifierListToRest(productTypeBaseVO.getProductComponents()));
		result.setEquipments(getEquipmentsToRest(productTypeBaseVO.getEquipments()));
		addProductItems(result, getAddonsToRest(productTypeBaseVO.getAddOns()));
		addProductItems(result, getDiscountsToRest(productTypeBaseVO.getDiscounts()));
		result.setSweeteners(getSweetenersToRest(productTypeBaseVO.getSweeteners()));
		result.setAppointmentDetail(getAppointmentDetailToRest(productTypeBaseVO.getAppointmentDetail()));
		result.setWinbackInd(productTypeBaseVO.getWinback());
		addProductItems(result, getAdditionalProductItemToRest(productTypeBaseVO.getAdditionalProductItemList()));

		return result;
	}
	
	private HomePhoneNumberDetailType getPhoneNumberToRest(HomePhoneNumberDetailTypeVO phoneListingTypeVO) {
		
		HomePhoneNumberDetailType phoneListingType = null;
		if (phoneListingTypeVO != null) {
			phoneListingType = new HomePhoneNumberDetailType();
			phoneListingType.setDirectoryListing(getToRestDirectoryListing(phoneListingTypeVO.getDirectoryListing()));
		    phoneListingType.setTelephoneNumber(getToRestTelephoneNumber(phoneListingTypeVO.getTelephoneNumber()));
		    if (phoneListingTypeVO != null && phoneListingTypeVO.getPortRequestType() != null) phoneListingType.setPortRequestType(getToRest(phoneListingTypeVO.getPortRequestType()));
		}    
		    
		return phoneListingType;
	}

	private PortRequestType getToRest(PortRequestTypeVO portRequestType) {
		PortRequestType portRequestType2= new PortRequestType();
		if(portRequestType!=null){
			portRequestType2.setPortinInd(portRequestType.isPortinInd());
			portRequestType2.setTemporaryPhoneNumberRequiredInd(portRequestType.isTemporaryPhoneNumberRequiredInd());
		}
		
		return portRequestType2;
	}

	private TelephoneNumberType getToRestTelephoneNumber(TelephoneNumberTypeVO telephoneNumber) {
		TelephoneNumberType telephoneNumberType = new TelephoneNumberType();
		if(telephoneNumber!=null){
			telephoneNumberType.setPhoneNumber(telephoneNumber.getPhoneNumber());
			telephoneNumberType.setPhoneNumberTypeCd(telephoneNumber.getPhoneNumberType());
		}
		
		return telephoneNumberType;
	}

	private DirectoryListingType getToRestDirectoryListing(DirectoryListingTypeVO directoryListingVO) {
		
		DirectoryListingType directoryListingType = new DirectoryListingType();
		if(directoryListingVO!=null){
			directoryListingType.setAddressListedInd(directoryListingVO.getAddressListedInd());
			directoryListingType.setListedInd(directoryListingVO.getListedInd());
			directoryListingType.setNameListedInd(directoryListingVO.getNameListedInd());
		}
		return directoryListingType;
	}

	private List<ProductCatalogItemRef> getFeaturesToRest(List<FFHFeatureTypeVO> featuresVO) {
	List<ProductCatalogItemRef> features = new ArrayList<ProductCatalogItemRef>();
		if(featuresVO!=null && !featuresVO.isEmpty()){
			for (FFHFeatureTypeVO FFHFeatureTypeVO : featuresVO) {
				features.add(getToRest(FFHFeatureTypeVO));
			}
		}
		return features;
	}

	private ProductCatalogItemRef getToRest(FFHFeatureTypeVO featureTypeVO) {
		ProductCatalogItemRef wirelineFeatureType = new ProductCatalogItemRef();
		if(featureTypeVO!=null){
			addMarketOfferClassification(wirelineFeatureType, EnterpriseWLNSalesServicesConstants.CLS_CALLING_FEATURE);
			addAction(wirelineFeatureType, featureTypeVO.getProductCatalogueIdentifier().getAction());
			wirelineFeatureType.setProductCatalogueIdentifier(getToRestProductComponent(featureTypeVO.getProductCatalogueIdentifier()));
		}
		return wirelineFeatureType;
	}

	private void addMarketOfferClassification(ProductCatalogItemRef productItem, String code) {
		if (!StringUtils.isBlank(code)) {
			ProductCharacteristic productCharacteristic = new ProductCharacteristic();
			productCharacteristic.setName(EnterpriseWLNSalesServicesConstants.MARKET_OFFER_CLASSIFICATION);
			productCharacteristic.setValue(code);
			productItem.addProductItemCharacteristicItem(productCharacteristic);	
		}
	}

	private void addAction(ProductCatalogItemRef productItem, String actionCd) {
		if (!StringUtils.isBlank(actionCd)) {
			ProductCharacteristic productCharacteristic = new ProductCharacteristic();
			productCharacteristic.setName(EnterpriseWLNSalesServicesConstants.ACTION_CD);
			productCharacteristic.setValue(actionCd);
			productItem.addProductItemCharacteristicItem(productCharacteristic);	
		}
	}

	private void addDiscountCode(ProductCatalogItemRef productItem, String discountCd) {
		if (!StringUtils.isBlank(discountCd)) {
			ProductCharacteristic productCharacteristic = new ProductCharacteristic();
			productCharacteristic.setName(EnterpriseWLNSalesServicesConstants.DISCOUNT_CODE);
			productCharacteristic.setValue(discountCd);
			productItem.addProductItemCharacteristicItem(productCharacteristic);	
		}
	}

	private ProductItemIdentifier getToRestProductComponent(ProductComponentVO productComponentVO) {
		ProductItemIdentifier productItemIdentifier = new ProductItemIdentifier();
		if(productComponentVO!=null){
		productItemIdentifier.setParentProductCatalogueId(productComponentVO.getParentProductCatalogueId());
		productItemIdentifier.setProductCatalogueId(productComponentVO.getProductCatalogueId());
		}
		return productItemIdentifier;
	}

	private List<SmartRingType> getSmartRingPhoneListToRest(List<SmartRingTypeVO> smartRingPhoneList) {
		List<SmartRingType> smartRingList = new ArrayList<SmartRingType>();
		if (smartRingPhoneList != null) {
		for (SmartRingTypeVO smartRing : smartRingPhoneList) {
			smartRingList.add(getToRest(smartRing));
		}
		}
		return smartRingList;
	}

	private SmartRingType getToRest(SmartRingTypeVO smartRing) {
		SmartRingType smartRingType = new SmartRingType();
		if(smartRing!=null){
			smartRingType.setPhoneNumber(getPhoneNumberToRest(smartRing.getPhone()));
		}
		return smartRingType;
	}

	private List<ProductCatalogItemRef> getAddonsToRest(List<FFHProductPlanAddOnTypeVO> addOns) {
		List<ProductCatalogItemRef> ffhProductPlanAddOnList = new ArrayList<ProductCatalogItemRef>();
		if(addOns!=null && !addOns.isEmpty()){
			for(FFHProductPlanAddOnTypeVO addOnVO : addOns){
				ProductCatalogItemRef addOn = new ProductCatalogItemRef();
				ffhProductPlanAddOnList.add(addOn);
				addMarketOfferClassification(addOn, EnterpriseWLNSalesServicesConstants.CLS_ADD_ON);		
				addAction(addOn, addOnVO.getAction());
				addOn.setProductCatalogueIdentifier(getProductCatalogueIdentifierToRest(addOnVO.getProductCatalogueIdentifier()));
			}
		}
		return ffhProductPlanAddOnList;
	}
	
	private ProductItemIdentifier getProductCatalogueIdentifierToRest(
			ProductComponentVO productCatalogueIdentifierVO) {
		ProductItemIdentifier productComponent = new ProductItemIdentifier();
		if(productCatalogueIdentifierVO!=null){
			if(!StringUtils.isBlank(productCatalogueIdentifierVO.getParentProductCatalogueId())){
				productComponent.setParentProductCatalogueId(productCatalogueIdentifierVO.getParentProductCatalogueId());
			}
			if(!StringUtils.isBlank(productCatalogueIdentifierVO.getProductCatalogueId())){
				productComponent.setProductCatalogueId(productCatalogueIdentifierVO.getProductCatalogueId());
			}
		}
		return productComponent;
	}
	
	private AppointmentDetailType getAppointmentDetailToRest(AppointmentDetailTypeVO appointmentDetailVO) {
		AppointmentDetailType appointmentDetailType = new AppointmentDetailType();
		if(appointmentDetailVO!=null){
			appointmentDetailType.setBookedAppointment(getBookedAppointmentDateToRest(appointmentDetailVO.getBookedAppointmentDate()));
			appointmentDetailType.setPreferredAppointmentDates(getPreferredAppointmentDateToRest(appointmentDetailVO.getPreferredAppointmentDates()));
		}
		return appointmentDetailType;
	}
	
	private AppointmentType getBookedAppointmentDateToRest(AppointmentTypeVO bookedAppointmentDate) {
		AppointmentType appointmentType = new AppointmentType();
		if(bookedAppointmentDate!=null){
			if(bookedAppointmentDate.getAppointmentDate()!=null){
				appointmentType.setAppointmentDate(bookedAppointmentDate.getAppointmentDate() != null? new LocalDate(bookedAppointmentDate.getAppointmentDate()): null);
			}
			if(!StringUtils.isBlank(bookedAppointmentDate.getAppointmentId())){
				appointmentType.setAppointmentId(bookedAppointmentDate.getAppointmentId());
			}
			if(bookedAppointmentDate.getAppointmentTimeSlot()!=null){
				appointmentType.setAppointmentTimeSlot(getTimeSlotToRest(bookedAppointmentDate.getAppointmentTimeSlot()));
			}
		}
		return appointmentType;
	}
	
	private AppointmentTimeSlot getTimeSlotToRest(AppointmentTimeSlotVO appointmentTimeSlot) {
		AppointmentTimeSlot timeSlot = new AppointmentTimeSlot();
		if(StringUtils.isNotBlank(appointmentTimeSlot.getStartTime())){
			timeSlot.setStartTime(appointmentTimeSlot.getStartTime());
		}
		if(StringUtils.isNotBlank(appointmentTimeSlot.getEndTime())){
			timeSlot.setEndTime(appointmentTimeSlot.getEndTime());
		}		
		return timeSlot;
	}
	
	private List<AppointmentType> getPreferredAppointmentDateToRest(
			List<AppointmentTypeVO> preferredAppointmentDatesVO) {
		List<AppointmentType> appointmentTypeList = new ArrayList<AppointmentType>();
		if(preferredAppointmentDatesVO!=null && !preferredAppointmentDatesVO.isEmpty()){
			for(AppointmentTypeVO appointmentTypeVO : preferredAppointmentDatesVO){
				AppointmentType appointmentType = new AppointmentType();
				if(appointmentTypeVO.getAppointmentDate()!=null){
					appointmentType.setAppointmentDate(appointmentTypeVO.getAppointmentDate() != null? new LocalDate(appointmentTypeVO.getAppointmentDate()): null);
				}
				if(!StringUtils.isBlank(appointmentTypeVO.getAppointmentId())){
					appointmentType.setAppointmentId(appointmentTypeVO.getAppointmentId());
				}
				appointmentTypeList.add(appointmentType);
			}
		}
		return appointmentTypeList;
	}
	
	private List<ProductCatalogItemRef> getDiscountsToRest(List<FFHDiscountTypeVO> discountsVO) {

		List<ProductCatalogItemRef> discountList = new ArrayList<ProductCatalogItemRef>();
		if (discountsVO != null && !discountsVO.isEmpty()) {
			for (FFHDiscountTypeVO discountTypeVO : discountsVO) {
				if (discountTypeVO.getProductCatalogueIdentifiers() != null
						&& !discountTypeVO.getProductCatalogueIdentifiers().isEmpty()) {
					for (ProductComponentVO discountItemVO : discountTypeVO.getProductCatalogueIdentifiers()) {
						ProductCatalogItemRef discount = new ProductCatalogItemRef();
						discountList.add(discount);
						addMarketOfferClassification(discount, EnterpriseWLNSalesServicesConstants.CLS_DISCOUNT);
						addAction(discount, discountItemVO.getAction());
						addDiscountCode(discount, discountTypeVO.getDiscountCode());
						discount.setProductCatalogueIdentifier(getProductCatalogueIdentifierToRest(discountItemVO));
					}
				} else {
					ProductCatalogItemRef discount = new ProductCatalogItemRef();
					discountList.add(discount);
					addMarketOfferClassification(discount, EnterpriseWLNSalesServicesConstants.CLS_DISCOUNT);
				}
			}
		}
		return discountList;
	}
	
	private List<ProductCatalogItemRef> getAdditionalProductItemToRest(
			List<AdditionalProductItemTypeVO> list) {
		
		List<ProductCatalogItemRef> result = new ArrayList<ProductCatalogItemRef>();
		if (list != null && !list.isEmpty()) {
			for (AdditionalProductItemTypeVO additionalProductItemTypeVO : list) {
				ProductCatalogItemRef additionalProductItem = new ProductCatalogItemRef();
				result.add(additionalProductItem);
				addAction(additionalProductItem, additionalProductItemTypeVO.getProductCatalogueIdentifier().getAction());
				additionalProductItem.setProductCatalogueIdentifier(getProductCatalogueIdentifierToRest(additionalProductItemTypeVO.getProductCatalogueIdentifier()));
			}
		}
		return result;
	}
	
	private List<ProductCatalogItemRef> getProductCatalogueIdentifierListToRest(
			List<ProductComponentVO> productCatalogueIdentifiersVO) {
		List<ProductCatalogItemRef> productComponentList = new ArrayList<ProductCatalogItemRef>();
		if(productCatalogueIdentifiersVO!=null && !productCatalogueIdentifiersVO.isEmpty()){
			for(ProductComponentVO productComponentVO: productCatalogueIdentifiersVO){
				ProductCatalogItemRef productComponent = new ProductCatalogItemRef();
				productComponentList.add(productComponent);
				addMarketOfferClassification(productComponent, EnterpriseWLNSalesServicesConstants.CLS_PRODUCT_COMPONENT);
				addAction(productComponent, productComponentVO.getAction());
				productComponent.setProductCatalogueIdentifier(mapProductItemIdentifier(productComponentVO));
			}
		}
		return productComponentList;
	}
	
	private ProductItemIdentifier mapProductItemIdentifier(ProductComponentVO productComponentVO) {
		if(productComponentVO == null) {
			return null;
		}
		ProductItemIdentifier productItemIdentifier = new ProductItemIdentifier();
		productItemIdentifier.setProductCatalogueId(productComponentVO.getProductCatalogueId());
		productItemIdentifier.setParentProductCatalogueId(productComponentVO.getParentProductCatalogueId());
		return productItemIdentifier;
		
	}

	private List<WirelineEquipmentType> getEquipmentsToRest(List<FFHEquipmentTypeVO> equipmentsVO) {
		List<WirelineEquipmentType> equipmentTypeList = new ArrayList<WirelineEquipmentType>();
		if(equipmentsVO!=null && !equipmentsVO.isEmpty()){
			for(FFHEquipmentTypeVO equipmentVO : equipmentsVO){
				WirelineEquipmentType equipment = new WirelineEquipmentType();
				equipment.setActionCd(equipmentVO.getAction());
				if(!StringUtils.isBlank(equipmentVO.getDeliveryMethodType())){
					equipment.setDeliveryMethodTypeCd(equipmentVO.getDeliveryMethodType());
				}
				if(!StringUtils.isBlank(equipmentVO.getMaterialItemCode())){
					equipment.setMaterialItemCode(equipmentVO.getMaterialItemCode());
				}
				if(equipmentVO.getProductCatalogueIdentifier()!=null){
					equipment.setProductCatalogueIdentifier(getProductCatalogueIdentifierToRest(equipmentVO.getProductCatalogueIdentifier()));
					equipment.setActionCd(equipmentVO.getProductCatalogueIdentifier().getAction());
				}
				if(equipmentVO.getAcquisitionType()!=null){
					equipment.setAcquisitionType(getAcquisitionTypeToRest(equipmentVO.getAcquisitionType()));
				}
				if(equipmentVO.getPaymentOption() !=null){
					equipment.setPaymentOption(getPaymentOptionToRest(equipmentVO.getPaymentOption()));
				}
				equipmentTypeList.add(equipment);
			}
		}
		return equipmentTypeList;
	}
	
	private EquipmentAcquisitionType getAcquisitionTypeToRest(EquipmentAcquisitionTypeVO acquisitionTypeVO) {
		EquipmentAcquisitionType equipmentAcquisition = new EquipmentAcquisitionType();
		if(acquisitionTypeVO!=null){
			if(acquisitionTypeVO.isBuyIndicator()!=null){
				equipmentAcquisition.setBuyIndicator(acquisitionTypeVO.isBuyIndicator());
			}
			if(acquisitionTypeVO.isCustomerOwnedIndicator()!=null){
				equipmentAcquisition.setCustomerOwnedIndicator(acquisitionTypeVO.isCustomerOwnedIndicator());
			}
			if(acquisitionTypeVO.isRentalEquipmentIndicator()!=null){
				equipmentAcquisition.setRentalEquipmentIndicator(acquisitionTypeVO.isRentalEquipmentIndicator());
			}
			
		}
		return equipmentAcquisition;
	}
	
	private PaymentOptionType getPaymentOptionToRest(PaymentOptionVO paymentOptionVO) {
		PaymentOptionType paymentOption = new PaymentOptionType();
		if(paymentOptionVO!=null){
			if(paymentOptionVO.getPayOptionTerm()!=null){
				paymentOption.setPayOptionTerm(paymentOptionVO.getPayOptionTerm());
			}
			if(paymentOptionVO.getPayOptionType()!=null){
				paymentOption.setPayOptionType(paymentOptionVO.getPayOptionType());
			}
		}
		return paymentOption;
	}
	
	private ProductOrderType getProductOrderTypeToRest(ProductOrderTypeVO productOrderTypeVO) {
		ProductOrderType productOrderType = new ProductOrderType();
		if(!StringUtils.isBlank(productOrderTypeVO.getOrderTypeCd())){
			productOrderType.setOrderTypeCd(productOrderTypeVO.getOrderTypeCd());
		}
		if(!StringUtils.isBlank(productOrderTypeVO.getOrderSubTypeCd())){
			productOrderType.setOrderSubTypeCd(productOrderTypeVO.getOrderSubTypeCd());
		}
		return productOrderType;
	}
	
	private List<SelectedSweetenerType> getSweetenersToRest(List<FFHSweetenerTypeVO> sweetenersVO) {
		
		List<SelectedSweetenerType> result = new ArrayList<SelectedSweetenerType>();
		if (sweetenersVO != null && !sweetenersVO.isEmpty()) {
			for (FFHSweetenerTypeVO sweetenerVO : sweetenersVO) {
				SelectedSweetenerType sweetener = new SelectedSweetenerType();
				result.add(sweetener);
				if (sweetenerVO.getOfferHeader() != null) {
					sweetener.setProductOffering(getOfferHeaderToRest(sweetenerVO.getOfferHeader()));
				}
				
				if (sweetenerVO.getDiscounts() != null && !sweetenerVO.getDiscounts().isEmpty()) {
					for (FFHDiscountTypeVO discountVO : sweetenerVO.getDiscounts()) {
						if (discountVO.getProductCatalogueIdentifiers() != null && !discountVO.getProductCatalogueIdentifiers().isEmpty()) {
							
							for (ProductComponentVO x: discountVO.getProductCatalogueIdentifiers()) {
								ProductCatalogItemRef discount = new ProductCatalogItemRef();
								//addMarketOfferClassification(discount, EnterpriseWLNSalesServicesConstants.CLS_DISCOUNT);
								//QC-71329 - sweetener issue in the shopping cart, use sweetener indicator instead of regular promotion
								addMarketOfferClassification(discount, EnterpriseWLNSalesServicesConstants.CLS_SWEETENER);
								addDiscountCode(discount, discountVO.getDiscountCode());
								discount.setProductCatalogueIdentifier(getProductCatalogueIdentifierToRest(x));
								addAction(discount, x.getAction());
								sweetener.addProductCatalogItemsItem(discount);
							}
							
						} else {
							//no productComponentVO, only discount code
							ProductCatalogItemRef discount = new ProductCatalogItemRef();
							sweetener.addProductCatalogItemsItem(discount);
							//QC-71329 - sweetener issue in the shopping cart
							//addMarketOfferClassification(discount, EnterpriseWLNSalesServicesConstants.CLS_DISCOUNT);
							addMarketOfferClassification(discount, EnterpriseWLNSalesServicesConstants.CLS_SWEETENER);
							addDiscountCode(discount, discountVO.getDiscountCode());
						}
					}
				}
				
				sweetener.setRelatedPromotionList(getRelatedPromotionListToRest(sweetenerVO.getRelatedPromotionList()));
			}
		}
		return result;
	}
	
	private ProductType getInternetProductToRest(InternetProductTypeVO internetProductVO) {
		
		ProductType internet = null;
		if (internetProductVO != null) {
			internet = getProductTypeToRest(internetProductVO);
			internet.setProductTypeCd(EnterpriseWLNSalesServicesConstants.HSIC);


			//CompetitiveExchangeCarrierInfo is removed from v2.1 yaml
			//if (internetProductVO.getCompetitiveExchangeCarrierInfo() != null) {
			//	internet.setCompetitiveExchangeCarrierInfo(getCompetitiveExchangeCarrierInfoToRest(internetProductVO.getCompetitiveExchangeCarrierInfo()));
			//}
			if(internetProductVO.getCompetitiveExchangeCarrierInfo() != null) {
				if(internetProductVO.getCompetitiveExchangeCarrierInfo().getCompetitiveExchangeCarrierInd() != null) {
					internet.addProductCharacteristicItem(createProductCharacteristic(CLEC_INDICATOR, String.valueOf(internetProductVO.getCompetitiveExchangeCarrierInfo().getCompetitiveExchangeCarrierInd())));
				}
				if(internetProductVO.getCompetitiveExchangeCarrierInfo().getCompetitiveLocalExchageCarrierId() != null) {
					internet.addProductCharacteristicItem(createProductCharacteristic(CLEC_ID, internetProductVO.getCompetitiveExchangeCarrierInfo().getCompetitiveLocalExchageCarrierId()));
				}
				if(internetProductVO.getCompetitiveExchangeCarrierInfo().getClecPhoneNumber() != null
						&& internetProductVO.getCompetitiveExchangeCarrierInfo().getClecPhoneNumber().getPhoneNumber() != null) {
					internet.addProductCharacteristicItem(createProductCharacteristic(CLEC_PHONE_NUMBER, String.valueOf(internetProductVO.getCompetitiveExchangeCarrierInfo().getClecPhoneNumber().getPhoneNumber())));
				}
			}
		}
		return internet;
	}

	private ProductCharacteristic createProductCharacteristic(String name, String value) {
		ProductCharacteristic productCharacteristic = new ProductCharacteristic();
		productCharacteristic.setName(name);
		productCharacteristic.setValue(value);
		return productCharacteristic;
	}

	private ProductType getTelevisionProductToRest(TelevisionProductTypeVO televisionProductVO) {
		
		ProductType televisionProduct = null;
		if (televisionProductVO != null) {
			televisionProduct = getProductTypeToRest(televisionProductVO);
			televisionProduct.setProductTypeCd(EnterpriseWLNSalesServicesConstants.TTV);

			/* TODO:: CompetitiveLocalExchangeCarrierType moved under Internet Product.
			if(televisionProductVO.isCompetitiveExchangeCarrierInd()!=null){
				televisionProduct.setCompetitiveExchangeCarrierInd(televisionProductVO.isCompetitiveExchangeCarrierInd());
			}
			*/
		}
		return televisionProduct;

	}
	
	//NWLN-10222
	private ProductType getAccessoryProductToRest(AccessoryProductTypeVO accessoryProductVO) {
		
		ProductType accessoryProduct = null;
		if (accessoryProductVO != null) {
			accessoryProduct = getProductTypeToRest(accessoryProductVO);
			accessoryProduct.setProductTypeCd(EnterpriseWLNSalesServicesConstants.OIS_ACCESSORIES_CD);
		}
		return accessoryProduct;
	}
	
	public ShipmentDetailType getShipmentDetailToRest(ShipmentDetailTypeVO shipmentDetailVO) {
		ShipmentDetailType shipmentDetailType = new ShipmentDetailType();
		if(shipmentDetailVO!=null && shipmentDetailVO.getShipmentAddress()!=null){
			shipmentDetailType.setShipmentAddress(getShipmentAddressToRest(shipmentDetailVO.getShipmentAddress()));
		}
		return shipmentDetailType;
	}

	private ShipmentAddressType getShipmentAddressToRest(ShipmentAddressTypeVO shipmentAddressVO) {
		ShipmentAddressType shipmentAddressType = new ShipmentAddressType();
		if(shipmentAddressVO!=null){
			shipmentAddressVO.getxCoordinate();
			
			shipmentAddressType.setXCoordinateId(shipmentAddressVO.getxCoordinate());
			shipmentAddressType.setYCoordinateId(shipmentAddressVO.getyCoordinate());
			shipmentAddressType.setGeocodingcalculationMethodCode(shipmentAddressVO.getGeocodingcalculationMethodCode());
			shipmentAddressType.setGeocodingMatchTypeCode(shipmentAddressVO.getGeocodingMatchTypeCode());
			shipmentAddressType.setConfidenceLevelCode(shipmentAddressVO.getConfidenceLevelCode());
			if(shipmentAddressVO.getAddressId()!=null){
				shipmentAddressType.setAddressId(String.valueOf(shipmentAddressVO.getAddressId()));
			}
			shipmentAddressType.setAddressTypeCode(shipmentAddressVO.getAddressTypeCode());
			if(!CollectionUtils.isEmpty(shipmentAddressVO.getAdditionalAddressInformation())){
				shipmentAddressType.setAdditionalAddressInformationList(shipmentAddressVO.getAdditionalAddressInformation());
			}
			if(!CollectionUtils.isEmpty(shipmentAddressVO.getRenderedAddress())){
				shipmentAddressType.setRenderedAddressList(shipmentAddressVO.getRenderedAddress());
			}
			if(shipmentAddressVO.getAddrAssgnmtId()!=null){
				shipmentAddressType.setAddressAssignmentId(String.valueOf(shipmentAddressVO.getAddrAssgnmtId()));
			}
			shipmentAddressType.setAddressNumberPrefixTxt(shipmentAddressVO.getAddressNumberPrefixTxt());
			shipmentAddressType.setAddressAssignmentTypeCd(shipmentAddressVO.getAddressAssignmentTypeCode());
			shipmentAddressType.setAddressAssignmentSubTypeCd(shipmentAddressVO.getAddressAssignmentSubTypeCode());
			shipmentAddressType.setAddressMatchingStatusCd(shipmentAddressVO.getAddressMatchingStatusCode());
			shipmentAddressType.setAddressSearchTxt(shipmentAddressVO.getAddressSearchText());
			shipmentAddressType.setCountryCode(shipmentAddressVO.getCountryCode());
			shipmentAddressType.setEmailAddressTxt(shipmentAddressVO.getEmailAddressText());
			shipmentAddressType.setExternalAddressId(shipmentAddressVO.getExternalAddressId());
			if(shipmentAddressVO.getExternalAddressSourceId()!=null)
			shipmentAddressType.setExternalAddressSourceId(String.valueOf(shipmentAddressVO.getExternalAddressSourceId()));
			shipmentAddressType.setExternalServiceAddressId(shipmentAddressVO.getExternalServiceAddressId());
			shipmentAddressType.setMailingTypeCode(shipmentAddressVO.getMailingTypeCode());
			shipmentAddressType.setMunicipalityName(shipmentAddressVO.getMunicipalityName());
			shipmentAddressType.setPostalZipCode(shipmentAddressVO.getPostalZipCode());
			shipmentAddressType.setProvinceStateCode(shipmentAddressVO.getProvinceStateCode());
			if(shipmentAddressVO.getRelateAddressAssignmentId()!=null)
			shipmentAddressType.setRelatedAddressAssignmentId(String.valueOf(shipmentAddressVO.getRelateAddressAssignmentId()));
			shipmentAddressType.setStreetDirectionPrefixCd(shipmentAddressVO.getStreetDirectionPrefixCd());
			shipmentAddressType.setStreetDirectionSuffixCd(shipmentAddressVO.getStreetDirectionSuffixCd());
			shipmentAddressType.setStreetNumber(shipmentAddressVO.getStreetNumber());
			shipmentAddressType.setStreetName(shipmentAddressVO.getStreetName());
			shipmentAddressType.setUnitNumber(shipmentAddressVO.getUnitNumber());
			shipmentAddressType.setUnitName(shipmentAddressVO.getUnitName());
			shipmentAddressType.setUnitTypeCode(shipmentAddressVO.getUnitTypeCode());
			shipmentAddressType.setBuildingName(shipmentAddressVO.getBuildingName());
			shipmentAddressType.setBuildingTypeCd(shipmentAddressVO.getBuildingTypeCd());
			if(!StringUtils.isEmpty(shipmentAddressVO.getValidateAddressIndicator()))
			shipmentAddressType.setValidateAddressIndicator(Boolean.valueOf(shipmentAddressVO.getValidateAddressIndicator()));
			shipmentAddressType.setCanadaPostBuildName(shipmentAddressVO.getCanadaPostBuildName());
			shipmentAddressType.setCanadaPostLocationnName(shipmentAddressVO.getCanadaPostLocnName());
			shipmentAddressType.setCanadaPostRecordTypeCode(shipmentAddressVO.getCanadaPostRecordType());
			shipmentAddressType.setCareOfTxt(shipmentAddressVO.getCareOf());
			shipmentAddressType.setCivicNumber(shipmentAddressVO.getCivicNumber());
			shipmentAddressType.setCivicNumberSuffixTxt(shipmentAddressVO.getCivicNumberSuffix());
			shipmentAddressType.setPostOfficeBoxNumber(shipmentAddressVO.getPostOfficeBoxNumber());
			shipmentAddressType.setRuralRouteNumber(shipmentAddressVO.getRuralRouteNumber());
			shipmentAddressType.setRuralRouteTypeCode(shipmentAddressVO.getRuralRouteTypeCode());
			shipmentAddressType.setStationAreaName(shipmentAddressVO.getStationName());
			shipmentAddressType.setStationQualifierCd(shipmentAddressVO.getStationQualifier());
			shipmentAddressType.setStationTypeCode(shipmentAddressVO.getStationTypeCode());
			shipmentAddressType.setFloorTypeCd(shipmentAddressVO.getFloorTypeCd());
			shipmentAddressType.setFloorName(shipmentAddressVO.getFloorName());
			shipmentAddressType.setLandmarkName(shipmentAddressVO.getLandmarkName());
			shipmentAddressType.setPlaceName(shipmentAddressVO.getPlaceName());
			
		}
		return shipmentAddressType;
	}

	private List<AccessoryOfferVO> getFromRest(CartItemGiftWithPurchase cartItemGiftWithPurchase) {
		List<AccessoryOfferVO> accessoryOfferList = null;

		if ( (cartItemGiftWithPurchase.getSelectedAccessoryOffers() != null) && (!cartItemGiftWithPurchase.getSelectedAccessoryOffers().isEmpty()) ) {
			accessoryOfferList = new ArrayList<AccessoryOfferVO>();

			for (AccessoryOffer accessoryOffer : cartItemGiftWithPurchase.getSelectedAccessoryOffers()) {
				AccessoryOfferVO accessoryOfferVO = new AccessoryOfferVO();
				AccessoryOfferHeaderVO accessoryOfferHeaderVO = new AccessoryOfferHeaderVO();
				accessoryOfferHeaderVO.setOfferTypeCd(accessoryOffer.getOfferHeader().getOfferTypeCd());
				accessoryOfferVO.setOfferHeader(accessoryOfferHeaderVO);

				FFHOfferHeaderVO FFHOfferHeaderVO = new FFHOfferHeaderVO();
				FFHOfferHeaderVO.setOfferCode(accessoryOffer.getOfferHeader().getOfferCode());
				FFHOfferHeaderVO.setOfferId(accessoryOffer.getOfferHeader().getOfferId());
				FFHOfferHeaderVO.setPerspectiveDate(accessoryOffer.getOfferHeader().getPerspectiveDate() != null? accessoryOffer.getOfferHeader().getPerspectiveDate().toDate(): null);
				FFHOfferHeaderVO.setSystemId(accessoryOffer.getOfferHeader().getSystemId());
				FFHOfferHeaderVO.setOfferProgramId(accessoryOffer.getOfferHeader().getOfferProgramId());
				accessoryOfferHeaderVO.setOfferHeader(FFHOfferHeaderVO);

				accessoryOfferList.add(accessoryOfferVO);
			}
		}

		return accessoryOfferList;
	}

	public List<AccessoryOffer> getAccessoryOffersToRest(List<AccessoryOfferVO> accessoryOfferVOList) {
		List<AccessoryOffer> accessoryOfferList = null;

		if ( accessoryOfferVOList != null && !accessoryOfferVOList.isEmpty() ) {
			accessoryOfferList = new ArrayList<AccessoryOffer>();

			for (AccessoryOfferVO accessoryOfferVO : accessoryOfferVOList) {
				AccessoryOffer accessoryOffer = new AccessoryOffer();
				AccessoryOfferHeader accessoryOfferHeader = new AccessoryOfferHeader();
				accessoryOfferHeader.setOfferTypeCd(accessoryOfferVO.getOfferHeader().getOfferTypeCd());
				accessoryOffer.setOfferHeader(accessoryOfferHeader);

				accessoryOfferHeader.setOfferCode(accessoryOfferVO.getOfferHeader().getOfferHeader().getOfferCode());
				accessoryOfferHeader.setOfferId(accessoryOfferVO.getOfferHeader().getOfferHeader().getOfferId());
				//accessoryOfferHeader.setPerspectiveDate(accessoryOfferVO.getOfferHeader().getOfferHeader().getPerspectiveDate());
				accessoryOfferHeader.setSystemId(accessoryOfferVO.getOfferHeader().getOfferHeader().getSystemId());
				accessoryOfferHeader.setOfferProgramId(accessoryOfferVO.getOfferHeader().getOfferHeader().getOfferProgramId());

				accessoryOfferList.add(accessoryOffer);
			}
		}

		return accessoryOfferList;
	}

	public List<RelatedImmediatePromotion> getRelatedPromotionListToRest(List<RelatedImmediatePromotionVO> relatedPromotionList) {
		List<RelatedImmediatePromotion> promos = null;
		if(relatedPromotionList != null) {
			promos = new ArrayList<RelatedImmediatePromotion>();
			for(RelatedImmediatePromotionVO prVO: relatedPromotionList) {
				if(prVO != null) {
					RelatedImmediatePromotion pr = new RelatedImmediatePromotion();
					pr.setId(prVO.getId());
					pr.setItemQualificationType(prVO.getItemQualificationType());
					pr.setItemStatus((prVO.getItemStatus()));
					pr.setPerspectiveDate(prVO.getPerspectiveDate() != null? new DateTime(prVO.getPerspectiveDate()): null);
					pr.setSelectedCoupon(getSelectedCouponToRest(prVO.getSelectedCoupon()));
					promos.add(pr);
				}
			}
		}
		return promos;
	}

	private SelectedCoupon getSelectedCouponToRest(SelectedCouponVO selectedCouponVO) {
		SelectedCoupon result = null;
		if(selectedCouponVO != null) {
			result = new SelectedCoupon();
			result.setId(selectedCouponVO.getId());
			result.setPerspectiveDate(selectedCouponVO.getPerspectiveDate() != null? new DateTime(selectedCouponVO.getPerspectiveDate()): null);
			result.setValidationCode(selectedCouponVO.getValidationCode());
		}
		return result;
	}
}
