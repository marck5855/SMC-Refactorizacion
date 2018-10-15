package mx.com.tp.smc.util;

import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * @author dazaeev
 */
public class Utils implements Serializable {
	private static final long serialVersionUID = 1793156339115484824L;

	public HttpHeaders getJsonHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Accept", "application/json;charset=utf-8");
		return headers;
	}

	public RestTemplate getTemplate() {
		RestTemplate rest = new RestTemplate();
		rest.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		return rest;
	}

	public String getEnviroment(String name) {
		String resp = "";
		try {
			Properties p = new Properties();
			InputStream input = null;

			input = getClass().getClassLoader().getResourceAsStream("application.properties");
			p.load(input);

			String uri = p.getProperty("getEnviroment");
			uri = uri.replace("<name>", name);
			HttpEntity<String> request = new HttpEntity<String>("", getJsonHeaders());
			RestTemplate rest = getTemplate();
			ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
			JSONObject json = (JSONObject) new JSONParser().parse(response.getBody());
			if (json.get("status").equals("OK")) {
				resp = "" + json.get("enviroment");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
}
