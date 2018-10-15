package mx.com.tp.smc.mgr;

import java.io.InputStream;
import java.math.BigDecimal;
//import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
//import java.util.concurrent.ExecutionException;
//import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
//import mx.com.tp.smc.conf.ConexionCMDB;
//import mx.com.tp.smc.conf.ConexionSMC;
import mx.com.tp.smc.conf.Constant;
import mx.com.tp.smc.entity.CatMenu;
//import mx.com.tp.smc.entity.CatArchivos;
import mx.com.tp.smc.entity.CatOrigenHome;
//import mx.com.tp.smc.entity.OrganizacionCMDB;
import mx.com.tp.smc.entity.Organization;
import mx.com.tp.smc.entity.Role;
import mx.com.tp.smc.entity.User;
import mx.com.tp.smc.manager.UserManager;
import mx.com.tp.smc.model.OrganizationModel;
import mx.com.tp.smc.model.OrigenHomeModel;
import mx.com.tp.smc.model.RoleModel;
import mx.com.tp.smc.model.UserModel;
import mx.com.tp.smc.request.RolForOrganization;
import mx.com.tp.smc.request.UserForOrganization;
import mx.com.tp.smc.request.UserRequest;
import mx.com.tp.smc.request.UserRoleUpdate;
import mx.com.tp.smc.request.UserValidation;
import mx.com.tp.smc.request.UserValidationAdd;
import mx.com.tp.smc.request.UserValidationUpdate;
import mx.com.tp.smc.response.OrganizationList;
import mx.com.tp.smc.response.ResponseUser;
import mx.com.tp.smc.response.RoleList;
import mx.com.tp.smc.response.Users;
import mx.com.tp.smc.response.UsersList;
import mx.com.tp.smc.service.TokenService;
import mx.com.tp.smc.service.impl.UserServiceImp;

@Component
public class UserMgr {

	@Autowired
	private TokenService tokenService;
	@Autowired
	private UserServiceImp userService;
	
	//Marcko
	@Autowired
	public UserManager userManager;
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
//	@Autowired
//	@Qualifier("viewJdbcTemplate")
//	private JdbcTemplate viewJdbcTemplate;

