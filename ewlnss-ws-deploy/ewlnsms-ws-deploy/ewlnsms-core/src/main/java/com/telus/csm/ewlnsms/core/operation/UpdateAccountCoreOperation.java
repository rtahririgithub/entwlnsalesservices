package com.telus.csm.ewlnsms.core.operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.adapter.IConsumerBillingAccountManagementServiceAdapter;
import com.telus.csm.ewlnsc.adapter.IConsumerCustomerMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.ccm.domain.GetFullCustomerInfoAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.GetFullCustomerInfoAdapterResponse;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateContactIndividualAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateContactIndividualAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.RegisterBillDeliveryMethodAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.RegisterBillDeliveryMethodAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.UpdateBillDeliveryAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.UpdateBillDeliveryAdapterResponse;
import com.telus.csm.ewlnsc.helper.AdapterResponseUtil;
import com.telus.csm.ewlnsc.helper.ValidationUtil;
import com.telus.csm.ewlnsc.task.EBillAdoptionAndActivationTask;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.workmanager.ICommonJWorkManager;
import com.telus.csm.ewlnsc.util.workmanager.WorkManagerFactory;
import com.telus.csm.ewlnsms.core.domain.UpdateAccountCoreRequest;
import com.telus.csm.ewlnsms.core.domain.UpdateAccountCoreResponse;
import com.telus.csm.ewlnsms.core.transformer.UpdateAccountTransformer;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.tmi.xmlschema.xsd.customer.customer.customersubdomain_v3.ContactIndividual;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.BillingAccountType;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.EbillNotificationType;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;

import commonj.work.Work;

public class UpdateAccountCoreOperation {

	protected static final Logger LOGGER = Logger.getLogger(UpdateAccountCoreOperation.class);
	protected static final LoggerUtil loggerUtil = LoggerUtil.getLogger(UpdateAccountCoreOperation.class);
	protected static final String JWORK_MANAGER_EXCEPTION_MSG = "ICommonJWorkManager caught Exception.";
	private UpdateAccountTransformer transformer = new UpdateAccountTransformer();
	private static final String WLN_ACCOUNT_SOURCE_CODE = "1001";

	protected String transId;
	protected OperationHeader header;

	/**
	 * Entry point is this operation class.
	 * 
	 * @param requestVO
	 * @return
	 * @throws Throwable
	 */
	public UpdateAccountCoreResponse execute(final UpdateAccountCoreRequest requestVO) {
		UpdateAccountCoreResponse result = new UpdateAccountCoreResponse();

		header = requestVO.getOperationHeader();
		if (header != null) {
			transId = header.getSalesTransactionId();
		}

		// validate Input
		List<String> missingElementList = new ArrayList<String>();
		List<String> invalidInputList = new ArrayList<String>();

		validateInput(requestVO, missingElementList, invalidInputList);

		if (!missingElementList.isEmpty() || !invalidInputList.isEmpty()) {
			loggerUtil.error("", "", "Input Validation failed.");
			result.setMsgHasError(true);
			result.setMessageList(generateMessageList(requestVO, missingElementList, invalidInputList));
			return result;
		}

		result = updateAccount(requestVO);

		return result;
	}

