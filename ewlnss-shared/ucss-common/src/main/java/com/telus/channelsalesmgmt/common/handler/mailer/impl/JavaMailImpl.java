package com.telus.channelsalesmgmt.common.handler.mailer.impl;
/**
 * <p>Title: JavaMail Handler Implementation</p>
 * <p>Description: JavaMail Handler Implementation</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: Telus Mobility</p>
 * @author Jason Li
 * @version 1.0
 */
import java.io.File;
import java.util.Calendar;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeBodyPart;

import com.telus.channelsalesmgmt.common.handler.mailer.IMailer;
import com.telus.channelsalesmgmt.common.util.LoggerUtil;

public class JavaMailImpl implements IMailer {
	
	public JavaMailImpl(){
	}
	
	public void sendMessage(String mailHost, String from, String[] recipients, String[] cc, String[] bcc, String subject, String messageInString, String mimeType, String[] attachmentFileNames) throws Exception {
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.host", mailHost);
		Session session = Session.getDefaultInstance(properties, null);
		sendMessage(session, from, recipients, cc, bcc, subject, messageInString, mimeType, attachmentFileNames);
	}
	
	public void sendMessage(Session session, String from, String[] recipients, String[] cc, String[] bcc, String subject, String messageInString, String mimeType, String[] attachmentFileNames) throws Exception {
		String func = "sendMessage";
		LoggerUtil.getLogger(this).enter(func);
		try {
			Message msg = new MimeMessage(session);
			
			if (recipients == null || recipients.length == 0){
				String message = "Failed to send notification: no recipient is specified.";
				throw new RuntimeException (message, null);
			}			
			for ( int i=0; i < recipients.length; i++ ) {
				msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients[i], true));
			}
			
			if (cc != null && cc.length > 0 ) {
				for (int i=0; i < cc.length; i++){
					msg.addRecipients(Message.RecipientType.CC, InternetAddress.parse(cc[i], true));
				}
			}
			
			if (bcc != null && bcc.length > 0 ) {
				for (int i=0; i < bcc.length; i++){
					msg.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc[i], true));
				}
			}
			 				
			msg.setFrom(new InternetAddress(from));
			msg.setSubject(subject);
			
			Multipart multipart = new MimeMultipart();
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(messageInString, mimeType);
			multipart.addBodyPart(messageBodyPart);
			msg.setContent(multipart);
			if (attachmentFileNames != null && attachmentFileNames.length > 0){
				attachFiles(multipart, attachmentFileNames);
			}
			msg.setSentDate(Calendar.getInstance().getTime());
			Transport.send(msg);
		}catch (MessagingException e){
			String message = "Failed to send notification to " + recipients;
			throw new RuntimeException(message, e);
		}
		finally{
			LoggerUtil.getLogger(this).exit(func);
		}
	}
	
	private void attachFiles(Multipart mp, String [] attachmentFileNames) throws Exception {
		for (int i = 0; i<attachmentFileNames.length; i++){
			MimeBodyPart mbp_file = new MimeBodyPart();
			File currentFile = new File(attachmentFileNames[i]);
			if(!currentFile.exists()){
				String message = "Filed at attachment since File '" + attachmentFileNames[i] + "' does not exist";
				throw new RuntimeException(message, null);
			}
			FileDataSource fds = new FileDataSource(attachmentFileNames[i]);
			mbp_file.setDataHandler(new DataHandler(fds));
			mbp_file.setFileName(fds.getName());
		    mp.addBodyPart(mbp_file);        
		}
	}	
}
