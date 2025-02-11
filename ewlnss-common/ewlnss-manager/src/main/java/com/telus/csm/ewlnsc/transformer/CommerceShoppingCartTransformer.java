package com.telus.csm.ewlnsc.transformer;

import static com.telus.csm.ewlnsc.util.CharacteristicUtils.getBooleanByName;
import static com.telus.csm.ewlnsc.util.CharacteristicUtils.getValueByName;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.HOME_SECURITY;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.HSIC;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.SING;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.TTV;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.telus.csm.commerce.sc.domain.BillingAccountRef;
import com.telus.csm.commerce.sc.domain.CartItem;
import com.telus.csm.commerce.sc.domain.CartPrice;
import com.telus.csm.commerce.sc.domain.CartTerm;
import com.telus.csm.commerce.sc.domain.Characteristic;
import com.telus.csm.commerce.sc.domain.PriceAlteration;
import com.telus.csm.commerce.sc.domain.ProductOfferingRef;
import com.telus.csm.commerce.sc.domain.ProductRefOrValue;
import com.telus.csm.commerce.sc.domain.RelatedParty;
import com.telus.csm.commerce.sc.domain.RelatedPlaceRefOrValue;
import com.telus.csm.commerce.sc.domain.TelusBillingAccountRef;
import com.telus.csm.commerce.sc.domain.TelusCartItem;
import com.telus.csm.commerce.sc.domain.TelusCartPrice;
import com.telus.csm.commerce.sc.domain.TelusCharacteristic;
import com.telus.csm.commerce.sc.domain.TelusProductOfferingRef;
import com.telus.csm.commerce.sc.domain.TelusRelatedImmediatePromotion;
import com.telus.csm.commerce.sc.domain.TelusSalesChannel;
import com.telus.csm.commerce.sc.domain.TelusSalesRepAssociatedOutlet;
import com.telus.csm.commerce.sc.domain.TelusShoppingCart;
import com.telus.csm.ess.common.domain.DescriptionVO;
import com.telus.csm.ewlnsc.domain.AgentProfileVO;
import com.telus.csm.ewlnsc.domain.ApplicationProfileVO;
import com.telus.csm.ewlnsc.domain.BillingAccountVO;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.CharacteristicVO;
import com.telus.csm.ewlnsc.domain.CustomerVO;
import com.telus.csm.ewlnsc.domain.EquipmentAcquisitionTypeVO;
import com.telus.csm.ewlnsc.domain.FFHDiscountTypeVO;
import com.telus.csm.ewlnsc.domain.FFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.FFHOfferHeaderVO;
import com.telus.csm.ewlnsc.domain.FFHProductPlanAddOnTypeVO;
import com.telus.csm.ewlnsc.domain.FFHSweetenerTypeVO;
import com.telus.csm.ewlnsc.domain.HomePhoneProductTypeVO;
import com.telus.csm.ewlnsc.domain.HomeSecurityProductTypeVO;
import com.telus.csm.ewlnsc.domain.InternetProductTypeVO;
import com.telus.csm.ewlnsc.domain.MoneyVO;
import com.telus.csm.ewlnsc.domain.PriceVO;
import com.telus.csm.ewlnsc.domain.ProductComponentVO;
import com.telus.csm.ewlnsc.domain.ProductOfferingRefVO;
import com.telus.csm.ewlnsc.domain.ProductTypeBaseVO;
import com.telus.csm.ewlnsc.domain.ProductTypeVO;
import com.telus.csm.ewlnsc.domain.RelatedImmediatePromotionVO;
import com.telus.csm.ewlnsc.domain.SalesRepAssociatedOutletVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingProfileVO;
import com.telus.csm.ewlnsc.domain.TelevisionProductTypeVO;
import com.telus.csm.ewlnsc.domain.UserProfileVO;

/**
 * This class transform CommerceShoppingCart to ShoppingCartVO
 *
 */
public class CommerceShoppingCartTransformer {

	private static final Logger LOGGER = Logger.getLogger(CommerceShoppingCartTransformer.class);

