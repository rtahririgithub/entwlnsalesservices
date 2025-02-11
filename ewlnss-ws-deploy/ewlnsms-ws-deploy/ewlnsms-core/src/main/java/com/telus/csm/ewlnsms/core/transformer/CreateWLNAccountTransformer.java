package com.telus.csm.ewlnsms.core.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.telus.csm.ewlnsc.adapter.ccm.domain.CreateNewCustomerAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CreateBillingAccountAdapterRequest;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.SalesServiceConstants;
import com.telus.csm.ewlnsc.util.StringUtility;
import com.telus.csm.ewlnsms.core.domain.CreateWLNAccountCoreRequest;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumerbillingaccountmgmtsvcrequestresponse_v1.CreateBillingAccount;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.CreditAddress;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.CreditIdentification;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.CreditProfileData;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.customercommon_v3.Address;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.customercommon_v3.Name;
import com.telus.tmi.xmlschema.xsd.customer.customer.customersubdomain_v3.ContactIndividual;
import com.telus.tmi.xmlschema.xsd.customer.customer.customersubdomain_v3.ContactIndividualAssignment;
import com.telus.tmi.xmlschema.xsd.customer.customer.customersubdomain_v3.Customer;
import com.telus.tmi.xmlschema.xsd.customer.customer.customersubdomain_v3.ElectronicContact;
import com.telus.tmi.xmlschema.xsd.customer.customer.customersubdomain_v3.TelecomContact;
import com.telus.tmi.xmlschema.xsd.customer.customerbill.customer_billing_sub_domain_v2.BillMedia;
import com.telus.tmi.xmlschema.xsd.customer.customerbill.customer_billing_sub_domain_v2.BillingAccount;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v4.AuditInfo;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.Account;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.NewCustomerInformation;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.PersonalCreditInformation;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.ChannelPartnerUserProfileType;

public class CreateWLNAccountTransformer {


	public CreateNewCustomerAdapterRequest getCreateCustReqDO(final CreateWLNAccountCoreRequest requestVO) {
		final CreateNewCustomerAdapterRequest result = new CreateNewCustomerAdapterRequest();
		
		final Customer customer = new Customer();
		final NewCustomerInformation custInfo = requestVO.getCustomerInfo();
		
		customer.setNameList(getCustomerNames(custInfo));
		// set the PIN
		customer.setCustomerPIN(custInfo.getPin());
		// set the service address to customer

		customer.setAddressList(getCustomerAddresses(requestVO.getAddress()));
		customer.setCustomerTypeCode(EnterpriseWLNSalesServicesConstants.CUSTOMER_TYPE_INDIVIDUAL);
		customer.setCustomerSubTypeCode(EnterpriseWLNSalesServicesConstants.CUSTOMER_SUB_TYPE_RESIDENTIAL);
		customer.setHearingImpairedInd(false);
		customer.setVisuallyImpairedInd(false);
		// customer.setIvrEnabled(IVR_ENABLED_TRUE)
		customer.setCustomerStatusCode(EnterpriseWLNSalesServicesConstants.CUSTOMER_STATUS_CODE);
		
		final List<ContactIndividual> contactIndividuals = setCustomerContactsForNewCustomer(custInfo);
		// set credit profile
		result.setCreditProfile(getConsumerCreditProfile(custInfo));

		result.setCustomer(customer);
		result.setCustomerContactsList(contactIndividuals);
		result.setAuditInfo(getAuditInfoFromHeader(requestVO.getOperationHeader()));
		result.setSalesTransactionId(requestVO.getOperationHeader().getSalesTransactionId());
		
		return result;
	}

	/**
	 * construct customer names.
	 *
	 * @param wirelineCustomerInfo
	 * @return
	 */
	private List<Name> getCustomerNames(NewCustomerInformation wirelineCustomerInfo) {
		final List<Name> result = new ArrayList<Name>();
		
		Name name = new Name();
		name.setFirstName(wirelineCustomerInfo.getFirstName());
		name.setFullName(wirelineCustomerInfo.getFirstName() + " " + wirelineCustomerInfo.getLastName());
		name.setLastName(wirelineCustomerInfo.getLastName());
		name.setSalutationCode(wirelineCustomerInfo.getTitleName());
		name.setLanguage(wirelineCustomerInfo.getPreferredLanguageCd()); // this has been set to contactIndividual below
		result.add(name);
		
		return result;
	}

	/**
	 * Construct customer names by calling the base one and overwrite the language code.
	 * To be used when creating the account.
	 * 
	 * @param wirelineCustomerInfo
	 * @param langCd
	 * @return
	 */
	private List<Name> getCustomerNames(final NewCustomerInformation wirelineCustomerInfo, final String langCd) {
		final List<Name> result = getCustomerNames(wirelineCustomerInfo);
		
		result.get(0).setLanguage(langCd);
		
		return result;
	}
	
