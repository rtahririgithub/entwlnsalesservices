package com.telus.csm.ewlnsc.adapter.impl;

import java.util.List;

import javax.xml.ws.BindingProvider;

import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.adapter.IEnterpriseSalesServiceFulfillmentSvcAdapter;
import com.telus.csm.ewlnsc.adapter.esfs.domain.CreateOrderItemAdapterRequest;
import com.telus.csm.ewlnsc.adapter.esfs.domain.CreateOrderItemAdapterResponse;
import com.telus.csm.ewlnsc.adapter.esfs.domain.UpdateSalesOrderAdapterRequest;
import com.telus.csm.ewlnsc.adapter.esfs.domain.UpdateSalesOrderAdapterResponse;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.StringUtility;
import com.telus.csm.ewlnsc.util.cacheadapter.CacheAdapterFactory;
import com.telus.csm.ewlnsc.util.cacheadapter.ICacheAdapter;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.SOAPServiceAdapter;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.CreateSalesOrderItem;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.CreateSalesOrderItemResponse;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.CreateSalesOrderItemResponse.MessageList;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.UpdateSalesOrder;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesfulfillmentservicerequestresponse_v5.UpdateSalesOrderResponse;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.Ping;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;
import com.telus.wsdl.mso.channelsalesmgmt.enterprisesalesfulfillmentservice_5.EnterpriseSalesFulfillmentServicePortType;
import com.telus.wsdl.mso.channelsalesmgmt.enterprisesalesfulfillmentservice_5.EnterpriseSalesFulfillmentServiceStub;
import com.telus.wsdl.mso.channelsalesmgmt.enterprisesalesfulfillmentservice_5.ServiceException;


public class EnterpriseSalesServiceFulfillmentSvcAdapter extends SOAPServiceAdapter implements IEnterpriseSalesServiceFulfillmentSvcAdapter {
	
	private static final Logger LOGGER = Logger.getLogger(EnterpriseSalesServiceFulfillmentSvcAdapter.class);
	private static final String ERROR_PREFIX = "EnterpriseSalesServiceFulfillmentSvcAdapter_";
	private static final String TRANS_ID_STR = ", transId=";
	
	public EnterpriseSalesServiceFulfillmentSvcAdapter() {
		super();
	}
	
	public EnterpriseSalesServiceFulfillmentSvcAdapter(AdapterFeatureDriver featureDrivers) {
		super(featureDrivers);
		ICacheAdapter cacheAdapter = CacheAdapterFactory.getSessionCacheAdapter();
		super.setCacheAdapter(cacheAdapter);
	}
	
	private static String wsdlUrl = ApplicationProperties.getConfigString("${connections/webServices/enterpriseFullfilmentSvc/wsdlUrl}");

	@Override
	public String getWsdlUrl() {
		return wsdlUrl; 	
	}

	private synchronized EnterpriseSalesFulfillmentServicePortType getPort() {
		return (EnterpriseSalesFulfillmentServicePortType) getInitilizedPort();
	}
	
	@Override
	protected BindingProvider getNewPort() {
		return (BindingProvider) new EnterpriseSalesFulfillmentServiceStub().getEnterpriseSalesFulfillmentServicePort();
	}
	@Override
	protected synchronized String getApplicationName() {
		return "APP_ENTRSALESSVC";
	}
	
