package com.pearson.ed.prospero.model.v1.subscriptions;

import java.util.List;

public class Subscriptions 
{
	private List<AnonSubscription> anonSubscriptionsList;
	
	private String errorString;
	
	

	public String getErrorString() {
		return errorString;
	}

	public void setErrorString(String errorString) {
		this.errorString = errorString;
	}

	public List<AnonSubscription> getAnonSubscriptionsList() {
		return anonSubscriptionsList;
	}

	public void setAnonSubscriptionsList(
			List<AnonSubscription> anonSubscriptionsList) {
		this.anonSubscriptionsList = anonSubscriptionsList;
	}
	
	
}
