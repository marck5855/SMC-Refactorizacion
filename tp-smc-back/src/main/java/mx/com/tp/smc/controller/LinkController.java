
package mx.com.tp.smc.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.tp.smc.manager.LinkManager;
import mx.com.tp.smc.request.LinkAdd;
import mx.com.tp.smc.request.RolValidation;
import mx.com.tp.smc.response.LinkList;
import mx.com.tp.smc.response.ResponseLink;
import mx.com.tp.smc.response.ResponseRol;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/links")
@Api(value = "links")
public class LinkController {

	final static Logger log = Logger.getLogger(RolController.class);

	@Autowired
	private LinkManager linkManager;

	// Buscar Todos los Links
	// http://10.180.251.111:8080/portaltpe/roles/findall?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	@RequestMapping(value = "/findall", method = RequestMethod.POST)
	@ApiOperation(value = "Busca Links a la Base de Datos", notes = "Busca Links a la Base de Datos")
	public ResponseEntity<LinkList> findAllLink() {
		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		// log.info("User: " + auth.getName() + ", Method: findAllRol" + ",
		// Role: " + auth.getAuthorities());
		LinkList responseManager = linkManager.returnAllLinks();
		return new ResponseEntity<LinkList>(responseManager, HttpStatus.OK);
	}
	
	// Agregar Link
	// http://10.180.251.111:8080/portaltpe/roles/addRol?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	@RequestMapping(value = "/addLink", method = RequestMethod.POST)
	@ApiOperation(value = "Agrega Links a la Base de Datos", notes = "Agrega Links a la Base de Datos")
	public ResponseEntity<ResponseLink> addLink(@Valid @RequestBody LinkAdd request) {
		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		// log.info("User: " + auth.getName() + ", Method: addRol" + ", Role: "
		// + auth.getAuthorities());
		ResponseLink responseManager = linkManager.insertLink(request);
		return new ResponseEntity<ResponseLink>(responseManager, HttpStatus.OK);
	}

	// Eliminar Rol
	// http://10.180.251.111:8080/portaltpe/roles/delete?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ApiOperation(value = "Elimina Rol a la Base de Datos", notes = "Elimina Rol a la Base de Datos")
	public ResponseEntity<ResponseRol> delRol(@Valid @RequestBody RolValidation request) {
		// Authentication auth =
		// SecurityContextHolder.getContext().getAuthentication();
		// log.info("User: " + auth.getName() + ", Method: delUser" + ", Role: "
		// + auth.getAuthorities());
		ResponseRol responseManager = null;// rolManager.deleteRol(request);
		return new ResponseEntity<ResponseRol>(responseManager, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/deleteLink/", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseLink> deleteLink(@RequestParam("idLink") Long idLink) {
		try {
			ResponseLink resp = linkManager.deleteLink(idLink);
			System.out.println(resp.toString());
			return new ResponseEntity<ResponseLink>(resp, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Excepcion >> " + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<ResponseLink>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/findByOrganization/", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<LinkList> findByOrganization(@RequestParam("organization") String organization) {
		LinkList responseManager = linkManager.returnOrganizacionLinks(organization);
		return new ResponseEntity<LinkList>(responseManager, HttpStatus.OK);
	}
}
