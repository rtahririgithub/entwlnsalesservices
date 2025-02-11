package com.telus.csm.ewlnsc.util;

public class ExecutionTimer {
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(ExecutionTimer.class);

	private long startTime = System.currentTimeMillis();
	private long elapse = 0;
	private long sectionStartTime = startTime;
	
	public long getElapse() {
		return elapse;
	}

	private String className = "";
	private String methodName = "";
	
	private StringBuilder lapMessage = new StringBuilder();
	
	public ExecutionTimer(String className, String methodName) {
		super();
		this.className = className;
		this.methodName = methodName;
	}
	
	public void lap(String sectionName) {
		long sectionEndTime = System.currentTimeMillis();
		lapMessage.append(" " + sectionName + "=" + (sectionEndTime - sectionStartTime));
		sectionStartTime = sectionEndTime; 
	}

	public void stop() {
		elapse = System.currentTimeMillis() - startTime;
		logger.debug(className + "." + methodName, "Elapse " + elapse + "ms" + lapMessage.toString());
	}
	
}
