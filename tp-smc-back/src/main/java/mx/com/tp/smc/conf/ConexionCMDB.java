//package mx.com.tp.smc.conf;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class ConexionCMDB {
//
//	//LOCAL
//	private static String jdbcURL = "jdbc:mysql://localhost:3306/datahub";
//	private static String jdbcUsername = "root";
//	private static String jdbcPassword = "";
//	public static Connection jdbcConnection;
//	
//	//QA
////	private static String jdbcURL = "jdbc:mysql://mariadb://10.180.199.9:3306/datahub";
////	private static String jdbcUsername = "portal";
////	private static String jdbcPassword = "PortalSeguridad2017";
////	public static Connection jdbcConnection;
//
//	
//	//PRODUCCION
////	private static String jdbcURL = "jdbc:mariadb://10.180.251.84:3306/datahub";
////	private static String jdbcUsername = "portal";
////	private static String jdbcPassword = "PortalSeguridad2017";
////	public static Connection jdbcConnection; 
//	
//	
//	public static void connect() throws SQLException{
//		
//		try {
//			Class.forName("org.mariadb.jdbc.Driver");
//			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
//		
//		}catch(ClassNotFoundException e) {
//			throw new SQLException();
//		}
//	}
//	
//	public static void disconnect() throws SQLException {
//		
//		if(jdbcConnection != null && !jdbcConnection.isClosed()) {
//			jdbcConnection.close();
//		}
//	}
//}