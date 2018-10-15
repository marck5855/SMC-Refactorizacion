package mx.com.tp.smc.mgr;

import java.io.Console;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.itextpdf.text.log.SysoCounter;

//import mx.com.tp.smc.conf.ConexionGETL;
//import mx.com.tp.smc.conf.ConexionSMC;
import mx.com.tp.smc.conf.Constant;
import mx.com.tp.smc.entity.CatMenu;
import mx.com.tp.smc.entity.CatOrganizacion;
import mx.com.tp.smc.entity.Menus;
import mx.com.tp.smc.entity.Role;
import mx.com.tp.smc.entity.TrolEntity;
import mx.com.tp.smc.manager.RolManager;
import mx.com.tp.smc.model.CatOrganizacionModel;
import mx.com.tp.smc.model.MenusModel;
import mx.com.tp.smc.model.RoleModel;
import mx.com.tp.smc.request.RolAdd;
import mx.com.tp.smc.request.RolRequest;
import mx.com.tp.smc.request.RolValidation;
import mx.com.tp.smc.request.TbMenuRequest;
import mx.com.tp.smc.response.CatOrganizacionList;
import mx.com.tp.smc.response.MenusList;
import mx.com.tp.smc.response.ResponseRol;
import mx.com.tp.smc.response.ResponseUser;
import mx.com.tp.smc.response.RoleList;
import mx.com.tp.smc.service.RolService;
import mx.com.tp.smc.service.RolServiceBack;
import mx.com.tp.smc.service.TokenService;

@Component
public class RolMgr {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private RolService rolService;
	
