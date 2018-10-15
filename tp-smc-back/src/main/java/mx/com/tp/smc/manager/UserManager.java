package mx.com.tp.smc.manager;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import mx.com.tp.smc.entity.CAOrganizationEntity;
import mx.com.tp.smc.entity.CorganizacionEntity;
import mx.com.tp.smc.entity.ToperatorRolEntity;
import mx.com.tp.smc.entity.TrolEntity;
import mx.com.tp.smc.entity.UserEntity;
import mx.com.tp.smc.request.OrganizationRequest;
import mx.com.tp.smc.request.RolForOrganization;
import mx.com.tp.smc.request.UserForOrganization;
import mx.com.tp.smc.request.UserLogin;
import mx.com.tp.smc.request.UserRequest;
import mx.com.tp.smc.request.UserRoleUpdate;
import mx.com.tp.smc.request.UserValidation;
import mx.com.tp.smc.request.UserValidationAdd;
import mx.com.tp.smc.request.UserValidationUpdate;
import mx.com.tp.smc.response.Organization;
import mx.com.tp.smc.response.OrganizationList;
import mx.com.tp.smc.response.ResponseUser;
import mx.com.tp.smc.response.Role;
import mx.com.tp.smc.response.RoleList;
import mx.com.tp.smc.response.Users;
import mx.com.tp.smc.response.UsersList;
import mx.com.tp.smc.service.CAOrganizationService;
import mx.com.tp.smc.service.RolServiceBack;
import mx.com.tp.smc.service.ToperatorRolService;
import mx.com.tp.smc.service.TrolService;
import mx.com.tp.smc.service.UserServiceBack;
import mx.com.tp.smc.service.impl.RolServiceBackImpl;

@Component
public class UserManager {
	final static Logger log = Logger.getLogger(UserManager.class);

	@Autowired
	private UserServiceBack userService;

	@Autowired
	private TrolService roleService;

	@Autowired
	private ToperatorRolService toperatorroleService;

	@Autowired
	private CAOrganizationService caOrganizationService;

	@Autowired
	private RolServiceBackImpl rolServiceBackimpl;

