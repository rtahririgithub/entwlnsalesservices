package com.telus.csm.ewlnsc.delegate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.StringUtils;

import com.telus.csm.ewlnsc.adapter.IExchangeForborneStatusSvcAdapter;
import com.telus.csm.ewlnsc.adapter.IServiceAddressMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetServiceAddressDetailsAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetServiceAddressDetailsAdapterResponse;
import com.telus.csm.ewlnsc.adapter.xfs.domain.ExchangeForborneStatusAdapterRequest;
import com.telus.csm.ewlnsc.adapter.xfs.domain.ExchangeForborneStatusAdapterResponse;
import com.telus.csm.ewlnsc.domain.ServiceAddressRequestVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressResponseVO;
import com.telus.csm.ewlnsc.transformer.GetAccessoryOfferListTransformer;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.ResponseMessage;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.ServiceAddress;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;

public class ServiceAddressDelegate {

	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(ServiceAddressDelegate.class);
	
	private static final String RESIDENTIAL = "R";
	
	/**
	 * Return foreborne status indicator for a given address.
	 * 
	 * @param addressId
	 * @param transId
	 * @return
	 */
	public static ServiceAddressResponseVO getServiceAddress(final ServiceAddressRequestVO serviceAddressReqVO) {
		final ServiceAddressResponseVO result = new ServiceAddressResponseVO();
		boolean foreborneInd=false;
		final String methodName = "isForeborne";
		final GetServiceAddressDetailsAdapterRequest request = new GetServiceAddressDetailsAdapterRequest();
		String npaNxx=null;
		if(serviceAddressReqVO!=null){
			if(!StringUtils.isEmpty(serviceAddressReqVO.getAddressId())){
				final ServiceAddress address = new ServiceAddress();
				address.setAddressId(serviceAddressReqVO.getAddressId());
				address.setProvinceStateCode(serviceAddressReqVO.getProvinceCode());
				request.setAddress(address);
				request.setSalesTransactionId(serviceAddressReqVO.getSalesTransactionId());
				final IServiceAddressMgmtSvcAdapter samAdapter = AdapterFactory.getAdapter(IServiceAddressMgmtSvcAdapter.class);
				LOGGER.info(methodName, "calling GetAddressDetails operation.");
				GetServiceAddressDetailsAdapterResponse resp = samAdapter.getServiceAddressDetails(request);
				if(resp!=null && !resp.isMsgHasError() && resp.getResponse()!=null && resp.getResponse().getAddress()!=null){
					LOGGER.info(methodName, "Address details were retrieved from ServiceAddressMgmtSvc");
					ServiceAddress svcAddress = resp.getResponse().getAddress();
					result.setServiceAddress(svcAddress);
					if(!StringUtils.isEmpty(svcAddress.getRatingNpaNxx())){
						LOGGER.info(methodName, "RatingNpaNxx was retrieved from ServiceAddressMgmtSvc");
						npaNxx = svcAddress.getRatingNpaNxx();
					}else{
						if(!StringUtils.isEmpty(serviceAddressReqVO.getNpaNxx())){
							npaNxx = serviceAddressReqVO.getNpaNxx();
						}
					}
				}else if(resp!=null && resp.getResponse()!=null && !resp.getResponse().getResponseMessageList().getResponseMessage().isEmpty()){
					LOGGER.error(null, methodName, "Errors were found when calling downstream for getServiceAddressDetails");
					result.setMessageList(transformMsg(resp.getResponse().getResponseMessageList().getResponseMessage()));
				}
			}else if(!StringUtils.isEmpty(serviceAddressReqVO.getNpaNxx())){
				LOGGER.info(methodName, "ServiceAddressId was not passed in Request, using NpaNxx from Request instead");
				npaNxx = serviceAddressReqVO.getNpaNxx();
			}
			
			//Calling ExchangeService if npaNxx is present
			if(!StringUtils.isEmpty(npaNxx)){
				foreborneInd = callExchangeService(serviceAddressReqVO.getSalesTransactionId(), npaNxx);
			}
		}
		result.setExchangeForborneStatusInd(foreborneInd);
		return result;
	}
	
