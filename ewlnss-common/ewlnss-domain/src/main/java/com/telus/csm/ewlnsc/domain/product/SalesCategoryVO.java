package com.telus.csm.ewlnsc.domain.product;

import java.util.List;
import com.telus.csm.ess.common.domain.DescriptionVO;

public class SalesCategoryVO {
	//NWLN-7789
	private String salesCategoryCode;
	private List<DescriptionVO> salesCategoryNameList;
	
		public String getSalesCategoryCode() {
		return salesCategoryCode;
	}
	public void setSalesCategoryCode(String salesCategoryCode) {
		this.salesCategoryCode = salesCategoryCode;
	}
	public List<DescriptionVO> getSalesCategoryNameList() {
		return salesCategoryNameList;
	}
	public void setSalesCategoryNameList(List<DescriptionVO> salesCategoryNameList) {
		this.salesCategoryNameList = salesCategoryNameList;
	}
}