	public static ShoppingCartVO mapToShoppingCartVO( TelusShoppingCart commerceSC, String cartItemId ) {
		
		ShoppingCartVO shoppingCartVO = new ShoppingCartVO();
		shoppingCartVO.setShoppingCartId( commerceSC.getId() );
		
		if ( CollectionUtils.isNotEmpty( commerceSC.getCartItem() ) ) {
			
			TelusCartItem commerceCartItem = (TelusCartItem) commerceSC.getCartItem().get(0);
			mapCartLevelAttributes(shoppingCartVO, commerceCartItem);
			
			List<String> cartItemIdList = new ArrayList<String> ();
			List<String> addedCartItemList = new ArrayList<String> ();

			for ( CartItem cartItem: commerceSC.getCartItem() ) {
			
				String cartItemInfo = cartItem.getId() + ":" + cartItem.getProduct().getName();
				cartItemIdList.add( cartItemInfo );
				
				if ( StringUtils.isBlank(cartItemId) //if it's null, then return all cartItems 
					|| cartItem.getId().equals( cartItemId ) //otherwise only the matching one 
					|| cartItem.getId().equalsIgnoreCase( "ES"+cartItemId )
					) {
					
					addedCartItemList.add( cartItemInfo );
					
					shoppingCartVO.getCartItemList().add( mapToCartItemVO( (TelusCartItem) cartItem) );
				}
			}
			
			LOGGER.info ("commerceShoppingCart(" + commerceSC.getId() + ") cartItems" + cartItemIdList + ";  requested cartItemId="+ cartItemId + "; transformed cartItems" + addedCartItemList );
		}
		
		return shoppingCartVO;
	}

	private static void mapCartLevelAttributes(ShoppingCartVO shoppingCartVO, TelusCartItem commerceCartItem) {
		
		shoppingCartVO.setShoppingProfile( mapToShoppingProfileVO( commerceCartItem ) );
		//TODO shoppingCartVO.setNote( mapToNotes(commerceCartItem) );
		//TODO shoppingCartVO.setContactMedium( mapToContactMediumVO(commerceCartItem) );
		shoppingCartVO.setCustomer( mapToCustomerVO(commerceCartItem) );
		shoppingCartVO.setBillingAccount( mapToBillingAccountVO(commerceCartItem));
		
		//TODO does every top level cartItem has same place information?
		shoppingCartVO.setServiceAddress( mapToServiceAddressVO(commerceCartItem));
	}


