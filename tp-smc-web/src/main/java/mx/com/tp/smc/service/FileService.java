package mx.com.tp.smc.service;

import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	
	
	public JSONObject saveFile(String token,MultipartFile file) throws Exception;

}