	/**
	 * construct addresses.
	 *
	 * @param wirelineCustomerInfo
	 * @return
	 */
	private List<Address> getCustomerAddresses(final com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.Address param) {
		final List<Address> result = new ArrayList<Address>();
			
		final Address address = copyAddress(param);
		if (address != null){
			if (StringUtils.isEmpty(address.getAddressTypeCode())) {
				address.setAddressTypeCode(EnterpriseWLNSalesServicesConstants.ADDRESS_TYPE_OFFICIAL);
			}
			if (StringUtils.isEmpty(address.getAddressAssignmentTypeCode())) {
			   address.setAddressAssignmentTypeCode(EnterpriseWLNSalesServicesConstants.ADDRESS_ASSIGNMENT_TYPE_OFFICIAL);
			}
			
			address.setPostalZipCode(StringUtility.covertSimplePostalCodeTo6Digitals(address.getPostalZipCode()));

			result.add(address);
		}
		
		return result;
	}
	

	/**
	 * Copy and return a new and identical object and the given address object.
	 *
	 * @param address
	 * @return
	 */
	private Address copyAddress(final com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.Address address) {
		if (address == null) {
			return null;
		}

		final Address result = new Address();
		if (!StringUtils.isEmpty(address.getAddressId())){
			result.setAddressId(Long.valueOf(address.getAddressId()));
		}
		result.setAddressTypeCode(address.getAddressTypeCode());
		result.getAdditionalAddressInformation().clear();
		result.getAdditionalAddressInformation().addAll(address.getAdditionalAddressInformationText());
		result.getRenderedAddress().clear();
		result.getRenderedAddress().addAll(address.getRenderedAddressText());
		if (!StringUtils.isEmpty(address.getAddressAssignmentId())){
			result.setAddrAssgnmtId(Long.valueOf(address.getAddressAssignmentId()));
		}
		result.setAddressAssignmentSubTypeCode(address.getAddressAssignmentSubTypeCode());
		result.setAddressAssignmentTypeCode(address.getAddressAssignmentTypeCode());
		result.setAddressMatchingStatusCode(address.getAddressMatchingStatusCode());
		result.setAddressSearchText(address.getAddressSearchText());
		result.setCountryCode(address.getCountryCode());
		result.setEmailAddressText(address.getEmailAddressText());
		result.setExternalAddressId(address.getExternalAddressId());
		if (!StringUtils.isEmpty(address.getExternalAddressSourceId())){
			result.setExternalAddressSourceId(Long.valueOf(address.getExternalAddressSourceId()));
		}
		result.setExternalServiceAddressId(address.getExternalServiceAddressId());
		result.setMailingTypeCode(address.getMailingTypeCode());
		result.setMunicipalityName(address.getMunicipalityName());
		result.setPostOfficeBoxNumber(address.getPoBox());
		result.setPostalZipCode(StringUtility.covertSimplePostalCodeTo6Digitals(address.getPostalZipCode()));
		result.setProvinceStateCode(address.getProvinceStateCode());
		if (!StringUtils.isEmpty(address.getRelateAddressAssignmentId())){
			result.setRelateAddressAssignmentId(Long.valueOf(address.getRelateAddressAssignmentId()));
		}
		result.setStreetName(address.getStreetName());
		result.setCivicNumber(address.getStreetNumber()); 
		result.setUnitNumber(address.getUnitName());
		result.setValidateAddressIndicator(address.getValidateAddressIndicator());
		result.setUnitTypeCode(address.getUnitTypeCode());
		result.setRuralRouteNumber(address.getRuralNumber());

		return result;
	}
	
