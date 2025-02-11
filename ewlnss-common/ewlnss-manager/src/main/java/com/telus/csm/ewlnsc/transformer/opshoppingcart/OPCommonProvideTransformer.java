package com.telus.csm.ewlnsc.transformer.opshoppingcart;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.StringHolder;

import com.telus.csm.ewlnsc.adapter.oqs.domain.ProductCharacteristicValue;
import com.telus.csm.ewlnsc.adapter.scis.domain.ProductInstanceInfoRestVO;
import com.telus.csm.ewlnsc.adapter.woscs.domain.PartyInteractionRole;
import com.telus.csm.ewlnsc.adapter.woscs.domain.Product;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductCharacteristic;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductCharacteristicNameValue;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductComponent;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductOffering;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductOrderItem;
import com.telus.csm.ewlnsc.delegate.ReferencePDSDataSvcBusDelegator;
import com.telus.csm.ewlnsc.domain.AdditionalProductItemTypeVO;
import com.telus.csm.ewlnsc.domain.AddressVO;
import com.telus.csm.ewlnsc.domain.AppointmentDetailTypeVO;
import com.telus.csm.ewlnsc.domain.AppointmentTypeVO;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.DisconnectRequestTypeVO;
import com.telus.csm.ewlnsc.domain.FFHDiscountTypeVO;
import com.telus.csm.ewlnsc.domain.FFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.FFHFeatureTypeVO;
import com.telus.csm.ewlnsc.domain.FFHProductPlanAddOnTypeVO;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO2;
import com.telus.csm.ewlnsc.domain.HomePhoneProductTypeVO;
import com.telus.csm.ewlnsc.domain.InternetProductTypeVO;
import com.telus.csm.ewlnsc.domain.MultilingualTextVO;
import com.telus.csm.ewlnsc.domain.OrderCommentVO;
import com.telus.csm.ewlnsc.domain.ProductComponentVO;
import com.telus.csm.ewlnsc.domain.RelatedCartItemVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressRequestVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressResponseVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingProfileVO;
import com.telus.csm.ewlnsc.domain.SmartRingTypeVO;
import com.telus.csm.ewlnsc.domain.TelevisionProductTypeVO;
import com.telus.csm.ewlnsc.domain.product.AssociatedProductItemVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemVO;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Discount;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.DiscountProductCatalogueItem;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.OfferProduct;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueItem;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineEquipment;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineEquipmentItem;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.SweetenerOfferSummary;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.WirelineOfferProductSummary;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemDO;
import com.telus.csm.ewlnsc.helper.CommonWLNGridHelper;
import com.telus.csm.ewlnsc.task.GetServiceAddressDetailsTask;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;

public class OPCommonProvideTransformer {
	private static final LoggerUtil logger = LoggerUtil.getLogger(OPCommonProvideTransformer.class);
	public static Properties PRODUCT_CHARACTERISTCS_PROPS = new Properties();
	static {
		InputStream inStream = OPShoppingCartTransformer.class.getClassLoader().getResourceAsStream(Constants.PRODUCT_CHARACTERISTICS_EXTERNAL_FILE_NAME);
		try {
			PRODUCT_CHARACTERISTCS_PROPS.load(inStream);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load from " + Constants.PRODUCT_CHARACTERISTICS_EXTERNAL_FILE_NAME, e);
		}
	}
	protected static enum AcquisitionType {RE, POE, SA};
	protected static final String OP_ACQUISITION_TYPE_RENT = "RE";


	
	/*************************************************/    
	/* findWirelineOfferProduct                      */
	/*    - use by child class                       */
	/*************************************************/
	protected static WirelineOfferProduct findWirelineOfferProduct(ShoppingCartContextVO shoppingCartContextVO, 
			                                                       CartItemVO cartItemVO, 
			                                                       String serviceType) {
		
		String functionName = "findWirelineOfferProduct";
		logger.debug(functionName, "begin");
		
		WirelineOfferProduct wirelineOfferProduct = null;
		if(cartItemVO.getProductMarketOffering() != null 
				&& cartItemVO.getProductMarketOffering().getOfferHeader() != null) {
			
			GetSalesOfferDetailResponseVO2 offerResp = 
						shoppingCartContextVO.getOfferByCartItemOfferId(cartItemVO.getProductMarketOffering().getOfferHeader().getOfferId());
			
			if(offerResp != null && offerResp.getOffer() != null) {
				
				OfferProduct offerProduct = offerResp.getOffer().getOfferProduct();
				if(offerProduct != null) {
					wirelineOfferProduct = getProductByProductTypeCd(offerProduct, serviceType);
				}
			}
		}
		
		logger.debug(functionName, "end");
		return wirelineOfferProduct;
	} 
	
	/*************************************************/    
	/* createCommentComponent                        */
	/*************************************************/
	private static ProductComponent createCommentComponent(OrderCommentVO comment, String actionType, String orderEntityId) {
		ProductComponent commentComponent = new ProductComponent();
		commentComponent.setName(Constants.COMPONENT_COMMENT_NAME);
		commentComponent.setActionType(actionType);

		// noteText
		commentComponent.addProductCharacteristic(createProductCharacteristic(null, Constants.NOTE_TEXT, comment.getNoteText()));

		// noteTypeCode
		commentComponent.addProductCharacteristic(createProductCharacteristic(null, Constants.NOTE_TYPE_CODE, comment.getNoteTypeCode()));

		// orderEntityTypeCode
		commentComponent.addProductCharacteristic(createProductCharacteristic(null, Constants.ORDER_ENTITY_TYPE_CODE, comment.getOrderEntityTypeCode()));

		// originatorId
		commentComponent.addProductCharacteristic(createProductCharacteristic(null, Constants.ORIGINATOR_ID, comment.getOriginatorId()));
		
		// orderEnityId
		commentComponent.addProductCharacteristic(createProductCharacteristic(null, Constants.ORDER_ENTITY_ID, orderEntityId));

		return commentComponent;
	}
	
	
	/*************************************************/    
	/* populateCommonProductOrderItemForUpdateOrder  */
	/*    - use by child class                       */
	/*************************************************/
	protected  static ProductOrderItem populateCommonProductOrderItemForUpdateOrder(CartItemVO cartItemVO, WirelineOfferProduct wirelineOfferProduct, String serviceType, String orderTypeCd, com.telus.csm.ewlnsc.adapter.oqs.domain.ProductOrder shoppingCartProductOrder, List<OrderCommentVO> orderComments) {
		ProductOrderItem productOrderItem = new ProductOrderItem();
		Product product = new Product();

		productOrderItem.setProduct(product);
		product.setName(serviceType);
		//product.setActionType(findTopActionType(orderTypeCd));

		// catalog id
		if(wirelineOfferProduct != null && wirelineOfferProduct.getMainComponentIdentifier() != null) {
			product.setCatalogId(wirelineOfferProduct.getMainComponentIdentifier().getExternalProductCatalogId());
		}

		String productOrderItemId = null;

		// product serial number, id
		for (com.telus.csm.ewlnsc.adapter.oqs.domain.ProductOrderItem shoppingCartProductOrderItem : shoppingCartProductOrder.getProductOrderItems()) {
			if ( (shoppingCartProductOrderItem != null) &&
				 (shoppingCartProductOrderItem.getProduct() != null) &&
				 (shoppingCartProductOrderItem.getProduct().getCatalogId() != null) &&
				 (shoppingCartProductOrderItem.getProduct().getCatalogId().equals(product.getCatalogId())) ) {
				product.setProductSerialNumber(shoppingCartProductOrderItem.getProduct().getProductSerialNumber());

				productOrderItemId = String.valueOf(shoppingCartProductOrderItem.getId());
				productOrderItem.setId(productOrderItemId);

				break;
			}
		}

		// comments
		if ( (orderComments != null) && (!orderComments.isEmpty()) ) {
			product.addProductComponent(createCommentsComponent(orderComments, serviceType, productOrderItemId));
		}

		return productOrderItem;
	}
	
	
	
	private static WirelineOfferProduct getProductByProductTypeCd(OfferProduct offerProduct, String productTypeCd) {
		if(offerProduct == null || offerProduct.getWirelineOfferProductList() == null || StringUtils.isEmpty(productTypeCd)) {
			return null;
		}
		for ( WirelineOfferProduct product: offerProduct.getWirelineOfferProductList()){
			if (productTypeCd.equalsIgnoreCase(product.getProductTypeCode())){
				return product;
			}
		}
		return null;
	}
	

	
	private static ProductComponent createCommentsComponent(List<OrderCommentVO> orderComments, String serviceType, String orderEntityId) {
		ProductComponent commentComponent = new ProductComponent();
		commentComponent.setName(Constants.COMPONENT_COMMENTS_NAME);

		for (OrderCommentVO comment : orderComments) {
			if (comment != null) {
				if ( (comment.getProductType() != null) && (!comment.getProductType().isEmpty()) ) {
					if (serviceType.equals(comment.getProductType())) {
						commentComponent.addProductComponent(createCommentComponent(comment, Constants.CLIENT_ITEM_ACTION_TYPE_AD, orderEntityId));
					}
				}
				else {
					commentComponent.addProductComponent(createCommentComponent(comment, Constants.CLIENT_ITEM_ACTION_TYPE_AD, orderEntityId));
				}
			}
		}

		if ( (commentComponent.getProductComponents() == null) || (commentComponent.getProductComponents().isEmpty()) ) {
			commentComponent = null;
		}

		return commentComponent;
	}
	
	protected static ProductCharacteristic createProductCharacteristic(String catalogAttributeId, String name, String value) {
		if(StringUtils.isEmpty(name) || StringUtils.isEmpty(value)) {
			return null;
		}
		ProductCharacteristic productCharacteristic = new ProductCharacteristic();
		productCharacteristic.setCatalogAttributeId(catalogAttributeId);
		productCharacteristic.setName(name);
		productCharacteristic.setValue(value);
		return productCharacteristic;
	}

	protected static ProductCharacteristic createProductCharacteristic(String name, String value) {
		return createProductCharacteristic(null, name, value);
	}
	
