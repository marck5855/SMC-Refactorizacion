
package mx.com.tp.smc.service.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import mx.com.tp.smc.service.FileService;


@Service("fileService")
@Transactional
@SuppressWarnings("unchecked")
public class FileServiceImpl extends RequestTemplate implements FileService {

	@Override
	public JSONObject saveFile(String token,MultipartFile file) throws Exception {

		Date date = new Date();
		byte[] bytes = file.getBytes();
		System.out.println("EL VALOR DEL ARCHIVO EN BYTES - "+ bytes);
//		String archivoNombre = infoPDF[0];
//		String archivoMes = infoPDF[2];
//		String archivoAnio = infoPDF[3];
		
		Properties p=new Properties();
		InputStream input = null;
		
		input = getClass().getClassLoader().getResourceAsStream("application.properties");
		p.load(input);

		String uri = p.getProperty("ip")+p.getProperty("saveFile");
		uri = uri.replace("<token>", token);
		System.out.println("EL VALOR DE LA URL ES -" + uri);
		JSONObject object = new JSONObject();
		object.put("archivoAnio", 2017);
		object.put("archivoMes", 12);
		object.put("archivoNombre", file.getOriginalFilename());
		object.put("archivoFechaactualizacion", date);
		object.put("archivoFechacarga", date);
		object.put("idoperator", 12);
		object.put("idrol", 13);
		object.put("archivoArchivo", bytes);

		HttpEntity<String> request = new HttpEntity<String>(object.toString(), getJsonHeaders());
		System.out.println("EL VALOR DE LA REQUEST ES -" + request);
		RestTemplate rest = getTemplate();
		System.out.println("EL VALOR DE LA REST ES -" + rest.exchange(uri, HttpMethod.POST, request, String.class));
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		System.out.println("EL VALOR DE LA RESPONSE ES -" + response);
		return (JSONObject) new JSONParser().parse(response.getBody());
	}

}
