package com.pearson.ed.prospero.forms;

import java.util.Date;

import javax.validation.Valid;

import org.apache.commons.httpclient.HttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pearson.ed.eventing.impl.prospero.publisher.ProsperoHttpClient;
import com.pearson.ed.prospero.lookups.EnviroLookup;

@Controller
@RequestMapping("/sendform")
@SessionAttributes("sendBean")
public class SendController 
{
	
	//ProsperoHttpClient prosperoHttpClient;
	private HttpClient httpClient;
	EnviroLookup enviroLookup;
	
	public HttpClient getHttpClient() { return httpClient; }
	public void setHttpClient(HttpClient httpClient) { this.httpClient = httpClient; }
	
	public EnviroLookup getEnviroLookup() { return enviroLookup; }
	public void setEnviroLookup(EnviroLookup enviroLookup) { this.enviroLookup = enviroLookup; }
	
	
	// Invoked initially to create the "form" attribute
	// Once created the "form" attribute comes from the HTTP session (see @SessionAttributes)
	@ModelAttribute("sendBean")
	public SendBean createFormBean() 
	{
		SendBean fb = new SendBean();
		fb.setPayloadContentType("application/json");
		fb.setRealm("*");
		return fb;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public void form() 
	{
	}

	@RequestMapping(method=RequestMethod.POST)
	public String processSubmit(@Valid SendBean formBean, BindingResult result, 
								Model model, RedirectAttributes redirectAttrs) 
	{
		if (result.hasErrors()) 
		{
			return null;
		}
		
		// Typically you would save to a db and clear the "form" attribute from the session 
		// via SessionStatus.setCompleted(). For the demo we leave it in the session.
		String message = "Status:Form submitted successfully.  Bound " + formBean;
		
		
		String url = enviroLookup.getEnviroUrl(formBean.getEnviro()) + enviroLookup.getEnviroSendPath(formBean.getEnviro()) ;
		
		ProsperoHttpClient prosperoHttpClient = new ProsperoHttpClient(httpClient, httpClient.getHttpConnectionManager(), url);
		
		String prosperoResult = "Result: " + prosperoHttpClient.publish(
				new Date(), 
				formBean.getPrincipal(), 
				formBean.getKey(), 
				formBean.getMessageType(), 
				formBean.getClientId(), 
				formBean.getClientString(), 
				formBean.getSystem(), 
				formBean.getSubSystem(), 
				formBean.getRealm(), 
				formBean.getTags(), 
				formBean.getPayloadContentType(), 
				formBean.getPayloadContent());
		
		
		// Success response handling
		
		// store a success message for rendering on the next request after redirect
		// redirect back to the form to render the success message along with newly bound values
		//redirectAttrs.addFlashAttribute("message", message);
		redirectAttrs.addFlashAttribute("message", prosperoResult);
		return "redirect:/rest/sendform";			
	}
	
}
