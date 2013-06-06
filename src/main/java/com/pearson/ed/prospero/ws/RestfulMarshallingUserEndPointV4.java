package com.pearson.ed.prospero.ws;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections.Buffer;
import org.apache.commons.collections.BufferUtils;
import org.apache.commons.collections.buffer.CircularFifoBuffer;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pearson.ed.eventing.impl.prospero.subscriber.ProsperoHttpClientSubscriber;
import com.pearson.ed.mockpowerschool.model.RestfulServiceClientImpl;
import com.pearson.ed.prospero.email.EmailContentDTO;
import com.pearson.ed.prospero.email.EmailWriter;
import com.pearson.ed.prospero.exceptions.BufferException;
import com.pearson.ed.prospero.lookups.CredsLookup;
import com.pearson.ed.prospero.lookups.EnviroLookup;
import com.pearson.ed.prospero.lookups.PrincipalLookup;
import com.pearson.ed.prospero.model.form.SendRequest;
import com.pearson.ed.prospero.model.v1.buffers.ProsperoBuffer;
import com.pearson.ed.prospero.model.v1.subscriptions.AnonSubscription;
import com.pearson.ed.prospero.model.v1.subscriptions.ProsperoCheckerSubscriptionRequest;
import com.pearson.ed.prospero.model.v1.subscriptions.Subscriptions;
import com.pearson.ed.prospero.model.v1.summary.ProsperoStatus;
import com.pearson.ed.prospero.model.v1.summary.StatusesSummary;


@Controller
public class RestfulMarshallingUserEndPointV4 extends BaseEndPointExceptionHandler
{
	Logger logger = Logger.getLogger(getClass());
	
	List<ProsperoCheckerSubscriptionRequest> defaultSubscriptions;
	


	public List<ProsperoCheckerSubscriptionRequest> getDefaultSubscriptions() {
		return defaultSubscriptions;
	}
	public void setDefaultSubscriptions(
			List<ProsperoCheckerSubscriptionRequest> defaultSubscriptions) {
		this.defaultSubscriptions = defaultSubscriptions;
	}

	Map<String, Buffer> mapOfBuffers = new ConcurrentHashMap<String, Buffer>();

	DateTime dateTime = new DateTime(1982, 7, 14, 0, 0, 0, 0);
	
	final String SERVICE_URL = "http://idpdev.pearsoncmg.com/ProsperoChecker/rest/";
	Set<String> emailSet;

	EmailWriter emailWriter;
	ProsperoService prosperoService;
	MultiThreadedHttpConnectionManager conMan;  //haha
	EnviroLookup enviroLookup;
	PrincipalLookup principalLookup;
	CredsLookup credsLookup;
	
	public EmailWriter getEmailWriter() { return emailWriter; }
	public void setEmailWriter(EmailWriter emailWriter) { this.emailWriter = emailWriter; }
	
	public void setProsperoService(ProsperoService prosperoService) {  this.prosperoService = prosperoService; }

	public PrincipalLookup getPrincipalLookup() { return principalLookup; }
	public void setPrincipalLookup(PrincipalLookup principalLookup) { this.principalLookup = principalLookup; }

	public CredsLookup getCredsLookup() { return credsLookup; }
	public void setCredsLookup(CredsLookup credsLookup) { this.credsLookup = credsLookup; }
	
	public MultiThreadedHttpConnectionManager getConMan() { return conMan; }
	public void setConMan(MultiThreadedHttpConnectionManager manager) { this.conMan = manager; }

	public EnviroLookup getEnviroLookup() { return enviroLookup; }
	public void setEnviroLookup(EnviroLookup enviroLookup) { this.enviroLookup = enviroLookup; }

	public Set<String> getEmailSet() { return emailSet; }
	public void setEmailSet(Set<String> emailSet) { this.emailSet = emailSet; }


	

