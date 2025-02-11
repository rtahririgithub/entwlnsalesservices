package com.telus.channelsalesmgmt.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Writer;
import java.lang.reflect.Method;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.ExtendedHierarchicalStreamWriterHelper;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.CompactWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.mapper.Mapper;

public class UcssXStream extends XStream {
	
	// customized XStream instance to write compact XML
	public static final XStream compactXStream = new XStream(new XppDriver() {
	    @Override
	    public HierarchicalStreamWriter createWriter(Writer out) {
	        return new CompactWriter(out, getNameCoder());
	    }
	});
	
	private Logger logger = Logger.getLogger(getClass());	
	
	private static UcssXStream instance;
	
	static {
		instance = new UcssXStream();
	}
	
	public UcssXStream() {
		super(new DomDriver());
		
		setMode(XStream.NO_REFERENCES);
		
		registerConverter(new ReadOnlyConverter(getMapper()), XStream.PRIORITY_LOW);
		
	}
	
	public static String toString(Object obj) {
		if( obj == null ) return null;
		return instance.toXML(obj);
	}
	
	public String toXML(Object obj) {
		String xml = null;
		try {
			xml = super.toXML(obj);
		} catch (Exception e) {
			logger.warn("could not generate XML representation of ojbect: " + e.getMessage());
			xml = ToStringBuilder.reflectionToString(obj);
		}
		return xml;
	}

	public static class ReadOnlyConverter implements Converter {
		
		private Logger logger = Logger.getLogger(getClass());

		private Mapper mapper;
		
		private static Object[] NO_ARGS = new Object[0];
		
		public ReadOnlyConverter(Mapper mapper) {
			this.mapper = mapper;
		}

		public void marshal(Object parent, HierarchicalStreamWriter writer,
				MarshallingContext context) {
	        final String classAttributeName = mapper.aliasForSystemAttribute("class");
	        
			Class parentType = parent.getClass();
			PropertyDescriptor[] properties = getProperties(parentType);
			
			if (properties == null)
				return;
			
			for (int i = 0; i < properties.length; i++) {
				PropertyDescriptor property = properties[i];
                String propertyName = property.getDisplayName();
                Class propertyType = property.getPropertyType();

                if (shouldSkipProperty(parentType, propertyType, propertyName))
                	continue;

                Method readMethod = property.getReadMethod();
                
                if (readMethod == null) 
                	continue;
                
            	Object value = null;
            	
            	try {
	                value = readMethod.invoke(parent, NO_ARGS);
            	} catch (Exception e) {
            		logger.debug("could not get value for property " + parentType.getName() + "." + propertyName + ": " + e.getMessage());
            		continue;
            	}
            	
            	if (value != null) {
                    String serializedMember = mapper.serializedMember(parentType, propertyName);
    				ExtendedHierarchicalStreamWriterHelper.startNode(writer, serializedMember, propertyType);
                    Class actualType = value.getClass();
                    Class defaultType = mapper.defaultImplementationOf(propertyType);
                    if (!actualType.equals(defaultType) && classAttributeName != null) {
                        writer.addAttribute(classAttributeName, mapper.serializedClass(actualType));
                    }
					context.convertAnother(value);
	                writer.endNode();
            	}
			}
		}

		private boolean shouldSkipProperty(Class parentType, Class propertyType,
				String propertyName) {
	        final String classAttributeName = mapper.aliasForSystemAttribute("class");
	        
	        if (propertyName.equals(classAttributeName))
	        	return true;

			return false;
		}

		public Object unmarshal(HierarchicalStreamReader reader,
				UnmarshallingContext context) {
			return null;
		}

		public boolean canConvert(Class type) {
			PropertyDescriptor[] descriptors = getProperties(type);
			return descriptors != null;
		}
		
		private BeanInfo getBeanInfo(Class type) {
			BeanInfo beanInfo = null;
			
			try {
				beanInfo = Introspector.getBeanInfo(type);
			} catch (IntrospectionException e) {
				logger.warn("could not get BeanInfo for class " + type + ": " + e.getMessage());
			}
			
			return beanInfo;
		}
		
		private PropertyDescriptor[] getProperties(Class type) {
			BeanInfo beanInfo = getBeanInfo(type);
			
			if (beanInfo == null)
				return null;
			
			return beanInfo.getPropertyDescriptors();
		}

	}
	
}
