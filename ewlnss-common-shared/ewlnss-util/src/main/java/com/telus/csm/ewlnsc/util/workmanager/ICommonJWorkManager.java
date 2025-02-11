/**
 * 
 */
package com.telus.csm.ewlnsc.util.workmanager;

import java.util.Collection;

import commonj.work.Work;
import commonj.work.WorkException;

/**
 * @author x131123
 *
 */
public interface ICommonJWorkManager {
	public Collection<Work> processTasks(Collection<Work> tasks, long timeout) throws WorkException,InterruptedException;
	public Collection<Work> processTasks(Collection<Work> tasks) throws WorkException,InterruptedException;
	public void processAsyncTasks(Collection<Work> tasks);
}
