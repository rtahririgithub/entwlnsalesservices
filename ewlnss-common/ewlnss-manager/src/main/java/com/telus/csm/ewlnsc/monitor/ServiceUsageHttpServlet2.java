package com.telus.csm.ewlnsc.monitor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.telus.channelsalesmgmt.common.aspect.profiling.DailyRollingServiceUsageProfileManager;
import com.telus.channelsalesmgmt.common.aspect.profiling.SavedUsageHttpContent;
import com.telus.channelsalesmgmt.common.aspect.profiling.ServiceUsageStatisticsManager;
import com.telus.channelsalesmgmt.common.aspect.profiling.ServiceUsageTimerManager;
import com.telus.channelsalesmgmt.common.util.LoggerUtil;

import weblogic.wsee.util.StringUtil;

public class ServiceUsageHttpServlet2 extends ServiceUsageTimerManager {
	private static final long serialVersionUID = 1L;

	public static final String css = "html,body{  color:#000;  background:#fff;}#boundary{font-size:.9em;  margin:0 auto;width:70%;  min-width:700px;  position:relative;}#content,#textcontent{background:#fff;}h1{  font-size:180%;  color:#060;}h2{  font-size:120%;  font-family:calibri,helvetica,arial,sans-serif;}#textcontent{  font-family:helvetica,arial,sans-serif;  padding:1em;}table { background:#D3E4E5; border:1px solid gray; border-collapse:collapse; color:#fff; font:normal 12px verdana, arial, helvetica, sans-serif;}caption { border:1px solid #5C443A; color:#5C443A; font-weight:bold; letter-spacing:20px; padding:6px 4px 8px 0px; text-align:center; text-transform:uppercase;}td, th { color:#363636; padding:.4em;}tr { border:1px dotted gray;}thead th, tfoot th { background:#5C443A; color:#FFFFFF; padding:3px 10px 3px 10px; text-align:left; text-transform:uppercase;}tbody td a { color:#363636; text-decoration:none;}tbody td a:visited { color:gray; text-decoration:line-through;}tbody td a:hover { text-decoration:underline;}tbody th a { color:#363636; font-weight:normal; text-decoration:none;}tbody th a:hover { color:#363636;}tbody td+td+td+td a { background-image:url('bullet_blue.png'); background-position:left center; background-repeat:no-repeat; color:#03476F; padding-left:15px;}tbody td+td+td+td a:visited { background-image:url('bullet_white.png'); background-position:left center; background-repeat:no-repeat;}tbody th, tbody td { text-align:left; vertical-align:top;}tfoot td { background:#5C443A; color:#FFFFFF; padding-top:3px;}.odd { background:#FFF;}.amber { background:#FFFF99;}.red { background:#FF0099;}tbody tr:hover { background:#99BCBF; border:1px solid #03476F; color:#000000;}";