	/**
	 * Construct customer contacts.
	 *
	 * @param custInfo
	 * @return
	 */
	private List<ContactIndividual> setCustomerContactsForNewCustomer(final NewCustomerInformation custInfo) {
		final List<ContactIndividual> result = new ArrayList<ContactIndividual>();
		// build contact individuals list and set it to createCustomer
		final ContactIndividualAssignment contactPrimaryAssignment = new ContactIndividualAssignment();
		contactPrimaryAssignment.setContactAssignmentTypeCode(EnterpriseWLNSalesServicesConstants.CONTACT_ASSIGNMENT_PRIMARY_TYPE_CODE);
		contactPrimaryAssignment.setContactAssignmentRoleTypeCode(EnterpriseWLNSalesServicesConstants.CONTACT_ASSIGNMENT_PRIMARY_ROLE_TYPE_CODE);
		contactPrimaryAssignment.setPreferredContactTimePeriodCode(custInfo.getPreferredContactTimePeriodCd());
		final ContactIndividualAssignment contactAuthorizedAssignment = new ContactIndividualAssignment();
		contactAuthorizedAssignment.setContactAssignmentTypeCode(EnterpriseWLNSalesServicesConstants.CONTACT_ASSIGNMENT_AUTHORIZED_TYPE_CODE);
		contactAuthorizedAssignment.setContactAssignmentRoleTypeCode(EnterpriseWLNSalesServicesConstants.CONTACT_ASSIGNMENT_AUTHORIZED_ROLE_TYPE_CODE);
		contactAuthorizedAssignment.setPreferredContactTimePeriodCode(custInfo.getPreferredContactTimePeriodCd());

		final TelecomContact dayPhone = new TelecomContact();
		final TelecomContact eveningPhone = new TelecomContact();
		final TelecomContact workPhone = new TelecomContact();
		final TelecomContact cellPhone = new TelecomContact();
		dayPhone.setTelephoneNumber(EnterpriseWLNSalesServicesConstants.EMPTY);
		dayPhone.setTelecomContactTypeCode(EnterpriseWLNSalesServicesConstants.TELECOM_CONTACT_PHONE);
		dayPhone.setTelecomContactSubTypeCode(EnterpriseWLNSalesServicesConstants.TELECOM_CONTACT_DAY_PHONE);
		eveningPhone.setTelephoneNumber(EnterpriseWLNSalesServicesConstants.EMPTY);
		eveningPhone.setTelecomContactTypeCode(EnterpriseWLNSalesServicesConstants.TELECOM_CONTACT_PHONE);
		eveningPhone.setTelecomContactSubTypeCode(EnterpriseWLNSalesServicesConstants.TELECOM_CONTACT_EVENING_PHONE);
		if (!StringUtils.isEmpty(custInfo.getServicePhoneNumber())) {
			dayPhone.setTelephoneNumber(custInfo.getServicePhoneNumber());
			eveningPhone.setTelephoneNumber(custInfo.getServicePhoneNumber());
		}
		// since work phone and cell phone are not mandatory as are day phone and
		// evening phone, we should build the objects as such
		boolean wpFound = false;
		boolean cpFound = false;
		if (!StringUtils.isEmpty(custInfo.getBusinessPhoneNumber())) {
			workPhone.setTelephoneNumber(custInfo.getBusinessPhoneNumber());
			workPhone.setTelephoneExtensionNumber(custInfo.getBusinessPhoneExtensionNumber());
			workPhone.setTelecomContactTypeCode(EnterpriseWLNSalesServicesConstants.TELECOM_CONTACT_PHONE);
			workPhone.setTelecomContactSubTypeCode(EnterpriseWLNSalesServicesConstants.TELECOM_CONTACT_WORK_PHONE);
			wpFound = true;
		}
		if (!StringUtils.isEmpty(custInfo.getMobilePhoneNumber())) {
			cellPhone.setTelephoneNumber(custInfo.getMobilePhoneNumber());
			cellPhone.setTelecomContactTypeCode(EnterpriseWLNSalesServicesConstants.TELECOM_CONTACT_CELL);
			cellPhone.setTelecomContactSubTypeCode(EnterpriseWLNSalesServicesConstants.TELECOM_CONTACT_CELL_PHONE);
			cpFound = true;
		}
		final List<TelecomContact> telecomContactList = new ArrayList<TelecomContact>();
		telecomContactList.add(dayPhone);
		telecomContactList.add(eveningPhone);
		if (wpFound) {
			telecomContactList.add(workPhone);
		}
		if (cpFound) {
			telecomContactList.add(cellPhone);
		}
		final ContactIndividual primaryContact = new ContactIndividual();
		final ContactIndividual authorizedContact = new ContactIndividual();
		primaryContact.setTelecomContactList(telecomContactList);
		setPrimaryEmailAddressMissingReasonCd(custInfo, primaryContact);
		if (!StringUtils.isEmpty(custInfo.getEmailAddressTxt())) {
			final ElectronicContact electronicContact = new ElectronicContact();
			electronicContact.setElectronicAddress(custInfo.getEmailAddressTxt());
			electronicContact.setElectronicContactTypeCode(EnterpriseWLNSalesServicesConstants.ELECTRONIC_CONTACT_EMAIL);
			electronicContact.setElectronicContactSubTypeCode(EnterpriseWLNSalesServicesConstants.ELECTRONIC_CONTACT_EMAIL_PRIMARY);
			final List<ElectronicContact> electronicContactList = new ArrayList<ElectronicContact>();
			electronicContactList.add(electronicContact);
			primaryContact.setElectronicContactList(electronicContactList);
		}
		primaryContact.setFirstName(custInfo.getFirstName());
		primaryContact.setLastName(custInfo.getLastName());
		primaryContact.setPreferredLanguageCode(StringUtils.capitalize(custInfo.getPreferredLanguageCd()));
		
		primaryContact.setSalutationCode(custInfo.getTitleName());
		final List<ContactIndividualAssignment> contactPrimaryAssignmentList = new ArrayList<ContactIndividualAssignment>();
		contactPrimaryAssignmentList.add(contactPrimaryAssignment);
		final List<ContactIndividualAssignment> contactAuthorizedAssignmentList = new ArrayList<ContactIndividualAssignment>();
		contactAuthorizedAssignmentList.add(contactAuthorizedAssignment);
		primaryContact.setContactAssignmentList(contactPrimaryAssignmentList);

		// Populate authorized contact information
		authorizedContact.setContactAssignmentList(contactAuthorizedAssignmentList);
		authorizedContact.setFullName(custInfo.getAuthorizedName());

		result.add(primaryContact);
		result.add(authorizedContact);

		return result;
	}