	protected static Date findServiceRequiredDate(CartItemVO cartItemVO, String serviceType, ShoppingCartContextVO shoppingCartContextVO) {
		
		AppointmentDetailTypeVO appointmentDetail = null;
		
		if(EnterpriseWLNSalesServicesConstants.HSIC.equals(serviceType)) {
			appointmentDetail = cartItemVO.getProducts().getInternetProduct().getAppointmentDetail();
		}else if(EnterpriseWLNSalesServicesConstants.TTV.equals(serviceType)) {
			appointmentDetail = cartItemVO.getProducts().getTelevisionProduct().getAppointmentDetail();
		}else if(EnterpriseWLNSalesServicesConstants.SING.equals(serviceType)) {
			appointmentDetail = cartItemVO.getProducts().getHomePhoneProduct().getAppointmentDetail();
		}
		
		if(appointmentDetail != null && appointmentDetail.getBookedAppointmentDate() != null && appointmentDetail.getBookedAppointmentDate().getAppointmentDate() != null) {
			return appointmentDetail.getBookedAppointmentDate().getAppointmentDate();
		}
		
		
		//if booked is not found then earliest from 
		if(appointmentDetail != null && appointmentDetail.getPreferredAppointmentDates() != null) {
			Date earliestDate = null;
			for(AppointmentTypeVO appointment: appointmentDetail.getPreferredAppointmentDates()) {
				if(appointment.getAppointmentDate() != null) {
					Date dt = appointment.getAppointmentDate();
					if(earliestDate == null) {
						earliestDate = dt;
					} else if(earliestDate.compareTo(dt) > 0) {
						earliestDate = dt;
					}
				}
			}
			if(earliestDate != null) {
				return earliestDate;
			}
		}
		
		//If not preferred found, check if SW.
		List<String> swServiceTypes = shoppingCartContextVO.getFeasibilityResponseVO().getInstallTypeSW();
		if(swServiceTypes != null && swServiceTypes.contains(serviceType)) {
			//qc70904 need to looking for other product booking date in all cart items instead of this cart item due to offer split project.
			Date bookingDateOnOtherProduct = getBookingDateOtherProducts(shoppingCartContextVO.getShoppingCartVO());
			if(bookingDateOnOtherProduct != null) {
				return bookingDateOnOtherProduct;
			}
		}
		
		//sysdate + lagtime
		ShoppingProfileVO shoppingProfileVO = shoppingCartContextVO.getShoppingCartVO().getShoppingProfile();
		logger.debug("findServiceRequiredDate", "Cannot find ServiceRequiredDate from an appointmentDetail. Will use RefPDS " + EnterpriseWLNSalesServicesConstants.REFPDS_LAG_TIME_TABLE);
		Map<String,String> nguiLagTimeMap = ReferencePDSDataSvcBusDelegator.getInstance().getReferencePDSTableByName(EnterpriseWLNSalesServicesConstants.REFPDS_LAG_TIME_TABLE);
		if(nguiLagTimeMap != null && !StringUtils.isEmpty(serviceType)) {
			String key = serviceType;
			if(shoppingProfileVO != null && shoppingProfileVO.getUserProfile() != null && Constants.D2C_PARTNER.equalsIgnoreCase(shoppingProfileVO.getUserProfile().getSalesPersonRoleCd())) {
				key += "_OUT";//outbound channel
			} else {
				key += "_IN";//inbound channel
			}
			String sLagDays = nguiLagTimeMap.get(key);
			if(!StringUtils.isEmpty(sLagDays)) try {
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DAY_OF_YEAR, Integer.parseInt(sLagDays));
				return cal.getTime();
			} catch(NumberFormatException e) {
				logger.warn("", "findServiceRequiredDate", "Not integer value for the key [" + key + "] in RefPDS table " +EnterpriseWLNSalesServicesConstants.REFPDS_LAG_TIME_TABLE, e);
			}
		}
		return null;
	}
	
	/**
	 * find any booking date in the shopping cart
	 * @param shoppingCart
	 * @return
	 */
	private static Date getBookingDateOtherProducts(ShoppingCartVO shoppingCart) {
		InternetProductTypeVO internetProduct = null;
		TelevisionProductTypeVO tvProduct = null;
		HomePhoneProductTypeVO singProduct = null;
		for(CartItemVO cartItemVO: shoppingCart.getCartItemList()) {
			if(cartItemVO.isSalesOrderItem() && cartItemVO.getProducts() != null) {
				//among all cart, there will be no duplicate product 
				if (cartItemVO.getProducts().getInternetProduct() !=null) {
				    internetProduct = cartItemVO.getProducts().getInternetProduct(); 
				}
				if (cartItemVO.getProducts().getTelevisionProduct() !=null) {
					tvProduct = cartItemVO.getProducts().getTelevisionProduct();
				}
				if (cartItemVO.getProducts().getHomePhoneProduct() !=null) {
					singProduct = cartItemVO.getProducts().getHomePhoneProduct();
				}	
			}
		}
		
		if(internetProduct != null && internetProduct.getAppointmentDetail() != null && internetProduct.getAppointmentDetail().getBookedAppointmentDate() != null &&  internetProduct.getAppointmentDetail().getBookedAppointmentDate().getAppointmentDate() != null) {
			return internetProduct.getAppointmentDetail().getBookedAppointmentDate().getAppointmentDate();
		}
		
		if(tvProduct != null && tvProduct.getAppointmentDetail() != null && tvProduct.getAppointmentDetail().getBookedAppointmentDate() != null &&  tvProduct.getAppointmentDetail().getBookedAppointmentDate().getAppointmentDate() != null) {
			return tvProduct.getAppointmentDetail().getBookedAppointmentDate().getAppointmentDate();
		}
		
		if(singProduct != null && singProduct.getAppointmentDetail() != null && singProduct.getAppointmentDetail().getBookedAppointmentDate() != null &&  singProduct.getAppointmentDetail().getBookedAppointmentDate().getAppointmentDate() != null) {
			return singProduct.getAppointmentDetail().getBookedAppointmentDate().getAppointmentDate();
		}
		
		//Try preferred date.
		Date preferredDate = null;
		if(internetProduct != null && internetProduct.getAppointmentDetail() != null && internetProduct.getAppointmentDetail().getPreferredAppointmentDates() != null) {
			preferredDate = getEarlierstPreferredDate(internetProduct.getAppointmentDetail().getPreferredAppointmentDates());
			if(preferredDate != null) {
				return preferredDate;
			}
		}
		
		if(tvProduct != null && tvProduct.getAppointmentDetail() != null && tvProduct.getAppointmentDetail().getPreferredAppointmentDates() != null) {
			preferredDate = getEarlierstPreferredDate(tvProduct.getAppointmentDetail().getPreferredAppointmentDates());
			if(preferredDate != null) {
				return preferredDate;
			}
		}
		
		if(singProduct != null && singProduct.getAppointmentDetail() != null && singProduct.getAppointmentDetail().getPreferredAppointmentDates() != null) {
			preferredDate = getEarlierstPreferredDate(singProduct.getAppointmentDetail().getPreferredAppointmentDates());
			if(preferredDate != null) {
				return preferredDate;
			}
		}
		
		return null;
	}

	private static Date getEarlierstPreferredDate(List<AppointmentTypeVO> preferredAppointmentDates) {
		Date earliestDate = null;
		for(AppointmentTypeVO appointment: preferredAppointmentDates) {
			if(appointment.getAppointmentDate() != null) {
				Date dt = appointment.getAppointmentDate();
				if(earliestDate == null) {
					earliestDate = dt;
				} else if(earliestDate.compareTo(dt) > 0) {
					earliestDate = dt;
				}
			}
		}
		return earliestDate;
	}

	protected static ProductOrderItem populateCommonProductOrderItem(OperationHeader operationHeader, CartItemVO cartItemVO, WirelineOfferProduct wirelineOfferProduct, String serviceType, String orderTypeCd, ShoppingCartContextVO shoppingCartContextVO, Date serviceRequiredDate) {

		ProductOrderItem productOrderItem = new ProductOrderItem();
		productOrderItem.setAction(findProductOrderItemAction(orderTypeCd));
		
		productOrderItem.setPartyInteractionRole(populatePartyInteractionRoleBase(operationHeader, shoppingCartContextVO));
		

		Product product = new Product();
		productOrderItem.setProduct(product);
		product.setName(serviceType);
		product.setActionType(findTopActionType(orderTypeCd));
		if(wirelineOfferProduct != null && wirelineOfferProduct.getMainComponentIdentifier() != null) {
			product.setCatalogId(wirelineOfferProduct.getMainComponentIdentifier().getExternalProductCatalogId());
		}
		
//		ProductComponent productInformationComp = new ProductComponent();
//		productInformationComp.setName(EnterpriseWLNSalesServicesConstants.COMP_PRODUCT_INFORMATION);
//		product.addProductComponent(productInformationComp);
		
		ProductComponent generalProductInfoComp = new ProductComponent();
		generalProductInfoComp.setName(EnterpriseWLNSalesServicesConstants.COMP_GENERAL_PRODUCT_INFO);
		product.addProductComponent(generalProductInfoComp);
		if(wirelineOfferProduct != null && wirelineOfferProduct.getProductTemplateIdentifier() != null) {
			String extOfferId = wirelineOfferProduct.getProductTemplateIdentifier().getExternalProductCatalogId();
			ProductCharacteristic offerCatIdChar = createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OFFER_CATALOG_ID, extOfferId);
			generalProductInfoComp.addProductCharacteristic(offerCatIdChar);
			if(EnterpriseWLNSalesServicesConstants.UPGRADE.equalsIgnoreCase(orderTypeCd)) {
				com.telus.csm.ewlnsc.adapter.oqs.domain.Product existingProd = findExistingProduct(shoppingCartContextVO, serviceType);
				if(existingProd != null) {
					com.telus.csm.ewlnsc.adapter.oqs.domain.Product existingGenProdInfo = findOpProductComponentByName(EnterpriseWLNSalesServicesConstants.COMP_GENERAL_PRODUCT_INFO, existingProd.getProductComponents());
					String origOfferId = findProductCharacteristicValue(existingGenProdInfo, EnterpriseWLNSalesServicesConstants.OFFER_CATALOG_ID);
					if(!StringUtils.isEmpty(origOfferId) && !origOfferId.equalsIgnoreCase(extOfferId)) {
						generalProductInfoComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.ORIGINAL_OFFER_CATALOG_ID, origOfferId));
					}
				}
			}
		}
		
		//From Allan: Please remove this from our OP request.