	private static  ShoppingProfileVO mapToShoppingProfileVO(TelusCartItem commerceCartItem)  {
		
		ShoppingProfileVO profile = new ShoppingProfileVO();
		
		String originalAppId = getValueByName(commerceCartItem.getCharacteristic(), "originatorApplicationId");
		if ( originalAppId!=null ) {
			ApplicationProfileVO appVO = new ApplicationProfileVO();
			appVO.setOriginatorApplicationId(originalAppId);
			profile.setApplicationProfile(appVO);
		}
		
		if ( CollectionUtils.isNotEmpty( commerceCartItem.getSalesChannel() ) ) {
			
			for( TelusSalesChannel salesChannel : commerceCartItem.getSalesChannel() ) {
				
				if ( "USER".equalsIgnoreCase( salesChannel.getRole()) ) {
					
					UserProfileVO userProfile = new UserProfileVO();
					
					userProfile.setChannelOrganizationInternalId(salesChannel.getChannelOrganizationInternalId());
					userProfile.setChannelOrganizationNumber(salesChannel.getChannelOrganizationNumber());
					userProfile.setChannelOrganizationTypeCd(salesChannel.getChannelOrganizationTypeCd());
					userProfile.setLoginId(salesChannel.getLoginId());
					userProfile.setOutletAssociatedProvinces(salesChannel.getOutletAssociatedProvinces());
					userProfile.setSalesPersonRoleCd(salesChannel.getSalesPersonRoleCd());
					
					if (CollectionUtils.isNotEmpty( salesChannel.getSalesRepAssociatedOutlet() ) ) {
						List<SalesRepAssociatedOutletVO> salesRepAssociatedOutlet = new ArrayList<SalesRepAssociatedOutletVO> ();
						for( TelusSalesRepAssociatedOutlet outlet : salesChannel.getSalesRepAssociatedOutlet() ) {
							SalesRepAssociatedOutletVO outletVO = new SalesRepAssociatedOutletVO();
							outletVO.setChannelOutletId(outlet.getChannelOutletId());
							outletVO.setOutletInternalId(outlet.getOutletInternalId());
							salesRepAssociatedOutlet.add( outletVO );
						}
						userProfile.setSalesRepAssociatedOutlet(salesRepAssociatedOutlet);
					};
					
					userProfile.setSalesRepId(salesChannel.getSalesRepId());
					userProfile.setSalesRepInternalId(salesChannel.getSalesRepInternalId());
					
					profile.setUserProfile(userProfile);
				}
				
				else if ( "AGENT".equalsIgnoreCase( salesChannel.getRole()) ) {
					
					AgentProfileVO agentProfile = new AgentProfileVO();
					agentProfile.setChannelOrganizationInternalId(salesChannel.getChannelOrganizationInternalId());
					agentProfile.setChannelOrganizationNumber(salesChannel.getChannelOrganizationNumber());
					agentProfile.setChannelOrganizationTypeCd(salesChannel.getChannelOrganizationTypeCd());
					agentProfile.setLoginId(salesChannel.getLoginId());
					agentProfile.setOutletAssociatedProvinces(salesChannel.getOutletAssociatedProvinces());
					//TODO agentProfile.setEmployeeId(salesChannel.get);
					agentProfile.setUserPrivilegeRoleCodes(salesChannel.getUserPrivillegeRoleCd());
					
					if (CollectionUtils.isNotEmpty( salesChannel.getSalesRepAssociatedOutlet() ) ) {
						List<SalesRepAssociatedOutletVO> salesRepAssociatedOutlet = new ArrayList<SalesRepAssociatedOutletVO> ();
						for( TelusSalesRepAssociatedOutlet outlet : salesChannel.getSalesRepAssociatedOutlet() ) {
							SalesRepAssociatedOutletVO outletVO = new SalesRepAssociatedOutletVO();
							outletVO.setChannelOutletId(outlet.getChannelOutletId());
							outletVO.setOutletInternalId(outlet.getOutletInternalId());
							salesRepAssociatedOutlet.add( outletVO );
						}
						agentProfile.setSalesRepAssociatedOutlet(salesRepAssociatedOutlet);
					};
					
					agentProfile.setSalesRepId(salesChannel.getSalesRepId());
					agentProfile.setSalesRepInternalId(salesChannel.getSalesRepInternalId());
					
					profile.setAgentProfile(agentProfile);
				}
			}
		}
		
		return profile;
	}

	private static CustomerVO mapToCustomerVO(TelusCartItem commerceCartItem)  {
		
		CustomerVO customerVO = null; 
		
		if (CollectionUtils.isNotEmpty( commerceCartItem.getRelatedParty() ) ) {
			for ( RelatedParty relatedParty: commerceCartItem.getRelatedParty()) {
				if ( "CUSTOMER".equalsIgnoreCase( relatedParty.getRole() ) ) { 
					customerVO = new CustomerVO();
					customerVO.setCustomerId( relatedParty.getId() );
					break;
				}
			}
		}
		return customerVO;
	}

	private static  BillingAccountVO mapToBillingAccountVO(TelusCartItem commerceCartItem)  {
		BillingAccountVO billingAccountVO = null; 
		if (CollectionUtils.isNotEmpty( commerceCartItem.getBillingAccount() ) ) {
			for ( BillingAccountRef billingAccount : commerceCartItem.getBillingAccount()) {
				if ( "EXISTING_BILLING_ACCOUNT".equalsIgnoreCase( billingAccount.getName() ) ) { 
					billingAccountVO = mapToBillingAccountVO( (TelusBillingAccountRef) billingAccount);
					break;
				}
			}
		}
		
		return billingAccountVO;
	}

