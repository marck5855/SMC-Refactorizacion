package mx.com.tp.smc.manager;

import java.util.ArrayList;
//import java.util.List;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.com.tp.smc.service.TokenServiceBack;
import mx.com.tp.smc.response.Status;
import mx.com.tp.smc.response.StatusList;
import mx.com.tp.smc.service.CatalogoService;


@Component
public class CatalogosManager {

	final static Logger logger = Logger.getLogger(CatalogosManager.class);
	
	@Autowired 
	private TokenServiceBack serviceToken;
	
	@Autowired
	private CatalogoService catalogoService;
	
	
	public StatusList getAllStatus() {
		DateTime initial = new DateTime();
		JSONArray array = null;
		boolean success = false;
		String error = "";
		String mssg = "Error al consultar el servicio";
		int total = 0;
		ArrayList<Status> listStatus = null; 
		
		try {
			String token = serviceToken.getToken();
			array = catalogoService.obtenerCatalogoStatus(token);
			
			listStatus = new ArrayList<Status>();
			
			for(int i=0; i<array.size();i++) {
				JSONObject json = (JSONObject) array.get(i);
				Status obj = new Status();
				obj.setIdStatus((Long) json.get("id"));
				obj.setDel((Long)json.get("del"));
				obj.setPersid((String)json.get("persid"));
				obj.setSym((String)json.get("sym"));
				obj.setDescription((String)json.get("description"));
				obj.setCode((String)json.get("code"));
				obj.setActive((Long)json.get("active"));
				listStatus.add(obj);
			}
			success = true;
			mssg = "Se consiguieron todos los estatus ";
		}catch(Exception e) {
			e.printStackTrace();
			mssg = "Ocurrio un error al obtener los estatus";
			error = "Error:" + e;
			logger.error("Error:" + e);
		}
		Period period = new Period(initial, new DateTime());
		return new StatusList(success, period, error, mssg, total, listStatus);
	}
	
	
}
