package com.telus.csm.ewlnss.adapter.common;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
//import org.apache.http.HttpRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telus.channelsalesmgmt.common.aspect.AspectWeaver;
import com.telus.channelsalesmgmt.common.aspect.aspect.Aspect;
import com.telus.csm.ewlnsc.aspect.ProfilingVariance;
import com.telus.csm.ewlnsc.aspect.ProxyProfilingAspect;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.util.AdapterLogUtil;
import com.telus.csm.ewlnss.adapter.util.GzipLoggingClientHttpRequestInterceptor;

public abstract class GzipRestServiceAdapter extends ServiceAdapterBase {
	
	/** Declaring the Logger **/
	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(GzipRestServiceAdapter.class);
	
	protected static final String RESPONSE_BODY_STR = "ResponseBody: ";
	
	public GzipRestServiceAdapter() {
	}

	public GzipRestServiceAdapter(AdapterFeatureDriver adapterFeatureDriver) {
		super(adapterFeatureDriver);
		//add any universal features for all REST adapters here
	}

	public abstract String getRootUrl();
	
	public String getEndpointAddress() {
		return getRootUrl();
	}
	
	/**
	 * This method provides a mean for subclass a to return Kong env  
s	 */
	protected String getKongEnv() {
		return "";
	}
	
	/**
	 * Subclass shall override this method to return true if the downstream call 
	 * requires OAuth2 token even though the API is not in Kong 
	 */
	protected boolean isOAuth2TokenRequird() {
		return false;
	}

	protected <T> ResponseEntity<T> get(String resourceTemplate, String resource, Class<T> responseType) {
		return getAdapterProxy().get(new ProfilingVariance(resourceTemplate), resource, responseType, new HashMap<String, String>(), null);
	}

	protected <T> ResponseEntity<T> get(String resourceTemplate, String resource, Class<T> responseType, Map<String, String> uriVariables) {
		return getAdapterProxy().get(new ProfilingVariance(resourceTemplate), resource, responseType, uriVariables, null);
	}
	
	protected <T> ResponseEntity<T> get(String resourceTemplate, String resource,Class<T> responseType, Map<String,String> uriVariables, Map<String,String> headerParams){
		return getAdapterProxy().get(new ProfilingVariance(resourceTemplate), resource, responseType, uriVariables, headerParams);
	}
	
	protected <T> ResponseEntity<T> post(String resourceTemplate, String resource, Object requestObject, Class<T> responseType) {
		return getAdapterProxy().post(new ProfilingVariance(resourceTemplate), resource, requestObject, responseType, new HashMap<String, String>(), null);
	}

	protected <T> ResponseEntity<T> post(String resourceTemplate, String resource, Object requestObject, Class<T> responseType, Map<String, String> uriVariables) {
		return getAdapterProxy().post(new ProfilingVariance(resourceTemplate), resource, requestObject, responseType, uriVariables, null);
	}
	
	protected <T> ResponseEntity<T> post(String resourceTemplate, String resource, Object requestObject, Class<T> responseType, Map<String, String> uriVariables, Map<String, String> headerParams) {
		return getAdapterProxy().post(new ProfilingVariance(resourceTemplate), resource, requestObject, responseType, uriVariables, headerParams);
	}

	protected <T> ResponseEntity<T> put(String resourceTemplate, String resource, Object requestObject) {
		return getAdapterProxy().put(new ProfilingVariance(resourceTemplate), resource, requestObject, new HashMap<String, String>(), null);
	}
	
	protected <T> ResponseEntity<T> put(String resourceTemplate, String resource, Object requestObject, Map<String, String> uriVariables) {
		return getAdapterProxy().put(new ProfilingVariance(resourceTemplate), resource, requestObject, uriVariables, null);
	}
	
	protected <T> ResponseEntity<T> put(String resourceTemplate, String resource, Object requestObject, Map<String, String> uriVariables, Map<String, String> headerParams) {
		return getAdapterProxy().put(new ProfilingVariance(resourceTemplate), resource, requestObject, uriVariables, headerParams);
	}
	
	protected <T> ResponseEntity<T> delete(String resourceTemplate, String resource,Class<T> responseType, Map<String,String> uriVariables){
		return getAdapterProxy().delete(new ProfilingVariance(resourceTemplate), resource, responseType, uriVariables, null);
	}
	
	private IRestServiceAdapterProxy getAdapterProxy() {
		
		IRestServiceAdapterProxy result = new RestServiceAdapterProxy();

		//apply profiling aspect
		try {
			Aspect[] profileAspects = new Aspect[] { new ProxyProfilingAspect(this.getClass().getSimpleName() + " " + getRootUrl()) };
			result = (IRestServiceAdapterProxy) AspectWeaver.weave(result, profileAspects);
		} catch (Exception e) {
			LOGGER.error(null, "getAdaptorProxy", "AspectWeaver.weave failed", e);
		}

		return result;
	}	

