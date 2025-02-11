package com.telus.csm.ewlnsc.aspect;

import org.apache.log4j.MDC;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.telus.csm.ewlnsc.util.LoggerUtil;

@Aspect
@Component
public class LogOperationNameAspect {
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(LogOperationNameAspect.class);
	
	@Pointcut("@annotation(LogOperationName)")
	private void anyMonitoredMethod() {}

	@Pointcut("execution(public * *(..))")
	private void anyPublicMethod() {}
	
	@Pointcut("@within(LogOperationName)")
	private void withinMonitoredType() {}
	
	@Before("anyMonitoredMethod() || (withinMonitoredType() && anyPublicMethod())")
	public void setOperationName(JoinPoint joinPoint) {
		MDC.put("operation", joinPoint.getSignature().getName());
	}
}
