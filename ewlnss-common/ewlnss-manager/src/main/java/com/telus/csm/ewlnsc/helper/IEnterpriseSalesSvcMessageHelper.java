package com.telus.csm.ewlnsc.helper;

/**
 * 
 */

import com.telus.csm.ewlnsc.domain.MessageDO;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;

/**
 * @author x131123
 *
 */
public interface IEnterpriseSalesSvcMessageHelper {
	
	public static final int MESSAGE_TYPE_ERROR = 1;
	public static final int MESSAGE_TYPE_WARN = 2;
	public static final int MESSAGE_TYPE_INFO = 3;
	public static final int MESSAGE_TYPE_EXCEPTION = 4;
	public static final int MESSAGE_TYPE_ERROR_RETRY_SAFE = 5;
	
	public static String[][] MESSAGE_MAPPING = {
		{EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR, ""+MESSAGE_TYPE_ERROR},
		{EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_WARNING, ""+MESSAGE_TYPE_WARN},
		{EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_INFO, ""+MESSAGE_TYPE_INFO},
		{EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_EXCEPTION, ""+MESSAGE_TYPE_EXCEPTION},
		{EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR_RETRY_SAFE, ""+MESSAGE_TYPE_ERROR_RETRY_SAFE}
	};
	
	/**
	 * 
	 * @param messageCode - this should be defined in EnterpriseSalesServiceConstants
	 * @param messageType - one of the four message types defined in this interface
	 * @param arguments - any arguments to be concatenated with the message text
	 * @param exception - any system exception thrown
	 * @return MessageDO - the return MessageDO will contain the String messageType and a 
	 * List of MessageDescDO
	 */
	public MessageDO getMessageDetail(String messageCode, int messageType, String[]arguments, Exception exception);
	
	public MessageDO getMessageDetail(String messageCode, String messageType, String[]arguments, Exception exception);
	
}
