package com.telus.csm.ewlnsms.core.operation;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsms.core.domain.SearchServiceAddressCoreRequest;
import com.telus.csm.ewlnsms.core.domain.SearchServiceAddressCoreResponse;
import com.telus.csm.ewlnsms.core.transformer.SearchServiceAddressTransformer;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;
import com.telus.csm.ewlnsc.adapter.IRestServiceAddressMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.IServiceAddressMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetServiceAddressDetailsAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.Address;
import com.telus.csm.ewlnsc.adapter.domain.AddressByFmsAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetServiceAddressDetailsAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.SearchServiceAddressAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.SearchServiceAddressAdapterResponse;
import com.telus.csm.ewlnsc.domain.MessageDO;
import com.telus.csm.ewlnsc.domain.MessageDescDO;
import com.telus.csm.ewlnsc.helper.AdapterResponseUtil;
import com.telus.csm.ewlnsc.helper.EnterpriseSalesSvcMessageHelper;
import com.telus.csm.ewlnsc.helper.IEnterpriseSalesSvcMessageHelper;

public class SearchServiceAddressCoreOperation {
	
	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(SearchServiceAddressCoreOperation.class);
	
	private static final String LINE_SEPARATOR="line.separator";
	
	//Declaring global StringBuilder that will contain the elements in the request that requires validation.
	StringBuilder errorSb = new StringBuilder();
	
	public SearchServiceAddressCoreResponse execute(SearchServiceAddressCoreRequest requestBO) throws Throwable {
		final String methodName = "SearchServiceAddressCoreOperation.execute()";
		LOGGER.info(methodName, "Starting execute method...");
		SearchServiceAddressCoreResponse searchServiceAddressCoreResponse = new SearchServiceAddressCoreResponse();
		
		//step 1: validateInput
		List<String> errorList = validateInput(requestBO);
		if(!errorList.isEmpty()){
			LOGGER.error(null, methodName, "Errors were found during Request Validation.. building Response back");
			searchServiceAddressCoreResponse.setMessageList(getMessageListFromErrorCodes(errorList,requestBO));
			return searchServiceAddressCoreResponse;
		}
		final SearchServiceAddressTransformer transformer = new SearchServiceAddressTransformer();
		IServiceAddressMgmtSvcAdapter soapAdapter = AdapterFactory.getAdapter(IServiceAddressMgmtSvcAdapter.class);
		//Calling directly Adapters from core
		if(StringUtils.isBlank(requestBO.getServiceAddress().getAddressId())){
			LOGGER.info(methodName, "No AddressId was provided in Request.. calling SearchServiceAddress operation");
			SearchServiceAddressAdapterRequest searchServiceAddressAdapterRequest = transformer.transformRequestSearchAddress(requestBO);		
			SearchServiceAddressAdapterResponse searchAddressAdapterResponse = soapAdapter.searchServiceAddress(searchServiceAddressAdapterRequest);
			if(!searchAddressAdapterResponse.isMsgHasError()){
				if(searchAddressAdapterResponse!=null && searchAddressAdapterResponse.getResponse().getResponseMessageList()==null){
					LOGGER.info(methodName, "No Errors were found during downstream call... building Response with Address Details.");
					searchServiceAddressCoreResponse = transformer.transformResponse(searchAddressAdapterResponse);
				}
				else if(searchAddressAdapterResponse!=null && searchAddressAdapterResponse.getResponse().getResponseMessageList()!=null){
					LOGGER.error(null, methodName, "Errors were found during downstream call... building Response with Error details.");
					searchServiceAddressCoreResponse = transformer.transformResponse(searchAddressAdapterResponse);
				}
			}else{
				AdapterResponseUtil.propagateMessage(searchAddressAdapterResponse, searchServiceAddressCoreResponse.getMessageList());
			}

		}else{
			LOGGER.info(methodName, "AddressId provided... calling GetAddressDetails operation.");
			GetServiceAddressDetailsAdapterRequest searchServiceAddressDetailsAdapterRequest = transformer.transformRequestgetDetails(requestBO);
			GetServiceAddressDetailsAdapterResponse getServiceAddressDetailsResponse = soapAdapter.getServiceAddressDetails(searchServiceAddressDetailsAdapterRequest);
			if(!getServiceAddressDetailsResponse.isMsgHasError()){
				if(getServiceAddressDetailsResponse!=null && getServiceAddressDetailsResponse.getResponse().getAddress()!=null){
					LOGGER.info(methodName, "No Errors were found during downstream call... Building Response with Address Details");
					searchServiceAddressCoreResponse = transformer.transformResponse(getServiceAddressDetailsResponse);
				}
				else if(getServiceAddressDetailsResponse!=null && getServiceAddressDetailsResponse.getResponse().getResponseMessageList()!=null){
					searchServiceAddressCoreResponse = transformer.transformResponse(getServiceAddressDetailsResponse);
				}
			}else{
				AdapterResponseUtil.propagateMessage(getServiceAddressDetailsResponse, searchServiceAddressCoreResponse.getMessageList());
			}
			
		}
		//callAddressManagementSvc(searchServiceAddressCoreResponse);
		return searchServiceAddressCoreResponse;
	}

