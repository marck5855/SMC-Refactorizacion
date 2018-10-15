package mx.com.tp.smc.conf;

public final class Constant {
	
	
	
//	FileMgr
//	B.D portalcl
	public static final String SQL_guardarFile_query = "INSERT INTO TARCHIVOS "
			 + "(TARCHIVOS.IDROL, TARCHIVOS.USERNAME, TARCHIVOS.ARCHIVO_NOMBRE, "
			 + "TARCHIVOS.ARCHIVO_MES, TARCHIVOS.ARCHIVO_ANIO, TARCHIVOS.ARCHIVO_FECHACARGA, "
			 + "TARCHIVOS.ARCHIVO_FECHAACTUALIZACION, TARCHIVOS.ARCHIVO_ARCHIVO, TARCHIVOS.ORGANIZACION, TARCHIVOS.ID_CARPETA) "
			 + "VALUES(?, ?, ?, ?, ?, now(), now(), ?, ?, ?) ";
	
	public static final String SQL_getFileExist_query = "SELECT COUNT(A.ARCHIVO_NOMBRE) AS NUMARCHIVOS "
			  + "FROM	TARCHIVOS A "
			  + "WHERE	A.USERNAME=? "
			  + "AND 	A.ORGANIZACION=? "
			  + "AND 	A.ARCHIVO_NOMBRE=?" ;
	public static final String SQL_guardarRuta_qCarpeta = "INSERT INTO CAT_CARPETAS	"
			+ "(NOMBRE, PATH, NIVEL, ORGANIZACION) "
			+ "VALUES "
			+ "(?,?,?,?)";
	
	public static final String SQL_guardarRuta_query = "SELECT  C.ID_CARPETA "
		  	 + "FROM	CAT_CARPETAS C "
		  	 + "WHERE	C.NOMBRE=? "
		  	 + "AND 	C.PATH=? "
		  	 + "AND 	C.NIVEL=? "
		  	 + "AND 	C.ORGANIZACION=? " ;
	
	public static final String SQL_getListaRutas_qCarpetas = "SELECT DISTINCT(C.PATH), C.NIVEL   "
			 + "FROM TARCHIVOS T "
			 + "JOIN CAT_CARPETAS C ON T.ID_CARPETA = C.ID_CARPETA "
			 + "WHERE T.ORGANIZACION = ? "
			 + "ORDER BY C.PATH ASC ";
	
	public static final String SQL_getExisteRuta_qexist = "SELECT COUNT(T.PATH) RUTA "
			 + "FROM CAT_CARPETAS T "
			 + "WHERE T.PATH = ? "
			 + "AND T.ORGANIZACION = ? ";
	
	public static final String SQL_getExisteRuta_qCarpetas = "SELECT COUNT(DISTINCT(C.PATH)) AS NOCUENTAS  "
			 + "FROM TARCHIVOS T "
			 + "JOIN CAT_CARPETAS C ON T.ID_CARPETA = C.ID_CARPETA "
			 + "WHERE T.USERNAME = ? "
			 + "AND T.ORGANIZACION = ? ";
	
//	HomeMgr
	
//	B.D datahub
	public static final String SQL_getPointByOrganizationDH = "SELECT * FROM datahub.cmdb_cat_clientes_puntas_vw where cliente_name = ? " + " order by punta_name";
//	public static final String SQL_getPointByOrganizationDH = "SELECT * FROM datahub.cmdb_cat_clientes_puntas_vw where cliente_name = '" + organization + "' order by punta_name";

	public static final String SQL_getIPByOrganization = "SELECT * FROM datahub.cmdb_cat_clientes_puntas_vw where cliente_id = ? " + " order by inactive desc limit 1";
//	public static final String SQL_getIPByOrganization = "SELECT * FROM datahub.cmdb_cat_clientes_puntas_vw where cliente_id ='" + organization + "' order by inactive desc limit 1";

