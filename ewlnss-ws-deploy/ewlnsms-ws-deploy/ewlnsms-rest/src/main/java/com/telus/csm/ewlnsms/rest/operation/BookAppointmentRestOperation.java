package com.telus.csm.ewlnsms.rest.operation;

import java.util.ArrayList;
import java.util.List;

import com.telus.channelsalesmgmt.common.util.LoggerUtil;
import com.telus.csm.ewlnsc.rest.exception.EssRestErrorException;
import com.telus.csm.ewlnsc.rest.exception.EssRestException;
import com.telus.csm.ewlnsc.rest.exception.EssRestSystemErrorException;
import com.telus.csm.ewlnsc.rest.util.RESTResponseMessageUtil;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsms.core.domain.BookAppointmentCoreRequest;
import com.telus.csm.ewlnsms.core.domain.BookAppointmentCoreResponse;
import com.telus.csm.ewlnsms.core.transformer.BookAppointmentTransformer;
import com.telus.csm.ewlnsms.core.operation.BookAppointmentCoreOperation;
import com.telus.csm.ewlnsms.rest.domain.BookAppointmentErrorResponse;
import com.telus.csm.ewlnsms.rest.domain.BookAppointmentRequest;
import com.telus.csm.ewlnsms.rest.domain.BookAppointmentResponse;
import com.telus.csm.ewlnsms.rest.domain.ResponseMessage;
public class BookAppointmentRestOperation {
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(BookAppointmentRestOperation.class);

	public BookAppointmentResponse execute(BookAppointmentRequest bookAppointmentRequest) {
		String functionName="BookAppointmentRestOperation.execute()";
		
		try {
			logger.enter(functionName);
			
			BookAppointmentResponse result;
			
			List<ResponseMessage> messageList = validateInput(bookAppointmentRequest);
			
			if(!messageList.isEmpty()){
				BookAppointmentErrorResponse errorResponse = new BookAppointmentErrorResponse();
				errorResponse.setResponseMessages(messageList);
				throw new EssRestErrorException(errorResponse);
			}
			
			BookAppointmentCoreRequest coreRequest = BookAppointmentTransformer.transform(bookAppointmentRequest);
			
			BookAppointmentCoreOperation coreOperation = new BookAppointmentCoreOperation();
			
			BookAppointmentCoreResponse coreResponse = coreOperation.execute(coreRequest);
			
			if(coreResponse.isHasError()){
				BookAppointmentErrorResponse errorResponse = BookAppointmentTransformer.transformErrors(coreResponse);
				throw new EssRestErrorException(errorResponse);
			}
			
			result = BookAppointmentTransformer.transform(coreResponse);
			
			
			return result;
			
		} catch (EssRestException e) {
			throw e;
		} catch (Exception e) {
			logger.error("", "execute", "Unknown Exception", e);
			BookAppointmentErrorResponse errorResponse = new BookAppointmentErrorResponse();
			ResponseMessage responseMessagesItem = JsonUtil
					.parseJsonToObject(RESTResponseMessageUtil.getResponseMessageJson(e), ResponseMessage.class);
			errorResponse.addResponseMessagesItem(responseMessagesItem);
			throw new EssRestSystemErrorException(errorResponse);
		} finally {
			logger.exit(functionName);
		}

	}

	private List<ResponseMessage> validateInput(BookAppointmentRequest bookAppointmentRequest) {
		List<ResponseMessage> responseMessageList = new ArrayList<ResponseMessage>();
		//TODO: find out about the input validation for this operation
		return responseMessageList;
	}

}