	private void callAddressManagementSvc(SearchServiceAddressCoreResponse searchServiceAddressCoreResponse) {
		String methodName="callAddressManagementSvc";
		
		IRestServiceAddressMgmtSvcAdapter restAmsAdapter = AdapterFactory.getAdapter(IRestServiceAddressMgmtSvcAdapter.class);

		//If AddressId is present in the response, then call Downstream Rest Service.
				if(searchServiceAddressCoreResponse.getMatchingServiceAddress().getAddress().getAddressId()!=null){
					LOGGER.info(methodName, "AddressId found from ServiceAddressMgmtSvc Response... Calling ServiceAddressManagementService to get GeoLocation details");
					//Calling rest service.
					String fmsId = searchServiceAddressCoreResponse.getMatchingServiceAddress().getAddress().getAddressId();
					String provinceCd = searchServiceAddressCoreResponse.getMatchingServiceAddress().getAddress().getProvinceStateCode();
					LOGGER.info(methodName,"Calling AMS Rest service with fmsId: [" + fmsId + "], provinceCode: ["+provinceCd+"]");
					AddressByFmsAdapterResponse restResponse = restAmsAdapter.searchFmsAddress(fmsId, provinceCd);
					//Manipulating Rest call response.
					if (restResponse != null) {
						if (restResponse.getAddresses() != null) {
							// Getting only the first value of the response
							Address address = restResponse.getAddresses().getAddress().get(0);
							// Getting locationPdsId and gisId
							String locationPdsId = address.getGisAddress().getLpdsId();
							String gisId = address.getGisAddress().getAddressId();
							// Appending values from Rest call to ESS response
							if (!StringUtils.isBlank(locationPdsId)) {
								LOGGER.info(methodName, "LocationPdsId: " + locationPdsId);
								searchServiceAddressCoreResponse.getMatchingServiceAddress().getAddress()
										.setLocationPdsId(locationPdsId);
							}
							if (!StringUtils.isBlank(gisId)) {
								LOGGER.info(methodName, "GisId: " + gisId);
								searchServiceAddressCoreResponse.getMatchingServiceAddress().getAddress().setGisId(gisId);
							}
						} else {
							LOGGER.warn(methodName, "AddressManagementService returns error in the Response");
							LOGGER.error(restResponse.getErrorResponse().getErrId(), methodName,
									"ErrorMessage: " + restResponse.getErrorResponse().getErrMessage() + ", Status: "
											+ restResponse.getErrorResponse().getStatus());
						}
					}
					
				}else{
					LOGGER.warn(methodName, "No AddressId found, AMS services is not going to be called.");
				}
	}

	
	private List<MessageList> getMessageListFromErrorCodes(List<String> errorList,
			SearchServiceAddressCoreRequest request) {
	List<MessageList> returnMessageList = new ArrayList<MessageList>();
	for(String error : errorList){
		MessageDO messageDO = EnterpriseSalesSvcMessageHelper
				.getInstance()
				.getMessageDetail(
						error,
						IEnterpriseSalesSvcMessageHelper.MESSAGE_TYPE_ERROR,
						null, null);
		String contextData = getContextData(request);
		messageDO.setContextData(contextData);
		messageDO.setDateTimeStamp(request.getOperationHeader().getSalesTransactionTimestamp());
		messageDO.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
		MessageList messageList = getMessageListByDO(request.getOperationHeader(), messageDO);
		returnMessageList.add(messageList);
	}
	return returnMessageList;
	}
	
