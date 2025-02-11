package com.telus.csm.ewlnsc.helper;

import java.util.ArrayList;
import java.util.List;

import com.telus.tmi.xmlschema.xsd.customer.basetypes.base_types_2_0.ValueType;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.AtomicProductSpecificationCharacteristic;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.ProductSpecificationCharacteristicValue;

/**
 * @author Jose.Mena
 *
 */
public class AtomicProductSpecificationCharacteristicMapper {

	private AtomicProductSpecificationCharacteristic atomicProductSpecificationCharacteristic;
	private List<ProductSpecificationCharacteristicValue> productSpecificationCharacteristicValues; 
	
	public AtomicProductSpecificationCharacteristicMapper(AtomicProductSpecificationCharacteristic valueSpec111) {
		this.atomicProductSpecificationCharacteristic = valueSpec111;
		if(atomicProductSpecificationCharacteristic!=null 
				&& atomicProductSpecificationCharacteristic.getProductSpecificationCharacteristicValues()!=null
				&& !atomicProductSpecificationCharacteristic.getProductSpecificationCharacteristicValues().isEmpty()){
		    this.productSpecificationCharacteristicValues = atomicProductSpecificationCharacteristic.getProductSpecificationCharacteristicValues();
		}
	}
	
	public void finished() {
		 if(atomicProductSpecificationCharacteristic!=null && productSpecificationCharacteristicValues!=null && !productSpecificationCharacteristicValues.isEmpty()){
			 atomicProductSpecificationCharacteristic.getProductSpecificationCharacteristicValues();
			 atomicProductSpecificationCharacteristic.getProductSpecificationCharacteristicValues().addAll(productSpecificationCharacteristicValues);
		 }
	}
	
	public List<ProductSpecificationCharacteristicValue> getProductSpecificationCharacteristicValues() {
		if(productSpecificationCharacteristicValues==null){
			productSpecificationCharacteristicValues = new ArrayList<ProductSpecificationCharacteristicValue>();
		}
		return productSpecificationCharacteristicValues;
	}

	public void setName(String name) {
		atomicProductSpecificationCharacteristic.setName(name);
	}
	
	public void setValueType(String valueType) {
		atomicProductSpecificationCharacteristic.setValueType(convertToValueTypeFromString(valueType));
	}

	public ProductSpecificationCharacteristicValue newProductSpecificationCharacteristicValue() {
		ProductSpecificationCharacteristicValue pscv = new ProductSpecificationCharacteristicValue();
		getProductSpecificationCharacteristicValues().add(pscv);
		return pscv;
	}
	
	private ValueType convertToValueTypeFromString(String valueType) {
		return ValueType.fromValue(valueType);
	}
	
}
