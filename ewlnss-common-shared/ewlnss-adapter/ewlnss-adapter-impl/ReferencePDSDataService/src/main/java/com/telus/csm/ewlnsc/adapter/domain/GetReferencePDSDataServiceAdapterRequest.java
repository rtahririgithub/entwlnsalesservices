package com.telus.csm.ewlnsc.adapter.domain;


import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;
/**
 * @author Alejandro.Hernandez
 *
 */
public class GetReferencePDSDataServiceAdapterRequest extends AdapterRequestBase {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String appId;
	


	public GetReferencePDSDataServiceAdapterRequest(String appId){
    	this.appId = appId;
    }
    
	public GetReferencePDSDataServiceAdapterRequest(){
		
	}
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	@Override
	public String getCacheKey(){
		String ewssVersion = EnterpriseWLNSalesServicesConstants.EWSS_VERSION_VALUE;
		String cacheKeyDelimiter = EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER;
		return "EWSS" + cacheKeyDelimiter + this.getClass().getSimpleName().substring(0, 26) + cacheKeyDelimiter + getAppId() + cacheKeyDelimiter + ewssVersion; 
	}
	
	
}
