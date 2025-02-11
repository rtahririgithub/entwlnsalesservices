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
import com.telus.csm.ess.core.domain.UpdateSaleCoreResponse;
import com.telus.csm.ess.order.core.operation.UpdateSaleCoreOperation2;
import com.telus.csm.ess.rest.domain.MessageType;
import com.telus.csm.ess.rest.domain.ResponseMessage;
import com.telus.csm.ess.rest.domain.SaleStatus;
import com.telus.csm.ess.rest.domain.ShoppingCartIdentifier;
import com.telus.csm.ess.rest.domain.UpdateSaleErrorResponse;
import com.telus.csm.ess.rest.domain.UpdateSaleRequest;
import com.telus.csm.ess.rest.domain.UpdateSaleResponse;
import com.telus.csm.ewlnsc.rest.exception.EssRestErrorException;
import com.telus.csm.ewlnsc.rest.exception.EssRestException;
import com.telus.csm.ewlnsc.rest.exception.EssRestSystemErrorException;
import com.telus.csm.ewlnsc.rest.util.RESTResponseMessageUtil;
import com.telus.csm.ewlnsc.util.JsonUtil;

@Component
@Scope(SCOPE_PROTOTYPE)
public class UpdateSaleRestOperation {

	private static Logger logger = Logger.getLogger(UpdateSaleRestOperation.class);

	@Autowired
	UpdateSaleCoreOperation2 updateSaleCoreOperation;
	
	public UpdateSaleResponse execute(String salesId, final UpdateSaleRequest param) {
		
		try {
		
			UpdateSaleCoreResponse updateSaleCoreResponse = updateSaleCoreOperation.execute(salesId, param.getFulfillmentAction().getCompleteSaleInd());

			if (updateSaleCoreResponse.isHasError()) {
				UpdateSaleErrorResponse errorResponse = new UpdateSaleErrorResponse();
				ShoppingCartIdentifier shoppingCartIdentifier = new ShoppingCartIdentifier();
				shoppingCartIdentifier.setShoppingCartId(updateSaleCoreResponse.getShoppingCartId());
				errorResponse.setShoppingCartIdentifier(shoppingCartIdentifier);
				
				SaleStatus saleStatus = new SaleStatus();
				saleStatus.setStatusCd(updateSaleCoreResponse.getSaleStatus());
				errorResponse.setStatus(saleStatus);
				
				errorResponse.setResponseMessages(transformToResponseMessagesFromCore(updateSaleCoreResponse.getMessageList()));

				throw new EssRestErrorException(errorResponse);
			}
			
			return transform(updateSaleCoreResponse);
			
		
		} catch (EssRestException e) {
			throw e;
		} catch (Exception e) {
			logger.error("Unknown Exception", e);
			UpdateSaleErrorResponse errorResponse = new UpdateSaleErrorResponse();
			ResponseMessage responseMessagesItem = JsonUtil
					.parseJsonToObject(RESTResponseMessageUtil.getResponseMessageJson(e), ResponseMessage.class);
			errorResponse.addResponseMessagesItem(responseMessagesItem);
			throw new EssRestSystemErrorException(errorResponse);
		}

	}
	
	private UpdateSaleResponse transform(final UpdateSaleCoreResponse response) {
		final UpdateSaleResponse result = new UpdateSaleResponse();
		
		ShoppingCartIdentifier shoppingCartIdentifier = new ShoppingCartIdentifier();
		shoppingCartIdentifier.setShoppingCartId(response.getShoppingCartId());
		result.setSaleIdentifier(shoppingCartIdentifier);
		
		SaleStatus saleStatus = new SaleStatus();
		saleStatus.setStatusCd(response.getSaleStatus());
		result.setStatus(saleStatus);
		
		return result;
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