	/**
	 * Get the ConsumerCreditProfile from ConsumerCreditProfileInfo.
	 *
	 * @param param
	 * @return
	 */
	private CreditProfileData getConsumerCreditProfile(final NewCustomerInformation param) {
		final CreditProfileData result = new CreditProfileData();

		//result.setBusinessLastUpdateTimestamp(param.get)
		result.setCreditAddress(convertCreditAddress(param.getCreditProfile().getCreditAddress()));
		// set the credit address type code is empty
		if (param.getCreditProfile().getCreditAddress() != null && StringUtils.isEmpty(param.getCreditProfile().getCreditAddress().getAddressTypeCode())) {
			result.getCreditAddress().setCreditAddressTypeCd(Constants.ADDRESS_TYPE_CREDIT_BUREAU);
		}
		// set the credit address to null if country is not Canada
		// defect #9414; we have to accept non-canadian address
		// care service expects clear text
		result.setCreditIdentification(getCreditIdentificationFromConsumerCreditIdentification(param.getCreditProfile().getCreditIdentification(), false));
		//CreditValueCd not available anymore in the result. To be removed
		result.setFormatCd(EnterpriseWLNSalesServicesConstants.CREDIT_PROFILE_FORMAT);
		//setMethod not available anymore to be removed
		result.setApplicationProvinceCd(param.getCreditProfile().getCreditApplicationProvinceCd());
		result.setPersonalInfo(param.getCreditProfile().getPersonalInfo());
		result.setCreditProfileStatusCd(EnterpriseWLNSalesServicesConstants.CREDIT_PROFILE_STATUS);
		// defect #WLS3998; make the postal code upper case
		if (result.getCreditAddress() != null && SalesServiceConstants.COUNTRY_CODE_CAN.equalsIgnoreCase(param.getCreditProfile().getCreditAddress().getCountryCode())) {
			result.getCreditAddress().setPostalCd(StringUtility.covertSimplePostalCodeTo6Digitals(result.getCreditAddress().getPostalCd()));
		}

		return result;
	}
	
	/**
	 * Map a given ConsumerCreditIdentification object to CreditIdentification object.
	 * The province code from health card is taken out.
	 *
	 * @param cIdentification
	 * @return
	 */
	public CreditIdentification getCreditIdentificationFromConsumerCreditIdentification(final PersonalCreditInformation ccIdentification, boolean encrypt) {
		final CreditIdentification result = new CreditIdentification();
		
		if (ccIdentification != null) {
			
			if (ccIdentification.getDriverLicense() != null && !StringUtils.isEmpty(ccIdentification.getDriverLicense().getDriverLicenseNum())) {
				final String drLiv = StringUtility.encrypt(ccIdentification.getDriverLicense().getDriverLicenseNum(), encrypt);
				result.setDriverLicense(ccIdentification.getDriverLicense());
				result.getDriverLicense().setDriverLicenseNum(drLiv);
			}
			
			if (ccIdentification.getPassport() != null && !StringUtils.isEmpty(ccIdentification.getPassport().getPassportNum())) {
				final String passport = StringUtility.encrypt(ccIdentification.getPassport().getPassportNum(), encrypt);
				result.setPassport(ccIdentification.getPassport());
				result.getPassport().setPassportNum(passport);
			}
			
			if (ccIdentification.getProvincialIdCard() != null && !StringUtils.isEmpty(ccIdentification.getProvincialIdCard().getProvincialIdNum())) {
				final String provId = StringUtility.encrypt(ccIdentification.getProvincialIdCard().getProvincialIdNum(), encrypt);
				result.setProvincialIdCard(ccIdentification.getProvincialIdCard());
				result.getProvincialIdCard().setProvincialIdNum(provId);
			}
			
			if (ccIdentification.getSin() != null && !StringUtils.isEmpty(ccIdentification.getSin())) {
				final String sin = StringUtility.encrypt(ccIdentification.getSin(), encrypt);
				result.setSin(ccIdentification.getSin());
				result.setSin(sin);
			}
			setHealthCardNum(ccIdentification, encrypt, result);
		}

		return result;
	}

