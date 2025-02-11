package com.telus.csm.ewlnsc.util.workmanager;

import javax.management.MBeanServer;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class WorkManagerFactory {

	private static Boolean isRunningInWLS;
	private static final LoggerUtil logger = LoggerUtil.getLogger(WorkManagerFactory.class);
	
	private WorkManagerFactory(){
		
	}

	/**
	 * @return ICommonJWorkManager
	 * @throws NamingException
	 */
	public static ICommonJWorkManager getCommonJWorkManager() throws NamingException {
		if (isRunningInsideWeblogic()) {
			if (ApplicationProperties.getWorkManagerSvr() != null && ApplicationProperties.getWorkManagerSvr().length() > 0) {
				return CommonJWorkManager.getInstance(ApplicationProperties.getWorkManagerJndiName(), ApplicationProperties.getWorkManagerSvr());
			} else {
				return CommonJWorkManager.getInstance(ApplicationProperties.getWorkManagerJndiName());
			}
		} else {
			return new CommonJWorkManagerJdkImpl();
		}
	}

	public static synchronized boolean isRunningInsideWeblogic() {
		// this check run only once per ClassLoader
		if (isRunningInWLS == null) {
			try {
				InitialContext ctx = new InitialContext();
				MBeanServer server = (MBeanServer) ctx.lookup("java:comp/env/jmx/runtime");
				server.getDomains();
				isRunningInWLS = Boolean.TRUE;
			} catch (NamingException e) {
				logger.error(e);
				isRunningInWLS = Boolean.FALSE;
			}
		}

		return isRunningInWLS.booleanValue();
	}

}
