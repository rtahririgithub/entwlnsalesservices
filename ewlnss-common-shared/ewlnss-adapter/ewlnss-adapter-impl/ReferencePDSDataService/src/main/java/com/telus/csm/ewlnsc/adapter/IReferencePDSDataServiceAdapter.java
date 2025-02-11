
package com.telus.csm.ewlnsc.adapter;

import com.telus.csm.ewlnsc.adapter.domain.GetReferencePDSDataServiceAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetReferencePDSDataServiceAdapterResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

/**
 * @author Alejandro.Hernandez
 *
 */
public interface IReferencePDSDataServiceAdapter extends IAdapterBase{
	
	public GetReferencePDSDataServiceAdapterResponse getReferenceData(final GetReferencePDSDataServiceAdapterRequest getReferencePdsRequestDO,String entityName);
	public GetReferencePDSDataServiceAdapterResponse getReferenceData(final GetReferencePDSDataServiceAdapterRequest getReferencePdsRequestDO);
	
	public GetReferencePDSDataServiceAdapterResponse getBusinessRuleInstances(final GetReferencePDSDataServiceAdapterRequest getReferencePdsRequestDO,String entityName);
	

}
