package com.telus.csm.ewlnsc.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.management.MBeanServer;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.grid.repository.WirelineCacheControlRepository;
import com.telus.framework.config.ldap.LdapNodeProvider;

import weblogic.version;

/**
 * Hello world!
 *
 */
public class App {
	private static Logger logger = Logger.getLogger(App.class);
	private static String watermark;
	
	private App(){
		
	}
	
	public static String getPingInfo(Date startDate) {

		// environment.properties
		String environment = "?";

		try {
			Properties envprop = new Properties();
			envprop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("environment.properties"));
			environment = getPropertiesList(envprop);		
		} catch( FileNotFoundException e ) {
			logger.error("Error reading environment.properties", e);
		} catch (IOException e){
			logger.error("Error reading environment.properties", e);
		}

		// appCtx.properties
		String appName = "?";
		String appVersion = "?";
	
		String appCtx = "?";

		try {
			Properties appCtxProp = new Properties();
			appCtxProp.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("/appCtx.properties"));
			
			String t = appCtxProp.getProperty("appName");
			if (t != null ) {
				appName = t;
			}

			t = appCtxProp.getProperty("appVersion");
			if (t != null ) {
				appVersion = t;
			}
			
			appCtx = getPropertiesList(appCtxProp);
		
		} catch( FileNotFoundException e ) {
			logger.error("Error reading appCtx.properties", e);
		} catch (IOException e){
			logger.error("Error reading appCtx.properties", e);
		}

		
		String domain = getDomain();

		String serverName = getServerName();
		
		String machine = "?";
	
		try {
			InetAddress x = InetAddress.getLocalHost();
			machine = x.toString();
		} catch( UnknownHostException e1 ) {
			logger.error("Error reading InetAddress.getLocalHost()", e1);
		}
	
		String jvmStartDate = "?";
		try {
			jvmStartDate = (new Date(ManagementFactory.getRuntimeMXBean().getStartTime())).toString();
		} catch( IllegalArgumentException e ) {
			logger.error("Error reading jvmStartDate", e);
		}
	
		String logFileDir = ApplicationProperties.getLogFileDir();
		
		String cacheSuffix = "?"; 
		String cacheLastUpdate = "?"; 
		try {
			WirelineCacheControlRepository controlRepo = WirelineCacheControlRepository.getInstance();
			cacheSuffix = controlRepo.getCacheSuffix();
			if (cacheSuffix == null) {
				cacheSuffix = "Grid not loaded";
			} else {
				cacheLastUpdate = controlRepo.getLastUpdated().toString();
			}	
	
		} catch( Exception e ) {
		}		

		return appName + " " + appVersion + "\nLoaded on: " + startDate

				+ "\n\nDomain: " + domain
				+ "\nServer: " + serverName
				+ "\nMachine: " + machine
				+ "\nWeblogic: " + version.getVersions()
				+ "\nJava: " + System.getProperty("java.version")
				+ "\nStarted on: " + jvmStartDate

				+ "\n\nappCtx.properties:\n" + appCtx

				+ "\nenvironment.properties:\n" + environment

				+ "\nLDAP URL: " + System.getProperty(LdapNodeProvider.PROVIDER_URL_KEY)

				+ "\nLog dir: " + logFileDir

				+ "\n\nGrid cache suffix: " + cacheSuffix + "\nGrid last updated: " + cacheLastUpdate;

	}

	private static String getServerName() {
		String serverName = "?";
		try {
			
			String t = System.getProperty("weblogic.Name");
			if (t != null ) {
				serverName = t;
			}

		} catch( SecurityException e ) {
			logger.error("Error reading weblogic.Name", e);
		} catch (IllegalArgumentException e){
			logger.error("Error reading weblogic.Name", e);
		}
		return serverName;
	}

	private static String getDomain() {
		String domain = "?";
		try {
			InitialContext ctx = new InitialContext();
			MBeanServer server = (MBeanServer)ctx.lookup("java:comp/env/jmx/runtime");
//			MBeanServer server = (MBeanServer)ctx.lookup("java:comp/env/jmx/domainRuntime")
			String t = server.getDefaultDomain();
			if (t != null ) {
				domain = t;
			}
		} catch( NamingException e ) {
			logger.error("Error reading java:comp/env/jmx/runtime", e);
		}
		return domain;
	}

	public static String getSystemPropertiesInfo() {
		
		return getPropertiesList(System.getProperties());
		
	}
	
	public static String getPropertiesList(Properties prop) {
		
		if (prop == null){
			return "";
		}
		
		StringBuilder result = new StringBuilder();
		
		Set<Entry<Object, Object>> x = prop.entrySet();
		for (Entry<Object, Object> entry : x) {
			result.append(entry.getKey() + "=" + entry.getValue() + "\n");
		}
		
		return result.toString();
	}
	
	public static String getWatermark() {
		
		if (watermark != null) {
			return watermark;
		}
		
		String machine = "?";
		String deploymentFile = "?";
		String buildLabel = "?";
		String buildDate = "?";
		
		try {
			InetAddress x = InetAddress.getLocalHost();
			machine = x.toString();
		} catch( Exception e1 ) {
			logger.error("Error reading InetAddress.getLocalHost()", e1);
		}

		try {
			Properties appCtxProp = new Properties();
			appCtxProp.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(("/appCtx.properties")));
			
			String t = appCtxProp.getProperty("buildLabel");
			if (t != null ) {
				buildLabel = t;
			}

			t = appCtxProp.getProperty("buildDate");
			if (t != null ) {
				buildDate = t;
			}
			
			t = appCtxProp.getProperty("deploymentFile");
			if (t != null ) {
				deploymentFile = t;
			}
		} catch( Exception e ) {
			logger.error("Error reading appCtx.properties", e);
		}

		watermark = "Machine=" + machine + "; DeploymentFile=" + deploymentFile + "; BuildLabel=" + buildLabel + "; BuildDate=" + buildDate;
		return watermark;
	}

}
