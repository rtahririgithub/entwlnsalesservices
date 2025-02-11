package com.telus.csm.ewlnsms.core.operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.telus.csm.esdg.database.EsdgDatabaseAdapter_1;
import com.telus.csm.esdg.domain.EsdgOrderDO_1;
import com.telus.csm.ewlnsc.domain.MessageDO;
import com.telus.csm.ewlnsc.domain.MessageDescDO;
import com.telus.csm.ewlnsc.domain.OrderItemSummaryVO;
import com.telus.csm.ewlnsc.helper.EnterpriseSalesSvcMessageHelper;
import com.telus.csm.ewlnsc.helper.IEnterpriseSalesSvcMessageHelper;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesErrorCodes;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsms.core.domain.SearchOrderCoreRequest;
import com.telus.csm.ewlnsms.core.domain.SearchOrderCoreResponse;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;


public class SearchOrderCoreOperation {

	private static final LoggerUtil logger = LoggerUtil.getLogger(SearchOrderCoreOperation.class);

	public SearchOrderCoreOperation() {
	}

	public SearchOrderCoreResponse execute(SearchOrderCoreRequest coreRequest) {
		
		String functionName = "SearchPOCoreOperation.execute()";
		logger.enter(functionName);
		
		logger.debug(functionName, coreRequest.toString() );

		SearchOrderCoreResponse result = new SearchOrderCoreResponse();
		
		List<String> errorCodes= inputValidation (coreRequest);
		if ( errorCodes.isEmpty()==false ) {
			result.setMessageList( getMessageListFromErrorCodes(errorCodes,coreRequest.getOperationHeader()) );
			return result;
			
		}
		
		result.setOrderItemSummaryList( searchOrderByCriteria( coreRequest) );
		
		return result;
	}

	private List<OrderItemSummaryVO> searchOrderByCriteria(SearchOrderCoreRequest coreRequest) {
		
		//prepare request
		Map<String,List<String>> criteria = new HashMap<String,List<String>> ();
		if ( StringUtils.isNotBlank( coreRequest.getSalesRepId()) ) {
			criteria.put( "SALES_REP_ID", Arrays.asList( coreRequest.getSalesRepId() ) ) ;
		} 
		if ( CollectionUtils.isNotEmpty(coreRequest.getOutletIdList()) ) {
			criteria.put( "CHNL_OUTLET_ID", coreRequest.getOutletIdList() ) ;
		}
		
		List<String> statuses = new ArrayList<String> ();
		if ( StringUtils.isNotBlank( coreRequest.getOrderStatus() ) ) {
			statuses.add( coreRequest.getOrderStatus() );
		}
		
		//invoke database adapter
		List<EsdgOrderDO_1> resultSet = new EsdgDatabaseAdapter_1().searchOrderByClassifiers(coreRequest.getStartDate(), coreRequest.getEndDate(), criteria, statuses );
		
		//convert the resultSet
		List<OrderItemSummaryVO> orderList = new ArrayList<OrderItemSummaryVO>(); 
		for( EsdgOrderDO_1 order: resultSet) {
			OrderItemSummaryVO item = new OrderItemSummaryVO();
			item.setItemId( order.getSalesContextId() );
			item.setItemStatus( order.getOrderStatus() );
			item.setPoId( order.getSalesInteractionId());
			item.setProductName( order.getTypeCode());
			orderList.add(item);
		}
		return orderList;
	}

	private List<String> inputValidation( SearchOrderCoreRequest coreRequest ) {
		

		List<String> result = new ArrayList<String>();

		if ( coreRequest.getStartDate() == null ) {
			result.add( EnterpriseWLNSalesServicesErrorCodes.GSOH_ERROR_MISSING_START_DATE);
		}

		if ( coreRequest.getEndDate() == null ) {
			result.add( EnterpriseWLNSalesServicesErrorCodes.GSOH_ERROR_MISSING_END_DATE);
		}
		
		if ( StringUtils.isBlank(coreRequest.getSalesRepId() ) && CollectionUtils.isEmpty( coreRequest.getOutletIdList() ) ) {
			result.add( EnterpriseWLNSalesServicesErrorCodes.GSOH_ERROR_MISSING_OUTLETID_SALESREPID);
		}

		return result;		
	}

	
	private List<MessageList> getMessageListFromErrorCodes( List<String> errorList,	OperationHeader operationHeader ) {
		
		List<MessageList> returnMessageList = new ArrayList<MessageList>();
		for(String error : errorList){
			MessageDO messageDO = EnterpriseSalesSvcMessageHelper.getInstance().getMessageDetail(error,	IEnterpriseSalesSvcMessageHelper.MESSAGE_TYPE_ERROR,null, null);
			String contextData = getContextData(operationHeader);
			messageDO.setContextData(contextData);
			messageDO.setDateTimeStamp(operationHeader.getSalesTransactionTimestamp());
			messageDO.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
			MessageList messageList = getMessageListByDO( operationHeader, messageDO );
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

	private String getContextData(OperationHeader operationHeader) {
		StringBuilder returnStr = new StringBuilder();
		if (operationHeader != null) {
			returnStr.append("TransactionId =[");
			returnStr.append(operationHeader.getSalesTransactionId());
			returnStr.append("]");
			//returnStr.append("Validation Errors: "); 
			//returnStr.append(System.getProperty(LINE_SEPARATOR));
			//returnStr.append( errorSb.toString());
		}
		return returnStr.toString();
	}	
}	
