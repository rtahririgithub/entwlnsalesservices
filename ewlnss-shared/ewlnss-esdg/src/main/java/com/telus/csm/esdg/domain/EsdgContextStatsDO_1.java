package com.telus.csm.esdg.domain;

import java.io.Serializable;

public class EsdgContextStatsDO_1 implements Serializable {
	private static final long serialVersionUID = -547185727751954255L;
	
	protected String contextName;
	protected long contextAttempts = 0;
	protected long contextSize = 0;
	protected long contextPrepTimeInMills = 0;
	
	public EsdgContextStatsDO_1() {		
	}
	
	public EsdgContextStatsDO_1(String contextName) {
		this.contextName = contextName;
	}
	
	public void addAttempt(long contextSize, long contextPrepTimeInMills) {
		contextAttempts += 1;
		this.contextSize += contextSize;
		this.contextPrepTimeInMills += contextPrepTimeInMills;
	}
	
	public String getContextName() {
		return contextName;
	}
	
	public void setContextName(String contextName) {
		this.contextName = contextName;
	}
	
	public long getContextAttempts() {
		return contextAttempts;
	}

	public void setContextAttempts(long contextAttempts) {
		this.contextAttempts = contextAttempts;
	}

	public long getContextSize() {
		return contextSize;
	}
	
	public void setContextSize(long contextSize) {
		this.contextSize = contextSize;
	}
	
	public long getContextPrepTimeInMills() {
		return contextPrepTimeInMills;
	}
	
	public void setContextPrepTimeInMills(long contextPrepTimeInMills) {
		this.contextPrepTimeInMills = contextPrepTimeInMills;
	}	
}
