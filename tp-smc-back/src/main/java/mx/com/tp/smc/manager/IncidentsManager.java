package mx.com.tp.smc.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import org.joda.time.DateTime;
import org.joda.time.Period;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.com.tp.smc.dictionary.Dictionary;
import mx.com.tp.smc.entity.DesService;
import mx.com.tp.smc.request.AddComentario;
import mx.com.tp.smc.request.Comentario;
import mx.com.tp.smc.request.Regiones;
import mx.com.tp.smc.request.TicketNotConciliedValidationUpdate;
import mx.com.tp.smc.request.UpdateStatus;
import mx.com.tp.smc.response.AddComment;
import mx.com.tp.smc.response.CatRegionesList;
import mx.com.tp.smc.response.CategoriesConsult;
import mx.com.tp.smc.response.Comment;
import mx.com.tp.smc.response.CommentList;
import mx.com.tp.smc.response.IncidentsConsult;
import mx.com.tp.smc.response.Point;
import mx.com.tp.smc.response.PointCMDB;
import mx.com.tp.smc.response.PointList;
import mx.com.tp.smc.response.PointListCMDB;
import mx.com.tp.smc.response.TicketClosed;
import mx.com.tp.smc.response.TicketClosedList;
import mx.com.tp.smc.response.TicketConcilied;
import mx.com.tp.smc.response.TicketConciliedList;
import mx.com.tp.smc.service.TicketServiceBack;
import mx.com.tp.smc.service.TokenServiceBack;
import mx.com.tp.smc.service.CatalogoService;
import java.sql.*;

@Component
public class IncidentsManager {

	final static Logger log = Logger.getLogger(IncidentsManager.class); 
	final static Logger logger = Logger.getLogger(IncidentsManager.class);
	
	//significa que instancia la clase, es igual a poner TokenService tk= new TokenService();
	@Autowired 
	private TokenServiceBack serviceToken;
	
	@Autowired
	private TicketServiceBack serviceTicket;
	
	@Autowired
	private CatalogoService catalogoService;
	