	// Interface for ProfilingAspect
	public interface IRestServiceAdapterProxy {
		abstract <T> ResponseEntity<T> get(ProfilingVariance resourceTemplate, String resource,Class<T> responseType, Map<String,String> uriVariables, Map<String,String> headerParams);
		abstract <T> ResponseEntity<T> delete(ProfilingVariance resourceTemplate, String resource, Class<T> responseType, Map<String, String> uriVariables, Map<String,String> headerParams);
		abstract <T> ResponseEntity<T> post(ProfilingVariance resourceTemplate, String resource, Object requestObject, Class<T> responseType, Map<String, String> uriVariables, Map<String, String> headerParams);
		abstract <T> ResponseEntity<T> put(ProfilingVariance resourceTemplate, String resource, Object requestObject, Map<String, String> uriVariables, Map<String, String> headerParams);
	}
	
	// Proxy inner class for ProfilingAspect
	public class RestServiceAdapterProxy implements IRestServiceAdapterProxy {
		
		@Override
		public <T> ResponseEntity<T> get(ProfilingVariance resourceTemplate, String resource,Class<T> responseType, Map<String,String> uriVariables, Map<String,String> headerParams){
			return doExchange(HttpMethod.GET, resource, null, responseType, uriVariables, headerParams);
		}
		
		@Override
		public <T> ResponseEntity<T> post(ProfilingVariance resourceTemplate, String resource, Object requestObject, Class<T> responseType, Map<String, String> uriVariables, Map<String, String> headerParams) {
			return doExchange(HttpMethod.POST, resource, requestObject, responseType, uriVariables, headerParams);
		}

		@Override
		public <T> ResponseEntity<T> put(ProfilingVariance resourceTemplate, String resource, Object requestObject, Map<String, String> uriVariables, Map<String, String> headerParams) {
			return doExchange(HttpMethod.PUT, resource, requestObject, null, uriVariables, headerParams);
		}
		
		@Override
		public <T> ResponseEntity<T> delete(ProfilingVariance resourceTemplate, String resource,Class<T> responseType, Map<String,String> uriVariables, Map<String,String> headerParams){
			return doExchange(HttpMethod.DELETE, resource, null, responseType, uriVariables, headerParams);
		}
		

		
		private <T> ResponseEntity<T> doExchange(HttpMethod method, String resource, Object requestObject, Class<T> responseType, Map<String, String> uriVariables, Map<String,String> headerParams) {
			String functionName="doExchange";
			LOGGER.enter(functionName);
			
			//logging request/response 

			HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
			        HttpClientBuilder.create().build());
			
			RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(clientHttpRequestFactory ));
			restTemplate.getMessageConverters()
	        .add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
			updateMessageConverters(restTemplate);
			List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
			interceptors.add(new GzipLoggingClientHttpRequestInterceptor());
			restTemplate.setInterceptors(interceptors);
			String url = getRootUrl();
			if (!StringUtils.isBlank(resource)) {
				url += "/" + resource ;
			}
			
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
			
			if (uriVariables != null) {
				Set<String> keySet = uriVariables.keySet();
				
				for(String key : keySet){
					builder.queryParam(key, uriVariables.get(key));
				}
			}

			URI completeUrl = builder.build().encode().toUri();
			
			String requestContext = "Method: " + method + " URI: " + completeUrl;
			
			LOGGER.info(functionName, requestContext);
			
			MediaType mediaType = null;
			if (method.equals(HttpMethod.POST) || method.equals(HttpMethod.PUT)) {
				// assume the request object is always JSON.
				// need to add logic to detect the media type if other media types are supported
				mediaType = MediaType.APPLICATION_JSON;
			}
			
			// Apply adapter features
			// Currently adapter features for RESTful services only manipulate Http headers.
			// we can change to pass the completeUrl for manipulation if required  
			Map<String,String> headerParams2 = getFeatures().apply(method, headerParams);
			HttpEntity<?> httpEntity = getHttpEntity(requestObject, headerParams2, mediaType);
			