	private MessageList getMessageListByDO(OperationHeader operationHeader, MessageDO msg) {
		MessageList messageList = new MessageList();
		messageList.setErrorCode(msg.getMessageCode());
		messageList.setTransactionId(operationHeader.getSalesTransactionId());
		messageList.setDateTimeStamp((msg.getDateTimeStamp() != null ? msg.getDateTimeStamp() : null));
		messageList.setMessageType(msg.getMessageType());
		messageList.setContextData(msg.getContextData());
		List<MessageDescDO> messageDescs = msg.getMesssageDescriptionTextList();
		if(messageDescs != null && !messageDescs.isEmpty()){
			for (MessageDescDO desc : messageDescs) {
				Message message = new Message();
				message.setLocale(desc.getLocale().toString());
				message.setMessage(desc.getMessageDescriptionText());
				messageList.getMessageList().add(message);
			}		
		}
		return messageList;
	}

	private String getContextData(SearchServiceAddressCoreRequest request) {
		StringBuilder returnStr = new StringBuilder();
		OperationHeader operationHeader = request.getOperationHeader();
		if (operationHeader != null) {
			returnStr.append("TransactionId =[");
			returnStr.append(operationHeader.getSalesTransactionId());
			returnStr.append("]");
			returnStr.append("Validation Errors: "); 
			returnStr.append(System.getProperty(LINE_SEPARATOR));
			returnStr.append(errorSb.toString());
		}
		return returnStr.toString();
	}

	private List<String> validateInput(SearchServiceAddressCoreRequest requestBO) {
		//Creating flag to control if there are errors in the Request.
		boolean isError=false;
		final String methodName = "SearchServiceAddressCoreOperation.validateInput()";
		List<String> messageList = new ArrayList<String>();
		if(requestBO.getOperationHeader()==null){
			LOGGER.error(EnterpriseWLNSalesServicesErrorCodes.SEARCH_ADDRESS_INPUT_VALIDATION_ERROR, methodName, "Operation Header cannot be null.");
			messageList.add(EnterpriseWLNSalesServicesErrorCodes.SEARCH_ADDRESS_INPUT_VALIDATION_ERROR);
			return messageList;
		}
		if(!StringUtils.isBlank(requestBO.getServiceAddress().getAddressId())){
			LOGGER.info(methodName, "AddressId was provided in request, validating province Code");
			if(StringUtils.isBlank(requestBO.getServiceAddress().getProvinceStateCode())){
				LOGGER.error(EnterpriseWLNSalesServicesErrorCodes.SEARCH_ADDRESS_INPUT_VALIDATION_ERROR, methodName, "Province State code is mandatory if FMSAddressId is provided");
				errorSb.append("Province State code is mandatory if FMSAddressId is provided");
				errorSb.append(System.getProperty(LINE_SEPARATOR));
				isError=true;
			}
		}
		if(isError){
			messageList.add(EnterpriseWLNSalesServicesErrorCodes.SEARCH_ADDRESS_INPUT_VALIDATION_ERROR);
		}
		return messageList;
	}

	
}
