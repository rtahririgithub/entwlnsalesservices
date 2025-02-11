package com.telus.channelsalesmgmt.common.monitoring;

import java.util.Collection;
import java.util.Map;
import java.util.SortedSet;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.util.StopWatch.TaskInfo;
import org.springframework.util.StringUtils;

import com.telus.channelsalesmgmt.common.aspect.profiling.ServiceUsageStatistics;
import com.telus.channelsalesmgmt.common.aspect.profiling.ServiceUsageStatisticsManager;

import commonj.work.Work;
import commonj.work.WorkItem;

@Component
public class ResourceMonitor {
	
	private static final String INTERNAL_PROCESSING = "internal processing";

	public static final String RESOURCE_LOG_NAME = "com.telus.ucss.wirelinesales.web.util.LogSOAPHandler";

	public static final String lOGGING_CONTEXT_NAME = "cid";
	
	public static final Log resource_log = LogFactory.getLog(ResourceMonitor.RESOURCE_LOG_NAME);

	private final Log log = LogFactory.getLog(ResourceMonitor.class);
	
	private final ThreadLocal<WorkMonitoringContext> workContextHolder = new ThreadLocal<WorkMonitoringContext>();
	
	private @Autowired Environment environment;
	
	private ServiceUsageStatisticsManager serviceUsageStatisticsManager = null; 
	
	public ResourceMonitor() {
		try {
			this.serviceUsageStatisticsManager = ServiceUsageStatisticsManager.Factory.getInstance();
		} catch (Exception e) {
			log.error("could not get statistics manager", e);
		}
	}

	private boolean isEnabled() {
		return environment.getProperty("SalesServices.enable.resourceMonitoring", Boolean.class, false);
	}
	
	private boolean isNewStatsMethod() {
		return environment.getProperty("SalesServices.enable.newStatsMethod", Boolean.class, false);
	}
	
	ProviderMonitoringContext getProviderMonitoringContext() {
		try {
			return getWorkMonitoringContext().getProviderMonitoringContext();
		} catch (Throwable t) {
			log.error("monitoring exception", t);
			return null;
		}
	}
	
	private WorkMonitoringContext getWorkMonitoringContext() {
		try {
			WorkMonitoringContext wmc = workContextHolder.get();
			
			if (wmc == null) {
				wmc = new WorkMonitoringContext();
				workContextHolder.set(wmc);
			}
				
			return wmc;
		} catch (Throwable t) {
			log.error("monitoring exception", t);
			return null;
		}
	}

	public void setTransactionContext(String tc) {
		try {
			setLoggingCorrelationId(tc);
			getProviderMonitoringContext().setCorrelationId(tc);
		} catch (Throwable t) {
			log.error("monitoring exception", t);
		}
	}

	private void setLoggingCorrelationId(String tc) {
		if (StringUtils.isEmpty(tc)) {
			MDC.remove(lOGGING_CONTEXT_NAME);
		} else {
			MDC.put(lOGGING_CONTEXT_NAME, tc);
		}
	}

	public void startMonitoringResource(String className, String methodName) {
		log.info("startMonitoringResource enabled");
		try {
			if (!isEnabled()) {
				return;
			}
			
			WorkMonitoringContext wmc = getWorkMonitoringContext();
			
			if (!wmc.isActive()) {
				startProvider(wmc, className, methodName);
			} else {
				if (wmc.isRecording()) {
					log.debug(wmc.getClassName() + "." + wmc.getMethodName() + " is already in progress, will not record " + className + "." + methodName);
				} else {
					StopWatch sw = wmc.getStopWatch();
					sw.stop();
					sw.start(className + "." + methodName);
					
					wmc.setClassName(className);
					wmc.setMethodName(methodName);
					wmc.setRecording(true);
				}
			}
		} catch (Throwable t) {
			log.error("monitoring exception", t);
		}
	}

	public void stopMonitoringResource(Throwable error) {
		log.info("stopMonitoringResource enabled");
        stopMonitoringResource(error, true);
	}

