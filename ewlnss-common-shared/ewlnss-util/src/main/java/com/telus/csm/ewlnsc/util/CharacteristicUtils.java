package com.telus.csm.ewlnsc.util;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;

public class CharacteristicUtils {

	private static final String PROPERTY_NAME = "name";
	private static final String PROPERTY_VALUE = "value";

	public static String getValueByName( List<?> characteristics, String name ) {
		
		if ( CollectionUtils.isNotEmpty(characteristics) ) {
		
			for( Object characteristic :  characteristics ) {
				
				try {
					String characteristicName = BeanUtils.getProperty(characteristic, PROPERTY_NAME );
					if (name.equals( characteristicName ) ) {
						return BeanUtils.getProperty(characteristic, PROPERTY_VALUE );
					}
				} catch (Exception e) {
					
					throw new RuntimeException ( e );
				} 
			}
		}
		
		return null;
	}

	public static Boolean getBooleanByName( List<?> characteristics, String name ) {
		
		Boolean result = null;
		String value = getValueByName(characteristics, name);
		if (  value!=null ) {
			result = Boolean.valueOf(value);
		}
		return result;
	}
	
	public static Float getFloatByName( List<?> characteristics, String name ) {
		
		Float result = null;
		String value = getValueByName(characteristics, name);
		if (  value!=null ) {
			result = Float.valueOf(value);
		}
		return result;
	}
	
	public static Object getByName( List<?> characteristics, String name ) {
		
		if ( CollectionUtils.isNotEmpty(characteristics) ) {
		
			for( Object characteristic :  characteristics ) {
				
				try {
					String characteristicName = BeanUtils.getProperty(characteristic, PROPERTY_NAME );
					if (name.equals( characteristicName ) ) {
						return characteristic;
					}
				} catch (Exception e) {
					
					throw new RuntimeException ( e );
				} 
			}
		}
		
		return null;
	}

	public static String getValue( Object characteristic) {

		try {
			
			return BeanUtils.getProperty(characteristic, PROPERTY_VALUE );
			
		} catch (Exception e) {
			
			throw new RuntimeException ( e );
		} 
	}
}
