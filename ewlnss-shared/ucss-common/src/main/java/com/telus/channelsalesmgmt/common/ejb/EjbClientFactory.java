package com.telus.channelsalesmgmt.common.ejb;

import static com.telus.channelsalesmgmt.common.monitoring.ResourceMonitor.resource_log;
import static com.telus.channelsalesmgmt.common.util.UcssXStream.compactXStream;
import static javax.naming.Context.INITIAL_CONTEXT_FACTORY;
import static javax.naming.Context.PROVIDER_URL;

import java.util.Properties;

import javax.naming.NamingException;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.access.SimpleRemoteStatelessSessionProxyFactoryBean;
import org.springframework.jndi.JndiTemplate;
import org.springframework.util.Assert;

import com.telus.channelsalesmgmt.common.monitoring.ResourceMonitor;

/**
 * <p>
 * This factory will create an EJB client that logs EJB request and response,
 * and monitors I/O time for remote calls.
 * </p>
 * 
 * @see #setBusinessInterface(Class)
 * @see #setJndiName(String)
 * @see #setUrl(String)
 */
public class EjbClientFactory extends SimpleRemoteStatelessSessionProxyFactoryBean {

	private static final String WEBLOGIC_INITIAL_CONTEXT_FACTORY = "weblogic.jndi.WLInitialContextFactory";
	
	private final Log log = LogFactory.getLog(EjbClientFactory.class);
	
	private String url = null;

	private @Autowired ResourceMonitor resourceMonitor;

	@Override
	public void afterPropertiesSet() throws IllegalArgumentException, NamingException {
		Assert.notNull(url, "url is required");
		
		Properties env = new Properties();
		env.put(INITIAL_CONTEXT_FACTORY, WEBLOGIC_INITIAL_CONTEXT_FACTORY);
		env.put(PROVIDER_URL, url);
		
		super.setJndiEnvironment(env);
		super.setLookupHomeOnStartup(false);
		super.afterPropertiesSet();
	}

	/**
	 * <p>
	 * Environment-specific URL for calling the EJB
	 * </p>
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public void setJndiTemplate(JndiTemplate jndiTemplate) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setJndiEnvironment(Properties jndiEnvironment) {
		throw new UnsupportedOperationException();
	}

	@Override

	public void setLookupHomeOnStartup(boolean lookupHomeOnStartup) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object response;
		Throwable error = null;
		
		String className = getBusinessInterface().getSimpleName();
		String methodName = invocation.getMethod().getName();
		
		resource_log.info("invoking:\n" + getBusinessInterface().getSimpleName() + "." + methodName 
				+ " [url=" + url + ", jndiName=" + getJndiName() + "]");
		
		try {
			Object parms = invocation.getArguments();
			resource_log.info("input parameters\n" + compactXStream.toXML(parms));
		} catch (Throwable t) {
			log.error("could not record EJB mathod parameters", t);
		}
			
		resourceMonitor.startMonitoringResource(className, methodName);
		
		try {
			response = super.invoke(invocation);
		} catch (Throwable t) {
			error = t;
			resource_log.info("exception\n" + t.getMessage());
			throw t;
		} finally {
			resourceMonitor.stopMonitoringResource(error);
		}
		
		try {
			resource_log.info("response\n" + compactXStream.toXML(response));
		} catch (Throwable t) {
			log.error("could not record EJB response", t);
		}
		
		return response;
	}

	@Override
	public String toString() {
		return getBusinessInterface().getSimpleName() + " [url=" + url  + ", jndiName=" + getJndiName() + "]";
	}

}
