package com.pearson.ed.prospero.forms;

import org.hibernate.validator.constraints.NotEmpty;

public class SendBean 
{
	
	@NotEmpty
	private String clientId;
	@NotEmpty
	private String clientString;

	@NotEmpty
	private String system;
	@NotEmpty
	private String subSystem;

	@NotEmpty
	private String messageType;
	@NotEmpty
	private String payloadContentType;
	@NotEmpty
	private String payloadContent;
	@NotEmpty
	private String tags;

	@NotEmpty
	private String realm;
	@NotEmpty
	private String principal;
	@NotEmpty
	private String key;
	
	@NotEmpty
	private String enviro;
	
	private String inquiryDetails;
	

	
	public String getSystem() { return system; }
	public void setSystem(String system) { this.system = system; }

	public String getSubSystem() { return subSystem; }
	public void setSubSystem(String subSystem) { this.subSystem = subSystem; }
	
	public String getClientId() { return clientId; }
	public void setClientId(String clientId) { this.clientId = clientId; }

	public String getClientString() { return clientString; }
	public void setClientString(String clientString) { this.clientString = clientString; }

	public String getTags() { return tags; }
	public void setTags(String tags) { this.tags = tags; }

	public String getRealm() { return realm; }
	public void setRealm(String realm) { this.realm = realm; }

	public String getPrincipal() { return principal; }
	public void setPrincipal(String principal) { this.principal = principal; }
	
	public String getMessageType() { return messageType; }
	public void setMessageType(String messageType) { this.messageType = messageType; }

	public String getPayloadContentType() { return payloadContentType; }
	public void setPayloadContentType(String payloadContentType) { this.payloadContentType = payloadContentType; }

	public String getPayloadContent() { return payloadContent; }
	public void setPayloadContent(String payloadContent) { this.payloadContent = payloadContent; }
	
	public String getInquiryDetails() { return inquiryDetails; }
	public void setInquiryDetails(String inquiryDetails) { this.inquiryDetails = inquiryDetails; }

	public String getKey() { return key; }
	public void setKey(String key) { this.key = key; }

	public String getEnviro() { return enviro; }
	public void setEnviro(String enviro) { this.enviro = enviro; }

	
	
	
	public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("properties");
        
        sb.append("clientId=");
        if (clientId != null) {
        	sb.append("'").append(clientId).append("', ");
        } else {
        	sb.append(clientId).append(", ");
        }
        
        sb.append("clientString=");
        if (clientString != null) {
        	sb.append("'").append(clientString).append("', ");
        } else {
        	sb.append(clientString).append(", ");
        }
        
        sb.append("system=");
        if (system != null) {
        	sb.append("'").append(system).append("', ");
        } else {
        	sb.append(system).append(", ");
        }
        
        sb.append("subSystem=");
        if (subSystem != null) {
        	sb.append("'").append(subSystem).append("', ");
        } else {
        	sb.append(subSystem).append(", ");
        }
        
        sb.append("messageType=");
        if (messageType != null) {
        	sb.append("'").append(messageType).append("', ");
        } else {
        	sb.append(messageType).append(", ");
        }
        
        sb.append("payloadContentType=");
        if (payloadContentType != null) {
        	sb.append("'").append(payloadContentType).append("', ");
        } else {
        	sb.append(payloadContentType).append(", ");
        }

        sb.append("payloadContent=");
        if (payloadContent != null) {
        	sb.append("'").append(payloadContent).append("', ");
        } else {
        	sb.append(payloadContent).append(", ");
        }
        
        sb.append("tags=");
        if (tags != null) {
        	sb.append("'").append(tags).append("', ");
        } else {
        	sb.append(tags).append(", ");
        }
        
        sb.append("realm=");
        if (realm != null) {
        	sb.append("'").append(realm).append("', ");
        } else {
        	sb.append(realm).append(", ");
        }
        
        sb.append("principal=");
        if (principal != null) {
        	sb.append("'").append(principal).append("', ");
        } else {
        	sb.append(principal).append(", ");
        }
        
        sb.append("key=");
        if (key != null) {
        	sb.append("'").append(key).append("', ");
        } else {
        	sb.append(key).append(", ");
        }
        
        sb.append("enviro=");
        if (enviro != null) {
        	sb.append("'").append(enviro).append("', ");
        } else {
        	sb.append(enviro).append(", ");
        }
        
        
        sb.append("inquiryDetails=");
        if (inquiryDetails != null) {
        	sb.append("'").append(inquiryDetails).append("', ");
        } else {
        	sb.append(inquiryDetails).append(", ");
        }
        
        return sb.toString();
    }
}
