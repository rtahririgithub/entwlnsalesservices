package com.telus.csm.ewlnsc.delegate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.telus.csm.ewlnsc.adapter.ISalesCustomerInfoRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetConsolidatedAccountProfileAdapterRequest;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetConsolidatedAccountProfileAdapterResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.ResponseStatus;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductRequestVO;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductResponseVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.util.EnterpriseSalesServiceUtil;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage.MessageList;

public class AssignedAndPendingProductDelegate {
	
	private AssignedAndPendingProductDelegate() {
	}
	
	public static GetAssignedAndPendingProductResponseVO execute(final GetAssignedAndPendingProductRequestVO param) {
		
		final GetAssignedAndPendingProductResponseVO result = new GetAssignedAndPendingProductResponseVO();
		
		final ISalesCustomerInfoRestSvcAdapter adapter = AdapterFactory.getAdapter(ISalesCustomerInfoRestSvcAdapter.class);
		
		final GetConsolidatedAccountProfileAdapterRequest req = new GetConsolidatedAccountProfileAdapterRequest();
		req.setCustomerId(param.getCustomerId());
		req.setBan(param.getBillingAccountNumber());
		final OperationHeader header = param.getOperationHeader();
		req.setApplicationId(String.valueOf(header.getOriginatorApplicationId()));
		req.setSalesTransactionId(header.getSalesTransactionId());
		req.setBrandId(EnterpriseSalesServiceUtil.getBrandId(header.getBrandCode()));
		req.setCorrelationId(header.getSalesTransactionId());

		if ( (header.isRefreshInd() != null) && (header.isRefreshInd())) {
			req.setRefreshCache(header.isRefreshInd());
		}
		
		if (header.getUserProfile() != null) {
			if (header.getUserProfile().getSalesRepId() != null) {
				req.setSalesRepId(header.getUserProfile().getSalesRepId());
			}

			if (header.getUserProfile().getSalesRepAssociatedOutletList() != null && !header.getUserProfile().getSalesRepAssociatedOutletList().isEmpty()) {
				req.setChannelOutletId(header.getUserProfile().getSalesRepAssociatedOutletList().get(0).getSalesRepAssociatedChannelOutletId());
			}
		}

		final GetConsolidatedAccountProfileAdapterResponse resp = adapter.getConsolidateAcc(req);

		if (resp != null) { 
			//Checking if primaryAccount is not null and has the subscribedProductList
			if (resp.getPrimaryAccountProfile()!=null && resp.getPrimaryAccountProfile().getSubscribedProductList()!=null && !resp.getPrimaryAccountProfile().getSubscribedProductList().isEmpty()){
				enrichResponse(result, resp.getPrimaryAccountProfile().getSubscribedProductList());
			}
			else if(resp.getConsolidatedAccountProfile()!=null && resp.getConsolidatedAccountProfile().getSubscribedProductList()!=null && !resp.getConsolidatedAccountProfile().getSubscribedProductList().isEmpty()){
				enrichResponse(result, resp.getConsolidatedAccountProfile().getSubscribedProductList());
			}
			else if(resp.getResponseStatus()!=null && WLNOfferUtil.responseHasError(resp.getResponseStatus().getStatusCd())){ 
				result.setMessageList(transformMsg(resp.getResponseStatus()));
				result.setMsgHasError(true);
			}
			
			result.setCustomerAccountProfileList(resp.getCustomerAccountProfileList());
			
			if(resp.getPrimaryAccountProfile()!=null){
				result.setPrimaryAccountProfile(resp.getPrimaryAccountProfile());
			}
			
		}

		return result;
	}

	private static void enrichResponse(GetAssignedAndPendingProductResponseVO result,List<SubscribedProductInfoRestVO> subscribedProductList){
		List<SubscribedProductInfoRestVO> assignedProductList = new ArrayList<SubscribedProductInfoRestVO>();
		List<SubscribedProductInfoRestVO> pendingProductList = new ArrayList<SubscribedProductInfoRestVO>();
		for(SubscribedProductInfoRestVO subscribedProduct : subscribedProductList){
			if(subscribedProduct.getProductInPendingOrderStatusInd()!=null && subscribedProduct.getProductInPendingOrderStatusInd()){
				//Product in pending status
				pendingProductList.add(subscribedProduct); 
			}else if(subscribedProduct.getProductInPendingOrderStatusInd()!=null && !subscribedProduct.getProductInPendingOrderStatusInd()){
				//Products in Assigned status
				assignedProductList.add(subscribedProduct);
			}
		}
		if(!assignedProductList.isEmpty()){
			result.setSubscribedProductList(assignedProductList);
		}
		if(!pendingProductList.isEmpty()){
			result.setPendingProductList(pendingProductList);
		}
		
	}
	
	private static List<MessageList> transformMsg(ResponseStatus responseStatus) {
		List<MessageList> messageList = new ArrayList<MessageList>();
		MessageList msgList = new MessageList();
		msgList.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
		msgList.setDateTimeStamp(new Date());
		msgList.setErrorCode(responseStatus.getSystemErrorId());
		msgList.setTransactionId(responseStatus.getCorrelationId());
		msgList.setMessageList(getMsgList(responseStatus.getSystemMessageTxt()));
		return messageList;
	}

	private static List<Message> getMsgList(String systemMessageTxt) {
		List<Message> resultMessageList = new ArrayList<Message>();
		Message msg = new Message();
		msg.setMessage(systemMessageTxt);
		msg.setLocale(EnterpriseWLNSalesServicesConstants.LANG_EN);
		resultMessageList.add(msg);
		return resultMessageList;
	}
}
