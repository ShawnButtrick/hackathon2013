package com.pearson.ed.prospero.ws;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pearson.ed.prospero.exceptions.EnviroException;
import com.pearson.ed.prospero.lookups.EnviroLookupImpl;
import com.pearson.ed.prospero.lookups.PrincipalLookupImpl;

public class PrincipalLookupImplTest 
{

	static PrincipalLookupImpl principalLookup;
	
	static String devNickname = "dev";
	static 	String devPrincipal = "devPrincipal";
	
	static String testNickname = "test";
	static String testPrincipal = "testPrincipal";
	
	static String intNickname = "int";
	static String intPrincipal = "intPrincipal";
	
	static String ppeNickname = "ppe";
	static String ppePrincipal = "ppePrincipal";
	
	static String prodNickname = "prod";
	static String prodPrincipal = "prodPrincipal";

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{ 
		principalLookup = new PrincipalLookupImpl();	
		 
		PrincipalLookupImpl.setDevEnviroNickname(devNickname);
		PrincipalLookupImpl.setTestEnviroNickname(testNickname);
		PrincipalLookupImpl.setIntEnviroNickname(intNickname);
		PrincipalLookupImpl.setPpeEnviroNickname(ppeNickname);
		PrincipalLookupImpl.setProdEnviroNickname(prodNickname);
		 
		PrincipalLookupImpl.setDevEnviroPrincipal(devPrincipal);
		PrincipalLookupImpl.setTestEnviroPrincipal(testPrincipal);
		PrincipalLookupImpl.setIntEnviroPrincipal(intPrincipal);
		PrincipalLookupImpl.setPpeEnviroPrincipal(ppePrincipal);
		PrincipalLookupImpl.setProdEnviroPrincipal(prodPrincipal);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception { }

	@Before
	public void setUp() throws Exception { }

	@After
	public void tearDown() throws Exception { }

	@Test
	public void testGetPrinipal1() 
	{
		String response = principalLookup.getPrinipal(devNickname);
		
		assertTrue("Bad mapping", devPrincipal.equals(response));
	}

	@Test
	public void testGetPrinipal2() 
	{
		String response = principalLookup.getPrinipal(testNickname);
		
		assertTrue("Bad mapping", testPrincipal.equals(response));
	}

	@Test
	public void testGetPrinipal3() 
	{
		String response = principalLookup.getPrinipal(intNickname);
		
		assertTrue("Bad mapping", intPrincipal.equals(response));
	}

	@Test
	public void testGetPrinipal4() 
	{
		String response = principalLookup.getPrinipal(ppeNickname);
		
		assertTrue("Bad mapping", ppePrincipal.equals(response));
	}

	@Test
	public void testGetPrinipal5() 
	{
		String response = principalLookup.getPrinipal(prodNickname);
		
		assertTrue("Bad mapping", prodPrincipal.equals(response));
	}

	@Test(expected = EnviroException.class)
	public void testGetPrincipal_Null() 
	{
		principalLookup.getPrinipal(null);
	}
	
	@Test(expected = EnviroException.class)
	public void testGetPrincipal_Junk() 
	{
		principalLookup.getPrinipal("Car Talk, Saturdays at 11am on NPR");
	}
	
}
