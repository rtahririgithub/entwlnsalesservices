package com.telus.csm.ewlnsc.helper;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHDiscountTypeVO;
import com.telus.csm.ewlnsc.domain.FFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.FFHProductPlanAddOnTypeVO;
import com.telus.csm.ewlnsc.domain.FFHSweetenerTypeVO;
import com.telus.csm.ewlnsc.domain.HomePhoneProductTypeVO;
import com.telus.csm.ewlnsc.domain.InternetProductTypeVO;
import com.telus.csm.ewlnsc.domain.ProductComponentVO;
import com.telus.csm.ewlnsc.domain.ProductSummaryVO;
import com.telus.csm.ewlnsc.domain.ProductTypeVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.TelevisionProductTypeVO;

public class ShoppingCartComparator {

	public static ShoppingCartComparatorResult compare(ShoppingCartVO fromShoppingCart, ShoppingCartVO toShoppingCart){
		List<ProductSummaryVO> productsFromSC1 =  getProductSummary(fromShoppingCart);
		List<ProductSummaryVO> productsFromSC2 =  getProductSummary(toShoppingCart);
		ShoppingCartComparatorResult comparisonResult = new ShoppingCartComparatorResult();
		for (ProductSummaryVO product : productsFromSC1) {
			if (!find(product, productsFromSC2)){
				comparisonResult.addShoppingCartChange(product, "REMOVE");
			}
		}
		for (ProductSummaryVO product : productsFromSC2) {
			if (!find(product, productsFromSC1)){
				comparisonResult.addShoppingCartChange(product, "ADD");
			}
		}
		return comparisonResult;
	}

	private static boolean find(ProductSummaryVO product, List<ProductSummaryVO> productsFromSC2) {
		if (product != null && productsFromSC2 != null) {
			for (ProductSummaryVO p : productsFromSC2) {
				if (p.getProductComponent().equals(product.getProductComponent())) return true;
			}
		}
		return false;
	}

		
	private static List<ProductSummaryVO> getProductSummary(ShoppingCartVO shoppingCart) {
		List<ProductSummaryVO> productSummaryList = new ArrayList<ProductSummaryVO>();
		if (shoppingCart != null && shoppingCart.getCartItemList() != null) {
			for (CartItemVO cartItem : shoppingCart.getCartItemList()) {
				productSummaryList.addAll(getProductsSummay(cartItem));
		    }
		}
		return productSummaryList;
	}	
	
	private static List<ProductSummaryVO> getProductsSummay(CartItemVO cartItem) {
		return getProductsSummary(cartItem.getProducts());
	}
	
	private static List<ProductSummaryVO> getProductsSummary(ProductTypeVO product) {
		List<ProductSummaryVO> productsSummaryList = new ArrayList<ProductSummaryVO>();
		if (product.getInternetProduct() != null) {
			List<ProductSummaryVO> internetProductsSummary =  getProductsSummary(product.getInternetProduct());
			productsSummaryList.addAll(internetProductsSummary);
		}
		if (product.getTelevisionProduct() != null) {
			List<ProductSummaryVO> televisionProductsSummary = getProductsSummary(product.getTelevisionProduct());
			productsSummaryList.addAll(televisionProductsSummary);
		}
		if (product.getHomePhoneProduct() != null) {
			List<ProductSummaryVO> homePhoneProductsSummary =  getProductsSummary(product.getHomePhoneProduct());
			productsSummaryList.addAll(homePhoneProductsSummary);
		}
		return productsSummaryList;
	  }
	
	public static List<ProductSummaryVO> getProductsSummary(InternetProductTypeVO internetProduct) {
		
		List<ProductSummaryVO> productsSummary = new  ArrayList<ProductSummaryVO>(); 
		
		//
		productsSummary.add(new ProductSummaryVO(internetProduct.getProductComponents().get(0), "HSIC", ProductSummaryVO.INTERNET_PRODUCT, ProductSummaryVO.MAIN));
		
		
		if (internetProduct.getDiscounts() != null) {
			for (FFHDiscountTypeVO discount : internetProduct.getDiscounts()){
				for (ProductComponentVO productComponent : discount.getProductCatalogueIdentifiers()){
					productsSummary.add(new ProductSummaryVO(productComponent, discount.getDiscountCode(), ProductSummaryVO.INTERNET_PRODUCT, ProductSummaryVO.DISCOUNT));	
				}
			}
		}
		
		if (internetProduct.getEquipments() != null) {
			for (FFHEquipmentTypeVO equipment : internetProduct.getEquipments()){
					productsSummary.add(new ProductSummaryVO(equipment.getProductCatalogueIdentifier(), "", ProductSummaryVO.INTERNET_PRODUCT, ProductSummaryVO.EQUIPMENT));	
			}
		}
		
		if (internetProduct.getSweeteners() != null) {
			for (FFHSweetenerTypeVO sweetener : internetProduct.getSweeteners()){
				for (FFHDiscountTypeVO discount : sweetener.getDiscounts()){
					for (ProductComponentVO productComponent : discount.getProductCatalogueIdentifiers()){
						productsSummary.add(new ProductSummaryVO(productComponent, sweetener.getOfferHeader().getOfferCode(),ProductSummaryVO.INTERNET_PRODUCT, ProductSummaryVO.SWEETENER));	
					}
				}
			}
		}
		if (internetProduct.getAddOns() != null) {
			for (FFHProductPlanAddOnTypeVO addOn : internetProduct.getAddOns()){
						productsSummary.add(new ProductSummaryVO(addOn.getProductCatalogueIdentifier(), "AddOn", ProductSummaryVO.INTERNET_PRODUCT, ProductSummaryVO.ADD_ON));	
			}
		}
		
		return productsSummary;
	}	
    
