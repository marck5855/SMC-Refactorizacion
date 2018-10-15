package mx.com.tp.smc.service.impl;

//import java.io.IOException;
import java.math.BigDecimal;
//import java.util.Collections;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.stringtemplate.v4.ST;
import mx.com.tp.smc.commons.RestUrls;
//import mx.com.tp.smc.dictionary.Dictionary;
import mx.com.tp.smc.entity.DesService;
import mx.com.tp.smc.repository.TicketRepository;
//import mx.com.tp.smc.restRequest.Comentario;
import mx.com.tp.smc.service.TicketServiceBack;

@Service
public class TicketServiceImpl extends RestUrls implements TicketServiceBack {

	@Autowired
	private TicketRepository ticketRepository;

	// http://10.180.251.111:8080/ticketapi/custom/ticket/status/organization?access_token=<arg>
//	@Override
	public JSONObject getTicketByStatus(String token, String statusId, int year, int month, String organization)
			throws Exception {
		ST template = new ST(getRootUri() +  ticketUri + active + tokenUri);
		template.add("arg", token);
		String path = template.render();
		JSONObject object = new JSONObject();
		object.put("status", statusId);
		object.put("year", year);
		object.put("month", month);
		object.put("organization", organization);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(path, HttpMethod.POST, request, String.class);
		JSONObject obj = (JSONObject) parser.parse(response.getBody());
		return obj;
	}

	@SuppressWarnings("unchecked")
//	@Override // http://10.180.199.117:8080/ticketapi/ticket/active?access_token=<arg>
	public JSONObject getTicketsByOrganization(String token, int year, int month, String organization, String type)throws Exception {
		ST template = new ST(getRootUri() + activeCMDB);
		template.add("arg", token);
		String path = template.render();
		JSONObject json = new JSONObject();
		json.put("month", month);
		json.put("organization", organization);
		json.put("type", type);
		json.put("year", year);
		HttpEntity<String> request = new HttpEntity<String>(json.toString(), getJsonHeaders());
		System.out.println("************************************");
		ResponseEntity<String> response = getTemplate().exchange(path, HttpMethod.POST, request, String.class);
		System.out.println("mmm");
		JSONObject obj = (JSONObject) parser.parse(response.getBody());
		System.out.println("33333333");
		return obj;
	}

	// ST recibe la dirección ya concatenada:
	// http://10.180.251.111:8080/ticketapi/contact/organization?access_token=<arg>

//	@Override
	public JSONObject getPointByOrganization(String token, String organization, String tenant) throws Exception {
		ST template = new ST(getRootUri() + contact + organizationUri + tokenUri);
		template.add("arg", token);
		String path = template.render();
		JSONObject json = new JSONObject();
		json.put("organization", organization); // le enviamos la organizacion
		json.put("tenant", tenant); // le enviamos el tenant
		HttpEntity<String> request = new HttpEntity<String>(json.toString(), getJsonHeaders());
		ResponseEntity<String> response = getTemplate().exchange(path, HttpMethod.POST, request, String.class);
		JSONObject obj = (JSONObject) parser.parse(response.getBody());
		return obj;
	}

	

