package com.telus.csm.ewlnsc.rest.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import com.telus.csm.ewlnsc.util.ExecutionLogUtil;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsis.rest.domain.GetAvailableAppointmentRequest;

/**
 * Servlet Filter implementation class RESTSevletFilter
 */
public class RESTSevletFilter implements Filter {


	static Logger logger = Logger.getLogger(RESTSevletFilter.class);

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		ExecutionLogUtil.initThreadLog();

		RESTServletResponseWrapper newResponse = new RESTServletResponseWrapper(
				(HttpServletResponse) response);
		long reqTm = System.currentTimeMillis();
		try {
			chain.doFilter(new RESTServletRequestWrapper(
					(HttpServletRequest) request), newResponse);
		} catch (ServletException e) {
			logger.error("Response: " + e.getMessage(), e);
			throw e;
		} catch (IOException e) {
			logger.error("Response: " + e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("Response: " + e.getMessage(), e);
			throw new ServletException(e);
		}
		long elapsedTime = System.currentTimeMillis() - reqTm;
		newResponse.logResponse(elapsedTime);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	private class RESTServletRequestWrapper extends HttpServletRequestWrapper {

		private String inputString = null;

		private RESTServletRequestWrapper(HttpServletRequest request) throws IOException {

			super(request);

			inputString = IOUtils.toString(request.getInputStream(), "UTF-8");
			// save the request Body - end

			// logging - start
			StringBuilder logBuilder = new StringBuilder();

			// log the url
			logBuilder.append(getMethod()).append(' ').append(getRequestURI());

			// log the query string
			String queryString = getQueryString();
			if (queryString != null)
				logBuilder.append('?').append(queryString);
			logBuilder.append(' ').append(getProtocol());
			
			// extract the transaction Id for log from query parm
			final String S1 = "sales-transaction-id=";
			boolean tranIdFound = false;
			if (queryString != null) {
				int idx = queryString.indexOf(S1);
				if (idx >= 0) {
					String tranId = queryString.substring(idx);
					tranId = tranId.replace(S1, "");
					idx = tranId.indexOf('&');
					if (idx >= 0) {
						tranId = tranId.substring(0, idx);
					}
					if (StringUtils.isNotBlank(tranId)) {
						MDC.put("transactionId", "Tran:" + tranId);
						tranIdFound = true;
					}
				}
			}

			// log the request body
			if (!inputString.isEmpty())
				logBuilder.append(" BODY ").append(inputString);
			
			// extract the transaction Id for log from operation header
			if (!tranIdFound && !inputString.isEmpty()) {
				GetAvailableAppointmentRequest objectWithOperationHeader = JsonUtil.parseJsonToObject(inputString, GetAvailableAppointmentRequest.class);
				if (objectWithOperationHeader != null && objectWithOperationHeader.getOperationHeader() != null) {
					String tranId = objectWithOperationHeader.getOperationHeader().getSalesTransactionId();
					if (!StringUtils.isBlank(tranId)) {
						MDC.put("transactionId", "Tran:" + tranId);
						tranIdFound = true;
					}
				}
			}

			logger.info("Request: " + logBuilder.toString());
			// logging - end

		}

		@Override
		public ServletInputStream getInputStream() throws IOException {
			
			//Un-escape HTML character encoding
			String inputUnescaped = StringEscapeUtils.unescapeHtml4(inputString);
			
			final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
					inputUnescaped.getBytes("UTF8"));

			ServletInputStream inputStream = new ServletInputStream() {
		        @Override
		        public int available() {
		            return byteArrayInputStream.available();
		        }

		        @Override
		        public int read() {
		            return byteArrayInputStream.read();
		        }

		        @Override
		        public int read(byte[] buf, int off, int len) {
		            return byteArrayInputStream.read(buf, off, len);
		        }
			};

			return inputStream;
		}
	}

	private class RESTServletResponseWrapper extends HttpServletResponseWrapper {

		RecordOutputStream outputStream;

		private int status = 404;

		private RESTServletResponseWrapper(HttpServletResponse response) {
			super(response);

		}

		public ServletOutputStream getOutputStream() throws IOException {

			if (outputStream == null) {
				outputStream = new RecordOutputStream();
			}

			return outputStream;
		}

		public PrintWriter getWriter() throws IOException {
			logger.info("Response not recorded from PrintWriter");

			return getResponse().getWriter();
		}

		private String getRecordedResponse() {
			String s = "";

			if (outputStream != null) {
				s = outputStream.getRecordedText();
			}

			return s;
		}

		public void setStatus(int sc) {
			status = sc;
			super.setStatus(sc);

		}

		public void setStatus(int sc, String sm) {
			status = sc;
			super.setStatus(sc, sm);
		}

		private class RecordOutputStream extends ServletOutputStream {

			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			ServletOutputStream origOutputStream;

			private RecordOutputStream() throws IOException {
				origOutputStream = getResponse().getOutputStream();
			}

			public void write(int input) throws IOException {
				bos.write(input);
				origOutputStream.write(input);
			}
			
			public void flush() throws IOException {
				super.flush();
				bos.flush();
				origOutputStream.flush();
			}

			public void close() throws IOException {
				super.close();
				bos.close();
				origOutputStream.close();
			}		

			private String getRecordedText() {
				return bos.toString();
			}
		}

		public void logResponse(long elapsedTime) {

			logger.info("Elapsed time: " + elapsedTime + " ms, Response: " + status + " " + getRecordedResponse());

		}
	}

}
