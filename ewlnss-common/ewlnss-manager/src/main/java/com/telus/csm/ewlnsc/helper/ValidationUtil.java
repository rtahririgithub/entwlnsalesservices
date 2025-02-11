package com.telus.csm.ewlnsc.helper;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.*;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.NEWLINE;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.telus.channelsalesmgmt.common.util.LoggerUtil;
import com.telus.csm.ewlnsc.delegate.ReferencePDSDataSvcBusDelegator;
import com.telus.csm.ewlnsc.domain.GetReferencePDSResponseDO;
import com.telus.csm.ewlnsc.domain.MessageDO;
import com.telus.csm.ewlnsc.domain.MessageDescDO;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.ValidateFieldsUtil;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;

/**
 * 
 * @author Jose.Mena
 *
 */
public class ValidationUtil {
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(ValidationUtil.class);
	
	private ValidationUtil(){
		
	}

	public static void validateHeader(OperationHeader header, List<String> missingList, List<String> invalidInputList){
		LoggerUtil loggerUtil = LoggerUtil.getLogger(ValidationUtil.class);
		StringBuilder sbMissing = new StringBuilder();
		StringBuilder sbInvalid = new StringBuilder();
		String functionName = "validateHeader()";
		// validate Header
		if (header == null) {
			sbMissing.append(MISSING_OP_HEADER).append(NEWLINE);
			missingList.add(MISSING_OP_HEADER);
		}else{
			// validate App ID
			if (header.getOriginatorApplicationId() == null) {
				sbMissing.append(MISSING_APP_ID).append(NEWLINE);
				missingList.add(MISSING_APP_ID);
			}

			if (header.getUserProfile() == null && header.getAgentProfile() == null) {
				sbMissing.append(MISSING_OP_HEADER_USER_PROFILE_AND_AGENT_PROFILE).append(NEWLINE);
				missingList.add(MISSING_OP_HEADER_USER_PROFILE_AND_AGENT_PROFILE);
			}
			else {
				if (header.getUserProfile() != null){
					validateUserProfile(header, missingList, invalidInputList, sbMissing, sbInvalid);
				}

				if (header.getAgentProfile() != null){
					validateAgentProfile(header, missingList, invalidInputList, sbMissing, sbInvalid);
				}				
			}

			// validate Transaction Id
			if (StringUtils.isBlank(header.getSalesTransactionId())) {
				sbMissing.append(MISSING_SALES_TRANSAC_ID).append(NEWLINE);
				missingList.add(MISSING_SALES_TRANSAC_ID);
			}
			
			// brandCode 
			if (StringUtils.isEmpty(header.getBrandCode())) {
				sbMissing.append(MISSING_BRAND_CODE_STR).append(NEWLINE);
				missingList.add(MISSING_BRAND_CODE_STR);
			}
			if (!BRAND_CODE_TELUS.equalsIgnoreCase(header.getBrandCode()) && !BRAND_CODE_KOODO.equalsIgnoreCase(header.getBrandCode())) {
				sbInvalid.append(INVALID_BRAND_CODE_STR).append(NEWLINE);
				invalidInputList.add(INVALID_BRAND_CODE_STR);
			}
		}
		
		if (sbMissing.length() > 0){
			loggerUtil.error("", functionName, "Missing Element(s): " + sbMissing.toString());
		}
		if (sbInvalid.length() > 0){
			loggerUtil.error("", functionName, "Invalid Element(s): " + sbInvalid.toString());
		}
	}

