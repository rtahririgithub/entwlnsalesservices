package com.telus.csm.ewlnsms.core.transformer;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.telus.csm.ewlnsc.adapter.domain.RegisterBillDeliveryMethodAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.UpdateBillDeliveryAdapterRequest;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.tmi.xmlschema.xsd.customer.customer.customersubdomain_v3.ContactIndividual;
import com.telus.tmi.xmlschema.xsd.customer.customer.customersubdomain_v3.ContactIndividualAssignment;
import com.telus.tmi.xmlschema.xsd.customer.customer.customersubdomain_v3.ElectronicContact;
import com.telus.tmi.xmlschema.xsd.customer.customer.customersubdomain_v3.TelecomContact;
import com.telus.tmi.xmlschema.xsd.customer.customerbill.customer_billing_sub_domain_v2.BillMedia;
import com.telus.tmi.xmlschema.xsd.customer.customerbill.customer_billing_sub_domain_v2.BillingAccount;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v4.AuditInfo;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.BillingAccountType;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.EbillNotificationType;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.ChannelPartnerUserProfileType;

public class UpdateAccountTransformer {

	private static final Logger LOGGER = Logger.getLogger(UpdateAccountTransformer.class);
	
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
			result.setOutletId(String
					.valueOf(user.getSalesRepAssociatedOutletList().get(0).getSalesRepAssociatedOutletInternalId()));
		}
		result.setCorrelationId(param.getSalesTransactionId());
		result.setOriginatorApplicationId(String.valueOf(param.getOriginatorApplicationId()));
		result.setUserId(String.valueOf(user.getSalesRepInternalId()));
		return result;
	}

	public List<ContactIndividual> updateContactIndividual(List<ContactIndividual> contactIndividualList,
			EbillNotificationType billNotification) {
		List<ContactIndividual> rtn= new ArrayList<ContactIndividual>();
		String email = billNotification.getNotificationEmailAddressTxt();
		String phone = billNotification.getNotificationSmsNum();

		if (!StringUtils.isEmpty(email) || !StringUtils.isEmpty(phone)) {
			LOGGER.info("updateContactIndividual().... has email/phone");
			for (ContactIndividual contact : contactIndividualList) {
				for (ContactIndividualAssignment assignment : contact.getContactAssignmentList()) {
					// Primary Account?
					if (EnterpriseWLNSalesServicesConstants.CONTACT_ASSIGNMENT_PRIMARY_TYPE_CODE
							.equalsIgnoreCase(assignment.getContactAssignmentTypeCode())) {
						LOGGER.info("updateContactIndividual().... found primary account");
						//contact list might be two contact, the one don't changed should not be send to downstream
						if (!rtn.contains(contact)) {
							rtn.add(contact);
						}
						// Update email
						if (!StringUtils.isEmpty(email)) {
							LOGGER.info("updateContactIndividual().... update email");
							boolean emailUpdated = false;
							for (ElectronicContact electronicContact : contact.getElectronicContactList()) {
								if (EnterpriseWLNSalesServicesConstants.ELECTRONIC_CONTACT_EMAIL_PRIMARY
										.equalsIgnoreCase(electronicContact.getElectronicContactSubTypeCode())) {
									LOGGER.info("updateContactIndividual().... found primary email and update to new one");
									electronicContact.setElectronicAddress(email);
									emailUpdated = true;
								}
							}

							// No primary email
							if (!emailUpdated) {
								LOGGER.info("updateContactIndividual().... no primary email, create one");
								ElectronicContact electronicContact = new ElectronicContact();
								electronicContact.setElectronicAddress(email);
								electronicContact.setElectronicContactTypeCode(
										EnterpriseWLNSalesServicesConstants.ELECTRONIC_CONTACT_EMAIL);
								electronicContact.setElectronicContactSubTypeCode(
										EnterpriseWLNSalesServicesConstants.ELECTRONIC_CONTACT_EMAIL_PRIMARY);
								contact.getElectronicContactList().add(electronicContact);
							}
						}

						// Update phone
						if (!StringUtils.isEmpty(phone)) {
							LOGGER.info("updateContactIndividual().... update cell phone");
							boolean phoneUpdated = false;
							for (TelecomContact telecomContact : contact.getTelecomContactList()) {
								if (EnterpriseWLNSalesServicesConstants.TELECOM_CONTACT_CELL
										.equalsIgnoreCase(telecomContact.getTelecomContactTypeCode())) {
									LOGGER.info("updateContactIndividual().... found cell phone and update to new one");
									telecomContact.setTelephoneNumber(phone);
									phoneUpdated = true;
								}
							}

							// No cell phone
							if (!phoneUpdated) {
								LOGGER.info("updateContactIndividual().... no cell phone, create one");
								TelecomContact cellPhone = new TelecomContact();
								cellPhone.setTelephoneNumber(phone);
								cellPhone.setTelecomContactTypeCode(
										EnterpriseWLNSalesServicesConstants.TELECOM_CONTACT_CELL);
								cellPhone.setTelecomContactSubTypeCode(
										EnterpriseWLNSalesServicesConstants.TELECOM_CONTACT_CELL_PHONE);
								contact.getTelecomContactList().add(cellPhone);
							}
						}
					}
				}
			}
		} else {
			LOGGER.info("updateContactIndividual().... no email/phone");
			return null;
		}

		return rtn;
	}

	public String getPrimaryEmail(List<ContactIndividual> contactIndividualList) {
		String email = null;

		for (ContactIndividual contact : contactIndividualList) {
			for (ContactIndividualAssignment assignment : contact.getContactAssignmentList()) {
				// Primary Account?
				if (EnterpriseWLNSalesServicesConstants.CONTACT_ASSIGNMENT_PRIMARY_TYPE_CODE
						.equalsIgnoreCase(assignment.getContactAssignmentTypeCode())) {

					for (ElectronicContact electronicContact : contact.getElectronicContactList()) {
						if (EnterpriseWLNSalesServicesConstants.ELECTRONIC_CONTACT_EMAIL_PRIMARY
								.equalsIgnoreCase(electronicContact.getElectronicContactSubTypeCode())) {
							email = electronicContact.getElectronicAddress();
						}
					}
				}
			}
		}

		return email;
	}
	//NWLN-10684
	public String getPrimaryPhoneNumber(List<ContactIndividual> contactIndividualList) {
		String phoneNumber = null;

		for (ContactIndividual contact : contactIndividualList) {
			for (ContactIndividualAssignment assignment : contact.getContactAssignmentList()) {
				// Primary Account?
				if (EnterpriseWLNSalesServicesConstants.CONTACT_ASSIGNMENT_PRIMARY_TYPE_CODE
						.equalsIgnoreCase(assignment.getContactAssignmentTypeCode())) {

					for (TelecomContact telecomContact : contact.getTelecomContactList()) {
						if (EnterpriseWLNSalesServicesConstants.TELECOM_CONTACT_CELL
								.equalsIgnoreCase(telecomContact.getTelecomContactTypeCode())) {
							phoneNumber = telecomContact.getTelephoneNumber();
						}
					}
				}
			}
		}

		return phoneNumber;
	}

	public boolean isPaperToEBill(BillingAccountType billingAccountType, List<BillingAccount> billingAccountList) {

		String accountNumber = billingAccountType.getBillingAccountNumber();
		//input must have ebill
		if (billingAccountType.getBillingDeliveryMethodList() != null
				&& !billingAccountType.getBillingDeliveryMethodList().isEmpty()) {
			if (!billingAccountType.getBillingDeliveryMethodList()
					.contains(EnterpriseWLNSalesServicesConstants.CODS_INVOICE_MEDIA_TYPE_EBILL))
				return false;
		}
		//existing list must have >0 copy of paper bill
		for (BillingAccount account : billingAccountList) {
			if (account.getBillingAccountNumber().equalsIgnoreCase(accountNumber)) {
				for (BillMedia billMedia : account.getBillMediaList()) {
					if (EnterpriseWLNSalesServicesConstants.CODS_INVOICE_MEDIA_TYPE_PAPER
							.equalsIgnoreCase(billMedia.getMediaTypeCode()) && billMedia.getCopyCount() > 0) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public RegisterBillDeliveryMethodAdapterRequest createRegisterBillDeliveryMethodAdapterRequest(String transactionId,
			BillingAccountType billingAccountType, String email, OperationHeader header) {

		BillMedia billMedia = new BillMedia();
		billMedia.setMediaTypeCode(EnterpriseWLNSalesServicesConstants.CODS_INVOICE_MEDIA_TYPE_EBILL);
		billMedia.setEBillEmail(email);

		RegisterBillDeliveryMethodAdapterRequest request = new RegisterBillDeliveryMethodAdapterRequest();
		request.setBillingSystemId(Integer.valueOf(billingAccountType.getMasterAccountSourceTypeCd()));
		request.setBillingAccountNumber(billingAccountType.getBillingAccountNumber());
		request.setBillMedia(billMedia);
		request.setAuditInfo(getAuditInfoFromHeader(header));
		request.setSalesTransactionId(transactionId);

		return request;
	}
	//NWLN-10684
	public UpdateBillDeliveryAdapterRequest createUpdateBillDeliveryAdapterRequest(String transactionId,
			BillingAccountType billingAccountType, EbillNotificationType billNotification, String email, String phoneNumber, OperationHeader header) {

		UpdateBillDeliveryAdapterRequest request = new UpdateBillDeliveryAdapterRequest();
		List<BillMedia> billMediaList = new ArrayList<BillMedia>();
		BillMedia billMedia = new BillMedia();
		billMedia.setMediaTypeCode(EnterpriseWLNSalesServicesConstants.CODS_INVOICE_MEDIA_TYPE_EBILL);
		billMedia.setCopyCount(1);
		if (email != null) {
			billMedia.setEBillEmail(email);
		}
		billMediaList.add(billMedia);
		
		request.setBillingSystemId(Integer.valueOf(billingAccountType.getMasterAccountSourceTypeCd()));
		request.setBillingAccountNumber(billingAccountType.getBillingAccountNumber());
		request.setBillMedia(billMediaList);
		
		if (EnterpriseWLNSalesServicesConstants.EBILL_NOTIFICATION_TYPE_EMAIL_SMS.equalsIgnoreCase(billNotification.getNotificationMethodCd())) {
			if (email != null) {
				request.setEmailAddress(Arrays.asList(email));
			}
			if (phoneNumber != null) {
				request.setCellPhoneNumber(Arrays.asList(phoneNumber));
			}
		} else if (EnterpriseWLNSalesServicesConstants.EBILL_NOTIFICATION_TYPE_EMAIL.equalsIgnoreCase(billNotification.getNotificationMethodCd())) {
			if (email != null) {
				request.setEmailAddress(Arrays.asList(email));
			}
		} else if (EnterpriseWLNSalesServicesConstants.EBILL_NOTIFICATION_TYPE_SMS.equalsIgnoreCase(billNotification.getNotificationMethodCd())) {
			if (phoneNumber != null) {
				request.setCellPhoneNumber(Arrays.asList(phoneNumber));
			}
		}
		request.setNotificationMethod(billNotification.getNotificationMethodCd());
		request.setAuditInfo(getAuditInfoFromHeader(header));
		request.setSalesTransactionId(transactionId);

		return request;
	}
}