	public ServiceUsageHttpServlet2() {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String methodName = "doGet";

		response.setContentType("text/html");

		StringBuilder content = new StringBuilder();
		content.append("<html><head><style>")
				.append("html,body{  color:#000;  background:#fff;}#boundary{font-size:.9em;  margin:0 auto;width:70%;  min-width:700px;  position:relative;}#content,#textcontent{background:#fff;}h1{  font-size:180%;  color:#060;}h2{  font-size:120%;  font-family:calibri,helvetica,arial,sans-serif;}#textcontent{  font-family:helvetica,arial,sans-serif;  padding:1em;}table { background:#D3E4E5; border:1px solid gray; border-collapse:collapse; color:#fff; font:normal 12px verdana, arial, helvetica, sans-serif;}caption { border:1px solid #5C443A; color:#5C443A; font-weight:bold; letter-spacing:20px; padding:6px 4px 8px 0px; text-align:center; text-transform:uppercase;}td, th { color:#363636; padding:.4em;}tr { border:1px dotted gray;}thead th, tfoot th { background:#5C443A; color:#FFFFFF; padding:3px 10px 3px 10px; text-align:left; text-transform:uppercase;}tbody td a { color:#363636; text-decoration:none;}tbody td a:visited { color:gray; text-decoration:line-through;}tbody td a:hover { text-decoration:underline;}tbody th a { color:#363636; font-weight:normal; text-decoration:none;}tbody th a:hover { color:#363636;}tbody td+td+td+td a { background-image:url('bullet_blue.png'); background-position:left center; background-repeat:no-repeat; color:#03476F; padding-left:15px;}tbody td+td+td+td a:visited { background-image:url('bullet_white.png'); background-position:left center; background-repeat:no-repeat;}tbody th, tbody td { text-align:left; vertical-align:top;}tfoot td { background:#5C443A; color:#FFFFFF; padding-top:3px;}.odd { background:#FFF;}.amber { background:#FFFF99;}.red { background:#FF0099;}tbody tr:hover { background:#99BCBF; border:1px solid #03476F; color:#000000;}")
				.append("</style></head>");

		content.append("<body>");
		try {
			if ("true".equalsIgnoreCase(request.getParameter("disable"))) {
				ServiceUsageStatisticsManager.Factory.getInstance().setDisabled(true);
				content.append("<h2>Profiling disabled.</h2>").append("\r\n").toString();
			} else {
				if ("false".equalsIgnoreCase(request.getParameter("disable"))) {
					ServiceUsageStatisticsManager.Factory.getInstance().setDisabled(false);
					content.append("<h2>Profiling enabled.</h2>").append("\r\n").toString();
				} else {
					boolean isDisplayHistory = false;
					String daysBackForHistory = request.getParameter("d");
					if (!StringUtil.isEmpty(daysBackForHistory)) {
						isDisplayHistory = true;
					}

					String serviceName = null;
					try {
						serviceName = getServletConfig().getInitParameter("ServiceName");
					} catch (Exception ex) {
					}

					if (!StringUtil.isEmpty(serviceName)) {
						content.append("<center><h1>").append(serviceName).append(" Usage Report")
								.append("</h1></center>").append("\r\n");
					}

					if (isDisplayHistory) {
						content.append(getHistoryContent(daysBackForHistory));
					} else
						content.append(getCurrentContent());
				}
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

	private static SavedUsageHttpContent savedCurrentContent = new SavedUsageHttpContent();

	private String getCurrentContent() throws Exception {
		long now = Calendar.getInstance().getTimeInMillis();
		if ((savedCurrentContent.getSavedCurrentContent() != null)
				&& (now - savedCurrentContent.getLastTimeSavedCurrentContent() < 30000L)) {
			return savedCurrentContent.getSavedCurrentContent();
		}

		StringBuffer content = new StringBuffer();
		content.append("<h1>Last hour usage profile</h1>").append("\r\n");
		ServiceUsageStatisticsManager hourlyManager = ServiceUsageStatisticsManager.Factory.getInstance();
		content.append(ServiceUsageStatisticsManager.getServiceUsageStatisticsFormattedString3(
				hourlyManager.getStatistics(), hourlyManager.getObservationStartDate()));

		content.append("<h1>Daily usage profile</h1>").append("\r\n");
		DailyRollingServiceUsageProfileManager dailyManager = DailyRollingServiceUsageProfileManager.Factory
				.getInstance();
		content.append(ServiceUsageStatisticsManager.getServiceUsageStatisticsFormattedString3(
				dailyManager.getDailyStatistics(), dailyManager.getObservationStartDate()));

		return savedCurrentContent.updateSavedCurrentContent(content.toString(), now);
	}

	private String getHistoryContent(String daysBackForHistory) throws Exception {
		String historyContent;
		StringBuilder content = new StringBuilder();
		DailyRollingServiceUsageProfileManager dailyManager = DailyRollingServiceUsageProfileManager.Factory
				.getInstance();
		DailyRollingServiceUsageProfileManager.DailyStatisticsHistory dailyStatisticsHistory = dailyManager
				.getDailyStatistics(daysBackForHistory);
		if (dailyStatisticsHistory != null) {
			content.append("<h1>Daily usage profile</h1>").append("\r\n");
			content.append(ServiceUsageStatisticsManager.getServiceUsageStatisticsFormattedString3(
					dailyStatisticsHistory.getDailyStatistics(), dailyStatisticsHistory.getObservationStartDate()));

			historyContent = content.toString();
		} else {
			historyContent = "<h2>History not found.</h2>" + "\r\n";
		}
		return historyContent;
	}
}