	public IncidentsConsult getAllTickets(String organization) {
		DateTime initial = new DateTime();
		JSONArray array = null;
		boolean success = true;
		String error = null;
		String mssg = "Se consiguieron todos los tickets activos de la organizacion" + organization;
		int total = 0;
		try {
			String token = serviceToken.getToken();
			if (token != null) {
				System.out.println("LLL");
				JSONObject response = serviceTicket.getTicketsByOrganization(token,initial.getYear(),initial.getMonthOfYear(),organization, Dictionary.TYPE);
				System.out.println("xxx");
				if (Boolean.parseBoolean(response.get("success").toString()) && response.get("tickets") != null) {
					array = (JSONArray) response.get("tickets");
					total = Integer.parseInt(response.get("total").toString());
				}else {
					success = false;
					mssg = "No se obtuvieron tickets de la organizacion: " + organization;
				}
			}	
				
		} catch (Exception ex) {
			mssg = "Error al obtener todos los tickets";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}
		Period period = new Period(initial, new DateTime());
		return new IncidentsConsult(success, period, mssg, total,error, array);
	}
	
	
	public IncidentsConsult getTicketsByStatus(String status, String organization) {
		DateTime initial = new DateTime();
		JSONArray array = null;
		boolean success = false;
		String error = "";
		String mssg = "Error al consultar el servicio";
		int total = 0;
		try {
			String token = serviceToken.getToken();
			if (token != null) {
				JSONObject response = serviceTicket.getTicketByStatus(token, status, initial.getYear(),initial.getMonthOfYear(), organization);
				if (Boolean.parseBoolean(response.get("success").toString()) && response.get("tickets") != null) {
					array = (JSONArray) response.get("tickets");
					total = Integer.parseInt(response.get("total").toString());
					mssg = "Exito al conseguir todos los tickets";
					success= true;
					
				}
			}		
		} catch (Exception ex) {
			mssg = "Error  al obtener tickets por status";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}
		Period period = new Period(initial, new DateTime());
		return new IncidentsConsult(success, period, mssg, total,error, array);
	}
	
	
	public PointListCMDB getPointByOrg(String organization, String tenant) {
		DateTime initial = new DateTime();
		JSONArray array = null;
		boolean success = false;
		String error = "", mssg = "Error al consultar el servicio";
		int total = 0;
		List<PointCMDB> list = null;
		try {
//			System.out.println("response - 1");
			String token = serviceToken.getToken();
//			System.out.println("response - 2");
			if (token != null) {
				
				JSONObject response = serviceTicket.getPointByOrganization(token, organization, tenant);
				System.out.println("response - > " + response.toString());
				if (Boolean.parseBoolean(response.get("success").toString()) && response.get("contacts") != null) {
					array = (JSONArray) response.get("contacts");
					total = Integer.parseInt(response.get("total").toString());
					list = new ArrayList<PointCMDB>();
					for(int i=0; i<array.size();i++) {
						JSONObject jsonObject = (JSONObject) array.get(i);
						PointCMDB object = new PointCMDB();
						object.setPuntaname((String)jsonObject.get("comboName"));
						object.setPuntaid((String)jsonObject.get("id"));
						object.setHostname((String)jsonObject.get("hostName"));
						object.setMacaddress((String)jsonObject.get("macAddress"));
						object.setIpaddress((String)jsonObject.get("ipAddress"));
						object.setTenant((String)jsonObject.get("tenant"));
						//object.setName((String)jsonObject.get("comboName"));
						//object.setUserId((String)jsonObject.get("userId"));
						//object.setTenant(null != jsonObject.get("tenant") ? "" + jsonObject.get("tenant") : "");
						list.add(object);    
					}
					success = true;
					mssg = "Se consiguieron las puntas";
				}	
			}
		}catch(Exception ex) {
			mssg = "Ocurrio un error al obtener las puntas de la organizacion";
			error = "Error:" + ex;
			log.error("Error:" + ex);
//			System.out.println("-------------------------------"+ organization);
		}
//		System.out.println("size - > " + list.size());
		
		Period period = new Period(initial, new DateTime());
		return new PointListCMDB(success, period,error,mssg,total,list);
	}
	
	
	public PointList getPointByOrgDH(String organization, String tenant) throws ClassNotFoundException {
		DateTime initial = new DateTime();
		JSONArray array = null;
		boolean success = false;
		String error = "", mssg = "Error al consultar el servicio";
		int total = 0;
		List<Point> list = null;
		
		
		try {
	          Class.forName("com.mysql.jdbc.Driver");
	          Connection conexion=DriverManager.getConnection("jdbc:mysql://localhost/bd1","root" ,"");
	          java.sql.Statement comando=conexion.createStatement();
	          ResultSet registro = comando.executeQuery("select *from CATALOGO_DEVICES_PERFORMANCE_VW");
	          if (registro.next()==true) 
	          {
	            registro.getString("descripcion");
	            registro.getString("precio");
	          } 
	          else 
	          {
	            //labelResultado.setText("No existe un artículo con dicho código");
	          }
	          conexion.close();
	        } catch(SQLException ex)
			{
	          //setTitle(ex.toString());
	        }
		
		
		
		try {
			String token = serviceToken.getToken();
			if (token != null) {
				JSONObject response = serviceTicket.getPointByOrganization(token, organization, tenant);
				if (Boolean.parseBoolean(response.get("success").toString()) && response.get("contacts") != null) {
					array = (JSONArray) response.get("contacts");
					total = Integer.parseInt(response.get("total").toString());
					list = new ArrayList<Point>();
					for(int i=0; i<array.size();i++) {
						JSONObject jsonObject = (JSONObject) array.get(i);
						Point object = new Point();
						object.setName((String)jsonObject.get("comboName"));
						object.setUserId((String)jsonObject.get("userId"));
						list.add(object);    
					}
					success = true;
					mssg = "Se consiguieron las puntas";
				}	
			}
		}catch(Exception ex) {
			mssg = "Ocurrio un error al obtener las puntas de la organizacion";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}
		
		Period period = new Period(initial, new DateTime());
		return new PointList(success, period,error,mssg,total,list);
	}
	
	
	public IncidentsConsult insertTicket(String organizacion, String applicant, String category, String[] description, String regCiudad, String insertar) {
		DateTime initial = new DateTime();
		JSONArray array = null;
		boolean success = false;
		String error = "";
		String mssg = "Error al conectar con el servicio";
		try {
			System.out.println("== APPLICANT ==" + applicant);
			System.out.println("== categori ==" + category);
			System.out.println("== URI ==" + insertar);
			
			String token = serviceToken.getToken();
			if (token != null) {
				JSONObject response = serviceTicket.insertTicket(token,organizacion, applicant, category, description,regCiudad,insertar);
				System.out.println("valor ============ 2" + response);
				
				if (Boolean.parseBoolean(response.get("success").toString()) && response.get("tickets") != null) {
					System.out.println("valor ============3 " + response.get("tickets"));
					array = (JSONArray) response.get("tickets");
					success = true;
					mssg = "El ticket se inserto correctamente";					
				}	
			}
		}catch(Exception ex) {
			mssg = "Error  al insertar ticket";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}
		Period period = new Period(initial, new DateTime());
		return new IncidentsConsult(success, period, mssg,1, error, array);
	}

	
	
