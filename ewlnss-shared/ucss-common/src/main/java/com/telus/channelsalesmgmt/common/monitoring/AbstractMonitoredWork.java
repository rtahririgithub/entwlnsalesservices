package com.telus.channelsalesmgmt.common.monitoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import commonj.work.Work;

public abstract class AbstractMonitoredWork extends SpringBeanAutowiringSupport implements Work {
	
	private ProviderMonitoringContext savedMonitoringContext = null;
	
	private boolean initialized = false;
	
	private @Autowired ResourceMonitor resourceMonitor;

	public AbstractMonitoredWork() {
		super();
		savedMonitoringContext = resourceMonitor.getProviderMonitoringContext();
		initialized = true;
	}

	// the run() method is declared final so that subclasses will fail at compile time if they implement run() ... subclasses should implement loggedRun() instead
	@Override
	public final void run() {
		Assert.isTrue(initialized, "the task constructor must call the no-arg parent class constructor using \"super()\"");

		Throwable error = null;
		
		try {
			resourceMonitor.startWorkProcessing(savedMonitoringContext, this);
			monitoredRun();
		} catch (RuntimeException e) {
			error = e;
			throw e;
		} catch (Error e) {
			error = e;
			throw e;
		} finally {
			resourceMonitor.stopWorkProcessing(error);
		}
	}

	public abstract void monitoredRun();

}
