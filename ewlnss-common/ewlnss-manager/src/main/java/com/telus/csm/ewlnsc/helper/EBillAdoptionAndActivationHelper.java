package com.telus.csm.ewlnsc.helper;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.IConsumerCustomerMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.IConsumerDiaryMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.ccm.domain.InsertCustomerPreferenceAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.InsertCustomerPreferenceAdapterResponse;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateCustomerPreferenceAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CreateCustomerEventAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CreateCustomerEventAdapterResponse;
import com.telus.csm.ewlnsc.delegate.ReferencePDSDataSvcBusDelegator;
import com.telus.csm.ewlnsc.domain.GetReferencePDSResponseDO;
import com.telus.csm.ewlnsc.domain.MessageDO;
import com.telus.csm.ewlnsc.domain.MessageDescDO;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumerdiaryservicerequestresponse_v1.CustomerEvent;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumerdiaryservicerequestresponse_v1.CustomerEventParameter;
import com.telus.tmi.xmlschema.xsd.customer.customer.customersubdomain_v3.CustomerPreference;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;

/**
 * 
 * @author Jose.Mena
 *
 */
public class EBillAdoptionAndActivationHelper {
	
	private static final LoggerUtil loggerUtil = LoggerUtil.getLogger(EBillAdoptionAndActivationHelper.class);

	public void eBillAdoptionAndActivation(OperationHeader header, String billingAccountNumber, Long customerId, String eBillDeclinedReasonCd, String eBillNotificationTypeCd){
		String functionName = "EBillAdoptionAndActivationHelper.eBillAdoptionAndActivation()";
		String insertCustomerOperation = "ConsumerCustomerManagementService.insertCustomerPreference()";
		String createCustomerEventOperation = "ConsumerDiaryManagementService.createCustomerEvent()";
		loggerUtil.enter(functionName);
		//Call ConsumerCustomerManagementService.insertCustomerPreference() downstream operation
		final IConsumerCustomerMgmtSvcAdapter adapterConsumer = AdapterFactory.getAdapter(IConsumerCustomerMgmtSvcAdapter.class);
		InsertCustomerPreferenceAdapterRequest adapterConsReq = new InsertCustomerPreferenceAdapterRequest();
		InsertCustomerPreferenceAdapterResponse adapterConsRes;
		CustomerPreference custPref = new CustomerPreference();
		custPref.setCustomerId(customerId);
		
		setCustomerPreferenceValueTxt(eBillDeclinedReasonCd, eBillNotificationTypeCd, custPref);
		
		custPref.setEffectiveStartDate(new Date());
		custPref.setMasterSourceId(EnterpriseWLNSalesServicesConstants.CODS_MASTER_SOURCE_ID);
		if (!StringUtils.isEmpty(billingAccountNumber)){
			custPref.setEntityId(Long.valueOf(billingAccountNumber));
		}else{
			loggerUtil.error("", functionName, "Billing Account Number is empty (Possibly it has not been created yet)");
		}
		custPref.setEntityTypeCd(EnterpriseWLNSalesServicesConstants.EBILL_ENTITY_TYPE_CD);
		custPref.setEntityMasterSourceId(EnterpriseWLNSalesServicesConstants.ENABLER_BILLING_ACCOUNT_SYSTEM_CODE);
		
		adapterConsReq.setCustomerPreference(custPref);
		adapterConsReq.setAuditInfo(OperationHeaderUtil.getAuditInfoFromHeader(header));
		
		adapterConsRes = adapterConsumer.insertCustomerPreference(adapterConsReq);
		
		if (adapterConsRes.isMsgHasError()){
			loggerUtil.error("", functionName, insertCustomerOperation + " failed.", adapterConsRes.getAdapterResponseMessage().getException());
			//for existing customer, if the insert failed should try update.
			UpdateCustomerPreferenceAdapterRequest rqt = new UpdateCustomerPreferenceAdapterRequest();
			rqt.setAuditInfo(adapterConsReq.getAuditInfo());
			rqt.setCustomerPreference(adapterConsReq.getCustomerPreference());
			adapterConsRes = adapterConsumer.updateCustomerPreference(rqt);
			if (adapterConsRes.isMsgHasError()) {
				loggerUtil.error("", functionName, "updateCustomerPreference failed.", adapterConsRes.getAdapterResponseMessage().getException());
			}
			
		}else{
			loggerUtil.info(functionName, insertCustomerOperation + " executed successfully.");
		}
		// 	Olivia: No, don't need to write to diary if the preference insert fails.
		if (!adapterConsRes.isMsgHasError()){
			//call ConsumerDiaryManagementService.createCustomerEvent()
			IConsumerDiaryMgmtSvcAdapter adapterDiary = AdapterFactory.getAdapter(IConsumerDiaryMgmtSvcAdapter.class);
			CreateCustomerEventAdapterRequest diaryReq = new CreateCustomerEventAdapterRequest();
			CreateCustomerEventAdapterResponse diaryResp;
			
			CustomerEvent customerEvent = new CustomerEvent();
			customerEvent.setCustomerId(customerId);
			customerEvent.setReferenceEntityId(billingAccountNumber);
			customerEvent.setCategoryCode(EnterpriseWLNSalesServicesConstants.EVENT_TYPE_CATEGORY_CD);
			customerEvent.setReferenceEntityTypeCode(EnterpriseWLNSalesServicesConstants.EVENT_TYPE_REF_ENT_TYPE_CD);
			customerEvent.setReferenceEntitySourceId(EnterpriseWLNSalesServicesConstants.EVENT_TYPE_REF_ENT_SOURCE_ID);
			customerEvent.setRetentionCode(EnterpriseWLNSalesServicesConstants.EVENT_TYPE_RETENTION_CD);
			customerEvent.setSourceId(EnterpriseWLNSalesServicesConstants.EVENT_TYPE_SOURCE_ID);
			customerEvent.setUserId(EnterpriseWLNSalesServicesConstants.EVENT_TYPE_USER_ID);
			customerEvent.setEventDate(new Date());
			customerEvent.setHighlightedInd(EnterpriseWLNSalesServicesConstants.EVENT_TYPE_HIGHLIGHTED_IND);
			customerEvent.setSystemGeneratedInd(EnterpriseWLNSalesServicesConstants.EVENT_TYPE_SYSTEM_GEN_IND);
			
			long eventTypeId = getEventTypeId(eBillDeclinedReasonCd, eBillNotificationTypeCd);
			
			customerEvent.setEventTypeId(eventTypeId);
			
			if (!StringUtils.isEmpty(eBillDeclinedReasonCd)){
				String declineReasonCd = eBillDeclinedReasonCd;
				String declinedReasonCdValue = getDeclinedReasonCdMap(declineReasonCd);
				CustomerEventParameter customerEventParameter = new CustomerEventParameter();
				customerEventParameter.setCustomerEventParameterName(EnterpriseWLNSalesServicesConstants.EVENT_TYPE_PARAMETER_NAME);
				customerEventParameter.setCustomerEventParameterValue(declinedReasonCdValue);
				customerEvent.setParameterList(Arrays.asList(customerEventParameter));
			}
			
			diaryReq.setCustomerEvent(customerEvent);
			diaryResp = adapterDiary.createCustomerEvent(diaryReq);
			
			if (diaryResp.isMsgHasError()){
				loggerUtil.error("", functionName, createCustomerEventOperation + " failed.", diaryResp.getAdapterResponseMessage().getException());
			}else{
				loggerUtil.info(functionName, createCustomerEventOperation + " executed successfully.");
			}
			
		}
		
		loggerUtil.exit(functionName);
	}
	
