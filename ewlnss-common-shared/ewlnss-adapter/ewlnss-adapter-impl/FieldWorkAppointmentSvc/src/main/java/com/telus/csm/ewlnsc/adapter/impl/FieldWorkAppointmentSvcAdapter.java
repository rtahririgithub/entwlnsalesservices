package com.telus.csm.ewlnsc.adapter.impl;

import javax.xml.ws.BindingProvider;

import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.adapter.IFieldWorkAppointmentSvcAdapter;
import com.telus.csm.ewlnsc.adapter.fwa.domain.FieldWorkAppointmentAdapterRequest;
import com.telus.csm.ewlnsc.adapter.fwa.domain.FieldWorkAppointmentAdapterResponse;
import com.telus.csm.ewlnsc.adapter.fwa.transformer.Transformer;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.StringUtility;
import com.telus.csm.ewlnsc.util.cacheadapter.CacheAdapterFactory;
import com.telus.csm.ewlnsc.util.cacheadapter.ICacheAdapter;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.SOAPServiceAdapter;
import com.telus.tmi.xmlschema.srv.rmo.processmgmt.fieldworkappointmentservicerequestresponse_v3.SearchAvailableAppointmentList;
import com.telus.tmi.xmlschema.srv.rmo.processmgmt.fieldworkappointmentservicerequestresponse_v3.SearchAvailableAppointmentListResponse;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.Ping;
import com.telus.wsdl.rmo.processmgmt.fieldworkappointmentservice_3.FieldWorkAppointmentServicePortType;
import com.telus.wsdl.rmo.processmgmt.fieldworkappointmentservice_3.FieldWorkAppointmentServiceStub;
import com.telus.wsdl.rmo.processmgmt.fieldworkappointmentservice_3.ServiceException;

public class FieldWorkAppointmentSvcAdapter extends SOAPServiceAdapter implements IFieldWorkAppointmentSvcAdapter {
	
	private static final Logger LOGGER = Logger.getLogger(FieldWorkAppointmentSvcAdapter.class);
	private static final String ERROR_PREFIX = "FieldWorkAppointmentSvcAdapter_";
	private static final String TRANS_ID_STR = ", transId=";
	
	public FieldWorkAppointmentSvcAdapter() {
		super();
	}
	
	public FieldWorkAppointmentSvcAdapter(AdapterFeatureDriver featureDrivers) {
		super(featureDrivers);
		ICacheAdapter cacheAdapter = CacheAdapterFactory.getSessionCacheAdapter();
		super.setCacheAdapter(cacheAdapter);
	}
	
	private static String wsdlUrl = ApplicationProperties.getConfigString("${connections/webServices/fieldWorkAppointmentSvc/wsdlUrl}");

	@Override
	public String getWsdlUrl() {
		return wsdlUrl; 	
	}

	private synchronized FieldWorkAppointmentServicePortType getPort() {
		return (FieldWorkAppointmentServicePortType) getInitilizedPort();
	}
	
	@Override
	protected BindingProvider getNewPort() {
		return (BindingProvider) new FieldWorkAppointmentServiceStub().getFieldWorkAppointmentServicePort();
	}

	@Override
	public String ping() throws ServiceException {
		return getPort().ping(new Ping()).getVersion();
	}
	
	@Override
	public FieldWorkAppointmentAdapterResponse searchAvailableAppointmentList(final FieldWorkAppointmentAdapterRequest param) {
		FieldWorkAppointmentAdapterResponse result;
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		final String methodName = downStreamSvcName + ".searchAvailableAppointmentList()"; 
		
		result = super.getFromCache(param.getCacheKey(), FieldWorkAppointmentAdapterResponse.class);
		if (result != null) {
			return result;
		}
		
		LOGGER.info("calling down stream FieldWorkAppointment service...");
		final String ctx = methodName + TRANS_ID_STR + param.getSalesTransactionId();
		
		result = new FieldWorkAppointmentAdapterResponse();
		final SearchAvailableAppointmentList request = Transformer.transform(param);
		try {
			
			final SearchAvailableAppointmentListResponse resp = getPort().searchAvailableAppointmentList(request);
			result = Transformer.transform(resp);
			
		} catch (ServiceException e) {
			LOGGER.error(e);
			return (FieldWorkAppointmentAdapterResponse) this.handleException(
					param.getSalesTransactionId(), e, result,
					ctx, methodName);			
		} catch (Exception e) {
			LOGGER.error(e);
			return (FieldWorkAppointmentAdapterResponse) this.handleException(
					param.getSalesTransactionId(), e, result,
					ctx, methodName);
		}
		
		if (result != null) {
			super.saveToCache(param.getCacheKey(), result);
		}
		
		return result;
	}

}
