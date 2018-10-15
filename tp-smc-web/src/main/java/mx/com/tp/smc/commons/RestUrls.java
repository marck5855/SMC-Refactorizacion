package mx.com.tp.smc.commons;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RestUrls {
	
		//Json parser
		protected JSONParser parser;
	
		//Root URI	
		//protected final String rootUri = "http://10.180.251.16:8080/ticketapi/";
		
		protected String getRootUri() throws IOException {
			
			Properties p = new Properties();
			InputStream input = null;

			input = getClass().getClassLoader().getResourceAsStream("application.properties");
			p.load(input);
			
			String rootUri = p.getProperty("getRutaTicket");
			
			return rootUri;
		}
		
		//Oauth URI
		protected final String oauthUri = "oauth/token?grant_type=password&";
		protected final String authUri = "username=<arg0>&password=<arg1>";
		protected final String tokenUri = "access_token=<arg>";
		
		//Ticket URIs
		protected final String ticketUri = "ticket/";
		//protected final String ticketUri2 = "ticket?";
		protected final String ticketUri2 = "ticketCMDB?";
		protected final String custom = "custom/";
		protected final String contact ="contact/";
		protected final String opsUri = "operations/";
		protected final String opsUri2 = "operation/";
				
		
		protected final String typeUri = "type?";
		protected final String statusUri = "status/organization?";
		protected final String organizationUri = "organizationCMDB?";
		protected final String active = "ticket/active?access_token=<arg>";
		protected final String activeCMDB = "ticket/activeCMDB?access_token=<arg>";
		
		protected final String comment = "operation/comment?";
		
		protected final String catUri = "category/byType?";
		
		
		public RestUrls() {
			 parser = new JSONParser();
		}
		
		//Auth Headers
		protected HttpHeaders getAuthHeaders() {
			String credentials = "client:client";
			String base64 = new String(Base64.encodeBase64(credentials.getBytes()));
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Basic " + base64);
			return headers;
		}
		

		//Rest Template
		protected RestTemplate getTemplate() {
			RestTemplate rest = new RestTemplate();
			rest.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
			return rest;
		}
		
		//Json Headers
		protected HttpHeaders getJsonHeaders() {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Accept", "application/json;charset=utf-8");
			return headers;
		}
		
		
}
