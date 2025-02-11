package com.telus.csm.ewlnsc.transformer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ess.rest.domain.Address;
import com.telus.csm.ess.rest.domain.AgentProfile;
import com.telus.csm.ess.rest.domain.ApplicationProfile;
import com.telus.csm.ess.rest.domain.BillingAccount;
import com.telus.csm.ess.rest.domain.CartItem;
import com.telus.csm.ess.rest.domain.CartItemDisconnectOrder;
import com.telus.csm.ess.rest.domain.CartItemGiftWithPurchase;
import com.telus.csm.ess.rest.domain.CartItemSalesOrder;
import com.telus.csm.ess.rest.domain.ClientName;
import com.telus.csm.ess.rest.domain.ContactMedium;
import com.telus.csm.ess.rest.domain.Customer;
import com.telus.csm.ess.rest.domain.DepositAssessedType;
import com.telus.csm.ess.rest.domain.DisconnectRequestType;
import com.telus.csm.ess.rest.domain.DisconnectServiceAddressType;
import com.telus.csm.ess.rest.domain.FulfillmentOptionType;
import com.telus.csm.ess.rest.domain.MediumCharacteristic;
import com.telus.csm.ess.rest.domain.Note;
import com.telus.csm.ess.rest.domain.OperationHeader;
import com.telus.csm.ess.rest.domain.OriginalCarrierInfoType;
import com.telus.csm.ess.rest.domain.RelatedPartyRef;
import com.telus.csm.ess.rest.domain.SalesRepAssociatedOutlet;
import com.telus.csm.ess.rest.domain.ServiceAddress;
import com.telus.csm.ess.rest.domain.ShoppingCart;
import com.telus.csm.ess.rest.domain.ShoppingProfile;
import com.telus.csm.ess.rest.domain.TelephoneNumberType;
import com.telus.csm.ess.rest.domain.UserProfile;
import com.telus.csm.ewlnsc.domain.AddressVO;
import com.telus.csm.ewlnsc.domain.AgentProfileVO;
import com.telus.csm.ewlnsc.domain.ApplicationProfileVO;
import com.telus.csm.ewlnsc.domain.BillingAccountVO;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.ClientNameVO;
import com.telus.csm.ewlnsc.domain.ContactMediumVO;
import com.telus.csm.ewlnsc.domain.CustomerVO;
import com.telus.csm.ewlnsc.domain.DepositAssessedTypeVO;
import com.telus.csm.ewlnsc.domain.DisconnectRequestTypeVO;
import com.telus.csm.ewlnsc.domain.DisconnectServiceAddressTypeVO;
import com.telus.csm.ewlnsc.domain.FulfillmentOptionTypeVO;
import com.telus.csm.ewlnsc.domain.MediumCharacteristicVO;
import com.telus.csm.ewlnsc.domain.NoteVO;
import com.telus.csm.ewlnsc.domain.OperationHeaderVO;
import com.telus.csm.ewlnsc.domain.OriginalCarrierInfoTypeVO;
import com.telus.csm.ewlnsc.domain.RelatedPartyRefVO;
import com.telus.csm.ewlnsc.domain.SalesRepAssociatedOutletVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingProfileVO;
import com.telus.csm.ewlnsc.domain.TelephoneNumberTypeVO;
import com.telus.csm.ewlnsc.domain.UserProfileVO;
import com.telus.csm.ewlnsc.helper.ShoppingCartItemsComparator;
import com.telus.csm.ewlnss.rest.time.DateTime;
import com.telus.csm.ewlnss.rest.time.LocalDate;

public class ShoppingCartTransformer {

	public static ShoppingCart transformShoppingCartToRest(ShoppingCartVO shoppingCartVO) {
		ShoppingCart shoppingCartRest = new ShoppingCart();
		shoppingCartRest.setBillingAccount(transformToRest(shoppingCartVO.getBillingAccount()));
		shoppingCartRest.setCartContextTypeList(shoppingCartVO.getCartContextTypeList());
		shoppingCartRest.setCartItemList(transformToRestCartItemList(shoppingCartVO.getCartItemList()));
		shoppingCartRest.setContactMedium(transformToRestContactMediumList(shoppingCartVO.getContactMedium()));
		shoppingCartRest.setCustomer(transformToRest(shoppingCartVO.getCustomer()));
		shoppingCartRest.setDepositAssessed(transformToRest(shoppingCartVO.getDepositAssessed()));
		shoppingCartRest.setFulfillmentOption(transformToRest(shoppingCartVO.getFulfillmentOption()));
		shoppingCartRest.setNote(transformToRestNoteList(shoppingCartVO.getNote()));
		shoppingCartRest.setRelatedParty(transformToRest(shoppingCartVO.getRelatedParty()));
		shoppingCartRest.setServiceAddress(transformToRest(shoppingCartVO.getServiceAddress()));
		shoppingCartRest.setShoppingProfile(transformToRest(shoppingCartVO.getShoppingProfile()));	
		return shoppingCartRest;
	}

