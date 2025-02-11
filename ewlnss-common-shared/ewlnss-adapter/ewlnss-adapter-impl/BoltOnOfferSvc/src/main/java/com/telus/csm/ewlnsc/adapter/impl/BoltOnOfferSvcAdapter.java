package com.telus.csm.ewlnsc.adapter.impl;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.BindingProvider;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.IBoltOnOfferSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetAvailableServiceInstanceListAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetAvailableServiceInstanceListAdapterResponse;
import com.telus.csm.ewlnsc.adapter.transformer.BoltOnOfferSvcTransformer;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.EnterpriseSalesServiceUtil;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.StringUtility;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.SOAPServiceAdapter;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseMessage;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseRelatedMessage;
import com.telus.tmi.xmlschema.srv.mso.campaignmgmt.boltonofferservicerequestresponse_v3.GetAvailableServiceInstanceList;
import com.telus.tmi.xmlschema.srv.mso.campaignmgmt.boltonofferservicerequestresponse_v3.GetAvailableServiceInstanceListResponse;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.Ping;
import com.telus.wsdl.mso.campaignmgmt.boltonofferservice_3.BoltOnOfferServicePortType;
import com.telus.wsdl.mso.campaignmgmt.boltonofferservice_3.BoltOnOfferServiceStub;
import com.telus.wsdl.mso.campaignmgmt.boltonofferservice_3.ServiceException;

public class BoltOnOfferSvcAdapter extends SOAPServiceAdapter implements IBoltOnOfferSvcAdapter {

	private static LoggerUtil logger = LoggerUtil.getLogger(BoltOnOfferSvcAdapter.class);
	private static final String ERROR_PREFIX = "BoltOnOfferSvcAdapter_";

	private static String wsdlUrl = ApplicationProperties
			.getConfigString("${connections/webServices/boltOnOfferSvc/wsdlUrl}");

	public BoltOnOfferSvcAdapter(AdapterFeatureDriver featureDrivers) {
		super(featureDrivers);
	}

	@Override
	public String ping() throws ServiceException {
		return getPort().ping(new Ping()).toString();
	}

	@Override
	public GetAvailableServiceInstanceListAdapterResponse getAvailableServiceInstanceList(GetAvailableServiceInstanceListAdapterRequest request) {
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		final String methodName = downStreamSvcName + ".getAvailableServiceInstanceList()";

		String contextData = methodName + "[";
		if (StringUtils.isNotBlank(request.getSalesTransactionId())) {
			contextData += " Transaction ID: " + request.getSalesTransactionId() + "]";
		}

		GetAvailableServiceInstanceList req = BoltOnOfferSvcTransformer.transformRequest(request);
		GetAvailableServiceInstanceListAdapterResponse adapterResponse = null;

		if (!request.isRefreshCache()) {
			adapterResponse = super.getFromCache(request.getCacheKey(), GetAvailableServiceInstanceListAdapterResponse.class);

			if (adapterResponse != null) {
				return adapterResponse;
			}
		}

		try {
			GetAvailableServiceInstanceListResponse response = getPort().getAvailableServiceInstanceList(req);
			adapterResponse = BoltOnOfferSvcTransformer.transformResponse(response);
		}
		catch (ServiceException e) {
			logger.error(e);

			return (GetAvailableServiceInstanceListAdapterResponse) handleServiceException(
					request.getSalesTransactionId(), e, adapterResponse, contextData, methodName, ERROR_PREFIX);
		}
		catch (Exception e) {
			logger.error(e);

			return (GetAvailableServiceInstanceListAdapterResponse) this.handleException(request.getSalesTransactionId(),
					e, adapterResponse, contextData, methodName);
		}

		if (adapterResponse != null && !adapterResponse.isMsgHasError()) {
			super.saveToCache(request.getCacheKey(), adapterResponse);
		}

		return adapterResponse;
	}

	@Override
	public String getWsdlUrl() {
		return wsdlUrl;
	}

	@Override
	protected BindingProvider getNewPort() {
		return (BindingProvider) new BoltOnOfferServiceStub().getBoltOnOfferServicePort();
	}

	private BoltOnOfferServicePortType getPort() {
		return (BoltOnOfferServicePortType) getInitilizedPort();
	}

	@Override
	protected synchronized String getApplicationName() {
		// TODO: Remove this method after contract is acquired for WLN app
		return "APP_FFHBOLTON";
	}

	@Override
	protected synchronized String getApplicationPwd() {
		// TODO: Remove this method after contract is acquired for WLN app
		return "soaorgid";
	}

	private AdapterResponseBase handleServiceException(
			final String transactionid,
			final ServiceException e,
			final AdapterResponseBase responseDO, final String contextData, final String methodName,
			final String errorPrefix) {
		String separatorStr = " --- ";
		String CONTEXT_DATA_STR = "- Context Data - ";

		logger.error(e.getFaultInfo().getErrorCode(), methodName, e.getFaultInfo().getErrorMessage()+ CONTEXT_DATA_STR + contextData);

		final String ctx = contextData + separatorStr
				+ e.getFaultInfo().getErrorMessage() + separatorStr
				+ e.getFaultInfo().getErrorCode() + separatorStr
				+ e.getFaultInfo().getErrorMessage() + separatorStr
				+ e.getFaultInfo().getVariables();

		final AdapterResponseMessage messageDO = new AdapterResponseMessage(e,
				messageHelper.getMappedMessageCode(errorPrefix + e.getFaultInfo().getErrorCode()), 
				ctx, transactionid, EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_EXCEPTION);
		messageHelper.populateErrorMessageDescTextListForPolicyAndService(messageDO);
		responseDO.setAdapterResponseMessage(messageDO);
		
		//populate relatedMessages element
		AdapterResponseRelatedMessage relatedMessage = new AdapterResponseRelatedMessage();
		relatedMessage.setRelatedErrorTypeCd(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
		relatedMessage.setRelatedErrorCd(e.getFaultInfo().getErrorCode());
		if (!StringUtils.isEmpty(e.getFaultInfo().getErrorMessage())){
			relatedMessage.setRelatedErrorMessageTxt(e.getFaultInfo().getErrorMessage());
		}else{
			relatedMessage.setRelatedErrorMessageTxt(EnterpriseSalesServiceUtil.getStackTrace(e));
		}
		List<AdapterResponseRelatedMessage> relatedMessageList = new ArrayList<AdapterResponseRelatedMessage>();
		relatedMessageList.add(relatedMessage);
		messageDO.setRelatedMessageList(relatedMessageList);
				
		responseDO.setMsgHasError(true);

		return responseDO;
	}
}