	private static  BillingAccountVO mapToBillingAccountVO(TelusBillingAccountRef billingAccount) {
		
		BillingAccountVO billingAccountVO = new BillingAccountVO();
		billingAccountVO.setBillingAccountNumber( billingAccount.getId() );
		for( Characteristic characteristic: billingAccount.getCharacteristic() ) {
			TelusCharacteristic tCharacteristic = (TelusCharacteristic) characteristic;
			//TODO find out correct names
			if ( "ACCOUNT_TYPE".equalsIgnoreCase(tCharacteristic.getName()) ) {
				billingAccountVO.setAccountTypeCd( (String) tCharacteristic.getValue() );
			} else if ( "ACCOUNT_SUBTYPE".equalsIgnoreCase(tCharacteristic.getName()) ) {
				billingAccountVO.setAccountSubTypeCd( (String) tCharacteristic.getValue());
			} else if ( "ACCOUNT_MASTER_SOURCE_CD".equalsIgnoreCase(tCharacteristic.getName()) ) {
				billingAccountVO.setAccountMasterSourceTypeCd( (String) tCharacteristic.getValue());
			}
		}
		return billingAccountVO;
	}
	
	private static ServiceAddressVO mapToServiceAddressVO(TelusCartItem commerceCartItem)  {
		
		ServiceAddressVO serviceAddressVO = null;
		if (commerceCartItem.getProduct()!=null&& CollectionUtils.isNotEmpty(commerceCartItem.getProduct().getPlace()) ) {
			for( RelatedPlaceRefOrValue place: commerceCartItem.getProduct().getPlace() ) {
				if ( "SERVICEADDRESS".equalsIgnoreCase(((place.getRole().replace(" ","" )).replace("_","")))) {
					serviceAddressVO = new ServiceAddressVO();
					serviceAddressVO.setServiceAddressId(place.getId()) ;
					//TODO
					//serviceAddressVO.setCityCode(cityCode);
					//serviceAddressVO.setProvinceCode(provinceCode);
				}
			}
		}
		return serviceAddressVO;
	}
	
	private static CartItemVO mapToCartItemVO(TelusCartItem srcCartItem) {
		
		CartItemVO targetCartItem = new CartItemVO();
		
		targetCartItem.setCartItemId(srcCartItem.getId());
		targetCartItem.setCartItemRelationId( srcCartItem.getId() );
		//targetCartItem.setAction( srcCartItem.getAction() ); not existing in SC v2
		targetCartItem.setCartItemContextTypeList( srcCartItem.getCartItemContext());
		
		targetCartItem.setProductMarketOffering( mapToMarketOfferingVO( (TelusProductOfferingRef) srcCartItem.getProductOffering() ) );
		targetCartItem.setProducts( mapToProductVO ( targetCartItem, srcCartItem ) );
		
		targetCartItem.setRelatedPromotionList( getCommitmentOffers( srcCartItem) ); //FIFA commitment offer
		
		return targetCartItem;
	}

	private static ProductTypeVO mapToProductVO( CartItemVO cartItemVO, TelusCartItem srcCartItem) {
		
		ProductTypeVO productVO = new ProductTypeVO();
		String productName = srcCartItem.getProduct().getName();

		if ( HSIC.equals(productName ) ) {
			
			cartItemVO.setSalesOrderItem(true);
			productVO.setInternetProduct( mapToInternetProduct( srcCartItem) );
			
		} else if ( TTV.equals( productName ) ) {
			
			cartItemVO.setSalesOrderItem(true);
			productVO.setTelevisionProduct( mapToTelevisionProduct( srcCartItem) );
			
		} else if ( SING.equals( productName ) ) {

			cartItemVO.setSalesOrderItem(true);
			productVO.setHomePhoneProduct(  mapToHomePhoneProduct( srcCartItem) );
			
		} else if ( HOME_SECURITY.equals( productName ) ) { //FIFA-SHS

			cartItemVO.setSalesOrderItem(true);
			productVO.setHomeSecurityProduct(  mapToHomeSecurityProduct( srcCartItem) );
			
		} else {
			
			LOGGER.error ("Unsupported shoppingCart product: " + productName );
			throw new RuntimeException("Unsupported shoppingCart product: " + productName );
		}

		return productVO;
	}

//	private static ProductOrderTypeVO mapToProductOrderType( Product product ) {
//		
//		ProductOrderTypeVO orderTypeVO  = null;
//		if ( CollectionUtils.isNotEmpty( product.getCharacteristic() ) ) {
//			orderTypeVO = new ProductOrderTypeVO();
//			orderTypeVO.setOrderTypeCd( getValueByName( product.getCharacteristic(), "orderTypeCd") );
//			orderTypeVO.setOrderSubTypeCd( getValueByName( product.getCharacteristic(), "orderSubTypeCd") );
//		}
//		return orderTypeVO;
//	}

