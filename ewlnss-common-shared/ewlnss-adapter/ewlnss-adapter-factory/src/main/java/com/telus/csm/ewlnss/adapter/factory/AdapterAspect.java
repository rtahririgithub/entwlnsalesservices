package com.telus.csm.ewlnss.adapter.factory;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.MDC;

import com.telus.channelsalesmgmt.common.aspect.aspect.Aspect;

public class AdapterAspect implements Aspect {

	
	private static final String ADAPTER_METHOD_NAME = "adapterMethodName";

	@Override
	public void after(Object arg0, String arg1, Object[] arg2, Object arg3, Object arg4, boolean arg5) {
		MDC.remove(ADAPTER_METHOD_NAME);

	}

	@Override
	public Object before(Object adapterObject, String method, Object[] parms) {
		
		MDC.put(ADAPTER_METHOD_NAME, adapterObject.getClass().getSimpleName() + "." + method);
		
		return null;
	}

	@Override
	public void exception(Object arg0, String arg1, Object[] arg2, Object arg3, Object arg4) {
		MDC.remove(ADAPTER_METHOD_NAME);

	}

	@Override
	public String getAspectName() {
		return null;
	}

	@Override
	public void setPointcutType(String arg0) {

	}

	public static String getAdapterMethodName() {
		String result = "";
		String adapterMethodName = (String) MDC.get(ADAPTER_METHOD_NAME);

		if (!StringUtils.isEmpty(adapterMethodName)) {
			result = adapterMethodName + " ";
		}
		
		return result;
	}


}
