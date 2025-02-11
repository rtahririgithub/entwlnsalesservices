package com.telus.csm.ewlnsc.domain;

public class ShoppingCartChange {
	
	private ProductSummaryVO product; 
	private String changeType;
	
	private String externalReferenceId;
	

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("product name:").append((product!=null)?product.getName():"EMPTY");
		buffer.append("product catalog id:").append((product!=null & product.getProductCatalogId()!= null)?product.getProductCatalogId():"EMPTY");
		buffer.append("product parent catalog id:").append((product!=null & product.getParentProductCatalogId()!= null)?product.getParentProductCatalogId():"EMPTY");
		return buffer.toString();
	}
	
	public String getExternalReferenceId() {
		return externalReferenceId;
	}

	public void setExternalReferenceId(String externalReferenceId) {
		this.externalReferenceId = externalReferenceId;
	}

	public void setProduct(ProductSummaryVO product) {
		this.product = product;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public ShoppingCartChange(ProductSummaryVO product, String changeType) {
		this.product = product;
		this.changeType = changeType;
	}

	public ProductSummaryVO getProduct() {
		return product;
	}

	public String getChangeType() {
		return changeType;
	}
	
	
}