			ResponseEntity<String> response;
			try {
				response = restTemplate.exchange(completeUrl, method, httpEntity, String.class);
				
				String respText = response.getBody();
				
				LOGGER.debug( AdapterLogUtil.getAdapterMethodName(), "  ResponseBody=" + respText );
				
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.setSerializationInclusion(Include.NON_NULL);
			    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			    Object responseObj = objectMapper.readValue(respText, responseType); 
			    
			    LOGGER.exit(functionName);
			    return new ResponseEntity<T>((T)responseObj,response.getStatusCode());
			    
			} catch (HttpStatusCodeException e) {
				// This parent exception covers
				//HttpClientErrorException - error 400
				//HttpServerErrorException - error 500
				LOGGER.error(e.getStatusCode().toString(), functionName, RESPONSE_BODY_STR + e.getResponseBodyAsString(), e);
				throw e;
			} catch (RuntimeException e) {
				LOGGER.error(functionName, "RuntimeException", LOGGER.getStackTrace(e));
				throw e;			
			} catch (Exception e) {
				LOGGER.error(functionName, "RuntimeException", LOGGER.getStackTrace(e));	
				throw  new RuntimeException(e) ;
			}
			
		}

		/*
		 * override this method to customize message converters
		 * the default is to have MappingJackson2HttpMessageConverter for APPLICATION_JSON
		 */
		protected void updateMessageConverters(RestTemplate restTemplate) {
			List<HttpMessageConverter<?>> msgConverters = restTemplate.getMessageConverters();
			if(msgConverters != null) {
				for(Iterator<HttpMessageConverter<?>> itr = msgConverters.iterator(); itr.hasNext(); ) {
					HttpMessageConverter<?> converter = itr.next();
					if(converter.canWrite(Object.class, MediaType.APPLICATION_JSON)) {
						itr.remove();
					}
				}
			} else {
				msgConverters = new ArrayList<HttpMessageConverter<?>>();
				restTemplate.setMessageConverters(msgConverters);
			}
		    MappingJackson2HttpMessageConverter msgConverter = new MappingJackson2HttpMessageConverter();
		    ObjectMapper objectMapper = msgConverter.getObjectMapper();
		    //Ignore fields with null value in serialization
			objectMapper.setSerializationInclusion(Include.NON_NULL);
			//Ignore unknown fields at de-serialization
		    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		    msgConverters.add(msgConverter);
		}
		
		private <T> HttpEntity<T> getHttpEntity(T requestObject, Map<String,String> headerParams, MediaType mediaType) {
			
			HttpHeaders httpHeaders = new HttpHeaders();
			
			httpHeaders = addSecurityHeader( httpHeaders );

			
			if (headerParams!=null && !headerParams.isEmpty()) {
				Set<String> headerKeySet = headerParams.keySet();
				for (String headerKey : headerKeySet) {
					httpHeaders.add(headerKey, headerParams.get(headerKey));
				}			
			}
			
			if (mediaType != null) {
				httpHeaders.setContentType(mediaType);
			} else {
				//Defaulting to JSON if contentType not passed
				httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			}
			
			HttpEntity<T> result;
			if (requestObject!=null) {
				result = new HttpEntity<T>(requestObject, httpHeaders);
			} else {
				//If no RequestObject passed, then create the HttpEntity just with the headers.
				result = new HttpEntity<T>(httpHeaders);
			}
			return result;

		}
		
		private HttpHeaders addBasicAuthHeaders(HttpHeaders httpHeaders, final String usernameIn, final String passwordIn) {
			
			String auth = usernameIn + ":" + passwordIn;
			byte[] encodedAuth = Base64.encodeBase64(auth.getBytes());
			String authHeader = "Basic " + new String(encodedAuth);
			httpHeaders.add("Authorization", authHeader);

			return httpHeaders; 
			
		}
		
		private HttpHeaders addSecurityHeader(HttpHeaders httpHeaders) {
			
			if (( getRootUrl().indexOf( "apigw") >0 )  //Kong API gateway endpoint
				|| isOAuth2TokenRequird() )	{
				
				httpHeaders = addOAuth2Headers( httpHeaders );
				
			}
			else if (getRootUrl().startsWith("https") || getRootUrl().contains("_vs")){ //SOA basic auth
				httpHeaders = addBasicAuthHeaders(httpHeaders, getApplicationName(), getApplicationPwd());
			}
			return httpHeaders;
		}

		private HttpHeaders addOAuth2Headers(HttpHeaders httpHeaders) {
			
			String token = OAuth2TokenDelegate.getInstance().getAuthorizationTokenString();
			if(token != null) {
				httpHeaders.set("Authorization", token );
				LOGGER.info("addOAuth2Headers", "add token to HttpHeader:" + token );
			}			

			//set Kong env
			if (StringUtils.isNoneBlank( getKongEnv() ) ) {
				httpHeaders.set( "env", getKongEnv() );
				LOGGER.info("addOAuth2Headers", "add kongEnv to HttpHeader:" + getKongEnv() );
			}
			
			return httpHeaders;
		}

	}

}