	/**
	 * 
	 * @param shoppingCartVO  new or retrieved from Cache
	 * @param shoppingCartRest
	 * @return
	 */
	public static ShoppingCartVO transformRestToShoppingCart(String shoppingCartId, ShoppingCart shoppingCartRest) {
		ShoppingCartVO shoppingCartVO = new ShoppingCartVO();
		shoppingCartVO.setShoppingCartId(shoppingCartId);
		shoppingCartVO.setBillingAccount(transformFromRest(shoppingCartRest.getBillingAccount()));
		shoppingCartVO.setCartContextTypeList(shoppingCartRest.getCartContextTypeList());
		shoppingCartVO.setCartItemList(transformFromRestCartItemList(shoppingCartRest.getCartItemList()));
		shoppingCartVO.setContactMedium(transformFromRestContactMediumList(shoppingCartRest.getContactMedium()));
		shoppingCartVO.setCustomer(transformFromRest(shoppingCartRest.getCustomer()));
		shoppingCartVO.setDepositAssessed(transformFromRest(shoppingCartRest.getDepositAssessed()));
		shoppingCartVO.setFulfillmentOption(transformFromRest(shoppingCartRest.getFulfillmentOption()));
		shoppingCartVO.setNote(transformFromRestNoteList(shoppingCartRest.getNote()));
		shoppingCartVO.setRelatedParty(transformFromRest(shoppingCartRest.getRelatedParty()));
		shoppingCartVO.setServiceAddress(transformFromRest(shoppingCartRest.getServiceAddress()));
		shoppingCartVO.setShoppingProfile(transformFromRest(shoppingCartRest.getShoppingProfile()));
		return shoppingCartVO;
	}

	private static ShoppingProfileVO transformFromRest(ShoppingProfile shoppingProfile) {
		if (shoppingProfile == null) {
			return null;
		}
		ShoppingProfileVO shoppingProfileVO = new ShoppingProfileVO();
		shoppingProfileVO.setAgentProfile(transformFromRest(shoppingProfile.getAgentProfile()));
		shoppingProfileVO.setApplicationProfile(transformFromRest(shoppingProfile.getApplicationProfile()));
		shoppingProfileVO.setUserProfile(transformFromRest(shoppingProfile.getUserProfile()));
		return shoppingProfileVO;
	}

	private static ShoppingProfile transformToRest(ShoppingProfileVO shoppingProfileVO) {
		if (shoppingProfileVO == null) {
			return null;
		}
		ShoppingProfile shoppingProfile = new ShoppingProfile();
		shoppingProfile.setAgentProfile(transformToRest(shoppingProfileVO.getAgentProfile()));
		shoppingProfile.setApplicationProfile(transformToRest(shoppingProfileVO.getApplicationProfile()));
		shoppingProfile.setUserProfile(transformToRest(shoppingProfileVO.getUserProfile()));
		return shoppingProfile;
	}

	private static UserProfileVO transformFromRest(UserProfile userProfile) {
		if (userProfile == null) {
			return null;
		}
		UserProfileVO userProfileVO = new UserProfileVO();
		userProfileVO.setChannelOrganizationInternalId(userProfile.getChannelOrganizationInternalId());
		userProfileVO.setChannelOrganizationNumber(userProfile.getChannelOrganizationNumber());
		userProfileVO.setChannelOrganizationTypeCd(userProfile.getChannelOrganizationTypeCd());
		userProfileVO.setLoginId(userProfile.getLoginId());
		userProfileVO.setOutletAssociatedProvinces(userProfile.getOutletAssociatedProvinces());
		userProfileVO.setSalesRepAssociatedOutlet(transformFromRestOutletList(userProfile.getSalesRepAssociatedOutlet()));
		userProfileVO.setSalesRepId(userProfile.getSalesRepId());
		userProfileVO.setSalesRepInternalId(userProfile.getSalesRepInternalId());
		userProfileVO.setSalesPersonRoleCd(userProfile.getSalesPersonRoleCd());
		return userProfileVO;
	}

