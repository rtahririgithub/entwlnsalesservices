package com.telus.csm.ewlnsc.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class GetAvailableInstallDateCoreResponse extends CoreResponseBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private transient List<AvailableInstallDateVO> availableInstallDates = null;
	
	
	/*
	 * This is list of all combination of ordered products
	 */
	private transient List<List<String>> orderedProductCombination = null;
	
	private List<String> serviceTypeSWList = new ArrayList<String>();

	private Map<String, Boolean> infeasibleProductsMap = new HashMap<String, Boolean>();
	
	
	public Map<String, Boolean> getInfeasibleProductsMap() {
		return infeasibleProductsMap;
	}

	public void setInfeasibleProductsMap(Map<String, Boolean> infeasibleProductsMap) {
		this.infeasibleProductsMap = infeasibleProductsMap;
	}

	public List<String> getServiceTypeSWList() {
		return serviceTypeSWList;
	}

	public void setServiceTypeSWList(List<String> serviceTypeSWList) {
		this.serviceTypeSWList = serviceTypeSWList;
	}

	public List<List<String>> getOrderedProductCombination() {
		return orderedProductCombination;
	}

	public void setOrderedProductCombination(
			List<List<String>> orderedProductCombination) {
		this.orderedProductCombination = orderedProductCombination;
	}

	public List<AvailableInstallDateVO> getAvailableInstallDates() {
		if (this.availableInstallDates == null)
			this.availableInstallDates = new ArrayList<AvailableInstallDateVO>();
		
		return availableInstallDates;
	}

	public void setAvailableInstallDates(List<AvailableInstallDateVO> availableInstallDates) {
		this.availableInstallDates = availableInstallDates;
	}
	
	
	public void addAvailableInstallDates(AvailableInstallDateVO installDate){
		this.getAvailableInstallDates().add(installDate);
	}
}
