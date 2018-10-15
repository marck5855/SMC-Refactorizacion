package mx.com.tp.smc.controller;

import java.io.File;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mx.com.tp.smc.manager.UserManager;
import mx.com.tp.smc.request.OrganizationRequest;
import mx.com.tp.smc.request.RolForOrganization;
import mx.com.tp.smc.request.UserForOrganization;
import mx.com.tp.smc.request.UserLogin;
import mx.com.tp.smc.request.UserRequest;
import mx.com.tp.smc.request.UserRoleUpdate;
import mx.com.tp.smc.request.UserValidation;
import mx.com.tp.smc.request.UserValidationAdd;
import mx.com.tp.smc.request.UserValidationUpdate;
import mx.com.tp.smc.response.AccessUser;
import mx.com.tp.smc.response.OrganizationList;
import mx.com.tp.smc.response.ResponseUser;
import mx.com.tp.smc.response.RoleList;
import mx.com.tp.smc.response.UsersList;

@RestController
@RequestMapping(value = "/user")
@Api(value = "user")
public class UserController {

	final static Logger log = Logger.getLogger(UserController.class);

	@Autowired
	private UserManager userManager;

	// http://10.180.251.111:8080/portaltpe/user/role?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	@RequestMapping(value = "/role", method = RequestMethod.POST)
	@ApiOperation(value = "Muestra los permisos y rol del usuario logeado", notes = "Muestra los permisos y rol del usuario logeado")
	public ResponseEntity<AccessUser> getInfUsr() {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		log.info("User: " + auth.getName() + ", Method: getInfUsr" + ", Role: " + auth.getAuthorities());
		// AccessUser responseManager =
		// userManager.getAccessUrlByUser(auth.getAuthorities().toString());
		// return new ResponseEntity<AccessUser>(responseManager,
		// HttpStatus.OK);
		return null;
	}
//  ok
	// Agregar al usuario
	// http://10.180.251.111:8080/portaltpe/user/add?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ApiOperation(value = "Agrega Usuarios a la Base de Datos", notes = "Agrega Usuarios a la Base de Datos")
	public ResponseEntity<ResponseUser> addUser(@Valid @RequestBody UserValidationAdd request) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		log.info("User: " + auth.getName() + ", Method: addUser" + ", Role: " + auth.getAuthorities()+", createuser: ");
		ResponseUser responseManager = userManager.insertUser(request);
		return new ResponseEntity<ResponseUser>(responseManager, HttpStatus.OK);
	}

	// Eliminar Usuario
	// http://10.180.251.111:8080/portaltpe/user/delete?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ApiOperation(value = "Elimina Usuarios a la Base de Datos", notes = "Elimina Usuarios a la Base de Datos")
	public ResponseEntity<ResponseUser> delUser(@Valid @RequestBody UserValidation request) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		log.info("User: " + auth.getName() + ", Method: delUser" + ", Role: " + auth.getAuthorities());
		ResponseUser responseManager = userManager.deleteUser(request);
		return new ResponseEntity<ResponseUser>(responseManager, HttpStatus.OK);

	}

	// Buscar Usuario
	// http://10.180.251.111:8080/portaltpe/user/find?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	@ApiOperation(value = "Busca un usuario por username", notes = "Busca un usuario por username")
	public ResponseEntity<ResponseUser> findUser(@Valid @RequestBody UserValidationUpdate request) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		log.info("User: " + auth.getName() + ", Method: findUser" + ", Role: " + auth.getAuthorities());
		ResponseUser responseManager = userManager.returnUser(request);
		return new ResponseEntity<ResponseUser>(responseManager, HttpStatus.OK);

	}

	// Actualiza Usuario
	// http://10.180.251.111:8080/portaltpe/user/update?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ApiOperation(value = "Actualiza Usuarios a la Base de Datos", notes = "Actualiza Usuarios a la Base de Datos")
	public ResponseEntity<ResponseUser> updateUser(@Valid @RequestBody UserValidationUpdate request) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		log.info("User: " + auth.getName() + ", Method: updateUser" + ", Role: " + auth.getAuthorities());
		ResponseUser responseManager = userManager.updateUser(request);
		return new ResponseEntity<ResponseUser>(responseManager, HttpStatus.OK);

	}
//    ok
	// Buscar Todos los Usuario
	// http://10.180.251.111:8080/portaltpe/user/findall?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	@RequestMapping(value = "/findall", method = RequestMethod.POST)
	@ApiOperation(value = "Busca Usuarios a la Base de Datos", notes = "Busca Usuarios a la Base de Datos")
	public ResponseEntity<UsersList> findAllUser(@Valid @RequestBody UserRequest request) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		log.info("User: " + auth.getName() + ", Method: findAllUserFilter" + ", Role: " + auth.getAuthorities());
		UsersList responseManager = userManager.returnAllUsers(request);
		return new ResponseEntity<UsersList>(responseManager, HttpStatus.OK);
	}

