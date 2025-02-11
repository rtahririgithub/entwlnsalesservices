package com.telus.channelsalesmgmt.common.monitoring;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import commonj.work.Work;
import commonj.work.WorkItem;

@Component
@Aspect
public class ResourceMonitoringAspect {
	
	private Log log = LogFactory.getLog(ResourceMonitoringAspect.class);
	
	private @Autowired ResourceMonitor resourceMonitor;
	
	@Pointcut("execution(public * *(..))")
	private void anyPublicMethod() {}
	
	@Pointcut("@within(com.telus.channelsalesmgmt.common.monitoring.MonitoredResource)")
	private void withinMonitoredResource() {}
	
	@Around("anyPublicMethod() && withinMonitoredResource()")
	public Object monitorResource(ProceedingJoinPoint pjp) throws Throwable {
		String className = pjp.getTarget().getClass().getSimpleName();
		String methodName = pjp.getSignature().getName();
		
		resourceMonitor.startMonitoringResource(className, methodName);
		
		Throwable error = null;
		
		try {
			return pjp.proceed();
		} catch (Throwable t) {
			error = t;
			throw t;
		} finally {
			resourceMonitor.stopMonitoringResource(error);
		}
	}
	
	@Around("execution(public * commonj.work.WorkManager.schedule(..)) && args(work,..)")
	public Object monitorSchedule(ProceedingJoinPoint pjp, Work work) throws Throwable {
		log.trace("\nschedule start - " + work);
		
		if (work == null) {
			return null;
		}
		
		WorkItem workItem = null;
		
		// synchronized because work can sometimes begin processing before this entire task is completed 
		synchronized(work) {
			workItem = (WorkItem) pjp.proceed();
			resourceMonitor.registerWork(work, workItem);
			log.trace("\nschedule end - " + workItem);
		}
		
		return workItem;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Around("execution(public * commonj.work.WorkManager.waitForAll(..)) && args(workItems,..)")
	public Object monitorWaitForAll(ProceedingJoinPoint pjp, Collection workItems) throws Throwable {
		log.debug("\nwaitForAll - " + workItems);
		return monitorWait(pjp, workItems, "waitForAll");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Around("execution(public * commonj.work.WorkManager.waitForAny(..)) && args(workItems,..)")
	public Object monitorWaitForAny(ProceedingJoinPoint pjp, Collection workItems) throws Throwable {
		return monitorWait(pjp, workItems, "waitForAny");
	}

	private Object monitorWait(ProceedingJoinPoint pjp, Collection<WorkItem> workItems, String methodName) throws Throwable {
		try {
			StringBuilder workNames = new StringBuilder("(");
			boolean isFirst = true;
			
			for (WorkItem workItem : workItems) {
				if (isFirst) {
					isFirst = false;
				} else {
					workNames.append(", ");
				}
				
				String workName = resourceMonitor.getWorkName(workItem);
				workNames.append(workName);
			}
			
			workNames.append(")");
			resourceMonitor.startMonitoringResource("WorkManager",  methodName + workNames.toString());
		} catch (Throwable t) {
			log.error("monitoring exception", t);
		}
		
		Throwable error = null;
		
		try {
			return pjp.proceed();
		} catch (Throwable e) {
			error = e;
			throw e;
		} finally {
			try {
				resourceMonitor.stopMonitoringResource(error, false);
			} catch (Throwable t) {
				log.error("monitoring exception", t);
			}
		}
	}
	
}
