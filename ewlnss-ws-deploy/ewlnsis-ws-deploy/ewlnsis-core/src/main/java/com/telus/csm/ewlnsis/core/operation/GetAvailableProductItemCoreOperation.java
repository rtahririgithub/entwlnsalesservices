package com.telus.csm.ewlnsis.core.operation;
 
import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.delegate.FIFAGetAvailableProductItemDelegate;
import com.telus.csm.ewlnsc.delegate.GetAvailableProductItemDelegate;
import com.telus.csm.ewlnsc.delegate.ShoppingCartDelegate;
import com.telus.csm.ewlnsc.delegate.ShoppingCartDelegateV2;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHOfferHeaderVO;
import com.telus.csm.ewlnsc.domain.GetAvailableProductItemDelegateResponse;
import com.telus.csm.ewlnsc.domain.HomePhoneProductTypeVO;
import com.telus.csm.ewlnsc.domain.ProductOfferingRefVO;
import com.telus.csm.ewlnsc.domain.ProductTypeVO;
import com.telus.csm.ewlnsc.domain.SalesRepAssociatedOutletVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingProfileVO;
import com.telus.csm.ewlnsc.domain.UserProfileVO;
import com.telus.csm.ewlnsc.helper.ShoppingCartContextHelper;

public class GetAvailableProductItemCoreOperation {
	
	String shoppingCartId;
	
	/*****************************************/
	/* execute                               */
	/*****************************************/
	public GetAvailableProductItemDelegateResponse execute(String transactionId, String shoppingCartId){
		this.shoppingCartId = shoppingCartId;
		
		ShoppingCartContextVO shoppingCartVO = this.getShoppingCartByShoppingCartId();

		return (new GetAvailableProductItemDelegate()).execute(transactionId, shoppingCartVO);
	}
	
	/*****************************************/
	/* execute - fifa flow                               */
	/*****************************************/
	public GetAvailableProductItemDelegateResponse execute(String transactionId, String shoppingCartId, String commerceCartitemId ){
		this.shoppingCartId = shoppingCartId;

		
		//ShoppingCartVO shoppingCartVO = mockCompassSC();
		ShoppingCartVO shoppingCartVO =  ShoppingCartDelegateV2.getInstance().getShoppingCart( transactionId, shoppingCartId ,commerceCartitemId  );
		ShoppingCartContextVO shoppingCartContextVO =new ShoppingCartContextVO(shoppingCartVO);
		
		return (new FIFAGetAvailableProductItemDelegate()).execute(transactionId, shoppingCartContextVO);
	}
	
	

	/*****************************************/
	/* getShoppingCartByShoppingCartId       */
	/*****************************************/
	private ShoppingCartContextVO getShoppingCartByShoppingCartId(){
		
		ShoppingCartContextHelper contextHelper = new ShoppingCartContextHelper();
		ShoppingCartContextVO result = contextHelper.execute(shoppingCartId);
		return result;
	}
	
	private ShoppingCartVO mockCompassSC() {
		ShoppingCartVO scvo = new ShoppingCartVO();
		scvo.setShoppingProfile( new ShoppingProfileVO());
		scvo.getShoppingProfile().setUserProfile(new UserProfileVO());
		List<SalesRepAssociatedOutletVO> ss = new ArrayList<SalesRepAssociatedOutletVO>();
		ss.add( new SalesRepAssociatedOutletVO());
		scvo.getShoppingProfile().getUserProfile().setSalesRepAssociatedOutlet(ss);
		scvo.getShoppingProfile().getUserProfile().getSalesRepAssociatedOutlet().get(0).setChannelOutletId("0000029199");//channel
		
		scvo.setServiceAddress( new ServiceAddressVO());
		scvo.getServiceAddress().setServiceAddressId("3647833");
		// cartvo.getCartItemList().get(0)
		//cartItemVO.getProductMarketOffering().getOfferHeader().getOfferId()
		

		List<CartItemVO> cartList = new ArrayList<CartItemVO>();
		scvo.setCartItemList(cartList);
		CartItemVO item = new CartItemVO();
		cartList.add(item);
		ProductOfferingRefVO offings = new ProductOfferingRefVO();
		FFHOfferHeaderVO header = new FFHOfferHeaderVO();
		offings.setOfferHeader( header );
		header.setOfferId("9156299503913068809");
		item.setProductMarketOffering(offings);
		
		ProductTypeVO prod = new ProductTypeVO();
		item.setProducts(prod);
		HomePhoneProductTypeVO homePhone =new HomePhoneProductTypeVO();
		homePhone.setProductTypeCd("SING");
		prod.setHomePhoneProduct(homePhone);
		return scvo;
	}
}
