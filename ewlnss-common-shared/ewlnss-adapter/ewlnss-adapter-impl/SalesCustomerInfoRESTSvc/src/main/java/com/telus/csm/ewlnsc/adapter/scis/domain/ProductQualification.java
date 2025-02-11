package com.telus.csm.ewlnsc.adapter.scis.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
public class ProductQualification implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private ServiceAddress serviceAddress;
	//OR3-170
	private String serviceId;
	
	private List<Product> productList;
    private Boolean gponEttsInd; 
	private Boolean mduIndicator;
	private Boolean forborneIndicator;
	private String changeOfTechnologyInd;
	
	public String getChangeOfTechnologyInd() {
		return changeOfTechnologyInd;
	}

	public void setChangeOfTechnologyInd(String changeOfTechnologyInd) {
		this.changeOfTechnologyInd = changeOfTechnologyInd;
	}

	public Boolean getForborneIndicator() {
		return this.forborneIndicator;
	}
	
	public void setForborneIndicator(Boolean forborneIndicator) {
		this.forborneIndicator = forborneIndicator;
	}

	public Boolean getMduIndicator() { 		
		return this.mduIndicator;
	}

	public void setMduIndicator(Boolean mduIndicator) {
		this.mduIndicator = mduIndicator;
	}
	
	public ServiceAddress getServiceAddress() {
		return serviceAddress;
	}
	public void setServiceAddress(ServiceAddress serviceAddress) {
		this.serviceAddress = serviceAddress;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public Boolean getGponEttsInd() {
		return gponEttsInd;
	}
	public void setGponEttsInd(Boolean gponEttsInd) {
		this.gponEttsInd = gponEttsInd;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
}
