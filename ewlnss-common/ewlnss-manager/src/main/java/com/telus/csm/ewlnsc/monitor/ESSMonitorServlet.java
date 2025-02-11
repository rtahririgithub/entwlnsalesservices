package com.telus.csm.ewlnsc.monitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.util.App;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.ExecutionLogUtil;
import com.telus.csm.ewlnsc.util.cacheadapter.CacheAdapterFactory;

public class ESSMonitorServlet extends HttpServlet {
	
	private static final long serialVersionUID = -8724681704315158150L;

	private static Logger logger = Logger.getLogger(ESSMonitorServlet.class);
	private static final Date startDate = new Date();

	public ESSMonitorServlet() {
		super();
		//force loading of config to enable logging
		ExecutionLogUtil.initThreadLog();
		ESSMonitor.postStartup(startDate);
		logger.info("Build version:\n" + App.getPingInfo(startDate));
		logger.info("System Properties:\n" + App.getSystemPropertiesInfo());

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		ExecutionLogUtil.initThreadLog();
		res.setContentType("text/plain");
		PrintWriter out = res.getWriter();

		String type = req.getParameter("type");
		
		if ("cacheLoad".equalsIgnoreCase(type)) {
			out.println(ESSMonitor.loadCache(req.getParameter("name"), req.getInputStream()));
		}
		
		return;

	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		ExecutionLogUtil.initThreadLog();
		res.setContentType("text/plain");
		PrintWriter out = res.getWriter();

		String type = req.getParameter("type");
		
		if ("version".equalsIgnoreCase(type)) {
			out.println(App.getPingInfo(startDate));
			return;
		}

		if ("alert".equalsIgnoreCase(type)) {
			String count = req.getParameter("count");
			Integer ct = null;
			try {
				if (!StringUtils.isEmpty(count)) {
					ct = Integer.valueOf(count);
				}
			} catch (NumberFormatException e) {
				out.println("Invalid count: " + e.toString());
				return;
			}
			out.println(ESSMonitor.getOperationAlerts(req.getParameter("operation"), ct));
			return;
		}

		if ("startUp".equalsIgnoreCase(type)) {
			String count = req.getParameter("count");
			Integer ct = null;
			try {
				ct = Integer.valueOf(count);
			} catch (NumberFormatException e) {
				logger.error(e);
			}
			out.println(ESSMonitor.getOperationAlerts("startUp", ct));
			return;
		}

		if ("adapter".equalsIgnoreCase(type)) {
			
			String[] names = req.getParameterValues("name");
			ArrayList<String> adapterNames = new ArrayList<String>();
			
			if (names != null) {
				for (int i = 0; i < names.length; i++) {
					adapterNames.add(names[i]);
				}
			}
			
			if (adapterNames.isEmpty()) {
				adapterNames.add("*");
			}
			
			boolean ping = "true".equalsIgnoreCase(req.getParameter("ping"));
			
			out.println(ESSMonitor.getAdapterList(ping, adapterNames));

			return;
		}
		
		if ("cacheNames".equalsIgnoreCase(type)) {
			out.println(ESSMonitor.getCacheNames(req.getParameter("prefix")));
			return;
		}

		if ("cacheKeys".equalsIgnoreCase(type)) {
			out.println(ESSMonitor.getCacheKeys(req.getParameter("name"), req.getParameter("prefix")));
			return;
		}

		if ("cacheUnload".equalsIgnoreCase(type)) {
			res.setContentType("application/json");			
			out.println(ESSMonitor.unloadCache(req.getParameter("name"), req.getParameter("key")));
			return;
		}

		if ("cache".equalsIgnoreCase(type)) {
			if ("true".equalsIgnoreCase(req.getParameter("delete"))) {
				out.println(ESSMonitor.deleteCacheObject(req.getParameter("name"), req.getParameter("key")));
			} else {
				out.println(ESSMonitor.getCacheObject(req.getParameter("name"), req.getParameter("key")));
			}
			return;
		}
		
		if ("cacheSearch".equalsIgnoreCase(type)) {
			out.println(ESSMonitor.searchCacheObject(req.getParameter("name"), req.getParameter("value")));
			return;
		}
		
		if ("ldap".equalsIgnoreCase(type)) {
			
			String key = "${" + req.getParameter("key") + "}";

			out.println("\n" + key + "=" + ApplicationProperties.getConfigString(key));
			return;
		}

		if ("runWirelineLoader".equalsIgnoreCase(type)) {
			String dir = req.getParameter("dir");
			out.println(ESSMonitor.runWirelineLoader(dir));
			return;
		}
		
		
		out.println("Usage:");
		out.println("1) Version: .../monitor?type=version");
		out.println("2) Alert:   .../monitor?type=alert&operation={String:optional}&count={int:optional}");
		out.println("3) Running Apps: .../monitor?type=startUp");
		out.println("4) Adapter: .../monitor?type=adapter&name={String:optional}&ping={boolean:optional}");
		out.println("5) Cache Names: .../monitor?type=cacheNames&prefix={String:optional}");
		out.println("6) Cache Keys: .../monitor?type=cacheKeys&name={String:optional}&prefix={String:optional}");
		out.println("7) Cache Object: .../monitor?type=cache&name={String:optional}&key={String}&delete={boolean:optional}");
		out.println("8) Search Cache Object: .../monitor?type=cacheSearch&name={String:optional}&value={String}");
		out.println("9) Unload Cache as JSON: .../monitor?type=cacheUnload&name={String}");
		out.println("10) Load Cache (Post JSON string): .../monitor?type=cacheLoad&name={String}");
		out.println("   6 - 10) name is the cache name, default is " + CacheAdapterFactory.SESSION_CACHE_NAME + " - session cache");
		out.println("11) Ldap value: .../monitor?type=ldap&key=.../.../...");
		out.println("12) Run WirelineLoader: .../monitor?type=runWirelineLoader&dir={String}");
		
		return;
	}

}
