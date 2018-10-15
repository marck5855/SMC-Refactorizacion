//package mx.com.tp.smc.conf;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class ConexionSMC {
//
//			//#LOCAL
//			private static String jdbcURL = "jdbc:mysql://localhost:3306/portalcl";
//			private static String jdbcUsername = "root";
//			private static String jdbcPassword = "";
//			public static Connection jdbcConnection; 
//		
//			//DESARROLLO
////		 	private static String jdbcURL = "jdbc:mysql://10.180.199.123:3306/SMC_DESAROLLO";
////			private static String jdbcUsername = "smcQA";
////			private static String jdbcPassword = "PortalSeguridad2017";
////			public static Connection jdbcConnection;
//			
//			//QA
////			private static String jdbcURL = "jdbc:mysql://10.180.251.151:3306/portalcl";
////			private static String jdbcUsername = "portalclQA";
////			private static String jdbcPassword = "PortalSeguridad2017";
////			public static Connection jdbcConnection; 
//			
//			
//			//PRODUCCION
////			private static String jdbcURL = "jdbc:mariadb://10.180.251.111:3306/portalcl";
////			private static String jdbcUsername = "portalcl";
////			private static String jdbcPassword = "PortalSeguridad2017";
////			public static Connection jdbcConnection; 	
//			
//			public static void connect() throws SQLException{
//				
//				try {
//					Class.forName("com.mysql.jdbc.Driver");
//					jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
//				
//				}catch(ClassNotFoundException e) {
//					throw new SQLException();
//				}
//			}
//			
//			public static void disconnect() throws SQLException {
//				
//				if(jdbcConnection != null && !jdbcConnection.isClosed()) {
//					jdbcConnection.close();
//				}
//			}
//}