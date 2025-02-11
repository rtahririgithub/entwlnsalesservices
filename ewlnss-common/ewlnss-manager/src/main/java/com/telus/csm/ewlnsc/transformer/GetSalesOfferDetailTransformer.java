package com.telus.csm.ewlnsc.transformer;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GSOD_OFFER_NOT_APPLICABLE;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.GSOD_OFFER_NOT_FOUND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.domain.GetOfferListByOfferIdentifierListAdapterRequest;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailRequestVO;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.util.EnterpriseSalesServiceUtil;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.ChannelOrgSummaryInfo;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.ChannelPartnerUserProfileType;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.ContactAddress;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.ContactDetails;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.OutletInfo;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.OfferIdentifier;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProgramPromotion;

/**
 * 
 * @author Jose.Mena
 *
 */
public class GetSalesOfferDetailTransformer {
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(GetSalesOfferDetailTransformer.class);

	private GetSalesOfferDetailTransformer() {
		
	}
	
	public static GetOfferListByOfferIdentifierListAdapterRequest transformToGetOfferRequest(GetSalesOfferDetailRequestVO request,SalesOfferCommonVO commonVO) {
		
		String functionName = "transform(GetSalesOfferDetailCoreRequest)";
		GetOfferListByOfferIdentifierListAdapterRequest result = new GetOfferListByOfferIdentifierListAdapterRequest();
		result.setBrandId(Long.valueOf(EnterpriseSalesServiceUtil.getBrandId(request.getOperationHeader().getBrandCode())));

		result.setSalesTransactionId(request.getOperationHeader().getSalesTransactionId());
		OutletInfo outlet = transformToOutletInfo(request.getOperationHeader().getUserProfile());
		result.setOutlet(outlet);
		result.setWirelineTransactionalContext(WLNOfferUtil.getWirelineTransactionalContext(request,commonVO,false));
		OfferIdentifier offerIdentifier = new OfferIdentifier();

		try {
			offerIdentifier.setOfferId(request.getOfferIdLong());
		} catch (NumberFormatException e) {
			logger.info(functionName, "Invalid OfferId: " + request.getOfferId());
		}			
		offerIdentifier.setOfferTypeCode(EnterpriseWLNSalesServicesConstants.WLN_OFFER_TYPE_CD);
		offerIdentifier.setPerspectiveDate(request.getPerspectiveDate());
//		offerIdentifier.setProgramCode(value);
		try {
			offerIdentifier.setProgramId(Long.parseLong(request.getOfferProgramId()));			
		} catch (NumberFormatException e) {
			logger.info(functionName, "Invalid OfferProgramId: " + request.getOfferProgramId());
		}
		result.getOfferIdentifierList().add(offerIdentifier);
		
		return result;
	}

	private static OutletInfo transformToOutletInfo(ChannelPartnerUserProfileType userProfile) {
		OutletInfo result = new OutletInfo();

		ChannelOrgSummaryInfo channelOrgInfo = new ChannelOrgSummaryInfo();
		channelOrgInfo.setChannelOrgType(userProfile.getChnlOrgTypeCode());
		channelOrgInfo.setInternalChannelOrgId(userProfile.getChnlOrgInternalId());
		channelOrgInfo.setChannelOrgCode(userProfile.getChnlOrgNumber());
		result.setChannelOrgInfo(channelOrgInfo);
		if (!userProfile.getSalesRepAssociatedOutletList().isEmpty()) {
			result.setInternalOutletId(userProfile.getSalesRepAssociatedOutletList().get(0).getSalesRepAssociatedOutletInternalId());
		}

		ContactDetails contactDetail = new ContactDetails();
		ContactAddress address = new ContactAddress();
		if (!userProfile.getOutletAssociatedProvinces().isEmpty()) {
			address.setProvince(userProfile.getOutletAssociatedProvinces().get(0));
		}
		contactDetail.setAddress(address);
		result.getContactDetails().add(contactDetail);
		
		return result;
	}

