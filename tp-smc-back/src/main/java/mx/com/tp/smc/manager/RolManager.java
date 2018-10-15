package mx.com.tp.smc.manager;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.json.simple.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import mx.com.tp.smc.entity.CMenusEntity;
import mx.com.tp.smc.entity.CatOrigenHomeEntity;
import mx.com.tp.smc.entity.CorganizacionEntity;
import mx.com.tp.smc.entity.TbMenuRolEntity;
import mx.com.tp.smc.entity.ToperatorRolEntity;
import mx.com.tp.smc.entity.TrolEntity;
import mx.com.tp.smc.request.Cmenu;
import mx.com.tp.smc.request.MenuRolAdd;
import mx.com.tp.smc.request.RolAdd;
import mx.com.tp.smc.request.RolRequest;
import mx.com.tp.smc.request.RolValidation;
import mx.com.tp.smc.request.TbMenuRequest;
import mx.com.tp.smc.response.CatMenu;
import mx.com.tp.smc.response.CatMenuList;
import mx.com.tp.smc.response.CatOrganizacion;
import mx.com.tp.smc.response.CatOrganizacionList;
import mx.com.tp.smc.response.CatOrigenHome;
import mx.com.tp.smc.response.Menus;
import mx.com.tp.smc.response.MenusList;
import mx.com.tp.smc.response.OrigenHomeList;
import mx.com.tp.smc.response.ResponseMenuRol;
import mx.com.tp.smc.response.ResponseRol;
import mx.com.tp.smc.response.ResponseUser;
import mx.com.tp.smc.response.Role;
import mx.com.tp.smc.response.RoleList;
import mx.com.tp.smc.service.OrigenHomeServiceBack;
import mx.com.tp.smc.service.RolServiceBack;
import mx.com.tp.smc.service.TbMenuRolService;
import mx.com.tp.smc.service.ToperatorRolService;

@Component
public class RolManager {

	final static Logger log = Logger.getLogger(RolManager.class);

	@Autowired
	private RolServiceBack rolServiceBackImpl;

	@Autowired
	private ToperatorRolService toperatorRolService;

	@Autowired
	private TbMenuRolService tbMenuRolService;

	@Autowired
	private OrigenHomeServiceBack origenHomeService;

//	Marco
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	public ResponseUser insertRol(RolAdd request) {
		DateTime initial = new DateTime();
		JSONArray array = null;
		boolean success = false;
		String error = "";
		String mssg = "Error al insertar los datos";
		int total = 0;

		BigDecimal siguiente = new BigDecimal(0);

		try {
			System.out.println("------------------------------insertRol----------------------------------");
			SqlRowSet rec = jdbcTemplate.queryForRowSet("SELECT organization_uuid, org_name FROM datahub.sdm_ca_organization where inactive = '0' and organization_uuid = ?", request.getIdOrganizacion());

			while (rec.next()) {
			
			Properties p = new Properties();
			InputStream input = null;

			input = getClass().getClassLoader().getResourceAsStream("application.properties");
			p.load(input);

			String driver = p.getProperty("spring.datasource.driver-class-name");
			String url = p.getProperty("spring.datasource.url");
			String user = p.getProperty("spring.datasource.username");
			String password = p.getProperty("spring.datasource.password");
			String query = p.getProperty("queryRol");

			Class.forName(driver);
			Connection conexion = DriverManager.getConnection(url, user, password);
			java.sql.Statement comando = conexion.createStatement();

			ResultSet rs = comando.executeQuery(query);

			if (rs.next()) {
				siguiente = rs.getBigDecimal("SIGUIENTE");
			}

			TrolEntity ue = new TrolEntity();

			ue.setIdRol(siguiente);
			ue.setRolRole(request.getRol());
			ue.setRolDescription(request.getDescripcion());
			ue.setOrganizacion(rec.getString("organization_uuid"));
			ue.setNombreOrganizacion(rec.getString("org_name"));
			
			rolServiceBackImpl.saveRol(ue);
			}
			mssg = "Exito al insertar los datos";
			success = true;
			total++;
		} catch (Exception ex) {
			
			mssg = "Error al Agregar los datos";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}

		Period period = new Period(initial, new DateTime());
		return new ResponseUser(success, period, mssg, error, total, "", array);

	}

	public RoleList returnAllRoles() {
		DateTime initial = new DateTime();
		boolean success = false;
		String error = "";
		String mssg = "Error al extraer los datos";
		int total = 0;
		List<Role> listRoles = new ArrayList<Role>();

		try {
			List<TrolEntity> rolesList = rolServiceBackImpl.getAllRol();

			for (TrolEntity rol : rolesList) {

				Role obj = new Role();
				obj.setIdRole(rol.getIdRol());
				obj.setRolRole(rol.getRolRole());
				obj.setRolDescription(rol.getRolDescription());
				obj.setOrganizacion(rol.getOrganizacion());
				obj.setNombreOrganizacion(rol.getNombreOrganizacion());

				listRoles.add(obj);
				mssg = "Exito al conseguir los usuarios";
				success = true;
				total = listRoles.size();

			}

		} catch (Exception ex) {
			mssg = "Error al Buscar los datos";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}

		Period period = new Period(initial, new DateTime());
		return new RoleList(success, period, error, mssg, total, listRoles);
	}

