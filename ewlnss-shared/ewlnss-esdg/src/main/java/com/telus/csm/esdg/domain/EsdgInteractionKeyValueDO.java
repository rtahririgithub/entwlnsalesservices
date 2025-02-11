package com.telus.csm.esdg.domain;

import java.io.Serializable;

public class EsdgInteractionKeyValueDO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String salesInteractionKeyValueId;
	private String salesInteractionId;
	private long salesInteractionStartTimeInMills = 0;
	private String keyName;
	private String valueData;
	public String getSalesInteractionKeyValueId() {
		return salesInteractionKeyValueId;
	}
	public void setSalesInteractionKeyValueId(String salesInteractionKeyValueId) {
		this.salesInteractionKeyValueId = salesInteractionKeyValueId;
	}
	public String getSalesInteractionId() {
		return salesInteractionId;
	}
	public void setSalesInteractionId(String salesInteractionId) {
		this.salesInteractionId = salesInteractionId;
	}
	public long getSalesInteractionStartTimeInMills() {
		return salesInteractionStartTimeInMills;
	}
	public void setSalesInteractionStartTimeInMills(long salesInteractionStartTimeInMills) {
		this.salesInteractionStartTimeInMills = salesInteractionStartTimeInMills;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getValueData() {
		return valueData;
	}
	public void setValueData(String valueData) {
		this.valueData = valueData;
	}

}
