package com.telus.csm.ewlnsms.core.transformer;






import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.client.HttpStatusCodeException;

import com.telus.csm.ess.common.domain.ContextDataAttributesVO;
import com.telus.csm.ess.common.domain.MessageDetailVO;
import com.telus.csm.ess.common.domain.MessageVO;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.quote.Product;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.adapter.wbk.domain.AddressInfo;
import com.telus.csm.ewlnsc.adapter.wbk.domain.BookAppointmentAdapterRequestBody;
import com.telus.csm.ewlnsc.adapter.wbk.domain.InstallationRequirementsList;
import com.telus.csm.ewlnsc.domain.AppointmentTimeSlotVO;
import com.telus.csm.ewlnsc.domain.AppointmentTypeBaseVO;
import com.telus.csm.ewlnsc.domain.AppointmentTypeVO;
import com.telus.csm.ewlnsc.domain.ProductAppointmentRequestVO;
import com.telus.csm.ewlnsc.domain.ProductAppointmentResponseVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.task.BookAppointmentTask;
import com.telus.csm.ewlnsc.transformer.EnterpriseWLNCommonTransformer;
import com.telus.csm.ewlnsis.core.transformer.GetAvailableInstallDetailCoreTransformer;
import com.telus.csm.ewlnsms.core.domain.BookAppointmentCoreRequest;
import com.telus.csm.ewlnsms.core.domain.BookAppointmentCoreResponse;
import com.telus.csm.ewlnsms.rest.domain.AppointmentTimeSlot;
import com.telus.csm.ewlnsms.rest.domain.AppointmentType;
import com.telus.csm.ewlnsms.rest.domain.AppointmentTypeBase;
import com.telus.csm.ewlnsms.rest.domain.BookAppointmentErrorResponse;
import com.telus.csm.ewlnsms.rest.domain.BookAppointmentRequest;
import com.telus.csm.ewlnsms.rest.domain.BookAppointmentResponse;
import com.telus.csm.ewlnsms.rest.domain.ContextAttribute;
import com.telus.csm.ewlnsms.rest.domain.ProductAppointmentRequest;
import com.telus.csm.ewlnsms.rest.domain.ProductAppointmentResponse;
import com.telus.csm.ewlnsms.rest.domain.ResponseMessage;
import com.telus.csm.ewlnss.rest.time.LocalDate;

import commonj.work.Work;

public class BookAppointmentTransformer {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 

	public static BookAppointmentCoreRequest transform(BookAppointmentRequest bookAppointmentRequest) {
		BookAppointmentCoreRequest coreRequest = new BookAppointmentCoreRequest();
		if(bookAppointmentRequest!=null){
			if(bookAppointmentRequest.getProductAppointmentRequest()!=null){
				coreRequest.setProductAppointmentRequest(getProductAppointmentRequestFromRest(bookAppointmentRequest.getProductAppointmentRequest()));
			}
			if(!StringUtils.isEmpty(bookAppointmentRequest.getShoppingCartId())){
				coreRequest.setShoppingCartId(bookAppointmentRequest.getShoppingCartId());
			}
			if(!StringUtils.isEmpty(bookAppointmentRequest.getCanBeReachedPhoneNumber())){
				coreRequest.setCanBeReachedPhoneNumber(bookAppointmentRequest.getCanBeReachedPhoneNumber());
			}
			if(bookAppointmentRequest.getOperationHeader()!=null){
				//coreRequest.setOperationHeader(getOperationHeaderFromRest(bookAppointmentRequest.getOperationHeader()));
			}
		}
		return coreRequest;
	}