	private static boolean callExchangeService(String salesTransactionId,String npaNxx){
		boolean result=false;
		String methodName = "callExchangeService";
		final IExchangeForborneStatusSvcAdapter exchAdapter = AdapterFactory.getAdapter(IExchangeForborneStatusSvcAdapter.class);
		final ExchangeForborneStatusAdapterRequest req = new ExchangeForborneStatusAdapterRequest();
		req.setSalesTransactionId(salesTransactionId);
		req.setCustomerType(RESIDENTIAL);
		req.setNpaNxxList(Arrays.asList(npaNxx));
		
		LOGGER.info(methodName, "calling getForborneStatusByNpaNxxList operation.");
		
		final ExchangeForborneStatusAdapterResponse exchResp = exchAdapter.getForborneStatusByNpaNxxList(req);
		if (exchResp.getExchangeForborneStatusList() != null && !exchResp.getExchangeForborneStatusList().isEmpty()) {
			boolean isForborne = exchResp.getExchangeForborneStatusList().get(0).isExchangeForborneStatusInd();
			result = isForborne;
		} else {
			LOGGER.error(null, methodName, "No valid response when calling downstream for getForborneStatusByNpaNxxList");
		}
		return result;
	}

	private static List<MessageList> transformMsg(List<ResponseMessage> responseMessage) {
		List<MessageList> messageList = new ArrayList<MessageList>();
		if(responseMessage!=null && !responseMessage.isEmpty()){
			for(ResponseMessage respMessage : responseMessage){
				MessageList msg = new MessageList();
				msg.setContextData(respMessage.getContextData());
				msg.setDateTimeStamp(respMessage.getDateTimeStamp());
				msg.setErrorCode(respMessage.getErrorCode());
				msg.setMessageList(getMsg(respMessage.getMessageList()));
				msg.setMessageType(respMessage.getMessageType());
				msg.setTransactionId(respMessage.getTransactionId());
				messageList.add(msg);
			}
		}
		return messageList;
	}

	private static List<Message> getMsg(
			com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.Message messageList) {
		List<Message> resultMessageList = new ArrayList<Message>();
		if(messageList!=null){
			Message msg = new Message();
			msg.setLocale(messageList.getLocale());
			msg.setMessage(messageList.getMessage());
			resultMessageList.add(msg);
		}
		return resultMessageList;
	}

	public static ServiceAddressResponseVO getServiceAddressDetails(final ServiceAddressRequestVO serviceAddressReqVO) {
		final GetServiceAddressDetailsAdapterRequest request = new GetServiceAddressDetailsAdapterRequest();
		GetServiceAddressDetailsAdapterResponse response = null;
		ServiceAddressResponseVO result = new ServiceAddressResponseVO();
		final String methodName = "getServiceAddressDetails";

		if (serviceAddressReqVO != null) {
			final IServiceAddressMgmtSvcAdapter samAdapter = AdapterFactory.getAdapter(IServiceAddressMgmtSvcAdapter.class);

			ServiceAddress address = new ServiceAddress();

			if ( (serviceAddressReqVO.getProvinceCode() != null) && (!serviceAddressReqVO.getProvinceCode().isEmpty()) ) {
				address.setProvinceStateCode(serviceAddressReqVO.getProvinceCode());
			}

			if ( (serviceAddressReqVO.getAddressId() != null) && (!serviceAddressReqVO.getAddressId().isEmpty()) ) {
				address.setAddressId(serviceAddressReqVO.getAddressId());
			}

			request.setAddress(address);
	
			request.setSalesTransactionId(serviceAddressReqVO.getSalesTransactionId());
			
			LOGGER.info(methodName, "calling GetAddressDetails operation.");

			response = samAdapter.getServiceAddressDetails(request);

			if (response != null && !response.isMsgHasError() && response.getResponse() != null && response.getResponse().getAddress() != null) {
				LOGGER.info(methodName, "Address details were retrieved from ServiceAddressMgmtSvc");
				result.setServiceAddress(response.getResponse().getAddress());
			}
			else if (response != null && response.getResponse() != null && !response.getResponse().getResponseMessageList().getResponseMessage().isEmpty()) {
				LOGGER.error(null, methodName, "Errors were found when calling downstream for getServiceAddressDetails");
				result.setMessageList(transformMsg(response.getResponse().getResponseMessageList().getResponseMessage()));
			}
		}

		return result;
	}
}
