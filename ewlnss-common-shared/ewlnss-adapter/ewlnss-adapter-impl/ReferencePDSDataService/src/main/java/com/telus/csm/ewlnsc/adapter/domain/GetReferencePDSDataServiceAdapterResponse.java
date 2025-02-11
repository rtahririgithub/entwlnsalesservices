package com.telus.csm.ewlnsc.adapter.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;

/**
 * @author Alejandro.Hernandez
 *
 */
public class GetReferencePDSDataServiceAdapterResponse extends AdapterResponseBase {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	

	//private Map<String,String> nguiInstallLagTimeMap = new HashMap<String,String>();
	
	private Map<String,String> referencePDSTableMap = new HashMap<String,String>();// would be used for any table
	private List<BusinessRuleInstanceTypeVO> ruleList;

//	public Map<String, String> getNguiInstallLagTimeMap() {
//		return nguiInstallLagTimeMap;
//	}


//	public void setNguiInstallLagTimeMap(Map<String, String> nguiInstallLagTimeMap) {
//		this.nguiInstallLagTimeMap = nguiInstallLagTimeMap;
//	}


	public Map<String, String> getReferencePDSTableMap() {
		return referencePDSTableMap;
	}


	public void setReferencePDSTableMap(Map<String, String> referencePDSTableMap) {
		this.referencePDSTableMap = referencePDSTableMap;
	}
	
	
	// Gary - 2018-03-12 
	private List<Map<String, String>> ReferencePDSTableObjectMap = new ArrayList<Map<String, String>>();


	public List<Map<String, String>> getReferencePDSTableObjectMap() {
		return ReferencePDSTableObjectMap;
	}


	public void setReferencePDSTableObjectMap(List<Map<String, String>> referencePDSTableObjectMap) {
		ReferencePDSTableObjectMap = referencePDSTableObjectMap;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public List<BusinessRuleInstanceTypeVO> getRuleList() {
		return ruleList;
	}


	public void setRuleList(List<BusinessRuleInstanceTypeVO> ruleList) {
		this.ruleList = ruleList;
	}

}
 