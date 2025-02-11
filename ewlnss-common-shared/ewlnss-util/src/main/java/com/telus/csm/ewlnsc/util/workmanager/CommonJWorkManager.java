/**
 *
 */
package com.telus.csm.ewlnsc.util.workmanager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.ExecutionLogUtil;

import commonj.work.Work;
import commonj.work.WorkEvent;
import commonj.work.WorkException;
import commonj.work.WorkItem;
import commonj.work.WorkManager;

/**
 * @author x131123
 *
 */
public class CommonJWorkManager implements ICommonJWorkManager {

	private static CommonJWorkManager instance;
	private WorkManager workManager;
	private static Logger logger = Logger.getLogger(CommonJWorkManager.class);
	
	public static ICommonJWorkManager getInstance(String workManagerJndiName) throws NamingException {
		return getInstance(workManagerJndiName, "");
	}

	public static ICommonJWorkManager getInstance(String workManagerJndiName, String workManagerSvr) throws NamingException {
		if(instance == null || instance.workManager == null) {
			instance = new CommonJWorkManager(workManagerJndiName, workManagerSvr);
		}
		return instance;
	}

//	/**
//	 * @throws NamingException
//	 *
//	 */
//	private CommonJWorkManager(String workManagerJndiName) throws NamingException {
//		this(workManagerJndiName, "");
//	}
	
	/**
	 * @throws NamingException
	 *
	 */
	private CommonJWorkManager(String workManagerJndiName, String workManagerSvr) throws NamingException {
		logger.debug("workManagerJndiName=" + workManagerJndiName + ",workManagerSvr=" + workManagerSvr);
		Context initialContext = null;
		try {
			if (workManagerSvr != null && workManagerSvr.length() > 0) {
				Hashtable<String,String> env = new Hashtable<String,String>();
				env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
				env.put(Context.PROVIDER_URL, workManagerSvr);
				initialContext = new InitialContext(env);
			} else {
				initialContext = new InitialContext();
			}				
		} catch(Exception t) {
			logger.debug("Cannot get initial context of work manager", t);
		}
		if (initialContext != null) {
			this.workManager = (WorkManager)initialContext.lookup(workManagerJndiName);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.telus.csm.essc.manager.ICommonJWorkManager#processTasks(java.util
	 * .Collection, long)
	 */
	@Override
	public Collection<Work> processTasks(Collection<Work> tasks, long timeout) throws WorkException,InterruptedException {
		Collection<WorkItem> workItems = new ArrayList<WorkItem>();
		Collection<Work> out = new ArrayList<Work>();
		try {
			for (Work task : tasks) {
				WorkItem workItem = workManager.schedule(new TransactionWork(task));
				workItems.add(workItem);
			}
			workManager.waitForAll(workItems, timeout);
			for (WorkItem workItem : workItems) {
				if (workItem.getStatus() == WorkEvent.WORK_COMPLETED
						|| workItem.getStatus() == WorkEvent.WORK_REJECTED) {
					Work work = workItem.getResult();
					if (work != null) {
						out.add(((TransactionWork)work).getWork());
					}
				}
			}
		} catch (WorkException e) {
			throw e;
		} catch (InterruptedException e) {
			throw e;
		}
		return out;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.telus.csm.essc.manager.ICommonJWorkManager#processTasks(java.util
	 * .Collection)
	 */

	@Override
	public Collection<Work> processTasks(Collection<Work> tasks) throws WorkException,InterruptedException {

		return processTasks(tasks, ApplicationProperties.getWorkManagerMaxTimeout());
	}

	@Override
	public void processAsyncTasks(Collection<Work> tasks) {
		try {
			for (Work task : tasks) {
				workManager.schedule(new AsyncTransactionWork(task));
			}
		} catch (WorkException e) {
			logger.error("processAsyncTasks", e);
		} catch (IllegalArgumentException e) {
			logger.error("processAsyncTasks", e);
		}

	}

	private static class TransactionWork implements Work {

		private Work task;
		private Map<?, ?> ctx = null;
		
		protected String threadIdSep = ">";
		
		public TransactionWork(Work task) {
			this.task = task;
			ctx = MDC.getContext();
		}

		@Override
		public void run() {
			
			ExecutionLogUtil.initThreadLog();
			
			if (ctx != null) {
				for ( Entry<?, ?> entry : ctx.entrySet()) {
					String key = entry.getKey().toString();
					String value = entry.getValue().toString();
					if ("threadId".equals(key)) {
						value += threadIdSep + Thread.currentThread().getId();
					}
					MDC.put(key, value);
				}
			}
			
			task.run();
		}

		@Override
		public boolean isDaemon() {
			return task.isDaemon();
		}

		@Override
		public void release() {
			task.release();
		}

		public Work getWork() {
			return task;
		}

	}

	private static class AsyncTransactionWork extends TransactionWork {

		public AsyncTransactionWork(Work task) {
			super(task);
			threadIdSep = "^";
		}

	}

}