	public CategoriesConsult getCategory(String bandeja) {
		DateTime initial = new DateTime();
		JSONArray array = null;
		boolean success = false;
		String error = "";
		String mssg = "Error al consultar la categoria";
		int total = 0;
		try {
			String token = serviceToken.getToken();
			if (token != null) {
				JSONObject response = serviceTicket.getCategorys(token, bandeja);
				if (Boolean.parseBoolean(response.get("success").toString()) && response.get("categories") != null) {
					array = (JSONArray) response.get("categories");
					total = Integer.parseInt(response.get("total").toString());
					mssg = "Exito al conseguir todos las categorias";
					success = true;
				}
			}	
				
		} catch (Exception ex) {
			mssg = "Error al obtener las categorias";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}
		Period period = new Period(initial, new DateTime());
		return new CategoriesConsult(success, period, mssg, total,error, array);
		
	}
	
	

	public TicketConciliedList returnAllNotConcilied(String organization) {
		
		System.out.println("returnAllNotConcilied: "+ organization);
		
		DateTime initial = new DateTime();
		boolean success = false;
		String error = "";
		String mssg = "Error al extraer los datos";
		int total = 0;
		List<TicketConcilied> listTicket = new ArrayList<TicketConcilied>();

		try {
			String [] status = {"Cerrado","PorValidar"};
			List<DesService> conciliedList = new ArrayList<DesService>();
			conciliedList = serviceTicket.getByStatusCloseAndValidation(status,organization);
			for(DesService ticket: conciliedList) {
				TicketConcilied obj = new TicketConcilied();
				obj.setFolioIncidencia(ticket.getFolioIncidencia());
				obj.setResumen(ticket.getResumen());
				System.out.println("###########" + ticket.getDescripcion());
				obj.setDescripcion(ticket.getDescripcion());
				obj.setDiagInicial(ticket.getDiagInicial());
				obj.setActRealizadas(ticket.getActRealizadas());
				obj.setAfectacion(ticket.getAfectacion());
				obj.setRegion(ticket.getRegion());
				obj.setZesProactivoReactivo(ticket.getZesProactivoReactivo());
				obj.setRegionciudad(ticket.getRegionciudad());
				obj.setActivo(ticket.getActivo());
				obj.setTicketSExterno(ticket.getTicketSExterno());
				obj.setCategoria(ticket.getCategoria());
				obj.setZfallaImputableCte(ticket.getZfallaImputableCte());
				obj.setTiempoImputableCte(ticket.getTiempoImputableCte());
				obj.setZfallaImputableTpe(ticket.getZfallaImputableTpe());
				obj.setTiempoImputableTpe(ticket.getTiempoImputableTpe());
				obj.setDiagFinal(ticket.getDiagFinal());
				obj.setSolucion(ticket.getSolucion());
				obj.setFolioStatus(ticket.getFolioStatus());
				obj.setPrioridad(ticket.getPrioridad());
				obj.setUrgencia(ticket.getUrgencia());
				obj.setImpacto(ticket.getImpacto());
				obj.setCreadoVia(ticket.getCreadoVia());
				obj.setFolioParentid(ticket.getFolioParentid());
				obj.setFolioAbierto(ticket.getFolioAbierto());
				obj.setFolioUltimamodificacion(ticket.getFolioUltimamodificacion());
				obj.setFolioTitulo(ticket.getFolioTitulo());
				obj.setFolioTitulo2(ticket.getFolioTitulo2());
				obj.setFolioTitulo3(ticket.getFolioTitulo3());
				obj.setZNomPuntaInex(ticket.getZNomPuntaInex());
				obj.setFolioTipo(ticket.getFolioTipo());
				obj.setFolioSubtipo(ticket.getFolioSubtipo());
				obj.setFolioGrupo(ticket.getFolioGrupo());
				obj.setNomSolicitanteAa(ticket.getNomSolicitanteAa());
				obj.setNom2SolicitanteAa(ticket.getNom2SolicitanteAa());
				obj.setApeSolicitanteAa(ticket.getApeSolicitanteAa());
				obj.setNomSolicitante(ticket.getNomSolicitante());
				obj.setNom2Solicitante(ticket.getNom2Solicitante());
				obj.setApeSolicitante(ticket.getApeSolicitante());
				obj.setFolioDuracion(ticket.getFolioDuracion());
				obj.setFhSolucion(ticket.getFhSolucion());
				obj.setFhndesolucion(ticket.getFhndesolucion());
				obj.setFolioCerrado(ticket.getFolioCerrado());
				obj.setOrganizacion(ticket.getOrganizacion());
				obj.setZtiempofallaterceros(ticket.getZtiempofallaterceros());
				System.out.println("=== valor de tiempo falla terceros ===" + ticket.getZtiempofallaterceros());
				obj.setZtiempofallaprov(ticket.getZtiempofallaprov());

				listTicket.add(obj);
				mssg = "Exito al conseguir los Datos";
				success= true;
				total = conciliedList.size();
			}
			
		} catch (Exception ex) {
			mssg = "Error al Agregar los datos";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}
		
		Period period = new Period(initial, new DateTime());
		return new TicketConciliedList(success, period, error, mssg, total, listTicket);
	}
	
