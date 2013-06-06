package com.pearson.ed.prospero.model.v1.subscriptions;

import java.util.List;

public class Subscription 
{
	private String id;
	private String principal_id;
	private String callback_url;
	private String wsdl_uri;
	private String queue_name;
	private String date_created;
	private String date_cancelled;
	private List<AnonTag> tags;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPrincipal_id() {
		return principal_id;
	}
	public void setPrincipal_id(String principal_id) {
		this.principal_id = principal_id;
	}
	public String getCallback_url() {
		return callback_url;
	}
	public void setCallback_url(String callback_url) {
		this.callback_url = callback_url;
	}
	public String getWsdl_uri() {
		return wsdl_uri;
	}
	public void setWsdl_uri(String wsdl_uri) {
		this.wsdl_uri = wsdl_uri;
	}
	public String getQueue_name() {
		return queue_name;
	}
	public void setQueue_name(String queue_name) {
		this.queue_name = queue_name;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public String getDate_cancelled() {
		return date_cancelled;
	}
	public void setDate_cancelled(String date_cancelled) {
		this.date_cancelled = date_cancelled;
	}
	public List<AnonTag> getTags() {
		return tags;
	}
	public void setTags(List<AnonTag> tags) {
		this.tags = tags;
	}
	
	
}