	/**
	 * 
	 * @param product
	 * @return
	 */
	public static List<ProductSummaryVO> getProductsSummary(TelevisionProductTypeVO product) {
		
		List<ProductSummaryVO> productsSummary = new  ArrayList<ProductSummaryVO>(); 
		
		//
		productsSummary.add(new ProductSummaryVO(product.getProductComponents().get(0), "HSIC", ProductSummaryVO.INTERNET_PRODUCT, ProductSummaryVO.MAIN));
		
		
		if (product.getDiscounts() != null) {
			for (FFHDiscountTypeVO discount : product.getDiscounts()){
				for (ProductComponentVO productComponent : discount.getProductCatalogueIdentifiers()){
					productsSummary.add(new ProductSummaryVO(productComponent, discount.getDiscountCode(), ProductSummaryVO.INTERNET_PRODUCT, ProductSummaryVO.DISCOUNT));	
				}
			}
		}
		
		if (product.getEquipments() != null) {
			for (FFHEquipmentTypeVO equipment : product.getEquipments()){
					productsSummary.add(new ProductSummaryVO(equipment.getProductCatalogueIdentifier(), "", ProductSummaryVO.INTERNET_PRODUCT, ProductSummaryVO.EQUIPMENT));	
			}
		}
		
		if (product.getSweeteners() != null) {
			for (FFHSweetenerTypeVO sweetener : product.getSweeteners()){
				for (FFHDiscountTypeVO discount : sweetener.getDiscounts()){
					for (ProductComponentVO productComponent : discount.getProductCatalogueIdentifiers()){
						productsSummary.add(new ProductSummaryVO(productComponent, sweetener.getOfferHeader().getOfferCode(),ProductSummaryVO.INTERNET_PRODUCT, ProductSummaryVO.SWEETENER));	
					}
				}
			}
		}
		if (product.getAddOns() != null) {
			for (FFHProductPlanAddOnTypeVO addOn : product.getAddOns()){
						productsSummary.add(new ProductSummaryVO(addOn.getProductCatalogueIdentifier(), "AddOn", ProductSummaryVO.INTERNET_PRODUCT, ProductSummaryVO.ADD_ON));	
			}
		}
		
		return productsSummary;
	}	
	/**
	 * 
	 * @param product
	 * @return
	 */
	public static List<ProductSummaryVO> getProductsSummary(HomePhoneProductTypeVO product) {
		
		List<ProductSummaryVO> productsSummary = new  ArrayList<ProductSummaryVO>(); 
		
		//
		productsSummary.add(new ProductSummaryVO(product.getProductComponents().get(0), "HSIC", ProductSummaryVO.INTERNET_PRODUCT, ProductSummaryVO.MAIN));
		
		
		if (product.getDiscounts() != null) {
			for (FFHDiscountTypeVO discount : product.getDiscounts()){
				for (ProductComponentVO productComponent : discount.getProductCatalogueIdentifiers()){
					productsSummary.add(new ProductSummaryVO(productComponent, discount.getDiscountCode(), ProductSummaryVO.INTERNET_PRODUCT, ProductSummaryVO.DISCOUNT));	
				}
			}
		}
		
		if (product.getEquipments() != null) {
			for (FFHEquipmentTypeVO equipment : product.getEquipments()){
					productsSummary.add(new ProductSummaryVO(equipment.getProductCatalogueIdentifier(), "", ProductSummaryVO.INTERNET_PRODUCT, ProductSummaryVO.EQUIPMENT));	
			}
		}
		
		if (product.getSweeteners() != null) {
			for (FFHSweetenerTypeVO sweetener : product.getSweeteners()){
				for (FFHDiscountTypeVO discount : sweetener.getDiscounts()){
					for (ProductComponentVO productComponent : discount.getProductCatalogueIdentifiers()){
						productsSummary.add(new ProductSummaryVO(productComponent, sweetener.getOfferHeader().getOfferCode(),ProductSummaryVO.INTERNET_PRODUCT, ProductSummaryVO.SWEETENER));	
					}
				}
			}
		}
		if (product.getAddOns() != null) {
			for (FFHProductPlanAddOnTypeVO addOn : product.getAddOns()){
						productsSummary.add(new ProductSummaryVO(addOn.getProductCatalogueIdentifier(), "AddOn", ProductSummaryVO.INTERNET_PRODUCT, ProductSummaryVO.ADD_ON));	
			}
		}
		
		return productsSummary;
	}	
}
