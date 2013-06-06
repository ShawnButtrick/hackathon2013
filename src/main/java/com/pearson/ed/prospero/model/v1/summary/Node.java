package com.pearson.ed.prospero.model.v1.summary;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Node 
{
	private String name;
	private String version;
	private String start_time;
	private String uptime_seconds;
	private String num_messages_posted;
	private String num_messages_delivered;
	private String num_failed_deliveries;
	private String num_watched_subscriptions;
	private String rest_status;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getUptime_seconds() {
		return uptime_seconds;
	}
	public void setUptime_seconds(String uptime_seconds) {
		this.uptime_seconds = uptime_seconds;
	}
	
	public String getNum_messages_posted() {
		return num_messages_posted;
	}
	public void setNum_messages_posted(String num_messages_posted) {
		this.num_messages_posted = num_messages_posted;
	}
	
	public String getNum_messages_delivered() {
		return num_messages_delivered;
	}
	public void setNum_messages_delivered(String num_messages_delivered) {
		this.num_messages_delivered = num_messages_delivered;
	}
	
	public String getNum_failed_deliveries() {
		return num_failed_deliveries;
	}
	public void setNum_failed_deliveries(String num_failed_deliveries) {
		this.num_failed_deliveries = num_failed_deliveries;
	}
	
	public String getNum_watched_subscriptions() {
		return num_watched_subscriptions;
	}
	public void setNum_watched_subscriptions(String num_watched_subscriptions) {
		this.num_watched_subscriptions = num_watched_subscriptions;
	}
	
	public String getRest_status() {
		return rest_status;
	}
	public void setRest_status(String rest_status) {
		this.rest_status = rest_status;
	}
	
}