	public void stopMonitoringResource(Throwable error, boolean collectStats) {
		try {
			if (!isEnabled()) {
				return;
			}
			
			WorkMonitoringContext wmc = getWorkMonitoringContext();
			ProviderMonitoringContext pmc = wmc.getProviderMonitoringContext();
			
			if (wmc.isRecording()) {
				StopWatch sw = wmc.getStopWatch();
				sw.stop();
				wmc.setRecording(false);
				long executionTime = sw.getLastTaskTimeMillis();
				sw.start(INTERNAL_PROCESSING);
				
				if (collectStats) {
					String className;
					String methodName;
					
					if (isNewStatsMethod()) {
						className = pmc.getClassName() + "." + pmc.getMethodName();
						methodName = wmc.getClassName() + "." + wmc.getMethodName();
					} else {
						className = wmc.getClassName();
						methodName = wmc.getMethodName();
					}
					
					collectObservation(className, methodName, executionTime, error);
				}
			} else {
				stopProvider(wmc, error);
			}	
		} catch (Throwable t) {
			log.error("monitoring exception", t);
		}
	}

	private void collectObservation(String className, String methodName, long executionTime, Throwable error) {
		boolean isError = error != null;
		ServiceUsageStatistics sus = serviceUsageStatisticsManager.getCollectingUsageStatisticsForClass(className);
		sus.collectObservation(methodName, executionTime, isError, error);
	}
	
	private void startProvider(WorkMonitoringContext wmc, String className, String methodName) {
		log.info("startProvider enabled");
		log.trace("start provider");

		try {
			// preserve correlation id from previous 
			String correlationId = wmc.getProviderMonitoringContext().getCorrelationId();
			
			// new context for every resource provider transaction
			ProviderMonitoringContext pmc = new ProviderMonitoringContext();
			wmc.setProviderMonitoringContext(pmc);
			
			pmc.setCorrelationId(correlationId);
			pmc.setClassName(className);
			pmc.setMethodName(methodName);
			pmc.setActive(true);
			
			startWorkProcessing(wmc, null);
		} catch (Throwable t) {
			log.error("monitoring exception", t);
		}
	}

	private void stopProvider(WorkMonitoringContext wmc, Throwable error) {
		log.info("stopProvider enabled");
		try {
			if (!isEnabled()) {
				return;
			}
			
			ProviderMonitoringContext pmc = wmc.getProviderMonitoringContext();

			// synchronize so that provider and work finishing at the same time don't lose information
			synchronized(pmc) {
				stopWorkProcessing(error);
				log.trace("stop provider");
				
				long executionTime = wmc.getStopWatch().getTotalTimeMillis();
				
				String className;
				String methodName;

				if (isNewStatsMethod()) {
					className = pmc.getClassName() + "." + pmc.getMethodName();
					methodName = "overall";
				} else {
					className = pmc.getClassName();
					methodName = pmc.getMethodName();
				}
				
				collectObservation(className, methodName, executionTime, error);
				
				SortedSet<FinishedWork> finished = pmc.getFinishedWork();
				Collection<WorkMonitoringContext> unfinished = pmc.getUnfinishedWork();

				for (FinishedWork fw : finished) {
					logStopWatch(fw.getStopWatch());
				}

				for (WorkMonitoringContext uwmc : unfinished) {
					resource_log.warn("\nunfinished work " + uwmc);
				}
				
				pmc.setActive(false);
			}
			
		} catch (Throwable t) {
			log.error("monitoring exception", t);
		}
	}

	private void logStopWatch(StopWatch sw) {
		logStopWatch(sw, null);
	}

	private void logStopWatch(StopWatch sw, String message) {
		StringBuilder sb = new StringBuilder();
		
		if (message != null) {
			sb.append(message);
		}
		
		sb.append("\n");
		sb.append(sw.prettyPrint());
		
		int processingTime = 0;
		
		for (TaskInfo task : sw.getTaskInfo()) {
			if (INTERNAL_PROCESSING.equals(task.getTaskName())) {
				processingTime += task.getTimeMillis();
			}
		}
		
		sb.append("\n");
		sb.append("total internal processing time: " + processingTime);
		
		resource_log.warn(sb.toString());
	}

	void startWorkProcessing(ProviderMonitoringContext savedContext, Work work) {
		log.info("startWorkProcessing enabled");
		try {
			setLoggingCorrelationId(savedContext.getCorrelationId());
			WorkMonitoringContext wmc = getWorkMonitoringContext();
			wmc.setProviderMonitoringContext(savedContext);
			
			if (isEnabled()) {
				startWorkProcessing(wmc, work);
			}
		} catch (Throwable t) {
			log.error("monitoring exception", t);
		}
	}