	private static List<ProductAppointmentRequestVO> getProductAppointmentRequestFromRest(
			List<ProductAppointmentRequest> productAppointmentRequestList) {
		List<ProductAppointmentRequestVO> productAppointmentRequestVOList = new ArrayList<ProductAppointmentRequestVO>();
		if(!productAppointmentRequestList.isEmpty()){
			for(ProductAppointmentRequest productAppointmentRequest : productAppointmentRequestList){
				ProductAppointmentRequestVO productAppointmentRequestVO = new ProductAppointmentRequestVO();
				if(productAppointmentRequest.getRequestedAppointment()!=null){
					productAppointmentRequestVO.setRequestedAppointment(getRequestedAppointmentFromRest(productAppointmentRequest.getRequestedAppointment()));
				}
				if(productAppointmentRequest.getProductServiceTypeList()!=null && !productAppointmentRequest.getProductServiceTypeList().isEmpty()){
					productAppointmentRequestVO.setProductServiceTypeList(productAppointmentRequest.getProductServiceTypeList());
				}
				productAppointmentRequestVOList.add(productAppointmentRequestVO);
			}
		}
		return productAppointmentRequestVOList;
	}

	private static AppointmentTypeBaseVO getRequestedAppointmentFromRest(
			AppointmentTypeBase requestedAppointment) {
		AppointmentTypeBaseVO appointmentTypeBaseVO = new AppointmentTypeBaseVO();
		if(requestedAppointment!=null){
			if(requestedAppointment.getAppointmentDate()!=null){
				appointmentTypeBaseVO.setAppointmentDate(requestedAppointment.getAppointmentDate()!= null? requestedAppointment.getAppointmentDate().toDate(): null);
			}
			if(requestedAppointment.getAppointmentTimeSlot()!=null){
				appointmentTypeBaseVO.setAppointmentTimeSlot(getAppointmentTimeSlotFromRest(requestedAppointment.getAppointmentTimeSlot()));
			}
		}
		return appointmentTypeBaseVO;
	}

	private static AppointmentTimeSlotVO getAppointmentTimeSlotFromRest(AppointmentTimeSlot appointmentTimeSlot) {
		AppointmentTimeSlotVO appointmentTimeSlotVO = new AppointmentTimeSlotVO();
		if(appointmentTimeSlot!=null){
			if(!StringUtils.isBlank(appointmentTimeSlot.getStartTime())){
				appointmentTimeSlotVO.setStartTime(appointmentTimeSlot.getStartTime());
			}
			if(!StringUtils.isEmpty(appointmentTimeSlot.getEndTime())){
				appointmentTimeSlotVO.setEndTime(appointmentTimeSlot.getEndTime());
			}
		}
		return appointmentTimeSlotVO;
	}

	public static BookAppointmentResponse transform(BookAppointmentCoreResponse coreResponse) {
		BookAppointmentResponse bookAppointmentResponse = new BookAppointmentResponse();
		if(coreResponse!=null){
			if(coreResponse.getSoftBookingInd()!=null){
				bookAppointmentResponse.setSoftBookingInd(coreResponse.getSoftBookingInd());
			}
			if(coreResponse.getProductAppointmentList()!=null){
				bookAppointmentResponse.setProductAppointmentList(getProductAppointmentListToRest(coreResponse.getProductAppointmentList()));
			}
		}
		return bookAppointmentResponse;
	}

	private static List<ProductAppointmentResponse> getProductAppointmentListToRest(
			List<ProductAppointmentResponseVO> productAppointmentVOList) {
		List<ProductAppointmentResponse> productAppointmentResponseList = new ArrayList<ProductAppointmentResponse>();
		if(!productAppointmentVOList.isEmpty()){
			for(ProductAppointmentResponseVO productAppointmentResponseVO : productAppointmentVOList){
				ProductAppointmentResponse productAppointmentRespose = new ProductAppointmentResponse();
				if(productAppointmentResponseVO.getAppointmentInfo()!=null){
					productAppointmentRespose.setAppointmentInfo(getAppointmentInfoToRest(productAppointmentResponseVO.getAppointmentInfo()));
				}
				if(productAppointmentResponseVO.getProductServiceTypeList()!=null && !productAppointmentResponseVO.getProductServiceTypeList().isEmpty()){
					productAppointmentRespose.setProductServiceTypeList(productAppointmentResponseVO.getProductServiceTypeList());
				}
				
				productAppointmentResponseList.add(productAppointmentRespose);
			}
		}
		return productAppointmentResponseList;
	}