	@RequestMapping(value = "/buffers/{id}" , method = RequestMethod.POST)
	@ResponseBody
	public String addMessageToBuffer(@RequestBody final String inMessage, @PathVariable("id") final String id) 
	{
		
		StringBuilder sb = new StringBuilder();
		
		logger.info(id + inMessage);
		
		Buffer b = mapOfBuffers.get(id);
		if(b == null)
		{
			b = BufferUtils.synchronizedBuffer(new CircularFifoBuffer(50));
			mapOfBuffers.put(id, b);
			
			sb.append("Created Buffer named: ");
			sb.append(id);
		}
		
		b.add(inMessage);
		
		sb.append("\r\n");
		sb.append("Added msg: ");
		sb.append(inMessage);
		
		return sb.toString();
	}
	
	@RequestMapping(value = "/buffers/{id}" , method = RequestMethod.GET)
	@ResponseBody
	public String showMessagesInBuffer(@PathVariable("id") final String id) 
	{
		Buffer b = mapOfBuffers.get(id);
		
		if(b == null)
		{
			throw new BufferException("Buffer Does Not Exist: " +id);
		}
		
		StringBuilder sb = new StringBuilder();
		Object[] oa = b.toArray();
		
		for(Object o : oa)
		{
			
			sb.append((String)o);
			sb.append("\r\n");
		}
		
		return sb.toString();
	}
	
	
	@RequestMapping(value = "/buffers" , method = RequestMethod.GET)
	public ModelAndView showBuffers() 
	{
		Set<Entry<String,Buffer>> set = mapOfBuffers.entrySet();
		ArrayList<ProsperoBuffer> buffersList = new ArrayList<ProsperoBuffer>();
		
		ModelAndView mv = new ModelAndView("buffers");
		
		for(Entry<String, Buffer> o : set)
		{
			ProsperoBuffer b = new ProsperoBuffer();
			b.setIdString(o.getKey());
			b.setUsage(o.getValue().size());
			buffersList.add(b);
		}
		
		mv.addObject("buffersList", buffersList);
	
		return mv;
	}
	
	
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/{enviro}/status", method=RequestMethod.GET, produces="application/json")
	private @ResponseBody String getStatus(@PathVariable String enviro) 
	{
		return getStatusForEnvironment(enviro);
	}
	
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/{enviro}/subscriptionsjson", method=RequestMethod.GET, produces="application/json")
	private  @ResponseBody String getSubscriptionsOld(@PathVariable String enviro) 
	{
		
		return getSubscriptionsForEnvironmentAsString(enviro);
	}
	
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/{enviro}/subscriptions", method=RequestMethod.GET)
	private ModelAndView getSubscriptions(@PathVariable String enviro) 
	{
		ModelAndView mv = new ModelAndView("subscriptions");
		
		mv.addObject("subscriptions", getSubscriptionsForEnvironment(enviro));
		
		return mv;
	}
	
	
	private Subscriptions getSubscriptionsForEnvironment(String enviroString)
	{
		Subscriptions ps = new Subscriptions();
		
		try 
		{
			ObjectMapper om = new ObjectMapper();
			String response = getSubscriptionsForEnvironmentAsString(enviroString);
			
			List<AnonSubscription> asl = om.readValue(response, new TypeReference<ArrayList<AnonSubscription>>(){});  //ugly huh?
			ps.setAnonSubscriptionsList(asl);
		} 
		catch (Exception e) 
		{
			logger.error("Problem when checking environment: "+enviroString, e);
			
			ps.setErrorString(e.getMessage());
		}
		
		return ps;
	}
	
