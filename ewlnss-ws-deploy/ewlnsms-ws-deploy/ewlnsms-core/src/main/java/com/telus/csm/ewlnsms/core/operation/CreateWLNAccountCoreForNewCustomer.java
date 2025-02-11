package com.telus.csm.ewlnsms.core.operation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.telus.csm.ewlnsc.adapter.IEnterpriseConsumerProfileRegistrationSvcAdapter;
import com.telus.csm.ewlnsc.adapter.IWLNCreditProfileMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.ccm.domain.GetFullCustomerInfoAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.GetFullCustomerInfoAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.CreateProfileForNewCustomerAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.SearchCreditProfileByCreditIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.SearchCreditProfileByCreditIdAdapterResponse;
import com.telus.csm.ewlnsc.helper.AdapterResponseUtil;
import com.telus.csm.ewlnsc.task.CreateProfileForNewCustomerTask;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.workmanager.ICommonJWorkManager;
import com.telus.csm.ewlnsc.util.workmanager.WorkManagerFactory;
import com.telus.csm.ewlnsms.core.domain.CreateWLNAccountCoreRequest;
import com.telus.csm.ewlnsms.core.domain.CreateWLNAccountCoreResponse;
import com.telus.csm.ewlnsms.core.transformer.CreateWLNAccountTransformer;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumercustomermgmtsvcrequestresponse_v2.FullCustomer;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.MatchingCustomerInfo;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.CreditIdentification;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.customercommon_v3.Address;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.customercommon_v3.Name;
import com.telus.tmi.xmlschema.xsd.customer.customer.wirelinecreditprofilemanagementtypes_v2.CreditProfileSearchResult;
import com.telus.tmi.xmlschema.xsd.customer.customerbill.customer_billing_sub_domain_v2.BillingAccount;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.MatchingAccountInfo;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.NewCustomerInformation;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.PersonalCreditInformation;

import commonj.work.Work;

public class CreateWLNAccountCoreForNewCustomer extends CreateWLNAccountCoreOperation{

	protected CreateWLNAccountCoreResponse createAccountForNewCustomer(CreateWLNAccountCoreRequest requestBO,
			CreateWLNAccountCoreResponse result) {
		CreateWLNAccountCoreResponse newResult = result;
		NewCustomerInformation customerInfo = requestBO.getCustomerInfo();
		final CreateWLNAccountTransformer transformer = new CreateWLNAccountTransformer();
		boolean isNewCustomer = false;

		PersonalCreditInformation creditIdentification;
		creditIdentification = setCreditIdentification(requestBO);
		
		if (creditIdentification != null){
			final SearchCreditProfileByCreditIdAdapterRequest searchCrReq = new SearchCreditProfileByCreditIdAdapterRequest();
			
			setCreditIdentificationAndAuditInfo(requestBO, customerInfo, transformer, searchCrReq);
			
			final SearchCreditProfileByCreditIdAdapterResponse searchCrResp = searchCreditProfileByCreditId(searchCrReq);

			if (searchCrResp.isMsgHasError()) {
				LOGGER.error("Search credit profile return error");
				AdapterResponseUtil.propagateMessage(searchCrResp, newResult.getMessageList());
				changePolicyErrorCode(newResult, searchCrResp);
			} else if (CollectionUtils.isEmpty(searchCrResp.getCreditProfileSearchResultList())) {
				LOGGER.info("Search credit profile return empty, perform create customer and account.");
				isNewCustomer = createNewCustomerAccountAndEbillTask(requestBO, newResult);
			} else {
				LOGGER.info("Search credit profile return match(s).");

				final List<MatchingCustomerInfo> custInfoSumList = new ArrayList<MatchingCustomerInfo>();
				int numOfActiveDuplicateAccounts = 0;
				int numOfClosedAccountsWithBalance = 0;
				int numOfCancelledWirelineAccounts = 0;

				getCustomerInfoAndPopulateDuplicateAndClosedAcc(transId, custInfoSumList, searchCrResp.getCreditProfileSearchResultList());

				for (final MatchingCustomerInfo elem : custInfoSumList) {
					numOfActiveDuplicateAccounts += elem.getActiveWirelineAccountList().size();
					numOfClosedAccountsWithBalance += elem.getClosedWirelineAccountWithBalanceList().size();
					numOfCancelledWirelineAccounts += elem.getCancelledWirelineAccountList().size();
				}

				// if any matches, treat that as duplicated
				if (numOfActiveDuplicateAccounts > 0 || numOfClosedAccountsWithBalance > 0 || numOfCancelledWirelineAccounts > 0) {
					LOGGER.info("Duplicate accounts or closed accounts with balance or closed accounts without balance found.");
					newResult = new CreateWLNAccountCoreResponse();
					newResult.setMatchingCustomerInfoList(custInfoSumList);
				} else { // no match, create new customer and create billing account
					LOGGER.info("Search credit profile return account that doesn't have duplicate account, or closed account with balance, or closed account without balance, perform create customer.");
					isNewCustomer = createNewCustomerAccountAndEbillTask(requestBO, newResult);
				}
			}
		}else{
			LOGGER.info("Search credit profile was not triggered because creditIdentification was not provided. Perform create customer.");
			isNewCustomer = createNewCustomerAccountAndEbillTask(requestBO, newResult);
		}
					
	
		createProfileForNewCustomer(requestBO, newResult, customerInfo, isNewCustomer);
		
		return newResult;
	}
	
