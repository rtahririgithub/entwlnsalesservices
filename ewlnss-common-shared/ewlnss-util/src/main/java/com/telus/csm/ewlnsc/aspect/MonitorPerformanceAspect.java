package com.telus.csm.ewlnsc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.telus.channelsalesmgmt.common.aspect.profiling.ServiceUsageStatistics;
import com.telus.channelsalesmgmt.common.aspect.profiling.ServiceUsageStatisticsManager;
import com.telus.csm.ewlnsc.util.ExecutionTimer;
import com.telus.csm.ewlnsc.util.LoggerUtil;

@Aspect
@Component
public class MonitorPerformanceAspect {
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(MonitorPerformanceAspect.class);
	
	@Pointcut("@annotation(MonitorPerformance)")
	private void anyMonitoredMethod() {}

	@Pointcut("execution(public * *(..))")
	private void anyPublicMethod() {}
	
	@Pointcut("@within(MonitorPerformance)")
	private void withinMonitoredType() {}
	
	@Around("anyMonitoredMethod() || (withinMonitoredType() && anyPublicMethod())")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

		Signature sig = joinPoint.getSignature();
		
		String methodName = sig.getName() + ProxyProfilingAspect.getMethodNameExtension(joinPoint.getArgs());

		ExecutionTimer et = new ExecutionTimer(sig.getDeclaringType().getSimpleName(), methodName);
		
		Throwable error = null; 
		try {
			return joinPoint.proceed();
		} catch (Throwable t) {
			error = t;
			throw t;
		} finally {
			try {
				et.stop();
				ServiceUsageStatistics stats = ServiceUsageStatisticsManager.Factory.getInstance().getCollectingUsageStatisticsForClass(sig.getDeclaringTypeName());
				stats.collectObservation(methodName, et.getElapse(), error != null, error);
			} catch (Exception e) {
				logger.error("", "logExecutionTime", "Exception", e);
			}
		}
	}
}
