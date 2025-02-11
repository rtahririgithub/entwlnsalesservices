package com.telus.csm.ewlnsms.core.operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ess.common.domain.MessageVO;
import com.telus.csm.ewlnsc.adapter.IFeasibilityServiceAdapter;
import com.telus.csm.ewlnsc.adapter.IWLNBookingRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.adapter.wbk.domain.BookAppointmentAdapterRequest;
import com.telus.csm.ewlnsc.adapter.wbk.domain.BookAppointmentAdapterRequestBody;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.ProductAppointmentRequestVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.helper.ShoppingCartContextHelper;
import com.telus.csm.ewlnsc.soap.webservice.EnterpriseWLNExceptionUtil;
import com.telus.csm.ewlnsc.task.BookAppointmentTask;
import com.telus.csm.ewlnsc.transformer.EnterpriseWLNCommonTransformer;
import com.telus.csm.ewlnsc.transformer.FeasibilitySvcTransformer;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.workmanager.ICommonJWorkManager;
import com.telus.csm.ewlnsc.util.workmanager.WorkManagerFactory;
import com.telus.csm.ewlnsms.core.domain.BookAppointmentCoreRequest;
import com.telus.csm.ewlnsms.core.domain.BookAppointmentCoreResponse;
import com.telus.csm.ewlnsms.core.transformer.BookAppointmentTransformer;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;
import com.telus.csm.ewlnsc.helper.EnterpriseWLNOrderUtil;

import commonj.work.Work;


public class BookAppointmentCoreOperation {

	private static final LoggerUtil logger = LoggerUtil.getLogger(BookAppointmentCoreOperation.class);
	private ICommonJWorkManager workManager;