	private boolean createNewCustomerAccountAndEbillTask(CreateWLNAccountCoreRequest requestBO, CreateWLNAccountCoreResponse newResult){
		boolean isNewCustomer = false;
		createNewCustomer(requestBO, newResult);
		if (newResult.getCustomerId() != null) {
			isNewCustomer = true;
			createAccount(requestBO, Long.valueOf(newResult.getCustomerId()), newResult);
			if (!StringUtils.isEmpty(newResult.getBillingAccountNumber())) {
				//call eBill Notification logic
				// eBillAdoptionAndActivation(requestBO, result, Long.valueOf(newResult.getCustomerId()))
				eBillAdoptionAndActivationTask(requestBO.getOperationHeader(), newResult.getBillingAccountNumber(), 
						Long.valueOf(newResult.getCustomerId()), requestBO.getAccountInfo().getEbillDeclinedReasonCd(), 
						requestBO.getAccountInfo().getEBillNotificationTypeCd());
			}
		} else {
			LOGGER.info("Customer Id is null.");
			
		}
		return isNewCustomer;
	}

	private PersonalCreditInformation setCreditIdentification(CreateWLNAccountCoreRequest requestBO) {
		PersonalCreditInformation newCreditIdentification = null;
		if (requestBO.getCustomerInfo().getCreditProfile() != null && requestBO.getCustomerInfo().getCreditProfile().getCreditIdentification() != null){
			newCreditIdentification = requestBO.getCustomerInfo().getCreditProfile().getCreditIdentification();
		}
		return newCreditIdentification;
	}

	private void createProfileForNewCustomer(CreateWLNAccountCoreRequest requestBO, CreateWLNAccountCoreResponse result,
			NewCustomerInformation customerInfo, boolean isNewCustomer) {
		if (isNewCustomer && customerInfo != null && !StringUtils.isEmpty(customerInfo.getEmailAddressTxt())) {
			createProfileForNewCustomer(result.getCustomerId(), requestBO);
		}
	}

	private void setCreditIdentificationAndAuditInfo(CreateWLNAccountCoreRequest requestBO,
			NewCustomerInformation customerInfo, final CreateWLNAccountTransformer transformer,
			final SearchCreditProfileByCreditIdAdapterRequest searchCrReq) {
		if (customerInfo != null && customerInfo.getCreditProfile() != null) {
			final PersonalCreditInformation pci = customerInfo.getCreditProfile().getCreditIdentification();
			final CreditIdentification credit = transformer.getCreditIdentificationFromConsumerCreditIdentification(pci, false);
			searchCrReq.setCreditIdentification(credit);
			searchCrReq.setAuditInfo(transformer.getAuditInfoV6EntrCommon(requestBO.getOperationHeader()));
		}
	}

	private void getCustomerInfoAndPopulateDuplicateAndClosedAcc(String transId,
			final List<MatchingCustomerInfo> custInfoSumList, List<CreditProfileSearchResult> creditProfileSearchResults) {
		for (final CreditProfileSearchResult elem : creditProfileSearchResults) {
			Date birthDate = null;
			long custId = elem.getCustomerID();

			if (elem.getPersonalInfo() != null) {
				birthDate = elem.getPersonalInfo().getBirthDate();
			}

			GetFullCustomerInfoAdapterResponse fullCustResp = getFullCustomerInfo(new GetFullCustomerInfoAdapterRequest(custId, transId));

			if (fullCustResp != null && fullCustResp.getFullCustomer() != null) {
				custInfoSumList.addAll(populateDuplicateAndClosedAccountList(fullCustResp, birthDate));
			}
		}
		
	}

