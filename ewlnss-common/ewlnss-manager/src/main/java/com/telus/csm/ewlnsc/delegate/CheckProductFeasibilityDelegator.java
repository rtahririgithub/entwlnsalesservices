package com.telus.csm.ewlnsc.delegate;

import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.telus.csm.ewlnsc.adapter.IFeasibilityServiceAdapter;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;

public class CheckProductFeasibilityDelegator {

	private static final LoggerUtil loggerUtil = LoggerUtil.getLogger(CheckProductFeasibilityDelegator.class);

	
	public CheckProductFeasibilityAdapterResponse checkFeasibility(CheckProductFeasibilityAdapterRequest request) {
		String functionName="CheckProductFeasibilityDelegator.checkFeasibility()";
		CheckProductFeasibilityAdapterResponse result = null;
		loggerUtil.enter(functionName);

		IFeasibilityServiceAdapter feasibilityAdapter = AdapterFactory.getAdapter(IFeasibilityServiceAdapter.class);

		try{
			result = feasibilityAdapter.checkProductFeasibility(request);
		}catch(Exception e){
			loggerUtil.error(null, functionName, e.getMessage());
		}
		loggerUtil.exit(functionName);
		return result;
	}
	
	public String mapOmsCode(String productCode) {
		String functionName = "getDeclinedReasonCdMap";
		String cidCode = "";
		String omsCode = "";
		Map <String, String> wssProdOmsCidMap;
		Map <String, String> wssProductTier;
		
		ReferencePDSDataSvcBusDelegator refPdsBusDelegate = ReferencePDSDataSvcBusDelegator.getInstance();
		
		wssProdOmsCidMap = MapUtils.invertMap(refPdsBusDelegate.getReferencePDSTableByName(EnterpriseWLNSalesServicesConstants.REFPDS_WSS_PROD_OMS_CID_MAP));
		wssProductTier = refPdsBusDelegate.getReferencePDSTableByName(EnterpriseWLNSalesServicesConstants.REFPDS_WSS_PRODUCT_TIER);

		cidCode = wssProdOmsCidMap.get(productCode);

		if (cidCode != null){
	//		loggerUtil.info(functionName, "Product Code was found in WSS_PROD_OMS_CID_MAP: " + productCode + " and mapped to " + cidCode);
			
			omsCode = wssProductTier.get(cidCode);
			
			if(omsCode != null) {
	//			loggerUtil.info(functionName, "CID Code was found in WSS_PRODUCT_TIER: " + cidCode + " and mapped to " + omsCode);
			}else {
	//			loggerUtil.info(functionName, "CID Code " + productCode + " was NOT found in WSS_PRODUCT_TIER.");
			}

		}else{
	//		loggerUtil.info(functionName, "Product Code " + productCode + " was NOT found in WSS_PROD_OMS_CID_MAP.");
		}
		
		return omsCode;
	}


}
