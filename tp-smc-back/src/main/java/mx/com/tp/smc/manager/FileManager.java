
package mx.com.tp.smc.manager;

import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import com.enlacetp.persistence.model.TarchivoEntity;


@Component
public class FileManager {
	final static Logger log = Logger.getLogger(FileManager.class);

	/*@Autowired
	private FileService fileService;

	public void saveFile(File request) {

		boolean success = false;
		String error = "";
		String mssg = "";

		try {

			TarchivoEntity entityPDF = new TarchivoEntity();

			entityPDF.setArchivoAnio(request.getArchivoAnio());
			entityPDF.setArchivoMes(request.getArchivoMes());
			entityPDF.setArchivoNombre(request.getArchivoNombre());
			entityPDF.setIdoperator(request.getIdoperator());
			entityPDF.setIdrol(request.getIdrol());
			entityPDF.setIdarchivo(request.getIdarchivo());
			entityPDF.setArchivoArchivo(request.getArchivoArchivo());

			fileService.saveFile(entityPDF);

			mssg = "Exito al cargar archivo";
			success = true;

		} catch (Exception ex) {

			mssg = "Error al cargar archivo";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}

	}*/
}
