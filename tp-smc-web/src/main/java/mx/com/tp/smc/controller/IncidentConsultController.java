
package mx.com.tp.smc.controller;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import mx.com.tp.smc.entity.CatMenu;
import mx.com.tp.smc.entity.Menus;
import mx.com.tp.smc.mgr.ConsultMgr;
import mx.com.tp.smc.mgr.HomeMgr;
//import mx.com.tp.smc.mgr.MenuMgr;
import mx.com.tp.smc.mgr.RolMgr;
import mx.com.tp.smc.mgr.UserMgr;
import mx.com.tp.smc.model.CatMenuModel;
import mx.com.tp.smc.model.MenusModel;
import mx.com.tp.smc.model.RoleModel;
import mx.com.tp.smc.model.TicketCloseModel;
import mx.com.tp.smc.model.UserModel;
import mx.com.tp.smc.util.TemplateBuilder;
import mx.com.tp.smc.util.UserHolder;

@Controller
@RequestMapping(value = "/home")
public class IncidentConsultController {

	final static Logger log = Logger.getLogger(HomeController.class);

	@Autowired
	private ConsultMgr mgr2;

	@Autowired
	private HomeMgr mgr;

	@Autowired
	private UserMgr usermgr;

	@Autowired
	private RolMgr rolMgr;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	public String homeAdminPage() {
		return "redirect:home";
	}

	@RequestMapping(value = "/incidents/ticketsCerradosMensuales", method = RequestMethod.GET)
	public String getAllClosedTickets(ModelMap model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String organization = mgr.getOrganizationByUserID(auth.getName());
	
		TicketCloseModel ticket = mgr2.getAllClosedTickets(organization);
		
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Tickets Mensuales Cerrados");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/incidents", "Incidentes"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Tickets Mensuales Cerrados"));
		model.addAttribute("model", ticket);

		/**/

		UserModel userModel = usermgr.getRolByUser(auth.getName());

		String rolRole = userModel.getTickets().get(0).getRole();

		RoleModel rolModel = rolMgr.getIdRol(rolRole);

		Long idRol = rolModel.getRoles().get(0).getIdRole();

		MenusModel menus = rolMgr.getMenusByRol(idRol);

		ArrayList<Menus> menusArray = new ArrayList<Menus>();

		CatMenuModel submenusModel = new CatMenuModel();

		menusArray = menus.getMenus();

		ArrayList<CatMenu> submenus = new ArrayList<CatMenu>();

		for (int i = 0; i < menusArray.size(); i++) {

			Menus objMenu = (Menus) menusArray.get(i);

			CatMenu submenu = new CatMenu();

			submenu.setIdSubMenu(objMenu.getIdSubMenu());
			submenu.setTituloPrincipal(objMenu.getTituloPrincipal());
			//submenu.setNombreMenu(objMenu.getNombreMenu());
			submenu.setPath(objMenu.getPath());
			submenu.setDescripcionMenu(objMenu.getDescripcionMenu());
			submenu.setIcono(objMenu.getIcono());

			submenus.add(submenu);
		}
		submenusModel.setMenus(submenus);

		menus.setUsername(UserHolder.getUsername());
		menus.setPagename("");
		menus.setCategory(TemplateBuilder.buildCategory("#", ""));
		model.addAttribute("model3", menus);

		model.addAttribute("model4", submenusModel);
		/**/

		return "ticketsCerradosMensuales";
	}

}