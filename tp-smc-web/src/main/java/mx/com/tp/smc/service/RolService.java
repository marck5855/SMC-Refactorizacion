package mx.com.tp.smc.service;

import java.math.BigDecimal;
import java.util.List;

import org.json.simple.JSONObject;

import mx.com.tp.smc.entity.TrolEntity2;

public interface RolService {
	
	JSONObject insertRoles(String token, String rol, String descripcion, String idOrganizacion) throws Exception;
	JSONObject getRoles(String token) throws Exception;
	JSONObject getAllOrganizations(String token) throws Exception;
	JSONObject deleteRol(String token, BigDecimal idRol) throws Exception;
	JSONObject getIdRol(String token, String rolRole) throws Exception;
	JSONObject getMenusByRol(String token, Long idRol) throws Exception;
	JSONObject deletePermiso(String token, BigDecimal idRol,Long idMenuDetalle, Long idMenuSubDetalle) throws Exception;

}