	private String getSubscriptionsForEnvironmentAsString(String enviro)
	{
		String serverUrl = enviroLookup.getEnviroUrl(enviro);
		String principal = principalLookup.getPrinipal(enviro);
		String creds = credsLookup.getCreds(enviro);
		
		HttpClient client = new HttpClient();
		client.setHttpConnectionManager(conMan);
		
		RestfulServiceClientImpl impl = new RestfulServiceClientImpl();
		impl.setServerUrl(serverUrl);
		impl.setRestClient(client);
		
		return impl.readSubscriptions(principal,creds);
	}
	
	
	@SuppressWarnings("unused")
	@RequestMapping(method=RequestMethod.GET, value="/{enviro}/subscriptions/delete/{subId}")
	private String deleteSubscription(@PathVariable String enviro, @PathVariable String subId) 
	{
		String serverUrl = enviroLookup.getEnviroUrl(enviro);
		String principal = principalLookup.getPrinipal(enviro);
		String creds = credsLookup.getCreds(enviro);

		
		HttpClient client = new HttpClient();
		client.setHttpConnectionManager(conMan);

		RestfulServiceClientImpl impl2 = new RestfulServiceClientImpl();
		impl2.setServerUrl(serverUrl);
		impl2.setRestClient(client);

		impl2.deletesubscription(subId, principal,creds);

		return "redirect:/rest/" +enviro +"/subscriptions";		
	}
	
	
	@RequestMapping(value="/summaryRaw", method=RequestMethod.GET, produces="application/json")
	private @ResponseBody StatusesSummary getSummaryRaw() 
	{
		StatusesSummary s = new StatusesSummary();
		
		s.getProsperoStatuses().add(getSummaryForEnviro("dev"));
		s.getProsperoStatuses().add(getSummaryForEnviro("test"));
		s.getProsperoStatuses().add(getSummaryForEnviro("int"));
		s.getProsperoStatuses().add(getSummaryForEnviro("ppe"));
		s.getProsperoStatuses().add(getSummaryForEnviro("prod"));
		
		return s;
	}
	
	
	public void doScheduledSummary()
	{
		StatusesSummary ss = getSummaryRaw();
		Integer problems = 0;
		ArrayList<String> probEnviros = new ArrayList<String>();
		
		for(ProsperoStatus status : ss.getProsperoStatuses())
		{
			if(status.getError() != null)
			{
				problems++;
				probEnviros.add(status.getEnviro());
			}
		}
		
		if(problems > 0)
		{
			if(Minutes.minutesBetween(dateTime, new DateTime()).getMinutes() > 59)
			{
				StringBuilder sb = new StringBuilder();
				sb.append(problems.toString());
				sb.append(" enviros found in Error: \n\r");
				for(String s : probEnviros)
				{
					sb.append("  ");
					sb.append(s);
					sb.append("\n\r");
				}
				sb.append("\n\r");
				sb.append(SERVICE_URL);
				sendEmails(sb.toString());
				
				dateTime =  new DateTime();
			}
		}
		
	}
	
	private void sendEmails(String msg)
	{
		EmailContentDTO emailContent = new EmailContentDTO();
		emailContent.setFromAddress("DoNotReply@Pearson.com");
		emailContent.setFromName("ProsperoChecker");
		emailContent.setMessage(msg);
		emailContent.setSubject("Prospero Problem");
		
		for(String s : emailSet)
		{
			emailContent.setToAddress(s);
			emailContent.setToName(s);
			sendEmail(emailContent);
		}
	}
	
	private void sendEmail(EmailContentDTO emailContent)
	{
		try 
		{
			emailWriter.sendEmail(emailContent);
		} 
		catch (MailException e) {
			logger.error("Something bad happened when trying to send Mail", e);
		} 
		catch (Exception e) {
			logger.error("", e);
		}
	}
	
	
	private ProsperoStatus getSummaryForEnviro(String enviroString)
	{
		ProsperoStatus ps;
		
		try 
		{
			ObjectMapper om = new ObjectMapper();
			String response = getStatusForEnvironment(enviroString);
			
			ps = om.readValue(response, ProsperoStatus.class);
			ps.setEnviro(enviroString);
		} 
		catch (Exception e) 
		{
			logger.error("Problem when checking environment: "+enviroString, e);
			
			ps = new ProsperoStatus();
			ps.setEnviro(enviroString);
			ps.setError(e.getMessage());
		}
		
		return ps;
	}
	
	
	private String getStatusForEnvironment(String enviro)
	{
		HttpClient client = new HttpClient();
		client.setHttpConnectionManager(conMan);

		String serverUrl = enviroLookup.getEnviroUrl(enviro);
		
		RestfulServiceClientImpl impl = new RestfulServiceClientImpl();
		impl.setServerUrl(serverUrl);
		impl.setRestClient(client);
		
		
		return impl.getStatus();
	}

	
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/submitMsg", method=RequestMethod.GET)
	private ModelAndView submitMsg(@ModelAttribute("sendRequest") SendRequest sendRequest, BindingResult result ) 
	{
		ModelAndView mv = new ModelAndView("submitResult");
		
		mv.addObject("sendRequest", sendRequest);
	
		return mv;
	}
	
