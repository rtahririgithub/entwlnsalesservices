package com.telus.csm.ewlnsms.core.transformer;

import static com.telus.csm.ewlnsc.util.Constants.ACQUISITION_TYPE;
import static com.telus.csm.ewlnsc.util.Constants.CONTRACT_TERM;
import static com.telus.csm.ewlnsc.util.Constants.D2C_PARTNER;
import static com.telus.csm.ewlnsc.util.Constants.DATE_FORMAT_PRODUCT_REQUEST;
import static com.telus.csm.ewlnsc.util.Constants.DELIVERY_METHOD;
import static com.telus.csm.ewlnsc.util.Constants.EQUIPMENT;
import static com.telus.csm.ewlnsc.util.Constants.EQUIPMENT_DESCRIPTION;
import static com.telus.csm.ewlnsc.util.Constants.GENERAL_PRODUCT_INFO;
import static com.telus.csm.ewlnsc.util.Constants.MIC;
import static com.telus.csm.ewlnsc.util.Constants.OMS_PRODUCT_HSIC;
import static com.telus.csm.ewlnsc.util.Constants.OMS_PRODUCT_SING;
import static com.telus.csm.ewlnsc.util.Constants.OMS_PRODUCT_TTV;
import static com.telus.csm.ewlnsc.util.Constants.ORDER_ACTION_TYPE;
import static com.telus.csm.ewlnsc.util.Constants.ORDER_ACTION_TYPE_CREATE;
import static com.telus.csm.ewlnsc.util.Constants.ORDER_ACTION_TYPE_MODIFY;
import static com.telus.csm.ewlnsc.util.Constants.PRODUCT_INFORMATION;
import static com.telus.csm.ewlnsc.util.Constants.PRODUCT_REQUEST_ID;
import static com.telus.csm.ewlnsc.util.Constants.PRODUCT_TEMPLATE_ID;
import static com.telus.csm.ewlnsc.util.Constants.SERVICE_PLAN;
import static com.telus.csm.ewlnsc.util.Constants.SERVICE_REQUEST_DATE;
import static com.telus.csm.ewlnsc.util.Constants.SET_TOP_BOX_MODEL;
import static com.telus.csm.ewlnsc.util.Constants.TELUS;
import static com.telus.csm.ewlnsc.util.Constants.TELUS_BRAND_ID;
import static com.telus.csm.ewlnsc.util.Constants.TTVQ_ADULT_PRESENT;
import static com.telus.csm.ewlnsc.util.Constants.TTVQ_CBR;
import static com.telus.csm.ewlnsc.util.Constants.TTVQ_CBRALT;
import static com.telus.csm.ewlnsc.util.Constants.TTVQ_ON_SITE_MAN;
import static com.telus.csm.ewlnsc.util.Constants.TTVQ_RENT_OR_OWN;
import static com.telus.csm.ewlnsc.util.Constants.TTVQ_TV_SET;
import static com.telus.csm.ewlnsc.util.Constants.TTV_QUESTIONNAIRE;
import static com.telus.csm.ewlnsc.util.Constants.INSTALL_TYPE;
import static com.telus.csm.ewlnsc.util.Constants.RECOMMENDED_INSTALL_TYPE;
import static com.telus.csm.ewlnsc.util.Constants.MAX_WORK_TIME;
import static com.telus.csm.ewlnsc.util.Constants.MIN_WORK_TIME;
import static com.telus.csm.ewlnsc.util.Constants.DEFAULT_WORK_BUCKET; 
import static com.telus.csm.ewlnsc.util.Constants.DEFAULT_WORK_TIME;
import static com.telus.csm.ewlnsc.util.Constants.BOOKING_REQUIRED;
import static com.telus.csm.ewlnsc.util.Constants.PRODUCT_COMPONENT_NAME;
import static com.telus.csm.ewlnsc.util.Constants.PRODUCT_COMPONENT_CATALOG_ATTRIBUTE_ID;
import static com.telus.csm.ewlnsc.util.Constants.PRODUCT_COMPONENT_CATALOG_ID;
import static com.telus.csm.ewlnsc.util.Constants.PRODUCT_COMPONENT_ATTRIBUTE_NAME;
import static com.telus.csm.ewlnsc.util.Constants.UNDERSCORE;
import static com.telus.csm.ewlnsc.util.Constants.PRODUCT_CHARACTERISTICS_EXTERNAL_FILE_NAME;
import static com.telus.csm.ewlnsc.util.Constants.DELIVERY_METHOD_INSTALLER;
import static com.telus.csm.ewlnsc.util.Constants.DELIVERY_METHOD_INSTORE;
import static com.telus.csm.ewlnsc.util.Constants.DELIVERY_METHOD_SHIPPED;
import static com.telus.csm.ewlnsc.util.Constants.DELIVERY_METHOD_INS;
import static com.telus.csm.ewlnsc.util.Constants.DELIVERY_METHOD_CST;
import static com.telus.csm.ewlnsc.util.Constants.DELIVERY_METHOD_SHP;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.ACTIVATION;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.HSIC;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.PR;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.RENEWAL;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.SING;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.TTV;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.*;

import java.io.InputStream;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.adapter.ccm.domain.GetFullCustomerInfoAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.wbk.domain.ConfirmBookingAdapterRequest;
import com.telus.csm.ewlnsc.adapter.wbk.domain.ConfirmBookingAdapterRequestBody;
import com.telus.csm.ewlnsc.adapter.domain.GetOfferListByOfferIdentifierListAdapterRequest;
import com.telus.csm.ewlnsc.adapter.oqs.domain.GetProductsByCustomerIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.CreateOrderAdapterRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.CreateOrderAdapterRequestBody;
import com.telus.csm.ewlnsc.adapter.woscs.domain.PartyInteractionRole;
import com.telus.csm.ewlnsc.adapter.woscs.domain.Product;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductCharacteristic;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductCharacteristicNameValue;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductComponent;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductOffering;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductOrderItem;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.EnterpriseSalesServiceUtil;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsms.core.domain.CreateWLNOrderCoreRequest;
import com.telus.csm.ewlnsms.core.domain.ProductAttributes;
import com.telus.csm.ewlnsms.core.domain.SalesOrderCommonObjectDO;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.CreateWirelineSalesOrder;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.AccessCriteria;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProductSpecificationList;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.base_types_2_0.ValueType;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.EnterpriseOrder.ServiceAddressDetail;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.HomePhoneComponent;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.HsicComponent;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductComponentIdentifier;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductOrderHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.TelevisionComponent;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.TelevisionQuestionairre;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineAddOnHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineEquipmentHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferHeader;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.ChannelOrgSummaryInfo;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.ContactAddress;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.ContactDetails;
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.OutletInfo;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Discount;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.OfferIdentifier;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.OfferProduct;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueItem;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineEquipment;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineEquipmentItem;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.CompositeProductSpecificationCharacteristicValue;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.ProductSpecification;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.ProductSpecificationCharacteristic;

import weblogic.jms.interception.service;


public class CreateWLNOrderTransformer {

	protected static final Logger LOGGER = Logger.getLogger(CreateWLNOrderTransformer.class);
	protected static final LoggerUtil loggerUtil = LoggerUtil.getLogger(CreateWLNOrderTransformer.class);
	
	private CreateWLNOrderTransformer(){
		
	}
	
	public static CreateWLNOrderCoreRequest transform(CreateWirelineSalesOrder request){
		CreateWLNOrderCoreRequest coreRequest = new CreateWLNOrderCoreRequest();
		coreRequest.setOperationHeader(request.getOperationHeader());
		coreRequest.setCreateWirelineSalesOrder(request.getCreateWirelineSalesOrder());
		
		String salesPersonRoleCd = "";
		if (request.getOperationHeader() != null && request.getOperationHeader().getSalesPersonRoleCode() != null){
			salesPersonRoleCd = request.getOperationHeader().getSalesPersonRoleCode();
		}
		if (salesPersonRoleCd.equalsIgnoreCase(D2C_PARTNER)){
			coreRequest.setOutboundChannel(true);
			coreRequest.setInboundChannel(false);
		} else{
			coreRequest.setOutboundChannel(false);
			coreRequest.setInboundChannel(true);
		} 
		
		return coreRequest;
	}
	
	public static CreateOrderAdapterRequest transform(SalesOrderCommonObjectDO param){
		CreateOrderAdapterRequest request = new CreateOrderAdapterRequest();
		CreateOrderAdapterRequestBody requestBody = request.getBody();
		
		request.setCustomerid(param.getSalesOrder().getCustomerDetail().getCustomerId());
		requestBody.setType(TYPE_DE); //will be defaulted to "DE" ; may have "RE" in future 
		request.setPrevalidation(Boolean.FALSE.toString()); //review with Syd 
		request.setReturnorderdetails(Boolean.FALSE.toString()); //false, true - see the actual order created in OP for debug purpose
		request.setOrdercreation(Boolean.TRUE.toString()); //review with Syd
		
		requestBody.setPartyInteractionRole(populatePartyInteractionRole(param));

		// productOrderItems
		if (param.getSalesOrder().getHomePhoneProduct() != null) {
			requestBody.addProductOrderItem(populateHomePhoneProduct(param));
		}
		
		if (param.getSalesOrder().getInternetProduct() != null){
			requestBody.addProductOrderItem(populateInternetProduct(param));
		}
		
		if (param.getSalesOrder().getTelevisionProduct() != null){
			requestBody.addProductOrderItem(populateTelevisionProduct(param));
		}
		
		return request;
	}

