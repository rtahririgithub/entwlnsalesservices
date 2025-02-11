package com.telus.csm.ewlnsis.soap.operation;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailRequestVO;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO;
import com.telus.csm.ewlnsc.domain.SalesOfferCriteriaVO;
import com.telus.csm.ewlnsc.grid.domain.EquipmentCatalogueItemDO;
import com.telus.csm.ewlnsc.helper.CommonWLNGridHelper;
import com.telus.csm.ewlnsc.helper.GetSalesOfferDetailHelper;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.transformer.GetAvailableOfferSummaryTransformer;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetSalesOfferDetailResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.GetSalesOfferDetailType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.SalesOfferDetailFilter;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.EnterpriseSalesOfferDetail;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesOfferProduct;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesWirelineEquipment;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesWirelineEquipmentItem;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferHeaderWithOfferFilter;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferProduct;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.OfferHeader;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.OfferProduct;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueItem;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductComponent;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProgramPromotion;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineEquipment;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineEquipmentItem;

/**
 * 
 * @author Jose.Mena
 *
 */
@Component
@Scope(SCOPE_PROTOTYPE)
public class GetSalesOfferDetailOperation {

	private static final LoggerUtil logger = LoggerUtil.getLogger(GetSalesOfferDetailOperation.class);

	@Autowired
    private GetSalesOfferDetailHelper getSalesOfferDetailHelper;

	public GetSalesOfferDetailResponseType execute(GetSalesOfferDetailType param) {
		String functionName = "execute()";
		logger.enter(functionName);
		
		/*
		 * Calling GetSalesOfferDetailHelper
		 */
		GetSalesOfferDetailRequestVO getSalesOfferDetailRequestVO = transform(param);
		
		GetSalesOfferDetailResponseVO getSalesOfferDetailResponseVO = getSalesOfferDetailHelper.execute(getSalesOfferDetailRequestVO);
		
		GetSalesOfferDetailResponseType response = transform(getSalesOfferDetailResponseVO);
		logger.exit(functionName);
		return response;
	}
	
	public static GetSalesOfferDetailRequestVO transform(GetSalesOfferDetailType param) {
		GetSalesOfferDetailRequestVO request = new GetSalesOfferDetailRequestVO();
		request.setOperationHeader(param.getOperationHeader());

		WirelineOfferHeaderWithOfferFilter selectedOffer = param.getWirelineOfferDetail().getSelectedOffer();
		request.setOfferInstanceId(selectedOffer.getOfferInstanceId());
		request.setOfferId(selectedOffer.getOfferId());
		request.setOfferCode(selectedOffer.getOfferCode());
		request.setOfferProgramId(selectedOffer.getOfferProgramId());
		request.setSystemID(selectedOffer.getSystemId());
		request.setPerspectiveDate(selectedOffer.getPerspectiveDate());
		
		request.setSalesOfferCriteria(getSalesOfferCriteria(param.getWirelineOfferDetail()));
		
		return request;
	}
	
	private static SalesOfferCriteriaVO getSalesOfferCriteria(SalesOfferDetailFilter salesOfferDetailFilter) {
		SalesOfferCriteriaVO salesOfferCriteriaVO = new SalesOfferCriteriaVO();
		if(salesOfferDetailFilter!=null){
			salesOfferCriteriaVO.setBillingAccountNumber(salesOfferDetailFilter.getBillingAccountNumber());
			salesOfferCriteriaVO.setCustomerId(salesOfferDetailFilter.getCustomerId());
			if(salesOfferDetailFilter.getServiceAddress()!=null){
				salesOfferCriteriaVO.setServiceAddress(GetAvailableOfferSummaryTransformer.getServiceAddress(salesOfferDetailFilter.getServiceAddress()));	
			}
			salesOfferCriteriaVO.setOfferFilter(salesOfferDetailFilter.getSelectedOffer().getOfferFilter());
			salesOfferCriteriaVO.setPromotionCd(salesOfferDetailFilter.getSelectedOffer().getPromotionCode());
			salesOfferCriteriaVO.setSubscribedServiceIdentifier(GetAvailableOfferSummaryTransformer.getServiceIdentifier(salesOfferDetailFilter.getSubscribedServiceIdentifierList()));
		}
		return salesOfferCriteriaVO;
	}

