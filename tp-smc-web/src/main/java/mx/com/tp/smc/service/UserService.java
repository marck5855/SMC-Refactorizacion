package mx.com.tp.smc.service;

import java.math.BigDecimal;

import org.json.simple.JSONObject;

public interface UserService {

	public void createUser();
	public void updateUser();
	public void changePassword();
	public JSONObject getAllUsers(String token,String username,String organization, String createuser) throws Exception;
	JSONObject insertUsers(String token,String username,String name,String organization,String password,BigDecimal idrole, String createuser, String noUsr) throws Exception;
	JSONObject updateUsers(String token,String username,String name,String organization,String password) throws Exception;
	public JSONObject deleteUsers(String token,String username) throws Exception;
	public JSONObject loginUsers(String token,String username,String password) throws Exception;
	public JSONObject getRolesByOrganizacion(String token,String organization) throws Exception;
	public JSONObject updateRoleUser(String token,String username,BigDecimal idrol) throws Exception;
	public JSONObject getOrganizations(String token, String organization) throws Exception;
	public JSONObject getOrganizationByUser(String token,String username) throws Exception;
	public JSONObject getRolByUser(String token, String username) throws Exception;
}
