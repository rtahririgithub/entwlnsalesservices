package com.telus.channelsalesmgmt.common.aspect.alert;

import javax.mail.Session;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import com.telus.channelsalesmgmt.common.aspect.alert.ExceptionAlertManager;
import com.telus.channelsalesmgmt.common.handler.mailer.IMailer;
import com.telus.channelsalesmgmt.common.handler.mailer.impl.JavaMailImpl;
import com.telus.channelsalesmgmt.common.util.LoggerUtil;
import com.telus.channelsalesmgmt.common.util.UcssXStream;
//import com.telus.tmi.xmlschema.xsd.common.exceptions.exceptions_v1_0.PolicyException;
//import com.telus.tmi.xmlschema.xsd.common.exceptions.exceptions_v1_0.ServiceException;
import commonj.work.WorkManager;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiceExceptionAlertManager.
 */
public abstract class ServiceExceptionAlertManager implements ExceptionAlertManager {
	
	/** The Constant defaultWMName_OnlinePriority. */
	static final String defaultWMName_OnlinePriority = "WorkManager-Online-Priority";
	
	/** The logger. */
	static Logger logger = Logger.getLogger( ServiceExceptionAlertManager.class );
	
	/** The recipients. */
	protected String[] recipients = null;
	
	/** The mail session name. */
	protected String mailSessionName = null;
	
	/** The mail heading. */
	protected String mailHeading = null;
	
	/** The is disabled. */
	protected boolean isDisabled = false;
	
	/**
	 * Sets the disabled.
	 *
	 * @param isDisabled the new disabled
	 */
	public void setDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	/**
	 * Sets the recipients.
	 *
	 * @param recipients the new recipients
	 */
	public void setRecipients(String[] recipients) {
		this.recipients = recipients;
	}

	/**
	 * Instantiates a new service exception alert manager.
	 *
	 * @param recipients the recipients
	 * @param mailSessionName the mail session name
	 * @param mailHeading the mail heading
	 */
	public ServiceExceptionAlertManager(String[] recipients, String mailSessionName, String mailHeading) {
		this.recipients = recipients;
		this.mailSessionName = mailSessionName;
		this.mailHeading = mailHeading;
	}
	