	private static PartyInteractionRole populatePartyInteractionRole(SalesOrderCommonObjectDO param) {
		PartyInteractionRole result = new PartyInteractionRole();
		List<ProductCharacteristicNameValue> productCharacteristics = new ArrayList<ProductCharacteristicNameValue>();
		result.setProductCharacteristics(productCharacteristics);
		
		ProductCharacteristicNameValue salesRep = new ProductCharacteristicNameValue();
		salesRep.setValue(param.getOperationHeader().getUserProfile().getLoginId());
		salesRep.setName(SALES_REP);
		productCharacteristics.add(salesRep);
		
		ProductCharacteristicNameValue externalRefNum = new ProductCharacteristicNameValue();
		if (!StringUtils.isEmpty(param.getOperationHeader().getSalesId())){
			externalRefNum.setValue(param.getOperationHeader().getSalesId());
		}else if (!StringUtils.isEmpty(param.getOperationHeader().getSalesTransactionId())){
			externalRefNum.setValue(param.getOperationHeader().getSalesTransactionId());
		}
		
		externalRefNum.setName(EXTERNAL_REF_NUM);
		productCharacteristics.add(externalRefNum);
		
		ProductCharacteristicNameValue preferredContactMethod = new ProductCharacteristicNameValue();
		//TODO validation required EM/LETTER
		String preferedContactMethodStr = "";
		if (param.getSalesOrder() != null && param.getSalesOrder().getOrderContext() != null && param.getSalesOrder().getOrderContext().getPreferredContactMethodTxt() != null){
			preferedContactMethodStr = param.getSalesOrder().getOrderContext().getPreferredContactMethodTxt().toString(); //TODO review xsd on data type
		}
		
		if (EMAIL.equals(preferedContactMethodStr)){
			preferredContactMethod.setValue("EM");
			preferredContactMethod.setName(PREFERRED_CONTACT_METHOD);
			productCharacteristics.add(preferredContactMethod);
			
			ProductCharacteristicNameValue emailContactMethod = new ProductCharacteristicNameValue();
			emailContactMethod.setValue(param.getSalesOrder().getOrderContext().getOrderEmailAddress().getElectronicAddress());
			emailContactMethod.setName(EMAIL.toLowerCase());
			productCharacteristics.add(emailContactMethod);
		}else if (LETTER.equals(preferedContactMethodStr)){
			preferredContactMethod.setValue("LE"); //TODO: change this later
			preferredContactMethod.setName(PREFERRED_CONTACT_METHOD);
			productCharacteristics.add(preferredContactMethod);
		}
		
		ProductCharacteristicNameValue csagProdChar = new ProductCharacteristicNameValue();
		csagProdChar.setValue(CSAG);
		csagProdChar.setName(CONTACT_CATEGORY);
		productCharacteristics.add(csagProdChar);
		
		ProductCharacteristicNameValue cuProdChar = new ProductCharacteristicNameValue();
		cuProdChar.setValue(CU);
		cuProdChar.setName(CONTACT_TYPE);
		productCharacteristics.add(cuProdChar);
		
		ProductCharacteristicNameValue fnProdChar = new ProductCharacteristicNameValue();
		if (param.getCustomerInfoDO() != null && param.getCustomerInfoDO().getFullCustomer() != null && !param.getCustomerInfoDO().getFullCustomer().getNameList().isEmpty()) {
			fnProdChar.setValue(param.getCustomerInfoDO().getFullCustomer().getNameList().get(0).getFirstName());
			fnProdChar.setName(FIRST_NAME);
			productCharacteristics.add(fnProdChar);
			
			ProductCharacteristicNameValue lnProdChar = new ProductCharacteristicNameValue();
			lnProdChar.setValue(param.getCustomerInfoDO().getFullCustomer().getNameList().get(0).getLastName());
			lnProdChar.setName(LAST_NAME);
			productCharacteristics.add(lnProdChar);
		}
		
		return result;
	}

	private static ProductOrderItem populateHomePhoneProduct(SalesOrderCommonObjectDO param) {
		
		ProductOrderItem result = populateCommonProductOrderItem(SING, param, param.getSalesOrder().getHomePhoneProduct().getProductOrderHeader());
		
		return result;
	}

	private static ProductOrderItem populateInternetProduct(SalesOrderCommonObjectDO param) {
		
		ProductOrderItem result = populateCommonProductOrderItem(HSIC, param, param.getSalesOrder().getInternetProduct().getProductOrderHeader());
		Product thisProduct = result.getProduct();
		
		// equipment
		HsicComponent internetProduct = param.getSalesOrder().getInternetProduct();
		for(WirelineEquipmentHeader wlnEquipment : internetProduct.getEquipmentList()){
			ProductComponent equipComp = populateProductEquipment(wlnEquipment,param,HSIC);
			thisProduct.addProductComponent(equipComp);
		}
		
		// hsPack
		final ProductComponent hspackComp = getHsPackComp(param);
		thisProduct.addProductComponent(hspackComp);

		return result;
	}

	private static ProductOrderItem populateTelevisionProduct(SalesOrderCommonObjectDO param) {
		
		ProductOrderItem result = populateCommonProductOrderItem(TTV, param, param.getSalesOrder().getTelevisionProduct().getProductOrderHeader());
		Product thisProduct = result.getProduct();
		
		// equipment
		TelevisionComponent ttvProduct = param.getSalesOrder().getTelevisionProduct();
		int idx = 0;
		for (WirelineEquipmentHeader wlnEquipment : ttvProduct.getEquipmentList()) {
			ProductComponent equipComp = populateProductEquipment(wlnEquipment, param, TTV);
			equipComp.setParentProductSerialNumber("PSEUDO_AUTO_FILLED_" + idx++);
			ProductComponent parentEquipComp = new ProductComponent();
			parentEquipComp.setActionType("AD");
			parentEquipComp.setCatalogId("33");
			parentEquipComp.setProductSerialNumber(equipComp.getParentProductSerialNumber());
			parentEquipComp.setName("Registration Number");
			ProductCharacteristic descriptionCharacteristic = new ProductCharacteristic();
			descriptionCharacteristic.setCatalogAttributeId("30699");
			descriptionCharacteristic.setName("setTopBoxModel");
			descriptionCharacteristic.setValue(""); //leave this blank for the POC eventually we will get this from PODS / getEquipmentSummary operation 
			parentEquipComp.setProductCharacteristics(Arrays.asList(descriptionCharacteristic));

			thisProduct.addProductComponent(parentEquipComp);
			thisProduct.addProductComponent(equipComp);					
		}
		
		// addon
		for (ProductComponent ttvAddon : populateTtvAddons(param)) {
			thisProduct.addProductComponent(ttvAddon);
		}

		// ttvQuestionnaire
		thisProduct.addProductComponent(getTtvQuestionnaireComponent(param));

		return result;
	}

	private static ProductOrderItem populateCommonProductOrderItem(String serviceType, SalesOrderCommonObjectDO param,
			ProductOrderHeader productOrderHeader) {

		ProductOrderItem result = new ProductOrderItem();
		result.setAction(getAction(productOrderHeader.getProductOrderType().getProductOrderTypeCd()));

		Product thisProduct = new Product();
		result.setProduct(thisProduct);

		WirelineOfferProduct offerProduct = null;
		if (param.getOfferDO() != null && param.getOfferDO().getOfferList() != null && !param.getOfferDO().getOfferList().isEmpty() && param.getOfferDO().getOfferList().get(0).getOfferProduct() != null){
			offerProduct = getProductByProductTypeCd(param.getOfferDO().getOfferList().get(0).getOfferProduct(), serviceType);
		}
		
		if (offerProduct !=  null){
			if (offerProduct.getMainComponentIdentifier() != null){
				thisProduct.setCatalogId(offerProduct.getMainComponentIdentifier().getExternalProductCatalogId());
			}
			thisProduct.setServiceType(serviceType);

			// set Product Components
			
			ProductComponent generalProductInfo = new ProductComponent();
			generalProductInfo.setName("generalProductInfo");
			
			ProductCharacteristic characteristicGenProdInfo = new ProductCharacteristic();
			//characteristicGenProdInfo.setCatalogAttributeId("0"); // TODO hard code based on prod code
			characteristicGenProdInfo.setName(OFFER_CATALOG_ID);
			if (offerProduct.getProductTemplateIdentifier() != null){
				characteristicGenProdInfo.setValue(offerProduct.getProductTemplateIdentifier().getExternalProductCatalogId());
			}
			generalProductInfo.addProductCharacteristic(characteristicGenProdInfo);
			thisProduct.addProductComponent(generalProductInfo);
			//
			ProductComponent orderActionAttr = new ProductComponent();
			orderActionAttr.setName(ORDER_ACTION_ATTRIBUTES);
			
			ProductCharacteristic characteristicOrderActionAttr = new ProductCharacteristic();
			characteristicOrderActionAttr.setName(BYPASS_CLEARANCE_IND);
			characteristicOrderActionAttr.setValue(Boolean.TRUE.toString());
			orderActionAttr.addProductCharacteristic(characteristicOrderActionAttr);
			thisProduct.addProductComponent(orderActionAttr);
			//
			ProductComponent serviceAddress = new ProductComponent();
			serviceAddress.setName(SERVICE_ADDRESS);
			
			ProductCharacteristic characteristicState = new ProductCharacteristic();
			characteristicState.setName(STATE);
			boolean isServiceAddressDetailNull = true;
			if (param.getSalesOrder() != null && param.getSalesOrder().getServiceAddressDetail() != null){
				characteristicState.setValue(param.getSalesOrder().getServiceAddressDetail().getSelectedProvinceCd());
				isServiceAddressDetailNull = false;
			}
			ProductCharacteristic characteristicFms = new ProductCharacteristic();
			characteristicFms.setName(FMS_ADDRESS_ID);
			if (!isServiceAddressDetailNull){
				characteristicFms.setValue(param.getSalesOrder().getServiceAddressDetail().getFmsAddressId());
			}
			serviceAddress.addProductCharacteristic(characteristicState);
			serviceAddress.addProductCharacteristic(characteristicFms);
			thisProduct.addProductComponent(serviceAddress);
			//
			ProductComponent productInformation = new ProductComponent();
			productInformation.setName(PRODUCT_INFORMATION);
			ProductOffering productOffering = new ProductOffering(); // TODO add id bill discount exit in CSO request and match (parent and cild) with the one from ois; always use external id
			productOffering.setActionType(AD);
			productOffering.setCatalogId(getCatalogIdForProductOffering(serviceType,param,offerProduct));
			productInformation.addProductOffering(productOffering); // use a loop based on cso list
			thisProduct.addProductComponent(productInformation);
		}

		return result;
	}