	@RequestMapping(value="/summary", method=RequestMethod.GET)
	private ModelAndView getSummary() 
	{
		ModelAndView mv = new ModelAndView("summary");

		StatusesSummary s = new StatusesSummary();
		s.getProsperoStatuses().add(getSummaryForEnviro("dev"));
		s.getProsperoStatuses().add(getSummaryForEnviro("test"));
		s.getProsperoStatuses().add(getSummaryForEnviro("int"));
		s.getProsperoStatuses().add(getSummaryForEnviro("ppe"));
		s.getProsperoStatuses().add(getSummaryForEnviro("prod"));
		
		mv.addObject("statusesSummary", s);
		return mv;
	}
	
	
	
	public void initMethod()
	{
		initSubscriptions();
	}
	
	public void initSubscriptions()
	{
		for(ProsperoCheckerSubscriptionRequest request : defaultSubscriptions)
		{
			String url = request.getProsperoBaseUrl() + request.getSubscribePath(); //enviroLookup.getEnviroUrl(enviro) + enviroLookup.getEnviroSubscribePath(enviro);
			String principal = request.getAuthorization().getUsername(); //principalLookup.getPrinipal(enviro);
			String creds = request.getAuthorization().getPassword(); //credsLookup.getCreds(enviro);
			
			HttpClient httpClient = new HttpClient();
			httpClient.setHttpConnectionManager(conMan);
			
			ProsperoHttpClientSubscriber prosperoHttpClient = new ProsperoHttpClientSubscriber(httpClient, httpClient.getHttpConnectionManager(), url);
			
			try
			{
				String message = "Result: " + prosperoHttpClient.subscribe(request);
				
				logger.info(message);
			}
			catch(Exception e)
			{
				logger.error("Problem subscribing to URL: " +url, e);
			}
		}
	}
	
	public void initMethodOld()
	{
		
		String[] enviroArray = {"dev","test","int","ppe"};
		
		for(String enviro : enviroArray)
		{
			String url = enviroLookup.getEnviroUrl(enviro) + enviroLookup.getEnviroSubscribePath(enviro);
			String principal = principalLookup.getPrinipal(enviro);
			String creds = credsLookup.getCreds(enviro);
			
			HttpClient httpClient = new HttpClient();
			httpClient.setHttpConnectionManager(conMan);
			
			ProsperoHttpClientSubscriber prosperoHttpClient = new ProsperoHttpClientSubscriber(httpClient, httpClient.getHttpConnectionManager(), url);
			//prosperoHttpClient.setServerUrlSvmtring(enviroLookup.getEnviroUrl(formBean.getEnviro().toString()) + "/v1/subscription");
			
			try
			{
				String message = "Result: " + prosperoHttpClient.subscribe(
					new Date(), 
					principal, 
					creds, 
					"http://idpdev.pearsoncmg.com/ProsperoChecker/rest/buffers/"+enviro, 
					"SmsUserEvent", 
					"SMS-CLIENT-ID", 
					"SMS-CLIENT-STRING", 
					"SMS", 
					"SMS", 
					"SMS:SmsUserEvent"				
					);
				
				logger.info(message);
			}
			catch(Exception e)
			{
				logger.error("Problem subscribing to enviro: " +enviro, e);
			}
		}
	}
	
	
}
