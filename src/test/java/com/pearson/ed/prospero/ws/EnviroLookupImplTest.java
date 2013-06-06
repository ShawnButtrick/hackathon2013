package com.pearson.ed.prospero.ws;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pearson.ed.prospero.exceptions.EnviroException;
import com.pearson.ed.prospero.lookups.EnviroLookupImpl;


public class EnviroLookupImplTest 
{

	static EnviroLookupImpl enviroLookup; 
	
	static String devNickname = "dev";
	static 	String devUrl = "devUrl";
	
	static String testNickname = "test";
	static String testUrl = "testUrl";
	
	static String intNickname = "int";
	static String intUrl = "intUrl";
	
	static String ppeNickname = "ppe";
	static String ppeUrl = "ppeUrl";
	
	static String prodNickname = "prod";
	static String prodUrl = "prodUrl";
			
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		 enviroLookup = new EnviroLookupImpl();	
		 
		 EnviroLookupImpl.setDevEnviroNickname(devNickname);
		 EnviroLookupImpl.setTestEnviroNickname(testNickname);
		 EnviroLookupImpl.setIntEnviroNickname(intNickname);
		 EnviroLookupImpl.setPpeEnviroNickname(ppeNickname);
		 EnviroLookupImpl.setProdEnviroNickname(prodNickname);
		 
		 EnviroLookupImpl.setDevEnviroUrl(devUrl);
		 EnviroLookupImpl.setTestEnviroUrl(testUrl);
		 EnviroLookupImpl.setIntEnviroUrl(intUrl);
		 EnviroLookupImpl.setPpeEnviroUrl(ppeUrl);
		 EnviroLookupImpl.setProdEnviroUrl(prodUrl);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception { }

	@Before
	public void setUp() throws Exception { }

	@After
	public void tearDown() throws Exception { }

	
	
	@Test
	public void testGetEnviroUrl1() 
	{
		String response = enviroLookup.getEnviroUrl(devNickname);
		
		assertTrue("Bad mapping", devUrl.equals(response));
	}
	
	@Test
	public void testGetEnviroUrl2() 
	{
		String response = enviroLookup.getEnviroUrl(testNickname);
		
		assertTrue("Bad mapping", testUrl.equals(response));
	}
	
	@Test
	public void testGetEnviroUrl3() 
	{
		String response = enviroLookup.getEnviroUrl(intNickname);
		
		assertTrue("Bad mapping", intUrl.equals(response));
	}
	
	@Test
	public void testGetEnviroUrl4() 
	{
		String response = enviroLookup.getEnviroUrl(ppeNickname);
		
		assertTrue("Bad mapping", ppeUrl.equals(response));
	}
	
	@Test
	public void testGetEnviroUrl5() 
	{
		String response = enviroLookup.getEnviroUrl(prodNickname);
		
		assertTrue("Bad mapping", prodUrl.equals(response));
	}
	
	@Test(expected = EnviroException.class)
	public void testGetEnviroUrl_Null() 
	{
		enviroLookup.getEnviroUrl(null);
	}
	
	@Test(expected = EnviroException.class)
	public void testGetEnviroUrl_Junk() 
	{
		enviroLookup.getEnviroUrl("Car Talk, Saturdays at 11am on NPR");
	}
}