	private SearchCreditProfileByCreditIdAdapterResponse searchCreditProfileByCreditId(final SearchCreditProfileByCreditIdAdapterRequest sProfileByCreditId) {
		SearchCreditProfileByCreditIdAdapterResponse result;
		
		final IWLNCreditProfileMgmtSvcAdapter adapter = AdapterFactory.getAdapter(IWLNCreditProfileMgmtSvcAdapter.class);
		result = adapter.searchCreditProfileByCreditId(sProfileByCreditId);
		
		return result;
	}
	
	/**
	 * Populate duplicate account list and closed account list for a given customer.
	 *
	 * @param fullCustomerResp
	 * @param birthDate
	 */
	private List<MatchingCustomerInfo> populateDuplicateAndClosedAccountList(final GetFullCustomerInfoAdapterResponse fullCustomerResp, final Date birthDate) {
		final List<MatchingCustomerInfo> result = new ArrayList<MatchingCustomerInfo>();
		
		final FullCustomer fullCustomer = fullCustomerResp.getFullCustomer();
		final List<BillingAccount> billingAccounts = fullCustomer.getBillingAccountList();
		final List<Name> names = fullCustomer.getNameList();
		final String firstName = names.get(0).getFirstName();
		final String lastName = names.get(0).getLastName();
		Double totalBalance = 0.0;
		Double closedAccountBalance = 0.0;
		Double cancelledAccountBalance = 0.0;

		final MatchingCustomerInfo custSummary = new MatchingCustomerInfo();
		custSummary.setCustomerId(String.valueOf(fullCustomer.getCustomerId()));
		custSummary.setDateOfBirthDt(birthDate);
		custSummary.setFirstName(firstName);
		custSummary.setLastName(lastName);

		// existing client list with all "cancelled" accounts and account balance is zero or negative
		final List<MatchingAccountInfo> cancelledAccList = new ArrayList<MatchingAccountInfo>();
		// existing client list with all "cancelled" accounts and account balance is greater than zero
		List<MatchingAccountInfo> closedAccList = new ArrayList<MatchingAccountInfo>();
		// active billings account list for existing clients with at least one active account
		final List<MatchingAccountInfo> activeAccList = new ArrayList<MatchingAccountInfo>();

		for (final BillingAccount billingAccount : billingAccounts) {
			if (isBillingMasterSourceIdEnabler(billingAccount)) {
				totalBalance = getTotalBalance(totalBalance, billingAccount);

				List<Address> addresses = billingAccount.getAddresses();

				MatchingAccountInfo billingAccountSummary = new MatchingAccountInfo();
				billingAccountSummary.setBillingAccountNumber(billingAccount.getBillingAccountNumber());

				setAccountSummaryBillingAddress(addresses, billingAccountSummary);

				//In case billing address not found with AddressAssignmentTypeCode == M take the 1st element of address for safe side.
				setBillingAddressForNonMAssignmentTypeCd(addresses, billingAccountSummary);

				if ( isActiveAccount(billingAccount) ) {
					LOGGER.info("Duplicate active account found: " + billingAccount.getBillingAccountNumber());
					setBalanceAmtForActive(billingAccount, billingAccountSummary);
					activeAccList.add(billingAccountSummary);
				} else if (	isClosedAccount(billingAccount) && (billingAccount.getCurrentBalanceAmount() != null && billingAccount.getCurrentBalanceAmount().doubleValue() > 0)) {

					LOGGER.info("Closed account with outstanding balance found: " + billingAccount.getBillingAccountNumber());
					closedAccountBalance = getBalanceAmount(billingAccount, billingAccountSummary);

					closedAccList.add(billingAccountSummary);
					
				}else if (	isClosedAccount(billingAccount) && (billingAccount.getCurrentBalanceAmount() != null && billingAccount.getCurrentBalanceAmount().doubleValue() <= 0)){
					LOGGER.info("Cancelled account with zero balance found: " + billingAccount.getBillingAccountNumber());

					//for #30096
					cancelledAccountBalance = getBalanceAmount(billingAccount, billingAccountSummary);

					cancelledAccList.add(billingAccountSummary);
				} else {
					LOGGER.info("Account found with status not under Active, Closed or Cancelled category. BAN = " + billingAccount.getBillingAccountNumber() + ", Status Code = " + billingAccount.getStatusCode());
				}
			}
		}

		custSummary.setTotalBalanceAmt(totalBalance);

		//for fixing defect #30127
		//if there is at least one active account,ignore any cancelled or closed accounts.
		setActiveClosedOrCancelledWlnAccList(result, closedAccountBalance, cancelledAccountBalance, custSummary,
				cancelledAccList, closedAccList, activeAccList);

		return result;
	}

