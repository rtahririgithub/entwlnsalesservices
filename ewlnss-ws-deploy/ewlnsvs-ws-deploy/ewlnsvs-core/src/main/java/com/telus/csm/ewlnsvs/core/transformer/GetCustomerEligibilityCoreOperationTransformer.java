package com.telus.csm.ewlnsvs.core.transformer;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.*;
import com.telus.csm.ewlnsc.adapter.domain.AssessCreditWorthinessAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditEligibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditProfileByCustomerIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.UpdateCreditProfileAdapterRequest;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsvs.core.domain.GetCustomerEligibilityCoreRequest;
import com.telus.csm.ewlnsvs.core.domain.GetCustomerEligibilityCoreResponse;
import com.telus.tmi.xmlschema.srv.cmo.ordermgmt.wlncreditprofilemanagementproxyservicerequestresponse_v2.AssessCreditWorthinessRequest;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesvalidationservicerequestresponse_v5.GetCustomerEligibilityResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesvalidationservicerequestresponse_v5.GetCustomerEligibilityType;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.CreditAddress;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.CreditCardCode;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.CustomerGuarantor;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.DriverLicense;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.HealthCard;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.Passport;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.PersonalInfo;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.ProvincialIdCard;
import com.telus.tmi.xmlschema.xsd.customer.customer.customermanagementcommontypes_v4.CreditCard;
import com.telus.tmi.xmlschema.xsd.customer.customer.enterprisecreditprofilemanagementservicetypes_v2.ConsumerCreditIdentification;
import com.telus.tmi.xmlschema.xsd.customer.customer.enterprisecreditprofilemanagementservicetypes_v2.ConsumerCreditProfileInfo;
import com.telus.tmi.xmlschema.xsd.customer.customer.enterprisecreditprofilemanagementservicetypes_v2.Identification;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v6.AuditInfo;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.Address;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.CustomerCreditInformation;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;

/**
 * 
 * @author Jose.Mena
 *
 */
public class GetCustomerEligibilityCoreOperationTransformer {
	
	private GetCustomerEligibilityCoreOperationTransformer(){
		
	}
	
	public static GetCustomerEligibilityCoreRequest transformRequest(GetCustomerEligibilityType request) {
		GetCustomerEligibilityCoreRequest req = new GetCustomerEligibilityCoreRequest();
		req.setOperationHeader(request.getOperationHeader());
		req.setCustomerId(request.getCustomerId());
		req.setCreditProfile(request.getCreditProfile());
		req.setNewCustomerInd(request.isNewCustomerInd());
		req.setRefreshCreditResultInd(request.isRefreshCreditResultInd());
		if (request.getOperationHeader().isRefreshInd() == null) {
			req.getOperationHeader().setRefreshInd(false);
		} else {
			req.getOperationHeader().setRefreshInd(request.getOperationHeader().isRefreshInd());
		}
		return req;
	}

	public static GetCustomerEligibilityResponseType transformResponse(GetCustomerEligibilityCoreResponse response) {
		GetCustomerEligibilityResponseType rs = new GetCustomerEligibilityResponseType();
		rs.setCreditAssessment(response.getCreditAssessment());
		rs.setCreditEligibility(response.getCreditEligibility());
		rs.setMessageList(response.getMessageList());
		return rs;
	}

	public static UpdateCreditProfileAdapterRequest transformRequest(
			GetCustomerEligibilityCoreRequest request) {
		UpdateCreditProfileAdapterRequest rq = new UpdateCreditProfileAdapterRequest();
		rq.setConsumerCreditProfileInfo(transformCreditProfile(request));
		rq.setAuditInfo(getAuditInfoForUpdCredit(request.getOperationHeader()));
		rq.setSalesTransactionId(request.getOperationHeader().getSalesTransactionId());
		return rq;
	}