	private void setHealthCardNum(final PersonalCreditInformation ccIdentification, boolean encrypt,
			final CreditIdentification result) {
		if (ccIdentification.getHealthCard() != null && !StringUtils.isEmpty(ccIdentification.getHealthCard().getHealthCardNum())) {
			final String healthCard = ccIdentification.getHealthCard().getHealthCardNum();
			result.setHealthCard(ccIdentification.getHealthCard());
			final String healthNr = StringUtility.encrypt(healthCard, encrypt);
			result.getHealthCard().setHealthCardNum(healthNr);
		}
	}

	private CreditAddress convertCreditAddress(com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.Address address) {
		if (address == null) {
			return null;
		}

		final CreditAddress result = new CreditAddress();
		
		result.setCreditAddressTypeCd(Constants.CREDIT_ADDRESS_TYPE_CODE);
		result.setAddressTypeCd(address.getAddressTypeCode());
		result.setCityName(address.getMunicipalityName());
		result.setProvinceCd(address.getProvinceStateCode());
		result.setCountryCd(address.getCountryCode());
		result.setPostalCd(address.getPostalZipCode());
		
		// set address line one
		if (!StringUtils.isEmpty(address.getStreetNumber())) {
			if (StringUtils.isEmpty(address.getUnitName())) {
				result.setAddressLineOne(address.getStreetNumber() + " " + address.getStreetName());
			} else {
				result.setAddressLineOne(address.getUnitName() + " - " + address.getStreetNumber() + " " + address.getStreetName());
			}
		} else if (!StringUtils.isEmpty(address.getPoBox())) {
			result.setAddressLineOne("PO BOX " + address.getPoBox());
		} else {
			result.setAddressLineOne("RR " + address.getRuralNumber());
		}

		// set address line two
		result.setAddressLineTwo(address.getMunicipalityName() + ", " + address.getProvinceStateCode() + ", " + address.getPostalZipCode());
		
		return result;
	}

	
	public CreateBillingAccountAdapterRequest getBillAccReqDO(final CreateWLNAccountCoreRequest requestVO, final Long customerId) {
		final CreateBillingAccountAdapterRequest result = new CreateBillingAccountAdapterRequest();
		
		final NewCustomerInformation custInfo = requestVO.getCustomerInfo();
		final Account account = requestVO.getAccountInfo();
		
		final BillingAccount billAccount = new BillingAccount();
		billAccount.setBillingNames(getCustomerNames(custInfo, account.getPreferredContactLanguageCode()));
		final BillMedia billMedia = new BillMedia();
		String billingMediaTypeCd = null;
		if (account.getBillingDeliveryMethodList() != null && !account.getBillingDeliveryMethodList().getMediaTypeCode().isEmpty()) {
			String elem = account.getBillingDeliveryMethodList().getMediaTypeCode().get(0);
			billMedia.setMediaTypeCode(elem);
			billMedia.setDetailLevelCode(EnterpriseWLNSalesServicesConstants.BILLING_MEDIA_DETAIL_LEVEL_CODE);
			billMedia.setCopyCount(EnterpriseWLNSalesServicesConstants.BILLING_MEDIA_COPY_COUNT);
			billingMediaTypeCd = elem;
		}
		
		billAccount.getBillMediaList().add(billMedia);

		if (EnterpriseWLNSalesServicesConstants.CODS_INVOICE_MEDIA_TYPE_EBILL.equalsIgnoreCase(billingMediaTypeCd)) {

			if (account.getBillingAddress() != null) {
				final Address address = copyAddress(account.getBillingAddress());
				convertBillingAddress(billAccount, address);
			}
			convertAddress(requestVO, billAccount);
			// set the third address as email (only if emailDeclinedInd is false and emailAddressTxt is provided, otherwise, downstream will throw an error if emailAddressTxt is missing)
			if ((custInfo.isEmailDeclinedInd() == null || !custInfo.isEmailDeclinedInd()) && !StringUtils.isEmpty(custInfo.getEmailAddressTxt())){
				final Address address = new Address();
				address.setAddressTypeCode(EnterpriseWLNSalesServicesConstants.ADDRESS_TYPE_EMAIL);
				address.setAddressAssignmentTypeCode(EnterpriseWLNSalesServicesConstants.ADDRESS_ASSIGNMENT_TYPE_OFFICIAL);
				address.setEmailAddressText(custInfo.getEmailAddressTxt());
				billAccount.getAddresses().add(address);
			}
			
		} else {
			if (requestVO.getAddress() != null) {
				final Address address = copyAddress(account.getBillingAddress());
				convertBillingAddress(billAccount, address);
			}
			convertAddress(requestVO, billAccount);
		}

		billAccount.setCustomerId(customerId);
		billAccount.setBillingMasterSourceId(Long.parseLong(Constants.ENABLER_BILLING_ACCOUNT_SYSTEM_CODE));
		billAccount.setBillingAccountNumber(EnterpriseWLNSalesServicesConstants.BILLING_ACCOUNT_NUMBER);
		billAccount.setBillInsertSuppressionIndicator(EnterpriseWLNSalesServicesConstants.BILLING_INSERT_SUPPRESSION_INDICATOR);
		billAccount.setBillReturnEnvelopeCode(EnterpriseWLNSalesServicesConstants.BILLING_RETURN_ENVELOPE_CODE);

		final CreateBillingAccount createBillingAccount = new CreateBillingAccount();
		createBillingAccount.setNewBillingAccount(billAccount);
		createBillingAccount.setAuditInfo(getAuditInfoFromHeader(requestVO.getOperationHeader()));
		
		result.setRequest(createBillingAccount);
		result.setSalesTransactionId(requestVO.getOperationHeader().getSalesTransactionId()); // 
		
		return result;
	}

