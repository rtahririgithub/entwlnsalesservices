package com.telus.csm.ewlnsc.adapter.impl;

import javax.xml.ws.BindingProvider;

import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.adapter.IConsumerBillingAccountManagementServiceAdapter;
import com.telus.csm.ewlnsc.adapter.domain.CreateBillingAccountAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CreateBillingAccountAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetBillingAccountAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetBillingAccountAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.RegisterBillDeliveryMethodAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.RegisterBillDeliveryMethodAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.UpdateBillDeliveryAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.UpdateBillDeliveryAdapterResponse;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.StringUtility;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.SOAPServiceAdapter;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumerbillingaccountmgmtsvcrequestresponse_v1.CreateBillingAccountResponse;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumerbillingaccountmgmtsvcrequestresponse_v1.GetBillingAccount;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumerbillingaccountmgmtsvcrequestresponse_v1.GetBillingAccountResponse;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumerbillingaccountmgmtsvcrequestresponse_v1.RegisterBillDeliveryMethod;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumerbillingaccountmgmtsvcrequestresponse_v1.RegisterBillDeliveryMethodResponse;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumerbillingaccountmgmtsvcrequestresponse_v1.UpdateBillDelivery;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.Ping;
import com.telus.wsdl.cmo.informationmgmt.consumerbillingaccountmanagementservice_1.ConsumerBillingAccountManagementService_v1_2_PortType;
import com.telus.wsdl.cmo.informationmgmt.consumerbillingaccountmanagementservice_1.ConsumerBillingAccountManagementServiceStub;
import com.telus.wsdl.cmo.informationmgmt.consumerbillingaccountmanagementservice_1.PolicyException;
import com.telus.wsdl.cmo.informationmgmt.consumerbillingaccountmanagementservice_1.ServiceException;

public class ConsumerBillingAccountManagementServiceAdapter extends SOAPServiceAdapter implements IConsumerBillingAccountManagementServiceAdapter {

	private static final Logger LOGGER = Logger.getLogger(ConsumerBillingAccountManagementServiceAdapter.class);
	private static final String ERROR_PREFIX = "ConsumerCustomerMgmtSvcAdapter_";
	
	public ConsumerBillingAccountManagementServiceAdapter() {
		super();
	}

	public ConsumerBillingAccountManagementServiceAdapter(AdapterFeatureDriver featureDrivers) {
		super(featureDrivers);
	}
	
	private static String wsdlUrl = ApplicationProperties.getConfigString("${connections/webServices/consumerBillingAccountManagementService/wsdlUrl}");

	@Override
	public String getWsdlUrl() {
		return wsdlUrl; 	
	}

	private synchronized ConsumerBillingAccountManagementService_v1_2_PortType getPort() {
		return (ConsumerBillingAccountManagementService_v1_2_PortType) getInitilizedPort();
	}

	@Override
	protected BindingProvider getNewPort() {
		return (BindingProvider) new ConsumerBillingAccountManagementServiceStub().getConsumerBillingAccountManagementServicePort();
	}

	@Override
	public CreateBillingAccountAdapterResponse createBillingAccount(final CreateBillingAccountAdapterRequest param) {
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		final String methodName = downStreamSvcName + ".createBillingAccount()"; 
		String contextDataPolicy = methodName;
		
		final CreateBillingAccountAdapterResponse result = new CreateBillingAccountAdapterResponse();
		
		final Long custId = param.getRequest().getNewBillingAccount().getCustomerId();
		
		LOGGER.info("calling down stream createBillingAccount for customer id=" + custId);
		
		final String ctx = methodName + ", transId=" + param.getSalesTransactionId() + ", custId=" + custId;

		try {
			
			final CreateBillingAccountResponse resp = getPort().createBillingAccount(param.getRequest());
			
			result.setResponse(resp);
			
		} catch (PolicyException e) {
			LOGGER.error(e);
			return (CreateBillingAccountAdapterResponse) this.handlePolicyException(
							param.getSalesTransactionId(),  e,
							e.getFaultInfo(), result,
							contextDataPolicy, methodName, ERROR_PREFIX);		
		} catch (ServiceException e) {
			LOGGER.error(e);
			return (CreateBillingAccountAdapterResponse) this.handleServiceException(
							param.getSalesTransactionId(), e,
							e.getFaultInfo(), result,
							ctx, methodName, ERROR_PREFIX);
		} catch (Exception e) {
			LOGGER.error(e);
			return (CreateBillingAccountAdapterResponse) this.handleException(
					param.getSalesTransactionId(), e, result,
					ctx, methodName);
		}
		
		return result;
	}

	@Override
	public String ping() throws PolicyException, ServiceException {
		return getPort().ping(new Ping()).getVersion();	}

