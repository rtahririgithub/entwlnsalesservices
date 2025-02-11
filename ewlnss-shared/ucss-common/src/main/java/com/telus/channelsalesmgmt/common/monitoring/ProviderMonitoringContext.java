package com.telus.channelsalesmgmt.common.monitoring;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import commonj.work.Work;
import commonj.work.WorkItem;

public class ProviderMonitoringContext {
	
	private String correlationId;
	
	private String className;

	private String methodName;

	private Map<String, AtomicInteger> workNameCounts = new ConcurrentHashMap<String, AtomicInteger>();

	private Map<WorkItem, String> workNames = new ConcurrentHashMap<WorkItem, String>();

	private Collection<WorkMonitoringContext> unfinishedWork = Collections.synchronizedSet(new HashSet<WorkMonitoringContext>());
	
	private SortedSet<FinishedWork> finishedWork = new TreeSet<FinishedWork>();

	private Map<Work, WorkItem> scheduledWorkItems = new ConcurrentHashMap<Work, WorkItem>();

	private String threadName;

	private AtomicInteger workIndex = new AtomicInteger(0);

	private boolean active = false;

	public ProviderMonitoringContext() {
		this.threadName = Thread.currentThread().toString();
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String tc) {
		this.correlationId = tc;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}
	
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Map<String, AtomicInteger> getWorkNameCounts() {
		return workNameCounts;
	}

	public Map<WorkItem, String> getWorkNames() {
		return workNames;
	}
	
	public String getWorkName(WorkItem workItem) {
		return workNames.get(workItem);
	}

	public Map<Work, WorkItem> getScheduledWorkItems() {
		return scheduledWorkItems;
	}

	public Collection<WorkMonitoringContext> getUnfinishedWork() {
		return unfinishedWork;
	}

	public SortedSet<FinishedWork> getFinishedWork() {
		return finishedWork;
	}

	public AtomicInteger getWorkIndex() {
		return workIndex;
	}

	public void setActive(boolean active) {
		this.active  = active;
	}
	
	public boolean isActive() {
		return active;
	}

	@Override
	public String toString() {
		return "ProviderMonitoringContext [className=" + className + ", methodName=" + methodName + ", threadName="
				+ threadName + "]";
	}

}