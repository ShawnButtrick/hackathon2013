package com.pearson.ed.prospero.lookups;


import com.pearson.ed.prospero.exceptions.EnviroException;
import com.pearson.ed.prospero.exceptions.EnviroSendPathException;
import com.pearson.ed.prospero.exceptions.EnviroSubscribePathException;

public class EnviroLookupImpl implements EnviroLookup
{
	static private String devEnviroNickname;
	static private String testEnviroNickname;
	static private String intEnviroNickname;
	static private String ppeEnviroNickname;
	static private String prodEnviroNickname;
	
	static private String devEnviroUrl;
	static private String testEnviroUrl;
	static private String intEnviroUrl;
	static private String ppeEnviroUrl;
	static private String prodEnviroUrl;
	
	static private String devEnviroSendPath;
	static private String testEnviroSendPath;
	static private String intEnviroSendPath;
	static private String ppeEnviroSendPath;
	static private String prodEnviroSendPath;
	
	static private String devEnviroSubscribePath;
	static private String testEnviroSubscribePath;
	static private String intEnviroSubscribePath;
	static private String ppeEnviroSubscribePath;
	static private String prodEnviroSubscribePath;
	
	
	
	static public String getDevEnviroNickname() { return devEnviroNickname; }
	static public void setDevEnviroNickname(String inDevEnviroNickname) { devEnviroNickname = inDevEnviroNickname; }
	static public String getTestEnviroNickname() { return testEnviroNickname; }
	static public void setTestEnviroNickname(String inTestEnviroNickname) { testEnviroNickname = inTestEnviroNickname; }
	static public String getIntEnviroNickname() { return intEnviroNickname; }
	static public void setIntEnviroNickname(String inIntEnviroNickname) { intEnviroNickname = inIntEnviroNickname; }
	static public String getPpeEnviroNickname() { return ppeEnviroNickname; }
	static public void setPpeEnviroNickname(String inPpeEnviroNickname) { ppeEnviroNickname = inPpeEnviroNickname; }
	static public String getProdEnviroNickname() { return prodEnviroNickname; }
	static public void setProdEnviroNickname(String inProdEnviroNickname) { prodEnviroNickname = inProdEnviroNickname; }

	static public String getDevEnviroUrl() { return devEnviroUrl; }
	static public void setDevEnviroUrl(String inDevEnviroString) { devEnviroUrl = inDevEnviroString; }
	static public String getTestEnviroUrl() { return testEnviroUrl; }
	static public void setTestEnviroUrl(String inTestEnviroString) { testEnviroUrl = inTestEnviroString; }
	static public String getIntEnviroUrl() { return intEnviroUrl; }
	static public void setIntEnviroUrl(String inIntEnviroString) { intEnviroUrl = inIntEnviroString; }
	static public String getPpeEnviroUrl() { return ppeEnviroUrl; }
	static public void setPpeEnviroUrl(String inPpeEnviroString) { ppeEnviroUrl = inPpeEnviroString; }
	static public String getProdEnviroUrl() { return prodEnviroUrl; }
	static public void setProdEnviroUrl(String inProdEnviroString) { prodEnviroUrl = inProdEnviroString; }

	
	static public String getDevEnviroSendPath() { return devEnviroSendPath; }
	static public void setDevEnviroSendPath(String devEnviroSendPath) { EnviroLookupImpl.devEnviroSendPath = devEnviroSendPath; }
	static public String getTestEnviroSendPath() { return testEnviroSendPath; }
	static public void setTestEnviroSendPath(String testEnviroSendPath) { EnviroLookupImpl.testEnviroSendPath = testEnviroSendPath; }
	static public String getIntEnviroSendPath() { return intEnviroSendPath; }
	static public void setIntEnviroSendPath(String intEnviroSendPath) { EnviroLookupImpl.intEnviroSendPath = intEnviroSendPath; }
	static public String getPpeEnviroSendPath() { return ppeEnviroSendPath; }
	static public void setPpeEnviroSendPath(String ppeEnviroSendPath) { EnviroLookupImpl.ppeEnviroSendPath = ppeEnviroSendPath; }
	static public String getProdEnviroSendPath() { return prodEnviroSendPath; }
	static public void setProdEnviroSendPath(String prodEnviroSendPath) { EnviroLookupImpl.prodEnviroSendPath = prodEnviroSendPath; }
	
