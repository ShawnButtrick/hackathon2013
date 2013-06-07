package com.pearson.ed.prospero.forms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.pearson.ed.prospero.result.Result;
import com.pearson.ed.prospero.result.Results;

@Controller
@RequestMapping("/results")
@SessionAttributes("resultsBean")
public class RedirectController {
	
	@ModelAttribute("resultsBean")
	public Results createFormBean() 
	{
		Results r = new Results();
		
		return r;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	private ModelAndView getResults(@RequestParam(required=true) String searchString) 
	{
		ModelAndView mv = new ModelAndView("results");
		
		//todo:  call out to youtube
		Result a = new Result();
			a.setStringA("string 1");
			a.setStringB("string 2");
		Result b = new Result();
			b.setStringA("string 1");
			b.setStringB("string 2");
		
		List<Result> resultsList = new ArrayList<Result>();
			resultsList.add(a);
			resultsList.add(b);
		
		Results r = new Results();
		r.setResultsList(resultsList);
		
		mv.addObject("resultsform", r);
		
		return mv;
	}
	
	
	
	@RequestMapping(value="/uriTemplate", method=RequestMethod.GET)
	public String uriTemplate(RedirectAttributes redirectAttrs) {
		redirectAttrs.addAttribute("account", "a123");  // Used as URI template variable
		redirectAttrs.addAttribute("date", new LocalDate(2011, 12, 31));  // Appended as a query parameter
		return "redirect:/rest/redirect/{account}";
	}

	@RequestMapping(value="/uriComponentsBuilder", method=RequestMethod.GET)
	public String uriComponentsBuilder() {
		UriComponents redirectUri = UriComponentsBuilder.fromPath("/redirect/{account}").queryParam("date", new Date(0))
				.build().expand("a123").encode();
		return "redirect:/rest/" + redirectUri.toUriString();
	}

	
}
