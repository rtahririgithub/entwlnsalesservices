package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.util.LoggerUtil;

import commonj.work.Work;

public abstract class TaskBase implements Work {
	
	private static LoggerUtil logger = LoggerUtil.getLogger(TaskBase.class);

	protected RuntimeException runtimeException = null;

	protected void rethrowException() {
		if (runtimeException != null) {
			logger.error("Exception: " + logger.getStackTrace(runtimeException) );
			throw runtimeException;
		}
	}

	@Override
	public void run() {
		String functionName = this.getClass().getSimpleName() + ".run()";
		logger.enter(functionName);
		try {
			execute();
		} catch (RuntimeException e) {
			runtimeException = e; 
			logger.error("Exception: " + runtimeException.toString() + logger.getStackTrace(e));
		}
    	
		logger.exit(functionName);
	}

	protected abstract void execute();
	
	@Override
	public boolean isDaemon() {
		return false;
	}

	@Override
	public void release() {
		
	}

}