	public CatOrganizacionList returnOrganization() {
		DateTime initial = new DateTime();
		boolean success = false;
		String error = "";
		String mssg = "Error al extraer los datos";
		int total = 0;
		List<CatOrganizacion> listOrganization = new ArrayList<CatOrganizacion>();

		try {
			List<CorganizacionEntity> organizationList = rolServiceBackImpl.getAllOrganization();
			for (CorganizacionEntity organization : organizationList) {

				CatOrganizacion obj = new CatOrganizacion();

				obj.setIdOrganizacion(organization.getIdOrganizacion());
				obj.setNombreOrganizacion(organization.getNombreOrganizacion());
				obj.setOrganizacionId(organization.getOrganizacionId());

				listOrganization.add(obj);
				mssg = "Exito al conseguir los usuarios";
				success = true;
				total = organizationList.size();

			}

		} catch (Exception ex) {
			mssg = "Error al Buscar los datos";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}

		Period period = new Period(initial, new DateTime());
		return new CatOrganizacionList(success, period, error, mssg, total, listOrganization);

	}

	public CatMenuList returnPrincipalMenus() {
		DateTime initial = new DateTime();
		boolean success = false;
		String error = "";
		String mssg = "Error al extraer los datos";
		int total = 0;
		List<CatMenu> listMenu = new ArrayList<CatMenu>();

		try {
			List<CMenusEntity> menuList = rolServiceBackImpl.getPrincipalMenus();
			for (CMenusEntity menu : menuList) {

				CatMenu obj = new CatMenu();

				obj.setIdMenu(menu.getIdMenu());
				obj.setIdSubMenu(menu.getIdSubMenu());
				obj.setNombreMenu(menu.getNombreMenu());
				obj.setDescripcionMenu(menu.getDescripcionMenu());
				obj.setTituloPrincipal(menu.getTituloPrincipal());

				listMenu.add(obj);
				mssg = "Exito al conseguir los menus";
				success = true;
				total = listMenu.size();

			}

		} catch (Exception ex) {
			mssg = "Error al Buscar los datos";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}

		Period period = new Period(initial, new DateTime());
		return new CatMenuList(success, period, error, mssg, total, listMenu);

	}

	public CatMenuList returnSubMenu(Cmenu request) {
		DateTime initial = new DateTime();
		boolean success = false;
		String error = "";
		String mssg = "Error al extraer los datos";
		int total = 0;
		List<CatMenu> listMenu = new ArrayList<CatMenu>();

		try {
			List<CMenusEntity> menuList = rolServiceBackImpl.getSubMenu(request.getIdMenu());
			for (CMenusEntity menu : menuList) {

				CatMenu obj = new CatMenu();

				obj.setIdMenu(menu.getIdMenu());
				obj.setIdSubMenu(menu.getIdSubMenu());
				obj.setNombreMenu(menu.getNombreMenu());
				obj.setPath(menu.getPath());
				obj.setDescripcionMenu(menu.getDescripcionMenu());

				listMenu.add(obj);
				mssg = "Exito al conseguir los subMenus";
				success = true;
				total = listMenu.size();

			}

		} catch (Exception ex) {
			mssg = "Error al Buscar los datos";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}

		Period period = new Period(initial, new DateTime());
		return new CatMenuList(success, period, error, mssg, total, listMenu);

	}


	public ResponseRol deleteRol(RolValidation request) {
		DateTime initial = new DateTime();
		JSONArray array = null;
		boolean success = false;
		String error = "";
		String mssg = "Error al eliminar los datos";
		int total = 0;

		try {

			List<TbMenuRolEntity> tbmenuList = tbMenuRolService.getMenusByRol(request.getIdrole().longValue());

			for (int i = 0; i < tbmenuList.size(); i++) {

				TbMenuRolEntity obj = (TbMenuRolEntity) tbmenuList.get(i);

				tbMenuRolService.deletePermisos(obj);
			}

			List<ToperatorRolEntity> to = toperatorRolService.getIdRol(request.getIdrole());

			for (int i = 0; i < to.size(); i++) {

				ToperatorRolEntity obj = (ToperatorRolEntity) to.get(i);

				toperatorRolService.deleteRol(obj);
			}

			TrolEntity te = rolServiceBackImpl.getRole(request.getIdrole());

			rolServiceBackImpl.deleteRol(te);
			mssg = "Rol Eliminado";
			success = true;
			total++;
		} catch (Exception ex) {

			mssg = "Error al Eliminar los datos";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}

		Period period = new Period(initial, new DateTime());
		return new ResponseRol(success, period, mssg, error, total, "", array);

	}

