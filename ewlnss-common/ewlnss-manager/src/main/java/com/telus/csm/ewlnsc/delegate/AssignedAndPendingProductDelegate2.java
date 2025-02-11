package com.telus.csm.ewlnsc.delegate;



import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.NamingException;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.IConsumerCustomerMgmtSvcAdapter;
import com.telus.csm.ewlnsc.adapter.IOrderQueryRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.IWirelineSalesEJBAdapter;
import com.telus.csm.ewlnsc.adapter.ccm.domain.GetFullCustomerInfoAdapterRequest;
import com.telus.csm.ewlnsc.adapter.scis.domain.AddressVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductSummaryVO;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductRequestVO;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductResponseVO;
import com.telus.csm.ewlnsc.domain.GetOffersRequestVO;
import com.telus.csm.ewlnsc.helper.WirelineProductHelper;
import com.telus.csm.ewlnsc.task.GetFullCustomerInfoTask;
import com.telus.csm.ewlnsc.task.GetOrderListByCustomerFromOrderQueryServiceTask;
import com.telus.csm.ewlnsc.task.GetPendingOrdersTask;
import com.telus.csm.ewlnsc.task.GetProductInstanceListByCustomerFromOrderQueryServiceTask;
import com.telus.csm.ewlnsc.task.GetProductInstanceWithIdsByCustomerFromOrderQueryServiceTask;
import com.telus.csm.ewlnsc.task.TaskExecutionResult;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.workmanager.ICommonJWorkManager;
import com.telus.csm.ewlnsc.util.workmanager.WorkManagerFactory;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumercustomermgmtsvcrequestresponse_v2.FullCustomer;
import com.telus.tmi.xmlschema.xsd.customer.customerbill.customer_billing_sub_domain_v2.BillingAccount;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Message;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.teluscommonaddresstypes_v7.CivicAddress;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage;
import com.telus.tmi.xmlschema.xsd.product.productinstance.customer_product_instance_sub_domain_v3.ProductInstance;

import commonj.work.Work;
import commonj.work.WorkException;

public class AssignedAndPendingProductDelegate2 {
	
	private static final Object UNKNOWN_ACCOUNT = null;
	private static final long SERVICE_CALL_TIMEOUT = 0;
	private static final String OP_SERVICE_UNAVAILABLE = null;
	static final String CUSTOMER_ODS_PRIMARY_PRODUCT_TYPE_CD = "P";
	
	private static final String PROV_CD_QUEBEC = "QC";
	private static final String PROV_CD_NEWFOUNDLAND = "NL";
	private static final String PROV_CD_QUEBEC_OLD = "PQ";
	private static final String PROV_CD_NEWFOUNDLAND_OLD = "NF";
	public final static String COUNTRY_CODE_CAN = "CAN";
	public static final String CODS_BILLING_ADDRESS_TYPE_CODE_CANADA = "C";
	public static final String CODS_BILLING_ADDRESS_TYPE_CODE_USA = "U";

	private static final LoggerUtil logger = LoggerUtil.getLogger(AssignedAndPendingProductDelegate2.class);
	
	ICommonJWorkManager workManager ;
	

	IWirelineSalesEJBAdapter wirelineSalesEJBAdapter;
	
	public AssignedAndPendingProductDelegate2(IWirelineSalesEJBAdapter wirelineSalesEJBAdapter) {
		this.wirelineSalesEJBAdapter = wirelineSalesEJBAdapter;
	}
	
