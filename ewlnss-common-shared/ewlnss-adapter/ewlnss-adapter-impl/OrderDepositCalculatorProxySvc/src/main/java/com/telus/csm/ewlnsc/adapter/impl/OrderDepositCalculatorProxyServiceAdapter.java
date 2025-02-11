package com.telus.csm.ewlnsc.adapter.impl;

import javax.xml.ws.BindingProvider;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.IOrderDepositCalculatorProxySvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.CalculateDepositRequest;
import com.telus.csm.ewlnsc.adapter.domain.CalculateDepositResponse;
import com.telus.csm.ewlnsc.adapter.transformer.OrderDepositCalculatorTransformer;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.StringUtility;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.SOAPServiceAdapter;
import com.telus.tmi.xmlschema.srv.cmo.ordermgmt.orderdepositcalculatorproxyservicerequestresponse_v1.CalculateDeposit;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditdeposittypes_v1.DepositCalulationResult;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.Ping;
import com.telus.wsdl.cmo.ordermgmt.orderdepositcalculatorproxyservice_1.OrderDepositCalculatorProxyServicePortType;
import com.telus.wsdl.cmo.ordermgmt.orderdepositcalculatorproxyservice_1.OrderDepositCalculatorProxyServiceStub;
import com.telus.wsdl.cmo.ordermgmt.orderdepositcalculatorproxyservice_1.PolicyException;
import com.telus.wsdl.cmo.ordermgmt.orderdepositcalculatorproxyservice_1.ServiceException;

public class OrderDepositCalculatorProxyServiceAdapter extends SOAPServiceAdapter implements IOrderDepositCalculatorProxySvcAdapter {

	private static String wsdlUrl = ApplicationProperties.getConfigString("${connections/webServices/depositCalculatorProxySvc/wsdlUrl}");
	private LoggerUtil loggerUtil = LoggerUtil.getLogger(OrderDepositCalculatorProxyServiceAdapter.class);
	private static final String ERROR_PREFIX = "OrderDepositCalculatorProxyServiceAdapter";
	
	public OrderDepositCalculatorProxyServiceAdapter() {
		super();
	}
	
	public OrderDepositCalculatorProxyServiceAdapter(AdapterFeatureDriver featureDrivers) {
		super(featureDrivers);
	}
	
	@Override
	public String getWsdlUrl() {
		return wsdlUrl; 	
	}

	private synchronized OrderDepositCalculatorProxyServicePortType getPort() {
		return (OrderDepositCalculatorProxyServicePortType) getInitilizedPort();
	}
	
	@Override
	protected BindingProvider getNewPort() {
		
		return (BindingProvider) new OrderDepositCalculatorProxyServiceStub().getOrderDepositCalculatorProxyServicePort();
		
	}
	
	@Override
	public String ping() throws PolicyException, ServiceException {
		return getPort().ping(new Ping()).getVersion();
	}
	@Override
	public CalculateDepositResponse calculateDeposit(CalculateDepositRequest calculateDepositRequest) {
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		final String methodName = downStreamSvcName + ".calculateDeposit()"; 
		String contextDataPolicy = methodName;
		
		String contextData = methodName + "[";
		if (StringUtils.isNotBlank(calculateDepositRequest.getSalesTransactionId())){
			contextData += " Transaction ID: " + calculateDepositRequest.getSalesTransactionId() + "]";
		}
		loggerUtil.enter(methodName);
		CalculateDeposit req = OrderDepositCalculatorTransformer.transform(calculateDepositRequest);
		DepositCalulationResult resp = new DepositCalulationResult();
		CalculateDepositResponse response = null;		
		try {
			resp = getPort().calculateDeposit(req);
			response = OrderDepositCalculatorTransformer.transform(resp);
			
		} catch (PolicyException e) {
			LoggerUtil.getStackTrace(e);
			return (CalculateDepositResponse) this
					.handlePolicyException(
							calculateDepositRequest.getSalesTransactionId(), e,
							e.getFaultInfo(), response,
							contextDataPolicy, methodName, ERROR_PREFIX);
		} catch (ServiceException e) {
			LoggerUtil.getStackTrace(e);
			return (CalculateDepositResponse) this
					.handleServiceException(
							calculateDepositRequest.getSalesTransactionId(), e,
							e.getFaultInfo(), response,
							contextData, methodName, ERROR_PREFIX);
		} catch (Exception e) {
			LoggerUtil.getStackTrace(e);
			return (CalculateDepositResponse) this.handleException(
					calculateDepositRequest.getSalesTransactionId(), e, response,
					contextData, methodName);
		}
		
		loggerUtil.exit(methodName);
		return response;
	}

}
