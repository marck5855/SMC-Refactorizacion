//package mx.com.tp.smc.conf;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class ConexionGETL {
//
//	//QA
//		private static String jdbcURL = "jdbc:mysql://10.180.199.142:3306/getl";
//		private static String jdbcUsername = "smc";
//		private static String jdbcPassword = "Juarez_32";
//		public static Connection jdbcConnection; 
//		
//		
//		public static void connect() throws SQLException{
//			
//			try {
//				Class.forName("org.mysql.jdbc.Driver");
//				jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
//			
//			}catch(ClassNotFoundException e) {
//				throw new SQLException();
//			}
//		}
//		
//		public static void disconnect() throws SQLException {
//			
//			if(jdbcConnection != null && !jdbcConnection.isClosed()) {
//				jdbcConnection.close();
//			}
//		}
//}