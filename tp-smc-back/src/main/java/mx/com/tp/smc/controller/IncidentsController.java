package mx.com.tp.smc.controller;

import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import mx.com.tp.smc.manager.CatalogosManager;
import mx.com.tp.smc.manager.IncidentsManager;
import mx.com.tp.smc.request.AddComentario;
import mx.com.tp.smc.request.AddIncident;
import mx.com.tp.smc.request.Comentario;
import mx.com.tp.smc.request.Organization;
import mx.com.tp.smc.request.Status;
import mx.com.tp.smc.request.Tenant;
import mx.com.tp.smc.request.TicketNotConciliedValidationUpdate;
import mx.com.tp.smc.request.UpdateStatus;
import mx.com.tp.smc.response.AddComment;
import mx.com.tp.smc.response.CategoriesConsult;
import mx.com.tp.smc.response.CommentList;
import mx.com.tp.smc.response.IncidentsConsult;
import mx.com.tp.smc.response.PointListCMDB;
import mx.com.tp.smc.response.TicketClosedList;
import mx.com.tp.smc.response.TicketConciliedList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/incident")
@Api(value="incidentes")
public class IncidentsController{
	
	final static Logger log = Logger.getLogger(IncidentsController.class);
	
	@Autowired
	private IncidentsManager incidentsManager; // le decimos que utilizaremos la clase IncidentsManager, sin instanciar objetos con new
	//ok
	//seccion consultar del menu incidentes.
    //URL QUE SOLICITA EL FRONT END : http://10.180.251.111:8080/portaltpe/incident/organization?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	@RequestMapping(value="/organization", method=RequestMethod.POST)
	@ApiOperation(value="Obtiene todos los tickets activos", notes="obtiene todos tickets activos")
	public ResponseEntity<IncidentsConsult> getallIncident(@RequestBody Organization request) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		log.info("User: " + auth.getName() + ", Method: getallIncidentActive" + ", Role: " + auth.getAuthorities());
		IncidentsConsult response = incidentsManager.getAllTickets(request.getOrganization());
		return  new ResponseEntity<IncidentsConsult> (response,HttpStatus.OK);
	}
	
	//esto es para la aplicacion movil
	@RequestMapping(value="/status", method=RequestMethod.POST)
	@ApiOperation(value="Obtiene tickets por status", notes ="obtiene tickets por status")
	public ResponseEntity<IncidentsConsult> getIncidentsByStatus(@RequestBody Status request){
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		log.info("User: " + auth.getName() + ", Method: getIncidentsByStatus" + ", Role: " + auth.getAuthorities());
		IncidentsConsult response = incidentsManager.getTicketsByStatus(request.getStatus(),request.getOrganization());
		return  new ResponseEntity<IncidentsConsult> (response,HttpStatus.OK);
	}
//	ok
	//seccion Insertar Ticket
	//http://10.180.251.111:8080/portaltpe/incident/add?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	@RequestMapping(value="/add", method = RequestMethod.POST)
	@ApiOperation(value = "inserta un nuevo ticket", notes = "Inserta un nuevo ticket")
	public ResponseEntity<IncidentsConsult> addTicket(@Valid @RequestBody AddIncident request) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		log.info("User: " + auth.getName() + ", Method: AddIncident" + ", Role: " + auth.getAuthorities());
		System.out.println("=== ENTRO A INSERTAR TICKET === ");
		IncidentsConsult response = incidentsManager.insertTicket(request.getOrg(),request.getApplicant(),request.getCategory(),request.getDescription(),request.getRegion(), request.getInsertar());
		return  new ResponseEntity<IncidentsConsult> (response,HttpStatus.OK);
	}
	
	
//	ok
	//es la parte de buscar solicitantes, me entrega puntas.
	// getPointByOrg viene de la clase IncidentsManager, toma como parametros el cuerpo de la solicitud que viene del fornt end
	// y llama a la clase Tenant para verificar las variables pasadas por el cuerpo, devuelve un objeto de Tipo ResponseEntity<PointList>
	// Tenant, es el tipo de empresa
	@RequestMapping(value="/point", method = RequestMethod.POST)
	@ApiOperation (value = "Obtiene todas las puntas por organizacion", notes = "Obtiene todas las puntas por organizacion")
	public ResponseEntity<PointListCMDB> getPointByOrg(@RequestBody Tenant request) {
		System.out.println("request-------------------------------------------------------------->"+ request.getOrganization());
		System.out.println("request-------------------------------------------------------------->"+ request.getTenant());
		System.out.println("request-------------------------------------------------------------->"+ request.getType());
		System.out.println("request-------------------------------------------------------------->"+ request.getClass());
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		log.info("User: " + auth.getName() + ", Method:getPointByOrg" + ", Role: " + auth.getAuthorities());
		PointListCMDB response = incidentsManager.getPointByOrg(request.getOrganization(), request.getTenant());
		return  new ResponseEntity<PointListCMDB> (response,HttpStatus.OK);
	}
	
