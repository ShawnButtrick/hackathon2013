package com.pearson.ed.prospero.ws;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.axiom.soap.SOAPFaultText;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.bouncycastle.crypto.engines.AESFastEngine;
import org.bouncycastle.crypto.macs.CMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.http.client.CommonsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.soap.axiom.AxiomSoapMessage;

/**
 * This class is a Client stub class and implements methods to be invoked on
 * Organization Life Cycle service.
 * 
 * @author VPLAHNA
 * 
 */
public class RestfulServiceClientImpl {

	private static String subscrUrlTemplate = "$SERVER/v1/subscription/$SUB";
	private static String subscriptionPrincipalResourcestatusUrlTemplate = "$SERVER/v1/subscriptions/principal/$PRINCIPAL";
	private static String statusUrlTpt = "$SERVER/v1/status";
	
	private static final Logger logger = Logger
			.getLogger(RestfulServiceClientImpl.class);
	
	public final static String MESSAGETYPE = "MESSAGE-TYPE";
	public static final String CLIENT = "CLIENT"; 
	public static final String CLIENTSTRING = "CLIENT-STRING";
	public static final String SYSTEM =  "SYSTEM";
	public static final String SUBSYSTEM =	"SUB-SYSTEM";
	public static final String REALM = "REALM";
	public static final String TAGS = "TAGS";
	public static final String AUTHORIZATION = "AUTHORIZATION";
	public static final String PAYLOADCONTENTTYPE = "PAYLOAD-CONTENT-TYPE";
	public static final String PAYLOAD =  "PAYLOAD";
	public static final String CALLBACKURL = "CALLBACK-URL";
	private static final Class<String> messageID = String.class;

	private RestTemplate webServiceTemplate;
	private String serverUrl; 
	private MultiThreadedHttpConnectionManager connectionManager;
	private Object publishToQueue(Map<String,?> postParam, Object request)
	{
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
	    messageConverters.add(new FormHttpMessageConverter());
	    messageConverters.add(new StringHttpMessageConverter());
	    messageConverters.add(new MappingJacksonHttpMessageConverter());
	    webServiceTemplate.setMessageConverters(messageConverters);
	    webServiceTemplate.setRequestFactory(new CommonsClientHttpRequestFactory());
		Object o = webServiceTemplate.postForObject(serverUrl, request, messageID, postParam);	
		return o;
	}
	
	public static void main(String[] args) {
		//
		// obviously a more robust method of determining what functional path to call
		// from this point is required ... TODO:
		//
		if (args.length == 3){

			String principal = args[0];         // prospero principal value
			String serverUrl = args[1];         // host and port of rospero instance
			String key = args[2];         // host and port of rospero instance
			
			HttpClient client = new HttpClient();
			MultiThreadedHttpConnectionManager manager = new MultiThreadedHttpConnectionManager();

			client.setHttpConnectionManager(manager);

			RestfulServiceClientImpl impl = new RestfulServiceClientImpl();
			impl.setServerUrl(serverUrl);
			impl.setRestClient(client);
			
			try{
				impl.readSubscriptions(principal, key);
				
//				List<String> list = impl.getSubscriptionIds();
//				for (String s : list) {
//					System.out.println(s);
//				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally{
				manager.shutdown();
			}
			
		}
		else {
			
			// File f= new File(Args[0]);
			// //dothis(f);
			// doit(f);

		}
	}	
	
