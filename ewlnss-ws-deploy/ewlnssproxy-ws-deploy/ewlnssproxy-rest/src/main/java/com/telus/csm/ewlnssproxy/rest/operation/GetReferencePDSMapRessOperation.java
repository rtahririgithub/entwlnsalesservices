package com.telus.csm.ewlnssproxy.rest.operation;


import com.telus.csm.ewlnsc.adapter.domain.GetReferencePDSDataServiceAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetReferencePDSDataServiceAdapterResponse;
import com.telus.csm.ewlnsc.rest.exception.EssRestErrorException;
import com.telus.csm.ewlnsc.rest.exception.EssRestException;
import com.telus.csm.ewlnsc.rest.exception.EssRestSystemErrorException;
import com.telus.csm.ewlnsc.util.LoggerUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;

import com.telus.csm.ewlnsc.adapter.IReferencePDSDataServiceAdapter;

import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.csm.ewlnssproxy.cpdsb.domain.ReferencePDSData;
import com.telus.csm.ewlnssproxy.cpdsb.domain.Error;
import com.telus.csm.ewlnssproxy.rest.transformer.GetReferencePDSMapTransformer;

public class GetReferencePDSMapRessOperation {
	private static LoggerUtil logger = LoggerUtil.getLogger(GetReferencePDSMapRessOperation.class);

	public ReferencePDSData execute(String transactionId, String applicationId, String entityName) {		
		ReferencePDSData response = null;
		
		try {
			List<String> messageList = validateInputs(transactionId, applicationId, entityName);
			
			if(!messageList.isEmpty()){
				Error err = new Error();
				err.setCode(new Integer(Response.Status.BAD_REQUEST.getStatusCode()).toString()); 
				err.setReason("Invalid input."); 
				err.setStatus("ERROR");
				err.setTraceId(transactionId); 
				err.setMessage(GetReferencePDSMapTransformer.transformResponseMessage(messageList,new Integer(Response.Status.BAD_REQUEST.getStatusCode()).toString())); 
				throw new EssRestErrorException(err);
			}
			
			GetReferencePDSDataServiceAdapterResponse adapterResponse = null;
			GetReferencePDSDataServiceAdapterRequest adapterRequest = new GetReferencePDSDataServiceAdapterRequest(applicationId);
			IReferencePDSDataServiceAdapter adapter = AdapterFactory.getAdapter(IReferencePDSDataServiceAdapter.class);
			
			if(entityName!=null && entityName.trim().length()>0){
				adapterResponse = adapter.getReferenceData(adapterRequest, entityName);	
			}else{
			    adapterResponse = adapter.getReferenceData(adapterRequest);	
			}
			
			if (adapterResponse == null || adapterResponse.isMsgHasError()==true) {
				messageList.add("Unable to get reference pds data.");
				Error err = new Error();
				err.setCode(new Integer(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).toString());
				err.setReason("Unable to get reference pds data."); 
				err.setStatus("ERROR");
				err.setTraceId(transactionId); 
				err.setMessage(GetReferencePDSMapTransformer.transformResponseMessage(messageList, new Integer(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).toString())); 
				throw new EssRestErrorException(err);				
			}
			
			if(adapterResponse!=null && 
					((adapterResponse.getReferencePDSTableMap()!=null && adapterResponse.getReferencePDSTableMap().size()>0)
						|| (adapterResponse.getReferencePDSTableObjectMap()!=null && adapterResponse.getReferencePDSTableObjectMap().size()>0)
						|| (adapterResponse.getRuleList()!=null && adapterResponse.getRuleList().size()>0))){
			    response = new GetReferencePDSMapTransformer().transformResponse(adapterResponse, ReferencePDSData.class);
			}
						
			return response;
			
		} catch (EssRestException e) {
			throw e;
		} catch (Exception ex) {
			logger.error("error","Unknown Exception", ex.getMessage(), ex);
			Error respError = new Error();			
			
            respError.setCode(new Integer(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).toString());
            respError.setReason(ex.getMessage());
            respError.setStatus("ERROR");
            respError.setTraceId(transactionId); 

            StringBuilder msgBuilder = new StringBuilder(ex.toString());
            if(ex.getCause() != null) {
                  msgBuilder.append("; ").append(ex.getCause().toString());
            }
            Throwable traceEx = ex.getCause() != null? ex.getCause(): ex;
            msgBuilder.append(" ").append(Arrays.toString(traceEx.getStackTrace()));
            com.telus.csm.ewlnssproxy.cpdsb.domain.Message msg = new com.telus.csm.ewlnssproxy.cpdsb.domain.Message();
            msg.setType("ERROR");
            msg.setText(msgBuilder.toString());
            respError.setMessage(Arrays.asList(msg));			
			
			throw new EssRestSystemErrorException(respError);
		}		
	}
	
    private List<String> validateInputs(String transactionId, String applicationId, String entityName) {
		
		
		List<String> missingFieldsList = new ArrayList<String>();
		
		/**
		 * missing applicationId
		 */
		if(StringUtils.isEmpty(applicationId)) {
			missingFieldsList.add("applicationId is missing from the request.");
		}		
			
		return missingFieldsList;
	}

}