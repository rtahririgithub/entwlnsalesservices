/**
 * 
 */
package com.telus.csm.ewlnsc.domain.price;

import java.util.List;

import com.telus.csm.ewlnsc.domain.CoreResponseBase;
import com.telus.csm.ewlnsc.domain.MoneyVO;

/**
 * @author x145592
 *
 */
public class PriceResponseVO extends CoreResponseBase {

	private List<CartItemPriceVO> cartItemPrice = null;
	private List<DepositVO> deposit = null;
	private CombinedPriceVO totalPrice = null;

	public List<CartItemPriceVO> getCartItemPrice() {
		return cartItemPrice;
	}

	public void setCartItemPrice(List<CartItemPriceVO> cartItemPrice) {
		this.cartItemPrice = cartItemPrice;
	}

	public List<DepositVO> getDeposit() {
		return deposit;
	}

	public void setDeposit(List<DepositVO> deposit) {
		this.deposit = deposit;
	}

	public CombinedPriceVO getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(CombinedPriceVO totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public MoneyVO getBaseMonthlyCharge() {
		if (totalPrice == null || totalPrice.getBillPrice() == null || totalPrice.getBillPrice().isEmpty()){
			return null;
		} else {
			return totalPrice.getBillPrice().get(0).getPriceCharge().getBasePriceAmount();
		}
	}

}
