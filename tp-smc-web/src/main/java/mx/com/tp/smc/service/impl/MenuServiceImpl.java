
package mx.com.tp.smc.service.impl;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import mx.com.tp.smc.service.MenuService;

@Service("menuService")
@Transactional
@SuppressWarnings("unchecked")
public class MenuServiceImpl extends RequestTemplate implements MenuService {

	@Override
	public JSONObject getPrincipalMenus(String token) throws Exception {

		Properties p = new Properties();
		InputStream input = null;

		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);

		String uri = p.getProperty("ip")+p.getProperty("getMenusPrincipales");

		uri = uri.replace("<token>", token);
		HttpEntity<String> request = new HttpEntity<String>("", getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}
	
	@Override
	public JSONObject getSubMenu(String token, Long idMenu) throws Exception {

		Properties p = new Properties();
		InputStream input = null;

		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);

		String uri = p.getProperty("ip")+p.getProperty("getSubMenu");

		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("idMenu", idMenu);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}
	
	@Override
	public JSONObject permisosAdd(String token,Long idRol, List<Long> idMenu, List<Long> idMenuSub, Long idHome) throws Exception {

		Properties p = new Properties();
		InputStream input = null;

		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);

		String uri = p.getProperty("ip")+p.getProperty("addPermisosRol");

		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("idRol", idRol);
		object.put("idMenu", idMenu);
		object.put("idMenuSub", idMenuSub);
		object.put("idHome", idHome);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}
}
