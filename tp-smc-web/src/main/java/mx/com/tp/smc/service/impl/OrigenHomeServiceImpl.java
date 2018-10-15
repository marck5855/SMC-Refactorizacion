package mx.com.tp.smc.service.impl;

import java.io.InputStream;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import mx.com.tp.smc.service.OrigenHomeService;

@Service("origenHomeService")
@Transactional
@SuppressWarnings("unchecked")
public class OrigenHomeServiceImpl extends RequestTemplate implements OrigenHomeService {

	@Override
	public JSONObject getOrigenHome(String token) throws Exception {

		Properties p = new Properties();
		InputStream input = null;

		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);

		String uri = p.getProperty("ip")+p.getProperty("getOrigenHome");
		uri = uri.replace("<token>", token);
		HttpEntity<String> request = new HttpEntity<String>("", getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

}
