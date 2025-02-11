package com.telus.csm.ewlnss.adapter.helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseMessage;
import com.telus.csm.ewlnss.adapter.domain.AdapterResponseMessageDesc;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.Message;

/**
 * @author x163290
 * 
 */
public class MessageMappingHelper {
	private Properties messageCodeMappingProp = new Properties(); 
	private Properties errorMessagePropEn = new Properties();
	private Properties warnMessagePropEn = new Properties();
	private static Logger logger = Logger.getLogger(MessageMappingHelper.class);

	private static MessageMappingHelper instance = null;

	private MessageMappingHelper() {
		try {

			ClassLoader classLoader = MessageMappingHelper.class
					.getClassLoader();

			//message code mapping
			messageCodeMappingProp.load(classLoader
					.getResourceAsStream("message_code_mapping.properties"));
			
			// error
			errorMessagePropEn
					.load(classLoader
							.getResourceAsStream("error_messages_en.properties"));
			
			// warning
			warnMessagePropEn
					.load(classLoader
							.getResourceAsStream("warn_messages_en.properties"));

		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
	}

	public static MessageMappingHelper getInstance() {
		if (instance == null) {
			synchronized (MessageMappingHelper.class){
				if (instance == null){
					instance = new MessageMappingHelper();
				}
			}
		} 
		
		return instance; 
	}
	
	public String getMappedMessageCode(String messageCode){
		String mappedMessageCode;
		mappedMessageCode = messageCodeMappingProp.getProperty(messageCode);
		
		if (mappedMessageCode != null){
			return mappedMessageCode;
		} else {
			return messageCode;
		}
	}


	public void populateErrorMessageDescTextListForPolicyAndService(AdapterResponseMessage messageDO) {
		AdapterResponseMessageDesc msgDescDoEn = new AdapterResponseMessageDesc(Locale.CANADA, errorMessagePropEn.getProperty(messageDO.getMessageCode()));
		if (msgDescDoEn.getMessageDescriptionText() == null) {
			msgDescDoEn = new AdapterResponseMessageDesc(Locale.CANADA, EnterpriseWLNSalesServicesConstants.DOWNSTREAM_ERROR_SEE_RELATED_MSG);
		}
		ArrayList<AdapterResponseMessageDesc> msgDescTextList = new ArrayList<AdapterResponseMessageDesc>();
		msgDescTextList.add(msgDescDoEn);	
		messageDO.setMesssageDescriptionTextList(msgDescTextList);
	}
	
	public void populateErrorMessageDescTextList(AdapterResponseMessage messageDO) {
		AdapterResponseMessageDesc msgDescDoEn = new AdapterResponseMessageDesc(Locale.CANADA,
				errorMessagePropEn.getProperty(messageDO.getMessageCode()));
		if (msgDescDoEn.getMessageDescriptionText() == null) {
			msgDescDoEn = new AdapterResponseMessageDesc(Locale.CANADA, messageDO.getContextData());
		}
		ArrayList<AdapterResponseMessageDesc> msgDescTextList = new ArrayList<AdapterResponseMessageDesc>();
		msgDescTextList.add(msgDescDoEn);	
		messageDO.setMesssageDescriptionTextList(msgDescTextList);
	}
	
	public void populateWarningMessageDescTextList(AdapterResponseMessage messageDO) {
		AdapterResponseMessageDesc msgDescDoEn = new AdapterResponseMessageDesc(Locale.CANADA,
				warnMessagePropEn.getProperty(messageDO.getMessageCode()));
		ArrayList<AdapterResponseMessageDesc> msgDescTextList = new ArrayList<AdapterResponseMessageDesc>();
		msgDescTextList.add(msgDescDoEn);	
		messageDO.setMesssageDescriptionTextList(msgDescTextList);
	}

	public Message createMessage(String mappedMessageCode) {
		Message msg = new Message();
		msg.setLocale("en_CA");
		msg.setMessage(getMsgDesc(mappedMessageCode));
		
		return msg;
	}

	public String getMsgDesc(String mappedMessageCode) {
		return errorMessagePropEn.getProperty(mappedMessageCode) == null ? "" : errorMessagePropEn.getProperty(mappedMessageCode);
	}
}