	private static ConsumerCreditProfileInfo transformCreditProfile(GetCustomerEligibilityCoreRequest request) {
		CustomerCreditInformation creditProfile = request.getCreditProfile();
		ConsumerCreditProfileInfo cred = new ConsumerCreditProfileInfo();

		Identification identification = new Identification();
		identification.setCustomerId(Long.valueOf(request.getCustomerId()));
		cred.setIdentification(identification);

		CreditAddress creditAddres;
		creditAddres = transformCreditAddress(creditProfile.getCreditAddress());
		cred.setCreditAddress(creditAddres);

		setPersonalInfo(creditProfile, cred);

		ConsumerCreditIdentification creditIdentification = new ConsumerCreditIdentification();

		if (creditProfile.getCreditIdentification() != null
				&& creditProfile.getCreditIdentification().getDriverLicense() != null) {
			DriverLicense driverLicense = new DriverLicense();
			driverLicense.setDriverLicenseNum(
					creditProfile.getCreditIdentification().getDriverLicense().getDriverLicenseNum());
			driverLicense.setProvinceCd(creditProfile.getCreditIdentification().getDriverLicense().getProvinceCd());
			driverLicense.setExpiryDate(creditProfile.getCreditIdentification().getDriverLicense().getExpiryDate());
			creditIdentification.setDriverLicense(driverLicense);
		}

		setSin(creditProfile, creditIdentification);

		if (creditProfile.getCreditIdentification() != null
				&& creditProfile.getCreditIdentification().getHealthCard() != null) {
			HealthCard healthCard = new HealthCard();
			healthCard.setHealthCardNum(creditProfile.getCreditIdentification().getHealthCard().getHealthCardNum());
			healthCard.setProvinceCd(creditProfile.getCreditIdentification().getHealthCard().getProvinceCd());
			creditIdentification.setHealthCard(healthCard);
		}

		Passport passport = new Passport();
		if (creditProfile.getCreditIdentification() != null
				&& creditProfile.getCreditIdentification().getPassport() != null) {
			passport.setPassportNum(creditProfile.getCreditIdentification().getPassport().getPassportNum());
			passport.setCountryCd(creditProfile.getCreditIdentification().getPassport().getCountryCd());
			creditIdentification.setPassport(passport);
		}

		setProvincialIdCard(creditProfile, creditIdentification);

		setCreditCardToken(creditProfile, creditIdentification);

		cred.setCreditIdentification(creditIdentification);

		cred.setCreditValueCd((creditProfile.getCreditIdentification() != null
				&& creditProfile.getCreditIdentification().getCreditValue() != null)
						? creditProfile.getCreditIdentification().getCreditValue().getCreditValueCode() : "");
		// cred.setFraudIndicatorCd(value); // Does not exist in EWLNSVS Schema
		CreditCardCode creditCardCode = new CreditCardCode();
		// creditCardCode.setPrimaryCreditCardCd(creditProfile.getPrimaryCreditCardCd())
		// // Does not exist in EWLNSVS Schema
		// creditCardCode.setSecondaryCreditCardCd(creditProfile.getSecondaryCreditCardCd())
		// // Does not exist in EWLNSVS Schema
		cred.setCreditCardCode(creditCardCode);

		cred.setApplicationProvinceCd(creditProfile.getCreditApplicationProvinceCd());

		setCustomerGuarantor(creditProfile, cred);

		if (creditProfile.getCreditAddress() != null) {
			cred.setComment(buildComment(creditProfile));
		}

		return cred;
	}

	private static void setProvincialIdCard(CustomerCreditInformation creditProfile,
			ConsumerCreditIdentification creditIdentification) {
		if (creditProfile.getCreditIdentification() != null
				&& creditProfile.getCreditIdentification().getProvincialIdCard() != null) {
			ProvincialIdCard provincialCard = new ProvincialIdCard();
			provincialCard.setProvincialIdNum(
					creditProfile.getCreditIdentification().getProvincialIdCard().getProvincialIdNum());
			provincialCard.setProvinceCd(creditProfile.getCreditIdentification().getProvincialIdCard().getProvinceCd());
			creditIdentification.setProvincialIdCard(provincialCard);
		}
	}