	public TicketConciliedList returnAllNotConciliedValidation(String organizacion) {
		DateTime initial = new DateTime();
		boolean success = false;
		String error = "";
		String mssg = "Error al extraer los datos";
		int total = 0;
		List<TicketConcilied> listTicket = new ArrayList<TicketConcilied>();
			System.out.println("ORGANIZACION M - " +  organizacion);
		try {
			String status = "Validado";
			List<DesService> conciliedList = serviceTicket.getAllTicketNotConcilied(status, organizacion);
			System.out.println("EL VALOR QUE RETORNA ES : " + conciliedList.size());
			for(DesService ticket: conciliedList) {
				TicketConcilied obj = new TicketConcilied();
				obj.setFolioIncidencia(ticket.getFolioIncidencia());
				obj.setResumen(ticket.getResumen());
				obj.setDescripcion(ticket.getDescripcion());
				obj.setDiagInicial(ticket.getDiagInicial());
				obj.setActRealizadas(ticket.getActRealizadas());
				obj.setAfectacion(ticket.getAfectacion());
				obj.setRegion(ticket.getRegion());
				obj.setZesProactivoReactivo(ticket.getZesProactivoReactivo());
				obj.setRegionciudad(ticket.getRegionciudad());
				obj.setActivo(ticket.getActivo());
				obj.setTicketSExterno(ticket.getTicketSExterno());
				obj.setCategoria(ticket.getCategoria());
				obj.setZfallaImputableCte(ticket.getZfallaImputableCte());
				obj.setTiempoImputableCte(ticket.getTiempoImputableCte());
				obj.setZfallaImputableTpe(ticket.getZfallaImputableTpe());
				obj.setTiempoImputableTpe(ticket.getTiempoImputableTpe());
				obj.setDiagFinal(ticket.getDiagFinal());
				obj.setSolucion(ticket.getSolucion());
				obj.setFolioStatus(ticket.getFolioStatus());
				obj.setPrioridad(ticket.getPrioridad());
				obj.setUrgencia(ticket.getUrgencia());
				obj.setImpacto(ticket.getImpacto());
				obj.setCreadoVia(ticket.getCreadoVia());
				obj.setFolioParentid(ticket.getFolioParentid());
				obj.setFolioAbierto(ticket.getFolioAbierto());
				obj.setFolioUltimamodificacion(ticket.getFolioUltimamodificacion());
				obj.setFolioTitulo(ticket.getFolioTitulo());
				obj.setFolioTitulo2(ticket.getFolioTitulo2());
				obj.setFolioTitulo3(ticket.getFolioTitulo3());
				obj.setZNomPuntaInex(ticket.getZNomPuntaInex());
				obj.setFolioTipo(ticket.getFolioTipo());
				obj.setFolioSubtipo(ticket.getFolioSubtipo());
				obj.setFolioGrupo(ticket.getFolioGrupo());
				obj.setNomSolicitanteAa(ticket.getNomSolicitanteAa());
				obj.setNom2SolicitanteAa(ticket.getNom2SolicitanteAa());
				obj.setApeSolicitanteAa(ticket.getApeSolicitanteAa());
				obj.setNomSolicitante(ticket.getNomSolicitante());
				obj.setNom2Solicitante(ticket.getNom2Solicitante());
				obj.setApeSolicitante(ticket.getApeSolicitante());
				obj.setFolioDuracion(ticket.getFolioDuracion());
				obj.setFhSolucion(ticket.getFhSolucion());
				obj.setFhndesolucion(ticket.getFhndesolucion());
				obj.setFolioCerrado(ticket.getFolioCerrado());
				obj.setZtiempofallaterceros(ticket.getZtiempofallaterceros());
				obj.setZtiempofallaprov(ticket.getZtiempofallaprov());
				
				listTicket.add(obj);
				mssg = "Exito al conseguir los Datos";
				success= true;
				total = conciliedList.size();
			}
			
		} catch (Exception ex) {
			mssg = "Error al Agregar los datos";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}
		
		Period period = new Period(initial, new DateTime());
		return new TicketConciliedList(success, period, error, mssg, total, listTicket);
	}

	
	public TicketConciliedList updateTicketNotConcilied(TicketNotConciliedValidationUpdate request) {
		DateTime initial = new DateTime();
		boolean success = false;
		String error = "";
		String mssg = "Error al insertar los datos";
		int total = 0;
		System.out.println("FALLA TERCEROS  ===" + request.getZtiempoFallaTer());
		
		try {
			
			DesService ue = serviceTicket.getTicketNotConcilied(request.getFolioIncidencia());
			
			if(ue != null) {
				ue.setNomSolicitante(request.getNomSolicitante());
				ue.setNom2Solicitante(request.getNom2Solicitante());
				ue.setApeSolicitante(request.getApeSolicitante());
				ue.setCategoria(request.getCategoria());
				ue.setFolioTitulo(request.getFolioTitulo());
				ue.setDescripcion(request.getDescripcion());
				ue.setSolucion(request.getSolucion());
				ue.setFolioCerrado(request.getFolioCerrado());
				ue.setFolioAbierto(request.getFolioAbierto());
				ue.setFolioTitulo3(request.getFolioTitulo3());
				ue.setDiagFinal(request.getDiagFinal());
				ue.setTiempoImputableCte(request.getTiempoImputableCte());
				ue.setTiempoImputableTpe(request.getTiempoImputableTpe());
				ue.setResumen(request.getResumen());
				ue.setZesProactivoReactivo(request.getProactivoReactivo());
				ue.setAfectacion(request.getAfectacion());
				ue.setZtiempofallaterceros(request.getZtiempoFallaTer());
				ue.setZtiempofallaprov(request.getZtiempoFallaProv());
				
				ue.setFolioStatus("PorValidar");
				serviceTicket.updateTicketNotConcilied(ue);
				mssg = "Exito al insertar los datos";
				success = true;
				total ++;
			}
			
			else
			{
				mssg = "Error al Agregar los datos";
				
				
			}
		} catch (Exception ex) {

			mssg = "Error al Agregar los datos";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}

		Period period = new Period(initial, new DateTime());
		return new TicketConciliedList(success, period, error, mssg, total, null);

	}
	