	private static String gettContractTermCd(CartItem srcCartItem) {
		
		if (CollectionUtils.isNotEmpty( srcCartItem.getItemTerm() ) ) {
			for ( CartTerm term: srcCartItem.getItemTerm() ) {
				
				//pick the first one, as there is only one term
				// and cover to integer
				return String.valueOf( term.getDuration().getAmount().intValue() );
			}
		}
		return null;
	}

	private static HomePhoneProductTypeVO mapToHomePhoneProduct(TelusCartItem srcCartItem) {
		
		HomePhoneProductTypeVO productVO = new HomePhoneProductTypeVO();
		productVO.setProductTypeCd(srcCartItem.getProduct().getName());
		mapProductTypeBaseVO( productVO, srcCartItem );
		
		return productVO;
	}

	private static TelevisionProductTypeVO mapToTelevisionProduct(TelusCartItem srcCartItem) {
		
		TelevisionProductTypeVO productVO = new TelevisionProductTypeVO();
		mapProductTypeBaseVO( productVO, srcCartItem );
		return productVO;
	}

	private static InternetProductTypeVO mapToInternetProduct(TelusCartItem srcCartItem) {
		
		InternetProductTypeVO productVO = new InternetProductTypeVO();
		mapProductTypeBaseVO( productVO, srcCartItem );
		
		return productVO;
	}

	private static HomeSecurityProductTypeVO mapToHomeSecurityProduct(TelusCartItem srcCartItem) {
		
		HomeSecurityProductTypeVO productVO = new HomeSecurityProductTypeVO();
		mapProductTypeBaseVO( productVO, srcCartItem );
		
		return productVO;
	}	
	private static void mapProductTypeBaseVO(ProductTypeBaseVO productTypeBaseVO, TelusCartItem srcCartItem) {
		
		ProductRefOrValue product = srcCartItem.getProduct();
		
		//TODO v2 productTypeBaseVO.setProductOrderType( mapToProductOrderType( product) );
		productTypeBaseVO.setSelectedContractTermCd( gettContractTermCd ( srcCartItem ) );
		productTypeBaseVO.setProductComponents( getProductComponents( product ) );
		productTypeBaseVO.setEquipments( getEquipments( srcCartItem ) );
		productTypeBaseVO.setAddOns( getAddOns( srcCartItem ));
		//TODO productTypeBaseVO.setDiscounts(discounts);
		productTypeBaseVO.setSweeteners( getSweeteners( srcCartItem ) );
		//TODO productTypeBaseVO.setAppointmentDetail(appointmentDetail);
		
		//TODO V2
		productTypeBaseVO.setWinback( getBooleanByName( product.getProductCharacteristic(), "winback" ));
		
		productTypeBaseVO.setCharacteristics(getCharacteristics(product.getProductCharacteristic()));

	}

