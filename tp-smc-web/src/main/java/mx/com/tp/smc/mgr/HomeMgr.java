package mx.com.tp.smc.mgr;

//import java.io.File;
//import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.InputStream;
import java.math.BigDecimal;
//import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
//import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.itextpdf.text.log.SysoCounter;

//import mx.com.tp.smc.conf.ConexionCMDB;
import mx.com.tp.smc.conf.Constant;
//import mx.com.tp.smc.conf.ConexionGETL;
import mx.com.tp.smc.entity.CatArchivos;
//import mx.com.tp.smc.entity.CatCarpetas;
import mx.com.tp.smc.entity.CatOrigenHome;
import mx.com.tp.smc.entity.CatRegiones;
import mx.com.tp.smc.entity.Categories;
//import mx.com.tp.smc.entity.Comment;
import mx.com.tp.smc.entity.Point;
import mx.com.tp.smc.entity.PointCMDB;
//import mx.com.tp.smc.entity.Role;
import mx.com.tp.smc.entity.Status;
import mx.com.tp.smc.entity.Ticket;
import mx.com.tp.smc.entity.TicketConcilied;


import mx.com.tp.smc.model.CatRegionesModel;
import mx.com.tp.smc.model.CategoriesModel;
//import mx.com.tp.smc.model.CommentModel;
import mx.com.tp.smc.model.FileModel;
import mx.com.tp.smc.model.HomeModel;
import mx.com.tp.smc.model.OrigenHomeModel;
import mx.com.tp.smc.model.PointModel;
//import mx.com.tp.smc.model.RoleModel;
import mx.com.tp.smc.model.StatusModel;
import mx.com.tp.smc.model.TicketConciliedModel;
import mx.com.tp.smc.model.TicketModel;
import mx.com.tp.smc.request.AddComentario;
import mx.com.tp.smc.request.AddIncident;
import mx.com.tp.smc.request.Comentario;
import mx.com.tp.smc.request.Organization;
import mx.com.tp.smc.request.Regiones;
import mx.com.tp.smc.request.Tenant;
import mx.com.tp.smc.request.TicketNotConciliedValidationUpdate;
import mx.com.tp.smc.request.UpdateStatus;
import mx.com.tp.smc.request.UserForOrganization;
import mx.com.tp.smc.response.AddComment;
import mx.com.tp.smc.response.CatRegionesList;
import mx.com.tp.smc.response.CategoriesConsult;
import mx.com.tp.smc.response.Comment;
import mx.com.tp.smc.response.CommentList;
import mx.com.tp.smc.response.DataGraphic;
import mx.com.tp.smc.response.IncidentsConsult;
import mx.com.tp.smc.response.OrganizationList;
import mx.com.tp.smc.response.OrigenHomeList;
import mx.com.tp.smc.response.PointListCMDB;
import mx.com.tp.smc.response.Region;
import mx.com.tp.smc.response.StatusList;
import mx.com.tp.smc.response.TicketConciliedList;
import mx.com.tp.smc.service.OrigenHomeService;
import mx.com.tp.smc.service.impl.TicketServiceImp;
import mx.com.tp.smc.service.TokenService;
import mx.com.tp.smc.service.impl.UserServiceImp;
import mx.com.tp.smc.entity.CorganizacionEntity;
import mx.com.tp.smc.entity.OrganizacionCMDB;
import mx.com.tp.smc.manager.CatalogosManager;
import mx.com.tp.smc.manager.HomeManager;
import mx.com.tp.smc.manager.IncidentsManager;
import mx.com.tp.smc.manager.RolManager;
import mx.com.tp.smc.manager.UserManager;
@Component
public class HomeMgr {

	@Autowired
	private TokenService tokenService;
	@Autowired
	private TicketServiceImp ticketService;
	@Autowired
	private UserServiceImp userService;

	@Autowired
	private OrigenHomeService origenHomeService;

//	Marcko
	@Autowired
	private IncidentsManager incidentsManager;

	@Autowired
	private UserManager userManager;
	
	@Autowired
	private HomeManager managerHome;

	@Autowired
	private RolManager rolManager;

	@Autowired
	private CatalogosManager catalogosManager;
	
//	@Autowired
//	@Qualifier("viewJdbcTemplate")
//	private JdbcTemplate viewJdbcTemplate;
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	

