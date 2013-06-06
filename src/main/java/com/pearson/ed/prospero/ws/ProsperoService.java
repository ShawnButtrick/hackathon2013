package com.pearson.ed.prospero.ws;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class ProsperoService {

	private RestfulServiceClientImpl restCLient;
	private String serverUrl;

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public void setRestCLient(RestfulServiceClientImpl restCLient) {
		this.restCLient = restCLient;
	}

	public String subscribe(String principal, String keyS,
			String callbackUrlCallback, String mesString, String client,
			String clientString, String system, String subsystem, String tags, String server) {
//		if (StringUtils.isBlank(mesString))
//			mesString = "Publish";
//		if (StringUtils.isBlank(client))
//			client = "RumbaClient";
//		if (StringUtils.isBlank(clientString))
//			clientString = "RumbaClient";
//		if (StringUtils.isBlank(system))
//			system = "Rumba";
//		if (StringUtils.isBlank(subsystem))
//			subsystem = "User";

		restCLient.setServerUrl(server);
		if (StringUtils.isBlank(server))
			restCLient.setServerUrl(serverUrl);
		return restCLient.subscribe(new Date(), principal, keyS, mesString, client, clientString, system, subsystem, callbackUrlCallback , tags);
	}

}
