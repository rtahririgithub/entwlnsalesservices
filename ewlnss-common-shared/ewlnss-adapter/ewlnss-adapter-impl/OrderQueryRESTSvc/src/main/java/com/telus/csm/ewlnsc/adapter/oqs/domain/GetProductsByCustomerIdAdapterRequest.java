package com.telus.csm.ewlnsc.adapter.oqs.domain;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;

/**
 * 
 * @author Jose.Mena
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class GetProductsByCustomerIdAdapterRequest extends AdapterRequestBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String customerid;
	private boolean includedetails;
	private List<String> instanceidlist = new ArrayList<String>();
	
	private String addressId;
	private String provinceCd;

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public boolean isIncludedetails() {
		return includedetails;
	}

	public void setIncludedetails(boolean includedetails) {
		this.includedetails = includedetails;
	}

	public List<String> getInstanceidlist() {
		return instanceidlist;
	}

	public void setInstanceidlist(List<String> instanceidlist) {
		this.instanceidlist = instanceidlist;
	}
	
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getProvinceCd() {
		return provinceCd;
	}
	public void setProvinceCd(String provinceCd) {
		this.provinceCd = provinceCd;
	}
	
	@Override
	public String getCacheKey(){
		return "GetProductByCustomerID"
				+ EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + EnterpriseWLNSalesServicesConstants.SALES_TXN_ID
				+ EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + getSalesTransactionId()
				+ EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + EnterpriseWLNSalesServicesConstants.CUSTOMER_ID
				+ EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + getCustomerid();
	}
}