	private static String getCatalogIdForProductOffering(String serviceType, SalesOrderCommonObjectDO param,WirelineOfferProduct offerProduct) {
		String catalogId=null;
		String parentCatalogId = null;
		String externalCatalogId=null;
		if (serviceType.equalsIgnoreCase(HSIC)) {
			if (param.getSalesOrder().getInternetProduct().getBillDiscountList() != null && !param.getSalesOrder().getInternetProduct().getBillDiscountList().isEmpty()) {
				for (ProductComponentIdentifier productBillDiscount : param.getSalesOrder().getInternetProduct().getBillDiscountList()) {
					catalogId = productBillDiscount.getProductCatalogueIdentifier();
					parentCatalogId = productBillDiscount.getParentProductCatalogueIdentifier();
					externalCatalogId = getExternalCatalogIdFromOfferDiscount(parentCatalogId, catalogId,offerProduct.getDiscountList());
				}
			}
		} else if (serviceType.equalsIgnoreCase(TTV)) {
			if (param.getSalesOrder().getTelevisionProduct().getBillDiscountList() != null&& !param.getSalesOrder().getTelevisionProduct().getBillDiscountList().isEmpty()) {
				for (ProductComponentIdentifier productBillDiscount : param.getSalesOrder().getTelevisionProduct().getBillDiscountList()) {
					catalogId = productBillDiscount.getProductCatalogueIdentifier();
					parentCatalogId = productBillDiscount.getParentProductCatalogueIdentifier();
					externalCatalogId = getExternalCatalogIdFromOfferDiscount(parentCatalogId, catalogId,offerProduct.getDiscountList());
				}
			}
		}else if(serviceType.equalsIgnoreCase(SING)){
			if (param.getSalesOrder().getHomePhoneProduct().getBillDiscountList() != null&& !param.getSalesOrder().getHomePhoneProduct().getBillDiscountList().isEmpty()) {
				for (ProductComponentIdentifier productBillDiscount : param.getSalesOrder().getHomePhoneProduct().getBillDiscountList()) {
					catalogId = productBillDiscount.getProductCatalogueIdentifier();
					parentCatalogId = productBillDiscount.getParentProductCatalogueIdentifier();
					externalCatalogId = getExternalCatalogIdFromOfferDiscount(parentCatalogId, catalogId,offerProduct.getDiscountList());
				}
			}
		}
		
		return externalCatalogId;
	}
	
	private static String getExternalCatalogIdFromOfferDiscount(String parentCatalogId, String catalogId ,List<Discount> offerDiscountList){
		String result=null;
		for (Discount discount : offerDiscountList) {
			if (discount.getProductCatalogueItemList() != null && !discount.getProductCatalogueItemList().isEmpty()) {
				for (ProductCatalogueItem productCatalogueItem : discount.getProductCatalogueItemList()) {
					String oisParentCatalogId = productCatalogueItem.getParentProductCatalogueIdentifier().getProductCatalogueId();
					String oisCatalogId = productCatalogueItem.getProductCatalogueIdentifier().getProductCatalogueId();
					if(catalogId.equalsIgnoreCase(oisCatalogId) && parentCatalogId.equalsIgnoreCase(oisParentCatalogId)){
						result = productCatalogueItem.getProductCatalogueIdentifier().getExternalProductCatalogId();
					}
				}
			}
		}
		return result;
	}

	private static ProductComponent getHsPackComp(final SalesOrderCommonObjectDO param) {
		final ProductComponent result = new ProductComponent();
		
//		result.setName("hsPack");
		result.setActionType("AD");
		result.setCatalogId("61611"); // new

//		final ProductComponent subComp = new ProductComponent();
//		subComp.setServiceType("AD");
//		
//		final String catalogId = getCatalogIdByServiceType(param, param.getOfferDO().getOfferList(),HSIC);
//		subComp.setCatalogId(catalogId); 
		
		final ProductCharacteristic prodChar = new ProductCharacteristic();
//		subComp.addProductCharacteristic(prodChar);
		prodChar.setCatalogAttributeId("30627");
		prodChar.setName("commitmentPeriodInYears");
		int contractTermInMonths = 0;
		try {
			contractTermInMonths = Integer.parseInt(param.getSalesOrder().getInternetProduct().getSelectedContractTermCd());
		} catch (NumberFormatException e) {
			contractTermInMonths = 0;
		}
		int contractTermInYears = contractTermInMonths / 12;
		prodChar.setValue(contractTermInYears+ "");

//		result.addProductComponent(subComp);
		result.addProductCharacteristic(prodChar);
		
		return result;
	}
	
	private static String getCatalogIdByServiceType(final SalesOrderCommonObjectDO param, final List<Offer> offerList,final String serviceType) {
		String result = null;
		String csoParentCatId = null;
		String csoCatId = null;
		
		if(serviceType.equalsIgnoreCase(HSIC)){
			csoParentCatId = param.getSalesOrder().getInternetProduct().getProduct().getParentProductCatalogueIdentifier();
			csoCatId = param.getSalesOrder().getInternetProduct().getProduct().getProductCatalogueIdentifier();
		}else if(serviceType.equalsIgnoreCase(TTV)){
			csoParentCatId = param.getSalesOrder().getTelevisionProduct().getProduct().getParentProductCatalogueIdentifier();
			csoCatId = param.getSalesOrder().getTelevisionProduct().getProduct().getProductCatalogueIdentifier();
		}else if(serviceType.equalsIgnoreCase(SING)){
			csoParentCatId = param.getSalesOrder().getHomePhoneProduct().getProduct().getParentProductCatalogueIdentifier();
			csoCatId = param.getSalesOrder().getHomePhoneProduct().getProduct().getProductCatalogueIdentifier();
		}
			for (final Offer offer : offerList) {
				OfferProduct offerProduct = offer.getOfferProduct();
				for (final WirelineOfferProduct wlnOfferProd : offerProduct.getWirelineOfferProductList()) {
					if (serviceType.equalsIgnoreCase(wlnOfferProd.getProductTypeCode())) {
						for (final com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductComponent prodComp : wlnOfferProd.getProductComponentList()) {
							for (final ProductCatalogueItem prodCatItem : prodComp.getProductCatalogueItemList()) {
								String oisParentCatId = prodCatItem.getParentProductCatalogueIdentifier().getProductCatalogueId();
								String oisCatId = prodCatItem.getProductCatalogueIdentifier().getProductCatalogueId();
								if (csoParentCatId.equalsIgnoreCase(oisParentCatId) && csoCatId.equalsIgnoreCase(oisCatId)) {
									result = prodCatItem.getProductCatalogueIdentifier().getExternalProductCatalogId();
									return result;
								}
							}
						}
					}
				}
			}
		
		return result;
	}
	
	public static String getEquipmentCatalogIdByServiceType(final SalesOrderCommonObjectDO commonDO, WirelineEquipmentHeader csoWlnEquipment, final String serviceType){
		String result = null;
		String csoParentCatId;
		String csoCatId;
		List<Offer> offerList=null;
		if(commonDO.getOfferDO().getOfferList()!=null && !commonDO.getOfferDO().getOfferList().isEmpty()){
			offerList = commonDO.getOfferDO().getOfferList();
		
			if(csoWlnEquipment!=null && !StringUtils.isBlank(csoWlnEquipment.getParentProductCatalogueIdentifier()) && !StringUtils.isBlank(csoWlnEquipment.getProductCatalogueIdentifier())){
				csoParentCatId = csoWlnEquipment.getParentProductCatalogueIdentifier();
				csoCatId = csoWlnEquipment.getProductCatalogueIdentifier();
				result = getEquipmentCatalogIdFromOffer(csoParentCatId, csoCatId, offerList, serviceType);
			}
		
	}
		return result;
	}
	
