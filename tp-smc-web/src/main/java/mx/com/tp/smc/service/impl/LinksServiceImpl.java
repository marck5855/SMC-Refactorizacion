
package mx.com.tp.smc.service.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import mx.com.tp.smc.service.LinksService;

@Service("linksService")
@Transactional
@SuppressWarnings("unchecked")
public class LinksServiceImpl extends RequestTemplate implements LinksService {

	@Override
	public JSONObject getLinks(String token) throws Exception {

		Properties p = new Properties();
		InputStream input = null;

		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);

		String uri = p.getProperty("ip")+p.getProperty("getLinks");
		uri = uri.replace("<token>", token);
		HttpEntity<String> request = new HttpEntity<String>("", getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}
	
	@Override
	public JSONObject getLinks(String token, String organization) throws Exception {
		Properties p = new Properties();
		InputStream input = null;

		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);

		String uri = p.getProperty("ip")+p.getProperty("getLinksOrganization");
		uri = uri.replace("<token>", token).replace("<organization>", organization);
		HttpEntity<String> request = new HttpEntity<String>("", getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

	@Override
	public JSONObject insertLinks(String token, String nombreLink, String link, String organization, String descripcionLink) throws Exception {

		Properties p = new Properties();
		InputStream input = null;

		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);

		String uri = p.getProperty("ip")+p.getProperty("insertLinks");

		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("nombreLink", nombreLink);
		object.put("link", link);
		object.put("organization", organization);
		object.put("descripcionLink", descripcionLink);

		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

	@Override
	public JSONObject deleteRol(String token, BigDecimal idRol) throws Exception {

		Properties p = new Properties();
		InputStream input = null;

		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);

		String uri = p.getProperty("ip")+p.getProperty("deleteRol");

		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("idrole", idRol);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}
}
