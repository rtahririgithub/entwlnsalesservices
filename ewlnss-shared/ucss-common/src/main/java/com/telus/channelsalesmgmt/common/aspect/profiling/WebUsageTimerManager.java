/*
 * Author: Jason Li (t840227)
 * Contact: jason.li2@telus.com
 */
package com.telus.channelsalesmgmt.common.aspect.profiling;

import java.io.Serializable;
import java.util.Calendar;

import javax.naming.InitialContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import commonj.timers.CancelTimerListener;
import commonj.timers.Timer;
import commonj.timers.TimerListener;
import commonj.timers.TimerManager;

// TODO: Auto-generated Javadoc
/**
 * The Class WebUsageTimerManager.
 */
public class WebUsageTimerManager extends HttpServlet implements Serializable, TimerListener, CancelTimerListener {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The logger. */
	static Logger logger = Logger.getLogger( WebUsageTimerManager.class );
	
	/** The Constant webTimerManagerName. */
	public static final String webTimerManagerName = "WebUsageTimerManager";
	
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {  	
		super.init(config);
		
		try {
			if( webTimerManagerName != null ) {
				InitialContext ic = new InitialContext();
				TimerManager tm = (TimerManager)ic.lookup("java:comp/env/" + webTimerManagerName);
				Calendar firstTimeCa = Calendar.getInstance();
				firstTimeCa.set(Calendar.HOUR_OF_DAY, 0);
				firstTimeCa.set(Calendar.MINUTE, 0);
				firstTimeCa.set(Calendar.SECOND, 0);
				firstTimeCa.set(Calendar.MILLISECOND, 0);
				tm.scheduleAtFixedRate(new WebUsageTimerManager(), firstTimeCa.getTime(), 3600 * 1000);				
				logger.info(webTimerManagerName + " is initialized.");
			}
		} catch (Exception ex) {
			logger.error(webTimerManagerName + " is failed to initialize.", ex);
		}
	}
		
	/* (non-Javadoc)
	 * @see commonj.timers.CancelTimerListener#timerCancel(commonj.timers.Timer)
	 */
	public void timerCancel(Timer arg0) {
		logger.warn(webTimerManagerName + " is cancelled.");
	}

	/* (non-Javadoc)
	 * @see commonj.timers.TimerListener#timerExpired(commonj.timers.Timer)
	 */
	public void timerExpired(Timer arg0) {
		try {
			DailyRollingWebUsageProfileManager.Factory.getInstance().takeHourlyProfileSnapshot();
			Calendar now = Calendar.getInstance();
			if( now.get(Calendar.HOUR_OF_DAY) == 0 ) {
				DailyRollingWebUsageProfileManager.Factory.getInstance().resetDailyStatistics();
				logger.info("Daily rolling web usage statistics has been reset.");
			}
		} catch (Exception ex) {
			logger.error("Exception caught when executing web usage timer job.", ex);
		} 
	}
}