	//Fifa shopping cart specific properties
	private static List<RelatedImmediatePromotionVO> getCommitmentOffers(TelusCartItem srcCartItem) {
		
		List<RelatedImmediatePromotionVO> result = new ArrayList<RelatedImmediatePromotionVO>();
		
		if ( CollectionUtils.isNotEmpty( srcCartItem.getRelatedImmediatePromotion() ) ) {
			for( TelusRelatedImmediatePromotion srcPromo : srcCartItem.getRelatedImmediatePromotion() ) {
				String promoSourceSystemId = getValueByName( srcPromo.getCharacteristic() , "sourceSystemId");
				
				if ( "13574".equals( promoSourceSystemId )  ) {
					
					RelatedImmediatePromotionVO target = new RelatedImmediatePromotionVO();
					target.setId( srcPromo.getId() );
					target.setName( getValueByName( srcPromo.getCharacteristic() , "Name") );

					result.add( target );
				}
			}
		}
		return result;
	}

	/**
	 * Extract and map commerceShoppingCart's  all level cartIitem.itemPrice.priceAlteration[priceType=Recurrent] (a.k.a Included Promotion )  to Sweetener
	 * 
	 * @param cartItem
	 * @return
	 */
	private static List<FFHSweetenerTypeVO> getSweeteners(CartItem cartItem) {
		
		List<FFHSweetenerTypeVO> sweeteners = new ArrayList<FFHSweetenerTypeVO> ();
		
		//check cartItem level
		if (CollectionUtils.isNotEmpty( cartItem.getItemPrice() )  ) {
			for( CartPrice cartPrice: cartItem.getItemPrice() ) {
				sweeteners.addAll( getSweeteners( cartItem.getProductOffering().getId(), cartPrice) );
			}
		}
		
		//QC84026 fix: check child cartItem as well
		if ( CollectionUtils.isNotEmpty( cartItem.getCartItem()) ) {
			for ( CartItem childItem : cartItem.getCartItem() ) {
				
				sweeteners.addAll( getSweeteners( childItem ) );
			}
		}
		return sweeteners;
	}

	private static List<FFHSweetenerTypeVO> getSweeteners( String cartItemOfferId, CartPrice cartPrice) {
		
		List<FFHSweetenerTypeVO> sweeteners = new ArrayList<FFHSweetenerTypeVO> ();
		
		if (  CollectionUtils.isNotEmpty( cartPrice.getPriceAlteration() ) ) {
			int i=0;
			for ( PriceAlteration pa : cartPrice.getPriceAlteration() ) {
				
				if ( "Recurrent".equalsIgnoreCase( pa.getPriceType() ) ) {
					
					MoneyVO basePriceAmount = new MoneyVO();
					basePriceAmount.setValueAmt( pa.getPrice().getDutyFreeAmount().getValue().floatValue() );
					
					PriceVO priceVO = new PriceVO();
					priceVO.setBasePriceAmount( basePriceAmount );
					priceVO.setRecurrenceCount(pa.getApplicationDuration() );
					priceVO.setPricingTypeCd( pa.getPriceType() );
					
					RelatedImmediatePromotionVO relatedImmediatePromotion = new RelatedImmediatePromotionVO();
					relatedImmediatePromotion.setName( pa.getName() );
					
					//need to populate the id so that later on, Transformer can find it
					//V1 mapping
//					if ( StringUtils.isNoneBlank( pa.getImmediatePromotionId() ) ) {
//						relatedImmediatePromotion.setId(pa.getImmediatePromotionId());
//					} else if ( StringUtils.isNotBlank( pa.getAlterationBundleProductOfferingId()) ) {
//						relatedImmediatePromotion.setId(pa.getAlterationBundleProductOfferingId());
//					} else {
//						//make up a Id
//						relatedImmediatePromotion.setId( cartItemOfferId + "_INCL" + (i++) );
//					}
					//V2 mapping
					relatedImmediatePromotion.setId( cartItemOfferId + "_INCL" + (i++) );
					
					ProductComponentVO discountIdentifier = new ProductComponentVO();
					//this is to identify from which cartItem.productOffering  this priceAlteration is coming from 
					discountIdentifier.setParentProductCatalogueId( cartItemOfferId ); 
					//continue using relatedImmediatePromotion.id
					discountIdentifier.setProductCatalogueId( relatedImmediatePromotion.getId());

					FFHDiscountTypeVO discount = new FFHDiscountTypeVO();
					discount.setProductCatalogueIdentifiers( Arrays.asList( discountIdentifier) );
					//NO where to get it: discount.setDiscountCode(discountCode);
					
					FFHSweetenerTypeVO sweetener = new FFHSweetenerTypeVO();
					sweetener.setRelatedPromotionList( Arrays.asList(relatedImmediatePromotion) );
					sweetener.setDiscounts( Arrays.asList(discount ) );
					sweetener.setPrice(priceVO);
					
					sweeteners.add(sweetener);
				}
			}
		}
		
		return sweeteners;
	}
	