	private void convertAddress(final CreateWLNAccountCoreRequest requestVO, final BillingAccount billAccount) {
		if (requestVO.getAddress() != null) {
			final Address address = copyAddress(requestVO.getAddress());
			if (address != null){
				address.setAddressTypeCode(EnterpriseWLNSalesServicesConstants.ADDRESS_TYPE_TAX);
				address.setAddressAssignmentTypeCode(EnterpriseWLNSalesServicesConstants.ADDRESS_ASSIGNMENT_TYPE_TAX);
				address.setPostalZipCode(StringUtility.covertSimplePostalCodeTo6Digitals(address.getPostalZipCode()));		
				billAccount.getAddresses().add(address);
			}
		}
	}

	private void convertBillingAddress(final BillingAccount billAccount, final Address address) {
		if (address != null){
			populateBillingAddress(address);
			address.setPostalZipCode(StringUtility.covertSimplePostalCodeTo6Digitals(address.getPostalZipCode()));
			billAccount.getAddresses().add(address);
		}
	}

	/**
	 * @param address
	 */
	private void populateBillingAddress(final Address address) {
		final String countryCd = address.getCountryCode();
		
		setAddressTypeCode(address, countryCd);
		
		if (StringUtils.isEmpty(address.getAddressAssignmentTypeCode())) {
			address.setAddressAssignmentTypeCode(EnterpriseWLNSalesServicesConstants.ADDRESS_BILLING_ASSIGNMENT_TYPE);
		}

		if (countryCd != null && (countryCd.toUpperCase().contains(SalesServiceConstants.COUNTRY_CODE_UN) || countryCd.toUpperCase().contains(SalesServiceConstants.COUNTRY_CODE_US))) {
			address.setAddressAssignmentTypeCode(EnterpriseWLNSalesServicesConstants.ADDRESS_BILLING_ASSIGNMENT_TYPE);
			address.setMasterSourceId(Constants.SALES_CUSTOMER_ODS_ADDRESS_MASTER_ID);

			String usStreetName = "";
			if (address.getUnitNumber() != null) {
				usStreetName = usStreetName + address.getUnitNumber() + "-";
			}
			if (address.getCivicNumber() != null) {
				usStreetName = usStreetName + address.getCivicNumber() + " ";
			}
			if (address.getStreetName() != null) {
				usStreetName = usStreetName + address.getStreetName();
			}

			final List<String> usAddressList = new ArrayList<String>();
			usAddressList.add(usStreetName);
			if (CollectionUtils.isEmpty(address.getAdditionalAddressInformation())) {
			    address.setAdditionalAddressInformation(usAddressList);
			}
			if (CollectionUtils.isEmpty(address.getRenderedAddress())) {
			    address.setRenderedAddress(usAddressList);
			}
		}

		address.setPostalZipCode(StringUtility.covertSimplePostalCodeTo6Digitals(address.getPostalZipCode()));
	}

	private void setAddressTypeCode(final Address address, final String countryCd) {
		if (StringUtils.isEmpty(address.getAddressTypeCode())) {
			if (!StringUtils.isEmpty(address.getRuralRouteNumber())) {
				address.setAddressTypeCode(EnterpriseWLNSalesServicesConstants.ADDRESS_TYPE_RR);
			} else if (!StringUtils.isEmpty(address.getPostOfficeBoxNumber())) {
				address.setAddressTypeCode(EnterpriseWLNSalesServicesConstants.ADDRESS_TYPE_POBOX);
			} else if (countryCd != null && countryCd.toUpperCase().contains(SalesServiceConstants.COUNTRY_CODE_CAN)) {
				address.setAddressTypeCode(EnterpriseWLNSalesServicesConstants.ADDRESS_TYPE_CANADA);
			} else if (countryCd != null && (countryCd.toUpperCase().contains(SalesServiceConstants.COUNTRY_CODE_UN) || 
							countryCd.toUpperCase().contains(SalesServiceConstants.COUNTRY_CODE_US))){
				address.setAddressTypeCode(EnterpriseWLNSalesServicesConstants.ADDRESS_TYPE_US);
			}
		}
	}