	private UpdateAccountCoreResponse updateAccount(final UpdateAccountCoreRequest requestVO) {
		UpdateAccountCoreResponse result = new UpdateAccountCoreResponse();

		String accountNumber = requestVO.getBillingAccount().getBillingAccountNumber();
		String masterSourceCode = requestVO.getBillingAccount().getMasterAccountSourceTypeCd();
		long custIdLong = Long.parseLong(requestVO.getCustomer().getCustomerId());
		AdapterResponseBase errorBase = null;
		String email = null;
		String phoneNumber = null;
		boolean hasError = false;
		boolean isPaperToEBill = false;

		LOGGER.info("execute delegator to updateAccount for customer id=" + custIdLong + " BAN=" + accountNumber);

		// Update WLN account only
		if (WLN_ACCOUNT_SOURCE_CODE.equalsIgnoreCase(masterSourceCode)) {
			// Get full customer info
			final GetFullCustomerInfoAdapterResponse fullCustResp = getFullCustomerInfo(
					new GetFullCustomerInfoAdapterRequest(custIdLong,
							requestVO.getOperationHeader().getSalesTransactionId()));

			if (fullCustResp.isMsgHasError()) {
				LOGGER.error("updateAccount().... getFullCustomerInfo - failed");
				errorBase = fullCustResp;
				hasError = true;
			}
			
			// can't find customer
			if (fullCustResp.getFullCustomer() == null) {
				LOGGER.error("updateAccount().... Can't find Customer - " + custIdLong);
				result.setMsgHasError(true);
				result.setMessageList(generateMessageList(requestVO, "Can't find Customer - " + custIdLong));
				return result;
			}

			if (!hasError) {
				// is paper bill?
				isPaperToEBill = transformer.isPaperToEBill(requestVO.getBillingAccount(),
						fullCustResp.getFullCustomer().getBillingAccountList());
				LOGGER.info("updateAccount().... Paper to EBill? " + isPaperToEBill);
			}

			if (!hasError && isPaperToEBill) {

				// Get contactIndividualList from full customer
				List<ContactIndividual> contactIndividualList = fullCustResp.getFullCustomer()
						.getContactIndividualList();

				// Update contact (eMail and Phone) in Contact Individual
				contactIndividualList = transformer.updateContactIndividual(contactIndividualList,
						requestVO.getBillNotification());

				if (contactIndividualList != null) {
					// Call update Contact Individual Adapter
					UpdateContactIndividualAdapterResponse updateContactResp = updateContactIndividual(
							contactIndividualList);

					if (updateContactResp.isMsgHasError()) {
						LOGGER.error("updateAccount().... updateContactIndividual - failed");
						errorBase = updateContactResp;
						hasError = true;
					}
				} else {
					LOGGER.info("updateAccount().... No email or phone change, load contact from fullCustomerInfo");
					contactIndividualList = fullCustResp.getFullCustomer().getContactIndividualList();
				}

				// get email from contact
				email = transformer.getPrimaryEmail(contactIndividualList);
				phoneNumber = transformer.getPrimaryPhoneNumber(contactIndividualList);
				LOGGER.info("updateAccount().... contact email: " + email);
			}
		}

		if (!hasError && isPaperToEBill) {
			// Register Bill Delivery Method
			//RegisterBillDeliveryMethodAdapterResponse registerResp = registerBillDeliveryMethod(requestVO.getBillingAccount(), email);

			//NWLN-10684
			UpdateBillDeliveryAdapterResponse updateBillDeliveryResp = updateBillDelivery(requestVO.getBillingAccount(), requestVO.getBillNotification(), email, phoneNumber);
			
			if (updateBillDeliveryResp.isMsgHasError()) {
				LOGGER.error("updateAccount().... updateBillDelivery - failed");
				errorBase = updateBillDeliveryResp;
				hasError = true;
			}
		}
		//NWLN-10684 - remove CCMS call, using new updateBillDelivery instead
		//if (!hasError && isPaperToEBill) {
			// EBill options provided, checked in isPaperToEBill
			// Insert Customer Preference
			//if (!StringUtils.isEmpty(accountNumber)) {
				// call eBill Notification logic
				// eBillAdoptionAndActivation(requestVO, result, custIdLong)
				//LOGGER.info("updateAccount().... eBillAdoptionAndActivationTask start ");
				//eBillAdoptionAndActivationTask(accountNumber, custIdLong, null,requestVO.getBillNotification().getNotificationMethodCd());
			//}
		//}

		if (hasError) {
			AdapterResponseUtil.propagateMessage(errorBase, result.getMessageList());
			changePolicyErrorCode(result, errorBase);
		} else if (!isPaperToEBill) {
			result.setMsgHasError(true);
			result.setMessageList(generateMessageList(requestVO, accountNumber + " is already subscribed to E.Bill"));
		} else {
			result.setSucessfulUpdateInd(true);
		}

		result.setTransactionId(transId);

		return result;
	}

	private GetFullCustomerInfoAdapterResponse getFullCustomerInfo(final GetFullCustomerInfoAdapterRequest requestVO) {
		LOGGER.info("getFullCustomerInfo called");

		final IConsumerCustomerMgmtSvcAdapter adapter = AdapterFactory
				.getAdapter(IConsumerCustomerMgmtSvcAdapter.class);

		return adapter.getFullCustomerInfo(requestVO);

	}

	private UpdateContactIndividualAdapterResponse updateContactIndividual(final List<ContactIndividual> contactList) {
		LOGGER.info("updateContactIndividual called.");
		UpdateContactIndividualAdapterResponse result = new UpdateContactIndividualAdapterResponse();

		final IConsumerCustomerMgmtSvcAdapter adapter = AdapterFactory
				.getAdapter(IConsumerCustomerMgmtSvcAdapter.class);

		for (ContactIndividual contactIndividual : contactList) {

			UpdateContactIndividualAdapterRequest adapterReq = new UpdateContactIndividualAdapterRequest();
			adapterReq.setSalesTransactionId(transId);
			adapterReq.setCustomerContact(contactIndividual);
			adapterReq.setAuditInfo(transformer.getAuditInfoFromHeader(header));

			result = adapter.updateContactIndividual(adapterReq);

			if (result.isMsgHasError())
				return result;
		}
		return result;
	}