	public TicketConciliedList updateStatusTicketNotConcilied(TicketNotConciliedValidationUpdate request) {
		DateTime initial = new DateTime();
		boolean success = false;
		String error = "";
		String mssg = "Error al Actualizar los datos";
		int total = 0;

		
		try {
			
			DesService ue = serviceTicket.getTicketNotConcilied(request.getFolioIncidencia());
			
			if(ue != null) {
				ue.setFolioStatus("Conciliado");
				serviceTicket.updateTicketNotConcilied(ue);
				mssg = "Exito al Actualizar los datos";
				success = true;
				total ++;
			}
			
			else
			{
				mssg = "Error al Actualizar los datos";
				
				
			}
		} catch (Exception ex) {

			mssg = "Error al Agregar los datos";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}

		Period period = new Period(initial, new DateTime());
		return new TicketConciliedList(success, period, error, mssg, total, null);

	}

	public TicketConciliedList updateTicketPorValidar(TicketNotConciliedValidationUpdate request) {
		DateTime initial = new DateTime();
		boolean success = false;
		String error = "";
		String mssg = "Error al Actualizar los datos";
		int total = 0;

		try {

			DesService ue = serviceTicket.getTicketNotConcilied(request.getFolioIncidencia());

			if (ue != null) {
				ue.setFolioStatus("PorValidar");
				serviceTicket.updateTicketNotConcilied(ue);
				mssg = "Exito al Actualizar los datos";
				success = true;
				total++;
			}

			else {
				mssg = "Error al Actualizar los datos";

			}
		} catch (Exception ex) {

			mssg = "Error al Agregar los datos";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}

		Period period = new Period(initial, new DateTime());
		return new TicketConciliedList(success, period, error, mssg, total, null);

	}
	
