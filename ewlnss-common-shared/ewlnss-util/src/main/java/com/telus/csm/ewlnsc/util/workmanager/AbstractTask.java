package com.telus.csm.ewlnsc.util.workmanager;

import commonj.work.Work;

/**
 * Base class for implementing parallel processing, also shield the subs-class from Work interface so that we 
 * can adapt to other interface if needed. 
 * 
 * @author Michael Liao
 *
 */
public abstract class AbstractTask  implements Work {


	@Override
	public boolean isDaemon() {
		return false;
	}

	@Override
	public void release() {
	}

	public static class TaskResult<T> {
		
		private T value;

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}
	}
}
