package mx.com.tp.smc.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.apache.commons.codec.binary.Base64;
//import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.multipart.MultipartFile;

public class RequestTemplate {

	protected HttpHeaders getJsonHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Accept", "application/json;charset=utf-8");
		return headers;
	}
	
	protected HttpHeaders getAuthHeaders() {
		String credentials = "client:client";
		String base64 = new String(Base64.encodeBase64(credentials.getBytes()));
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64);
		return headers;
	}
	
	protected RestTemplate getTemplate() {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		return rest;
	}

	
}
