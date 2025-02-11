package com.telus.csm.ewlnsc.rest.aspect;

import org.apache.log4j.MDC;
import com.telus.channelsalesmgmt.common.aspect.aspect.Aspect;

public class LoggingAspect implements Aspect {

	@Override
	public String getAspectName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public Object before(Object target, String methodName, Object[] actualParameters) {
		MDC.put("operation", methodName);
		return null;
	}

	@Override
	public void exception(Object target, String methodName, Object[] actualParameters, Object beforeResult, Object t) {
	}

	@Override
	public void after(Object target, String methodName, Object[] actualParameters, Object beforeResult, Object resultObject, boolean hasException) {
	}

	@Override
	public void setPointcutType(String pointcutType) {
	}

}