	private static UserProfile transformToRest(UserProfileVO userProfileVO) {
		if (userProfileVO == null) {
			return null;
		}
		UserProfile userProfile = new UserProfile();
		userProfile.setChannelOrganizationInternalId(userProfileVO.getChannelOrganizationInternalId());
		userProfile.setChannelOrganizationNumber(userProfileVO.getChannelOrganizationNumber());
		userProfile.setChannelOrganizationTypeCd(userProfileVO.getChannelOrganizationTypeCd());
		userProfile.setLoginId(userProfileVO.getLoginId());
		userProfile.setOutletAssociatedProvinces(userProfileVO.getOutletAssociatedProvinces());
		userProfile.setSalesRepAssociatedOutlet(transformToRestOutletList(userProfileVO.getSalesRepAssociatedOutlet()));
		userProfile.setSalesRepId(userProfileVO.getSalesRepId());
		userProfile.setSalesRepInternalId(userProfileVO.getSalesRepInternalId());
		userProfile.setSalesPersonRoleCd(userProfileVO.getSalesPersonRoleCd());
		return userProfile;
	}

	private static ApplicationProfileVO transformFromRest(ApplicationProfile applicationProfile) {
		if (applicationProfile == null) {
			return null;
		}
		ApplicationProfileVO applicationProfileVO = new ApplicationProfileVO();
		applicationProfileVO.setOriginatorApplicationId(applicationProfile.getOriginatorApplicationId());
		return applicationProfileVO;
	}

	private static ApplicationProfile transformToRest(ApplicationProfileVO applicationProfileVO) {
		if (applicationProfileVO == null) {
			return null;
		}
		ApplicationProfile applicationProfile = new ApplicationProfile();
		applicationProfile.setOriginatorApplicationId(applicationProfileVO.getOriginatorApplicationId());
		return applicationProfile;
	}

	private static AgentProfileVO transformFromRest(AgentProfile agentProfile) {
		if (agentProfile == null) {
			return null;
		}
		AgentProfileVO agentProfileVO = new AgentProfileVO();
		agentProfileVO.setChannelOrganizationInternalId(agentProfile.getChannelOrganizationInternalId());
		agentProfileVO.setChannelOrganizationNumber(agentProfile.getChannelOrganizationNumber());
		agentProfileVO.setChannelOrganizationTypeCd(agentProfile.getChannelOrganizationTypeCd());
		agentProfileVO.setEmployeeId(agentProfile.getEmployeeId());
		agentProfileVO.setLoginId(agentProfile.getLoginId());
		agentProfileVO.setOutletAssociatedProvinces(agentProfile.getOutletAssociatedProvinces());
		agentProfileVO.setSalesRepAssociatedOutlet(transformFromRestOutletList(agentProfile.getSalesRepAssociatedOutlet()));
		agentProfileVO.setSalesRepId(agentProfile.getSalesRepId());
		agentProfileVO.setSalesRepInternalId(agentProfile.getSalesRepInternalId());
		agentProfileVO.setUserPrivilegeRoleCodes(agentProfile.getUserPrivilegeRoleCodes());
		return agentProfileVO;
	}

	private static AgentProfile transformToRest(AgentProfileVO agentProfileVO) {
		if (agentProfileVO == null) {
			return null;
		}
		AgentProfile agentProfile = new AgentProfile();
		agentProfile.setChannelOrganizationInternalId(agentProfileVO.getChannelOrganizationInternalId());
		agentProfile.setChannelOrganizationNumber(agentProfileVO.getChannelOrganizationNumber());
		agentProfile.setChannelOrganizationTypeCd(agentProfileVO.getChannelOrganizationTypeCd());
		agentProfile.setEmployeeId(agentProfileVO.getEmployeeId());
		agentProfile.setLoginId(agentProfileVO.getLoginId());
		agentProfile.setOutletAssociatedProvinces(agentProfileVO.getOutletAssociatedProvinces());
		agentProfile.setSalesRepAssociatedOutlet(transformToRestOutletList(agentProfileVO.getSalesRepAssociatedOutlet()));
		agentProfile.setSalesRepId(agentProfileVO.getSalesRepId());
		agentProfile.setSalesRepInternalId(agentProfileVO.getSalesRepInternalId());
		agentProfile.setUserPrivilegeRoleCodes(agentProfileVO.getUserPrivilegeRoleCodes());
		return agentProfile;
	}

	private static List<SalesRepAssociatedOutletVO> transformFromRestOutletList(List<SalesRepAssociatedOutlet> salesRepAssociatedOutlets) {
		if (salesRepAssociatedOutlets == null) {
			return null;
		}
		ArrayList<SalesRepAssociatedOutletVO> outletVOList = new ArrayList<SalesRepAssociatedOutletVO>();
		for(SalesRepAssociatedOutlet outlet: salesRepAssociatedOutlets) {
			outletVOList.add(transformFromRest(outlet));
		}
		return outletVOList;
	}

