package com.telus.csm.ewlnss.adapter.util;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * Allows logging outgoing requests and the corresponding responses.
 * Requires the use of a {@link org.springframework.http.client.BufferingClientHttpRequestFactory} to log
 * the body of received responses.
 */
public class GzipLoggingClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

	private static final String REQUEST_TO_STR = " request to ";
	/** the logger. */
    public static final Logger LOGGER = Logger.getLogger(GzipLoggingClientHttpRequestInterceptor.class);

    private volatile boolean loggedMissingBuffering;
	private static final int WINDOWS_LOG_RESPONSE_LENGTH = 4000;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        logRequest(request, body);
        long reqTime = System.currentTimeMillis();
        ClientHttpResponse response = execution.execute(request, body);
        HttpHeaders headers = response.getHeaders();

        // you'd want to check if the value needs to be changed
        if (headers.containsKey("Content-Type")) {
            headers.remove("Content-Type");
        }
    	long elapsedTm = System.currentTimeMillis() - reqTime;
        logResponse(request, response, elapsedTm);
        return response;
    }

    protected void logRequest(HttpRequest request, byte[] body) {
        
    	StringBuilder builder = new StringBuilder();
        builder.append(AdapterLogUtil.getAdapterMethodName());
        builder.append("Sending ");
        builder.append(request.getMethod());
        builder.append(REQUEST_TO_STR);
        builder.append(request.getURI());
        
        HashMap<String, List<String>> customHeaders = new HashMap<String, List<String>>();
        
        // Extract custom headers set by application for logging  
        for (Entry<String, List<String>> v : request.getHeaders().entrySet()) {
        	String headerKey = v.getKey();
        	if (!"Authorization".equals(headerKey) && !"Accept-Charset".equals(headerKey)  && !"Accept".equals(headerKey) && !"Content-Length".equals(headerKey)) {
        		customHeaders.put(v.getKey(), v.getValue());
        	}
        }
        builder.append(" Custom Headers: " + customHeaders);
        
        if (body.length > 0 && hasTextBody(request.getHeaders())) {
            String bodyText = new String(body);
            builder.append(" Body: ").append(bodyText);
        }
        LOGGER.info(builder.toString());
    }

    protected void logResponse(HttpRequest request, ClientHttpResponse response, long	elapsedTm) {
            try {
                StringBuilder builder = new StringBuilder();
                builder.append(AdapterLogUtil.getAdapterMethodName());
                builder.append("Elapsed time: " + elapsedTm + " ms, ");
                builder.append("Received \"");
                builder.append(response.getRawStatusCode());
                builder.append(" ");
                builder.append(response.getStatusText());
                builder.append("\" response for ");
                builder.append(request.getMethod());
                builder.append(REQUEST_TO_STR);
                builder.append(request.getURI());
                HttpHeaders responseHeaders = response.getHeaders();
                long contentLength = responseHeaders.getContentLength();
                if (contentLength != 0) {
                	//contentLength == -1 means unknown length
                    if (hasTextBody(responseHeaders) && isBuffered(response)) {
                        String bodyText = StreamUtils.copyToString(response.getBody(), Charset.forName("UTF-8"));
                        int bodyLength = bodyText.length();
                        String osName = System.getProperty("os.name");
                        if (osName == null) {
                        	osName = "";
                        }
                        if (!osName.toLowerCase().startsWith("windows") || bodyLength <= WINDOWS_LOG_RESPONSE_LENGTH ) {
                        	builder.append(": [").append(bodyText).append("]");
                        	LOGGER.info(builder.toString());
                        } else {
                        	// split response > 2000 into multiple log records (logging failed for String.length > 3500)
                        	builder.append(": response.length>" + WINDOWS_LOG_RESPONSE_LENGTH + " on multiple lines [");
                        	int i;
                        	for (i = 0; i+WINDOWS_LOG_RESPONSE_LENGTH < bodyLength; i += WINDOWS_LOG_RESPONSE_LENGTH) {
                        		builder.append(bodyText.substring(i, i+WINDOWS_LOG_RESPONSE_LENGTH));
                            	LOGGER.info(builder.toString());
                            	builder = new StringBuilder();
                        	}
                        	builder.append(bodyText.substring(i, bodyLength)).append("]");
                        	LOGGER.info(builder.toString());
                        }
                    } else {
                        appendLength(builder, contentLength);
                        MediaType contentType = responseHeaders.getContentType();
                        appendContentType(builder, contentType);
                        LOGGER.info(builder.toString());
                    }
                } else {
                	LOGGER.info(builder.toString());
                }
            } catch (IOException e) {
            	LOGGER.error("Failed to log response for " + request.getMethod() + REQUEST_TO_STR + request.getURI(), e);
            }
    }

	private void appendContentType(StringBuilder builder, MediaType contentType) {
		if (contentType != null) {
		    builder.append(" and content type ").append(contentType);
		} else {
		    builder.append(" and unknown context type");
		}
	}

	private void appendLength(StringBuilder builder, long contentLength) {
		if (contentLength == -1) {
		    builder.append(" with content of unknown length");
		} else {
		    builder.append(" with content of length ").append(contentLength);
		}
	}

    protected boolean hasTextBody(HttpHeaders headers) {
        MediaType contentType = headers.getContentType();
        if (contentType != null) {
            String subtype = contentType.getSubtype();
            return "text".equals(contentType.getType()) || "xml".equals(subtype) || "json".equals(subtype);
        }
        return false;
    }

    protected boolean isBuffered(ClientHttpResponse response) {
        // class is non-public, so we check by name
    	String responseClassName = response.getClass().getName();
        boolean buffered = "org.springframework.http.client.BufferingClientHttpResponseWrapper".equals(responseClassName);

        if (!buffered && !loggedMissingBuffering) {
        	LOGGER.warn( "Can't log HTTP response bodies, as you haven't configured the RestTemplate with a BufferingClientHttpRequestFactory");
            loggedMissingBuffering = true;
        }
        return buffered;
    }

}