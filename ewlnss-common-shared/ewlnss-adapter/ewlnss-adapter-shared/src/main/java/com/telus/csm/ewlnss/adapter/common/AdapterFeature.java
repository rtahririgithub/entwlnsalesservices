package com.telus.csm.ewlnss.adapter.common;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.ws.BindingProvider;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

public abstract class AdapterFeature {

	protected AdapterFeatureDriver adapterFeatureDriver;
	
	private SortedSet<HttpMethod> applicableMethods = new TreeSet<HttpMethod>(); 
	
	public AdapterFeature() {
	}

	public AdapterFeature(AdapterFeatureDriver adapterFeatureDriverIn) {
		adapterFeatureDriver = adapterFeatureDriverIn;
	}

	public abstract boolean isApplicable();

	public abstract String getFeatureString();
	
	// only effective for rest services
	public void addApplicableMethod(HttpMethod parm) {
		applicableMethods.add(parm);
	}
	
	public boolean isApplicableToMethod(HttpMethod method) {

		if (applicableMethods.isEmpty()){
			return true;
		}

		return applicableMethods.contains(method);
	}

	//soap 
	public abstract void apply(BindingProvider port);

	//rest 
	public abstract Map<String, String> apply(Map<String, String> result);

}
