package com.telus.csm.ewlnsc.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;

/**
 * ShoppingCartVO is to keep any enhancements outside the ShoppingCartBaseVO
 * which mimics the Rest Shopping Cart. If the Rest Shopping Cart is changed and
 * we want to add such item it has to go to ShoppingCartBaseVO
 *
 * @author x171813
 *
 */
public class ShoppingCartVO extends ShoppingCartBaseVO {
	private static final long serialVersionUID = 1L;
	private List<ExternalOrderDetail> externalOrderDetailList = Collections.synchronizedList(new ArrayList<ExternalOrderDetail>());
	private List<ShoppingCartValidationTraceVO> validationResultList;
	private boolean isValidShoppingCart;
	private String status;

	public List<ShoppingCartValidationTraceVO> getValidationResultList() {
		return validationResultList;
	}

	public void setValidationResultList(List<ShoppingCartValidationTraceVO> validationTraceList) {
		this.validationResultList = validationTraceList;
	}

	public boolean isValidShoppingCart() {
		return isValidShoppingCart;
	}

	public void setValidShoppingCart(boolean isValidShoppingCart) {
		this.isValidShoppingCart = isValidShoppingCart;
	}

	public String getCartType() {
		
		String cartType = null;

		//TODO extract to constants
		if (getBillingAccount()!=null && 
			EnterpriseWLNSalesServicesConstants.ENABLER_BILLING_ACCOUNT_SYSTEM_CODE.equals( getBillingAccount().getAccountMasterSourceTypeCd() ) ) {
			cartType = "ESCART_WLN";
		} else {
			
			//TODO extract to constants
			//wireless
			cartType = "ESCART_WLS";
		}
		return cartType;
	}

	public void setCartType(String cartType) {
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isCartContextTypeForAll() {
		
		boolean result = false;
		
		if ( getCartContextTypeList()!=null) {
			for( String cartContextType : getCartContextTypeList()  ) {
				if ( EnterpriseWLNSalesServicesConstants.SHOPPING_CART_CTX_TYPE_ALL.equalsIgnoreCase(cartContextType) ) {
					result = true;
					break;
				}
			}
		}
		return result;
	}

	/**
	 * Set all cartItems' status 
	 * 
	 * @param orderStatus
	 */
	public void setCartItemsStatus(String orderStatus) {
		
		if ( getCartItemList()!=null ) {
			
			for( CartItemVO cartItem: getCartItemList()) {
				cartItem.setStatus(orderStatus);
			}
		}
	}

	public boolean isWirelineProspectCustomer() {
		boolean result = false;
		
		if (getNote() != null) {
			for (NoteVO note : getNote()) {
				if ("CUSTOMER_TYPE".equalsIgnoreCase(note.getType()) &&
					"WIRELINE_PROSPECT".equalsIgnoreCase(note.getText())) {
					result = true;
				}
			}
		}
		
		return result;
	}
	
	// QC76752/QC79276 Change CREDIT_COMPLETED to CREDIT_BAN_COMPLETED
	public boolean isCustomerCreditCompleted() {
		boolean result = false;
		
		if (getNote() != null) {
			for (NoteVO note : getNote()) {
				if ("CREDIT_BAN_COMPLETED".equalsIgnoreCase(note.getType())) {
					result = Boolean.valueOf(note.getText());
				}
			}
		}
		
		return result;
	}

	public List<ExternalOrderDetail> getExternalOrderDetailList() {
		return externalOrderDetailList;
	}

	public void setExternalOrderDetailList(List<ExternalOrderDetail> externalOrderDetailList) {
		this.externalOrderDetailList = externalOrderDetailList;
	}
	
	public void addExternalOrderDetail(ExternalOrderDetail externalOrderDetail){
		this.externalOrderDetailList.add(externalOrderDetail);
	}

}
