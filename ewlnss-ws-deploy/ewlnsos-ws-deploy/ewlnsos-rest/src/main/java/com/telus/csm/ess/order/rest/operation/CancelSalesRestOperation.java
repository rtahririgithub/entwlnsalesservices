package com.telus.csm.ess.order.rest.operation;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.telus.csm.ess.common.domain.MessageDetailVO;
import com.telus.csm.ess.common.domain.MessageVO;
import com.telus.csm.ess.core.domain.CancelSaleCoreResponse;
import com.telus.csm.ess.order.core.operation.CancelSalesCoreOperation2;
import com.telus.csm.ess.rest.domain.CancelShoppingCartErrorResponse;
import com.telus.csm.ess.rest.domain.MessageType;
import com.telus.csm.ess.rest.domain.ResponseMessage;
import com.telus.csm.ewlnsc.rest.exception.EssRestErrorException;
import com.telus.csm.ewlnsc.rest.exception.EssRestException;
import com.telus.csm.ewlnsc.rest.exception.EssRestSystemErrorException;
import com.telus.csm.ewlnsc.rest.util.RESTResponseMessageUtil;
import com.telus.csm.ewlnsc.util.JsonUtil;

@Component
@Scope(SCOPE_PROTOTYPE)
public class CancelSalesRestOperation {
	
	private static final Logger logger = Logger.getLogger(CancelSalesRestOperation.class);

	@Autowired
	CancelSalesCoreOperation2 cancelSalesCoreOperation;
	
	public void execute(String shoppingCartId) {

		try {
			
			CancelSaleCoreResponse cancelSaleCoreResponse  = cancelSalesCoreOperation.execute(shoppingCartId);
			
			if (cancelSaleCoreResponse.isHasError()) {
				CancelShoppingCartErrorResponse errorResponse = new CancelShoppingCartErrorResponse();
				errorResponse.setResponseMessages(transformToResponseMessagesFromCore(cancelSaleCoreResponse.getMessageList()));
				throw new EssRestErrorException(errorResponse);
			}			
		
		} catch (EssRestException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Unknown Exception", e);
			CancelShoppingCartErrorResponse errorResponse = new CancelShoppingCartErrorResponse();
			ResponseMessage responseMessagesItem = JsonUtil
					.parseJsonToObject(RESTResponseMessageUtil.getResponseMessageJson(e), ResponseMessage.class);
			errorResponse.addResponseMessagesItem(responseMessagesItem);
			throw new EssRestSystemErrorException(errorResponse);
		}
		
	}

	public static List<ResponseMessage> transformToResponseMessagesFromCore(
			List<MessageVO> messageList) {
		List<ResponseMessage> responseMessageList = new ArrayList<ResponseMessage>();
		if(messageList!=null && !messageList.isEmpty()){
			for(MessageVO msgVO : messageList){
				ResponseMessage responseMsg = new ResponseMessage();
				responseMsg.setErrorCode(msgVO.getErrCode());
				responseMsg.setMessageTypeCd(msgVO.getMessageType());
				responseMsg.setMessages(getMessageListFromCore(msgVO.getMessages()));
				responseMessageList.add(responseMsg);
			}
		}
		return responseMessageList;
	}

	private static List<MessageType> getMessageListFromCore(List<MessageDetailVO> messages) {
		List<MessageType> messageTypeList = new ArrayList<MessageType>();
		if(messages!=null && !messages.isEmpty()){
			for(MessageDetailVO msgDetailVO : messages){
				MessageType msgType = new MessageType();
				msgType.setLocaleCd(msgDetailVO.getLocaleCd());
				msgType.setMessageTxt(msgDetailVO.getMessagetTxt());
				messageTypeList.add(msgType);
			}
		}
		return messageTypeList;
	}


	
}
