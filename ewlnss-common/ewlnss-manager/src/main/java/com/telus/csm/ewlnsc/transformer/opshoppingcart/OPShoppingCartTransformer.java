package com.telus.csm.ewlnsc.transformer.opshoppingcart;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.domain.CreateInvoiceAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetDepositInfoRequest;
import com.telus.csm.ewlnsc.adapter.domain.quote.QuoteRequest;
import com.telus.csm.ewlnsc.adapter.oqs.domain.GetOrderSummaryByOrderIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.scis.domain.ProductQualification;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.adapter.wbk.domain.CancelBookingAdapterRequest;
import com.telus.csm.ewlnsc.adapter.wbk.domain.ConfirmBookingAdapterRequest;
import com.telus.csm.ewlnsc.adapter.wbk.domain.ConfirmBookingAdapterRequestBody;
import com.telus.csm.ewlnsc.adapter.wbk.domain.GetBookingRequirementRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.CreateOrderAdapterRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.CreateOrderAdapterRequestBody;
import com.telus.csm.ewlnsc.adapter.woscs.domain.CreateOrderAdapterResponse;
import com.telus.csm.ewlnsc.adapter.woscs.domain.GiftWithPurchase;
import com.telus.csm.ewlnsc.adapter.woscs.domain.PartyInteractionRole;
import com.telus.csm.ewlnsc.adapter.woscs.domain.Product;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductCharacteristic;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductCharacteristicNameValue;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductComponent;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductOrderItem;
import com.telus.csm.ewlnsc.adapter.woscs.domain.SubmitOrderAdapterRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.UpdateOrderAdapterRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.UpdateOrderAdapterResponse;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ValidateProductConfigAdapterResponse;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.ContactMediumVO;
import com.telus.csm.ewlnsc.domain.DisconnectRequestTypeVO;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO2;
import com.telus.csm.ewlnsc.domain.OPShoppingCartDelegateResponseVO;
import com.telus.csm.ewlnsc.domain.OrderCommentVO;
import com.telus.csm.ewlnsc.domain.RelatedCartItemVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressResponseVO;
import com.telus.csm.ewlnsc.domain.ShipmentAddressTypeVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.OfferProduct;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.AccessoryOffer;
import com.telus.csm.ewlnsc.helper.EnterpriseWLNOrderUtil;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Description;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;

public class OPShoppingCartTransformer extends OPCommonProvideTransformer{
	private static final LoggerUtil logger = LoggerUtil.getLogger(OPShoppingCartTransformer.class);
	private static final String OP_TRANSFER_TO_BACKOFFICE = "transfertobackoffice";

	public static List<CreateOpOrderRequestWrapper> transformToCreateOrderAdapterRequest(OperationHeader operationHeader, ShoppingCartContextVO shoppingCartContextVO) {
		ShoppingCartVO shoppingCart = shoppingCartContextVO.getShoppingCartVO();
		//do provide order
		CreateOpOrderRequestWrapper provideCreateOpOrder = transformToProvideCreateOrderAdapterRequest(operationHeader, shoppingCartContextVO);
		
		//do disconnect order(s)
		ArrayList<CartItemVO> disconnectRequests = new ArrayList<CartItemVO>();
		
		//NWLN-8293 - change to remove CLEC disconnect orders for SL winback with temp # orders (pineapple)
		Boolean hasSLPortTemp = isPineAppleOrder(shoppingCart);
		if(!hasSLPortTemp) {
			for(CartItemVO cartItemVO: shoppingCart.getCartItemList()) {
				if(cartItemVO.isDisconnectOrderItem()) {
					disconnectRequests.add(cartItemVO);
				}
			}
		}
		List<CreateOpOrderRequestWrapper> disconnectCreateOpOrders = null;
		if(!disconnectRequests.isEmpty()) {
			disconnectCreateOpOrders = OPDisconnectOrderTransformer.transform(operationHeader, shoppingCartContextVO, disconnectRequests);
		}

		ArrayList<CreateOpOrderRequestWrapper> createOpOrders = new ArrayList<CreateOpOrderRequestWrapper>();
		if(provideCreateOpOrder != null) {
			createOpOrders.add(provideCreateOpOrder);
		}
		if(disconnectCreateOpOrders != null) {
			createOpOrders.addAll(disconnectCreateOpOrders);
		}
		return createOpOrders;
	}
	