	public com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v6.AuditInfo getAuditInfoV6EntrCommon(final OperationHeader param) {

		com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v6.AuditInfo
			result = new com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v6.AuditInfo();

		result.setUserId(String.valueOf(param.getUserProfile().getSalesRepInternalId()));
		result.setSalesRepresentativeId(String.valueOf(param.getUserProfile().getSalesRepInternalId()));
		result.setCorrelationId(param.getSalesTransactionId());
		result.setChannelOrganizationId(String.valueOf(param.getUserProfile().getChnlOrgInternalId()));
		if (!param.getUserProfile().getSalesRepAssociatedOutletList().isEmpty()) {
			result.setOutletId(param.getUserProfile().getSalesRepAssociatedOutletList().get(0).getSalesRepAssociatedChannelOutletId());
		}
		result.setOriginatorApplicationId(SalesServiceConstants.APPLICATION_ID_DEFAULT);

		return result;
	}
	
	public ContactIndividual[] setCustomerContactsForExistingCustomer(final NewCustomerInformation custInfo, final List<ContactIndividual> contactList) {
		// QC44538 Find the existing primary contact individual and assignment as we need some default values
		ContactIndividualAssignment existingContactIndividualAssignment = null;
		ContactIndividual existingContactIndividual= null;
		for (final ContactIndividual contactIndividual : contactList) {
			for (ContactIndividualAssignment contactIndividualAssignment : contactIndividual.getContactAssignmentList()) {
				if (EnterpriseWLNSalesServicesConstants.CONTACT_ASSIGNMENT_PRIMARY_TYPE_CODE.equals(contactIndividualAssignment.getContactAssignmentTypeCode())) {
					existingContactIndividualAssignment = contactIndividualAssignment;
					existingContactIndividual = contactIndividual;
					break;
				}
			}
		}
		// build contact individuals list and set it to createCustomer
		ContactIndividualAssignment contactPrimaryAssignment = new ContactIndividualAssignment();
		contactPrimaryAssignment.setContactAssignmentTypeCode(EnterpriseWLNSalesServicesConstants.CONTACT_ASSIGNMENT_PRIMARY_TYPE_CODE);
		contactPrimaryAssignment.setContactAssignmentRoleTypeCode(EnterpriseWLNSalesServicesConstants.CONTACT_ASSIGNMENT_PRIMARY_ROLE_TYPE_CODE);
		setPreferredContactTimeAndMethod(existingContactIndividualAssignment, contactPrimaryAssignment);
		TelecomContact dayPhone = new TelecomContact();
		TelecomContact eveningPhone = new TelecomContact();
		TelecomContact workPhone = new TelecomContact();
		TelecomContact cellPhone = new TelecomContact();
		dayPhone.setTelephoneNumber(EnterpriseWLNSalesServicesConstants.EMPTY);
		dayPhone.setTelecomContactTypeCode(EnterpriseWLNSalesServicesConstants.TELECOM_CONTACT_PHONE);
		dayPhone.setTelecomContactSubTypeCode(EnterpriseWLNSalesServicesConstants.TELECOM_CONTACT_DAY_PHONE);
		//missing contractId in the request which will cause updateContactIndividual failed.
		setTelecomContactId(existingContactIndividualAssignment, dayPhone);
		eveningPhone.setTelephoneNumber(EnterpriseWLNSalesServicesConstants.EMPTY);
		eveningPhone.setTelecomContactTypeCode(EnterpriseWLNSalesServicesConstants.TELECOM_CONTACT_PHONE);
		eveningPhone.setTelecomContactSubTypeCode(EnterpriseWLNSalesServicesConstants.TELECOM_CONTACT_EVENING_PHONE);
		//missing contractId in the request which will cause updateContactIndividual failed.
		setTelecomContactId(existingContactIndividualAssignment, eveningPhone);
		if (!StringUtils.isEmpty(custInfo.getServicePhoneNumber())) {
			dayPhone.setTelephoneNumber(custInfo.getServicePhoneNumber());
			eveningPhone.setTelephoneNumber(custInfo.getServicePhoneNumber());
		}
		// since work phone and cell phone are not mandatory as are day phone and
		// evening phone, we should build the objects as such
		boolean wpFound = false;
		boolean cpFound = false;
		if (!StringUtils.isEmpty(custInfo.getBusinessPhoneNumber())) {
			workPhone.setTelephoneNumber(custInfo.getBusinessPhoneNumber());
			workPhone.setTelephoneExtensionNumber(custInfo.getBusinessPhoneExtensionNumber());
			workPhone.setTelecomContactTypeCode(EnterpriseWLNSalesServicesConstants.TELECOM_CONTACT_PHONE);
			workPhone.setTelecomContactSubTypeCode(EnterpriseWLNSalesServicesConstants.TELECOM_CONTACT_WORK_PHONE);

			wpFound = true;
		}
		if (!StringUtils.isEmpty(custInfo.getMobilePhoneNumber())) {
			cellPhone.setTelephoneNumber(custInfo.getMobilePhoneNumber());
			cellPhone.setTelecomContactTypeCode(EnterpriseWLNSalesServicesConstants.TELECOM_CONTACT_CELL);
			cellPhone.setTelecomContactSubTypeCode(EnterpriseWLNSalesServicesConstants.TELECOM_CONTACT_CELL_PHONE);
			cpFound = true;
		}
		List<TelecomContact> telecomContactList = new ArrayList<TelecomContact>();
		telecomContactList.add(dayPhone);
		telecomContactList.add(eveningPhone);
		if (wpFound) {
			telecomContactList.add(workPhone);
		}
		if (cpFound) {
			telecomContactList.add(cellPhone);
		}
		ContactIndividual primaryContact = new ContactIndividual();
		
		setPrimaryEmailAddressMissingReasonCd(custInfo, primaryContact);
		
		primaryContact.getTelecomContactList().addAll(telecomContactList);
		
		setElectronicContactList(custInfo, primaryContact);
		
		primaryContact.setFirstName(custInfo.getFirstName());
		if (existingContactIndividual != null) {
			primaryContact.setMiddleName(existingContactIndividual.getMiddleName());
		}
		primaryContact.setLastName(custInfo.getLastName());
		primaryContact.setFullName(custInfo.getFirstName() + " " + custInfo.getLastName());
		primaryContact.setPreferredLanguageCode(custInfo.getPreferredLanguageCd());
		primaryContact.setSalutationCode(custInfo.getTitleName());
		primaryContact.getContactAssignmentList().add(contactPrimaryAssignment);

		return new ContactIndividual[] { primaryContact };
	}

