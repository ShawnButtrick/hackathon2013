package com.pearson.ed.prospero.forms;

import javax.validation.Valid;

import org.apache.commons.httpclient.HttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/subscribeform")
@SessionAttributes("subscribeBean")
public class SubscribeController 
{
	
	//ProsperoHttpClientSubscriber prosperoHttpClient;
	private HttpClient httpClient;

	
	public HttpClient getHttpClient() { return httpClient; }
	public void setHttpClient(HttpClient httpClient) { this.httpClient = httpClient; }

	// Invoked initially to create the "form" attribute
	// Once created the "form" attribute comes from the HTTP session (see @SessionAttributes)
	@ModelAttribute("subscribeBean")
	public SubscribeBean createFormBean() 
	{
		SubscribeBean sb = new SubscribeBean();
		
		sb.setSearchString("SMS-CLIENT-STRING");
		
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
		
		try
		{
			message = "Results: ";
			//DO SHIT HERE
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
