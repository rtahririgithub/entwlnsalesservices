package com.telus.csm.ewlnsc.adapter.transformer;

import java.util.Comparator;

import org.apache.commons.lang3.StringUtils;

import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.ProductSpecificationCharacteristic;

public class ProductSpecificationCharacteristicComparator implements Comparator<ProductSpecificationCharacteristic>{

	@Override
	public int compare(ProductSpecificationCharacteristic o1, ProductSpecificationCharacteristic o2) {
		if(StringUtils.isNotBlank(o1.getName()) && StringUtils.isNotBlank(o2.getName())){
			return o1.getName().compareToIgnoreCase(o2.getName());
		}
		return 0;
	}

}