	private static List<SalesRepAssociatedOutlet> transformToRestOutletList(List<SalesRepAssociatedOutletVO> salesRepAssociatedOutletVOs) {
		if (salesRepAssociatedOutletVOs == null) {
			return null;
		}
		ArrayList<SalesRepAssociatedOutlet> outletList = new ArrayList<SalesRepAssociatedOutlet>();
		for(SalesRepAssociatedOutletVO outlet: salesRepAssociatedOutletVOs) {
			outletList.add(transformToRest(outlet));
		}
		return outletList;
	}

	private static SalesRepAssociatedOutletVO transformFromRest(SalesRepAssociatedOutlet outlet) {
		if (outlet == null) {
			return null;
		}
		SalesRepAssociatedOutletVO outletVO = new SalesRepAssociatedOutletVO();
		outletVO.setChannelOutletId(outlet.getChannelOutletId());
		outletVO.setOutletInternalId(outlet.getOutletInternalId());
		return outletVO;
	}

	private static SalesRepAssociatedOutlet transformToRest(SalesRepAssociatedOutletVO outletVO) {
		if (outletVO == null) {
			return null;
		}
		SalesRepAssociatedOutlet outlet = new SalesRepAssociatedOutlet();
		outlet.setChannelOutletId(outletVO.getChannelOutletId());
		outlet.setOutletInternalId(outletVO.getOutletInternalId());
		return outlet;
	}

	private static ServiceAddressVO transformFromRest(ServiceAddress serviceAddress) {
		if (serviceAddress == null) {
			return null;
		}
		ServiceAddressVO addressVO = new ServiceAddressVO();
		addressVO.setCityCode(serviceAddress.getCityCode());
		addressVO.setProvinceCode(serviceAddress.getProvinceCode());
		addressVO.setServiceAddressId(serviceAddress.getServiceAddressId());
		return addressVO;
	}

	private static ServiceAddress transformToRest(ServiceAddressVO serviceAddressVO) {
		if (serviceAddressVO == null) {
			return null;
		}
		ServiceAddress address = new ServiceAddress();
		address.setCityCode(serviceAddressVO.getCityCode());
		address.setProvinceCode(serviceAddressVO.getProvinceCode());
		address.setServiceAddressId(serviceAddressVO.getServiceAddressId());
		return address;
	}

	private static RelatedPartyRefVO transformFromRest(RelatedPartyRef relatedParty) {
		if (relatedParty == null) {
			return null;
		}
		RelatedPartyRefVO relatedPartyRefVO = new RelatedPartyRefVO();
		relatedPartyRefVO.setHref(relatedParty.getPartyId());
		relatedPartyRefVO.setName(relatedParty.getPartyName());
		relatedPartyRefVO.setRole(relatedParty.getRoleCd());
		return relatedPartyRefVO;
	}

	private static RelatedPartyRef transformToRest(RelatedPartyRefVO relatedPartyVO) {
		if (relatedPartyVO == null) {
			return null;
		}
		RelatedPartyRef relatedPartyRef = new RelatedPartyRef();
		relatedPartyRef.setPartyId(relatedPartyVO.getHref());
		relatedPartyRef.setPartyName(relatedPartyVO.getName());
		relatedPartyRef.setRoleCd(relatedPartyVO.getRole());
		return relatedPartyRef;
	}

	private static List<NoteVO> transformFromRestNoteList(List<Note> notes) {
		if (notes == null) {
			return null;
		}
		ArrayList<NoteVO> notesVo = new ArrayList<NoteVO>();
		for(Note nt: notes) {
			notesVo.add(transformFromRest(nt));
		}
		return notesVo;
	}

	private static List<Note> transformToRestNoteList(List<NoteVO> noteVOs) {
		if (noteVOs == null) {
			return null;
		}
		ArrayList<Note> notes = new ArrayList<Note>();
		for(NoteVO nt: noteVOs) {
			notes.add(transformToRest(nt));
		}
		return notes;
	}
	
	private static NoteVO transformFromRest(Note nt) {
		if (nt == null) {
			return null;
		}
		NoteVO noteVO = new NoteVO();
		noteVO.setAuthor(nt.getAuthorName());
		noteVO.setDate(nt.getCreateDate() != null? nt.getCreateDate().toDate(): null);
		noteVO.setText(nt.getNoteTxt());
		noteVO.setType(nt.getTypeCd());
		return noteVO;
	}
	
