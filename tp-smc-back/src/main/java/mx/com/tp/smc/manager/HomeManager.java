package mx.com.tp.smc.manager;

//import java.util.ArrayList;
//import java.util.List;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.com.tp.smc.dictionary.Dictionary;
import mx.com.tp.smc.response.DataGraphic;
//import mx.com.tp.smc.entity.CatOrigenHomeEntity;
//import mx.com.tp.smc.entity.TrolEntity;
//import mx.com.tp.smc.service.OrigenHomeService;
import mx.com.tp.smc.service.TicketServiceBack;
import mx.com.tp.smc.service.TokenServiceBack;

@Component
public class HomeManager {

	@Autowired
	private TicketServiceBack serviceTicket;

	@Autowired
	private TokenServiceBack serviceToken;

	

	final static Logger log = Logger.getLogger(HomeManager.class);

	public DataGraphic getGraphicTicket(String organization) {
		DateTime initial = new DateTime();
		int close = 0, active = 0, solved = 0, total = 0;
		boolean success = false;
		String error = "";
		String mssg = "Error al consultar el servicio";
		try {
			String token = serviceToken.getToken();
			if (token != null) {
				JSONObject response = serviceTicket.getTicketsByOrganization(token, initial.getYear(),
						initial.getMonthOfYear(), organization, Dictionary.TYPE);
				success = (Boolean) response.get("success");
				if (success && response.get("tickets") != null) {
					JSONArray array = (JSONArray) response.get("tickets");
					for (int i = 0; i < array.size(); i++) {
						JSONObject jsonObject = (JSONObject) array.get(i);
						String status = (String) jsonObject.get("status");
						if (status.equalsIgnoreCase(Dictionary.STATUS_CERRADO)) {
							close++;
						}
						if (status.equalsIgnoreCase(Dictionary.STATUS_ENCURSO)) {
							active++;
						}
						if (status.equalsIgnoreCase(Dictionary.STATUS_RESUELTO)) {
							solved++;
						}
					}
					total = array.size();
					mssg = "Exito al conseguir los datos para la grafica";
					success = true;
				}
			}

		} catch (Exception ex) {
			mssg = "Fallo al mostrar grafica";
			error = "Error:" + ex;
			log.error("Error en grafica tickets home:" + ex);
		}

		Period period = new Period(initial, new DateTime());
		return new DataGraphic(success, period, error, mssg, total, close, active, solved);
	}

	
}