	private void setPrimaryEmailAddressMissingReasonCd(final NewCustomerInformation custInfo,
			ContactIndividual primaryContact) {
		if (!StringUtils.isEmpty(custInfo.getEmailDeclinedReasonCd())){
			primaryContact.setPrimaryEmailAddressMissingReasonCd(custInfo.getEmailDeclinedReasonCd());
		}
	}

	private void setElectronicContactList(final NewCustomerInformation custInfo, ContactIndividual primaryContact) {
		if (!StringUtils.isEmpty(custInfo.getEmailAddressTxt())) {
			ElectronicContact electronicContact = new ElectronicContact();
			electronicContact.setElectronicAddress(custInfo.getEmailAddressTxt());
			electronicContact.setElectronicContactTypeCode(EnterpriseWLNSalesServicesConstants.ELECTRONIC_CONTACT_EMAIL);
			electronicContact.setElectronicContactSubTypeCode(EnterpriseWLNSalesServicesConstants.ELECTRONIC_CONTACT_EMAIL_PRIMARY);
			primaryContact.getElectronicContactList().add(electronicContact);
		}
	}

	private void setPreferredContactTimeAndMethod(ContactIndividualAssignment existingContactIndividualAssignment,
			ContactIndividualAssignment contactPrimaryAssignment) {
		if (existingContactIndividualAssignment != null) {
			contactPrimaryAssignment.setPreferredContactTimePeriodCode(existingContactIndividualAssignment.getPreferredContactTimePeriodCode());
			contactPrimaryAssignment.setPreferredContactMethodCode(existingContactIndividualAssignment.getPreferredContactMethodCode());
		}
	}
	
	/**
	 * Get audit info from header.
	 */
	public AuditInfo getAuditInfoFromHeader(final OperationHeader param) {
		final AuditInfo result = new AuditInfo();

		final ChannelPartnerUserProfileType user = param.getUserProfile();
		result.setSalesRepresentativeId(String.valueOf(user.getSalesRepInternalId()));
		result.setCorrelationId(param.getSalesTransactionId());
		result.setChannelOrganizationId(String.valueOf(user.getChnlOrgInternalId()));
		if (!user.getSalesRepAssociatedOutletList().isEmpty()) {
			result.setOutletId(String.valueOf(user.getSalesRepAssociatedOutletList().get(0).getSalesRepAssociatedOutletInternalId()));
		}
		result.setCorrelationId(param.getSalesTransactionId());
		result.setOriginatorApplicationId(String.valueOf(param.getOriginatorApplicationId()));
		result.setUserId(String.valueOf(user.getSalesRepInternalId()));
		return result;
	}
	
	private void setTelecomContactId(ContactIndividualAssignment existingContactIndividualAssignment,
			TelecomContact dayOrEveningPhone) {
		if (existingContactIndividualAssignment != null) {
			dayOrEveningPhone.setTelecomContactId(existingContactIndividualAssignment.getContactAssignmentId());
		}
	}
}