	private void startWorkProcessing(WorkMonitoringContext wmc, Work work) {
		log.info("startWorkProcessing enabled");
		log.trace("start work - " + work);
		
		ProviderMonitoringContext pmc = wmc.getProviderMonitoringContext();
		
		String workName = null;
		
		if (work == null) {
			// this is the main thread, it's not a separate worker thread
			workName = pmc.getClassName() + "." + pmc.getMethodName();
		} else {
			// this is a separate worker thread
			
			// synchronized because this can sometimes execute before the corresponding advised 
			// scheduling operation is complete (which creates the name we are looking for) 
			synchronized(work) {
				WorkItem wi = pmc.getScheduledWorkItems().remove(work);
				
				if (wi == null) {
					log.error("no scheduled workItem found for " + work);
				} else {
					workName = pmc.getWorkNames().get(wi);
				}
			}

			pmc.getUnfinishedWork().add(wmc);
		}
		
		StopWatch sw = new StopWatch(workName + " - " + Thread.currentThread().getName());
		sw.start(INTERNAL_PROCESSING);
		
		int workIndex = pmc.getWorkIndex().incrementAndGet();

		wmc.setWorkIndex(workIndex);
		wmc.setStopWatch(sw);
		wmc.setWorkName(workName);
		wmc.setClassName(null);
		wmc.setMethodName(null);
		wmc.setActive(true);
	}

	void stopWorkProcessing(Throwable error) {
		log.info("stopWorkProcessing enabled");
		log.trace("stop work");
		
		try {
			if (!isEnabled()) {
				return;
			}
			
			WorkMonitoringContext wmc = getWorkMonitoringContext();
			ProviderMonitoringContext pmc = wmc.getProviderMonitoringContext();

			// synchronize so that provider and work finishing at the same time don't lose information
			synchronized(pmc) {
				StopWatch sw = wmc.getStopWatch();
				sw.stop();
			
				if (pmc.isActive()) {
					pmc.getUnfinishedWork().remove(wmc);
					
					FinishedWork fw = new FinishedWork(wmc.getWorkIndex(), sw);
					pmc.getFinishedWork().add(fw);
				} else {
					// print stopWatch now because provider already finished
					logStopWatch(sw, "\nunfinished work from previously finished " + pmc);
				}
			}
			
			wmc.setActive(false);
		} catch (Throwable t) {
			log.error("monitoring exception", t);
		}
	}

	public void sleep(int pollingIntervalInMS) throws InterruptedException {
		startMonitoringResource("Thread", "sleep");
		Throwable error = null;
		
		try {
			Thread.sleep(pollingIntervalInMS);
		} catch (InterruptedException e) {
			error = e;
			throw e;
		} catch (RuntimeException e) {
			error = e;
			throw e;
		} catch (Error e) {
			error = e;
			throw e;
		} finally {
			stopMonitoringResource(error, isNewStatsMethod());
		}
	}

	public void registerWork(Work work, WorkItem workItem) {
		try {
			if (!isEnabled()) {
				return;
			}
			
			WorkMonitoringContext wmc = getWorkMonitoringContext();
			ProviderMonitoringContext pmc = wmc.getProviderMonitoringContext();

			String baseName = work.getClass().getSimpleName();
			Map<String, AtomicInteger> counts = pmc.getWorkNameCounts();
			
			AtomicInteger countHolder;

			synchronized(counts) {
				countHolder = counts.get(baseName);

				if (countHolder == null) {
					countHolder = new AtomicInteger(0);
					counts.put(baseName, countHolder);
				}
			}

			int count = countHolder.incrementAndGet();
			String workName = baseName + "." + count;
			
			pmc.getScheduledWorkItems().put(work, workItem);
			pmc.getWorkNames().put(workItem, workName);
		} catch (Throwable t) {
			log.error("monitoring exception", t);
		}
	}

	String getWorkName(WorkItem workItem) {
		if (!isEnabled()) {
			return null;
		}
		
		ProviderMonitoringContext mc = getProviderMonitoringContext();
		
		if (mc == null) {
			return null;
		} else {
			return mc.getWorkName(workItem);
		}
	}

}
