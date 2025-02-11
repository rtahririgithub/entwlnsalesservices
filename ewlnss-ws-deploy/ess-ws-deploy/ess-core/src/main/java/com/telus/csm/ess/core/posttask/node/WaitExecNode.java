package com.telus.csm.ess.core.posttask.node;

import org.python.modules.synchronize;

import com.telus.csm.ewlnsc.util.LoggerUtil;

public class WaitExecNode extends AbstractNode <Boolean> {
	private final LoggerUtil logger = LoggerUtil.getLogger(getClass());
	private long waitMillis;

	public WaitExecNode(long waitMillis) {
		super("WaitExecNode");
		this.waitMillis = waitMillis;
	}

	@Override
	public void run() {
		try {
			logger.info("run", "Waiting for [" + waitMillis + "] millis.");
			synchronized(this) {
				this.wait(waitMillis);
			}
			setResult(Boolean.TRUE);
		} catch (Exception e) {
			String msg = "Wait has been interrupted. ";
			logger.error("", "run", msg, e);
			handleException(e, msg);
			setResult(Boolean.FALSE);
		}
	}

}
