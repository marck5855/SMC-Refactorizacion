
package mx.com.tp.smc.mgr;

import java.io.Console;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import mx.com.tp.smc.conf.Constant;
//import mx.com.tp.smc.entity.CatArchivos;
import mx.com.tp.smc.entity.CatCarpetas;
import mx.com.tp.smc.entity.File;
import mx.com.tp.smc.entity.PointCMDB;
import mx.com.tp.smc.model.FileModel;
import mx.com.tp.smc.service.FileService;
import mx.com.tp.smc.service.impl.FileServiceImpl;
import mx.com.tp.smc.service.TokenService;
//import mx.com.tp.smc.util.UserHolder;

@Component
public class FileMgr {
	final static Logger log = Logger.getLogger(FileMgr.class);

	@Autowired
	private TokenService tokenService;

	@Autowired
	private FileService fileService;

	@Autowired
	private FileServiceImpl fileServiceI;
	
//	Marco
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	public FileModel saveFile(MultipartFile file) {
		FileModel model = new FileModel();
		File request;
		
		try {
			JSONObject json = fileService.saveFile(tokenService.getToken("adri", "adri"), file);
			if ((Boolean) json.get("success")) {
				JSONArray array = (JSONArray) json.get("listPoint");
				ArrayList<File> tickets = new ArrayList<File>();
				for (int i = 0; i < array.size(); i++) {
					JSONObject object = (JSONObject) array.get(i);
					File ticket = new File();
					if (object.get("username") != null)
						ticket.setUsername((String) object.get("username"));
					if (object.get("name") != null)
						ticket.setName((String) object.get("name"));
					if (object.get("organization") != null)
						ticket.setOrganization((String) object.get("organization"));

					tickets.add(ticket);
				}
				model.setArchivo(tickets);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info(model);
		return model;
	}
	



//	public Connection connect() throws Exception {
//		Class.forName("com.mysql.jdbc.Driver");
////		return DriverManager.getConnection("jdbc:mariadb://10.180.199.123:3306/SMC_DESAROLLO", "smcQA", "PortalSeguridad2017");
////		return DriverManager.getConnection("jdbc:mysql://10.180.251.151:3306/portalcl", "portalclQA", "PortalSeguridad2017");
////		return DriverManager.getConnection("jdbc:mariadb://10.180.251.111:3306/portalcl", "portalcl", "PortalSeguridad2017");
//		return DriverManager.getConnection("jdbc:mysql://localhost:3306/portalcl", "root","");
//	}

//	Marco
	public int guardarFile(InputStream string, long idRol, String name, String archivo, int anio, int mes, String fecha, String organizacion, int idCarpeta) {
		System.out.println("Metodo guardarFile jdbcTemplete---->1");
		int res = 0;
		try {
			log.info("Inicia insert de archivo");
		res = jdbcTemplate.update(Constant.SQL_guardarFile_query, idRol, name, archivo ,mes , anio, string, organizacion, idCarpeta);
		System.out.println("Inserto archivo---->2");
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		return res;
	}
	
//	Versión anterior 
//	public int guardarFile(InputStream string, long idRol, String name, String archivo, int anio, int mes,
//			String fecha, String organizacion, int idCarpeta) {
//		int res = 0;
//		Connection conexion = null;
//		PreparedStatement pstmt = null;
//		try {
//			String query = "INSERT INTO TARCHIVOS "
//						 + "(TARCHIVOS.IDROL, TARCHIVOS.USERNAME, TARCHIVOS.ARCHIVO_NOMBRE, "
//						 + "TARCHIVOS.ARCHIVO_MES, TARCHIVOS.ARCHIVO_ANIO, TARCHIVOS.ARCHIVO_FECHACARGA, "
//						 + "TARCHIVOS.ARCHIVO_FECHAACTUALIZACION, TARCHIVOS.ARCHIVO_ARCHIVO, TARCHIVOS.ORGANIZACION, TARCHIVOS.ID_CARPETA) "
//						 + "VALUES(?, ?, ?, ?, ?, now(), now(), ?, ?, ?) ";
//			conexion = connect();
//			pstmt = conexion.prepareStatement(query);
//			pstmt.setLong(1, idRol);
//			pstmt.setString(2, name);
//			pstmt.setString(3, archivo);
//			pstmt.setInt(4, mes);
//			pstmt.setInt(5, anio);
//			//pstmt.setString(6, fecha);
//			//pstmt.setString(7, fecha);
//			pstmt.setBinaryStream(6, string);
//			pstmt.setString(7, organizacion);
//			pstmt.setInt(8, idCarpeta);
//
//			res = pstmt.executeUpdate();
//
//			conexion.close();
//			pstmt.close();
//		} catch (Exception e) {
//			try {
//				if (null != conexion) {
//					conexion.close();
//				}
//				if (null != pstmt) {
//					pstmt.close();
//				}
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//		}
//		return res;
//	}
	
	
	/*
	 * Validacion de existencia de duplicidad de archivo
	 * Nueva funcionalidad por incidente - "Validar duplicidad de documentos"
	 * */
	
//	Marco
	public int getFileExist(String username, String organization, String archivo) { 
		int numarchivos = 0;
		Connection conexion = null;
		String query;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			log.info("Inicia validacion de existencia de archivo");
			int rowCount = jdbcTemplate.queryForObject(Constant.SQL_getFileExist_query,
					new Object[] { username, organization, archivo}, Integer.class);
			
			if (rowCount > 0) {
				
				numarchivos = rowCount;
			}
			System.out.println("valor de  rowCount ----------->"+ rowCount);
		} catch (Exception e) {
			
			
			e.printStackTrace();
		}
		
		System.out.println("valor de  numarchivos ----------->"+ numarchivos);
		return numarchivos;
		
	}

//	Versión anterior 
//	public int getFileExist(String username, String organization, String archivo) { 
//		int numarchivos = -1;
//		Connection conexion = null;
//		String query;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			log.info("Inicia validacion de existencia de archivo");
//			conexion = connect();
//			query = "SELECT	COUNT(A.ARCHIVO_NOMBRE) AS NUMARCHIVOS "
//				  + "FROM	TARCHIVOS A "
//				  + "WHERE	A.USERNAME=? "
//				  + "AND 	A.ORGANIZACION=? "
//				  + "AND 	A.ARCHIVO_NOMBRE=?" ;
//			pstmt = conexion.prepareStatement(query);
//				pstmt.setString(1, username);
//				pstmt.setString(2, organization);
//				pstmt.setString(3, archivo);
//			rs = pstmt.executeQuery();
//				rs.next();
//			numarchivos = rs.getInt(1);
//				log.info("Numero de documento duplicado: "+numarchivos);
//			rs.close();
//			pstmt.close();
//			
//			return numarchivos;
//			
//		} catch (Exception e) {
//			try {
//				if (null != conexion) {
//					conexion.close();
//				}
//				if (null != pstmt) {
//					pstmt.close();
//				}
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//		}
//		return numarchivos;
//		
//	}
	
	
	//guardarRuta
//	Marco
	public int guardarRuta(String name, String carpeta, int nivel, String organizacion) {

		int idCarpeta = 0;

		try {
			log.info("Inicia insercion de ruta");
			int res = jdbcTemplate.update(Constant.SQL_guardarRuta_qCarpeta, name, carpeta, nivel, organizacion);
			System.out.println("insercion de ruta---->2");
			
			System.out.println("insercion de ruta---->3");
			int rowCount = jdbcTemplate.queryForObject(Constant.SQL_guardarRuta_query,
					new Object[] { name, carpeta, nivel, organizacion}, Integer.class);
			System.out.println("insercion de ruta---->4");
			if (rowCount > 0) {
				idCarpeta = rowCount;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return idCarpeta;
	}
	
//	Versión anterior
//	public int guardarRuta(String name, String carpeta, 
//			   int nivel, String organizacion) {
//int res = 0;
//int idCarpeta = 0;
//Connection conexion = null;
//PreparedStatement pstmt = null;
//try {
//log.info("Inicia insercion de ruta");
//
//conexion = connect();
//ResultSet rs = null;
//String qCarpeta = "INSERT INTO CAT_CARPETAS	"
//				+ "(NOMBRE, PATH, NIVEL, ORGANIZACION) "
//				+ "VALUES "
//				+ "(?,?,?,?)";
//pstmt = conexion.prepareStatement(qCarpeta);
//pstmt.setString(1, name);
//pstmt.setString(2, carpeta);
//pstmt.setInt(3, nivel);
//pstmt.setString(4, organizacion);
//
//res = pstmt.executeUpdate();
//
//	String query = "SELECT  C.ID_CARPETA "
//			  	 + "FROM	CAT_CARPETAS C "
//			  	 + "WHERE	C.NOMBRE=? "
//			  	 + "AND 	C.PATH=? "
//			  	 + "AND 	C.NIVEL=? "
//			  	 + "AND 	C.ORGANIZACION=? " ;
//	pstmt = conexion.prepareStatement(query);
//	pstmt.setString(1, name);
//	pstmt.setString(2, carpeta);
//	pstmt.setInt(3, nivel);
//	pstmt.setString(4, organizacion);
//	rs = pstmt.executeQuery();
//	rs.next();
//	
//	idCarpeta = rs.getInt(1);
//
//		rs.close();
//		pstmt.close();
//		
//	return idCarpeta;
//
//} catch (Exception e) {
//try {
//	if (null != conexion) {
//		conexion.close();
//	}
//	if (null != pstmt) {
//		pstmt.close();
//	}
//} catch (SQLException e1) {
//	e1.printStackTrace();
//}
//e.printStackTrace();
//}
//return idCarpeta;
//}
	

//	Marco
	public FileModel getListaRutas(String path, String organizacion) {
		Connection conexion = null;
		PreparedStatement pstmt = null;
		FileModel model = new FileModel();
		try {
			log.info("Inicia lectura de carpetas");
			ArrayList<CatCarpetas> carpetas = new ArrayList<CatCarpetas>();
			carpetas = (ArrayList<CatCarpetas>) jdbcTemplate.query(Constant.SQL_getListaRutas_qCarpetas, new Object[] { organizacion },
					new RowMapper<CatCarpetas>() {

						@Override
						public CatCarpetas mapRow(ResultSet rs, int rowNum) throws SQLException {
							CatCarpetas listaCarpeta = new CatCarpetas();
							listaCarpeta.setPath(rs.getString("path"));
							listaCarpeta.setNivel(rs.getInt("nivel"));
						    log.info("Carpeta: " + listaCarpeta.getPath());
							return listaCarpeta;
						}});
			model.setListaCapeta(carpetas);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
//	Versión anterior
//	public FileModel getListaRutas(String path, String organizacion) {
//		Connection conexion = null;
//		PreparedStatement pstmt = null;
//		FileModel model = new FileModel();
//		try {
//			log.info("Inicia lectura de carpetas");
//			conexion = connect();
//			ResultSet rs = null;
//			
//			String qCarpetas = "SELECT DISTINCT(C.PATH), C.NIVEL   "
//							 + "FROM TARCHIVOS T "
//							 + "JOIN CAT_CARPETAS C ON T.ID_CARPETA = C.ID_CARPETA "
//							 + "WHERE T.ORGANIZACION = ? "
//							 + "ORDER BY C.PATH ASC ";
//			
//			pstmt = conexion.prepareStatement(qCarpetas);
//			pstmt.setString(1, organizacion);
//
//			rs = pstmt.executeQuery();
//			
//			ArrayList<CatCarpetas> carpetas = new ArrayList<CatCarpetas>();
//			
//			while (rs.next()) {
//				CatCarpetas listaCarpeta = new CatCarpetas();
//					listaCarpeta.setPath(rs.getString("path"));
//					listaCarpeta.setNivel(rs.getInt("nivel"));
//				log.info("Carpeta: " + listaCarpeta.getPath());
//				carpetas.add(listaCarpeta);
//			}
//			model.setListaCapeta(carpetas);
//			conexion.close();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}
	
	
	
//	Versión anterior
//	public FileModel getListaRutas(String path, String organizacion) {
//		Connection conexion = null;
//		PreparedStatement pstmt = null;
//		FileModel model = new FileModel();
//		try {
//			log.info("Inicia lectura de carpetas");
//			conexion = connect();
//			ResultSet rs = null;
//			
//			String qCarpetas = "SELECT DISTINCT(C.PATH), C.NIVEL   "
//							 + "FROM TARCHIVOS T "
//							 + "JOIN CAT_CARPETAS C ON T.ID_CARPETA = C.ID_CARPETA "
//							 + "WHERE T.ORGANIZACION = ? "
//							 + "ORDER BY C.PATH ASC ";
//			
//			pstmt = conexion.prepareStatement(qCarpetas);
//			pstmt.setString(1, organizacion);
//
//			rs = pstmt.executeQuery();
//			
//			ArrayList<CatCarpetas> carpetas = new ArrayList<CatCarpetas>();
//			
//			while (rs.next()) {
//				CatCarpetas listaCarpeta = new CatCarpetas();
//					listaCarpeta.setPath(rs.getString("path"));
//					listaCarpeta.setNivel(rs.getInt("nivel"));
//				log.info("Carpeta: " + listaCarpeta.getPath());
//				carpetas.add(listaCarpeta);
//			}
//			model.setListaCapeta(carpetas);
//			conexion.close();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return model;
//	}
	
//	Marco
	public int getExisteRuta(String usr, String path, String organization) {
		int numeroCarpetas = 0;
		try {
			log.info("Inicia lectura de carpetas getExisteRuta");
			
			int rowCount = jdbcTemplate.queryForObject(Constant.SQL_getExisteRuta_qexist,
					new Object[] {path, organization}, Integer.class);
			
			if(rowCount > 0) {
				numeroCarpetas = rowCount;
			}
			
			log.info("Carpeta existe: "+numeroCarpetas);
				if(numeroCarpetas == 0) {
					log.info("Entro a consultar conteo de carpetas existentes");
					
					int rowCount2 = jdbcTemplate.queryForObject(Constant.SQL_getExisteRuta_qCarpetas,
							new Object[] {usr, organization}, Integer.class);
					
					numeroCarpetas = rowCount2;
					
				return numeroCarpetas;
				}else {
					numeroCarpetas = 1;
					return numeroCarpetas;
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return numeroCarpetas;
	}
	
//	Versión anterior
//	public int getExisteRuta(String usr, String path, String organization) {
//		int numeroCarpetas = 0;
//		Connection conexion = null;
//		PreparedStatement pstmt = null;
//		try {
//			log.info("Inicia lectura de carpetas");
//			conexion = connect();
//			ResultSet rs = null;
//			
//			//"Carpeta_MASTER";
//			String qexist = "SELECT COUNT(T.PATH) RUTA "
//							 + "FROM CAT_CARPETAS T "
//							 + "WHERE T.PATH = ? "
//							 + "AND T.ORGANIZACION = ? ";
//			pstmt = conexion.prepareStatement(qexist);
//			pstmt.setString(1, path);
//			pstmt.setString(2, organization);
//			rs = pstmt.executeQuery();
//			rs.next();
//			numeroCarpetas = rs.getInt(1);
//			log.info("Carpeta existe: "+numeroCarpetas);
//				if(numeroCarpetas == 0) {
//					log.info("Entro a consultar conteo de carpetas existentes");
//				String qCarpetas = "SELECT COUNT(DISTINCT(C.PATH)) AS NOCUENTAS  "
//								 + "FROM TARCHIVOS T "
//								 + "JOIN CAT_CARPETAS C ON T.ID_CARPETA = C.ID_CARPETA "
//								 + "WHERE T.USERNAME = ? "
//								 + "AND T.ORGANIZACION = ? ";
//				
//				pstmt = conexion.prepareStatement(qCarpetas);
//				pstmt.setString(1, usr);
//				pstmt.setString(2, organization);
//				rs = pstmt.executeQuery();
//				rs.next();
//				numeroCarpetas = rs.getInt(1);
//				conexion.close();
//				return numeroCarpetas;
//				}else {
//					numeroCarpetas = 1;
//					return numeroCarpetas;
//				}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return numeroCarpetas;
//	}
//	
	
}
