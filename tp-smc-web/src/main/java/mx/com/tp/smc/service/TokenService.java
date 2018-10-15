package mx.com.tp.smc.service;

//import org.json.simple.JSONObject;

public interface TokenService {

	public String getToken(String user, String pass) throws Exception;
	public String refreshToken(String token) throws Exception;
	
}