	private static void doit(File f) {
		List<String> stringList = new ArrayList<String>();
		try {
			Scanner sc = new Scanner(f);
			StringBuffer sb = new StringBuffer();
			while(sc.hasNextLine())
			{
				String str = sc.nextLine();
				sb.append(str+"\r\n");
			}
			sc.close();
			String props = sb.toString(); 
			PrintWriter pw = new PrintWriter(f);
			pw.write(props.replaceAll("<ns:CreatedBy>[a-f0-9]{32}</ns:CreatedBy>","<ns:CreatedBy>99999999999999999999999999999999</ns:CreatedBy>"));
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void processfile(File ff) {
		
		List<String> stringList = new ArrayList<String>();
	try {
		Scanner sc = new Scanner(ff);
		StringBuffer sb = new StringBuffer();
		while(sc.hasNextLine())
		{
			String str = sc.nextLine();
			sb.append(str+"\r\n");
		}
		sc.close();
		String fullfile = sb.toString();
		List <String> props = getprops(fullfile);
		for (int i = 1; i < props.size(); i++)
		{
			fullfile = fullfile.replace(props.get(i), props.get(i).replaceAll("\\/ns[0-9]?:","/*:"));
		}
		
		PrintWriter pw = new PrintWriter(ff);
		pw.write(fullfile);
		pw.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

	private static void dothis(File f) {
		for (File ff : f.listFiles()) {
			if (ff.isDirectory())
				dothis(ff);
			else if (ff.getName().contains("emplate"))
				{
				System.out.println(ff.getAbsolutePath());
				processfile(ff);
				}
		}

	}

	private static List<String> getprops(String str) {
		Pattern p = Pattern.compile("\\$\\{.*\\}");
		Matcher m = p.matcher(str);
		ArrayList<String> string = new ArrayList<String>();
		while (m.find())
			string.add(m.group());
		return string;
	}

	public void setServerUrl(String string) {
		serverUrl = string;
	}

	public void setModeUnitTest(String modeUnitTest) {
		this.modeUnitTest = modeUnitTest;
	}

	private String modeUnitTest = "N";

	private HostConfiguration hostConfiguration;

	private HttpClient restClient;


	/**
	 * getter for webservice template
	 * 
	 * @return webServiceTemplate
	 */
	public RestTemplate getWebServiceTemplate() {
		return webServiceTemplate;
	}

	/**
	 * Setter for webservice template.
	 * 
	 * @param webServiceTemplate
	 */
	public void setWebServiceTemplate(RestTemplate webServiceTemplate) {
		this.webServiceTemplate = webServiceTemplate;
	}
	
	/**
	 * Method to get fault message. 
	 * 
	 * @param message
	 * 
	 * @return fault message
	 */
	private String getFaultMessage(WebServiceMessage message){
		AxiomSoapMessage soapMessage = (AxiomSoapMessage) message;
		SOAPFaultText sOAPFaultText = soapMessage.getAxiomMessage().getSOAPEnvelope().getBody().getFault().getReason()
				.getSOAPFaultText("en");
		return sOAPFaultText.getText().toString();
	}
	/**
	 * 
	 * @param d
	 * @param principal
	 * @param keyS
	 * @param mesString
	 * @param client
	 * @param clientString
	 * @param system
	 * @param subsystem
	 * @param Realm
	 * @param tags
	 * @param pContent
	 * @param payload
	 * @return
	 */
	public static String generateAuthorization(Date d, String principal,String keyS,String mesString, String client, String clientString, String system, 
			String subsystem, String Realm, String tags, String pContent, String payload)
	{
		d = new Date(d.getTime()+ 5*1000*60);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss-04:00");
		byte[] data = stringConcat(dateFormat.format(d),client,clientString,system,subsystem,Realm,tags,mesString,pContent,payload).getBytes();
        byte[] key = keyS.getBytes();

        byte[] output = new byte[16];

        CMac macProvider = new CMac( new AESFastEngine(), 128 );
        macProvider.init( new KeyParameter(key) );
        macProvider.update( data, 0, data.length );
        macProvider.doFinal( output, 0 );
       
        String result = new String( Hex.encode( output ) );

        return (principal+"|"+dateFormat.format(d)+"|"+result);
	}	

	/**
	 * This overload is specifically for use in calling prospero to retrieve subscription metadata
	 * for subscriptions for a given principal.
	 * The data returned will contain subscription information consistent with the value
	 * of the principal parameter.
	 * 
	 * @param d
	 * @param principal
	 * @param subscrId
	 * @return
	 */
	public static String generateAuthorization(Date d, String principal, String subscrId, String keyString)
	{

		d = new Date(d.getTime()+ 5*1000*60);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss-04:00");
		byte[] data = stringConcat(dateFormat.format(d),subscrId).getBytes();

        byte[] output = new byte[16];
		byte[] key = keyString.getBytes();

        CMac macProvider = new CMac( new AESFastEngine(), 128 );
        macProvider.init( new KeyParameter(key) );
        macProvider.update( data, 0, data.length );
        macProvider.doFinal( output, 0 );
       
        String result = new String( Hex.encode( output ) );

        return (principal+"|"+dateFormat.format(d)+"|"+result);
	}	
	/**
	 * This overload is specifically for use in calling prospero to retrieve subscription metadata.
	 * The data returned will contain subscription information consistent with the value
	 * of the principal parameter.
	 * 
	 * @param d
	 * @param principal
	 * @param subscrId
	 * @return
	 */
	public static String generateAuthorization(Date d, String principal, String keyString)
	{

		d = new Date(d.getTime()+ 5*1000*60);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss-04:00");
		byte[] data = stringConcat(dateFormat.format(d),principal).getBytes();

        byte[] output = new byte[16];
		byte[] key = keyString.getBytes();

        CMac macProvider = new CMac( new AESFastEngine(), 128 );
        macProvider.init( new KeyParameter(key) );
        macProvider.update( data, 0, data.length );
        macProvider.doFinal( output, 0 );
       
        String result = new String( Hex.encode( output ) );

        return (principal+"|"+dateFormat.format(d)+"|"+result);
	}	

	/**
	 * 
	 * @param d
	 * @param principal
	 * @param keyS
	 * @param mesString
	 * @param client
	 * @param clientString
	 * @param system
	 * @param subsystem
	 * @param Realm
	 * @param tags
	 * @param pContent
	 * @param payload
	 * @return
	 */
	public String publish(Date d, String principal, String keyS,
			String mesString, String client, String clientString,
			String system, String subsystem, String Realm, String tags,
			String pContent, String payload) {
		ArrayList<NameValuePair> queryList = new ArrayList<NameValuePair>();
		
		if (StringUtils.isNotBlank(mesString))
			queryList.add(new NameValuePair(MESSAGETYPE, mesString));

		if (StringUtils.isNotBlank(client))
			queryList.add(new NameValuePair(CLIENT, client));

		if (StringUtils.isNotBlank(clientString))
			queryList.add(new NameValuePair(CLIENTSTRING, clientString));

		if (StringUtils.isNotBlank(system))
			queryList.add(new NameValuePair(SYSTEM, system));

		if (StringUtils.isNotBlank(subsystem))
			queryList.add(new NameValuePair(SUBSYSTEM, subsystem));

		if (StringUtils.isNotBlank(Realm))
			queryList.add(new NameValuePair(REALM, Realm));

		if (StringUtils.isNotBlank(tags))
			queryList.add(new NameValuePair(TAGS, tags));

//		if (mesString != null || StringUtils.isNotBlank(mesString))
			queryList.add(new NameValuePair(AUTHORIZATION, generateAuthorization(d,
				principal, keyS, mesString, client, clientString,
				system, subsystem, Realm, tags, pContent, payload)));

		if (StringUtils.isNotBlank(pContent))
			queryList.add(new NameValuePair(PAYLOADCONTENTTYPE, pContent));

		if (StringUtils.isNotBlank(payload))
			queryList.add(new NameValuePair(PAYLOAD, payload)); 
		NameValuePair[] queryParams = (NameValuePair[]) queryList.toArray(new NameValuePair[0]);
		return postQueryWithHeader(queryParams,"application/x-www-form-urlencoded; charset=UTF-8");

		
	}
	
	public String subscribe(Date d, String principal, String keyS,
			String mesString, String client, String clientString,
			String system, String subsystem, String callback, String tags) {

		ArrayList<NameValuePair> queryList = new ArrayList<NameValuePair>();
		if (StringUtils.isNotBlank(mesString))
			queryList.add(new NameValuePair(MESSAGETYPE, mesString));

		if (StringUtils.isNotBlank(client))
			queryList.add(new NameValuePair(CLIENT, client));

		if (StringUtils.isNotBlank(clientString))
			queryList.add(new NameValuePair(CLIENTSTRING, clientString));

		if (StringUtils.isNotBlank(system))
			queryList.add(new NameValuePair(SYSTEM, system));

		if (StringUtils.isNotBlank(subsystem))
			queryList.add(new NameValuePair(SUBSYSTEM, subsystem));


		if (StringUtils.isNotBlank(tags))
			queryList.add(new NameValuePair(TAGS, tags));
		
		if (StringUtils.isNotBlank(callback))
			queryList.add(new NameValuePair(CALLBACKURL, callback));

//		if (mesString != null && StringUtils.isNotBlank(mesString))
			queryList.add(new NameValuePair(AUTHORIZATION,  generateAuthorizationSub(d,
					principal, keyS, mesString, client, clientString,
					system, subsystem, callback, tags)));

		NameValuePair[] queryParams = (NameValuePair[]) queryList.toArray(new NameValuePair[0]);
		return postQueryWithHeader(queryParams,"application/x-www-form-urlencoded; charset=UTF-8");

		
	}
	private String generateAuthorizationSub(Date d, String principal,
			String keyS, String mesString, String client,
			String clientString, String system, String subsystem,
			String callback, String tags) {
		d = new Date(d.getTime()+ 5*1000*60);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss-04:00");
		byte[] data = stringConcat(dateFormat.format(d),client,clientString,system,subsystem,callback,tags,mesString).getBytes();
        byte[] key = keyS.getBytes();

        byte[] output = new byte[16];

        CMac macProvider = new CMac( new AESFastEngine(), 128 );
        macProvider.init( new KeyParameter(key) );
        macProvider.update( data, 0, data.length );
        macProvider.doFinal( output, 0 );
       
        String result = new String( Hex.encode( output ) );
//        System.out.println(principal+"|"+dateFormat.format(d)+"|"+result);
        return (principal+"|"+dateFormat.format(d)+"|"+result);
	}


	public String postQueryWithHeader(NameValuePair[] queryParams, String contentType)
	{
		//restClient = new HttpClient(connectionManager);//make sure bean contains this
		
		PostMethod method = new PostMethod(serverUrl);
		method.setRequestHeader("Content-Type",
				contentType);
		method.setRequestBody(queryParams);
		method.setQueryString(queryParams);
//		System.out.println("Pushed to Queue:" + method.getQueryString());
		String responseBody;

		try {
			restClient.executeMethod(method);
			responseBody = method.getResponseBodyAsString();
			System.out.println("Recieved from Queue:" + responseBody);
			method.releaseConnection();
			return responseBody;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * This method is mainly for debugging/investigative purposes.
	 * Given a subscription id and principal, it queries prospero for 
	 * metadata on the given subscription
	 * 
	 * @param d
	 * @param principal
	 * @param subscriptionId
	 * @return
	 */
	public String readSubscriptions(String principal, String key) {

		String responseBody = null;
		
		String url = subscriptionPrincipalResourcestatusUrlTemplate.replace("$SERVER",serverUrl);
		url = url.replace("$PRINCIPAL",principal);
		
		GetMethod status = new GetMethod(url);
		status.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		
		try {
			String token = generateAuthorization(new Date(), principal, key);
			status.setRequestHeader("Authorization", token);
			
			restClient.executeMethod(status);
			responseBody = status.getResponseBodyAsString();
			
			JSONObject json = (JSONObject) JSONSerializer.toJSON(responseBody);
			JSONArray subscriptions = json.getJSONArray("subscriptions");

			for (int idx1 = 0;idx1 < subscriptions.size(); idx1++){
					
					Date d = new Date(); 

					JSONObject jo = subscriptions.getJSONObject(idx1);
					
					String subscriptionId = (String)jo.getJSONObject("subscription").get("id");
					
					String requestUrl = subscrUrlTemplate.replace("$SERVER", serverUrl);
					requestUrl        = requestUrl .replace("$SUB", subscriptionId);
					
					String auth = generateAuthorization(d, principal, subscriptionId, key);
				
					ArrayList<NameValuePair> queryList = new ArrayList<NameValuePair>();					

					queryList.add(new NameValuePair(AUTHORIZATION, auth));
					queryList.add(new NameValuePair(PAYLOADCONTENTTYPE, "application/x-www-form-urlencoded; charset=UTF-8"));

					NameValuePair[] queryParams = (NameValuePair[]) queryList.toArray(new NameValuePair[0]);
					
					GetMethod method = new GetMethod(requestUrl);

					method.setRequestHeader("Authorization",
							auth);
					method.setRequestHeader("Content-Type",
							"application/x-www-form-urlencoded; charset=UTF-8");					
					method.setQueryString(queryParams);
					
					try {
						restClient.executeMethod(method);
						responseBody = method.getResponseBodyAsString();
						System.out.println(responseBody);
					}
					catch(Exception e){
						e.printStackTrace();
					}
					finally{
						method.releaseConnection();
					}

				}
			
		} catch (IOException e) {
			
			throw new RuntimeException(e);
			
		}
		
		return responseBody;
		
	}

	/**
	 * Helper to get a list of subscription ids
	 * @return
	 */
	public List<String> getSubscriptionIds() {

		String responseBody = null;
		List<String> ids = null;
		
		String url = statusUrlTpt.replace("$SERVER",serverUrl);

		
		GetMethod status = new GetMethod(url);
		status.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		
		try {
			
			restClient.executeMethod(status);
			responseBody = status.getResponseBodyAsString();
			
			JSONObject json = (JSONObject) JSONSerializer.toJSON(responseBody);
			JSONArray subscriptions = json.getJSONObject("status").
					                 getJSONArray("up_nodes").
					                 getJSONObject(0).
					                 getJSONObject("node").
					                 getJSONArray("watched_subscriptions");

			if ( subscriptions.size() > 0 ) { 
				ids = new ArrayList<String>();

				for (int idx1 = 0;idx1 < subscriptions.size(); idx1++){
						
					String o = subscriptions.getJSONObject(idx1).getJSONObject("proc").getString("subscription_id");
					ids.add(o);

				}
			}
			
		} catch (IOException e) {
			
			throw new RuntimeException(e);
			
		}
		
		return ids;
		
	}
	public void setRestClient(HttpClient restClient) {
		this.restClient = restClient;
	}

	private static String stringConcat(String... str)
	{
		String returner = "";
		for (String s : str)
		{
			if (s == null || StringUtils.isBlank(s))
			{
				
			}
			else
			{
				returner += s;
			}
		}
		return returner;
	}
	
}