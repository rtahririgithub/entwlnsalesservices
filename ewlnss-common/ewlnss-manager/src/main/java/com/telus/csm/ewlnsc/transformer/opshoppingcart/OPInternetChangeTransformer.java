package com.telus.csm.ewlnsc.transformer.opshoppingcart;

import com.telus.csm.ewlnsc.adapter.woscs.domain.Product;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductOrderItem;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;

public class OPInternetChangeTransformer extends OPInternetProvideTransformer {

	public static ProductOrderItem transformChange(OperationHeader operationHeader, CartItemVO cartItemVO, ShoppingCartContextVO shoppingCartContextVO) {
		ProductOrderItem result = transformProvide(operationHeader, cartItemVO, shoppingCartContextVO);

		Product product = result.getProduct();
		com.telus.csm.ewlnsc.adapter.oqs.domain.Product existingProd = findExistingProduct(shoppingCartContextVO, EnterpriseWLNSalesServicesConstants.HSIC);
		if (existingProd != null) {
			product.setProductSerialNumber(existingProd.getProductSerialNumber());
		}
		
		// explicit product remove
		if(cartItemVO.getProducts()!=null &&  cartItemVO.getProducts().getInternetProduct().getAdditionalProductItemList() != null){
			explicitProductRemoval(cartItemVO.getProducts().getInternetProduct().getAdditionalProductItemList(), shoppingCartContextVO, product);
		}
				
		explicitAddonRemoval(product, existingProd, cartItemVO.getProducts().getInternetProduct().getAddOns());
		
		// implicit product or orphaned discount removal
		implicitProductOrOrphanedDiscountRemoval(shoppingCartContextVO, product, EnterpriseWLNSalesServicesConstants.HSIC);

		// explicit discount remove is DONE as part of addProductOfferings
		// if the action type is REMOVE

		// implicit discount removal
		implicitDiscountRemoval(shoppingCartContextVO, product);
		
		//Keepcommitment
		setKeepcommitment(shoppingCartContextVO, product, EnterpriseWLNSalesServicesConstants.HSIC, Constants.HS_PACK);
	
		return result;
	}

}
