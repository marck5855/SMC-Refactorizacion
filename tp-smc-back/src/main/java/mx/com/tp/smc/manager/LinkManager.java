
package mx.com.tp.smc.manager;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.com.tp.smc.entity.CatLinksEntity;
import mx.com.tp.smc.entity.ToperatorRolEntity;
import mx.com.tp.smc.request.LinkAdd;
import mx.com.tp.smc.request.RolValidation;
import mx.com.tp.smc.response.Link;
import mx.com.tp.smc.response.LinkList;
import mx.com.tp.smc.response.ResponseLink;
import mx.com.tp.smc.response.ResponseRol;
import mx.com.tp.smc.service.LinkServiceBack;
import mx.com.tp.smc.service.ToperatorRolService;

@Component
public class LinkManager {

	final static Logger log = Logger.getLogger(LinkManager.class);

	@Autowired
	private LinkServiceBack linkService;

	@Autowired
	private ToperatorRolService toperatorRolService;

	public LinkList returnAllLinks() {
		DateTime initial = new DateTime();
		boolean success = false;
		String error = "";
		String mssg = "Error al extraer los datos";
		int total = 0;
		List<Link> listLinks = new ArrayList<Link>();

		try {
			List<CatLinksEntity> linksList = linkService.getAllLink();

			for (CatLinksEntity link : linksList) {

				Link obj = new Link();

				obj.setIdLink(link.getIdLink());
				obj.setUrl(link.getUrl());
				obj.setNombreLink(link.getNombreLink());
				obj.setOrganizacion(link.getOrganizacion());
				obj.setDescripcionLink(link.getDescripcionLink());
				obj.setFechaAlta(link.getFechaAlta());

				listLinks.add(obj);
				mssg = "Exito al conseguir los usuarios";
				success = true;
				total = listLinks.size();

			}

		} catch (Exception ex) {
			mssg = "Error al Buscar los datos";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}

		Period period = new Period(initial, new DateTime());
		return new LinkList(success, period, error, mssg, total, listLinks);
	}
	
	public LinkList returnOrganizacionLinks(String organization) {
		DateTime initial = new DateTime();
		boolean success = false;
		String error = "";
		String mssg = "Error al extraer los datos";
		int total = 0;
		List<Link> listLinks = new ArrayList<Link>();

		try {

			Properties p = new Properties();
			InputStream input = null;

			input = getClass().getClassLoader().getResourceAsStream("application.properties");
			p.load(input);

			String driver = p.getProperty("spring.datasource.driver-class-name");
			String url = p.getProperty("spring.datasource.url");
			String user = p.getProperty("spring.datasource.username");
			String password = p.getProperty("spring.datasource.password");
			String query = p.getProperty("queryLinkOrganization");

			Class.forName(driver);
			Connection conexion = DriverManager.getConnection(url, user, password);
			java.sql.Statement comando = conexion.createStatement();

			ResultSet rs = comando.executeQuery(query + "'" + organization + "'");

			while((rs!=null) && (rs.next())) {
				Link l = new Link();
				l.setIdLink(rs.getLong("ID_LINK"));
				l.setUrl(rs.getString("URL"));
				l.setNombreLink(rs.getString("NOMBRE_LINK"));
				l.setOrganizacion(rs.getString("ID_ORGANIZACION"));
				l.setDescripcionLink(rs.getString("DESCRIPCION_LINK"));
				l.setFechaAlta(rs.getDate("FECHA_ALTA"));
				listLinks.add(l);
			}

			mssg = "Exito al insertar los datos";
			success = true;
			total++;
		} catch (Exception ex) {
			mssg = "Error al Agregar los datos";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}

		Period period = new Period(initial, new DateTime());
		return new LinkList(success, period, error, mssg, total, listLinks);
	}

	public ResponseLink insertLink(LinkAdd request) {
		DateTime initial = new DateTime();
		JSONArray array = null;
		boolean success = false;
		String error = "";
		String mssg = "Error al insertar los datos";
		int total = 0;

		BigDecimal siguiente = new BigDecimal(0);

		try {

			Properties p = new Properties();
			InputStream input = null;

			input = getClass().getClassLoader().getResourceAsStream("application.properties");
			p.load(input);

			String driver = p.getProperty("spring.datasource.driver-class-name");
			String url = p.getProperty("spring.datasource.url");
			String user = p.getProperty("spring.datasource.username");
			String password = p.getProperty("spring.datasource.password");
			String query = p.getProperty("queryLink");

			Class.forName(driver);
			Connection conexion = DriverManager.getConnection(url, user, password);
			java.sql.Statement comando = conexion.createStatement();

			ResultSet rs = comando.executeQuery(query);

			if (rs.next()) {
				siguiente = rs.getBigDecimal("SIGUIENTE");
			}

			CatLinksEntity cl = new CatLinksEntity();

			cl.setIdLink((null != siguiente && siguiente.longValue() > 0) ? siguiente.longValue() : 1L);
			cl.setUrl(request.getLink());
			cl.setNombreLink(request.getNombreLink());
			cl.setOrganizacion(request.getOrganization());
			cl.setDescripcionLink(request.getDescripcionLink());
			cl.setFechaAlta(new Date());

			linkService.saveLink(cl);

			mssg = "Exito al insertar los datos";
			success = true;
			total++;
		} catch (Exception ex) {

			mssg = "Error al Agregar los datos";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}

		Period period = new Period(initial, new DateTime());
		return new ResponseLink(success, period, mssg, error, total, "", array);

	}
	
	/**
	 * Eliminar Link
	 * @param request
	 * @return
	 */
	public ResponseLink deleteLink(Long request) {
		DateTime initial = new DateTime();
		JSONArray array = null;
		boolean success = false;
		String error = "";
		String mssg = "Error al eliminar link.";
		int total = 0;
		try {
			linkService.deleteLink(request);
			mssg = "El Link fue eliminado satisfactoriamente.";
			success = true;
			total++;
		} catch (Exception ex) {
			mssg = "Se Encontraron Errores, vuelva a intentarlo.";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}
		Period period = new Period(initial, new DateTime());
		return new ResponseLink(success, period, mssg, error, total, "", array);
	}
	
	public ResponseRol deleteRol(RolValidation request) {
		DateTime initial = new DateTime();
		JSONArray array = null;
		boolean success = false;
		String error = "";
		String mssg = "Error al eliminar los datos";
		int total = 0;

		try {

			List<ToperatorRolEntity> to = toperatorRolService.getIdRol(request.getIdrole());

			for (int i = 0; i < to.size(); i++) {

				ToperatorRolEntity obj = (ToperatorRolEntity) to.get(i);

				toperatorRolService.deleteRol(obj);
			}

//			TrolEntity te = new TrolEntity();// rolService.getRole(request.getIdrole());

			// rolService.deleteRol(te);
			mssg = "Rol Eliminado";
			success = true;
			total++;
		} catch (Exception ex) {

			mssg = "Error al Eliminar los datos";
			error = "Error:" + ex;
			log.error("Error:" + ex);
		}

		Period period = new Period(initial, new DateTime());
		return new ResponseRol(success, period, mssg, error, total, "", array);

	}

}