	public static void transform(SalesOfferCommonVO commonVO, Offer oisOffer, List<TraceVO> traces, GetSalesOfferDetailResponseVO response) {

		if ( (response != null) && (response.getOffer() != null) && (response.getOffer().getOfferProduct() != null) &&
			 (response.getOffer().getOfferProduct().getWirelineOfferProductList() != null) && (!response.getOffer().getOfferProduct().getWirelineOfferProductList().isEmpty()) ) {
			for (com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct offerProduct : response.getOffer().getOfferProduct().getWirelineOfferProductList()) {
				if ( (offerProduct.getDiscountList() != null) && (!offerProduct.getDiscountList().isEmpty()) ) {
					for (com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Discount discount : offerProduct.getDiscountList()) {
						if ( (discount != null) && (discount.getDiscountProductCatalogueItemList() != null) && (!discount.getDiscountProductCatalogueItemList().isEmpty()) ) {
							for (com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.DiscountProductCatalogueItem productCatalogueItem : discount.getDiscountProductCatalogueItemList()) {
								if ( (productCatalogueItem != null) && (productCatalogueItem.getDiscountType() != null) &&
									 (productCatalogueItem.getDiscountType().getDiscountTypeCode() != null) &&
									 (!productCatalogueItem.getDiscountType().getDiscountTypeCode().isEmpty()) &&
									 (EnterpriseWLNSalesServicesConstants.DISCOUNT_TYPE_AMT.equals(productCatalogueItem.getDiscountType().getDiscountTypeCode())) ) {
									com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.DiscountType newDiscountType = new com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.DiscountType();
									newDiscountType.setDiscountTypeCode(EnterpriseWLNSalesServicesConstants.DISCOUNT_TYPE_D);
									productCatalogueItem.setDiscountType(newDiscountType);
								}
							}
						}
					}
				}
			}
		}

//		List<MessageList> messageList = response.getMessageList();

		if (oisOffer == null) {
			MessageList message = new MessageList();
			message.setDateTimeStamp(new Date());
			message.setErrorCode(GSOD_OFFER_NOT_APPLICABLE);
			message.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);

			Message msg = new Message();
			msg.setLocale(EnterpriseWLNSalesServicesConstants.CANADIAN_ENGLISH_LOCALE);
			msg.setMessage("Offer is not applicable");
			message.setMessageList(Arrays.asList(msg));

			response.addMsg(message);
		}
		
		if (traces != null && !traces.isEmpty()) {
			StringBuilder trace = new StringBuilder(); 
			for (TraceVO x : traces) {
				trace.append(x.toLogMessage() + "\n");
			}

			
			// create the message
			MessageList message = new MessageList();
			message.setDateTimeStamp(new Date());
			message.setTransactionId(commonVO.getOffersRequestVO().getOperationHeader().getSalesTransactionId());
			
			message.setErrorCode("0");
			message.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_INFO);

			Message msg = new Message();
			msg.setLocale(EnterpriseWLNSalesServicesConstants.CANADIAN_ENGLISH_LOCALE);
			msg.setMessage("Offer Filter trace");
			message.setMessageList(Arrays.asList(msg));

			// set context data
			message.setContextData(trace.toString());
			response.addMsg(message);			
		}
		
		return;
	}
	
	public static List<MessageList> generateMessageList(GetSalesOfferDetailRequestVO requestVO,String errorCode,String errorMessage) {
		String salesTransactionId = "";
		if (requestVO.getOperationHeader() != null && requestVO.getOperationHeader().getSalesTransactionId() != null) {
			salesTransactionId = requestVO.getOperationHeader().getSalesTransactionId();
		}
		
		List<MessageList> messageList = new ArrayList<MessageList>();
		MessageList message = new MessageList();
		message.setDateTimeStamp(new Date());
		message.setTransactionId(salesTransactionId);
		message.setErrorCode(errorCode);
		message.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);

		Message msg = new Message();
		msg.setLocale(EnterpriseWLNSalesServicesConstants.CANADIAN_ENGLISH_LOCALE);
		msg.setMessage(errorMessage);
		message.setMessageList(Arrays.asList(msg));

		// set context data
		StringBuilder context = new StringBuilder();
		context.append("Offer Id: " + requestVO.getOfferId());
		
		if (!StringUtils.isEmpty(requestVO.getPromotionCode())) {
			context.append("; Promotion Code: " + requestVO.getPromotionCode());	
		}
		message.setContextData(context.toString());

		messageList.add(message);

		return messageList;
	}

}