	public  GetAssignedAndPendingProductResponseVO execute(final GetAssignedAndPendingProductRequestVO param,final GetOffersRequestVO offersRequest) {
		
		try {
			long sessionId = System.currentTimeMillis();
			String agentUid = "AUTO_"+param.getOperationHeader().getOriginatorApplicationId();
            String salesTransactionId = param.getOperationHeader().getSalesTransactionId();
			final GetAssignedAndPendingProductResponseVO result = new GetAssignedAndPendingProductResponseVO();
			List<Work> workTaskList = new ArrayList<Work>();
			WirelineProductHelper wirelineProductHelper = new WirelineProductHelper();
			
			//Initialize the adpater
			workManager = WorkManagerFactory.getCommonJWorkManager();

			//Initialize the adpaters
			IConsumerCustomerMgmtSvcAdapter  getFullCustomerInfoAdapter = AdapterFactory.getAdapter(IConsumerCustomerMgmtSvcAdapter.class);
			IOrderQueryRestSvcAdapter        orderQueryRestSvcAdapter =   AdapterFactory.getAdapter(IOrderQueryRestSvcAdapter.class, new AdapterFeatureDriver());  
			//IWirelineSalesEJBAdapter wirelineSalesEJBAdapter = (IWirelineSalesEJBAdapter) AdapterFactory.getAdapter("IWirelineOrderManager");
			
			//Initialize the tasks
			GetFullCustomerInfoAdapterRequest getFullCustomerInfoAdapterRequest = new GetFullCustomerInfoAdapterRequest(Long.valueOf(param.getCustomerId()), param.getOperationHeader().getSalesTransactionId());
			GetFullCustomerInfoTask codsFullCustomerInfoTask = new GetFullCustomerInfoTask(getFullCustomerInfoAdapterRequest, getFullCustomerInfoAdapter);
			GetProductInstanceListByCustomerFromOrderQueryServiceTask getAssignedProductInstanceListTask = new GetProductInstanceListByCustomerFromOrderQueryServiceTask(Long.valueOf(param.getCustomerId()), agentUid, sessionId, salesTransactionId, orderQueryRestSvcAdapter, wirelineProductHelper);
			GetOrderListByCustomerFromOrderQueryServiceTask getCustomerOrdersTask = new GetOrderListByCustomerFromOrderQueryServiceTask(Long.valueOf(param.getCustomerId()), agentUid, sessionId, salesTransactionId, orderQueryRestSvcAdapter, wirelineProductHelper);
			GetPendingOrdersTask getPendingOrderstask = new GetPendingOrdersTask(Long.valueOf(param.getCustomerId()), sessionId, wirelineSalesEJBAdapter);
			
			//prepare the task for execution
			workTaskList.add(getCustomerOrdersTask);
			workTaskList.add(getAssignedProductInstanceListTask);
			workTaskList.add(codsFullCustomerInfoTask);
			workTaskList.add(getPendingOrderstask);
			Collection<Work> responseTaskList = null;

			responseTaskList = workManager.processTasks(workTaskList);

			//collect errors from task
			collectErrors(workTaskList, result);
			
			if (result.hasError()){
				//Onf the tasks failed stop the process
				return result;
			}
			FullCustomer fullCustomer =  codsFullCustomerInfoTask.getResult().getFullCustomer();
			Map<String, ArrayList<SubscribedProductSummaryVO>> subscribedProductsByChannelTransactionId = getCustomerOrdersTask.getResponse().getServiceCallResponse();
			Map<String, ArrayList<SubscribedProductSummaryVO>> subscribedProductsByAccountNumber = getCustomerOrdersTask.getResponse().getServiceCallResponse();
			List<SubscribedProductSummaryVO> consolidatedSubscribedProducts = new ArrayList<SubscribedProductSummaryVO>();
			populateSubscribedProductsForAccount(fullCustomer.getProductInstanceList(), param.getBillingAccountNumber(), consolidatedSubscribedProducts, true);
			getAssignedProductInstanceDetailWithBatch(Long.valueOf(param.getCustomerId()), Long.valueOf(param.getBillingAccountNumber()), agentUid, sessionId,  getAssignedProductInstanceListTask, orderQueryRestSvcAdapter );
			identifyBanForPendingOmsOrders(getCustomerOrdersTask, fullCustomer);
			Map<String, SubscribedProductSummaryVO> assignedProductSummaryMap = getAssignedProductInstanceListTask.getResponse().getServiceCallResponse();
			enrichProductsListWithOmsInformation(param.getBillingAccountNumber(), consolidatedSubscribedProducts, assignedProductSummaryMap , fullCustomer);
			enrichProductsListWithPendingOmsInformation(param.getBillingAccountNumber(), consolidatedSubscribedProducts, subscribedProductsByChannelTransactionId, subscribedProductsByAccountNumber, fullCustomer);
			enrichWithPendingOrdersFromVesta(param.getBillingAccountNumber(), consolidatedSubscribedProducts, getPendingOrderstask, getCustomerOrdersTask, fullCustomer);
			List<SubscribedProductInfoRestVO> subscribedProductList = wirelineProductHelper.transformSubscribedProductListVOToSubscribedProductInfoRestVO(consolidatedSubscribedProducts);
			result.setGetProductsByCustomerIdAdapterResponse(getAssignedProductInstanceListTask.getGetProductsByCustomerIdAdapterResponse());
			enrichResponse(result, subscribedProductList, offersRequest);
			
			return result;
		} catch (NumberFormatException e) {
			logger.error("","execute",e.getMessage(),e);
			throw new RuntimeException(e);
		} catch (NamingException e) {
			logger.error("","execute",e.getMessage(),e);
			throw new RuntimeException(e);
		} catch (WorkException e) {
			logger.error("","execute",e.getMessage(),e);
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			logger.error("","execute",e.getMessage(),e);
			throw new RuntimeException(e);
		}	
	}

	
	private void collectErrors(List<Work> workTaskList, GetAssignedAndPendingProductResponseVO result) {
		for (Work work : workTaskList) {
			if (work instanceof GetProductInstanceListByCustomerFromOrderQueryServiceTask) {
				GetProductInstanceListByCustomerFromOrderQueryServiceTask getProductInstanceListByCustomerFromOrderQueryServiceTask = (GetProductInstanceListByCustomerFromOrderQueryServiceTask)work;
				if (!getProductInstanceListByCustomerFromOrderQueryServiceTask.getResponse().getErrors().isEmpty()){
					SalesResponseMessage.MessageList messageList = new SalesResponseMessage.MessageList();
					messageList.setContextData("");
					messageList.setMessageType("ERROR");
					List<Message> messages = new ArrayList<Message>();
					Message message = new Message();
					message.setMessage("GetProductInstanceListByCustomerFromOrderQueryServiceTask failed");
					messages.add(message);
					messageList.setMessageList(messages);
					result.addMsg(messageList);
					result.setMsgHasError(true);
				}
			} else if (work instanceof GetOrderListByCustomerFromOrderQueryServiceTask) {
				GetOrderListByCustomerFromOrderQueryServiceTask getOrderListByCustomerFromOrderQueryServiceTask = (GetOrderListByCustomerFromOrderQueryServiceTask) work;
				if (!getOrderListByCustomerFromOrderQueryServiceTask.getResponse().getErrors().isEmpty()){
					SalesResponseMessage.MessageList messageList = new SalesResponseMessage.MessageList();
					messageList.setContextData("");
					messageList.setMessageType("ERROR");
					List<Message> messages = new ArrayList<Message>();
					Message message = new Message();
					message.setMessage("getOrderListByCustomerFromOrderQueryServiceTask failed");
					messages.add(message);
					messageList.setMessageList(messages);
					result.addMsg(messageList);
					result.setMsgHasError(true);
				}
			} else if (work instanceof GetFullCustomerInfoTask) {
				GetFullCustomerInfoTask getFullCustomerInfoTask = (GetFullCustomerInfoTask) work;
				if (getFullCustomerInfoTask.getResult().isMsgHasError()){
					SalesResponseMessage.MessageList messageList = new SalesResponseMessage.MessageList();
					messageList.setContextData("");
					messageList.setMessageType("ERROR");
					List<Message> messages = new ArrayList<Message>();
					Message message = new Message();
					message.setMessage("GetFullCustomerInfoTask failed");
					messages.add(message);
					messageList.setMessageList(messages);
					result.addMsg(messageList);
					result.setMsgHasError(true);
				}
			} else if (work instanceof GetPendingOrdersTask) {
//				GetPendingOrdersTask getPendingOrdersTask = (GetPendingOrdersTask) work;
//				if (!getPendingOrdersTask.getErrors().isEmpty()){
//					SalesResponseMessage.MessageList messageList = new SalesResponseMessage.MessageList();
//					messageList.setContextData("");
//					messageList.setMessageType("ERROR");
//					List<Message> messages = new ArrayList<Message>();
//					Message message = new Message();
//					message.setMessage("GetPendingOrdersTask failed");
//					messages.add(message);
//					messageList.setMessageList(messages);
//					result.addMsg(messageList);
//					result.setMsgHasError(true);
//				}
		}
		}
		
	}

