package com.telus.csm.ewlnsc.soap.webservice;

import com.telus.channelsalesmgmt.common.aspect.AspectWeaver;
import com.telus.channelsalesmgmt.common.aspect.aspect.Aspect;
import com.telus.channelsalesmgmt.common.aspect.aspect.FlatProfilingAspect;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public abstract class SalesServiceSoapBase {

	private static LoggerUtil logger = LoggerUtil.getLogger(SalesServiceSoapBase.class);

	/** The default aspects. */
	private static Aspect[] keyServiceProfileAspects = null;

	static {
		// profile aspects
		Aspect flatProfilingAspect = new FlatProfilingAspect();
		keyServiceProfileAspects = new Aspect[1];
		keyServiceProfileAspects[0] = flatProfilingAspect;
	}

	private Object serviceProxy = null;

	public SalesServiceSoapBase() {

	}
	
	protected abstract Object getNewServiceImpl();
	
	protected Object getServiceProxy() {
		
		try {
			if (serviceProxy == null) {
				serviceProxy = AspectWeaver.weave(getNewServiceImpl(), keyServiceProfileAspects);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return serviceProxy;

	}
	
}
