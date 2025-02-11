package com.telus.csm.ewlnsc.domain;

import java.util.HashMap;
import java.util.Map;

public class GetReferencePDSResponseDO extends ResponseDO {
	
	private Map<String, Object> refpdsTable = new HashMap<String, Object>();

	public Map<String, Object> getRefpdsTable() {
		return refpdsTable;
	}

	public void setRefpdsTable(Map<String, Object> refpdsTable) {
		this.refpdsTable = refpdsTable;
	}
	
	

}
