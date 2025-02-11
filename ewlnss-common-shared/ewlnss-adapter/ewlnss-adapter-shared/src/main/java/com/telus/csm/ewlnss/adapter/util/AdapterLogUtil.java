package com.telus.csm.ewlnss.adapter.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.MDC;

public class AdapterLogUtil {
	
	private AdapterLogUtil() {
		super();
	}

	private static final String ADAPTER_METHOD_NAME = "adapterMethodName";

	public static String getAdapterMethodName() {
		String result = "";
		String adapterMethodName = (String) MDC.get(ADAPTER_METHOD_NAME);

		if (!StringUtils.isEmpty(adapterMethodName)) {
			result = adapterMethodName + " ";
		}
		
		return result;
	}


}