	private static List<FFHProductPlanAddOnTypeVO> getAddOns(TelusCartItem srcCartItem) {

		List<FFHProductPlanAddOnTypeVO> ffhProductPlanAddOnList = new ArrayList<FFHProductPlanAddOnTypeVO>();
		
		if(CollectionUtils.isNotEmpty(srcCartItem.getCartItem()) ){
			
			for( CartItem cartItem : srcCartItem.getCartItem()){
				
				TelusCartItem productItem = (TelusCartItem) cartItem;
				
				if ( isEquipmentProduct( productItem ) == false  ) { //for non equipment product
					
					FFHProductPlanAddOnTypeVO addOnVO = new FFHProductPlanAddOnTypeVO();	
					addOnVO.setAction( getValueByName( productItem.getCharacteristic(), "action") );
					addOnVO.setProductCatalogueIdentifier( getProductCatalogueIdentifier(productItem.getProductOffering(), addOnVO.getAction() ) );
					ffhProductPlanAddOnList.add(addOnVO);
				}
			}
		}

		return ffhProductPlanAddOnList;
	}

	private static List<ProductComponentVO> getProductComponents(ProductRefOrValue product) {
		List<ProductComponentVO> components = new ArrayList<ProductComponentVO>();
		return components;
	}

	private static List<FFHEquipmentTypeVO> getEquipments(CartItem srcCartItem) {
		
		List<FFHEquipmentTypeVO> equipmentTypeList = new ArrayList<FFHEquipmentTypeVO>();
		
		if(CollectionUtils.isNotEmpty(srcCartItem.getCartItem() ) ){
			
			for( CartItem productItem : srcCartItem.getCartItem()){
				
				if (isEquipmentProduct( productItem ) ) {

					FFHEquipmentTypeVO equipmentVO = new FFHEquipmentTypeVO();
					
					List<Characteristic> productCharacteristic = productItem.getProduct().getProductCharacteristic();
					equipmentVO.setAction( getValueByName( productCharacteristic, "action") );
					
					
					equipmentVO.setDeliveryMethodType( getValueByName( productCharacteristic, "deliveryMethod" ) ); 
					
					equipmentVO.setMaterialItemCode( getValueByName( productCharacteristic, "MicCode" ) ); 
					equipmentVO.setAcquisitionType( getAcquisitionType( productCharacteristic ));
					
					equipmentVO.setProductCatalogueIdentifier( getProductCatalogueIdentifier(productItem.getProductOffering(), equipmentVO.getAction() ) );
					equipmentVO.setDescription( getDescription(productItem.getProductOffering()));
					equipmentVO.setPrice(getPrice( productItem.getItemPrice()));
					
					//capture child cart item level equipment characteristics
					equipmentVO.setCharacteristics( getCharacteristics( productCharacteristic ) ); 

					equipmentTypeList.add(equipmentVO);
				}
			}
		}
		
		return equipmentTypeList;
	}
	
	private static PriceVO getPrice(List<CartPrice> itemTotalPrice) {
		if(itemTotalPrice == null) return null;
		PriceVO priceVO =  null;
		TelusCartPrice cartPrice = (TelusCartPrice) itemTotalPrice.get(0);
		if(  cartPrice != null &&  cartPrice.getFinalPrice() != null
				&&  cartPrice.getFinalPrice().getDutyFreeAmount() != null  ) {
			priceVO = new PriceVO();
			priceVO.setBasePriceAmount(new MoneyVO());
			priceVO.getBasePriceAmount().setValueAmt(new Float(  cartPrice.getFinalPrice().getDutyFreeAmount().getValue() ));
		}
		return priceVO;
	}