	private static String getEquipmentCatalogIdFromOffer(String parentCatalogId,String catalogId,final List<Offer> offerList, final String serviceType){
		String result = null;
		//Look up in the Offer.equipent for matching catalogId 
		for (final Offer offer : offerList) {
			OfferProduct offerProduct = offer.getOfferProduct();
			for (final WirelineOfferProduct wlnOfferProd : offerProduct.getWirelineOfferProductList()) {
				if (serviceType.equalsIgnoreCase(wlnOfferProd.getProductTypeCode())) {
					for(final WirelineEquipment oisWirelineEquipment : wlnOfferProd.getWirelineEquipmentList()){
						if(oisWirelineEquipment.getProductCatalogueItem()!=null){
							String oisParentCatId = oisWirelineEquipment.getProductCatalogueItem().getParentProductCatalogueIdentifier().getProductCatalogueId();
							String oisCatId = oisWirelineEquipment.getProductCatalogueItem().getProductCatalogueIdentifier().getProductCatalogueId();
							if(parentCatalogId.equalsIgnoreCase(oisParentCatId) && catalogId.equalsIgnoreCase(oisCatId)){
								result = oisWirelineEquipment.getProductCatalogueItem().getProductCatalogueIdentifier().getExternalProductCatalogId();
							}
						}
					}
				}
			}
		}
		return result;
	}
	
private static ProductComponent populateProductEquipment(WirelineEquipmentHeader csoWlnEquipment, SalesOrderCommonObjectDO param, String serviceType) {
		
		ProductComponent productEquipment = new ProductComponent();
		productEquipment.setName(EQUIPMENT);
		
//		final ProductComponent subComponent = new ProductComponent();
//		subComponent.setActionType("AD");
//		subComponent.setCatalogId(getEquipmentCatalogIdByServiceType(param, csoWlnEquipment,serviceType));
		productEquipment.setActionType("AD");
		productEquipment.setCatalogId(getEquipmentCatalogIdByServiceType(param, csoWlnEquipment,serviceType));
		
		/**
		 * Step 1: Get the list of equipment from CWSOrequest.internetProduct.equipment
		 */
		
		/**
		 * Step 2: for each of the equipment in the step 1, find the matching wirelineProduct.wirelineEquipmentList[] element from the OIS response.
		 * if CWSORequest.internetProduct.byodInd=TRUE, then find MIC code within the OIS response "byodEquipmentList" with the matching MIC.
		 * otherwise find the matching MIC within the purchaseEquipmentList and rentalEquipmentList (The MIC is always different for equipment for purchase vs rent)
		 */
		
		String equipmentCatalogId = csoWlnEquipment.getProductCatalogueIdentifier();
		String equipmentParentCatalogId = csoWlnEquipment.getParentProductCatalogueIdentifier();
		
		List<ProductCharacteristic> productCharacteristics = null;
		for(Offer wlnOffer : param.getOfferDO().getOfferList()){
			OfferProduct offerProduct = wlnOffer.getOfferProduct();
			List<WirelineOfferProduct> wirelineOfferProductList = offerProduct.getWirelineOfferProductList();
			for(WirelineOfferProduct wirelineOfferProduct : wirelineOfferProductList){
				List<WirelineEquipment> wirelineEquipmentList = wirelineOfferProduct.getWirelineEquipmentList();
				for(WirelineEquipment wirelineEquipment :wirelineEquipmentList){
					String oisEquipmentCatalogId = wirelineEquipment.getProductCatalogueItem().getProductCatalogueIdentifier().getProductCatalogueId();
					String oisEquipmentParentCatalogId = wirelineEquipment.getProductCatalogueItem().getParentProductCatalogueIdentifier().getProductCatalogueId();
					if(equipmentCatalogId.equalsIgnoreCase(oisEquipmentCatalogId) && equipmentParentCatalogId.equalsIgnoreCase(oisEquipmentParentCatalogId)){
						//populate the ProductCharacteristics
						productCharacteristics = populateCharacteristicsFromOffer(wirelineEquipment, csoWlnEquipment,param,serviceType);
					}
				}
			}
		}
//		subComponent.setProductCharacteristics(productCharacteristics);
		productEquipment.setProductCharacteristics(productCharacteristics);
		return productEquipment;
	}
	
	private static Collection<ProductComponent> populateTtvAddons(SalesOrderCommonObjectDO param) {
		HashMap<String, ProductComponent> result = new HashMap<String, ProductComponent>();
		
		for (WirelineAddOnHeader addOnHeader : param.getSalesOrder().getTelevisionProduct().getAddOnList()) {
			ProductComponentIdentifier addon = addOnHeader.getAddOn();
			ProductCatalogueItem childProductCatalog = lookupProductCatalogue(param, addon.getProductCatalogueIdentifier(), addon.getParentProductCatalogueIdentifier());
			
			if (childProductCatalog == null) {
				LOGGER.info("catalogue Item not found. Product catalogue ID=" + addon.getProductCatalogueIdentifier() + "Parent product catalogue ID=" + addon.getParentProductCatalogueIdentifier());
			} else {
			
				ProductComponent childTtvAddon = new ProductComponent();
				String parentId = childProductCatalog.getParentProductCatalogueIdentifier()
						.getExternalProductCatalogId();
				String childId = childProductCatalog.getProductCatalogueIdentifier().getExternalProductCatalogId();
				childTtvAddon.setCatalogId(childId);
				if (!childProductCatalog.getProductCatalogueNameList().isEmpty()) {
					childTtvAddon.setName(childProductCatalog.getProductCatalogueNameList().get(0).getDescriptionText());
				}

				ProductComponent parentTtvAddon = result.get(parentId);
				if (parentTtvAddon == null) {
					parentTtvAddon = new ProductComponent();
					parentTtvAddon.setCatalogId(parentId);
					result.put(parentId, parentTtvAddon);
				}

				parentTtvAddon.addProductComponent(childTtvAddon);
			}
		}
		
		return result.values();
		
	}

	private static ProductCatalogueItem lookupProductCatalogue(SalesOrderCommonObjectDO param, String productCatalogId,
			String parentProductCatalogId) {

		for (Offer wlnOffer : param.getOfferDO().getOfferList()) {

			for (WirelineOfferProduct wirelineOfferProduct : wlnOffer.getOfferProduct().getWirelineOfferProductList()) {

				if (wirelineOfferProduct.getAddOn() != null) {

					// first search the included product list
					for (ProductCatalogueItem productCatalogueItem : wirelineOfferProduct.getAddOn()
							.getIncludedProductCatalogueItemList()) {
						/**
						 * look for catalog item with matching id and parent id
						 */
						if (productCatalogId.equalsIgnoreCase(
								productCatalogueItem.getProductCatalogueIdentifier().getProductCatalogueId())
								&& parentProductCatalogId.equalsIgnoreCase(productCatalogueItem
										.getParentProductCatalogueIdentifier().getProductCatalogueId())) {
							return productCatalogueItem;
						}
					}
					// then search the optional product list
					for (ProductCatalogueItem productCatalogueItem : wirelineOfferProduct.getAddOn()
							.getOptionalProductCatalogueItemList()) {
						/**
						 * look for catalog item with matching id and parent id
						 */
						if (productCatalogId.equalsIgnoreCase(
								productCatalogueItem.getProductCatalogueIdentifier().getProductCatalogueId())
								&& parentProductCatalogId.equalsIgnoreCase(productCatalogueItem
										.getParentProductCatalogueIdentifier().getProductCatalogueId())) {
							return productCatalogueItem;
						}
					}
				}
			}
		}

		return null;
	}

	private static ProductComponent getTtvQuestionnaireComponent(SalesOrderCommonObjectDO param) {
		ProductComponent result = new ProductComponent();
		result.setName("ttvQuestionnaire");

		ProductComponent technicalQuestionnaireTTV = new ProductComponent();
		
		technicalQuestionnaireTTV.setCatalogId("50380");
		technicalQuestionnaireTTV.setName("TechnicalQuestionnaireTTV");
		
		technicalQuestionnaireTTV.setProductCharacteristics(new ArrayList<ProductCharacteristic>());

		for (TelevisionQuestionairre questionaireReq : param.getSalesOrder().getTelevisionProduct().getTelevisionQuestionairreList()){
			String nameCd = questionaireReq.getNameCd();
			String name = null;
			String catalogAttributeId = null;
			if ("ttvqRentOrOwn".equals(nameCd)) {
				name = "Rent_Or_Own";
				catalogAttributeId = "83488";
			} else if ("ttvqOnSiteMan".equals(nameCd)) {
				name = "On_Site_Manager";
				catalogAttributeId = "83510";
			} else if ("ttvqCBR".equals(nameCd)) {
				name = "CBR";
				catalogAttributeId = "20410288";
			} else if ("ttvqCBRalt".equals(nameCd)) {
				name = "Alternate_CBR";
				catalogAttributeId = "20410298";
			} else if ("ttvqAdultPresent".equals(nameCd)) {
				name = "Adult_Presence";
				catalogAttributeId = "20410308";
			} else if ("ttvqTVSet".equals(nameCd)) {
				name = "TV_Sets";
				catalogAttributeId = "20410318";
			}
				
			if (catalogAttributeId != null) {
				ProductCharacteristic question = new ProductCharacteristic();
				// no need to pass description
				// question.setDescription(questionaireReq.getQuestionTxt().getDescription().get(0).getDescriptionText());
				question.setValue(questionaireReq.getAnswerTxt());
				question.setName(name);
				question.setCatalogAttributeId(catalogAttributeId);
				technicalQuestionnaireTTV.addProductCharacteristic(question);
			}
		}

		result.addProductComponent(technicalQuestionnaireTTV);

		return result;
	}	