	public ResponseRol getIdRol(RolRequest request) {
		DateTime initial = new DateTime();

		boolean success = false;
		String error = "";
		String mssg = "Error al buscar los datos de rol";
		int total = 0;

		Role obj = new Role();

		try {

			TrolEntity te = rolServiceBackImpl.getIdRole(request.getRolRole());

			obj.setIdRole(te.getIdRol());
			obj.setRolRole(te.getRolRole());
			obj.setRolDescription(te.getRolDescription());
			obj.setOrganizacion(te.getOrganizacion());
			obj.setNombreOrganizacion(te.getNombreOrganizacion());

			mssg = "Exito extraer datos del Rol";
			success = true;
			total++;

		} catch (Exception ex) {

			mssg = "Error al buscar los datos de rol";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}

		Period period = new Period(initial, new DateTime());
		return new ResponseRol(success, period, mssg, error, total, obj);

	}

	public MenusList returnMenusByRol(TbMenuRequest request) {
		DateTime initial = new DateTime();
		boolean success = false;
		String error = "";
		String mssg = "Error al conseguir los menus por rol";
		int total = 0;
		List<Menus> listMenu = new ArrayList<Menus>();

		try {
			List<TbMenuRolEntity> tbmenuList = tbMenuRolService.getMenusByRol(request.getIdRol());

			for (TbMenuRolEntity tbmr : tbmenuList) {

				Menus obj = new Menus();

				obj.setIdMenu(tbmr.getIdMenu());
				obj.setIdRol(tbmr.getIdRol());
				obj.setIdHome(tbmr.getIdHome());

				if (obj.getIdMenu() == 0) {

					obj.setIdSubMenu(0L);
					obj.setNombreMenu("");
					obj.setPath("");
					obj.setDescripcionMenu("");
					obj.setIcono("");
					obj.setTituloPrincipal("");

				} else {

					CMenusEntity menu = rolServiceBackImpl.getMenu(obj.getIdMenu());

					obj.setIdSubMenu(menu.getIdSubMenu());
					obj.setNombreMenu(menu.getNombreMenu());
					obj.setPath(menu.getPath());
					obj.setDescripcionMenu(menu.getDescripcionMenu());
					obj.setIcono(menu.getIcono());
					obj.setTituloPrincipal(menu.getTituloPrincipal());

				}

				CatOrigenHomeEntity homeobj = origenHomeService.getHome(obj.getIdHome());

				obj.setOrigenHome(homeobj.getOrigenHome());
				obj.setUrlHome(homeobj.getUrlHome());
				obj.setDescripcion(homeobj.getDescripcion());

				listMenu.add(obj);

				mssg = "Exito al conseguir los menus por rol";
				success = true;
				total = listMenu.size();

			}

		} catch (Exception ex) {
			mssg = "Error al conseguir los menus por rol";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}

		Period period = new Period(initial, new DateTime());
		return new MenusList(success, period, error, mssg, total, listMenu);

	}

	public ResponseMenuRol addPermission(MenuRolAdd request) {
		DateTime initial = new DateTime();
		JSONArray array = null;
		boolean success = false;
		String error = "";
		String mssg = "Error al Agregar los permisos al rol";
		int total = 0;

		try {

			TbMenuRolEntity mr = new TbMenuRolEntity();

			for (Long idM : request.getIdMenu()) {

				mr.setIdRol(request.getIdRol());
				mr.setIdMenu(idM);
				mr.setIdHome(request.getIdHome());

				tbMenuRolService.savePermisos(mr);
			}

			for (Long idMs : request.getIdMenuSub()) {

				mr.setIdRol(request.getIdRol());
				mr.setIdMenu(idMs);
				mr.setIdHome(request.getIdHome());

				tbMenuRolService.savePermisos(mr);
			}

			mssg = "Exito a agregar los permisos al rol";
			success = true;
			total++;
		} catch (Exception ex) {

			mssg = "Error al Agregar los permisos al rol";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}

		Period period = new Period(initial, new DateTime());
		return new ResponseMenuRol(success, period, mssg, error, total, "", array);

	}

	public OrigenHomeList returnAllOrigenHomes() {
		DateTime initial = new DateTime();
		boolean success = false;
		String error = "";
		String mssg = "Error al extraer los datos";
		int total = 0;
		List<CatOrigenHome> listOrigenHome = new ArrayList<CatOrigenHome>();

		try {
			List<CatOrigenHomeEntity> ohList = origenHomeService.getAllOrigenHome();

			for (CatOrigenHomeEntity oh : ohList) {

				CatOrigenHome obj = new CatOrigenHome();

				obj.setIdHome(oh.getIdHome());
				obj.setOrigenHome(oh.getOrigenHome());
				obj.setUrlHome(oh.getUrlHome());
				obj.setDescripcion(oh.getDescripcion());

				listOrigenHome.add(obj);
				mssg = "Exito al conseguir los usuarios";
				success = true;
				total = listOrigenHome.size();

			}

		} catch (Exception ex) {
			mssg = "Error al Buscar los datos";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}

		Period period = new Period(initial, new DateTime());
		return new OrigenHomeList(success, period, error, mssg, total, listOrigenHome);
	}
}
