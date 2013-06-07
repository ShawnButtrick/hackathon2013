package com.pearson.ed.prospero.forms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.pearson.ed.tony.download.YoutubeDownloadController;

@Controller
@RequestMapping("/searchform")
@SessionAttributes("searchBean")
public class SearchController 
{
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	YoutubeDownloadController downloader = new YoutubeDownloadController();
	
	// Invoked initially to create the "form" attribute
	// Once created the "form" attribute comes from the HTTP session (see @SessionAttributes)
	@ModelAttribute("searchBean")
	public SearchBean createFormBean() 
	{
		SearchBean sb = new SearchBean();
		sb.setSearchString("what u wanna search for?");
		return sb;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public void form() 
	{
	}

	@RequestMapping(method=RequestMethod.POST)
	public String processSubmit(@Valid SearchBean formBean, BindingResult result, SessionStatus ss, Model model, RedirectAttributes redirectAttrs) throws ClientProtocolException, IOException 
	{
		if (result.hasErrors()) 
		{
			return null;
		}
		
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
		
		
		downloader.downloadVideoIds(videoIds);
		
		//UriComponents redirectUri = UriComponentsBuilder.fromPath("/rest/results").queryParam("searchString", formBean.getSearchString()).build().encode();
		return "ok";
	}
	
}