	private static List<ProductCharacteristic> populateCharacteristicsFromOffer(WirelineEquipment wirelineEquipment,WirelineEquipmentHeader csoWlnEquipment, SalesOrderCommonObjectDO salesCommonDO, String serviceType) {
		/**
		 * Step 4-	adding the following  productCharacteristics:
		 */
		
		Map<String, ProductAttributes> productAttributesMap = salesCommonDO.getProductAttributesMap();
		String key;
		List<ProductCharacteristic> productCharacteristicList = new ArrayList<ProductCharacteristic>();
			
			List<WirelineEquipmentItem> byodEquipmentList = wirelineEquipment.getByodEquipmentList();
			if (byodEquipmentList != null && !byodEquipmentList.isEmpty()) {
				for (WirelineEquipmentItem wirelineEquipmentItem : byodEquipmentList) {
					if (wirelineEquipmentItem.getMaterialItemCode().equalsIgnoreCase(csoWlnEquipment.getMaterialItemCode())) {
						//Equipment Description
						key = EQUIPMENT_DESCRIPTION + UNDERSCORE + serviceType;
						loggerUtil.info("Key: ", key);
						ProductAttributes equipmentDescription = productAttributesMap.get(key);
						if (equipmentDescription != null) {
							ProductCharacteristic descriptionCharacteristic = new ProductCharacteristic();
							descriptionCharacteristic.setCatalogAttributeId(equipmentDescription.getProductComponentCatalogAttributeId());
							descriptionCharacteristic.setName(equipmentDescription.getProductComponentAttributeName());
							descriptionCharacteristic.setValue(wirelineEquipmentItem.getEquipmentDescriptionList().get(0).getDescriptionText());
							productCharacteristicList.add(descriptionCharacteristic);
						}
						key = MIC + UNDERSCORE + serviceType;
						
						ProductAttributes equipmentMic = productAttributesMap.get(key);
						if(equipmentMic!=null){
							ProductCharacteristic micCharacteristic = new ProductCharacteristic();
							micCharacteristic.setCatalogAttributeId(equipmentMic.getProductComponentCatalogAttributeId());
							micCharacteristic.setName(equipmentMic.getProductComponentAttributeName());
							micCharacteristic.setValue(wirelineEquipmentItem.getMaterialItemCode());
							productCharacteristicList.add(micCharacteristic);
						}
						
						key = DELIVERY_METHOD + UNDERSCORE + serviceType;
						ProductAttributes equipmentDeliveryMethod = productAttributesMap.get(key);
						
						if(equipmentDeliveryMethod!=null){
							ProductCharacteristic deliveryMethodCharacteristic = new ProductCharacteristic();
							deliveryMethodCharacteristic.setCatalogAttributeId(equipmentDeliveryMethod.getProductComponentCatalogAttributeId());
							deliveryMethodCharacteristic.setName(equipmentDeliveryMethod.getProductComponentAttributeName());
							deliveryMethodCharacteristic.setValue(getDeliveryMethod(csoWlnEquipment.getDeliveryMethodTypeCd()));
							productCharacteristicList.add(deliveryMethodCharacteristic);
						}


						key = ACQUISITION_TYPE + UNDERSCORE + serviceType;
						ProductAttributes equipmentAcquisitionType = productAttributesMap.get(key);
						if(equipmentAcquisitionType!=null){
							ProductCharacteristic acquisitionTypeCharacteristic = new ProductCharacteristic();
							acquisitionTypeCharacteristic.setCatalogAttributeId(equipmentAcquisitionType.getProductComponentCatalogAttributeId());
							acquisitionTypeCharacteristic.setName(equipmentAcquisitionType.getProductComponentAttributeName());
							acquisitionTypeCharacteristic.setValue("POE"); 
							productCharacteristicList.add(acquisitionTypeCharacteristic);
						}

						break;
					}
				}
			}
			
				/**
				 * looking in purchaseEquipmentList
				 */
			List<WirelineEquipmentItem> purchaseEquipmentList = wirelineEquipment.getPurchaseEquipmentList();
			if (purchaseEquipmentList != null && !purchaseEquipmentList.isEmpty()) {

				for (WirelineEquipmentItem wirelineEquipmentItem : purchaseEquipmentList) {
					if (wirelineEquipmentItem.getMaterialItemCode().equalsIgnoreCase(csoWlnEquipment.getMaterialItemCode())) {
						
						//Equipment Description
						key = EQUIPMENT_DESCRIPTION + UNDERSCORE + serviceType;
						ProductAttributes equipmentDescription = productAttributesMap.get(key);
						if(equipmentDescription!=null){
							ProductCharacteristic descriptionCharacteristic = new ProductCharacteristic();
							descriptionCharacteristic.setCatalogAttributeId(equipmentDescription.getProductComponentCatalogAttributeId());
							descriptionCharacteristic.setName(equipmentDescription.getProductComponentAttributeName());
							descriptionCharacteristic.setValue(wirelineEquipmentItem.getEquipmentDescriptionList().get(0).getDescriptionText());
							productCharacteristicList.add(descriptionCharacteristic);
						}
						
						key = MIC + UNDERSCORE + serviceType;
						ProductAttributes equipmentMic = productAttributesMap.get(key);
						if(equipmentMic!=null){
							ProductCharacteristic micCharacteristic = new ProductCharacteristic();
							micCharacteristic.setCatalogAttributeId(equipmentMic.getProductComponentCatalogAttributeId());
							micCharacteristic.setName(equipmentMic.getProductComponentAttributeName());
							micCharacteristic.setValue(wirelineEquipmentItem.getMaterialItemCode());
							productCharacteristicList.add(micCharacteristic);
						}

						
						key = DELIVERY_METHOD + UNDERSCORE + serviceType;
						ProductAttributes equipmentDeliveryMethod = productAttributesMap.get(key);
						
						if(equipmentDeliveryMethod!=null){
							ProductCharacteristic deliveryMethodCharacteristic = new ProductCharacteristic();
							deliveryMethodCharacteristic.setCatalogAttributeId(equipmentDeliveryMethod.getProductComponentCatalogAttributeId());
							deliveryMethodCharacteristic.setName(equipmentDeliveryMethod.getProductComponentAttributeName());
							deliveryMethodCharacteristic.setValue(getDeliveryMethod(csoWlnEquipment.getDeliveryMethodTypeCd()));
							productCharacteristicList.add(deliveryMethodCharacteristic);
						}


						key = ACQUISITION_TYPE + UNDERSCORE + serviceType;
						ProductAttributes equipmentAcquisitionType = productAttributesMap.get(key);
						
						if(equipmentAcquisitionType!=null){
							ProductCharacteristic acquisitionTypeCharacteristic = new ProductCharacteristic();
							acquisitionTypeCharacteristic.setCatalogAttributeId(equipmentAcquisitionType.getProductComponentCatalogAttributeId());
							acquisitionTypeCharacteristic.setName(equipmentAcquisitionType.getProductComponentAttributeName());
							acquisitionTypeCharacteristic.setValue("SA"); 
							productCharacteristicList.add(acquisitionTypeCharacteristic);
						}
						break;
					}
				}
				
				}
				/**
				 * looking in rentalEquipmentList
				 */
				List<WirelineEquipmentItem> rentalEquipmentList = wirelineEquipment.getRentalEquipmentList();
				if(rentalEquipmentList!=null && !rentalEquipmentList.isEmpty()){
				
				for (WirelineEquipmentItem wirelineEquipmentItem : rentalEquipmentList) {
					if (wirelineEquipmentItem.getMaterialItemCode()
							.equalsIgnoreCase(csoWlnEquipment.getMaterialItemCode())) {
						
						key = EQUIPMENT_DESCRIPTION + UNDERSCORE + serviceType;
						ProductAttributes equipmentDescription = productAttributesMap.get(key);
						if(equipmentDescription!=null){
							ProductCharacteristic descriptionCharacteristic = new ProductCharacteristic();
							descriptionCharacteristic.setCatalogAttributeId(equipmentDescription.getProductComponentCatalogAttributeId());
							descriptionCharacteristic.setName(equipmentDescription.getProductComponentAttributeName());
							descriptionCharacteristic.setValue(wirelineEquipmentItem.getEquipmentDescriptionList().get(0).getDescriptionText());
							productCharacteristicList.add(descriptionCharacteristic);
						}


						key = MIC + UNDERSCORE + serviceType;
						ProductAttributes equipmentMic = productAttributesMap.get(key);
						if(equipmentMic!=null){
							ProductCharacteristic micCharacteristic = new ProductCharacteristic();
							micCharacteristic.setCatalogAttributeId(equipmentMic.getProductComponentCatalogAttributeId());
							micCharacteristic.setName(equipmentMic.getProductComponentAttributeName());
							micCharacteristic.setValue(wirelineEquipmentItem.getMaterialItemCode());
							productCharacteristicList.add(micCharacteristic);
						}
						

						key = DELIVERY_METHOD + UNDERSCORE + serviceType;
						ProductAttributes equipmentDeliveryMethod = productAttributesMap.get(key);
						
						if(equipmentDeliveryMethod!=null){
							ProductCharacteristic deliveryMethodCharacteristic = new ProductCharacteristic();
							deliveryMethodCharacteristic.setCatalogAttributeId(equipmentDeliveryMethod.getProductComponentCatalogAttributeId());
							deliveryMethodCharacteristic.setName(equipmentDeliveryMethod.getProductComponentAttributeName());
							deliveryMethodCharacteristic.setValue(getDeliveryMethod(csoWlnEquipment.getDeliveryMethodTypeCd()));
							productCharacteristicList.add(deliveryMethodCharacteristic);
						}
						
						key = ACQUISITION_TYPE + UNDERSCORE + serviceType;
						ProductAttributes equipmentAcquisitionType = productAttributesMap.get(key);
						if(equipmentAcquisitionType!=null){
							ProductCharacteristic acquisitionTypeCharacteristic = new ProductCharacteristic();
							acquisitionTypeCharacteristic.setCatalogAttributeId(equipmentAcquisitionType.getProductComponentCatalogAttributeId());
							acquisitionTypeCharacteristic.setName(equipmentAcquisitionType.getProductComponentAttributeName());
							acquisitionTypeCharacteristic.setValue("RE");
							productCharacteristicList.add(acquisitionTypeCharacteristic);
						}
					break;
					}
				}
	}
	
			
		
		return productCharacteristicList;
	}
	
	private static String getDeliveryMethod(String deliveryMethodTypeCd) {
		String result="";
		if(deliveryMethodTypeCd.equalsIgnoreCase(DELIVERY_METHOD_SHIPPED)){
			result = DELIVERY_METHOD_SHP;
		}else if(deliveryMethodTypeCd.equalsIgnoreCase(DELIVERY_METHOD_INSTORE)){
			result = DELIVERY_METHOD_CST;
		}else if(deliveryMethodTypeCd.equalsIgnoreCase(DELIVERY_METHOD_INSTALLER)){
			result = DELIVERY_METHOD_INS;
		}
		return result;
	}

	private static String getAction(String productOrderType) {
		if (ACTIVATION.equalsIgnoreCase(productOrderType)){
			return PR;
		} else if (RENEWAL.equalsIgnoreCase(productOrderType)){
			//TODO: What would be the value in this case ? Allan: It would "PROBABLY" be change  ("CH") but I cannot say that for sure because we haven't done the analysis.
			// return "";
		}
		return null;
	}