	private static void enrichResponse(GetAssignedAndPendingProductResponseVO result,List<SubscribedProductInfoRestVO> subscribedProductList , GetOffersRequestVO offersRequest){
		List<SubscribedProductInfoRestVO> assignedProductList = new ArrayList<SubscribedProductInfoRestVO>();
		List<SubscribedProductInfoRestVO> pendingProductList = new ArrayList<SubscribedProductInfoRestVO>();
		if (subscribedProductList != null) {
			for(SubscribedProductInfoRestVO subscribedProduct : subscribedProductList){
				if(subscribedProduct.getProductInPendingOrderStatusInd()!=null && subscribedProduct.getProductInPendingOrderStatusInd()){
					//Product in pending status
					pendingProductList.add(subscribedProduct); 
				}else if(subscribedProduct.getProductInPendingOrderStatusInd()!=null && !subscribedProduct.getProductInPendingOrderStatusInd()){
					//Products in Assigned status
					assignedProductList.add(subscribedProduct);
				}
			}
		}
		if(!assignedProductList.isEmpty()){
			result.setSubscribedProductList(assignedProductList);
		}
		if(!pendingProductList.isEmpty()){
			result.setPendingProductList(pendingProductList);
		}
		
	}
	
//	private static List<MessageList> transformMsg(ResponseStatus responseStatus) {
//		List<MessageList> messageList = new ArrayList<MessageList>();
//		MessageList msgList = new MessageList();
//		msgList.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
//		msgList.setDateTimeStamp(new Date());
//		msgList.setErrorCode(responseStatus.getSystemErrorId());
//		msgList.setTransactionId(responseStatus.getCorrelationId());
//		msgList.setMessageList(getMsgList(responseStatus.getSystemMessageTxt()));
//		return messageList;
//	}
//
//	private static List<Message> getMsgList(String systemMessageTxt) {
//		List<Message> resultMessageList = new ArrayList<Message>();
//		Message msg = new Message();
//		msg.setMessage(systemMessageTxt);
//		msg.setLocale(EnterpriseWLNSalesServicesConstants.LANG_EN);
//		resultMessageList.add(msg);
//		return resultMessageList;
//	}
	/**
	 * 
	 * @param getCustomerOrdersTask
	 * @param codsCustomer
	 */
	private void identifyBanForPendingOmsOrders(GetOrderListByCustomerFromOrderQueryServiceTask getCustomerOrdersTask, FullCustomer codsCustomer) {
		Map<String, ArrayList<SubscribedProductSummaryVO>> productsByBan = null;
		
		if (getCustomerOrdersTask != null) {
			TaskExecutionResult<Map<String, ArrayList<SubscribedProductSummaryVO>>> response = getCustomerOrdersTask.getSummaryResponse();
			if (response != null && response.isServiceCallSuccessFlag() && response.isTaskCompletionFlag()) {
				productsByBan = response.getServiceCallResponse();
			}
		}
		
		if (productsByBan == null || codsCustomer == null) {
			return;
		}
		
		ArrayList<SubscribedProductSummaryVO> productsWithUnknownBan = productsByBan.get(UNKNOWN_ACCOUNT);
		if (productsWithUnknownBan != null) {
		identifyBanOnAllProducts(productsWithUnknownBan, codsCustomer);

		Iterator<SubscribedProductSummaryVO> i = productsWithUnknownBan.iterator();
		while (i.hasNext()) {
			SubscribedProductSummaryVO product = i.next();
			String ban = product.getBillingAccountNumber();
			if (StringUtils.isNotBlank(ban)) {
				ArrayList<SubscribedProductSummaryVO> productsInAccount = productsByBan.get(ban);
				if (productsInAccount == null) {
					productsInAccount = new ArrayList<SubscribedProductSummaryVO>();
					productsByBan.put(ban, productsInAccount);
				}
				productsInAccount.add(product);
				i.remove();
			}
		}
		}
	}
	private void identifyBanOnAllProducts(ArrayList<SubscribedProductSummaryVO> products, FullCustomer codsCustomer) {
		if (products == null || codsCustomer == null) {
			return;
		}
		
		for (SubscribedProductSummaryVO product : products) {
			String pcn = product.getRecurringPayChannelNumber();
			String ban = findBanForPayChannel(pcn, codsCustomer);
			product.setBillingAccountNumber(ban);
		}
	}

