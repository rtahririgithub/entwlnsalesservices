package com.telus.csm.ewlnsc.monitor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.task.TaskBase;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

/**
 * @author x159146
 *
 */
public class ComponentMonitorTask extends TaskBase {
	
	public static final String SEP = "\n";
	public static final String DET_SEP_BEGIN = "{";
	public static final String DET_SEP_END = "}";
	public static final String FAILURE = "FAILURE";
	public static final String SUCCESS = "SUCCESS";
	public static final String WARNING = "WARNING";


	static Logger logger = Logger.getLogger(ComponentMonitorTask.class);
	
	private IAdapterBase adapter = null;
	private long elapseTime;
	private String response;
	
	private List<String> failureDetails = new ArrayList<String>();
	protected boolean runSuccess = true;

	
	public ComponentMonitorTask(IAdapterBase adapter) {
		super();
		this.adapter = adapter;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	protected void execute() {
		logger.debug("ComponentMonitorTask.run() - " + getName());
		long startTime = System.currentTimeMillis(); 
		try {
			response = adapter.ping();
		} catch (Exception e) {
			fail(e);
		}
		elapseTime = System.currentTimeMillis() - startTime;
	}

	public long getElapseTime() {
		return elapseTime;
	}
	
	protected void fail( Throwable e ) {
		runSuccess = false;
		addFailureDetails(e);
	}
	
	protected void addFailureDetails(Throwable e) {
		StringWriter strWriter = new StringWriter(); 
		PrintWriter pw =  new PrintWriter( strWriter ) ;
		e.printStackTrace( pw );
		pw.flush();
		StringBuffer buffer = strWriter.getBuffer();
		failureDetails.add( buffer.toString() );
		pw.close();
	}
	
	public String getResult(boolean isShowResponse) {
		String ret = "*" + getName() + ":" + SEP + adapter.getEndpointAddress() + SEP; 
		if (runSuccess) {
			ret += SUCCESS;
		} else {
			ret += FAILURE + getFailureDetails();
		}
		
		ret += "(" + getElapseTime() + " milliseconds)";
		
		if (isShowResponse && runSuccess) {
			ret += SEP + response;
		}

		return ret;
	}
	
	public String getFailureDetails() {
		StringBuffer ret = new StringBuffer();
		for(String detail : failureDetails) {
			ret.append(SEP + DET_SEP_BEGIN + detail + DET_SEP_END);
		}
		return ret.toString();
	}

	public String getName() {
		return adapter.getClass().getInterfaces()[0].getSimpleName();
	}


}
