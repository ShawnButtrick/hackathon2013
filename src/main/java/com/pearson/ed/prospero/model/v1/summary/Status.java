package com.pearson.ed.prospero.model.v1.summary;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Status {

	private String served_by;
	private String current_time;
	private List<AnonNode> up_nodes;
	
	public List<AnonNode> getUp_nodes() {
		return up_nodes;
	}
	public void setUp_nodes(List<AnonNode> up_nodes) {
		this.up_nodes = up_nodes;
	}
	
	public String getCurrent_time() {
		return current_time;
	}
	public void setCurrent_time(String current_time) {
		this.current_time = current_time;
	}

	public String getServed_by() {
		return served_by;
	}
	public void setServed_by(String served_by) {
		this.served_by = served_by;
	}
}
