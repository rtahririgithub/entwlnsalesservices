package com.telus.csm.ewlnsc.helper;

import java.util.ArrayList;
import java.util.List;

import com.telus.tmi.xmlschema.xsd.customer.basetypes.base_types_2_0.ValueType;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.CompositeProductSpecificationCharacteristicValue;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.ProductSpecificationCharacteristic;

/**
 * @author Jose.Mena
 *
 */
public class ProductSpecificationCharacteristicMapper {

	private ProductSpecificationCharacteristic productSpecificationCharacteristic;
	private List<CompositeProductSpecificationCharacteristicValue> productSpecificationCharacteristicValues;
	
	public ProductSpecificationCharacteristicMapper(ProductSpecificationCharacteristic prodSpecCharac1) {
		this.productSpecificationCharacteristic = prodSpecCharac1;
		if (productSpecificationCharacteristic != null 
				&& productSpecificationCharacteristic.getProductSpecificationCharacteristicValues() != null
				&& !productSpecificationCharacteristic.getProductSpecificationCharacteristicValues().isEmpty()) {
			this.productSpecificationCharacteristicValues = productSpecificationCharacteristic.getProductSpecificationCharacteristicValues();
		}
	}

	public void finished() {
		if(productSpecificationCharacteristic!=null 
				&& productSpecificationCharacteristicValues!=null 
				&& !productSpecificationCharacteristicValues.isEmpty()){
		    productSpecificationCharacteristic.getProductSpecificationCharacteristicValues().addAll(productSpecificationCharacteristicValues);
		}
	}
	
	public List<CompositeProductSpecificationCharacteristicValue> getProductSpecificationCharacteristicValues(){
		if (productSpecificationCharacteristicValues == null) {
			productSpecificationCharacteristicValues = new ArrayList<CompositeProductSpecificationCharacteristicValue>();
		}
		return productSpecificationCharacteristicValues;
	}
	
	public void setName(String name) {
		productSpecificationCharacteristic.setName(name);
	}
	
	public void setValueType(String valueType) {
		productSpecificationCharacteristic.setValueType(convertToValueTypeFromString(valueType));
	}
	
	public CompositeProductSpecificationCharacteristicValueMapper newCompositeProductSpecificationCharacteristicValue() {
		CompositeProductSpecificationCharacteristicValue cpscv = new CompositeProductSpecificationCharacteristicValue();
		getProductSpecificationCharacteristicValues().add(cpscv);
		return new CompositeProductSpecificationCharacteristicValueMapper(cpscv);
	}
	
	public CompositeProductSpecificationCharacteristicValueMapper newCompositeProductSpecificationCharacteristicValue(String value) {
		CompositeProductSpecificationCharacteristicValue cpscv = new CompositeProductSpecificationCharacteristicValue();

		cpscv.setValue(value);
		cpscv.setValueType(ValueType.STRING);
		cpscv.setDefault(false);

		getProductSpecificationCharacteristicValues().add(cpscv);

		return new CompositeProductSpecificationCharacteristicValueMapper(cpscv);
	}

	/**
	 * @param valueType
	 * @return
	 */
	private ValueType convertToValueTypeFromString(String valueType) {
		return ValueType.fromValue(valueType);
	}
}
