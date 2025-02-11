package com.telus.csm.ewlnssproxy.rest.transformer;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.adapter.domain.GetServiceAddressDetailsAdapterRequest;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.ServiceAddress;

public class GetServiceAddressDetailsTransformer {
	
	private static Logger logger = Logger.getLogger(GetServiceAddressDetailsTransformer.class);

	final static String SET = "set";
    final static String GET = "get";
    final static Class[] SETTER_ARGS = new Class[]{String.class};
    final static Object[] SETTER_VAL  = new Object[1];
    
    final static Map<String, String> addressMap = new HashMap<String, String>();
    static {
    	addressMap.put("COID", "Coid");
    	addressMap.put("CLLICode", "ClliCode");
    }
    
	
	public static GetServiceAddressDetailsAdapterRequest transform(String transactionId, String serviceAddressId, String provinceCd) {
		
		GetServiceAddressDetailsAdapterRequest request = new GetServiceAddressDetailsAdapterRequest();
		
		ServiceAddress address = new ServiceAddress();
		address.setAddressId(serviceAddressId);
		address.setProvinceStateCode(provinceCd);
		
		request.setAddress(address);
		request.setSalesTransactionId(transactionId);
		
		return request;
	}
	
	public static com.telus.csm.ewlnssproxy.csab.domain.ServiceAddress transform(ServiceAddress address) {
		
		com.telus.csm.ewlnssproxy.csab.domain.ServiceAddress serviceAddress = new com.telus.csm.ewlnssproxy.csab.domain.ServiceAddress();
		
		transform(address, serviceAddress, addressMap);
		
		return serviceAddress;
	}
	
	public static void transform(Object scr, Object tar, Map<String, String> customizedMap) {
		
		try {
			Class source = scr.getClass();
			Class target = tar.getClass();
			final Method[] methods = source.getMethods();
			for (Method m : methods) {
				final String name=m.getName();
	            if (
	                name.length()>GET.length()
	             && name.indexOf(GET)==0
	             && m.getReturnType().equals(String.class)
	             && m.getParameterTypes().length==0)
	            {
	                final String v = (String)m.invoke(scr);
	                if (v!=null)
	                {
	                	try {
		                    final Method setter=target.getMethod(SET+name.substring(3),SETTER_ARGS);
		                    if (setter!=null)
		                    {
		                        SETTER_VAL[0]=v.trim();
		                        setter.invoke(tar, SETTER_VAL);
		                    }
	                	}
	                	catch (NoSuchMethodException ne) {
	            			// ignore not existing method
	            		}
	                }
	            }
			}
			
			for (String key : customizedMap.keySet()) {
				final Method m = source.getMethod(GET+key, null);
				final String name=m.getName();
	            if (
	                name.length()>GET.length()
	             && name.indexOf(GET)==0
	             && m.getReturnType().equals(String.class)
	             && m.getParameterTypes().length==0)
	            {
	                final String v = (String)m.invoke(scr);
	                if (v!=null)
	                {
	                	try {
		                    final Method setter=target.getMethod(SET+customizedMap.get(key),SETTER_ARGS);
		                    if (setter!=null)
		                    {
		                        SETTER_VAL[0]=v.trim();
		                        setter.invoke(tar, SETTER_VAL);
		                    }
	                	}
	                	catch (NoSuchMethodException ne) {
	            			// ignore not existing method
	            		}
	                }
	            }
			}
		}
		catch (Exception e) {
			logger.warn("invoke error: " + e.getMessage());
		}
	}
}
