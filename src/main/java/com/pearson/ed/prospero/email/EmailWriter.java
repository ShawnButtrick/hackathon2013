package com.pearson.ed.prospero.email;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;

public interface EmailWriter {
	public void setMailSender(JavaMailSender ms);
	public void sendEmail(EmailContentDTO emailContent) throws MailException, Exception;
}