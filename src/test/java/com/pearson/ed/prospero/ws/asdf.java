package com.pearson.ed.prospero.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;

public class asdf 
{

	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private static YouTube youtube;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Ignore
	@Test
	public void test() throws IOException 
	{
		HttpGet get = new HttpGet("https://www.googleapis.com/youtube/v3/playlistItems?part=id,snippet,status,contentDetails&playlistId=PLD58BCF75F880DD81&key=AIzaSyD1lRHrPMSkGXjLelY7Rj29hhOpH7eBeUs");
		ClientConnectionManager man = new PoolingClientConnectionManager();
		HttpClient client = new DefaultHttpClient(man);
		
		HttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();
		PlaylistItemListResponse items = JSON_FACTORY.fromInputStream(entity.getContent(), PlaylistItemListResponse.class);
		
		List<String> videoIds = new ArrayList<String>();
		
		for(PlaylistItem item :items.getItems())
		{
			videoIds.add(item.getContentDetails().getVideoId());
		}
		
		
	}

}
