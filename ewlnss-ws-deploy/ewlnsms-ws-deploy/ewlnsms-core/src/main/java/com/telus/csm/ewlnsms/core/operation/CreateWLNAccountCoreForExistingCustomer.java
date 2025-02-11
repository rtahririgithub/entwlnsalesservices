package com.telus.csm.ewlnsms.core.operation;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.IConsumerCustomerMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.IConsumerIdentityProfileMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.ccm.domain.GetFullCustomerInfoAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.GetFullCustomerInfoAdapterResponse;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateContactIndividualAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateContactIndividualAdapterResponse;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateCustomerOfficialAddressAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.UpdateCustomerOfficialAddressAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.UpdateOwnerIdentityCredentialAdapterRequest;
import com.telus.csm.ewlnsc.helper.AdapterResponseUtil;
import com.telus.csm.ewlnsc.task.UpdateContactIndividualTask;
import com.telus.csm.ewlnsc.task.UpdateOfficialAddressTask;
import com.telus.csm.ewlnsc.task.UpdateOwnerIdentityCredentialTask;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.StringUtility;
import com.telus.csm.ewlnsc.util.workmanager.ICommonJWorkManager;
import com.telus.csm.ewlnsc.util.workmanager.WorkManagerFactory;
import com.telus.csm.ewlnsms.core.domain.CreateWLNAccountCoreRequest;
import com.telus.csm.ewlnsms.core.domain.CreateWLNAccountCoreResponse;
import com.telus.csm.ewlnsms.core.transformer.CreateWLNAccountTransformer;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumeridentityprofilemgmtsvcrequestresponse_v1.IdentityCredential;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumeridentityprofilemgmtsvcrequestresponse_v1.IdentityCredentialOwner;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.customercommon_v3.Address;
import com.telus.tmi.xmlschema.xsd.customer.customer.customersubdomain_v3.ContactIndividual;
import com.telus.tmi.xmlschema.xsd.customer.customer.customersubdomain_v3.ContactIndividualAssignment;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.NewCustomerInformation;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;

import commonj.work.Work;

public class CreateWLNAccountCoreForExistingCustomer extends CreateWLNAccountCoreOperation{

	protected CreateWLNAccountCoreResponse createAccountForExistingCustomer(final CreateWLNAccountCoreRequest requestBO,
			CreateWLNAccountCoreResponse result) {
		NewCustomerInformation customerInfo = requestBO.getCustomerInfo();
		com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.Address mainAddress = null;
		if (customerInfo != null){
			mainAddress = customerInfo.getMainAddress();
		}
		CreateWLNAccountCoreResponse newResult = result;
		long custIdLong;
		custIdLong = Long.parseLong(requestBO.getCustomerId());
		final GetFullCustomerInfoAdapterResponse fullCustResp = getFullCustomerInfo(new GetFullCustomerInfoAdapterRequest(custIdLong, requestBO.getOperationHeader().getSalesTransactionId()));
		if (fullCustResp.isMsgHasError()) {
			AdapterResponseUtil.propagateMessage(fullCustResp, newResult.getMessageList());
			changePolicyErrorCode(newResult, fullCustResp);
		} else {
			updateOfficialAddress(custIdLong, fullCustResp.getFullCustomer().getAddressList(), requestBO.getOperationHeader(), mainAddress);
			if (customerInfo != null){
				updateContactIndividual(fullCustResp.getFullCustomer().getCustomerId(), fullCustResp.getFullCustomer().getContactIndividualList(), customerInfo, requestBO.getOperationHeader());
			}
			//Call ConsumerIdentityProfileMgmtSvc.updateOwnerIdentityCredential()
			updateOwnerIdentityCredential(requestBO);
			createAccount(requestBO, custIdLong, newResult);
			if (!StringUtils.isEmpty(newResult.getBillingAccountNumber())) {
				//call eBill Notification logic
				// eBillAdoptionAndActivation(requestBO, result, custIdLong)
				eBillAdoptionAndActivationTask(requestBO.getOperationHeader(), newResult.getBillingAccountNumber(), 
						custIdLong, requestBO.getAccountInfo().getEbillDeclinedReasonCd(), 
						requestBO.getAccountInfo().getEBillNotificationTypeCd());
			}
		}
		return newResult;
	}
	
	private void updateOfficialAddress(final Long custId, final List<Address> addressList, 
			OperationHeader header, 
			com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.Address mainAddress) {
		if (isCustomerOfficialAddressFound(addressList)) {
			LOGGER.debug("customer already has official address.");
		} else {
			final CreateWLNAccountTransformer transformer = new CreateWLNAccountTransformer();
			final IConsumerCustomerMgmtSvcAdapter adapter = AdapterFactory.getAdapter(IConsumerCustomerMgmtSvcAdapter.class);
			final UpdateCustomerOfficialAddressAdapterRequest adapterReq = new UpdateCustomerOfficialAddressAdapterRequest();
			final Address address = new Address();
			address.setAddressTypeCode(EnterpriseWLNSalesServicesConstants.ADDRESS_TYPE_OFFICIAL);
			address.setAddressAssignmentTypeCode(EnterpriseWLNSalesServicesConstants.ADDRESS_ASSIGNMENT_TYPE_OFFICIAL);
			address.setPostalZipCode(StringUtility.covertSimplePostalCodeTo6Digitals(mainAddress.getPostalZipCode()));

			adapterReq.setOfficialAddress(address);
			adapterReq.setCustomerId(custId);
			adapterReq.setAuditInfo(transformer.getAuditInfoFromHeader(header));
			adapterReq.setSalesTransactionId(transId);
			
			adapter.updateCustomerOfficialAddress(adapterReq);
		}
	}
	
