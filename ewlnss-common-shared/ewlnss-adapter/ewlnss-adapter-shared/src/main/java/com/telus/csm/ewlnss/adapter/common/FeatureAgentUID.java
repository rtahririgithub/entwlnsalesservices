package com.telus.csm.ewlnss.adapter.common;

import static com.telus.csm.ewlnsc.util.Constants.D2C_PARTNER;

import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.apache.log4j.Logger;

public class FeatureAgentUID extends AdapterFeature {

	protected static final Logger LOGGER = Logger.getLogger(FeatureAgentUID.class);
	
	public FeatureAgentUID() {
		//
	}
	
	public FeatureAgentUID(AdapterFeatureDriver adapterFeatureDriver) {
		super(adapterFeatureDriver);
	}

	@Override
	public boolean isApplicable() {
		return true;
	}

	@Override
	public String getFeatureString() {
		return this.getClass().getSimpleName() + ":" + adapterFeatureDriver.getSalesPersonRoleCode();
	}

	@Override
	public void apply(BindingProvider port) {

	}

	//Setting agent_uid in HTTP headers
	@Override
	public Map<String, String> apply(Map<String, String> headerParams) {
		
		final String HEADER_AGENT_UID = "agent_uid";
		
		if (headerParams == null) {
			headerParams = new HashMap<String, String>();
		}
		
		if (headerParams.get(HEADER_AGENT_UID) == null) {
			String agentUID = "AUTO_CHN";
			if (D2C_PARTNER.equalsIgnoreCase(adapterFeatureDriver.getSalesPersonRoleCode())) {
				agentUID = "AUTO_D2C";
			}
			LOGGER.info("Setting agent_uid in HttpHeader with value: " + agentUID);
			headerParams.put(HEADER_AGENT_UID, agentUID);
		}

		return headerParams;

	}

}
