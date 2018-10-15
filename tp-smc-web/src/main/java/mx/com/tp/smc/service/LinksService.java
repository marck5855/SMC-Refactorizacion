
package mx.com.tp.smc.service;

import java.math.BigDecimal;

import org.json.simple.JSONObject;

public interface LinksService {

	JSONObject getLinks(String token) throws Exception;
	JSONObject getLinks(String token, String organization) throws Exception;
	JSONObject insertLinks(String token,String nombreLink, String link, String organization, String descripcionLink) throws Exception;
	JSONObject deleteRol(String token, BigDecimal idRol) throws Exception;
}