	public static final String SQL_getPointName ="SELECT * FROM datahub.cmdb_cat_clientes_puntas_vw where cliente_id = ? "+ " and punta_name = ? ";
//	public static final String SQL_getPointName ="SELECT * FROM datahub.cmdb_cat_clientes_puntas_vw where cliente_id = '" + organization  + "' and punta_name = '"+ pointId + "'";

	
//	B.D portalcl
	public static final String SQL_getListaArchivos = "SELECT T.*, C.* " + "FROM TARCHIVOS T " + "JOIN CAT_CARPETAS C ON T.ID_CARPETA=C.ID_CARPETA " + "WHERE T.ORGANIZACION= ? ";
//	public static final String SQL_getListaArchivos = "SELECT T.*, C.* " + "FROM TARCHIVOS T " + "JOIN CAT_CARPETAS C ON T.ID_CARPETA=C.ID_CARPETA " + "WHERE T.ORGANIZACION='"+organizacion+"'";

	public static final String SQL_bajarArchivo = "SELECT * from TARCHIVOS WHERE TARCHIVOS.ARCHIVO_NOMBRE = ? ";
//	public static final String SQL_bajarArchivo = "SELECT * from TARCHIVOS WHERE TARCHIVOS.ARCHIVO_NOMBRE ='" + idArchivo + "'";
	
//	B.D datahub
	public static final String SQL_getSeverityPoint = "SELECT CAP_ID_D ID_PUNTA, concat('TFE: ',cap_tfe,' DESCRIPCION: ',last_name) COMBO FROM datahub.catalogo_puntas_match_full_vw  ORDER BY last_name ASC";
	
//	MenuMgr
	
//	B.D portalcl
	public static final String SQL_getPrincipalMenus= "SELECT * FROM CAT_MENUS";
	
//	RolMgr
	
//	B.D portalcl
	public static final String SQL_getRolesSMDB = "SELECT * FROM TROL ORDER BY IDROL";
	
	public static final String SQL_getMenusporRolCMDB ="SELECT * FROM TB_MENU_ROL TR INNER JOIN CAT_MENUS M ON TR.ID_MENU = M.ID_MENU WHERE IDROL = ? ";
//	public static final String SQL_getMenusporRolCMDB ="SELECT * FROM TB_MENU_ROL TR INNER JOIN CAT_MENUS M ON TR.ID_MENU = M.ID_MENU WHERE IDROL = '"+ idRol + "'";
	
//	UserMgr
	
//	B.D portalcl
	public static final String SQL_getNoUsuarios_1 = "SELECT * FROM USERS WHERE USERNAME= ?";
//	public static final String SQL_getNoUsuarios_1 = "SELECT * FROM USERS WHERE USERNAME='" + username + "'";
	
	public static final String SQL_getNoUsuarios_2 ="SELECT * FROM USERS WHERE CREATEUSER= ? " + " AND ENABLED =1";
//	public static final String SQL_getNoUsuarios_2 ="SELECT * FROM USERS WHERE CREATEUSER='" + username + "' AND ENABLED =1";