	static public String getDevEnviroSubscribePath() { return devEnviroSubscribePath; }
	static public void setDevEnviroSubscribePath(String devEnviroSubscribePath) { EnviroLookupImpl.devEnviroSubscribePath = devEnviroSubscribePath; }
	static public String getTestEnviroSubscribePath() { return testEnviroSubscribePath; }
	static public void setTestEnviroSubscribePath(String testEnviroSubscribePath) { EnviroLookupImpl.testEnviroSubscribePath = testEnviroSubscribePath; }
	static public String getIntEnviroSubscribePath() { return intEnviroSubscribePath; }
	static public void setIntEnviroSubscribePath(String intEnviroSubscribePath) { EnviroLookupImpl.intEnviroSubscribePath = intEnviroSubscribePath; }
	static public String getPpeEnviroSubscribePath() { return ppeEnviroSubscribePath; }
	static public void setPpeEnviroSubscribePath(String ppeEnviroSubscribePath) { EnviroLookupImpl.ppeEnviroSubscribePath = ppeEnviroSubscribePath; }
	static public String getProdEnviroSubscribePath() { return prodEnviroSubscribePath; }
	static public void setProdEnviroSubscribePath(String prodEnviroSubscribePath) {  EnviroLookupImpl.prodEnviroSubscribePath = prodEnviroSubscribePath; }
	
	
	
	
	@Override
	public String getEnviroUrl(String enviroNameString) 
	{
		String returnString = null;
		
		if(enviroNameString == null) {
			throw new EnviroException("Enviro was null");
		}
		
		enviroNameString = enviroNameString.toLowerCase();
		
		if(enviroNameString.equals(devEnviroNickname)) {
			returnString = devEnviroUrl;
		}
		else if(enviroNameString.equals(testEnviroNickname)) {
			returnString = testEnviroUrl;
		}
		else if(enviroNameString.equals(intEnviroNickname)) {
			returnString = intEnviroUrl;
		}
		else if(enviroNameString.equals(ppeEnviroNickname)) {
			returnString = ppeEnviroUrl;
		}
		else if(enviroNameString.equals(prodEnviroNickname)) {
			returnString = prodEnviroUrl;
		}
		else {
			throw new EnviroException("Enviro was invalid");
		}
		
		return returnString;
	}
	
	
	@Override
	public String getEnviroSendPath(String enviroNameString) 
	{
		String returnString = null;
		
		if(enviroNameString == null) {
			throw new EnviroSendPathException("Enviro was null");
		}
		
		enviroNameString = enviroNameString.toLowerCase();
		
		if(enviroNameString.equals(devEnviroNickname)) {
			returnString = devEnviroSendPath;
		}
		else if(enviroNameString.equals(testEnviroNickname)) {
			returnString = testEnviroSendPath;
		}
		else if(enviroNameString.equals(intEnviroNickname)) {
			returnString = intEnviroSendPath;
		}
		else if(enviroNameString.equals(ppeEnviroNickname)) {
			returnString = ppeEnviroSendPath;
		}
		else if(enviroNameString.equals(prodEnviroNickname)) {
			returnString = prodEnviroSendPath;
		}
		else {
			throw new EnviroSendPathException("Enviro was invalid");
		}
		
		return returnString;
	}
	
	@Override
	public String getEnviroSubscribePath(String enviroNameString) 
	{
		String returnString = null;
		
		if(enviroNameString == null) {
			throw new EnviroSubscribePathException("Enviro was null");
		}
		
		enviroNameString = enviroNameString.toLowerCase();
		
		if(enviroNameString.equals(devEnviroNickname)) {
			returnString = devEnviroSubscribePath;
		}
		else if(enviroNameString.equals(testEnviroNickname)) {
			returnString = testEnviroSubscribePath;
		}
		else if(enviroNameString.equals(intEnviroNickname)) {
			returnString = intEnviroSubscribePath;
		}
		else if(enviroNameString.equals(ppeEnviroNickname)) {
			returnString = ppeEnviroSubscribePath;
		}
		else if(enviroNameString.equals(prodEnviroNickname)) {
			returnString = prodEnviroSubscribePath;
		}
		else {
			throw new EnviroException("Enviro was invalid");
		}
		
		return returnString;
	}

}
