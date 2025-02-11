package com.telus.csm.ewlnsc.helper;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.domain.ValueTypeVO;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.base_types_2_0.ValueType;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.AtomicProductSpecificationCharacteristic;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.CompositeProductSpecificationCharacteristicValue;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.ProductSpecificationCharacteristicValue;

/**
 * @author Jose.Mena
 *
 */
public class CompositeProductSpecificationCharacteristicValueMapper {

	private CompositeProductSpecificationCharacteristicValue compositeProductSpecificationCharacteristicValue;
	private List<AtomicProductSpecificationCharacteristic> valueSpecificationCharacteristic;
	
	public CompositeProductSpecificationCharacteristicValueMapper(
			CompositeProductSpecificationCharacteristicValue prodSpecCharacVal11) {
		this.compositeProductSpecificationCharacteristicValue = prodSpecCharacVal11;
		if(compositeProductSpecificationCharacteristicValue!=null 
				&& compositeProductSpecificationCharacteristicValue.getValueSpecificationCharacteristic()!=null 
				&& !compositeProductSpecificationCharacteristicValue.getValueSpecificationCharacteristic().isEmpty()){
		    this.valueSpecificationCharacteristic = compositeProductSpecificationCharacteristicValue.getValueSpecificationCharacteristic();
		}
	}
	
	public void finished() {
		// no implementation in CSS. 
	}
	
	public List<AtomicProductSpecificationCharacteristic> getValueSpecificationCharacteristic(){
		if (valueSpecificationCharacteristic == null){
			valueSpecificationCharacteristic = new ArrayList<AtomicProductSpecificationCharacteristic>();
		}
		return valueSpecificationCharacteristic;
	}
	
	public void setValueType(String valueType) {
		compositeProductSpecificationCharacteristicValue.setValueTo(valueType);
	}

	public void setDefault(boolean value) {
		compositeProductSpecificationCharacteristicValue.setDefault(value);
	}
	
	public void setValue(String value) {
		compositeProductSpecificationCharacteristicValue.setValue(value);
	}
	
	public AtomicProductSpecificationCharacteristicMapper newAtomicProductSpecificationCharacteristic() {
		AtomicProductSpecificationCharacteristic apsc = new AtomicProductSpecificationCharacteristic();
		getValueSpecificationCharacteristic().add(apsc);
		return new AtomicProductSpecificationCharacteristicMapper(apsc);
	}
	
	public AtomicProductSpecificationCharacteristicMapper newAtomicValue(String name, String value) {
		return newAtomicValue(name, value, ValueTypeVO.STRING);
	}
	
	public AtomicProductSpecificationCharacteristicMapper newAtomicValue(String name, Number value) {
		return newAtomicValue(name, value.toString(), ValueTypeVO.NUMERIC);
	}
	
	public AtomicProductSpecificationCharacteristicMapper newAtomicValue(String name, String value, ValueTypeVO valueType) {
		AtomicProductSpecificationCharacteristicMapper atomic =
				newAtomicProductSpecificationCharacteristic();

		atomic.setName(name);
		atomic.setValueType(valueType.value());

		ProductSpecificationCharacteristicValue characteristicValue = 
				atomic.newProductSpecificationCharacteristicValue();
			
		characteristicValue.setValueType(convertToValueType(valueType.value()));
		characteristicValue.setDefault(false);
		characteristicValue.setValue(value);

		atomic.finished();
		
		return atomic;
	}

	/**
	 * @param value
	 * @return
	 */
	private ValueType convertToValueType(String valueType) {
		return ValueType.fromValue(valueType);
	}
	
}
