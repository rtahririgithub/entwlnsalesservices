package com.telus.csm.ewlnsc.adapter.domain;

import java.sql.Timestamp;

import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.ServiceAddress;


/**
 * GetServiceAddressDetailsAdapterRequest
 * @author x159148
 *
 */
public class GetServiceAddressDetailsAdapterRequest extends AdapterRequestBase {
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private String userId;
	private Timestamp timeStamp;
	private ServiceAddress address;
	
	@Override
	public String getCacheKey(){
		return "GgetServiceAddressDetails"
				+ EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + EnterpriseWLNSalesServicesConstants.SALES_TXN_ID
				+ EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + getSalesTransactionId()
				+ EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + EnterpriseWLNSalesServicesConstants.ADDRESS_ID
				+ EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + address.getAddressId()
				+ EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + EnterpriseWLNSalesServicesConstants.PROVINCE_STATE_CODE
				+ EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER + address.getProvinceStateCode();
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	public ServiceAddress getAddress() {
		return address;
	}
	public void setAddress(ServiceAddress address) {
		this.address = address;
	}
}