	public ResponseUser insertUser(UserValidationAdd request) {
		DateTime initial = new DateTime();
		JSONArray array = null;
		boolean success = false;
		String error = "";
		String mssg = "Error al insertar los datos";
		int total = 0;

//		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		request.setPassword(passwordEncoder.encode(request.getPassword()));
		
		System.out.println("<===== EL VALOR DEL REQUEST ======>" + request.getUsersnum());
		
		try {
			UserEntity ue = new UserEntity();
			TrolEntity te = roleService.getRole(request.getIdRol());
			ToperatorRolEntity to = new ToperatorRolEntity();
			ue.setUserName(request.getUsername());
			ue.setName(request.getName());
			ue.setRole(te.getRolRole());
			ue.setEnabled(new BigDecimal("1"));
			ue.setPassword(request.getPassword());
			ue.setOrganization(request.getOrganization());
			ue.setCreateuser(request.getCreateuser());
			ue.setUsernum(request.getUsersnum());
			to.setUserName(request.getUsername());
			to.setIdRol(request.getIdRol());
			userService.saveUser(ue);
			toperatorroleService.saveToperatorRol(to);
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

	public ResponseUser deleteUser(UserValidation request) {
		DateTime initial = new DateTime();
		JSONArray array = null;
		boolean success = false;
		String error = "";
		String mssg = "Error al eliminar los datos";
		int total = 0;

		try {
			UserEntity ue = userService.getUser(request.getUsername());
			ue.setEnabled(new BigDecimal("0"));
			userService.updateUser(ue);
			mssg = "Usuario Eliminado";
			success = true;
			total++;
		} catch (Exception ex) {

			mssg = "Error al Eliminar los datos";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}

		Period period = new Period(initial, new DateTime());
		return new ResponseUser(success, period, mssg, error, total, "", array);

	}

	public ResponseUser returnUser(UserValidationUpdate request) {
		DateTime initial = new DateTime();
		boolean success = false;
		String error = "";
		String mssg = "Error al insertar los datos";
		int total = 0;
		Users obj = new Users();

		try {
			UserEntity ue = userService.getUser(request.getUsername());
			obj.setName(ue.getName());
			obj.setOrganization(ue.getOrganization());
			obj.setUsername(ue.getUserName());
			obj.setRole(ue.getRole());
			mssg = "Exito al insertar los datos";
			success = true;
			total++;
		} catch (Exception ex) {

			mssg = "Error al Agregar los datos";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}

		Period period = new Period(initial, new DateTime());
		return new ResponseUser(success, period, mssg, error, total, obj);

	}

	public ResponseUser updateUser(UserValidationUpdate request) {
		DateTime initial = new DateTime();
		JSONArray array = null;
		boolean success = false;
		String error = "";
		String mssg = "Error al insertar los datos";
		int total = 0;

//		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		request.setPassword(passwordEncoder.encode(request.getPassword()));

		try {
			UserEntity ue = userService.getUser(request.getUsername());
			ue.setName(request.getName());
			ue.setPassword(request.getPassword());
			ue.setOrganization(request.getOrganization());
			userService.updateUser(ue);
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

	public UsersList returnAllUsers(UserRequest request) {

		DateTime initial = new DateTime();
		boolean success = false;
		String error = "";
		String mssg = "Error al extraer los datos";
		int total = 0;
		List<Users> listUsers = new ArrayList<Users>();
		String organizationName;

		try {
			Properties p = new Properties();
			InputStream input = null;
			input = getClass().getClassLoader().getResourceAsStream("application.properties");
			p.load(input);
			String itsm = p.getProperty("getITSM");
			String care = p.getProperty("getCARE");
			if (request.getOrganization().equals(itsm) || request.getOrganization().equals(care)) {
				List<UserEntity> usuariosList = userService.getAllUser();
				List<CorganizacionEntity> organizationList = rolServiceBackimpl.getAllOrganization();
				for (UserEntity usuario : usuariosList) {
					organizationName = "";
					if (usuario.getUserName().equals("adri") || usuario.getUserName().equals(request.getUsername())) {
						// usuario adri y el logueado no se muestran en la vista
					} else {
						if ((usuario.getEnabled().intValue()) == 1) {

							for (CorganizacionEntity organization : organizationList) {

								if (usuario.getOrganization().equals(organization.getOrganizacionId())) {
									organizationName = organization.getNombreOrganizacion();
									break;
								}

							}
							Users obj = new Users();
							obj.setName(usuario.getName());
							obj.setOrganization(organizationName);
							obj.setUsername(usuario.getUserName());
							obj.setRole(usuario.getRole());
							obj.setCreateuser(usuario.getCreateuser());
							obj.setUsersnum(usuario.getUsernum());
							listUsers.add(obj);
							mssg = "Exito al conseguir los usuarios 1";
							success = true;
							total = usuariosList.size();
							
							System.out.println("USUARIOS"+ listUsers);
						}
					}
				}
			} else {
				/* Sa cambia la forma de obtener los usuarios. 
				 * Cambio por incidente - "Usuario administrador no pueda ver usuarios que no son de el"
				 * */
				List<UserEntity> usuariosFilterList = userService.getAllUserFilter(request.getCreateuser());
				List<CorganizacionEntity> organizationAllList = rolServiceBackimpl.getAllOrganization();
				for (UserEntity usuario : usuariosFilterList) {
					organizationName = "";
					
					if (usuario.getUserName().equals("adri") || usuario.getUserName().equals(request.getUsername())) {
						// usuario adri y el logueado no se muestran en la vista
					} else {
						if ((usuario.getEnabled().intValue()) == 1) {
							
							for (CorganizacionEntity organization : organizationAllList) {
								
								if (usuario.getOrganization().equals(organization.getOrganizacionId())) {
										organizationName = organization.getNombreOrganizacion();
										break;
									}
							}
							Users obj = new Users();
								obj.setName(usuario.getName());
								obj.setOrganization(organizationName);
								obj.setUsername(usuario.getUserName());
								obj.setRole(usuario.getRole());
								obj.setCreateuser(usuario.getCreateuser());
								obj.setUsersnum(usuario.getUsernum());
							listUsers.add(obj);
							mssg = "Exito al conseguir los usuarios - KOSE";
							success = true;
							total = usuariosFilterList.size();
							System.out.println("USUARIOS"+ listUsers);
						}
					}
					
				}
			}

		} catch (Exception ex) {
			mssg = "Error al Agregar los datos";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}

		Period period = new Period(initial, new DateTime());
		return new UsersList(success, period, error, mssg, total, listUsers);
	}

//	public ResponseUser loginUser(UserLogin request) {
//		DateTime initial = new DateTime();
//		JSONArray array = null;
//		boolean success = false;
//		String error = "";
//		String mssg = "Error al encontrar los datos";
//		int total = 0;
//		Users obj = new Users();
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		try {
//			UserEntity ue = userService.getUser(request.getUsername());
//			if (encoder.matches(request.getPassword(), ue.getPassword())) {
//				obj.setName(ue.getName());
//				obj.setRole(ue.getRole());
//				obj.setUsername(ue.getUserName());
//				mssg = "Exito al encontrar los datos";
//				success = true;
//				total++;
//
//			} else {
//				mssg = "Error al encontrar los datos";
//
//			}
//
//		} catch (Exception ex) {
//
//			mssg = "Error al encontrar los datos";
//			error = "Error:" + ex;
//			log.error("Error:" + ex);
//		}
//
//		Period period = new Period(initial, new DateTime());
//		return new ResponseUser(success, period, mssg, error, total, obj);
//
//	}

	public RoleList getRolesByOrganization(RolForOrganization request) {
		DateTime initial = new DateTime();
		boolean success = false;
		String error = "";
		String mssg = "Error al extraer los datos";
		int total = 0;
		List<Role> listRole = new ArrayList<Role>();

		try {

			Properties p = new Properties();
			InputStream input = null;

			input = getClass().getClassLoader().getResourceAsStream("application.properties");
			p.load(input);

			String itsm = p.getProperty("getITSM");

			String care = p.getProperty("getCARE");

			if (request.getOrganization().equals(itsm) || request.getOrganization().equals(care)) {

				List<TrolEntity> allRoleList = roleService.getAllRole();

				for (TrolEntity role : allRoleList) {
					Role obj = new Role();
					obj.setIdRole(role.getIdRol());
					obj.setRolRole(role.getRolRole());
					obj.setRolDescription(role.getRolDescription());
					obj.setOrganizacion(role.getOrganizacion());
					listRole.add(obj);
					mssg = "Exito al conseguir los roles";
					success = true;
					total = allRoleList.size();
				}
			} else {

				List<TrolEntity> roleList = roleService.getRoleByOrganizacion(request.getOrganization());

				for (TrolEntity role : roleList) {
					Role obj = new Role();
					obj.setIdRole(role.getIdRol());
					obj.setRolRole(role.getRolRole());
					obj.setRolDescription(role.getRolDescription());
					obj.setOrganizacion(role.getOrganizacion());
					listRole.add(obj);
					mssg = "Exito al conseguir los roles";
					success = true;
					total = roleList.size();
				}

			}

		} catch (Exception ex) {
			mssg = "Error al Agregar los datos";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}

		Period period = new Period(initial, new DateTime());
		return new RoleList(success, period, error, mssg, total, listRole);
	}

	public ResponseUser updateRoleUser(UserRoleUpdate request) {
		DateTime initial = new DateTime();
		JSONArray array = null;
		boolean success = false;
		String error = "";
		String mssg = "Error al insertar los datos";
		int total = 0;

		try {
			UserEntity ue = userService.getUser(request.getUsername());
			TrolEntity te = roleService.getRole(request.getIdRol());
			ToperatorRolEntity to = toperatorroleService.getOperatorRole(request.getUsername());
			ue.setRole(te.getRolRole());
			to.setIdRol(request.getIdRol());
			userService.updateUser(ue);
			toperatorroleService.saveToperatorRol(to);

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

	public OrganizationList returnOrganization(OrganizationRequest request) {

		System.out.println("******returnOrganization******");

		DateTime initial = new DateTime();
		boolean success = false;
		String error = "";
		String mssg = "Error al extraer los datos";
		int total = 0;
		List<Organization> listOrganization = new ArrayList<Organization>();

		try {

			Properties p = new Properties();
			InputStream input = null;

			input = getClass().getClassLoader().getResourceAsStream("application.properties");
			p.load(input);

			String itsm = p.getProperty("getITSM");

			String care = p.getProperty("getCARE");

			System.out.println("******valor de request****** " + request.getOrganization());

			if (request.getOrganization().equals(itsm) || request.getOrganization().equals(care)) {

				System.out.println("******es igual a CARE o ITSM****** ");

				List<CAOrganizationEntity> allOrganizationList = caOrganizationService.getAllOrganization();
				for (CAOrganizationEntity organization : allOrganizationList) {

					if (organization.getInactive() == 0) {

						Organization obj = new Organization();
						obj.setOrganizationId(organization.getOrganizationId());
						obj.setOrganizationName(organization.getOrgName());
						listOrganization.add(obj);
						mssg = "Exito al conseguir todas las organizaciones";
						success = true;
						total = allOrganizationList.size();
					}
				}

			} else {

				System.out.println("******es otra organizacion que no es CARE o ITSM****** ");

				List<CAOrganizationEntity> organizationList = caOrganizationService
						.getOrganization(request.getOrganization());
				for (CAOrganizationEntity organization : organizationList) {

					if (organization.getInactive() == 0) {

						Organization obj = new Organization();
						obj.setOrganizationId(organization.getOrganizationId());
						obj.setOrganizationName(organization.getOrgName());
						listOrganization.add(obj);
						mssg = "Exito al conseguir los datos por organizacion";
						success = true;
						total = organizationList.size();
					}
				}

			}

		} catch (Exception ex) {
			mssg = "Error al extraer los datos";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}

		Period period = new Period(initial, new DateTime());
		return new OrganizationList(success, period, error, mssg, total, listOrganization);

	}

	public OrganizationList returnOrganizationByUser(UserForOrganization request) {
		System.out.println("Valor Username-->"+request.getUsername());
		DateTime initial = new DateTime();
		boolean success = false;
		String error = "";
		String mssg = "Error al insertar los datos";
		int total = 0;
		List<Organization> listOrganization = new ArrayList<Organization>();
		try {
			UserEntity ue = userService.getUser(request.getUsername());
			// List<CAOrganizationEntity> organizationList =
			// caOrganizationService.getAllOrganization();
			System.out.println("getUserName----> " + ue.getUserName());
			List<CorganizacionEntity> organizationList = rolServiceBackimpl.getAllOrganization();

			for (CorganizacionEntity organization : organizationList) {
				if (ue.getOrganization().equals(organization.getOrganizacionId())) {
					Organization obj = new Organization();
					obj.setOrganizationId(organization.getOrganizacionId());
					obj.setOrganizationName(organization.getNombreOrganizacion());
					System.out.println("getNombreOrganizacion----->"+ organization.getNombreOrganizacion());
					listOrganization.add(obj);
					System.out.println("=== VALOR DE LA LISTA ===" + listOrganization.get(0).getOrganizationName());
					break;
				}

			}

			userService.updateUser(ue);
			mssg = "Exito al insertar los datos";
			success = true;
			total++;
		} catch (Exception ex) {

			mssg = "Error al Agregar los datos";
			error = "Error:" + ex;
			log.error("Error:" + mssg);
		}

		Period period = new Period(initial, new DateTime());
		return new OrganizationList(success, period, error, mssg, total, listOrganization);

	}

	public ResponseUser getRolByUser(UserValidation request) {
		DateTime initial = new DateTime();

		boolean success = false;
		String error = "";
		String mssg = "Error al extraer datos de usuario";
		int total = 0;

		Users obj = new Users();

		try {

			UserEntity ue = userService.getUser(request.getUsername());

			obj.setName(ue.getName());
			obj.setOrganization(ue.getOrganization());
			obj.setUsername(ue.getUserName());
			obj.setRole(ue.getRole());

			mssg = "Exito extraer datos de usuario";
			success = true;
			total++;
		} catch (Exception ex) {

			mssg = "Error al extraer datos de usuario";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}

		Period period = new Period(initial, new DateTime());
		return new ResponseUser(success, period, mssg, error, total, obj);

	}

}