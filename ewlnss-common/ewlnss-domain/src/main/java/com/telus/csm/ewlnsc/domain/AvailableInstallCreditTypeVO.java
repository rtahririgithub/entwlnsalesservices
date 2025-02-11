package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public class AvailableInstallCreditTypeVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String productType = null;
	private FFHSweetenerTypeVO sweetener = null;

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public FFHSweetenerTypeVO getSweetener() {
		return sweetener;
	}

	public void setSweetener(FFHSweetenerTypeVO sweetener) {
		this.sweetener = sweetener;
	}
	

}