	private static AppointmentType getAppointmentInfoToRest(AppointmentTypeVO appointmentInfoVO) {
		AppointmentType appointmentType = new AppointmentType();
		if(appointmentInfoVO!=null){
			if(appointmentInfoVO.getAppointmentDate()!=null){
				appointmentType.setAppointmentDate(appointmentInfoVO.getAppointmentDate() != null? new LocalDate(appointmentInfoVO.getAppointmentDate()): null);
			}
			if(!StringUtils.isEmpty(appointmentInfoVO.getAppointmentId())){
				appointmentType.setAppointmentId(appointmentInfoVO.getAppointmentId());
			}
			if(appointmentInfoVO.getAppointmentTimeSlot()!=null){
				appointmentType.setAppointmentTimeSlot(getAppointmentTimeSlotToRest(appointmentInfoVO.getAppointmentTimeSlot()));
			}
		}
		return appointmentType;
	}

	private static AppointmentTimeSlot getAppointmentTimeSlotToRest(AppointmentTimeSlotVO appointmentTimeSlotVO) {
		AppointmentTimeSlot appointmentTimeSlot = new AppointmentTimeSlot();
		if(appointmentTimeSlotVO!=null){
			appointmentTimeSlot.setEndTime(appointmentTimeSlotVO.getEndTime());
			appointmentTimeSlot.setStartTime(appointmentTimeSlotVO.getStartTime());
		}
		return appointmentTimeSlot;
	}

	

	public static BookAppointmentAdapterRequestBody getBookAppointmentBody(
		ProductAppointmentRequestVO productAppointment, BookAppointmentCoreRequest coreRequest, CheckProductFeasibilityAdapterResponse feasibilityResponse, CheckProductFeasibilityAdapterRequest feasibilityRequest, ShoppingCartVO shoppingCartVO,  String opShoppingCartId, List<SubscribedProductInfoRestVO> assignedProductList, boolean changeOfTechnologyInd) 
	{
        BookAppointmentAdapterRequestBody body = null; 
        List<InstallationRequirementsList> installationReqListForAppointment = EnterpriseWLNCommonTransformer.getInstallationReqListForAppointment(feasibilityResponse, feasibilityRequest,assignedProductList,changeOfTechnologyInd);
        
		if (CollectionUtils.isNotEmpty(installationReqListForAppointment)) {
			body = new BookAppointmentAdapterRequestBody();			
			body.setCanBeReachedNumber(coreRequest.getCanBeReachedPhoneNumber());
			body.setApptDate(sdf.format(productAppointment.getRequestedAppointment().getAppointmentDate()));
			body.setApptStartTime(productAppointment.getRequestedAppointment().getAppointmentTimeSlot().getStartTime());
			body.setApptEndTime(productAppointment.getRequestedAppointment().getAppointmentTimeSlot().getEndTime());
			body.setInstallationRequirementsList(getFilteredInstallationReqList(productAppointment,  installationReqListForAppointment));
		    			
			if (!StringUtils.isBlank(opShoppingCartId)) {
				body.setOrderId(opShoppingCartId);
			}
			
			body.setAddressInfo(getAddressInfoFromVO(shoppingCartVO));
		}
		return body;
	}

	private static AddressInfo getAddressInfoFromVO(ShoppingCartVO shoppingCartVO) {
		AddressInfo addressInfo = new AddressInfo();
		if(shoppingCartVO!=null && shoppingCartVO.getServiceAddress()!=null){
			if(!StringUtils.isBlank(shoppingCartVO.getServiceAddress().getServiceAddressId())){
				addressInfo.setFmsAddressId(shoppingCartVO.getServiceAddress().getServiceAddressId());
			}
			if(!StringUtils.isBlank(shoppingCartVO.getServiceAddress().getCityCode())){
				addressInfo.setCity(shoppingCartVO.getServiceAddress().getCityCode());
			}
			if(!StringUtils.isBlank(shoppingCartVO.getServiceAddress().getProvinceCode())){
				addressInfo.setProvince(shoppingCartVO.getServiceAddress().getProvinceCode());
			}
		}
		return addressInfo;
	}


