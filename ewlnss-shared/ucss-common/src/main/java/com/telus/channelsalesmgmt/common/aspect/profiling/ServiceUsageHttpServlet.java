/*
 * Author: Jason Li (t840227)
 * Contact: jason.li2@telus.com
 */
package com.telus.channelsalesmgmt.common.aspect.profiling;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.telus.channelsalesmgmt.common.aspect.profiling.DailyRollingServiceUsageProfileManager.DailyStatisticsHistory;
import com.telus.channelsalesmgmt.common.util.LoggerUtil;

import weblogic.wsee.util.StringUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiceUsageHttpServlet.
 */
public class ServiceUsageHttpServlet extends ServiceUsageTimerManager {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant ServletParam_ServiceName. */
	private static final String ServletParam_ServiceName = "ServiceName";

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
		"}" +
		"table { background:#D3E4E5;" +
		" border:1px solid gray;" +
		" border-collapse:collapse;" +
		" color:#fff;" +
		" font:normal 12px verdana, arial, helvetica, sans-serif;" +
		"}" +
		"caption { border:1px solid #5C443A;" +
		" color:#5C443A;" +
		" font-weight:bold;" +
		" letter-spacing:20px;" +
		" padding:6px 4px 8px 0px;" +
		" text-align:center;" +
		" text-transform:uppercase;" +
		"}" +
		"td, th { color:#363636;" +
		" padding:.4em;" +
		"}" +
		"tr { border:1px dotted gray;" +
		"}" +
		"thead th, tfoot th { background:#5C443A;" +
		" color:#FFFFFF;" +
		" padding:3px 10px 3px 10px;" +
		" text-align:left;" +
		" text-transform:uppercase;" +
		"}" +
		"tbody td a { color:#363636;" +
		" text-decoration:none;" +
		"}" +
		"tbody td a:visited { color:gray;" +
		" text-decoration:line-through;" +
		"}" +
		"tbody td a:hover { text-decoration:underline;" +
		"}" +
		"tbody th a { color:#363636;" +
		" font-weight:normal;" +
		" text-decoration:none;" +
		"}" +
		"tbody th a:hover { color:#363636;" +
		"}" +
		"tbody td+td+td+td a { background-image:url('bullet_blue.png');" +
		" background-position:left center;" +
		" background-repeat:no-repeat;" +
		" color:#03476F;" +
		" padding-left:15px;" +
		"}" +
		"tbody td+td+td+td a:visited { background-image:url('bullet_white.png');" +
		" background-position:left center;" +
		" background-repeat:no-repeat;" +
		"}" +
		"tbody th, tbody td { text-align:left;" +
		" vertical-align:top;" +
		"}" +
		"tfoot td { background:#5C443A;" +
		" color:#FFFFFF;" +
		" padding-top:3px;" +
		"}" +
		".odd { background:#FFF;" +
		"}" +
		".amber { background:#FFFF99;" +
		"}" +
		".red { background:#FF0099;" +
		"}" +
		"tbody tr:hover { background:#99BCBF;" +
		" border:1px solid #03476F;" +
		" color:#000000;" +
		"}";
	
	
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
			if( "true".equalsIgnoreCase(request.getParameter("disable")) ) {
				ServiceUsageStatisticsManager.Factory.getInstance().setDisabled(true);
				content.append("<h2>Profiling disabled.</h2>").append("\r\n").toString();
				return;
			} else if( "false".equalsIgnoreCase(request.getParameter("disable")) ) {
				ServiceUsageStatisticsManager.Factory.getInstance().setDisabled(false);
				content.append("<h2>Profiling enabled.</h2>").append("\r\n").toString();
				return;
			}
			
			boolean isDisplayHistory = false;
			String daysBackForHistory = request.getParameter("d");
			if( !StringUtil.isEmpty(daysBackForHistory) ) {
				isDisplayHistory = true;
			}
			
			String serviceName = null;
			try {
				serviceName = getServletConfig().getInitParameter(ServletParam_ServiceName);
			} catch(Exception ex) {}
		      
			
			if( !StringUtil.isEmpty(serviceName) ) {
				content.append("<center><h1>").append(serviceName).append(" Usage Report").append("</h1></center>").append("\r\n");
			}
			
			if( isDisplayHistory ) {
				content.append(getHistoryContent(daysBackForHistory));				
			} else {
				content.append(getCurrentContent());						
			}
			
		} catch (Exception ex) {			
			LoggerUtil.getLogger(this).error(-1, methodName, "Exception caught when generating usage statistics", ex);
		} finally {
			content.append("</body></html>");
			
			PrintWriter out = response.getWriter();
			out.println(content.toString());
			out.close();
		}
	}

	/** The saved current content. */
	private static SavedUsageHttpContent savedCurrentContent = new SavedUsageHttpContent(); 

	/**
	 * Gets the current content.
	 *
	 * @param daysBackForHistory the days back for history
	 * @return the current content
	 * @throws Exception the exception
	 */
	private String getCurrentContent() throws Exception {
		long now = Calendar.getInstance().getTimeInMillis();
		if( savedCurrentContent.getSavedCurrentContent() != null && now - savedCurrentContent.getLastTimeSavedCurrentContent() < 30000 ) {
			return savedCurrentContent.getSavedCurrentContent();
		}
		
		StringBuffer content = new StringBuffer();
		content.append("<h1>Last hour usage profile</h1>").append("\r\n");
		ServiceUsageStatisticsManager hourlyManager = ServiceUsageStatisticsManager.Factory.getInstance();
		content.append(ServiceUsageStatisticsManager.getServiceUsageStatisticsFormattedString3(hourlyManager.getStatistics(), hourlyManager.getObservationStartDate()));
		
		content.append("<h1>Daily usage profile</h1>").append("\r\n");
		DailyRollingServiceUsageProfileManager dailyManager = DailyRollingServiceUsageProfileManager.Factory.getInstance();
		content.append(ServiceUsageStatisticsManager.getServiceUsageStatisticsFormattedString3(dailyManager.getDailyStatistics(), dailyManager.getObservationStartDate()));
		
		return savedCurrentContent.updateSavedCurrentContent(content.toString(), now);		
	}

	/** The saved history content. */
//	private static Map<String, String> savedHistoryContent = new HashMap<String, String>();
	
	/**
	 * Gets the history content.
	 *
	 * @param daysBackForHistory the days back for history
	 * @return the history content
	 * @throws Exception the exception
	 */
	private String getHistoryContent(String daysBackForHistory) throws Exception {
//		String historyContent = savedHistoryContent.get(daysBackForHistory);
		String historyContent;
//		if( historyContent != null ) return historyContent;
		
		StringBuffer content = new StringBuffer();
		DailyRollingServiceUsageProfileManager dailyManager = DailyRollingServiceUsageProfileManager.Factory.getInstance();		
		DailyStatisticsHistory dailyStatisticsHistory = dailyManager.getDailyStatistics(daysBackForHistory);
		if( dailyStatisticsHistory != null ) {
			content.append("<h1>Daily usage profile</h1>").append("\r\n");					
			content.append(ServiceUsageStatisticsManager.getServiceUsageStatisticsFormattedString3(dailyStatisticsHistory.getDailyStatistics(), dailyStatisticsHistory.getObservationStartDate()));
			
			historyContent = content.toString();
//			savedHistoryContent.put(daysBackForHistory, historyContent);
		} else {
			historyContent = content.append("<h2>History not found.</h2>").append("\r\n").toString();
		}
		return historyContent;
	}
}
