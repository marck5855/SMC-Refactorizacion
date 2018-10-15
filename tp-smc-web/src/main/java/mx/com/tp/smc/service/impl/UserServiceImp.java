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

import mx.com.tp.smc.service.UserService;

@Service("userService")
@Transactional
@SuppressWarnings("unchecked")
public class UserServiceImp extends RequestTemplate implements UserService {

	@Override
	public void createUser() {
	}

	@Override
	public void updateUser() {
	}

	@Override
	public void changePassword() {
	}

	@Override
	public JSONObject getAllUsers(String token,String username,String organization, String createuser) throws Exception {
		System.out.println("==============        EL VALOR DE LA ORGANIZACION QUE LLEGA AL REST ES    ================= " + username );
		System.out.println("==============        EL VALOR DE LA ORGANIZACION QUE LLEGA AL REST ES    ================= " + organization);
		System.out.println("==============        EL VALOR DE LA ORGANIZACION QUE LLEGA AL REST ES    ================= " + createuser);
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip") + p.getProperty("getAllUsers");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
			object.put("organization", organization);
			object.put("username", username);
			object.put("createuser", createuser);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

	@Override
	public JSONObject insertUsers(String token, String username, String name, String organization, String password,
			BigDecimal idrole, String createuser, String noUsr) throws Exception {
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip") + p.getProperty("insertUsers");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("organization", organization);
		object.put("password", password);
		object.put("username", username);
		object.put("name", name);
		object.put("idRol", idrole);
		object.put("enabled", 1);
		object.put("createuser", createuser);
		object.put("usersnum", noUsr);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

	@Override
	public JSONObject updateUsers(String token, String username, String name, String organization, String password)
			throws Exception {
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip") + p.getProperty("updateUsers");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("organization", organization);
		object.put("password", password);
		object.put("username", username);
		object.put("name", name);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

	@Override
	public JSONObject deleteUsers(String token, String username) throws Exception {
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip") + p.getProperty("deleteUsers");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("username", username);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

	@Override
	public JSONObject loginUsers(String token, String username, String password) throws Exception {
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip") + p.getProperty("loginUsers");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("username", username);
		object.put("password", password);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

	@Override
	public JSONObject getRolesByOrganizacion(String token, String organization) throws Exception {
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip") + p.getProperty("getRolesByOrganization");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("organization", organization);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());

	}

	@Override
	public JSONObject updateRoleUser(String token, String username, BigDecimal idrol) throws Exception {
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip") + p.getProperty("updateRoleUser");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("username", username);
		object.put("idRol", idrol);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

//	@Override
	public JSONObject getOrganizations(String token, String organization) throws Exception {
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip") + p.getProperty("getAllOrganizations");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("organization", organization);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

	//Ok
	@Override
	public JSONObject getOrganizationByUser(String token, String username) throws Exception {
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip") + p.getProperty("getOrganizationByUser");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("username", username);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}
	//ok
	@Override
	public JSONObject getRolByUser(String token, String username) throws Exception {
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip") + p.getProperty("getRolByUser");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("username", username);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}
}