	private static WirelineOfferProduct getProductByProductTypeCd(OfferProduct offerProduct, String productTypeCd) {
		for ( WirelineOfferProduct product: offerProduct.getWirelineOfferProductList()){
			if (productTypeCd.equalsIgnoreCase(product.getProductTypeCode())){
				return product;
			}
		}
		return null;
	}

	private static String getExternalCatalogId(List<WirelineOfferProduct> wirelineOfferProductList, String prodParam) {
		for (WirelineOfferProduct product : wirelineOfferProductList){
			if (product.getProductTypeCode().equalsIgnoreCase(prodParam)){
				return product.getMainComponentIdentifier().getExternalProductCatalogId();
			}
		}
		return null;
	}
	
	// TODO check if we need to enable the cache.
	public static GetProductsByCustomerIdAdapterRequest transformGetProductByCustomerIdRequest(CreateWLNOrderCoreRequest coreRequest){
		GetProductsByCustomerIdAdapterRequest adapterRequest = new GetProductsByCustomerIdAdapterRequest();
		adapterRequest.setCustomerid(coreRequest.getCreateWirelineSalesOrder().getSalesOrder().getCustomerDetail().getCustomerId());
		return adapterRequest;
	}
	
	public static GetOfferListByOfferIdentifierListAdapterRequest transformOfferInformationRequest(CreateWLNOrderCoreRequest coreRequest){
		GetOfferListByOfferIdentifierListAdapterRequest adapterRequest = new GetOfferListByOfferIdentifierListAdapterRequest();
		adapterRequest.setBrandId(Long.parseLong(EnterpriseSalesServiceUtil.getBrandId(coreRequest.getOperationHeader().getBrandCode())));
		adapterRequest.setSalesTransactionId(coreRequest.getOperationHeader().getSalesTransactionId());
		adapterRequest.setOutlet(transform(coreRequest.getOperationHeader()));
		adapterRequest.setOfferIdentifierList(setOfferIdentifierList(coreRequest));
		return adapterRequest;
	}
	
	private static List<OfferIdentifier> setOfferIdentifierList(CreateWLNOrderCoreRequest coreRequest) {
		List<OfferIdentifier> offerIdentifierList = new ArrayList<OfferIdentifier>();
		WirelineOfferHeader selectedOffer = coreRequest.getCreateWirelineSalesOrder().getSalesOrder().getSelectedOffer();
		if(selectedOffer!=null){
			OfferIdentifier offerIdentifier = new OfferIdentifier();
			offerIdentifier.setOfferId(Long.valueOf(selectedOffer.getOfferId()));
			offerIdentifier.setOfferTypeCode(EnterpriseWLNSalesServicesConstants.WLN_OFFER_TYPE_CD);
			offerIdentifier.setPerspectiveDate(selectedOffer.getPerspectiveDate());
			offerIdentifier.setProgramId(Long.valueOf(selectedOffer.getOfferProgramId()));
			offerIdentifierList.add(offerIdentifier);
		}
		return offerIdentifierList;
	}

	public static OutletInfo transform(OperationHeader operationHeader) {

		OutletInfo result = new OutletInfo();

		ChannelOrgSummaryInfo channelOrgSummaryInfo = new ChannelOrgSummaryInfo();
		channelOrgSummaryInfo.setChannelOrgType(operationHeader.getUserProfile().getChnlOrgTypeCode());
		channelOrgSummaryInfo.setInternalChannelOrgId(operationHeader.getUserProfile().getChnlOrgInternalId());
		channelOrgSummaryInfo.setChannelOrgCode(operationHeader.getUserProfile().getChnlOrgNumber());

		try {
			result.setInternalOutletId(operationHeader.getUserProfile().getSalesRepAssociatedOutletList().get(0)
					.getSalesRepAssociatedOutletInternalId());
		} catch (Exception e) {
			loggerUtil.error("", "transform Outlet", e.getMessage(), e);
		}

		String province = null;
		try {
			province = operationHeader.getUserProfile().getOutletAssociatedProvinces().get(0);
		} catch (Exception e) {
			loggerUtil.error("", "transform Outlet", e.getMessage(), e);
		}

		if (province != null) {
			ContactAddress contactAddress = new ContactAddress();
			contactAddress.setProvince(province);

			ContactDetails contactDetails = new ContactDetails();
			contactDetails.setAddress(contactAddress);

			result.getContactDetails().add(contactDetails);

			result.setChannelOrgInfo(channelOrgSummaryInfo);
		}

		return result;
	}

	public static GetFullCustomerInfoAdapterRequest transformGetFullCustomerInfoRequest(CreateWLNOrderCoreRequest coreRequest) {
		Long customerId=1L;
		if(!StringUtils.isBlank(coreRequest.getCreateWirelineSalesOrder().getSalesOrder().getCustomerDetail().getCustomerId())){
			customerId = Long.valueOf(coreRequest.getCreateWirelineSalesOrder().getSalesOrder().getCustomerDetail().getCustomerId());
		}
		return new GetFullCustomerInfoAdapterRequest(customerId, coreRequest.getOperationHeader().getSalesTransactionId());
	}

	public static CheckProductFeasibilityAdapterRequest transformCheckProductFeasibility(
			CreateWLNOrderCoreRequest coreRequest) {
		CheckProductFeasibilityAdapterRequest adapterRequest = new CheckProductFeasibilityAdapterRequest();
		adapterRequest.setUserId(String.valueOf(coreRequest.getOperationHeader().getUserProfile().getSalesRepInternalId()));
		adapterRequest.setTimeStamp(new Timestamp(coreRequest.getOperationHeader().getSalesTransactionTimestamp().getTime()));
		adapterRequest.setSalesTransactionId(coreRequest.getOperationHeader().getSalesTransactionId());
		
		/**  
		 * Transform ServiceAddress
		 */
		adapterRequest.setAddress(transformServiceAddress(coreRequest.getCreateWirelineSalesOrder().getSalesOrder().getServiceAddressDetail()));
		
		/**
		 * Transform AccessCriteria
		 */
		adapterRequest.setAccessCriteria(transformAccessCriteria());
		
		/**
		 * Transforming the ProductSpecificationList
		 */
		adapterRequest.setProductSpecificationList(transformProductSpecificationList(coreRequest));
		
		/** 
		 * Transforming the productServiceInstance - Note: 29-Dec-2017 Change order is out of scope
		 */
		
		
		return adapterRequest;
	}


	private static ProductSpecificationList transformProductSpecificationList(CreateWLNOrderCoreRequest coreRequest
			) {
		ProductSpecificationList productSpecificationList = new ProductSpecificationList();
		List<ProductSpecification> list = new ArrayList<ProductSpecification>();
		//Creating a productSpecification object for each product in the request
		//If the ESS productOrderType is Activation, we will map it to Provide in OMS. 
		//If the productOrderType is renewal, it will be mapped to CHANGE. 
		
		String brandCode = coreRequest.getOperationHeader().getBrandCode();
		String requestIdDate = transformDateToString(DATE_FORMAT_PRODUCT_REQUEST,new Date());
		/**
		 * internetProduct
		 */
		
		list.add(internetProduct(coreRequest.getCreateWirelineSalesOrder().getSalesOrder().getInternetProduct(),brandCode,requestIdDate));
		
		/** 
		 * televisionProduct
		 */
		list.add(televisionProduct(coreRequest.getCreateWirelineSalesOrder().getSalesOrder().getTelevisionProduct(),brandCode,requestIdDate));
		
		
		/** 
		 * homePhoneProduct
		 */
		//list.add(homePhoneProduct(coreRequest.getCreateWirelineSalesOrder().getSalesOrder().getHomePhoneProduct(),brandCode,requestIdDate));
		
		productSpecificationList.setProductSpecification(list);
		
		return productSpecificationList;
	}

	

	private static ProductSpecification homePhoneProduct(HomePhoneComponent homePhoneProduct,String brandCode,String requestIdDate) {
		ProductSpecification homePhoneProductSpecification = new ProductSpecification();
		if (brandCode.trim().equalsIgnoreCase(TELUS)){
			homePhoneProductSpecification.setBrandID(new BigInteger(TELUS_BRAND_ID));
		}
		homePhoneProductSpecification.setName(OMS_PRODUCT_SING);
		final String homePhoneProductNumber = ApplicationProperties.getConfigString("${common/wlnProductSpecification/productTypeSING}");
		homePhoneProductSpecification.setProductNumber(homePhoneProductNumber);
		List<ProductSpecificationCharacteristic> productSpecificationCharacteristicList = new ArrayList<ProductSpecificationCharacteristic>();
		String productOrderType = "";
		if(homePhoneProduct.getProductOrderHeader().getProductOrderType().getProductOrderTypeCd().equalsIgnoreCase(EnterpriseWLNSalesServicesConstants.SALES_ACTIVITY_ACTIVATION)){
			productOrderType=ORDER_ACTION_TYPE_CREATE;
		}else if(homePhoneProduct.getProductOrderHeader().getProductOrderType().getProductOrderTypeCd().equalsIgnoreCase(EnterpriseWLNSalesServicesConstants.SALES_ACTIVITY_RENEWAL)){
			productOrderType=ORDER_ACTION_TYPE_MODIFY;
		}
		productSpecificationCharacteristicList.add(buildProductSpecificationCharacteristic(PRODUCT_REQUEST_ID, requestIdDate + OMS_PRODUCT_SING));
		productSpecificationCharacteristicList.add(buildProductSpecificationCharacteristic(ORDER_ACTION_TYPE, productOrderType));
		productSpecificationCharacteristicList.add(buildProductSpecificationCharacteristic(SERVICE_REQUEST_DATE, transformDateToString(DATE_FORMAT_PRODUCT_REQUEST,new Date())));
		homePhoneProductSpecification.setProductSpecificationCharacteristics(productSpecificationCharacteristicList);
		return homePhoneProductSpecification;
	}