	// http://10.180.251.111:8080/ticketapi/category/all?access_token=<arg>
//	@Override
	public JSONObject getCategorys(String token, String bandeja) throws Exception {
		String uri = getRootUri() + catUri + tokenUri;
		uri = uri.replace("<arg>", token);
		JSONObject object = new JSONObject();
		object.put("type", bandeja);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

//	@Override
	public List<DesService> getAllTicketNotConcilied(String folioStatus , String organizacion) {
		System.out.println("VALOR DE LA ORG " + organizacion + folioStatus);
		return ticketRepository.findByFolioStatusAndOrganizacion(folioStatus,organizacion);
	}

//	@Override
	public List<DesService> getAllTicketNotConciliedStatusCloseAndValidate(String folioStatus1, String folioStatus2) {

		return ticketRepository.findByFolioStatusAndFolioStatus(folioStatus1, folioStatus2);
	}

//	@Override
	public DesService getTicketNotConcilied(BigDecimal folioIncidencia) {

		return ticketRepository.findByFolioIncidencia(folioIncidencia);

	}

//	@Override
	public void updateTicketNotConcilied(DesService ue) {
		ticketRepository.save(ue);
	}

//	@Override
	public List<DesService> getAllTicketClosed(String folioStatus, String organizacion) {

		return ticketRepository.findByFolioStatusAndOrganizacion(folioStatus, organizacion);
	}

//	@Override
	public List<DesService> getByStatusCloseAndValidation(String[] status, String organization) {
		return ticketRepository.findByFolioStatusInAndOrganizacion(status,organization);
	}

	// http://10.180.251.111:8080/ticketapi/ticket/status?access_token=<arg>
//	@Override
	public JSONObject getAllTicketsClosed(String token, int month, String statusId, String type, int year)
			throws Exception {
		ST template = new ST(getRootUri() + custom + ticketUri + statusUri + tokenUri);
		template.add("arg", token);
		String path = template.render();
		JSONObject object = new JSONObject();
		object.put("status", statusId);
		object.put("year", year);
		object.put("month", month);
		object.put("type", type);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(path, HttpMethod.POST, request, String.class);
		JSONObject obj = (JSONObject) parser.parse(response.getBody());
		return obj;
	}

//	@Override
	public List<DesService> getAllTickets() {

		return ticketRepository.findAll();
	}

//	@Override
	public void saveAllTicketsClosed(DesService ue) {
		ticketRepository.save(ue);
	}

//	@Override
	public JSONObject getAddComentario(String token, String refNum, String comment, String creatorUUID)throws Exception {
		// TODO Auto-generated method stub
		ST template = new ST("http://10.180.199.117:8080/ticketapi/operation/comment?access_token=<arg>");
//		ST template = new ST("http://10.180.251.16:8080/ticketapi/operation/comment?access_token=<arg>");
		template.add("arg", token);
		String path = template.render();
		JSONObject object = new JSONObject();
		object.put("refNum", refNum);
		object.put("comment", comment);
		object.put("creatorUUID", creatorUUID);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(path, HttpMethod.POST, request, String.class);
		JSONObject obj = (JSONObject) parser.parse(response.getBody());
		return obj;
	}

//	@Override
	public JSONObject getUpdateStatus(String token, String refNum, String sym, String region) throws Exception {
		ST template = new ST("http://10.180.199.117:8080/ticketapi/operation/status?access_token=<arg>");
//		ST template = new ST("http://10.180.251.16:8080/ticketapi/operation/status?access_token=<arg>");
		template.add("arg", token);
		String path = template.render();
		JSONObject object = new JSONObject();
		object.put("refNum", refNum);
		object.put("sym", sym);
		object.put("creator", "SMC");
		object.put("comment", "Se cambia el status del ticket");
		object.put("justificacion", "aaaa");
		object.put("diagFinal", "400051");
		object.put("solution", "400051");
		object.put("actRealizadas", "actividades realizadas");
		object.put("areaResultora", "area resolutora");
		object.put("nivelSoporte", "N2");
		object.put("RegCiuId", region);
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(path, HttpMethod.POST, request, String.class);
		JSONObject obj = (JSONObject) parser.parse(response.getBody());
		return obj;
	}

//	@Override
	public JSONObject insertTicket(String token,String organizacion, String applicant, String category,
			String[] description, String region, String insertar) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("REST ADRI " + applicant);
//		ST template = new ST("http://10.180.251.152:8080/ticketapi/operation/preticket-CARE?access_token=<arg>");
		ST template = new ST("http://10.180.199.117:8080/ticketapi/operation/ticketCMDB-CARE?access_token=<arg>");
//		ST template = new ST("http://10.180.251.16:8080/ticketapi/operation/preticket-CARE?access_token=<arg>");
		template.add("arg", token);
		
		String path = template.render();
		
		JSONArray arr=new JSONArray();
		arr.add(description[0]);
		
		JSONObject object = new JSONObject();
//		object.put("requestBy", "SMC");
//		object.put("peackId", applicant);
//		object.put("categoryId", category);
//		object.put("description", arr);
//		object.put("type", "I");
//		object.put("summary", "esto es una prueba");
//		object.put("diagInitial", "Diagnostico Inicial");
//		object.put("networkType", "");
//		object.put("regCiuId", region);
//		object.put("createdVia","");
//		object.put("url", "");
		
		object.put("requestBy", "SMC");
		object.put("peack", applicant);
		object.put("categoryId", category);
		object.put("desc", arr);
		object.put("summary", "esto es una prueba");
		object.put("type", "I");
		object.put("diagInitial", "Diagnostico Inicial");
		object.put("networkType", "");
		object.put("regCiuId", region);
		object.put("url", "");
		
		
		System.out.println("=== VALOR DE OBJECT === " + object.toJSONString(object));
		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(path, HttpMethod.POST, request, String.class);
		JSONObject obj = (JSONObject) parser.parse(response.getBody());
		return obj;
	}

	


}