	public TicketConciliedList updateStatusTicketNotConciliedValidation(TicketNotConciliedValidationUpdate request) {
		DateTime initial = new DateTime();
		boolean success = false;
		String error = "";
		String mssg = "Error al insertar los datos";
		int total = 0;

		
		try {
			
			DesService ue = serviceTicket.getTicketNotConcilied(request.getFolioIncidencia());
			
			if(ue != null) {
				ue.setFolioStatus("Validado");
				serviceTicket.updateTicketNotConcilied(ue);
				mssg = "Exito al insertar los datos";
				success = true;
				total ++;
			}
			
			else
			{
				mssg = "Error al Agregar los datos";
				
				
			}
		} catch (Exception ex) {

			mssg = "Error al Agregar los datos";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}

		Period period = new Period(initial, new DateTime());
		return new TicketConciliedList(success, period, error, mssg, total, null);
	}
	
	
	public TicketClosedList returnAllClosed(String organization) {
		
		System.out.println("****ENTRO/returnAllClosed***"+organization);
		
		DateTime initial = new DateTime();
		boolean success = false;
		String error = "";
		String mssg = "Error al extraer los datos";
		int total = 0;
		List<TicketClosed> listTicket = new ArrayList<TicketClosed>();

		try {
			String [] status = {"Conciliado","Validado"};
			List<DesService> conciliedList = new ArrayList<DesService>();
			
			System.out.println("****ANTES DE LA BUSQUEDA***"+organization+" "+status);
			
			conciliedList = serviceTicket.getByStatusCloseAndValidation(status,organization);
			
			System.out.println("****DESPUES DE LA BUSQUEDA***"+conciliedList);
			
			for (DesService ticket : conciliedList) {
				TicketClosed obj = new TicketClosed();
				obj.setDescripcion(ticket.getDescripcion());
				obj.setFolioIncidencia(ticket.getFolioIncidencia());
				obj.setFolioAbierto(ticket.getFolioAbierto());
				obj.setResumen(ticket.getResumen());
				obj.setFolioTitulo3(ticket.getFolioTitulo3());
				obj.setTiempoImputableCte(ticket.getTiempoImputableCte());
				obj.setTiempoImputableTpe(ticket.getTiempoImputableTpe());
				obj.setFolioCerrado(ticket.getFolioCerrado());
				obj.setNomSolicitante(ticket.getNomSolicitante());
				obj.setNom2Solicitante(ticket.getNom2Solicitante());
				obj.setApeSolicitante(ticket.getApeSolicitante());
				obj.setCategoria(ticket.getCategoria());
				obj.setNomSolicitanteAa(ticket.getNomSolicitanteAa());
				obj.setNom2SolicitanteAa(ticket.getNom2SolicitanteAa());
				obj.setApeSolicitanteAa(ticket.getApeSolicitanteAa());
				obj.setTicketSExterno(ticket.getTicketSExterno());
				obj.setFolioTitulo(ticket.getFolioTitulo());
				obj.setDiagFinal(ticket.getDiagFinal());
				obj.setSolucion(ticket.getSolucion());
				obj.setFolioStatus(ticket.getFolioStatus());
				obj.setAfectacion(ticket.getAfectacion());
				System.out.println("AFECTACION === " + ticket.getAfectacion());
				obj.setZesProactivoReactivo(ticket.getZesProactivoReactivo());
				obj.setZtiempofallaTerceros(ticket.getZtiempofallaterceros());
				obj.setZtiempofallaProv(ticket.getZtiempofallaprov());
				listTicket.add(obj);
				mssg = "Exito al conseguir los Datos";
				success = true;
				total = conciliedList.size();
			}

		} catch (Exception ex) {
			mssg = "Error al Agregar los datos";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}

		Period period = new Period(initial, new DateTime());
		return new TicketClosedList(success, period, error, mssg, total, listTicket);
	}