	private void setCustomerPreferenceValueTxt(String ebillDeclinedReasonCd, String eBillNotificationTypeCd, CustomerPreference custPref) {
		if (!StringUtils.isEmpty(ebillDeclinedReasonCd)){
			custPref.setCustomerPreferenceTypeCd(EnterpriseWLNSalesServicesConstants.EBILL_DECLINE_RSN);
			custPref.setCustomerPreferenceValueTxt(ebillDeclinedReasonCd);
		}else{
			custPref.setCustomerPreferenceTypeCd(EnterpriseWLNSalesServicesConstants.EBILL_NOTIF_MTHD);
			custPref.setCustomerPreferenceValueTxt(eBillNotificationTypeCd);
		}
	}
	
	private long getEventTypeId(String eBillDeclinedReasonCd, String eBillNotificationTypeCd) {
		//determine eventTypeId
		long eventTypeId = 0;
		if (!StringUtils.isEmpty(eBillDeclinedReasonCd)){
			//DECLINED EBILL
			eventTypeId = EnterpriseWLNSalesServicesConstants.EVENT_TYPE_ID_DECLINE_EBILL;
		}else{
			if (EnterpriseWLNSalesServicesConstants.EBILL_NOTIFICATION_TYPE_EMAIL.equalsIgnoreCase(eBillNotificationTypeCd)){
				eventTypeId = EnterpriseWLNSalesServicesConstants.EVENT_TYPE_ID_EMAIL_EBILL;
			}else if (EnterpriseWLNSalesServicesConstants.EBILL_NOTIFICATION_TYPE_EMAIL_SMS.equalsIgnoreCase(eBillNotificationTypeCd)){
				eventTypeId = EnterpriseWLNSalesServicesConstants.EVENT_TYPE_ID_EMAIL_SMS_EBILL;
			}else if (EnterpriseWLNSalesServicesConstants.EBILL_NOTIFICATION_TYPE_SMS.equalsIgnoreCase(eBillNotificationTypeCd)){
				eventTypeId = EnterpriseWLNSalesServicesConstants.EVENT_TYPE_ID_SMS_EBILL;
			}
		}
		return eventTypeId;
	}
	
	private String getDeclinedReasonCdMap(String declineReasonCd) {
		String reasonCdValue = "";

		ReferencePDSDataSvcBusDelegator refPdsBusDelegate = ReferencePDSDataSvcBusDelegator.getInstance();

		GetReferencePDSResponseDO response = refPdsBusDelegate.getReferencePDSTableObjectByName(EnterpriseWLNSalesServicesConstants.REFPDS_DECLINED_REASON_TABLE);

		if (response.isMsgHasError()) {
			for (MessageDO message : response.getMsgList()) {
				for (MessageDescDO messageDescription : message.getMesssageDescriptionTextList()) {
					loggerUtil.warn("getDeclinedReasonCdMap", messageDescription.getMessageDescriptionText());
				}
			}
		}
		else {
			Map<String, Object> objResult = response.getRefpdsTable();
			
			Map<String, String> declinedReasonCdMap = (Map<String, String>) objResult.get(EnterpriseWLNSalesServicesConstants.REFPDS_DECLINED_REASON_TABLE);

			if (declinedReasonCdMap == null || declinedReasonCdMap.isEmpty()) {
				loggerUtil.debug("getDeclinedReasonCdMap", "Declined Reason Code " + declineReasonCd + " was NOT found in RefPDS.");
			}
			else if (declinedReasonCdMap.containsKey(declineReasonCd)) {
				reasonCdValue = declinedReasonCdMap.get(declineReasonCd);

				loggerUtil.debug("getDeclinedReasonCdMap", "Declined Reason Code was found in RefPDS: " + reasonCdValue);
			}
		}

		return reasonCdValue;
	}
	
}
