package com.telus.csm.ewlnsc.adapter.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.xml.ws.BindingProvider;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.adapter.ICorporateStoresPOSServiceAdapter;
import com.telus.csm.ewlnsc.adapter.ccm.domain.SubmitTransactionToPOSAdapterRequest;
import com.telus.csm.ewlnsc.adapter.ccm.domain.SubmitTransactionToPOSAdapterResponse;
import com.telus.csm.ewlnsc.adapter.ccm.transformer.Transformer;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.StringUtility;
import com.telus.csm.ewlnsc.util.cacheadapter.CacheAdapterFactory;
import com.telus.csm.ewlnsc.util.cacheadapter.ICacheAdapter;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.SOAPServiceAdapter;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseMessage;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseMessageDesc;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.corporatestoresposservicerequestresponse_v1.ApplicationTransactionIdentifier;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.corporatestoresposservicerequestresponse_v1.ErrorList;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.corporatestoresposservicerequestresponse_v1.MessageHeader;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.corporatestoresposservicerequestresponse_v1.OutItem;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.corporatestoresposservicerequestresponse_v1.OutItemType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.corporatestoresposservicerequestresponse_v1.PosIdentifier;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.corporatestoresposservicerequestresponse_v1.Service;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.corporatestoresposservicerequestresponse_v1.SubmitTransactionToPOS;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.corporatestoresposservicerequestresponse_v1.SubmitTransactionToPOSResponse;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.corporatestoresposservicerequestresponse_v1.TransactionItem;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.Ping;
import com.telus.wsdl.mso.channelsalesmgmt.corporatestoresposservice_1.CorporateStoresPOSServicePortType;
import com.telus.wsdl.mso.channelsalesmgmt.corporatestoresposservice_1.CorporateStoresPOSServiceStub;
import com.telus.wsdl.mso.channelsalesmgmt.corporatestoresposservice_1.PolicyException;
import com.telus.wsdl.mso.channelsalesmgmt.corporatestoresposservice_1.ServiceException;


public class CorporateStoresPOSServiceAdapter	extends SOAPServiceAdapter implements ICorporateStoresPOSServiceAdapter {
	
	private static final Logger LOGGER = Logger.getLogger(CorporateStoresPOSServiceAdapter.class);
	private static final LoggerUtil loggerUtil = LoggerUtil.getLogger(CorporateStoresPOSServiceAdapter.class);
	private static final String ERROR_PREFIX = "CorporateStoresPOSServiceAdapter_";
	public CorporateStoresPOSServiceAdapter() {
		super();
	}
	
	public CorporateStoresPOSServiceAdapter(AdapterFeatureDriver featureDrivers) {
		super(featureDrivers);
		ICacheAdapter cacheAdapter = CacheAdapterFactory.getSessionCacheAdapter();
		super.setCacheAdapter(cacheAdapter);
	}
	
	private static String wsdlUrl = ApplicationProperties.getConfigString("${connections/webServices/corporateStoresPOSService/wsdlUrl}");

	@Override
	public String getWsdlUrl() {
		return wsdlUrl; 	
	}

	private synchronized CorporateStoresPOSServicePortType getPort() {
		return (CorporateStoresPOSServicePortType) getInitilizedPort();
	}
	
	@Override
	protected BindingProvider getNewPort() {
		return (BindingProvider) new CorporateStoresPOSServiceStub().getCorporateStoresPOSServicePort();
	}

	@Override
	public String ping() throws PolicyException, ServiceException {
		return getPort().ping(new Ping()).getVersion();
	}
	


