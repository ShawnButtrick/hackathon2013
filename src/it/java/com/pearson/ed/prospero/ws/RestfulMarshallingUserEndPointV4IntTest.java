package com.pearson.ed.prospero.ws;


import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	inheritLocations = false,
	locations = {"classpath:/applicationContext-prospero-int.xml"}
)
public class RestfulMarshallingUserEndPointV4IntTest 
{
	
	private MockMvc mockMvc;
	
	
	@Before
	public void setup() {

		mockMvc = MockMvcBuilders
				.xmlConfigSetup("classpath:/applicationContext-prospero-int.xml")
				.configureWebAppRootDir("src/main/webapp", false).build();
		}
	
	@Test
	public void testDevEnviro() throws Exception  
	{
		//mockMvc.perform(get("/dev/status")).andExpect(status().isOk());
		mockMvc.perform(get("/dev/status"));
		
	}
	
	@Test
	public void testTestEnviro() throws Exception  
	{
		mockMvc.perform(get("/test/status"));
		//.andExpect(status().isOk())
		//.andExpect(content().type(MediaType.APPLICATION_JSON));
	}
	
	@Test
	public void testIntEnviro() throws Exception  
	{
		mockMvc.perform(get("/int/status"));
		//.andExpect(status().isOk())
		//.andExpect(content().type(MediaType.APPLICATION_JSON));
	}
	
	@Test
	public void testPpeEnviro() throws Exception  
	{
		mockMvc.perform(get("/ppe/status"));
	}
	
	@Test
	public void testProdEnviro() throws Exception  
	{
		mockMvc.perform(get("/prod/status"))
		.andExpect(status().isOk())
		.andExpect(content().type(MediaType.APPLICATION_JSON));
	}
	

	@Test
	public void testSummaryRaw() throws Exception  
	{
		mockMvc.perform(get("/summaryRaw"))
		.andExpect(status().isOk())
		.andExpect(content().type(MediaType.APPLICATION_JSON));
	}
	
	@Test
	public void testSad() throws Exception  
	{
		mockMvc.perform(get("/puke/status")).andExpect(status().is(404));
		
	}
	
	@Test
	public void testSummary() throws Exception  
	{
		mockMvc.perform(get("/summary")).andExpect(status().is(200));
		
	}
	
	@After
	public void tearDown() 
	{			
		mockMvc = null;		
	}
	 
}
