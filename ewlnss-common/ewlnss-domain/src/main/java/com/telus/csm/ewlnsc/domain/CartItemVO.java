package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.List;

/**
 * To be changed to the abstract class. It will have specific implementations
 * derived from it.
 * 
 * @author x171813
 *
 */
public class CartItemVO implements Serializable, Comparable<CartItemVO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String action = null;
	private String cartItemId = null;
	private String cartItemRelationId = null;
	private List<SubscribedServiceVO> existingServiceIdentifier = null;
	private List<String> cartItemContextTypeList = null;
	private List<CartItemRelationshipVO> cartItemRelationship = null;

	private boolean salesOrderItem;
	private boolean perkOrderItem;
	private boolean disconnectOrderItem;
	private boolean gwpOrderItem;

	private ProductOfferingRefVO productMarketOffering = null;
	private ProductTypeVO products = null;
	private ShipmentDetailTypeVO shipmentDetail = null;
	private List<AccessoryOfferVO> accessoryOfferList = null;

	private List<DisconnectRequestTypeVO> disconnectRequestList = null;
	
	private String status;
	private List<RelatedImmediatePromotionVO> relatedPromotionList;

	public List<DisconnectRequestTypeVO> getDisconnectRequestList() {
		return disconnectRequestList;
	}

	public void setDisconnectRequestList(List<DisconnectRequestTypeVO> disconnectRequestList) {
		this.disconnectRequestList = disconnectRequestList;
	}

	public void setPerkOrderItem(boolean perkOrderItem) {
		this.perkOrderItem = perkOrderItem;
	}

	public void setDisconnectOrderItem(boolean disconnectOrderItem) {
		this.disconnectOrderItem = disconnectOrderItem;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(String cartItemId) {
		this.cartItemId = cartItemId;
	}

	public String getCartItemRelationId() {
		return cartItemRelationId;
	}

	public void setCartItemRelationId(String cartItemRelationId) {
		this.cartItemRelationId = cartItemRelationId;
	}

	public List<SubscribedServiceVO> getExistingServiceIdentifier() {
		return existingServiceIdentifier;
	}

	public void setExistingServiceIdentifier(List<SubscribedServiceVO> existingServiceIdentifier) {
		this.existingServiceIdentifier = existingServiceIdentifier;
	}

	public List<String> getCartItemContextTypeList() {
		return cartItemContextTypeList;
	}

	public void setCartItemContextTypeList(List<String> cartItemContextTypeList) {
		this.cartItemContextTypeList = cartItemContextTypeList;
	}

	public List<CartItemRelationshipVO> getCartItemRelationship() {
		return cartItemRelationship;
	}

	public void setCartItemRelationship(List<CartItemRelationshipVO> cartItemRelationship) {
		this.cartItemRelationship = cartItemRelationship;
	}

	public boolean isSalesOrderItem() {
		return salesOrderItem;
	}

	public boolean isPerkOrderItem() {
		return perkOrderItem;
	}

	public boolean isDisconnectOrderItem() {
		return disconnectOrderItem;
	}
	

	public boolean isGwpOrderItem() {
		return gwpOrderItem;
	}

	public void setGwpOrderItem(boolean gwpOrderItem) {
		this.gwpOrderItem = gwpOrderItem;
	}

	public void setSalesOrderItem(boolean salesOrderItem) {
		this.salesOrderItem = salesOrderItem;
	}
	
	public ProductOfferingRefVO getProductMarketOffering() {
		return productMarketOffering;
	}

	public void setProductMarketOffering(ProductOfferingRefVO productMarketOffering) {
		this.productMarketOffering = productMarketOffering;
	}

	public ProductTypeVO getProducts() {
		return products;
	}

	public void setProducts(ProductTypeVO products) {
		this.products = products;
	}

	public ShipmentDetailTypeVO getShipmentDetail() {
		return shipmentDetail;
	}

	public void setShipmentDetail(ShipmentDetailTypeVO shipmentDetail) {
		this.shipmentDetail = shipmentDetail;
	}

	public List<AccessoryOfferVO> getAccessoryOfferList() {
		return accessoryOfferList;
	}

	public void setAccessoryOfferList(List<AccessoryOfferVO> accessoryOfferList) {
		this.accessoryOfferList = accessoryOfferList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<RelatedImmediatePromotionVO> getRelatedPromotionList() {
		return relatedPromotionList;
	}

	public void setRelatedPromotionList(List<RelatedImmediatePromotionVO> relatedPromotionList) {
		this.relatedPromotionList = relatedPromotionList;
	}

	@Override
	//Facilitating sorting
	public int compareTo(CartItemVO o) {
		if ( cartItemId==null) {
			if ( o.cartItemId==null) {
				return compareRelationId( o );
			} else {
				return -1 ;
			}
		} else {
			if ( o.cartItemId!=null ) {
				int result = cartItemId.compareTo( o.cartItemId ) ;
				if ( result==0) {
					return compareRelationId( o );
				} else {
					return result;
				}
			} else {
				return 1;
			}
		}
	}
	//Facilitating sorting - second level
	private int compareRelationId( CartItemVO o ) {
		if ( cartItemRelationId==null) {
			if ( o.cartItemRelationId==null) {
				return 0;
			} else {
				return -1 ;
			}
		} else {
			if ( o.cartItemRelationId!=null ) {
				return cartItemRelationId.compareTo( o.cartItemRelationId ) ;
			} else {
				return 1;
			}
		}
	}

	public String toIdentityString() {
		return "CartItem (id=" + cartItemId + ", relationId=" + cartItemRelationId + ", status=" + status+ ")";
	}

}
