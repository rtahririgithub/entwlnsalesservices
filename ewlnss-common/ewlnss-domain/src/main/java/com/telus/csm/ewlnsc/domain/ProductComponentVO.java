package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public class ProductComponentVO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private String action = null;

	private String productCatalogueId = null;

	private String parentProductCatalogueId = null;

	/**
	 * Get productCatalogueId
	 * 
	 * @return productCatalogueId
	 **/

	public String getProductCatalogueId() {
		return productCatalogueId;
	}

	public void setProductCatalogueId(String productCatalogueId) {
		this.productCatalogueId = productCatalogueId;
	}

	/**
	 * Get parentProductCatalogueId
	 * 
	 * @return parentProductCatalogueId
	 **/
	public String getParentProductCatalogueId() {
		return parentProductCatalogueId;
	}

	public void setParentProductCatalogueId(String parentProductCatalogueId) {
		this.parentProductCatalogueId = parentProductCatalogueId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public boolean equals(Object componentVO) {
		if (componentVO == null) {
			return false;
		} else {
			ProductComponentVO productComponentVO = (ProductComponentVO) componentVO;

			if (this.parentProductCatalogueId == null && productComponentVO.getParentProductCatalogueId() != null) {
				return false;
			}
			if (this.parentProductCatalogueId != null && productComponentVO.getParentProductCatalogueId() == null) {
				return false;
			}
			if (this.productCatalogueId == null && productComponentVO.getProductCatalogueId() != null) {
				return false;
			}
			if (this.productCatalogueId != null && productComponentVO.getProductCatalogueId() == null) {
				return false;
			} else {
				return (productCatalogueId.equalsIgnoreCase(productComponentVO.getProductCatalogueId())
						&& parentProductCatalogueId.equalsIgnoreCase(productComponentVO.getParentProductCatalogueId()));
			}
		}
	}

}
