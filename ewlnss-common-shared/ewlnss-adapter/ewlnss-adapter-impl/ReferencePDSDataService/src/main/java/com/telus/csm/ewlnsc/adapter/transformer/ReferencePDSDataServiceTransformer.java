/**
 * 
 */
package com.telus.csm.ewlnsc.adapter.transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.telus.csm.ewlnsc.adapter.domain.BusinessRuleInstanceTypeVO;
import com.telus.csm.ewlnsc.adapter.domain.CodeValueTypeVO;
import com.telus.csm.ewlnsc.adapter.domain.GetReferencePDSDataServiceAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetReferencePDSDataServiceAdapterResponse; 
import com.telus.csm.ewlnsc.util.LoggerUtil; 
import com.telus.tmi.xmlschema.srv.erm.refpds.referencepdsdataservicerequestresponse_v1_0.BusinessRuleInstanceType;
import com.telus.tmi.xmlschema.srv.erm.refpds.referencepdsdataservicerequestresponse_v1_0.CodeDecodeInstanceType;
import com.telus.tmi.xmlschema.srv.erm.refpds.referencepdsdataservicerequestresponse_v1_0.CodeValueType;
import com.telus.tmi.xmlschema.srv.erm.refpds.referencepdsdataservicerequestresponse_v1_0.GetInstancesResponse;
import com.telus.tmi.xmlschema.srv.erm.refpds.referencepdsdataservicerequestresponse_v1_0.GetReferenceData;
import com.telus.tmi.xmlschema.srv.erm.refpds.referencepdsdataservicerequestresponse_v1_0.GetReferenceDataResponse;
import com.telus.tmi.xmlschema.srv.erm.refpds.referencepdsdataservicerequestresponse_v1_0.InstanceType;
import com.telus.tmi.xmlschema.srv.erm.refpds.referencepdsdataservicerequestresponse_v1_0.RefEntityType;
import com.telus.tmi.xmlschema.srv.erm.refpds.referencepdsdataservicerequestresponse_v1_0.XRefInstanceType;

/**
 * @author Alejandro.Hernandez
 *
 */
public class ReferencePDSDataServiceTransformer {

	private static final String CODEDECODE = "CodeDecode";
	private static final String VIEW = "View";
	private static final String XREF = "XRef";
	private static final String BUSINESS_RULE = "BusinessRule";
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(ReferencePDSDataServiceTransformer.class);
	private ReferencePDSDataServiceTransformer(){
		
	}
	
	/**
	 * @param getReferencePdsRequestDO
	 * @return
	 */
	public static GetReferenceData transformRequest(GetReferencePDSDataServiceAdapterRequest getReferencePdsRequestDO) {
		GetReferenceData request = new GetReferenceData();
		request.setAppId(getReferencePdsRequestDO.getAppId());
		return request;
	}

	/**
	 * @param entityName 
	 * @param response
	 * @return GetReferencePDSDataServiceAdapterResponse
	 */
	public static GetReferencePDSDataServiceAdapterResponse transformResponse(GetReferenceDataResponse resp, String entityName) {
		GetReferencePDSDataServiceAdapterResponse adapterResponse = new GetReferencePDSDataServiceAdapterResponse();
		Map<String,String> refPDSTableHashMap = new HashMap<String,String>();
		for (RefEntityType refEntity : resp.getReturn().getRefEntity()) {
			String name = refEntity.getName();
			if(entityName.equalsIgnoreCase(name)){
				// Gary 2018-03-12 - handle BusinessRule - 
				if (BUSINESS_RULE.equalsIgnoreCase(refEntity.getType())){ 
					List<Map<String, String>> result = transformBusinessRuleInstanceTypeToList(refEntity);
					adapterResponse.setReferencePDSTableObjectMap(result);
				} else {
					refPDSTableHashMap = buildHashMap(refEntity);
					adapterResponse.setReferencePDSTableMap(refPDSTableHashMap);
				}
				break;
			}
		}
		
		return adapterResponse;
	}

