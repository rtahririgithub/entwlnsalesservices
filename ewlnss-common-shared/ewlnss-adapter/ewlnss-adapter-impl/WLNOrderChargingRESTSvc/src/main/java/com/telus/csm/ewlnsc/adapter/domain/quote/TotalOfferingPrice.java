/**
 * 
 */
package com.telus.csm.ewlnsc.adapter.domain.quote;

import java.util.HashMap;
import java.util.Map;

/**
 * @author x145592
 *
 */
public class TotalOfferingPrice {
    
	private String totalOC;
    private String totalRC;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
    public String getTotalOC() {
		return totalOC;
	}
	public void setTotalOC(String totalOC) {
		this.totalOC = totalOC;
	}
	public String getTotalRC() {
		return totalRC;
	}
	public void setTotalRC(String totalRC) {
		this.totalRC = totalRC;
	}
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

    
}
