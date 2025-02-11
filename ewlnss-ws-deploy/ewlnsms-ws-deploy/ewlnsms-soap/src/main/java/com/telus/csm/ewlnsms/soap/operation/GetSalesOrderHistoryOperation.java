package com.telus.csm.ewlnsms.soap.operation;

import static com.telus.csm.ewlnsc.util.EnterpriseSalesServiceUtil.getEndOfDate;
import static com.telus.csm.ewlnsc.util.EnterpriseSalesServiceUtil.splitString;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.domain.OrderItemSummaryVO;
import com.telus.csm.ewlnsms.core.domain.SearchOrderCoreRequest;
import com.telus.csm.ewlnsms.core.domain.SearchOrderCoreResponse;
import com.telus.csm.ewlnsms.core.operation.SearchOrderCoreOperation;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetSalesOrderHistoryResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.GetSalesOrderHistoryType;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.Order;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.Order.OrderContext;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SystemIntegrationParameterType;

public class GetSalesOrderHistoryOperation {
	
	private static final Logger LOGGER = Logger.getLogger(GetSalesOrderHistoryOperation.class);
	
	private static final SimpleDateFormat SEARCH_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	public GetSalesOrderHistoryResponseType execute(GetSalesOrderHistoryType parameters){
		
		LOGGER.info("Entering GetSalesOrderHistoryOperation.execute()...");

		GetSalesOrderHistoryResponseType response = new GetSalesOrderHistoryResponseType();
		
		SearchOrderCoreRequest coreRequest = transformRequest( parameters );
		
		try {
			
			LOGGER.info("Calling SearchPOCoreOperation...");
			SearchOrderCoreResponse coreResponse = new SearchOrderCoreOperation().execute(coreRequest);
			response = transformResponse(coreResponse);
			
		} catch (Throwable e) {
			LOGGER.error("Error happened when calling GetSalesOrderHistoryOperation -> details: " + e.getMessage() ,e);
		}
		return response;
	}

	private SearchOrderCoreRequest transformRequest(GetSalesOrderHistoryType parameters) {
		
		SearchOrderCoreRequest coreRequest = new SearchOrderCoreRequest();
		
		coreRequest.setOperationHeader(parameters.getOperationHeader());

		if ( CollectionUtils.isNotEmpty( parameters.getOperationHeader().getSystemIntegrationParameterList() ) ) {
			for ( OperationHeader.SystemIntegrationParameterList parameter : parameters.getOperationHeader().getSystemIntegrationParameterList()) {
				if ("SEARCH_FROM_DATE".equals(parameter.getParameterName())) {
					try {
						coreRequest.setStartDate( SEARCH_DATE_FORMAT.parse(parameter.getParameterValue()) );
					} catch (ParseException e) {
					}
				} else if ("SEARCH_TO_DATE".equals(parameter.getParameterName())) {
					try {
						Date requestToDate = SEARCH_DATE_FORMAT.parse( parameter.getParameterValue());
						coreRequest.setEndDate( getEndOfDate(requestToDate)  );
					} catch (ParseException e) {
					}
				}
			}
		}
		
		if ( CollectionUtils.isNotEmpty( parameters.getSearchByCriteriaList() ) ) {
			
			for (GetSalesOrderHistoryType.SearchByCriteriaList criteria : parameters.getSearchByCriteriaList() ) {
				
				if ( "SALES_REP_ID".equals( criteria.getSearchCriteriaName() ) 
					&& StringUtils.isNotBlank( criteria.getSearchCriteriaValueTxt() ) ) {
					
					coreRequest.setSalesRepId( criteria.getSearchCriteriaValueTxt().trim() );
					
				} else if ( "OUTLET_ID_LIST".equals( criteria.getSearchCriteriaName() ) ) {

					coreRequest.setOutletIdList( splitString( criteria.getSearchCriteriaValueTxt(), "," ) );
					
				} else if ( "ORDER_STATUS".equals( criteria.getSearchCriteriaName() )
					&& StringUtils.isNotBlank( criteria.getSearchCriteriaValueTxt() ) ) {
					
					coreRequest.setOrderStatus( criteria.getSearchCriteriaValueTxt() );
				}
			}
		}
		
		return coreRequest;
	}
	
	private GetSalesOrderHistoryResponseType transformResponse(SearchOrderCoreResponse coreResponse) {
		
		LOGGER.info("Entering GetSalesOrderHistoryOperation.transformResponse()");
		
		GetSalesOrderHistoryResponseType response = new GetSalesOrderHistoryResponseType();

		if( CollectionUtils.isNotEmpty( coreResponse.getMessageList() ) ) {
			response.setMessageList(coreResponse.getMessageList());
		}
		
		if (CollectionUtils.isNotEmpty( coreResponse.getOrderItemSummaryList()) ) {
			
			List<Order> orderList = new ArrayList<Order> ();
			
			for( OrderItemSummaryVO orderItem: coreResponse.getOrderItemSummaryList()) {
				
				orderList.add( transformOrderItemSummary(orderItem) );
			}
			
			response.setSalesOrder(orderList);
		}
		
		return response;
	}

	private static Order transformOrderItemSummary(OrderItemSummaryVO orderItem) {
		
		OrderContext orderContext = new OrderContext();
		orderContext.setSalesId( orderItem.getItemId() );
		orderContext.setOrderStatusId( orderItem.getItemStatus() );
		
		List<SystemIntegrationParameterType> sipList = new ArrayList<SystemIntegrationParameterType> ();
		orderContext.setSystemIntegrationParameterList( sipList );
		
		SystemIntegrationParameterType sipPO = new SystemIntegrationParameterType();
		sipPO.setParameterName("PO_ID");
		sipPO.setParameterValue( orderItem.getPoId() );
		sipList.add( sipPO );
		
		SystemIntegrationParameterType sipProduct = new SystemIntegrationParameterType();
		sipProduct.setParameterName("PRODUCT.NAME");
		sipProduct.setParameterValue( orderItem.getProductName());
		sipList.add( sipProduct );
		
		Order order = new Order();
		order.setOrderContext( orderContext );
		return order;
	}

}