	private String findBanForPayChannel(String pcn, FullCustomer codsCustomer) {
		if (pcn == null || codsCustomer.getBillingAccountList() == null) {
			return null;
		}
		
		for (BillingAccount account : codsCustomer.getBillingAccountList()) {
			String ban = account.getBillingAccountNumber();
			String codsPcn = null;
			if (account.getPayChannel() != null) {
				codsPcn = account.getPayChannel().getPayChannelNumber();
			}
			if (StringUtils.equals(pcn, codsPcn)) {
				return ban;
			}
		}
		
		return null;
	}	
	/**
	 * 
	 * @param accountProfile
	 * @param getProductInstanceListByCustomerFromOrderQueryServiceTask
	 * @param customer
	 * @throws Exception
	 */
	private void enrichProductsListWithOmsInformation(String billingAccountNumber, List<SubscribedProductSummaryVO> consolidatedSubscribedProductsList, Map<String, SubscribedProductSummaryVO> productSummaryMap, FullCustomer customer)  {
		    ArrayList<SubscribedProductSummaryVO> subscribedProductSummaryList = new ArrayList<SubscribedProductSummaryVO>();
			for( SubscribedProductSummaryVO subscribedProductSummaryFromCODS : consolidatedSubscribedProductsList ) {
				if( subscribedProductSummaryFromCODS.getProductInstance() == null ) continue;
				String codsProductInstanceId = subscribedProductSummaryFromCODS.getProductInstance().getProductInstanceId();
				if (codsProductInstanceId==null) continue;
				
				SubscribedProductSummaryVO subscribedProductSummaryFromOMS = productSummaryMap.get(codsProductInstanceId);
				if( subscribedProductSummaryFromOMS != null ) {
					//always use services address from CODS and use the one from OMS as secondary source. (This is the current way being handled in VESTA)
					if( !isEmptyAddress(subscribedProductSummaryFromCODS.getServiceAddress()) ) {
						subscribedProductSummaryFromOMS.setServiceAddress(subscribedProductSummaryFromCODS.getServiceAddress());
					}
					subscribedProductSummaryFromOMS.setSubscriptionStatus(subscribedProductSummaryFromCODS.getSubscriptionStatus());
					subscribedProductSummaryList.add(subscribedProductSummaryFromOMS);
				}
			}
			consolidatedSubscribedProductsList.clear();
			addProductSummaryArrayToAccountProfile(billingAccountNumber, consolidatedSubscribedProductsList, subscribedProductSummaryList, customer);
	}
	/**
	 * Checks if is empty address.
	 *
	 * @param address the addressVO
	 * @return true, if is empty address
	 */
	protected boolean isEmptyAddress(AddressVO address) {
		return address == null ||
				StringUtils.isEmpty(address.getMunicipalityName()) ||
				StringUtils.isEmpty(address.getCivicNo()) ||
				StringUtils.isEmpty(address.getProvinceStateCode()) ||
				StringUtils.isEmpty(address.getStreetName());
	}
	
	/**
	 * Checks if is empty address  .
	 *
	 * @param address the addressVO
	 * @return true, if is empty address
	 */
	protected boolean isEmptyAddressForForborne(AddressVO address) {
		return address == null || ((address.getAddressId()==null || address.getAddressId()<=0) && (
				StringUtils.isEmpty(address.getCity()) ||
				StringUtils.isEmpty(address.getStreetNumber()) ||
				StringUtils.isEmpty(address.getProvince().value()) ||
				StringUtils.isEmpty(address.getStreetName())
				));
	}

	/**
	 * Checks if is empty address.
	 *
	 * @param civicAddress the civic address
	 * @return true, if is empty address
	 */
	protected boolean isEmptyAddress(CivicAddress civicAddress) {
		return civicAddress == null ||
			StringUtils.isEmpty(civicAddress.getMunicipalityName()) ||
			StringUtils.isEmpty(civicAddress.getProvinceStateCode()) ||
			StringUtils.isEmpty(civicAddress.getStreetName()) ||
			StringUtils.isEmpty(civicAddress.getStreetNumber());
	}

	