	private static void validateUserProfile(OperationHeader header, List<String> missingList,
			List<String> invalidInputList, StringBuilder sbMissing, StringBuilder sbInvalid) {
		// validate Sales Rep Id
		if (header.getUserProfile().getSalesRepInternalId() == 0) {
			sbMissing.append(MISSING_SALES_REP_INTERNAL_ID).append(NEWLINE);
			missingList.add(MISSING_SALES_REP_INTERNAL_ID);
		} else if (header.getUserProfile().getSalesRepInternalId() < 0){
			sbInvalid.append(MISSING_SALES_REP_INTERNAL_ID).append(NEWLINE);
			invalidInputList.add(MISSING_SALES_REP_INTERNAL_ID);
		}
		// validate Channel Org Id
		if (header.getUserProfile().getChnlOrgInternalId() == 0) {
			sbMissing.append(MISSING_CHANNEL_ORG_ID).append(NEWLINE);
			missingList.add(MISSING_CHANNEL_ORG_ID);
		} else if(header.getUserProfile().getChnlOrgInternalId() < 0){
			sbInvalid.append(MISSING_CHANNEL_ORG_ID).append(NEWLINE);
			invalidInputList.add(MISSING_CHANNEL_ORG_ID);
		}
		// validate getSalesRepAssociatedOutletInternalId
		if (header.getUserProfile().getSalesRepAssociatedOutletList().isEmpty()
				|| header.getUserProfile().getSalesRepAssociatedOutletList().get(0).getSalesRepAssociatedOutletInternalId() == 0) {
			sbMissing.append(MISSING_CHANNEL_OUTLET_ID).append(NEWLINE);
			missingList.add(MISSING_CHANNEL_OUTLET_ID);
		} else if (!header.getUserProfile().getSalesRepAssociatedOutletList().isEmpty()
				&& header.getUserProfile().getSalesRepAssociatedOutletList().get(0).getSalesRepAssociatedOutletInternalId() < 0){
			sbInvalid.append(MISSING_CHANNEL_OUTLET_ID).append(NEWLINE);
			invalidInputList.add(MISSING_CHANNEL_OUTLET_ID);
		}
		
		//validate request.userProfile.outletAssociatedProvinces is valid if it is passed in the request
		if (header.getUserProfile().getOutletAssociatedProvinces() != null && !header.getUserProfile().getOutletAssociatedProvinces().isEmpty()) {
			for (String province: header.getUserProfile().getOutletAssociatedProvinces()) {
				if (!StringUtils.isEmpty(province)) {
					boolean isValidProvince = validateProvinceCode(province);
					if (!isValidProvince) {
						invalidInputList.add(INVALID_OUTLET_ASSOCIATED_PROVINCE);
					}
				}
			}
		}
	}

