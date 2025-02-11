package com.telus.channelsalesmgmt.common.handler.mailer;

import javax.mail.Session;

/**
 * <p>Title: EMAIL Handler Interface</p>
 * <p>Description: EMAIL Handler Interface</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: Telus Mobility</p>
 * @author Jason Li
 * @version 1.0
 */
public interface IMailer {

	public abstract void sendMessage(String mailHost, String from, String[] recipients, String[] cc, String[] bcc, String subject, String messageInString, String mimeType, String[] attachmentFileNames) throws Exception;
	
	public abstract void sendMessage(Session session, String from, String[] recipients, String[] cc, String[] bcc, String subject, String messageInString, String mimeType, String[] attachmentFileNames) throws Exception;

}