	public BookAppointmentCoreOperation(){
		try {
			workManager = WorkManagerFactory.getCommonJWorkManager();
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public BookAppointmentCoreResponse execute(BookAppointmentCoreRequest coreRequest) {
		String functionName = "BookAppointmentCoreOperation.execute()";
		logger.enter(functionName);
		BookAppointmentCoreResponse result = new BookAppointmentCoreResponse();
		/*
		 * get the ShoppingCartVO from cache 
		 */

		ShoppingCartContextHelper contextHelper = new ShoppingCartContextHelper();
		ShoppingCartContextVO contextVO = contextHelper.execute(coreRequest.getShoppingCartId());


		List<Work> workTaskList = new ArrayList<Work>();
		if(contextVO!=null){

			List<MessageVO> messageList = inputValidation(coreRequest, contextVO.getShoppingCartVO());

			if (CollectionUtils.isNotEmpty(messageList)){
				result.setHasError(true);
				result.setMessageList(messageList);
				return result;
			} 
			
				CheckProductFeasibilityAdapterRequest feasibilitySvcRequest = FeasibilitySvcTransformer.transform(contextVO);
				IFeasibilityServiceAdapter feasibilityAdapter = AdapterFactory.getAdapter(IFeasibilityServiceAdapter.class, new AdapterFeatureDriver());
				CheckProductFeasibilityAdapterResponse feasibilityResponse = feasibilityAdapter.checkProductFeasibility(feasibilitySvcRequest);
				String opShoppingCartId = null; 
				
				List<SubscribedProductInfoRestVO> assignedProductList = EnterpriseWLNCommonTransformer.getAssignedProductsFromVO(contextVO);
				
				boolean changeOfTechnology = EnterpriseWLNOrderUtil.getCotIndicator(contextVO.getProductQualificationAdapterResponseVO());

				if(feasibilityResponse.getProductFeasibilityInfoList()!=null){
					logger.info(functionName, "FeasibilityService call was successful, preparing request for Book Appointment.");
					if(coreRequest.getProductAppointmentRequest()!=null && !coreRequest.getProductAppointmentRequest().isEmpty()){
						for(ProductAppointmentRequestVO productAppointment : coreRequest.getProductAppointmentRequest()){
							BookAppointmentAdapterRequest bookAppointmentRequest = null;
							BookAppointmentAdapterRequestBody bookAppointmentBody = BookAppointmentTransformer.getBookAppointmentBody(productAppointment,coreRequest,feasibilityResponse,feasibilitySvcRequest,contextVO.getShoppingCartVO(),opShoppingCartId,assignedProductList,changeOfTechnology);
							if (bookAppointmentBody != null ) {
								bookAppointmentRequest = new BookAppointmentAdapterRequest();	
								bookAppointmentRequest.setBody(bookAppointmentBody);

								if(contextVO.getShoppingCartVO()!=null) {
									if (contextVO.getShoppingCartVO().getCustomer()!=null && !StringUtils.isBlank(contextVO.getShoppingCartVO().getCustomer().getCustomerId())){
										bookAppointmentRequest.setStickysessionid(contextVO.getShoppingCartVO().getCustomer().getCustomerId());								
									}
									String externalOrderId = EnterpriseWLNOrderUtil.getExternalOrderIdBySystem(contextVO.getShoppingCartVO(), EnterpriseWLNSalesServicesConstants.EXTERNAL_SYSTEM_OMS);
									if (!StringUtils.isBlank(externalOrderId)) {
										opShoppingCartId = externalOrderId;
									}
								}

								IWLNBookingRestSvcAdapter adapter = AdapterFactory.getAdapter(IWLNBookingRestSvcAdapter.class);
								BookAppointmentTask appointmentTask = new BookAppointmentTask(adapter, bookAppointmentRequest);
								appointmentTask.setProductAppointmentRequest(productAppointment);
								workTaskList.add(appointmentTask);	

							}

						}

					}
					Collection<Work> responseTaskList = new ArrayList<Work>();
					if(!workTaskList.isEmpty()){
						try{
							responseTaskList = workManager.processTasks(workTaskList);
						}catch(Exception e){
							logger.error("",functionName,e.getMessage(),e);
							throw new RuntimeException(e);
						}
					}
					List<Work> appointmentTaskList=new ArrayList<Work>();
					if(!responseTaskList.isEmpty()){
						for(Work resultTask : responseTaskList){
							if(resultTask!=null){
								if(resultTask instanceof BookAppointmentTask){
									appointmentTaskList.add(resultTask);
								}
							}
						}
					}


					result = BookAppointmentTransformer.validateBookingSvcResponse(appointmentTaskList, feasibilityResponse);

					if(result.isHasError()){
						return result;	
					}

					result.setProductAppointmentList(BookAppointmentTransformer.transform(appointmentTaskList,coreRequest,contextVO));

					if (!StringUtils.isBlank(opShoppingCartId)) {
						result.setSoftBookingInd(Boolean.FALSE);					
					} else {
						result.setSoftBookingInd(Boolean.TRUE);					
					}				
			}
		}

		return result;
	}

	private List<MessageVO> inputValidation(BookAppointmentCoreRequest coreRequest, ShoppingCartVO shoppingCartVO) {

		List<String> requestProductList = new ArrayList<String>();
		List<String> shoppingCartProductList = new ArrayList<String>();
		List<MessageVO> result = new ArrayList<MessageVO>();

		for (ProductAppointmentRequestVO appointmentRequest: coreRequest.getProductAppointmentRequest()) {
			if (CollectionUtils.isNotEmpty(appointmentRequest.getProductServiceTypeList())) {
				for(String productServiceType: appointmentRequest.getProductServiceTypeList()) {
					requestProductList.add(productServiceType);
				}				
			}			
		}


		if(shoppingCartVO != null && CollectionUtils.isNotEmpty(shoppingCartVO.getCartItemList())){
			for (CartItemVO cartItem: shoppingCartVO.getCartItemList()){
				shoppingCartProductList.addAll(EnterpriseWLNOrderUtil.getProductsFromCartItem(cartItem));
			}
		}

/*		
 *  This block is commented because COT products are not mandatory to exist in shopping cart 
 * 
        if(CollectionUtils.isNotEmpty(shoppingCartProductList) && CollectionUtils.isNotEmpty(requestProductList)) {
			if (!shoppingCartProductList.containsAll(requestProductList)) {
				requestProductList.removeAll(shoppingCartProductList);		
				if (CollectionUtils.isNotEmpty(requestProductList)){
				   result = EnterpriseWLNCommonTransformer.transformToMessageListVO("ESS_BOOK_APPOINTMENT_0004", "Product(s) in Booking request not in Shopping Cart List: " + Arrays.toString(requestProductList.toArray()));
				}
			}	
		}
*/		
		return result;		
	}

}	
