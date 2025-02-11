/**
 * 
 */
package com.telus.csm.ewlnsc.delegate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.IReferencePDSDataServiceAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetReferencePDSDataServiceAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetReferencePDSDataServiceAdapterResponse;
import com.telus.csm.ewlnsc.domain.GetReferencePDSResponseDO;
import com.telus.csm.ewlnsc.domain.MessageDO;
import com.telus.csm.ewlnsc.domain.MessageDescDO;
import com.telus.csm.ewlnsc.transformer.RefPDSServiceDelegatorTransformer;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseMessage;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseMessageDesc;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;

/**
 * Business Delegate to get ReferencePDSData information
 * 
 * @author Alejandro.Hernandez
 *
 */
public class ReferencePDSDataSvcBusDelegator {

	/** Declaring the Logger **/
	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(ReferencePDSDataSvcBusDelegator.class);
	
	private static ReferencePDSDataSvcBusDelegator refPDSSvcDelegatorInstance;
	
	private ReferencePDSDataSvcBusDelegator(){
	}
	
	public static ReferencePDSDataSvcBusDelegator getInstance(){
		if(refPDSSvcDelegatorInstance==null){
			refPDSSvcDelegatorInstance = new ReferencePDSDataSvcBusDelegator();
		}
		return refPDSSvcDelegatorInstance;
	}
	
	/********************************************************/
	/*   getReferencePDSTableObjectByName                   */
	/********************************************************/
	public GetReferencePDSResponseDO getReferencePDSTableObjectByName(String refPDSTableName){
		String functionName="getReferencePDSTableObjectByName: refPDSTableName = " + refPDSTableName;
		
		
		GetReferencePDSResponseDO resp = new  GetReferencePDSResponseDO();
		
		Map<String,Object> tableMap = new HashMap<String, Object>();
		if (StringUtils.isEmpty(refPDSTableName)){
			LOGGER.info(functionName, "Returning empty HashMap because refPDSTableName parameter was empty.");   
			
			resp.setRefpdsTable(tableMap);
			return resp;
		}
		
		/** Step 1: Building GetReferencePDSDataserviceAdapterRequest **/
		String appId = EnterpriseWLNSalesServicesConstants.EWLNSS_APP_ID;
		GetReferencePDSDataServiceAdapterRequest adapterRequest = RefPDSServiceDelegatorTransformer.transformRequest(appId);
		
		/** Step 2: Getting Adapter from Adapter Factory **/
		IReferencePDSDataServiceAdapter adapter = AdapterFactory.getAdapter(IReferencePDSDataServiceAdapter.class);
		
		/** Step 3: Calling Adapter implementation **/
		GetReferencePDSDataServiceAdapterResponse adapterResponse = new GetReferencePDSDataServiceAdapterResponse(); 
		adapterResponse = adapter.getReferenceData(adapterRequest, refPDSTableName);
		
		/** Step 4: handling results from Service Response **/
		if(adapterResponse.isMsgHasError()){
			LOGGER.error(null, functionName, "An Error has occured when calling ReferencePDSDataSvc, please check Response Details");
			List<MessageDO> messageDOList = buildMessageDOList(adapterResponse.getAdapterResponseMessage());
			resp.addMessages(messageDOList);
			resp.setMsgHasError(true);
		}else{ 
			 
			if (adapterResponse.getReferencePDSTableObjectMap() != null && adapterResponse.getReferencePDSTableObjectMap().size() > 0){
				tableMap.put(refPDSTableName, adapterResponse.getReferencePDSTableObjectMap());
			}else{
				Map<String, String> stringMap = RefPDSServiceDelegatorTransformer.transformRefPDSResponse(adapterResponse);
				tableMap.put(refPDSTableName, stringMap);
			} 
			
			resp.setRefpdsTable(tableMap);
		}
		
		/** Step 5: Returning Response that contains map and error messages **/ 
		return resp;
	}
	
	private List<MessageDO> buildMessageDOList(AdapterResponseMessage adapterMessage){
		List<MessageDO> messages = new ArrayList<MessageDO>();
		
		MessageDO messageDO = new MessageDO();
		messageDO.setContextData(adapterMessage.getContextData());
		messageDO.setDateTimeStamp(adapterMessage.getDateTimeStamp());
		messageDO.setException(adapterMessage.getException());
		messageDO.setMessageCode(adapterMessage.getMessageCode());
		messageDO.setMessageType(adapterMessage.getMessageType());
		
		for (AdapterResponseMessageDesc adpRsptMsgDesc : adapterMessage.getMesssageDescriptionTextList()){
			MessageDescDO msgDescDO = new MessageDescDO();
			msgDescDO.setLocale(adpRsptMsgDesc.getLocale());
			msgDescDO.setMessageDescriptionText(adpRsptMsgDesc.getMessageDescriptionText());
			messageDO.addMessageDescDO(msgDescDO);
		} 
		
		messageDO.setTimestamp(adapterMessage.getTimestamp());
		messageDO.setTransactionId(adapterMessage.getTransactionId());
	
		messages.add(messageDO);
		return messages;
	}
	
	/********************************************************/
	/*   getReferencePDSTableByName                         */
	/********************************************************/
	public Map<String,String> getReferencePDSTableByName(String refPDSTableName){
		
		GetReferencePDSResponseDO response = this.getReferencePDSTableObjectByName(refPDSTableName);
		
		Map<String, Object> objResult = response.getRefpdsTable();
		
		Map<String, String> result = (Map<String, String>) objResult.get(refPDSTableName);
		if (result == null)
			result = new HashMap<String, String>();
		
		return result;
		
	}
	
//	public Map<String,String> getReferencePDSTableByName(String refPDSTableName){
//				
//		String functionName="getReferencePDSTableByName";
//		
//		LOGGER.enter(functionName);
//		
//		Map<String,String> tableMap = new HashMap<String, String>();
//		if (StringUtils.isEmpty(refPDSTableName)){
//			LOGGER.info(functionName, "Returning empty HashMap because refPDSTableName parameter was empty.");
//			return tableMap;
//		}
//
//		/** Step 1: Building GetReferencePDSDataserviceAdapterRequest **/
//		String appId = EnterpriseWLNSalesServicesConstants.EWLNSS_APP_ID;
//		
//		GetReferencePDSDataServiceAdapterRequest adapterRequest = RefPDSServiceDelegatorTransformer.transformRequest(appId);
//		
//		GetReferencePDSDataServiceAdapterResponse adapterResponse = new GetReferencePDSDataServiceAdapterResponse();
//		
//		/** Step 2: Getting Adapter from Adapter Factory **/
//		IReferencePDSDataServiceAdapter adapter = AdapterFactory.getAdapter(IReferencePDSDataServiceAdapter.class);
//		
//		/** Step 3: Calling Adapter implementation **/
//		adapterResponse = adapter.getReferenceData(adapterRequest, refPDSTableName);
//		
//		/** Step 4: handling results from Service Response **/
//		if(adapterResponse.isMsgHasError()){
//			LOGGER.error(null, functionName, "An Error has occured when calling ReferencePDSDataSvc, please check Response Details");
//		}else{
//			LOGGER.info(functionName, "Response successfully retrieved from RefPDSDataService...");
//			LOGGER.info(functionName, "Transforming Response to HashMap");
//			tableMap = RefPDSServiceDelegatorTransformer.transformRefPDSResponse(adapterResponse);
//		}
//		
//		/** Step 5: Returning Map **/
//		LOGGER.exit(functionName);
//		return tableMap;
//	}
	
	}
