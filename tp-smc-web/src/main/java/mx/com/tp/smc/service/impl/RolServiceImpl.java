package mx.com.tp.smc.service.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import mx.com.tp.smc.entity.TrolEntity;
import mx.com.tp.smc.entity.TrolEntity2;
import mx.com.tp.smc.repository.TrolRepositoryBack;
import mx.com.tp.smc.response.Role;
import mx.com.tp.smc.response.RoleList;
import mx.com.tp.smc.service.RolService;
import mx.com.tp.smc.service.TrolDAO;

@Service("rolService")
@Transactional
@SuppressWarnings("unchecked")
public class RolServiceImpl extends RequestTemplate implements RolService {

//	@Autowired
//	private TrolDAO trolDAO;
	
	@Override
	public JSONObject insertRoles(String token, String rol, String descripcion, String idOrganizacion) throws Exception {
		
		System.out.println("VALORES ENVIADOS" + token + "/" + rol + "/" + descripcion + "/" + idOrganizacion);
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip")+p.getProperty("insertRoles");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("idOrganizacion", idOrganizacion);
		object.put("rol", rol);
		object.put("descripcion", descripcion);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		System.out.println("valores del response " + response);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

	@Override
	public JSONObject getRoles(String token) throws Exception {
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip")+p.getProperty("getRoles");
		uri = uri.replace("<token>", token);
		HttpEntity<String> request = new HttpEntity<String>("", getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

	@Override
	public JSONObject getAllOrganizations(String token) throws Exception {
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip")+p.getProperty("getOrganizations");
		uri = uri.replace("<token>", token);
		HttpEntity<String> request = new HttpEntity<String>("", getJsonHeaders());
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
		System.out.println("<============= LA URL ES ======================= "+ p.getProperty("ip")+p.getProperty("deleteRol"));
		String uri = p.getProperty("ip")+p.getProperty("deleteRol");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("idrole", idRol);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

	
	@Override
	public JSONObject deletePermiso(String token, BigDecimal idRol, Long idMenuDetalle, Long idMenuSubDetalle)
			throws Exception {
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip")+p.getProperty("deletePermiso");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("idrole", idRol);
		object.put("idMenuDetalle", idMenuDetalle);
		object.put("idMenuSubDetalle", idMenuSubDetalle);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

	
	public JSONObject getIdRol(String token, String rolRole) throws Exception {
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip")+p.getProperty("getIdRol");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("rolRole", rolRole);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

	
	public JSONObject getMenusByRol(String token, Long idRol) throws Exception {
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip") + p.getProperty("getMenusByRol");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("idRol", idRol);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

}