	private static CreateOpOrderRequestWrapper transformToProvideCreateOrderAdapterRequest(OperationHeader operationHeader, ShoppingCartContextVO shoppingCartContextVO) {
		ShoppingCartVO shoppingCart = shoppingCartContextVO.getShoppingCartVO();
		CreateOpOrderRequestWrapper createOpOrderRequestWrapper = new CreateOpOrderRequestWrapper();
		CreateOrderAdapterRequest request = new CreateOrderAdapterRequest();
		createOpOrderRequestWrapper.setCreateOrderAdapterRequest(request);
		ArrayList<RelatedCartItemVO> relatedCartItemList = new ArrayList<RelatedCartItemVO>();
		createOpOrderRequestWrapper.setRelatedCartItemList(relatedCartItemList);
		CreateOrderAdapterRequestBody requestBody = request.getBody();
		
		if(shoppingCart.getCustomer() != null) {
			request.setCustomerid(shoppingCart.getCustomer().getCustomerId());
		}
		requestBody.setType(EnterpriseWLNSalesServicesConstants.TYPE_DE); //will be defaulted to "DE" ; may have "RE" in future 
		
		//Used in request query params. We don't need to send
//		request.setCid(shoppingCart.getShoppingCartId());
//		request.setPrevalidation(Boolean.FALSE.toString()); //review with Syd 
		request.setReturnorderdetails(Boolean.FALSE.toString()); //true - see the actual order created in OP for debug purpose
//		request.setOrdercreation(Boolean.TRUE.toString()); //review with Syd
		
		requestBody.setPartyInteractionRole(populatePartyInteractionRole(operationHeader, shoppingCartContextVO));

		//GWP, Gift With Purchase
		for (CartItemVO cartItemVO : shoppingCart.getCartItemList()) {
			if ( (cartItemVO != null) &&
				 (cartItemVO.isGwpOrderItem()) &&
				 (cartItemVO.getRelatedPromotionList() != null) &&
				 (!cartItemVO.getRelatedPromotionList().isEmpty()) ) {
				List<GiftWithPurchase> gwps = createGiftWithPurchase(shoppingCartContextVO, cartItemVO);
				if(gwps != null) {
					if(requestBody.getGiftWithPurchase() == null) {
						requestBody.setGiftWithPurchase(new ArrayList<GiftWithPurchase>());
					}
					requestBody.getGiftWithPurchase().addAll(gwps);
					RelatedCartItemVO relatedItm = new RelatedCartItemVO();
					relatedItm.setCartItemId(cartItemVO.getCartItemId());
					relatedItm.setCartItemType(RelatedCartItemVO.CARTITEM_TYPE.GWP.toString());
					relatedCartItemList.add(relatedItm);
				}
			}
		}

		// productOrderItems
		boolean homePhoneExistsInCart = false;
		boolean homePhoneProvideInCart =false;
		boolean internetExistsInCart = false;
		boolean tvProvideInCart = false;
		boolean internetProvideInCart =false; //qc69949
		ProductComponent shippingAddressComp = null;
		if(shoppingCart.getCartItemList() != null) {
			Long primaryPhoneNumber = null;
			for(CartItemVO cartItemVO: shoppingCart.getCartItemList()) {
				//shippingAddress, OP uses a shipping address in the first product only. We will find a shipping address and set it to all products in the order
				if(shippingAddressComp == null && cartItemVO.getShipmentDetail() != null && cartItemVO.getShipmentDetail().getShipmentAddress() != null) {
					shippingAddressComp = createShippingAddressComp(cartItemVO.getShipmentDetail().getShipmentAddress(), shoppingCartContextVO);
				}

				if(cartItemVO.isSalesOrderItem() && cartItemVO.getProducts() != null) {
					if(!existsInRelatedCartItemList(cartItemVO.getCartItemId(), relatedCartItemList)) {
						RelatedCartItemVO relatedItm = new RelatedCartItemVO();
						relatedItm.setCartItemId(cartItemVO.getCartItemId());
						relatedItm.setCartItemType(RelatedCartItemVO.CARTITEM_TYPE.SALE.toString());
						relatedCartItemList.add(relatedItm);
					}
					if(cartItemVO.getProducts()!=null){
						if(cartItemVO.getProducts().getHomePhoneProduct() != null) {
							homePhoneExistsInCart = EnterpriseWLNSalesServicesConstants.CART_ACTION_ADD.equalsIgnoreCase(cartItemVO.getAction()) || EnterpriseWLNSalesServicesConstants.CART_ACTION_MODIFY.equalsIgnoreCase(cartItemVO.getAction());
							homePhoneProvideInCart = EnterpriseWLNSalesServicesConstants.CART_ACTION_ADD.equalsIgnoreCase(cartItemVO.getAction()) ; //qc69388
							if (EnterpriseWLNSalesServicesConstants.ACTIVATION.equalsIgnoreCase(cartItemVO.getProducts().getHomePhoneProduct().getProductOrderType().getOrderTypeCd())) {
								ProductOrderItem phoneProductOrderItem = OPHomePhoneProvideTransformer.transform(operationHeader, cartItemVO, shoppingCartContextVO);
								requestBody.addProductOrderItem(phoneProductOrderItem);
							} else if (EnterpriseWLNSalesServicesConstants.UPGRADE.equalsIgnoreCase(cartItemVO.getProducts().getHomePhoneProduct().getProductOrderType().getOrderTypeCd())) {
								ProductOrderItem phoneProductOrderItem = OPHomePhoneChangeTransformer.transformChange(operationHeader, cartItemVO, shoppingCartContextVO);
								requestBody.addProductOrderItem(phoneProductOrderItem);
							}
							primaryPhoneNumber = findPrimaryPhoneNumber(shoppingCartContextVO);
						}

						if(cartItemVO.getProducts().getInternetProduct() != null) {
							internetExistsInCart = EnterpriseWLNSalesServicesConstants.CART_ACTION_ADD.equalsIgnoreCase(cartItemVO.getAction()) || EnterpriseWLNSalesServicesConstants.CART_ACTION_MODIFY.equalsIgnoreCase(cartItemVO.getAction());
							internetProvideInCart = EnterpriseWLNSalesServicesConstants.CART_ACTION_ADD.equalsIgnoreCase(cartItemVO.getAction()); //qc69949
							if(EnterpriseWLNSalesServicesConstants.ACTIVATION.equalsIgnoreCase(cartItemVO.getProducts().getInternetProduct().getProductOrderType().getOrderTypeCd())) {
								ProductOrderItem hsProductOrderItem = OPInternetProvideTransformer.transformProvide(operationHeader, cartItemVO, shoppingCartContextVO);
								requestBody.addProductOrderItem(hsProductOrderItem);
							} else if(EnterpriseWLNSalesServicesConstants.UPGRADE.equalsIgnoreCase(cartItemVO.getProducts().getInternetProduct().getProductOrderType().getOrderTypeCd())) {
								ProductOrderItem hsProductOrderItem = OPInternetChangeTransformer.transformChange(operationHeader, cartItemVO, shoppingCartContextVO);
								requestBody.addProductOrderItem(hsProductOrderItem);
							}
						}

						if(cartItemVO.getProducts().getTelevisionProduct() != null) {
							tvProvideInCart = EnterpriseWLNSalesServicesConstants.CART_ACTION_ADD.equalsIgnoreCase(cartItemVO.getAction());
							if(EnterpriseWLNSalesServicesConstants.ACTIVATION.equalsIgnoreCase(cartItemVO.getProducts().getTelevisionProduct().getProductOrderType().getOrderTypeCd())) {
								ProductOrderItem tvProductOrderItem = OPTvProvideTransformer.transform(operationHeader, cartItemVO, shoppingCartContextVO);
								requestBody.addProductOrderItem(tvProductOrderItem);
							} else if(EnterpriseWLNSalesServicesConstants.UPGRADE.equalsIgnoreCase(cartItemVO.getProducts().getTelevisionProduct().getProductOrderType().getOrderTypeCd())) {
								ProductOrderItem tvProductOrderItem = OPTvChangeTransformer.transformChange(operationHeader, cartItemVO, shoppingCartContextVO);
								requestBody.addProductOrderItem(tvProductOrderItem);
							}
						}
					}
				} 
			}
			
			//sort to have RM(remove) product components first
			if(requestBody.getProductOrderItems() != null) {
				for(ProductOrderItem prdOrdItm: requestBody.getProductOrderItems()) {
					if(prdOrdItm.getProduct() != null && prdOrdItm.getProduct().getProductComponents() != null) {
						Collections.sort(prdOrdItm.getProduct().getProductComponents(), new Comparator<ProductComponent>() {
							@Override
							public int compare(ProductComponent o1, ProductComponent o2) {
								if(o1.getActionType() != null && o1.getActionType().equalsIgnoreCase(o2.getActionType())) {
									return 0;
								} else if(EnterpriseWLNSalesServicesConstants.RM.equalsIgnoreCase(o1.getActionType())) {
									return -1;
								} else if(EnterpriseWLNSalesServicesConstants.RM.equalsIgnoreCase(o2.getActionType())) {
									return 1;
								}
								return 0;
							}
						});
					}
				}
			}

			//find disconnect cart items
			for(CartItemVO cartItemVO: shoppingCart.getCartItemList()) {
				if(cartItemVO.isDisconnectOrderItem()) {
					if(cartItemVO.getDisconnectRequestList() != null) {
						for(DisconnectRequestTypeVO disconnectRequestTypeVO: cartItemVO.getDisconnectRequestList()) {
							//if a SING disconnect and port-in then include to the same order
							String includeServiceToProvide = findIncludeDeleteServiceToProvide(disconnectRequestTypeVO, shoppingCart);
							if(!StringUtils.isEmpty(includeServiceToProvide)) {
								ProductComponent lsrDataProdCom = createLsrDataProductComponent(Arrays.asList(disconnectRequestTypeVO), primaryPhoneNumber, shoppingCartContextVO);
								//find a SING product to add the isrData
								if(requestBody.getProductOrderItems() != null) {
									for(ProductOrderItem prodOrdItm: requestBody.getProductOrderItems()) {
										if(includeServiceToProvide.equalsIgnoreCase(prodOrdItm.getProduct().getName())) {
											prodOrdItm.getProduct().addProductComponent(lsrDataProdCom);
											break;
										}
									}
								}
								break;
							}
						}
					}
				}
			}
		}
		
		//If any product has Activation then add existing or pending products if address is COPPER in some condition
		boolean activation = false;
		for(CartItemVO cartItemVO: shoppingCart.getCartItemList()) {
			if(cartItemVO.isSalesOrderItem() && cartItemVO.getProducts() != null) {
				if(cartItemVO.getProducts().getHomePhoneProduct() != null 
						&& cartItemVO.getProducts().getHomePhoneProduct().getProductOrderType() != null
						&& EnterpriseWLNSalesServicesConstants.ACTIVATION.equalsIgnoreCase(cartItemVO.getProducts().getHomePhoneProduct().getProductOrderType().getOrderTypeCd())) {
					activation = true;
					break;
				} else if(cartItemVO.getProducts().getInternetProduct() != null 
						&& cartItemVO.getProducts().getInternetProduct().getProductOrderType() != null
						&& EnterpriseWLNSalesServicesConstants.ACTIVATION.equalsIgnoreCase(cartItemVO.getProducts().getInternetProduct().getProductOrderType().getOrderTypeCd())) {
					activation = true;
					break;
				} else if(cartItemVO.getProducts().getTelevisionProduct() != null 
						&& cartItemVO.getProducts().getTelevisionProduct().getProductOrderType() != null
						&& EnterpriseWLNSalesServicesConstants.ACTIVATION.equalsIgnoreCase(cartItemVO.getProducts().getTelevisionProduct().getProductOrderType().getOrderTypeCd())) {
					activation = true;
					break;
				}
			}
			
		}
		if(activation && shoppingCartContextVO.getProductQualificationAdapterResponseVO() != null && shoppingCartContextVO.getProductQualificationAdapterResponseVO().getProductQualificationList() != null) {
			boolean isCopper = isCopperAddress(shoppingCartContextVO.getProductQualificationAdapterResponseVO().getProductQualificationList());

			if(isCopper) {
				if(shoppingCartContextVO.getAssignedAndPendingProductResponseVO() != null) {
					String serviceRequiredDate = null;
					for(ProductOrderItem prodOrdItm: requestBody.getProductOrderItems()) {
						if(prodOrdItm.getProduct() != null) {
							ProductComponent ordActionAttrComp = findOpProductComponentByNameOP(EnterpriseWLNSalesServicesConstants.COMP_ORDER_ACTION_ATTRIBUTES, prodOrdItm.getProduct().getProductComponents());
							if(ordActionAttrComp != null) {
								String val = findProductCharacteristicValue(ordActionAttrComp, EnterpriseWLNSalesServicesConstants.SERVICE_REQUIRED_DATE);
								if(!StringUtils.isEmpty(val)) {
									serviceRequiredDate = val;
									break;
								}
							}
						}
					}
					//qc69949/qc69388 rewrite rule.
					//sl activation: if has existing hs, add HS (no need to add existing TV)
					//HS activation: if has existing SL, add change SL. 
					//TV activation: if has existing SL, don't need to add, if has existing HS, don't need to add HS since HS should be in cart already
					//some TV activation don't have HS upgrade.
					SubscribedProductInfoRestVO existingSL =null;
					SubscribedProductInfoRestVO existingHS =null;
					if(shoppingCartContextVO.getAssignedAndPendingProductResponseVO().getSubscribedProductList() != null) {
						for(SubscribedProductInfoRestVO subscribedProd: shoppingCartContextVO.getAssignedAndPendingProductResponseVO().getSubscribedProductList()) {
							if(EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(subscribedProd.getProductTypeCd())) {
								existingSL = subscribedProd;
							} else if (EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(subscribedProd.getProductTypeCd())) {
								existingHS = subscribedProd;
							}
						}
					}
					if(shoppingCartContextVO.getAssignedAndPendingProductResponseVO().getPendingProductList() != null) {
						for(SubscribedProductInfoRestVO subscribedProd: shoppingCartContextVO.getAssignedAndPendingProductResponseVO().getPendingProductList()) {
							if(existingSL == null && EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(subscribedProd.getProductTypeCd())) {
								existingSL = subscribedProd;
							} else if (existingHS ==null && EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(subscribedProd.getProductTypeCd())) {
								existingHS = subscribedProd;
							}
						}
					}
					//add SL into cart if has existing SL but don't have SL in cart and don't have existing HS,
					//meaning must have HS or TV provide in CART
					if (existingSL !=null && !homePhoneExistsInCart && existingHS ==null && 
							(internetProvideInCart || tvProvideInCart)) {
						requestBody.addProductOrderItem(createExistingProductOrderItem(existingSL, serviceRequiredDate));
					}
					//add HS into cart if has existing HS and order provide SL
					if (existingHS !=null && homePhoneProvideInCart && !internetExistsInCart ) {
						requestBody.addProductOrderItem(createExistingProductOrderItem(existingHS, serviceRequiredDate));
					}
					//end of qc69949/qc69388
				}
			}
		}
		if(shippingAddressComp != null && request.getBody() != null && request.getBody().getProductOrderItems() != null) {
			for(ProductOrderItem prdOrdItm: request.getBody().getProductOrderItems()) {
				if(prdOrdItm.getProduct() != null) {
					prdOrdItm.getProduct().addProductComponent(shippingAddressComp);
				}
			}
		}
		return createOpOrderRequestWrapper;
	}