	private void setBalanceAmtForActive(final BillingAccount billingAccount,
			MatchingAccountInfo billingAccountSummary) {
		billingAccountSummary.setBalanceAmt(billingAccount.getCurrentBalanceAmount() == null? 0.0 : billingAccount.getCurrentBalanceAmount().doubleValue());
	}

	private boolean isBillingMasterSourceIdEnabler(final BillingAccount billingAccount) {
		return billingAccount.getBillingMasterSourceId() == Long.parseLong(EnterpriseWLNSalesServicesConstants.ENABLER_BILLING_ACCOUNT_SYSTEM_CODE);
	}

	private void setActiveClosedOrCancelledWlnAccList(final List<MatchingCustomerInfo> result,
			Double closedAccountBalance, Double cancelledAccountBalance, final MatchingCustomerInfo custSummary,
			final List<MatchingAccountInfo> cancelledAccList, List<MatchingAccountInfo> closedAccList,
			final List<MatchingAccountInfo> activeAccList) {
		if (!activeAccList.isEmpty()) {
			custSummary.setActiveWirelineAccountList(activeAccList);
			logMessageOnCancelledOrClosedAccList(custSummary, cancelledAccList, closedAccList);
			result.add(custSummary);
		} else {
			custSummary.setTotalBalanceAmt(cancelledAccountBalance + closedAccountBalance);
			custSummary.setClosedWirelineAccountWithBalanceList(closedAccList);
			custSummary.setCancelledWirelineAccountList(cancelledAccList);
			result.add(custSummary);
		}
	}

	private Double getTotalBalance(Double totalBalance, final BillingAccount billingAccount) {
		Double newTotalBalance = 0.0;
		if (billingAccount.getCurrentBalanceAmount() != null) {
			newTotalBalance = totalBalance + billingAccount.getCurrentBalanceAmount().doubleValue();
		}
		return newTotalBalance;
	}

	private void setBillingAddressForNonMAssignmentTypeCd(List<Address> addresses,
			MatchingAccountInfo billingAccountSummary) {
		if (billingAccountSummary.getBillingAddress() == null && !addresses.isEmpty()) {
			billingAccountSummary.setBillingAddress(convertAddress(addresses.get(0)));
		}
	}

	private void logMessageOnCancelledOrClosedAccList(final MatchingCustomerInfo custSummary,
			final List<MatchingAccountInfo> cancelledAccList, List<MatchingAccountInfo> closedAccList) {
		if (!cancelledAccList.isEmpty() || !closedAccList.isEmpty()) {
			LOGGER.info("Customer: " + custSummary.getCustomerId() + ", First Name: " + custSummary.getFirstName() + ", Last Name: " + custSummary.getLastName() + 
				" has cancelled or closed accounts but got ignored because the active account exists. CancelledWirelineAccount.size: " + cancelledAccList.size() + 
				", ClosedWirelineAccount.size: " + closedAccList.size());
		}
	}

	private Double getBalanceAmount(final BillingAccount billingAccount,
			MatchingAccountInfo billingAccountSummary) {
		Double accountBalance = 0.0;
		if(billingAccount.getCurrentBalanceAmount() != null){
			accountBalance = billingAccount.getCurrentBalanceAmount();
			billingAccountSummary.setBalanceAmt(accountBalance);
		}
		return accountBalance;
	}

	private void setAccountSummaryBillingAddress(List<Address> addresses, MatchingAccountInfo billingAccountSummary) {
		for (final Address address : addresses) {
			if (address != null && Constants.ADDRESS_BILLING_ASSIGNMENT_TYPE.equals(address.getAddressAssignmentTypeCode())) {
				billingAccountSummary.setBillingAddress(convertAddress(address));
				break;
			}
		}
	}
	
	/**
	 * Construct customer contacts.
	 *
	 * @param wirelineCustomerInfo
	 * @return
	 */
	
