package com.telus.channelsalesmgmt.common.ws;

import static com.telus.channelsalesmgmt.common.monitoring.ResourceMonitor.resource_log;

import java.net.InetAddress;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.remoting.RemoteProxyFailureException;
import org.springframework.remoting.jaxws.JaxWsPortProxyFactoryBean;
import org.springframework.remoting.jaxws.JaxWsSoapFaultException;
import org.springframework.util.StringUtils;

import com.telus.channelsalesmgmt.common.monitoring.ResourceMonitor;

public class JaxWsClientFactory extends JaxWsPortProxyFactoryBean {
	
	private Log log = LogFactory.getLog(JaxWsClientFactory.class);
	
	private @Autowired Environment environment;
	
	private @Autowired ResourceMonitor resourceMonitor;
	
	@Override
	public void afterPropertiesSet() {
		// if calling localhost, do not authenticate
		try {
			Set<InetAddress> addresses = new HashSet<InetAddress>();
			String noAuthHostList = environment.getProperty("UCSS.no.auth.service.hosts");
			String[] noAuthHosts = StringUtils.commaDelimitedListToStringArray(noAuthHostList);
			for (String noAuthHost : noAuthHosts) {
				addresses.add(InetAddress.getByName(noAuthHost));
			}
			log.info("will not authorize service calls to these hosts: " + addresses);
			String host = new URL(getEndpointAddress()).getHost();
			InetAddress addrEndpoint = InetAddress.getByName(host);
			if (addresses.contains(addrEndpoint)) {
				setUsername(null);
				setPassword(null);
			}
		} catch (Exception e) {
			log.warn(e);
		}
		
		super.afterPropertiesSet();
	}

	@Override
	protected Object doInvoke(MethodInvocation invocation) throws Throwable {
		resource_log.info("invoking:\n" + getEndpointAddress() + " - " + invocation.getMethod().getName() + " - " + getUsername() + " / " + getPassword());
		Throwable error = null;
		
		try {
			try {
				String className = invocation.getMethod().getDeclaringClass().getSimpleName();
				String methodName = invocation.getMethod().getName();
				resourceMonitor.startMonitoringResource(className, methodName);
			} catch (Throwable t) {
				log.error("monitoring exception", t);
			}
			return super.doInvoke(invocation);
		} catch (RemoteProxyFailureException ex) {
			error = ex;
			throw ex;
		} catch (JaxWsSoapFaultException ex) {
			error = new RemoteAccessException("error when accessing method [" + invocation.getMethod().getName() + "] at [" + getEndpointAddress() + "]", ex);
			throw error;
		} catch (RemoteAccessException ex) {
			error = ex;
			throw ex;
		} catch (Throwable t) {
			error = new RemoteAccessException("error when accessing service at [" + getEndpointAddress() + "]", t);
			throw error;
		} finally {
			resourceMonitor.stopMonitoringResource(error);
		}
	}

}