	private static DescriptionVO getDescription(ProductOfferingRef productOffering) {
		DescriptionVO desc = new DescriptionVO();
		desc.setDescriptionText(productOffering.getName());
//		desc.setLocale(Enterprisesa);
		return desc;
	}

	private static boolean isEquipmentProduct(CartItem productItem) {
		
		String productType = getValueByName( productItem.getProduct().getProductCharacteristic(), "productType") ;
		boolean result = "EQUIPMENT".equalsIgnoreCase(productType);
		return result;
	}

	private static ProductComponentVO getProductCatalogueIdentifier(ProductOfferingRef productOffering, String action) {
		ProductComponentVO productIdentifier = new ProductComponentVO();
		productIdentifier.setProductCatalogueId(productOffering.getId() );
		productIdentifier.setAction( action );
		
		return productIdentifier;
	}

	private static EquipmentAcquisitionTypeVO getAcquisitionType(List<Characteristic> characteristic) {
		
		String acquisitionType = getValueByName( characteristic, "acquisitionType" );
		
		if ( StringUtils.isNoneBlank( acquisitionType) ) {
		
			EquipmentAcquisitionTypeVO equipmentAcquisition = new EquipmentAcquisitionTypeVO();

			if ( "Rented".equalsIgnoreCase(acquisitionType) ) {
				equipmentAcquisition.setRentalEquipmentIndicator(true);
			} else if ("Purchased".equalsIgnoreCase(acquisitionType) ) {
				equipmentAcquisition.setBuyIndicator(true);
			} else if ( "BYOD".equalsIgnoreCase(acquisitionType) ) {
				equipmentAcquisition.setCustomerOwnedIndicator(true);
			} else if ( "Warranty".equalsIgnoreCase(acquisitionType) ) {
				//??what do map?
			} else {
				LOGGER.error("unknown acquisitionType: " + acquisitionType );
				throw new RuntimeException ( "unknown acquisitionType: " + acquisitionType );
			}
			return equipmentAcquisition;
		}
		
		return null;
	}

	private static ProductOfferingRefVO mapToMarketOfferingVO( TelusProductOfferingRef srcOffer ) {
		
		ProductOfferingRefVO targetProductOffering = new ProductOfferingRefVO();
		FFHOfferHeaderVO offerHeader = new FFHOfferHeaderVO();
		targetProductOffering.setOfferHeader(offerHeader);
		
		offerHeader.setOfferId( srcOffer.getId() );
		if (srcOffer.getPerspectiveDate()!=null ) {
			offerHeader.setPerspectiveDate( srcOffer.getPerspectiveDate().toDate() );
		}
		
		if (srcOffer.getSelectedCoupon()!=null) {
			//TODO promotionCode - where to map to???
			srcOffer.getSelectedCoupon().getId();
		}
		
		//TODO confirm the characteristic names
		List<Characteristic> offerCharacteristic = srcOffer.getCharacteristic();
		offerHeader.setOfferCode( getValueByName ( offerCharacteristic, "offerCode") ); 
		offerHeader.setOfferProgramId( getValueByName ( offerCharacteristic, "offerProgramId") );
		offerHeader.setSystemId( getValueByName ( offerCharacteristic, "systemID") );
		
		return targetProductOffering;
	}
	
	private static List<CharacteristicVO> getCharacteristics(List<Characteristic> characteristic) {
		
		List<CharacteristicVO> result = null;
		if ( CollectionUtils.isNotEmpty(characteristic)) {
			result = new ArrayList<CharacteristicVO> ();
			for( Characteristic source : characteristic) {
				CharacteristicVO target = new CharacteristicVO();
				target.setName( source.getName() );
				target.setValue( ((TelusCharacteristic)source).getValue() );
				result.add( target );
			}
		}
		return result;
	}

}
