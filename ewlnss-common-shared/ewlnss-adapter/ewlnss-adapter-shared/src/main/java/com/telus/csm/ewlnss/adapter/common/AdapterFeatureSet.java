package com.telus.csm.ewlnss.adapter.common;

import java.util.HashSet;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.springframework.http.HttpMethod;

public class AdapterFeatureSet extends HashSet<AdapterFeature> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -349374949811063110L;

	public String getFeaturesString() {
		StringBuilder result = new StringBuilder();
		
		for (AdapterFeature adt : this) {
			result.append(adt.getFeatureString() + "|");
		}
		
		return result.toString();
		
	}
	
	@Override
	public boolean add(AdapterFeature adtIn) {
		
		String featureStringIn = adtIn.getFeatureString();
				
		for (AdapterFeature adt : this) {
			if (adt.getFeatureString().equals(featureStringIn)){
				return false;
			}
		}
		
		return super.add(adtIn);

	}

	public void apply(BindingProvider port) {
		for (AdapterFeature adapterFeature : this) {
			adapterFeature.apply(port);
		}
	}

	public Map<String, String> apply(HttpMethod method, Map<String, String> headerParams) {
		
		Map<String, String> result = headerParams;
		
		for (AdapterFeature adapterFeature : this) {
			if (adapterFeature.isApplicableToMethod(method)){
				result = adapterFeature.apply(result);
			}
		}
		return result;
	}

}
