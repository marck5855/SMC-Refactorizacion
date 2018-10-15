package mx.com.tp.smc.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.stringtemplate.v4.ST;


import mx.com.tp.smc.commons.RestUrls;

@Service
public class CatalogoService extends RestUrls{
	
	public JSONArray obtenerCatalogoStatus(String token) throws Exception {
			ST template = new ST("http://10.180.199.117:8080/ticketapi/catalogo/status?access_token=<arg>");
			template.add("arg", token);
			String path = template.render();
			HttpEntity<String> request = new HttpEntity<String>("", getJsonHeaders());
			ResponseEntity<String> response = getTemplate().exchange(path, HttpMethod.POST, request, String.class);
			JSONArray obj = (JSONArray) parser.parse(response.getBody());
			return obj;
		
	}
	
	public JSONObject obtenerComentariosTicket(String token, String refNum, String type) throws Exception {
			ST template = new ST("http://10.180.199.117:8080/ticketapi/ticket/comment?access_token=<arg>");
			template.add("arg", token);
			String path = template.render();
			JSONObject object = new JSONObject();
			object.put("refNum", refNum);
			object.put("type", type);
			HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
			ResponseEntity<String> response = getTemplate().exchange(path, HttpMethod.POST, request, String.class);
			JSONObject obj = (JSONObject) parser.parse(response.getBody());
			return obj;
	}
	
	public JSONArray getCatRegiones(String token) throws Exception {
		ST template = new ST("http://10.180.199.117:8080/ticketapi/catalogo/regionesCiudades?access_token=<arg>");
		template.add("arg", token);
		String path = template.render();
		HttpEntity<String> request = new HttpEntity<String>("", getJsonHeaders());
		ResponseEntity<String> response = getTemplate().exchange(path, HttpMethod.POST, request, String.class);
		JSONArray obj = (JSONArray) parser.parse(response.getBody());
		return obj;
	
}

}