	@Override
	public SubmitTransactionToPOSAdapterResponse submitTransactionToPOS(SubmitTransactionToPOSAdapterRequest request) {
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		final String methodName = downStreamSvcName + ".submitTransactionToPOS()"; 
		String contextDataPolicy = methodName;
		
		String contextData = methodName + "[";
		if (StringUtils.isNotBlank(request.getShoppingCartId())){
			contextData += " Sopping Cart ID: " + request.getShoppingCartId() + "]";
		}
		
		SubmitTransactionToPOSAdapterResponse resp = new SubmitTransactionToPOSAdapterResponse();
		SubmitTransactionToPOS req = transform(request);
		SubmitTransactionToPOSResponse result = new SubmitTransactionToPOSResponse();
		
		try {
			result = getPort().submitTransactionToPOS(req);
			resp = transform(result);
			
		} catch (PolicyException e) {
			loggerUtil.error("", methodName, "", e);
			return (SubmitTransactionToPOSAdapterResponse) this
					.handlePolicyException(
							request.getShoppingCartId(), e,
							e.getFaultInfo(), resp,
							contextDataPolicy, methodName, ERROR_PREFIX);
		} catch (ServiceException e) {
			loggerUtil.error("", methodName, "", e);
			return (SubmitTransactionToPOSAdapterResponse) this
					.handleServiceException(
							request.getShoppingCartId(), e,
							e.getFaultInfo(), resp,
							contextData, methodName, ERROR_PREFIX);
		} catch (Exception e) {
			loggerUtil.error("", methodName, "", e);
			return (SubmitTransactionToPOSAdapterResponse) this.handleException(
					request.getShoppingCartId(), e, resp,
					contextData, methodName);
		}
		return resp;
	}
	public  SubmitTransactionToPOS transform(final SubmitTransactionToPOSAdapterRequest param) {
		final SubmitTransactionToPOS result = new SubmitTransactionToPOS();
		
		MessageHeader messageHeader = new MessageHeader();
		
		PosIdentifier posIdentifier = new PosIdentifier();
		
		posIdentifier.setPosToken(param.getPosToken());
		
		messageHeader.setPosIdentifier(posIdentifier);
		
		result.setMessageHeader(messageHeader);
		
		ApplicationTransactionIdentifier applicationTransactionIdentifier = new ApplicationTransactionIdentifier();
		
		applicationTransactionIdentifier.setApplicationId("111");
		applicationTransactionIdentifier.setApplicationRequestId(param.getShoppingCartId());
		applicationTransactionIdentifier.setApplicationTransactionId("0");

		TransactionItem transactionItem = new TransactionItem();

		transactionItem.setApplicationTransactionIdentifier(applicationTransactionIdentifier);

		Service service = new Service();
		
		service.setServiceCode("FFH_ORDER");
		
		OutItemType outItemType = new OutItemType();
		
		outItemType.setService(service );
		
		OutItem outItem = new OutItem();
		
		outItem.setOutItemType(outItemType);
		outItem.setAmount(0.0);
		
		transactionItem.setOutItem(outItem);
		
		transactionItem.setLineType("SALES");
		
		List<TransactionItem> transactionItemList = new ArrayList<TransactionItem>();
		
		transactionItemList.add(transactionItem);
		
		result.setTransactionItem(transactionItemList);
		
	    return result;
	}

	public  SubmitTransactionToPOSAdapterResponse transform(final SubmitTransactionToPOSResponse param) {
		final SubmitTransactionToPOSAdapterResponse result = new SubmitTransactionToPOSAdapterResponse();
		
		result.setSuccessfulProcessInd(true);
		
		result.setAdapterResponseMessage(buildResponseMessage(param));
		
		return result;
	}

	

	private AdapterResponseMessage buildResponseMessage(SubmitTransactionToPOSResponse result) {
		AdapterResponseMessage responseMessage = new AdapterResponseMessage();
		if ((result.getReplyCode() != null && ! "0".equals(result.getReplyCode())) || result.getReplyCode().getErrorList() != null){
			
			responseMessage.setDateTimeStamp(result.getMessageHeader().getTimeStamp());
			responseMessage.setMessageCode(result.getReplyCode().getReplyStatus());
//			responseMessage.setMessageType(result.getMessageType());
//			responseMessage.setTransactionId(result.getMessageHeader().getPosIdentifier().getPosParameter().getTransactionNumber());
			responseMessage.setMesssageDescriptionTextList(buildMessageDescriptionTextList(result));
	//		responseMessage.setContextData(result.getContextData());
		}
		return responseMessage;
	}

	private  List<AdapterResponseMessageDesc> buildMessageDescriptionTextList(
			SubmitTransactionToPOSResponse result) {
		List<AdapterResponseMessageDesc> messageDescList = new ArrayList<AdapterResponseMessageDesc>();
		
		for(ErrorList error : result.getReplyCode().getErrorList()) {
			AdapterResponseMessageDesc msgDesc = new AdapterResponseMessageDesc();

			msgDesc.setLocale(Locale.CANADA);
			msgDesc.setMessageDescriptionText(error.getErrorMessage());
			
			messageDescList.add(msgDesc);
		}
		return messageDescList;
	}

}
