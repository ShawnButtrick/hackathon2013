package com.pearson.ed.prospero.lookups;

public interface EnviroLookup 
{
	public String getEnviroUrl(String enviroNameString);
	public String getEnviroSendPath(String enviroNameString);
	public String getEnviroSubscribePath(String enviroNameString);
}