	@Override
	public GetBillingAccountAdapterResponse getBillingAccount(GetBillingAccountAdapterRequest requestDO) {
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		final String methodName = downStreamSvcName + ".getBillingAccount()"; 
		String contextDataPolicy = methodName;
		
		final String ctx = methodName + ", transId=" + requestDO.getSalesTransactionId() + ", billingAccountNumber=" + requestDO.getBillingAccountNumber();
		LOGGER.info("calling getBillingAccount for accountNumber: " + requestDO.getBillingAccountNumber());
		
		GetBillingAccountAdapterResponse result = super.getFromCache(requestDO.getCacheKey(), GetBillingAccountAdapterResponse.class);
		if (result != null) {
			return result;
		} else {
			result = new GetBillingAccountAdapterResponse();
		}
		
		try{
			GetBillingAccount downstreamRequest = new GetBillingAccount();
			downstreamRequest.setBillingAccountNumber(requestDO.getBillingAccountNumber());
			downstreamRequest.setBillingSystemId(requestDO.getBillingSystemId());
			
			final GetBillingAccountResponse downstreamResponse = getPort().getBillingAccount(downstreamRequest);
			
			if (downstreamResponse.getBillingAccount() != null) {
				result.setBillingAccount(downstreamResponse.getBillingAccount());
				super.saveToCache(requestDO.getCacheKey(), result);
			}
		}catch(PolicyException e){
			LOGGER.error(e);
			return (GetBillingAccountAdapterResponse) this.handlePolicyException(
					requestDO.getSalesTransactionId(), e,
					e.getFaultInfo(), result,
					contextDataPolicy, methodName, ERROR_PREFIX);	
		}catch(ServiceException e){
			LOGGER.error(e);
			return (GetBillingAccountAdapterResponse) this.handleServiceException(
							requestDO.getSalesTransactionId(), e,
							e.getFaultInfo(), result,
							ctx, methodName, ERROR_PREFIX);
		}catch(Exception e){
			LOGGER.error(e);
			return (GetBillingAccountAdapterResponse) this.handleException(
					requestDO.getSalesTransactionId(), e, result,
					ctx, methodName);
		}
		
		return result;
	}

	@Override
	public RegisterBillDeliveryMethodAdapterResponse registerBillDeliveryMethod(
			RegisterBillDeliveryMethodAdapterRequest requestDO) {
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		final String methodName = downStreamSvcName + ".registerBillDeliveryMethod()"; 
		String contextDataPolicy = methodName;
		
		final String ctx = methodName + ", transId=" + requestDO.getSalesTransactionId() + ", billingAccountNumber=" + requestDO.getBillingAccountNumber();
		LOGGER.info("calling registerBillDeliveryMethod for accountNumber: " + requestDO.getBillingAccountNumber());
		
		RegisterBillDeliveryMethodAdapterResponse result = new RegisterBillDeliveryMethodAdapterResponse();
		
		try{
			RegisterBillDeliveryMethod downstreamRequest = new RegisterBillDeliveryMethod();
			downstreamRequest.setBillingAccountNumber(requestDO.getBillingAccountNumber());
			downstreamRequest.setBillingSystemId(requestDO.getBillingSystemId());
			downstreamRequest.setBillMedia(requestDO.getBillMedia());
			downstreamRequest.setAuditInfo(requestDO.getAuditInfo());
			
			getPort().registerBillDeliveryMethod(downstreamRequest);

		}catch(PolicyException e){
			LOGGER.error(e);
			return (RegisterBillDeliveryMethodAdapterResponse) this.handlePolicyException(
					requestDO.getSalesTransactionId(), e,
					e.getFaultInfo(), result,
					contextDataPolicy, methodName, ERROR_PREFIX);	
		}catch(ServiceException e){
			LOGGER.error(e);
			return (RegisterBillDeliveryMethodAdapterResponse) this.handleServiceException(
							requestDO.getSalesTransactionId(), e,
							e.getFaultInfo(), result,
							ctx, methodName, ERROR_PREFIX);
		}catch(Exception e){
			LOGGER.error(e);
			return (RegisterBillDeliveryMethodAdapterResponse) this.handleException(
					requestDO.getSalesTransactionId(), e, result,
					ctx, methodName);
		}
		
		return result;
	}

	@Override
    //NWLN-10684
	public UpdateBillDeliveryAdapterResponse updateBillDelivery(UpdateBillDeliveryAdapterRequest requestDO) {
		
		String downStreamSvcName = StringUtility.getWebServiceNameFromWsdlUrl(getWsdlUrl());
		final String methodName = downStreamSvcName + ".updateBillDelivery()"; 
		String contextDataPolicy = methodName;
		
		final String ctx = methodName + ", transId=" + requestDO.getSalesTransactionId() + ", billingAccountNumber=" + requestDO.getBillingAccountNumber();
		LOGGER.info("calling updateBillDelivery for accountNumber: " + requestDO.getBillingAccountNumber());
		
		UpdateBillDeliveryAdapterResponse result = new UpdateBillDeliveryAdapterResponse();
		
		try{
			UpdateBillDelivery downstreamRequest = new UpdateBillDelivery();
			downstreamRequest.setBillingAccountNumber(requestDO.getBillingAccountNumber());
			downstreamRequest.setBillingSystemId(requestDO.getBillingSystemId());
			downstreamRequest.setBillMedia(requestDO.getBillMedia());
			downstreamRequest.setNotificationMethod(requestDO.getNotificationMethod());
			downstreamRequest.setEmailAddress(requestDO.getEmailAddress());
			downstreamRequest.setCellPhoneNumber(requestDO.getCellPhoneNumber());
			downstreamRequest.setAuditInfo(requestDO.getAuditInfo());
			
			getPort().updateBillDelivery(downstreamRequest);

		}catch(PolicyException e){
			LOGGER.error(e);
			return (UpdateBillDeliveryAdapterResponse) this.handlePolicyException(
					requestDO.getSalesTransactionId(), e,
					e.getFaultInfo(), result,
					contextDataPolicy, methodName, ERROR_PREFIX);	
		}catch(ServiceException e){
			LOGGER.error(e);
			return (UpdateBillDeliveryAdapterResponse) this.handleServiceException(
							requestDO.getSalesTransactionId(), e,
							e.getFaultInfo(), result,
							ctx, methodName, ERROR_PREFIX);
		}catch(Exception e){
			LOGGER.error(e);
			return (UpdateBillDeliveryAdapterResponse) this.handleException(
					requestDO.getSalesTransactionId(), e, result,
					ctx, methodName);
		}
		
		return result;
	}
	
}