//	// Controla el Login del Usuario
//	// http://10.180.251.111:8080/portaltpe/user/loginusuer?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
//	@RequestMapping(value = "/loginuser", method = RequestMethod.POST)
//	@ApiOperation(value = "Busca Usuarios a la Base de Datos", notes = "Busca Usuarios a la Base de Datos")
//	public ResponseEntity<ResponseUser> loginUser(@Valid @RequestBody UserLogin request) {
////		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////		log.info("User: " + auth.getName() + ", Method: loginUser" + ", Role: " + auth.getAuthorities());
//		ResponseUser responseManager = userManager.loginUser(request);
//		return new ResponseEntity<ResponseUser>(responseManager, HttpStatus.OK);
//	}
//  ok
	// Retorna los roles por Organizacion
	// http://10.180.251.111:8080/portaltpe/user/allroles?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	@RequestMapping(value = "/rolesByOrganization", method = RequestMethod.POST)
	@ApiOperation(value = "Retorna los roles por Organizacion", notes = "Retorna los roles por Organizacion")
	public ResponseEntity<RoleList> getRolesByOrganization(@Valid @RequestBody RolForOrganization request) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		log.info("User: " + auth.getName() + ", Method: getAllOrganization" + ", Role: " + auth.getAuthorities());
		RoleList responseManager = userManager.getRolesByOrganization(request);
		return new ResponseEntity<RoleList>(responseManager, HttpStatus.OK);
	}

	// Retorna todas las organizaciones
	// http://10.180.251.111:8080/portaltpe/user/allorganization?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	@RequestMapping(value = "/allorganization", method = RequestMethod.POST)
	@ApiOperation(value = "Retorna todas las organizaciones", notes = "Retorna todas las organizaciones")
	public ResponseEntity<OrganizationList> getAllOrganization(@Valid @RequestBody OrganizationRequest request) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		log.info("User: " + auth.getName() + ", Method: getAllOrganization" + ", Role: " + auth.getAuthorities());
		OrganizationList responseManager = userManager.returnOrganization(request);
		return new ResponseEntity<OrganizationList>(responseManager, HttpStatus.OK);
	}
//  ok
	// Actualiza Usuario
	// http://10.180.251.111:8080/portaltpe/user/updateroleuser?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	@RequestMapping(value = "/updateroleuser", method = RequestMethod.POST)
	@ApiOperation(value = "Actualiza Usuarios a la Base de Datos", notes = "Actualiza Usuarios a la Base de Datos")
	public ResponseEntity<ResponseUser> updateRoleUser(@Valid @RequestBody UserRoleUpdate request) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		log.info("User: " + auth.getName() + ", Method: updateRoleUser" + ", Role: " + auth.getAuthorities());
		ResponseUser responseManager = userManager.updateRoleUser(request);
		return new ResponseEntity<ResponseUser>(responseManager, HttpStatus.OK);

	}

	//ok
	// Retorna todas la organizacion de un usuario
	// http://10.180.251.111:8080/portaltpe/user/organizationbyuser?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	@RequestMapping(value = "/organizationbyuser", method = RequestMethod.POST)
	@ApiOperation(value = "Retorna todas las organizaciones", notes = "Retorna todas las organizaciones")
	public ResponseEntity<OrganizationList> getOrganizationByUser(@Valid @RequestBody UserForOrganization request) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		log.info("User: " + auth.getName() + ", Method: getOrganizationByUser" + ", Role: " + auth.getAuthorities());
		OrganizationList responseManager = userManager.returnOrganizationByUser(request);
		return new ResponseEntity<OrganizationList>(responseManager, HttpStatus.OK);
	}
//	ok
	// Cargar PDF
	// http://10.180.251.111:8080/restportal/user/saveFile?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	@RequestMapping(value = "/saveFile", method = RequestMethod.POST)
	@ApiOperation(value = "Carga de PDF a la base de datos", notes = "Carga de PDF a la base de datos")
	public ResponseEntity<ResponseUser> saveFile(@Valid @RequestBody File request) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		log.info("User: " + auth.getName() + ", Method: saveFile" + ", Role: " + auth.getAuthorities());
//		 fileManager.saveFile(request);
		return null;
	}
	//ok
	// Retorna el rol de un usuario
	// http://10.180.251.111:8080/portaltpe/user/delete?access_token=a5d9131b-4afd-4ff4-9cc9-8c891f4a30b8
	@RequestMapping(value = "/rolbyuser", method = RequestMethod.POST)
	@ApiOperation(value = "Retorna el rol de un usuario", notes = "Retorna el rol de un usuario")
	public ResponseEntity<ResponseUser> getRolByUser(@Valid @RequestBody UserValidation request) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		log.info("User: " + auth.getName() + ", Method: delUser" + ", Role: " + auth.getAuthorities());
		ResponseUser responseManager = userManager.getRolByUser(request);
		return new ResponseEntity<ResponseUser>(responseManager, HttpStatus.OK);

	}
}