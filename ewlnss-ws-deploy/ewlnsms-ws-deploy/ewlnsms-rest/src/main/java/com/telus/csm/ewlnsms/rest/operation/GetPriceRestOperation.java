package com.telus.csm.ewlnsms.rest.operation;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.telus.csm.ewlnsc.rest.exception.EssRestErrorException;
import com.telus.csm.ewlnsc.rest.exception.EssRestException;
import com.telus.csm.ewlnsc.rest.exception.EssRestSystemErrorException;
import com.telus.csm.ewlnsc.rest.util.RESTResponseMessageUtil;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsms.rest.domain.ResponseMessage;
import com.telus.csm.ewlnsms.rest.transform.GetPriceTransformer;
import com.telus.csm.ewlnsms.core.operation.GetPriceCoreOperation;
import com.telus.csm.ewlnsms.rest.domain.GetPriceErrorResponse;
import com.telus.csm.ewlnsms.rest.domain.PriceResponse;

@Component
@Scope(SCOPE_PROTOTYPE)
public class GetPriceRestOperation {

	private static final Logger LOGGER = Logger.getLogger(GetPriceRestOperation.class);

	@Autowired
	GetPriceCoreOperation coreOperation;
	
	public PriceResponse execute(final String shoppingCartId, final String transactionId) {

		final List<String> messageList = new ArrayList<String>();
		if (StringUtils.isEmpty(shoppingCartId)) {
			messageList.add("shoppingCartId is missing from the request.");
			final GetPriceErrorResponse err = new GetPriceErrorResponse();
			err.setResponseMessages(GetPriceTransformer.transformResponseMessages(messageList, "ESS_WLN_GET_PRICE_00001")); 
			throw new EssRestErrorException(err);
		}
		
		try {
			PriceResponse response = coreOperation.execute(shoppingCartId, transactionId);
			if (response == null) {
				messageList.add("Unable to get the offer.");
				final GetPriceErrorResponse err = new GetPriceErrorResponse();
				err.setResponseMessages(GetPriceTransformer.transformResponseMessages(messageList, "ESS_WLN_GET_PRICE_00003")); 
				throw new EssRestErrorException(err);				
			}
			return response;
			
		} catch (EssRestException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.error("Unknown Exception", e);
			GetPriceErrorResponse errorResponse = new GetPriceErrorResponse();
			ResponseMessage responseMessagesItem = JsonUtil.parseJsonToObject(RESTResponseMessageUtil.getResponseMessageJson(e), ResponseMessage.class);
			errorResponse.addResponseMessagesItem(responseMessagesItem);
			throw new EssRestSystemErrorException(errorResponse);
		}
	}
}