//	ok
	//Sección que devuelve todas las categorias
	//http://10.180.251.111:8080/portaltpe/incident/category?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	@RequestMapping(value="/category", method = RequestMethod.POST)
	@ApiOperation (value = "Obtiene todas las categorias", notes = "Obtiene todas las categorias")
	public ResponseEntity<CategoriesConsult> getCategory(@RequestBody Tenant req) {
		System.out.println("VALOR DEL TENANT" + req.getType());
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		log.info("User: " + auth.getName() + ", Method:getCategory" + ", Role: " + auth.getAuthorities());
		CategoriesConsult response = incidentsManager.getCategory(req.getType());
		return  new ResponseEntity<CategoriesConsult> (response,HttpStatus.OK);
	}
	
	
		//ok
	    // Buscar Todos los Tickest No Conciliados
	    //http://10.180.251.111:8080/portaltpe/incident/noconcilied?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	    @RequestMapping(value = "/notconcilied", method = RequestMethod.POST)
		@ApiOperation(value = "Busca Tickets No Conciliados", notes = "Busca Tickets No Conciliados")
		public ResponseEntity<TicketConciliedList> findAllNotConcilied(@RequestBody Organization request) {
	    	System.out.println("findAllNotConcilied"+ request.getOrganization());
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//			log.info("User: " + auth.getName() + ", Method: findAllNotConcilied" + ", Role: " + auth.getAuthorities());
			TicketConciliedList responseManager = incidentsManager.returnAllNotConcilied(request.getOrganization());
			return new ResponseEntity<TicketConciliedList>(responseManager, HttpStatus.OK);
		}
	    //ok
	 // Buscar Todos los Tickest No Conciliados con status Validacion
	    //http://10.180.251.111:8080/portaltpe/incident/noconcilied?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	    @RequestMapping(value = "/notconciliedvalidation", method = RequestMethod.POST)
		@ApiOperation(value = "Busca Tickets No Conciliados con status validacion", notes = "Busca Tickets No Conciliados con estatus validacion")
		public ResponseEntity<TicketConciliedList> findAllNotConciliedValidation(@RequestBody Organization request) {
	    	System.out.println("ORGANIZACION" + request.getOrganization());
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//			log.info("User: " + auth.getName() + ", Method: findAllNotConciliedValidation" + ", Role: " + auth.getAuthorities());
			TicketConciliedList responseManager = incidentsManager.returnAllNotConciliedValidation(request.getOrganization());
			return new ResponseEntity<TicketConciliedList>(responseManager, HttpStatus.OK);
		}
	
	 // Actualiza los datos del Ticket No Conciliado
	    //http://10.180.251.111:8080/portaltpe/incident/updateticketnotconcilied?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	    @RequestMapping(value = "/updateticketnotconcilied", method = RequestMethod.POST)
		@ApiOperation(value = "Busca Tickets No Conciliados", notes = "Busca Tickets No Conciliados")
		public ResponseEntity<TicketConciliedList> updateTicketNotConcilied(@Valid @RequestBody TicketNotConciliedValidationUpdate request) {
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//			log.info("User: " + auth.getName() + ", Method: updateTicketNotConcilied" + ", Role: " + auth.getAuthorities());
			TicketConciliedList responseManager = incidentsManager.updateTicketNotConcilied(request);
			return new ResponseEntity<TicketConciliedList>(responseManager, HttpStatus.OK);
		}
	    //ok
	    // Actualiza los datos del Ticket a PorValidar
	    //http://10.180.251.111:8080/portaltpe/incident/updateTicketPorValidar?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	    @RequestMapping(value = "/updateTicketPorValidar", method = RequestMethod.POST)
		@ApiOperation(value = "Actualiza los datos del Ticket a PorValidar", notes = "Actualiza los datos del Ticket a PorValidar")
	    public ResponseEntity<TicketConciliedList> updateTicketPorValidar(@Valid @RequestBody TicketNotConciliedValidationUpdate request) {
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//			log.info("User: " + auth.getName() + ", Method: updateTicketPorValidar" + ", Role: " + auth.getAuthorities());
			TicketConciliedList responseManager = incidentsManager.updateTicketPorValidar(request);
			return new ResponseEntity<TicketConciliedList>(responseManager, HttpStatus.OK);
		}
	    
	    
	    //ok
	 // Actualiza Status Ticket Cerrados a Conciliado
	    //http://10.180.251.111:8080/portaltpe/incident/updatestatusticketnotconcilied?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	    @RequestMapping(value = "/updatestatusticketnotconcilied", method = RequestMethod.POST)
		@ApiOperation(value = "Busca Tickets No Conciliados", notes = "Busca Tickets No Conciliados")
		public ResponseEntity<TicketConciliedList> updateStatusTicketNotConcilied(@Valid @RequestBody TicketNotConciliedValidationUpdate request) {
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//			log.info("User: " + auth.getName() + ", Method: updateStatusTicketNotConcilied" + ", Role: " + auth.getAuthorities());
			TicketConciliedList responseManager = incidentsManager.updateStatusTicketNotConcilied(request);
			return new ResponseEntity<TicketConciliedList>(responseManager, HttpStatus.OK);
		}
	    //ok
	 // Actualiza Status Ticket Cerrados a Validados
	    //http://10.180.251.111:8080/portaltpe/incident/updatestatusticketnotconcilied?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	    @RequestMapping(value = "/updatestatusticketnotconciliedvalidation", method = RequestMethod.POST)
		@ApiOperation(value = "Busca Tickets No Conciliados", notes = "Busca Tickets No Conciliados")
		public ResponseEntity<TicketConciliedList> updateStatusTicketNotConciliedValidation(@Valid @RequestBody TicketNotConciliedValidationUpdate request) {
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//			log.info("User: " + auth.getName() + ", Method: updateStatusTicketNotConciliedValidation" + ", Role: " + auth.getAuthorities());
			TicketConciliedList responseManager = incidentsManager.updateStatusTicketNotConciliedValidation(request);
			return new ResponseEntity<TicketConciliedList>(responseManager, HttpStatus.OK);
		}
	    
	    //ok
	 // Buscar Todos los Tickest Cerrados Conciliados
	    //http://10.180.251.111:8080/portaltpe/incident/noconcilied?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	    @RequestMapping(value = "/closed", method = RequestMethod.POST)
		@ApiOperation(value = "Buscar Todos los Tickest Cerrados Conciliados", notes = "Buscar Todos los Tickest Cerrados Conciliados")
		public ResponseEntity<TicketClosedList> getAllClosedTickets(@RequestBody Organization request) {
//			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//			log.info("User: " + auth.getName() + ", Method: getAllClosedTickets" + ", Role: " + auth.getAuthorities());
			TicketClosedList responseManager = incidentsManager.returnAllClosed(request.getOrganization());
			return new ResponseEntity<TicketClosedList>(responseManager, HttpStatus.OK);
		}
	    
	    //ok
	    @RequestMapping(value="/addComentario", method = RequestMethod.POST)
		@ApiOperation (value = "INSERTA", notes = "Obtiene todos los comentarios al ticket")
		public ResponseEntity<AddComment> getAddComentarios(@RequestBody AddComentario request) {
			System.out.println("== ENTRE A INSERTAR COMENTARIO DEL TICKET === " + request.getRefNum());
			AddComment response = incidentsManager.getAddComentarios(request);
			return  new ResponseEntity<AddComment> (response,HttpStatus.OK);
		}

		@RequestMapping(value="/comentario", method = RequestMethod.POST)
		@ApiOperation (value = "Obtiene todos los comentarios", notes = "Obtiene todos los comentarios al ticket")
		public ResponseEntity<CommentList> getAllComentarios(@RequestBody Comentario request) {
			System.out.println("== ENTRE A OBTENER COMENTARIOS === ");
			CommentList response2 = incidentsManager.getAllComentarios(request);
			return  new ResponseEntity<CommentList> (response2,HttpStatus.OK);
		}
//	    ok
		@RequestMapping(value="/updateStatus", method = RequestMethod.POST)
		@ApiOperation (value = "Cambia el Status de Un ticket", notes = "Cambia el Status de un ticket")
		public ResponseEntity<UpdateStatus> getUpdateStatus(@RequestBody UpdateStatus request) {
			System.out.println("== STATUS - ENTRE A CAMBIAR STATUS === " + request.getRefNum());
			UpdateStatus response = incidentsManager.getUpdateStatus(request);
			return  new ResponseEntity<UpdateStatus> (response,HttpStatus.OK);
		}
		
}