	private void createProfileForNewCustomer(final String custId, CreateWLNAccountCoreRequest requestBO) {
		List<Work> tasksList = new ArrayList<Work>();
		String functionName = "createProfileForNewCustomer()";
		IEnterpriseConsumerProfileRegistrationSvcAdapter adapter = AdapterFactory.getAdapter(IEnterpriseConsumerProfileRegistrationSvcAdapter.class);
		CreateProfileForNewCustomerAdapterRequest adapterReq = new CreateProfileForNewCustomerAdapterRequest();
		adapterReq.setCustomerId(custId);
		adapterReq.setEmail(requestBO.getCustomerInfo().getEmailAddressTxt());
		adapterReq.setFirstName(requestBO.getCustomerInfo().getFirstName());
		adapterReq.setLastName(requestBO.getCustomerInfo().getLastName());
		adapterReq.setNotificationLanguage(requestBO.getCustomerInfo().getPreferredLanguageCd());
		tasksList.add(new CreateProfileForNewCustomerTask(adapter, adapterReq));
		
		if(!tasksList.isEmpty()){
			try{
				ICommonJWorkManager commonJMgr = WorkManagerFactory.getCommonJWorkManager();
				commonJMgr.processAsyncTasks(tasksList);
			} catch (NamingException e) {
				loggerUtil.error("", functionName, JWORK_MANAGER_EXCEPTION_MSG + EnterpriseWLNSalesServicesConstants.SPACE + e);
			}
		}		
	}
	
	private com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.Address convertAddress(final Address param) {
		final com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.Address result = 
				new com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.Address();
		
	    result.setAddressId(String.valueOf(param.getAddressId()));
	    result.setAddressTypeCode(param.getAddressTypeCode());
	    result.setAddressAssignmentSubTypeCode(param.getAddressAssignmentSubTypeCode());
	    result.setAddressAssignmentTypeCode(param.getAddressAssignmentTypeCode());
	    result.setAddressMatchingStatusCode(param.getAddressMatchingStatusCode());
	    result.setAddressSearchText(param.getAddressSearchText());
	    result.setCountryCode(param.getCountryCode());
	    result.setEmailAddressText(param.getEmailAddressText());
	    result.setExternalAddressId(param.getExternalAddressId());
	    result.setExternalAddressSourceId(String.valueOf(param.getExternalAddressSourceId()));
	    result.setExternalServiceAddressId(param.getExternalServiceAddressId());
	    result.setMailingTypeCode(param.getMailingTypeCode());
	    result.setMunicipalityName(param.getMunicipalityName());
	    result.setPostalZipCode(param.getPostalZipCode());
	    result.setProvinceStateCode(param.getProvinceStateCode());
	    result.setRelateAddressAssignmentId(String.valueOf(param.getRelateAddressAssignmentId()));
	    result.setStreetName(param.getStreetName());
	    result.setValidateAddressIndicator(param.getValidateAddressIndicator());
	    result.setUnitTypeCode(param.getUnitTypeCode());
	    result.setPoBox(param.getPostOfficeBoxNumber());
	    result.setRuralNumber(param.getRuralRouteNumber());
	    result.setStreetVector(param.getStreetDirectionCode());
	    result.setAdditionalAddressInformationText(param.getAdditionalAddressInformation());
	    result.setRenderedAddressText(param.getRenderedAddress());
	    result.setStreetNumber(param.getCivicNumber());
	    result.setStreetTypeSuffixCode(param.getStreetTypeCode());
	    result.setUnitName(param.getUnitNumber());
	    
		return result;
	}
	
	private boolean isClosedAccount(BillingAccount billingAccount) {
		return Constants.ACC_STATUS_CANCELLED.equals(billingAccount.getStatusCode()) 
				|| Constants.ACC_STATUS_CLOSED.equals(billingAccount.getStatusCode()) 
				|| Constants.ACC_STATUS_CLOSED_ENABLER.equals(billingAccount.getStatusCode()) 
				|| isInactiveOrClosedL(billingAccount);
	}
	
	private boolean isInactiveOrClosedL(BillingAccount billingAccount){
		return Constants.ACC_STATUS_INACTIVE.equals(billingAccount.getStatusCode()) 
				|| Constants.ACC_STATUS_CLOSED_L.equals(billingAccount.getStatusCode());
	}

	private boolean isActiveAccount(BillingAccount billingAccount) {
		return Constants.ACC_STATUS_OPEN.equals(billingAccount.getStatusCode()) 
				|| Constants.ACC_STATUS_TENTATIVE.equals(billingAccount.getStatusCode()) 
				|| Constants.ACC_STATUS_SUSPENDED.equals(billingAccount.getStatusCode()) 
				|| Constants.ACC_STATUS_ACTIVE.equals(billingAccount.getStatusCode());
	}
	
}
