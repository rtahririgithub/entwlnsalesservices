/**
 * 
 */
package com.telus.csm.ewlnsc.transformer;

import java.util.HashMap;
import java.util.Map;


import com.telus.csm.ewlnsc.adapter.domain.GetReferencePDSDataServiceAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetReferencePDSDataServiceAdapterResponse;
import com.telus.csm.ewlnsc.util.LoggerUtil;

/**
 * @author Alejandro.Hernandez
 *
 */
public class RefPDSServiceDelegatorTransformer {


	private static final LoggerUtil loggerUtil = LoggerUtil.getLogger(RefPDSServiceDelegatorTransformer.class);
	
	/**
	 * @param appId
	 * @return
	 */
	public static GetReferencePDSDataServiceAdapterRequest transformRequest(String appId) {
		GetReferencePDSDataServiceAdapterRequest adapterRequest = new GetReferencePDSDataServiceAdapterRequest(appId);
		return adapterRequest;
	}

	/**
	 * @param adapterResponse
	 * @return
	 */
	public static Map<String, String> transformResponse(GetReferencePDSDataServiceAdapterResponse adapterResponse) {
		Map<String,String> lagTimeMap = new HashMap<String, String>();
		if(!adapterResponse.getReferencePDSTableMap().isEmpty()){
			lagTimeMap.putAll(adapterResponse.getReferencePDSTableMap());
		}
		return lagTimeMap;
	}
	
	/**
	 * @param adapterResponse
	 * @return 
	 */
	public static Map<String, String> transformRefPDSResponse(GetReferencePDSDataServiceAdapterResponse adapterResponse) {
		Map<String,String> referencePDSTableMap = new HashMap<String, String>();
		if(!adapterResponse.getReferencePDSTableMap().isEmpty()){
			referencePDSTableMap.putAll(adapterResponse.getReferencePDSTableMap());
		}
		return referencePDSTableMap;
	}


}
