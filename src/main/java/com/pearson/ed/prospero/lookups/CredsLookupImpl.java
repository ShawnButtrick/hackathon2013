package com.pearson.ed.prospero.lookups;

import com.pearson.ed.prospero.exceptions.EnviroException;
import com.pearson.ed.prospero.exceptions.EnviroSendPathException;

public class CredsLookupImpl implements CredsLookup
{
	static private String devEnviroNickname;
	static private String testEnviroNickname;
	static private String intEnviroNickname;
	static private String ppeEnviroNickname;
	static private String prodEnviroNickname;
	
	static private String devEnviroCred;
	static private String testEnviroCred;
	static private String intEnviroCred;
	static private String ppeEnviroCred;
	static private String prodEnviroCred;
	
	
	
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
	

	public static String getDevEnviroCred() { return devEnviroCred; }
	public static void setDevEnviroCred(String devEnviroCred) { CredsLookupImpl.devEnviroCred = devEnviroCred; }
	public static String getTestEnviroCred() { return testEnviroCred; }
	public static void setTestEnviroCred(String testEnviroCred) { CredsLookupImpl.testEnviroCred = testEnviroCred; }
	public static String getIntEnviroCred() { return intEnviroCred; }
	public static void setIntEnviroCred(String intEnviroCred) { CredsLookupImpl.intEnviroCred = intEnviroCred; }
	public static String getPpeEnviroCred() { return ppeEnviroCred; }
	public static void setPpeEnviroCred(String ppeEnviroCred) { CredsLookupImpl.ppeEnviroCred = ppeEnviroCred; }
	public static String getProdEnviroCred() { return prodEnviroCred; }
	public static void setProdEnviroCred(String prodEnviroCred) { CredsLookupImpl.prodEnviroCred = prodEnviroCred; }
	
	
	@Override
	public String getCreds(String enviroNameString) 
	{
		String returnString = null;
		
		if(enviroNameString == null) {
			throw new EnviroException("Enviro was null");
		}
		
		if(enviroNameString.equals(devEnviroNickname)) {
			returnString = devEnviroCred;
		}
		else if(enviroNameString.equals(testEnviroNickname)) {
			returnString = testEnviroCred;
		}
		else if(enviroNameString.equals(intEnviroNickname)) {
			returnString = intEnviroCred;
		}
		else if(enviroNameString.equals(ppeEnviroNickname)) {
			returnString = ppeEnviroCred;
		}
		else if(enviroNameString.equals(prodEnviroNickname)) {
			returnString = prodEnviroCred;
		}
		else {
			throw new EnviroSendPathException("Enviro was invalid");
		}
		
		
		return returnString;
	}

	
}