	public static List<ProductAppointmentResponseVO> transform(
			List<Work> appointmentTaskList, BookAppointmentCoreRequest coreRequest, ShoppingCartContextVO contextVO) {
		List<ProductAppointmentResponseVO> productAppointmentResponseList = new ArrayList<ProductAppointmentResponseVO>();
		if(appointmentTaskList!=null && !appointmentTaskList.isEmpty()){
			for(Work bookAppointmentTask : appointmentTaskList){
				BookAppointmentTask appointmentTask = (BookAppointmentTask) bookAppointmentTask;
				ProductAppointmentResponseVO appointmentResponseVO = new ProductAppointmentResponseVO();
				appointmentResponseVO.setProductServiceTypeList(appointmentTask.getProductAppointmentRequest().getProductServiceTypeList());
				appointmentResponseVO.setAppointmentInfo(getAppointmentInfoFromTask(appointmentTask));
				productAppointmentResponseList.add(appointmentResponseVO);
			}
		}
		return productAppointmentResponseList;
	}

	private static AppointmentTypeVO getAppointmentInfoFromTask(BookAppointmentTask appointmentTask) {
		AppointmentTypeVO appointmentTypeVO = new AppointmentTypeVO();
		appointmentTypeVO.setAppointmentDate(appointmentTask.getProductAppointmentRequest().getRequestedAppointment().getAppointmentDate());
		if(appointmentTask.getResult()!=null && appointmentTask.getResult().getBookingInfoList()!=null && !appointmentTask.getResult().getBookingInfoList().isEmpty()){
			appointmentTypeVO.setAppointmentId(appointmentTask.getResult().getBookingInfoList().get(0).getBookingId());
		}
		appointmentTypeVO.setAppointmentTimeSlot(appointmentTask.getProductAppointmentRequest().getRequestedAppointment().getAppointmentTimeSlot());
		return appointmentTypeVO;
	}

	public static BookAppointmentCoreResponse validateBookingSvcResponse(List<Work> appointmentTaskList,			
			CheckProductFeasibilityAdapterResponse feasibilityResponse) {
		BookAppointmentCoreResponse result =  new BookAppointmentCoreResponse();
		if(appointmentTaskList!=null && !appointmentTaskList.isEmpty()){
			for(Work appointmentTask : appointmentTaskList){
				if(appointmentTask!=null && appointmentTask instanceof BookAppointmentTask){
					BookAppointmentTask appTask = (BookAppointmentTask) appointmentTask;
					if(appTask.getRuntimeException()!=null){
						HttpStatusCodeException downstreamException = (HttpStatusCodeException) appTask.getRuntimeException();
						if(downstreamException.getStatusCode().toString().equals("404")){
							result.setHasError(true);
							result.setMessageList(EnterpriseWLNCommonTransformer.transformToMessageListVO("ESS_BOOK_APPOINTMENT_0004","BookingService is down."));
							return result;
						}	
					}	
					if(appTask.getResult()!=null && appTask.getResult().getMessageList()!=null && !appTask.getResult().getMessageList().isEmpty()){
						result.setHasError(true);
						result.setMessageList(EnterpriseWLNCommonTransformer.transformToMessageListVO("ESS_BOOK_APPOINTMENT_0004", appTask.getResult().getMessageList()));
						return result;
					}
				}
			}
		} else {
			// Check if product on contextCartVO have SW installation only and return a message.
			if (CollectionUtils.isNotEmpty(feasibilityResponse.getProductFeasibilityInfoList().getProductFeasibilityInfo())) {
				if ( CollectionUtils.isNotEmpty(feasibilityResponse.getInstallTypeSW())) {
				
						// All products have SW installation
						result.setHasError(true);
						result.setMessageList(EnterpriseWLNCommonTransformer.transformToMessageListVO("ESS_BOOK_APPOINTMENT_0004", "Cannot perform a Book Appointment for the following SW products " + Arrays.toString(feasibilityResponse.getInstallTypeSW().toArray())));
						return result;
					
				}
			}
		}
		return result;
	}

