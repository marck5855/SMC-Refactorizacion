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
//import ch.qos.logback.core.net.SyslogOutputStream;
import mx.com.tp.smc.service.TokenService;

@Service("tokenService")
@Transactional
public class TokenServiceImp extends RequestTemplate implements TokenService {

	@Override
	public String getToken(String user, String pass) throws Exception {
//		Properties p = new Properties();
//		InputStream input = null;
//		input = getClass().getClassLoader().getResourceAsStream("application.properties");
//		p.load(input);
//		String uri = p.getProperty("ip") + p.getProperty("getToken");
//		uri = uri.replace("<user>", user);
//		uri = uri.replace("<pass>", pass);
//		HttpEntity<String> request = new HttpEntity<String>(getAuthHeaders());
//		RestTemplate rest = getTemplate();
//		System.out.println("uri ------------>" + uri);
//		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
//		JSONObject obj = (JSONObject) new JSONParser().parse(response.getBody());
//		System.out.println("Token ---------->" + obj );
//		return (String) obj.get("access_token");
		return "";
			}

	@Override
	public String refreshToken(String token) throws Exception {

		return "";
	}
}