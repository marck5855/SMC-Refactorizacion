package mx.com.tp.smc.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import mx.com.tp.smc.manager.CatalogosManager;
import mx.com.tp.smc.manager.IncidentsManager;
import mx.com.tp.smc.response.CatRegionesList;
import mx.com.tp.smc.response.StatusList;
//import mx.com.tp.smc.restRequest.Comentario;
//import mx.com.tp.smc.restRequest.Organization;
//import mx.com.tp.smc.restRequest.Regiones;
//import mx.com.tp.smc.restRequest.Tenant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/catalogos")
@Api(value="catalogos")
public class CatalogosController {

	final static Logger log = Logger.getLogger(CatalogosController.class);
	
	@Autowired
	private CatalogosManager catalogosManager;
	
	@Autowired
	private IncidentsManager incidentsManager;
	
	@RequestMapping(value="/status", method = RequestMethod.POST)
	@ApiOperation (value = "Obtiene todos los stauts para un ticket", notes = "Obtiene todos los status para un ticket")
	public ResponseEntity<StatusList> getAllStatus() {
		StatusList response2 = catalogosManager.getAllStatus();
		return  new ResponseEntity<StatusList> (response2,HttpStatus.OK);
	}
	
//	ok
	@RequestMapping(value = "/regiones", method = RequestMethod.POST)
	@ApiOperation (value = "Obtiene el catalogo de regiones", notes = "Obtiene el catalogo de regiones")
	public ResponseEntity<CatRegionesList> getcatRegiones() {
		System.out.println("== ENTRE A  REGIONES === ");
		CatRegionesList res = incidentsManager.getCatRegiones();
		return  new ResponseEntity<CatRegionesList> (res,HttpStatus.OK);
	}
	
}
