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

import mx.com.tp.smc.manager.RolManager;
import mx.com.tp.smc.request.Cmenu;
import mx.com.tp.smc.request.MenuRolAdd;
import mx.com.tp.smc.request.RolAdd;
import mx.com.tp.smc.request.RolRequest;
import mx.com.tp.smc.request.RolValidation;
import mx.com.tp.smc.request.TbMenuRequest;
import mx.com.tp.smc.response.CatMenuList;
import mx.com.tp.smc.response.CatOrganizacionList;
import mx.com.tp.smc.response.MenusList;
import mx.com.tp.smc.response.OrigenHomeList;
import mx.com.tp.smc.response.ResponseMenuRol;
import mx.com.tp.smc.response.ResponseRol;
import mx.com.tp.smc.response.ResponseUser;
import mx.com.tp.smc.response.RoleList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/roles")
@Api(value = "roles")
public class RolController {

	final static Logger log = Logger.getLogger(RolController.class);

	@Autowired
	private RolManager rolManager;

//	ok
	// Agregar Rol
	// http://10.180.251.111:8080/portaltpe/roles/addRol?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	@RequestMapping(value = "/addRol", method = RequestMethod.POST)
	@ApiOperation(value = "Agrega Roles a la Base de Datos", notes = "Agrega Roles a la Base de Datos")
	public ResponseEntity<ResponseUser> addRol(@Valid @RequestBody RolAdd request) {
//		 Authentication auth =
//		 SecurityContextHolder.getContext().getAuthentication();
//		 log.info("User: " + auth.getName() + ", Method: addRol" + ", Role: "
//		 + auth.getAuthorities());
		ResponseUser responseManager = rolManager.insertRol(request);
		return new ResponseEntity<ResponseUser>(responseManager, HttpStatus.OK);
	}
//	ok
	// Buscar Todos los Roles
	// http://10.180.251.111:8080/portaltpe/roles/findall?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	@RequestMapping(value = "/findall", method = RequestMethod.POST)
	@ApiOperation(value = "Busca Roles a la Base de Datos", notes = "Busca Roles a la Base de Datos")
	public ResponseEntity<RoleList> findAllRol() {
//		 Authentication auth =
//		 SecurityContextHolder.getContext().getAuthentication();
//		 log.info("User: " + auth.getName() + ", Method: findAllRol" + ", Role: " + auth.getAuthorities());
		RoleList responseManager = rolManager.returnAllRoles();
		return new ResponseEntity<RoleList>(responseManager, HttpStatus.OK);
	}

	// Retorna todas las organizaciones
	// http://10.180.251.111:8080/portaltpe/roles/allorganization?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	@RequestMapping(value = "/allorganization", method = RequestMethod.POST)
	@ApiOperation(value = "Retorna todas las organizaciones", notes = "Retorna todas las organizaciones")
	public ResponseEntity<CatOrganizacionList> getAllOrganization() {
//		 Authentication auth =
//		 SecurityContextHolder.getContext().getAuthentication();
//		 log.info("User: " + auth.getName() + ", Method: getAllOrganization" +
//		 ", Role: " + auth.getAuthorities());
		CatOrganizacionList responseManager = rolManager.returnOrganization();
		return new ResponseEntity<CatOrganizacionList>(responseManager, HttpStatus.OK);
	}

