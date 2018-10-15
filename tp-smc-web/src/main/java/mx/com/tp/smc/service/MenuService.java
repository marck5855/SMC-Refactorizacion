package mx.com.tp.smc.service;

import java.util.List;

import org.json.simple.JSONObject;

public interface MenuService {

	JSONObject getPrincipalMenus(String token) throws Exception;

	JSONObject getSubMenu(String token, Long idMenu) throws Exception;

	JSONObject permisosAdd(String token, Long idRol, List<Long> idMenu, List<Long> idMenuSub, Long idHome) throws Exception;

}