	private RegisterBillDeliveryMethodAdapterResponse registerBillDeliveryMethod(BillingAccountType billingAccountType,
			String email) {
		LOGGER.info("registerBillDeliveryMethod called.");
		final IConsumerBillingAccountManagementServiceAdapter adapter = AdapterFactory
				.getAdapter(IConsumerBillingAccountManagementServiceAdapter.class);

		RegisterBillDeliveryMethodAdapterRequest requestDO = transformer
				.createRegisterBillDeliveryMethodAdapterRequest(transId, billingAccountType, email, header);

		return adapter.registerBillDeliveryMethod(requestDO);
	}
	
	private UpdateBillDeliveryAdapterResponse updateBillDelivery(BillingAccountType billingAccountType, EbillNotificationType billNotification, String email, String phoneNumber) {
		LOGGER.info("updateBillDelivery called.");
		final IConsumerBillingAccountManagementServiceAdapter adapter = AdapterFactory
				.getAdapter(IConsumerBillingAccountManagementServiceAdapter.class);

		UpdateBillDeliveryAdapterRequest requestDO = transformer
				.createUpdateBillDeliveryAdapterRequest(transId, billingAccountType, billNotification, email, phoneNumber, header);

		return adapter.updateBillDelivery(requestDO);
	}

	private void changePolicyErrorCode(UpdateAccountCoreResponse result, final AdapterResponseBase responseDO) {
		// check if policy/service or general exception
		if (!EnterpriseWLNSalesServicesErrorCodes.UNKNOWN_SERVICE_ERROR
				.equals(responseDO.getAdapterResponseMessage().getMessageCode())) {
			result.getMessageList().get(0)
					.setErrorCode(EnterpriseWLNSalesServicesErrorCodes.UPDATE_ACCOUNT_DOWN_STREAM_ERROR);
		}
	}

	private void eBillAdoptionAndActivationTask(String billingAccountNumber, Long customerId,
			String eBillDeclinedReasonCd, String eBillNotificationTypeCd) {
		String functionName = "eBillAdoptionAndActivationTask()";
		List<Work> tasksList = new ArrayList<Work>();
		tasksList.add(new EBillAdoptionAndActivationTask(header, billingAccountNumber, customerId,
				eBillDeclinedReasonCd, eBillNotificationTypeCd));
		if (!tasksList.isEmpty()) {
			try {
				ICommonJWorkManager commonJMgr = WorkManagerFactory.getCommonJWorkManager();
				commonJMgr.processAsyncTasks(tasksList);
			} catch (NamingException e) {
				loggerUtil.error("", functionName,
						JWORK_MANAGER_EXCEPTION_MSG + EnterpriseWLNSalesServicesConstants.SPACE + e);
			}
		}
	}

	private void validateInput(UpdateAccountCoreRequest coreRequest, List<String> mandatoryList,
			List<String> invalidInputList) {
		StringBuilder sbMandatory = new StringBuilder();
		// validate Header
		ValidationUtil.validateHeader(coreRequest.getOperationHeader(), mandatoryList, invalidInputList);

		// validate Billing Account
		if (coreRequest.getBillingAccount() == null) {
			sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.UPDATE_ACCOUNT_MISSING_BILLING_ACCOUNT)
					.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.UPDATE_ACCOUNT_MISSING_BILLING_ACCOUNT);
		} else if (StringUtils.isEmpty(coreRequest.getBillingAccount().getBillingAccountNumber())) {
			// validate Billing Account Number
			sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.UPDATE_ACCOUNT_MISSING_BILLING_ACCOUNT_NUM)
					.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.UPDATE_ACCOUNT_MISSING_BILLING_ACCOUNT_NUM);
		}

		// validate Customer Info
		if (coreRequest.getCustomer() == null) {
			sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.UPDATE_ACCOUNT_MISSING_CUSTOMER)
					.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.UPDATE_ACCOUNT_MISSING_CUSTOMER);
		} else if (StringUtils.isEmpty(coreRequest.getCustomer().getCustomerId())) {
			// validate Customer ID
			sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.UPDATE_ACCOUNT_MISSING_CUSTOMER_ID)
					.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.UPDATE_ACCOUNT_MISSING_CUSTOMER_ID);
		}

		// validate Bill Notification
		if (coreRequest.getBillNotification() == null) {
			sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.UPDATE_ACCOUNT_MISSING_BILL_NOTIFY)
					.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.UPDATE_ACCOUNT_MISSING_BILL_NOTIFY);
		} else if (StringUtils.isEmpty(coreRequest.getBillNotification().getNotificationMethodCd())) {
			// validate notification method code
			sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.UPDATE_ACCOUNT_MISSING_BILL_NOTIFY_CODE)
					.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.UPDATE_ACCOUNT_MISSING_BILL_NOTIFY_CODE);
		} else if ((EnterpriseWLNSalesServicesConstants.EBILL_NOTIFICATION_TYPE_SMS
				.equalsIgnoreCase(coreRequest.getBillNotification().getNotificationMethodCd())
				|| EnterpriseWLNSalesServicesConstants.EBILL_NOTIFICATION_TYPE_EMAIL_SMS
						.equalsIgnoreCase(coreRequest.getBillNotification().getNotificationMethodCd()))
				&& StringUtils.isEmpty(coreRequest.getBillNotification().getNotificationSmsNum())) {
			// validate sms number
			sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.UPDATE_ACCOUNT_MISSING_BILL_NOTIFY_SMS)
					.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.UPDATE_ACCOUNT_MISSING_BILL_NOTIFY_SMS);
		} 
		//email field is not mandatory in HS3 - also remove the validation logic in ESS