	public static BookAppointmentErrorResponse transformErrors(BookAppointmentCoreResponse coreResponse) {
		BookAppointmentErrorResponse errorResponse = new BookAppointmentErrorResponse();
		errorResponse.setResponseMessages(getResponseMessagesFromCore(coreResponse));
		return errorResponse;
	}

	private static List<ResponseMessage> getResponseMessagesFromCore(BookAppointmentCoreResponse coreResponse) {
		List<ResponseMessage> responseMsgList = new ArrayList<ResponseMessage>();
		if(coreResponse.getMessageList()!=null && !coreResponse.getMessageList().isEmpty()){
			for(MessageVO msgVO : coreResponse.getMessageList()){
				ResponseMessage responseMsg = new ResponseMessage();
				responseMsg.setErrorCode(msgVO.getErrCode());
				responseMsg.setMessageTypeCd(msgVO.getMessageType());
				responseMsg.setMessages(getMessagesFromCore(msgVO.getMessages()));
				responseMsg.setContextDataAttributes(getContextAttributesFromCore(msgVO.getContextDataAttributes()));
				responseMsgList.add(responseMsg);
			}
		}
		
		return responseMsgList;
	}

	private static List<ContextAttribute> getContextAttributesFromCore(
			List<ContextDataAttributesVO> contextDataAttributes) {
		List<ContextAttribute> contextAttributeList = new ArrayList<ContextAttribute>();
		if(contextDataAttributes!=null && !contextDataAttributes.isEmpty()){
			for(ContextDataAttributesVO contextDataAttributeVO : contextDataAttributes){
				ContextAttribute ctxAttribute = new ContextAttribute();
				ctxAttribute.setAttributeTypeTxt(contextDataAttributeVO.getAttributeTypeTxt());
				ctxAttribute.setAttributeValueTxt(contextDataAttributeVO.getAttributeValueTxt());
				contextAttributeList.add(ctxAttribute);
			}
		}
		return contextAttributeList;
	}


	private static List<com.telus.csm.ewlnsms.rest.domain.MessageType> getMessagesFromCore(
			List<MessageDetailVO> messages) {
		List<com.telus.csm.ewlnsms.rest.domain.MessageType> messageTypeList = new ArrayList<com.telus.csm.ewlnsms.rest.domain.MessageType>();
		if(messages!=null && !messages.isEmpty()){
			for(MessageDetailVO messageDetailVO : messages){
				com.telus.csm.ewlnsms.rest.domain.MessageType messageType = new com.telus.csm.ewlnsms.rest.domain.MessageType();
				messageType.setLocaleCd(messageDetailVO.getLocaleCd());
				messageType.setMessageTxt(messageDetailVO.getMessagetTxt());
				messageTypeList.add(messageType);
			}
		}
		return messageTypeList;
	}

	
	private static List<InstallationRequirementsList> getFilteredInstallationReqList(
			ProductAppointmentRequestVO productAppointment,
			List<InstallationRequirementsList> installationReqList)
	{
	   List<InstallationRequirementsList> result = new ArrayList<InstallationRequirementsList>();		
		if (CollectionUtils.isNotEmpty(productAppointment.getProductServiceTypeList())) {
			
			for ( String productTypeCd : productAppointment.getProductServiceTypeList() ) {				
				for ( InstallationRequirementsList installationRequirementsFromFeasibilityResp : installationReqList ) {
					if ( productTypeCd.equalsIgnoreCase(installationRequirementsFromFeasibilityResp.getProductServiceType())){
				       result.add(installationRequirementsFromFeasibilityResp);
				       }		
				}		
			}
		}
         
		return result;
	}

	
}