//		ProductComponent marketOfferInfoComp = new ProductComponent();
//		marketOfferInfoComp.setName(EnterpriseWLNSalesServicesConstants.COMP_MARKET_OFFER_INFO);
//		product.addProductComponent(marketOfferInfoComp);
//		ProductCharacteristic marketOfferNameChar = createProductCharacteristic(EnterpriseWLNSalesServicesConstants.MARKET_OFFER_NAME, null);
//		marketOfferInfoComp.addProductCharacteristic(marketOfferNameChar);
//		ProductCharacteristic marketOfferIdChar = createProductCharacteristic(EnterpriseWLNSalesServicesConstants.MARKET_OFFER_ID, null);
//		marketOfferInfoComp.addProductCharacteristic(marketOfferIdChar);
//		if(cartItemVO.getProductMarketOffering() != null && cartItemVO.getProductMarketOffering().getOfferHeader() != null) {
//			marketOfferNameChar.setValue(cartItemVO.getProductMarketOffering().getOfferHeader().getOfferCode());
//			marketOfferIdChar.setValue(cartItemVO.getProductMarketOffering().getOfferHeader().getOfferId());
//		}

		ProductComponent orderActionAttrComp = new ProductComponent();
		orderActionAttrComp.setName(EnterpriseWLNSalesServicesConstants.COMP_ORDER_ACTION_ATTRIBUTES);
		product.addProductComponent(orderActionAttrComp);
		
		//bypassClearanceInd
		/**
		 * From: Sergey
		 * I had clarification with OMS again during investigation of this issue - adding HS for existing SL
		 * so in case clearance information is required, it should be or clearance information or bypassCLearanceInd = true
		 */
		boolean bypassClearanceInd = false;
		if (EnterpriseWLNSalesServicesConstants.ACTIVATION.equalsIgnoreCase(orderTypeCd)){//Provide
			if(EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(serviceType) || EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(serviceType)){
				bypassClearanceInd = true;
			}
		}
		orderActionAttrComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.BYPASS_CLEARANCE_IND, String.valueOf(bypassClearanceInd)));

		if(serviceRequiredDate != null) {
			//Should be: "value": "2018-10-04T09:40:37Z"
			ProductCharacteristic serviceRequiredDateChar = new ProductCharacteristic();
			serviceRequiredDateChar.setName(EnterpriseWLNSalesServicesConstants.SERVICE_REQUIRED_DATE);
			String serviceRequiredDateVal = formatToOpServiceRequiredDate(serviceRequiredDate);
			serviceRequiredDateChar.setValue(serviceRequiredDateVal);
			orderActionAttrComp.addProductCharacteristic(serviceRequiredDateChar);
		}
		
		ProductComponent seviceAddressComp = new ProductComponent();
		seviceAddressComp.setName(EnterpriseWLNSalesServicesConstants.COMP_SERVICE_ADDRESS);
		product.addProductComponent(seviceAddressComp);
		if(shoppingCartContextVO.getServiceAddressResponseVO() != null && shoppingCartContextVO.getServiceAddressResponseVO().getServiceAddress() != null) {
			seviceAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_FMS_ADDRESS_ID, shoppingCartContextVO.getServiceAddressResponseVO().getServiceAddress().getAddressId()));
			seviceAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_RATECENTRE, shoppingCartContextVO.getServiceAddressResponseVO().getServiceAddress().getRateCenter()));
			seviceAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_CITY_CLICODE, shoppingCartContextVO.getServiceAddressResponseVO().getServiceAddress().getMunicipalityClli()));
			seviceAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_POSTOFFICEBOX, shoppingCartContextVO.getServiceAddressResponseVO().getServiceAddress().getPostOfficeBoxNumber()));
			seviceAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_APPARTMENT, shoppingCartContextVO.getServiceAddressResponseVO().getServiceAddress().getUnitNumber()));
			seviceAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_STREET_NUMBER, shoppingCartContextVO.getServiceAddressResponseVO().getServiceAddress().getStreetNumber()));
			seviceAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_STREET_NAME, shoppingCartContextVO.getServiceAddressResponseVO().getServiceAddress().getStreetName()));
			seviceAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_VECTOR, shoppingCartContextVO.getServiceAddressResponseVO().getServiceAddress().getStreetDirectionCode()));
			seviceAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_CITY, shoppingCartContextVO.getServiceAddressResponseVO().getServiceAddress().getMunicipalityName()));
			seviceAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_STATE, shoppingCartContextVO.getServiceAddressResponseVO().getServiceAddress().getProvinceStateCode()));
			seviceAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_COUNTRY, shoppingCartContextVO.getServiceAddressResponseVO().getServiceAddress().getCountryCode()));
			seviceAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_POSTALCODE, shoppingCartContextVO.getServiceAddressResponseVO().getServiceAddress().getPostalZipCode()));
		}
		return productOrderItem;
	}

	private static String findProductOrderItemAction(String orderTypeCd) {
		if (EnterpriseWLNSalesServicesConstants.ACTIVATION.equalsIgnoreCase(orderTypeCd)){
			return EnterpriseWLNSalesServicesConstants.PR;
		
		//direction from Allan and Aman, that for Upgrade Orders, we will not pass the action in the productOrderItem level.   We will only pass for Activation (PR) orders	
//		} else if (EnterpriseWLNSalesServicesConstants.UPGRADE.equalsIgnoreCase(orderTypeCd)){
//			return EnterpriseWLNSalesServicesConstants.CH;
		}
		return null;
	}
	
	protected static PartyInteractionRole populatePartyInteractionRoleBase(OperationHeader operationHeader, ShoppingCartContextVO shoppingCartContextVO) {
		PartyInteractionRole result = new PartyInteractionRole();
		List<ProductCharacteristicNameValue> productCharacteristics = new ArrayList<ProductCharacteristicNameValue>();
		result.setProductCharacteristics(productCharacteristics);

		if (operationHeader != null) {
			ProductCharacteristicNameValue externalRefNum = new ProductCharacteristicNameValue();
			externalRefNum.setValue(operationHeader.getSalesTransactionId());
			externalRefNum.setName(EnterpriseWLNSalesServicesConstants.EXTERNAL_REF_NUM);
			productCharacteristics.add(externalRefNum);
			
			if(operationHeader.getUserProfile() != null) {
				ProductCharacteristicNameValue dealerId = new ProductCharacteristicNameValue();
				dealerId.setValue(operationHeader.getUserProfile().getSalesRepAssociatedOutletList().get(0).getSalesRepAssociatedChannelOutletId());
				dealerId.setName(EnterpriseWLNSalesServicesConstants.STORE_NAME);
				productCharacteristics.add(dealerId);				
				
				ProductCharacteristicNameValue dealerSalesRep = new ProductCharacteristicNameValue();
				dealerSalesRep.setValue(operationHeader.getUserProfile().getSalesRepId());
				dealerSalesRep.setName(EnterpriseWLNSalesServicesConstants.DEALER_SALES_REP_ID);
				productCharacteristics.add(dealerSalesRep);
			}
			
		}
		
		ProductCharacteristicNameValue externalRefSrc = new ProductCharacteristicNameValue();
		externalRefSrc.setName(EnterpriseWLNSalesServicesConstants.EXTERNAL_REF_SRC);
		if(shoppingCartContextVO.getShoppingCartVO().getShoppingProfile().getUserProfile() != null 
				&& Constants.D2C_ORDER.equalsIgnoreCase(shoppingCartContextVO.getShoppingCartVO().getShoppingProfile().getUserProfile().getChannelOrganizationTypeCd())) {
			externalRefSrc.setValue(Constants.OP_D2C_ORDER);
		} else {
			externalRefSrc.setValue(Constants.VESTA_ORDER);
		}
		productCharacteristics.add(externalRefSrc);

		return result;
	}

	protected static String findTopActionType(String orderTypeCd) {
		if (EnterpriseWLNSalesServicesConstants.ACTIVATION.equalsIgnoreCase(orderTypeCd)){
			return EnterpriseWLNSalesServicesConstants.AD;
		} else if (EnterpriseWLNSalesServicesConstants.UPGRADE.equalsIgnoreCase(orderTypeCd)){
			return EnterpriseWLNSalesServicesConstants.UP;
		}
		return null;
	}

	public static com.telus.csm.ewlnsc.adapter.oqs.domain.Product findOpProductComponentByName(String compName, List<com.telus.csm.ewlnsc.adapter.oqs.domain.Product> products) {
		if(StringUtils.isEmpty(compName) || products == null) {
			return null;
		}
		for(com.telus.csm.ewlnsc.adapter.oqs.domain.Product prod: products) {
			if(compName.equalsIgnoreCase(prod.getName())) {
				return prod;
			}
		}
		return null;
	}

	public static ProductComponent findProductComponentByName(String compName, List<ProductComponent> productComponents) {
		if(StringUtils.isEmpty(compName) || productComponents == null) {
			return null;
		}
		for(ProductComponent productComponent: productComponents) {
			if(compName.equalsIgnoreCase(productComponent.getName())) {
				return productComponent;
			}
		}
		return null;
	}

	public static ProductComponent findProductComponentByCatId(String prodCatId, List<ProductComponent> productComponents) {
		if(StringUtils.isEmpty(prodCatId) || productComponents == null) {
			return null;
		}
		for(ProductComponent productComponent: productComponents) {
			if(prodCatId.equalsIgnoreCase(productComponent.getCatalogId())) {
				return productComponent;
			}
		}
		return null;
	}

	protected static String findProductCharacteristicValue(ProductComponent product, String prodCharName) {
		if(!StringUtils.isEmpty(prodCharName) && product != null && product.getProductCharacteristics() != null) {
			for(ProductCharacteristic prodChar: product.getProductCharacteristics()) {
				if(prodCharName.equalsIgnoreCase(prodChar.getName())) {
					return prodChar.getValue();
				}
			}
		}
		return null;
	}

	protected static String findProductCharacteristicValue(com.telus.csm.ewlnsc.adapter.oqs.domain.Product product, String prodCharName) {
		if(!StringUtils.isEmpty(prodCharName) && product != null && product.getProductCharacteristics() != null) {
			for(ProductCharacteristicValue prodChar: product.getProductCharacteristics()) {
				if(prodCharName.equalsIgnoreCase(prodChar.getName())) {
					return prodChar.getValue();
				}
			}
		}
		return null;
	}

	public static com.telus.csm.ewlnsc.adapter.oqs.domain.Product findExistingProduct(ShoppingCartContextVO shoppingCartContextVO, String productTypeCd) {
		if(!StringUtils.isEmpty(productTypeCd) 
				&& shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO() != null
				&& shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO().getGetProductsByCustomerIdAdapterResponse() != null
				&& shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO().getGetProductsByCustomerIdAdapterResponse().getProducts() != null) {
			for(com.telus.csm.ewlnsc.adapter.oqs.domain.Product prod: shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO().getGetProductsByCustomerIdAdapterResponse().getProducts()) {
				if(productTypeCd.equalsIgnoreCase(prod.getServiceType())) {
					return prod;
				}
			}
		}
		return null;
	}
	
	protected static ProductComponent populateProdComp(WirelineOfferProduct wirelineOfferProduct, ProductComponentVO prodCompVO, String serviceType, String cotractTermMonths, ShoppingCartContextVO shoppingCartContextVO) {
		if(EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_ADD.equalsIgnoreCase(prodCompVO.getAction())) {
			//add components
			return createProdComp(shoppingCartContextVO, wirelineOfferProduct, prodCompVO, serviceType, cotractTermMonths, null);
		} else if(EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(prodCompVO.getAction())) {//explicit remove
			ProductComponent prodComp = new ProductComponent();
			prodComp.setActionType(findProdCompActionType(prodCompVO.getAction()));
			String externalCatalogueId = findExternalCatalogueId(prodCompVO.getProductCatalogueId());
			prodComp.setCatalogId(externalCatalogueId);
			//find a serial number, etc.
			if(shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO() != null && shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO().getGetProductsByCustomerIdAdapterResponse() != null) {
				com.telus.csm.ewlnsc.adapter.oqs.domain.Product prod = findOpProductComponentByExtCatId(externalCatalogueId, shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO().getGetProductsByCustomerIdAdapterResponse().getProducts());
				if(prod != null) {
					prodComp.setProductSerialNumber(prod.getProductSerialNumber());
					prodComp.setDescription(prod.getDescription());
				}
			}
			return prodComp;
		} else if(EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_NO_CHANGE.equalsIgnoreCase(prodCompVO.getAction())) {
			//recontract
			//find if a term has been changed
			ProductInstanceInfoRestVO productInstanceInfoRestVO = shoppingCartContextVO.findProductInstanceInfoRestVO(serviceType);
			if(productInstanceInfoRestVO != null) {
				String currentTerm = productInstanceInfoRestVO.getTermCd();
				if(!StringUtils.isEmpty(currentTerm) && !currentTerm.equalsIgnoreCase(contractTermMonthsToOpYears(cotractTermMonths))) {
					return createProdComp(shoppingCartContextVO, wirelineOfferProduct, prodCompVO, serviceType, cotractTermMonths, productInstanceInfoRestVO.getProductInstanceId());
				}
			}
		}
		return null;
	}

	private static ProductComponent createProdComp(ShoppingCartContextVO shoppingCartContextVO, WirelineOfferProduct wirelineOfferProduct, ProductComponentVO prodCompVO, String serviceType, String cotractTermMonths, String defaultProductSerialNumber) {
		if(wirelineOfferProduct == null || wirelineOfferProduct.getProductComponentList() == null) {
			logger.warn("", "createProdComp", "wirelineOfferProduct or wirelineOfferProduct.getProductComponentList() is null");
			return null;
		}
		for(com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductComponent offerProdComp: wirelineOfferProduct.getProductComponentList()) {
			for(ProductCatalogueItem prodCatItm: offerProdComp.getProductCatalogueItemList()) {
				if(prodCompVO.getProductCatalogueId() != null && prodCompVO.getProductCatalogueId().equalsIgnoreCase(prodCatItm.getProductCatalogueIdentifier().getProductCatalogueId())) {
					ProductComponent prodComp = new ProductComponent();
					prodComp.setActionType(findProdCompActionType(prodCompVO.getAction()));
					prodComp.setCatalogId(prodCatItm.getProductCatalogueIdentifier().getExternalProductCatalogId());
					if(prodCatItm.getProductCatalogueNameList() != null && !prodCatItm.getProductCatalogueNameList().isEmpty()) {
						prodComp.setDescription(prodCatItm.getProductCatalogueNameList().get(0).getDescriptionText());
					}
					//TODO name, serviceType. E.g. HSEXL, ADSP
					
					//set productSerialNumber when updating an existing component
					if(EnterpriseWLNSalesServicesConstants.UP.equalsIgnoreCase(prodComp.getActionType())) {
						com.telus.csm.ewlnsc.adapter.oqs.domain.Product opProd = findOpProductComponentByExtCatId(prodComp.getCatalogId(), shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO().getGetProductsByCustomerIdAdapterResponse().getProducts());
						if(opProd != null && !StringUtils.isEmpty(opProd.getProductSerialNumber())) {
							prodComp.setProductSerialNumber(opProd.getProductSerialNumber());
						} else if(!StringUtils.isEmpty(defaultProductSerialNumber)) {//this is the old logic. Keep it for safety
							prodComp.setProductSerialNumber(defaultProductSerialNumber);
						}
					}
					
					if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(serviceType)) {
						//contract term
						String catalogAttributeId = PRODUCT_CHARACTERISTCS_PROPS.getProperty(Constants.CONTRACT_TERM + Constants.UNDERSCORE + serviceType + Constants.PRODUCT_COMPONENT_CATALOG_ATTRIBUTE_ID);
						String name = PRODUCT_CHARACTERISTCS_PROPS.getProperty(Constants.CONTRACT_TERM + Constants.UNDERSCORE + serviceType + Constants.PRODUCT_COMPONENT_ATTRIBUTE_NAME);
						String value = contractTermMonthsToOpYears(cotractTermMonths);
						prodComp.addProductCharacteristic(createProductCharacteristic(catalogAttributeId, name, value));
					}
					return prodComp;
				}
			}
		}
		return null;
	}

	public static String contractTermMonthsToOpYears(String cotractTermMonths) {
		int contractTermYears = 0;
		try {
			contractTermYears = Integer.parseInt(cotractTermMonths) /12;
		} catch(Exception e) {
			logger.warn("", "createProdComp", "failed to parse cotractTermMonths: " + cotractTermMonths, e);
		}
		String value = contractTermYears == 0? Constants.TERM_CODE_MTM: String.valueOf(contractTermYears);
		return value;
	}

	protected static String findProdCompActionType(String action) {
		if (EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_ADD.equalsIgnoreCase(action)){
			return EnterpriseWLNSalesServicesConstants.AD;
		} else if (EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(action)){
			return EnterpriseWLNSalesServicesConstants.RM;
		} else if (EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_NO_CHANGE.equalsIgnoreCase(action)){
			return EnterpriseWLNSalesServicesConstants.UP;
		}
		return null;
	}

	protected static String findExternalCatalogueId(String productCatalogueId) {
		if(StringUtils.isEmpty(productCatalogueId)) {
			return null;
		}
		CatalogueItemDO catItmDO = CommonWLNGridHelper.getInstance().getCatalogueItemById(productCatalogueId);
		if(catItmDO != null) {
			return catItmDO.getProductCode();
		}
		
		return null;
	}
	
	protected static String getProductCatalogItemTypeCd(String productCatalogueId) {
		if(StringUtils.isEmpty(productCatalogueId)) {
			return null;
		}
		CatalogueItemDO catItmDO = CommonWLNGridHelper.getInstance().getCatalogueItemById(productCatalogueId);
		if(catItmDO != null) {
			return catItmDO.getItemType();
		}
		return null;
	}

	protected static com.telus.csm.ewlnsc.adapter.oqs.domain.Product findOpProductComponentByExtCatId(String externalCatalogueId, List<com.telus.csm.ewlnsc.adapter.oqs.domain.Product> products) {
		if(StringUtils.isEmpty(externalCatalogueId) || products == null) {
			return null;
		}
		for(com.telus.csm.ewlnsc.adapter.oqs.domain.Product prod: products) {
			if(externalCatalogueId.equalsIgnoreCase(prod.getCatalogId())) {
				return prod;
			}
			com.telus.csm.ewlnsc.adapter.oqs.domain.Product prodChild = findOpProductComponentByExtCatId(externalCatalogueId, prod.getProductComponents());
			if(prodChild != null) {
				return prodChild;
			}
		}
		return null;
	}
	
	protected static ProductCatalogueItem findFeatureProductCatalogueItem(FFHFeatureTypeVO feature, WirelineOfferProduct wirelineOfferProduct) {
		if(feature == null || feature.getProductCatalogueIdentifier() == null || StringUtils.isEmpty(feature.getProductCatalogueIdentifier().getProductCatalogueId())) {
			return null;
		}
		ProductCatalogueItem foundProdCatItm = null;
		if(wirelineOfferProduct.getFeature() != null && wirelineOfferProduct.getFeature().getIncludedProductCatalogueItemList() != null) {
			for(ProductCatalogueItem prodCatItm: wirelineOfferProduct.getFeature().getIncludedProductCatalogueItemList()) {
				if(prodCatItm.getProductCatalogueIdentifier() != null && feature.getProductCatalogueIdentifier().getProductCatalogueId().equalsIgnoreCase(prodCatItm.getProductCatalogueIdentifier().getProductCatalogueId())) {
					return prodCatItm;
				}
			}
		}
		if(foundProdCatItm == null) {
			if(wirelineOfferProduct.getFeature() != null && wirelineOfferProduct.getFeature().getOptionalProductCatalogueItemList() != null) {
				for(ProductCatalogueItem prodCatItm: wirelineOfferProduct.getFeature().getOptionalProductCatalogueItemList()) {
					if(prodCatItm.getProductCatalogueIdentifier() != null && feature.getProductCatalogueIdentifier().getProductCatalogueId().equalsIgnoreCase(prodCatItm.getProductCatalogueIdentifier().getProductCatalogueId())) {
						return prodCatItm;
					}
				}
			}
		}
		return null;
	}

	protected static ProductItemVO cloneProductItemVO(ProductItemVO prdItmVO) {
		if(prdItmVO == null) {
			return null;
		}
		String jsonString = JsonUtil.getJsonFromObject(prdItmVO);
		ProductItemVO clonedPrdItmVO = JsonUtil.parseJsonToObject(jsonString, ProductItemVO.class);
		return clonedPrdItmVO;
	}

	protected static ProductComponent createAddonProductComponent(WirelineOfferProduct wirelineOfferProduct, FFHProductPlanAddOnTypeVO addon) {
		ProductComponent addonComp = null;
		if(addon != null && EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_ADD.equalsIgnoreCase(addon.getAction()) && wirelineOfferProduct.getAddOn() != null) {
			addonComp = findCreateAddonProductComponent(wirelineOfferProduct.getAddOn().getIncludedProductCatalogueItemList(), addon);
			if(addonComp != null) {
				return addonComp;
			}
			addonComp = findCreateAddonProductComponent(wirelineOfferProduct.getAddOn().getOptionalProductCatalogueItemList(), addon);
		}
		return addonComp;
	}

	private static ProductComponent findCreateAddonProductComponent(List<ProductCatalogueItem> prodCatalogItems, FFHProductPlanAddOnTypeVO addon) {
		if(prodCatalogItems == null || prodCatalogItems.isEmpty()) {
			return null;
		}
		for(ProductCatalogueItem prodCatItm: prodCatalogItems) {
			if(addon.getProductCatalogueIdentifier() != null 
					&& addon.getProductCatalogueIdentifier().getProductCatalogueId() != null
					&& prodCatItm.getProductCatalogueIdentifier() != null
					&& addon.getProductCatalogueIdentifier().getProductCatalogueId().equalsIgnoreCase(prodCatItm.getProductCatalogueIdentifier().getProductCatalogueId())) {
				ProductComponent addonComp = new ProductComponent();
				addonComp.setActionType(findProdCompActionType(addon.getAction()));
				addonComp.setCatalogId(prodCatItm.getProductCatalogueIdentifier().getExternalProductCatalogId());
				if(prodCatItm.getProductCatalogueNameList() != null && !prodCatItm.getProductCatalogueNameList().isEmpty()) {
					addonComp.setName(prodCatItm.getProductCatalogueNameList().get(0).getDescriptionText());
				}
				return addonComp;
			}
		}
		return null;
	}
	
	private static String getExternalParentCatelogId(WirelineOfferProduct wirelineOfferProduct, FFHProductPlanAddOnTypeVO addon) {
		String externalParentCatelogId = null;
		
		if(addon != null && EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_ADD.equalsIgnoreCase(addon.getAction()) && wirelineOfferProduct.getAddOn() != null) {
			for(ProductCatalogueItem prodCatItm: wirelineOfferProduct.getAddOn().getIncludedProductCatalogueItemList()) {
				if(addon.getProductCatalogueIdentifier() != null 
						&& addon.getProductCatalogueIdentifier().getProductCatalogueId() != null
						&& prodCatItm.getProductCatalogueIdentifier() != null
						&& addon.getProductCatalogueIdentifier().getProductCatalogueId().equalsIgnoreCase(prodCatItm.getProductCatalogueIdentifier().getProductCatalogueId())) {
					
					externalParentCatelogId = prodCatItm.getParentProductCatalogueIdentifier().getExternalProductCatalogId();
				}
			}
			if(externalParentCatelogId != null) {
				return externalParentCatelogId;
			}
			for(ProductCatalogueItem prodCatItm: wirelineOfferProduct.getAddOn().getOptionalProductCatalogueItemList()) {
				if(addon.getProductCatalogueIdentifier() != null 
						&& addon.getProductCatalogueIdentifier().getProductCatalogueId() != null
						&& prodCatItm.getProductCatalogueIdentifier() != null
						&& addon.getProductCatalogueIdentifier().getProductCatalogueId().equalsIgnoreCase(prodCatItm.getProductCatalogueIdentifier().getProductCatalogueId())) {
					
					externalParentCatelogId = prodCatItm.getParentProductCatalogueIdentifier().getExternalProductCatalogId();
				}
			}
		}
		return externalParentCatelogId;
	}
	
	protected static SweetenerOfferSummary findSweetenerOfferSummary(String promoId, List<SweetenerOfferSummary> sweetenerOffers) {
		if(StringUtils.isEmpty(promoId) || sweetenerOffers == null) {
			return null;
		}
		for(SweetenerOfferSummary offerSummary: sweetenerOffers) {
			if(promoId.equalsIgnoreCase(String.valueOf(offerSummary.getPromotionId()))) {
				return offerSummary;
			}
		}
		return null;
	}

	protected static void addProductOfferings(List<Discount> offerDiscounts, List<FFHDiscountTypeVO> discountVOs, Product product, ShoppingCartContextVO shoppingCartContextVO) {
		if(discountVOs == null || offerDiscounts == null) {
			return;
		}
		
		for(FFHDiscountTypeVO discountVO: discountVOs) {
			if(discountVO.getProductCatalogueIdentifiers() != null) {
				for(ProductComponentVO prodCompVO: discountVO.getProductCatalogueIdentifiers()) {
					//qc72804 - search the priceplan within all offer discounts, find the first one and add to the product offer list in oms
					boolean found = false;
					for(Discount discount: offerDiscounts) {
						// skip manual sweeteners
						if (discount.isManualSweetener()) {
							continue;
						}
						if (!found) {
							for(DiscountProductCatalogueItem discProdCatItm: discount.getDiscountProductCatalogueItemList()) {
								if(prodCompVO.getProductCatalogueId().equalsIgnoreCase(discProdCatItm.getProductCatalogueIdentifier().getProductCatalogueId()) && 
										//QC-69953 Handle submit correctly for same price plans associated with different parent components in the Cart
										//offer's discount must match shopping cart discount by both parent and its own product catalogueId.
										prodCompVO.getParentProductCatalogueId() !=null && discProdCatItm.getParentProductCatalogueIdentifier() !=null &&
										prodCompVO.getParentProductCatalogueId().equalsIgnoreCase(discProdCatItm.getParentProductCatalogueIdentifier().getProductCatalogueId())) {
									found = true;
									addProductOfferingToComponent(discProdCatItm, prodCompVO, shoppingCartContextVO, product);
								}
							}
						}
					}
				}
			}
		}
	}
	
	private static void addProductOfferingToComponent(DiscountProductCatalogueItem discProdCatItm, ProductComponentVO prodCompVO, ShoppingCartContextVO shoppingCartContextVO, Product product) {
		ProductOffering prodOffering = new ProductOffering();
		if (CollectionUtils.isNotEmpty(discProdCatItm.getProductCatalogueNameList())) { // fix for Defect 74249 as suggested by Vatsal/Allan.
			prodOffering.setName(discProdCatItm.getProductCatalogueNameList().get(0).getDescriptionText());
		} else {
			prodOffering.setName("");
		}
		
		prodOffering.setCatalogId(discProdCatItm.getProductCatalogueIdentifier().getExternalProductCatalogId());
		prodOffering.setActionType(findProdCompActionType(prodCompVO.getAction()));
		
		if(EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(prodCompVO.getAction())) {
			//set productSerialNumber
			if(shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO() != null 
					&& shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO().getGetProductsByCustomerIdAdapterResponse() != null 
					&& shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO().getGetProductsByCustomerIdAdapterResponse().getProducts() != null) {
				com.telus.csm.ewlnsc.adapter.oqs.domain.Product prodForOffering = findProductForOfferingByExtCatId(prodOffering.getCatalogId(), shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO().getGetProductsByCustomerIdAdapterResponse().getProducts());
				if(prodForOffering != null) {
					prodOffering.setId(prodForOffering.getProductSerialNumber());
				}
			}
		}

		String parentExtCatalogId = discProdCatItm.getParentProductCatalogueIdentifier().getExternalProductCatalogId();
		if(!StringUtils.isEmpty(parentExtCatalogId)) {
			boolean added = false;
			if(product.getProductComponents() != null) {
				added = addProdOfferingToComp(parentExtCatalogId, prodOffering, product.getProductComponents());
			}	
			if(!added && parentExtCatalogId.equalsIgnoreCase(product.getCatalogId())) {
				if(EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(product.getName()) || EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(product.getName())) {
					for(ProductComponent prodCom: product.getProductComponents()) {
						if(EnterpriseWLNSalesServicesConstants.COMP_PRODUCT_INFORMATION.equalsIgnoreCase(prodCom.getName())) {
							prodCom.addProductOffering(prodOffering);
							added = true;
							break;
						}
					}
				} else {
					product.addProductOffering(prodOffering);
					added = true;
				}
			}
			if(!added) {
				//create a new prod comp
				ProductComponent prodComp = new ProductComponent();
				prodComp.setCatalogId(parentExtCatalogId);
				prodComp.addProductOffering(prodOffering);
				product.addProductComponent(prodComp);
			}
		}
	}

	protected static com.telus.csm.ewlnsc.adapter.oqs.domain.Product findProductForOfferingByExtCatId(String externalCatalogueId,  List<com.telus.csm.ewlnsc.adapter.oqs.domain.Product> products) {
		if(StringUtils.isEmpty(externalCatalogueId) || products == null) {
			return null;
		}
		for(com.telus.csm.ewlnsc.adapter.oqs.domain.Product prod: products) {
			for(com.telus.csm.ewlnsc.adapter.oqs.domain.ProductOffering offering: prod.getProductOffering()) {
				if(externalCatalogueId.equalsIgnoreCase(offering.getCatalogId())) {
					return prod;
				}
			}
			com.telus.csm.ewlnsc.adapter.oqs.domain.Product prodInChild = findProductForOfferingByExtCatId(externalCatalogueId, prod.getProductComponents());
			if(prodInChild != null) {
				return prodInChild;
			}
		}
		return null;
	}

	private static boolean addProdOfferingToComp(String parentExtCatalogId, ProductOffering prodOffering, List<ProductComponent> prodCompList) {
		if(StringUtils.isEmpty(parentExtCatalogId) || prodOffering == null || prodCompList == null) {
			return false;
		}
		for(ProductComponent prodComp: prodCompList) {
			if(parentExtCatalogId.equalsIgnoreCase(prodComp.getCatalogId())) {
				prodComp.addProductOffering(prodOffering);
				return true;
			}
			boolean addedToChild = addProdOfferingToComp(parentExtCatalogId, prodOffering, prodComp.getProductComponents());
			if(addedToChild) {
				return true;
			}
		}
		return false;
	}
	
	//NWLN-9384 -if the grid's PROD_CATLG_ITM_TYPE_CD is "PPL", it is a price plan need to set as productofferring in OMS order
	protected static void addInternetPPLAddonToProductOffering(ProductComponent addonComp, FFHProductPlanAddOnTypeVO addon, ShoppingCartContextVO shoppingCartContextVO, Product product, WirelineOfferProduct wirelineOfferProduct) {
		ProductOffering prodOffering = new ProductOffering();
		prodOffering.setName(addonComp.getName());
		prodOffering.setCatalogId(addonComp.getCatalogId());
		prodOffering.setActionType(addonComp.getActionType());

		if (EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(addon.getAction())) {
			// set productSerialNumber
			if (shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO() != null
					&& shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO()
							.getGetProductsByCustomerIdAdapterResponse() != null
					&& shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO()
							.getGetProductsByCustomerIdAdapterResponse().getProducts() != null) {
				com.telus.csm.ewlnsc.adapter.oqs.domain.Product prodForOffering = findProductForOfferingByExtCatId(
						prodOffering.getCatalogId(), shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO()
								.getGetProductsByCustomerIdAdapterResponse().getProducts());
				if (prodForOffering != null) {
					prodOffering.setId(prodForOffering.getProductSerialNumber());
				}
			}
		}

		String parentExtCatalogId = getExternalParentCatelogId(wirelineOfferProduct, addon);
		if (!StringUtils.isEmpty(parentExtCatalogId)) {
			boolean added = false;
			if (product.getProductComponents() != null) {
				added = addProdOfferingToComp(parentExtCatalogId, prodOffering, product.getProductComponents());
			}
			if (!added && parentExtCatalogId.equalsIgnoreCase(product.getCatalogId())) {
				product.addProductOffering(prodOffering);
				added = true;
			}
			if (!added) {
				// create a new prod comp
				ProductComponent prodComp = new ProductComponent();
				prodComp.setCatalogId(parentExtCatalogId);
				prodComp.addProductOffering(prodOffering);
				product.addProductComponent(prodComp);
			}
		}
	}

	protected static List<ProductOffering> buildInstallationCreditProductOfferings(SweetenerOfferSummary sweetener) {
		List<ProductOffering> prodOfferings = new ArrayList<ProductOffering>();
		CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
		final String functionName="buildInstallationCreditProductOfferings";
		for (WirelineOfferProductSummary offerProductSummary : sweetener.getOfferProductSummary().getWirelineOfferProductSummaryList()) {
			if ( (offerProductSummary != null) &&
				 (offerProductSummary.getDiscountList() != null) &&
				 (!offerProductSummary.getDiscountList().isEmpty()) ) {
				for (com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Discount discount : offerProductSummary.getDiscountList()) {
					if ( (discount != null) &&
						 (discount.getDiscountProductCatalogueItemList() != null) &&
						 (!discount.getDiscountProductCatalogueItemList().isEmpty()) ) {
						for (com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.DiscountProductCatalogueItem discountProductCatalogueItem : discount.getDiscountProductCatalogueItemList()) {
							if ( (discountProductCatalogueItem != null) &&
								 (discountProductCatalogueItem.getProductCatalogueIdentifier() != null) &&
								 (discountProductCatalogueItem.getProductCatalogueIdentifier().getExternalProductCatalogId() != null) &&
								 (!discountProductCatalogueItem.getProductCatalogueIdentifier().getExternalProductCatalogId().isEmpty()) ) {
								
									 String name = null ; 
									  if( (discountProductCatalogueItem.getProductCatalogueNameList() != null) &&
									   		(!discountProductCatalogueItem.getProductCatalogueNameList().isEmpty()) ) {
										  
										  	for (com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.Description description : discountProductCatalogueItem.getProductCatalogueNameList()) {
										  		name = description.getDescriptionText() ;
										  	}
									
									  }
									
									if ( name == null || name.isEmpty()) {
										String catalogueId=discountProductCatalogueItem.getProductCatalogueIdentifier().getProductCatalogueId();
										CatalogueItemDO catalogueItem = gridHelper.getCatalogueItemById(StringUtils.substringBefore(catalogueId, "_"));
										name = catalogueItem.getName();
									}
									if ( name != null && ! name.isEmpty() ) {
										ProductOffering prodOffering = new ProductOffering();
										prodOffering.setName(name);
										prodOffering.setCatalogId(discountProductCatalogueItem.getProductCatalogueIdentifier().getExternalProductCatalogId());
										prodOffering.setActionType(EnterpriseWLNSalesServicesConstants.AD);
										prodOfferings.add(prodOffering);
									}
									else
									{
										logger.info(functionName, "product catalogue name list is null/blank");
									}
								
							}
						}
					}
				}
			}

			break;
		}

		if (prodOfferings.isEmpty()) {
			prodOfferings = null;
		}

		return prodOfferings;
	}

	protected static ProductComponent populateProductEquipment(String serviceType, WirelineOfferProduct wirelineOfferProduct, FFHEquipmentTypeVO wlnEquipment, ShoppingCartContextVO shoppingCartContextVO, StringHolder catalogueIdHolder, StringHolder deliveryMethodHolder) {
		ProductComponent prodComp = null;
		if(EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_ADD.equalsIgnoreCase(wlnEquipment.getAction())) {
			WirelineEquipment wirelineEquipment = null;
			if(wirelineOfferProduct.getWirelineEquipmentList() != null) {
				for(WirelineEquipment equip: wirelineOfferProduct.getWirelineEquipmentList()) {
					if(wlnEquipment.getProductCatalogueIdentifier() != null 
							&& equip.getProductCatalogueItem() != null
							&& wlnEquipment.getProductCatalogueIdentifier().getParentProductCatalogueId().equalsIgnoreCase(equip.getProductCatalogueItem().getParentProductCatalogueIdentifier().getProductCatalogueId())
							&& wlnEquipment.getProductCatalogueIdentifier().getProductCatalogueId().equalsIgnoreCase(equip.getProductCatalogueItem().getProductCatalogueIdentifier().getProductCatalogueId())) {
						wirelineEquipment = equip;
						break;
					}
				}
			}
			if(wirelineEquipment == null) {
				return null;
			}
			prodComp = new ProductComponent();
			prodComp.setActionType(findProdCompActionType(wlnEquipment.getAction()));
			if(wirelineEquipment != null && wirelineEquipment.getProductCatalogueItem() != null && wirelineEquipment.getProductCatalogueItem().getParentProductCatalogueIdentifier() != null) {
				catalogueIdHolder.value = wirelineEquipment.getProductCatalogueItem().getParentProductCatalogueIdentifier().getExternalProductCatalogId();
			}
	
			if(wirelineEquipment != null) {
				prodComp.setCatalogId(wirelineEquipment.getProductCatalogueItem().getProductCatalogueIdentifier().getExternalProductCatalogId());
				if(wirelineEquipment.getProductCatalogueItem().getProductCatalogueNameList() != null && !wirelineEquipment.getProductCatalogueItem().getProductCatalogueNameList().isEmpty()) {
					prodComp.setName(wirelineEquipment.getProductCatalogueItem().getProductCatalogueNameList().get(0).getDescriptionText());
				}
			}
			
			//product characteristics
			if(wirelineEquipment != null) {
				if( wlnEquipment.getAcquisitionType() != null) {
					if(wlnEquipment.getAcquisitionType().isRentalEquipmentIndicator() != null && wlnEquipment.getAcquisitionType().isRentalEquipmentIndicator()) {
						List<WirelineEquipmentItem> wlnEqItmList = wirelineEquipment.getRentalEquipmentList();
						populateEquipmentProductCharacteristics(serviceType, wlnEquipment, prodComp, wlnEqItmList, AcquisitionType.RE);
					}
					if(wlnEquipment.getAcquisitionType().isBuyIndicator() != null && wlnEquipment.getAcquisitionType().isBuyIndicator()) {
						List<WirelineEquipmentItem> wlnEqItmList = wirelineEquipment.getPurchaseEquipmentList();
						populateEquipmentProductCharacteristics(serviceType, wlnEquipment, prodComp, wlnEqItmList, AcquisitionType.SA);
					}
					if(wlnEquipment.getAcquisitionType().isCustomerOwnedIndicator() != null && wlnEquipment.getAcquisitionType().isCustomerOwnedIndicator()) {
						List<WirelineEquipmentItem> wlnEqItmList = wirelineEquipment.getByodEquipmentList();
						populateEquipmentProductCharacteristics(serviceType, wlnEquipment, prodComp, wlnEqItmList, AcquisitionType.POE);
					}
				}
				deliveryMethodHolder.value = wlnEquipment.getDeliveryMethodType();
			}
		} else if(EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(wlnEquipment.getAction())) {//explicit remove
			prodComp = new ProductComponent();
			prodComp.setActionType(findProdCompActionType(wlnEquipment.getAction()));
			if(wlnEquipment.getProductCatalogueIdentifier() != null && wlnEquipment.getProductCatalogueIdentifier().getProductCatalogueId() != null) {
				String externalCatalogueId = findExternalCatalogueId(wlnEquipment.getProductCatalogueIdentifier().getProductCatalogueId());
				prodComp.setCatalogId(externalCatalogueId);
				if(shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO() != null && shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO().getGetProductsByCustomerIdAdapterResponse() != null) {
					com.telus.csm.ewlnsc.adapter.oqs.domain.Product prod = findOpProductComponentByExtCatId(externalCatalogueId, shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO().getGetProductsByCustomerIdAdapterResponse().getProducts());
					if(prod != null) {
						prodComp.setProductSerialNumber(prod.getProductSerialNumber());
						prodComp.setDescription(prod.getDescription());
					}
				}
			}
		}
		
		return prodComp;
	}

	private static void populateEquipmentProductCharacteristics(String serviceType, FFHEquipmentTypeVO wlnEquipment,
			ProductComponent prodComp, List<WirelineEquipmentItem> wlnEqItmList, AcquisitionType acquisitionType) {
		if(wlnEqItmList != null) {
			for(WirelineEquipmentItem wlnEqItm: wlnEqItmList) {
				if(wlnEquipment.getMaterialItemCode() != null && wlnEquipment.getMaterialItemCode().equalsIgnoreCase(wlnEqItm.getMaterialItemCode())) {
					{
						String catalogAttributeId = PRODUCT_CHARACTERISTCS_PROPS.getProperty(Constants.EQUIPMENT_DESCRIPTION + Constants.UNDERSCORE + serviceType + Constants.PRODUCT_COMPONENT_CATALOG_ATTRIBUTE_ID);
						String name = PRODUCT_CHARACTERISTCS_PROPS.getProperty(Constants.EQUIPMENT_DESCRIPTION + Constants.UNDERSCORE + serviceType + Constants.PRODUCT_COMPONENT_ATTRIBUTE_NAME);
						String value = wlnEqItm.getEquipmentDescriptionList().get(0).getDescriptionText();
						ProductCharacteristic prodCharacteristic = createProductCharacteristic(catalogAttributeId, name, value);
						prodComp.addProductCharacteristic(prodCharacteristic);
					}
					{
						String catalogAttributeId = PRODUCT_CHARACTERISTCS_PROPS.getProperty(Constants.MIC + Constants.UNDERSCORE + serviceType + Constants.PRODUCT_COMPONENT_CATALOG_ATTRIBUTE_ID);
						String name = PRODUCT_CHARACTERISTCS_PROPS.getProperty(Constants.MIC + Constants.UNDERSCORE + serviceType + Constants.PRODUCT_COMPONENT_ATTRIBUTE_NAME);
						String value = wlnEqItm.getMaterialItemCode();
						ProductCharacteristic prodCharacteristic = createProductCharacteristic(catalogAttributeId, name, value);
						prodComp.addProductCharacteristic(prodCharacteristic);
					}
					{
						String catalogAttributeId = PRODUCT_CHARACTERISTCS_PROPS.getProperty(Constants.DELIVERY_METHOD + Constants.UNDERSCORE + serviceType + Constants.PRODUCT_COMPONENT_CATALOG_ATTRIBUTE_ID);
						String name = PRODUCT_CHARACTERISTCS_PROPS.getProperty(Constants.DELIVERY_METHOD + Constants.UNDERSCORE + serviceType + Constants.PRODUCT_COMPONENT_ATTRIBUTE_NAME);
						String value = findDeliveryMethod(wlnEquipment.getDeliveryMethodType());
						ProductCharacteristic prodCharacteristic = createProductCharacteristic(catalogAttributeId, name, value);
						prodComp.addProductCharacteristic(prodCharacteristic);
					}
					{
						String catalogAttributeId = PRODUCT_CHARACTERISTCS_PROPS.getProperty(Constants.ACQUISITION_TYPE + Constants.UNDERSCORE + serviceType + Constants.PRODUCT_COMPONENT_CATALOG_ATTRIBUTE_ID);
						String name = PRODUCT_CHARACTERISTCS_PROPS.getProperty(Constants.ACQUISITION_TYPE + Constants.UNDERSCORE + serviceType + Constants.PRODUCT_COMPONENT_ATTRIBUTE_NAME);
						String value = String.valueOf(acquisitionType);
						ProductCharacteristic prodCharacteristic = createProductCharacteristic(catalogAttributeId, name, value);
						prodComp.addProductCharacteristic(prodCharacteristic);
					}
				}
			}
		}
	}
	
	private static String findDeliveryMethod(String deliveryMethodTypeCd) {
		String result="";
		if(deliveryMethodTypeCd.equalsIgnoreCase(Constants.DELIVERY_METHOD_SHIPPED)){
			result = Constants.DELIVERY_METHOD_SHP;
		} else if(deliveryMethodTypeCd.equalsIgnoreCase(Constants.DELIVERY_METHOD_INSTORE)){
			result = Constants.DELIVERY_METHOD_CST;
		} else if(deliveryMethodTypeCd.equalsIgnoreCase(Constants.DELIVERY_METHOD_INSTALLER)){
			result = Constants.DELIVERY_METHOD_INS;
		}
		return result;
	}

	protected static void implicitProductOrOrphanedDiscountRemoval(ShoppingCartContextVO shoppingCartContextVO, Product product, String serviceType) {
		// Per Chris: Product Item - Discounts can be orphaned, meaning it
		// is not associated to any Product Item, so it is a Product Item by
		// itself
		for(com.telus.csm.ewlnsc.adapter.oqs.domain.Product topProd:shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO().getGetProductsByCustomerIdAdapterResponse().getProducts()) {
			if(serviceType.equalsIgnoreCase(topProd.getServiceType())) {
				if (shoppingCartContextVO.getAvailableProductItemDelegateResponse() != null
						&& shoppingCartContextVO.getAvailableProductItemDelegateResponse().getProductItems() != null) {
					for(ProductItemVO productItemVO : shoppingCartContextVO.getAvailableProductItemDelegateResponse().getProductItems()) {
						if (productItemVO.isExistingInd() && !productItemVO.isCarryOverInd()) {
							// we remove existing and not-carryover
							if (productItemVO.getProductItemIdentifier() != null) {
								String externalCatalogueId = productItemVO.getProductItemIdentifier().getExternalId();
								com.telus.csm.ewlnsc.adapter.oqs.domain.Product prod = findOpProductComponentByExtCatId(externalCatalogueId, topProd.getProductComponents());
								if (prod != null) {
									// it is a product component
									ProductComponent removeProdComp = new ProductComponent();
									removeProdComp.setActionType(EnterpriseWLNSalesServicesConstants.RM);
									removeProdComp.setCatalogId(externalCatalogueId);
									removeProdComp.setProductSerialNumber(prod.getProductSerialNumber());
									removeProdComp.setDescription(prod.getDescription());
									product.addProductComponent(removeProdComp);
									logger.info("implicitProductOrOrphanedDiscountRemoval", "Implicitely removing an existing product [" + prod.getDescription() + "]with externalCatId [" + externalCatalogueId + "]" );
									continue;
								}
								com.telus.csm.ewlnsc.adapter.oqs.domain.Product prodForOffering = findProductForOfferingByExtCatId(externalCatalogueId, topProd.getProductComponents());
								if (prodForOffering != null) {
									// it is a product offering
									ProductOffering prodOffering = new ProductOffering();
									prodOffering.setActionType(EnterpriseWLNSalesServicesConstants.RM);
									prodOffering.setCatalogId(externalCatalogueId);
									prodOffering.setId(prodForOffering.getProductSerialNumber());
									prodOffering.setName(prodForOffering.getDescription());
									product.addProductOffering(prodOffering);
									logger.info("implicitProductOrOrphanedDiscountRemoval", "Implicitely removing an existing product for offering [" + prodForOffering.getDescription() + "] with externalCatId [" + externalCatalogueId + "]" );
									continue;
								}
							}
						}
					}
				}
			}
			break;//found the prod type
		}
	}

	protected static void implicitDiscountRemoval(ShoppingCartContextVO shoppingCartContextVO, Product product) {
		// 1. Per Chris: Associated Item - Discounts are the only ones that
		// can be an Associated Item
		if (shoppingCartContextVO.getAvailableProductItemDelegateResponse() != null
				&& shoppingCartContextVO.getAvailableProductItemDelegateResponse().getProductItems() != null) {
			for (ProductItemVO productItemVO : shoppingCartContextVO.getAvailableProductItemDelegateResponse().getProductItems()) {
				if (productItemVO.getAssociatedProductItems() != null) {
					for (AssociatedProductItemVO associatedProductItemVO : productItemVO.getAssociatedProductItems()) {
						if (associatedProductItemVO.isExistingInd() && !associatedProductItemVO.isCarryOverInd()) {
							// to be removed
							ProductOffering prodOffering = new ProductOffering();
							if (productItemVO.getProductItemIdentifier() != null) {
								String externalCatalogueId = associatedProductItemVO.getProductItemIdentifier().getExternalId();
								prodOffering.setCatalogId(externalCatalogueId);
							}
							prodOffering.setActionType(EnterpriseWLNSalesServicesConstants.RM);
							if (shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO() != null
									&& shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO().getGetProductsByCustomerIdAdapterResponse() != null
									&& shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO().getGetProductsByCustomerIdAdapterResponse().getProducts() != null) {
								com.telus.csm.ewlnsc.adapter.oqs.domain.Product prodForOffering = findProductForOfferingByExtCatId(prodOffering.getCatalogId(), shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO().getGetProductsByCustomerIdAdapterResponse().getProducts());
								if(prodForOffering != null) {
									prodOffering.setId(prodForOffering.getParentProductSerialNumber());
									prodOffering.setName(prodForOffering.getDescription());
								}
							}
							product.addProductOffering(prodOffering);
						}
					}
				}
			}
		}
	}

	protected static void explicitProductRemoval(List<AdditionalProductItemTypeVO> additionalProductItemList, ShoppingCartContextVO shoppingCartContextVO,Product product) {
		if (additionalProductItemList != null) {
			for (AdditionalProductItemTypeVO additionalProductItemTypeVO : additionalProductItemList) {
				if (additionalProductItemTypeVO.getProductCatalogueIdentifier() != null) {
					ProductComponent removeProdComp = new ProductComponent();
					removeProdComp.setActionType(EnterpriseWLNSalesServicesConstants.RM);
					String externalCatalogueId = findExternalCatalogueId(additionalProductItemTypeVO.getProductCatalogueIdentifier().getProductCatalogueId());
					removeProdComp.setCatalogId(externalCatalogueId);
					// find a serial number
					if (shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO() != null
							&& shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO().getGetProductsByCustomerIdAdapterResponse() != null) {
						com.telus.csm.ewlnsc.adapter.oqs.domain.Product prod = findOpProductComponentByExtCatId(externalCatalogueId, shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO().getGetProductsByCustomerIdAdapterResponse().getProducts());
						if (prod != null) {
							removeProdComp.setProductSerialNumber(prod.getProductSerialNumber());
							removeProdComp.setDescription(prod.getDescription());
						}
					}
					product.addProductComponent(removeProdComp);
				}
			}
		}
	}
	
	protected static void explicitAddonRemoval(Product product,com.telus.csm.ewlnsc.adapter.oqs.domain.Product existingProd, List<FFHProductPlanAddOnTypeVO> addOns) {
		if(product == null || existingProd == null || addOns == null) {
			return;
		}
		for(FFHProductPlanAddOnTypeVO addon: addOns) {
			if(EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(addon.getAction())) {
				ProductComponent removeProdComp = new ProductComponent();
				removeProdComp.setActionType(EnterpriseWLNSalesServicesConstants.RM);
				String externalCatalogueId = findExternalCatalogueId(addon.getProductCatalogueIdentifier().getProductCatalogueId());
				removeProdComp.setCatalogId(externalCatalogueId);
				// find a serial number
				com.telus.csm.ewlnsc.adapter.oqs.domain.Product prod = findOpProductComponentByExtCatId(externalCatalogueId, existingProd.getProductComponents());
				if (prod != null) {
					removeProdComp.setProductSerialNumber(prod.getProductSerialNumber());
					removeProdComp.setDescription(prod.getDescription());
				}
				product.addProductComponent(removeProdComp);
			}
		}
	}
	
	public static void addProductComponentWithoutCreatingDuplications(Product productToHoldTheComponent, ProductComponent productComponentToAdd) {
		if ( (productToHoldTheComponent.getProductComponents() != null) &&
			 (!productToHoldTheComponent.getProductComponents().isEmpty()) ) {
			ProductComponent newProductComponentToBeReplaced = findProductComponentByName(productComponentToAdd.getName(), productToHoldTheComponent.getProductComponents());

			if (newProductComponentToBeReplaced != null) {
				for (int i = 0; i < productToHoldTheComponent.getProductComponents().size(); i ++) {
					if (productToHoldTheComponent.getProductComponents().get(i) != null) {
						if ( (productToHoldTheComponent.getProductComponents().get(i).getName() != null) &&
							 (newProductComponentToBeReplaced.getName() != null) ) {
							if (productToHoldTheComponent.getProductComponents().get(i).getName().equals(newProductComponentToBeReplaced.getName())) {
								productToHoldTheComponent.getProductComponents().remove(i);

								break;
							}
						}
						else if ( (productToHoldTheComponent.getProductComponents().get(i).getServiceType() != null) &&
								  (newProductComponentToBeReplaced.getServiceType() != null) ) {
							if (productToHoldTheComponent.getProductComponents().get(i).getServiceType().equals(newProductComponentToBeReplaced.getServiceType())) {
								productToHoldTheComponent.getProductComponents().remove(i);

								break;
							}
						}
						else if ( (productToHoldTheComponent.getProductComponents().get(i).getCatalogId() != null) &&
								  (newProductComponentToBeReplaced.getCatalogId() != null) ) {
							if (productToHoldTheComponent.getProductComponents().get(i).getCatalogId().equals(newProductComponentToBeReplaced.getCatalogId())) {
								productToHoldTheComponent.getProductComponents().remove(i);

								break;
							}
						}
					}
				}
			}

			productToHoldTheComponent.addProductComponent(productComponentToAdd);
		}
		else {
			productToHoldTheComponent.addProductComponent(productComponentToAdd);
		}
	}

	/**
	 * Should be: "value": "2018-10-04T09:40:37Z"
	 * @param serviceRequiredDate
	 * @return
	 */
	protected static String formatToOpServiceRequiredDate(Date serviceRequiredDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(serviceRequiredDate);
		cal.set(Calendar.HOUR_OF_DAY, 12); 
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		String serviceRequiredDateVal = sdf.format(cal.getTime());
		return serviceRequiredDateVal;
	}

	protected static ProductComponent createLsrDataProductComponent(List<DisconnectRequestTypeVO> disconnectRequestTypeList, Long primaryPhoneNumber, ShoppingCartContextVO shoppingCartContextVO) {
		if(disconnectRequestTypeList == null || disconnectRequestTypeList.isEmpty()) {
			return null;
		}
		ArrayList<String> productServiceTypesToDisconnect = new ArrayList<String>();
		for(DisconnectRequestTypeVO req: disconnectRequestTypeList) {
			if(req.getProductServiceType() != null) {
				for(String servType: req.getProductServiceType()) {
					if(productServiceTypesToDisconnect.contains(servType)) {
						continue;
					}
					productServiceTypesToDisconnect.add(servType);
				}
			}
		}
		ProductComponent lsrDataComp = new ProductComponent();
		lsrDataComp.setName(EnterpriseWLNSalesServicesConstants.COMP_LSR_DATA);
		
		if(disconnectRequestTypeList.get(0).getOriginalCarrierInfo() != null) {
			if(disconnectRequestTypeList.get(0).getOriginalCarrierInfo().getProviderId() != null) {
				//importLSPName
				lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.importLSPName), disconnectRequestTypeList.get(0).getOriginalCarrierInfo().getProviderId()));
				//loopMigrationInd. The "loopMigrationInd" is a derived value.  If the Service Provider is SHAW ("497E"), then the value of the "loopMigrationInd" is false, else it is true
				lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.loopMigrationInd), String.valueOf(!Constants.CLEC_SHAW_CARRIER_ID.equalsIgnoreCase(disconnectRequestTypeList.get(0).getOriginalCarrierInfo().getProviderId()))));
			}
			if(!StringUtils.isEmpty(disconnectRequestTypeList.get(0).getOriginalCarrierInfo().getReseller())) {
				lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.reseller), disconnectRequestTypeList.get(0).getOriginalCarrierInfo().getReseller()));
			}
		}
		//hsClecInd
		if(productServiceTypesToDisconnect.contains(EnterpriseWLNSalesServicesConstants.HSIC)) {
			lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.hsClecInd), "D"));
			if(disconnectRequestTypeList.get(0).getOriginalCarrierInfo() != null && disconnectRequestTypeList.get(0).getOriginalCarrierInfo().getAccountNumber() != null) {
				//hsEAN
				lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.hsEAN), String.valueOf(disconnectRequestTypeList.get(0).getOriginalCarrierInfo().getAccountNumber())));
			}
		}else { //NWLN-8430 Vesta winbacks
			lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.hsClecInd), "N"));
		}
		//tvClecInd
		if(productServiceTypesToDisconnect.contains(EnterpriseWLNSalesServicesConstants.TTV)) {
			lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.tvClecInd), "D"));
			if(disconnectRequestTypeList.get(0).getOriginalCarrierInfo() != null && disconnectRequestTypeList.get(0).getOriginalCarrierInfo().getAccountNumber() != null) {
				//tvEAN
				lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.tvEAN), String.valueOf(disconnectRequestTypeList.get(0).getOriginalCarrierInfo().getAccountNumber())));
			}
		}else { //NWLN-8430 Vesta winbacks
			lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.tvClecInd), "N"));
		}
		//tnClecInd
		if(productServiceTypesToDisconnect.contains(EnterpriseWLNSalesServicesConstants.SING)) {
			//NWLN-8430 Vesta winbacks
			Boolean portIn = false;
			if(isSingPortIn(shoppingCartContextVO.getShoppingCartVO())) {
				lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.importPrimaryTN), "P"));
				//csProvider
				if(disconnectRequestTypeList.get(0).getOriginalCarrierInfo() != null && disconnectRequestTypeList.get(0).getOriginalCarrierInfo().getProviderId() != null ){
					lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.csProvider), String.valueOf(disconnectRequestTypeList.get(0).getOriginalCarrierInfo().getProviderId())));
				}
				//smartRing
				if(isSmartRingPortIn(shoppingCartContextVO.getShoppingCartVO())) {
					lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.importSmartRingTN), "P"));
					if(disconnectRequestTypeList.get(0).getOriginalCarrierInfo() != null && disconnectRequestTypeList.get(0).getOriginalCarrierInfo().getProviderId() != null ){
						lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.csProviderSmartRing), String.valueOf(disconnectRequestTypeList.get(0).getOriginalCarrierInfo().getProviderId())));
					}
					if(disconnectRequestTypeList.get(0).getOriginalCarrierInfo() != null && disconnectRequestTypeList.get(0).getOriginalCarrierInfo().getAccountNumber() != null) {
						lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.tnEanSmartRing), String.valueOf(disconnectRequestTypeList.get(0).getOriginalCarrierInfo().getAccountNumber())));
					}				
				}
				portIn=true;
			}
			lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.tnClecInd), "D"));
			//use the request phone number tnPrimary
			if(disconnectRequestTypeList.get(0).getOriginalCarrierInfo() != null && disconnectRequestTypeList.get(0).getOriginalCarrierInfo().getTelephoneNumber() != null ){
				lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.tnPrimary), String.valueOf(disconnectRequestTypeList.get(0).getOriginalCarrierInfo().getTelephoneNumber().getPhoneNumber())));
			} else if (portIn && primaryPhoneNumber !=null ){
				//in case request phone number is not there. (not really needed)
				lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.tnPrimary), String.valueOf(primaryPhoneNumber)));
			}
			//NWLN-8430 Vesta winbacks tnEANPrimary
			if(disconnectRequestTypeList.get(0).getOriginalCarrierInfo() != null && disconnectRequestTypeList.get(0).getOriginalCarrierInfo().getAccountNumber() != null) {
				lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.tnEanPrimary), String.valueOf(disconnectRequestTypeList.get(0).getOriginalCarrierInfo().getAccountNumber())));
			}
		}else { //NWLN-8430 Vesta winbacks
			lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.tnClecInd), "N"));
		}
		//secTnClecInd
		lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.secTnClecInd), "N"));
		
		//secondaryName
		if(disconnectRequestTypeList.get(0).getOriginalCarrierInfo() != null && disconnectRequestTypeList.get(0).getOriginalCarrierInfo().getCustomerName() != null) {
			String value = disconnectRequestTypeList.get(0).getOriginalCarrierInfo().getCustomerName().getFirstName() + " " + disconnectRequestTypeList.get(0).getOriginalCarrierInfo().getCustomerName().getLastName();
			lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.secondaryName), value));
		}
		
		if(disconnectRequestTypeList.get(0).getOriginalCarrierInfo() != null && disconnectRequestTypeList.get(0).getOriginalCarrierInfo().getDisconnectServiceAddress() != null) {
			boolean addressSet = false;
			if(!StringUtils.isEmpty(disconnectRequestTypeList.get(0).getOriginalCarrierInfo().getDisconnectServiceAddress().getServiceAddressId())) {
				ServiceAddressResponseVO serviceAddressResponseVO = null;
				if(shoppingCartContextVO.getServiceAddressResponseVO() != null 
						&& shoppingCartContextVO.getServiceAddressResponseVO().getServiceAddress() != null 
						&& disconnectRequestTypeList.get(0).getOriginalCarrierInfo().getDisconnectServiceAddress().getServiceAddressId().equalsIgnoreCase(shoppingCartContextVO.getServiceAddressResponseVO().getServiceAddress().getAddressId())) {
					serviceAddressResponseVO = shoppingCartContextVO.getServiceAddressResponseVO();
				} else if(shoppingCartContextVO.getShoppingCartVO().getServiceAddress() != null && !StringUtils.isEmpty(shoppingCartContextVO.getShoppingCartVO().getServiceAddress().getProvinceCode())) {
					String provinceCode = shoppingCartContextVO.getShoppingCartVO().getServiceAddress().getProvinceCode();
					serviceAddressResponseVO = getServiceAddress(disconnectRequestTypeList.get(0).getOriginalCarrierInfo().getDisconnectServiceAddress().getServiceAddressId(), provinceCode);
				}
				if(serviceAddressResponseVO != null && serviceAddressResponseVO.getServiceAddress() != null) {
					//oldServiceAddressHouseNumber
					lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.oldServiceAddressHouseNumber), serviceAddressResponseVO.getServiceAddress().getStreetNumber()));
					//oldServiceAddressStreetName
					lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.oldServiceAddressStreetName), serviceAddressResponseVO.getServiceAddress().getStreetName()));
					//oldServiceAddressCity
					lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.oldServiceAddressCity), serviceAddressResponseVO.getServiceAddress().getMunicipalityName()));
					//oldServiceAddressProvince
					lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.oldServiceAddressProvince), serviceAddressResponseVO.getServiceAddress().getProvinceStateCode()));
					//oldServiceAddressPostalCode
					lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.oldServiceAddressPostalCode), serviceAddressResponseVO.getServiceAddress().getPostalZipCode()));
					addressSet = true;
				}

			}
			if(!addressSet && disconnectRequestTypeList.get(0).getOriginalCarrierInfo().getDisconnectServiceAddress().getFullAddress() != null) {
				AddressVO fullAddress = disconnectRequestTypeList.get(0).getOriginalCarrierInfo().getDisconnectServiceAddress().getFullAddress();
				//oldServiceAddressHouseNumber
				lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.oldServiceAddressHouseNumber), fullAddress.getStreetNumber()));
				//oldServiceAddressStreetName
				lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.oldServiceAddressStreetName), fullAddress.getStreetName()));
				//oldServiceAddressCity
				lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.oldServiceAddressCity), fullAddress.getMunicipalityName()));
				//oldServiceAddressProvince
				lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.oldServiceAddressProvince), fullAddress.getProvinceStateCode()));
				//oldServiceAddressPostalCode
				lsrDataComp.addProductCharacteristic(createProductCharacteristic(String.valueOf(EnterpriseWLNSalesServicesConstants.OP_CLEC_PROD_CHARS.oldServiceAddressPostalCode), fullAddress.getPostalZipCode()));
			}
		}
		//nwln-8430
		return lsrDataComp;
	}

	
	protected static ServiceAddressResponseVO getServiceAddress(String addressId, String provinceCode) {
		if(StringUtils.isEmpty(addressId)) {
			return null;
		}
		ServiceAddressRequestVO serviceAddressRequestVO = new ServiceAddressRequestVO();
		serviceAddressRequestVO.setAddressId(addressId);
		serviceAddressRequestVO.setProvinceCode(provinceCode);;
		GetServiceAddressDetailsTask serviceAddressDetailsTask = new GetServiceAddressDetailsTask(serviceAddressRequestVO);
		serviceAddressDetailsTask.run();
		ServiceAddressResponseVO serviceAddressResponseVO = serviceAddressDetailsTask.getResult();
		return serviceAddressResponseVO;
	}

	protected static Long findPrimaryPhoneNumber(ShoppingCartContextVO shoppingCartContextVO) {
		for(CartItemVO cartItemVO: shoppingCartContextVO.getShoppingCartVO().getCartItemList()) {
			if(cartItemVO.getProducts()!=null && cartItemVO.getProducts().getHomePhoneProduct() != null
					&& cartItemVO.getProducts().getHomePhoneProduct().getPhoneNumber() != null 
					&& cartItemVO.getProducts().getHomePhoneProduct().getPhoneNumber().getTelephoneNumber() != null 
					&& cartItemVO.getProducts().getHomePhoneProduct().getPhoneNumber().getTelephoneNumber().getPhoneNumber() != null
					&& cartItemVO.getProducts().getHomePhoneProduct().getPhoneNumber().getTelephoneNumber().getPhoneNumber() > 0) {//if have a phone number
				return cartItemVO.getProducts().getHomePhoneProduct().getPhoneNumber().getTelephoneNumber().getPhoneNumber();
			}
		}
		return null;
	}
	
	protected static String findIncludeDeleteServiceToProvide(DisconnectRequestTypeVO disconnectRequestTypeVO,ShoppingCartVO shoppingCart) {
		boolean singPortIn = isSingPortIn(shoppingCart);
		if(disconnectRequestTypeVO.getProductServiceType() != null && disconnectRequestTypeVO.getProductServiceType().contains(EnterpriseWLNSalesServicesConstants.SING) && singPortIn) {
			return EnterpriseWLNSalesServicesConstants.SING;
		}
		return null;
	}

	protected static boolean isSingPortIn(ShoppingCartVO shoppingCart) {
		for(CartItemVO cartItemVO: shoppingCart.getCartItemList()) {
			if(cartItemVO.isSalesOrderItem() && cartItemVO.getProducts() != null && cartItemVO.getProducts().getHomePhoneProduct() != null)
			if(cartItemVO.getProducts().getHomePhoneProduct().getPhoneNumber() != null 
					&& cartItemVO.getProducts().getHomePhoneProduct().getPhoneNumber().getPortRequestType() != null
					&& cartItemVO.getProducts().getHomePhoneProduct().getPhoneNumber().getPortRequestType().isPortinInd() != null) {
				return cartItemVO.getProducts().getHomePhoneProduct().getPhoneNumber().getPortRequestType().isPortinInd();
			}
		}
		return false;
	}
	
	protected static boolean isSmartRingPortIn(ShoppingCartVO shoppingCart) {
		for(CartItemVO cartItemVO: shoppingCart.getCartItemList()) {
			if(cartItemVO.getProducts() != null && cartItemVO.getProducts().getHomePhoneProduct() != null) {
				HomePhoneProductTypeVO homePhoneProduct = cartItemVO.getProducts().getHomePhoneProduct();
				if(homePhoneProduct != null){
					for (SmartRingTypeVO smartRing : homePhoneProduct.getSmartRingPhoneList()) {
						if(smartRing.getPhone() != null && smartRing.getPhone().getPortRequestType() !=null && 
								Boolean.TRUE.equals(smartRing.getPhone().getPortRequestType().isPortinInd()) ) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	protected static boolean existsInRelatedCartItemList(String cartItemId, List<RelatedCartItemVO> relatedCartItemVOs) {
		boolean hasRelatedItm = false;
		if(!StringUtils.isEmpty(cartItemId)) {
			for(RelatedCartItemVO relatedItm: relatedCartItemVOs) {
				if(cartItemId.equalsIgnoreCase(relatedItm.getCartItemId())) {
					hasRelatedItm = true;
					break;
				}
			}
		}
		return hasRelatedItm;
	}

	protected static String findEnglishTextValue(List<MultilingualTextVO> textList) {
		String value = null;
		if(textList != null && !textList.isEmpty()) {
			for(MultilingualTextVO txt: textList) {
				if(Constants.ENGLISH.equalsIgnoreCase(txt.getLocaleTxt())) {
					value = txt.getValueTxt();
				}
			}
			if(StringUtils.isEmpty(value)) {
				value =textList.get(0).getValueTxt();
			}
		}
		return value;
	}

	/**
	 * 
	 * @param shoppingCartContextVO
	 * @param productInOpOrder: product in new OP Order
	 * @param productType: HSIC, TTV
	 */
	protected static void setKeepcommitment(ShoppingCartContextVO shoppingCartContextVO, Product productInOpOrder, String productType, String whereToLookForExisting) {
		// find existing commitmentPeriodInYears
		String existingCompCommitmentYears = null;
		if (shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO() != null
				&& shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO().getGetProductsByCustomerIdAdapterResponse() != null
				&& shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO().getGetProductsByCustomerIdAdapterResponse().getProducts() != null) {
			for(com.telus.csm.ewlnsc.adapter.oqs.domain.Product existTopProd: shoppingCartContextVO.getOpAssignedAndPendingProductResponseVO().getGetProductsByCustomerIdAdapterResponse().getProducts()) {
				if(productType.equalsIgnoreCase(existTopProd.getServiceType())) {
					com.telus.csm.ewlnsc.adapter.oqs.domain.Product existProd = findOpProductComponentByName(whereToLookForExisting, existTopProd.getProductComponents());
					if(existProd != null) {
						String commitmentTermName = PRODUCT_CHARACTERISTCS_PROPS.getProperty(Constants.CONTRACT_TERM + Constants.UNDERSCORE + productType + Constants.PRODUCT_COMPONENT_ATTRIBUTE_NAME);
						existingCompCommitmentYears = findProductCharacteristicValue(existProd, commitmentTermName);
						if(!StringUtils.isEmpty(existingCompCommitmentYears)) {
							break;
						}
						if(existProd.getProductComponents() != null) {
							for(com.telus.csm.ewlnsc.adapter.oqs.domain.Product opProduct: existProd.getProductComponents()) {
								existingCompCommitmentYears = findProductCharacteristicValue(opProduct, commitmentTermName);
								if(!StringUtils.isEmpty(existingCompCommitmentYears)) {
									break;
								}
							}
						}
					}
				}
			}
		}
		//compare existing commitmentPeriodInYears and new one
		if (!StringUtils.isEmpty(existingCompCommitmentYears)) {
			for (ProductComponent prodComp : productInOpOrder.getProductComponents()) {
				String newCommitmentPeriodInYears = null;
				String commitmentTermName = PRODUCT_CHARACTERISTCS_PROPS.getProperty(Constants.CONTRACT_TERM + Constants.UNDERSCORE + productType + Constants.PRODUCT_COMPONENT_ATTRIBUTE_NAME);
				if (!StringUtils.isEmpty(commitmentTermName)) {
					newCommitmentPeriodInYears = findProductCharacteristicValue(prodComp, commitmentTermName);
				}
				if (!StringUtils.isEmpty(newCommitmentPeriodInYears)) {
					// add Keepcommitment product characteristic
					String name = PRODUCT_CHARACTERISTCS_PROPS.getProperty(Constants.KEEP_COMMITMENT + Constants.UNDERSCORE + productType + Constants.PRODUCT_COMPONENT_ATTRIBUTE_NAME);
					String catalogAttributeId = PRODUCT_CHARACTERISTCS_PROPS.getProperty(Constants.KEEP_COMMITMENT + Constants.UNDERSCORE + productType + Constants.PRODUCT_COMPONENT_CATALOG_ATTRIBUTE_ID);
					String value = newCommitmentPeriodInYears.equalsIgnoreCase(existingCompCommitmentYears) ? "Yes": "No";
					ProductCharacteristic prodChar = createProductCharacteristic(catalogAttributeId, name, value);
					if (prodChar != null) {
						prodComp.addProductCharacteristic(prodChar);
						break;
					}
				}
			}
		}
	}
	
	protected static boolean isPineAppleOrder(ShoppingCartVO shoppingCart) {
		 boolean hasSLPortTemp=false;
		for(CartItemVO cartItemVO: shoppingCart.getCartItemList()) {
			if ( (cartItemVO != null) && (cartItemVO.getProducts() != null) ) {
				if( cartItemVO.getProducts().getHomePhoneProduct() != null &&
					cartItemVO.getProducts().getHomePhoneProduct().getPhoneNumber() != null &&
					cartItemVO.getProducts().getHomePhoneProduct().getPhoneNumber().getPortRequestType() != null &&
					cartItemVO.getProducts().getHomePhoneProduct().getPhoneNumber().getPortRequestType().isTemporaryPhoneNumberRequiredInd() != null &&
					cartItemVO.getProducts().getHomePhoneProduct().getPhoneNumber().getPortRequestType().isTemporaryPhoneNumberRequiredInd()) {		
						hasSLPortTemp = true; 
				}
			}
		}
		return hasSLPortTemp;
	}

}