	public CommentList getAllComentarios(Comentario request) {
		DateTime initial = new DateTime();
		JSONArray array = null;
		boolean success = true;
		String error = "";
		String mssg = "Se consiguieron todos los Comentarios del ticket";
		int total = 0;
		ArrayList<Comment> listComent = null; 
		
		try {
			
			String token = serviceToken.getToken();
			if(token != null) {
			JSONObject response = catalogoService.obtenerComentariosTicket(token,request.getRefNum(),request.getType());
				if(Boolean.parseBoolean(response.get("success").toString()) && response.get("comentarios") != null) {
					array = (JSONArray) response.get("comentarios");
					System.out.println("=== VALOR DEL ARRAY === " + array);
					
					listComent = new ArrayList<Comment>();
					
					for(int i=0; i<array.size();i++) {
						JSONObject json = (JSONObject) array.get(i);
						Comment comment = new Comment();
						comment.setPersid((String)json.get("persid"));
						comment.setCallReqid((String)json.get("callReqId"));
						comment.setDateInsert((String)json.get("dateInsert"));
						comment.setDescription((String)json.get("description"));
						listComent.add(comment);
						System.out.println("=== VALOR DE LA LISTA ===" + listComent);
					}
					total = Integer.parseInt(response.get("total").toString());
					mssg = "Exito al conseguir los Datos";
					success = true;

				}
			}			
		}catch(Exception e) {
			success = false;
			mssg = "Ocurrio un error al obtener los estatus" + e;
			error = "Error:" + e;
			logger.error("Error:" + e);
		}
		
		Period period = new Period(initial, new DateTime());
		return new CommentList(success, period, error, mssg, total, listComent);
	}