	private static ProductSpecification televisionProduct(TelevisionComponent televisionProduct, String brandCode,String requestIdDate) {
		ProductSpecification televisionProductSpecification = new ProductSpecification();
		if (brandCode.trim().equalsIgnoreCase(TELUS)){
			televisionProductSpecification.setBrandID(new BigInteger(TELUS_BRAND_ID));
		}
		televisionProductSpecification.setName(OMS_PRODUCT_TTV);
		final String televisionProductNumber = ApplicationProperties.getConfigString("${common/wlnProductSpecification/productTypeTTV}");
		televisionProductSpecification.setProductNumber(televisionProductNumber);
		List<ProductSpecificationCharacteristic> productSpecificationCharacteristicList = new ArrayList<ProductSpecificationCharacteristic>();
		String productOrderType = "";
		if(televisionProduct.getProductOrderHeader().getProductOrderType().getProductOrderTypeCd().equalsIgnoreCase(EnterpriseWLNSalesServicesConstants.SALES_ACTIVITY_ACTIVATION)){
			productOrderType=ORDER_ACTION_TYPE_CREATE;
		}else if(televisionProduct.getProductOrderHeader().getProductOrderType().getProductOrderTypeCd().equalsIgnoreCase(EnterpriseWLNSalesServicesConstants.SALES_ACTIVITY_RENEWAL)){
			productOrderType=ORDER_ACTION_TYPE_MODIFY;
		}
		productSpecificationCharacteristicList.add(buildProductSpecificationCharacteristic(PRODUCT_REQUEST_ID, requestIdDate + OMS_PRODUCT_TTV));
		productSpecificationCharacteristicList.add(buildProductSpecificationCharacteristic(ORDER_ACTION_TYPE, productOrderType));
		productSpecificationCharacteristicList.add(buildProductSpecificationCharacteristic(SERVICE_REQUEST_DATE, transformDateToString(DATE_FORMAT_PRODUCT_REQUEST,new Date())));
		televisionProductSpecification.setProductSpecificationCharacteristics(productSpecificationCharacteristicList);
		return televisionProductSpecification;
	}

	private static ProductSpecification internetProduct(HsicComponent internetProduct, String brandCode,String requestIdDate) {
		ProductSpecification internetProductSpecification = new ProductSpecification();
		if (brandCode.trim().equalsIgnoreCase(TELUS)){
			internetProductSpecification.setBrandID(new BigInteger(TELUS_BRAND_ID));
		}
		internetProductSpecification.setName(OMS_PRODUCT_HSIC);
		final String internetProductNumber = ApplicationProperties.getConfigString("${common/wlnProductSpecification/productTypeHSIC}");
		internetProductSpecification.setProductNumber(internetProductNumber); //This is the productCatalogId, getting it from LDAP now
		List<ProductSpecificationCharacteristic> productSpecificationCharacteristicList = new ArrayList<ProductSpecificationCharacteristic>();
		
		String productOrderType = "";
		if(internetProduct.getProductOrderHeader().getProductOrderType().getProductOrderTypeCd().equalsIgnoreCase(EnterpriseWLNSalesServicesConstants.SALES_ACTIVITY_ACTIVATION)){
			productOrderType=ORDER_ACTION_TYPE_CREATE;
		}else if(internetProduct.getProductOrderHeader().getProductOrderType().getProductOrderTypeCd().equalsIgnoreCase(EnterpriseWLNSalesServicesConstants.SALES_ACTIVITY_RENEWAL)){
			productOrderType=ORDER_ACTION_TYPE_MODIFY;
		}
		
		productSpecificationCharacteristicList.add(buildProductSpecificationCharacteristic(PRODUCT_REQUEST_ID, requestIdDate + OMS_PRODUCT_HSIC));
		productSpecificationCharacteristicList.add(buildProductSpecificationCharacteristic(ORDER_ACTION_TYPE, productOrderType));
		productSpecificationCharacteristicList.add(buildProductSpecificationCharacteristic(SERVICE_PLAN, "HSSP")); //TODO: To be defined
		productSpecificationCharacteristicList.add(buildProductSpecificationCharacteristic(SERVICE_REQUEST_DATE, transformDateToString(DATE_FORMAT_PRODUCT_REQUEST,new Date())));
		
		internetProductSpecification.setProductSpecificationCharacteristics(productSpecificationCharacteristicList);
		return internetProductSpecification;
	}

	private static com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ServiceAddress transformServiceAddress(ServiceAddressDetail serviceAddressDetail){ 
		 
		com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ServiceAddress address = 
				new com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ServiceAddress();
		address.setAddressId(serviceAddressDetail.getFmsAddressId());
		address.setProvinceStateCode(serviceAddressDetail.getSelectedProvinceCd());		
		return address;
	}
	
	private static AccessCriteria transformAccessCriteria(){
		AccessCriteria accessCriteria = new AccessCriteria();
		
		accessCriteria.setMultiUnitDwellingInd(false);
		accessCriteria.setNewResidentAtServiceAddressInd(true);
		accessCriteria.setPrimaryLineHolderInd(true);
		
		return accessCriteria;
	}
	
	/***********************************************/
	/* buildProductSpecificationCharacteristic     */
	/***********************************************/
	private static ProductSpecificationCharacteristic buildProductSpecificationCharacteristic(String name, String value, ValueType valueType){
		ProductSpecificationCharacteristic characteristic = new ProductSpecificationCharacteristic();
		
		characteristic.setName(name);
		characteristic.setValueType(valueType);
		
		CompositeProductSpecificationCharacteristicValue compositeProductSpecificationCharacteristicValue = new CompositeProductSpecificationCharacteristicValue();
		compositeProductSpecificationCharacteristicValue.setValueType(valueType);
		compositeProductSpecificationCharacteristicValue.setDefault(false);
		compositeProductSpecificationCharacteristicValue.setValue(value);
		List<CompositeProductSpecificationCharacteristicValue> list = new ArrayList<CompositeProductSpecificationCharacteristicValue>();
		list.add(compositeProductSpecificationCharacteristicValue);
		characteristic.setProductSpecificationCharacteristicValues(list); 
		
		return characteristic;
	}
	
	/***********************************************/
	/* buildProductSpecificationCharacteristic     */
	/***********************************************/	
	private static ProductSpecificationCharacteristic buildProductSpecificationCharacteristic(String name, String value){
		 
		return buildProductSpecificationCharacteristic(name, value, ValueType.STRING);
	}
	
	private static String transformDateToString(String format, Date date){
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}
	
	private static ProductAttributes populateProductAttribute(String key,String productComponentCatalogId , Properties properties){
		ProductAttributes productAttributes = new ProductAttributes();
		productAttributes.setProductComponentName(properties.getProperty(key+PRODUCT_COMPONENT_NAME));
		if (productComponentCatalogId != null)
			productAttributes.setProductComponentCatalogId(productComponentCatalogId);
		else
			productAttributes.setProductComponentCatalogId(properties.getProperty(key + PRODUCT_COMPONENT_CATALOG_ID));
		
		productAttributes.setProductComponentCatalogAttributeId(properties.getProperty(key+PRODUCT_COMPONENT_CATALOG_ATTRIBUTE_ID));
		productAttributes.setProductComponentAttributeName(properties.getProperty(key+PRODUCT_COMPONENT_ATTRIBUTE_NAME));
		return productAttributes;
	}