	/**
	 * Checks if is empty address.
	 *
	 * @param address the address
	 * @return true, if is empty address
	 */
	protected static boolean isEmptyAddress(com.telus.tmi.xmlschema.xsd.customer.basetypes.customercommon_v3.Address address) {
		return address == null ||
			StringUtils.isEmpty(address.getMunicipalityName()) ||
			StringUtils.isEmpty(address.getCivicNumber()) ||
			StringUtils.isEmpty(address.getProvinceStateCode()) ||
			StringUtils.isEmpty(address.getStreetName());
	}
	/**
	 * 
	 * @param accountProfile
	 * @param getOrderListByCustomerTask
	 * @param customer
	 * @throws Exception
	 */
	private void enrichProductsListWithPendingOmsInformation(
			String billingAccount, 
			List<SubscribedProductSummaryVO> consolidatedSubscribedProductSummaryList, 
    		 Map<String, ArrayList<SubscribedProductSummaryVO>> pendingOMSSubscribedProductSummaryByChannelTransactionIdMap,
			 Map<String, ArrayList<SubscribedProductSummaryVO>> pendingOMSSubscribedProductSummaryByBanMap,
			FullCustomer customer)  {
		
		if( pendingOMSSubscribedProductSummaryByChannelTransactionIdMap != null ) {
			for( Iterator<ArrayList<SubscribedProductSummaryVO>> i = pendingOMSSubscribedProductSummaryByChannelTransactionIdMap.values().iterator(); i.hasNext(); ) {
				addProductSummaryArrayToAccountProfile( billingAccount, consolidatedSubscribedProductSummaryList, i.next(), customer);
			}
		}
		
		if (pendingOMSSubscribedProductSummaryByBanMap != null && StringUtils.isNotBlank(billingAccount)) {
			ArrayList<SubscribedProductSummaryVO> pendingOMSSubscribedProducts = pendingOMSSubscribedProductSummaryByBanMap.get(billingAccount);
			addProductSummaryArrayToAccountProfile(billingAccount, consolidatedSubscribedProductSummaryList, pendingOMSSubscribedProducts, customer);
		}
	}
	//param.getCustomerId()), Long.valueOf(param.getBillingAccountNumber()), agentUid, sessionId,  orderQueryRestSvcAdapter 
	private void getAssignedProductInstanceDetailWithBatch(Long customerId, Long billingAccountNumber, String agentGuid, Long sessionId, GetProductInstanceListByCustomerFromOrderQueryServiceTask getAssignedProductInstanceListTask, IOrderQueryRestSvcAdapter orderQueryRestSvcAdapter){
		try{
			List<GetProductInstanceWithIdsByCustomerFromOrderQueryServiceTask> getAssignedProductInstanceDetailListTaskList = new ArrayList<GetProductInstanceWithIdsByCustomerFromOrderQueryServiceTask>();
			List<Work> workTaskList = new ArrayList<Work>();
			//TODO: Khalil to initialize workManager
			//Initialize the adpater
			try {
				workManager = WorkManagerFactory.getCommonJWorkManager();
			} catch (Exception e) {
				logger.error(e);
			}
				Map<String, SubscribedProductSummaryVO> subcribedProductMap = getAssignedProductInstanceListTask.getResponse().getServiceCallResponse();
				List<String> idsList = new ArrayList<String>();
				if(subcribedProductMap.keySet().size()>0){
					for (Map.Entry<String, SubscribedProductSummaryVO> entry : subcribedProductMap.entrySet()) {
						idsList.add(entry.getKey());
					}
				}
				
				Map<String,List<String>> idsGroupMap = new HashMap<String,List<String>>();
				int idCount = 0;
				int idGroup = 0;
				List<String> idsGroup = new ArrayList<String>();
				for(String id : idsList){
					idsGroup.add(id);
					if(idCount % 5 == 4){
						List<String> idsTGroup = new ArrayList<String>();
						idsTGroup.addAll(idsGroup);
						idsGroupMap.put(""+idGroup, idsTGroup);
						idsGroup.clear();
						idGroup ++;
					}
					idCount ++;
				}
				if(idsGroup.size()>0){
					idsGroupMap.put(""+idGroup, idsGroup);
				}
	
				if(idsGroupMap.keySet().size()>0){
					for (Map.Entry<String,List<String>> entry : idsGroupMap.entrySet()) {
						GetProductInstanceWithIdsByCustomerFromOrderQueryServiceTask getAssignedProductInstanceDetailListTask = new GetProductInstanceWithIdsByCustomerFromOrderQueryServiceTask(customerId, agentGuid, sessionId, entry.getValue(), orderQueryRestSvcAdapter, new WirelineProductHelper());
						workTaskList.add(getAssignedProductInstanceDetailListTask);
						getAssignedProductInstanceDetailListTaskList.add(getAssignedProductInstanceDetailListTask);
						
					}
				}
			workManager.processTasks(workTaskList);
			//TODO: implement error handling
//			if(getAssignedProductInstanceDetailListTaskList.size()>0){
//				for(GetProductInstanceWithIdsByCustomerFromOrderQueryServiceTask getAssignedProductInstanceDetailListTask : getAssignedProductInstanceDetailListTaskList){
//					// check getAssignedProductInstanceDetailListTask
//					if (getAssignedProductInstanceDetailListTask != null && getAssignedProductInstanceDetailListTask.getResponse().isTaskCompletionFlag()==false) {
//						AccountProfileProblemListVO accountProfileProblem = new AccountProfileProblemListVO();
//						accountProfileProblem.setProblemCode(OP_SERVICE_UNAVAILABLE);
//						accountProfileProblem.getProblemValue().clear();
//						accountProfileProblem.getProblemValue().add(OP_SERVICE_UNAVAILABLE);
//						List<Throwable> errors = getAssignedProductInstanceDetailListTask.getResponse().getErrors();
//						wirelineSystemNotAvailableReasonTxt = "OrderQueryService.retrieveAssignedProductDetailsWithIdsByCustomerId : Cause["+getErrorMessageString(errors)+"]";
//						accountProfileProblemArray.add(accountProfileProblem);
//					}
//					else if (getAssignedProductInstanceDetailListTask != null && getAssignedProductInstanceDetailListTask.getResponse().isServiceCallSuccessFlag()==false) {
//						LoggerUtil.getLogger(this).error(ERROR_ID_SERVICE_EXCEPTION, methodName,"Failed calling OrderQueryService Restful - retrieveAssignedProductDetailsWithIdsByCustomerId");
//						wlnSystemNotAvailableInd = true;
//						List<Throwable> errors = getAssignedProductInstanceDetailListTask.getResponse().getErrors();
//						isOmsAssignedProductDetailInstanceListTaskCompleted = false;
//						AccountProfileProblemListVO accountProfileProblem = new AccountProfileProblemListVO();
//						accountProfileProblem.setProblemCode(OP_SERVICE_UNAVAILABLE);
//						accountProfileProblem.getProblemValue().clear();
//						accountProfileProblem.getProblemValue().add(OP_SERVICE_UNAVAILABLE);
//						accountProfileProblemArray.add(accountProfileProblem);
//					}
//				}
//			}	
	
			if (getAssignedProductInstanceDetailListTaskList.size()>0) {
				getAssignedProductInstanceListTask.getResponse().getServiceCallResponse().clear();
				Map<String, SubscribedProductSummaryVO> consolidateAllSubcribedProductMap = new HashMap<String, SubscribedProductSummaryVO>();
				for(GetProductInstanceWithIdsByCustomerFromOrderQueryServiceTask getAssignedProductInstanceDetailListTask : getAssignedProductInstanceDetailListTaskList){
				 if(getAssignedProductInstanceDetailListTask!=null 
				    && getAssignedProductInstanceDetailListTask.getResponse()!=null 
					&& getAssignedProductInstanceDetailListTask.getResponse().getServiceCallResponse()!=null){	
					 consolidateAllSubcribedProductMap.putAll(getAssignedProductInstanceDetailListTask.getResponse().getServiceCallResponse());
				 }
				}
				getAssignedProductInstanceListTask.getResponse().setServiceCallResponse(consolidateAllSubcribedProductMap);
			}
		} catch (Exception e) {
			logger.error(e);  //Gary additional logging info 2019-Aug-10
			logger.error("getAssignedProductInstanceDetailWithBatch method Failed");
		}
	}//QC 59376
	
