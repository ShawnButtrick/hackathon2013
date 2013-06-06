package com.pearson.ed.prospero.model.v1.subscriptions;

import com.pearson.ed.eventing.model.SubscriptionRequest;

public class ProsperoCheckerSubscriptionRequest extends SubscriptionRequest 
{
	String prosperoBaseUrl;
	String subscribePath;
	String sendPath;
	
	
	public String getProsperoBaseUrl() {
		return prosperoBaseUrl;
	}
	public void setProsperoBaseUrl(String prosperoBaseUrl) {
		this.prosperoBaseUrl = prosperoBaseUrl;
	}
	public String getSendPath() {
		return sendPath;
	}
	public void setSendPath(String sendPath) {
		this.sendPath = sendPath;
	}
	public String getSubscribePath() {
		return subscribePath;
	}
	public void setSubscribePath(String subscribePath) {
		this.subscribePath = subscribePath;
	}
	
}
