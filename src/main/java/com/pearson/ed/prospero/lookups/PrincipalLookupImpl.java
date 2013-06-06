package com.pearson.ed.prospero.lookups;

import com.pearson.ed.prospero.exceptions.EnviroException;

public class PrincipalLookupImpl implements PrincipalLookup 
{
	
	static private String devEnviroNickname;
	static private String testEnviroNickname;
	static private String intEnviroNickname;
	static private String ppeEnviroNickname;
	static private String prodEnviroNickname;
	
	static private String devEnviroPrincipal;
	static private String testEnviroPrincipal;
	static private String intEnviroPrincipal;
	static private String ppeEnviroPrincipal;
	static private String prodEnviroPrincipal;
	
	public static String getDevEnviroPrincipal() { return devEnviroPrincipal;	}
	public static void setDevEnviroPrincipal(String devEnviroPrincipal) { PrincipalLookupImpl.devEnviroPrincipal = devEnviroPrincipal; }

	public static String getTestEnviroPrincipal() { return testEnviroPrincipal; }
	public static void setTestEnviroPrincipal(String testEnviroPrincipal) { PrincipalLookupImpl.testEnviroPrincipal = testEnviroPrincipal; }
	
	public static String getIntEnviroPrincipal() { return intEnviroPrincipal; }
	public static void setIntEnviroPrincipal(String intEnviroPrincipal) { PrincipalLookupImpl.intEnviroPrincipal = intEnviroPrincipal; }
	
	public static String getPpeEnviroPrincipal() { return ppeEnviroPrincipal; }
	public static void setPpeEnviroPrincipal(String ppeEnviroPrincipal) { PrincipalLookupImpl.ppeEnviroPrincipal = ppeEnviroPrincipal; }
	
	public static String getProdEnviroPrincipal() { return prodEnviroPrincipal; }
	public static void setProdEnviroPrincipal(String prodEnviroPrincipal) { PrincipalLookupImpl.prodEnviroPrincipal = prodEnviroPrincipal; }

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


	@Override
	public String getPrinipal(String enviroNameString) 
	{
		String returnString = null;
		
		if(enviroNameString == null)
			throw new EnviroException();
		
		enviroNameString = enviroNameString.toLowerCase();
		
		if(enviroNameString.equals(devEnviroNickname)) {
			returnString = devEnviroPrincipal;
		}
		else if(enviroNameString.equals(testEnviroNickname)) {
			returnString = testEnviroPrincipal;
		}
		else if(enviroNameString.equals(intEnviroNickname)) {
			returnString = intEnviroPrincipal;
		}
		else if(enviroNameString.equals(ppeEnviroNickname)) {
			returnString = ppeEnviroPrincipal;
		}
		else if(enviroNameString.equals(prodEnviroNickname)) {
			returnString = prodEnviroPrincipal;
		}
		else {
			throw new EnviroException();
		}
		
		return returnString;
	}

}