	//Marcko
	@Autowired
	private RolManager rolManager;
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
//  Marco  
	public RoleModel insertRoles(String rol, String descripcion, String idOrganizacion) {

		System.out.println(" === rolesadd: rol: === " + rol + " === descripcion === " + descripcion + " === organization === " + idOrganizacion);

		RoleModel model = new RoleModel();
		RolAdd request = new RolAdd();
		request.setRol(rol);
		request.setDescripcion(descripcion);
		request.setIdOrganizacion(idOrganizacion);
		try {
			ResponseUser responseManager = rolManager.insertRol(request);
			if ((Boolean) responseManager.getSuccess()) {
				ArrayList<Role> roles = new ArrayList<Role>();
				System.out.println("=== V - ROL === " + roles);
				System.out.println("=== V - ROL === " + responseManager);
				Role role = new Role();
				role.setMssg((String) responseManager.getMssg());
				role.setSucces(String.valueOf(responseManager.getSuccess()));
				roles.add(role);
				model.setRoles(roles);
				
				//Subir variable
				FileWriter fichero = null;
		        PrintWriter pw = null;
		        try
		        {
		        	fichero = new FileWriter("/opt/apache-tomcat-9.0.1/temp/" + "rolesCreados.txt");
		            //fichero = new FileWriter("/home/implementacion/apache-tomcat-9.0.1/temp/" + "rolesCreados.txt");
		            System.out.println("=== VALIDANDO ARCHIVO DEL SERVIDOR === " + fichero);
		            pw = new PrintWriter(fichero);
		            pw.println("ROLCREADO");
		            System.out.println("=== EL ROL FUE CREADO === ");
		            pw.close();
		        } catch (Exception e) {
		        	pw.close();
		            e.printStackTrace();
		        } finally {
		           try {
		           // Nuevamente aprovechamos el finally para 
		           // asegurarnos que se cierra el fichero.
		           if (null != fichero) {
		        	   pw.close();
		        	   fichero.close();
		           }
		           } catch (Exception e2) {
		        	   pw.close();
		              e2.printStackTrace();
		           }
		        }

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	
//Versión anterior	
//	public RoleModel insertRoles(String rol, String descripcion, String idOrganizacion) {
//
//		System.out.println(" === rolesadd: rol: === " + rol + " === descripcion === " + descripcion + " === organization === " + idOrganizacion);
//
//		RoleModel model = new RoleModel();
//		try {
//			JSONObject json = rolService.insertRoles(tokenService.getToken("adri", "adri"), rol, descripcion,idOrganizacion);
//			if ((Boolean) json.get("success")) {
//				ArrayList<Role> roles = new ArrayList<Role>();
//				System.out.println("=== V - ROL === " + roles);
//				System.out.println("=== V - ROL === " + json);
//				Role role = new Role();
//				role.setMssg((String) json.get("mssg"));
//				role.setSucces((String) json.get("success").toString());
//				roles.add(role);
//				model.setRoles(roles);
//				
//				//Subir variable
//				FileWriter fichero = null;
//		        PrintWriter pw = null;
//		        try
//		        {
//		        	fichero = new FileWriter("/opt/apache-tomcat-9.0.1/temp/" + "rolesCreados.txt");
//		            //fichero = new FileWriter("/home/implementacion/apache-tomcat-9.0.1/temp/" + "rolesCreados.txt");
//		            System.out.println("=== VALIDANDO ARCHIVO DEL SERVIDOR === " + fichero);
//		            pw = new PrintWriter(fichero);
//		            pw.println("ROLCREADO");
//		            System.out.println("=== EL ROL FUE CREADO === ");
//		            pw.close();
//		        } catch (Exception e) {
//		        	pw.close();
//		            e.printStackTrace();
//		        } finally {
//		           try {
//		           // Nuevamente aprovechamos el finally para 
//		           // asegurarnos que se cierra el fichero.
//		           if (null != fichero) {
//		        	   pw.close();
//		        	   fichero.close();
//		           }
//		           } catch (Exception e2) {
//		        	   pw.close();
//		              e2.printStackTrace();
//		           }
//		        }
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}

//	Marco
	public RoleModel getRoles() {
		RoleModel model = new RoleModel();
		try {
			RoleList responseManager = rolManager.returnAllRoles();
			if ((Boolean) responseManager.getSuccess()) {
				ArrayList<Role> roles = new ArrayList<Role>();
				for (mx.com.tp.smc.response.Role role : responseManager.getListRole()) {
					Role rol = new Role();

					if (role.getIdRole() != null)
						rol.setIdRole(role.getIdRole().longValue());
					if (role.getRolRole() != null)
						rol.setRolRole((String) role.getRolRole());
					if (role.getRolDescription() != null)
						rol.setRolDescription((String) role.getRolDescription());
					if (role.getOrganizacion() != null)
						rol.setOrganizacion((String) role.getOrganizacion());
					if (role.getNombreOrganizacion() != null)
						rol.setNombreOrganizacion((String) role.getNombreOrganizacion());

					MenusModel menus = getMenusByRol(rol.getIdRole());

					ArrayList<Menus> menusDetalleArray = new ArrayList<Menus>();

					menusDetalleArray = menus.getMenus();

					ArrayList<Menus> submenus = new ArrayList<Menus>();

					if (menusDetalleArray == null) {

						rol.setTieneMenus(false);
						rol.setDisabled(false);
						
					} else {

						for (int x = 0; x < menusDetalleArray.size(); x++) {

							Menus objMenu = (Menus) menusDetalleArray.get(x);

							Menus submenu = new Menus();

							submenu.setIdMenu(objMenu.getIdMenu());
							submenu.setIdSubMenu(objMenu.getIdSubMenu());
							submenu.setNombreMenu(objMenu.getNombreMenu());
							submenu.setIdHome(objMenu.getIdHome());
							submenu.setDescripcion(objMenu.getDescripcion());
							submenu.setTieneMenus(true);
							submenu.setOrigenHome(objMenu.getOrigenHome());

							submenus.add(submenu);

							rol.setIdMenu(submenu.getIdMenu());
							rol.setDescripcion(submenu.getDescripcion());
							rol.setOrigenHome(submenu.getOrigenHome());

						}

						rol.setTieneMenus(true);
						rol.setDisabled(true);
						rol.setMenusDetalle(menus.getMenus());
						rol.setSubMenusDetalle(submenus);

					}

					roles.add(rol);

				}
				model.setRoles(roles);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
//	Versión anterior
//	public RoleModel getRoles() {
//		RoleModel model = new RoleModel();
//		try {
//
//			JSONObject json = rolService.getRoles(tokenService.getToken("adri", "adri"));
//			if ((Boolean) json.get("success")) {
//				JSONArray array = (JSONArray) json.get("listRole");
//				ArrayList<Role> roles = new ArrayList<Role>();
//				for (int i = 0; i < array.size(); i++) {
//					JSONObject object = (JSONObject) array.get(i);
//					Role rol = new Role();
//
//					if (object.get("idRole") != null)
//						rol.setIdRole((Long) object.get("idRole"));
//					if (object.get("rolRole") != null)
//						rol.setRolRole((String) object.get("rolRole"));
//					if (object.get("rolDescription") != null)
//						rol.setRolDescription((String) object.get("rolDescription"));
//					if (object.get("organizacion") != null)
//						rol.setOrganizacion((String) object.get("organizacion"));
//					if (object.get("nombreOrganizacion") != null)
//						rol.setNombreOrganizacion((String) object.get("nombreOrganizacion"));
//
//					MenusModel menus = getMenusByRol(rol.getIdRole());
//
//					ArrayList<Menus> menusDetalleArray = new ArrayList<Menus>();
//
//					menusDetalleArray = menus.getMenus();
//
//					ArrayList<Menus> submenus = new ArrayList<Menus>();
//
//					if (menusDetalleArray == null) {
//
//						rol.setTieneMenus(false);
//						rol.setDisabled(false);
//						
////						System.out.println("**valor de rol FALSE**" + rol.isDisabled());
//
//					} else {
//
//						for (int x = 0; x < menusDetalleArray.size(); x++) {
//
//							Menus objMenu = (Menus) menusDetalleArray.get(x);
//
//							Menus submenu = new Menus();
//
//							submenu.setIdMenu(objMenu.getIdMenu());
//							submenu.setIdSubMenu(objMenu.getIdSubMenu());
//							submenu.setNombreMenu(objMenu.getNombreMenu());
//							submenu.setIdHome(objMenu.getIdHome());
//							submenu.setDescripcion(objMenu.getDescripcion());
//							submenu.setTieneMenus(true);
//							submenu.setOrigenHome(objMenu.getOrigenHome());
//
//							submenus.add(submenu);
//
//							//rol.setIdHome(objMenu.getIdHome());
//							rol.setIdMenu(submenu.getIdMenu());
//							rol.setDescripcion(submenu.getDescripcion());
//							rol.setOrigenHome(submenu.getOrigenHome());
//
//						}
//
//						rol.setTieneMenus(true);
//						rol.setDisabled(true);
////						System.out.println("**valor de rol TRUE**" + rol.isDisabled());
//						rol.setMenusDetalle(menus.getMenus());
//						rol.setSubMenusDetalle(submenus);
//
//					}
//
//					roles.add(rol);
//
//				}
//				model.setRoles(roles);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}
	
	
	public RoleModel getRolesSMDB() {
		RoleModel model = new RoleModel();
		try {
			System.out.println("---Inicia getRolesSMDB---");
			ArrayList<Role> rol = new  ArrayList<Role>();
			rol = (ArrayList<Role>) jdbcTemplate.query(Constant.SQL_getRolesSMDB, new Object[] {},
					new RowMapper<Role>() {

						@Override
						public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
							Role roles = new Role();
							System.out.println("---Inicia mapRow---");
							roles.setIdRole(rs.getLong("IDROL"));
							roles.setRolRole(rs.getString("ROL_ROLE"));
							roles.setRolDescription(rs.getString("ROL_DESCRIPTION"));
							roles.setNombreOrganizacion(rs.getString("NOMBRE_ORGANIZACION"));

							MenusModel menus = getMenusporRolCMDB(rs.getBigDecimal("IDROL"));
							
						
							ArrayList<Menus> menusDetalle = new ArrayList<Menus>();
							ArrayList<Menus> submenus = new ArrayList<Menus>();
							menusDetalle = menus.getMenus();
							
							if(menus.getMenus().size() == 0) {
								roles.setTieneMenus(false);
								roles.setDisabled(false);				

							}else {
							
							for (int x = 0; x < menusDetalle.size(); x++) {
								Menus objMenu = (Menus) menusDetalle.get(x);
								Menus submenu = new Menus();
					
								submenu.setIdMenu(objMenu.getIdMenu());
								submenu.setIdRol(objMenu.getIdRol());
								submenu.setIdHome(objMenu.getIdHome());
								submenu.setIdSubMenu(objMenu.getIdSubMenu());
								submenu.setNombreMenu(objMenu.getNombreMenu());
								submenu.setPath(objMenu.getPath());
								submenu.setDescripcionMenu(objMenu.getDescripcionMenu());
								submenu.setIcono(objMenu.getIcono());
								submenu.setTituloPrincipal(objMenu.getTituloPrincipal());
								submenus.add(submenu);
								roles.setIdMenu(submenu.getIdMenu());
								roles.setDescripcion(submenu.getDescripcion());
								roles.setOrigenHome(submenu.getOrigenHome());
							}

							roles.setTieneMenus(true);
							roles.setDisabled(true);
							roles.setMenusDetalle(menus.getMenus());
							roles.setSubMenusDetalle(submenus);
							}

							return roles;
						}});
			model.setRoles(rol);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	
//	public RoleModel getRolesSMDB() {
//		RoleModel model = new RoleModel();
//		try {
//			ConexionSMC.connect();
//			Statement st = ConexionSMC.jdbcConnection.createStatement();
//			
//			ResultSet registro = st.executeQuery("SELECT * FROM TROL ORDER BY IDROL");
//			ArrayList<Role> rol = new  ArrayList<Role>();
//			while (registro.next()) {
//				Role roles = new Role();
//				roles.setIdRole(registro.getLong("IDROL"));
//				roles.setRolRole(registro.getString("ROL_ROLE"));
//				roles.setRolDescription(registro.getString("ROL_DESCRIPTION"));
//				roles.setNombreOrganizacion(registro.getString("NOMBRE_ORGANIZACION"));
//
//				MenusModel menus = getMenusporRolCMDB(registro.getBigDecimal("IDROL"));
//				
//			
//				ArrayList<Menus> menusDetalle = new ArrayList<Menus>();
//				ArrayList<Menus> submenus = new ArrayList<Menus>();
//				menusDetalle = menus.getMenus();
//				
//				if(menus.getMenus().size() == 0) {
//					roles.setTieneMenus(false);
//					roles.setDisabled(false);				
//
//				}else {
//				
//				for (int x = 0; x < menusDetalle.size(); x++) {
//					Menus objMenu = (Menus) menusDetalle.get(x);
//					Menus submenu = new Menus();
//		
//					submenu.setIdMenu(objMenu.getIdMenu());
//					submenu.setIdRol(objMenu.getIdRol());
//					submenu.setIdHome(objMenu.getIdHome());
//					submenu.setIdSubMenu(objMenu.getIdSubMenu());
//					submenu.setNombreMenu(objMenu.getNombreMenu());
//					submenu.setPath(objMenu.getPath());
//					submenu.setDescripcionMenu(objMenu.getDescripcionMenu());
//					submenu.setIcono(objMenu.getIcono());
//					submenu.setTituloPrincipal(objMenu.getTituloPrincipal());
//					submenus.add(submenu);
//					roles.setIdMenu(submenu.getIdMenu());
//					roles.setDescripcion(submenu.getDescripcion());
//					roles.setOrigenHome(submenu.getOrigenHome());
//				}
//
//				roles.setTieneMenus(true);
//				roles.setDisabled(true);
//				roles.setMenusDetalle(menus.getMenus());
//				roles.setSubMenusDetalle(submenus);
//				}
//				rol.add(roles);
//			}
//			model.setRoles(rol);
//			ConexionSMC.disconnect();
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}
	
//	Marco
	public CatOrganizacionModel getAllOrganizations() {
		CatOrganizacionModel model = new CatOrganizacionModel();
		try {
			CatOrganizacionList responseManager = rolManager.returnOrganization();
			if ((Boolean) responseManager.getSuccess()) {
				ArrayList<CatOrganizacion> organizaciones = new ArrayList<CatOrganizacion>();
				for (mx.com.tp.smc.response.CatOrganizacion catorga : responseManager.getListOrganization()) {

					CatOrganizacion organizacion = new CatOrganizacion();
					if (catorga.getIdOrganizacion() != null)
						organizacion.setIdOrganizacion((Long) catorga.getIdOrganizacion());

					if (catorga.getNombreOrganizacion() != null)
						organizacion.setNombreOrganizacion((String) catorga.getNombreOrganizacion());

					if (catorga.getOrganizacionId() != null)
						organizacion.setOrganizacionId((String) catorga.getOrganizacionId());

					organizaciones.add(organizacion);
				}

				model.setOrganizaciones(organizaciones);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
//	Versión anterior
//	public CatOrganizacionModel getAllOrganizations() {
//		CatOrganizacionModel model = new CatOrganizacionModel();
//		try {
//			JSONObject json = rolService.getAllOrganizations(tokenService.getToken("adri", "adri"));
//			if ((Boolean) json.get("success")) {
//				JSONArray array = (JSONArray) json.get("listOrganization");
//				ArrayList<CatOrganizacion> organizaciones = new ArrayList<CatOrganizacion>();
//				for (int i = 0; i < array.size(); i++) {
//					JSONObject object = (JSONObject) array.get(i);
//
//					CatOrganizacion organizacion = new CatOrganizacion();
//					if (object.get("idOrganizacion") != null)
//						organizacion.setIdOrganizacion((Long) object.get("idOrganizacion"));
//
//					if (object.get("nombreOrganizacion") != null)
//						organizacion.setNombreOrganizacion((String) object.get("nombreOrganizacion"));
//
//					if (object.get("organizacionId") != null)
//						organizacion.setOrganizacionId((String) object.get("organizacionId"));
//
//					organizaciones.add(organizacion);
//				}
//
//				model.setOrganizaciones(organizaciones);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}

//	Marco
	public RoleModel deleteRol(BigDecimal idRol) {
		RoleModel model = new RoleModel();
		RolValidation request =  new RolValidation();
		request.setIdrole(idRol);
		try {
			ResponseRol responseManager = rolManager.deleteRol(request);
			if ((Boolean) responseManager.getSuccess()) {
				ArrayList<Role> roles = new ArrayList<Role>();
				Role rol = new Role();
				rol.setMssg((String) responseManager.getMssg());
				rol.setSucces(String.valueOf(responseManager.getSuccess()));
				roles.add(rol);
				model.setRoles(roles);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
//	Versión anterior
//	public RoleModel deleteRol(BigDecimal idRol) {
//		RoleModel model = new RoleModel();
//		try {
//			JSONObject json = rolService.deleteRol(tokenService.getToken("adri", "adri"), idRol);
//			if ((Boolean) json.get("success")) {
//				ArrayList<Role> roles = new ArrayList<Role>();
//				Role rol = new Role();
//				rol.setMssg((String) json.get("mssg"));
//				rol.setSucces((String) json.get("success").toString());
//				roles.add(rol);
//				model.setRoles(roles);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}

	public MenusModel deletePermiso(BigDecimal idRol, Long idMenuDetalle, Long idMenuSubDetalle) {
		MenusModel model = new MenusModel();
		try {
			JSONObject json = rolService.deletePermiso(tokenService.getToken("adri", "adri"), idRol, idMenuDetalle,
					idMenuSubDetalle);
			if ((Boolean) json.get("success")) {
				ArrayList<Menus> menus = new ArrayList<Menus>();
				Menus menu = new Menus();
				menu.setMssg((String) json.get("mssg"));
				menu.setSucces((String) json.get("success").toString());
				menus.add(menu);
				model.setMenus(menus);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	


	//Marco
	public RoleModel getIdRol(String rolRole) {
		RoleModel model = new RoleModel();
		RolRequest request = new RolRequest();
		Long iderol;
		request.setRolRole(rolRole);
		System.out.println("rolRole---->"+ rolRole);
		try {
			ResponseRol responseManager = rolManager.getIdRol(request);
			System.out.println("getRol-------------------" + responseManager.getRol().getIdRole());
		
			if (responseManager.getRol() != null) {
				iderol = responseManager.getRol().getIdRole().longValue();
				
				ArrayList<Role> roles = new ArrayList<Role>();
				Role rol = new Role();

				rol.setIdRole(iderol);

				roles.add(rol);
				model.setRoles(roles);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

//	Versión anterior
//	public RoleModel getIdRol(String rolRole) {
//		RoleModel model = new RoleModel();
//
//		try {
//
//			JSONObject response = rolService.getIdRol(tokenService.getToken("adri", "adri"), rolRole);
//
//			if ((Boolean) response.get("success")) {
//
//				JSONObject jsonObject = (JSONObject) response.get("rol");
//
//				ArrayList<Role> roles = new ArrayList<Role>();
//				Role rol = new Role();
//
//				rol.setIdRole((Long) jsonObject.get("idRole"));
//
//				roles.add(rol);
//				model.setRoles(roles);
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}

//	Marco
	public MenusModel getMenusByRol(Long idRol) {
		System.out.println("idRol------------------------>"+ idRol);
		MenusModel model = new MenusModel();
		TbMenuRequest request = new TbMenuRequest();
		request.setIdRol(idRol);
		try {
			System.out.println("getIdRol------------------------>"+ request.getIdRol());
			MenusList responseManager = rolManager.returnMenusByRol(request);
			
			if ((Boolean) responseManager.getSuccess()) {
				ArrayList<Menus> menus = new ArrayList<Menus>();
				
				for(mx.com.tp.smc.response.Menus men : responseManager.getListMenuRol()) {
					Menus menu = new Menus();

					if (men.getIdMenu() != null)
						menu.setIdMenu((Long) men.getIdMenu());

					if (men.getIdRol() != null)
						menu.setIdRol((Long) men.getIdRol());

					if (men.getIdHome() != null)
						menu.setIdHome((Long) men.getIdHome());

					if (men.getIdSubMenu() != null)
						menu.setIdSubMenu((Long) men.getIdSubMenu());

					if (men.getNombreMenu() != null)
						menu.setNombreMenu((String) men.getNombreMenu());

					if (men.getPath() != null)
						menu.setPath((String) men.getPath());

					if (men.getDescripcionMenu() != null)
						menu.setDescripcionMenu((String) men.getDescripcionMenu());

					if (men.getIcono() != null)
						menu.setIcono((String) men.getIcono());

					if (men.getTituloPrincipal() != null)
						menu.setTituloPrincipal((String) men.getTituloPrincipal());

					if (men.getOrigenHome() != null)
						menu.setOrigenHome((String) men.getOrigenHome());

					if (men.getUrlHome() != null)
						menu.setUrlHome((String) men.getUrlHome());

					if (men.getDescripcion() != null)
						menu.setDescripcion((String) men.getDescripcion());

					menus.add(menu);
				}
				model.setMenus(menus);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	
//  Version anterior	
//	public MenusModel getMenusByRol(Long idRol) {
//		MenusModel model = new MenusModel();
//		try {
//
//			JSONObject json = rolService.getMenusByRol(tokenService.getToken("adri", "adri"), idRol);
//
//			if ((Boolean) json.get("success")) {
//
//				JSONArray array = (JSONArray) json.get("listMenuRol");
//
//				ArrayList<Menus> menus = new ArrayList<Menus>();
//
//				for (int i = 0; i < array.size(); i++) {
//
//					JSONObject object = (JSONObject) array.get(i);
//					Menus menu = new Menus();
//
//					if (object.get("idMenu") != null)
//						menu.setIdMenu((Long) object.get("idMenu"));
//
//					if (object.get("idRol") != null)
//						menu.setIdRol((Long) object.get("idRol"));
//
//					if (object.get("idHome") != null)
//						menu.setIdHome((Long) object.get("idHome"));
//
//					if (object.get("idSubMenu") != null)
//						menu.setIdSubMenu((Long) object.get("idSubMenu"));
//
//					if (object.get("nombreMenu") != null)
//						menu.setNombreMenu((String) object.get("nombreMenu"));
//
//					if (object.get("path") != null)
//						menu.setPath((String) object.get("path"));
//
//					if (object.get("descripcionMenu") != null)
//						menu.setDescripcionMenu((String) object.get("descripcionMenu"));
//
//					if (object.get("icono") != null)
//						menu.setIcono((String) object.get("icono"));
//
//					if (object.get("tituloPrincipal") != null)
//						menu.setTituloPrincipal((String) object.get("tituloPrincipal"));
//
//					if (object.get("origenHome") != null)
//						menu.setOrigenHome((String) object.get("origenHome"));
//
//					if (object.get("urlHome") != null)
//						menu.setUrlHome((String) object.get("urlHome"));
//
//					if (object.get("descripcion") != null)
//						menu.setDescripcion((String) object.get("descripcion"));
//
//					menus.add(menu);
//				}
//				model.setMenus(menus);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}

//	Marco
	private MenusModel getMenusporRolCMDB(BigDecimal idRol) {
		MenusModel model = new MenusModel();
		try {
			ArrayList<Menus> menu = new ArrayList<Menus>();
			menu = (ArrayList<Menus>) jdbcTemplate.query(Constant.SQL_getMenusporRolCMDB, new Object[] {idRol},
					new RowMapper<Menus>() {

						@Override
						public Menus mapRow(ResultSet rs, int rowNum) throws SQLException {
							Menus menus = new Menus();
							menus.setIdMenu(rs.getLong("ID_MENU"));
							menus.setIdRol(rs.getLong("IDROL"));
							menus.setIdHome(rs.getLong("ID_HOME"));
							menus.setIdSubMenu(rs.getLong("ID_SUBMENU"));
							menus.setNombreMenu(rs.getString("NOMBRE"));
							menus.setPath(rs.getString("PATH"));
							menus.setDescripcion(rs.getString("DESCRIPCION"));
							menus.setIcono(rs.getString("ICONO"));
							menus.setTituloPrincipal(rs.getString("TITULO_PRINCIPAL"));
							return menus;
						}
				
			});
			model.setMenus(menu);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
//	Versión anterior 
//	private MenusModel getMenusporRolCMDB(BigDecimal idRol) {
//		MenusModel model = new MenusModel();
//		try {
//			ConexionSMC.connect();
//			Statement st = ConexionSMC.jdbcConnection.createStatement();
//			
//			ResultSet registro = st.executeQuery("SELECT * FROM TB_MENU_ROL TR INNER JOIN CAT_MENUS M ON TR.ID_MENU = M.ID_MENU WHERE IDROL = '"+ idRol + "'");
//			
//			ArrayList<Menus> menu = new ArrayList<Menus>();
//			while (registro.next()) {
//			Menus menus = new Menus();
//				menus.setIdMenu(registro.getLong("ID_MENU"));
//				menus.setIdRol(registro.getLong("IDROL"));
//				menus.setIdHome(registro.getLong("ID_HOME"));
//				menus.setIdSubMenu(registro.getLong("ID_SUBMENU"));
//				menus.setNombreMenu(registro.getString("NOMBRE"));
//				menus.setPath(registro.getString("PATH"));
//				menus.setDescripcion(registro.getString("DESCRIPCION"));
//				menus.setIcono(registro.getString("ICONO"));
//				menus.setTituloPrincipal(registro.getString("TITULO_PRINCIPAL"));
//				menu.add(menus);
//			}
//			model.setMenus(menu);
//			ConexionSMC.disconnect();
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}
	

}
