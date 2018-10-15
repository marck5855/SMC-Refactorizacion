package mx.com.tp.smc.mgr;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.com.tp.smc.entity.TicketClose;
import mx.com.tp.smc.manager.IncidentsManager;
import mx.com.tp.smc.model.TicketCloseModel;
import mx.com.tp.smc.request.Organization;
import mx.com.tp.smc.response.TicketClosed;
import mx.com.tp.smc.response.TicketClosedList;
import mx.com.tp.smc.service.impl.TicketServiceImp;
import mx.com.tp.smc.service.TokenService;

@Component
public class ConsultMgr {

	final static Logger logger = Logger.getLogger(ConsultMgr.class);

	@Autowired
	private TicketServiceImp ticketService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private IncidentsManager incidentsManager;
	
//	Marcko Funcional
	public TicketCloseModel getAllClosedTickets(String organization) {
		TicketCloseModel model = new TicketCloseModel();
		Organization request = new Organization();
		request.setOrganization(organization);
		String nombreSolicitante="";
		try {
			
			TicketClosedList responseManager = incidentsManager.returnAllClosed(request.getOrganization());
			System.out.println("****getAllClosedTickets/getListTicket***"+responseManager.getListTicket());
			
			if(responseManager.getListTicket() != null || responseManager.getListTicket().isEmpty()) {
				ArrayList<TicketClose> tickets = new ArrayList<TicketClose>();

				for (TicketClosed ticketresponse : responseManager.getListTicket()) {

					TicketClose ticket = new TicketClose();
					
					ticket.setDescripcion((String)ticketresponse.getDescripcion());
					ticket.setCategoria((String)ticketresponse.getCategoria());
					ticket.setTicketSExterno((String)ticketresponse.getTicketSExterno());
					ticket.setApeSolicitante((String)ticketresponse.getApeSolicitante());
					ticket.setNomSolicitante((String)ticketresponse.getNomSolicitante());
					ticket.setNom2Solicitante((String)ticketresponse.getNom2Solicitante());
					ticket.setFolioIncidencia(ticketresponse.getFolioIncidencia().longValue());
					ticket.setFolioAbierto((String)ticketresponse.getFolioAbierto());
					ticket.setResumen((String)ticketresponse.getResumen());
					ticket.setFolioTitulo3((String)ticketresponse.getFolioTitulo3());
					ticket.setTiempoImputableCte((String)ticketresponse.getTiempoImputableCte());
					ticket.setTiempoImputableTpe((String)ticketresponse.getTiempoImputableTpe());
					ticket.setFolioStatus((String)ticketresponse.getFolioStatus());
					ticket.setFolioCerrado((String)ticketresponse.getFolioCerrado());
					ticket.setFolioTitulo((String)ticketresponse.getFolioTitulo());
					ticket.setDiagFinal((String)ticketresponse.getDiagFinal());
					ticket.setSolucion((String)ticketresponse.getSolucion());
					ticket.setAfectacion((String)ticketresponse.getAfectacion());
					ticket.setZesProactivoReactivo((String)ticketresponse.getZesProactivoReactivo());
					ticket.setZtiempoFallaTerceros((String)ticketresponse.getZtiempofallaTerceros());
					ticket.setZtiempoFallaProv((String)ticketresponse.getZtiempofallaProv());
					ticket.setOrganizacion(organization);
					
					nombreSolicitante = ticketresponse.getNomSolicitante()+ticketresponse.getNom2Solicitante()+ticketresponse.getApeSolicitante();
					
					ticket.setNombreSolicitante(nombreSolicitante);
					

					tickets.add(ticket);
				}
				model.setTickets(tickets);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

}

//Versión Anterior
//public TicketCloseModel getAllClosedTickets(String organization) {
//	TicketCloseModel model = new TicketCloseModel();
//	String nombreSolicitante="";
//	try {
//		JSONObject json = ticketService.getAllClosedTickets(tokenService.getToken("adri", "adri"), organization);
//		
//		System.out.println("****getAllClosedTickets/JSONObject***"+json);
//		
//		
//		if ((Boolean) json.get("success")) {
//			JSONArray array = (JSONArray) json.get("listTicket");
//			
//			System.out.println("****getAllClosedTickets/JSONArray***"+array);
//			
//			ArrayList<TicketClose> tickets = new ArrayList<TicketClose>();
//
//			for (int i = 0; i < array.size(); i++) {
//				JSONObject object = (JSONObject) array.get(i);
//				TicketClose ticket = new TicketClose();
//				ticket.setDescripcion((String) object.get("descripcion"));
//				ticket.setCategoria((String) object.get("categoria"));
//				ticket.setTicketSExterno((String) object.get("ticketSExterno"));
//				ticket.setApeSolicitante((String)object.get("apeSolicitante"));
//				ticket.setNomSolicitante((String)object.get("nomSolicitante"));
//				ticket.setNom2Solicitante((String)object.get("nom2Solicitante"));
//				ticket.setFolioIncidencia((Long) object.get("folioIncidencia"));
//				ticket.setFolioAbierto((String) object.get("folioAbierto"));
//				ticket.setResumen((String) object.get("resumen"));
//				ticket.setFolioTitulo3((String) object.get("folioTitulo3"));
//				ticket.setTiempoImputableCte((String) object.get("tiempoImputableCte"));
//				ticket.setTiempoImputableTpe((String) object.get("tiempoImputableTpe"));
//				ticket.setFolioStatus((String) object.get("folioStatus"));
//				ticket.setFolioCerrado((String) object.get("folioCerrado"));
//				ticket.setFolioTitulo((String) object.get("folioTitulo"));
//				ticket.setDiagFinal((String) object.get("diagFinal"));
//				ticket.setSolucion((String) object.get("solucion"));
//				ticket.setAfectacion((String) object.get("afectacion"));
//				ticket.setZesProactivoReactivo((String) object.get("zesProactivoReactivo"));
//				ticket.setZtiempoFallaTerceros((String) object.get("ztiempofallaTerceros"));
//				ticket.setZtiempoFallaProv((String) object.get("ztiempofallaProv"));
//				ticket.setOrganizacion(organization);
//				
//				nombreSolicitante = (String) object.get("nomSolicitante")+(String) object.get("nom2Solicitante")+(String) object.get("apeSolicitante");
//				
//				ticket.setNombreSolicitante(nombreSolicitante);
//				
//
//				tickets.add(ticket);
//			}
//			model.setTickets(tickets);
//
//		}
//
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	return model;
//}
//
//}