	private static Note transformToRest(NoteVO ntVO) {
		if (ntVO == null) {
			return null;
		}
		Note note = new Note();
		note.setAuthorName(ntVO.getAuthor());
		note.setCreateDate(ntVO.getDate() != null? new DateTime(ntVO.getDate()): null);
		note.setNoteTxt(ntVO.getText());
		note.setTypeCd(ntVO.getType());
		return note;
	}

	private static FulfillmentOptionTypeVO transformFromRest(FulfillmentOptionType fulfillmentOption) {
		if (fulfillmentOption == null) {
			return null;
		}
		FulfillmentOptionTypeVO fulfillmentOptionTypeVO = new FulfillmentOptionTypeVO();
		fulfillmentOptionTypeVO.setFulfillmentOptionTypeTxt(fulfillmentOption.getFulfillmentOptionTypeTxt());
		return fulfillmentOptionTypeVO;
	}

	private static FulfillmentOptionType transformToRest(FulfillmentOptionTypeVO fulfillmentOptionVO) {
		if (fulfillmentOptionVO == null) {
			return null;
		}
		FulfillmentOptionType fulfillmentOptionType = new FulfillmentOptionType();
		fulfillmentOptionType.setFulfillmentOptionTypeTxt(fulfillmentOptionVO.getFulfillmentOptionTypeTxt());
		return fulfillmentOptionType;
	}

	private static DepositAssessedTypeVO transformFromRest(DepositAssessedType depositAssessed) {
		if (depositAssessed == null) {
			return null;
		}
		DepositAssessedTypeVO depositAssessedTypeVO = new DepositAssessedTypeVO();
		depositAssessedTypeVO.setDepositAmt(depositAssessed.getDepositAmt());
		depositAssessedTypeVO.setInvoiceNumber(depositAssessed.getInvoiceNumber());
		return depositAssessedTypeVO;
	}

	private static DepositAssessedType transformToRest(DepositAssessedTypeVO depositAssessedVO) {
		if (depositAssessedVO == null) {
			return null;
		}
		DepositAssessedType depositAssessedType = new DepositAssessedType();
		depositAssessedType.setDepositAmt(depositAssessedVO.getDepositAmt());
		depositAssessedType.setInvoiceNumber(depositAssessedVO.getInvoiceNumber());
		return depositAssessedType;
	}
	
	private static TelephoneNumberType getToRest(TelephoneNumberTypeVO telephoneNumberVO) {
		if (telephoneNumberVO == null) {
			return null;
		}
		TelephoneNumberType telephoneNumber = new TelephoneNumberType();
		telephoneNumber.setPhoneNumber(telephoneNumberVO.getPhoneNumber());
		telephoneNumber.setPhoneNumberTypeCd(telephoneNumberVO.getPhoneNumberType());
		
		return telephoneNumber;
	}

	private static CustomerVO transformFromRest(Customer customer) {
		if (customer == null) {
			return null;
		}
		CustomerVO customerVO = new CustomerVO();
		customerVO.setCustomerId(customer.getCustomerId());
		return customerVO;
	}

	private static Customer transformToRest(CustomerVO customerVO) {
		if (customerVO == null) {
			return null;
		}
		Customer customer = new Customer();
		customer.setCustomerId(customerVO.getCustomerId());
		return customer;
	}

	private static BillingAccountVO transformFromRest(BillingAccount billingAccount) {
		if (billingAccount == null) {
			return null;
		}
		BillingAccountVO billingAccountVO = new BillingAccountVO();
		billingAccountVO.setAccountMasterSourceTypeCd(billingAccount.getAccountMasterSourceTypeCd());
		billingAccountVO.setAccountSubTypeCd(billingAccount.getAccountSubTypeCd());
		billingAccountVO.setAccountTypeCd(billingAccount.getAccountTypeCd());
		billingAccountVO.setBillingAccountNumber(billingAccount.getBillingAccountNumber());
		return billingAccountVO;
	}

	private static BillingAccount transformToRest(BillingAccountVO billingAccountVO) {
		if (billingAccountVO == null) {
			return null;
		}
		BillingAccount billingAccount = new BillingAccount();
		billingAccount.setAccountMasterSourceTypeCd(billingAccountVO.getAccountMasterSourceTypeCd());
		billingAccount.setAccountSubTypeCd(billingAccountVO.getAccountSubTypeCd());
		billingAccount.setAccountTypeCd(billingAccountVO.getAccountTypeCd());
		billingAccount.setBillingAccountNumber(billingAccountVO.getBillingAccountNumber());
		return billingAccount;
	}

