package com.telus.csm.ewlnsc.adapter.impl;

import javax.xml.ws.BindingProvider;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.IDepositMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.CancelDepositAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CancelDepositAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.CreateDepositAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CreateDepositAdapterResponse;
import com.telus.csm.ewlnsc.adapter.transformer.DepositMgmtSvcTransformer;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.StringUtility;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.SOAPServiceAdapter;
import com.telus.tmi.xmlschema.srv.cmo.billingaccountmgmt.depositmanagementservicerequestresponse_v1.CancelDeposit;
import com.telus.tmi.xmlschema.srv.cmo.billingaccountmgmt.depositmanagementservicerequestresponse_v1.CancelDepositResponse;
import com.telus.tmi.xmlschema.srv.cmo.billingaccountmgmt.depositmanagementservicerequestresponse_v1.CreateDeposit;
import com.telus.tmi.xmlschema.srv.cmo.billingaccountmgmt.depositmanagementservicerequestresponse_v1.CreateDepositResponse;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v2.Ping;
import com.telus.wsdl.cmo.billingaccountmgmt.depositmanagementservice_1.DepositManagementServicePortType;
import com.telus.wsdl.cmo.billingaccountmgmt.depositmanagementservice_1.DepositManagementServiceStub;
import com.telus.wsdl.cmo.billingaccountmgmt.depositmanagementservice_1.ServiceException;

public class DepositMgmtSvcAdapter extends SOAPServiceAdapter implements IDepositMgmtSvcAdapter {

	private static LoggerUtil logger = LoggerUtil.getLogger(DepositMgmtSvcAdapter.class);
	private static final String ERROR_PREFIX = "DepositMgmtSvcAdapter_";

	private static String wsdlUrl = ApplicationProperties
			.getConfigString("${connections/webServices/depositMgmtSvc/wsdlUrl}");

	public DepositMgmtSvcAdapter(AdapterFeatureDriver featureDrivers) {
		super(featureDrivers);
	}

	@Override
	public String ping() throws ServiceException {
		return getPort().ping(new Ping()).toString();
	}

	@Override
	public CreateDepositAdapterResponse createDeposit(CreateDepositAdapterRequest request) {
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		final String methodName = downStreamSvcName + ".createDeposit()";

		String contextData = methodName + "[";
		if (StringUtils.isNotBlank(request.getSalesTransactionId())) {
			contextData += " Transaction ID: " + request.getSalesTransactionId() + "]";
		}

		CreateDeposit req = DepositMgmtSvcTransformer.transformRequest(request);
		CreateDepositResponse result = new CreateDepositResponse();
		CreateDepositAdapterResponse resp = new CreateDepositAdapterResponse();

		try {

			result = getPort().createDeposit(req);
			resp = DepositMgmtSvcTransformer.transformResponse(result);

		} catch (ServiceException e) {
			logger.error(e);
			return (CreateDepositAdapterResponse) this.handleServiceException(
					request.getSalesTransactionId(), e, e.getFaultInfo(), resp, contextData, methodName, ERROR_PREFIX);
		} catch (Exception e) {
			logger.error(e);
			return (CreateDepositAdapterResponse) this.handleException(request.getSalesTransactionId(),
					e, resp, contextData, methodName);
		}

		return resp;
	}

	@Override
	public CancelDepositAdapterResponse cancelDeposit(CancelDepositAdapterRequest request) {
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		final String methodName = downStreamSvcName + ".cancelDeposit()";

		String contextData = methodName + "[";
		if (StringUtils.isNotBlank(request.getSalesTransactionId())) {
			contextData += " Transaction ID: " + request.getSalesTransactionId() + "]";
		}

		CancelDeposit req = DepositMgmtSvcTransformer.transformRequest(request);
		CancelDepositResponse result = new CancelDepositResponse();
		CancelDepositAdapterResponse resp = new CancelDepositAdapterResponse();

		try {

			result = getPort().cancelDeposit(req);
			resp = DepositMgmtSvcTransformer.transformResponse(result);

		} catch (ServiceException e) {
			logger.error(e);
			return (CancelDepositAdapterResponse) this.handleServiceException(
					request.getSalesTransactionId(), e, e.getFaultInfo(), resp, contextData, methodName, ERROR_PREFIX);
		} catch (Exception e) {
			logger.error(e);
			return (CancelDepositAdapterResponse) this.handleException(request.getSalesTransactionId(),
					e, resp, contextData, methodName);
		}

		return resp;
	}

	@Override
	public String getWsdlUrl() {
		return wsdlUrl;
	}

	@Override
	protected BindingProvider getNewPort() {
		return (BindingProvider) new DepositManagementServiceStub().getDepositManagementServicePort();
	}

	private DepositManagementServicePortType getPort() {
		return (DepositManagementServicePortType) getInitilizedPort();
	}

	@Override
	protected synchronized String getApplicationName() {
		// TODO: Remove this method after contract is acquired for WLN app
		return "APP_ENTRSALESSVC";
	}

}
