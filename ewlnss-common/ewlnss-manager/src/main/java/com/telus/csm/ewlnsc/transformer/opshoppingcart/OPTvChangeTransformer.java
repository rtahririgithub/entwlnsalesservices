package com.telus.csm.ewlnsc.transformer.opshoppingcart;

import com.telus.csm.ewlnsc.adapter.woscs.domain.Product;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductOrderItem;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;

public class OPTvChangeTransformer extends OPTvProvideTransformer {

	public static ProductOrderItem transformChange(OperationHeader operationHeader, CartItemVO cartItemVO, ShoppingCartContextVO shoppingCartContextVO) {
		ProductOrderItem result = transform(operationHeader, cartItemVO, shoppingCartContextVO);

		Product product = result.getProduct();
		com.telus.csm.ewlnsc.adapter.oqs.domain.Product existingProd = findExistingProduct(shoppingCartContextVO, EnterpriseWLNSalesServicesConstants.TTV);
		if (existingProd != null) {
			product.setProductSerialNumber(existingProd.getProductSerialNumber());
		}
		
		// explicit product remove
		if(cartItemVO.getProducts()!=null && cartItemVO.getProducts().getTelevisionProduct().getAdditionalProductItemList() != null){
			explicitProductRemoval(cartItemVO.getProducts().getTelevisionProduct().getAdditionalProductItemList(), shoppingCartContextVO, product);
		}
		
		explicitAddonRemoval(product, existingProd, cartItemVO.getProducts().getTelevisionProduct().getAddOns());
		
		// implicit product or orphaned discount removal
		implicitProductOrOrphanedDiscountRemoval(shoppingCartContextVO, product, EnterpriseWLNSalesServicesConstants.TTV);

		// explicit discount remove is DONE as part of addProductOfferings
		// if the action type is REMOVE

		// implicit discount removal
		implicitDiscountRemoval(shoppingCartContextVO, product);
			
		//Keepcommitment
		setKeepcommitment(shoppingCartContextVO, product, EnterpriseWLNSalesServicesConstants.TTV, EnterpriseWLNSalesServicesConstants.COMP_PRODUCT_INFORMATION);
		
		return result;
	}

}
