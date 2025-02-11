package com.telus.channelsalesmgmt.common.monitoring;

import org.springframework.util.StopWatch;

public class WorkMonitoringContext {
	
	private ProviderMonitoringContext providerMonitoringContext = new ProviderMonitoringContext();

	private StopWatch stopWatch;
	
	private String className;

	private String methodName;

	private String workName;

	private String threadName;

	private int workIndex;

	private boolean recording;

	private boolean active = false;
	
	public WorkMonitoringContext() {
		// there should be only one WMC for each thread
		this.threadName = Thread.currentThread().toString();
	}

	public ProviderMonitoringContext getProviderMonitoringContext() {
		return providerMonitoringContext;
	}

	public void setProviderMonitoringContext(ProviderMonitoringContext providerMonitoringContext) {
		this.providerMonitoringContext = providerMonitoringContext;
	}

	public StopWatch getStopWatch() {
		return stopWatch;
	}

	public void setStopWatch(StopWatch stopWatch) {
		this.stopWatch = stopWatch;
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

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkIndex(int workIndex) {
		this.workIndex = workIndex;
	}
	
	public int getWorkIndex() {
		return workIndex;
	}

	@Override
	public String toString() {
		return "WorkMonitoringContext [workName=" + workName + ", threadName=" + threadName + "]";
	}

	public boolean isRecording() {
		return this.recording;
	}

	public void setRecording(boolean recording) {
		this.recording = recording;
	}

	public boolean isActive() {
		return active ;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
