package com.pearson.ed.prospero.forms;

import java.util.Date;

import javax.crypto.spec.PSource;
import javax.validation.Valid;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pearson.ed.eventing.impl.prospero.publisher.ProsperoHttpClient;
import com.pearson.ed.eventing.impl.prospero.subscriber.ProsperoHttpClientSubscriber;
import com.pearson.ed.prospero.lookups.EnviroLookup;

@Controller
@RequestMapping("/subscribeform")
@SessionAttributes("subscribeBean")
public class SubscribeController 
{
	
	//ProsperoHttpClientSubscriber prosperoHttpClient;
	private HttpClient httpClient;
	private EnviroLookup enviroLookup;

	
	public HttpClient getHttpClient() { return httpClient; }
	public void setHttpClient(HttpClient httpClient) { this.httpClient = httpClient; }

	public EnviroLookup getEnviroLookup() { return enviroLookup; }
	public void setEnviroLookup(EnviroLookup enviroLookup) { this.enviroLookup = enviroLookup; }
	

	
	
	
	// Invoked initially to create the "form" attribute
	// Once created the "form" attribute comes from the HTTP session (see @SessionAttributes)
	@ModelAttribute("subscribeBean")
	public SubscribeBean createFormBean() 
	{
		SubscribeBean sb = new SubscribeBean();
		
		sb.setClientId("SMS-CLIENT-ID");
		sb.setClientString("SMS-CLIENT-STRING");
		
		sb.setSystem("SMS");
		sb.setSubSystem("SMS");
		
		sb.setMessageType("SmsUserEvent");
		sb.setTags("SMS:SmsUserEvent");
		
		return sb;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public void form() 
	{
	}

	@RequestMapping(method=RequestMethod.POST)
	public String processSubmit(@Valid SubscribeBean formBean, BindingResult result, SessionStatus ss,
								Model model, RedirectAttributes redirectAttrs) 
	{
		if (result.hasErrors()) 
		{
			return null;
		}
		
		// Typically you would save to a db and clear the "form" attribute from the session 
		// via SessionStatus.setCompleted(). For the demo we leave it in the session.
		String message; // = "Status:Form submitted successfully.  Bound " + formBean;
		String error;
		
		String url = enviroLookup.getEnviroUrl(formBean.getEnviro()) + enviroLookup.getEnviroSubscribePath(formBean.getEnviro()) ;
		
		ProsperoHttpClientSubscriber prosperoHttpClient = new ProsperoHttpClientSubscriber(httpClient, httpClient.getHttpConnectionManager(), url);
		
		prosperoHttpClient.setServerUrlString(enviroLookup.getEnviroUrl(formBean.getEnviro().toString()) + "/v1/subscription");
		try
		{
			message = "Result: " + prosperoHttpClient.subscribe(
				new Date(), 
				formBean.getPrincipal(), 
				formBean.getKey(), 
				formBean.getCallbackUrl(), 
				formBean.getMessageType(), 
				formBean.getClientId(), 
				formBean.getClientString(), 
				formBean.getSystem(), 
				formBean.getSubSystem(), 
				formBean.getTags()				
				);
		}
		catch(Exception e)
		{
			error = e.getMessage();
			//result.addError(new ObjectError("otherError", e.getMessage() ));
			redirectAttrs.addFlashAttribute("error", error);
			return "redirect:/rest/subscribeform";
		}
		
				
		// store a success message for rendering on the next request after redirect
		// redirect back to the form to render the success message along with newly bound values
		//redirectAttrs.addFlashAttribute("message", message);
		redirectAttrs.addFlashAttribute("message", message);
		return "redirect:/rest/subscribeform";			
	}
	
}
