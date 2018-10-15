package mx.com.tp.smc.service.impl;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.stringtemplate.v4.ST;

import mx.com.tp.smc.commons.RestUrls;
import mx.com.tp.smc.service.TokenServiceBack;

@Service
public class TokenServiceImpl extends RestUrls implements TokenServiceBack {
    //URL : http://10.180.251.111:8080/ticketapi/oauth/token?grant_type=password&username=<arg0>&password=<arg1>
//	@Override
	public String getToken() throws Exception {
		ST template = new ST(getRootUri() + oauthUri + authUri);//esto es la url de envio al web services, se llama a la clase ST para formatear la cadena , estas variables
		//se encuentran inicializadas en la clase RestUrls del paquete com.enlacetp.commons
		template.add("arg0", "portalSMC"); // indcamos que rellene el parametro de username=<arg0>, con el usuario portal
		template.add("arg1", "portalSMC");// indcamos que rellene el parametro de username=<arg1>, la contraseña portal , esto va a cambiar una vez los usuarios se loguen, esto es temporal para las pruebas del desarrollo
		String path = template.render().toString();
		HttpEntity<String> request =  new HttpEntity<String>(getAuthHeaders());
		System.out.println("request---->"+ request.toString());
		System.out.println("request---->"+ path);
		RestTemplate rest = getTemplate();
		ResponseEntity<String> response = rest.exchange(path, HttpMethod.POST, request, String.class);// ejecuta la url renderizada en el path, para ejecutar el webservice
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(response.getBody());//nos trae todo las variable y resultados emitido por el web service
		return (String) obj.get("access_token"); //access_token es una variable  contiene la cadena del token, la separamos para obtener sòlo su resultado, descartando los demas resultados que nos envió el webservices.
	}



}