	public static GetSalesOfferDetailResponseType transform(GetSalesOfferDetailResponseVO getSalesOfferDetailResponseVO) {
		GetSalesOfferDetailResponseType result = new GetSalesOfferDetailResponseType();
		
		EnterpriseSalesOfferDetail enterpriseSalesOfferDetail = transform(getSalesOfferDetailResponseVO, getSalesOfferDetailResponseVO.getOffer(), getSalesOfferDetailResponseVO.getProgramPromotion());
		result.setOfferDetail(enterpriseSalesOfferDetail);
		
		result.setMessageList(transform(getSalesOfferDetailResponseVO.getMessageList()));
		
		return result;
	}

	public static EnterpriseSalesOfferDetail transform(GetSalesOfferDetailResponseVO getSalesOfferDetailResponseVO, Offer oisOffer, ProgramPromotion programPromotion) {
		
		if (oisOffer == null) {
			return null;
		}
		
		EnterpriseSalesOfferDetail result = new EnterpriseSalesOfferDetail();
		
		OfferHeader offerHeader = new OfferHeader();
		offerHeader.setSourceSystemId(oisOffer.getSourceSystemId());
		offerHeader.setProgramId(oisOffer.getProgramId());
		offerHeader.setProgramCode(oisOffer.getProgramCode());
		offerHeader.setProgramDescriptionList(oisOffer.getProgramDescriptionList());
		offerHeader.setPerspectiveDate(oisOffer.getPerspectiveDate());
		offerHeader.setEffectiveDate(oisOffer.getEffectiveDate());
		offerHeader.setExpiryDate(oisOffer.getExpiryDate());
		offerHeader.setBrandId(oisOffer.getBrandId());
		offerHeader.setPromotionCode(oisOffer.getPromotionCode());
		offerHeader.setTypeCode(oisOffer.getTypeCode());

		offerHeader.setOfferId(oisOffer.getOfferId());
		offerHeader.setOfferDescriptionList(oisOffer.getOfferDescriptionList());
		offerHeader.setOfferTierId(oisOffer.getOfferTierId());
		offerHeader.setOfferCategoryList(oisOffer.getOfferCategoryList());
		offerHeader.setBasePriceAmt(oisOffer.getBasePriceAmt());
		offerHeader.setWirelessOfferCode(oisOffer.getWirelessOfferCode());
		offerHeader.setPaymentSupportTypeCode(oisOffer.getPaymentSupportTypeCode());
		offerHeader.setRankingNum(oisOffer.getRankingNum());

		offerHeader.setOfferReferenceId(oisOffer.getOfferReferenceId());
		offerHeader.setAssignedOfferInd(oisOffer.isAssignedOfferInd());
		offerHeader.setOfferMarketingMessageList(oisOffer.getOfferMarketingMessageList());
		offerHeader.setProductEligiblityList(oisOffer.getProductEligiblityList());
		result.setOfferHeader(offerHeader);

		OfferProduct oisOfferProduct = oisOffer.getOfferProduct();
		result.setOfferProduct(transform(getSalesOfferDetailResponseVO, oisOfferProduct));

		result.setPromotionCodeOfferInfo(programPromotion);
	
		return result;
	}

	private static SalesOfferProduct transform(GetSalesOfferDetailResponseVO getSalesOfferDetailResponseVO, OfferProduct oisOfferProduct) {
		SalesOfferProduct result = new SalesOfferProduct();
		
		for (com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct oisWirelineOfferProduct : oisOfferProduct.getWirelineOfferProductList()) {
			result.getWirelineOfferProductList().add(transform(getSalesOfferDetailResponseVO, oisWirelineOfferProduct));
		}
		return result;
	}

	private static WirelineOfferProduct transform(GetSalesOfferDetailResponseVO getSalesOfferDetailResponseVO,
			com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct oisWirelineOfferProduct) {

		WirelineOfferProduct result = new WirelineOfferProduct();
		
		result.setProductTypeCode(oisWirelineOfferProduct.getProductTypeCode());
		result.setProductTemplateProductTypeCode(oisWirelineOfferProduct.getProductTemplateProductTypeCode());
		result.setTransactionTypeList(oisWirelineOfferProduct.getTransactionTypeList());
		result.setContractTermList(oisWirelineOfferProduct.getContractTermList());
		result.setProductTemplateIdentifier(oisWirelineOfferProduct.getProductTemplateIdentifier());
		result.setProductComponentList(oisWirelineOfferProduct.getProductComponentList());
		result.setProductCatalogSystemId(oisWirelineOfferProduct.getProductCatalogSystemId());
		result.setMainComponentIdentifier(oisWirelineOfferProduct.getMainComponentIdentifier());
		result.setProductCategoryCode(oisWirelineOfferProduct.getProductCategoryCode());
		
		for (WirelineEquipment oisWirelineEquipment : oisWirelineOfferProduct.getWirelineEquipmentList()) {
			result.getWirelineEquipmentList().add(transform(oisWirelineEquipment));
		}
		
		result.setAddOn(oisWirelineOfferProduct.getAddOn());
		result.setDiscountList(oisWirelineOfferProduct.getDiscountList());
		result.setFeature(oisWirelineOfferProduct.getFeature());
		
		if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(oisWirelineOfferProduct.getProductTypeCode())){
			String externalCId = getExternalCidFromOffer(oisWirelineOfferProduct.getProductComponentList());
			
			String hsPack = WLNOfferUtil.mapOmsCode(externalCId);
			
			String nonSellableProduct = ApplicationProperties.getConfigString("${common/getAvailableOfferSummaryListParams/nonSellableProduct}");
			
			if(hsPack.equalsIgnoreCase(nonSellableProduct)){
				result.setSellableInd(false);
			}else{
				result.setSellableInd(true);
			}
		}else{
			result.setSellableInd(true);
		}
		