	/**
	 * Adds the product summary array to account profile.
	 *
	 * @param accountProfile the account profile
	 * @param productSummaryArray the product summary array
	 */
	private void addProductSummaryArrayToAccountProfile(String billingAccountNumber,  List<SubscribedProductSummaryVO> consolidatedSubscribedProductList, List<SubscribedProductSummaryVO> productSummaryArray, FullCustomer customer) {
		if( productSummaryArray != null && productSummaryArray.size() > 0 ) {
			consolidatedSubscribedProductList.addAll(productSummaryArray);
			addPricePlansToProductSummary(billingAccountNumber, productSummaryArray, customer);
		}
	}

	private void addPricePlansToProductSummary(String billingAccountNumber, List<SubscribedProductSummaryVO> productSummaryArray, FullCustomer customer) {
		if (customer.getProductInstanceList() == null || billingAccountNumber == null ) {
			return;
		}
		for (ProductInstance product : customer.getProductInstanceList()) {
			if (product == null || product.getComponentList() == null || product.getProductInstanceId() == null || product.getMasterSourceId() == null) {
				continue;
			}

			if (billingAccountNumber.equals(product.getBillingAccountNumber())) {
				Set<String> pricePlanIdSet = new HashSet<String>();
				
				// OP service instance ID = ConsumerCustomerManagementSvc product instance ID
				String serviceInstanceId = product.getProductInstanceId().toString();
				
				// find corresponding SuscribedProductSummaryVO
				SubscribedProductSummaryVO productVO = null;
				for (SubscribedProductSummaryVO pvo : productSummaryArray) {
					if (pvo.getProductInstance() == null) {
						continue;
					}
					
					if (serviceInstanceId.equals(pvo.getProductInstance().getProductInstanceId())) {
						productVO = pvo;
						break;
					}
				}
				
				if (productVO == null) {
					continue;
				}

				for (ProductInstance component : product.getComponentList()) {
					if (component == null || component.getProductCatalogId() == null || component.getMasterSourceId() == null) {
						continue;
					}
		

					pricePlanIdSet.add(component.getProductCatalogId().toString());
				}
				
				productVO.getPricePlanIdList().addAll(pricePlanIdSet);
			}
		}
	}
	/**
	 * Populate subscribed products for account.
	 *
	 * @param customerProductInstances the customer product instances
	 * @param billingAccountNumber the billing account number
	 * @param products the products
	 * @param topLevelProductInstance the top level product instance
	 */
	private static void populateSubscribedProductsForAccount(
			List<com.telus.tmi.xmlschema.xsd.product.productinstance.customer_product_instance_sub_domain_v3.ProductInstance> customerProductInstances,
			String billingAccountNumber, List<SubscribedProductSummaryVO> products, boolean topLevelProductInstance) {
		if (customerProductInstances==null || products==null) {
			return;
		}
		
		for (com.telus.tmi.xmlschema.xsd.product.productinstance.customer_product_instance_sub_domain_v3.ProductInstance productInstance : customerProductInstances) {
			// skip products not associated with this account
			if (!billingAccountNumber.equalsIgnoreCase(productInstance.getBillingAccountNumber())) continue;

			List<com.telus.tmi.xmlschema.xsd.product.productinstance.customer_product_instance_sub_domain_v3.ProductInstance> componentList = 
				productInstance.getComponentList();
			
			if (componentList!=null && componentList.size() > 0) {
				for (com.telus.tmi.xmlschema.xsd.product.productinstance.customer_product_instance_sub_domain_v3.ProductInstance componentInstance : componentList) {
					// skip non-primary product type code
					if (!CUSTOMER_ODS_PRIMARY_PRODUCT_TYPE_CD.equals(componentInstance.getProductTypeCode())) {
						continue;
					}
					// skip products not associated with this account
					if (!billingAccountNumber.equalsIgnoreCase(componentInstance.getBillingAccountNumber())) {
						continue;
					}
					// skip if ComponentSubscriptionKeyId is empty
					String productInstanceId = componentInstance.getComponentSubscriptionKeyId();
					if (productInstanceId==null) {
						continue;
					}
					SubscribedProductSummaryVO productSummary = new SubscribedProductSummaryVO();
									
					productSummary.setSubscriptionStatus(componentInstance.getStatusCode());
					if( !isEmptyAddress(productInstance.getAddress()) ) {
						productSummary.setServiceAddress(translateAddress(productInstance.getAddress()));
					} else if( !isEmptyAddress(componentInstance.getAddress()) ) {
						productSummary.setServiceAddress(translateAddress(componentInstance.getAddress()));
					} else { //ServiceAddress empty rule: ignore this product, if no associated service address (per VESTA rule)
						logger.info("populateSubscribedProductsForAccount", "Ignore product without service address. product instance = " + productInstanceId);
						continue;
					}
					SubscribedProductSummaryVO.ProductInstance productSummaryProductInstance = 
							new SubscribedProductSummaryVO.ProductInstance();
					//if (componentInstance.getProductCatalogId()!=null) productSummaryProductInstance.setProductCatalogId(Long.toString(componentInstance.getProductCatalogId()));
					// map key id to product instance id
					productSummaryProductInstance.setProductInstanceId(productInstanceId);
					productSummary.setProductInstance(productSummaryProductInstance);
	
					if (componentInstance.getProductInstanceId() != null) {
						// OP service instance ID = ConsumerCustomerManagementSvc product instance ID
						productSummaryProductInstance.setServiceId(componentInstance.getProductInstanceId().toString());
					}
					
					products.add(productSummary);
				}
			}
		}
	}
	 
 

