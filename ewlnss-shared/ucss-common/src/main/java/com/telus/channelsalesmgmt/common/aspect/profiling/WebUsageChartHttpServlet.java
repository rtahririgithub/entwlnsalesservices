/*
 * Author: Jason Li (t840227)
 * Contact: jason.li2@telus.com
 */
package com.telus.channelsalesmgmt.common.aspect.profiling;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weblogic.wsee.util.StringUtil;

import com.telus.channelsalesmgmt.common.util.LoggerUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiceUsageChartHttpServlet.
 */
public class WebUsageChartHttpServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant ServletParam_UsageChartPageTitle. */
	private static final String ServletParam_UsageChartPageTitle = "UsageChartPageTitle";
	
	/** The Constant ServletParam_UsageChartTitle. */
	private static final String ServletParam_UsageChartTitle = "UsageChartTitle";
	
	/** The Constant ServletParam_ServiceUsageSubTitle. */
	private static final String ServletParam_WebUsageSubTitle = "WebUsageChartSubTitle";
	
	/** The usage chart page title. */
	private String usageChartPageTitle = "Web Daily Usage Chart";
	
	/** The usage chart title. */
	private String usageChartTitle = "Web Usage Chart";
	
	/** The service usage sub title. */
	private String webUsageSubTitle = null;
	
	/** The is initialized. */
	private boolean isInitialized = false;
	
	/** The Constant css. */
	public static final String css = 
		"html,body{" +
		"  color:#000;" +
		"  background:#fff;" +
		"}" +
		"#boundary{font-size:.9em;" +
		"  margin:0 auto;width:70%;" +
		"  min-width:700px;" +
		"  position:relative;" +
		"}" +
		"#content,#textcontent{background:#fff;}" +
		"h1{" +
		"  font-size:180%;" +
		"  color:#060;" +
		"}" +
		"h2{" +
		"  font-size:120%;" +
		"  font-family:calibri,helvetica,arial,sans-serif;" +
		"}" +
		"#textcontent{" +
		"  font-family:helvetica,arial,sans-serif;" +
		"  padding:1em;" +
		"}";
	
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		String methodName = "init";
		
		super.init(config);
		try {
		    Field defaultHeadlessField = java.awt.GraphicsEnvironment.class.getDeclaredField("defaultHeadless");
		    defaultHeadlessField.setAccessible(true);
		    defaultHeadlessField.set(null,Boolean.FALSE);
		    Field headlessField = java.awt.GraphicsEnvironment.class.getDeclaredField("headless");
		    headlessField.setAccessible(true);
		    headlessField.set(null,Boolean.TRUE);		    
		    
		    usageChartPageTitle = getServletConfig().getInitParameter(ServletParam_UsageChartPageTitle);
		    usageChartTitle = getServletConfig().getInitParameter(ServletParam_UsageChartTitle);
			webUsageSubTitle = getServletConfig().getInitParameter(ServletParam_WebUsageSubTitle);
			isInitialized = true;
		} catch (Exception ex) {
			LoggerUtil.getLogger(this).error(-1, methodName, "Exception caught when setting awt headless", ex);
		} 
	}
	
	/** The saved current content. */
	private static SavedUsageHttpContent savedCurrentContent = new SavedUsageHttpContent();
		
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String methodName = "doGet";
		
		response.setContentType("text/html");
		
		StringBuffer content = new StringBuffer();
		content.append("<html><head><style>")
			.append(css)
			.append("</style></head>");
		
		content.append("<body>");
		
		try {
			if( !isInitialized ) {
				content.append("GraphicsEnvironment cannot be initialized.");
				return;
			}
			
			content.append(getCurrentContent(savedCurrentContent, null));
		} catch (Exception ex) {
			LoggerUtil.getLogger(this).error(-1, methodName, "Exception caught when generating usage chart", ex);
		} finally {
			content.append("</body></html>");
			
			PrintWriter out = response.getWriter();
			out.println(content.toString());
			out.close();
		}
	}
	
	/**
	 * Gets the current content.
	 *
	 * @param savedContent the saved content
	 * @param classMethodIndexSet the class method index set
	 * @return the current content
	 * @throws Exception the exception
	 */
	private String getCurrentContent(SavedUsageHttpContent savedContent, Set<String> classMethodIndexSet) throws Exception {
		long now = Calendar.getInstance().getTimeInMillis();
		if( savedContent.getSavedCurrentContent() != null && now - savedContent.getLastTimeSavedCurrentContent() < 60000 ) {
			return savedContent.getSavedCurrentContent();
		}
		
		StringBuffer content = new StringBuffer();
		
		content.append("<center><h1>").append(usageChartPageTitle).append("</h1></center>").append("\r\n");
		
		try {
			content.append("<h2><strong>Host:</strong> ").append(InetAddress.getLocalHost().getHostName()).append("</h2>\r\n");
		} catch (UnknownHostException x) {
		}
		content.append("<h2><strong>Observation current date/time:</strong> " ).append(Calendar.getInstance().getTime()).append("</h2>\r\n");
		
		WebUsageChartManager webUsageChartManager = new WebUsageChartManager();
		String b64SvcUsageImg = webUsageChartManager.drawCombinedDailyUsageChartToBase64String(
				usageChartTitle, 
				webUsageSubTitle, DailyRollingWebUsageProfileManager.Factory.getInstance().getDailyStatisticsByHour());
		if( !StringUtil.isEmpty(b64SvcUsageImg) ) {
			content.append("<img src=\"data:image/png;base64,").append(b64SvcUsageImg).append("\" alt=\"chart image\" />");			
		} else {
			content.append("Nothing to display.");
		}
		
		return savedContent.updateSavedCurrentContent(content.toString(), now);
	}

}
