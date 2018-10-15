package mx.com.tp.smc.service.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import mx.com.tp.smc.service.TicketService;


@Service("ticketService1")
@Transactional
@SuppressWarnings("unchecked")
public class TicketServiceImp extends RequestTemplate implements TicketService{

	
	@Override
	public JSONObject getTicketByOrganization(String token, String organization) throws Exception {
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip") + p.getProperty("getTicketByOrganization");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("organization", organization);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

	
	@Override
	public JSONObject getTicketGraph(String token, String organization) throws Exception {
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip") + p.getProperty("getTicketGraph");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("organization", organization);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

	
	@Override
	public JSONObject getAllTicketNorConcilied(String token, String organization) throws Exception {
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip") + p.getProperty("getAllTicketNorConcilied");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("organization", organization);

		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

	
	public JSONObject getAllTicketStatusValidation(String token , String organization) throws Exception {
		
		System.out.println("ORGANIZACION " + organization);
		
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip") + p.getProperty("getAllTicketStatusValidation");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("organization", organization);

		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

	
	@Override
	public JSONObject updateTicketNorConcilied(String token, BigDecimal folioincidencia, String nombre1, String nombre2,
			String apellidos, String categoria, String usuariofinal, String descripcion, String solucion,
			String fechasolucion, String fechaapertura, String afectadocliente, String diagnosticofinal,
			String tiempofallacliente, String tiempofallaenlace, String resumen, String proactivoReactivo , String afectacion, String ztiempoFallaTer,String ztiempoFallaProv) throws Exception {
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip") + p.getProperty("updateTicketNorConcilied");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("folioIncidencia", folioincidencia);
		object.put("apeSolicitante", apellidos);
		object.put("categoria", categoria);
		object.put("descripcion", descripcion);
		object.put("diagFinal", diagnosticofinal);
		object.put("folioAbierto", fechaapertura);
		object.put("folioCerrado", fechasolucion);
		object.put("folioTitulo", usuariofinal);
		object.put("folioTitulo3", afectadocliente);
		object.put("nomSolicitante", nombre1);
		object.put("nom2Solicitante", nombre2);
		object.put("resumen", resumen);
		object.put("solucion", solucion);
		object.put("tiempoImputableCte", tiempofallacliente);
		object.put("tiempoImputableTpe", tiempofallaenlace);
		object.put("proactivoReactivo", proactivoReactivo);
		object.put("afectacion", afectacion);
		object.put("ztiempoFallaTer", ztiempoFallaTer);
		object.put("ztiempoFallaProv", ztiempoFallaProv);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

//	ok
	@Override
	public JSONObject updateStatusTicketNorConcilied(String token, BigDecimal folioincidencia) throws Exception {
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip") + p.getProperty("updateStatusTicketNorConcilied");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("folioIncidencia", folioincidencia);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

	
	@Override
	public JSONObject updateTicketPorValidar(String token, BigDecimal folioincidencia) throws Exception {
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip") + p.getProperty("updateTicketPorValidar");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("folioIncidencia", folioincidencia);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

	
	@Override
	public JSONObject updateStatusTicketNorConciliedValidation(String token, BigDecimal folioincidencia, String nombre1,
			String nombre2, String apellidos, String categoria, String usuariofinal, String descripcion,
			String solucion, String fechasolucion, String fechaapertura, String afectadocliente,
			String diagnosticofinal, String tiempofallacliente, String tiempofallaenlace, String resumen,String proactivoReactivo, String afectacion,String ztiempoFallaTer, String ztiempoFallaProv)
			throws Exception {
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip") + p.getProperty("updateStatusTicketNorConciliedValidation");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("folioIncidencia", folioincidencia);
		object.put("apeSolicitante", apellidos);
		object.put("categoria", categoria);
		object.put("descripcion", descripcion);
		object.put("diagFinal", diagnosticofinal);
		object.put("folioAbierto", fechaapertura);
		object.put("folioCerrado", fechasolucion);
		object.put("folioTitulo", usuariofinal);
		object.put("folioTitulo3", afectadocliente);
		object.put("nomSolicitante", nombre1);
		object.put("nom2Solicitante", nombre2);
		object.put("resumen", resumen);
		object.put("solucion", solucion);
		object.put("tiempoImputableCte", tiempofallacliente);
		object.put("tiempoImputableTpe", tiempofallaenlace);
		object.put("proactivoReactivo", proactivoReactivo);
		object.put("afectacion", afectacion);
		object.put("ztiempoFallaTer", ztiempoFallaTer);
		object.put("ztiempoFallaProv", ztiempoFallaProv);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

	
//	@Override
//	public JSONObject getCategories(String token, String tenant) throws Exception {
//		String[] tenantSplit = tenant.split("\\ - ");
//		if (tenantSplit.length > 0) {
//			tenant = tenantSplit[0];
//		}
//		System.out.println("tenant: " + tenant);
//		Properties p = new Properties();
//		InputStream input = null;
//		input = getClass().getClassLoader().getResourceAsStream("application.properties");
//		p.load(input);
//		String uri = p.getProperty("ip") + p.getProperty("getCategories");
//		uri = uri.replace("<token>", token);
//		uri += "&tenant=" + tenant;
//		HttpEntity<String> request = new HttpEntity<String>("", getJsonHeaders());
//		RestTemplate rest = getTemplate();
//		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
//		return (JSONObject) new JSONParser().parse(response.getBody());
//	}

	
	@Override
	public JSONObject getCategories(String token, String bandeja) throws Exception {
//		String[] tenantSplit = tenant.split("\\ - ");
//		if (tenantSplit.length > 0) {
//			tenant = tenantSplit[0];
//		}
		System.out.println("BANDEJA: " + bandeja);
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip") + p.getProperty("getCategories");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("type", bandeja);
		
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}
	
	//ok
	@Override
	public JSONObject getAllClosedTickets(String token, String organization) throws Exception {
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip") + p.getProperty("getAllClosedTickets");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("organization", organization);

		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

	
	@Override
	public JSONObject getPointByOrganization(String token, String organization) throws Exception {
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip") + p.getProperty("getPointByOrganization");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("organization", organization);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

	
	
	public JSONObject addIncident(String token, String aplicant, String idCategory, String description, String insertar)
			throws Exception {
		System.out.println(" === REST INSERTAR INSIDENTE === " + aplicant + " === " + idCategory + " === " + description);
		
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip") + p.getProperty("addIncident");
		uri = uri.replace("<token>", token);
		JSONArray arr = new JSONArray();
		arr.add(String.valueOf(description));
		JSONObject object = new JSONObject();
		object.put("applicant", aplicant);
		object.put("category", idCategory);
		object.put("description", arr);
		object.put("uri", insertar);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}
	
	@Override
	public JSONObject agregarComentario(String token, String incidente,String comentario) throws Exception{
		System.out.println(" === REST INSERTAR COMENTARIO === " + incidente + " === " + comentario );
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip") + p.getProperty("getAgregarComentario");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("refNum", incidente);
		object.put("comment", comentario);
		object.put("creatorUUID", "0xa67b58efebf0764587868b997b0f7e40");
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}
	
	@Override
	public JSONObject getAllStatus(String token) throws Exception{
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip") + p.getProperty("getAllStatus");
		uri = uri.replace("<token>", token);
		HttpEntity<String> request = new HttpEntity<String>("", getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}


	public JSONObject getAllComentarios(String token, String refNum)throws Exception {
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip") + p.getProperty("getAllComentarios");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("refNum", refNum);
		object.put("type", "I");
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}


	@Override
	public JSONObject getUpdateStatus(String token, String incidente, String status, String region) throws Exception{
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip") + p.getProperty("getUpdateStatus");
		uri = uri.replace("<token>", token);
		JSONObject object = new JSONObject();
		object.put("refNum", incidente);
		object.put("sym", status);
		System.out.println("=== valor del objeto ===" + object );
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		System.out.println("=== valor del request ===" + request);
		RestTemplate rest = getTemplate();
		System.out.println("=== valor del reST ===" + rest);
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		System.out.println("=== valor del response ===" + response);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}


	@Override
	public JSONObject getCatRegiones(String token) throws Exception{
		Properties p = new Properties();
		InputStream input = null;
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);
		String uri = p.getProperty("ip") + p.getProperty("getreg");
		uri = uri.replace("<token>", token);
		System.out.println("=== URL === " + uri);
		HttpEntity<String> request = new HttpEntity<String>("", getJsonHeaders());
		System.out.println("=== URL 2=== " + request);
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		System.out.println("=== URL 4=== " + response);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

}