	    /**
	     * Translate address.
	     *
	     * @param address the address
	     * @return the com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.channelsalescommontypes_v2. address
	     */
	    public static AddressVO translateAddress(
				com.telus.tmi.xmlschema.xsd.customer.basetypes.customercommon_v3.Address address) {
			if (address==null) return null;

			AddressVO translatedAddress = new AddressVO();

			String addressTypeCode = address.getAddressTypeCode();

			translatedAddress.setAddressId(address.getAddressId());
			translatedAddress.setAddressTypeCode(address.getAddressTypeCode());
			translatedAddress.setAdditionalAddressInformation(address.getAdditionalAddressInformation());
			translatedAddress.setRenderedAddress(address.getRenderedAddress());
			translatedAddress.setAddrAssgnmtId(address.getAddrAssgnmtId());
			translatedAddress.setAddressAssignmentTypeCode(address.getAddressAssignmentTypeCode());
			translatedAddress.setAddressAssignmentSubTypeCode(address.getAddressAssignmentSubTypeCode());
			translatedAddress.setAddressMatchingStatusCode(address.getAddressMatchingStatusCode());
			translatedAddress.setAddressSearchText(address.getAddressSearchText());
			translatedAddress.setCanadaPostBuildName(address.getCanadaPostBuildName());
			translatedAddress.setCanadaPostLocnName(address.getCanadaPostLocnName());
			translatedAddress.setCanadaPostRecordType(address.getCanadaPostRecordType());
			translatedAddress.setCareOf(address.getCareOf());
			translatedAddress.setCountryCode(address.getCountryCode());
			translatedAddress.setEmailAddressText(address.getEmailAddressText());
			translatedAddress.setExternalAddressId(address.getExternalAddressId());
			translatedAddress.setExternalAddressSourceId(address.getExternalAddressSourceId());
			translatedAddress.setExternalServiceAddressId(address.getExternalServiceAddressId());

			if(address.getExternalServiceAddressId() != null && !address.getExternalServiceAddressId().isEmpty()){
				translatedAddress.setServiceAddressId(removeNonNumericChars(address.getExternalServiceAddressId()));
			}

			translatedAddress.setMailingTypeCode(address.getMailingTypeCode());
			translatedAddress.setMunicipalityName(address.getMunicipalityName());
			translatedAddress.setPostOfficeBoxNumber(address.getPostOfficeBoxNumber());
			translatedAddress.setPostalZipCode(address.getPostalZipCode());
			translatedAddress.setProvinceStateCode(address.getProvinceStateCode());
			translatedAddress.setRelateAddressAssignmentId(address.getRelateAddressAssignmentId());
			translatedAddress.setStreetDirectionCode(address.getStreetDirectionCode());
			translatedAddress.setStreetName(address.getStreetName());
			translatedAddress.setStreetTypeCode(address.getStreetTypeCode());
			translatedAddress.setUnitNumber(address.getUnitNumber());
			translatedAddress.setValidateAddressIndicator(address.getValidateAddressIndicator());
			translatedAddress.setUnitTypeCode(address.getUnitTypeCode());
			translatedAddress.setCivicNumber(address.getCivicNumber());
			translatedAddress.setCivicNoSuffix(address.getCivicNumberSuffix());
			translatedAddress.setRuralRouteNumber(address.getRuralRouteNumber());
			translatedAddress.setRuralRouteTypeCode(address.getRuralRouteTypeCode());
			translatedAddress.setStationName(address.getStationName());
			translatedAddress.setStationQualifier(address.getStationQualifier());
			translatedAddress.setStationTypeCode(address.getStationTypeCode());
			translatedAddress.setFleetMailOfficeName(address.getFleetMailOfficeName());
			translatedAddress.setHmcsName(address.getHmcsName());
			translatedAddress.setMasterSourceId(address.getMasterSourceId());
			//translatedAddress.setLastUpdateTimeStamp(address.getLastUpdateTimeStamp());

			translatedAddress.setCity(address.getMunicipalityName());
			translatedAddress.setCivicNo(address.getCivicNumber());
			translatedAddress.setCivicNoSuffix(address.getCivicNumberSuffix());
			translatedAddress.setCountry(address.getCountryCode());
			translatedAddress.setPoBox(address.getPostOfficeBoxNumber());
			translatedAddress.setPostalCode(address.getPostalZipCode());

			String provinceCodeStr = convertNewProvinceCodeToOld(address.getProvinceStateCode());
//			if (!StringUtils.isEmpty(provinceCodeStr)) {
//				ProvinceCode provinceCode = null;
//				if( (!StringUtils.isEmpty(addressTypeCode) && CODS_BILLING_ADDRESS_TYPE_CODE_CANADA.equalsIgnoreCase(addressTypeCode)) ||
//					StringUtils.isEmpty(address.getCountryCode()) ||
//					COUNTRY_CODE_CAN.equals(address.getCountryCode()) ) {
//					try {
//						provinceCode = ProvinceCode.fromValue(provinceCodeStr);
//					} catch( Exception ex) {
//						log.warn("Unrecognized Canadian province code. CountryCode = " + address.getCountryCode() + ", provinceCode = " + provinceCodeStr);
//					}
//				}
//
//				if( provinceCode != null ) {
//					translatedAddress.setProvince(provinceCode.value());
//				} else {
//					translatedAddress.setForeignState(provinceCodeStr);
//				}
//			}
			translatedAddress.setRuralNumber(address.getRuralRouteNumber());
			translatedAddress.setRuralType(address.getRuralRouteTypeCode());
			translatedAddress.setStreetDirection(address.getStreetDirectionCode());
			translatedAddress.setStreetNumber(address.getCivicNumber());
			translatedAddress.setStreetNumberSuffix(address.getCivicNumberSuffix());
			translatedAddress.setStreetType(address.getStreetTypeCode());
			translatedAddress.setUnit(address.getUnitNumber());
			translatedAddress.setUnitType(address.getUnitTypeCode());
			translatedAddress.setAddressTypeInfo(address.getAddressTypeCode());
			//US billing address
			//WSS-195 defect 59822 - if steet name is null or empty then also take the details from additionalAddressInformation for all address types
			if(translatedAddress.getStreetName() == null  || "".equals(translatedAddress.getStreetName()) || !StringUtils.isEmpty(addressTypeCode) && CODS_BILLING_ADDRESS_TYPE_CODE_USA.equalsIgnoreCase(addressTypeCode) ) {
				List<String> additionalInfo = address.getAdditionalAddressInformation();
				if( additionalInfo != null && additionalInfo.size() > 0 ) {
					StringBuffer sb = new StringBuffer();
					for( int i = 0; i < additionalInfo.size(); i++ ) {
						sb.append(" ").append(additionalInfo.get(i));
					}
					translatedAddress.setStreetName(sb.toString().trim());
				}
			}
			return translatedAddress;
		}