	/**
	 * @param response
	 * @return GetReferencePDSDataServiceAdapterResponse
	 */
	public static GetReferencePDSDataServiceAdapterResponse transformResponse(GetReferenceDataResponse resp) {
		GetReferencePDSDataServiceAdapterResponse adapterResponse = new GetReferencePDSDataServiceAdapterResponse();
		Map<String,String> refPDSTableHashMap = new HashMap<String,String>();
		
		List<Map<String, String>> referencePDSTableObjectMap = new ArrayList<Map<String, String>>();
		Map<String,String> referencePDSTableMap = new HashMap<String,String>();
		
		for (RefEntityType refEntity : resp.getReturn().getRefEntity()) {
			if (BUSINESS_RULE.equalsIgnoreCase(refEntity.getType())){ 
				List<Map<String, String>> result = transformBusinessRuleInstanceTypeToList(refEntity);
				referencePDSTableObjectMap.addAll(result);
			} else {
				refPDSTableHashMap = buildHashMap(refEntity);
				if(refPDSTableHashMap!=null){
					for(Map.Entry<String, String> entry : refPDSTableHashMap.entrySet()) {
					    String key = entry.getKey();
					    String value = entry.getValue();
					    
					    referencePDSTableMap.put(key, value);
					}
				}				
			}			
		}
		
		adapterResponse.setReferencePDSTableObjectMap(referencePDSTableObjectMap); 
		adapterResponse.setReferencePDSTableMap(referencePDSTableMap); 
		
		return adapterResponse;
	}
	
	
	/**************************************************/
	/* transformBusinessInstance                      */
	/*    extract the code and value into a map       */
	/**************************************************/
	private static void transformBusinessInstance(List<CodeValueType> codeValueTypeList, Map<String, String> resultMap){ 
		
		for (CodeValueType codeValueType : codeValueTypeList){
			String code = codeValueType.getCode();
			String value = "";
			if (codeValueType.getValue().size() > 0){
				value = codeValueType.getValue().get(0);
			}
			//logger.info("transformBusinessInstance", "Code=" + code + " - value=" + value);
			// @TODO extend this to handle multiple value.  But current REFPDS table does not support this kind of structure.
			resultMap.put(code, value);
		}
		 
	}
	
	/**************************************************/
	/* transformBusinessRuleInstanceTypeToMap         */
	/*    transform each row of REFPDS to list of map */
	/**************************************************/
	private static List<Map<String, String>> transformBusinessRuleInstanceTypeToList(RefEntityType refEntity){
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		
		if (refEntity.getInstance() != null && refEntity.getInstance().size() > 0){ 
			for (InstanceType instance :  refEntity.getInstance()){
				BusinessRuleInstanceType businessInstance = (BusinessRuleInstanceType) instance;
				Map<String, String> resultMap = new HashMap<String, String>();
				transformBusinessInstance(businessInstance.getInput(), resultMap);
				transformBusinessInstance(businessInstance.getOutput(), resultMap);
				list.add(resultMap);
			}
		};
		
		return list;
	}
	
	private static Map<String,String> buildHashMap(final RefEntityType refEntity){
		Map<String,String> tableHashMap = new HashMap<String, String>();
		if (CODEDECODE.equalsIgnoreCase(refEntity.getType())) {
			for (final InstanceType instance : refEntity.getInstance()) {
				final CodeDecodeInstanceType codeDecode = (CodeDecodeInstanceType) instance;
				tableHashMap.put(codeDecode.getCode(), codeDecode.getDecode().get(0).getValue());
			}
		}
		if (VIEW.equalsIgnoreCase(refEntity.getType())) {
			for (final InstanceType instance : refEntity.getInstance()) {
				final CodeDecodeInstanceType codeDecode = (CodeDecodeInstanceType) instance;
				tableHashMap.put(codeDecode.getCode(), codeDecode.getDecode().get(0).getValue());
			}
		}
		if (XREF.equalsIgnoreCase(refEntity.getType())) {
			for (final InstanceType instance : refEntity.getInstance()) {
				final XRefInstanceType xref = (XRefInstanceType) instance;
				tableHashMap.put(xref.getFromId().getCode(), xref.getToId().getCode());
			}
		}
		return tableHashMap;
	}

	public static List<BusinessRuleInstanceTypeVO> transformBusinessRuleResponse(GetInstancesResponse wsResponse) {
		List<BusinessRuleInstanceTypeVO> ruleList = new ArrayList<BusinessRuleInstanceTypeVO>();
		if(wsResponse != null) {
			List<InstanceType> wsResponseRule = wsResponse.getReturn();
			for (InstanceType ruleItem : wsResponseRule) {
				BusinessRuleInstanceTypeVO ruleInstance = new BusinessRuleInstanceTypeVO();
				BusinessRuleInstanceType brItem = (BusinessRuleInstanceType) ruleItem;
				ruleInstance.setInput(transform(brItem.getInput()));
				ruleInstance.setOutput(transform(brItem.getOutput()));
				ruleList.add(ruleInstance);
			}
		}
		return ruleList;
	}

	private static List<CodeValueTypeVO> transform(List<CodeValueType> inputList) {
		List<CodeValueTypeVO> resultList = new ArrayList<CodeValueTypeVO>(); 
		if( inputList != null ) {
			for (CodeValueType inputItem : inputList) {
				CodeValueTypeVO resultItem = new CodeValueTypeVO();
				resultItem.setCode(inputItem.getCode());
				resultItem.setValue(inputItem.getValue());
				resultList.add(resultItem);
			}
			
		}
		return resultList;
	}


	
}
