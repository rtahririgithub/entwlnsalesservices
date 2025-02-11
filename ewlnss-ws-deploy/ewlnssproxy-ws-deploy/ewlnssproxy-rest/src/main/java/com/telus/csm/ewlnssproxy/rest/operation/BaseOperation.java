package com.telus.csm.ewlnssproxy.rest.operation;

import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.telus.csm.ewlnssproxy.cpib.domain.Error;
import com.telus.csm.ewlnssproxy.cpib.domain.ErrorCharacteristic;
import com.telus.csm.ewlnssproxy.cpib.domain.Message;

public class BaseOperation {
	private Logger logger = Logger.getLogger(BaseOperation.class);

	public Error handleProxyError(Response.Status rspStatus, String reason, String status, String traceId,
			List<Message> messageList, List<ErrorCharacteristic> characteristicList) {

		Logger logger = getLogger();
		logger.info("Start handling proxy error....");
		logger.info("Response Code: " + rspStatus);
		logger.info("Reason       : " + reason);

		Error error = new Error();
		error.setCode(String.valueOf(rspStatus.getStatusCode()));
		error.setReason(reason);
		error.setStatus(status);
		error.setTraceId(traceId);
		if (messageList != null)
			error.setMessage(messageList);
		if (characteristicList != null)
			error.setCharacteristic(characteristicList);

		return error;
	}

	public Logger getLogger() {
		return logger;
	}

}
