package mx.com.tp.smc.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mx.com.tp.smc.mgr.RolMgr;
import mx.com.tp.smc.model.RoleModel;
import mx.com.tp.smc.util.TemplateBuilder;
import mx.com.tp.smc.util.UserHolder;

@Controller
@RequestMapping(value = "/roles")
public class RolesController {
	final static Logger log = Logger.getLogger(RolesController.class);

	@Autowired
	private RolMgr rolMgr;
	
	//Carga de roles creados 
	@RequestMapping(value = "/administrarUsuarios/roles", method = RequestMethod.GET) 
	public String registroRoles(ModelMap model) {
		
		RoleModel roles = rolMgr.getRolesSMDB();
		roles.setUsername(UserHolder.getUsername());
		roles.setPagename("Administrar Usuarios");
		roles.setCategory(TemplateBuilder.buildCategory("/portaltpe/roles/manageUsers", "Administrar Usuarios"));
		roles.setSubCategory(TemplateBuilder.buildCategory("#", "Registro de Roles"));
		return "roles";
	}
}