	@Override
	public CreateOrderItemAdapterResponse createOrderItem(final CreateOrderItemAdapterRequest param) {
		CreateOrderItemAdapterResponse result = new CreateOrderItemAdapterResponse();
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		final String methodName = downStreamSvcName + ".getForborneStatusByNpaNxxList()"; 
		String contextDataPolicy = methodName;
		
		LOGGER.info("calling down stream createCustomer");
		final String ctx = methodName + TRANS_ID_STR + param.getSalesTransactionId();

		final CreateSalesOrderItem request = transform(param);
		try {
			
			final CreateSalesOrderItemResponse resp = getPort().createSalesOrderItem(request);
			result = transform(resp);
			
		} catch (ServiceException e) {
			LOGGER.error(e);
			return (CreateOrderItemAdapterResponse) this.handleServiceException(
							param.getSalesTransactionId(), e,
							transform(e), result,
							ctx, methodName, ERROR_PREFIX);
		} catch (Exception e) {
			LOGGER.error(e);
			return (CreateOrderItemAdapterResponse) this.handleException(
					param.getSalesTransactionId(), e, result,
					ctx, methodName);
		}
		
		if (result != null) {
			super.saveToCache(param.getCacheKey(), result);
		}
		
		return result;
	}

	@Override
	public UpdateSalesOrderAdapterResponse updateSalesOrder(final UpdateSalesOrderAdapterRequest param) {
		UpdateSalesOrderAdapterResponse result = new UpdateSalesOrderAdapterResponse();
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		final String methodName = downStreamSvcName + ".getForborneStatusByNpaNxxList()"; 
		String contextDataPolicy = methodName;
		
		LOGGER.info("calling down stream updateSalesOrder");
		final String ctx = methodName + TRANS_ID_STR + param.getSalesTransactionId();

		final UpdateSalesOrder request = transform(param);
		try {
			
			final UpdateSalesOrderResponse resp = getPort().updateSalesOrder(request);
			result = transform(resp);
			
		} catch (ServiceException e) {
			LOGGER.error(e);
			return (UpdateSalesOrderAdapterResponse) this.handleServiceException(
							param.getSalesTransactionId(), e,
							transform(e), result,
							ctx, methodName, ERROR_PREFIX);
		} catch (Exception e) {
			LOGGER.error(e);
			return (UpdateSalesOrderAdapterResponse) this.handleException(
					param.getSalesTransactionId(), e, result,
					ctx, methodName);
		}
		
		if (result != null) {
			super.saveToCache(param.getCacheKey(), result);
		}
		
		return result;
	}	
	
	private UpdateSalesOrderAdapterResponse transform(UpdateSalesOrderResponse resp) {
		UpdateSalesOrderAdapterResponse updateSalesOrderAdapterResponse = new UpdateSalesOrderAdapterResponse();
		updateSalesOrderAdapterResponse.setUpdateSalesOrderResponse(resp);
		return updateSalesOrderAdapterResponse;
	}

	private UpdateSalesOrder transform(UpdateSalesOrderAdapterRequest param) {
		UpdateSalesOrder updateSalesOrder = new UpdateSalesOrder();
		updateSalesOrder.setOperationHeader(param.getOperationHeader());
		updateSalesOrder.setUpdateWirelessSalesOrder(param.getCreateUpdateWirelessSalesOrderType());
		return updateSalesOrder;
	}

	
	private CreateOrderItemAdapterResponse transform(CreateSalesOrderItemResponse resp) {
		CreateOrderItemAdapterResponse createOrderItemAdapterResponse = new CreateOrderItemAdapterResponse();
		createOrderItemAdapterResponse.setCreateSalesOrderItemResponse(resp);
		return createOrderItemAdapterResponse;
	}

	private  com.telus.tmi.xmlschema.xsd.common.exceptions.exceptions_v1_0.ServiceException  transform(ServiceException exception) {
		if (exception != null) {
			com.telus.tmi.xmlschema.xsd.common.exceptions.exceptions_v1_0.ServiceException serviceException = new com.telus.tmi.xmlschema.xsd.common.exceptions.exceptions_v1_0.ServiceException();
			serviceException.setErrorCode(exception.getFaultInfo().getErrorCode());
			serviceException.setErrorMessage(exception.getFaultInfo().getErrorMessage());
			return serviceException;
		}
		return null;
	}
	
	private CreateSalesOrderItem transform(CreateOrderItemAdapterRequest param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String ping() throws Exception {

		return getPort().ping(new Ping()).getVersion();
	}

	
	
}
