package com.telus.csm.ewlnsc.adapter.impl;

import com.telus.csm.ewlnsc.adapter.IRestServiceAddressMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.AddressByFmsAdapterResponse;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.RestServiceAdapter;

public class RestServiceAddressMgmtSvcAdapter  extends RestServiceAdapter implements IRestServiceAddressMgmtSvcAdapter {

	public RestServiceAddressMgmtSvcAdapter() {
		super();
	}

	public RestServiceAddressMgmtSvcAdapter(AdapterFeatureDriver adapterFeatureDriver) {
		super(adapterFeatureDriver);
	}

	private static String rootUrl = ApplicationProperties.getConfigString("${connections/webServices/restServiceAddressMgmtSvc/wsdlUrl}");

	@Override
	public String getRootUrl() {
		return rootUrl; 	
	}
	
	@Override
	public String ping() {
		Object obj = get("ping", "demo", String.class, null);
		return (String) obj;
	}

	@Override
	public AddressByFmsAdapterResponse searchFmsAddress(String addressId, String provinceCode ){
		/*String provinceDecode = "";
		if (provinceCode.equals("BC")){
			provinceDecode = "202";
		} else if (provinceCode.equalsIgnoreCase("AB")){
			provinceDecode = "201";
		}
		String fmsIdWithProvince = "fms/" + addressId + "%20%2F%20" + provinceDecode;
		AddressByFmsAdapterResponse adapterResponse = new AddressByFmsAdapterResponse();
		adapterResponse = null;
		if(adapterResponse.getAddresses()==null){
			AMSErrorResponse errorResponse = null;
			adapterResponse.setErrorResponse(errorResponse);
		}*/
		return null;

	}
	
}
