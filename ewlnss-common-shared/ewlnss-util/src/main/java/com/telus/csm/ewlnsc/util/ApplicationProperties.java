package com.telus.csm.ewlnsc.util;

import java.util.HashMap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.util.StringValueResolver;

/**
 * @author George Mak
 *
 *
 */
public class ApplicationProperties implements InitializingBean, EmbeddedValueResolverAware, EnvironmentAware {

	private Environment environment;
	private StringValueResolver resolver;

	private static ApplicationProperties applicationProperties;
	
	private static HashMap<String, String> configMap = new HashMap<String, String>();
	private static HashMap<String, String> propertyMap = new HashMap<String, String>();
	
	private static synchronized ApplicationProperties getInstance() {
		return applicationProperties;
	}
	
	public static synchronized void setInstance(ApplicationProperties applicationPropertiesIn) {
		applicationProperties = applicationPropertiesIn;
	}
	
	public ApplicationProperties() {
		super();
	}

	@Override
	public void setEmbeddedValueResolver(StringValueResolver resolverIn) {
		resolver = resolverIn;
	}
	
	@Override
	public void setEnvironment(Environment environmentIn) {
		environment = environmentIn;
	}

	public static String getConfigString(String parm) {
		
		String result = configMap.get(parm);
		if (result == null) {
			result = getInstance().resolver.resolveStringValue(parm);
			configMap.put(parm, result);
		}
		
		return result;
	}
	
	public static boolean getConfigBoolean(String parm) {
		return Boolean.parseBoolean(getConfigString(parm));
	}

	public static int getConfigInteger(String parm) {
		return Integer.parseInt(getConfigString(parm));
	}
	
	public static long getConfigLong(String parm) {
		return Long.parseLong(getConfigString(parm));
	}
	
	public static String getPropertyString(String parm) {
		String result = propertyMap.get(parm);
		if (result == null) {
			result = getInstance().environment.getProperty(parm);
			propertyMap.put(parm, result);
		}
		
		return result;
	}

	
	@Override
	public void afterPropertiesSet() {
		setInstance(this);
	}
	
	public static String getWorkManagerSvr() {
		return getConfigString("${connections/services/workManagerSvc/url}");
	}
	
	public static String getWorkManagerJndiName() {
		return getConfigString("${connections/services/workManagerSvc/jndiName}");
	}

	public static long getWorkManagerMaxTimeout() {
		return getConfigLong("${connections/services/workManagerSvc/maxTimeout}");
	}
	
	public static String getLogFileDir() {
		return getConfigString("${dirs/log4j/EnterpriseWLNSalesSvc}");
	}

	public static String getEssentialsV1InternalCID() {
		return getConfigString("${common/ttvCatalogIds/essentialsV1Internal}");
	}
	
	public static String getEssentialsV2InternalCID() {
		return getConfigString("${common/ttvCatalogIds/essentialsV2Internal}");
	}
	
	public static String getTVLiteInternalCID() {
		return getConfigString("${common/ttvCatalogIds/tvLiteInternal}");
	}
	
	public static String getPikTVInternalCID() {
		return getConfigString("${common/ttvCatalogIds/pikTVInternal}");
	}
	public static boolean isCacheMonitorEnabled() {
		return true;
	}


}
