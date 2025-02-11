package com.telus.csm.ewlnsc.monitor;

import java.util.ArrayList;

import org.apache.log4j.spi.LoggingEvent;

public class ESSMonitorAppender extends org.apache.log4j.AppenderSkeleton {

	@Override
	public void close() {
		//
	}

	@Override
	public boolean requiresLayout() {
		return true;
	}

	@Override
	protected void append(LoggingEvent paramLoggingEvent) {

		Object op = paramLoggingEvent.getMDC("operation");
		String opString = "";
		if (op != null) {
			opString = op.toString();
		}
		
		ArrayList<String> message = new ArrayList<String>();
		
		message.add(this.layout.format(paramLoggingEvent));
		
		
		String[] exception = paramLoggingEvent.getThrowableStrRep();
		if (exception !=null && exception.length > 0) {
			message.add(exception[0]);
		}
		
		ESSMonitor.postAlert(opString, paramLoggingEvent.getTimeStamp(), message);
	}

}
