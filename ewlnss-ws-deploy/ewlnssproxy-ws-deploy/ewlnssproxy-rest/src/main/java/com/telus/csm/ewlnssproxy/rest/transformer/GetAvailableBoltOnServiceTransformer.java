package com.telus.csm.ewlnssproxy.rest.transformer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.telus.csm.ewlnsc.adapter.domain.GetAvailableServiceInstanceListAdapterRequest;

public class GetAvailableBoltOnServiceTransformer extends BaseTransformer{
	
	public static GetAvailableServiceInstanceListAdapterRequest transformAvailableServiceInstanceListRequest(String transactionId, String applicationId, String customerId, String roleId) {
		GetAvailableServiceInstanceListAdapterRequest availableServiceInstanceListAdapterRequest = new GetAvailableServiceInstanceListAdapterRequest();
		availableServiceInstanceListAdapterRequest.setSalesTransactionId(transactionId);
		availableServiceInstanceListAdapterRequest.setCustomerId(customerId);
		availableServiceInstanceListAdapterRequest.setApplicationId(applicationId);
		availableServiceInstanceListAdapterRequest.setRoleId(roleId);

		return availableServiceInstanceListAdapterRequest;
	}	
   
	public static List<com.telus.csm.ewlnssproxy.cpib.domain.RequiredService> transformResponse(List<com.telus.tmi.xmlschema.srv.mso.campaignmgmt.boltonofferservicerequestresponse_v3.RequiredService> request) throws Exception{		
		
    	List<com.telus.tmi.xmlschema.srv.mso.campaignmgmt.boltonofferservicerequestresponse_v3.RequiredService> source = request;  // source and target have common properties but not same Class.
    	List<com.telus.csm.ewlnssproxy.cpib.domain.RequiredService> target = new ArrayList<com.telus.csm.ewlnssproxy.cpib.domain.RequiredService>();
		 
		try{	

			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
			mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			for(com.telus.tmi.xmlschema.srv.mso.campaignmgmt.boltonofferservicerequestresponse_v3.RequiredService requiredService: source){
				
				Date startDate = requiredService.getStartDate();
				Date endDate = requiredService.getEndDate();
				
				requiredService.setStartDate(null);
				requiredService.setEndDate(null);
				
				com.telus.csm.ewlnssproxy.cpib.domain.RequiredService schemaRequiredService = mapper.convertValue(requiredService, com.telus.csm.ewlnssproxy.cpib.domain.RequiredService.class);
				
				if(schemaRequiredService!=null){
					
					if(startDate!=null){
						schemaRequiredService.setStartDate(new com.telus.csm.ewlnss.rest.time.DateTime(startDate));
					}
					
					if(endDate!=null){
						schemaRequiredService.setEndDate(new com.telus.csm.ewlnss.rest.time.DateTime(endDate));
					}
					
					target.add(schemaRequiredService);
				}
			}			
		}catch(Exception e){
		   throw e;
		}
		 
		return target;
	}	
	
}
