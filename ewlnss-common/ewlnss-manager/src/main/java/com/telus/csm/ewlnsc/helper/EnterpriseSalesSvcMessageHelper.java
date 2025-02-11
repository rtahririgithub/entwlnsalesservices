package com.telus.csm.ewlnsc.helper;

/**
 * 
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.telus.csm.ewlnsc.domain.EssAppException;
import com.telus.csm.ewlnsc.domain.MessageDO;
import com.telus.csm.ewlnsc.domain.MessageDescDO;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;

/**
 * @author x131123
 * 
 */
public class EnterpriseSalesSvcMessageHelper implements
		IEnterpriseSalesSvcMessageHelper {

	private static final String REPLACE_PATTERN_END = "\\}";

	private static final String REPLACE_PATTERN_START = "\\$\\{";

	private Properties errorMessagePropEn = new Properties();
	private Properties errorMessagePropFr = new Properties();

	private Properties errorRetryMessagePropEn = new Properties();
	private Properties errorRetryMessagePropFr = new Properties();

	private Properties exceptionMessagePropEn = new Properties();
	private Properties exceptionMessagePropFr = new Properties();

	private Properties warnMessagePropEn = new Properties();
	private Properties warnMessagePropFr = new Properties();

	private Properties infoMessagePropEn = new Properties();
	private Properties infoMessagePropFr = new Properties();
	
	private Properties messageCodeMappingProp = new Properties(); 

	private static EnterpriseSalesSvcMessageHelper instance = null;
	
	private static Logger logger = Logger.getLogger(EnterpriseSalesSvcMessageHelper.class);

	private EnterpriseSalesSvcMessageHelper() {
		try {

			ClassLoader classLoader = EnterpriseSalesSvcMessageHelper.class
					.getClassLoader();

			//message code mapping
			messageCodeMappingProp.load(classLoader
					.getResourceAsStream("message_code_mapping.properties"));
			
			// error
			errorMessagePropEn
					.load(classLoader
							.getResourceAsStream("error_messages_en.properties"));
			errorMessagePropFr
					.load(classLoader
							.getResourceAsStream("error_messages_fr.properties"));

			// error retry 
			errorRetryMessagePropEn
					.load(classLoader
							.getResourceAsStream("error_retry_messages_en.properties"));
			errorRetryMessagePropFr
					.load(classLoader
							.getResourceAsStream("error_retry_messages_fr.properties"));

			// exception
			exceptionMessagePropEn
					.load(classLoader
							.getResourceAsStream("exception_messages_en.properties"));
			exceptionMessagePropFr
					.load(classLoader
							.getResourceAsStream("exception_messages_fr.properties"));

			// warn
			warnMessagePropEn
					.load(classLoader
							.getResourceAsStream("warn_messages_en.properties"));
			warnMessagePropFr
					.load(classLoader
							.getResourceAsStream("warn_messages_fr.properties"));

			// info
			infoMessagePropEn
					.load(classLoader
							.getResourceAsStream("info_messages_en.properties"));
			infoMessagePropFr
					.load(classLoader
							.getResourceAsStream("info_messages_fr.properties"));
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
	}

	public static EnterpriseSalesSvcMessageHelper getInstance() {
		if (instance == null) {
			synchronized (EnterpriseSalesSvcMessageHelper.class){
				if (instance == null){
					instance = new EnterpriseSalesSvcMessageHelper();
				}
			}
		} 
		
		return instance;
	}



	@Override
	public MessageDO getMessageDetail(String messageCode, String messageType,
			String[] arguments, Exception exception){
		int messageTypeInt = this.decodeMessageTypeToInt(messageType);
		return getMessageDetail(messageCode, messageTypeInt, arguments, exception);
	}
	
	private int decodeMessageTypeToInt(String messageCode){ 
		
		if (messageCode.equals(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR)){
			return IEnterpriseSalesSvcMessageHelper.MESSAGE_TYPE_ERROR;
		} else if (messageCode.equals(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_WARNING)){
			return IEnterpriseSalesSvcMessageHelper.MESSAGE_TYPE_WARN;
		} else if (messageCode.equals(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_EXCEPTION)){
			return IEnterpriseSalesSvcMessageHelper.MESSAGE_TYPE_EXCEPTION;
		} else if (messageCode.equals(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR_RETRY_SAFE)){
			return IEnterpriseSalesSvcMessageHelper.MESSAGE_TYPE_ERROR_RETRY_SAFE;
		} else {
			return IEnterpriseSalesSvcMessageHelper.MESSAGE_TYPE_INFO;
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.telus.csm.essc.helper.IEnterpriseSalesSvcMessageHelper#getMessageDetail
	 * (java.lang.String, int, java.lang.String[])
	 */
	@Override
	public MessageDO getMessageDetail(String messageCode, int messageType,
			String[] arguments, Exception exception) {
		String tempMessageCode = messageCode;
		MessageDO output = new MessageDO();
		
		String mappedMessageCode = getMappedMessageCode(tempMessageCode);
		if(mappedMessageCode != null){
			tempMessageCode = mappedMessageCode;
		}
		
		switch (messageType) {
		case MESSAGE_TYPE_ERROR:
			output.setMessageCode(tempMessageCode);
			if(errorRetryMessagePropEn.getProperty(output.getMessageCode()) != null) {
				output.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR_RETRY_SAFE);
				populateErrorMessageDescTextList(output, errorRetryMessagePropEn,
						errorRetryMessagePropFr, arguments, exception);
			} else {
				output.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
				populateErrorMessageDescTextList(output, errorMessagePropEn,
						errorMessagePropFr, arguments, exception);
			}
			
			break;
		case MESSAGE_TYPE_EXCEPTION:
			output.setMessageCode(tempMessageCode);
			output.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_EXCEPTION);
			populateErrorMessageDescTextList(output, exceptionMessagePropEn,
					exceptionMessagePropFr, arguments, exception);
			break;
		case MESSAGE_TYPE_WARN:
			output.setMessageCode(tempMessageCode);
			output.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_WARNING);
			populateErrorMessageDescTextList(output, warnMessagePropEn,
					warnMessagePropFr, arguments, exception);
			break;
		case MESSAGE_TYPE_INFO:
			output.setMessageCode(tempMessageCode);
			output.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_INFO);
			populateErrorMessageDescTextList(output, infoMessagePropEn,
					infoMessagePropFr, arguments, exception);
			break;
		case MESSAGE_TYPE_ERROR_RETRY_SAFE:
			output.setMessageCode(tempMessageCode);
			if(errorRetryMessagePropEn.getProperty(output.getMessageCode()) != null) {
				output.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR_RETRY_SAFE);
				populateErrorMessageDescTextList(output, errorRetryMessagePropEn,
						errorRetryMessagePropFr, arguments, exception);
			} else {
				output.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR_RETRY_SAFE);
				populateErrorMessageDescTextList(output, errorMessagePropEn,
						errorMessagePropFr, arguments, exception);
			}
			
			break;			
		default:
			break;
		}
		
		return output;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.telus.csm.essc.helper.IEnterpriseSalesSvcMessageHelper#getMessageDetail
	 * (java.lang.String, int, java.lang.String[])
	 */

	public MessageDO getMessageDetail(String messageCode, int messageType,
			String[] arguments, Exception exception, String transactionId, String contextData, Date timestamp) {
		MessageDO messageDO = this.getMessageDetail(messageCode, messageType, arguments, exception);
		messageDO.setTransactionId(transactionId);
		messageDO.setContextData(contextData + messageDO.getContextData());
		messageDO.setTimestamp(timestamp);
		return messageDO;
	}
	
	public MessageDO getMessageDetail(String messageCode, String messageType,
			String[] arguments, Exception exception, String transactionId, String contextData, Date timestamp) {
		int messageTypeInt = this.decodeMessageTypeToInt(messageType);
		return getMessageDetail(messageCode, messageTypeInt,
				arguments, exception, transactionId, contextData, timestamp);
	}
	
	public MessageDO getMessageDetail( EssAppException eae, String[] arguments ) {

		MessageDO messageDO = getMessageDetail(eae.getErrorCode(), MESSAGE_TYPE_ERROR, arguments, null);

		messageDO.setMessageCode( eae.getErrorCode());
		messageDO.setMessageType( EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
		messageDO.setTimestamp( new Date() );
		
		return messageDO;
	}
	
	private void populateErrorMessageDescTextList(MessageDO messageDO,
			Properties messagePropEn, Properties messagePropFr,
			String[] arguments, Exception exception) {
		MessageDescDO msgDescDoEn = new MessageDescDO(Locale.CANADA,
				messagePropEn.getProperty(messageDO.getMessageCode()));
		String messageFr = messagePropFr.getProperty(messageDO.getMessageCode());
		ArrayList<MessageDescDO> msgDescTextList = new ArrayList<MessageDescDO>();
	
		msgDescTextList.add(msgDescDoEn);		
		if (!StringUtils.isEmpty(messageFr)){
			MessageDescDO msgDescDoFr = new MessageDescDO(Locale.CANADA_FRENCH, messageFr);
			msgDescTextList.add(msgDescDoFr);
		}
		
		if (arguments != null && arguments.length > 0) {
			replaceToken(msgDescTextList, arguments);
		}
		messageDO.setMesssageDescriptionTextList(msgDescTextList);
		messageDO.setContextData(printStackTrace(exception));
		messageDO.setArguments(arguments);
	}
	
	private String printStackTrace(Throwable t) 
	{
		if (t == null) {
			return "";
		}
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		return sw.toString();
	}

	private void replaceToken(ArrayList<MessageDescDO> msgDescTextList,
			String[] arguments) {
		for (int i = 0; i < msgDescTextList.size(); i++) {
			int replaceIndex = 0;			
			for (int j = 0; j < arguments.length; j++,replaceIndex++) {
				Matcher matcher = Pattern.compile(
						REPLACE_PATTERN_START + replaceIndex + REPLACE_PATTERN_END)
						.matcher(
								(msgDescTextList.get(i))
										.getMessageDescriptionText());
				if (arguments[j] != null) {
					arguments[j] = arguments[j].replaceAll("\\$", "\\\\\\$");
				}
				(msgDescTextList.get(i))
						.setMessageDescriptionText(matcher
								.replaceFirst(arguments[j]));
			}
		}
	}
	
	private String getMappedMessageCode(String messageCode){
		String mappedMessageCode;
		mappedMessageCode = messageCodeMappingProp.getProperty(messageCode);
		return mappedMessageCode;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.telus.csm.essc.helper.IEnterpriseSalesSvcMessageHelper#getMessageDetail
	 * (java.lang.String, int, java.lang.String[])
	 */
	public MessageDO getMessageDetailForSAPMemo(String messageCode, int messageType,
			String[] arguments, Exception exception) {
		String tempMessageCode = messageCode;
		MessageDO output = new MessageDO();
		
		String mappedMessageCode = getMappedMessageCode(tempMessageCode);
		if(mappedMessageCode != null){
			tempMessageCode = mappedMessageCode;
		}
		
		switch (messageType) {
		case MESSAGE_TYPE_ERROR:
			output.setMessageCode(tempMessageCode);
			if(errorRetryMessagePropEn.getProperty(output.getMessageCode()) != null) {
				output.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR_RETRY_SAFE);
				populateErrorMessageDescTextListForSAPMemo(output, errorRetryMessagePropEn,
						arguments, exception);
			} else {
				output.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
				populateErrorMessageDescTextListForSAPMemo(output, errorMessagePropEn,
						arguments, exception);
			}
			
			break;
		case MESSAGE_TYPE_EXCEPTION:
			output.setMessageCode(tempMessageCode);
			output.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_EXCEPTION);
			populateErrorMessageDescTextListForSAPMemo(output, exceptionMessagePropEn,
					arguments, exception);
			break;
		case MESSAGE_TYPE_WARN:
			output.setMessageCode(tempMessageCode);
			output.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_WARNING);
			populateErrorMessageDescTextListForSAPMemo(output, warnMessagePropEn,
					arguments, exception);
			break;
		case MESSAGE_TYPE_INFO:
			output.setMessageCode(tempMessageCode);
			output.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_INFO);
			populateErrorMessageDescTextListForSAPMemo(output, infoMessagePropEn,
					arguments, exception);
			break;
		case MESSAGE_TYPE_ERROR_RETRY_SAFE:
			output.setMessageCode(tempMessageCode);
			if(errorRetryMessagePropEn.getProperty(output.getMessageCode()) != null) {
				output.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR_RETRY_SAFE);
				populateErrorMessageDescTextListForSAPMemo(output, errorRetryMessagePropEn,
						arguments, exception);
			} else {
				output.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR_RETRY_SAFE);
				populateErrorMessageDescTextListForSAPMemo(output, errorMessagePropEn,
						arguments, exception);
			}
			
			break;			
		default:
			break;
		}
		
		return output;
	}
	
	private void populateErrorMessageDescTextListForSAPMemo(MessageDO messageDO,
			Properties messagePropEn,
			String[] arguments, Exception exception) {
		MessageDescDO msgDescDoEn = new MessageDescDO(Locale.CANADA,
				messagePropEn.getProperty(messageDO.getMessageCode()));
		//MessageDescDO msgDescDoFr = new MessageDescDO(Locale.CANADA_FRENCH,
		//		msgDescDoEn.getMessageDescriptionText())
		ArrayList<MessageDescDO> msgDescTextList = new ArrayList<MessageDescDO>();
		
		
		msgDescTextList.add(msgDescDoEn);		
		// msgDescTextList.add(msgDescDoFr)
		if (arguments != null && arguments.length > 0) {
			replaceToken(msgDescTextList, arguments);
		}
		messageDO.setMesssageDescriptionTextList(msgDescTextList);
		messageDO.setContextData(printStackTrace(exception));
	}

	public static void main(String[] args) {
		/*IEnterpriseSalesSvcMessageHelper helper = EnterpriseSalesSvcMessageHelper
				.getInstance();

		System.out.println("For error case....");
		MessageDO errorResponse = helper
				.getMessageDetail(
						EnterpriseWLNSalesServicesConstants.ESS_ERROR_CODE_FEATURE_CONFLICT_FEATURE,
						IEnterpriseSalesSvcMessageHelper.MESSAGE_TYPE_ERROR,
						new String[]{"SXCC10ARS","SXCC5ARS"}, null);
		System.out.println("MessageCode = " + errorResponse.getMessageCode());
		System.out.println("MessageType = " + errorResponse.getMessageType());
		System.out
				.println("Message Text in English = "
						+ errorResponse
								.getLocaleSpecificMessageDescText(Locale.CANADA));
		System.out
				.println("Message Text in French = "
						+ errorResponse
								.getLocaleSpecificMessageDescText(Locale.CANADA_FRENCH));

		System.out.println("\nFor Exception cases...");
		try {
			String str = null;
			// str.toString()
		} catch (NullPointerException npe) {
			MessageDO exceptionResponse = helper
					.getMessageDetail(
							EnterpriseWLNSalesServicesConstants.ESS_ERROR_CODE_UNKNOWN_ERROR,
							IEnterpriseSalesSvcMessageHelper.MESSAGE_TYPE_EXCEPTION,
							null, npe);
			System.out.println("MessageCode = "
					+ exceptionResponse.getMessageCode());
			System.out.println("MessageType = "
					+ exceptionResponse.getMessageType());
			System.out.println("Message Text in English = "
					+ exceptionResponse
							.getLocaleSpecificMessageDescText(Locale.CANADA));
			System.out
					.println("Message Text in French = "
							+ exceptionResponse
									.getLocaleSpecificMessageDescText(Locale.CANADA_FRENCH));
		}

		System.out.println("\nFor Warning cases...");
		MessageDO warnResponse = helper
				.getMessageDetail(
						EnterpriseWLNSalesServicesConstants.ESS_WARN_CODE_BLANK_CALLING_CIRCLE_PHONE_NUMBERS_LIST,
						IEnterpriseSalesSvcMessageHelper.MESSAGE_TYPE_WARN,
						null, null);
		System.out.println("MessageCode = " + warnResponse.getMessageCode());
		System.out.println("MessageType = " + warnResponse.getMessageType());
		System.out.println("Message Text in English = "
				+ warnResponse.getLocaleSpecificMessageDescText(Locale.CANADA));
		System.out
				.println("Message Text in French = "
						+ warnResponse
								.getLocaleSpecificMessageDescText(Locale.CANADA_FRENCH));

		System.out.println("\nFor Info cases...");
		MessageDO infoResponse = helper
				.getMessageDetail(
						EnterpriseWLNSalesServicesConstants.ESS_INFO_CODE_FEATURE_CALLING_CIRCLE_INCLUDED_TELUS,
						IEnterpriseSalesSvcMessageHelper.MESSAGE_TYPE_INFO,
						new String[] { "04/11/2013","04/12/2013" }, null);
		System.out.println("MessageCode = " + infoResponse.getMessageCode());
		System.out.println("MessageType = " + infoResponse.getMessageType());
		System.out.println("Message Text in English = "
				+ infoResponse.getLocaleSpecificMessageDescText(Locale.CANADA));
		System.out
				.println("Message Text in French = "
						+ infoResponse
								.getLocaleSpecificMessageDescText(Locale.CANADA_FRENCH));

		System.out.println("\nFor Error cases with downstream error code...");
		MessageDO msgDo = helper
				.getMessageDetail(
						"SEMS.2000",
						IEnterpriseSalesSvcMessageHelper.MESSAGE_TYPE_ERROR,
						null, null);
		System.out.println("MessageCode = " + msgDo.getMessageCode());
		System.out.println("MessageType = " + msgDo.getMessageType());
		System.out.println("Message Text in English = "
				+ msgDo.getLocaleSpecificMessageDescText(Locale.CANADA));
		System.out
				.println("Message Text in French = "
						+ msgDo
								.getLocaleSpecificMessageDescText(Locale.CANADA_FRENCH));

*/		
	}
}