	public AddComment getAddComentarios(AddComentario request) {
		// TODO Auto-generated method stub
		DateTime initial = new DateTime();
		JSONArray array = null;
		boolean success = true;
		String error = "";
		String mssg = "Se consiguieron todos los Comentarios del ticket";
		int total = 0;
		ArrayList<Comment> listComent = null;
		
		try {
			String token = serviceToken.getToken();
			if(token != null) {
				JSONObject response = serviceTicket.getAddComentario(token,request.getRefNum(), request.getComment(), request.getCreatorUUID());
				if(Boolean.parseBoolean(response.get("success").toString()) && response.get("comentarios") != null) {
					array = (JSONArray) response.get("comentarios");
					System.out.println("=== VALOR DEL ARRAY === " + array);
					
					listComent = new ArrayList<Comment>();
					
					for(int i=0; i<array.size();i++) {
						JSONObject json = (JSONObject) array.get(i);
						Comment comment = new Comment();
						comment.setPersid((String)json.get("persid"));
						comment.setCallReqid((String)json.get("callReqId"));
						comment.setDateInsert((String)json.get("dateInsert"));
						comment.setDescription((String)json.get("description"));
						listComent.add(comment);
						System.out.println("=== VALOR DE LA LISTA ===" + listComent);
					}
					total = Integer.parseInt(response.get("total").toString());
					mssg = "Exito al conseguir los Datos";
					success = true;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
				
		return null;
	}


	public UpdateStatus getUpdateStatus(UpdateStatus request) {
		// TODO Auto-generated method stub
//		DateTime initial = new DateTime();
//		JSONArray array = null;
//		boolean success = false;
//		String error = "";
//		String mssg = "Error al conectar con el servicio";
//		int total = 0;
//		ArrayList<Status> listStatus= null;
		
		try {
			String token = serviceToken.getToken();
			
			if(token != null) {
				System.out.println("=== ENTRE AL MANAGER == " + request.getRefNum());
				System.out.println("Valor getRefNum------------->"+ request.getRefNum());
				System.out.println("Valor getSym------------->"+ request.getSym());
				System.out.println("Valor region------------->"+ request.getRegion());
				JSONObject response = serviceTicket.getUpdateStatus(token, request.getRefNum(),request.getSym(), request.getRegion());
				System.out.println("=== VALOR DEL RESPONSE == " + response);
				if(Boolean.parseBoolean(response.get("success").toString()) && response.get("comentarios") != null) {
					
				}
			}
		}catch(Exception e) {
			
		}
		return null;
	}


	public CatRegionesList getCatRegiones() {
	
		DateTime initial = new DateTime();
		JSONArray array = null;
		boolean success = true;
		String error = "";
		String mssg = "Se consiguieron todos los Comentarios del ticket";
		int total = 0;
		ArrayList<Regiones> listRegion = null;
		
		try {
			String token = serviceToken.getToken();
			if(token != null) {
				array = catalogoService.getCatRegiones(token);
				listRegion = new ArrayList<Regiones>();
				
				for (int i = 0; i < array.size(); i++) {
					JSONObject json = (JSONObject) array.get(i);
					Regiones obj = new Regiones();
					System.out.println("=== VALOR DEL OBJECTO PARA REGIONES === " + json);
					obj.setId((String) json.get("id"));
					obj.setSym((String) json.get("sym"));
					listRegion.add(obj);
				}
				success = true;
				mssg = "Se consiguieron todos los estatus";
				
			}
		}catch(Exception e ) {
			success = false;
			mssg = "Ocurrio un error al obtener las regiones" + e;
			error = "Error:" + e;
			logger.error("Error:" + e);
		}
		Period period = new Period(initial, new DateTime());
		return new CatRegionesList(success, period, error, mssg, total, listRegion);
	}

	
}