	private static boolean isCopperAddress(List<ProductQualification> productQualificationList) {
		for(ProductQualification prodQualification: productQualificationList) {
			if(prodQualification.getGponEttsInd() != null && prodQualification.getGponEttsInd()) {
				//it is fiber
				return false;
			}
		}
		return true;
	}

	private static ProductComponent createShippingAddressComp(ShipmentAddressTypeVO shipmentAddress, ShoppingCartContextVO shoppingCartContextVO) {
		if(shipmentAddress == null) {
			return null;
		}
		ProductComponent shippingAddressComp = new ProductComponent();
		shippingAddressComp.setName(EnterpriseWLNSalesServicesConstants.COMP_SHIPPING_ADDRESS);
		ServiceAddressResponseVO serviceAddressResponseVO = null;
		if(shipmentAddress.getAddressId() != null) {
			if(shoppingCartContextVO.getServiceAddressResponseVO() != null 
				&& shoppingCartContextVO.getServiceAddressResponseVO().getServiceAddress() != null 
				&& String.valueOf(shipmentAddress.getAddressId()).equalsIgnoreCase(shoppingCartContextVO.getServiceAddressResponseVO().getServiceAddress().getAddressId())) {
				serviceAddressResponseVO = shoppingCartContextVO.getServiceAddressResponseVO();
			} else {
				String provinceCode = !StringUtils.isEmpty(shipmentAddress.getProvinceStateCode())? shipmentAddress.getProvinceStateCode(): shoppingCartContextVO.getShoppingCartVO().getServiceAddress().getProvinceCode();
				serviceAddressResponseVO = getServiceAddress(String.valueOf(shipmentAddress.getAddressId()), provinceCode);
			}
		}	
		if(serviceAddressResponseVO != null && serviceAddressResponseVO.getServiceAddress() != null) {
			shippingAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_STREET_NUMBER, serviceAddressResponseVO.getServiceAddress().getStreetNumber()));
			shippingAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_STREET_NAME, serviceAddressResponseVO.getServiceAddress().getStreetName()));
			shippingAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_VECTOR, serviceAddressResponseVO.getServiceAddress().getStreetDirectionCode()));
			shippingAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_APPARTMENT, serviceAddressResponseVO.getServiceAddress().getUnitNumber()));
			shippingAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_CITY, serviceAddressResponseVO.getServiceAddress().getMunicipalityName()));
			shippingAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_STATE, serviceAddressResponseVO.getServiceAddress().getProvinceStateCode()));

			if ("CA".equalsIgnoreCase(serviceAddressResponseVO.getServiceAddress().getCountryCode())
					|| "CAN".equalsIgnoreCase(serviceAddressResponseVO.getServiceAddress().getCountryCode())) {
				shippingAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_COUNTRY, "CANADA"));
			}
			else {
				shippingAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_COUNTRY, serviceAddressResponseVO.getServiceAddress().getCountryCode()));
			}

			shippingAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_POSTALCODE, serviceAddressResponseVO.getServiceAddress().getPostalZipCode()));
		} else {
			shippingAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_STREET_NUMBER, shipmentAddress.getStreetNumber()));
			shippingAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_STREET_NAME, shipmentAddress.getStreetName()));
			shippingAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_VECTOR, shipmentAddress.getStreetDirectionCode()));
			shippingAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_APPARTMENT, shipmentAddress.getUnitNumber()));
			shippingAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_CITY, shipmentAddress.getMunicipalityName()));
			shippingAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_STATE, shipmentAddress.getProvinceStateCode()));

			if ("CA".equalsIgnoreCase(shipmentAddress.getCountryCode())
					|| "CAN".equalsIgnoreCase(shipmentAddress.getCountryCode())) {
				shippingAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_COUNTRY, "CANADA"));
			}
			else {
				shippingAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_COUNTRY, shipmentAddress.getCountryCode()));				
			}

			shippingAddressComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.OP_POSTALCODE, shipmentAddress.getPostalZipCode()));
		}

		return shippingAddressComp;
	}

	
	private static ProductOrderItem createExistingProductOrderItem(SubscribedProductInfoRestVO subscribedProd, String serviceRequiredDate) {
		ProductOrderItem existingProdOrdItm = new ProductOrderItem();
		Product product = new Product();
		product.setName(subscribedProd.getProductTypeCd());
		if(subscribedProd.getProductInstance() != null && subscribedProd.getProductInstance().size() > 0) {
			product.setProductSerialNumber(subscribedProd.getProductInstance().get(0).getProductInstanceId());
		}
		existingProdOrdItm.setProduct(product);
		
		if(!StringUtils.isEmpty(serviceRequiredDate)) {
			ProductComponent orderActionAttrComp = new ProductComponent();
			orderActionAttrComp.setName(EnterpriseWLNSalesServicesConstants.COMP_ORDER_ACTION_ATTRIBUTES);
			product.addProductComponent(orderActionAttrComp);
			orderActionAttrComp.addProductCharacteristic(createProductCharacteristic(EnterpriseWLNSalesServicesConstants.SERVICE_REQUIRED_DATE, serviceRequiredDate));
		}
		
		return existingProdOrdItm;
	}




	private static ProductComponent findOpProductComponentByNameOP(String compName, List<ProductComponent> products) {
		if(StringUtils.isEmpty(compName) || products == null) {
			return null;
		}
		for(ProductComponent prod: products) {
			if(compName.equalsIgnoreCase(prod.getName())) {
				return prod;
			}
		}
		return null;
	}

	public static WirelineOfferProduct findWirelineOfferProduct(ShoppingCartContextVO shoppingCartContextVO, CartItemVO cartItemVO, String serviceType) {
		WirelineOfferProduct wirelineOfferProduct = null;
		if(cartItemVO.getProductMarketOffering() != null 
				&& cartItemVO.getProductMarketOffering().getOfferHeader() != null) {
			GetSalesOfferDetailResponseVO2 offerResp = shoppingCartContextVO.getOfferByCartItemOfferId(cartItemVO.getProductMarketOffering().getOfferHeader().getOfferId());
			if(offerResp != null && offerResp.getOffer() != null) {
				OfferProduct offerProduct = offerResp.getOffer().getOfferProduct();
				if(offerProduct != null) {
					wirelineOfferProduct = getProductByProductTypeCd(offerProduct, serviceType);
				}
			}
		}
		return wirelineOfferProduct;
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
	
	private static PartyInteractionRole populatePartyInteractionRole(OperationHeader operationHeader, ShoppingCartContextVO shoppingCartContextVO) {
		PartyInteractionRole result = populatePartyInteractionRoleBase(operationHeader, shoppingCartContextVO);
		List<ProductCharacteristicNameValue> productCharacteristics = result.getProductCharacteristics();
		
//		if (operationHeader != null) {
//			ProductCharacteristicNameValue storeId = new ProductCharacteristicNameValue();
//			storeId.setValue(operationHeader.getUserProfile().getSalesRepAssociatedOutletList().get(0).getSalesRepAssociatedChannelOutletId());
//			storeId.setName(EnterpriseWLNSalesServicesConstants.STORE_ID);
//			productCharacteristics.add(storeId);			
//		}
		if (operationHeader != null && operationHeader.getAgentProfile() != null) {		
			
			ProductCharacteristicNameValue salesRep = new ProductCharacteristicNameValue();
			salesRep.setValue(operationHeader.getAgentProfile().getLoginId());
			salesRep.setName(EnterpriseWLNSalesServicesConstants.SALES_REP_ID);
			productCharacteristics.add(salesRep);
		}
		
		// CSAG Email - EM or LE
		ProductCharacteristicNameValue contactType = new ProductCharacteristicNameValue();
		contactType.setValue(EnterpriseWLNSalesServicesConstants.CU);
		contactType.setName(EnterpriseWLNSalesServicesConstants.CONTACT_TYPE);
		productCharacteristics.add(contactType);

		ProductCharacteristicNameValue contactCategory = new ProductCharacteristicNameValue();
		contactCategory.setValue(EnterpriseWLNSalesServicesConstants.CSAG);
		contactCategory.setName(EnterpriseWLNSalesServicesConstants.CONTACT_CATEGORY);
		productCharacteristics.add(contactCategory);

		if ( (shoppingCartContextVO.getFullCustomerInfoAdapterResponse() != null) &&
			 (shoppingCartContextVO.getFullCustomerInfoAdapterResponse().getFullCustomer() != null) &&
			 (shoppingCartContextVO.getFullCustomerInfoAdapterResponse().getFullCustomer().getNameList() != null) &&
			 (!shoppingCartContextVO.getFullCustomerInfoAdapterResponse().getFullCustomer().getNameList().isEmpty()) &&
			 (shoppingCartContextVO.getFullCustomerInfoAdapterResponse().getFullCustomer().getNameList().get(0) != null) ) {
			if ( (shoppingCartContextVO.getFullCustomerInfoAdapterResponse().getFullCustomer().getNameList().get(0).getFirstName() != null) &&
				 (!shoppingCartContextVO.getFullCustomerInfoAdapterResponse().getFullCustomer().getNameList().get(0).getFirstName().isEmpty()) ) {
				ProductCharacteristicNameValue customerFirstName = new ProductCharacteristicNameValue();
				customerFirstName.setValue(shoppingCartContextVO.getFullCustomerInfoAdapterResponse().getFullCustomer().getNameList().get(0).getFirstName());
				customerFirstName.setName(EnterpriseWLNSalesServicesConstants.FIRST_NAME);
				productCharacteristics.add(customerFirstName);
			}

			if ( (shoppingCartContextVO.getFullCustomerInfoAdapterResponse().getFullCustomer().getNameList().get(0).getLastName() != null) &&
				 (!shoppingCartContextVO.getFullCustomerInfoAdapterResponse().getFullCustomer().getNameList().get(0).getLastName().isEmpty()) ) {
				ProductCharacteristicNameValue customerLastName = new ProductCharacteristicNameValue();
				customerLastName.setValue(shoppingCartContextVO.getFullCustomerInfoAdapterResponse().getFullCustomer().getNameList().get(0).getLastName());
				customerLastName.setName(EnterpriseWLNSalesServicesConstants.LAST_NAME);
				productCharacteristics.add(customerLastName);
			}
		}
		ProductCharacteristicNameValue preferredContactMethod = new ProductCharacteristicNameValue();
		preferredContactMethod.setName(EnterpriseWLNSalesServicesConstants.PREFERRED_CONTACT_METHOD);
		productCharacteristics.add(preferredContactMethod);

		if ( (shoppingCartContextVO.getShoppingCartVO() != null) &&
			 (shoppingCartContextVO.getShoppingCartVO().getContactMedium() != null) &&
			 (!shoppingCartContextVO.getShoppingCartVO().getContactMedium().isEmpty()) ) {

			for (ContactMediumVO contactMediumVO : shoppingCartContextVO.getShoppingCartVO().getContactMedium()) {
				if ( (contactMediumVO != null) && (contactMediumVO.getContactType() != null) &&
					 ((EnterpriseWLNSalesServicesConstants.CSAG + "_" + EnterpriseWLNSalesServicesConstants.EMAIL).equalsIgnoreCase(contactMediumVO.getContactType())) ) {
					preferredContactMethod.setValue(EnterpriseWLNSalesServicesConstants.EM);

					if ( (contactMediumVO.getCharacteristic() != null) &&
						 (contactMediumVO.getCharacteristic().getEmailAddress() != null) &&
						 (!contactMediumVO.getCharacteristic().getEmailAddress().isEmpty()) ) {
						ProductCharacteristicNameValue email = new ProductCharacteristicNameValue();
						email.setValue(contactMediumVO.getCharacteristic().getEmailAddress() );
						email.setName(EnterpriseWLNSalesServicesConstants.EMAIL.toLowerCase());
						productCharacteristics.add(email);
					}

					break;
				}
			}
		}		
		if (preferredContactMethod.getValue() == null || preferredContactMethod.getValue().isEmpty()) {
			preferredContactMethod.setValue(EnterpriseWLNSalesServicesConstants.LE);
		}
		return result;
	}

	public static OPShoppingCartDelegateResponseVO transformToOPShoppingCartDelegateResponseVO(CreateOrderAdapterResponse createOrderAdapterResponse) {
		if(createOrderAdapterResponse == null) {
			return null;
		}
		OPShoppingCartDelegateResponseVO responseVO = new OPShoppingCartDelegateResponseVO();
		responseVO.setOpShoppingCartId(createOrderAdapterResponse.getId());
		responseVO.setMessageList(createOrderAdapterResponse.getMessageList());
		responseVO.setProductOrderItems(createOrderAdapterResponse.getProductOrderItems());
		
		return responseVO;
	}
	
	public static OPShoppingCartDelegateResponseVO transformToOPShoppingCartDelegateResponseVO(ValidateProductConfigAdapterResponse validateOrderAdapterResponse) {
		if(validateOrderAdapterResponse == null) {
			return null;
		}
		OPShoppingCartDelegateResponseVO responseVO = new OPShoppingCartDelegateResponseVO();
		responseVO.setMessageList(validateOrderAdapterResponse.getMessageList());
		return responseVO;
	}

	public static ConfirmBookingAdapterRequest transformToConfirmBookingAdapterRequest(ShoppingCartContextVO shoppingCartContextVO, String bookingId, Integer opOrderId) {
		ShoppingCartVO shoppingCartVO = shoppingCartContextVO.getShoppingCartVO();
		ConfirmBookingAdapterRequest adapterRequest = new ConfirmBookingAdapterRequest();
		ConfirmBookingAdapterRequestBody adapterRequestBody = adapterRequest.getBody();
		if(opOrderId!=null){
			adapterRequestBody.setOrderId(String.valueOf(opOrderId));
		}
		
		adapterRequestBody.setBookingId(bookingId);
		if(shoppingCartVO.getCustomer() != null) {
			adapterRequest.setStickysessionid(shoppingCartVO.getCustomer().getCustomerId());//A unique id which is used to improve the performance by using cached data. The recommendation is to use Customer ID, but any unique id will work.
		}
		String canBeReachedNumber = null;
		if(shoppingCartVO.getContactMedium() != null) {
			for(ContactMediumVO contactMediumVO: shoppingCartVO.getContactMedium()) {
				if(contactMediumVO.getCharacteristic() != null && !StringUtils.isEmpty(contactMediumVO.getCharacteristic().getPhoneNumber())) {
					canBeReachedNumber = contactMediumVO.getCharacteristic().getPhoneNumber();
					break;
				}
			}
		}
		adapterRequestBody.setCanBeReachedNumber(canBeReachedNumber);
		return adapterRequest;
	}
	
	public static CancelBookingAdapterRequest transformToCancelBookingAdapterRequest(ShoppingCartVO shoppingCartVO, String bookingId, String cancellationSystem) {

		CancelBookingAdapterRequest adapterRequest = new CancelBookingAdapterRequest();
		
		String externalOrderId = EnterpriseWLNOrderUtil.getExternalOrderIdBySystem(shoppingCartVO,cancellationSystem);
		if (!StringUtils.isBlank(externalOrderId)) {
			adapterRequest.setOrderId(externalOrderId);
		}

		adapterRequest.setBookingId(bookingId);
		return adapterRequest;
	}
	
	private static List<GiftWithPurchase> createGiftWithPurchase(ShoppingCartContextVO shoppingCartContextVO, CartItemVO cartItemVO) {
		List<GiftWithPurchase> giftWithPurchaseItems = null;
		if(shoppingCartContextVO.getAvailableGWPAccessoryPromotionsInShoppingCart(cartItemVO) != null) {
			giftWithPurchaseItems = new ArrayList<GiftWithPurchase>();
			for (AccessoryOffer accessoryOffer : shoppingCartContextVO.getAvailableGWPAccessoryPromotionsInShoppingCart(cartItemVO)) {
				GiftWithPurchase giftWithPurchase = new GiftWithPurchase();
				giftWithPurchase.setPromoId(accessoryOffer.getPromotionId());
				giftWithPurchase.setActionName(accessoryOffer.getOfferProduct().getAccessoryOfferProduct().getAccessoryOfferCode());
				if(accessoryOffer.getOfferHeader().getOfferDescriptionList() != null 
						&& !accessoryOffer.getOfferHeader().getOfferDescriptionList().isEmpty()) {
					for(Description descr: accessoryOffer.getOfferHeader().getOfferDescriptionList()) {
						if(Constants.LANG_EN.equalsIgnoreCase(descr.getLocale())) {
							giftWithPurchase.setDescription(descr.getDescriptionText());
							break;
						}
					}
					if(StringUtils.isEmpty(giftWithPurchase.getDescription())) {//take the first if EN is not found
						giftWithPurchase.setDescription(accessoryOffer.getOfferHeader().getOfferDescriptionList().get(0).getDescriptionText());
					}
				}
				if(accessoryOffer.getOfferProduct().getAccessoryOfferProduct().getGiftList() != null 
						&& !accessoryOffer.getOfferProduct().getAccessoryOfferProduct().getGiftList().isEmpty()
						&& accessoryOffer.getOfferProduct().getAccessoryOfferProduct().getGiftList().get(0).getGiftAmount() != null) {
					giftWithPurchase.setOriginalCostAmount(String.valueOf(accessoryOffer.getOfferProduct().getAccessoryOfferProduct().getGiftList().get(0).getGiftAmount()));
				}
				if(accessoryOffer.getOfferProduct().getAccessoryOfferProduct() != null
						&& accessoryOffer.getOfferProduct().getAccessoryOfferProduct().getContractTermList() != null
						&& !accessoryOffer.getOfferProduct().getAccessoryOfferProduct().getContractTermList().isEmpty()) {
					giftWithPurchase.setOriginalCommitmentTerm(String.valueOf(accessoryOffer.getOfferProduct().getAccessoryOfferProduct().getContractTermList().get(0)));
				}
				Date perspDt = accessoryOffer.getOfferHeader().getPerspectiveDate() != null? accessoryOffer.getOfferHeader().getPerspectiveDate(): new Date();
				giftWithPurchase.setPerspectiveDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(perspDt));
				giftWithPurchaseItems.add(giftWithPurchase);
			}
		}
		return giftWithPurchaseItems;
	}

	public static GetDepositInfoRequest transformToGetDepositInfoRequest(Integer opOrderId) {
		GetDepositInfoRequest request = new GetDepositInfoRequest();
		
		if(opOrderId!=null){
			request.setOrderId(String.valueOf(opOrderId));
		}		
		return request;
	}

	public static CreateInvoiceAdapterRequest transformToCreateInvoiceAdapterRequest(Integer opOrderId) {
		CreateInvoiceAdapterRequest request = new CreateInvoiceAdapterRequest();
		if(opOrderId!=null){
			request.setOrderid(String.valueOf(opOrderId));
		}
		
		return request;
	}

	public static SubmitOrderAdapterRequest transformToSubmitOrderAdapterRequest(Integer opOrderId, boolean forceBackOffice) {
		SubmitOrderAdapterRequest request = new SubmitOrderAdapterRequest();
		request.setOrderid(String.valueOf(opOrderId));
		if(forceBackOffice) {
			request.setMode(OP_TRANSFER_TO_BACKOFFICE);
		}
		return request;
	}

	public static GetBookingRequirementRequest transformToGetBookingRequirementRequest(Integer opOrderId) {
		GetBookingRequirementRequest request = new GetBookingRequirementRequest();
		
		if(opOrderId!=null){
			request.setOrderId(String.valueOf(opOrderId));
		}		
		return request;
	}
	
	public static QuoteRequest transformToGetQuoteRequest(Integer opOrderId) {
		QuoteRequest request = new QuoteRequest();
		request.setOrderId(String.valueOf(opOrderId));
		return request;
	}

	/**********************************************************/
	/* transformToUpdateOrderAdapterRequest - Gary 2018-10-29 */
	/**
	 * @param opOrderId ********************************************************/
	public static UpdateOrderAdapterRequest transformToUpdateOrderAdapterRequest(ShoppingCartContextVO shoppingCartContextVO, List<OrderCommentVO> orderComments, com.telus.csm.ewlnsc.adapter.oqs.domain.ProductOrder shoppingCartProductOrder, int opOrderId) {
		ShoppingCartVO shoppingCart = shoppingCartContextVO.getShoppingCartVO();

		UpdateOrderAdapterRequest updateOrderAdapterRequest = new UpdateOrderAdapterRequest();
		if(EnterpriseWLNOrderUtil.opIdExistInExternalOrderDetailList(opOrderId,shoppingCart)){
			updateOrderAdapterRequest.setOrderid(String.valueOf(opOrderId));
		}
		
		updateOrderAdapterRequest.getBody().setType(EnterpriseWLNSalesServicesConstants.TYPE_DE); //will be defaulted to "DE" ; may have "RE" in future

		if (shoppingCart.getCartItemList() != null) {
			for (CartItemVO cartItemVO: shoppingCart.getCartItemList()) {
				if (cartItemVO.getProducts() != null) {
					updateOrderAdapterRequest.getBody().setId(shoppingCartProductOrder.getId());
					if (cartItemVO.getProducts().getHomePhoneProduct() != null) {
						ProductOrderItem productOrderItem = OPHomePhoneUpdateTransformer.transform(shoppingCartContextVO, 
										                               cartItemVO, 
										                               cartItemVO.getProducts().getHomePhoneProduct(), 
										                               shoppingCartProductOrder, 
										                               orderComments);
						// only add to productOrderItem if comment is not null
						if (productHasComment(productOrderItem))
							updateOrderAdapterRequest.getBody().addProductOrderItem(productOrderItem);
					}

					if(cartItemVO.getProducts().getInternetProduct() != null) {
						ProductOrderItem productOrderItem = OPInternetUpdateTransformer.transform(shoppingCartContextVO, 
										                              cartItemVO, 
										                              cartItemVO.getProducts().getInternetProduct(), 
										                              shoppingCartProductOrder, 
										                              orderComments);
						if (productHasComment(productOrderItem))
							updateOrderAdapterRequest.getBody().addProductOrderItem(productOrderItem);
					}

					if(cartItemVO.getProducts().getTelevisionProduct() != null) {
						ProductOrderItem productOrderItem = OPTVUpdateTransformer.transform(shoppingCartContextVO, 
								                        cartItemVO, 
								                        cartItemVO.getProducts().getTelevisionProduct(), 
								                        shoppingCartProductOrder, 
								                        orderComments);
						if (productHasComment(productOrderItem))
							updateOrderAdapterRequest.getBody().addProductOrderItem(productOrderItem);
					}
				}
			}
		}

		return updateOrderAdapterRequest;
	}
	

	private static boolean productHasComment(ProductOrderItem productOrderItem){
		boolean hasComment = false;
		
		if (productOrderItem == null)
			return hasComment;
		
		if (productOrderItem.getProduct() == null || 
			productOrderItem.getProduct().getProductComponents() == null ||
			productOrderItem.getProduct().getProductComponents().size() == 0 )
			return hasComment;
		
		List<ProductComponent> productComponents = productOrderItem.getProduct().getProductComponents();
		for (ProductComponent productComponent : productComponents){
			if (productComponent != null && "comments".equals(productComponent.getName())){
				for (ProductComponent productComponentComment : productComponent.getProductComponents()){
					if ( productComponentComment.getProductCharacteristics() != null && productComponentComment.getProductCharacteristics().size() > 0){
						for (ProductCharacteristic characteristics : productComponentComment.getProductCharacteristics()){
							if (characteristics != null && "noteText".equals(characteristics.getName()))
								return true;
						}
					} 
				}
				
			}
		}
		
		
		return hasComment;
	}


	public static OPShoppingCartDelegateResponseVO transformToOPShoppingCartDelegateResponseVO(UpdateOrderAdapterResponse updateOrderAdapterResponse) {
		if(updateOrderAdapterResponse == null) {
			return null;
		}

		OPShoppingCartDelegateResponseVO responseVO = new OPShoppingCartDelegateResponseVO();
		responseVO.setOpShoppingCartId(updateOrderAdapterResponse.getId());
		responseVO.setMessageList(updateOrderAdapterResponse.getMessageList());
		return responseVO;
	}

	public static GetOrderSummaryByOrderIdAdapterRequest transformToGetOrderSummaryByOrderIdAdapterRequest(ShoppingCartContextVO shoppingCartContextVO) {
		GetOrderSummaryByOrderIdAdapterRequest request = new GetOrderSummaryByOrderIdAdapterRequest();
		String externalOrderId = EnterpriseWLNOrderUtil.getExternalOrderIdBySystem(shoppingCartContextVO.getShoppingCartVO(), EnterpriseWLNSalesServicesConstants.EXTERNAL_SYSTEM_OMS);
		if(!StringUtils.isBlank(externalOrderId)){
			request.setOrderId(externalOrderId);
		}		
		request.setPriceOffering(false);
		request.setTxnId(shoppingCartContextVO.getShoppingCartVO().getOperationHeader().getSalesTransactionId());
		return request;
	}
}