	public static boolean validateProvinceCode(String provinceCode) {
		if (StringUtils.isEmpty(provinceCode)) {
			return false;
		}
		else {
			String newProvinceStateCode = ValidateFieldsUtil.convertOldProvinceCodeToNew(provinceCode);

			ReferencePDSDataSvcBusDelegator refPdsBusDelegate = ReferencePDSDataSvcBusDelegator.getInstance();

			GetReferencePDSResponseDO response = refPdsBusDelegate.getReferencePDSTableObjectByName(EnterpriseWLNSalesServicesConstants.REFPDS_PROVINCE_TABLE);

			if (response.isMsgHasError()) {
				for (MessageDO message : response.getMsgList()) {
					for (MessageDescDO messageDescription : message.getMesssageDescriptionTextList()) {
						logger.warn("validateProvinceCode", messageDescription.getMessageDescriptionText());
					}
				}

				return true;
			}
			else {
				Map<String, Object> objResult = response.getRefpdsTable();
				
				Map<String, String> provinceStateCdMap = (Map<String, String>) objResult.get(EnterpriseWLNSalesServicesConstants.REFPDS_PROVINCE_TABLE);

				if (provinceStateCdMap == null || provinceStateCdMap.isEmpty()) {
					logger.debug("validateProvinceCode", "Province Code was NOT found in RefPDS.");

					return true;
				}
				else if (provinceStateCdMap.containsKey(newProvinceStateCode)) {
					logger.debug("validateProvinceCode", "Province Code was found in RefPDS.");

					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 
	 * @param header
	 * @param missingElementList
	 * @param invalidInputList
	 * @param warningList
	 * @param missingElementErrorCode
	 * @param invalidElementErrorCode
	 * @return
	 */
	public static List<MessageList> generateMessageList(OperationHeader header, List<String> missingElementList,
			List<String> invalidInputList, List<String> warningList, String missingElementErrorCode, String invalidElementErrorCode) {
		
		List<MessageList> messageList = new ArrayList<MessageList>();
		StringBuilder sbMissing = new StringBuilder();
		StringBuilder sbInvalid = new StringBuilder();
		StringBuilder sbWarning = new StringBuilder();
		String salesTransactionId = "";
		// iterate over missing element list
		for (String msg : missingElementList) {
			sbMissing.append(msg).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
		}
		// iterate over invalid input
		for (String msg : invalidInputList) {
			sbInvalid.append(msg).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
		}
		// iterate over warning list
		for (String msg : warningList) {
			sbWarning.append(msg).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
		}
		if (header != null && header.getSalesTransactionId() != null) {
			salesTransactionId = header.getSalesTransactionId();
		}
		// create the message
		if (!missingElementList.isEmpty()) {
			MessageList messageMissing = new MessageList();
			messageMissing.setDateTimeStamp(new Date());
			messageMissing.setTransactionId(salesTransactionId);
	
			messageMissing.setErrorCode(missingElementErrorCode);
			messageMissing.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
	
			Message msg = new Message();
			msg.setLocale(EnterpriseWLNSalesServicesConstants.CANADIAN_ENGLISH_LOCALE);
			msg.setMessage(EnterpriseWLNSalesServicesConstants.MANDATORY_INPUT_MESSAGE_TEXT);
			messageMissing.setMessageList(Arrays.asList(msg));
	
			// set context data
			messageMissing.setContextData(sbMissing.toString());
	
			messageList.add(messageMissing);
		}
		if (!invalidInputList.isEmpty()) {
			MessageList messageInvalid = new MessageList();
			messageInvalid.setDateTimeStamp(new Date());
			messageInvalid.setTransactionId(salesTransactionId);
	
			messageInvalid.setErrorCode(invalidElementErrorCode);
			messageInvalid.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
	
			Message msg = new Message();
			msg.setLocale(EnterpriseWLNSalesServicesConstants.CANADIAN_ENGLISH_LOCALE);
			msg.setMessage(EnterpriseWLNSalesServicesConstants.INVALID_INPUT_MESSAGE_TEXT);
			messageInvalid.setMessageList(Arrays.asList(msg));
	
			// set context data
			messageInvalid.setContextData(sbInvalid.toString());
	
			messageList.add(messageInvalid);
		}
		if (!warningList.isEmpty()) {
			MessageList messageWarning = new MessageList();
			messageWarning.setDateTimeStamp(new Date());
			messageWarning.setTransactionId(salesTransactionId);
	
			messageWarning.setErrorCode(invalidElementErrorCode);
			messageWarning.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_WARNING);
	
			Message msg = new Message();
			msg.setLocale(EnterpriseWLNSalesServicesConstants.CANADIAN_ENGLISH_LOCALE);
			msg.setMessage(EnterpriseWLNSalesServicesConstants.INVALID_INPUT_MESSAGE_TEXT);
			messageWarning.setMessageList(Arrays.asList(msg));
	
			// set context data
			messageWarning.setContextData(sbWarning.toString());
	
			messageList.add(messageWarning);
		}
	
		return messageList;
	}

	private static void validateAgentProfile(OperationHeader header, List<String> missingList,
			List<String> invalidInputList, StringBuilder sbMissing, StringBuilder sbInvalid) {
		// validate Channel Org Internal Id
		if ( (header.getAgentProfile().getChannelOrganizationInternalId() == null) ||
			 (header.getAgentProfile().getChannelOrganizationInternalId().isEmpty()) ) {
			sbMissing.append(MISSING_AGENT_PROFILE_CHANNEL_ORG_ID).append(NEWLINE);
			missingList.add(MISSING_AGENT_PROFILE_CHANNEL_ORG_ID);
		}
		else {
			try {
				if (Integer.valueOf(header.getAgentProfile().getChannelOrganizationInternalId()) < 0) {
					sbInvalid.append(MISSING_AGENT_PROFILE_CHANNEL_ORG_ID).append(NEWLINE);
					invalidInputList.add(MISSING_AGENT_PROFILE_CHANNEL_ORG_ID);
				}
			}
			catch (NumberFormatException e) {
				sbInvalid.append(MISSING_AGENT_PROFILE_CHANNEL_ORG_ID).append(NEWLINE);
				invalidInputList.add(MISSING_AGENT_PROFILE_CHANNEL_ORG_ID);
			}			
		}

		// validate Channel Org Type Code
		if ( (header.getAgentProfile().getChannelOrganizationTypeCd() == null) ||
			 (header.getAgentProfile().getChannelOrganizationTypeCd().isEmpty()) ) {
			sbMissing.append(MISSING_AGENT_PROFILE_CHANNEL_ORG_TYPE_CODE).append(NEWLINE);
			missingList.add(MISSING_AGENT_PROFILE_CHANNEL_ORG_TYPE_CODE);
		}
	}

	public static void validateHeaderAgentProfileUserPrivilegeRoleCodeList(OperationHeader header, List<String> missingList) {
		//validate request.agentProfile.userPrivilegeRoleCodeList
		if ( (header.getAgentProfile().getUserPrivilegeRoleCodeList() == null) ||
			 (header.getAgentProfile().getUserPrivilegeRoleCodeList().isEmpty()) ||
			 (header.getAgentProfile().getUserPrivilegeRoleCodeList().get(0) == null) ) {
			missingList.add(MISSING_AGENT_PROFILE_USER_PRIVILEGE_ROLE_CODE_LIST);
		}
	}
}
