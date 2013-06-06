package com.pearson.ed.prospero.model.v1.summary;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProsperoStatus 
{
	private Status status;
	private String enviro;
	private String error;

	public String getEnviro() {
		return enviro;
	}

	public void setEnviro(String enviro) {
		this.enviro = enviro;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
