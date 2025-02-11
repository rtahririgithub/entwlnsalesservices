package com.telus.csm.ewlnss.adapter.common;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.cacheadapter.CacheAdapterFactory;
import com.telus.csm.ewlnsc.util.cacheadapter.ICacheAdapter;



/**
 * This class encapsulate the logic of 
 * 1) acquiring OAuth2 access token
 * 2) manage token cache.
 *
 */
public final class OAuth2TokenDelegate {

	private final Logger log = Logger.getLogger( OAuth2TokenDelegate.class );
	
	private static final String AUTH_GRANT_TYPE_CLIENT_CREDENTIALS = "client_credentials";
	private static final String AUTH_SCOPE = "scope";
	private static final String AUTH_GRANT_TYPE = "grant_type";
	private static final String AUTHORIZATION = "Authorization";
	private static final String AUTH_TOKEN_TYPE_BASIC = "Basic ";
	private static final String AUTH_TOKEN_TYPE_BEARER = "Bearer ";

	
	private static OAuth2TokenDelegate INSTANCE = new OAuth2TokenDelegate();
	
	private String authorizationServerUrl=null;
	private String proxyServerHost;
	private int proxyServerPort;
	private String clientId=null;
	private String clientSecret=null;
	//make sure to use be space-delimited list when specifying multiple scopes 
	private String scope;
	public int gracePeriodSeconds;

	//Use coherence cache to save the token. this will reduce the call to Authorization server
	private ICacheAdapter accessTokenCache;

	private OAuth2TokenDelegate() {

		accessTokenCache = CacheAdapterFactory.getCacheAdapter("OAuth2TokenCache");
		
		authorizationServerUrl=ApplicationProperties.getConfigString("${connections/kongApiGateway/authorizationServerEndpoint/}");
		proxyServerHost=ApplicationProperties.getConfigString("${connections/kongApiGateway/proxyHost/}");
		proxyServerPort=Integer.parseInt( ApplicationProperties.getConfigString("${connections/kongApiGateway/proxyPort/}"));
		clientId=ApplicationProperties.getConfigString("${connections/kongApiGateway/clientId}");
		clientSecret=ApplicationProperties.getConfigString("${connections/kongApiGateway/clientSecret}");
		gracePeriodSeconds= Integer.parseInt( ApplicationProperties.getConfigString("${connections/kongApiGateway/tokenTimeoutGracePeriodSeconds}") );

		//make sure to use be space-delimited list when specifying multiple scopes 
		scope=ApplicationProperties.getConfigString("${connections/kongApiGateway/scope}");
	}

	public static OAuth2TokenDelegate getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new OAuth2TokenDelegate();
		}

		return INSTANCE;
	}
	
	private AccessToken getTokenFromCache(String targetApiId) {
		
		return (AccessToken) accessTokenCache.get( targetApiId );
	}
	
	private void saveToCache( AccessToken accessToken ) {
		accessTokenCache.put( accessToken.getScope(), accessToken );
	}
	public String getAuthorizationTokenString( ) {
		
		AccessToken accessToken = getTokenFromCache(scope);
		
		String context = "get accessToken scope=" + scope + ";";
		
		Date now = new Date();
		
		if ( accessToken == null || (now.after(accessToken.getExpiryDate()))) {
			
			//lock on targetId, this lock is per JVM.
			//TODO better find a way to use coherence lock so that one node will try to get the token
			//, and other node just wait and re-use the token
			synchronized ( scope ) { 
				
				accessToken = getTokenFromCache( scope );
			
				if ( accessToken == null  || now.after(accessToken.getExpiryDate() ) ) {
					
					log.debug( context + " not found in cache, acquiring new token." );
					
					long startTime = System.currentTimeMillis();
	
					accessToken = acquireToken (scope) ;
					
					long elapsedTime = System.currentTimeMillis()-startTime;

					if ( accessToken!=null ) {
						
						saveToCache ( accessToken );

						log.debug( context + " token acquired successfully, elapsedTime=" + elapsedTime + ", " + accessToken.toString()  );
					}
					
				} else {
					log.debug( context + " found in cache: " +  accessToken.toString() );
				}
			}
		} else {
			log.debug( context + " found in cache: " +  accessToken.toString() );
		}

		return AUTH_TOKEN_TYPE_BEARER + accessToken.getToken();
	}


	private AccessToken acquireToken( String scopeText ) {

		AccessToken accessToken = null;
		try {

			// setting up the request headers
			HttpHeaders httpHeaders = new HttpHeaders();

			httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

			// set up the basic authentication header
			String authorizationHeader = AUTH_TOKEN_TYPE_BASIC + DatatypeConverter.printBase64Binary((clientId + ":" + clientSecret).getBytes());
			httpHeaders.add(AUTHORIZATION, authorizationHeader);
			
			MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
			queryParams.add( AUTH_GRANT_TYPE, AUTH_GRANT_TYPE_CLIENT_CREDENTIALS );
			queryParams.add( AUTH_SCOPE, scopeText );

			// request entity is created with request body and headers
			HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(queryParams, httpHeaders);

			//If the service is inside TEN, we need to use proxy to reach the auth server.
			CloseableHttpClient httpClient = HttpClientBuilder.create().setProxy(new HttpHost(proxyServerHost, proxyServerPort)).build(); 

			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
			requestFactory.setHttpClient( httpClient );

			RestTemplate restTemplate = new RestTemplate(requestFactory);
			
			ResponseEntity<AccessToken> responseEntity = restTemplate.exchange(authorizationServerUrl,	HttpMethod.POST, requestEntity, AccessToken.class);
			
			accessToken= responseEntity.getBody();
			
			accessToken.setScope( scopeText );
			// Set access token expiry time by adding seconds from the response minus gracePeriodSeconds.
			accessToken.setExpiryDate( getNewDate( accessToken.getExpiresIn() - gracePeriodSeconds) );

		} catch (Exception e) {
			
			log.error( String.format( "acquireToken(%s): failed", scopeText ) , e);
		}
		
		return accessToken;
	}
	
	
	private Date getNewDate( Long advanceForSeconds ) {
		
		Calendar cal = Calendar.getInstance();
		cal.add( Calendar.SECOND, advanceForSeconds.intValue() );
		return cal.getTime();
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class AccessToken implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		@JsonProperty("access_token")
		private String token;
		
		@JsonProperty("token_type")
		private String tokenType;
		
		@JsonProperty("expires_in")
		private Long expiresIn;

		private Date expiryDate;
		
		private String scope;

		public String getScope() {
			return scope;
		}

		public void setScope(String scope) {
			this.scope = scope;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public String getTokenType() {
			return tokenType;
		}

		public void setTokenType(String tokenType) {
			this.tokenType = tokenType;
		}

		public Long getExpiresIn() {
			return expiresIn;
		}

		public void setExpiresIn(Long expiresIn) {
			this.expiresIn = expiresIn;
		}

		public Date getExpiryDate() {
			return expiryDate;
		}

		public void setExpiryDate(Date expiryDate) {
			this.expiryDate = expiryDate;
		}

		@Override
		public String toString() {

			StringBuilder builder = new StringBuilder();
			
			builder.append("AccessToken [")
			.append("scope=").append(scope)
			.append(", expiryDate=").append(expiryDate)
			.append(", expiresIn=").append(expiresIn)
			.append(", tokenType=").append(tokenType)
			//.append(", token=").append(token)
			.append("]");
			return builder.toString();
		}
	}
}