	private static List<CartItemVO> transformFromRestCartItemList(List<CartItem> cartItems) {
		if (cartItems == null) {
			return null;
		}
		List<CartItemVO> cartItemVOList = new ArrayList<CartItemVO>();
		if(!cartItems.isEmpty()){
			for(CartItem cartItem : cartItems){
				CartItemTransformer cartItemTransformer = new CartItemTransformer();
				cartItemVOList.addAll(cartItemTransformer.transformToCartItemVO(cartItem));
			}
		}
		return cartItemVOList;
	}


	private static List<CartItem> transformToRestCartItemList(List<CartItemVO> cartItemVOs) {
		
		CartItemTransformer cartItemTransformer = new CartItemTransformer();
		if (cartItemVOs == null) {
			return null;
		}
		
		List<CartItem> cartItemList = new ArrayList<CartItem>();
		if(!cartItemVOs.isEmpty()){
			// First, sort the cart items in the following order:
			// 1. SalesOrder, 2. DisconnectOrder, 3. PerkOrderItem, 4. GiftWithPurchaseOrder
			Collections.sort(cartItemVOs, new ShoppingCartItemsComparator());
			for(CartItemVO cartItemVO : cartItemVOs){
				CartItem cartItem = new CartItem();

				if (cartItemVO.isSalesOrderItem())
				{
					CartItemSalesOrder cartItemSalesOrder = new CartItemSalesOrder();
					cartItem.setCartItemSalesOrder(cartItemSalesOrder);
					cartItemSalesOrder.setActionCd(cartItemVO.getAction());
					cartItemSalesOrder.setCartItemContextTypeList(cartItemVO.getCartItemContextTypeList());
					cartItemSalesOrder.setCartItemId(cartItemVO.getCartItemId());
					cartItemSalesOrder.setCartItemRelationId(cartItemVO.getCartItemRelationId());
					cartItemSalesOrder.setCartItemRelationship(cartItemTransformer.getCartItemRelationShipToRest(cartItemVO.getCartItemRelationship()));
					cartItemSalesOrder.setExistingServiceIdentifier(cartItemTransformer.getExistingServiceIdentifierToRest(cartItemVO.getExistingServiceIdentifier()));
					cartItemSalesOrder.setProductMarketOffering(cartItemTransformer.getProductMarketOfferingToRest(cartItemVO.getProductMarketOffering()));
					cartItemSalesOrder.setProduct(cartItemTransformer.getProductsToRest(cartItemVO.getProducts()));
					cartItemSalesOrder.setShipmentDetail(cartItemTransformer.getShipmentDetailToRest(cartItemVO.getShipmentDetail()));
					cartItemSalesOrder.setRelatedPromotionList(cartItemTransformer.getRelatedPromotionListToRest(cartItemVO.getRelatedPromotionList()));
					cartItemList.add(cartItem);
				} else if (cartItemVO.isDisconnectOrderItem()  ) {
					CartItemDisconnectOrder cartItemDisconnectOrder = new CartItemDisconnectOrder();
					cartItem.setCartItemDisconnectOrder(cartItemDisconnectOrder);
					cartItemDisconnectOrder.setActionCd(cartItemVO.getAction());
					cartItemDisconnectOrder.setCartItemContextTypeList(cartItemVO.getCartItemContextTypeList());
					cartItemDisconnectOrder.setCartItemId(cartItemVO.getCartItemId());
					cartItemDisconnectOrder.setCartItemRelationId(cartItemVO.getCartItemRelationId());
					cartItemDisconnectOrder.setCartItemRelationship(cartItemTransformer.getCartItemRelationShipToRest(cartItemVO.getCartItemRelationship()));
					cartItemDisconnectOrder.setExistingServiceIdentifier(cartItemTransformer.getExistingServiceIdentifierToRest(cartItemVO.getExistingServiceIdentifier()));
					cartItemDisconnectOrder.setDisconnectRequestList(getToRest(cartItemVO.getDisconnectRequestList()));
					cartItemList.add(cartItem);
				} else if (cartItemVO.isPerkOrderItem() ) {
					/*TODO:: Perk is moved under CartItemSalesOrder. This code needs to change accordingly. 
					CartItemSalesPerk cartItemSalesPerk = new CartItemSalesPerk();
					cartItem.setCartItemSalesPerk(cartItemSalesPerk);
					cartItemSalesPerk.setAction(cartItemVO.getAction());
					cartItemSalesPerk.setCartItemContextTypeList(cartItemVO.getCartItemContextTypeList());
					cartItemSalesPerk.setCartItemId(cartItemVO.getCartItemId());
					cartItemSalesPerk.setCartItemRelationId(cartItemVO.getCartItemRelationId());
					cartItemSalesPerk.setCartItemRelationship(CartItemTransformer.getCartItemRelationShipToRest(cartItemVO.getCartItemRelationship()));
					cartItemSalesPerk.setExistingServiceIdentifier(CartItemTransformer.getExistingServiceIdentifierToRest(cartItemVO.getExistingServiceIdentifier()));
					cartItemList.add(cartItem);
					*/
				}else if (cartItemVO.isGwpOrderItem() ) {
					CartItemGiftWithPurchase cartItemGwp = new CartItemGiftWithPurchase();
					cartItem.setCartItemGiftWithPurchase(cartItemGwp);
					cartItemGwp.setActionCd(cartItemVO.getAction());
					cartItemGwp.setCartItemContextTypeList(cartItemVO.getCartItemContextTypeList());
					cartItemGwp.setCartItemId(cartItemVO.getCartItemId());
					cartItemGwp.setCartItemRelationId(cartItemVO.getCartItemRelationId());
					cartItemGwp.setCartItemRelationship(cartItemTransformer.getCartItemRelationShipToRest(cartItemVO.getCartItemRelationship()));
					cartItemGwp.setExistingServiceIdentifier(cartItemTransformer.getExistingServiceIdentifierToRest(cartItemVO.getExistingServiceIdentifier()));
					cartItemGwp.setShipmentDetail(cartItemTransformer.getShipmentDetailToRest(cartItemVO.getShipmentDetail()));
					cartItemGwp.setSelectedAccessoryOffers(cartItemTransformer.getAccessoryOffersToRest(cartItemVO.getAccessoryOfferList()));
					cartItemGwp.setRelatedPromotionList(cartItemTransformer.getRelatedPromotionListToRest(cartItemVO.getRelatedPromotionList()));
					cartItemList.add(cartItem);
				}
			}
		}
		return cartItemList;
	}