	final static Logger logger = Logger.getLogger(HomeMgr.class);

//	Marco
	public HomeModel getHomeGraph() {
		System.out.println("Entro a metodo getHomeGraph()--------->1");
		HomeModel model = new HomeModel();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String organizationName = "";
		UserForOrganization request = new UserForOrganization();
		request.setUsername(auth.getName());
		try {
			System.out.println("Entro a try metodo getHomeGraph()--------->2");
			OrganizationList responseManager = userManager.returnOrganizationByUser(request);

			if (responseManager.getListOrganization() != null ) {
				System.out.println("Entro a if metodo getHomeGraph()--------->3");
				for (mx.com.tp.smc.response.Organization org : responseManager.getListOrganization()) {
					if (org.getOrganizationName() != null)
						organizationName = org.getOrganizationName().toString();

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("Entro a 2try metodo getHomeGraph()--------->4");
			Organization Organization = new Organization();
			Organization.setOrganization(organizationName);
			DataGraphic responseManager = managerHome.getGraphicTicket(Organization.getOrganization());
			System.out.println("=== VALOR DEL responseManager ===" + responseManager);
			if ((Boolean) responseManager.getSuccess()) {
				System.out.println("Entro a 2if metodo getHomeGraph()--------->5");
				model.setClosedTickets(responseManager.getCerrado());
				model.setOpenTickets(responseManager.getEnCurso());
				model.setSolvedTickets(responseManager.getSolucionado());
			}
		} catch (Exception e) {
			logger.error(e.toString());
		}

		return model;
	}
	
//	Versión anterior
//	public HomeModel getHomeGraph() {
//		HomeModel model = new HomeModel();
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String organizationName = "";
//		try {
//			JSONObject json2 = userService.getOrganizationByUser(tokenService.getToken("adri", "adri"), auth.getName());
//			if ((Boolean) json2.get("success")) {
//				JSONArray array2 = (JSONArray) json2.get("listOrganization");
//				for (int i = 0; i < array2.size(); i++) {
//					JSONObject object2 = (JSONObject) array2.get(i);
//					if (object2.get("organizationName") != null)
//
//						organizationName = object2.get("organizationName").toString();
//
//				}
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		try {
//			JSONObject json = ticketService.getTicketGraph(tokenService.getToken("adri", "adri"), organizationName);
//			System.out.println("=== VALOR DEL JSON ===" + json);
//			if ((Boolean) json.get("success")) {
//				model.setClosedTickets((Long) json.get("cerrado"));
//				model.setOpenTickets((Long) json.get("enCurso"));
//				model.setSolvedTickets((Long) json.get("solucionado"));
//			}
//		} catch (Exception e) {
//			logger.error(e.toString());
//		}
//
//		return model;
//	}

	
//	Marco
	public TicketModel getAllIncidents() {
		TicketModel model = new TicketModel();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String organizationName = "";
		String nombreSolicitante = "";
		System.out.println("---------------------------------------->1");
		UserForOrganization request = new UserForOrganization();
		request.setUsername(auth.getName());
		try {
			System.out.println("---------------------------------------->2");
			OrganizationList responseManager = userManager.returnOrganizationByUser(request);

			if (responseManager.getListOrganization() != null ) {
				for (mx.com.tp.smc.response.Organization org : responseManager.getListOrganization()) {
					if (org.getOrganizationName() != null)
						organizationName = org.getOrganizationName().toString();
					System.out.println("---------------------------------------->3");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("---------------------------------------->4");

			Organization organization = new Organization();
			organization.setOrganization(organizationName);
			IncidentsConsult response = incidentsManager.getAllTickets(organization.getOrganization());

			if ((Boolean)response.getSuccess() ) {
				System.out.println("---------------------------------------->5");
				ArrayList<Ticket> tickets = new ArrayList<Ticket>();
				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				for (int i = 0; i < response.getTickets().size(); i++) {
					System.out.println("---------------------------------------->6");
					JSONObject object = (JSONObject) response.getTickets().get(i);
					
						Ticket ticket = new Ticket(); 
						System.out.println("VALOR DEL TICKET " + ticket);
						if (object.get("refNum") != null)
							ticket.setRefNum((String) object.get("refNum"));
						if (object.get("priority") != null)
							ticket.setPriority((String) object.get("priority"));
						if (object.get("category") != null)
							ticket.setCategory((String) object.get("category"));
						if (object.get("status") != null)
							ticket.setStatus((String) object.get("status"));
						
						if (object.get("assignee") != null) {

							ticket.setAssignee((String)object.get("assignee"));
						}else if(object.get("assignee") == null) {
							ticket.setAssignee("Sin Asignar");
						}
						
						//************************Debes traer el TFE desde el ticket API (tfe)****************************
						if (object.get("tfe") != null) {
							ticket.setTfe((String) object.get("tfe"));
						}else {
							String[] tfe = object.get("customer").toString().split(",");
							ticket.setTfe(tfe[0].replace("null", ""));
						}	
						if (object.get("contactLocation") != null)
							ticket.setContactLocation((String) object.get("contactLocation"));

						if (object.get("assigneeFirstName") != null && object.get("asigneeMiddleName") != null
								&& object.get("assigneeLastName") != null)
							nombreSolicitante = (String) object.get("assigneeFirstName")
									+ (String) object.get("asigneeMiddleName")
									+ (String) object.get("assigneeLastName");
						ticket.setNombreSolicitante(nombreSolicitante);// Solicitante

						if (object.get("customer") != null) {
							ticket.setCliente(((String) object.get("customer")).replace("null", ""));
						}


						if (object.get("description") != null) {
							ticket.setDescripcion((String) object.get("description"));// descripcion
							String descAssi = ticket.getDescripcion();
							if(descAssi.contains("<") && descAssi.contains(">") ){
								String[] parts = descAssi.split("<");
								String descripcionP = parts[0];
								String usuarioP = parts[1];
						        String usuario=usuarioP.replace(">", "");
								System.out.println(usuario);
								ticket.setAssignee(usuario);
								ticket.setDescripcion(descripcionP);
							}
						
						}

						if (object.get("summary") != null)
							ticket.setResumen((String) object.get("summary"));// Resumen

						if (object.get("finalDiagnose") != null)
							ticket.setDiagFinal((String) object.get("finalDiagnose"));

						if (object.get("zsolucionId") != null)
							ticket.setSolucion((String) object.get("zsolucionId"));

						if (object.get("solutionTime") != null)
							ticket.setFechaHoraSolucion((String) object.get("solutionTime"));// FechaHoraSolucion

						if (object.get("openTime") != null)
							ticket.setFechaApertura((String) object.get("openTime"));// FechaApertura

						

						Date fechaInicial = formato.parse(ticket.getFechaApertura());
						String tiempo = "";
						System.out.println("**********fecha inicial**************** "+ fechaInicial);
						if (ticket.getFechaHoraSolucion() != null) {
							
							System.out.println("**********hora solucion != null**************** "+ ticket.getFechaHoraSolucion());
							
							Date fechaSolucion = formato.parse(ticket.getFechaHoraSolucion());

							long diffInMillisec = fechaSolucion.getTime() - fechaInicial.getTime();
							
							System.out.println("**********RESULTADO DE CALCULO != null**************** "+ diffInMillisec);

							long diffInSec = TimeUnit.MILLISECONDS.toSeconds(diffInMillisec);

							diffInSec /= 60L;

							long minutes = diffInSec % 60L;
							diffInSec /= 60L;

							long hours = diffInSec % 24L;
							diffInSec /= 24L;

							long days = diffInSec;

							tiempo = String.valueOf("Dias: " + days + "\nHoras: " + hours + "\nMinutos:" + minutes);
						} else {
							
							
							Date fechaDeHoy = new Date();
							String fecha = formato.format(fechaDeHoy);
							
							System.out.println("**********entro el else**************** "+ fecha);

							long diffInMillisec = formato.parse(fecha).getTime() - fechaInicial.getTime();
							
							System.out.println("**********RESULTADO DE CALCULO ELSE**************** "+ diffInMillisec);

							long diffInSec = TimeUnit.MILLISECONDS.toSeconds(diffInMillisec);

							diffInSec /= 60L;

							long minutes = diffInSec % 60L;
							diffInSec /= 60L;

							long hours = diffInSec % 24L;
							diffInSec /= 24L;

							long days = diffInSec;

							tiempo = String.valueOf("Dias: " + days + "\nHoras: " + hours + "\nMinutos:" + minutes);
						}
						
						System.out.println("**********tiempo calculado**************** "+ tiempo);
						ticket.setTiempoEnCurso(tiempo);

						if (object.get("lastModTime") != null)
							ticket.setUltimaModificacion((String) object.get("lastModTime"));// Ultima
																								// Modificacion

						if (object.get("closeTime") != null)
							ticket.setFechaCierre((String) object.get("closeTime"));// FechaCierre
						
						if(object.get("slaViolation") != null) {
							ticket.setSlaViolation((Long) object.get("slaViolation"));
						}else {
							ticket.setSlaViolation(new Long(0));
						}
						if(object.get("macroPredictViol") != null) {
							ticket.setMacroPredictViol((Long) object.get("macroPredictViol"));
						}else {
							ticket.setMacroPredictViol(new Long(0));
						}

						tickets.add(ticket);
					
				}
				model.setTickets(tickets);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Obtengo la organizacion id: " + organizationName);
		return model;
	}
	
	
//	Versión inicial
//	public TicketModel getAllIncidents() {
//		TicketModel model = new TicketModel();
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String organizationName = "";
//		String nombreSolicitante = "";
//		System.out.println("---------------------------------------->1");
//		try {
//			JSONObject json2 = userService.getOrganizationByUser(tokenService.getToken("adri", "adri"), auth.getName());
//			if ((Boolean) json2.get("success")) {
//				System.out.println("---------------------------------------->2");
//				JSONArray array2 = (JSONArray) json2.get("listOrganization");
//				for (int i = 0; i < array2.size(); i++) {
//					System.out.println("---------------------------------------->3");
//					JSONObject object2 = (JSONObject) array2.get(i);
//					if (object2.get("organizationName") != null)
//						 organizationName=object2.get("organizationName").toString();
//						//organizationName = object2.get("organizationId").toString();
//					
//				}
//				
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		try {
//			System.out.println("---------------------------------------->4");
//
//			JSONObject json = ticketService.getTicketByOrganization(tokenService.getToken("adri", "adri"),
//					organizationName);
//			if ((Boolean) json.get("success")) {
//				System.out.println("---------------------------------------->5");
//				ArrayList<Ticket> tickets = new ArrayList<Ticket>();
//				JSONArray array = (JSONArray) json.get("tickets");
//				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				for (int i = 0; i < array.size(); i++) {
//					System.out.println("---------------------------------------->6");
//					JSONObject object = (JSONObject) array.get(i);
//					
//						Ticket ticket = new Ticket(); 
//						System.out.println("VALOR DEL TICKET " + ticket);
//						
//						if (object.get("refNum") != null)
//							ticket.setRefNum((String) object.get("refNum"));
//						if (object.get("priority") != null)
//							ticket.setPriority((String) object.get("priority"));
//						if (object.get("category") != null)
//							ticket.setCategory((String) object.get("category"));
//						if (object.get("status") != null)
//							ticket.setStatus((String) object.get("status"));
////						if (object.get("assignee") != null) {
////							String []assigneeSplit = ("" + object.get("assignee")).split("\\ ");
////							if(assigneeSplit.length > 0) {
////								ticket.setAssignee(assigneeSplit[0] == null || ("" + assigneeSplit[0]).equals("null") ? "" : "" + assigneeSplit[0]);
////							} else {
////								ticket.setAssignee(object.get("assignee") == null ? "" : "" + object.get("assignee"));
////							}
////						}
//						
//						if (object.get("assignee") != null) {
//
//							ticket.setAssignee((String)object.get("assignee"));
//						}else if(object.get("assignee") == null) {
//							ticket.setAssignee("Sin Asignar");
//						}
//						
//						//************************Debes traer el TFE desde el ticket API (tfe)****************************
//						if (object.get("tfe") != null) {
//							ticket.setTfe((String) object.get("tfe"));
//						}else {
//							String[] tfe = object.get("customer").toString().split(",");
//							ticket.setTfe(tfe[0].replace("null", ""));
//						}	
//						if (object.get("contactLocation") != null)
//							ticket.setContactLocation((String) object.get("contactLocation"));
//
//						if (object.get("assigneeFirstName") != null && object.get("asigneeMiddleName") != null
//								&& object.get("assigneeLastName") != null)
//							nombreSolicitante = (String) object.get("assigneeFirstName")
//									+ (String) object.get("asigneeMiddleName")
//									+ (String) object.get("assigneeLastName");
//						ticket.setNombreSolicitante(nombreSolicitante);// Solicitante
//
//						if (object.get("customer") != null) {
//							ticket.setCliente(((String) object.get("customer")).replace("null", ""));
//						}
//
//
//						if (object.get("description") != null) {
//							ticket.setDescripcion((String) object.get("description"));// descripcion
//							String descAssi = ticket.getDescripcion();
//							if(descAssi.contains("<") && descAssi.contains(">") ){
//								String[] parts = descAssi.split("<");
//								String descripcionP = parts[0];
//								String usuarioP = parts[1];
//						        String usuario=usuarioP.replace(">", "");
//								System.out.println(usuario);
//								ticket.setAssignee(usuario);
//								ticket.setDescripcion(descripcionP);
//							}
//						
//						}
//
//						if (object.get("summary") != null)
//							ticket.setResumen((String) object.get("summary"));// Resumen
//
//						if (object.get("finalDiagnose") != null)
//							ticket.setDiagFinal((String) object.get("finalDiagnose"));
//
//						if (object.get("zsolucionId") != null)
//							ticket.setSolucion((String) object.get("zsolucionId"));
//
//						if (object.get("solutionTime") != null)
//							ticket.setFechaHoraSolucion((String) object.get("solutionTime"));// FechaHoraSolucion
//
//						if (object.get("openTime") != null)
//							ticket.setFechaApertura((String) object.get("openTime"));// FechaApertura
//
//						
//
//						Date fechaInicial = formato.parse(ticket.getFechaApertura());
//						String tiempo = "";
//						System.out.println("**********fecha inicial**************** "+ fechaInicial);
//						if (ticket.getFechaHoraSolucion() != null) {
//							
//							System.out.println("**********hora solucion != null**************** "+ ticket.getFechaHoraSolucion());
//							
//							Date fechaSolucion = formato.parse(ticket.getFechaHoraSolucion());
//
//							long diffInMillisec = fechaSolucion.getTime() - fechaInicial.getTime();
//							
//							System.out.println("**********RESULTADO DE CALCULO != null**************** "+ diffInMillisec);
//
//							long diffInSec = TimeUnit.MILLISECONDS.toSeconds(diffInMillisec);
//
//							diffInSec /= 60L;
//
//							long minutes = diffInSec % 60L;
//							diffInSec /= 60L;
//
//							long hours = diffInSec % 24L;
//							diffInSec /= 24L;
//
//							long days = diffInSec;
//
//							tiempo = String.valueOf("Dias: " + days + "\nHoras: " + hours + "\nMinutos:" + minutes);
//						} else {
//							
//							
//							Date fechaDeHoy = new Date();
//							String fecha = formato.format(fechaDeHoy);
//							
//							System.out.println("**********entro el else**************** "+ fecha);
//
//							long diffInMillisec = formato.parse(fecha).getTime() - fechaInicial.getTime();
//							
//							System.out.println("**********RESULTADO DE CALCULO ELSE**************** "+ diffInMillisec);
//
//							long diffInSec = TimeUnit.MILLISECONDS.toSeconds(diffInMillisec);
//
//							diffInSec /= 60L;
//
//							long minutes = diffInSec % 60L;
//							diffInSec /= 60L;
//
//							long hours = diffInSec % 24L;
//							diffInSec /= 24L;
//
//							long days = diffInSec;
//
//							tiempo = String.valueOf("Dias: " + days + "\nHoras: " + hours + "\nMinutos:" + minutes);
//						}
//						
//						System.out.println("**********tiempo calculado**************** "+ tiempo);
//						ticket.setTiempoEnCurso(tiempo);
//
//						if (object.get("lastModTime") != null)
//							ticket.setUltimaModificacion((String) object.get("lastModTime"));// Ultima
//																								// Modificacion
//
//						if (object.get("closeTime") != null)
//							ticket.setFechaCierre((String) object.get("closeTime"));// FechaCierre
//						
//						if(object.get("slaViolation") != null) {
//							ticket.setSlaViolation((Long) object.get("slaViolation"));
//						}else {
//							ticket.setSlaViolation(new Long(0));
//						}
//						if(object.get("macroPredictViol") != null) {
//							ticket.setMacroPredictViol((Long) object.get("macroPredictViol"));
//						}else {
//							ticket.setMacroPredictViol(new Long(0));
//						}
//
//						tickets.add(ticket);
//					
//				}
//				model.setTickets(tickets);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		logger.info("Obtengo la organizacion id: " + organizationName);
//		return model;
//	}
	
	
	
//	Marco
	public TicketModel getAgregarComentarios(String incidente, String comentario) {
		TicketModel model = new TicketModel();
		System.out.println("=== INCIDENTE  === " + incidente);
		System.out.println("=== COMENTARIO  === " + comentario);
		AddComentario request = new AddComentario();
		request.setComment(comentario);
		request.setRefNum(incidente);
		try {
			AddComment response = incidentsManager.getAddComentarios(request);
			System.out.println("Valor del incidentsManager" + response);
			JSONArray array = (JSONArray) response.getTickets();
			
			if ((Boolean) response.getSuccess()) {
				JSONObject object = (JSONObject) array.get(0);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return model;
	}
	
//	Versión anterior
//	public TicketModel getAgregarComentarios(String incidente, String comentario) {
//		TicketModel model = new TicketModel();
//		System.out.println("=== INCIDENTE  === " + incidente);
//		System.out.println("=== COMENTARIO  === " + comentario);
//
//		try {
//			JSONObject json = ticketService.agregarComentario(tokenService.getToken("adri", "adri"), incidente, comentario);
//			System.out.println("Valor del JSON" + json);
//			JSONArray array = (JSONArray) json.get("");
//			
//			if ((Boolean) json.get("success")) {
//				JSONObject object = (JSONObject) array.get(0);
//			}
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		return model;
//	}
	
	
// Marco Funcional
	public TicketConciliedModel getAllTicketNotConcilied(String organization) {
		
		TicketConciliedModel model = new TicketConciliedModel();
		
		try {
			System.out.println("entro a getAllTicketNotConcilied con organizacion :" + organization);
			Organization request = new Organization();
			request.setOrganization(organization);
			TicketConciliedList responseManager = incidentsManager.returnAllNotConcilied(request.getOrganization());
			
			if ((Boolean)responseManager.getSuccess()) {
				
				ArrayList<TicketConcilied> tickets = new ArrayList<TicketConcilied>();
				
				for (mx.com.tp.smc.response.TicketConcilied ticketresponse :  responseManager.getListTicket()) {
					TicketConcilied ticket = new TicketConcilied();
					ticket.setDescripcion((String)ticketresponse.getDescripcion());
					ticket.setNomSolicitante((String)ticketresponse.getNomSolicitante());
					ticket.setNom2Solicitante((String)ticketresponse.getNom2Solicitante());
					ticket.setApeSolicitante((String)ticketresponse.getApeSolicitante());
					
					ticket.setNomSolicitanteAa((String)ticketresponse.getNom2SolicitanteAa());
					ticket.setNom2SolicitanteAa((String)ticketresponse.getNom2SolicitanteAa());
					ticket.setApeSolicitanteAa((String)ticketresponse.getApeSolicitanteAa());
					
					ticket.setFolioIncidencia(ticketresponse.getFolioIncidencia().longValue());
					ticket.setResumen((String)ticketresponse.getResumen());
					ticket.setFolioAbierto((String)ticketresponse.getFolioAbierto());
					ticket.setFolioCerrado((String)ticketresponse.getFolioCerrado());
					ticket.setFolioTitulo3((String)ticketresponse.getFolioTitulo3());
					ticket.setFolioTitulo((String)ticketresponse.getFolioTitulo());
					ticket.setDescripcion((String)ticketresponse.getDescripcion()); 
					ticket.setSolucion((String)ticketresponse.getSolucion());
					ticket.setCategoria((String)ticketresponse.getCategoria());
					ticket.setDiagFinal((String)ticketresponse.getDiagFinal());
					ticket.setFolioStatus((String)ticketresponse.getFolioStatus());
					ticket.setTiempoImputableCte((String)ticketresponse.getTiempoImputableCte());
					ticket.setTiempoImputableTpe((String)ticketresponse.getTiempoImputableTpe());
					ticket.setZtiempoFallaTerceros((String)ticketresponse.getZtiempofallaterceros());
					ticket.setZtiempoFallaProv((String)ticketresponse.getZtiempofallaprov());
					System.out.println("=== T ===" + ticketresponse.getZtiempofallaprov());
					ticket.setOrganizacion(organization);
					
					if(ticketresponse.getAfectacion() != null) {
						ticket.setAfectacion(ticketresponse.getAfectacion());
					}
					if(ticketresponse.getZesProactivoReactivo() != null) {
						ticket.setZesProactivoReactivo(ticketresponse.getZesProactivoReactivo());
					}
					if(ticketresponse.getTicketSExterno() != null) {
						ticket.setTicketSExterno(ticketresponse.getTicketSExterno());
					}
					tickets.add(ticket);
				}
				System.out.println("tickets-------------->" + tickets);
				model.setTickets(tickets);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// logger.info(model);
		return model;
	}
	
//	Versión anterior
//	public TicketConciliedModel getAllTicketNotConcilied(String organization) {
//		TicketConciliedModel model = new TicketConciliedModel();
//		try {
//	
//	
//			System.out.println("entro a getAllTicketNotConcilied con organizacion: ---------------------------------------->" + organization);
//			JSONObject json = ticketService.getAllTicketNorConcilied(tokenService.getToken("adri", "adri"),organization);
//			if ((Boolean) json.get("success")) {
//				JSONArray array = (JSONArray) json.get("listTicket");
//				ArrayList<TicketConcilied> tickets = new ArrayList<TicketConcilied>();
//					
//				for (int i = 0; i < array.size(); i++) {
//					JSONObject object = (JSONObject) array.get(i);
//					TicketConcilied ticket = new TicketConcilied();
//					ticket.setDescripcion((String) object.get("descripcion"));
//					System.out.println("=== DESCRIPCION ===" + object.get("descripcion"));
//					ticket.setNomSolicitante((String) object.get("nomSolicitante"));
//					ticket.setNom2Solicitante((String) object.get("nom2Solicitante"));
//					ticket.setApeSolicitante((String) object.get("apeSolicitante"));
//					
//					ticket.setNomSolicitanteAa((String) object.get("nomSolicitanteAa"));
//					ticket.setNom2SolicitanteAa((String) object.get("nom2SolicitanteAa"));
//					ticket.setApeSolicitanteAa((String) object.get("apeSolicitanteAa"));
//					
//					ticket.setFolioIncidencia((Long) object.get("folioIncidencia"));
////					System.out.println("=== FOLIO ===" + object.get("folioIncidencia"));
//					ticket.setResumen((String) object.get("resumen"));
//					ticket.setFolioAbierto((String) object.get("folioAbierto"));
//					ticket.setFolioCerrado((String) object.get("folioCerrado"));
//					ticket.setFolioTitulo3((String) object.get("folioTitulo3"));
//					ticket.setFolioTitulo((String) object.get("folioTitulo"));
//					ticket.setDescripcion((String) object.get("descripcion"));
//					ticket.setSolucion((String) object.get("solucion"));
//					ticket.setCategoria((String) object.get("categoria"));
//					ticket.setDiagFinal((String) object.get("diagFinal"));
//					ticket.setFolioStatus((String) object.get("folioStatus"));
//					ticket.setTiempoImputableCte((String) object.get("tiempoImputableCte"));
//					ticket.setTiempoImputableTpe((String) object.get("tiempoImputableTpe"));
//					ticket.setZtiempoFallaTerceros((String) object.get("ztiempofallaterceros"));
//					ticket.setZtiempoFallaProv((String) object.get("ztiempofallaprov"));
//					System.out.println("=== T ===" + object.get("ztiempofallaprov"));
//					ticket.setOrganizacion(organization);
//					
//					if(object.get("afectacion") != null) {
//						ticket.setAfectacion((String) object.get("afectacion"));
//					}
//					if(object.get("zesProactivoReactivo") != null) {
//						ticket.setZesProactivoReactivo((String) object.get("zesProactivoReactivo"));
//					}
//					if(object.get("ticketSExterno") != null) {
//						ticket.setTicketSExterno((String) object.get("ticketSExterno"));
//					}
//					tickets.add(ticket);
//				}
//				model.setTickets(tickets);
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		// logger.info(model);
//		return model;
//	}



    
//	Marcko Funcional
	public TicketConciliedModel getAllTicketStatusValidation(String organization) {
		TicketConciliedModel model = new TicketConciliedModel();
		Organization request = new Organization();
		request.setOrganization(organization);
		System.out.println("ORGANIZACION-------------->"+ request.getOrganization());
		
		try {
			TicketConciliedList responseManager = incidentsManager.returnAllNotConciliedValidation(request.getOrganization());
			
		if (responseManager.getListTicket() != null) {
				ArrayList<TicketConcilied> tickets = new ArrayList<TicketConcilied>();
				
				for (mx.com.tp.smc.response.TicketConcilied ticketConcilied : responseManager.getListTicket()) {
					TicketConcilied ticket = new TicketConcilied();
					ticket.setNomSolicitante((String)ticketConcilied.getNomSolicitante());
					ticket.setNom2Solicitante((String)ticketConcilied.getNom2Solicitante());
					ticket.setApeSolicitante((String)ticketConcilied.getApeSolicitante());
					ticket.setFolioIncidencia(ticketConcilied.getFolioIncidencia().longValue());
					ticket.setResumen((String)ticketConcilied.getResumen());
					ticket.setFolioAbierto((String)ticketConcilied.getFolioAbierto());
					ticket.setFolioCerrado((String)ticketConcilied.getFolioCerrado());
					ticket.setFolioTitulo3((String)ticketConcilied.getFolioTitulo3());
					ticket.setFolioTitulo((String)ticketConcilied.getFolioTitulo());
					ticket.setDescripcion((String)ticketConcilied.getDescripcion());
					ticket.setSolucion((String)ticketConcilied.getSolucion());
					ticket.setCategoria((String)ticketConcilied.getCategoria());
					ticket.setDiagFinal((String)ticketConcilied.getDiagFinal());
					ticket.setFolioStatus((String)ticketConcilied.getFolioStatus());
					ticket.setTiempoImputableCte((String)ticketConcilied.getTiempoImputableCte());
					ticket.setTiempoImputableTpe((String)ticketConcilied.getTiempoImputableTpe());
					ticket.setZtiempoFallaTerceros(ticketConcilied.getZtiempofallaterceros());
					ticket.setZtiempoFallaProv(ticketConcilied.getZtiempofallaprov());
					tickets.add(ticket);
				}
				model.setTickets(tickets);

			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
//	Versión anterior
//	public TicketConciliedModel getAllTicketStatusValidation(String organization) {
//	TicketConciliedModel model = new TicketConciliedModel();
//	System.out.println( "ORGANIZACION " + organization );
//	try {
//		JSONObject json = ticketService.getAllTicketStatusValidation(tokenService.getToken("adri", "adri"), organization);
//		if ((Boolean) json.get("success")) {
//			JSONArray array = (JSONArray) json.get("listTicket");
//			ArrayList<TicketConcilied> tickets = new ArrayList<TicketConcilied>();
//
//			for (int i = 0; i < array.size(); i++) {
//				JSONObject object = (JSONObject) array.get(i);
//				TicketConcilied ticket = new TicketConcilied();
//				ticket.setNomSolicitante((String) object.get("nomSolicitante"));
//				ticket.setNom2Solicitante((String) object.get("nom2Solicitante"));
//				ticket.setApeSolicitante((String) object.get("apeSolicitante"));
//				ticket.setFolioIncidencia((Long) object.get("folioIncidencia"));
//				ticket.setResumen((String) object.get("resumen"));
//				ticket.setFolioAbierto((String) object.get("folioAbierto"));
//				ticket.setFolioCerrado((String) object.get("folioCerrado"));
//				ticket.setFolioTitulo3((String) object.get("folioTitulo3"));
//				ticket.setFolioTitulo((String) object.get("folioTitulo"));
//				ticket.setDescripcion((String) object.get("descripcion"));
//				ticket.setSolucion((String) object.get("solucion"));
//				ticket.setCategoria((String) object.get("categoria"));
//				ticket.setDiagFinal((String) object.get("diagFinal"));
//				ticket.setFolioStatus((String) object.get("folioStatus"));
//				ticket.setTiempoImputableCte((String) object.get("tiempoImputableCte"));
//				ticket.setTiempoImputableTpe((String) object.get("tiempoImputableTpe"));
//				ticket.setZtiempoFallaTerceros((String) object.get("ztiempofallaterceros"));
//				ticket.setZtiempoFallaProv((String)object.get("ztiempofallaprov"));
//				tickets.add(ticket);
//			}
//			model.setTickets(tickets);
//
//		}
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//	return model;
//}
	
//	Marco
	public TicketConciliedModel updateTicketNotConcilied(BigDecimal folioincidencia, String nombre1, String nombre2,
			String apellidos, String categoria, String usuariofinal, String descripcion, String solucion,
			String fechasolucion, String fechaapertura, String afectadocliente, String diagnosticofinal,
			String tiempofallacliente, String tiempofallaenlace, String resumen, String proactivoReactivo, String afectacion, String ztiempoFallaTer,String ztiempoFallaProv) {
		TicketConciliedModel model = new TicketConciliedModel();
		TicketNotConciliedValidationUpdate request = new TicketNotConciliedValidationUpdate();
		request.setFolioIncidencia(folioincidencia);
		request.setNomSolicitante(nombre1);
		request.setNom2Solicitante(nombre2);
		request.setApeSolicitante(apellidos);
		request.setCategoria(categoria);
		request.setFolioTitulo(usuariofinal);
		request.setDescripcion(descripcion);
		request.setSolucion(solucion);
		request.setFolioCerrado(fechasolucion);
		request.setFolioAbierto(fechaapertura);
		request.setFolioTitulo3(afectadocliente);
		request.setDiagFinal(diagnosticofinal);
		request.setTiempoImputableCte(tiempofallacliente);
		request.setTiempoImputableTpe(tiempofallaenlace);
		request.setResumen(resumen);
		request.setProactivoReactivo(proactivoReactivo);
		request.setAfectacion(afectacion);
		request.setZtiempoFallaTer(ztiempoFallaTer);
		request.setZtiempoFallaProv(ztiempoFallaProv);
		
		
		try {
			TicketConciliedList responseManager = incidentsManager.updateTicketNotConcilied(request);

			if ((Boolean)responseManager.getSuccess()) {
				ArrayList<TicketConcilied> tickets = new ArrayList<TicketConcilied>();
				TicketConcilied ticket = new TicketConcilied();
				ticket.setMssg((String) responseManager.getMssg());
				ticket.setSucces(String.valueOf(responseManager.getSuccess()));
				tickets.add(ticket);
				model.setTickets(tickets);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// logger.info(model);
		return model;
	}
	
//	Versión anterior
//	public TicketConciliedModel updateTicketNotConcilied(BigDecimal folioincidencia, String nombre1, String nombre2,
//			String apellidos, String categoria, String usuariofinal, String descripcion, String solucion,
//			String fechasolucion, String fechaapertura, String afectadocliente, String diagnosticofinal,
//			String tiempofallacliente, String tiempofallaenlace, String resumen, String proactivoReactivo, String afectacion, String ztiempoFallaTer,String ztiempoFallaProv) {
//		TicketConciliedModel model = new TicketConciliedModel();
//
//		try {
//			JSONObject json = ticketService.updateTicketNorConcilied(tokenService.getToken("adri", "adri"),
//					folioincidencia, nombre1, nombre2, apellidos, categoria, usuariofinal, descripcion, solucion,
//					fechasolucion, fechaapertura, afectadocliente, diagnosticofinal, tiempofallacliente,
//					tiempofallaenlace, resumen, proactivoReactivo, afectacion,  ztiempoFallaTer,ztiempoFallaProv);
//
//			if ((Boolean) json.get("success")) {
//				ArrayList<TicketConcilied> tickets = new ArrayList<TicketConcilied>();
//				TicketConcilied ticket = new TicketConcilied();
//				ticket.setMssg((String) json.get("mssg"));
//				ticket.setSucces((String) json.get("success").toString());
//				tickets.add(ticket);
//				model.setTickets(tickets);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		// logger.info(model);
//		return model;
	
//	Marco
	public TicketConciliedModel updateStatusTicketNotConcilied(BigDecimal folioincidencia) {
		TicketConciliedModel model = new TicketConciliedModel();
		TicketNotConciliedValidationUpdate request = new TicketNotConciliedValidationUpdate();
		request.setFolioIncidencia(folioincidencia);
		try {
			TicketConciliedList responseManager = incidentsManager.updateStatusTicketNotConcilied(request);
;
			if ((Boolean) responseManager.getSuccess()) {
				ArrayList<TicketConcilied> tickets = new ArrayList<TicketConcilied>();
				TicketConcilied ticket = new TicketConcilied();
				ticket.setMssg((String) responseManager.getMssg());
				ticket.setSucces(String.valueOf(responseManager.getSuccess()));
				tickets.add(ticket);
				model.setTickets(tickets);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

//Versión actual
//	public TicketConciliedModel updateStatusTicketNotConcilied(BigDecimal folioincidencia) {
//		TicketConciliedModel model = new TicketConciliedModel();
//		try {
//			JSONObject json = ticketService.updateStatusTicketNorConcilied(tokenService.getToken("adri", "adri"),
//					folioincidencia);
//			if ((Boolean) json.get("success")) {
//				ArrayList<TicketConcilied> tickets = new ArrayList<TicketConcilied>();
//				TicketConcilied ticket = new TicketConcilied();
//				ticket.setMssg((String) json.get("mssg"));
//				ticket.setSucces((String) json.get("success").toString());
//				tickets.add(ticket);
//				model.setTickets(tickets);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}

//	Marco
	public TicketConciliedModel updateTicketPorValidar(BigDecimal folioincidencia) {
		TicketConciliedModel model = new TicketConciliedModel();
		TicketNotConciliedValidationUpdate request = new TicketNotConciliedValidationUpdate();
		request.setFolioIncidencia(folioincidencia);
		try {
			TicketConciliedList responseManager = incidentsManager.updateTicketPorValidar(request);

			if ((Boolean) responseManager.getSuccess()) {
				ArrayList<TicketConcilied> tickets = new ArrayList<TicketConcilied>();
				TicketConcilied ticket = new TicketConcilied();
				ticket.setMssg((String) responseManager.getMssg());
				ticket.setSucces(String.valueOf(responseManager.getSuccess()));
				tickets.add(ticket);
				model.setTickets(tickets);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// logger.info(model);
		return model;
	}
	
//Versión actual	
//	public TicketConciliedModel updateTicketPorValidar(BigDecimal folioincidencia) {
//		TicketConciliedModel model = new TicketConciliedModel();
//		try {
//			JSONObject json = ticketService.updateTicketPorValidar(tokenService.getToken("adri", "adri"),
//					folioincidencia);
//			if ((Boolean) json.get("success")) {
//				ArrayList<TicketConcilied> tickets = new ArrayList<TicketConcilied>();
//				TicketConcilied ticket = new TicketConcilied();
//				ticket.setMssg((String) json.get("mssg"));
//				ticket.setSucces((String) json.get("success").toString());
//				tickets.add(ticket);
//				model.setTickets(tickets);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		// logger.info(model);
//		return model;
//	}

//	Marco
	public TicketConciliedModel updateStatusTicketNotConciliedValidation(BigDecimal folioincidencia, String nombre1,
			String nombre2, String apellidos, String categoria, String usuariofinal, String descripcion,
			String solucion, String fechasolucion, String fechaapertura, String afectadocliente,
			String diagnosticofinal, String tiempofallacliente, String tiempofallaenlace, String resumen, String proactivoReactivo, String afectacion, String ztiempoFallaTer, String ztiempoFallaProv) {
		TicketConciliedModel model = new TicketConciliedModel();
		TicketNotConciliedValidationUpdate request = new TicketNotConciliedValidationUpdate();
		request.setFolioIncidencia(folioincidencia);
		request.setNomSolicitante(nombre1);
		request.setNom2Solicitante(nombre2);
		request.setApeSolicitante(apellidos);
		request.setCategoria(categoria);
		request.setFolioTitulo(usuariofinal);
		request.setDescripcion(descripcion);
		request.setSolucion(solucion);
		request.setFolioCerrado(fechasolucion);
		request.setFolioAbierto(fechaapertura);
		request.setFolioTitulo3(afectadocliente);
		request.setDiagFinal(diagnosticofinal);
		request.setTiempoImputableCte(tiempofallacliente);
		request.setTiempoImputableTpe(tiempofallaenlace);
		request.setResumen(resumen);
		request.setProactivoReactivo(proactivoReactivo);
		request.setAfectacion(afectacion);
		request.setZtiempoFallaTer(ztiempoFallaTer);
		request.setZtiempoFallaProv(ztiempoFallaProv);
		try {
			TicketConciliedList responseManager = incidentsManager.updateStatusTicketNotConciliedValidation(request);

			if ((Boolean) responseManager.getSuccess()) {
				ArrayList<TicketConcilied> tickets = new ArrayList<TicketConcilied>();
				TicketConcilied ticket = new TicketConcilied();
				ticket.setMssg((String) responseManager.getMssg());
				ticket.setSucces(String.valueOf(responseManager.getSuccess()));
				tickets.add(ticket);
				model.setTickets(tickets);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		// logger.info(model);
		return model;
	}
	
//	Versión anterior
//	public TicketConciliedModel updateStatusTicketNotConciliedValidation(BigDecimal folioincidencia, String nombre1,
//			String nombre2, String apellidos, String categoria, String usuariofinal, String descripcion,
//			String solucion, String fechasolucion, String fechaapertura, String afectadocliente,
//			String diagnosticofinal, String tiempofallacliente, String tiempofallaenlace, String resumen, String proactivoReactivo, String afectacion, String ztiempoFallaTer, String ztiempoFallaProv) {
//		TicketConciliedModel model = new TicketConciliedModel();
//
//		try {
//			JSONObject json = ticketService.updateStatusTicketNorConciliedValidation(
//					tokenService.getToken("adri", "adri"), folioincidencia, nombre1, nombre2, apellidos, categoria,
//					usuariofinal, descripcion, solucion, fechasolucion, fechaapertura, afectadocliente,
//					diagnosticofinal, tiempofallacliente, tiempofallaenlace, resumen, proactivoReactivo,afectacion, ztiempoFallaTer,ztiempoFallaProv);
//			if ((Boolean) json.get("success")) {
//				ArrayList<TicketConcilied> tickets = new ArrayList<TicketConcilied>();
//				TicketConcilied ticket = new TicketConcilied();
//				ticket.setMssg((String) json.get("mssg"));
//				ticket.setSucces((String) json.get("success").toString());
//				tickets.add(ticket);
//				model.setTickets(tickets);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//
//		}
//		// logger.info(model);
//		return model;
//	}
	
//	Marco
	public CategoriesModel getCategories(String bandeja) {
		
		System.out.println("CAAAAARRRREEEEE---------------------------------------------------->"+ bandeja);
		CategoriesModel model = new CategoriesModel();
		String category;
		String subCategory;
		String falla;
		int posicion;
		Tenant req = new Tenant();
		req.setType(bandeja);;
		try {
			System.out.println("=== BANDEJA === " + bandeja);
			CategoriesConsult response = incidentsManager.getCategory(req.getType());
			if ((Boolean) response.getSuccess()) {
				JSONArray array = (JSONArray) response.getTickets();
				ArrayList<Categories> tickets = new ArrayList<Categories>();
				for (int i = 0; i < array.size(); i++) {
					posicion = 0;
					category = "";
					subCategory = "";
					falla = "";
					JSONObject object = (JSONObject) array.get(i);
					if ((String) object.get("lastModDt") != null) {
						System.out.println("=== VALORES QUE ARROJA EL REST PARA LAS CATEGORIAS === " + object.get("sym"));
						Categories ticket = new Categories();
						ticket.setIdCategory((String) object.get("id"));
						ticket.setSym((String) object.get("sym"));
						ticket.setSucces(String.valueOf(response.getSuccess()));
						ticket.setMssg((String)response.getMssg());
						posicion = object.get("sym").toString().indexOf(".");
						category = object.get("sym").toString().substring(0, posicion);
						subCategory = object.get("sym").toString().substring(posicion + 1,
								object.get("sym").toString().length());
						ticket.setCategory(category);
						posicion = subCategory.indexOf(".");
						falla = subCategory.substring(posicion + 1, subCategory.length());
						//subCategory = subCategory.substring(0, posicion);
						ticket.setSubCategory(subCategory);
						ticket.setFallo(falla);
						ticket.setTenant((null != object.get("tenant")) ? "" + object.get("tenant") : "");
						tickets.add(ticket);
					}
				}
				model.setTickets(tickets);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// logger.info(model);
		return model;
	}

//Versión anterior
//public CategoriesModel getCategories(String bandeja) {
//		
//		System.out.println("CAAAAARRRREEEEE---------------------------------------------------->"+ bandeja);
//		CategoriesModel model = new CategoriesModel();
//		String category;
//		String subCategory;
//		String falla;
//		int posicion;
//		try {
//			System.out.println("=== BANDEJA === " + bandeja);
//			JSONObject json = ticketService.getCategories(tokenService.getToken("adri", "adri"), bandeja);
//			if ((Boolean) json.get("success")) {
//				JSONArray array = (JSONArray) json.get("tickets");
//				ArrayList<Categories> tickets = new ArrayList<Categories>();
//
//				for (int i = 0; i < array.size(); i++) {
//					posicion = 0;
//					category = "";
//					subCategory = "";
//					falla = "";
//					JSONObject object = (JSONObject) array.get(i);
//					if ((String) object.get("lastModDt") != null) {
//						System.out.println("=== VALORES QUE ARROJA EL REST PARA LAS CATEGORIAS === " + object.get("sym"));
//						Categories ticket = new Categories();
//						ticket.setIdCategory((String) object.get("id"));
//						ticket.setSym((String) object.get("sym"));
//						ticket.setSucces((String) json.get("success").toString());
//						ticket.setMssg((String) json.get("mssg"));
//						posicion = object.get("sym").toString().indexOf(".");
//						category = object.get("sym").toString().substring(0, posicion);
//						subCategory = object.get("sym").toString().substring(posicion + 1,
//								object.get("sym").toString().length());
//						ticket.setCategory(category);
//						posicion = subCategory.indexOf(".");
//						falla = subCategory.substring(posicion + 1, subCategory.length());
//						//subCategory = subCategory.substring(0, posicion);
//						ticket.setSubCategory(subCategory);
//						ticket.setFallo(falla);
//						ticket.setTenant((null != object.get("tenant")) ? "" + object.get("tenant") : "");
//						tickets.add(ticket);
//					}
//				}
//				model.setTickets(tickets);
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		// logger.info(model);
//		return model;
//	}

//	Marco
	public CatRegionesModel getCatRegiones() {
		CatRegionesModel model = new CatRegionesModel();
		System.out.println("array statusr ----------------->1");
		try {
			CatRegionesList res = incidentsManager.getCatRegiones();
			if ((Boolean) res.getSuccess()) {
				System.out.println("array statusr ----------------->2");
				res.getListRegion();
//				JSONArray array = (JSONArray) res.getListRegion();
				ArrayList<CatRegiones> regiones = new ArrayList<CatRegiones>();
				for (Regiones regio : res.getListRegion()) {
					System.out.println("array statusr ----------------->3");
					CatRegiones reg = new CatRegiones();
					reg.setId((String) regio.getId());
					reg.setSym((String) regio.getSym());
					regiones.add(reg);
				}
				model.setRegiones(regiones);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
//	Versión anterior
//	public CatRegionesModel getCatRegiones() {
//		CatRegionesModel model = new CatRegionesModel();
//		try {
//			JSONObject json = ticketService.getCatRegiones(tokenService.getToken("adri", "adri"));
//			
//			if ((Boolean) json.get("success")) {
//				JSONArray array = (JSONArray) json.get("listRegion");
//				ArrayList<CatRegiones> regiones = new ArrayList<CatRegiones>();
//				for (int i = 0; i < array.size(); i++) {
//					JSONObject obj = (JSONObject) array.get(i);
//					CatRegiones reg = new CatRegiones();
//					reg.setId((String) obj.get("id"));
//					reg.setSym((String) obj.get("sym"));
//					regiones.add(reg);
//				}
//				model.setRegiones(regiones);
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}
	
//	Marcko Funcional
	public PointModel getPointByOrganization(String organization) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		PointModel model = new PointModel();
		try {
			Tenant request = new Tenant();
			request.setOrganization(organization);
			
			System.out.println("Valor getOrganization----------------------------------------->"+ request.getOrganization());
			
			PointListCMDB response = incidentsManager.getPointByOrg( request.getOrganization(), request.getTenant());

			ArrayList<PointCMDB> tickets = new ArrayList<PointCMDB>();

				Collections.sort(tickets,Collections.reverseOrder().reversed());
				
				for (mx.com.tp.smc.response.PointCMDB ticketresponse : response.getListPoint()) {
					PointCMDB ticket = new PointCMDB();
					ticket.setPuntaid((String) ticketresponse.getPuntaid());
					ticket.setPuntaname((String) ticketresponse.getPuntaname());
					ticket.setSucces(String.valueOf(response.getSuccess())) ;
					ticket.setMssg((String) response.getMssg());
					ticket.setTenant((String) ticketresponse.getTenant());
					ticket.setIpaddress((String) ticketresponse.getIpaddress());
					
					tickets.add(ticket);
				}
				model.setTicketsCMDB(tickets);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
//Versión anterior
//	public PointModel getPointByOrganization(String organization) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println("VALOR DE LA ORGANIZACION PARA LAS PUNTAS" + organization);
//		PointModel model = new PointModel();
//		try {
//			JSONObject json = ticketService.getPointByOrganization(tokenService.getToken("adri", "adri"), organization);
//			if ((Boolean) json.get("success")) {
//				JSONArray array = (JSONArray) json.get("listPoint");
//				ArrayList<PointCMDB> tickets = new ArrayList<PointCMDB>();
//
//				System.out.println("--------------------------------------------------->"+ json.get("listPoint"));
//				Collections.sort(tickets,Collections.reverseOrder().reversed());
//				
//				for (int i = 0; i < array.size(); i++) {
//					JSONObject object = (JSONObject) array.get(i);
//					PointCMDB ticket = new PointCMDB();
//					ticket.setPuntaid((String) object.get("puntaid"));
//					ticket.setPuntaname((String) object.get("puntaname"));
//					ticket.setSucces((String) json.get("success").toString());
//					ticket.setMssg((String) json.get("mssg"));
//					ticket.setTenant((String) object.get("tenant"));
//					ticket.setIpaddress((String) object.get("ipaddress"));
//					tickets.add(ticket);
//				}
//				model.setTicketsCMDB(tickets);
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		// logger.info(model);
//		return model;
//	}

	///// INICIO CHRISTIAN J. -NETA SYSTEMS //

//	Marco
	public PointModel getPointByOrganizationDH(String organization) throws ClassNotFoundException {
		
		PointModel model = new PointModel();
		organization = organization.toUpperCase();
		ArrayList<PointCMDB> tickets = new ArrayList<PointCMDB>();
		try {
			
			ArrayList<PointCMDB> ticket = new ArrayList<PointCMDB>();

			ticket = (ArrayList<PointCMDB>) jdbcTemplate.query(Constant.SQL_getPointByOrganizationDH, new Object[] { organization },
					new RowMapper<PointCMDB>() {
				      
						@Override
						public PointCMDB mapRow(ResultSet rs, int rowNum) throws SQLException {
							PointCMDB point = new PointCMDB();
							
							point.setClienteid(rs.getString("cliente_id"));
							point.setClientename(rs.getString("cliente_name"));
							point.setPuntaid(rs.getString("punta_id"));
							point.setPuntaname(rs.getString("punta_name"));
							point.setInactive(rs.getString("inactive"));
							point.setResourcename(rs.getString("resource_name"));
							point.setHostname(rs.getString("host_name"));
							point.setIpaddress(rs.getString("ip_address"));
							point.setMacaddress(rs.getString("mac_address"));
							point.setZcpe(rs.getString("zcpe"));
							String ipPunta = "";
							ipPunta = rs.getString("ip_address");
							String ip;
								if (ipPunta != null) {
									ip = ipPunta.replace(".", "_");
								} else {
									ip = "00_000_00_00";
								}
								point.setIpaddress(ip);
							
							return point;
						}
			});
			tickets.addAll(ticket);
			model.setTicketsCMDB(tickets);
		} catch (DataAccessException ex) {
			ex.printStackTrace();
		}

		// logger.info(model);
		return model;
	}
	

//	Versión anterior
//public PointModel getPointByOrganizationDH(String organization) throws ClassNotFoundException {
//		
//		PointModel model = new PointModel();
//		String ipPunta = "";
//		organization = organization.toUpperCase();
//		try {
//			
//			ConexionCMDB.connect();
//			Statement st = ConexionCMDB.jdbcConnection.createStatement();
//			ResultSet registro = st.executeQuery("SELECT * FROM datahub.cmdb_cat_clientes_puntas_vw where cliente_name = '" + organization + "' order by punta_name");
//
//			
//			ArrayList<PointCMDB> tickets = new ArrayList<PointCMDB>();
//
//			while (registro.next()) {
//				
//				PointCMDB ticket = new PointCMDB();
//				
//				ticket.setClienteid(registro.getString("cliente_id"));
//				ticket.setClientename(registro.getString("cliente_name"));
//				ticket.setPuntaid(registro.getString("punta_id"));
//				ticket.setPuntaname(registro.getString("punta_name"));
//				ticket.setInactive(registro.getString("inactive"));
//				ticket.setResourcename(registro.getString("resource_name"));
//				ticket.setHostname(registro.getString("host_name"));
//				ticket.setIpaddress(registro.getString("ip_address"));
//				ticket.setMacaddress(registro.getString("mac_address"));
//				ticket.setZcpe(registro.getString("zcpe"));
//
//				ipPunta = registro.getString("ip_address");
//				String ip;
//					if (ipPunta != null) {
//						ip = ipPunta.replace(".", "_");
//					} else {
//						ip = "00_000_00_00";
//					}
//				ticket.setIpaddress(ip);
//				
//				tickets.add(ticket);
//			}
//			
//			model.setTicketsCMDB(tickets);
//			ConexionCMDB.disconnect();
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		}
//
//		// logger.info(model);
//		return model;
//	}

//	Marco
	public PointModel getIPByOrganization(String organization) throws ClassNotFoundException {
		PointModel model = new PointModel();
		organization = organization.toUpperCase();
		System.out.println("Valor de la organizacion" + organization);

		try {
			ArrayList<PointCMDB> ipTickets = new ArrayList<PointCMDB>();
			ipTickets = (ArrayList<PointCMDB>) jdbcTemplate.query(Constant.SQL_getIPByOrganization, new Object[] { organization },
					new RowMapper<PointCMDB>(){

						@Override
						public PointCMDB mapRow(ResultSet rs, int rowNum) throws SQLException {
							String ipPunta = "";
							PointCMDB ticket = new PointCMDB();
							String user = rs.getString("cliente_id");
							ticket.setClienteid(user);
							System.out.println("VALOR DE LA IP - " + rs.getString("ip_address") + " - " + rs.getString("punta_name"));
							ipPunta = rs.getString("ip_address");
							String ip = ipPunta.replace(".", "_");
							ticket.setIpaddress(ip);
							
							return ticket;
						}
				
			});
			
			model.setTicketsCMDB(ipTickets);
		} catch (Exception ex) {
			logger.info(ex.toString());

		}
		return model;
	}
	
	
//	Versión anterior
//	public PointModel getIPByOrganization(String organization) throws ClassNotFoundException {
//		PointModel model = new PointModel();
//		String ipPunta = "";
//		organization = organization.toUpperCase();
//
//		System.out.println("Valor de la organizacion" + organization);
//
//		try {
//
//			ConexionCMDB.connect();
//			Statement comando = ConexionCMDB.jdbcConnection.createStatement();
//			
//			ResultSet registro = comando.executeQuery("SELECT * FROM datahub.cmdb_cat_clientes_puntas_vw where cliente_id ='" + organization + "' order by inactive desc limit 1");
//
//			System.out.println("PUNTAS ::::::::::::::::::::::::::::::" + registro);
//			ArrayList<PointCMDB> ipTickets = new ArrayList<PointCMDB>();
//
//			while (registro.next()) {
//				PointCMDB ticket = new PointCMDB();
//				String user = registro.getString("cliente_id");
//				ticket.setClienteid(user);
//				System.out.println("VALOR DE LA IP - " + registro.getString("ip_address") + " - " + registro.getString("punta_name"));
//				// ticket.setName((String) registro.getString("CLIENTE"));
//				ipPunta = registro.getString("ip_address");
//				String ip = ipPunta.replace(".", "_");
//				ticket.setIpaddress(ip);
////				ticket.setSucces("success");
////				ticket.setMssg("Exito al Conseguir las Puntas");
//
//				ipTickets.add(ticket);
//
//			}
//			model.setTicketsCMDB(ipTickets);
//			ConexionCMDB.disconnect();
//		} catch (SQLException ex) {
//			logger.info(ex.toString());
//
//		}
//		// logger.info(model);
//		return model;
//	}
	
	

	///// FIN CHRISTIAN J. -NETA SYSTEMS //

	//// INICIO CHRISTIAN J. -NETA SYSTEMS //
	

//	Marcco
	public PointModel getPointName(String pointId, String organization) throws ClassNotFoundException {
		
		System.out.println("=== PUNTA === " + pointId + " === " + organization);
		
		PointModel model = new PointModel();
		
		try {
			ArrayList<Point> tickets = new ArrayList<Point>();
			tickets = (ArrayList<Point>) jdbcTemplate.query(Constant.SQL_getPointName,new Object[] { pointId, organization },
					new RowMapper<Point>() {

						@Override
						public Point mapRow(ResultSet rs, int rowNum) throws SQLException {
							String ipPunta = "";
							Point ticket = new Point();
							String punta = rs.getString("punta_name");
							String user = rs.getString("cliente_id");
							ticket.setUserId(user);
							ipPunta = rs.getString("ip_address");
							String ip = ipPunta.replace(".", "_");
//							System.out.println("VALOR 1 -" + pointId);
							System.out.println("VALOR 2 -" + punta);
							System.out.println("VALOR 3 -" + ip);
							System.out.println("VALOR 4 -" + user);
							ticket.setIpPunta(ip);
							System.out.println("VALOR DEL MODELO - " + ticket);
							return ticket;
						}});

			model.setTickets(tickets);
			
		} catch (Exception ex) {
			logger.info(ex.toString());

		}

		// logger.info(model);
		return model;
	}
	
//	Versión anterior
//public PointModel getPointName(String pointId, String organization) throws ClassNotFoundException {
//		
//		System.out.println("=== PUNTA === " + pointId + " === " + organization);
//		
//		PointModel model = new PointModel();
//		String ipPunta = "";
//        
//		
//	    //String pun =  pointId;
//	    //String[] parts = pun.split("- TFE");
//	    //String nomPunta = parts[0];
//	    //String tfe = parts[1];
//
////	    System.out.println("VALOR DE LA PUNTA ID ES -" + nomPunta);
////	    System.out.println("VALOR DE LA PUNTA TFE ES -" + tfe);
//		try {
//			
//			ConexionCMDB.connect();
//			Statement comando = ConexionCMDB.jdbcConnection.createStatement();
//			ResultSet registro = comando.executeQuery("SELECT * FROM datahub.cmdb_cat_clientes_puntas_vw where cliente_id = '" + organization  + "' and punta_name = '"+ pointId + "'");
//
//			ArrayList<Point> tickets = new ArrayList<Point>();
//			while (registro.next()) {
//				Point ticket = new Point();
//
//				String punta = registro.getString("punta_name");
//				String user = registro.getString("cliente_id");
//				ticket.setUserId(user);
//				ipPunta = registro.getString("ip_address");
//				String ip = ipPunta.replace(".", "_");
//				System.out.println("VALOR 1 -" + pointId);
//				System.out.println("VALOR 2 -" + punta);
//				System.out.println("VALOR 3 -" + ip);
//				System.out.println("VALOR 4 -" + user);
//				ticket.setIpPunta(ip);
//				System.out.println("VALOR DEL MODELO - " + ticket);
//				tickets.add(ticket);
//			}
//
//			model.setTickets(tickets);
//			ConexionCMDB.disconnect();
//			
//		} catch (SQLException ex) {
//			logger.info(ex.toString());
//
//		}
//
//		// logger.info(model);
//		return model;
//	}

	public FileModel getListaArchivos(String organizacion) throws ClassNotFoundException {
		System.out.println("=== ORG ARCHIVOS Marco ===" + organizacion);
		FileModel model = new FileModel();
		try {
//			Class.forName("com.mysql.jdbc.Driver");
////			Connection conexion = DriverManager.getConnection("jdbc:mariadb://10.180.199.123:3306/SMC_DESAROLLO", "smcQA","PortalSeguridad2017");
////			Connection conexion = DriverManager.getConnection("jdbc:mysql://10.180.251.151:3306/portalcl", "portalclQA","PortalSeguridad2017");
////			Connection conexion = DriverManager.getConnection("jdbc:mariadb://10.180.251.111:3306/portalcl", "portalcl","PortalSeguridad2017");
//			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/portalcl", "root","");
//			Statement comando = conexion.createStatement();
			
			ArrayList<CatArchivos> archivos = new ArrayList<CatArchivos>();
			archivos = (ArrayList<CatArchivos>) jdbcTemplate.query(Constant.SQL_getListaArchivos,new Object[] { organizacion },
					new RowMapper<CatArchivos>() {
				
						@Override
						public CatArchivos mapRow(ResultSet rs, int rowNum) throws SQLException {
							CatArchivos archivos= new CatArchivos();
							archivos.setIdArchivo(rs.getInt("idArchivo"));
							archivos.setIdRol(rs.getInt("idRol"));
							archivos.setUsername(rs.getString("username"));
							archivos.setArchivonombre(rs.getString("archivo_nombre"));
							archivos.setArchivoAnio(rs.getInt("archivo_Anio"));
							archivos.setArchivofechacarga(rs.getDate("archivo_fechacarga"));
							archivos.setArchivofechaactualizacion(rs.getDate("archivo_fechaactualizacion"));
							archivos.setArchivoArchivo(rs.getBlob("archivo_Archivo"));
							archivos.setOrganizacion(rs.getString("organizacion"));
							archivos.setNombre(rs.getString("nombre"));
							archivos.setPath(rs.getString("path"));
							archivos.setNivel(rs.getInt("nivel"));
							archivos.setOrganizacionCarp(rs.getString("organizacion"));
							return archivos;
						}});
			
			model.setArchivosPdf(archivos);
//			conexion.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	

//	public void bajarArchivo(String idArchivo) throws SQLException, IOException {
//
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
////			Connection conexion = DriverManager.getConnection("jdbc:mariadb://10.180.199.123:3306/SMC_DESAROLLO", "smcQA", "PortalSeguridad2017");
////			Connection conexion = DriverManager.getConnection("jdbc:mysql://10.180.251.151:3306/portalcl", "portalclQA", "PortalSeguridad2017");
////			Connection conexion = DriverManager.getConnection("jdbc:mariadb://10.180.251.111:3306/portalcl", "portalcl", "PortalSeguridad2017");
//			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/portalcl", "root","");
//			Statement comando = conexion.createStatement();
//
//			ResultSet rs = comando
//					.executeQuery("SELECT * from TARCHIVOS WHERE TARCHIVOS.ARCHIVO_NOMBRE ='" + idArchivo + "'");
//
////			rs.next();
////			String pathname = "/home/docsSMC/" + rs.getString("archivo_nombre");
////			File file = new File(pathname);
////			FileOutputStream output = new FileOutputStream(file);
////			Blob archivo = rs.getBlob("archivo_Archivo");
////			InputStream inStream = archivo.getBinaryStream();
////
////			int length = -1;
////			int size = (int) archivo.length();
////
////			byte[] buffer = new byte[size];
////			while ((length = inStream.read(buffer)) != -1) {
////				output.write(buffer, 0, length);
////			}
////
////			output.close();
//
//			
//			
//		} catch (Exception ioe) {
//			// TODO Auto-generated catch block
//			ioe.printStackTrace();
//			throw new IOException(ioe.getMessage());
//		}
//		
//	
//	}

//	Marco
	public PointModel getSeverityPoint() throws ClassNotFoundException {
		PointModel model = new PointModel();

		try {
			ArrayList<Point> puntas = new ArrayList<Point>();
			puntas = (ArrayList<Point>) jdbcTemplate.query(Constant.SQL_getSeverityPoint , new Object[] {},
					new RowMapper<Point>() {

						@Override
						public Point mapRow(ResultSet rs, int rowNum) throws SQLException {
							String puntasStr = "";
							Point point = new Point();
							point.setUserId((String)rs.getString("ID_PUNTA"));
							puntasStr = puntasStr + point.getUserId() + "_";
							point.setIdPuntasSeveras(puntasStr);
							System.out.println("Puntas concatenadas: " + puntasStr);
							return point;
						}});

			

			model.setTickets(puntas);
		} catch (Exception ex) {
			logger.info(ex.toString());

		}

		// logger.info(model);
		return model;
	}

//	Versión anterior
//	public PointModel getSeverityPoint() throws ClassNotFoundException {
//		PointModel model = new PointModel();
//
//		try {
//			Class.forName("org.mariadb.jdbc.Driver");
//			Connection conexion = DriverManager.getConnection("jdbc:mariadb://10.180.251.79:3306/datahub", "PORTAL",
//					"PORTAL");
//			java.sql.Statement comando = conexion.createStatement();
//			ResultSet registro = comando.executeQuery(
//					"SELECT CAP_ID_D ID_PUNTA, concat('TFE: ',cap_tfe,' DESCRIPCION: ',last_name) COMBO FROM datahub.catalogo_puntas_match_full_vw  ORDER BY last_name ASC");
//			ArrayList<Point> puntas = new ArrayList<Point>();
//
//			String puntasStr = "";
//
//			while (registro.next()) {
//				Point punta = new Point();
//				punta.setUserId((String) registro.getString("ID_PUNTA"));
//
//				puntasStr = puntasStr + punta.getUserId() + "_";
//
//				punta.setIdPuntasSeveras(puntasStr);
//
//				puntas.add(punta);
//
//			}
//
//			System.out.println("Puntas concatenadas: " + puntasStr);
//
//			model.setTickets(puntas);
//			conexion.close();
//		} catch (SQLException ex) {
//			logger.info(ex.toString());
//
//		}
//
//		// logger.info(model);
//		return model;
//	}
	
//	Marco
	public TicketConciliedModel addIncident(String aplicant, String idCategory, String description, String insertar) {
		TicketConciliedModel model = new TicketConciliedModel();
		System.out.println("<======================= VALORES QUE SE LE MANDAN AL TICKET API =======================>" + aplicant + "/" + idCategory + "/" + description);
		AddIncident request = new AddIncident();
		request.setApplicant(aplicant);
		request.setCategory(idCategory);
		request.setDescription(description.split(""));
		request.setInsertar(insertar);
		try {
			IncidentsConsult response = incidentsManager.insertTicket(request.getOrg(),request.getApplicant(),request.getCategory(),request.getDescription(),request.getRegion(), request.getInsertar());
			
			JSONArray array = (JSONArray) response.getTickets();
			if ((Boolean) response.getSuccess()) {
					System.out.println("=== SOY SUCCESS ===");
				JSONObject object = (JSONObject) array.get(0);
				ArrayList<TicketConcilied> tickets = new ArrayList<TicketConcilied>();
				TicketConcilied ticket = new TicketConcilied();
				ticket.setMssg((String) response.getMssg());
				ticket.setSucces(String.valueOf(response.getSuccess()));
				ticket.setRefNum((String) object.get("refNum"));
				tickets.add(ticket);
				model.setTickets(tickets);
			} else {
				System.out.println("=== SOY FALSE ===");
				ArrayList<TicketConcilied> tickets = new ArrayList<TicketConcilied>();
				TicketConcilied ticket = new TicketConcilied();
				ticket.setMssg((String) response.getMssg());
				ticket.setSucces(String.valueOf(response.getSuccess()));
				tickets.add(ticket);
				model.setTickets(tickets);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// logger.info(model);
		return model;
	}
	
//	public TicketConciliedModel addIncident(String aplicant, String idCategory, String description, String insertar) {
//		TicketConciliedModel model = new TicketConciliedModel();
//		System.out.println("<======================= VALORES QUE SE LE MANDAN AL TICKET API =======================>" + aplicant + "/" + idCategory + "/" + description);
//		try {
//			
//			
//			JSONObject json = ticketService.addIncident(tokenService.getToken("adri", "adri"), aplicant, idCategory,description,insertar);
//
//			JSONArray array = (JSONArray) json.get("tickets");
//
//			if ((Boolean) json.get("success")) {
//					System.out.println("=== SOY SUCCESS ===");
//				JSONObject object = (JSONObject) array.get(0);
//				ArrayList<TicketConcilied> tickets = new ArrayList<TicketConcilied>();
//				TicketConcilied ticket = new TicketConcilied();
//				ticket.setMssg((String) json.get("mssg"));
//				ticket.setSucces((String) json.get("success").toString());
//				ticket.setRefNum((String) object.get("refNum"));
//				tickets.add(ticket);
//				model.setTickets(tickets);
//			} else {
//				System.out.println("=== SOY FALSE ===");
//				ArrayList<TicketConcilied> tickets = new ArrayList<TicketConcilied>();
//				TicketConcilied ticket = new TicketConcilied();
//				ticket.setMssg((String) json.get("mssg"));
//				ticket.setSucces((String) json.get("success").toString());
//				tickets.add(ticket);
//				model.setTickets(tickets);
//			}
//			
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		// logger.info(model);
//		return model;
//	}
	
	 public String getOrganizationByUser(String username) {

			String organizationName = "";
			String organizationId   = "";
			UserForOrganization request = new UserForOrganization();
			request.setUsername(username);
			try {
				System.out.println("entro a getOrganizationByUser con username: ---------------------------------------->" + username);
				OrganizationList responseManager = userManager.returnOrganizationByUser(request);
				if (responseManager != null) {
					if ((Boolean) responseManager.getSuccess()) {
						for (mx.com.tp.smc.response.Organization org : responseManager.getListOrganization()) {
							if (org.getOrganizationName() != null)

								organizationName=org.getOrganizationName().toString();

						}
					}
				} else {
					logger.info("No se encontraron organizaciones");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// logger.info(organizationName);
			return organizationName;
		}
	 
// Versión anterior
//	    public String getOrganizationByUser(String username) {
//
//		String organizationName = "";
//		String organizationId   = "";
//		try {
//			System.out.println("entro a getOrganizationByUser con username: ---------------------------------------->" + username);
//			JSONObject json2 = userService.getOrganizationByUser(tokenService.getToken("adri", "adri"), username);
//			if (json2 != null) {
//				if ((Boolean) json2.get("success")) {
//					JSONArray array2 = (JSONArray) json2.get("listOrganization");
//					for (int i = 0; i < array2.size(); i++) {
//						JSONObject object2 = (JSONObject) array2.get(i);
//						if (object2.get("organizationName") != null)
//
//							organizationName=object2.get("organizationName").toString();
//							//organizationId = object2.get("organizationId").toString();
//
//					}
//				}
//			} else {
//				logger.info("No se encontraron organizaciones");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		// logger.info(organizationName);
//		return organizationName;
//	}
	    
//	    Marcko
	    public String getOrganizationByUserID(String username) {
			System.out.println("Entro a getOrganizationByUserID con username: -------------------------------------->" + username);
			String organizationName = "";
			String organizationId   = "";
			
			try {
				UserForOrganization request = new UserForOrganization();
				request.setUsername(username);
				System.out.println("getUsername()--------------------->"+ request.getUsername());
				OrganizationList responseManager = userManager.returnOrganizationByUser(request);
	
				if(responseManager.getListOrganization()!=null || responseManager.getListOrganization().isEmpty()) {
					for (mx.com.tp.smc.response.Organization org : responseManager.getListOrganization()) { 
						organizationId = org.getOrganizationId();
						System.out.println("organizationId------>" + organizationId);
					}
				} else {
				logger.info("No se encontraron organizaciones");
				}
					} catch (Exception e) {
						e.printStackTrace();
						}
	
		// logger.info(organizationName);
		return organizationId;
	
		}
	
	
//Versión Anterior
//		public String getOrganizationByUserID(String username) {
//			System.out.println("Entro a getOrganizationByUserID con usename------>" + username);
//			String organizationName = "";
//			String organizationId   = "";
//			try {
//				JSONObject json2 = userService.getOrganizationByUser(tokenService.getToken("adri", "adri"), username);
//				if (json2 != null) {
//					if ((Boolean) json2.get("success")) {
//						JSONArray array2 = (JSONArray) json2.get("listOrganization");
//						for (int i = 0; i < array2.size(); i++) {
//							JSONObject object2 = (JSONObject) array2.get(i);
//							if (object2.get("organizationName") != null)
//
//								//organizationName=object2.get("organizationName").toString();
//								organizationId = object2.get("organizationId").toString();
//
//						}
//					}
//				} else {
//					logger.info("No se encontraron organizaciones");
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//          System.out.println("organizationId------>" + organizationId);
//			// logger.info(organizationName);
//			return organizationId;
//		}
		

//	Marco
	public OrigenHomeModel getOrigenHome() {
		OrigenHomeModel model = new OrigenHomeModel();
		try {

			OrigenHomeList responseManager = rolManager.returnAllOrigenHomes();
			if ((Boolean) responseManager.getSuccess()) {
				responseManager.getListOrigenHome();

				ArrayList<CatOrigenHome> origenHomes = new ArrayList<CatOrigenHome>();
				for (mx.com.tp.smc.response.CatOrigenHome catOrigenHome : responseManager.getListOrigenHome()) {

					CatOrigenHome coh = new CatOrigenHome();

					if (catOrigenHome.getIdHome() != null)
						coh.setIdHome((Long) catOrigenHome.getIdHome());

					if (catOrigenHome.getOrigenHome() != null)
						coh.setOrigenHome((String) catOrigenHome.getOrigenHome());

					if (catOrigenHome.getUrlHome() != null)
						coh.setUrlHome((String) catOrigenHome.getUrlHome());

					if (catOrigenHome.getDescripcion() != null)
						coh.setDescripcion((String) catOrigenHome.getDescripcion());

					origenHomes.add(coh);
				}
				model.setOrigenHome(origenHomes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
//Versión anterior	
//	public OrigenHomeModel getOrigenHome() {
//		OrigenHomeModel model = new OrigenHomeModel();
//		try {
//
//			JSONObject json = origenHomeService.getOrigenHome(tokenService.getToken("adri", "adri"));
//
//			if ((Boolean) json.get("success")) {
//				JSONArray array = (JSONArray) json.get("listOrigenHome");
//				ArrayList<CatOrigenHome> origenHomes = new ArrayList<CatOrigenHome>();
//
//				for (int i = 0; i < array.size(); i++) {
//					JSONObject object = (JSONObject) array.get(i);
//					CatOrigenHome coh = new CatOrigenHome();
//
//					if (object.get("idHome") != null)
//						coh.setIdHome((Long) object.get("idHome"));
//
//					if (object.get("origenHome") != null)
//						coh.setOrigenHome((String) object.get("origenHome"));
//
//					if (object.get("urlHome") != null)
//						coh.setUrlHome((String) object.get("urlHome"));
//
//					if (object.get("descripcion") != null)
//						coh.setDescripcion((String) object.get("descripcion"));
//
//					origenHomes.add(coh);
//				}
//				model.setOrigenHome(origenHomes);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}
	
	public StatusModel getAllStatus() {
		StatusModel model = new StatusModel();
		try {
			StatusList response2 = catalogosManager.getAllStatus();
			if ((Boolean) response2.getSuccess()) {
				
				ArrayList<Status> statusList = new ArrayList<Status>();
				
				for (mx.com.tp.smc.response.Status statusr: response2.getListStatus()) {
					Status status = new Status();
					status.setIdStatus((Long) statusr.getIdStatus());
					status.setDel((Long) statusr.getDel());
					status.setPersid((String) statusr.getPersid());
					status.setSym((String) statusr.getSym());
					status.setDescription((String) statusr.getDescription());
					status.setCode((String) statusr.getCode());
					status.setActive((Long) statusr.getActive());
					statusList.add(status);
				}
				
				model.setStatus(statusList);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return model;
	}

//Versión anterior	
//	public StatusModel getAllStatus() {
//		StatusModel model = new StatusModel();
//		try {
//			JSONObject json = ticketService.getAllStatus(tokenService.getToken("adri", "adri"));
//			if ((Boolean) json.get("success")) {
//		
//				JSONArray array = (JSONArray) json.get("listStatus");
//				ArrayList<Status> statusList = new ArrayList<Status>();
//				
//				for (int i = 0; i < array.size(); i++) {
//					JSONObject object = (JSONObject) array.get(i);
//					Status status = new Status();
//					status.setIdStatus((Long) object.get("idStatus"));
//					status.setDel((Long) object.get("del"));
//					status.setPersid((String) object.get("persid"));
//					status.setSym((String) object.get("sym"));
//					status.setDescription((String) object.get("description"));
//					status.setCode((String) object.get("code"));
//					status.setActive((Long) object.get("active"));
//					statusList.add(status);
//				}
//				
//				model.setStatus(statusList);
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}
	
	
//	Marco
//	public StatusModel getUpdateStatus(String incidente, String selectStatus, String region) {
//		StatusModel model = new StatusModel();
//		UpdateStatus request = new UpdateStatus();
//		request.setRegion(region);
//		request.set
//		
//		try {
//			UpdateStatus response = incidentsManager.getUpdateStatus(request);
//			JSONObject json = ticketService.getUpdateStatus(tokenService.getToken("adri", "adri"),incidente, selectStatus, region);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}

	
	public StatusModel getUpdateStatus(String incidente, String selectStatus, String region) {
		StatusModel model = new StatusModel();
		UpdateStatus request = new UpdateStatus();
		request.setRegion(region);
		request.setRefNum(incidente);
		request.setSym(selectStatus);
		System.out.println("Valor incidente------------->"+ incidente);
		System.out.println("Valor selectStatus------------->"+ selectStatus);
		System.out.println("Valor region------------->"+ region);
		try {
			UpdateStatus response = incidentsManager.getUpdateStatus(request);
			}catch(Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	
//	Marco
	public TicketModel getAllComment(String refNum) {
		// TODO Auto-generated method stub
		TicketModel model = new TicketModel();
		Comentario request = new Comentario();
		request.setRefNum(refNum);
		
		try {
			CommentList response2 = incidentsManager.getAllComentarios(request);
			if((Boolean) response2.getSuccess()) {
				ArrayList<Ticket> commentList = new ArrayList<Ticket>();
							
					for (Comment comment : response2.getListComment() ) {

					Ticket com = new Ticket();
					com.setPersid((String) comment.getPersid());
					com.setCallReqid((String) comment.getCallReqid());
					com.setDateInsert((String) comment.getDateInsert());
					com.setDescription((String) comment.getDescription());
					commentList.add(com);
				}
				
				model.setTickets(commentList);
				
			}else {
				ArrayList<Ticket> commentList = new ArrayList<Ticket>();
				Ticket com = new Ticket();
				com.setPersid("");
				com.setCallReqid("");
				com.setDateInsert("");
				com.setDescription("No se encontraron comentarios al Ticket " + refNum);
				commentList.add(com);
				
				model.setTickets(commentList);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	
	
//	Versión anterior
//	public TicketModel getAllComment(String refNum) {
//		// TODO Auto-generated method stub
//		TicketModel model = new TicketModel();
//		try {
//			JSONObject json = ticketService.getAllComentarios(tokenService.getToken("adri", "adri"), refNum);
//			if((Boolean) json.get("success")) {
//				JSONArray array = (JSONArray) json.get("listComment");
//				ArrayList<Ticket> commentList = new ArrayList<Ticket>();
//							
//				for (int i = 0; i < array.size(); i++) {
//					JSONObject object = (JSONObject) array.get(i);
//					Ticket com = new Ticket();
//					com.setPersid((String) object.get("persid"));
//					com.setCallReqid((String) object.get("callReqid"));
//					com.setDateInsert((String) object.get("dateInsert"));
//					com.setDescription((String) object.get("description"));
//					commentList.add(com);
//				}
//				
//				model.setTickets(commentList);
//				
//			}else {
//				ArrayList<Ticket> commentList = new ArrayList<Ticket>();
//				Ticket com = new Ticket();
//				com.setPersid("");
//				com.setCallReqid("");
//				com.setDateInsert("");
//				com.setDescription("No se encontraron comentarios al Ticket " + refNum);
//				commentList.add(com);
//				
//				model.setTickets(commentList);
//			}
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}
//	
	
}
