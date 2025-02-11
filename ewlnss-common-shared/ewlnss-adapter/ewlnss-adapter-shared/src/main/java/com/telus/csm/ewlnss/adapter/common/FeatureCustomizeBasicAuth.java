package com.telus.csm.ewlnss.adapter.common;

import java.util.Map;

import javax.xml.ws.BindingProvider;

public class FeatureCustomizeBasicAuth extends AdapterFeature {


	public FeatureCustomizeBasicAuth() {
		//
	}
	
	public FeatureCustomizeBasicAuth(AdapterFeatureDriver adapterFeatureDriver) {
		super(adapterFeatureDriver);
	}

	@Override
	public boolean isApplicable() {
		return "TRL".equals(adapterFeatureDriver.getChannelOrgType());
	}

	@Override
	public String getFeatureString() {
		return this.getClass().getSimpleName() + ":" + adapterFeatureDriver.getChannelOrgType();
	}

	@Override
	public void apply(BindingProvider port) {

		Map<String, Object> requestContext = port.getRequestContext();
		
		if (requestContext.get(BindingProvider.USERNAME_PROPERTY) != null) {
//			requestContext.put(BindingProvider.USERNAME_PROPERTY, applicationNameTRL)
//			requestContext.put(BindingProvider.PASSWORD_PROPERTY, applicationPwdTRL)
		}
		
	}

	@Override
	public Map<String, String> apply(Map<String, String> headerParams) {
		
		if (headerParams.get("Authorization") == null) {
//			String auth = applicationNameTRL + ":" + applicationPwdTRL
//			byte[] encodedAuth = Base64.encodeBase64(auth.getBytes())
//			String authHeader = "Basic " + new String(encodedAuth)
		}

		return headerParams;



	}

}