	private static List<DisconnectRequestType> getToRest(List<DisconnectRequestTypeVO> disconnectRequestList) {
		
		List<DisconnectRequestType> disconnectRequestTypeList = new ArrayList<DisconnectRequestType>();
		for (DisconnectRequestTypeVO disconnectRequestTypeVO : disconnectRequestList) {
			DisconnectRequestType disconnectRequestType = new DisconnectRequestType();
			disconnectRequestType.setOriginalCarrierInfo(getToRest(disconnectRequestTypeVO.getOriginalCarrierInfo()));
			disconnectRequestType.setProductServiceType(disconnectRequestTypeVO.getProductServiceType());
			disconnectRequestType.setRequestedDisconnectDate(disconnectRequestTypeVO.getRequestedDisconnectDate() != null? new LocalDate(disconnectRequestTypeVO.getRequestedDisconnectDate()): null);
			disconnectRequestTypeList.add(disconnectRequestType);
		}
		return disconnectRequestTypeList;
	}

	private static OriginalCarrierInfoType getToRest(OriginalCarrierInfoTypeVO originalCarrierInfo) {
		OriginalCarrierInfoType originalCarrierInfoType = new OriginalCarrierInfoType();
		originalCarrierInfoType.setAccountNumber(originalCarrierInfo.getAccountNumber());
		originalCarrierInfoType.setCustomerName(getToRest(originalCarrierInfo.getCustomerName()));
		originalCarrierInfoType.setTelephoneNumber(getToRest(originalCarrierInfo.getTelephoneNumber()));
		originalCarrierInfoType.setDisconnectServiceAddress(getToRest(originalCarrierInfo.getDisconnectServiceAddress()));
		originalCarrierInfoType.setProviderId(originalCarrierInfo.getProviderId());
		originalCarrierInfoType.setResellerName(originalCarrierInfo.getReseller());
		return originalCarrierInfoType;
	}

	private static DisconnectServiceAddressType getToRest(DisconnectServiceAddressTypeVO disconnectServiceAddress) {
		DisconnectServiceAddressType disconnectServiceAddressType = null;
		if (disconnectServiceAddress!=null) {
			disconnectServiceAddressType = new DisconnectServiceAddressType();
			disconnectServiceAddressType.setFullAddress(getToRest(disconnectServiceAddress.getFullAddress()));
			disconnectServiceAddressType.setServiceAddressId(disconnectServiceAddress.getServiceAddressId());
		}
		return disconnectServiceAddressType;
	}

	