	final static Logger logger = Logger.getLogger(UserMgr.class);

//	Marco
	public UserModel getUsers(String username, String organization, String createuser) {
		UserModel model = new UserModel();
		UserRequest request = new UserRequest();
		request.setUsername(username);
		request.setOrganization(organization);
		request.setCreateuser(createuser);
		try {
			UsersList responseManager = userManager.returnAllUsers(request);
			if ((Boolean) responseManager.getSuccess()) {
				System.out.println("Esta entrando el json tiene datos");
				ArrayList<User> tickets = new ArrayList<User>();
				for (Users user : responseManager.getListPoint()) {
					User ticket = new User();
					if (user.getUsername() != null)
						ticket.setUsername((String) user.getUsername());
					if (user.getName() != null)
						ticket.setName((String) user.getName());
					if (user.getOrganization() != null)
						ticket.setOrganization((String)user.getOrganization());
					if(user.getRole() != null) 	
						ticket.setRole((String) user.getRole());
					if(user.getCreateuser() != null)
						ticket.setCreateuser((String)user.getCreateuser());
					if(user.getUsersnum() != null)
						ticket.setUsersnum((String) user.getUsersnum());
					tickets.add(ticket);
				}
				model.setTickets(tickets);
			}else {
				System.out.println("no tiene datos el json");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// logger.info(model);
		return model;
	}
	
//	Versión anterior
//	public UserModel getUsers(String username, String organization, String createuser) {
//		UserModel model = new UserModel();
//		try {
//			JSONObject json = userService.getAllUsers(tokenService.getToken("adri", "adri"), username, organization, createuser);
//			if ((Boolean) json.get("success")) {
//				System.out.println("Esta entrando el json tiene datos");
//				JSONArray array = (JSONArray) json.get("listPoint");
//				ArrayList<User> tickets = new ArrayList<User>();
//				for (int i = 0; i < array.size(); i++) {
//					JSONObject object = (JSONObject) array.get(i);
//					User ticket = new User();
//					if (object.get("username") != null)
//						ticket.setUsername((String) object.get("username"));
//					if (object.get("name") != null)
//						ticket.setName((String) object.get("name"));
//					if (object.get("organization") != null)
//						ticket.setOrganization((String) object.get("organization"));
//					if(object.get("role") != null) 	
//						ticket.setRole((String) object.get("role"));
//					if(object.get("createuser") != null)
//						ticket.setCreateuser((String) object.get("createuser"));
//					if(object.get("usersnum") != null)
//						ticket.setUsersnum((String) object.get("usersnum"));
//					tickets.add(ticket);
//				}
//				model.setTickets(tickets);
//			}else {
//				System.out.println("no tiene datos el json");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		// logger.info(model);
//		return model;
//	}
	
	
//	Marco
	public UserModel getNoUsuarios(String username) {
		System.out.println("<============ VALOR DEL USERNAME ============> " + username);
		UserModel model = new UserModel();
		
		try {
			ArrayList<User> usuario = new ArrayList<User>();
			usuario = (ArrayList<User>) jdbcTemplate.query(Constant.SQL_getNoUsuarios_1, new Object[] { username },
					new RowMapper<User>() {

						@Override
						public User mapRow(ResultSet rs, int rowNum) throws SQLException {
							User usr = new User();
							usr.setUsername(rs.getString("username"));
							usr.setOrganization(rs.getString("organization"));
							usr.setCreateuser(rs.getString("createuser"));
							usr.setUsersnum(rs.getString("usersnum"));
							
							System.out.println("<=========== EL VALOR DE USUARIO ES : ===========>" + usr);
							return usr;
						}});
			
			model.setTickets(usuario);
			
			ArrayList<User> us = new ArrayList<User>();
			us = (ArrayList<User>) jdbcTemplate.query(Constant.SQL_getNoUsuarios_2, new Object[] { username },
					new RowMapper<User>() {

						@Override
						public User mapRow(ResultSet rs, int rowNum) throws SQLException {
							User usrs = new User();
							usrs.setUsername(rs.getString("username"));
							usrs.setOrganization(rs.getString("organization"));
							usrs.setCreateuser(rs.getString("createuser"));
							usrs.setUsersnum(rs.getString("usersnum"));
							
							System.out.println("<=========== USUARIOS REGISTRADOS POR ESTE ADMINISTRADOR : ===========>" + usrs.getUsername());
							return usrs;
						}
				
			});
			
			if(username.equals("care")|| username.equals("care_dos") || username.equals("care_tres")) {
				System.out.println("<=========== EL USUARIO PUEDE REGISTRAR YA QUE ES USUARIO CARE : ===========>");
				usuario.get(0).setUsersnum("C");
				model.setTickets(usuario);
				return model;
			}else {
				
			System.out.println("<=========== USUARIOS REGISTRADOS : ===========>"+ us.size());
			int numero2 = Integer.parseInt(usuario.get(0).getUsersnum());
			int numero = us.size();
			
			
			System.out.println("<=========== USUARIOS REGISTRADOS : ===========>"+ numero);
			System.out.println("<=========== USUARIOS QUE PUEDE RESGISTRAR : ===========>"+ numero2);
		
			if(numero < numero2) {
				System.out.println("<=========== EL USUARIO PUEDE REGISTRAR  : ===========>");
				usuario.get(0).setUsersnum("R");
				model.setTickets(usuario);
				return model;
			}else if (numero == numero2) {
				System.out.println("<=========== EL USUARIO NO PUEDE REGISTRAR  : ===========>");
				usuario.get(0).setUsersnum("N");
				model.setTickets(usuario);
				return model;
			}
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return model;
	}
	
	
//	Versión anterior
//	public UserModel getNoUsuarios(String username) {
//		System.out.println("<============ VALOR DEL USERNAME ============> " + username);
//		UserModel model = new UserModel();
//		
//		try {
//			
//			ConexionSMC.connect();
//			Statement st = ConexionSMC.jdbcConnection.createStatement();
//			ResultSet rs = st.executeQuery("SELECT * FROM USERS WHERE USERNAME='" + username + "'");
//			
//			ArrayList<User> usuario = new ArrayList<User>();
//			ArrayList<User> us = new ArrayList<User>();
//			while(rs.next()) {
//				User usr = new User();
//				usr.setUsername(rs.getString("username"));
//				usr.setOrganization(rs.getString("organization"));
//				usr.setCreateuser(rs.getString("createuser"));
//				usr.setUsersnum(rs.getString("usersnum"));
//				
//				System.out.println("<=========== EL VALOR DE USUARIO ES : ===========>" + usr);
//				usuario.add(usr);
//			}
//			model.setTickets(usuario);
//			
//			
//			ResultSet result = st.executeQuery("SELECT * FROM USERS WHERE CREATEUSER='" + username + "' AND ENABLED =1");
//			while (result.next()) {
//				User usrs = new User();
//				usrs.setUsername(result.getString("username"));
//				usrs.setOrganization(result.getString("organization"));
//				usrs.setCreateuser(result.getString("createuser"));
//				usrs.setUsersnum(result.getString("usersnum"));
//				
//				System.out.println("<=========== USUARIOS REGISTRADOS POR ESTE ADMINISTRADOR : ===========>" + usrs.getUsername());
//				us.add(usrs);
//			}
//			
//			if(username.equals("care")|| username.equals("care_dos") || username.equals("care_tres")) {
//				System.out.println("<=========== EL USUARIO PUEDE REGISTRAR YA QUE ES USUARIO CARE : ===========>");
//				usuario.get(0).setUsersnum("C");
//				model.setTickets(usuario);
//				return model;
//			}else {
//				
//			System.out.println("<=========== USUARIOS REGISTRADOS : ===========>"+ us.size());
//			int numero2 = Integer.parseInt(usuario.get(0).getUsersnum());
//			int numero = us.size();
//			
//			
//			System.out.println("<=========== USUARIOS REGISTRADOS : ===========>"+ numero);
//			System.out.println("<=========== USUARIOS QUE PUEDE RESGISTRAR : ===========>"+ numero2);
//		
//			if(numero < numero2) {
//				System.out.println("<=========== EL USUARIO PUEDE REGISTRAR  : ===========>");
//				usuario.get(0).setUsersnum("R");
//				model.setTickets(usuario);
//				return model;
//			}else if (numero == numero2) {
//				System.out.println("<=========== EL USUARIO NO PUEDE REGISTRAR  : ===========>");
//				usuario.get(0).setUsersnum("N");
//				model.setTickets(usuario);
//				return model;
//			}
//		}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		return model;
//	}
	
//	Marco
	public UserModel validaUsuario(String username) {
		UserModel model = new UserModel();
		try {
			ArrayList<User> user = new ArrayList<User>();
			user = (ArrayList<User>) jdbcTemplate.query(Constant.SQL_validaUsuario, new Object[] { username },
					new RowMapper<User>() {

						@Override
						public User mapRow(ResultSet rs, int rowNum) throws SQLException {
							User us = new User();
							us.setUsername(rs.getString("USERNAME"));
							us.setName(rs.getString("NAME"));
							us.setOrganization(rs.getString("ORGANIZATION"));
							us.setSucces("success");
							us.setMssg("No Se puede Crear el Usuario , El Usuario a registrar ya Existe");
							System.out.println("=== REGISTRO ITERACION === " + us);
							return us;
						}});			
			model.setTickets(user);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
//	Versión anterior
//	public UserModel validaUsuario(String username) {
//		UserModel model = new UserModel();
//		try {
//			ConexionSMC.connect();
//			Statement st = ConexionSMC.jdbcConnection.createStatement();
//			ResultSet registro = st.executeQuery("SELECT * FROM USERS WHERE USERNAME = '" + username + "'");
//			ArrayList<User> user = new ArrayList<User>();
//			while(registro.next()) {
//				User us = new User();
//				us.setUsername(registro.getString("USERNAME"));
//				us.setName(registro.getString("NAME"));
//				us.setOrganization(registro.getString("ORGANIZATION"));
//				us.setSucces("success");
//				us.setMssg("No Se puede Crear el Usuario , El Usuario a registrar ya Existe");
//				user.add(us);
//				System.out.println("=== REGISTRO ITERACION === " + us);
//			}
//			model.setTickets(user);
//			ConexionSMC.disconnect();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}
	
//	Marco
	public UserModel insertUsers(String username, String name, String organization, String password,
			BigDecimal idrole, String createuser, String noUsr) throws ClassNotFoundException {
			
			System.out.println("<============ VALOR DEL NOUSR ============> " + noUsr);
		
			UserModel model = new UserModel();
			UserValidationAdd request = new UserValidationAdd();
			request.setUsername(username);
			request.setName(name);
			request.setOrganization(organization);
			request.setPassword(password);
			request.setIdRol(idrole);
			request.setCreateuser(createuser);
			request.setUsersnum(noUsr);
			
		try {
				ResponseUser responseManager = userManager.insertUser(request);
				if ((Boolean) responseManager.getSuccess()) {
					ArrayList<User> tickets = new ArrayList<User>();
					User ticket = new User();
					ticket.setMssg((String) responseManager.getMssg());
					ticket.setSucces(String.valueOf(responseManager.getSuccess()));
					tickets.add(ticket);
					model.setTickets(tickets);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
//	Versión anterior
//	public UserModel insertUsers(String username, String name, String organization, String password,
//			BigDecimal idrole, String createuser, String noUsr) throws ClassNotFoundException {
//			
//			System.out.println("<============ VALOR DEL NOUSR ============> " + noUsr);
//		
//			UserModel model = new UserModel();
//		try {
//
//				JSONObject json = userService.insertUsers(tokenService.getToken("adri", "adri"), username, name,
//						organization, password, idrole, createuser, noUsr);
//				if ((Boolean) json.get("success")) {
//					ArrayList<User> tickets = new ArrayList<User>();
//					User ticket = new User();
//					ticket.setMssg((String) json.get("mssg"));
//					ticket.setSucces((String) json.get("success").toString());
//					tickets.add(ticket);
//					model.setTickets(tickets);
//				}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}

//	Marco
	public UserModel updateUsers(String username, String name, String organization, String password) {
		UserModel model = new UserModel();
		UserValidationUpdate request = new  UserValidationUpdate();
		request.setUsername(username);
		request.setName(name);
		request.setOrganization(organization);
		request.setPassword(password);
		try {
			ResponseUser responseManager = userManager.updateUser(request);
			if ((Boolean) responseManager.getSuccess()) {
				ArrayList<User> tickets = new ArrayList<User>();
				User ticket = new User();
				ticket.setMssg((String) responseManager.getMssg());
				ticket.setSucces(String.valueOf(responseManager.getSuccess()));
				tickets.add(ticket);
				model.setTickets(tickets);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
//Vesrión anterior
//	public UserModel updateUsers(String username, String name, String organization, String password) {
//		UserModel model = new UserModel();
//		try {
//			JSONObject json = userService.updateUsers(tokenService.getToken("adri", "adri"), username, name,
//					organization, password);
//			if ((Boolean) json.get("success")) {
//				ArrayList<User> tickets = new ArrayList<User>();
//				User ticket = new User();
//				ticket.setMssg((String) json.get("mssg"));
//				ticket.setSucces((String) json.get("success").toString());
//				tickets.add(ticket);
//				model.setTickets(tickets);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}

//	Marco
	public UserModel deleteUsers(String username) {
		UserModel model = new UserModel();
		UserValidation request =  new UserValidation();
		request.setUsername(username);
		try {
			ResponseUser responseManager = userManager.deleteUser(request);
			if ((Boolean) responseManager.getSuccess()) {
				ArrayList<User> tickets = new ArrayList<User>();
				User ticket = new User();
				ticket.setMssg((String) responseManager.getMssg());
				ticket.setSucces(String.valueOf(responseManager.getSuccess()));
				tickets.add(ticket);
				model.setTickets(tickets);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
//	Versión anterior
//	public UserModel deleteUsers(String username) {
//		UserModel model = new UserModel();
//		try {
//			JSONObject json = userService.deleteUsers(tokenService.getToken("adri", "adri"), username);
//			if ((Boolean) json.get("success")) {
//				ArrayList<User> tickets = new ArrayList<User>();
//				User ticket = new User();
//				ticket.setMssg((String) json.get("mssg"));
//				ticket.setSucces((String) json.get("success").toString());
//				tickets.add(ticket);
//				model.setTickets(tickets);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}


//	public UserModel loginUsers(String username, String password) {
//		UserModel model = new UserModel();
//		try {
//			JSONObject json = userService.loginUsers(tokenService.getToken("adri", "adri"), username, password);
//			if ((Boolean) json.get("success")) {
//				ArrayList<User> tickets = new ArrayList<User>();
//				User ticket = new User();
//				ticket.setMssg((String) json.get("mssg"));
//				ticket.setSucces((String) json.get("success").toString());
//				tickets.add(ticket);
//				model.setTickets(tickets);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}
	
	

	
// Marco
	public RoleModel getRolesByOrganizacion(String organization) {
		RoleModel model = new RoleModel();
		RolForOrganization request = new RolForOrganization();
		request.setOrganization(organization);
		try {
			RoleList responseManager = userManager.getRolesByOrganization(request);
			if ((Boolean) responseManager.getSuccess()) {

				ArrayList<Role> tickets = new ArrayList<Role>();
				for (mx.com.tp.smc.response.Role role : responseManager.getListRole()) {

					Role ticket = new Role();
					if (role.getIdRole() != null)
						ticket.setIdRole(role.getIdRole().longValue());
					if (role.getRolRole() != null)
						ticket.setRolRole((String) role.getRolRole());
					if (role.getRolDescription() != null)
						ticket.setRolDescription((String) role.getRolDescription());

					tickets.add(ticket);
				}
				model.setRoles(tickets);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
//	Versión anterior
//	public RoleModel getRolesByOrganizacion(String organization) {
//		RoleModel model = new RoleModel();
//		try {
//
//			JSONObject json = userService.getRolesByOrganizacion(tokenService.getToken("adri", "adri"), organization);
//			if ((Boolean) json.get("success")) {
//				JSONArray array = (JSONArray) json.get("listRole");
//
//				ArrayList<Role> tickets = new ArrayList<Role>();
//				for (int i = 0; i < array.size(); i++) {
//					JSONObject object = (JSONObject) array.get(i);
//					Role ticket = new Role();
//					if (object.get("idRole") != null)
//						ticket.setIdRole((Long) object.get("idRole"));
//					if (object.get("rolRole") != null)
//						ticket.setRolRole((String) object.get("rolRole"));
//					if (object.get("rolDescription") != null)
//						ticket.setRolDescription((String) object.get("rolDescription"));
//
//					tickets.add(ticket);
//				}
//				model.setRoles(tickets);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}

//	Marcko
	public UserModel updateRoleUser(String username, BigDecimal idrol) {
		UserModel model = new UserModel();
		UserRoleUpdate request = new UserRoleUpdate();
		request.setUsername(username);
		request.setIdRol(idrol);
		try {
			ResponseUser responseManager = userManager.updateRoleUser(request);
			if ((Boolean) responseManager.getSuccess()) {
				ArrayList<User> tickets = new ArrayList<User>();
				User ticket = new User();
				ticket.setMssg((String) responseManager.getMssg());
				ticket.setSucces(String.valueOf(responseManager.getSuccess()));
				tickets.add(ticket);
				model.setTickets(tickets);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

//Versión anterior	
//	public UserModel updateRoleUser(String username, BigDecimal idrol) {
//		UserModel model = new UserModel();
//		try {
//			JSONObject json = userService.updateRoleUser(tokenService.getToken("adri", "adri"), username, idrol);
//			if ((Boolean) json.get("success")) {
//				ArrayList<User> tickets = new ArrayList<User>();
//				User ticket = new User();
//				ticket.setMssg((String) json.get("mssg"));
//				ticket.setSucces((String) json.get("success").toString());
//				tickets.add(ticket);
//				model.setTickets(tickets);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}
	
//	Marco
	public OrganizationModel getOrganizations() {
		OrganizationModel model = new OrganizationModel();
		System.out.println("holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa----1");

		try {
			System.out.println("holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa----2");
			ArrayList<Organization> tickets = new ArrayList<Organization>();
			tickets = (ArrayList<Organization>) jdbcTemplate.query(Constant.SQL_getOrganizations, new Object[] {},
					new RowMapper<Organization>() {

						@Override
						public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
							Organization org = new Organization();
							System.out.println("holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa----3");
							org.setOrganizationId(rs.getString("organization_uuid"));
							org.setOrganizationName(rs.getString("org_name"));
							return org;
						}});
			model.setTickets(tickets);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	
//	Versión anterior 
//	public OrganizationModel getOrganizations() {
//		OrganizationModel model = new OrganizationModel();
//		try {
//			ConexionCMDB.connect();
//			Statement st = ConexionCMDB.jdbcConnection.createStatement();  
//			//ResultSet registro = st.executeQuery("SELECT cliente_id,resource_name FROM datahub.cmdb_cat_clientes_vw A ORDER BY A.resource_name");
//			ResultSet registro = st.executeQuery("SELECT organization_uuid, org_name FROM datahub.sdm_ca_organization where inactive = '0' order by org_name");
//			ArrayList<Organization> tickets = new ArrayList<Organization>();
//			
//			while(registro.next()) {
//				Organization org = new Organization();
//				org.setOrganizationId(registro.getString("organization_uuid"));
//				org.setOrganizationName(registro.getString("org_name"));
//				tickets.add(org);
//			}
//			model.setTickets(tickets);
//			ConexionCMDB.disconnect();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}
	
//	Marco
	public OrganizationModel getOrganizationByUser(String username) {
		OrganizationModel model = new OrganizationModel();
		UserForOrganization request = new UserForOrganization();
		request.setUsername(username);
		try {
			Properties p = new Properties();
			InputStream input = null;
			input = getClass().getClassLoader().getResourceAsStream("application.properties");
			p.load(input);

			String uri = p.getProperty("ipGrafana");
			OrganizationList responseManager = userManager.returnOrganizationByUser(request);

			if ((Boolean) responseManager.getSuccess()) {
				ArrayList<Organization> tickets = new ArrayList<Organization>();
				for (mx.com.tp.smc.response.Organization org : responseManager.getListOrganization()) {
					Organization ticket = new Organization();

					if (org.getOrganizationId() != null) {
						ticket.setOrganizationId((String) org.getOrganizationId());
					}

					if (org.getOrganizationName() != null) {
						ticket.setOrganizationName((String) org.getOrganizationName());
					}

					ticket.setIpPuertoGrafana(uri);
					tickets.add(ticket);
				}
				model.setTickets(tickets);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
//	Versión anterior
//	public OrganizationModel getOrganizationByUser(String username) {
//		OrganizationModel model = new OrganizationModel();
//		try {
//			Properties p = new Properties();
//			InputStream input = null;
//			input = getClass().getClassLoader().getResourceAsStream("application.properties");
//			p.load(input);
//
//			String uri = p.getProperty("ipGrafana");
//
//			JSONObject json = userService.getOrganizationByUser(tokenService.getToken("adri", "adri"), username);
//
//			if ((Boolean) json.get("success")) {
//				JSONArray array = (JSONArray) json.get("listOrganization");
//				ArrayList<Organization> tickets = new ArrayList<Organization>();
//				for (int i = 0; i < array.size(); i++) {
//					JSONObject object = (JSONObject) array.get(i);
//					Organization ticket = new Organization();
//
//					if (object.get("organizationId") != null) {
//						ticket.setOrganizationId((String) object.get("organizationId"));
//					}
//
//					if (object.get("organizationName") != null) {
//						ticket.setOrganizationName((String) object.get("organizationName"));
//					}
//
//					ticket.setIpPuertoGrafana(uri);
//					tickets.add(ticket);
//				}
//				model.setTickets(tickets);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}

//	Marco
	public OrganizationModel getOrganizationByUserCMDB(String username) {

		OrganizationModel model = new OrganizationModel();
		try {
			ArrayList<Organization> tickets = new ArrayList<Organization>();
			tickets = (ArrayList<Organization>) jdbcTemplate.query(Constant.SQL_getOrganizationByUserCMDB, new Object[] {username},
					new RowMapper<Organization>() {

						@Override
						public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
							Organization org= new Organization();
							String cadena = rs.getString("NOMBRE_ORGANIZACION");
							
							String resultado = cadena.replaceAll("&", "---amp---");
							System.out.println("== VALOR DEL REPLACE == " + resultado);
							org.setOrganizationName(resultado);
							org.setOrganizationId(rs.getString("ORGANIZACION_ID"));		
							
							return org;
						}});
			
			model.setTickets(tickets);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
//	Versión anterior
//	public OrganizationModel getOrganizationByUserCMDB(String username) {
//
//		OrganizationModel model = new OrganizationModel();
//		try {
//			ConexionSMC.connect();
//			
//			Statement comando = ConexionSMC.jdbcConnection.createStatement();
//			ResultSet rs = comando.executeQuery(" SELECT * FROM USERS INNER JOIN CAT_ORGANIZACION ON ORGANIZATION = ORGANIZACION_ID WHERE USERNAME ='" + username + "'");
//			ArrayList<Organization> tickets = new ArrayList<Organization>();
//			
//			while(rs.next()) {
//				
//				Organization org= new Organization();
//				String cadena = rs.getString("NOMBRE_ORGANIZACION");
//				
//				String resultado = cadena.replaceAll("&", "---amp---");
//				System.out.println("== VALOR DEL REPLACE == " + resultado);
//				org.setOrganizationName(resultado);
//				org.setOrganizationId(rs.getString("ORGANIZACION_ID"));			
//				tickets.add(org);
//			}
//			
//			model.setTickets(tickets);
//			ConexionSMC.disconnect();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}
//	
	
	//Marco 
	public UserModel getRolByUser(String username) {
		UserModel model = new UserModel();
		UserValidation request = new UserValidation();
		request.setUsername(username);
		System.out.println("Username---->"+ username);
		String us=""; 
		try {

			System.out.println("getUsername---->"+ request.getUsername());
			ResponseUser responseManager = userManager.getRolByUser(request);
			
			if (responseManager.getUser() != null) {
				us = responseManager.getUser().getRole();
				ArrayList<User> users = new ArrayList<User>();
				User user = new User();

				user.setRole(us);

				users.add(user);
				model.setTickets(users);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	
//Versión anterior	
//	public UserModel getRolByUser(String username) {
//		UserModel model = new UserModel();
//
//		try {
//			JSONObject response = userService.getRolByUser(tokenService.getToken("adri", "adri"), username);
//
//			if ((Boolean) response.get("success")) {
//
//				JSONObject jsonObject = (JSONObject) response.get("user");
//
//				ArrayList<User> users = new ArrayList<User>();
//				User user = new User();
//
//				user.setRole((String) jsonObject.get("role"));
//
//				users.add(user);
//				model.setTickets(users);
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}

	
//	Marco
	public OrigenHomeModel getOrigenHome(String usuario) throws SQLException, ClassNotFoundException {
		OrigenHomeModel model = new OrigenHomeModel();
		try {
			ArrayList<CatOrigenHome> org = new ArrayList<CatOrigenHome>();

			org = (ArrayList<CatOrigenHome>) jdbcTemplate.query(Constant.SQL_getOrigenHome, new Object[] { usuario },
					new RowMapper<CatOrigenHome>() {

						@Override
						public CatOrigenHome mapRow(ResultSet rs, int rowNum) throws SQLException {
							CatOrigenHome origen = new CatOrigenHome();
							origen.setIdHome(rs.getLong("id_Home"));
							origen.setOrigenHome(rs.getString("origen_Home"));
							origen.setUrlHome(rs.getString("url_Home"));
							origen.setUrlDetalle(rs.getString("url_detalle"));
							origen.setDescripcion(rs.getString("descripcion"));
							origen.setUrlUno(rs.getString("url_uno"));
							origen.setUrlDos(rs.getString("url_dos"));
							origen.setUrlTre(rs.getString("url_tre"));
							origen.setOrganizacionNombre(rs.getString("organizacion_name"));
							origen.setLatencia(rs.getFloat("latencia"));
							origen.setPaquetes(rs.getFloat("paquetes"));
							origen.setDisponibilidad(rs.getFloat("disponibilidad"));
							System.out.println("ORIGEN HOME - " + origen.getUrlHome());
							return origen;
						}});
					
			model.setOrigenHome(org);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
}

//Versión anterior
//public OrigenHomeModel getOrigenHome(String usuario) throws SQLException, ClassNotFoundException {
//	OrigenHomeModel model = new OrigenHomeModel();
//	try {
//		ConexionSMC.connect();
//		Statement comando = ConexionSMC.jdbcConnection.createStatement();
//		ResultSet rs = comando.executeQuery("SELECT CAT_ORIGEN_HOME.ID_HOME, " + 
//				" CAT_ORIGEN_HOME.ORIGEN_HOME, " + 
//				" CAT_ORIGEN_HOME.URL_HOME," + 
//				" CAT_ORIGEN_HOME.URL_DETALLE, " + 
//				" CAT_ORIGEN_HOME.DESCRIPCION, " + 
//				" CAT_ORIGEN_HOME.URL_UNO," + 
//				" CAT_ORIGEN_HOME.URL_DOS, " + 
//				" CAT_ORIGEN_HOME.URL_TRE," + 
//				" CAT_SLA.ORGANIZACION_NAME," + 
//				" CAT_SLA.LATENCIA," + 
//				" CAT_SLA.PAQUETES," + 
//				" CAT_SLA.DISPONIBILIDAD" + 
//				" FROM TROL  " + 
//				" inner join TOPERATOR_ROL   on TROL.IDROL = TOPERATOR_ROL.IDROL " + 
//				" inner join USERS           on TOPERATOR_ROL.USERNAME = USERS.USERNAME" + 
//				" inner join CAT_SLA		 on CAT_SLA.ORGANIZACION_NAME = TROL.NOMBRE_ORGANIZACION " + 
//				" inner join TB_MENU_ROL     on TB_MENU_ROL.IDROL = TROL.IDROL " + 
//				" inner join CAT_MENUS       on CAT_MENUS.ID_MENU = TB_MENU_ROL.ID_MENU " + 
//				" inner join CAT_ORIGEN_HOME on CAT_ORIGEN_HOME.ID_HOME = TB_MENU_ROL.ID_HOME " + 
//				" WHERE USERS.USERNAME ='" + usuario + "' limit 1");
//		
//		
//		
//
//		ArrayList<CatOrigenHome> org = new ArrayList<CatOrigenHome>();
//
//		while (rs.next()) {
//			CatOrigenHome origen = new CatOrigenHome();
//			origen.setIdHome(rs.getLong("id_Home"));
//			origen.setOrigenHome(rs.getString("origen_Home"));
//			origen.setUrlHome(rs.getString("url_Home"));
//			origen.setUrlDetalle(rs.getString("url_detalle"));
//			origen.setDescripcion(rs.getString("descripcion"));
//			origen.setUrlUno(rs.getString("url_uno"));
//			origen.setUrlDos(rs.getString("url_dos"));
//			origen.setUrlTre(rs.getString("url_tre"));
//			origen.setOrganizacionNombre(rs.getString("organizacion_name"));
//			origen.setLatencia(rs.getFloat("latencia"));
//			origen.setPaquetes(rs.getFloat("paquetes"));
//			origen.setDisponibilidad(rs.getFloat("disponibilidad"));
//			System.out.println("ORIGEN HOME - " + origen.getUrlHome());
//			org.add(origen);
//
//		}
//		model.setOrigenHome(org);
//		ConexionSMC.disconnect();
//	} catch (SQLException e) {
//		e.printStackTrace();
//	}
//	return model;
//}

