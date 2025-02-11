
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.telus.framework.xml.bind.DateAdapter;
import com.telus.framework.xml.bind.DateTimeAdapter;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommonname_v1.IndividualName;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.ChannelPartnerUserProfileType;
import com.telus.tmi.xmlschema.xsd.product.productoffering.wirelesssubscriberofferinformationtypes_v2.BillCredit;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for Order complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Order">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orderContext">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="orderType" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                   &lt;element name="orderSubType" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *                   &lt;element name="orderVersionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="orderStatusId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="orderStatusChangeDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                   &lt;element name="orderCreationDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                   &lt;element name="salesId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="externalOrderReferenceList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}OrderReference" maxOccurs="10" minOccurs="0"/>
 *                   &lt;element name="orderEmailAddress" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ElectronicContact" minOccurs="0"/>
 *                   &lt;element name="salesRepEmailAddress" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ElectronicContact" minOccurs="0"/>
 *                   &lt;element name="systemIntegrationParameterList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}SystemIntegrationParameterType" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="userProfile" type="{http://xmlschema.tmi.telus.com/xsd/Partner/Partner/ChannelPartnerCommon_v2}ChannelPartnerUserProfileType" minOccurs="0"/>
 *                   &lt;element name="agentProfile" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}AgentProfile" minOccurs="0"/>
 *                   &lt;element name="salesPersonRoleCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="orderCancellationReasonCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="brandCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="originatorApplicationId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="relatedPartyList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}RelatedPartyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="existingBillingAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="existingPhoneNumber" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PhoneNumberType" minOccurs="0"/>
 *         &lt;element name="newBillingAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newPhoneNumberList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PhoneNumberType" maxOccurs="3" minOccurs="0"/>
 *         &lt;element name="selectedProvinceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subscriberProductType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creditLimitProgramIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="selectedCreditProgram" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}CreditProgramDetail" minOccurs="0"/>
 *         &lt;element name="contractTermInMonths" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="contractEffectiveDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="equipment" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}EquipmentSerialNumberSet" minOccurs="0"/>
 *         &lt;element name="simOnlyTransaction" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="deviceType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="hardwarePricing" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}HardwarePricingDetailType" minOccurs="0"/>
 *         &lt;element name="applyHardwarePriceOnBillInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="applyFinanceTaxOnBillInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="applyBibTaxOnBillInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="simPricing" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PriceAmountAndTax" minOccurs="0"/>
 *         &lt;element name="applyNewSimPriceOnBillInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="channelDiscountList" maxOccurs="10" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="discountCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="discountType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="discountAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="penaltyPricingList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PenaltyChargeDetail" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="promotionList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PromotionHeader" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="offer" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="offerHeader" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelessOfferHeader"/>
 *                   &lt;element name="offerReservationStatusCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="hardwarePromotionVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="validationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="offerGetPhoneIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="billCreditList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/WirelessSubscriberOfferInformationTypes_v2}BillCredit" maxOccurs="25" minOccurs="0"/>
 *                   &lt;element name="selectedRewardBillCredit" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}RewardBillCredit" minOccurs="0"/>
 *                   &lt;element name="selectedOfferBillDiscountPlan" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}BillDiscountPlan" minOccurs="0"/>
 *                   &lt;element name="overrideRedemptionMethodID" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *                   &lt;element name="bringItBackProgramInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="sweetener" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="pricePlanCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="serviceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="billCredit" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/WirelessSubscriberOfferInformationTypes_v2}BillCredit" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="pricePlanAddons" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="pricePlanCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="transactionActionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="billChargeThresholdConsentInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="callingCirclePhoneNumberList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}CallingCirclePhoneNumberList" minOccurs="0"/>
 *                   &lt;element name="headOfficeCallingPhoneNumberList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="serviceItemList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ServiceItem" maxOccurs="50" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="prepaidMigrationBalanceTransfer" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="transferToBillingAccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="transferFromBillingAccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="balanceAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="portInIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="portProtectionIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="portRequest" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PortRequest" minOccurs="0"/>
 *         &lt;element name="newSubscriberDetail" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="birthDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                   &lt;element name="voicemailLanguageCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="contactLanguageCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="contactEmailAddress" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ElectronicContact" minOccurs="0"/>
 *                   &lt;element name="businessUser" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}SalesOrderBusinessUserType" minOccurs="0"/>
 *                   &lt;element name="reasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="subscriberAdditionalLineTxt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="subscriberPreference" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}SubscriberPreference" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="serviceSupportFeeItemList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ServiceSupportFeeItemList" minOccurs="0"/>
 *         &lt;element name="depositPaymentList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}DepositInformation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="preauthorizedCreditCard" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}CreditCard" minOccurs="0"/>
 *         &lt;element name="partialTransactionOption" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="fulfillmentMethod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="fulfillmentDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                   &lt;element name="warehouseShipmentIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="warehouseShipmentDetail" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="contact" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonName_v1}IndividualName" minOccurs="0"/>
 *                             &lt;element name="shippingAddress" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Address"/>
 *                             &lt;element name="shippingNotificationEmail" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ElectronicContact" minOccurs="0"/>
 *                             &lt;element name="shippingNotificationContactPhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="shippingNotificationLanguageCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="serviceRequest" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ServiceRequest" minOccurs="0"/>
 *         &lt;element name="parentServiceRequest" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ServiceRequest" minOccurs="0"/>
 *         &lt;element name="businessOrderItem" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="selectedBundle" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}BundleSummary" minOccurs="0"/>
 *                   &lt;element name="wirelessEquipment" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}BusinessWirelessEquipment" minOccurs="0"/>
 *                   &lt;element name="hsia" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}HsiaInformation" minOccurs="0"/>
 *                   &lt;element name="voip" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}VoipInformation" minOccurs="0"/>
 *                   &lt;element name="migrationCreditBalanceTransfer" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="townIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                             &lt;element name="transferFromBillingAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="transferToBillingAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="transferReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="bundleOrderId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="bundleOrderContact" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}BundleOrderContactDetailType" minOccurs="0"/>
 *                   &lt;element name="primaryServiceEditionFamilyTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="telusCreditCardTransaction" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}TelusCreditCardTransaction" minOccurs="0"/>
 *         &lt;element name="eBillRegistration" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="billNotificationMethodCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="billNotificationEmailAddressTxt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="billNotificationSmsNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="selfServeRegistration" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}SelfServeRegistrationType" minOccurs="0"/>
 *         &lt;element name="salesPaymentDetail" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PaymentDetailType" minOccurs="0"/>
 *         &lt;element name="corporateOrderItem" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="corporateEntity" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}CorporateEntity" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="bundleOrderItem" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="bundleQuoteReferenceNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="businessApp" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="businessAppAccount" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}BusinessApplicationAccount" minOccurs="0"/>
 *                   &lt;element name="businessAppList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}BusinessApplication" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="subscriberAssociation" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="associationActionCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="primarySubscriberMobileId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="primarySubscriberPhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="transactionalAccessoryOfferList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}AccessoryOfferDiscount" maxOccurs="20" minOccurs="0"/>
 *         &lt;element name="orderReferenceAssociationList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}OrderReferenceAssociation" maxOccurs="20" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Order", propOrder = {
    "orderContext",
    "relatedPartyList",
    "existingBillingAccountNumber",
    "existingPhoneNumber",
    "newBillingAccountNumber",
    "newPhoneNumberList",
    "selectedProvinceCode",
    "subscriberProductType",
    "creditLimitProgramIndicator",
    "selectedCreditProgram",
    "contractTermInMonths",
    "contractEffectiveDate",
    "equipment",
    "simOnlyTransaction",
    "hardwarePricing",
    "applyHardwarePriceOnBillInd",
    "applyFinanceTaxOnBillInd",
    "applyBibTaxOnBillInd",
    "simPricing",
    "applyNewSimPriceOnBillInd",
    "channelDiscountList",
    "penaltyPricingList",
    "promotionList",
    "offer",
    "sweetener",
    "pricePlanAddons",
    "prepaidMigrationBalanceTransfer",
    "portInIndicator",
    "portProtectionIndicator",
    "portRequest",
    "newSubscriberDetail",
    "serviceSupportFeeItemList",
    "depositPaymentList",
    "preauthorizedCreditCard",
    "partialTransactionOption",
    "serviceRequest",
    "parentServiceRequest",
    "businessOrderItem",
    "telusCreditCardTransaction",
    "eBillRegistration",
    "selfServeRegistration",
    "salesPaymentDetail",
    "corporateOrderItem",
    "bundleOrderItem",
    "businessApp",
    "subscriberAssociation",
    "transactionalAccessoryOfferList",
    "orderReferenceAssociationList"
})
public class Order
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected Order.OrderContext orderContext;
    protected List<RelatedPartyType> relatedPartyList;
    protected String existingBillingAccountNumber;
    protected PhoneNumberType existingPhoneNumber;
    protected String newBillingAccountNumber;
    protected List<PhoneNumberType> newPhoneNumberList;
    protected String selectedProvinceCode;
    protected String subscriberProductType;
    protected Boolean creditLimitProgramIndicator;
    protected CreditProgramDetail selectedCreditProgram;
    protected BigInteger contractTermInMonths;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date contractEffectiveDate;
    protected EquipmentSerialNumberSet equipment;
    protected Order.SimOnlyTransaction simOnlyTransaction;
    protected HardwarePricingDetailType hardwarePricing;
    protected Boolean applyHardwarePriceOnBillInd;
    protected Boolean applyFinanceTaxOnBillInd;
    protected Boolean applyBibTaxOnBillInd;
    protected PriceAmountAndTax simPricing;
    protected Boolean applyNewSimPriceOnBillInd;
    protected List<Order.ChannelDiscountList> channelDiscountList;
    protected List<PenaltyChargeDetail> penaltyPricingList;
    protected List<PromotionHeader> promotionList;
    protected Order.Offer offer;
    protected Order.Sweetener sweetener;
    protected Order.PricePlanAddons pricePlanAddons;
    protected Order.PrepaidMigrationBalanceTransfer prepaidMigrationBalanceTransfer;
    protected Boolean portInIndicator;
    protected Boolean portProtectionIndicator;
    protected PortRequest portRequest;
    protected Order.NewSubscriberDetail newSubscriberDetail;
    protected ServiceSupportFeeItemList serviceSupportFeeItemList;
    protected List<DepositInformation> depositPaymentList;
    protected CreditCard preauthorizedCreditCard;
    protected Order.PartialTransactionOption partialTransactionOption;
    protected ServiceRequest serviceRequest;
    protected ServiceRequest parentServiceRequest;
    protected Order.BusinessOrderItem businessOrderItem;
    protected TelusCreditCardTransaction telusCreditCardTransaction;
    protected Order.EBillRegistration eBillRegistration;
    protected SelfServeRegistrationType selfServeRegistration;
    protected PaymentDetailType salesPaymentDetail;
    protected Order.CorporateOrderItem corporateOrderItem;
    protected Order.BundleOrderItem bundleOrderItem;
    protected Order.BusinessApp businessApp;
    protected Order.SubscriberAssociation subscriberAssociation;
    protected List<AccessoryOfferDiscount> transactionalAccessoryOfferList;
    protected List<OrderReferenceAssociation> orderReferenceAssociationList;

    /**
     * Gets the value of the orderContext property.
     * 
     * @return
     *     possible object is
     *     {@link Order.OrderContext }
     *     
     */
    public Order.OrderContext getOrderContext() {
        return orderContext;
    }

    /**
     * Sets the value of the orderContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link Order.OrderContext }
     *     
     */
    public void setOrderContext(Order.OrderContext value) {
        this.orderContext = value;
    }

    /**
     * Gets the value of the relatedPartyList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relatedPartyList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelatedPartyList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RelatedPartyType }
     * 
     * 
     */
    public List<RelatedPartyType> getRelatedPartyList() {
        if (relatedPartyList == null) {
            relatedPartyList = new ArrayList<RelatedPartyType>();
        }
        return this.relatedPartyList;
    }

    /**
     * Gets the value of the existingBillingAccountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExistingBillingAccountNumber() {
        return existingBillingAccountNumber;
    }

    /**
     * Sets the value of the existingBillingAccountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExistingBillingAccountNumber(String value) {
        this.existingBillingAccountNumber = value;
    }

    /**
     * Gets the value of the existingPhoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link PhoneNumberType }
     *     
     */
    public PhoneNumberType getExistingPhoneNumber() {
        return existingPhoneNumber;
    }

    /**
     * Sets the value of the existingPhoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link PhoneNumberType }
     *     
     */
    public void setExistingPhoneNumber(PhoneNumberType value) {
        this.existingPhoneNumber = value;
    }

    /**
     * Gets the value of the newBillingAccountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewBillingAccountNumber() {
        return newBillingAccountNumber;
    }

    /**
     * Sets the value of the newBillingAccountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewBillingAccountNumber(String value) {
        this.newBillingAccountNumber = value;
    }

    /**
     * Gets the value of the newPhoneNumberList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the newPhoneNumberList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNewPhoneNumberList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PhoneNumberType }
     * 
     * 
     */
    public List<PhoneNumberType> getNewPhoneNumberList() {
        if (newPhoneNumberList == null) {
            newPhoneNumberList = new ArrayList<PhoneNumberType>();
        }
        return this.newPhoneNumberList;
    }

    /**
     * Gets the value of the selectedProvinceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectedProvinceCode() {
        return selectedProvinceCode;
    }

    /**
     * Sets the value of the selectedProvinceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectedProvinceCode(String value) {
        this.selectedProvinceCode = value;
    }

    /**
     * Gets the value of the subscriberProductType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriberProductType() {
        return subscriberProductType;
    }

    /**
     * Sets the value of the subscriberProductType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriberProductType(String value) {
        this.subscriberProductType = value;
    }

    /**
     * Gets the value of the creditLimitProgramIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCreditLimitProgramIndicator() {
        return creditLimitProgramIndicator;
    }

    /**
     * Sets the value of the creditLimitProgramIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCreditLimitProgramIndicator(Boolean value) {
        this.creditLimitProgramIndicator = value;
    }

    /**
     * Gets the value of the selectedCreditProgram property.
     * 
     * @return
     *     possible object is
     *     {@link CreditProgramDetail }
     *     
     */
    public CreditProgramDetail getSelectedCreditProgram() {
        return selectedCreditProgram;
    }

    /**
     * Sets the value of the selectedCreditProgram property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditProgramDetail }
     *     
     */
    public void setSelectedCreditProgram(CreditProgramDetail value) {
        this.selectedCreditProgram = value;
    }

    /**
     * Gets the value of the contractTermInMonths property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getContractTermInMonths() {
        return contractTermInMonths;
    }

    /**
     * Sets the value of the contractTermInMonths property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setContractTermInMonths(BigInteger value) {
        this.contractTermInMonths = value;
    }

    /**
     * Gets the value of the contractEffectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getContractEffectiveDate() {
        return contractEffectiveDate;
    }

    /**
     * Sets the value of the contractEffectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractEffectiveDate(Date value) {
        this.contractEffectiveDate = value;
    }

    /**
     * Gets the value of the equipment property.
     * 
     * @return
     *     possible object is
     *     {@link EquipmentSerialNumberSet }
     *     
     */
    public EquipmentSerialNumberSet getEquipment() {
        return equipment;
    }

    /**
     * Sets the value of the equipment property.
     * 
     * @param value
     *     allowed object is
     *     {@link EquipmentSerialNumberSet }
     *     
     */
    public void setEquipment(EquipmentSerialNumberSet value) {
        this.equipment = value;
    }

    /**
     * Gets the value of the simOnlyTransaction property.
     * 
     * @return
     *     possible object is
     *     {@link Order.SimOnlyTransaction }
     *     
     */
    public Order.SimOnlyTransaction getSimOnlyTransaction() {
        return simOnlyTransaction;
    }

    /**
     * Sets the value of the simOnlyTransaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link Order.SimOnlyTransaction }
     *     
     */
    public void setSimOnlyTransaction(Order.SimOnlyTransaction value) {
        this.simOnlyTransaction = value;
    }

    /**
     * Gets the value of the hardwarePricing property.
     * 
     * @return
     *     possible object is
     *     {@link HardwarePricingDetailType }
     *     
     */
    public HardwarePricingDetailType getHardwarePricing() {
        return hardwarePricing;
    }

    /**
     * Sets the value of the hardwarePricing property.
     * 
     * @param value
     *     allowed object is
     *     {@link HardwarePricingDetailType }
     *     
     */
    public void setHardwarePricing(HardwarePricingDetailType value) {
        this.hardwarePricing = value;
    }

    /**
     * Gets the value of the applyHardwarePriceOnBillInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isApplyHardwarePriceOnBillInd() {
        return applyHardwarePriceOnBillInd;
    }

    /**
     * Sets the value of the applyHardwarePriceOnBillInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setApplyHardwarePriceOnBillInd(Boolean value) {
        this.applyHardwarePriceOnBillInd = value;
    }

    /**
     * Gets the value of the applyFinanceTaxOnBillInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isApplyFinanceTaxOnBillInd() {
        return applyFinanceTaxOnBillInd;
    }

    /**
     * Sets the value of the applyFinanceTaxOnBillInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setApplyFinanceTaxOnBillInd(Boolean value) {
        this.applyFinanceTaxOnBillInd = value;
    }

    /**
     * Gets the value of the applyBibTaxOnBillInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isApplyBibTaxOnBillInd() {
        return applyBibTaxOnBillInd;
    }

    /**
     * Sets the value of the applyBibTaxOnBillInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setApplyBibTaxOnBillInd(Boolean value) {
        this.applyBibTaxOnBillInd = value;
    }

    /**
     * Gets the value of the simPricing property.
     * 
     * @return
     *     possible object is
     *     {@link PriceAmountAndTax }
     *     
     */
    public PriceAmountAndTax getSimPricing() {
        return simPricing;
    }

    /**
     * Sets the value of the simPricing property.
     * 
     * @param value
     *     allowed object is
     *     {@link PriceAmountAndTax }
     *     
     */
    public void setSimPricing(PriceAmountAndTax value) {
        this.simPricing = value;
    }

    /**
     * Gets the value of the applyNewSimPriceOnBillInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isApplyNewSimPriceOnBillInd() {
        return applyNewSimPriceOnBillInd;
    }

    /**
     * Sets the value of the applyNewSimPriceOnBillInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setApplyNewSimPriceOnBillInd(Boolean value) {
        this.applyNewSimPriceOnBillInd = value;
    }

    /**
     * Gets the value of the channelDiscountList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the channelDiscountList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChannelDiscountList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Order.ChannelDiscountList }
     * 
     * 
     */
    public List<Order.ChannelDiscountList> getChannelDiscountList() {
        if (channelDiscountList == null) {
            channelDiscountList = new ArrayList<Order.ChannelDiscountList>();
        }
        return this.channelDiscountList;
    }

    /**
     * Gets the value of the penaltyPricingList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the penaltyPricingList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPenaltyPricingList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PenaltyChargeDetail }
     * 
     * 
     */
    public List<PenaltyChargeDetail> getPenaltyPricingList() {
        if (penaltyPricingList == null) {
            penaltyPricingList = new ArrayList<PenaltyChargeDetail>();
        }
        return this.penaltyPricingList;
    }

    /**
     * Gets the value of the promotionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the promotionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPromotionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PromotionHeader }
     * 
     * 
     */
    public List<PromotionHeader> getPromotionList() {
        if (promotionList == null) {
            promotionList = new ArrayList<PromotionHeader>();
        }
        return this.promotionList;
    }

    /**
     * Gets the value of the offer property.
     * 
     * @return
     *     possible object is
     *     {@link Order.Offer }
     *     
     */
    public Order.Offer getOffer() {
        return offer;
    }

    /**
     * Sets the value of the offer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Order.Offer }
     *     
     */
    public void setOffer(Order.Offer value) {
        this.offer = value;
    }

    /**
     * Gets the value of the sweetener property.
     * 
     * @return
     *     possible object is
     *     {@link Order.Sweetener }
     *     
     */
    public Order.Sweetener getSweetener() {
        return sweetener;
    }

    /**
     * Sets the value of the sweetener property.
     * 
     * @param value
     *     allowed object is
     *     {@link Order.Sweetener }
     *     
     */
    public void setSweetener(Order.Sweetener value) {
        this.sweetener = value;
    }

    /**
     * Gets the value of the pricePlanAddons property.
     * 
     * @return
     *     possible object is
     *     {@link Order.PricePlanAddons }
     *     
     */
    public Order.PricePlanAddons getPricePlanAddons() {
        return pricePlanAddons;
    }

    /**
     * Sets the value of the pricePlanAddons property.
     * 
     * @param value
     *     allowed object is
     *     {@link Order.PricePlanAddons }
     *     
     */
    public void setPricePlanAddons(Order.PricePlanAddons value) {
        this.pricePlanAddons = value;
    }

    /**
     * Gets the value of the prepaidMigrationBalanceTransfer property.
     * 
     * @return
     *     possible object is
     *     {@link Order.PrepaidMigrationBalanceTransfer }
     *     
     */
    public Order.PrepaidMigrationBalanceTransfer getPrepaidMigrationBalanceTransfer() {
        return prepaidMigrationBalanceTransfer;
    }

    /**
     * Sets the value of the prepaidMigrationBalanceTransfer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Order.PrepaidMigrationBalanceTransfer }
     *     
     */
    public void setPrepaidMigrationBalanceTransfer(Order.PrepaidMigrationBalanceTransfer value) {
        this.prepaidMigrationBalanceTransfer = value;
    }

    /**
     * Gets the value of the portInIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPortInIndicator() {
        return portInIndicator;
    }

    /**
     * Sets the value of the portInIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPortInIndicator(Boolean value) {
        this.portInIndicator = value;
    }

    /**
     * Gets the value of the portProtectionIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPortProtectionIndicator() {
        return portProtectionIndicator;
    }

    /**
     * Sets the value of the portProtectionIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPortProtectionIndicator(Boolean value) {
        this.portProtectionIndicator = value;
    }

    /**
     * Gets the value of the portRequest property.
     * 
     * @return
     *     possible object is
     *     {@link PortRequest }
     *     
     */
    public PortRequest getPortRequest() {
        return portRequest;
    }

    /**
     * Sets the value of the portRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link PortRequest }
     *     
     */
    public void setPortRequest(PortRequest value) {
        this.portRequest = value;
    }

    /**
     * Gets the value of the newSubscriberDetail property.
     * 
     * @return
     *     possible object is
     *     {@link Order.NewSubscriberDetail }
     *     
     */
    public Order.NewSubscriberDetail getNewSubscriberDetail() {
        return newSubscriberDetail;
    }

    /**
     * Sets the value of the newSubscriberDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link Order.NewSubscriberDetail }
     *     
     */
    public void setNewSubscriberDetail(Order.NewSubscriberDetail value) {
        this.newSubscriberDetail = value;
    }

    /**
     * Gets the value of the serviceSupportFeeItemList property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceSupportFeeItemList }
     *     
     */
    public ServiceSupportFeeItemList getServiceSupportFeeItemList() {
        return serviceSupportFeeItemList;
    }

    /**
     * Sets the value of the serviceSupportFeeItemList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceSupportFeeItemList }
     *     
     */
    public void setServiceSupportFeeItemList(ServiceSupportFeeItemList value) {
        this.serviceSupportFeeItemList = value;
    }

    /**
     * Gets the value of the depositPaymentList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the depositPaymentList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDepositPaymentList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DepositInformation }
     * 
     * 
     */
    public List<DepositInformation> getDepositPaymentList() {
        if (depositPaymentList == null) {
            depositPaymentList = new ArrayList<DepositInformation>();
        }
        return this.depositPaymentList;
    }

    /**
     * Gets the value of the preauthorizedCreditCard property.
     * 
     * @return
     *     possible object is
     *     {@link CreditCard }
     *     
     */
    public CreditCard getPreauthorizedCreditCard() {
        return preauthorizedCreditCard;
    }

    /**
     * Sets the value of the preauthorizedCreditCard property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCard }
     *     
     */
    public void setPreauthorizedCreditCard(CreditCard value) {
        this.preauthorizedCreditCard = value;
    }

    /**
     * Gets the value of the partialTransactionOption property.
     * 
     * @return
     *     possible object is
     *     {@link Order.PartialTransactionOption }
     *     
     */
    public Order.PartialTransactionOption getPartialTransactionOption() {
        return partialTransactionOption;
    }

    /**
     * Sets the value of the partialTransactionOption property.
     * 
     * @param value
     *     allowed object is
     *     {@link Order.PartialTransactionOption }
     *     
     */
    public void setPartialTransactionOption(Order.PartialTransactionOption value) {
        this.partialTransactionOption = value;
    }

    /**
     * Gets the value of the serviceRequest property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequest }
     *     
     */
    public ServiceRequest getServiceRequest() {
        return serviceRequest;
    }

    /**
     * Sets the value of the serviceRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequest }
     *     
     */
    public void setServiceRequest(ServiceRequest value) {
        this.serviceRequest = value;
    }

    /**
     * Gets the value of the parentServiceRequest property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceRequest }
     *     
     */
    public ServiceRequest getParentServiceRequest() {
        return parentServiceRequest;
    }

    /**
     * Sets the value of the parentServiceRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceRequest }
     *     
     */
    public void setParentServiceRequest(ServiceRequest value) {
        this.parentServiceRequest = value;
    }

    /**
     * Gets the value of the businessOrderItem property.
     * 
     * @return
     *     possible object is
     *     {@link Order.BusinessOrderItem }
     *     
     */
    public Order.BusinessOrderItem getBusinessOrderItem() {
        return businessOrderItem;
    }

    /**
     * Sets the value of the businessOrderItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link Order.BusinessOrderItem }
     *     
     */
    public void setBusinessOrderItem(Order.BusinessOrderItem value) {
        this.businessOrderItem = value;
    }

    /**
     * Gets the value of the telusCreditCardTransaction property.
     * 
     * @return
     *     possible object is
     *     {@link TelusCreditCardTransaction }
     *     
     */
    public TelusCreditCardTransaction getTelusCreditCardTransaction() {
        return telusCreditCardTransaction;
    }

    /**
     * Sets the value of the telusCreditCardTransaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link TelusCreditCardTransaction }
     *     
     */
    public void setTelusCreditCardTransaction(TelusCreditCardTransaction value) {
        this.telusCreditCardTransaction = value;
    }

    /**
     * Gets the value of the eBillRegistration property.
     * 
     * @return
     *     possible object is
     *     {@link Order.EBillRegistration }
     *     
     */
    public Order.EBillRegistration getEBillRegistration() {
        return eBillRegistration;
    }

    /**
     * Sets the value of the eBillRegistration property.
     * 
     * @param value
     *     allowed object is
     *     {@link Order.EBillRegistration }
     *     
     */
    public void setEBillRegistration(Order.EBillRegistration value) {
        this.eBillRegistration = value;
    }

    /**
     * Gets the value of the selfServeRegistration property.
     * 
     * @return
     *     possible object is
     *     {@link SelfServeRegistrationType }
     *     
     */
    public SelfServeRegistrationType getSelfServeRegistration() {
        return selfServeRegistration;
    }

    /**
     * Sets the value of the selfServeRegistration property.
     * 
     * @param value
     *     allowed object is
     *     {@link SelfServeRegistrationType }
     *     
     */
    public void setSelfServeRegistration(SelfServeRegistrationType value) {
        this.selfServeRegistration = value;
    }

    /**
     * Gets the value of the salesPaymentDetail property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentDetailType }
     *     
     */
    public PaymentDetailType getSalesPaymentDetail() {
        return salesPaymentDetail;
    }

    /**
     * Sets the value of the salesPaymentDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentDetailType }
     *     
     */
    public void setSalesPaymentDetail(PaymentDetailType value) {
        this.salesPaymentDetail = value;
    }

    /**
     * Gets the value of the corporateOrderItem property.
     * 
     * @return
     *     possible object is
     *     {@link Order.CorporateOrderItem }
     *     
     */
    public Order.CorporateOrderItem getCorporateOrderItem() {
        return corporateOrderItem;
    }

    /**
     * Sets the value of the corporateOrderItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link Order.CorporateOrderItem }
     *     
     */
    public void setCorporateOrderItem(Order.CorporateOrderItem value) {
        this.corporateOrderItem = value;
    }

    /**
     * Gets the value of the bundleOrderItem property.
     * 
     * @return
     *     possible object is
     *     {@link Order.BundleOrderItem }
     *     
     */
    public Order.BundleOrderItem getBundleOrderItem() {
        return bundleOrderItem;
    }

    /**
     * Sets the value of the bundleOrderItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link Order.BundleOrderItem }
     *     
     */
    public void setBundleOrderItem(Order.BundleOrderItem value) {
        this.bundleOrderItem = value;
    }

    /**
     * Gets the value of the businessApp property.
     * 
     * @return
     *     possible object is
     *     {@link Order.BusinessApp }
     *     
     */
    public Order.BusinessApp getBusinessApp() {
        return businessApp;
    }

    /**
     * Sets the value of the businessApp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Order.BusinessApp }
     *     
     */
    public void setBusinessApp(Order.BusinessApp value) {
        this.businessApp = value;
    }

    /**
     * Gets the value of the subscriberAssociation property.
     * 
     * @return
     *     possible object is
     *     {@link Order.SubscriberAssociation }
     *     
     */
    public Order.SubscriberAssociation getSubscriberAssociation() {
        return subscriberAssociation;
    }

    /**
     * Sets the value of the subscriberAssociation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Order.SubscriberAssociation }
     *     
     */
    public void setSubscriberAssociation(Order.SubscriberAssociation value) {
        this.subscriberAssociation = value;
    }

    /**
     * Gets the value of the transactionalAccessoryOfferList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transactionalAccessoryOfferList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransactionalAccessoryOfferList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccessoryOfferDiscount }
     * 
     * 
     */
    public List<AccessoryOfferDiscount> getTransactionalAccessoryOfferList() {
        if (transactionalAccessoryOfferList == null) {
            transactionalAccessoryOfferList = new ArrayList<AccessoryOfferDiscount>();
        }
        return this.transactionalAccessoryOfferList;
    }

    /**
     * Gets the value of the orderReferenceAssociationList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orderReferenceAssociationList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrderReferenceAssociationList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrderReferenceAssociation }
     * 
     * 
     */
    public List<OrderReferenceAssociation> getOrderReferenceAssociationList() {
        if (orderReferenceAssociationList == null) {
            orderReferenceAssociationList = new ArrayList<OrderReferenceAssociation>();
        }
        return this.orderReferenceAssociationList;
    }

    /**
     * Sets the value of the relatedPartyList property.
     * 
     * @param relatedPartyList
     *     allowed object is
     *     {@link RelatedPartyType }
     *     
     */
    public void setRelatedPartyList(List<RelatedPartyType> relatedPartyList) {
        this.relatedPartyList = relatedPartyList;
    }

    /**
     * Sets the value of the newPhoneNumberList property.
     * 
     * @param newPhoneNumberList
     *     allowed object is
     *     {@link PhoneNumberType }
     *     
     */
    public void setNewPhoneNumberList(List<PhoneNumberType> newPhoneNumberList) {
        this.newPhoneNumberList = newPhoneNumberList;
    }

    /**
     * Sets the value of the channelDiscountList property.
     * 
     * @param channelDiscountList
     *     allowed object is
     *     {@link Order.ChannelDiscountList }
     *     
     */
    public void setChannelDiscountList(List<Order.ChannelDiscountList> channelDiscountList) {
        this.channelDiscountList = channelDiscountList;
    }

    /**
     * Sets the value of the penaltyPricingList property.
     * 
     * @param penaltyPricingList
     *     allowed object is
     *     {@link PenaltyChargeDetail }
     *     
     */
    public void setPenaltyPricingList(List<PenaltyChargeDetail> penaltyPricingList) {
        this.penaltyPricingList = penaltyPricingList;
    }

    /**
     * Sets the value of the promotionList property.
     * 
     * @param promotionList
     *     allowed object is
     *     {@link PromotionHeader }
     *     
     */
    public void setPromotionList(List<PromotionHeader> promotionList) {
        this.promotionList = promotionList;
    }

    /**
     * Sets the value of the depositPaymentList property.
     * 
     * @param depositPaymentList
     *     allowed object is
     *     {@link DepositInformation }
     *     
     */
    public void setDepositPaymentList(List<DepositInformation> depositPaymentList) {
        this.depositPaymentList = depositPaymentList;
    }

    /**
     * Sets the value of the transactionalAccessoryOfferList property.
     * 
     * @param transactionalAccessoryOfferList
     *     allowed object is
     *     {@link AccessoryOfferDiscount }
     *     
     */
    public void setTransactionalAccessoryOfferList(List<AccessoryOfferDiscount> transactionalAccessoryOfferList) {
        this.transactionalAccessoryOfferList = transactionalAccessoryOfferList;
    }

    /**
     * Sets the value of the orderReferenceAssociationList property.
     * 
     * @param orderReferenceAssociationList
     *     allowed object is
     *     {@link OrderReferenceAssociation }
     *     
     */
    public void setOrderReferenceAssociationList(List<OrderReferenceAssociation> orderReferenceAssociationList) {
        this.orderReferenceAssociationList = orderReferenceAssociationList;
    }

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            Order.OrderContext theOrderContext;
            theOrderContext = this.getOrderContext();
            strategy.appendField(locator, this, "orderContext", buffer, theOrderContext);
        }
        {
            List<RelatedPartyType> theRelatedPartyList;
            theRelatedPartyList = (((this.relatedPartyList!= null)&&(!this.relatedPartyList.isEmpty()))?this.getRelatedPartyList():null);
            strategy.appendField(locator, this, "relatedPartyList", buffer, theRelatedPartyList);
        }
        {
            String theExistingBillingAccountNumber;
            theExistingBillingAccountNumber = this.getExistingBillingAccountNumber();
            strategy.appendField(locator, this, "existingBillingAccountNumber", buffer, theExistingBillingAccountNumber);
        }
        {
            PhoneNumberType theExistingPhoneNumber;
            theExistingPhoneNumber = this.getExistingPhoneNumber();
            strategy.appendField(locator, this, "existingPhoneNumber", buffer, theExistingPhoneNumber);
        }
        {
            String theNewBillingAccountNumber;
            theNewBillingAccountNumber = this.getNewBillingAccountNumber();
            strategy.appendField(locator, this, "newBillingAccountNumber", buffer, theNewBillingAccountNumber);
        }
        {
            List<PhoneNumberType> theNewPhoneNumberList;
            theNewPhoneNumberList = (((this.newPhoneNumberList!= null)&&(!this.newPhoneNumberList.isEmpty()))?this.getNewPhoneNumberList():null);
            strategy.appendField(locator, this, "newPhoneNumberList", buffer, theNewPhoneNumberList);
        }
        {
            String theSelectedProvinceCode;
            theSelectedProvinceCode = this.getSelectedProvinceCode();
            strategy.appendField(locator, this, "selectedProvinceCode", buffer, theSelectedProvinceCode);
        }
        {
            String theSubscriberProductType;
            theSubscriberProductType = this.getSubscriberProductType();
            strategy.appendField(locator, this, "subscriberProductType", buffer, theSubscriberProductType);
        }
        {
            Boolean theCreditLimitProgramIndicator;
            theCreditLimitProgramIndicator = this.isCreditLimitProgramIndicator();
            strategy.appendField(locator, this, "creditLimitProgramIndicator", buffer, theCreditLimitProgramIndicator);
        }
        {
            CreditProgramDetail theSelectedCreditProgram;
            theSelectedCreditProgram = this.getSelectedCreditProgram();
            strategy.appendField(locator, this, "selectedCreditProgram", buffer, theSelectedCreditProgram);
        }
        {
            BigInteger theContractTermInMonths;
            theContractTermInMonths = this.getContractTermInMonths();
            strategy.appendField(locator, this, "contractTermInMonths", buffer, theContractTermInMonths);
        }
        {
            Date theContractEffectiveDate;
            theContractEffectiveDate = this.getContractEffectiveDate();
            strategy.appendField(locator, this, "contractEffectiveDate", buffer, theContractEffectiveDate);
        }
        {
            EquipmentSerialNumberSet theEquipment;
            theEquipment = this.getEquipment();
            strategy.appendField(locator, this, "equipment", buffer, theEquipment);
        }
        {
            Order.SimOnlyTransaction theSimOnlyTransaction;
            theSimOnlyTransaction = this.getSimOnlyTransaction();
            strategy.appendField(locator, this, "simOnlyTransaction", buffer, theSimOnlyTransaction);
        }
        {
            HardwarePricingDetailType theHardwarePricing;
            theHardwarePricing = this.getHardwarePricing();
            strategy.appendField(locator, this, "hardwarePricing", buffer, theHardwarePricing);
        }
        {
            Boolean theApplyHardwarePriceOnBillInd;
            theApplyHardwarePriceOnBillInd = this.isApplyHardwarePriceOnBillInd();
            strategy.appendField(locator, this, "applyHardwarePriceOnBillInd", buffer, theApplyHardwarePriceOnBillInd);
        }
        {
            Boolean theApplyFinanceTaxOnBillInd;
            theApplyFinanceTaxOnBillInd = this.isApplyFinanceTaxOnBillInd();
            strategy.appendField(locator, this, "applyFinanceTaxOnBillInd", buffer, theApplyFinanceTaxOnBillInd);
        }
        {
            Boolean theApplyBibTaxOnBillInd;
            theApplyBibTaxOnBillInd = this.isApplyBibTaxOnBillInd();
            strategy.appendField(locator, this, "applyBibTaxOnBillInd", buffer, theApplyBibTaxOnBillInd);
        }
        {
            PriceAmountAndTax theSimPricing;
            theSimPricing = this.getSimPricing();
            strategy.appendField(locator, this, "simPricing", buffer, theSimPricing);
        }
        {
            Boolean theApplyNewSimPriceOnBillInd;
            theApplyNewSimPriceOnBillInd = this.isApplyNewSimPriceOnBillInd();
            strategy.appendField(locator, this, "applyNewSimPriceOnBillInd", buffer, theApplyNewSimPriceOnBillInd);
        }
        {
            List<Order.ChannelDiscountList> theChannelDiscountList;
            theChannelDiscountList = (((this.channelDiscountList!= null)&&(!this.channelDiscountList.isEmpty()))?this.getChannelDiscountList():null);
            strategy.appendField(locator, this, "channelDiscountList", buffer, theChannelDiscountList);
        }
        {
            List<PenaltyChargeDetail> thePenaltyPricingList;
            thePenaltyPricingList = (((this.penaltyPricingList!= null)&&(!this.penaltyPricingList.isEmpty()))?this.getPenaltyPricingList():null);
            strategy.appendField(locator, this, "penaltyPricingList", buffer, thePenaltyPricingList);
        }
        {
            List<PromotionHeader> thePromotionList;
            thePromotionList = (((this.promotionList!= null)&&(!this.promotionList.isEmpty()))?this.getPromotionList():null);
            strategy.appendField(locator, this, "promotionList", buffer, thePromotionList);
        }
        {
            Order.Offer theOffer;
            theOffer = this.getOffer();
            strategy.appendField(locator, this, "offer", buffer, theOffer);
        }
        {
            Order.Sweetener theSweetener;
            theSweetener = this.getSweetener();
            strategy.appendField(locator, this, "sweetener", buffer, theSweetener);
        }
        {
            Order.PricePlanAddons thePricePlanAddons;
            thePricePlanAddons = this.getPricePlanAddons();
            strategy.appendField(locator, this, "pricePlanAddons", buffer, thePricePlanAddons);
        }
        {
            Order.PrepaidMigrationBalanceTransfer thePrepaidMigrationBalanceTransfer;
            thePrepaidMigrationBalanceTransfer = this.getPrepaidMigrationBalanceTransfer();
            strategy.appendField(locator, this, "prepaidMigrationBalanceTransfer", buffer, thePrepaidMigrationBalanceTransfer);
        }
        {
            Boolean thePortInIndicator;
            thePortInIndicator = this.isPortInIndicator();
            strategy.appendField(locator, this, "portInIndicator", buffer, thePortInIndicator);
        }
        {
            Boolean thePortProtectionIndicator;
            thePortProtectionIndicator = this.isPortProtectionIndicator();
            strategy.appendField(locator, this, "portProtectionIndicator", buffer, thePortProtectionIndicator);
        }
        {
            PortRequest thePortRequest;
            thePortRequest = this.getPortRequest();
            strategy.appendField(locator, this, "portRequest", buffer, thePortRequest);
        }
        {
            Order.NewSubscriberDetail theNewSubscriberDetail;
            theNewSubscriberDetail = this.getNewSubscriberDetail();
            strategy.appendField(locator, this, "newSubscriberDetail", buffer, theNewSubscriberDetail);
        }
        {
            ServiceSupportFeeItemList theServiceSupportFeeItemList;
            theServiceSupportFeeItemList = this.getServiceSupportFeeItemList();
            strategy.appendField(locator, this, "serviceSupportFeeItemList", buffer, theServiceSupportFeeItemList);
        }
        {
            List<DepositInformation> theDepositPaymentList;
            theDepositPaymentList = (((this.depositPaymentList!= null)&&(!this.depositPaymentList.isEmpty()))?this.getDepositPaymentList():null);
            strategy.appendField(locator, this, "depositPaymentList", buffer, theDepositPaymentList);
        }
        {
            CreditCard thePreauthorizedCreditCard;
            thePreauthorizedCreditCard = this.getPreauthorizedCreditCard();
            strategy.appendField(locator, this, "preauthorizedCreditCard", buffer, thePreauthorizedCreditCard);
        }
        {
            Order.PartialTransactionOption thePartialTransactionOption;
            thePartialTransactionOption = this.getPartialTransactionOption();
            strategy.appendField(locator, this, "partialTransactionOption", buffer, thePartialTransactionOption);
        }
        {
            ServiceRequest theServiceRequest;
            theServiceRequest = this.getServiceRequest();
            strategy.appendField(locator, this, "serviceRequest", buffer, theServiceRequest);
        }
        {
            ServiceRequest theParentServiceRequest;
            theParentServiceRequest = this.getParentServiceRequest();
            strategy.appendField(locator, this, "parentServiceRequest", buffer, theParentServiceRequest);
        }
        {
            Order.BusinessOrderItem theBusinessOrderItem;
            theBusinessOrderItem = this.getBusinessOrderItem();
            strategy.appendField(locator, this, "businessOrderItem", buffer, theBusinessOrderItem);
        }
        {
            TelusCreditCardTransaction theTelusCreditCardTransaction;
            theTelusCreditCardTransaction = this.getTelusCreditCardTransaction();
            strategy.appendField(locator, this, "telusCreditCardTransaction", buffer, theTelusCreditCardTransaction);
        }
        {
            Order.EBillRegistration theEBillRegistration;
            theEBillRegistration = this.getEBillRegistration();
            strategy.appendField(locator, this, "eBillRegistration", buffer, theEBillRegistration);
        }
        {
            SelfServeRegistrationType theSelfServeRegistration;
            theSelfServeRegistration = this.getSelfServeRegistration();
            strategy.appendField(locator, this, "selfServeRegistration", buffer, theSelfServeRegistration);
        }
        {
            PaymentDetailType theSalesPaymentDetail;
            theSalesPaymentDetail = this.getSalesPaymentDetail();
            strategy.appendField(locator, this, "salesPaymentDetail", buffer, theSalesPaymentDetail);
        }
        {
            Order.CorporateOrderItem theCorporateOrderItem;
            theCorporateOrderItem = this.getCorporateOrderItem();
            strategy.appendField(locator, this, "corporateOrderItem", buffer, theCorporateOrderItem);
        }
        {
            Order.BundleOrderItem theBundleOrderItem;
            theBundleOrderItem = this.getBundleOrderItem();
            strategy.appendField(locator, this, "bundleOrderItem", buffer, theBundleOrderItem);
        }
        {
            Order.BusinessApp theBusinessApp;
            theBusinessApp = this.getBusinessApp();
            strategy.appendField(locator, this, "businessApp", buffer, theBusinessApp);
        }
        {
            Order.SubscriberAssociation theSubscriberAssociation;
            theSubscriberAssociation = this.getSubscriberAssociation();
            strategy.appendField(locator, this, "subscriberAssociation", buffer, theSubscriberAssociation);
        }
        {
            List<AccessoryOfferDiscount> theTransactionalAccessoryOfferList;
            theTransactionalAccessoryOfferList = (((this.transactionalAccessoryOfferList!= null)&&(!this.transactionalAccessoryOfferList.isEmpty()))?this.getTransactionalAccessoryOfferList():null);
            strategy.appendField(locator, this, "transactionalAccessoryOfferList", buffer, theTransactionalAccessoryOfferList);
        }
        {
            List<OrderReferenceAssociation> theOrderReferenceAssociationList;
            theOrderReferenceAssociationList = (((this.orderReferenceAssociationList!= null)&&(!this.orderReferenceAssociationList.isEmpty()))?this.getOrderReferenceAssociationList():null);
            strategy.appendField(locator, this, "orderReferenceAssociationList", buffer, theOrderReferenceAssociationList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Order)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Order that = ((Order) object);
        {
            Order.OrderContext lhsOrderContext;
            lhsOrderContext = this.getOrderContext();
            Order.OrderContext rhsOrderContext;
            rhsOrderContext = that.getOrderContext();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "orderContext", lhsOrderContext), LocatorUtils.property(thatLocator, "orderContext", rhsOrderContext), lhsOrderContext, rhsOrderContext)) {
                return false;
            }
        }
        {
            List<RelatedPartyType> lhsRelatedPartyList;
            lhsRelatedPartyList = (((this.relatedPartyList!= null)&&(!this.relatedPartyList.isEmpty()))?this.getRelatedPartyList():null);
            List<RelatedPartyType> rhsRelatedPartyList;
            rhsRelatedPartyList = (((that.relatedPartyList!= null)&&(!that.relatedPartyList.isEmpty()))?that.getRelatedPartyList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "relatedPartyList", lhsRelatedPartyList), LocatorUtils.property(thatLocator, "relatedPartyList", rhsRelatedPartyList), lhsRelatedPartyList, rhsRelatedPartyList)) {
                return false;
            }
        }
        {
            String lhsExistingBillingAccountNumber;
            lhsExistingBillingAccountNumber = this.getExistingBillingAccountNumber();
            String rhsExistingBillingAccountNumber;
            rhsExistingBillingAccountNumber = that.getExistingBillingAccountNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "existingBillingAccountNumber", lhsExistingBillingAccountNumber), LocatorUtils.property(thatLocator, "existingBillingAccountNumber", rhsExistingBillingAccountNumber), lhsExistingBillingAccountNumber, rhsExistingBillingAccountNumber)) {
                return false;
            }
        }
        {
            PhoneNumberType lhsExistingPhoneNumber;
            lhsExistingPhoneNumber = this.getExistingPhoneNumber();
            PhoneNumberType rhsExistingPhoneNumber;
            rhsExistingPhoneNumber = that.getExistingPhoneNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "existingPhoneNumber", lhsExistingPhoneNumber), LocatorUtils.property(thatLocator, "existingPhoneNumber", rhsExistingPhoneNumber), lhsExistingPhoneNumber, rhsExistingPhoneNumber)) {
                return false;
            }
        }
        {
            String lhsNewBillingAccountNumber;
            lhsNewBillingAccountNumber = this.getNewBillingAccountNumber();
            String rhsNewBillingAccountNumber;
            rhsNewBillingAccountNumber = that.getNewBillingAccountNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "newBillingAccountNumber", lhsNewBillingAccountNumber), LocatorUtils.property(thatLocator, "newBillingAccountNumber", rhsNewBillingAccountNumber), lhsNewBillingAccountNumber, rhsNewBillingAccountNumber)) {
                return false;
            }
        }
        {
            List<PhoneNumberType> lhsNewPhoneNumberList;
            lhsNewPhoneNumberList = (((this.newPhoneNumberList!= null)&&(!this.newPhoneNumberList.isEmpty()))?this.getNewPhoneNumberList():null);
            List<PhoneNumberType> rhsNewPhoneNumberList;
            rhsNewPhoneNumberList = (((that.newPhoneNumberList!= null)&&(!that.newPhoneNumberList.isEmpty()))?that.getNewPhoneNumberList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "newPhoneNumberList", lhsNewPhoneNumberList), LocatorUtils.property(thatLocator, "newPhoneNumberList", rhsNewPhoneNumberList), lhsNewPhoneNumberList, rhsNewPhoneNumberList)) {
                return false;
            }
        }
        {
            String lhsSelectedProvinceCode;
            lhsSelectedProvinceCode = this.getSelectedProvinceCode();
            String rhsSelectedProvinceCode;
            rhsSelectedProvinceCode = that.getSelectedProvinceCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "selectedProvinceCode", lhsSelectedProvinceCode), LocatorUtils.property(thatLocator, "selectedProvinceCode", rhsSelectedProvinceCode), lhsSelectedProvinceCode, rhsSelectedProvinceCode)) {
                return false;
            }
        }
        {
            String lhsSubscriberProductType;
            lhsSubscriberProductType = this.getSubscriberProductType();
            String rhsSubscriberProductType;
            rhsSubscriberProductType = that.getSubscriberProductType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subscriberProductType", lhsSubscriberProductType), LocatorUtils.property(thatLocator, "subscriberProductType", rhsSubscriberProductType), lhsSubscriberProductType, rhsSubscriberProductType)) {
                return false;
            }
        }
        {
            Boolean lhsCreditLimitProgramIndicator;
            lhsCreditLimitProgramIndicator = this.isCreditLimitProgramIndicator();
            Boolean rhsCreditLimitProgramIndicator;
            rhsCreditLimitProgramIndicator = that.isCreditLimitProgramIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditLimitProgramIndicator", lhsCreditLimitProgramIndicator), LocatorUtils.property(thatLocator, "creditLimitProgramIndicator", rhsCreditLimitProgramIndicator), lhsCreditLimitProgramIndicator, rhsCreditLimitProgramIndicator)) {
                return false;
            }
        }
        {
            CreditProgramDetail lhsSelectedCreditProgram;
            lhsSelectedCreditProgram = this.getSelectedCreditProgram();
            CreditProgramDetail rhsSelectedCreditProgram;
            rhsSelectedCreditProgram = that.getSelectedCreditProgram();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "selectedCreditProgram", lhsSelectedCreditProgram), LocatorUtils.property(thatLocator, "selectedCreditProgram", rhsSelectedCreditProgram), lhsSelectedCreditProgram, rhsSelectedCreditProgram)) {
                return false;
            }
        }
        {
            BigInteger lhsContractTermInMonths;
            lhsContractTermInMonths = this.getContractTermInMonths();
            BigInteger rhsContractTermInMonths;
            rhsContractTermInMonths = that.getContractTermInMonths();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "contractTermInMonths", lhsContractTermInMonths), LocatorUtils.property(thatLocator, "contractTermInMonths", rhsContractTermInMonths), lhsContractTermInMonths, rhsContractTermInMonths)) {
                return false;
            }
        }
        {
            Date lhsContractEffectiveDate;
            lhsContractEffectiveDate = this.getContractEffectiveDate();
            Date rhsContractEffectiveDate;
            rhsContractEffectiveDate = that.getContractEffectiveDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "contractEffectiveDate", lhsContractEffectiveDate), LocatorUtils.property(thatLocator, "contractEffectiveDate", rhsContractEffectiveDate), lhsContractEffectiveDate, rhsContractEffectiveDate)) {
                return false;
            }
        }
        {
            EquipmentSerialNumberSet lhsEquipment;
            lhsEquipment = this.getEquipment();
            EquipmentSerialNumberSet rhsEquipment;
            rhsEquipment = that.getEquipment();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "equipment", lhsEquipment), LocatorUtils.property(thatLocator, "equipment", rhsEquipment), lhsEquipment, rhsEquipment)) {
                return false;
            }
        }
        {
            Order.SimOnlyTransaction lhsSimOnlyTransaction;
            lhsSimOnlyTransaction = this.getSimOnlyTransaction();
            Order.SimOnlyTransaction rhsSimOnlyTransaction;
            rhsSimOnlyTransaction = that.getSimOnlyTransaction();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "simOnlyTransaction", lhsSimOnlyTransaction), LocatorUtils.property(thatLocator, "simOnlyTransaction", rhsSimOnlyTransaction), lhsSimOnlyTransaction, rhsSimOnlyTransaction)) {
                return false;
            }
        }
        {
            HardwarePricingDetailType lhsHardwarePricing;
            lhsHardwarePricing = this.getHardwarePricing();
            HardwarePricingDetailType rhsHardwarePricing;
            rhsHardwarePricing = that.getHardwarePricing();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hardwarePricing", lhsHardwarePricing), LocatorUtils.property(thatLocator, "hardwarePricing", rhsHardwarePricing), lhsHardwarePricing, rhsHardwarePricing)) {
                return false;
            }
        }
        {
            Boolean lhsApplyHardwarePriceOnBillInd;
            lhsApplyHardwarePriceOnBillInd = this.isApplyHardwarePriceOnBillInd();
            Boolean rhsApplyHardwarePriceOnBillInd;
            rhsApplyHardwarePriceOnBillInd = that.isApplyHardwarePriceOnBillInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "applyHardwarePriceOnBillInd", lhsApplyHardwarePriceOnBillInd), LocatorUtils.property(thatLocator, "applyHardwarePriceOnBillInd", rhsApplyHardwarePriceOnBillInd), lhsApplyHardwarePriceOnBillInd, rhsApplyHardwarePriceOnBillInd)) {
                return false;
            }
        }
        {
            Boolean lhsApplyFinanceTaxOnBillInd;
            lhsApplyFinanceTaxOnBillInd = this.isApplyFinanceTaxOnBillInd();
            Boolean rhsApplyFinanceTaxOnBillInd;
            rhsApplyFinanceTaxOnBillInd = that.isApplyFinanceTaxOnBillInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "applyFinanceTaxOnBillInd", lhsApplyFinanceTaxOnBillInd), LocatorUtils.property(thatLocator, "applyFinanceTaxOnBillInd", rhsApplyFinanceTaxOnBillInd), lhsApplyFinanceTaxOnBillInd, rhsApplyFinanceTaxOnBillInd)) {
                return false;
            }
        }
        {
            Boolean lhsApplyBibTaxOnBillInd;
            lhsApplyBibTaxOnBillInd = this.isApplyBibTaxOnBillInd();
            Boolean rhsApplyBibTaxOnBillInd;
            rhsApplyBibTaxOnBillInd = that.isApplyBibTaxOnBillInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "applyBibTaxOnBillInd", lhsApplyBibTaxOnBillInd), LocatorUtils.property(thatLocator, "applyBibTaxOnBillInd", rhsApplyBibTaxOnBillInd), lhsApplyBibTaxOnBillInd, rhsApplyBibTaxOnBillInd)) {
                return false;
            }
        }
        {
            PriceAmountAndTax lhsSimPricing;
            lhsSimPricing = this.getSimPricing();
            PriceAmountAndTax rhsSimPricing;
            rhsSimPricing = that.getSimPricing();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "simPricing", lhsSimPricing), LocatorUtils.property(thatLocator, "simPricing", rhsSimPricing), lhsSimPricing, rhsSimPricing)) {
                return false;
            }
        }
        {
            Boolean lhsApplyNewSimPriceOnBillInd;
            lhsApplyNewSimPriceOnBillInd = this.isApplyNewSimPriceOnBillInd();
            Boolean rhsApplyNewSimPriceOnBillInd;
            rhsApplyNewSimPriceOnBillInd = that.isApplyNewSimPriceOnBillInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "applyNewSimPriceOnBillInd", lhsApplyNewSimPriceOnBillInd), LocatorUtils.property(thatLocator, "applyNewSimPriceOnBillInd", rhsApplyNewSimPriceOnBillInd), lhsApplyNewSimPriceOnBillInd, rhsApplyNewSimPriceOnBillInd)) {
                return false;
            }
        }
        {
            List<Order.ChannelDiscountList> lhsChannelDiscountList;
            lhsChannelDiscountList = (((this.channelDiscountList!= null)&&(!this.channelDiscountList.isEmpty()))?this.getChannelDiscountList():null);
            List<Order.ChannelDiscountList> rhsChannelDiscountList;
            rhsChannelDiscountList = (((that.channelDiscountList!= null)&&(!that.channelDiscountList.isEmpty()))?that.getChannelDiscountList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "channelDiscountList", lhsChannelDiscountList), LocatorUtils.property(thatLocator, "channelDiscountList", rhsChannelDiscountList), lhsChannelDiscountList, rhsChannelDiscountList)) {
                return false;
            }
        }
        {
            List<PenaltyChargeDetail> lhsPenaltyPricingList;
            lhsPenaltyPricingList = (((this.penaltyPricingList!= null)&&(!this.penaltyPricingList.isEmpty()))?this.getPenaltyPricingList():null);
            List<PenaltyChargeDetail> rhsPenaltyPricingList;
            rhsPenaltyPricingList = (((that.penaltyPricingList!= null)&&(!that.penaltyPricingList.isEmpty()))?that.getPenaltyPricingList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "penaltyPricingList", lhsPenaltyPricingList), LocatorUtils.property(thatLocator, "penaltyPricingList", rhsPenaltyPricingList), lhsPenaltyPricingList, rhsPenaltyPricingList)) {
                return false;
            }
        }
        {
            List<PromotionHeader> lhsPromotionList;
            lhsPromotionList = (((this.promotionList!= null)&&(!this.promotionList.isEmpty()))?this.getPromotionList():null);
            List<PromotionHeader> rhsPromotionList;
            rhsPromotionList = (((that.promotionList!= null)&&(!that.promotionList.isEmpty()))?that.getPromotionList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "promotionList", lhsPromotionList), LocatorUtils.property(thatLocator, "promotionList", rhsPromotionList), lhsPromotionList, rhsPromotionList)) {
                return false;
            }
        }
        {
            Order.Offer lhsOffer;
            lhsOffer = this.getOffer();
            Order.Offer rhsOffer;
            rhsOffer = that.getOffer();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offer", lhsOffer), LocatorUtils.property(thatLocator, "offer", rhsOffer), lhsOffer, rhsOffer)) {
                return false;
            }
        }
        {
            Order.Sweetener lhsSweetener;
            lhsSweetener = this.getSweetener();
            Order.Sweetener rhsSweetener;
            rhsSweetener = that.getSweetener();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sweetener", lhsSweetener), LocatorUtils.property(thatLocator, "sweetener", rhsSweetener), lhsSweetener, rhsSweetener)) {
                return false;
            }
        }
        {
            Order.PricePlanAddons lhsPricePlanAddons;
            lhsPricePlanAddons = this.getPricePlanAddons();
            Order.PricePlanAddons rhsPricePlanAddons;
            rhsPricePlanAddons = that.getPricePlanAddons();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pricePlanAddons", lhsPricePlanAddons), LocatorUtils.property(thatLocator, "pricePlanAddons", rhsPricePlanAddons), lhsPricePlanAddons, rhsPricePlanAddons)) {
                return false;
            }
        }
        {
            Order.PrepaidMigrationBalanceTransfer lhsPrepaidMigrationBalanceTransfer;
            lhsPrepaidMigrationBalanceTransfer = this.getPrepaidMigrationBalanceTransfer();
            Order.PrepaidMigrationBalanceTransfer rhsPrepaidMigrationBalanceTransfer;
            rhsPrepaidMigrationBalanceTransfer = that.getPrepaidMigrationBalanceTransfer();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "prepaidMigrationBalanceTransfer", lhsPrepaidMigrationBalanceTransfer), LocatorUtils.property(thatLocator, "prepaidMigrationBalanceTransfer", rhsPrepaidMigrationBalanceTransfer), lhsPrepaidMigrationBalanceTransfer, rhsPrepaidMigrationBalanceTransfer)) {
                return false;
            }
        }
        {
            Boolean lhsPortInIndicator;
            lhsPortInIndicator = this.isPortInIndicator();
            Boolean rhsPortInIndicator;
            rhsPortInIndicator = that.isPortInIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "portInIndicator", lhsPortInIndicator), LocatorUtils.property(thatLocator, "portInIndicator", rhsPortInIndicator), lhsPortInIndicator, rhsPortInIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsPortProtectionIndicator;
            lhsPortProtectionIndicator = this.isPortProtectionIndicator();
            Boolean rhsPortProtectionIndicator;
            rhsPortProtectionIndicator = that.isPortProtectionIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "portProtectionIndicator", lhsPortProtectionIndicator), LocatorUtils.property(thatLocator, "portProtectionIndicator", rhsPortProtectionIndicator), lhsPortProtectionIndicator, rhsPortProtectionIndicator)) {
                return false;
            }
        }
        {
            PortRequest lhsPortRequest;
            lhsPortRequest = this.getPortRequest();
            PortRequest rhsPortRequest;
            rhsPortRequest = that.getPortRequest();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "portRequest", lhsPortRequest), LocatorUtils.property(thatLocator, "portRequest", rhsPortRequest), lhsPortRequest, rhsPortRequest)) {
                return false;
            }
        }
        {
            Order.NewSubscriberDetail lhsNewSubscriberDetail;
            lhsNewSubscriberDetail = this.getNewSubscriberDetail();
            Order.NewSubscriberDetail rhsNewSubscriberDetail;
            rhsNewSubscriberDetail = that.getNewSubscriberDetail();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "newSubscriberDetail", lhsNewSubscriberDetail), LocatorUtils.property(thatLocator, "newSubscriberDetail", rhsNewSubscriberDetail), lhsNewSubscriberDetail, rhsNewSubscriberDetail)) {
                return false;
            }
        }
        {
            ServiceSupportFeeItemList lhsServiceSupportFeeItemList;
            lhsServiceSupportFeeItemList = this.getServiceSupportFeeItemList();
            ServiceSupportFeeItemList rhsServiceSupportFeeItemList;
            rhsServiceSupportFeeItemList = that.getServiceSupportFeeItemList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceSupportFeeItemList", lhsServiceSupportFeeItemList), LocatorUtils.property(thatLocator, "serviceSupportFeeItemList", rhsServiceSupportFeeItemList), lhsServiceSupportFeeItemList, rhsServiceSupportFeeItemList)) {
                return false;
            }
        }
        {
            List<DepositInformation> lhsDepositPaymentList;
            lhsDepositPaymentList = (((this.depositPaymentList!= null)&&(!this.depositPaymentList.isEmpty()))?this.getDepositPaymentList():null);
            List<DepositInformation> rhsDepositPaymentList;
            rhsDepositPaymentList = (((that.depositPaymentList!= null)&&(!that.depositPaymentList.isEmpty()))?that.getDepositPaymentList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "depositPaymentList", lhsDepositPaymentList), LocatorUtils.property(thatLocator, "depositPaymentList", rhsDepositPaymentList), lhsDepositPaymentList, rhsDepositPaymentList)) {
                return false;
            }
        }
        {
            CreditCard lhsPreauthorizedCreditCard;
            lhsPreauthorizedCreditCard = this.getPreauthorizedCreditCard();
            CreditCard rhsPreauthorizedCreditCard;
            rhsPreauthorizedCreditCard = that.getPreauthorizedCreditCard();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "preauthorizedCreditCard", lhsPreauthorizedCreditCard), LocatorUtils.property(thatLocator, "preauthorizedCreditCard", rhsPreauthorizedCreditCard), lhsPreauthorizedCreditCard, rhsPreauthorizedCreditCard)) {
                return false;
            }
        }
        {
            Order.PartialTransactionOption lhsPartialTransactionOption;
            lhsPartialTransactionOption = this.getPartialTransactionOption();
            Order.PartialTransactionOption rhsPartialTransactionOption;
            rhsPartialTransactionOption = that.getPartialTransactionOption();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "partialTransactionOption", lhsPartialTransactionOption), LocatorUtils.property(thatLocator, "partialTransactionOption", rhsPartialTransactionOption), lhsPartialTransactionOption, rhsPartialTransactionOption)) {
                return false;
            }
        }
        {
            ServiceRequest lhsServiceRequest;
            lhsServiceRequest = this.getServiceRequest();
            ServiceRequest rhsServiceRequest;
            rhsServiceRequest = that.getServiceRequest();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceRequest", lhsServiceRequest), LocatorUtils.property(thatLocator, "serviceRequest", rhsServiceRequest), lhsServiceRequest, rhsServiceRequest)) {
                return false;
            }
        }
        {
            ServiceRequest lhsParentServiceRequest;
            lhsParentServiceRequest = this.getParentServiceRequest();
            ServiceRequest rhsParentServiceRequest;
            rhsParentServiceRequest = that.getParentServiceRequest();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "parentServiceRequest", lhsParentServiceRequest), LocatorUtils.property(thatLocator, "parentServiceRequest", rhsParentServiceRequest), lhsParentServiceRequest, rhsParentServiceRequest)) {
                return false;
            }
        }
        {
            Order.BusinessOrderItem lhsBusinessOrderItem;
            lhsBusinessOrderItem = this.getBusinessOrderItem();
            Order.BusinessOrderItem rhsBusinessOrderItem;
            rhsBusinessOrderItem = that.getBusinessOrderItem();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "businessOrderItem", lhsBusinessOrderItem), LocatorUtils.property(thatLocator, "businessOrderItem", rhsBusinessOrderItem), lhsBusinessOrderItem, rhsBusinessOrderItem)) {
                return false;
            }
        }
        {
            TelusCreditCardTransaction lhsTelusCreditCardTransaction;
            lhsTelusCreditCardTransaction = this.getTelusCreditCardTransaction();
            TelusCreditCardTransaction rhsTelusCreditCardTransaction;
            rhsTelusCreditCardTransaction = that.getTelusCreditCardTransaction();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "telusCreditCardTransaction", lhsTelusCreditCardTransaction), LocatorUtils.property(thatLocator, "telusCreditCardTransaction", rhsTelusCreditCardTransaction), lhsTelusCreditCardTransaction, rhsTelusCreditCardTransaction)) {
                return false;
            }
        }
        {
            Order.EBillRegistration lhsEBillRegistration;
            lhsEBillRegistration = this.getEBillRegistration();
            Order.EBillRegistration rhsEBillRegistration;
            rhsEBillRegistration = that.getEBillRegistration();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "eBillRegistration", lhsEBillRegistration), LocatorUtils.property(thatLocator, "eBillRegistration", rhsEBillRegistration), lhsEBillRegistration, rhsEBillRegistration)) {
                return false;
            }
        }
        {
            SelfServeRegistrationType lhsSelfServeRegistration;
            lhsSelfServeRegistration = this.getSelfServeRegistration();
            SelfServeRegistrationType rhsSelfServeRegistration;
            rhsSelfServeRegistration = that.getSelfServeRegistration();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "selfServeRegistration", lhsSelfServeRegistration), LocatorUtils.property(thatLocator, "selfServeRegistration", rhsSelfServeRegistration), lhsSelfServeRegistration, rhsSelfServeRegistration)) {
                return false;
            }
        }
        {
            PaymentDetailType lhsSalesPaymentDetail;
            lhsSalesPaymentDetail = this.getSalesPaymentDetail();
            PaymentDetailType rhsSalesPaymentDetail;
            rhsSalesPaymentDetail = that.getSalesPaymentDetail();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "salesPaymentDetail", lhsSalesPaymentDetail), LocatorUtils.property(thatLocator, "salesPaymentDetail", rhsSalesPaymentDetail), lhsSalesPaymentDetail, rhsSalesPaymentDetail)) {
                return false;
            }
        }
        {
            Order.CorporateOrderItem lhsCorporateOrderItem;
            lhsCorporateOrderItem = this.getCorporateOrderItem();
            Order.CorporateOrderItem rhsCorporateOrderItem;
            rhsCorporateOrderItem = that.getCorporateOrderItem();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "corporateOrderItem", lhsCorporateOrderItem), LocatorUtils.property(thatLocator, "corporateOrderItem", rhsCorporateOrderItem), lhsCorporateOrderItem, rhsCorporateOrderItem)) {
                return false;
            }
        }
        {
            Order.BundleOrderItem lhsBundleOrderItem;
            lhsBundleOrderItem = this.getBundleOrderItem();
            Order.BundleOrderItem rhsBundleOrderItem;
            rhsBundleOrderItem = that.getBundleOrderItem();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "bundleOrderItem", lhsBundleOrderItem), LocatorUtils.property(thatLocator, "bundleOrderItem", rhsBundleOrderItem), lhsBundleOrderItem, rhsBundleOrderItem)) {
                return false;
            }
        }
        {
            Order.BusinessApp lhsBusinessApp;
            lhsBusinessApp = this.getBusinessApp();
            Order.BusinessApp rhsBusinessApp;
            rhsBusinessApp = that.getBusinessApp();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "businessApp", lhsBusinessApp), LocatorUtils.property(thatLocator, "businessApp", rhsBusinessApp), lhsBusinessApp, rhsBusinessApp)) {
                return false;
            }
        }
        {
            Order.SubscriberAssociation lhsSubscriberAssociation;
            lhsSubscriberAssociation = this.getSubscriberAssociation();
            Order.SubscriberAssociation rhsSubscriberAssociation;
            rhsSubscriberAssociation = that.getSubscriberAssociation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subscriberAssociation", lhsSubscriberAssociation), LocatorUtils.property(thatLocator, "subscriberAssociation", rhsSubscriberAssociation), lhsSubscriberAssociation, rhsSubscriberAssociation)) {
                return false;
            }
        }
        {
            List<AccessoryOfferDiscount> lhsTransactionalAccessoryOfferList;
            lhsTransactionalAccessoryOfferList = (((this.transactionalAccessoryOfferList!= null)&&(!this.transactionalAccessoryOfferList.isEmpty()))?this.getTransactionalAccessoryOfferList():null);
            List<AccessoryOfferDiscount> rhsTransactionalAccessoryOfferList;
            rhsTransactionalAccessoryOfferList = (((that.transactionalAccessoryOfferList!= null)&&(!that.transactionalAccessoryOfferList.isEmpty()))?that.getTransactionalAccessoryOfferList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "transactionalAccessoryOfferList", lhsTransactionalAccessoryOfferList), LocatorUtils.property(thatLocator, "transactionalAccessoryOfferList", rhsTransactionalAccessoryOfferList), lhsTransactionalAccessoryOfferList, rhsTransactionalAccessoryOfferList)) {
                return false;
            }
        }
        {
            List<OrderReferenceAssociation> lhsOrderReferenceAssociationList;
            lhsOrderReferenceAssociationList = (((this.orderReferenceAssociationList!= null)&&(!this.orderReferenceAssociationList.isEmpty()))?this.getOrderReferenceAssociationList():null);
            List<OrderReferenceAssociation> rhsOrderReferenceAssociationList;
            rhsOrderReferenceAssociationList = (((that.orderReferenceAssociationList!= null)&&(!that.orderReferenceAssociationList.isEmpty()))?that.getOrderReferenceAssociationList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "orderReferenceAssociationList", lhsOrderReferenceAssociationList), LocatorUtils.property(thatLocator, "orderReferenceAssociationList", rhsOrderReferenceAssociationList), lhsOrderReferenceAssociationList, rhsOrderReferenceAssociationList)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="bundleQuoteReferenceNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "bundleQuoteReferenceNum"
    })
    public static class BundleOrderItem
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        protected String bundleQuoteReferenceNum;

        /**
         * Gets the value of the bundleQuoteReferenceNum property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBundleQuoteReferenceNum() {
            return bundleQuoteReferenceNum;
        }

        /**
         * Sets the value of the bundleQuoteReferenceNum property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBundleQuoteReferenceNum(String value) {
            this.bundleQuoteReferenceNum = value;
        }

        public String toString() {
            final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
            final StringBuilder buffer = new StringBuilder();
            append(null, buffer, strategy);
            return buffer.toString();
        }

        public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            strategy.appendStart(locator, this, buffer);
            appendFields(locator, buffer, strategy);
            strategy.appendEnd(locator, this, buffer);
            return buffer;
        }

        public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            {
                String theBundleQuoteReferenceNum;
                theBundleQuoteReferenceNum = this.getBundleQuoteReferenceNum();
                strategy.appendField(locator, this, "bundleQuoteReferenceNum", buffer, theBundleQuoteReferenceNum);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof Order.BundleOrderItem)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final Order.BundleOrderItem that = ((Order.BundleOrderItem) object);
            {
                String lhsBundleQuoteReferenceNum;
                lhsBundleQuoteReferenceNum = this.getBundleQuoteReferenceNum();
                String rhsBundleQuoteReferenceNum;
                rhsBundleQuoteReferenceNum = that.getBundleQuoteReferenceNum();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "bundleQuoteReferenceNum", lhsBundleQuoteReferenceNum), LocatorUtils.property(thatLocator, "bundleQuoteReferenceNum", rhsBundleQuoteReferenceNum), lhsBundleQuoteReferenceNum, rhsBundleQuoteReferenceNum)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object object) {
            final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
            return equals(null, null, object, strategy);
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="businessAppAccount" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}BusinessApplicationAccount" minOccurs="0"/>
     *         &lt;element name="businessAppList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}BusinessApplication" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "businessAppAccount",
        "businessAppList"
    })
    public static class BusinessApp
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        protected BusinessApplicationAccount businessAppAccount;
        protected List<BusinessApplication> businessAppList;

        /**
         * Gets the value of the businessAppAccount property.
         * 
         * @return
         *     possible object is
         *     {@link BusinessApplicationAccount }
         *     
         */
        public BusinessApplicationAccount getBusinessAppAccount() {
            return businessAppAccount;
        }

        /**
         * Sets the value of the businessAppAccount property.
         * 
         * @param value
         *     allowed object is
         *     {@link BusinessApplicationAccount }
         *     
         */
        public void setBusinessAppAccount(BusinessApplicationAccount value) {
            this.businessAppAccount = value;
        }

        /**
         * Gets the value of the businessAppList property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the businessAppList property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getBusinessAppList().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BusinessApplication }
         * 
         * 
         */
        public List<BusinessApplication> getBusinessAppList() {
            if (businessAppList == null) {
                businessAppList = new ArrayList<BusinessApplication>();
            }
            return this.businessAppList;
        }

        /**
         * Sets the value of the businessAppList property.
         * 
         * @param businessAppList
         *     allowed object is
         *     {@link BusinessApplication }
         *     
         */
        public void setBusinessAppList(List<BusinessApplication> businessAppList) {
            this.businessAppList = businessAppList;
        }

        public String toString() {
            final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
            final StringBuilder buffer = new StringBuilder();
            append(null, buffer, strategy);
            return buffer.toString();
        }

        public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            strategy.appendStart(locator, this, buffer);
            appendFields(locator, buffer, strategy);
            strategy.appendEnd(locator, this, buffer);
            return buffer;
        }

        public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            {
                BusinessApplicationAccount theBusinessAppAccount;
                theBusinessAppAccount = this.getBusinessAppAccount();
                strategy.appendField(locator, this, "businessAppAccount", buffer, theBusinessAppAccount);
            }
            {
                List<BusinessApplication> theBusinessAppList;
                theBusinessAppList = (((this.businessAppList!= null)&&(!this.businessAppList.isEmpty()))?this.getBusinessAppList():null);
                strategy.appendField(locator, this, "businessAppList", buffer, theBusinessAppList);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof Order.BusinessApp)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final Order.BusinessApp that = ((Order.BusinessApp) object);
            {
                BusinessApplicationAccount lhsBusinessAppAccount;
                lhsBusinessAppAccount = this.getBusinessAppAccount();
                BusinessApplicationAccount rhsBusinessAppAccount;
                rhsBusinessAppAccount = that.getBusinessAppAccount();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "businessAppAccount", lhsBusinessAppAccount), LocatorUtils.property(thatLocator, "businessAppAccount", rhsBusinessAppAccount), lhsBusinessAppAccount, rhsBusinessAppAccount)) {
                    return false;
                }
            }
            {
                List<BusinessApplication> lhsBusinessAppList;
                lhsBusinessAppList = (((this.businessAppList!= null)&&(!this.businessAppList.isEmpty()))?this.getBusinessAppList():null);
                List<BusinessApplication> rhsBusinessAppList;
                rhsBusinessAppList = (((that.businessAppList!= null)&&(!that.businessAppList.isEmpty()))?that.getBusinessAppList():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "businessAppList", lhsBusinessAppList), LocatorUtils.property(thatLocator, "businessAppList", rhsBusinessAppList), lhsBusinessAppList, rhsBusinessAppList)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object object) {
            final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
            return equals(null, null, object, strategy);
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="selectedBundle" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}BundleSummary" minOccurs="0"/>
     *         &lt;element name="wirelessEquipment" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}BusinessWirelessEquipment" minOccurs="0"/>
     *         &lt;element name="hsia" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}HsiaInformation" minOccurs="0"/>
     *         &lt;element name="voip" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}VoipInformation" minOccurs="0"/>
     *         &lt;element name="migrationCreditBalanceTransfer" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="townIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *                   &lt;element name="transferFromBillingAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="transferToBillingAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="transferReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="bundleOrderId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="bundleOrderContact" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}BundleOrderContactDetailType" minOccurs="0"/>
     *         &lt;element name="primaryServiceEditionFamilyTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "selectedBundle",
        "wirelessEquipment",
        "hsia",
        "voip",
        "migrationCreditBalanceTransfer",
        "bundleOrderId",
        "bundleOrderContact",
        "primaryServiceEditionFamilyTypeCode"
    })
    public static class BusinessOrderItem
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        protected BundleSummary selectedBundle;
        protected BusinessWirelessEquipment wirelessEquipment;
        protected HsiaInformation hsia;
        protected VoipInformation voip;
        protected Order.BusinessOrderItem.MigrationCreditBalanceTransfer migrationCreditBalanceTransfer;
        protected String bundleOrderId;
        protected BundleOrderContactDetailType bundleOrderContact;
        protected String primaryServiceEditionFamilyTypeCode;

        /**
         * Gets the value of the selectedBundle property.
         * 
         * @return
         *     possible object is
         *     {@link BundleSummary }
         *     
         */
        public BundleSummary getSelectedBundle() {
            return selectedBundle;
        }

        /**
         * Sets the value of the selectedBundle property.
         * 
         * @param value
         *     allowed object is
         *     {@link BundleSummary }
         *     
         */
        public void setSelectedBundle(BundleSummary value) {
            this.selectedBundle = value;
        }

        /**
         * Gets the value of the wirelessEquipment property.
         * 
         * @return
         *     possible object is
         *     {@link BusinessWirelessEquipment }
         *     
         */
        public BusinessWirelessEquipment getWirelessEquipment() {
            return wirelessEquipment;
        }

        /**
         * Sets the value of the wirelessEquipment property.
         * 
         * @param value
         *     allowed object is
         *     {@link BusinessWirelessEquipment }
         *     
         */
        public void setWirelessEquipment(BusinessWirelessEquipment value) {
            this.wirelessEquipment = value;
        }

        /**
         * Gets the value of the hsia property.
         * 
         * @return
         *     possible object is
         *     {@link HsiaInformation }
         *     
         */
        public HsiaInformation getHsia() {
            return hsia;
        }

        /**
         * Sets the value of the hsia property.
         * 
         * @param value
         *     allowed object is
         *     {@link HsiaInformation }
         *     
         */
        public void setHsia(HsiaInformation value) {
            this.hsia = value;
        }

        /**
         * Gets the value of the voip property.
         * 
         * @return
         *     possible object is
         *     {@link VoipInformation }
         *     
         */
        public VoipInformation getVoip() {
            return voip;
        }

        /**
         * Sets the value of the voip property.
         * 
         * @param value
         *     allowed object is
         *     {@link VoipInformation }
         *     
         */
        public void setVoip(VoipInformation value) {
            this.voip = value;
        }

        /**
         * Gets the value of the migrationCreditBalanceTransfer property.
         * 
         * @return
         *     possible object is
         *     {@link Order.BusinessOrderItem.MigrationCreditBalanceTransfer }
         *     
         */
        public Order.BusinessOrderItem.MigrationCreditBalanceTransfer getMigrationCreditBalanceTransfer() {
            return migrationCreditBalanceTransfer;
        }

        /**
         * Sets the value of the migrationCreditBalanceTransfer property.
         * 
         * @param value
         *     allowed object is
         *     {@link Order.BusinessOrderItem.MigrationCreditBalanceTransfer }
         *     
         */
        public void setMigrationCreditBalanceTransfer(Order.BusinessOrderItem.MigrationCreditBalanceTransfer value) {
            this.migrationCreditBalanceTransfer = value;
        }

        /**
         * Gets the value of the bundleOrderId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBundleOrderId() {
            return bundleOrderId;
        }

        /**
         * Sets the value of the bundleOrderId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBundleOrderId(String value) {
            this.bundleOrderId = value;
        }

        /**
         * Gets the value of the bundleOrderContact property.
         * 
         * @return
         *     possible object is
         *     {@link BundleOrderContactDetailType }
         *     
         */
        public BundleOrderContactDetailType getBundleOrderContact() {
            return bundleOrderContact;
        }

        /**
         * Sets the value of the bundleOrderContact property.
         * 
         * @param value
         *     allowed object is
         *     {@link BundleOrderContactDetailType }
         *     
         */
        public void setBundleOrderContact(BundleOrderContactDetailType value) {
            this.bundleOrderContact = value;
        }

        /**
         * Gets the value of the primaryServiceEditionFamilyTypeCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPrimaryServiceEditionFamilyTypeCode() {
            return primaryServiceEditionFamilyTypeCode;
        }

        /**
         * Sets the value of the primaryServiceEditionFamilyTypeCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPrimaryServiceEditionFamilyTypeCode(String value) {
            this.primaryServiceEditionFamilyTypeCode = value;
        }

        public String toString() {
            final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
            final StringBuilder buffer = new StringBuilder();
            append(null, buffer, strategy);
            return buffer.toString();
        }

        public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            strategy.appendStart(locator, this, buffer);
            appendFields(locator, buffer, strategy);
            strategy.appendEnd(locator, this, buffer);
            return buffer;
        }

        public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            {
                BundleSummary theSelectedBundle;
                theSelectedBundle = this.getSelectedBundle();
                strategy.appendField(locator, this, "selectedBundle", buffer, theSelectedBundle);
            }
            {
                BusinessWirelessEquipment theWirelessEquipment;
                theWirelessEquipment = this.getWirelessEquipment();
                strategy.appendField(locator, this, "wirelessEquipment", buffer, theWirelessEquipment);
            }
            {
                HsiaInformation theHsia;
                theHsia = this.getHsia();
                strategy.appendField(locator, this, "hsia", buffer, theHsia);
            }
            {
                VoipInformation theVoip;
                theVoip = this.getVoip();
                strategy.appendField(locator, this, "voip", buffer, theVoip);
            }
            {
                Order.BusinessOrderItem.MigrationCreditBalanceTransfer theMigrationCreditBalanceTransfer;
                theMigrationCreditBalanceTransfer = this.getMigrationCreditBalanceTransfer();
                strategy.appendField(locator, this, "migrationCreditBalanceTransfer", buffer, theMigrationCreditBalanceTransfer);
            }
            {
                String theBundleOrderId;
                theBundleOrderId = this.getBundleOrderId();
                strategy.appendField(locator, this, "bundleOrderId", buffer, theBundleOrderId);
            }
            {
                BundleOrderContactDetailType theBundleOrderContact;
                theBundleOrderContact = this.getBundleOrderContact();
                strategy.appendField(locator, this, "bundleOrderContact", buffer, theBundleOrderContact);
            }
            {
                String thePrimaryServiceEditionFamilyTypeCode;
                thePrimaryServiceEditionFamilyTypeCode = this.getPrimaryServiceEditionFamilyTypeCode();
                strategy.appendField(locator, this, "primaryServiceEditionFamilyTypeCode", buffer, thePrimaryServiceEditionFamilyTypeCode);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof Order.BusinessOrderItem)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final Order.BusinessOrderItem that = ((Order.BusinessOrderItem) object);
            {
                BundleSummary lhsSelectedBundle;
                lhsSelectedBundle = this.getSelectedBundle();
                BundleSummary rhsSelectedBundle;
                rhsSelectedBundle = that.getSelectedBundle();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "selectedBundle", lhsSelectedBundle), LocatorUtils.property(thatLocator, "selectedBundle", rhsSelectedBundle), lhsSelectedBundle, rhsSelectedBundle)) {
                    return false;
                }
            }
            {
                BusinessWirelessEquipment lhsWirelessEquipment;
                lhsWirelessEquipment = this.getWirelessEquipment();
                BusinessWirelessEquipment rhsWirelessEquipment;
                rhsWirelessEquipment = that.getWirelessEquipment();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "wirelessEquipment", lhsWirelessEquipment), LocatorUtils.property(thatLocator, "wirelessEquipment", rhsWirelessEquipment), lhsWirelessEquipment, rhsWirelessEquipment)) {
                    return false;
                }
            }
            {
                HsiaInformation lhsHsia;
                lhsHsia = this.getHsia();
                HsiaInformation rhsHsia;
                rhsHsia = that.getHsia();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "hsia", lhsHsia), LocatorUtils.property(thatLocator, "hsia", rhsHsia), lhsHsia, rhsHsia)) {
                    return false;
                }
            }
            {
                VoipInformation lhsVoip;
                lhsVoip = this.getVoip();
                VoipInformation rhsVoip;
                rhsVoip = that.getVoip();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "voip", lhsVoip), LocatorUtils.property(thatLocator, "voip", rhsVoip), lhsVoip, rhsVoip)) {
                    return false;
                }
            }
            {
                Order.BusinessOrderItem.MigrationCreditBalanceTransfer lhsMigrationCreditBalanceTransfer;
                lhsMigrationCreditBalanceTransfer = this.getMigrationCreditBalanceTransfer();
                Order.BusinessOrderItem.MigrationCreditBalanceTransfer rhsMigrationCreditBalanceTransfer;
                rhsMigrationCreditBalanceTransfer = that.getMigrationCreditBalanceTransfer();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "migrationCreditBalanceTransfer", lhsMigrationCreditBalanceTransfer), LocatorUtils.property(thatLocator, "migrationCreditBalanceTransfer", rhsMigrationCreditBalanceTransfer), lhsMigrationCreditBalanceTransfer, rhsMigrationCreditBalanceTransfer)) {
                    return false;
                }
            }
            {
                String lhsBundleOrderId;
                lhsBundleOrderId = this.getBundleOrderId();
                String rhsBundleOrderId;
                rhsBundleOrderId = that.getBundleOrderId();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "bundleOrderId", lhsBundleOrderId), LocatorUtils.property(thatLocator, "bundleOrderId", rhsBundleOrderId), lhsBundleOrderId, rhsBundleOrderId)) {
                    return false;
                }
            }
            {
                BundleOrderContactDetailType lhsBundleOrderContact;
                lhsBundleOrderContact = this.getBundleOrderContact();
                BundleOrderContactDetailType rhsBundleOrderContact;
                rhsBundleOrderContact = that.getBundleOrderContact();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "bundleOrderContact", lhsBundleOrderContact), LocatorUtils.property(thatLocator, "bundleOrderContact", rhsBundleOrderContact), lhsBundleOrderContact, rhsBundleOrderContact)) {
                    return false;
                }
            }
            {
                String lhsPrimaryServiceEditionFamilyTypeCode;
                lhsPrimaryServiceEditionFamilyTypeCode = this.getPrimaryServiceEditionFamilyTypeCode();
                String rhsPrimaryServiceEditionFamilyTypeCode;
                rhsPrimaryServiceEditionFamilyTypeCode = that.getPrimaryServiceEditionFamilyTypeCode();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "primaryServiceEditionFamilyTypeCode", lhsPrimaryServiceEditionFamilyTypeCode), LocatorUtils.property(thatLocator, "primaryServiceEditionFamilyTypeCode", rhsPrimaryServiceEditionFamilyTypeCode), lhsPrimaryServiceEditionFamilyTypeCode, rhsPrimaryServiceEditionFamilyTypeCode)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object object) {
            final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
            return equals(null, null, object, strategy);
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="townIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
         *         &lt;element name="transferFromBillingAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="transferToBillingAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="transferReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "townIndicator",
            "transferFromBillingAccountNumber",
            "transferToBillingAccountNumber",
            "transferReasonCode"
        })
        public static class MigrationCreditBalanceTransfer
            implements Serializable, Equals, ToString
        {

            private final static long serialVersionUID = 2L;
            protected boolean townIndicator;
            @XmlElement(required = true)
            protected String transferFromBillingAccountNumber;
            @XmlElement(required = true)
            protected String transferToBillingAccountNumber;
            protected String transferReasonCode;

            /**
             * Gets the value of the townIndicator property.
             * 
             */
            public boolean isTownIndicator() {
                return townIndicator;
            }

            /**
             * Sets the value of the townIndicator property.
             * 
             */
            public void setTownIndicator(boolean value) {
                this.townIndicator = value;
            }

            /**
             * Gets the value of the transferFromBillingAccountNumber property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTransferFromBillingAccountNumber() {
                return transferFromBillingAccountNumber;
            }

            /**
             * Sets the value of the transferFromBillingAccountNumber property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTransferFromBillingAccountNumber(String value) {
                this.transferFromBillingAccountNumber = value;
            }

            /**
             * Gets the value of the transferToBillingAccountNumber property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTransferToBillingAccountNumber() {
                return transferToBillingAccountNumber;
            }

            /**
             * Sets the value of the transferToBillingAccountNumber property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTransferToBillingAccountNumber(String value) {
                this.transferToBillingAccountNumber = value;
            }

            /**
             * Gets the value of the transferReasonCode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTransferReasonCode() {
                return transferReasonCode;
            }

            /**
             * Sets the value of the transferReasonCode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTransferReasonCode(String value) {
                this.transferReasonCode = value;
            }

            public String toString() {
                final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
                final StringBuilder buffer = new StringBuilder();
                append(null, buffer, strategy);
                return buffer.toString();
            }

            public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
                strategy.appendStart(locator, this, buffer);
                appendFields(locator, buffer, strategy);
                strategy.appendEnd(locator, this, buffer);
                return buffer;
            }

            public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
                {
                    boolean theTownIndicator;
                    theTownIndicator = (true?this.isTownIndicator():false);
                    strategy.appendField(locator, this, "townIndicator", buffer, theTownIndicator);
                }
                {
                    String theTransferFromBillingAccountNumber;
                    theTransferFromBillingAccountNumber = this.getTransferFromBillingAccountNumber();
                    strategy.appendField(locator, this, "transferFromBillingAccountNumber", buffer, theTransferFromBillingAccountNumber);
                }
                {
                    String theTransferToBillingAccountNumber;
                    theTransferToBillingAccountNumber = this.getTransferToBillingAccountNumber();
                    strategy.appendField(locator, this, "transferToBillingAccountNumber", buffer, theTransferToBillingAccountNumber);
                }
                {
                    String theTransferReasonCode;
                    theTransferReasonCode = this.getTransferReasonCode();
                    strategy.appendField(locator, this, "transferReasonCode", buffer, theTransferReasonCode);
                }
                return buffer;
            }

            public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
                if (!(object instanceof Order.BusinessOrderItem.MigrationCreditBalanceTransfer)) {
                    return false;
                }
                if (this == object) {
                    return true;
                }
                final Order.BusinessOrderItem.MigrationCreditBalanceTransfer that = ((Order.BusinessOrderItem.MigrationCreditBalanceTransfer) object);
                {
                    boolean lhsTownIndicator;
                    lhsTownIndicator = (true?this.isTownIndicator():false);
                    boolean rhsTownIndicator;
                    rhsTownIndicator = (true?that.isTownIndicator():false);
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "townIndicator", lhsTownIndicator), LocatorUtils.property(thatLocator, "townIndicator", rhsTownIndicator), lhsTownIndicator, rhsTownIndicator)) {
                        return false;
                    }
                }
                {
                    String lhsTransferFromBillingAccountNumber;
                    lhsTransferFromBillingAccountNumber = this.getTransferFromBillingAccountNumber();
                    String rhsTransferFromBillingAccountNumber;
                    rhsTransferFromBillingAccountNumber = that.getTransferFromBillingAccountNumber();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "transferFromBillingAccountNumber", lhsTransferFromBillingAccountNumber), LocatorUtils.property(thatLocator, "transferFromBillingAccountNumber", rhsTransferFromBillingAccountNumber), lhsTransferFromBillingAccountNumber, rhsTransferFromBillingAccountNumber)) {
                        return false;
                    }
                }
                {
                    String lhsTransferToBillingAccountNumber;
                    lhsTransferToBillingAccountNumber = this.getTransferToBillingAccountNumber();
                    String rhsTransferToBillingAccountNumber;
                    rhsTransferToBillingAccountNumber = that.getTransferToBillingAccountNumber();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "transferToBillingAccountNumber", lhsTransferToBillingAccountNumber), LocatorUtils.property(thatLocator, "transferToBillingAccountNumber", rhsTransferToBillingAccountNumber), lhsTransferToBillingAccountNumber, rhsTransferToBillingAccountNumber)) {
                        return false;
                    }
                }
                {
                    String lhsTransferReasonCode;
                    lhsTransferReasonCode = this.getTransferReasonCode();
                    String rhsTransferReasonCode;
                    rhsTransferReasonCode = that.getTransferReasonCode();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "transferReasonCode", lhsTransferReasonCode), LocatorUtils.property(thatLocator, "transferReasonCode", rhsTransferReasonCode), lhsTransferReasonCode, rhsTransferReasonCode)) {
                        return false;
                    }
                }
                return true;
            }

            public boolean equals(Object object) {
                final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
                return equals(null, null, object, strategy);
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="discountCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="discountType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="discountAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "discountCode",
        "discountType",
        "discountAmount"
    })
    public static class ChannelDiscountList
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        @XmlElement(required = true)
        protected String discountCode;
        protected String discountType;
        protected Double discountAmount;

        /**
         * Gets the value of the discountCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDiscountCode() {
            return discountCode;
        }

        /**
         * Sets the value of the discountCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDiscountCode(String value) {
            this.discountCode = value;
        }

        /**
         * Gets the value of the discountType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDiscountType() {
            return discountType;
        }

        /**
         * Sets the value of the discountType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDiscountType(String value) {
            this.discountType = value;
        }

        /**
         * Gets the value of the discountAmount property.
         * 
         * @return
         *     possible object is
         *     {@link Double }
         *     
         */
        public Double getDiscountAmount() {
            return discountAmount;
        }

        /**
         * Sets the value of the discountAmount property.
         * 
         * @param value
         *     allowed object is
         *     {@link Double }
         *     
         */
        public void setDiscountAmount(Double value) {
            this.discountAmount = value;
        }

        public String toString() {
            final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
            final StringBuilder buffer = new StringBuilder();
            append(null, buffer, strategy);
            return buffer.toString();
        }

        public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            strategy.appendStart(locator, this, buffer);
            appendFields(locator, buffer, strategy);
            strategy.appendEnd(locator, this, buffer);
            return buffer;
        }

        public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            {
                String theDiscountCode;
                theDiscountCode = this.getDiscountCode();
                strategy.appendField(locator, this, "discountCode", buffer, theDiscountCode);
            }
            {
                String theDiscountType;
                theDiscountType = this.getDiscountType();
                strategy.appendField(locator, this, "discountType", buffer, theDiscountType);
            }
            {
                Double theDiscountAmount;
                theDiscountAmount = this.getDiscountAmount();
                strategy.appendField(locator, this, "discountAmount", buffer, theDiscountAmount);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof Order.ChannelDiscountList)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final Order.ChannelDiscountList that = ((Order.ChannelDiscountList) object);
            {
                String lhsDiscountCode;
                lhsDiscountCode = this.getDiscountCode();
                String rhsDiscountCode;
                rhsDiscountCode = that.getDiscountCode();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "discountCode", lhsDiscountCode), LocatorUtils.property(thatLocator, "discountCode", rhsDiscountCode), lhsDiscountCode, rhsDiscountCode)) {
                    return false;
                }
            }
            {
                String lhsDiscountType;
                lhsDiscountType = this.getDiscountType();
                String rhsDiscountType;
                rhsDiscountType = that.getDiscountType();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "discountType", lhsDiscountType), LocatorUtils.property(thatLocator, "discountType", rhsDiscountType), lhsDiscountType, rhsDiscountType)) {
                    return false;
                }
            }
            {
                Double lhsDiscountAmount;
                lhsDiscountAmount = this.getDiscountAmount();
                Double rhsDiscountAmount;
                rhsDiscountAmount = that.getDiscountAmount();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "discountAmount", lhsDiscountAmount), LocatorUtils.property(thatLocator, "discountAmount", rhsDiscountAmount), lhsDiscountAmount, rhsDiscountAmount)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object object) {
            final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
            return equals(null, null, object, strategy);
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="corporateEntity" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}CorporateEntity" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "corporateEntity"
    })
    public static class CorporateOrderItem
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        protected CorporateEntity corporateEntity;

        /**
         * Gets the value of the corporateEntity property.
         * 
         * @return
         *     possible object is
         *     {@link CorporateEntity }
         *     
         */
        public CorporateEntity getCorporateEntity() {
            return corporateEntity;
        }

        /**
         * Sets the value of the corporateEntity property.
         * 
         * @param value
         *     allowed object is
         *     {@link CorporateEntity }
         *     
         */
        public void setCorporateEntity(CorporateEntity value) {
            this.corporateEntity = value;
        }

        public String toString() {
            final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
            final StringBuilder buffer = new StringBuilder();
            append(null, buffer, strategy);
            return buffer.toString();
        }

        public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            strategy.appendStart(locator, this, buffer);
            appendFields(locator, buffer, strategy);
            strategy.appendEnd(locator, this, buffer);
            return buffer;
        }

        public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            {
                CorporateEntity theCorporateEntity;
                theCorporateEntity = this.getCorporateEntity();
                strategy.appendField(locator, this, "corporateEntity", buffer, theCorporateEntity);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof Order.CorporateOrderItem)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final Order.CorporateOrderItem that = ((Order.CorporateOrderItem) object);
            {
                CorporateEntity lhsCorporateEntity;
                lhsCorporateEntity = this.getCorporateEntity();
                CorporateEntity rhsCorporateEntity;
                rhsCorporateEntity = that.getCorporateEntity();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "corporateEntity", lhsCorporateEntity), LocatorUtils.property(thatLocator, "corporateEntity", rhsCorporateEntity), lhsCorporateEntity, rhsCorporateEntity)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object object) {
            final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
            return equals(null, null, object, strategy);
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="billNotificationMethodCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="billNotificationEmailAddressTxt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="billNotificationSmsNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "billNotificationMethodCd",
        "billNotificationEmailAddressTxt",
        "billNotificationSmsNum"
    })
    public static class EBillRegistration
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        @XmlElement(required = true)
        protected String billNotificationMethodCd;
        protected String billNotificationEmailAddressTxt;
        protected String billNotificationSmsNum;

        /**
         * Gets the value of the billNotificationMethodCd property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBillNotificationMethodCd() {
            return billNotificationMethodCd;
        }

        /**
         * Sets the value of the billNotificationMethodCd property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBillNotificationMethodCd(String value) {
            this.billNotificationMethodCd = value;
        }

        /**
         * Gets the value of the billNotificationEmailAddressTxt property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBillNotificationEmailAddressTxt() {
            return billNotificationEmailAddressTxt;
        }

        /**
         * Sets the value of the billNotificationEmailAddressTxt property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBillNotificationEmailAddressTxt(String value) {
            this.billNotificationEmailAddressTxt = value;
        }

        /**
         * Gets the value of the billNotificationSmsNum property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBillNotificationSmsNum() {
            return billNotificationSmsNum;
        }

        /**
         * Sets the value of the billNotificationSmsNum property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBillNotificationSmsNum(String value) {
            this.billNotificationSmsNum = value;
        }

        public String toString() {
            final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
            final StringBuilder buffer = new StringBuilder();
            append(null, buffer, strategy);
            return buffer.toString();
        }

        public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            strategy.appendStart(locator, this, buffer);
            appendFields(locator, buffer, strategy);
            strategy.appendEnd(locator, this, buffer);
            return buffer;
        }

        public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            {
                String theBillNotificationMethodCd;
                theBillNotificationMethodCd = this.getBillNotificationMethodCd();
                strategy.appendField(locator, this, "billNotificationMethodCd", buffer, theBillNotificationMethodCd);
            }
            {
                String theBillNotificationEmailAddressTxt;
                theBillNotificationEmailAddressTxt = this.getBillNotificationEmailAddressTxt();
                strategy.appendField(locator, this, "billNotificationEmailAddressTxt", buffer, theBillNotificationEmailAddressTxt);
            }
            {
                String theBillNotificationSmsNum;
                theBillNotificationSmsNum = this.getBillNotificationSmsNum();
                strategy.appendField(locator, this, "billNotificationSmsNum", buffer, theBillNotificationSmsNum);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof Order.EBillRegistration)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final Order.EBillRegistration that = ((Order.EBillRegistration) object);
            {
                String lhsBillNotificationMethodCd;
                lhsBillNotificationMethodCd = this.getBillNotificationMethodCd();
                String rhsBillNotificationMethodCd;
                rhsBillNotificationMethodCd = that.getBillNotificationMethodCd();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "billNotificationMethodCd", lhsBillNotificationMethodCd), LocatorUtils.property(thatLocator, "billNotificationMethodCd", rhsBillNotificationMethodCd), lhsBillNotificationMethodCd, rhsBillNotificationMethodCd)) {
                    return false;
                }
            }
            {
                String lhsBillNotificationEmailAddressTxt;
                lhsBillNotificationEmailAddressTxt = this.getBillNotificationEmailAddressTxt();
                String rhsBillNotificationEmailAddressTxt;
                rhsBillNotificationEmailAddressTxt = that.getBillNotificationEmailAddressTxt();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "billNotificationEmailAddressTxt", lhsBillNotificationEmailAddressTxt), LocatorUtils.property(thatLocator, "billNotificationEmailAddressTxt", rhsBillNotificationEmailAddressTxt), lhsBillNotificationEmailAddressTxt, rhsBillNotificationEmailAddressTxt)) {
                    return false;
                }
            }
            {
                String lhsBillNotificationSmsNum;
                lhsBillNotificationSmsNum = this.getBillNotificationSmsNum();
                String rhsBillNotificationSmsNum;
                rhsBillNotificationSmsNum = that.getBillNotificationSmsNum();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "billNotificationSmsNum", lhsBillNotificationSmsNum), LocatorUtils.property(thatLocator, "billNotificationSmsNum", rhsBillNotificationSmsNum), lhsBillNotificationSmsNum, rhsBillNotificationSmsNum)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object object) {
            final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
            return equals(null, null, object, strategy);
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="birthDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *         &lt;element name="voicemailLanguageCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="contactLanguageCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="contactEmailAddress" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ElectronicContact" minOccurs="0"/>
     *         &lt;element name="businessUser" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}SalesOrderBusinessUserType" minOccurs="0"/>
     *         &lt;element name="reasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="subscriberAdditionalLineTxt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="subscriberPreference" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}SubscriberPreference" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "firstName",
        "lastName",
        "birthDate",
        "voicemailLanguageCode",
        "contactLanguageCode",
        "contactEmailAddress",
        "businessUser",
        "reasonCode",
        "subscriberAdditionalLineTxt",
        "subscriberPreference"
    })
    public static class NewSubscriberDetail
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        @XmlElement(required = true)
        protected String firstName;
        @XmlElement(required = true)
        protected String lastName;
        @XmlElement(type = String.class)
        @XmlJavaTypeAdapter(DateAdapter.class)
        @XmlSchemaType(name = "date")
        protected Date birthDate;
        protected String voicemailLanguageCode;
        protected String contactLanguageCode;
        protected ElectronicContact contactEmailAddress;
        protected SalesOrderBusinessUserType businessUser;
        protected String reasonCode;
        protected String subscriberAdditionalLineTxt;
        protected SubscriberPreference subscriberPreference;

        /**
         * Gets the value of the firstName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFirstName() {
            return firstName;
        }

        /**
         * Sets the value of the firstName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFirstName(String value) {
            this.firstName = value;
        }

        /**
         * Gets the value of the lastName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLastName() {
            return lastName;
        }

        /**
         * Sets the value of the lastName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLastName(String value) {
            this.lastName = value;
        }

        /**
         * Gets the value of the birthDate property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public Date getBirthDate() {
            return birthDate;
        }

        /**
         * Sets the value of the birthDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBirthDate(Date value) {
            this.birthDate = value;
        }

        /**
         * Gets the value of the voicemailLanguageCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVoicemailLanguageCode() {
            return voicemailLanguageCode;
        }

        /**
         * Sets the value of the voicemailLanguageCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVoicemailLanguageCode(String value) {
            this.voicemailLanguageCode = value;
        }

        /**
         * Gets the value of the contactLanguageCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getContactLanguageCode() {
            return contactLanguageCode;
        }

        /**
         * Sets the value of the contactLanguageCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setContactLanguageCode(String value) {
            this.contactLanguageCode = value;
        }

        /**
         * Gets the value of the contactEmailAddress property.
         * 
         * @return
         *     possible object is
         *     {@link ElectronicContact }
         *     
         */
        public ElectronicContact getContactEmailAddress() {
            return contactEmailAddress;
        }

        /**
         * Sets the value of the contactEmailAddress property.
         * 
         * @param value
         *     allowed object is
         *     {@link ElectronicContact }
         *     
         */
        public void setContactEmailAddress(ElectronicContact value) {
            this.contactEmailAddress = value;
        }

        /**
         * Gets the value of the businessUser property.
         * 
         * @return
         *     possible object is
         *     {@link SalesOrderBusinessUserType }
         *     
         */
        public SalesOrderBusinessUserType getBusinessUser() {
            return businessUser;
        }

        /**
         * Sets the value of the businessUser property.
         * 
         * @param value
         *     allowed object is
         *     {@link SalesOrderBusinessUserType }
         *     
         */
        public void setBusinessUser(SalesOrderBusinessUserType value) {
            this.businessUser = value;
        }

        /**
         * Gets the value of the reasonCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getReasonCode() {
            return reasonCode;
        }

        /**
         * Sets the value of the reasonCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setReasonCode(String value) {
            this.reasonCode = value;
        }

        /**
         * Gets the value of the subscriberAdditionalLineTxt property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSubscriberAdditionalLineTxt() {
            return subscriberAdditionalLineTxt;
        }

        /**
         * Sets the value of the subscriberAdditionalLineTxt property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSubscriberAdditionalLineTxt(String value) {
            this.subscriberAdditionalLineTxt = value;
        }

        /**
         * Gets the value of the subscriberPreference property.
         * 
         * @return
         *     possible object is
         *     {@link SubscriberPreference }
         *     
         */
        public SubscriberPreference getSubscriberPreference() {
            return subscriberPreference;
        }

        /**
         * Sets the value of the subscriberPreference property.
         * 
         * @param value
         *     allowed object is
         *     {@link SubscriberPreference }
         *     
         */
        public void setSubscriberPreference(SubscriberPreference value) {
            this.subscriberPreference = value;
        }

        public String toString() {
            final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
            final StringBuilder buffer = new StringBuilder();
            append(null, buffer, strategy);
            return buffer.toString();
        }

        public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            strategy.appendStart(locator, this, buffer);
            appendFields(locator, buffer, strategy);
            strategy.appendEnd(locator, this, buffer);
            return buffer;
        }

        public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            {
                String theFirstName;
                theFirstName = this.getFirstName();
                strategy.appendField(locator, this, "firstName", buffer, theFirstName);
            }
            {
                String theLastName;
                theLastName = this.getLastName();
                strategy.appendField(locator, this, "lastName", buffer, theLastName);
            }
            {
                Date theBirthDate;
                theBirthDate = this.getBirthDate();
                strategy.appendField(locator, this, "birthDate", buffer, theBirthDate);
            }
            {
                String theVoicemailLanguageCode;
                theVoicemailLanguageCode = this.getVoicemailLanguageCode();
                strategy.appendField(locator, this, "voicemailLanguageCode", buffer, theVoicemailLanguageCode);
            }
            {
                String theContactLanguageCode;
                theContactLanguageCode = this.getContactLanguageCode();
                strategy.appendField(locator, this, "contactLanguageCode", buffer, theContactLanguageCode);
            }
            {
                ElectronicContact theContactEmailAddress;
                theContactEmailAddress = this.getContactEmailAddress();
                strategy.appendField(locator, this, "contactEmailAddress", buffer, theContactEmailAddress);
            }
            {
                SalesOrderBusinessUserType theBusinessUser;
                theBusinessUser = this.getBusinessUser();
                strategy.appendField(locator, this, "businessUser", buffer, theBusinessUser);
            }
            {
                String theReasonCode;
                theReasonCode = this.getReasonCode();
                strategy.appendField(locator, this, "reasonCode", buffer, theReasonCode);
            }
            {
                String theSubscriberAdditionalLineTxt;
                theSubscriberAdditionalLineTxt = this.getSubscriberAdditionalLineTxt();
                strategy.appendField(locator, this, "subscriberAdditionalLineTxt", buffer, theSubscriberAdditionalLineTxt);
            }
            {
                SubscriberPreference theSubscriberPreference;
                theSubscriberPreference = this.getSubscriberPreference();
                strategy.appendField(locator, this, "subscriberPreference", buffer, theSubscriberPreference);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof Order.NewSubscriberDetail)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final Order.NewSubscriberDetail that = ((Order.NewSubscriberDetail) object);
            {
                String lhsFirstName;
                lhsFirstName = this.getFirstName();
                String rhsFirstName;
                rhsFirstName = that.getFirstName();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "firstName", lhsFirstName), LocatorUtils.property(thatLocator, "firstName", rhsFirstName), lhsFirstName, rhsFirstName)) {
                    return false;
                }
            }
            {
                String lhsLastName;
                lhsLastName = this.getLastName();
                String rhsLastName;
                rhsLastName = that.getLastName();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "lastName", lhsLastName), LocatorUtils.property(thatLocator, "lastName", rhsLastName), lhsLastName, rhsLastName)) {
                    return false;
                }
            }
            {
                Date lhsBirthDate;
                lhsBirthDate = this.getBirthDate();
                Date rhsBirthDate;
                rhsBirthDate = that.getBirthDate();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "birthDate", lhsBirthDate), LocatorUtils.property(thatLocator, "birthDate", rhsBirthDate), lhsBirthDate, rhsBirthDate)) {
                    return false;
                }
            }
            {
                String lhsVoicemailLanguageCode;
                lhsVoicemailLanguageCode = this.getVoicemailLanguageCode();
                String rhsVoicemailLanguageCode;
                rhsVoicemailLanguageCode = that.getVoicemailLanguageCode();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "voicemailLanguageCode", lhsVoicemailLanguageCode), LocatorUtils.property(thatLocator, "voicemailLanguageCode", rhsVoicemailLanguageCode), lhsVoicemailLanguageCode, rhsVoicemailLanguageCode)) {
                    return false;
                }
            }
            {
                String lhsContactLanguageCode;
                lhsContactLanguageCode = this.getContactLanguageCode();
                String rhsContactLanguageCode;
                rhsContactLanguageCode = that.getContactLanguageCode();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "contactLanguageCode", lhsContactLanguageCode), LocatorUtils.property(thatLocator, "contactLanguageCode", rhsContactLanguageCode), lhsContactLanguageCode, rhsContactLanguageCode)) {
                    return false;
                }
            }
            {
                ElectronicContact lhsContactEmailAddress;
                lhsContactEmailAddress = this.getContactEmailAddress();
                ElectronicContact rhsContactEmailAddress;
                rhsContactEmailAddress = that.getContactEmailAddress();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "contactEmailAddress", lhsContactEmailAddress), LocatorUtils.property(thatLocator, "contactEmailAddress", rhsContactEmailAddress), lhsContactEmailAddress, rhsContactEmailAddress)) {
                    return false;
                }
            }
            {
                SalesOrderBusinessUserType lhsBusinessUser;
                lhsBusinessUser = this.getBusinessUser();
                SalesOrderBusinessUserType rhsBusinessUser;
                rhsBusinessUser = that.getBusinessUser();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "businessUser", lhsBusinessUser), LocatorUtils.property(thatLocator, "businessUser", rhsBusinessUser), lhsBusinessUser, rhsBusinessUser)) {
                    return false;
                }
            }
            {
                String lhsReasonCode;
                lhsReasonCode = this.getReasonCode();
                String rhsReasonCode;
                rhsReasonCode = that.getReasonCode();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "reasonCode", lhsReasonCode), LocatorUtils.property(thatLocator, "reasonCode", rhsReasonCode), lhsReasonCode, rhsReasonCode)) {
                    return false;
                }
            }
            {
                String lhsSubscriberAdditionalLineTxt;
                lhsSubscriberAdditionalLineTxt = this.getSubscriberAdditionalLineTxt();
                String rhsSubscriberAdditionalLineTxt;
                rhsSubscriberAdditionalLineTxt = that.getSubscriberAdditionalLineTxt();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "subscriberAdditionalLineTxt", lhsSubscriberAdditionalLineTxt), LocatorUtils.property(thatLocator, "subscriberAdditionalLineTxt", rhsSubscriberAdditionalLineTxt), lhsSubscriberAdditionalLineTxt, rhsSubscriberAdditionalLineTxt)) {
                    return false;
                }
            }
            {
                SubscriberPreference lhsSubscriberPreference;
                lhsSubscriberPreference = this.getSubscriberPreference();
                SubscriberPreference rhsSubscriberPreference;
                rhsSubscriberPreference = that.getSubscriberPreference();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "subscriberPreference", lhsSubscriberPreference), LocatorUtils.property(thatLocator, "subscriberPreference", rhsSubscriberPreference), lhsSubscriberPreference, rhsSubscriberPreference)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object object) {
            final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
            return equals(null, null, object, strategy);
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="offerHeader" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelessOfferHeader"/>
     *         &lt;element name="offerReservationStatusCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="hardwarePromotionVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="validationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="offerGetPhoneIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="billCreditList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/WirelessSubscriberOfferInformationTypes_v2}BillCredit" maxOccurs="25" minOccurs="0"/>
     *         &lt;element name="selectedRewardBillCredit" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}RewardBillCredit" minOccurs="0"/>
     *         &lt;element name="selectedOfferBillDiscountPlan" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}BillDiscountPlan" minOccurs="0"/>
     *         &lt;element name="overrideRedemptionMethodID" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
     *         &lt;element name="bringItBackProgramInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "offerHeader",
        "offerReservationStatusCd",
        "hardwarePromotionVersion",
        "validationCode",
        "offerGetPhoneIndicator",
        "billCreditList",
        "selectedRewardBillCredit",
        "selectedOfferBillDiscountPlan",
        "overrideRedemptionMethodID",
        "bringItBackProgramInd"
    })
    public static class Offer
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        @XmlElement(required = true)
        protected WirelessOfferHeader offerHeader;
        protected String offerReservationStatusCd;
        protected String hardwarePromotionVersion;
        protected String validationCode;
        protected Boolean offerGetPhoneIndicator;
        protected List<BillCredit> billCreditList;
        protected RewardBillCredit selectedRewardBillCredit;
        protected BillDiscountPlan selectedOfferBillDiscountPlan;
        protected BigInteger overrideRedemptionMethodID;
        protected Boolean bringItBackProgramInd;

        /**
         * Gets the value of the offerHeader property.
         * 
         * @return
         *     possible object is
         *     {@link WirelessOfferHeader }
         *     
         */
        public WirelessOfferHeader getOfferHeader() {
            return offerHeader;
        }

        /**
         * Sets the value of the offerHeader property.
         * 
         * @param value
         *     allowed object is
         *     {@link WirelessOfferHeader }
         *     
         */
        public void setOfferHeader(WirelessOfferHeader value) {
            this.offerHeader = value;
        }

        /**
         * Gets the value of the offerReservationStatusCd property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOfferReservationStatusCd() {
            return offerReservationStatusCd;
        }

        /**
         * Sets the value of the offerReservationStatusCd property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOfferReservationStatusCd(String value) {
            this.offerReservationStatusCd = value;
        }

        /**
         * Gets the value of the hardwarePromotionVersion property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHardwarePromotionVersion() {
            return hardwarePromotionVersion;
        }

        /**
         * Sets the value of the hardwarePromotionVersion property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHardwarePromotionVersion(String value) {
            this.hardwarePromotionVersion = value;
        }

        /**
         * Gets the value of the validationCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValidationCode() {
            return validationCode;
        }

        /**
         * Sets the value of the validationCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValidationCode(String value) {
            this.validationCode = value;
        }

        /**
         * Gets the value of the offerGetPhoneIndicator property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isOfferGetPhoneIndicator() {
            return offerGetPhoneIndicator;
        }

        /**
         * Sets the value of the offerGetPhoneIndicator property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setOfferGetPhoneIndicator(Boolean value) {
            this.offerGetPhoneIndicator = value;
        }

        /**
         * Gets the value of the billCreditList property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the billCreditList property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getBillCreditList().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BillCredit }
         * 
         * 
         */
        public List<BillCredit> getBillCreditList() {
            if (billCreditList == null) {
                billCreditList = new ArrayList<BillCredit>();
            }
            return this.billCreditList;
        }

        /**
         * Gets the value of the selectedRewardBillCredit property.
         * 
         * @return
         *     possible object is
         *     {@link RewardBillCredit }
         *     
         */
        public RewardBillCredit getSelectedRewardBillCredit() {
            return selectedRewardBillCredit;
        }

        /**
         * Sets the value of the selectedRewardBillCredit property.
         * 
         * @param value
         *     allowed object is
         *     {@link RewardBillCredit }
         *     
         */
        public void setSelectedRewardBillCredit(RewardBillCredit value) {
            this.selectedRewardBillCredit = value;
        }

        /**
         * Gets the value of the selectedOfferBillDiscountPlan property.
         * 
         * @return
         *     possible object is
         *     {@link BillDiscountPlan }
         *     
         */
        public BillDiscountPlan getSelectedOfferBillDiscountPlan() {
            return selectedOfferBillDiscountPlan;
        }

        /**
         * Sets the value of the selectedOfferBillDiscountPlan property.
         * 
         * @param value
         *     allowed object is
         *     {@link BillDiscountPlan }
         *     
         */
        public void setSelectedOfferBillDiscountPlan(BillDiscountPlan value) {
            this.selectedOfferBillDiscountPlan = value;
        }

        /**
         * Gets the value of the overrideRedemptionMethodID property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getOverrideRedemptionMethodID() {
            return overrideRedemptionMethodID;
        }

        /**
         * Sets the value of the overrideRedemptionMethodID property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setOverrideRedemptionMethodID(BigInteger value) {
            this.overrideRedemptionMethodID = value;
        }

        /**
         * Gets the value of the bringItBackProgramInd property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isBringItBackProgramInd() {
            return bringItBackProgramInd;
        }

        /**
         * Sets the value of the bringItBackProgramInd property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setBringItBackProgramInd(Boolean value) {
            this.bringItBackProgramInd = value;
        }

        /**
         * Sets the value of the billCreditList property.
         * 
         * @param billCreditList
         *     allowed object is
         *     {@link BillCredit }
         *     
         */
        public void setBillCreditList(List<BillCredit> billCreditList) {
            this.billCreditList = billCreditList;
        }

        public String toString() {
            final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
            final StringBuilder buffer = new StringBuilder();
            append(null, buffer, strategy);
            return buffer.toString();
        }

        public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            strategy.appendStart(locator, this, buffer);
            appendFields(locator, buffer, strategy);
            strategy.appendEnd(locator, this, buffer);
            return buffer;
        }

        public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            {
                WirelessOfferHeader theOfferHeader;
                theOfferHeader = this.getOfferHeader();
                strategy.appendField(locator, this, "offerHeader", buffer, theOfferHeader);
            }
            {
                String theOfferReservationStatusCd;
                theOfferReservationStatusCd = this.getOfferReservationStatusCd();
                strategy.appendField(locator, this, "offerReservationStatusCd", buffer, theOfferReservationStatusCd);
            }
            {
                String theHardwarePromotionVersion;
                theHardwarePromotionVersion = this.getHardwarePromotionVersion();
                strategy.appendField(locator, this, "hardwarePromotionVersion", buffer, theHardwarePromotionVersion);
            }
            {
                String theValidationCode;
                theValidationCode = this.getValidationCode();
                strategy.appendField(locator, this, "validationCode", buffer, theValidationCode);
            }
            {
                Boolean theOfferGetPhoneIndicator;
                theOfferGetPhoneIndicator = this.isOfferGetPhoneIndicator();
                strategy.appendField(locator, this, "offerGetPhoneIndicator", buffer, theOfferGetPhoneIndicator);
            }
            {
                List<BillCredit> theBillCreditList;
                theBillCreditList = (((this.billCreditList!= null)&&(!this.billCreditList.isEmpty()))?this.getBillCreditList():null);
                strategy.appendField(locator, this, "billCreditList", buffer, theBillCreditList);
            }
            {
                RewardBillCredit theSelectedRewardBillCredit;
                theSelectedRewardBillCredit = this.getSelectedRewardBillCredit();
                strategy.appendField(locator, this, "selectedRewardBillCredit", buffer, theSelectedRewardBillCredit);
            }
            {
                BillDiscountPlan theSelectedOfferBillDiscountPlan;
                theSelectedOfferBillDiscountPlan = this.getSelectedOfferBillDiscountPlan();
                strategy.appendField(locator, this, "selectedOfferBillDiscountPlan", buffer, theSelectedOfferBillDiscountPlan);
            }
            {
                BigInteger theOverrideRedemptionMethodID;
                theOverrideRedemptionMethodID = this.getOverrideRedemptionMethodID();
                strategy.appendField(locator, this, "overrideRedemptionMethodID", buffer, theOverrideRedemptionMethodID);
            }
            {
                Boolean theBringItBackProgramInd;
                theBringItBackProgramInd = this.isBringItBackProgramInd();
                strategy.appendField(locator, this, "bringItBackProgramInd", buffer, theBringItBackProgramInd);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof Order.Offer)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final Order.Offer that = ((Order.Offer) object);
            {
                WirelessOfferHeader lhsOfferHeader;
                lhsOfferHeader = this.getOfferHeader();
                WirelessOfferHeader rhsOfferHeader;
                rhsOfferHeader = that.getOfferHeader();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "offerHeader", lhsOfferHeader), LocatorUtils.property(thatLocator, "offerHeader", rhsOfferHeader), lhsOfferHeader, rhsOfferHeader)) {
                    return false;
                }
            }
            {
                String lhsOfferReservationStatusCd;
                lhsOfferReservationStatusCd = this.getOfferReservationStatusCd();
                String rhsOfferReservationStatusCd;
                rhsOfferReservationStatusCd = that.getOfferReservationStatusCd();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "offerReservationStatusCd", lhsOfferReservationStatusCd), LocatorUtils.property(thatLocator, "offerReservationStatusCd", rhsOfferReservationStatusCd), lhsOfferReservationStatusCd, rhsOfferReservationStatusCd)) {
                    return false;
                }
            }
            {
                String lhsHardwarePromotionVersion;
                lhsHardwarePromotionVersion = this.getHardwarePromotionVersion();
                String rhsHardwarePromotionVersion;
                rhsHardwarePromotionVersion = that.getHardwarePromotionVersion();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "hardwarePromotionVersion", lhsHardwarePromotionVersion), LocatorUtils.property(thatLocator, "hardwarePromotionVersion", rhsHardwarePromotionVersion), lhsHardwarePromotionVersion, rhsHardwarePromotionVersion)) {
                    return false;
                }
            }
            {
                String lhsValidationCode;
                lhsValidationCode = this.getValidationCode();
                String rhsValidationCode;
                rhsValidationCode = that.getValidationCode();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "validationCode", lhsValidationCode), LocatorUtils.property(thatLocator, "validationCode", rhsValidationCode), lhsValidationCode, rhsValidationCode)) {
                    return false;
                }
            }
            {
                Boolean lhsOfferGetPhoneIndicator;
                lhsOfferGetPhoneIndicator = this.isOfferGetPhoneIndicator();
                Boolean rhsOfferGetPhoneIndicator;
                rhsOfferGetPhoneIndicator = that.isOfferGetPhoneIndicator();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "offerGetPhoneIndicator", lhsOfferGetPhoneIndicator), LocatorUtils.property(thatLocator, "offerGetPhoneIndicator", rhsOfferGetPhoneIndicator), lhsOfferGetPhoneIndicator, rhsOfferGetPhoneIndicator)) {
                    return false;
                }
            }
            {
                List<BillCredit> lhsBillCreditList;
                lhsBillCreditList = (((this.billCreditList!= null)&&(!this.billCreditList.isEmpty()))?this.getBillCreditList():null);
                List<BillCredit> rhsBillCreditList;
                rhsBillCreditList = (((that.billCreditList!= null)&&(!that.billCreditList.isEmpty()))?that.getBillCreditList():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "billCreditList", lhsBillCreditList), LocatorUtils.property(thatLocator, "billCreditList", rhsBillCreditList), lhsBillCreditList, rhsBillCreditList)) {
                    return false;
                }
            }
            {
                RewardBillCredit lhsSelectedRewardBillCredit;
                lhsSelectedRewardBillCredit = this.getSelectedRewardBillCredit();
                RewardBillCredit rhsSelectedRewardBillCredit;
                rhsSelectedRewardBillCredit = that.getSelectedRewardBillCredit();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "selectedRewardBillCredit", lhsSelectedRewardBillCredit), LocatorUtils.property(thatLocator, "selectedRewardBillCredit", rhsSelectedRewardBillCredit), lhsSelectedRewardBillCredit, rhsSelectedRewardBillCredit)) {
                    return false;
                }
            }
            {
                BillDiscountPlan lhsSelectedOfferBillDiscountPlan;
                lhsSelectedOfferBillDiscountPlan = this.getSelectedOfferBillDiscountPlan();
                BillDiscountPlan rhsSelectedOfferBillDiscountPlan;
                rhsSelectedOfferBillDiscountPlan = that.getSelectedOfferBillDiscountPlan();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "selectedOfferBillDiscountPlan", lhsSelectedOfferBillDiscountPlan), LocatorUtils.property(thatLocator, "selectedOfferBillDiscountPlan", rhsSelectedOfferBillDiscountPlan), lhsSelectedOfferBillDiscountPlan, rhsSelectedOfferBillDiscountPlan)) {
                    return false;
                }
            }
            {
                BigInteger lhsOverrideRedemptionMethodID;
                lhsOverrideRedemptionMethodID = this.getOverrideRedemptionMethodID();
                BigInteger rhsOverrideRedemptionMethodID;
                rhsOverrideRedemptionMethodID = that.getOverrideRedemptionMethodID();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "overrideRedemptionMethodID", lhsOverrideRedemptionMethodID), LocatorUtils.property(thatLocator, "overrideRedemptionMethodID", rhsOverrideRedemptionMethodID), lhsOverrideRedemptionMethodID, rhsOverrideRedemptionMethodID)) {
                    return false;
                }
            }
            {
                Boolean lhsBringItBackProgramInd;
                lhsBringItBackProgramInd = this.isBringItBackProgramInd();
                Boolean rhsBringItBackProgramInd;
                rhsBringItBackProgramInd = that.isBringItBackProgramInd();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "bringItBackProgramInd", lhsBringItBackProgramInd), LocatorUtils.property(thatLocator, "bringItBackProgramInd", rhsBringItBackProgramInd), lhsBringItBackProgramInd, rhsBringItBackProgramInd)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object object) {
            final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
            return equals(null, null, object, strategy);
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="orderType" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *         &lt;element name="orderSubType" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
     *         &lt;element name="orderVersionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="orderStatusId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="orderStatusChangeDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *         &lt;element name="orderCreationDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *         &lt;element name="salesId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="externalOrderReferenceList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}OrderReference" maxOccurs="10" minOccurs="0"/>
     *         &lt;element name="orderEmailAddress" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ElectronicContact" minOccurs="0"/>
     *         &lt;element name="salesRepEmailAddress" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ElectronicContact" minOccurs="0"/>
     *         &lt;element name="systemIntegrationParameterList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}SystemIntegrationParameterType" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="userProfile" type="{http://xmlschema.tmi.telus.com/xsd/Partner/Partner/ChannelPartnerCommon_v2}ChannelPartnerUserProfileType" minOccurs="0"/>
     *         &lt;element name="agentProfile" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}AgentProfile" minOccurs="0"/>
     *         &lt;element name="salesPersonRoleCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="orderCancellationReasonCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="brandCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="originatorApplicationId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "orderType",
        "orderSubType",
        "orderVersionCode",
        "orderStatusId",
        "orderStatusChangeDateTime",
        "orderCreationDateTime",
        "salesId",
        "externalOrderReferenceList",
        "orderEmailAddress",
        "salesRepEmailAddress",
        "systemIntegrationParameterList",
        "userProfile",
        "agentProfile",
        "salesPersonRoleCode",
        "orderCancellationReasonCd",
        "brandCode",
        "originatorApplicationId"
    })
    public static class OrderContext
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        @XmlElement(required = true)
        protected Object orderType;
        @XmlElement(required = true)
        protected Object orderSubType;
        protected String orderVersionCode;
        protected String orderStatusId;
        @XmlElement(type = String.class)
        @XmlJavaTypeAdapter(DateTimeAdapter.class)
        @XmlSchemaType(name = "dateTime")
        protected Date orderStatusChangeDateTime;
        @XmlElement(type = String.class)
        @XmlJavaTypeAdapter(DateTimeAdapter.class)
        @XmlSchemaType(name = "dateTime")
        protected Date orderCreationDateTime;
        protected String salesId;
        protected List<OrderReference> externalOrderReferenceList;
        protected ElectronicContact orderEmailAddress;
        protected ElectronicContact salesRepEmailAddress;
        protected List<SystemIntegrationParameterType> systemIntegrationParameterList;
        protected ChannelPartnerUserProfileType userProfile;
        protected AgentProfile agentProfile;
        protected String salesPersonRoleCode;
        protected String orderCancellationReasonCd;
        protected String brandCode;
        protected Long originatorApplicationId;

        /**
         * Gets the value of the orderType property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getOrderType() {
            return orderType;
        }

        /**
         * Sets the value of the orderType property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setOrderType(Object value) {
            this.orderType = value;
        }

        /**
         * Gets the value of the orderSubType property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getOrderSubType() {
            return orderSubType;
        }

        /**
         * Sets the value of the orderSubType property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setOrderSubType(Object value) {
            this.orderSubType = value;
        }

        /**
         * Gets the value of the orderVersionCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOrderVersionCode() {
            return orderVersionCode;
        }

        /**
         * Sets the value of the orderVersionCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOrderVersionCode(String value) {
            this.orderVersionCode = value;
        }

        /**
         * Gets the value of the orderStatusId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOrderStatusId() {
            return orderStatusId;
        }

        /**
         * Sets the value of the orderStatusId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOrderStatusId(String value) {
            this.orderStatusId = value;
        }

        /**
         * Gets the value of the orderStatusChangeDateTime property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public Date getOrderStatusChangeDateTime() {
            return orderStatusChangeDateTime;
        }

        /**
         * Sets the value of the orderStatusChangeDateTime property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOrderStatusChangeDateTime(Date value) {
            this.orderStatusChangeDateTime = value;
        }

        /**
         * Gets the value of the orderCreationDateTime property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public Date getOrderCreationDateTime() {
            return orderCreationDateTime;
        }

        /**
         * Sets the value of the orderCreationDateTime property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOrderCreationDateTime(Date value) {
            this.orderCreationDateTime = value;
        }

        /**
         * Gets the value of the salesId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSalesId() {
            return salesId;
        }

        /**
         * Sets the value of the salesId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSalesId(String value) {
            this.salesId = value;
        }

        /**
         * Gets the value of the externalOrderReferenceList property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the externalOrderReferenceList property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getExternalOrderReferenceList().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link OrderReference }
         * 
         * 
         */
        public List<OrderReference> getExternalOrderReferenceList() {
            if (externalOrderReferenceList == null) {
                externalOrderReferenceList = new ArrayList<OrderReference>();
            }
            return this.externalOrderReferenceList;
        }

        /**
         * Gets the value of the orderEmailAddress property.
         * 
         * @return
         *     possible object is
         *     {@link ElectronicContact }
         *     
         */
        public ElectronicContact getOrderEmailAddress() {
            return orderEmailAddress;
        }

        /**
         * Sets the value of the orderEmailAddress property.
         * 
         * @param value
         *     allowed object is
         *     {@link ElectronicContact }
         *     
         */
        public void setOrderEmailAddress(ElectronicContact value) {
            this.orderEmailAddress = value;
        }

        /**
         * Gets the value of the salesRepEmailAddress property.
         * 
         * @return
         *     possible object is
         *     {@link ElectronicContact }
         *     
         */
        public ElectronicContact getSalesRepEmailAddress() {
            return salesRepEmailAddress;
        }

        /**
         * Sets the value of the salesRepEmailAddress property.
         * 
         * @param value
         *     allowed object is
         *     {@link ElectronicContact }
         *     
         */
        public void setSalesRepEmailAddress(ElectronicContact value) {
            this.salesRepEmailAddress = value;
        }

        /**
         * Gets the value of the systemIntegrationParameterList property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the systemIntegrationParameterList property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSystemIntegrationParameterList().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SystemIntegrationParameterType }
         * 
         * 
         */
        public List<SystemIntegrationParameterType> getSystemIntegrationParameterList() {
            if (systemIntegrationParameterList == null) {
                systemIntegrationParameterList = new ArrayList<SystemIntegrationParameterType>();
            }
            return this.systemIntegrationParameterList;
        }

        /**
         * Gets the value of the userProfile property.
         * 
         * @return
         *     possible object is
         *     {@link ChannelPartnerUserProfileType }
         *     
         */
        public ChannelPartnerUserProfileType getUserProfile() {
            return userProfile;
        }

        /**
         * Sets the value of the userProfile property.
         * 
         * @param value
         *     allowed object is
         *     {@link ChannelPartnerUserProfileType }
         *     
         */
        public void setUserProfile(ChannelPartnerUserProfileType value) {
            this.userProfile = value;
        }

        /**
         * Gets the value of the agentProfile property.
         * 
         * @return
         *     possible object is
         *     {@link AgentProfile }
         *     
         */
        public AgentProfile getAgentProfile() {
            return agentProfile;
        }

        /**
         * Sets the value of the agentProfile property.
         * 
         * @param value
         *     allowed object is
         *     {@link AgentProfile }
         *     
         */
        public void setAgentProfile(AgentProfile value) {
            this.agentProfile = value;
        }

        /**
         * Gets the value of the salesPersonRoleCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSalesPersonRoleCode() {
            return salesPersonRoleCode;
        }

        /**
         * Sets the value of the salesPersonRoleCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSalesPersonRoleCode(String value) {
            this.salesPersonRoleCode = value;
        }

        /**
         * Gets the value of the orderCancellationReasonCd property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOrderCancellationReasonCd() {
            return orderCancellationReasonCd;
        }

        /**
         * Sets the value of the orderCancellationReasonCd property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOrderCancellationReasonCd(String value) {
            this.orderCancellationReasonCd = value;
        }

        /**
         * Gets the value of the brandCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBrandCode() {
            return brandCode;
        }

        /**
         * Sets the value of the brandCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBrandCode(String value) {
            this.brandCode = value;
        }

        /**
         * Gets the value of the originatorApplicationId property.
         * 
         * @return
         *     possible object is
         *     {@link Long }
         *     
         */
        public Long getOriginatorApplicationId() {
            return originatorApplicationId;
        }

        /**
         * Sets the value of the originatorApplicationId property.
         * 
         * @param value
         *     allowed object is
         *     {@link Long }
         *     
         */
        public void setOriginatorApplicationId(Long value) {
            this.originatorApplicationId = value;
        }

        /**
         * Sets the value of the externalOrderReferenceList property.
         * 
         * @param externalOrderReferenceList
         *     allowed object is
         *     {@link OrderReference }
         *     
         */
        public void setExternalOrderReferenceList(List<OrderReference> externalOrderReferenceList) {
            this.externalOrderReferenceList = externalOrderReferenceList;
        }

        /**
         * Sets the value of the systemIntegrationParameterList property.
         * 
         * @param systemIntegrationParameterList
         *     allowed object is
         *     {@link SystemIntegrationParameterType }
         *     
         */
        public void setSystemIntegrationParameterList(List<SystemIntegrationParameterType> systemIntegrationParameterList) {
            this.systemIntegrationParameterList = systemIntegrationParameterList;
        }

        public String toString() {
            final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
            final StringBuilder buffer = new StringBuilder();
            append(null, buffer, strategy);
            return buffer.toString();
        }

        public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            strategy.appendStart(locator, this, buffer);
            appendFields(locator, buffer, strategy);
            strategy.appendEnd(locator, this, buffer);
            return buffer;
        }

        public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            {
                Object theOrderType;
                theOrderType = this.getOrderType();
                strategy.appendField(locator, this, "orderType", buffer, theOrderType);
            }
            {
                Object theOrderSubType;
                theOrderSubType = this.getOrderSubType();
                strategy.appendField(locator, this, "orderSubType", buffer, theOrderSubType);
            }
            {
                String theOrderVersionCode;
                theOrderVersionCode = this.getOrderVersionCode();
                strategy.appendField(locator, this, "orderVersionCode", buffer, theOrderVersionCode);
            }
            {
                String theOrderStatusId;
                theOrderStatusId = this.getOrderStatusId();
                strategy.appendField(locator, this, "orderStatusId", buffer, theOrderStatusId);
            }
            {
                Date theOrderStatusChangeDateTime;
                theOrderStatusChangeDateTime = this.getOrderStatusChangeDateTime();
                strategy.appendField(locator, this, "orderStatusChangeDateTime", buffer, theOrderStatusChangeDateTime);
            }
            {
                Date theOrderCreationDateTime;
                theOrderCreationDateTime = this.getOrderCreationDateTime();
                strategy.appendField(locator, this, "orderCreationDateTime", buffer, theOrderCreationDateTime);
            }
            {
                String theSalesId;
                theSalesId = this.getSalesId();
                strategy.appendField(locator, this, "salesId", buffer, theSalesId);
            }
            {
                List<OrderReference> theExternalOrderReferenceList;
                theExternalOrderReferenceList = (((this.externalOrderReferenceList!= null)&&(!this.externalOrderReferenceList.isEmpty()))?this.getExternalOrderReferenceList():null);
                strategy.appendField(locator, this, "externalOrderReferenceList", buffer, theExternalOrderReferenceList);
            }
            {
                ElectronicContact theOrderEmailAddress;
                theOrderEmailAddress = this.getOrderEmailAddress();
                strategy.appendField(locator, this, "orderEmailAddress", buffer, theOrderEmailAddress);
            }
            {
                ElectronicContact theSalesRepEmailAddress;
                theSalesRepEmailAddress = this.getSalesRepEmailAddress();
                strategy.appendField(locator, this, "salesRepEmailAddress", buffer, theSalesRepEmailAddress);
            }
            {
                List<SystemIntegrationParameterType> theSystemIntegrationParameterList;
                theSystemIntegrationParameterList = (((this.systemIntegrationParameterList!= null)&&(!this.systemIntegrationParameterList.isEmpty()))?this.getSystemIntegrationParameterList():null);
                strategy.appendField(locator, this, "systemIntegrationParameterList", buffer, theSystemIntegrationParameterList);
            }
            {
                ChannelPartnerUserProfileType theUserProfile;
                theUserProfile = this.getUserProfile();
                strategy.appendField(locator, this, "userProfile", buffer, theUserProfile);
            }
            {
                AgentProfile theAgentProfile;
                theAgentProfile = this.getAgentProfile();
                strategy.appendField(locator, this, "agentProfile", buffer, theAgentProfile);
            }
            {
                String theSalesPersonRoleCode;
                theSalesPersonRoleCode = this.getSalesPersonRoleCode();
                strategy.appendField(locator, this, "salesPersonRoleCode", buffer, theSalesPersonRoleCode);
            }
            {
                String theOrderCancellationReasonCd;
                theOrderCancellationReasonCd = this.getOrderCancellationReasonCd();
                strategy.appendField(locator, this, "orderCancellationReasonCd", buffer, theOrderCancellationReasonCd);
            }
            {
                String theBrandCode;
                theBrandCode = this.getBrandCode();
                strategy.appendField(locator, this, "brandCode", buffer, theBrandCode);
            }
            {
                Long theOriginatorApplicationId;
                theOriginatorApplicationId = this.getOriginatorApplicationId();
                strategy.appendField(locator, this, "originatorApplicationId", buffer, theOriginatorApplicationId);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof Order.OrderContext)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final Order.OrderContext that = ((Order.OrderContext) object);
            {
                Object lhsOrderType;
                lhsOrderType = this.getOrderType();
                Object rhsOrderType;
                rhsOrderType = that.getOrderType();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "orderType", lhsOrderType), LocatorUtils.property(thatLocator, "orderType", rhsOrderType), lhsOrderType, rhsOrderType)) {
                    return false;
                }
            }
            {
                Object lhsOrderSubType;
                lhsOrderSubType = this.getOrderSubType();
                Object rhsOrderSubType;
                rhsOrderSubType = that.getOrderSubType();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "orderSubType", lhsOrderSubType), LocatorUtils.property(thatLocator, "orderSubType", rhsOrderSubType), lhsOrderSubType, rhsOrderSubType)) {
                    return false;
                }
            }
            {
                String lhsOrderVersionCode;
                lhsOrderVersionCode = this.getOrderVersionCode();
                String rhsOrderVersionCode;
                rhsOrderVersionCode = that.getOrderVersionCode();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "orderVersionCode", lhsOrderVersionCode), LocatorUtils.property(thatLocator, "orderVersionCode", rhsOrderVersionCode), lhsOrderVersionCode, rhsOrderVersionCode)) {
                    return false;
                }
            }
            {
                String lhsOrderStatusId;
                lhsOrderStatusId = this.getOrderStatusId();
                String rhsOrderStatusId;
                rhsOrderStatusId = that.getOrderStatusId();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "orderStatusId", lhsOrderStatusId), LocatorUtils.property(thatLocator, "orderStatusId", rhsOrderStatusId), lhsOrderStatusId, rhsOrderStatusId)) {
                    return false;
                }
            }
            {
                Date lhsOrderStatusChangeDateTime;
                lhsOrderStatusChangeDateTime = this.getOrderStatusChangeDateTime();
                Date rhsOrderStatusChangeDateTime;
                rhsOrderStatusChangeDateTime = that.getOrderStatusChangeDateTime();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "orderStatusChangeDateTime", lhsOrderStatusChangeDateTime), LocatorUtils.property(thatLocator, "orderStatusChangeDateTime", rhsOrderStatusChangeDateTime), lhsOrderStatusChangeDateTime, rhsOrderStatusChangeDateTime)) {
                    return false;
                }
            }
            {
                Date lhsOrderCreationDateTime;
                lhsOrderCreationDateTime = this.getOrderCreationDateTime();
                Date rhsOrderCreationDateTime;
                rhsOrderCreationDateTime = that.getOrderCreationDateTime();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "orderCreationDateTime", lhsOrderCreationDateTime), LocatorUtils.property(thatLocator, "orderCreationDateTime", rhsOrderCreationDateTime), lhsOrderCreationDateTime, rhsOrderCreationDateTime)) {
                    return false;
                }
            }
            {
                String lhsSalesId;
                lhsSalesId = this.getSalesId();
                String rhsSalesId;
                rhsSalesId = that.getSalesId();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "salesId", lhsSalesId), LocatorUtils.property(thatLocator, "salesId", rhsSalesId), lhsSalesId, rhsSalesId)) {
                    return false;
                }
            }
            {
                List<OrderReference> lhsExternalOrderReferenceList;
                lhsExternalOrderReferenceList = (((this.externalOrderReferenceList!= null)&&(!this.externalOrderReferenceList.isEmpty()))?this.getExternalOrderReferenceList():null);
                List<OrderReference> rhsExternalOrderReferenceList;
                rhsExternalOrderReferenceList = (((that.externalOrderReferenceList!= null)&&(!that.externalOrderReferenceList.isEmpty()))?that.getExternalOrderReferenceList():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "externalOrderReferenceList", lhsExternalOrderReferenceList), LocatorUtils.property(thatLocator, "externalOrderReferenceList", rhsExternalOrderReferenceList), lhsExternalOrderReferenceList, rhsExternalOrderReferenceList)) {
                    return false;
                }
            }
            {
                ElectronicContact lhsOrderEmailAddress;
                lhsOrderEmailAddress = this.getOrderEmailAddress();
                ElectronicContact rhsOrderEmailAddress;
                rhsOrderEmailAddress = that.getOrderEmailAddress();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "orderEmailAddress", lhsOrderEmailAddress), LocatorUtils.property(thatLocator, "orderEmailAddress", rhsOrderEmailAddress), lhsOrderEmailAddress, rhsOrderEmailAddress)) {
                    return false;
                }
            }
            {
                ElectronicContact lhsSalesRepEmailAddress;
                lhsSalesRepEmailAddress = this.getSalesRepEmailAddress();
                ElectronicContact rhsSalesRepEmailAddress;
                rhsSalesRepEmailAddress = that.getSalesRepEmailAddress();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "salesRepEmailAddress", lhsSalesRepEmailAddress), LocatorUtils.property(thatLocator, "salesRepEmailAddress", rhsSalesRepEmailAddress), lhsSalesRepEmailAddress, rhsSalesRepEmailAddress)) {
                    return false;
                }
            }
            {
                List<SystemIntegrationParameterType> lhsSystemIntegrationParameterList;
                lhsSystemIntegrationParameterList = (((this.systemIntegrationParameterList!= null)&&(!this.systemIntegrationParameterList.isEmpty()))?this.getSystemIntegrationParameterList():null);
                List<SystemIntegrationParameterType> rhsSystemIntegrationParameterList;
                rhsSystemIntegrationParameterList = (((that.systemIntegrationParameterList!= null)&&(!that.systemIntegrationParameterList.isEmpty()))?that.getSystemIntegrationParameterList():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "systemIntegrationParameterList", lhsSystemIntegrationParameterList), LocatorUtils.property(thatLocator, "systemIntegrationParameterList", rhsSystemIntegrationParameterList), lhsSystemIntegrationParameterList, rhsSystemIntegrationParameterList)) {
                    return false;
                }
            }
            {
                ChannelPartnerUserProfileType lhsUserProfile;
                lhsUserProfile = this.getUserProfile();
                ChannelPartnerUserProfileType rhsUserProfile;
                rhsUserProfile = that.getUserProfile();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "userProfile", lhsUserProfile), LocatorUtils.property(thatLocator, "userProfile", rhsUserProfile), lhsUserProfile, rhsUserProfile)) {
                    return false;
                }
            }
            {
                AgentProfile lhsAgentProfile;
                lhsAgentProfile = this.getAgentProfile();
                AgentProfile rhsAgentProfile;
                rhsAgentProfile = that.getAgentProfile();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "agentProfile", lhsAgentProfile), LocatorUtils.property(thatLocator, "agentProfile", rhsAgentProfile), lhsAgentProfile, rhsAgentProfile)) {
                    return false;
                }
            }
            {
                String lhsSalesPersonRoleCode;
                lhsSalesPersonRoleCode = this.getSalesPersonRoleCode();
                String rhsSalesPersonRoleCode;
                rhsSalesPersonRoleCode = that.getSalesPersonRoleCode();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "salesPersonRoleCode", lhsSalesPersonRoleCode), LocatorUtils.property(thatLocator, "salesPersonRoleCode", rhsSalesPersonRoleCode), lhsSalesPersonRoleCode, rhsSalesPersonRoleCode)) {
                    return false;
                }
            }
            {
                String lhsOrderCancellationReasonCd;
                lhsOrderCancellationReasonCd = this.getOrderCancellationReasonCd();
                String rhsOrderCancellationReasonCd;
                rhsOrderCancellationReasonCd = that.getOrderCancellationReasonCd();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "orderCancellationReasonCd", lhsOrderCancellationReasonCd), LocatorUtils.property(thatLocator, "orderCancellationReasonCd", rhsOrderCancellationReasonCd), lhsOrderCancellationReasonCd, rhsOrderCancellationReasonCd)) {
                    return false;
                }
            }
            {
                String lhsBrandCode;
                lhsBrandCode = this.getBrandCode();
                String rhsBrandCode;
                rhsBrandCode = that.getBrandCode();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "brandCode", lhsBrandCode), LocatorUtils.property(thatLocator, "brandCode", rhsBrandCode), lhsBrandCode, rhsBrandCode)) {
                    return false;
                }
            }
            {
                Long lhsOriginatorApplicationId;
                lhsOriginatorApplicationId = this.getOriginatorApplicationId();
                Long rhsOriginatorApplicationId;
                rhsOriginatorApplicationId = that.getOriginatorApplicationId();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "originatorApplicationId", lhsOriginatorApplicationId), LocatorUtils.property(thatLocator, "originatorApplicationId", rhsOriginatorApplicationId), lhsOriginatorApplicationId, rhsOriginatorApplicationId)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object object) {
            final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
            return equals(null, null, object, strategy);
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="fulfillmentMethod" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="fulfillmentDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *         &lt;element name="warehouseShipmentIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="warehouseShipmentDetail" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="contact" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonName_v1}IndividualName" minOccurs="0"/>
     *                   &lt;element name="shippingAddress" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Address"/>
     *                   &lt;element name="shippingNotificationEmail" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ElectronicContact" minOccurs="0"/>
     *                   &lt;element name="shippingNotificationContactPhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="shippingNotificationLanguageCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "fulfillmentMethod",
        "fulfillmentDate",
        "warehouseShipmentIndicator",
        "warehouseShipmentDetail"
    })
    public static class PartialTransactionOption
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        @XmlElement(required = true)
        protected String fulfillmentMethod;
        @XmlElement(type = String.class)
        @XmlJavaTypeAdapter(DateAdapter.class)
        @XmlSchemaType(name = "date")
        protected Date fulfillmentDate;
        protected Boolean warehouseShipmentIndicator;
        protected Order.PartialTransactionOption.WarehouseShipmentDetail warehouseShipmentDetail;

        /**
         * Gets the value of the fulfillmentMethod property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFulfillmentMethod() {
            return fulfillmentMethod;
        }

        /**
         * Sets the value of the fulfillmentMethod property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFulfillmentMethod(String value) {
            this.fulfillmentMethod = value;
        }

        /**
         * Gets the value of the fulfillmentDate property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public Date getFulfillmentDate() {
            return fulfillmentDate;
        }

        /**
         * Sets the value of the fulfillmentDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFulfillmentDate(Date value) {
            this.fulfillmentDate = value;
        }

        /**
         * Gets the value of the warehouseShipmentIndicator property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isWarehouseShipmentIndicator() {
            return warehouseShipmentIndicator;
        }

        /**
         * Sets the value of the warehouseShipmentIndicator property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setWarehouseShipmentIndicator(Boolean value) {
            this.warehouseShipmentIndicator = value;
        }

        /**
         * Gets the value of the warehouseShipmentDetail property.
         * 
         * @return
         *     possible object is
         *     {@link Order.PartialTransactionOption.WarehouseShipmentDetail }
         *     
         */
        public Order.PartialTransactionOption.WarehouseShipmentDetail getWarehouseShipmentDetail() {
            return warehouseShipmentDetail;
        }

        /**
         * Sets the value of the warehouseShipmentDetail property.
         * 
         * @param value
         *     allowed object is
         *     {@link Order.PartialTransactionOption.WarehouseShipmentDetail }
         *     
         */
        public void setWarehouseShipmentDetail(Order.PartialTransactionOption.WarehouseShipmentDetail value) {
            this.warehouseShipmentDetail = value;
        }

        public String toString() {
            final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
            final StringBuilder buffer = new StringBuilder();
            append(null, buffer, strategy);
            return buffer.toString();
        }

        public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            strategy.appendStart(locator, this, buffer);
            appendFields(locator, buffer, strategy);
            strategy.appendEnd(locator, this, buffer);
            return buffer;
        }

        public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            {
                String theFulfillmentMethod;
                theFulfillmentMethod = this.getFulfillmentMethod();
                strategy.appendField(locator, this, "fulfillmentMethod", buffer, theFulfillmentMethod);
            }
            {
                Date theFulfillmentDate;
                theFulfillmentDate = this.getFulfillmentDate();
                strategy.appendField(locator, this, "fulfillmentDate", buffer, theFulfillmentDate);
            }
            {
                Boolean theWarehouseShipmentIndicator;
                theWarehouseShipmentIndicator = this.isWarehouseShipmentIndicator();
                strategy.appendField(locator, this, "warehouseShipmentIndicator", buffer, theWarehouseShipmentIndicator);
            }
            {
                Order.PartialTransactionOption.WarehouseShipmentDetail theWarehouseShipmentDetail;
                theWarehouseShipmentDetail = this.getWarehouseShipmentDetail();
                strategy.appendField(locator, this, "warehouseShipmentDetail", buffer, theWarehouseShipmentDetail);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof Order.PartialTransactionOption)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final Order.PartialTransactionOption that = ((Order.PartialTransactionOption) object);
            {
                String lhsFulfillmentMethod;
                lhsFulfillmentMethod = this.getFulfillmentMethod();
                String rhsFulfillmentMethod;
                rhsFulfillmentMethod = that.getFulfillmentMethod();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "fulfillmentMethod", lhsFulfillmentMethod), LocatorUtils.property(thatLocator, "fulfillmentMethod", rhsFulfillmentMethod), lhsFulfillmentMethod, rhsFulfillmentMethod)) {
                    return false;
                }
            }
            {
                Date lhsFulfillmentDate;
                lhsFulfillmentDate = this.getFulfillmentDate();
                Date rhsFulfillmentDate;
                rhsFulfillmentDate = that.getFulfillmentDate();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "fulfillmentDate", lhsFulfillmentDate), LocatorUtils.property(thatLocator, "fulfillmentDate", rhsFulfillmentDate), lhsFulfillmentDate, rhsFulfillmentDate)) {
                    return false;
                }
            }
            {
                Boolean lhsWarehouseShipmentIndicator;
                lhsWarehouseShipmentIndicator = this.isWarehouseShipmentIndicator();
                Boolean rhsWarehouseShipmentIndicator;
                rhsWarehouseShipmentIndicator = that.isWarehouseShipmentIndicator();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "warehouseShipmentIndicator", lhsWarehouseShipmentIndicator), LocatorUtils.property(thatLocator, "warehouseShipmentIndicator", rhsWarehouseShipmentIndicator), lhsWarehouseShipmentIndicator, rhsWarehouseShipmentIndicator)) {
                    return false;
                }
            }
            {
                Order.PartialTransactionOption.WarehouseShipmentDetail lhsWarehouseShipmentDetail;
                lhsWarehouseShipmentDetail = this.getWarehouseShipmentDetail();
                Order.PartialTransactionOption.WarehouseShipmentDetail rhsWarehouseShipmentDetail;
                rhsWarehouseShipmentDetail = that.getWarehouseShipmentDetail();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "warehouseShipmentDetail", lhsWarehouseShipmentDetail), LocatorUtils.property(thatLocator, "warehouseShipmentDetail", rhsWarehouseShipmentDetail), lhsWarehouseShipmentDetail, rhsWarehouseShipmentDetail)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object object) {
            final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
            return equals(null, null, object, strategy);
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="contact" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonName_v1}IndividualName" minOccurs="0"/>
         *         &lt;element name="shippingAddress" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Address"/>
         *         &lt;element name="shippingNotificationEmail" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ElectronicContact" minOccurs="0"/>
         *         &lt;element name="shippingNotificationContactPhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="shippingNotificationLanguageCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "contact",
            "shippingAddress",
            "shippingNotificationEmail",
            "shippingNotificationContactPhoneNum",
            "shippingNotificationLanguageCd"
        })
        public static class WarehouseShipmentDetail
            implements Serializable, Equals, ToString
        {

            private final static long serialVersionUID = 2L;
            protected IndividualName contact;
            @XmlElement(required = true)
            protected Address shippingAddress;
            protected ElectronicContact shippingNotificationEmail;
            protected String shippingNotificationContactPhoneNum;
            protected String shippingNotificationLanguageCd;

            /**
             * Gets the value of the contact property.
             * 
             * @return
             *     possible object is
             *     {@link IndividualName }
             *     
             */
            public IndividualName getContact() {
                return contact;
            }

            /**
             * Sets the value of the contact property.
             * 
             * @param value
             *     allowed object is
             *     {@link IndividualName }
             *     
             */
            public void setContact(IndividualName value) {
                this.contact = value;
            }

            /**
             * Gets the value of the shippingAddress property.
             * 
             * @return
             *     possible object is
             *     {@link Address }
             *     
             */
            public Address getShippingAddress() {
                return shippingAddress;
            }

            /**
             * Sets the value of the shippingAddress property.
             * 
             * @param value
             *     allowed object is
             *     {@link Address }
             *     
             */
            public void setShippingAddress(Address value) {
                this.shippingAddress = value;
            }

            /**
             * Gets the value of the shippingNotificationEmail property.
             * 
             * @return
             *     possible object is
             *     {@link ElectronicContact }
             *     
             */
            public ElectronicContact getShippingNotificationEmail() {
                return shippingNotificationEmail;
            }

            /**
             * Sets the value of the shippingNotificationEmail property.
             * 
             * @param value
             *     allowed object is
             *     {@link ElectronicContact }
             *     
             */
            public void setShippingNotificationEmail(ElectronicContact value) {
                this.shippingNotificationEmail = value;
            }

            /**
             * Gets the value of the shippingNotificationContactPhoneNum property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShippingNotificationContactPhoneNum() {
                return shippingNotificationContactPhoneNum;
            }

            /**
             * Sets the value of the shippingNotificationContactPhoneNum property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShippingNotificationContactPhoneNum(String value) {
                this.shippingNotificationContactPhoneNum = value;
            }

            /**
             * Gets the value of the shippingNotificationLanguageCd property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getShippingNotificationLanguageCd() {
                return shippingNotificationLanguageCd;
            }

            /**
             * Sets the value of the shippingNotificationLanguageCd property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setShippingNotificationLanguageCd(String value) {
                this.shippingNotificationLanguageCd = value;
            }

            public String toString() {
                final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
                final StringBuilder buffer = new StringBuilder();
                append(null, buffer, strategy);
                return buffer.toString();
            }

            public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
                strategy.appendStart(locator, this, buffer);
                appendFields(locator, buffer, strategy);
                strategy.appendEnd(locator, this, buffer);
                return buffer;
            }

            public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
                {
                    IndividualName theContact;
                    theContact = this.getContact();
                    strategy.appendField(locator, this, "contact", buffer, theContact);
                }
                {
                    Address theShippingAddress;
                    theShippingAddress = this.getShippingAddress();
                    strategy.appendField(locator, this, "shippingAddress", buffer, theShippingAddress);
                }
                {
                    ElectronicContact theShippingNotificationEmail;
                    theShippingNotificationEmail = this.getShippingNotificationEmail();
                    strategy.appendField(locator, this, "shippingNotificationEmail", buffer, theShippingNotificationEmail);
                }
                {
                    String theShippingNotificationContactPhoneNum;
                    theShippingNotificationContactPhoneNum = this.getShippingNotificationContactPhoneNum();
                    strategy.appendField(locator, this, "shippingNotificationContactPhoneNum", buffer, theShippingNotificationContactPhoneNum);
                }
                {
                    String theShippingNotificationLanguageCd;
                    theShippingNotificationLanguageCd = this.getShippingNotificationLanguageCd();
                    strategy.appendField(locator, this, "shippingNotificationLanguageCd", buffer, theShippingNotificationLanguageCd);
                }
                return buffer;
            }

            public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
                if (!(object instanceof Order.PartialTransactionOption.WarehouseShipmentDetail)) {
                    return false;
                }
                if (this == object) {
                    return true;
                }
                final Order.PartialTransactionOption.WarehouseShipmentDetail that = ((Order.PartialTransactionOption.WarehouseShipmentDetail) object);
                {
                    IndividualName lhsContact;
                    lhsContact = this.getContact();
                    IndividualName rhsContact;
                    rhsContact = that.getContact();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "contact", lhsContact), LocatorUtils.property(thatLocator, "contact", rhsContact), lhsContact, rhsContact)) {
                        return false;
                    }
                }
                {
                    Address lhsShippingAddress;
                    lhsShippingAddress = this.getShippingAddress();
                    Address rhsShippingAddress;
                    rhsShippingAddress = that.getShippingAddress();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "shippingAddress", lhsShippingAddress), LocatorUtils.property(thatLocator, "shippingAddress", rhsShippingAddress), lhsShippingAddress, rhsShippingAddress)) {
                        return false;
                    }
                }
                {
                    ElectronicContact lhsShippingNotificationEmail;
                    lhsShippingNotificationEmail = this.getShippingNotificationEmail();
                    ElectronicContact rhsShippingNotificationEmail;
                    rhsShippingNotificationEmail = that.getShippingNotificationEmail();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "shippingNotificationEmail", lhsShippingNotificationEmail), LocatorUtils.property(thatLocator, "shippingNotificationEmail", rhsShippingNotificationEmail), lhsShippingNotificationEmail, rhsShippingNotificationEmail)) {
                        return false;
                    }
                }
                {
                    String lhsShippingNotificationContactPhoneNum;
                    lhsShippingNotificationContactPhoneNum = this.getShippingNotificationContactPhoneNum();
                    String rhsShippingNotificationContactPhoneNum;
                    rhsShippingNotificationContactPhoneNum = that.getShippingNotificationContactPhoneNum();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "shippingNotificationContactPhoneNum", lhsShippingNotificationContactPhoneNum), LocatorUtils.property(thatLocator, "shippingNotificationContactPhoneNum", rhsShippingNotificationContactPhoneNum), lhsShippingNotificationContactPhoneNum, rhsShippingNotificationContactPhoneNum)) {
                        return false;
                    }
                }
                {
                    String lhsShippingNotificationLanguageCd;
                    lhsShippingNotificationLanguageCd = this.getShippingNotificationLanguageCd();
                    String rhsShippingNotificationLanguageCd;
                    rhsShippingNotificationLanguageCd = that.getShippingNotificationLanguageCd();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "shippingNotificationLanguageCd", lhsShippingNotificationLanguageCd), LocatorUtils.property(thatLocator, "shippingNotificationLanguageCd", rhsShippingNotificationLanguageCd), lhsShippingNotificationLanguageCd, rhsShippingNotificationLanguageCd)) {
                        return false;
                    }
                }
                return true;
            }

            public boolean equals(Object object) {
                final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
                return equals(null, null, object, strategy);
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="transferToBillingAccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="transferFromBillingAccount" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="balanceAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "transferToBillingAccount",
        "transferFromBillingAccount",
        "balanceAmount"
    })
    public static class PrepaidMigrationBalanceTransfer
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        @XmlElement(required = true)
        protected String transferToBillingAccount;
        @XmlElement(required = true)
        protected String transferFromBillingAccount;
        protected double balanceAmount;

        /**
         * Gets the value of the transferToBillingAccount property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTransferToBillingAccount() {
            return transferToBillingAccount;
        }

        /**
         * Sets the value of the transferToBillingAccount property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTransferToBillingAccount(String value) {
            this.transferToBillingAccount = value;
        }

        /**
         * Gets the value of the transferFromBillingAccount property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTransferFromBillingAccount() {
            return transferFromBillingAccount;
        }

        /**
         * Sets the value of the transferFromBillingAccount property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTransferFromBillingAccount(String value) {
            this.transferFromBillingAccount = value;
        }

        /**
         * Gets the value of the balanceAmount property.
         * 
         */
        public double getBalanceAmount() {
            return balanceAmount;
        }

        /**
         * Sets the value of the balanceAmount property.
         * 
         */
        public void setBalanceAmount(double value) {
            this.balanceAmount = value;
        }

        public String toString() {
            final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
            final StringBuilder buffer = new StringBuilder();
            append(null, buffer, strategy);
            return buffer.toString();
        }

        public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            strategy.appendStart(locator, this, buffer);
            appendFields(locator, buffer, strategy);
            strategy.appendEnd(locator, this, buffer);
            return buffer;
        }

        public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            {
                String theTransferToBillingAccount;
                theTransferToBillingAccount = this.getTransferToBillingAccount();
                strategy.appendField(locator, this, "transferToBillingAccount", buffer, theTransferToBillingAccount);
            }
            {
                String theTransferFromBillingAccount;
                theTransferFromBillingAccount = this.getTransferFromBillingAccount();
                strategy.appendField(locator, this, "transferFromBillingAccount", buffer, theTransferFromBillingAccount);
            }
            {
                double theBalanceAmount;
                theBalanceAmount = (true?this.getBalanceAmount(): 0.0D);
                strategy.appendField(locator, this, "balanceAmount", buffer, theBalanceAmount);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof Order.PrepaidMigrationBalanceTransfer)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final Order.PrepaidMigrationBalanceTransfer that = ((Order.PrepaidMigrationBalanceTransfer) object);
            {
                String lhsTransferToBillingAccount;
                lhsTransferToBillingAccount = this.getTransferToBillingAccount();
                String rhsTransferToBillingAccount;
                rhsTransferToBillingAccount = that.getTransferToBillingAccount();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "transferToBillingAccount", lhsTransferToBillingAccount), LocatorUtils.property(thatLocator, "transferToBillingAccount", rhsTransferToBillingAccount), lhsTransferToBillingAccount, rhsTransferToBillingAccount)) {
                    return false;
                }
            }
            {
                String lhsTransferFromBillingAccount;
                lhsTransferFromBillingAccount = this.getTransferFromBillingAccount();
                String rhsTransferFromBillingAccount;
                rhsTransferFromBillingAccount = that.getTransferFromBillingAccount();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "transferFromBillingAccount", lhsTransferFromBillingAccount), LocatorUtils.property(thatLocator, "transferFromBillingAccount", rhsTransferFromBillingAccount), lhsTransferFromBillingAccount, rhsTransferFromBillingAccount)) {
                    return false;
                }
            }
            {
                double lhsBalanceAmount;
                lhsBalanceAmount = (true?this.getBalanceAmount(): 0.0D);
                double rhsBalanceAmount;
                rhsBalanceAmount = (true?that.getBalanceAmount(): 0.0D);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "balanceAmount", lhsBalanceAmount), LocatorUtils.property(thatLocator, "balanceAmount", rhsBalanceAmount), lhsBalanceAmount, rhsBalanceAmount)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object object) {
            final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
            return equals(null, null, object, strategy);
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="pricePlanCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="transactionActionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="billChargeThresholdConsentInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="callingCirclePhoneNumberList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}CallingCirclePhoneNumberList" minOccurs="0"/>
     *         &lt;element name="headOfficeCallingPhoneNumberList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="serviceItemList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ServiceItem" maxOccurs="50" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "pricePlanCode",
        "transactionActionCode",
        "billChargeThresholdConsentInd",
        "callingCirclePhoneNumberList",
        "headOfficeCallingPhoneNumberList",
        "serviceItemList"
    })
    public static class PricePlanAddons
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        @XmlElement(required = true)
        protected String pricePlanCode;
        @XmlElement(required = true)
        protected String transactionActionCode;
        protected Boolean billChargeThresholdConsentInd;
        protected CallingCirclePhoneNumberList callingCirclePhoneNumberList;
        protected List<String> headOfficeCallingPhoneNumberList;
        protected List<ServiceItem> serviceItemList;

        /**
         * Gets the value of the pricePlanCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPricePlanCode() {
            return pricePlanCode;
        }

        /**
         * Sets the value of the pricePlanCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPricePlanCode(String value) {
            this.pricePlanCode = value;
        }

        /**
         * Gets the value of the transactionActionCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTransactionActionCode() {
            return transactionActionCode;
        }

        /**
         * Sets the value of the transactionActionCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTransactionActionCode(String value) {
            this.transactionActionCode = value;
        }

        /**
         * Gets the value of the billChargeThresholdConsentInd property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isBillChargeThresholdConsentInd() {
            return billChargeThresholdConsentInd;
        }

        /**
         * Sets the value of the billChargeThresholdConsentInd property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setBillChargeThresholdConsentInd(Boolean value) {
            this.billChargeThresholdConsentInd = value;
        }

        /**
         * Gets the value of the callingCirclePhoneNumberList property.
         * 
         * @return
         *     possible object is
         *     {@link CallingCirclePhoneNumberList }
         *     
         */
        public CallingCirclePhoneNumberList getCallingCirclePhoneNumberList() {
            return callingCirclePhoneNumberList;
        }

        /**
         * Sets the value of the callingCirclePhoneNumberList property.
         * 
         * @param value
         *     allowed object is
         *     {@link CallingCirclePhoneNumberList }
         *     
         */
        public void setCallingCirclePhoneNumberList(CallingCirclePhoneNumberList value) {
            this.callingCirclePhoneNumberList = value;
        }

        /**
         * Gets the value of the headOfficeCallingPhoneNumberList property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the headOfficeCallingPhoneNumberList property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getHeadOfficeCallingPhoneNumberList().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getHeadOfficeCallingPhoneNumberList() {
            if (headOfficeCallingPhoneNumberList == null) {
                headOfficeCallingPhoneNumberList = new ArrayList<String>();
            }
            return this.headOfficeCallingPhoneNumberList;
        }

        /**
         * Gets the value of the serviceItemList property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the serviceItemList property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getServiceItemList().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ServiceItem }
         * 
         * 
         */
        public List<ServiceItem> getServiceItemList() {
            if (serviceItemList == null) {
                serviceItemList = new ArrayList<ServiceItem>();
            }
            return this.serviceItemList;
        }

        /**
         * Sets the value of the headOfficeCallingPhoneNumberList property.
         * 
         * @param headOfficeCallingPhoneNumberList
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHeadOfficeCallingPhoneNumberList(List<String> headOfficeCallingPhoneNumberList) {
            this.headOfficeCallingPhoneNumberList = headOfficeCallingPhoneNumberList;
        }

        /**
         * Sets the value of the serviceItemList property.
         * 
         * @param serviceItemList
         *     allowed object is
         *     {@link ServiceItem }
         *     
         */
        public void setServiceItemList(List<ServiceItem> serviceItemList) {
            this.serviceItemList = serviceItemList;
        }

        public String toString() {
            final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
            final StringBuilder buffer = new StringBuilder();
            append(null, buffer, strategy);
            return buffer.toString();
        }

        public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            strategy.appendStart(locator, this, buffer);
            appendFields(locator, buffer, strategy);
            strategy.appendEnd(locator, this, buffer);
            return buffer;
        }

        public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            {
                String thePricePlanCode;
                thePricePlanCode = this.getPricePlanCode();
                strategy.appendField(locator, this, "pricePlanCode", buffer, thePricePlanCode);
            }
            {
                String theTransactionActionCode;
                theTransactionActionCode = this.getTransactionActionCode();
                strategy.appendField(locator, this, "transactionActionCode", buffer, theTransactionActionCode);
            }
            {
                Boolean theBillChargeThresholdConsentInd;
                theBillChargeThresholdConsentInd = this.isBillChargeThresholdConsentInd();
                strategy.appendField(locator, this, "billChargeThresholdConsentInd", buffer, theBillChargeThresholdConsentInd);
            }
            {
                CallingCirclePhoneNumberList theCallingCirclePhoneNumberList;
                theCallingCirclePhoneNumberList = this.getCallingCirclePhoneNumberList();
                strategy.appendField(locator, this, "callingCirclePhoneNumberList", buffer, theCallingCirclePhoneNumberList);
            }
            {
                List<String> theHeadOfficeCallingPhoneNumberList;
                theHeadOfficeCallingPhoneNumberList = (((this.headOfficeCallingPhoneNumberList!= null)&&(!this.headOfficeCallingPhoneNumberList.isEmpty()))?this.getHeadOfficeCallingPhoneNumberList():null);
                strategy.appendField(locator, this, "headOfficeCallingPhoneNumberList", buffer, theHeadOfficeCallingPhoneNumberList);
            }
            {
                List<ServiceItem> theServiceItemList;
                theServiceItemList = (((this.serviceItemList!= null)&&(!this.serviceItemList.isEmpty()))?this.getServiceItemList():null);
                strategy.appendField(locator, this, "serviceItemList", buffer, theServiceItemList);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof Order.PricePlanAddons)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final Order.PricePlanAddons that = ((Order.PricePlanAddons) object);
            {
                String lhsPricePlanCode;
                lhsPricePlanCode = this.getPricePlanCode();
                String rhsPricePlanCode;
                rhsPricePlanCode = that.getPricePlanCode();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "pricePlanCode", lhsPricePlanCode), LocatorUtils.property(thatLocator, "pricePlanCode", rhsPricePlanCode), lhsPricePlanCode, rhsPricePlanCode)) {
                    return false;
                }
            }
            {
                String lhsTransactionActionCode;
                lhsTransactionActionCode = this.getTransactionActionCode();
                String rhsTransactionActionCode;
                rhsTransactionActionCode = that.getTransactionActionCode();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "transactionActionCode", lhsTransactionActionCode), LocatorUtils.property(thatLocator, "transactionActionCode", rhsTransactionActionCode), lhsTransactionActionCode, rhsTransactionActionCode)) {
                    return false;
                }
            }
            {
                Boolean lhsBillChargeThresholdConsentInd;
                lhsBillChargeThresholdConsentInd = this.isBillChargeThresholdConsentInd();
                Boolean rhsBillChargeThresholdConsentInd;
                rhsBillChargeThresholdConsentInd = that.isBillChargeThresholdConsentInd();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "billChargeThresholdConsentInd", lhsBillChargeThresholdConsentInd), LocatorUtils.property(thatLocator, "billChargeThresholdConsentInd", rhsBillChargeThresholdConsentInd), lhsBillChargeThresholdConsentInd, rhsBillChargeThresholdConsentInd)) {
                    return false;
                }
            }
            {
                CallingCirclePhoneNumberList lhsCallingCirclePhoneNumberList;
                lhsCallingCirclePhoneNumberList = this.getCallingCirclePhoneNumberList();
                CallingCirclePhoneNumberList rhsCallingCirclePhoneNumberList;
                rhsCallingCirclePhoneNumberList = that.getCallingCirclePhoneNumberList();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "callingCirclePhoneNumberList", lhsCallingCirclePhoneNumberList), LocatorUtils.property(thatLocator, "callingCirclePhoneNumberList", rhsCallingCirclePhoneNumberList), lhsCallingCirclePhoneNumberList, rhsCallingCirclePhoneNumberList)) {
                    return false;
                }
            }
            {
                List<String> lhsHeadOfficeCallingPhoneNumberList;
                lhsHeadOfficeCallingPhoneNumberList = (((this.headOfficeCallingPhoneNumberList!= null)&&(!this.headOfficeCallingPhoneNumberList.isEmpty()))?this.getHeadOfficeCallingPhoneNumberList():null);
                List<String> rhsHeadOfficeCallingPhoneNumberList;
                rhsHeadOfficeCallingPhoneNumberList = (((that.headOfficeCallingPhoneNumberList!= null)&&(!that.headOfficeCallingPhoneNumberList.isEmpty()))?that.getHeadOfficeCallingPhoneNumberList():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "headOfficeCallingPhoneNumberList", lhsHeadOfficeCallingPhoneNumberList), LocatorUtils.property(thatLocator, "headOfficeCallingPhoneNumberList", rhsHeadOfficeCallingPhoneNumberList), lhsHeadOfficeCallingPhoneNumberList, rhsHeadOfficeCallingPhoneNumberList)) {
                    return false;
                }
            }
            {
                List<ServiceItem> lhsServiceItemList;
                lhsServiceItemList = (((this.serviceItemList!= null)&&(!this.serviceItemList.isEmpty()))?this.getServiceItemList():null);
                List<ServiceItem> rhsServiceItemList;
                rhsServiceItemList = (((that.serviceItemList!= null)&&(!that.serviceItemList.isEmpty()))?that.getServiceItemList():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceItemList", lhsServiceItemList), LocatorUtils.property(thatLocator, "serviceItemList", rhsServiceItemList), lhsServiceItemList, rhsServiceItemList)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object object) {
            final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
            return equals(null, null, object, strategy);
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="deviceType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "deviceType"
    })
    public static class SimOnlyTransaction
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        protected String deviceType;

        /**
         * Gets the value of the deviceType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDeviceType() {
            return deviceType;
        }

        /**
         * Sets the value of the deviceType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDeviceType(String value) {
            this.deviceType = value;
        }

        public String toString() {
            final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
            final StringBuilder buffer = new StringBuilder();
            append(null, buffer, strategy);
            return buffer.toString();
        }

        public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            strategy.appendStart(locator, this, buffer);
            appendFields(locator, buffer, strategy);
            strategy.appendEnd(locator, this, buffer);
            return buffer;
        }

        public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            {
                String theDeviceType;
                theDeviceType = this.getDeviceType();
                strategy.appendField(locator, this, "deviceType", buffer, theDeviceType);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof Order.SimOnlyTransaction)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final Order.SimOnlyTransaction that = ((Order.SimOnlyTransaction) object);
            {
                String lhsDeviceType;
                lhsDeviceType = this.getDeviceType();
                String rhsDeviceType;
                rhsDeviceType = that.getDeviceType();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "deviceType", lhsDeviceType), LocatorUtils.property(thatLocator, "deviceType", rhsDeviceType), lhsDeviceType, rhsDeviceType)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object object) {
            final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
            return equals(null, null, object, strategy);
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="associationActionCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="primarySubscriberMobileId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="primarySubscriberPhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "associationActionCd",
        "primarySubscriberMobileId",
        "primarySubscriberPhoneNum"
    })
    public static class SubscriberAssociation
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        protected String associationActionCd;
        protected String primarySubscriberMobileId;
        protected String primarySubscriberPhoneNum;

        /**
         * Gets the value of the associationActionCd property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAssociationActionCd() {
            return associationActionCd;
        }

        /**
         * Sets the value of the associationActionCd property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAssociationActionCd(String value) {
            this.associationActionCd = value;
        }

        /**
         * Gets the value of the primarySubscriberMobileId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPrimarySubscriberMobileId() {
            return primarySubscriberMobileId;
        }

        /**
         * Sets the value of the primarySubscriberMobileId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPrimarySubscriberMobileId(String value) {
            this.primarySubscriberMobileId = value;
        }

        /**
         * Gets the value of the primarySubscriberPhoneNum property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPrimarySubscriberPhoneNum() {
            return primarySubscriberPhoneNum;
        }

        /**
         * Sets the value of the primarySubscriberPhoneNum property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPrimarySubscriberPhoneNum(String value) {
            this.primarySubscriberPhoneNum = value;
        }

        public String toString() {
            final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
            final StringBuilder buffer = new StringBuilder();
            append(null, buffer, strategy);
            return buffer.toString();
        }

        public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            strategy.appendStart(locator, this, buffer);
            appendFields(locator, buffer, strategy);
            strategy.appendEnd(locator, this, buffer);
            return buffer;
        }

        public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            {
                String theAssociationActionCd;
                theAssociationActionCd = this.getAssociationActionCd();
                strategy.appendField(locator, this, "associationActionCd", buffer, theAssociationActionCd);
            }
            {
                String thePrimarySubscriberMobileId;
                thePrimarySubscriberMobileId = this.getPrimarySubscriberMobileId();
                strategy.appendField(locator, this, "primarySubscriberMobileId", buffer, thePrimarySubscriberMobileId);
            }
            {
                String thePrimarySubscriberPhoneNum;
                thePrimarySubscriberPhoneNum = this.getPrimarySubscriberPhoneNum();
                strategy.appendField(locator, this, "primarySubscriberPhoneNum", buffer, thePrimarySubscriberPhoneNum);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof Order.SubscriberAssociation)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final Order.SubscriberAssociation that = ((Order.SubscriberAssociation) object);
            {
                String lhsAssociationActionCd;
                lhsAssociationActionCd = this.getAssociationActionCd();
                String rhsAssociationActionCd;
                rhsAssociationActionCd = that.getAssociationActionCd();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "associationActionCd", lhsAssociationActionCd), LocatorUtils.property(thatLocator, "associationActionCd", rhsAssociationActionCd), lhsAssociationActionCd, rhsAssociationActionCd)) {
                    return false;
                }
            }
            {
                String lhsPrimarySubscriberMobileId;
                lhsPrimarySubscriberMobileId = this.getPrimarySubscriberMobileId();
                String rhsPrimarySubscriberMobileId;
                rhsPrimarySubscriberMobileId = that.getPrimarySubscriberMobileId();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "primarySubscriberMobileId", lhsPrimarySubscriberMobileId), LocatorUtils.property(thatLocator, "primarySubscriberMobileId", rhsPrimarySubscriberMobileId), lhsPrimarySubscriberMobileId, rhsPrimarySubscriberMobileId)) {
                    return false;
                }
            }
            {
                String lhsPrimarySubscriberPhoneNum;
                lhsPrimarySubscriberPhoneNum = this.getPrimarySubscriberPhoneNum();
                String rhsPrimarySubscriberPhoneNum;
                rhsPrimarySubscriberPhoneNum = that.getPrimarySubscriberPhoneNum();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "primarySubscriberPhoneNum", lhsPrimarySubscriberPhoneNum), LocatorUtils.property(thatLocator, "primarySubscriberPhoneNum", rhsPrimarySubscriberPhoneNum), lhsPrimarySubscriberPhoneNum, rhsPrimarySubscriberPhoneNum)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object object) {
            final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
            return equals(null, null, object, strategy);
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="pricePlanCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="serviceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="billCredit" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/WirelessSubscriberOfferInformationTypes_v2}BillCredit" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "pricePlanCode",
        "serviceCode",
        "billCredit"
    })
    public static class Sweetener
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        protected String pricePlanCode;
        protected String serviceCode;
        protected BillCredit billCredit;

        /**
         * Gets the value of the pricePlanCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPricePlanCode() {
            return pricePlanCode;
        }

        /**
         * Sets the value of the pricePlanCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPricePlanCode(String value) {
            this.pricePlanCode = value;
        }

        /**
         * Gets the value of the serviceCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getServiceCode() {
            return serviceCode;
        }

        /**
         * Sets the value of the serviceCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setServiceCode(String value) {
            this.serviceCode = value;
        }

        /**
         * Gets the value of the billCredit property.
         * 
         * @return
         *     possible object is
         *     {@link BillCredit }
         *     
         */
        public BillCredit getBillCredit() {
            return billCredit;
        }

        /**
         * Sets the value of the billCredit property.
         * 
         * @param value
         *     allowed object is
         *     {@link BillCredit }
         *     
         */
        public void setBillCredit(BillCredit value) {
            this.billCredit = value;
        }

        public String toString() {
            final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
            final StringBuilder buffer = new StringBuilder();
            append(null, buffer, strategy);
            return buffer.toString();
        }

        public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            strategy.appendStart(locator, this, buffer);
            appendFields(locator, buffer, strategy);
            strategy.appendEnd(locator, this, buffer);
            return buffer;
        }

        public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
            {
                String thePricePlanCode;
                thePricePlanCode = this.getPricePlanCode();
                strategy.appendField(locator, this, "pricePlanCode", buffer, thePricePlanCode);
            }
            {
                String theServiceCode;
                theServiceCode = this.getServiceCode();
                strategy.appendField(locator, this, "serviceCode", buffer, theServiceCode);
            }
            {
                BillCredit theBillCredit;
                theBillCredit = this.getBillCredit();
                strategy.appendField(locator, this, "billCredit", buffer, theBillCredit);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof Order.Sweetener)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final Order.Sweetener that = ((Order.Sweetener) object);
            {
                String lhsPricePlanCode;
                lhsPricePlanCode = this.getPricePlanCode();
                String rhsPricePlanCode;
                rhsPricePlanCode = that.getPricePlanCode();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "pricePlanCode", lhsPricePlanCode), LocatorUtils.property(thatLocator, "pricePlanCode", rhsPricePlanCode), lhsPricePlanCode, rhsPricePlanCode)) {
                    return false;
                }
            }
            {
                String lhsServiceCode;
                lhsServiceCode = this.getServiceCode();
                String rhsServiceCode;
                rhsServiceCode = that.getServiceCode();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceCode", lhsServiceCode), LocatorUtils.property(thatLocator, "serviceCode", rhsServiceCode), lhsServiceCode, rhsServiceCode)) {
                    return false;
                }
            }
            {
                BillCredit lhsBillCredit;
                lhsBillCredit = this.getBillCredit();
                BillCredit rhsBillCredit;
                rhsBillCredit = that.getBillCredit();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "billCredit", lhsBillCredit), LocatorUtils.property(thatLocator, "billCredit", rhsBillCredit), lhsBillCredit, rhsBillCredit)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object object) {
            final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
            return equals(null, null, object, strategy);
        }

    }

}
