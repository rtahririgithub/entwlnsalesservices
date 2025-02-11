
package com.telus.csm.ewlnsc.util.workmanager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.SalesServiceConstants;

import commonj.work.Work;
import commonj.work.WorkException;

/**
 * 
 * This ICommonJWorkManager implementation  uses JDK thread to execute concurrent tasks, it's created mainly for facilitating 
 * unit testing outside of Weblogic server where CommonJ work manager is not available. 
 * 
 * It's not meant for production environment.
 * 
 * @author Michael Liao
 *
 */
public class CommonJWorkManagerJdkImpl implements ICommonJWorkManager {

	static Logger logger = Logger.getLogger(CommonJWorkManagerJdkImpl.class);
	
	@Override
	public Collection<Work> processTasks(Collection<Work> tasks, long timeout) throws WorkException,InterruptedException {
		
		List<Thread> threads = new ArrayList<Thread>(tasks.size() ) ;
		for( Work w: tasks ) {
			Thread worker  = new Thread( w );
			worker.start();
			threads.add( worker );
		}
		
		for( Thread worker:  threads ) {
			worker.join(timeout);
		}
		
		return tasks;
	}


	@Override
	public Collection<Work> processTasks(Collection<Work> tasks) throws WorkException,InterruptedException {

		return processTasks(tasks, ApplicationProperties.getWorkManagerMaxTimeout());
	}

	@Override
	public void processAsyncTasks(Collection<Work> tasks) {
		for( Work w: tasks ) {
			Thread worker  = new Thread( w );
			worker.start(); //start and forget it
		}
	}

}
