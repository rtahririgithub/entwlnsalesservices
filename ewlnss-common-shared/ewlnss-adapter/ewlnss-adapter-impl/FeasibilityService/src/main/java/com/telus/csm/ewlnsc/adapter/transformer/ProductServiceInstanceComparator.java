package com.telus.csm.ewlnsc.adapter.transformer;

import java.util.Comparator;


import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProductServiceInstance;

public class ProductServiceInstanceComparator implements Comparator<ProductServiceInstance>{

	@Override
	public int compare(ProductServiceInstance o1, ProductServiceInstance o2) {
		if(o1.getProductCatalogId()!=null && o2.getProductCatalogId()!=null){
			return Long.valueOf(o1.getProductCatalogId()).compareTo(Long.valueOf(o2.getProductCatalogId()));
		}			
		return 0;
	}

}