	/* (non-Javadoc)
	 * @see com.telus.channelsalesmgmt.common.aspect.alert.ExceptionAlertManager#notifyException(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Object)
	 */
	public void notifyException(Object target, String methodName, Object[] actualParameters, Object t) throws Exception {
		if( isDisabled ) return;
		
		try {
			InitialContext ic = new InitialContext();
			WorkManager wm = (WorkManager)ic.lookup("java:comp/env/" + defaultWMName_OnlinePriority);
			String className = target.getClass().getName();
			int idx = className.lastIndexOf(".");
			if( idx != -1 ) {
				className = className.substring(idx + 1, className.length());
			}
			
			StringBuffer messageTitle = new StringBuffer("<").append(mailHeading).append("> ");
			StringBuffer messageBody = new StringBuffer()
				.append("Class Name = ").append(target.getClass().getName()).append("\r\n\r\n")				
				.append("Method Name = ").append(methodName).append("\r\n\r\n")
				.append("Parameters = ");
			if( actualParameters == null || actualParameters.length < 1 ) {
				messageBody.append("<null>");
			} else {
				for( int i = 0; i < actualParameters.length; i++ ) {
					messageBody.append("\r\n")
						.append("   [").append(i).append("] = ").append(UcssXStream.toString(actualParameters[i]));
				}
			}
			messageBody.append("\r\n\r\n");
			if( t != null ) {
				messageBody.append("Exception = ").append("\r\n");
//				if( t instanceof PolicyException ) {
//	    			PolicyException pEx = (PolicyException)t;
//	    			messageTitle.append("pEx [").append(pEx.getErrorCode()).append("]");
//	    			
//	    			messageBody.append("*** PolicyException\r\n");
//	    			messageBody.append("***   messageId = " + pEx.getMessageId()).append("\r\n");
//	    			messageBody.append("***   errorCode = " + pEx.getErrorCode()).append("\r\n");
//	    			messageBody.append("***   errorMessage = " + pEx.getErrorMessage()).append("\r\n");
//	    			messageBody.append("***   message = " + pEx.getMessage()).append("\r\n");
//	    			if( pEx.getVariables() != null ) {
//	    				for( int i = 0; i < pEx.getVariables().length; i++ ) {
//	    					messageBody.append("***   more = " + pEx.getVariables()[i]).append("\r\n");
//	    				}
//	    			}
//	    		} else if( t instanceof ServiceException ) {
//	    			ServiceException pEx = (ServiceException)t;
//	    			messageTitle.append("sEx [").append(pEx.getErrorCode()).append("]");
//	    			
//	    			messageBody.append("*** ServiceException\r\n");
//	    			messageBody.append("***   messageId = " + pEx.getMessageId()).append("\r\n");
//	    			messageBody.append("***   errorCode = " + pEx.getErrorCode()).append("\r\n");
//	    			messageBody.append("***   errorMessage = " + pEx.getErrorMessage()).append("\r\n");
//	    			messageBody.append("***   message = " + pEx.getMessage()).append("\r\n");
//	    			if( pEx.getVariables() != null ) {
//	    				for( int i = 0; i < pEx.getVariables().length; i++ ) {
//	    					messageBody.append("***   more = " + pEx.getVariables()[i]).append("\r\n");
//	    				}
//	    			}
//	    		} else if( t instanceof Throwable ) {
	    		if( t instanceof Throwable ) {
	    			Throwable ex = (Throwable)t;
	    			messageTitle.append("Ex");
	    			
	    			messageBody.append("*** Exception\r\n");
	    			messageBody.append("***   message = ").append(ex.getMessage()).append("\r\n");
	    		} else {
	    			messageTitle.append("Error");
	    			
	    			messageBody.append("***   message = ").append(t.toString()).append("\r\n");
	    		}
				
				if( t instanceof Throwable ) {
					Throwable ex = (Throwable)t;
					messageBody.append("******   stack trace\r\n" + LoggerUtil.getStackTrace(ex));
	    		}
			}
			
			messageTitle.append(" - ").append(className);
			
			wm.schedule(new ServiceExceptionAlertMailTask(messageTitle.toString(), messageBody.toString()));
		} catch (Exception ex) {
			logger.error("Exception caught when sending exception alert email.", ex);
		}
	}
	
	/**
	 * The Class ServiceExceptionAlertMailTask.
	 */
	class ServiceExceptionAlertMailTask implements commonj.work.Work {
		
		/** The message title. */
		private String messageTitle;
		
		/** The message in string. */
		private String messageInString;
		
		/**
		 * Instantiates a new service exception alert mail task.
		 *
		 * @param messageTitle the message title
		 * @param messageInString the message in string
		 */
		public ServiceExceptionAlertMailTask(String messageTitle, String messageInString) {
			this.messageTitle = messageTitle;
			this.messageInString = messageInString;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
			try {
				InitialContext ic = new InitialContext();
				Session mailSession = (Session) ic.lookup(mailSessionName);
				IMailer mailer = new JavaMailImpl();
				mailer.sendMessage(
						mailSession, 
						"no.reply@telus.com", 
						recipients, 
						null, 
						null, 
						messageTitle, 
						messageInString, 
						"text/plain", 
						null);
			} catch (Exception ex) {			
				logger.error("Exception caught when sending exception alert email.", ex);
			}
		}
	
		/* (non-Javadoc)
		 * @see commonj.work.Work#isDaemon()
		 */
		public boolean isDaemon() {
			return false;
		}
	
		/* (non-Javadoc)
		 * @see commonj.work.Work#release()
		 */
		public void release() {
		}
	}
}