	public static final String SQL_validaUsuario = "SELECT * FROM USERS WHERE USERNAME = ? ";
//	public static final String SQLvalidaUsuario = "SELECT * FROM USERS WHERE USERNAME = '" + username + "'";

	
//	B.D datahub
	public static final String SQL_getOrganizations ="SELECT organization_uuid, org_name FROM datahub.sdm_ca_organization where inactive = '0' order by org_name";
	
//	B.D portalcl
	public static final String SQL_getOrganizationByUserCMDB = " SELECT * FROM USERS INNER JOIN CAT_ORGANIZACION ON ORGANIZATION = ORGANIZACION_ID WHERE USERNAME = ? ";
//	public static final String SQL_getOrganizationByUserCMDB = " SELECT * FROM USERS INNER JOIN CAT_ORGANIZACION ON ORGANIZATION = ORGANIZACION_ID WHERE USERNAME ='" + username + "'";

	
	public static final String SQL_getOrigenHome =
	"SELECT CAT_ORIGEN_HOME.ID_HOME, " + 
	" CAT_ORIGEN_HOME.ORIGEN_HOME, " + 
	" CAT_ORIGEN_HOME.URL_HOME," + 
	" CAT_ORIGEN_HOME.URL_DETALLE, " + 
	" CAT_ORIGEN_HOME.DESCRIPCION, " + 
	" CAT_ORIGEN_HOME.URL_UNO," + 
	" CAT_ORIGEN_HOME.URL_DOS, " + 
	" CAT_ORIGEN_HOME.URL_TRE," + 
	" CAT_SLA.ORGANIZACION_NAME," + 
	" CAT_SLA.LATENCIA," + 
	" CAT_SLA.PAQUETES," + 
	" CAT_SLA.DISPONIBILIDAD" + 
	" FROM TROL  " + 
	" inner join TOPERATOR_ROL   on TROL.IDROL = TOPERATOR_ROL.IDROL " + 
	" inner join USERS           on TOPERATOR_ROL.USERNAME = USERS.USERNAME" + 
	" inner join CAT_SLA		 on CAT_SLA.ORGANIZACION_NAME = TROL.NOMBRE_ORGANIZACION " + 
	" inner join TB_MENU_ROL     on TB_MENU_ROL.IDROL = TROL.IDROL " + 
	" inner join CAT_MENUS       on CAT_MENUS.ID_MENU = TB_MENU_ROL.ID_MENU " + 
	" inner join CAT_ORIGEN_HOME on CAT_ORIGEN_HOME.ID_HOME = TB_MENU_ROL.ID_HOME " + 
	" WHERE USERS.USERNAME = ? " + " limit 1";
	
//	public static final String SQL_getOrigenHome =
//			"SELECT CAT_ORIGEN_HOME.ID_HOME, " + 
//			" CAT_ORIGEN_HOME.ORIGEN_HOME, " + 
//			" CAT_ORIGEN_HOME.URL_HOME," + 
//			" CAT_ORIGEN_HOME.URL_DETALLE, " + 
//			" CAT_ORIGEN_HOME.DESCRIPCION, " + 
//			" CAT_ORIGEN_HOME.URL_UNO," + 
//			" CAT_ORIGEN_HOME.URL_DOS, " + 
//			" CAT_ORIGEN_HOME.URL_TRE," + 
//			" CAT_SLA.ORGANIZACION_NAME," + 
//			" CAT_SLA.LATENCIA," + 
//			" CAT_SLA.PAQUETES," + 
//			" CAT_SLA.DISPONIBILIDAD" + 
//			" FROM TROL  " + 
//			" inner join TOPERATOR_ROL   on TROL.IDROL = TOPERATOR_ROL.IDROL " + 
//			" inner join USERS           on TOPERATOR_ROL.USERNAME = USERS.USERNAME" + 
//			" inner join CAT_SLA		 on CAT_SLA.ORGANIZACION_NAME = TROL.NOMBRE_ORGANIZACION " + 
//			" inner join TB_MENU_ROL     on TB_MENU_ROL.IDROL = TROL.IDROL " + 
//			" inner join CAT_MENUS       on CAT_MENUS.ID_MENU = TB_MENU_ROL.ID_MENU " + 
//			" inner join CAT_ORIGEN_HOME on CAT_ORIGEN_HOME.ID_HOME = TB_MENU_ROL.ID_HOME " + 
//			" WHERE USERS.USERNAME ='" + usuario + "' limit 1";
	
//	SecurityConfiguration
	
//	B.D portalcl
	public static final String SQL_configureGlobalSecurity1 = "SELECT USERNAME, PASSWORD, ENABLED FROM USERS WHERE USERNAME = ?";
			
	public static final String SQL_configureGlobalSecurity2 = "SELECT USERNAME, ROLE FROM USERS WHERE USERNAME = ?";
	
//	HomeController
//	B.D portalcl
	public static final String SQL_ARCHIVO ="select x.IDARCHIVO, x.ARCHIVO_NOMBRE, x.ARCHIVO_ARCHIVO from TARCHIVOS x where x.ORGANIZACION = ?";
//	public static final String SQL_ARCHIVO ="select x.IDARCHIVO, x.ARCHIVO_NOMBRE, x.ARCHIVO_ARCHIVO from TARCHIVOS x where x.ORGANIZACION = '"+ organization + "'";
	

//	RolManager(tp-smc-back)
//	B.D portalcl
//	public static final String SQL_insertRol ="SELECT organization_uuid, org_name FROM datahub.sdm_ca_organization where inactive = '0' and organization_uuid = ? ";
	
}