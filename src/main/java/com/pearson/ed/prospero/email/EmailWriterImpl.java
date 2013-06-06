package com.pearson.ed.prospero.email;

import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.core.io.ClassPathResource;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailWriterImpl implements EmailWriter {
	private static final Logger logger = Logger.getLogger(EmailWriterImpl.class);
	private JavaMailSender mailSender = null;
	private String ATTACHMENT_REGEX = "\\w+\\.(gif|bmp|jpg|png|swf|js|css|pdf|doc|txt|xml|zip|tar)";
	
	public void setMailSender(JavaMailSender ms) {
		this.mailSender = ms;
	}
	
	public void sendEmail(EmailContentDTO emailContent) throws MailException, Exception {
		boolean sendAsHTML = false;
		boolean useAttachements = false;
		boolean contentAttachements = false;
		Pattern myPattern = null;
		Matcher myMatcher = null;
		ClassPathResource file = null;
		
		// Create a mime-encoded message
		MimeMessage message = mailSender.createMimeMessage();
		
		// Attempt to determine how to encode and send email based on its content
		myPattern = Pattern.compile("<html>", Pattern.CASE_INSENSITIVE);
		myMatcher = myPattern.matcher(emailContent.getMessage());
		if (myMatcher.find()) {
			sendAsHTML = true;

			// Attempt to determine if there are referenced attachments to be included
			myPattern = Pattern.compile(ATTACHMENT_REGEX, Pattern.CASE_INSENSITIVE);
			myMatcher = myPattern.matcher(emailContent.getMessage());
			if (myMatcher.find()) {
				contentAttachements = true;
			}
		}

		// Determine if the email has any attachments to process
		if (emailContent.hasAttachment() || contentAttachements == true) {
			useAttachements = true;
		}
		
		// Use the true flag to indicate you need a multi-part mine encoded message so we can include attachments
		MimeMessageHelper msg = new MimeMessageHelper(message, useAttachements, "UTF-8");
		
		// Set the basic email attributes
		msg.setFrom(emailContent.getFromAddress(), emailContent.getFromName());
		msg.setTo(new InternetAddress(emailContent.getToAddress(), emailContent.getToName()));
		msg.setSubject(emailContent.getSubject());
		msg.setText(emailContent.getMessage(), sendAsHTML);
		

		// Send the email
	    // Handle in-line attachments specified in email content 
	    if (contentAttachements) {
	    	// Loop through email contents to extract and set in-line attachments
			myPattern = Pattern.compile(ATTACHMENT_REGEX, Pattern.CASE_INSENSITIVE);
			myMatcher = myPattern.matcher(emailContent.getMessage());
			while (myMatcher.find()) {
				try {
					file = new ClassPathResource("com/pearson/ed/notification/services/processors/impl/attachments/" + myMatcher.group());
					logger.debug("Content specific attachment");
					logger.debug("Resource Name: " + file.getPath());
					logger.debug("Resource Exists: " + file.exists());
					msg.addInline(myMatcher.group(), file, "image/gif");
					file = null;
				}
				catch (Exception e) {
					logger.debug("NOT FOUND: com/pearson/ed/notification/services/processors/impl/attachments/" + myMatcher.group());
				}
			}
	    }
		
	    // Handle general email level attachments
	    if (emailContent.hasAttachment() == true) {
	    	try {
	    		file = new ClassPathResource("com/pearson/ed/notification/services/processors/impl/attachments/" + emailContent.getAttachment());
	    		logger.debug("Email specific attachment");
	    		logger.debug("Resource Name: " + file.getPath());
	    		logger.debug("Resource Exists: " + file.exists());
	    		msg.addAttachment(emailContent.getAttachment(), file);
	    	}
	    	catch (Exception e) {
	    		logger.debug("NOT FOUND: com/pearson/ed/notification/services/processors/impl/attachments/" + emailContent.getAttachment());
	    	}
	    }
	    
	    this.mailSender.send(msg.getMimeMessage());
		//this.mailSender. send(message);
	}
}