	private static void setCreditCardToken(CustomerCreditInformation creditProfile,
			ConsumerCreditIdentification creditIdentification) {
		if (creditProfile.getCreditIdentification() != null
				&& creditProfile.getCreditIdentification().getCreditCard() != null) {
			CreditCard creditCardToken = new CreditCard();
			creditCardToken.setToken(creditProfile.getCreditIdentification().getCreditCard().getToken());
			creditCardToken.setFirst6(creditProfile.getCreditIdentification().getCreditCard().getFirst6());
			creditCardToken.setLast4(creditProfile.getCreditIdentification().getCreditCard().getLast4());
			creditCardToken.setExpiryMonth(creditProfile.getCreditIdentification().getCreditCard().getExpiryMonth());
			creditCardToken.setExpiryYear(creditProfile.getCreditIdentification().getCreditCard().getExpiryYear());
			creditCardToken.setCardVerificationData(
					creditProfile.getCreditIdentification().getCreditCard().getCardVerificationData());
			creditCardToken.setHolderName(creditProfile.getCreditIdentification().getCreditCard().getHolderName());
			creditIdentification.setCreditCardToken(creditCardToken);
		}
	}

	private static void setCustomerGuarantor(CustomerCreditInformation creditProfile, ConsumerCreditProfileInfo cred) {
		if (creditProfile.getCustomerGuarantor() != null) {
			CustomerGuarantor customerGuarantor = new CustomerGuarantor();
			CustomerGuarantor paramGuarantor = creditProfile.getCustomerGuarantor();
			customerGuarantor.setId(paramGuarantor.getId());
			customerGuarantor.setGuarantorCustomerID(paramGuarantor.getGuarantorCustomerID());
			customerGuarantor.setGuarantorCreditProfileID(paramGuarantor.getGuarantorCreditProfileID());
			customerGuarantor.setExpiryDate(paramGuarantor.getExpiryDate());
			customerGuarantor.setGuaranteedAmt(paramGuarantor.getGuaranteedAmt());
			customerGuarantor.setGuarantorFullName(paramGuarantor.getGuarantorFullName());
			customerGuarantor.setGuarantorPhoneNum(paramGuarantor.getGuarantorPhoneNum());
			customerGuarantor.setReferenceNum(paramGuarantor.getReferenceNum());
			customerGuarantor.setCommentTxt(paramGuarantor.getCommentTxt());
			cred.setCustomerGuarantor(customerGuarantor);
		}
	}

	private static void setSin(CustomerCreditInformation creditProfile,
			ConsumerCreditIdentification creditIdentification) {
		if (creditProfile.getCreditIdentification() != null
				&& creditProfile.getCreditIdentification().getSin() != null) {
			creditIdentification.setSin(creditProfile.getCreditIdentification().getSin());
		}
	}

	private static void setPersonalInfo(CustomerCreditInformation creditProfile, ConsumerCreditProfileInfo cred) {
		if (creditProfile.getPersonalInfo() != null) {
			PersonalInfo personalInfo = new PersonalInfo();
			personalInfo.setEmploymentStatusCd(creditProfile.getPersonalInfo().getEmploymentStatusCd());
			personalInfo.setResidencyCd(creditProfile.getPersonalInfo().getResidencyCd());
			personalInfo.setCreditCheckConsentCd(creditProfile.getPersonalInfo().getCreditCheckConsentCd());
			personalInfo.setBirthDate(creditProfile.getPersonalInfo().getBirthDate());
			personalInfo.setUnderLegalCareCd(creditProfile.getPersonalInfo().getUnderLegalCareCd());
			personalInfo.setProvinceOfCurrentResidenceCd(creditProfile.getPersonalInfo().getProvinceOfCurrentResidenceCd());
			cred.setPersonalInfo(personalInfo);
		}
	}