	private static Address getToRest(AddressVO fullAddress) {
		Address address = null; 
		if (fullAddress!=null) {
			address = new Address();
			address.setAddressId(fullAddress.getAddressId() == null? null: String.valueOf((fullAddress.getAddressId())));
			address.setStreetNumber(fullAddress.getStreetNumber());
			address.setStreetName(fullAddress.getStreetName());
			address.setMunicipalityName(fullAddress.getMunicipalityName());
			address.setProvinceStateCode(fullAddress.getProvinceStateCode());
			address.setPostalZipCode(fullAddress.getPostalZipCode());
		}
		return address;
	}

	private static ClientName getToRest(ClientNameVO customerName) {
		ClientName clientName = new ClientName();
		clientName.setFirstName(customerName.getFirstName());
		clientName.setLastName(customerName.getLastName());
		return clientName;
	}

	private static List<ContactMediumVO> transformFromRestContactMediumList(List<ContactMedium> contactMedium) {
		if (contactMedium == null) {
			return null;
		}
		ArrayList<ContactMediumVO> cmVoList = new ArrayList<ContactMediumVO>();
		for(ContactMedium cm: contactMedium) {
			cmVoList.add(transformFromRest(cm));
		}
		return cmVoList;
	}

	private static List<ContactMedium> transformToRestContactMediumList(List<ContactMediumVO> contactMediumVOs) {
		if (contactMediumVOs == null) {
			return null;
		}
		ArrayList<ContactMedium> cmList = new ArrayList<ContactMedium>();
		for(ContactMediumVO cm: contactMediumVOs) {
			cmList.add(transformToRest(cm));
		}
		return cmList;
	}

	private static ContactMediumVO transformFromRest(ContactMedium contactMedium) {
		if (contactMedium == null) {
			return null;
		}
		ContactMediumVO contactMediumVO = new ContactMediumVO();
		contactMediumVO.setCharacteristic(transformFromRest(contactMedium.getCharacteristic()));
		contactMediumVO.setContactType(contactMedium.getContactTypeCd());
		contactMediumVO.setPreferred(contactMedium.getPreferredInd());
		return contactMediumVO;
	}

	private static ContactMedium transformToRest(ContactMediumVO contactMediumVO) {		
		if (contactMediumVO == null) {
			return null;
		}
		ContactMedium contactMedium = new ContactMedium();
		contactMedium.setCharacteristic(transformToRest(contactMediumVO.getCharacteristic()));
		contactMedium.setContactTypeCd(contactMediumVO.getContactType());
		contactMedium.setPreferredInd(contactMediumVO.getPreferred());
		return contactMedium;
	}

	private static MediumCharacteristicVO transformFromRest(MediumCharacteristic characteristic) {
		if (characteristic == null) {
			return null;
		}
		MediumCharacteristicVO characteristicVO = new MediumCharacteristicVO();
		characteristicVO.setCity(characteristic.getCityName());
		characteristicVO.setCountry(characteristic.getCountryName());
		characteristicVO.setEmailAddress(characteristic.getEmailAddressId());
		characteristicVO.setFaxNumber(characteristic.getFaxNumber());
		characteristicVO.setPhoneNumber(characteristic.getPhoneNumber());
		characteristicVO.setStateOrProvince(characteristic.getStateOrProvinceCd());
		characteristicVO.setStreet1(characteristic.getStreetLineOneTxt());
		characteristicVO.setStreet2(characteristic.getStreetLineTwoTxt());
		characteristicVO.setType(characteristic.getTypeCd());
		return characteristicVO;
	}

	private static MediumCharacteristic transformToRest(MediumCharacteristicVO characteristicVO) {
		if (characteristicVO == null) {
			return null;
		}
		MediumCharacteristic characteristic = new MediumCharacteristic();
		characteristic.setCityName(characteristicVO.getCity());
		characteristic.setCountryName(characteristicVO.getCountry());
		characteristic.setEmailAddressId(characteristicVO.getEmailAddress());
		characteristic.setFaxNumber(characteristicVO.getFaxNumber());
		characteristic.setPhoneNumber(characteristicVO.getPhoneNumber());
		characteristic.setStateOrProvinceCd(characteristicVO.getStateOrProvince());
		characteristic.setStreetLineOneTxt(characteristicVO.getStreet1());
		characteristic.setStreetLineTwoTxt(characteristicVO.getStreet2());
		characteristic.setTypeCd(characteristicVO.getType());
		return characteristic;
	}

	public static OperationHeaderVO transformOperationHeaderFromRest(OperationHeader operationHeader) {
		OperationHeaderVO operationHeaderVO = new OperationHeaderVO();
		if(operationHeader!=null && !StringUtils.isBlank(operationHeader.getSalesTransactionId())){
				operationHeaderVO.setSalesTransactionId(operationHeader.getSalesTransactionId());			
		}
		return operationHeaderVO;
	}

}