	private void updateContactIndividual(final Long custId, final List<ContactIndividual> contactList, NewCustomerInformation customerInfo, OperationHeader header) {
		LOGGER.info("updateContactIndividual called.");
		final IConsumerCustomerMgmtSvcAdapter adapter = AdapterFactory.getAdapter(IConsumerCustomerMgmtSvcAdapter.class);
		final CreateWLNAccountTransformer transformer = new CreateWLNAccountTransformer();

		ContactIndividual[] contactIndividuals = transformer.setCustomerContactsForExistingCustomer(customerInfo, contactList);

		if (contactIndividuals != null) {
			for (int i = 0; i < contactIndividuals.length; i++) {
				if (contactIndividuals[i] != null) {
					setPrimaryEmailAddressMissingReasonCd(customerInfo, contactIndividuals, i);
					List<ContactIndividualAssignment> contactIndividualAssignments = contactIndividuals[i].getContactAssignmentList();

					setContactIndividualAssignmentEntity(custId, contactIndividualAssignments);
					
					UpdateContactIndividualAdapterRequest adapterReq = new UpdateContactIndividualAdapterRequest();
					adapterReq.setSalesTransactionId(transId);
					adapterReq.setCustomerContact(contactIndividuals[i]);
					adapterReq.setAuditInfo(transformer.getAuditInfoFromHeader(header));
					
					adapter.updateContactIndividual(adapterReq);
				}
			}
		}
	}

	private void setContactIndividualAssignmentEntity(final Long custId,
			List<ContactIndividualAssignment> contactIndividualAssignments) {
		for (int j = 0; j < contactIndividualAssignments.size(); j++) {
			contactIndividualAssignments.get(j).setEntityId(custId);
			contactIndividualAssignments.get(j).setEntityTypeCode(EnterpriseWLNSalesServicesConstants.CONTACT_INDIVIDUAL_ENTITY_TYPE_CODE);
			contactIndividualAssignments.get(j).setContactAssignmentTypeCode(EnterpriseWLNSalesServicesConstants.CONTACT_ASSIGNMENT_PRIMARY_TYPE_CODE);
		}
	}

	private void setPrimaryEmailAddressMissingReasonCd(NewCustomerInformation customerInfo,
			ContactIndividual[] contactIndividuals, int i) {
		if (!StringUtils.isEmpty(customerInfo.getEmailDeclinedReasonCd())) {
			contactIndividuals[i].setPrimaryEmailAddressMissingReasonCd(customerInfo.getEmailDeclinedReasonCd());
		}
	}

	private void updateOwnerIdentityCredential(CreateWLNAccountCoreRequest requestBO) {
		String functionName = "updateOwnerIdentityCredential()";
		loggerUtil.enter(functionName);
		// Call ConsumerIdentityProfileMgmtSvc.updateOwnerIdentityCredential()
		String customerId = requestBO.getCustomerId();
		String pin = requestBO.getCustomerInfo().getPin();
		
		UpdateOwnerIdentityCredentialAdapterRequest adapterReq = new UpdateOwnerIdentityCredentialAdapterRequest();
		IConsumerIdentityProfileMgmtSvcAdapter adapter = AdapterFactory.getAdapter(IConsumerIdentityProfileMgmtSvcAdapter.class);
		CreateWLNAccountTransformer transformer = new CreateWLNAccountTransformer();
		
		IdentityCredentialOwner identityCredentialOwner = new IdentityCredentialOwner();
		identityCredentialOwner.setCustomerId(Long.valueOf(customerId));
		
		IdentityCredential identityCredential = new IdentityCredential();
		identityCredential.setTypeCode(EnterpriseWLNSalesServicesConstants.UPDATE_OWNER_IDENTITY_CREDENTIAL_TYPE_CODE);
		identityCredential.setValue(pin);
		
		adapterReq.setIdentityCredentialOwner(identityCredentialOwner);
		adapterReq.setIdentityCredential(identityCredential);
		adapterReq.setAuditInfo(transformer.getAuditInfoFromHeader(requestBO.getOperationHeader()));
		
		List<Work> tasksList = new ArrayList<Work>();
		tasksList.add(new UpdateOwnerIdentityCredentialTask(adapter, adapterReq));
		try {
			ICommonJWorkManager commonJMgr = WorkManagerFactory.getCommonJWorkManager();
			commonJMgr.processAsyncTasks(tasksList);
		} catch (NamingException e) {
			loggerUtil.error("", functionName, JWORK_MANAGER_EXCEPTION_MSG + EnterpriseWLNSalesServicesConstants.SPACE + e);
		}
		
		loggerUtil.exit(functionName);
	}
	
	/**
	 * check to see if a given customer has official address assigned.
	 *
	 * @param getFullCustomerInfoResponse
	 * @return
	 * @return boolean
	 */
	private boolean isCustomerOfficialAddressFound(final List<Address> addressList) {
		boolean result = false;
		
		if (addressList != null) {
			for (final Address address : addressList) {
	            if ("C".equalsIgnoreCase(address.getAddressAssignmentTypeCode())){
	            	result = true;
	            }
	        }
		}
		
		return result;
	}
	
}