//		else if ((EnterpriseWLNSalesServicesConstants.EBILL_NOTIFICATION_TYPE_EMAIL
//				.equalsIgnoreCase(coreRequest.getBillNotification().getNotificationMethodCd())
//				|| EnterpriseWLNSalesServicesConstants.EBILL_NOTIFICATION_TYPE_EMAIL_SMS
//						.equalsIgnoreCase(coreRequest.getBillNotification().getNotificationMethodCd()))
//				&& StringUtils.isEmpty(coreRequest.getBillNotification().getNotificationEmailAddressTxt())) {
//			// validate email
//			sbMandatory.append(EnterpriseWLNSalesServicesErrorCodes.UPDATE_ACCOUNT_MISSING_BILL_NOTIFY_EMAIL)
//					.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
//			mandatoryList.add(EnterpriseWLNSalesServicesErrorCodes.UPDATE_ACCOUNT_MISSING_BILL_NOTIFY_EMAIL);
//		}

		if (sbMandatory.length() > 0) {
			LOGGER.error("Missing Element(s): " + sbMandatory.toString());
		}
	}

	private List<MessageList> generateMessageList(UpdateAccountCoreRequest requestVO, List<String> missingElementList,
			List<String> invalidInputList) {
		List<MessageList> messageList = new ArrayList<MessageList>();
		StringBuilder sbMissing = new StringBuilder();
		StringBuilder sbInvalid = new StringBuilder();
		String salesTransactionId = "";
		sbMissing.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
		sbInvalid.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
		// iterate over missing element list
		for (String msg : missingElementList) {
			sbMissing.append(msg).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
		}
		// iterate over invalid input
		for (String msg : invalidInputList) {
			sbInvalid.append(msg).append(EnterpriseWLNSalesServicesConstants.NEWLINE);
		}
		if (requestVO.getOperationHeader() != null && requestVO.getOperationHeader().getSalesTransactionId() != null) {
			salesTransactionId = requestVO.getOperationHeader().getSalesTransactionId();
		}
		// create the message
		if (!missingElementList.isEmpty()) {
			MessageList messageMissing = new MessageList();
			messageMissing.setDateTimeStamp(new Date());
			messageMissing.setTransactionId(salesTransactionId);

			messageMissing.setErrorCode(EnterpriseWLNSalesServicesErrorCodes.UPDATE_ACCOUNT_MISSING_MANDATORY_ELEMENTS);
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

			messageInvalid.setErrorCode(EnterpriseWLNSalesServicesErrorCodes.UPDATE_ACCOUNT_INVALID_INPUT);
			messageInvalid.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);

			Message msg = new Message();
			msg.setLocale(EnterpriseWLNSalesServicesConstants.CANADIAN_ENGLISH_LOCALE);
			msg.setMessage(EnterpriseWLNSalesServicesConstants.INVALID_INPUT_MESSAGE_TEXT);
			messageInvalid.setMessageList(Arrays.asList(msg));

			// set context data
			messageInvalid.setContextData(sbInvalid.toString());

			messageList.add(messageInvalid);
		}

		return messageList;
	}

	private List<MessageList> generateMessageList(UpdateAccountCoreRequest requestVO, String error) {
		List<MessageList> messageList = new ArrayList<MessageList>();

		String salesTransactionId = "";

		if (requestVO.getOperationHeader() != null && requestVO.getOperationHeader().getSalesTransactionId() != null) {
			salesTransactionId = requestVO.getOperationHeader().getSalesTransactionId();
		}
		// create the message
		MessageList messageMissing = new MessageList();
		messageMissing.setDateTimeStamp(new Date());
		messageMissing.setTransactionId(salesTransactionId);

		messageMissing.setErrorCode(EnterpriseWLNSalesServicesErrorCodes.UPDATE_ACCOUNT_INVALID_INPUT);
		messageMissing.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);

		Message msg = new Message();
		msg.setLocale(EnterpriseWLNSalesServicesConstants.CANADIAN_ENGLISH_LOCALE);
		msg.setMessage(error);
		messageMissing.setMessageList(Arrays.asList(msg));

		messageList.add(messageMissing);

		return messageList;
	}
}