	// Retorna los Menus
	// http://10.180.251.111:8080/portaltpe/roles/allmenus?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	@RequestMapping(value = "/allmenus", method = RequestMethod.POST)
	@ApiOperation(value = "Retorna los menus", notes = "Retorna los menus")
	public ResponseEntity<CatMenuList> getPrincipalMenus() {
//		 Authentication auth =
//		 SecurityContextHolder.getContext().getAuthentication();
//		 log.info("User: " + auth.getName() + ", Method: getAllOrganization" +
//		 ", Role: " + auth.getAuthorities());
		CatMenuList responseManager = rolManager.returnPrincipalMenus();
		return new ResponseEntity<CatMenuList>(responseManager, HttpStatus.OK);
	}
	//ok
	// Retorna los SubMenus
	// http://10.180.251.111:8080/portaltpe/roles/allmenus?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	@RequestMapping(value = "/submenu", method = RequestMethod.POST)
	@ApiOperation(value = "Retorna los SubMenus", notes = "Retorna los SubMenus")
	public ResponseEntity<CatMenuList> getSubMenu(@Valid @RequestBody Cmenu request) {
//		 Authentication auth =
//		 SecurityContextHolder.getContext().getAuthentication();
//		 log.info("User: " + auth.getName() + ", Method: getAllOrganization" +
//		 ", Role: " + auth.getAuthorities());
		CatMenuList responseManager = rolManager.returnSubMenu(request);
		return new ResponseEntity<CatMenuList>(responseManager, HttpStatus.OK);
	}
//  ok
	// Eliminar Rol
	// http://10.180.251.111:8080/portaltpe/roles/delete?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ApiOperation(value = "Elimina Rol a la Base de Datos", notes = "Elimina Rol a la Base de Datos")
	public ResponseEntity<ResponseRol> delRol(@Valid @RequestBody RolValidation request) {
//		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		 log.info("User: " + auth.getName() + ", Method: delUser" + ", Role: " + auth.getAuthorities());
		ResponseRol responseManager = rolManager.deleteRol(request);
		return new ResponseEntity<ResponseRol>(responseManager, HttpStatus.OK);

	}
    //ok
	// Retorna Rol
	// http://10.180.251.111:8080/portaltpe/roles/findIdrol?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	@RequestMapping(value = "/findIdrol", method = RequestMethod.POST)
	@ApiOperation(value = "Retorna el id Rol", notes = "Retorna el id Rol")
	public ResponseEntity<ResponseRol> getIdRol(@Valid @RequestBody RolRequest request) {
//		 Authentication auth =
//		 SecurityContextHolder.getContext().getAuthentication();
//		 log.info("User: " + auth.getName() + ", Method: delUser" + ", Role: "
//		 + auth.getAuthorities());
		ResponseRol responseManager = rolManager.getIdRol(request);
		return new ResponseEntity<ResponseRol>(responseManager, HttpStatus.OK);

	}
	//ok
	// Retorna Menus por Rol
	// http://10.180.251.111:8080/portaltpe/roles/findMenus?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	@RequestMapping(value = "/findMenus", method = RequestMethod.POST)
	@ApiOperation(value = "Retorna Menus por Rol", notes = "Retorna Menus por Rol")
	public ResponseEntity<MenusList> getMenusByRol(@Valid @RequestBody TbMenuRequest request) {
//		 Authentication auth =
//		 SecurityContextHolder.getContext().getAuthentication();
//		 log.info("User: " + auth.getName() + ", Method: delUser" + ", Role: "
//		 + auth.getAuthorities());
		MenusList responseManager = rolManager.returnMenusByRol(request);
		return new ResponseEntity<MenusList>(responseManager, HttpStatus.OK);

	}

	// Agrega los permisos de menu a un rol
	// http://10.180.251.111:8080/portaltpe/roles/addRol?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	@RequestMapping(value = "/addPermission", method = RequestMethod.POST)
	@ApiOperation(value = "Agrega los permisos de menu a un rol", notes = "Agrega los permisos de menu a un rol")
	public ResponseEntity<ResponseMenuRol> addPermission(@Valid @RequestBody MenuRolAdd request) {
//		 Authentication auth =
//		 SecurityContextHolder.getContext().getAuthentication();
//		 log.info("User: " + auth.getName() + ", Method: addRol" + ", Role: "
//		 + auth.getAuthorities());
		ResponseMenuRol responseManager = rolManager.addPermission(request);
		return new ResponseEntity<ResponseMenuRol>(responseManager, HttpStatus.OK);
	}
	//ok   
	// Buscar Origen para Homes
	// http://10.180.251.111:8080/portaltpe/roles/findall?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	@RequestMapping(value = "/findOrigen", method = RequestMethod.POST)
	@ApiOperation(value = "Buscar Origen para Homes", notes = "Buscar Origen para Homes")
	public ResponseEntity<OrigenHomeList> findOrigenHome() {
//		 Authentication auth =
//		 SecurityContextHolder.getContext().getAuthentication();
//		 log.info("User: " + auth.getName() + ", Method: findAllRol" + ", Role: " + auth.getAuthorities());
		OrigenHomeList responseManager = rolManager.returnAllOrigenHomes();
		return new ResponseEntity<OrigenHomeList>(responseManager, HttpStatus.OK);
	}

}