	    /**
	     * Translate address.
	     *
	     * @param address the address
	     * @return the com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.channelsalescommontypes_v2. address
	     */
	    public AddressVO translateAddress(
				com.telus.tmi.xmlschema.xsd.customer.customer.customermanagementcommontypes_v3.Address address) {
			if (address==null) return null;

			AddressVO translatedAddress = new AddressVO();

			if (address.getAddressType() != null) {
				translatedAddress.setAddressTypeCode(address.getAddressType().value());
			}
			translatedAddress.setAttention(address.getAttention());
			translatedAddress.setCity(address.getCity());
			translatedAddress.setCivicNo(address.getCivicNo());
			translatedAddress.setCivicNoSuffix(address.getCivicNoSuffix());
			translatedAddress.setCountry(address.getCountry());
			translatedAddress.setForeignState(address.getForeignState());
			translatedAddress.setPoBox(address.getPoBox());
			translatedAddress.setPostalCode(address.getPostalCode());
			translatedAddress.setPrimaryLine(address.getPrimaryLine());
			if (address.getProvince() != null) {
				translatedAddress.setProvince(address.getProvince().value());
			}
			translatedAddress.setRuralCompartment(address.getRuralCompartment());
			translatedAddress.setRuralDeliveryType(address.getRuralDeliveryType());
			translatedAddress.setRuralGroup(address.getRuralGroup());
			translatedAddress.setRuralLocation(address.getRuralLocation());
			translatedAddress.setRuralNumber(address.getRuralNumber());
			translatedAddress.setRuralQualifier(address.getRuralQualifier());
			translatedAddress.setRuralSite(address.getRuralSite());
			translatedAddress.setRuralType(address.getRuralType());
			translatedAddress.setSecondaryLine(address.getSecondaryLine());
			translatedAddress.setStreetDirection(address.getStreetDirection());
			translatedAddress.setStreetName(address.getStreetName());
			translatedAddress.setStreetNumber(address.getStreetNumber());
			translatedAddress.setStreetNumberSuffix(address.getStreetNumberSuffix());
			translatedAddress.setStreetType(address.getStreetType());
			translatedAddress.setUnit(address.getUnit());
			translatedAddress.setUnitType(address.getUnitType());
			translatedAddress.setZipGeoCode(address.getZipGeoCode());
			return translatedAddress;
		}
	    /**
	     * 
	     * @param s
	     * @return
	     */
	    public static String removeNonNumericChars(String s) {
			String temp = "";
			if (s != null && !s.isEmpty()) {
				char[] chars = s.toCharArray();
				for (char c : chars) {
					if (!Character.isLetter(c)) {
						temp = temp + c;
					}
				}
			}
			return temp;
		}
	    /**
		 * Convert new Quebec/NewFoundland province codes to old one
		 * @param provinceCode
		 * @return
		 */
		public static String convertNewProvinceCodeToOld(String provinceCode) {
			if (provinceCode==null) {
				return null;
			}
			else if (PROV_CD_QUEBEC.equals(provinceCode)) {
				return PROV_CD_QUEBEC_OLD;
			}
			else if (PROV_CD_NEWFOUNDLAND.equals(provinceCode)) {
				return PROV_CD_NEWFOUNDLAND_OLD;
			}
			else {
				return provinceCode;
			}
		}
		
		/**
		 * Populate wln pending orders from vesta.
		 *
		 * @param accountProfile the account profile
		 * @param getPendingOrdersTask the get pending orders task
		 */
		private void enrichWithPendingOrdersFromVesta(String billingAccountNumber,  List<SubscribedProductSummaryVO> consolidatedSubscribedProductList, GetPendingOrdersTask getPendingOrdersTask, GetOrderListByCustomerFromOrderQueryServiceTask getOrderListByCustomerIdTask,FullCustomer customer) {
			Map<String, ArrayList<SubscribedProductSummaryVO>> pendingOMSSubscribedProductSummaryByChannelTransactionIdMap = getOrderListByCustomerIdTask.getSummaryResponse().getServiceCallResponse();
			Map<String, ArrayList<SubscribedProductSummaryVO>> pendingVestaSubscribedProductSummaryByChannelTransactionIdMap = getPendingOrdersTask.getResponse();
			if( pendingOMSSubscribedProductSummaryByChannelTransactionIdMap != null && 
				pendingVestaSubscribedProductSummaryByChannelTransactionIdMap != null ) {
				for( Iterator<String> i = pendingVestaSubscribedProductSummaryByChannelTransactionIdMap.keySet().iterator(); i.hasNext(); ) {
					String channelTransactionId = i.next();
					if( pendingOMSSubscribedProductSummaryByChannelTransactionIdMap.containsKey(channelTransactionId) ) {
						//remove duplicates
						pendingVestaSubscribedProductSummaryByChannelTransactionIdMap.put(channelTransactionId, null);
					}
				}
			}
			
			if( pendingVestaSubscribedProductSummaryByChannelTransactionIdMap != null ) {
				for( Iterator<ArrayList<SubscribedProductSummaryVO>> i = pendingVestaSubscribedProductSummaryByChannelTransactionIdMap.values().iterator(); i.hasNext(); ) {
					addProductSummaryArrayToAccountProfile(billingAccountNumber, consolidatedSubscribedProductList, i.next(), customer);
				}
			}
		}
}
