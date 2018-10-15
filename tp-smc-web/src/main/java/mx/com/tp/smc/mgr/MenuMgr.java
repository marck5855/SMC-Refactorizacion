
package mx.com.tp.smc.mgr;

//import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestParam;
//import mx.com.tp.smc.conf.ConexionSMC;
import mx.com.tp.smc.conf.Constant;
import mx.com.tp.smc.entity.CatMenu;
import mx.com.tp.smc.entity.PointCMDB;
import mx.com.tp.smc.entity.TbMenuRol;
import mx.com.tp.smc.manager.RolManager;
//import mx.com.tp.smc.entity.User;
import mx.com.tp.smc.model.CatMenuModel;
import mx.com.tp.smc.model.MenuRolModel;
import mx.com.tp.smc.request.Cmenu;
import mx.com.tp.smc.request.MenuRolAdd;
import mx.com.tp.smc.response.CatMenuList;
import mx.com.tp.smc.response.ResponseMenuRol;
//import mx.com.tp.smc.model.MenusModel;
//import mx.com.tp.smc.model.UserModel;
import mx.com.tp.smc.service.MenuService;
import mx.com.tp.smc.service.TokenService;
//import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

@Component
public class MenuMgr {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private MenuService menuService;
	
	//Marco
	@Autowired
	private RolManager rolManager;
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

//	Marco
	public CatMenuModel getPrincipalMenus() {
		CatMenuModel model = new CatMenuModel();
		System.out.println("----------------------Metodo getPrincipalMenus----------------");
		try {
			ArrayList<CatMenu> menus = new ArrayList<CatMenu>();
			menus = (ArrayList<CatMenu>) jdbcTemplate.query(Constant.SQL_getPrincipalMenus, new Object[] {},
					new RowMapper<CatMenu>() {

						@Override
						public CatMenu mapRow(ResultSet rs, int rowNum) throws SQLException {
							CatMenu menu = new CatMenu();
							menu.setIdMenu(rs.getLong("ID_MENU"));
							menu.setIdSubMenu(rs.getLong("id_submenu"));
							menu.setNombreMenu(rs.getString("NOMBRE"));
							menu.setPath(rs.getString("PATH"));
							menu.setDescripcionMenu(rs.getString("DESCRIPCION"));
							menu.setIcono(rs.getString("ICONO"));
							menu.setTituloPrincipal(rs.getString("TITULO_PRINCIPAL"));
							
							return menu;
						}});
			model.setMenus(menus);			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
//	Version anterior
//	public CatMenuModel getPrincipalMenus() {
//		CatMenuModel model = new CatMenuModel();
//		try {
//			ConexionSMC.connect();
//			Statement st = ConexionSMC.jdbcConnection.createStatement();
//			ResultSet registro = st.executeQuery("SELECT * FROM CAT_MENUS"); 
//			
//			ArrayList<CatMenu> menus = new ArrayList<CatMenu>();
//			
//			while(registro.next()) {
//				CatMenu menu = new CatMenu();
//				menu.setIdMenu(registro.getLong("ID_MENU"));
//				menu.setIdSubMenu(registro.getLong("id_submenu"));
//				menu.setNombreMenu(registro.getString("NOMBRE"));
//				menu.setPath(registro.getString("PATH"));
//				menu.setDescripcionMenu(registro.getString("DESCRIPCION"));
//				menu.setIcono(registro.getString("ICONO"));
//				menu.setTituloPrincipal(registro.getString("TITULO_PRINCIPAL"));
//				
//				menus.add(menu);
//			}
//			model.setMenus(menus);
//			ConexionSMC.disconnect();
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}
	
//	Marco
	public CatMenuModel getSubMenu(Long idMenu) {
		System.out.println("Metodo getSubMenu -------------------------------------------------------------------------------------------------------> 1");
		CatMenuModel model = new CatMenuModel();
		Cmenu request = new Cmenu();
		request.setIdMenu(idMenu);
		try {
			CatMenuList responseManager = rolManager.returnSubMenu(request);
			if ((Boolean) responseManager.getSuccess()) {
				ArrayList<CatMenu> menus = new ArrayList<CatMenu>();

				for (mx.com.tp.smc.response.CatMenu catmenu : responseManager.getListMenu()) {

					CatMenu menu = new CatMenu();
					if (catmenu.getIdMenu() != null)
						menu.setIdMenu((Long) catmenu.getIdMenu());

					if (catmenu.getIdSubMenu() != null)
						menu.setIdSubMenu((Long) catmenu.getIdSubMenu());

					if (catmenu.getNombreMenu() != null)
						menu.setNombreMenu((String) catmenu.getNombreMenu());

					if (catmenu.getPath() != null)
						menu.setPath((String) catmenu.getPath());

					if (catmenu.getDescripcionMenu() != null)
						menu.setDescripcionMenu((String) catmenu.getDescripcionMenu());

					menus.add(menu);
				}

				model.setMenus(menus);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
//Versión anterior
//	public CatMenuModel getSubMenu(Long idMenu) {
//		CatMenuModel model = new CatMenuModel();
//		try {
//			JSONObject json = menuService.getSubMenu(tokenService.getToken("adri", "adri"), idMenu);
//			if ((Boolean) json.get("success")) {
//				JSONArray array = (JSONArray) json.get("listMenu");
//				ArrayList<CatMenu> menus = new ArrayList<CatMenu>();
//
//				for (int i = 0; i < array.size(); i++) {
//					JSONObject object = (JSONObject) array.get(i);
//
//					CatMenu menu = new CatMenu();
//					if (object.get("idMenu") != null)
//						menu.setIdMenu((Long) object.get("idMenu"));
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
//					menus.add(menu);
//				}
//
//				model.setMenus(menus);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}

//	Marco
	public MenuRolModel permisosAdd(Long idRol, List<Long> idMenu, List<Long> idMenuSub, Long idHome) {
		System.out.println("Metodo permisosAdd -------------------------------------------------------------------------------------------------------> 2");
		MenuRolModel model = new MenuRolModel();
		MenuRolAdd request = new MenuRolAdd();
		request.setIdRol(idRol);
		request.setIdMenu(idMenu);
		request.setIdMenuSub(idMenuSub);
		request.setIdHome(idHome);
		try {
			ResponseMenuRol responseManager = rolManager.addPermission(request);
			if ((Boolean) responseManager.getSuccess()) {
				ArrayList<TbMenuRol> menuRol = new ArrayList<TbMenuRol>();
				TbMenuRol mr = new TbMenuRol();
				mr.setMssg((String) responseManager.getMssg());
				mr.setSucces(String.valueOf(responseManager.getSuccess()));
				mr.setDisabled("disabled");
				menuRol.add(mr);
				model.setMenuRol(menuRol);
			}else {
				
				ArrayList<TbMenuRol> menuRol = new ArrayList<TbMenuRol>();
				TbMenuRol mr = new TbMenuRol();
				
				mr.setDisabled("false");
				menuRol.add(mr);
				model.setMenuRol(menuRol);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
//	Versión anterior
//	public MenuRolModel permisosAdd(Long idRol, List<Long> idMenu, List<Long> idMenuSub, Long idHome) {
//		MenuRolModel model = new MenuRolModel();
//		try {
//			JSONObject json = menuService.permisosAdd(tokenService.getToken("adri", "adri"), idRol, idMenu, idMenuSub,idHome);
//			if ((Boolean) json.get("success")) {
//				ArrayList<TbMenuRol> menuRol = new ArrayList<TbMenuRol>();
//				TbMenuRol mr = new TbMenuRol();
//				mr.setMssg((String) json.get("mssg"));
//				mr.setSucces((String) json.get("success").toString());
//				mr.setDisabled("disabled");
//				menuRol.add(mr);
//				model.setMenuRol(menuRol);
//			}else {
//				
//				ArrayList<TbMenuRol> menuRol = new ArrayList<TbMenuRol>();
//				TbMenuRol mr = new TbMenuRol();
//				
//				mr.setDisabled("false");
//				menuRol.add(mr);
//				model.setMenuRol(menuRol);
//				
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}
}