	public static Map<String, ProductAttributes> populateProductAttributeMap(CreateWLNOrderCoreRequest request) {
		Map<String, ProductAttributes> productAttributeMap = new HashMap<String,ProductAttributes>();
		Properties properties = new Properties();
		InputStream input = null;
		String key ="";
		String productComponentCatalogId = "";
		try{
			input = CreateWLNOrderTransformer.class.getClassLoader().getResourceAsStream(PRODUCT_CHARACTERISTICS_EXTERNAL_FILE_NAME);
			//Load the Properties from the file
			properties.load(input);
			
			HsicComponent internetProduct = request.getCreateWirelineSalesOrder().getSalesOrder().getInternetProduct();
			TelevisionComponent televisionProduct = request.getCreateWirelineSalesOrder().getSalesOrder().getTelevisionProduct();
			
			if(internetProduct!=null){
				/**
				 * Key: contractTerm_HSIC
				 */
				key = CONTRACT_TERM+UNDERSCORE+OMS_PRODUCT_HSIC;
				productComponentCatalogId = internetProduct.getProduct().getProductCatalogueIdentifier();
				productAttributeMap.put(key, populateProductAttribute(key, productComponentCatalogId, properties));
				
				/**
				 * Key: equipmentDescription_HSIC
				 */
				key = EQUIPMENT_DESCRIPTION+UNDERSCORE+OMS_PRODUCT_HSIC;
				productComponentCatalogId = internetProduct.getEquipmentList().get(0).getProductCatalogueIdentifier();
				productAttributeMap.put(key, populateProductAttribute(key, productComponentCatalogId, properties));
				
				/**
				 * acquisitionType_HSIC
				 */
				key = ACQUISITION_TYPE+UNDERSCORE+OMS_PRODUCT_HSIC;
				productComponentCatalogId = internetProduct.getEquipmentList().get(0).getProductCatalogueIdentifier();
				productAttributeMap.put(key, populateProductAttribute(key, productComponentCatalogId, properties));
				
				/**
				 * deliveryMethod_HSIC
				 */
				key = DELIVERY_METHOD+UNDERSCORE+OMS_PRODUCT_HSIC;
				productComponentCatalogId = internetProduct.getEquipmentList().get(0).getProductCatalogueIdentifier();
				productAttributeMap.put(key, populateProductAttribute(key, productComponentCatalogId, properties));
				
				/**
				 * mic_HSIC
				 */
				key = MIC+UNDERSCORE+OMS_PRODUCT_HSIC;
				productComponentCatalogId = internetProduct.getEquipmentList().get(0).getProductCatalogueIdentifier();
				productAttributeMap.put(key, populateProductAttribute(key, productComponentCatalogId, properties));
				
				/**
				 * productTemplateId_HSIC
				 */
				key = PRODUCT_TEMPLATE_ID+UNDERSCORE+OMS_PRODUCT_HSIC;
				productAttributeMap.put(key, populateProductAttribute(key, null, properties));
				
				/**
				 * installType_HSIC
				 */
				key = INSTALL_TYPE + UNDERSCORE + OMS_PRODUCT_HSIC;
				productAttributeMap.put(key, populateProductAttribute(key, null, properties));
				
				/**
				 * recommendedInstallType_HSIC
				 */
				key = RECOMMENDED_INSTALL_TYPE + UNDERSCORE + OMS_PRODUCT_HSIC;
				productAttributeMap.put(key, populateProductAttribute(key, null, properties));
				
				/**
				 * maxWorkTime_HSIC
				 */
				key = MAX_WORK_TIME + UNDERSCORE + OMS_PRODUCT_HSIC;
				productAttributeMap.put(key, populateProductAttribute(key, null, properties));
				
				/**
				 * minWorkTime
				 */
				key = MIN_WORK_TIME + UNDERSCORE + OMS_PRODUCT_HSIC;
				productAttributeMap.put(key, populateProductAttribute(key, null, properties));
				
				/**
				 * defaultWorkBucket_HSIC
				 */
				key = DEFAULT_WORK_BUCKET + UNDERSCORE + OMS_PRODUCT_HSIC;
				productAttributeMap.put(key, populateProductAttribute(key, null, properties));
				
				/**
				 * defaultWorkTime_HSIC
				 */
				key = DEFAULT_WORK_TIME + UNDERSCORE + OMS_PRODUCT_HSIC;
				productAttributeMap.put(key, populateProductAttribute(key, null, properties));
				
				/**
				 * bookingRequired_HSIC
				 */
				key = BOOKING_REQUIRED + UNDERSCORE + OMS_PRODUCT_HSIC;
				productAttributeMap.put(key, populateProductAttribute(key, null, properties));
				
			}
			
			if(televisionProduct!=null){
				/**
				 * Key: contractTerm_TTV
				 */
				key = CONTRACT_TERM + UNDERSCORE + OMS_PRODUCT_TTV;
				productAttributeMap.put(key, populateProductAttribute(key, null, properties));
				
				/**
				 * setTopBoxModel_TTV
				 */
				productComponentCatalogId = televisionProduct.getEquipmentList().get(0).getParentProductCatalogueIdentifier();
				key = SET_TOP_BOX_MODEL+UNDERSCORE+OMS_PRODUCT_TTV;
				productAttributeMap.put(key, populateProductAttribute(key, productComponentCatalogId, properties));
				
				/**
				 * equipmentDescription_TTV
				 */
				productComponentCatalogId = televisionProduct.getEquipmentList().get(0).getParentProductCatalogueIdentifier();
				key = EQUIPMENT_DESCRIPTION+UNDERSCORE+OMS_PRODUCT_TTV;
				productAttributeMap.put(key, populateProductAttribute(key, productComponentCatalogId, properties));
				
				/**
				 * acquisitionType_TTV
				 */
				productComponentCatalogId = televisionProduct.getEquipmentList().get(0).getParentProductCatalogueIdentifier();
				key = ACQUISITION_TYPE+UNDERSCORE+OMS_PRODUCT_TTV;
				productAttributeMap.put(key, populateProductAttribute(key, productComponentCatalogId, properties));
				
				/**
				 * deliveryMethod_TTV
				 */
				productComponentCatalogId = televisionProduct.getEquipmentList().get(0).getParentProductCatalogueIdentifier();
				key = DELIVERY_METHOD+UNDERSCORE+OMS_PRODUCT_TTV;
				productAttributeMap.put(key, populateProductAttribute(key, productComponentCatalogId, properties));
				
				/**
				 * mic_TTV
				 */
				productComponentCatalogId = televisionProduct.getEquipmentList().get(0).getParentProductCatalogueIdentifier();
				key = MIC+UNDERSCORE+OMS_PRODUCT_TTV;
				productAttributeMap.put(key, populateProductAttribute(key, productComponentCatalogId, properties));
				
				/**
				 * productTemplateId_TTV
				 */
				key = PRODUCT_TEMPLATE_ID+UNDERSCORE+OMS_PRODUCT_TTV;
				productAttributeMap.put(key, populateProductAttribute(key, null, properties));
				
				/**
				 * ttvqRentOrOwn_TTV
				 */
				key = TTVQ_RENT_OR_OWN+UNDERSCORE+OMS_PRODUCT_TTV;
				productAttributeMap.put(key, populateProductAttribute(key, null, properties));
				
				/**
				 * ttvqOnSiteMan_TTV
				 */
				key = TTVQ_ON_SITE_MAN+UNDERSCORE+OMS_PRODUCT_TTV;
				productAttributeMap.put(key, populateProductAttribute(key, null, properties));
				
				/**
				 * ttvqCBR_TTV
				 */
				key = TTVQ_CBR+UNDERSCORE+OMS_PRODUCT_TTV;
				productAttributeMap.put(key, populateProductAttribute(key, null, properties));
				
				/**
				 * ttvqCBRalt_TTV
				 */
				key = TTVQ_CBRALT+UNDERSCORE+OMS_PRODUCT_TTV;
				productAttributeMap.put(key, populateProductAttribute(key, null, properties));
				
				/**
				 * ttvqAdultPresent_TTV
				 */
				key = TTVQ_ADULT_PRESENT+UNDERSCORE+OMS_PRODUCT_TTV;
				productAttributeMap.put(key, populateProductAttribute(key, null, properties));
				
				/**
				 * ttvqTVSet_TTV
				 */
				key = TTVQ_ADULT_PRESENT+UNDERSCORE+OMS_PRODUCT_TTV;
				productAttributeMap.put(key, populateProductAttribute(key, null, properties));
				
				/**
				 * installType_TTV
				 */
				key = INSTALL_TYPE+UNDERSCORE+OMS_PRODUCT_TTV;
				productAttributeMap.put(key, populateProductAttribute(key, null, properties));
				
				/**
				 * recommendedInstallType_TTV
				 */
				key = RECOMMENDED_INSTALL_TYPE + UNDERSCORE + OMS_PRODUCT_TTV;
				productAttributeMap.put(key, populateProductAttribute(key, null, properties));
				
				/**
				 * maxWorkTime_TTV
				 */
				key = MAX_WORK_TIME + UNDERSCORE + OMS_PRODUCT_TTV;
				productAttributeMap.put(key, populateProductAttribute(key, null, properties));
				
				/**
				 * minWorkTime_TTV
				 */
				key = MIN_WORK_TIME + UNDERSCORE + OMS_PRODUCT_TTV;
				productAttributeMap.put(key, populateProductAttribute(key, null, properties));
				
				/**
				 * defaultWorkBucket_TTV
				 */
				key = DEFAULT_WORK_BUCKET + UNDERSCORE + OMS_PRODUCT_TTV;
				productAttributeMap.put(key, populateProductAttribute(key,null, properties));
				
				/**
				 * defaultWorkTime_TTV
				 */
				key = DEFAULT_WORK_TIME + UNDERSCORE + OMS_PRODUCT_TTV;
				productAttributeMap.put(key, populateProductAttribute(key, null, properties));
				
				/**
				 * bookingRequired_TTV
				 */
				key = BOOKING_REQUIRED + UNDERSCORE + OMS_PRODUCT_TTV;
				productAttributeMap.put(key, populateProductAttribute(key, null, properties));
			}
			
			
		}catch(Exception e){
			loggerUtil.error("","populateProductCharacteristics","Error happened while reading properties file: " + e.getMessage() , e);
		}
		
				
		
				
		return productAttributeMap;
	}

	public static ConfirmBookingAdapterRequest transformConfirmBookingRequest(int orderId,
			SalesOrderCommonObjectDO salesCommonDO, CreateWLNOrderCoreRequest coreRequest) {
		ConfirmBookingAdapterRequest adapterRequest = new ConfirmBookingAdapterRequest();
		ConfirmBookingAdapterRequestBody adapterRequestBody = adapterRequest.getBody();
		adapterRequestBody.setOrderId(String.valueOf(orderId)); //This will default to be a Hard Booking
		adapterRequestBody.setCanBeReachedNumber(salesCommonDO.getSalesOrder().getOrderContext().getOrderContactPhoneNumber().getTelephoneNumber());
		Long appointmentId = null;
		if(salesCommonDO.getSalesOrder().getInternetProduct()!=null && salesCommonDO.getSalesOrder().getInternetProduct().getAppointmentDetail()!=null){
			appointmentId=salesCommonDO.getSalesOrder().getInternetProduct().getAppointmentDetail().getAppointmentId();
		}else if(salesCommonDO.getSalesOrder().getTelevisionProduct()!=null && salesCommonDO.getSalesOrder().getTelevisionProduct().getAppointmentDetail()!=null){
			appointmentId = salesCommonDO.getSalesOrder().getTelevisionProduct().getAppointmentDetail().getAppointmentId();
		}else if(salesCommonDO.getSalesOrder().getHomePhoneProduct()!=null && salesCommonDO.getSalesOrder().getHomePhoneProduct().getAppointmentDetail()!=null){
			appointmentId = salesCommonDO.getSalesOrder().getHomePhoneProduct().getAppointmentDetail().getAppointmentId();
		}
		
		adapterRequestBody.setBookingId(String.valueOf(appointmentId)); //TODO: which appointmentId from the three Products shall we use? 
		adapterRequest.setStickysessionid(salesCommonDO.getSalesOrder().getCustomerDetail().getCustomerId()); //A unique id which is used to improve the performance by using cached data. The recommendation is to use Customer ID, but any unique id will work.
		return adapterRequest;
	}
}