		result.setRecontractEligibilityInd(getSalesOfferDetailResponseVO.getRecontractEligibleProductCodeList().contains(oisWirelineOfferProduct.getProductTypeCode()));

		return result;
	}

	private static String getExternalCidFromOffer(List<ProductComponent> productComponentList) {
		String externalProductCatalogueId=null;
		if(productComponentList!=null && !productComponentList.isEmpty()){
			for(ProductComponent productComponent : productComponentList){
				for (ProductCatalogueItem productCatalogueItem : productComponent.getProductCatalogueItemList()) {
					externalProductCatalogueId = productCatalogueItem.getProductCatalogueIdentifier()
							.getExternalProductCatalogId();
					break;
				}

			}
		}
		return externalProductCatalogueId;
	}

	private static SalesWirelineEquipment transform(WirelineEquipment oisWirelineEquipment) {
		
		SalesWirelineEquipment result = new SalesWirelineEquipment();
		
		result.setProductCatalogueItem(oisWirelineEquipment.getProductCatalogueItem());
		result.setMinQty(oisWirelineEquipment.getMinQty());
		result.setMaxQty(oisWirelineEquipment.getMaxQty());
		
		for (WirelineEquipmentItem s : oisWirelineEquipment.getPurchaseEquipmentList()) {
			result.getPurchaseEquipmentList().add(transform(s));
		}
		
		for (WirelineEquipmentItem s : oisWirelineEquipment.getRentalEquipmentList()) {
			result.getRentalEquipmentList().add(transform(s));
		}
		
		for (WirelineEquipmentItem s : oisWirelineEquipment.getByodEquipmentList()) {
			result.getByodEquipmentList().add(transform(s));
		}

		return result;
	}

	private static SalesWirelineEquipmentItem transform(WirelineEquipmentItem s) {
		SalesWirelineEquipmentItem result = new SalesWirelineEquipmentItem();
		result.setMaterialItemCode(s.getMaterialItemCode());
		result.setEquipmentPurchasePriceAmt(s.getEquipmentPurchasePriceAmt());
		result.setEquipmentRentalPriceAmt(s.getEquipmentRentalPriceAmt());
		result.setEquipmentLateReturnPriceAmt(s.getEquipmentLateReturnPriceAmt());
		result.setEquipmentDescriptionList(s.getEquipmentDescriptionList());
		result.setDeliveryMethodList(s.getDeliveryMethodList());
		result.setIncludedInd(s.isIncludedInd());
		result.setPurchaseDiscountList(s.getPurchaseDiscountList());
		if (s.getPaymentOptionList() != null) {
			result.setPaymentOptionList(s.getPaymentOptionList());
		}
		CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
		EquipmentCatalogueItemDO equipmentDO = gridHelper.getEquipmentByProductCode(s.getMaterialItemCode());
		
		if(equipmentDO!=null && !StringUtils.isEmpty(equipmentDO.getModelName())){
			result.setModelName(equipmentDO.getModelName());
		}
		
		return result;
	}

	private static List<MessageList> transform(
			List<com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList> messageList) {
		List<MessageList> schemaMessageList = new ArrayList<MessageList>();
		for (com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList msgList: messageList) {
			MessageList msg = new MessageList();
			msg.setContextData(msgList.getContextData());
			msg.setDateTimeStamp(msgList.getDateTimeStamp());
			msg.setErrorCode(msgList.getErrorCode());
			msg.setMessageList(msgList.getMessageList());
			msg.setMessageType(msgList.getMessageType());
			msg.setTransactionId(msgList.getTransactionId());
			msg.setRelatedMessageList(msgList.getRelatedMessageList());
			schemaMessageList.add(msg);
		}
		return schemaMessageList;
	}

}
