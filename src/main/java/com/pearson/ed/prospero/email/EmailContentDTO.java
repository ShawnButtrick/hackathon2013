package com.pearson.ed.prospero.email;

import java.util.ArrayList;

public class EmailContentDTO {
	private String fromAddress = null;
	private String fromName = null;
	private String toAddress = null;
	private String toName = null;
	private String subject = null;
	private String message = null;
	private String attachment = null;

	public String getFromAddress() {
		return this.fromAddress;
	}
		
	public void setFromAddress(String address) {
		this.fromAddress = address;
	}
		
	public String getFromName() {
		return this.fromName;
	}
		
	public void setFromName(String name) {
		this.fromName = name;
	}
		
	public String getToAddress() {
		return this.toAddress;
	}
		
	public void setToAddress(String address) {
		this.toAddress = address;
	}
		
	public String getToName() {
		return this.toName;
	}
		
	public void setToName(String name) {
		this.toName = name;
	}
		
	public String getSubject() {
		return this.subject;
	}
		
	public void setSubject(String sub) {
		this.subject = sub;
	}
		
	public String getMessage() {
		return this.message;
	}
		
	public void setMessage(String msg) {
		this.message = msg;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getAttachment() {
		return attachment;
	}
	
	public boolean hasAttachment() {
		if (attachment != null) {
			return true;
		}
		
		return false;
	}
}