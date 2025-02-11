package com.telus.csm.ewlnsc.domain;

import com.telus.csm.ewlnsc.grid.domain.CatalogueItemDO;

public class ProductSummaryVO {
     
	private ProductComponentVO productComponent; 
	private String name;
	private String type;
	private String subType;
	private String externalReference;
	private String parentExternalReference;
	public final static String INTERNET_PRODUCT = "HSIC";
	public final static String TTV_PRODUCT = "TTV_MAIN_PRODUCT";
	public final static String HOME_PHONE_PRODUCT = "HOME_PHONE_MAIN_PRODUCT";
	
	public final static String SWEETENER = "SWEETNER";
	public final static String DISCOUNT = "DISCOUNT";
	public final static String ADD_ON = "ADD_ON";
	public final static String MAIN = "MAIN";
	public static final String EQUIPMENT = "EQUIPMENT";
	
	public ProductSummaryVO(ProductComponentVO productComponent, String string) {
		// TODO Auto-generated constructor stub
	}

	public ProductSummaryVO(ProductComponentVO productComponent, String name, String type, String subType) {
		this.productComponent = productComponent;
		this.name = name;
		this.type =type;
		this.subType = subType;
	}
	
	public ProductComponentVO getProductComponent() {
		return productComponent;
	}

	public void setProductComponent(ProductComponentVO productComponent) {
		this.productComponent = productComponent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getProductCatalogId(){
		if (productComponent != null) {
			return productComponent.getProductCatalogueId();
		}
		return null;
	}
	public String getParentProductCatalogId(){
		if (productComponent != null) {
			return productComponent.getParentProductCatalogueId();
		}	
		return null;
	}
	
	public boolean isIntenetProduct() {
		return INTERNET_PRODUCT.equalsIgnoreCase(type);
	}
	
	public boolean isTTVProduct() {
		return TTV_PRODUCT.equalsIgnoreCase(type);
	}
	
	public boolean isHomePhoneProduct() {
		return HOME_PHONE_PRODUCT.equalsIgnoreCase(type);
	}
	
	public boolean isSweetener() {
		return SWEETENER.equalsIgnoreCase(subType);
	}
	public boolean isDiscount() {
		return DISCOUNT.equalsIgnoreCase(subType);
	}
	public boolean isAddOn() {
		return ADD_ON.equalsIgnoreCase(subType);
	}
	public boolean isMainProduct() {
		return MAIN.equalsIgnoreCase(subType);
	}
	public boolean isEquipment() {
		return EQUIPMENT.equalsIgnoreCase(subType);
	}	
	
	public String getExternalReference() {
		
		return externalReference;
	}	
}
