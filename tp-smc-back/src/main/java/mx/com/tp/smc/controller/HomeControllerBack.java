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
import org.springframework.web.bind.annotation.RestController;
import mx.com.tp.smc.manager.HomeManager;
import mx.com.tp.smc.request.Organization;
import mx.com.tp.smc.response.DataGraphic;
//import mx.com.tp.smc.restRequest.Regiones;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/home")
@Api(value = "home")
public class HomeControllerBack {

	final static Logger log = Logger.getLogger(HomeControllerBack.class);

	@Autowired
	private HomeManager managerHome;

	//ok
	@RequestMapping(value = "/ticket", method = RequestMethod.POST)
	@ApiOperation(value = "Grafica de tickets", notes = "Muestra grafica inicial de incidentes")
	public ResponseEntity<DataGraphic> paintGraph(@Valid @RequestBody Organization request) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		log.info("User: " + auth.getName() + ", Method: paintGraph" + ", Role: " + auth.getAuthorities());
		DataGraphic responseManager = managerHome.getGraphicTicket(request.getOrganization());
		return new ResponseEntity<DataGraphic>(responseManager, HttpStatus.OK);
	}
	
	

}