	private static String buildComment(CustomerCreditInformation creditProfile) {
		StringBuilder sb = new StringBuilder();
		String updatedString = "updated ";
		// Credit Address
		setCommentsCreditAddress(creditProfile, sb, updatedString);
		// Personal Info
		setCommentsPersonalInfo(creditProfile, sb, updatedString);
		// Credit Identification
		if (creditProfile.getCreditIdentification() != null) {
			// driverLicense
			setCommentsDriverLicense(creditProfile, sb, updatedString);
			
			if (creditProfile.getCreditIdentification().getSin() != null) {
				sb.append(updatedString).append("CreditIdentification sin")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
			// healthCard
			setCommentsHealthCard(creditProfile, sb, updatedString);
			// Passport
			setCommentsPassport(creditProfile, sb, updatedString);
			// Provincial Id Card
			setCommentsProvincialIdCard(creditProfile, sb, updatedString);
			if (creditProfile.getCreditIdentification().getCreditValue() != null) {
				sb.append(updatedString).append("CreditIdentification CreditValue")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
		}
		// Guarantor
		setCommentsGuarantor(creditProfile, sb, updatedString);

		if (sb.toString().length() >= 1000){
			return sb.substring(0, 1000);
		}else{
			return sb.toString();
		}
	}

	private static void setCommentsDriverLicense(CustomerCreditInformation creditProfile, StringBuilder sb,
			String updatedString) {
		if (creditProfile.getCreditIdentification().getDriverLicense() != null) {
			if (creditProfile.getCreditIdentification().getDriverLicense().getDriverLicenseNum() != null) {
				sb.append(updatedString).append("CreditIdentification DriverLicenseNum")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
			if (creditProfile.getCreditIdentification().getDriverLicense().getProvinceCd() != null) {
				sb.append(updatedString).append("CreditIdentification ProvinceCd")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
			if (creditProfile.getCreditIdentification().getDriverLicense().getExpiryDate() != null) {
				sb.append(updatedString).append("CreditIdentification ExpiryDate")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
		}
	}

	private static void setCommentsHealthCard(CustomerCreditInformation creditProfile, StringBuilder sb,
			String updatedString) {
		if (creditProfile.getCreditIdentification().getHealthCard() != null) {
			if (creditProfile.getCreditIdentification().getHealthCard().getHealthCardNum() != null) {
				sb.append(updatedString).append("CreditIdentification healthCard healthCardNum")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
			if (creditProfile.getCreditIdentification().getHealthCard().getProvinceCd() != null) {
				sb.append(updatedString).append("CreditIdentification healthCard ProvinceCd")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
		}
	}

	private static void setCommentsPassport(CustomerCreditInformation creditProfile, StringBuilder sb,
			String updatedString) {
		if (creditProfile.getCreditIdentification().getPassport() != null) {
			if (creditProfile.getCreditIdentification().getPassport().getPassportNum() != null) {
				sb.append(updatedString).append("CreditIdentification passport PassportNum")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
			if (creditProfile.getCreditIdentification().getPassport().getCountryCd() != null) {
				sb.append(updatedString).append("CreditIdentification passport CountryCd")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
		}
	}

	private static void setCommentsGuarantor(CustomerCreditInformation creditProfile, StringBuilder sb,
			String updatedString) {
		if (creditProfile.getCustomerGuarantor() != null) {
			if (creditProfile.getCustomerGuarantor().getId() > 0) {
				sb.append(updatedString).append("Guarantor id").append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
			if (creditProfile.getCustomerGuarantor().getGuarantorCustomerID() > 0) {
				sb.append(updatedString).append("Guarantor GuarantorCustomerID")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
			if (creditProfile.getCustomerGuarantor().getGuarantorCreditProfileID() > 0) {
				sb.append(updatedString).append("Guarantor GuarantorCreditProfileID")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
			if (creditProfile.getCustomerGuarantor().getExpiryDate() != null) {
				sb.append(updatedString).append("Guarantor ExpiryDate")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
			if (creditProfile.getCustomerGuarantor().getGuaranteedAmt() != null) {
				sb.append(updatedString).append("Guarantor GuaranteedAmt")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
			if (creditProfile.getCustomerGuarantor().getGuarantorFullName() != null) {
				sb.append(updatedString).append("Guarantor GuarantorFullName")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
			if (creditProfile.getCustomerGuarantor().getGuarantorPhoneNum() != null) {
				sb.append(updatedString).append("Guarantor GuarantorPhoneNum")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
			setCommentsGuarantorRefNum(creditProfile, sb, updatedString);
		}
	}

	private static void setCommentsGuarantorRefNum(CustomerCreditInformation creditProfile, StringBuilder sb,
			String updatedString) {
		if (creditProfile.getCustomerGuarantor().getReferenceNum() != null) {
			sb.append(updatedString).append("Guarantor ReferenceNum")
					.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
		}
	}

	private static void setCommentsProvincialIdCard(CustomerCreditInformation creditProfile, StringBuilder sb,
			String updatedString) {
		if (creditProfile.getCreditIdentification().getProvincialIdCard() != null) {
			if (creditProfile.getCreditIdentification().getProvincialIdCard().getProvincialIdNum() != null) {
				sb.append(updatedString).append("CreditIdentification provincialIdCard ProvincialIdNum")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
			if (creditProfile.getCreditIdentification().getProvincialIdCard().getProvinceCd() != null) {
				sb.append(updatedString).append("CreditIdentification provincialIdCard ProvinceCd")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
		}
	}

	private static void setCommentsPersonalInfo(CustomerCreditInformation creditProfile, StringBuilder sb,
			String updatedString) {
		if (creditProfile.getPersonalInfo() != null) {
			if (creditProfile.getPersonalInfo().getEmploymentStatusCd() != null) {
				sb.append(updatedString).append("PersonalInfo EmploymentStatusCd")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
			if (creditProfile.getPersonalInfo().getResidencyCd() != null) {
				sb.append(updatedString).append("PersonalInfo ResidencyCd")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
			if (creditProfile.getPersonalInfo().getCreditCheckConsentCd() != null) {
				sb.append(updatedString).append("PersonalInfo CreditCheckConsentCd")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
			if (creditProfile.getPersonalInfo().getBirthDate() != null) {
				sb.append(updatedString).append("PersonalInfo BirthDate")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
			if (creditProfile.getPersonalInfo().getUnderLegalCareCd() != null) {
				sb.append(updatedString).append("PersonalInfo UnderLegalCareCd")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
			if (creditProfile.getPersonalInfo().getProvinceOfCurrentResidenceCd() != null) {
				sb.append(updatedString).append("PersonalInfo ProvinceOfCurrentResidenceCd")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
		}
	}

	private static void setCommentsCreditAddress(CustomerCreditInformation creditProfile, StringBuilder sb,
			String updatedString) {
		if (creditProfile.getCreditAddress() != null) {
			if (!StringUtils.isEmpty(creditProfile.getCreditApplicationProvinceCd())) {
				sb.append(updatedString).append("ApplicationProvinceCd")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
			if (!StringUtils.isEmpty(creditProfile.getCreditAddress().getAddressTypeCode())) {
				sb.append(updatedString).append("CreditAddress CreditAddressTypeCd")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
			if (!StringUtils.isEmpty(creditProfile.getCreditAddress().getPoBox())) {
				sb.append(updatedString).append("CreditAddress AddressLineOne")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
			if (!StringUtils.isEmpty(creditProfile.getCreditAddress().getMunicipalityName())) {
				sb.append(updatedString).append("CreditAddress AddressLineTwo")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
			if (!StringUtils.isEmpty(creditProfile.getCreditAddress().getMunicipalityName())) {
				sb.append(updatedString).append("CreditAddress CityName")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
			if (!StringUtils.isEmpty(creditProfile.getCreditAddress().getProvinceStateCode())) {
				sb.append(updatedString).append("CreditAddress ProvinceCd")
						.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
			}
			setCommentsCreditAddressCountry(creditProfile, sb, updatedString);
			
			setCommentsCreditAddressZip(creditProfile, sb, updatedString);
		}
	}

	private static void setCommentsCreditAddressZip(CustomerCreditInformation creditProfile, StringBuilder sb,
			String updatedString) {
		if (!StringUtils.isEmpty(creditProfile.getCreditAddress().getPostalZipCode())) {
			sb.append(updatedString).append("CreditAddress PostalCd")
					.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
		}
	}

	private static void setCommentsCreditAddressCountry(CustomerCreditInformation creditProfile, StringBuilder sb,
			String updatedString) {
		if (!StringUtils.isEmpty(creditProfile.getCreditAddress().getCountryCode())) {
			sb.append(updatedString).append("CreditAddress CountryCd")
					.append(EnterpriseWLNSalesServicesConstants.NEWLINE);
		}
	}

	private static CreditAddress transformCreditAddress(Address address) {
		CreditAddress creditAddress = new CreditAddress();
		creditAddress.setCreditAddressTypeCd("CB");
		if (address != null) {
			// set address line one
			if (!StringUtils.isEmpty(address.getStreetNumber())) {
				if (StringUtils.isEmpty(address.getUnitName())) {
					creditAddress.setAddressLineOne(address.getStreetNumber() + " " + address.getStreetName());
				} else {
					creditAddress.setAddressLineOne(address.getUnitName() + " - " + address.getStreetNumber() + " " + address.getStreetName());
				}
			} else if (!StringUtils.isEmpty(address.getPoBox())) {
				creditAddress.setAddressLineOne("PO BOX " + address.getPoBox());
			} else {
				creditAddress.setAddressLineOne("RR " + address.getRuralNumber());
			}
			
			creditAddress.setAddressLineTwo(address.getMunicipalityName() + " " + address.getProvinceStateCode() + " "
					+ address.getPostalZipCode());
			creditAddress.setCityName(address.getMunicipalityName());
			creditAddress.setProvinceCd(address.getProvinceStateCode());
			creditAddress.setCountryCd(address.getCountryCode());
			creditAddress.setPostalCd(address.getPostalZipCode());
		}
		return creditAddress;
	}

	private static AuditInfo getAuditInfoForUpdCredit(OperationHeader header) {
		AuditInfo auditInfo = new AuditInfo();
		if (header != null) {
			auditInfo.setOriginatorApplicationId(String.valueOf(header.getOriginatorApplicationId()));
			auditInfo.setCorrelationId(header.getSalesTransactionId());
			if (header.getUserProfile() != null) {
				auditInfo.setUserId(String.valueOf(header.getUserProfile().getSalesRepInternalId()));
				auditInfo.setSalesRepresentativeId(String.valueOf(header.getUserProfile().getSalesRepInternalId()));
				auditInfo.setChannelOrganizationId(String.valueOf(header.getUserProfile().getChnlOrgInternalId()));
				if (header.getUserProfile().getSalesRepAssociatedOutletList() != null 
						&& !header.getUserProfile().getSalesRepAssociatedOutletList().isEmpty()
						&& header.getUserProfile().getSalesRepAssociatedOutletList().get(0).getSalesRepAssociatedOutletInternalId() > 0){
					auditInfo.setOutletId(String.valueOf(header.getUserProfile().getSalesRepAssociatedOutletList().get(0).getSalesRepAssociatedOutletInternalId()));
				}
				
			}
		}
		auditInfo.setTimestamp(new Timestamp(System.currentTimeMillis()));
		return auditInfo;
	}

	public static AssessCreditWorthinessAdapterRequest buildAssessCreditWorthinessRequestDO(
			GetCustomerEligibilityCoreRequest request) {
		AssessCreditWorthinessAdapterRequest req = new AssessCreditWorthinessAdapterRequest();
		AssessCreditWorthinessRequest assessCreditWorhinessReq = new AssessCreditWorthinessRequest();
		assessCreditWorhinessReq.setCreditAssessmentTypeCd(CREDIT_ASSESSMENT_TYPE_CD);
		assessCreditWorhinessReq.setCreditAssessmentSubTypeCd(CREDIT_ASSESSMENT_SUBTYPE_CD);
		assessCreditWorhinessReq.setCustomerID(Long.valueOf(request.getCustomerId()));
		assessCreditWorhinessReq.setCommentTxt((request.getCreditProfile() != null && request.getCreditProfile().getCommentTxt() != null)
						? request.getCreditProfile().getCommentTxt() : "");
		if (request.getOperationHeader() != null && request.getOperationHeader().getOriginatorApplicationId() != null) {
			assessCreditWorhinessReq.setApplicationID(request.getOperationHeader().getOriginatorApplicationId().toString());
		}
		if (request.getOperationHeader() != null
				&& request.getOperationHeader().getUserProfile() != null
				&& request.getOperationHeader().getUserProfile().getChnlOrgNumber() != null) {
			assessCreditWorhinessReq.setChannelID(request.getOperationHeader().getUserProfile().getChnlOrgNumber());
		}
		assessCreditWorhinessReq.setLineOfBusiness(LINE_OF_BUSINESS_WIRELINE);// To test downstream errors we can comment this line.
		req.setAssessCreditWorthinessRequest(assessCreditWorhinessReq);
		req.setAuditInfo(getAuditInfoForAssessCredit(request));
		req.setSalesTransactionId(request.getOperationHeader().getSalesTransactionId());
		return req;
	}

	private static com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v7.AuditInfo getAuditInfoForAssessCredit(
			GetCustomerEligibilityCoreRequest request) {
		com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v7.AuditInfo auditInfo = new com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v7.AuditInfo();
		if (request.getOperationHeader() != null) {
			auditInfo.setCorrelationId(request.getOperationHeader().getSalesTransactionId());
			auditInfo.setOriginatorApplicationId(String.valueOf(request.getOperationHeader().getOriginatorApplicationId()));
			if (request.getOperationHeader().getUserProfile() != null) {
				auditInfo.setUserId(String.valueOf(request.getOperationHeader().getUserProfile().getSalesRepInternalId()));
				auditInfo.setSalesRepresentativeId(String.valueOf(request.getOperationHeader().getUserProfile().getSalesRepInternalId()));
				auditInfo.setChannelOrganizationId(String.valueOf(request.getOperationHeader().getUserProfile().getChnlOrgInternalId()));
				if (request.getOperationHeader().getUserProfile().getSalesRepAssociatedOutletList() != null 
						&& !request.getOperationHeader().getUserProfile().getSalesRepAssociatedOutletList().isEmpty()
						&& request.getOperationHeader().getUserProfile().getSalesRepAssociatedOutletList().get(0).getSalesRepAssociatedOutletInternalId() > 0){
					auditInfo.setOutletId(String.valueOf(request.getOperationHeader().getUserProfile().getSalesRepAssociatedOutletList().get(0).getSalesRepAssociatedOutletInternalId()));
				}
			}
		}
		auditInfo.setTimestamp(new Date());
		return auditInfo;
	}

	public static GetCreditEligibilityAdapterRequest buildCreditEligibilityRequestDO(
			GetCustomerEligibilityCoreRequest request) {
		GetCreditEligibilityAdapterRequest req = new GetCreditEligibilityAdapterRequest();
		req.setCustomerId(request.getCustomerId());
		req.setAuditInfo(getAuditInfoGetCreditElig(request));
		req.setSalesTransactionId(request.getOperationHeader().getSalesTransactionId());
		req.setRefreshCache(request.getOperationHeader().isRefreshInd());
		return req;
	}

	private static com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v7.AuditInfo getAuditInfoGetCreditElig(
			GetCustomerEligibilityCoreRequest request) {
		com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v7.AuditInfo auditInfo = new com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v7.AuditInfo();
		if (request.getOperationHeader() != null) {
			auditInfo.setOriginatorApplicationId(String.valueOf(request.getOperationHeader().getOriginatorApplicationId()));
			auditInfo.setCorrelationId(request.getOperationHeader().getSalesTransactionId());
			if (request.getOperationHeader().getUserProfile() != null) {
				auditInfo.setUserId(String.valueOf(request.getOperationHeader().getUserProfile().getSalesRepInternalId()));
				auditInfo.setSalesRepresentativeId(String.valueOf(request.getOperationHeader().getUserProfile().getSalesRepInternalId()));
				auditInfo.setChannelOrganizationId(String.valueOf(request.getOperationHeader().getUserProfile().getChnlOrgInternalId()));
				if (request.getOperationHeader().getUserProfile().getSalesRepAssociatedOutletList() != null 
						&& !request.getOperationHeader().getUserProfile().getSalesRepAssociatedOutletList().isEmpty()
						&& request.getOperationHeader().getUserProfile().getSalesRepAssociatedOutletList().get(0).getSalesRepAssociatedOutletInternalId() > 0){
					auditInfo.setOutletId(String.valueOf(request.getOperationHeader().getUserProfile().getSalesRepAssociatedOutletList().get(0).getSalesRepAssociatedOutletInternalId()));
				}
			}
		}
		auditInfo.setTimestamp(new Date());
		return auditInfo;
	}

	public static GetCreditProfileByCustomerIdAdapterRequest buildGetCreditProfileByCustId(
			GetCustomerEligibilityCoreRequest request) {
		GetCreditProfileByCustomerIdAdapterRequest rq = new GetCreditProfileByCustomerIdAdapterRequest();
		rq.setCustomerId(request.getCustomerId());
		rq.setAuditInfo(getAuditInfoForGetCredProfByCustId(request));
		rq.setSalesTransactionId(request.getOperationHeader().getSalesTransactionId());
		rq.setRefreshCache(request.getOperationHeader().isRefreshInd());
		return rq;
	}

	private static AuditInfo getAuditInfoForGetCredProfByCustId(GetCustomerEligibilityCoreRequest request) {
		AuditInfo auditInfo = new AuditInfo();
		if (request.getOperationHeader() != null) {
			auditInfo.setOriginatorApplicationId(String.valueOf(request.getOperationHeader().getOriginatorApplicationId()));
			auditInfo.setCorrelationId(request.getOperationHeader().getSalesTransactionId());
			if (request.getOperationHeader().getUserProfile() != null) {
				auditInfo.setUserId(String.valueOf(request.getOperationHeader().getUserProfile().getSalesRepInternalId()));
				auditInfo.setSalesRepresentativeId(String.valueOf(request.getOperationHeader().getUserProfile().getSalesRepInternalId()));
				auditInfo.setChannelOrganizationId(String.valueOf(request.getOperationHeader().getUserProfile().getChnlOrgInternalId()));
				if (request.getOperationHeader().getUserProfile().getSalesRepAssociatedOutletList() != null 
						&& !request.getOperationHeader().getUserProfile().getSalesRepAssociatedOutletList().isEmpty()
						&& request.getOperationHeader().getUserProfile().getSalesRepAssociatedOutletList().get(0).getSalesRepAssociatedOutletInternalId() > 0){
					auditInfo.setOutletId(String.valueOf(request.getOperationHeader().getUserProfile().getSalesRepAssociatedOutletList().get(0).getSalesRepAssociatedOutletInternalId()));
				}
			}
		}
		auditInfo.setTimestamp(new Date());
		return auditInfo;
	}
}
