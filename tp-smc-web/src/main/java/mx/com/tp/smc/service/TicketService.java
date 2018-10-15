package mx.com.tp.smc.service;


import java.math.BigDecimal;


import org.json.simple.JSONObject;

/**
 * @author dazaeev
 *
 */
public interface TicketService {

	/**
	 * @param token
	 * @param organization
	 * @return
	 * @throws Exception
	 */
	public JSONObject getTicketByOrganization(String token, String organization) throws Exception;

	/**
	 * @param token
	 * @param organization
	 * @return
	 * @throws Exception
	 */
	public JSONObject getTicketGraph(String token, String organization) throws Exception;

	/**
	 * @param token
	 * @param organization
	 * @return
	 * @throws Exception
	 */
	public JSONObject getAllTicketNorConcilied(String token, String organization) throws Exception;

	/**
	 * @param token
	 * @param folioincidencia
	 * @param nombre1
	 * @param nombre2
	 * @param apellidos
	 * @param categoria
	 * @param usuariofinal
	 * @param descripcion
	 * @param solucion
	 * @param fechasolucion
	 * @param fechaapertura
	 * @param afectadocliente
	 * @param diagnosticofinal
	 * @param tiempofallacliente
	 * @param tiempofallaenlace
	 * @param resumen
	 * @return
	 * @throws Exception
	 */
	public JSONObject updateTicketNorConcilied(String token, BigDecimal folioincidencia, String nombre1, String nombre2,
			String apellidos, String categoria, String usuariofinal, String descripcion, String solucion,
			String fechasolucion, String fechaapertura, String afectadocliente, String diagnosticofinal,
			String tiempofallacliente, String tiempofallaenlace, String resumen, String proactivoReactivo, String afectacion, String ztiempoFallaTer,String ztiempoFallaProv) throws Exception;

	/**
	 * @param token
	 * @param folioincidencia
	 * @return
	 * @throws Exception
	 */
	public JSONObject updateStatusTicketNorConcilied(String token, BigDecimal folioincidencia) throws Exception;

	/**
	 * @param token
	 * @param tenant
	 * @return
	 * @throws Exception
	 */
	public JSONObject getCategories(String token, String tenant) throws Exception;

	/**
	 * @param token
	 * @param organization
	 * @return
	 * @throws Exception
	 */
	public JSONObject getAllClosedTickets(String token, String organization) throws Exception;

	/**
	 * @param token
	 * @param organization
	 * @return
	 * @throws Exception
	 */
	public JSONObject getPointByOrganization(String token, String organization) throws Exception;

	/**
	 * @param token
	 * @param aplicant
	 * @param idCategory
	 * @param description
	 * @return
	 * @throws Exception
	 */
	public JSONObject addIncident(String token, String aplicant, String idCategory, String description, String insert)
			throws Exception;

	/**
	 * @param token
	 * @param folioincidencia
	 * @param nombre1
	 * @param nombre2
	 * @param apellidos
	 * @param categoria
	 * @param usuariofinal
	 * @param descripcion
	 * @param solucion
	 * @param fechasolucion
	 * @param fechaapertura
	 * @param afectadocliente
	 * @param diagnosticofinal
	 * @param tiempofallacliente
	 * @param tiempofallaenlace
	 * @param resumen
	 * @return
	 * @throws Exception
	 */
	public JSONObject updateStatusTicketNorConciliedValidation(String token, BigDecimal folioincidencia, String nombre1,
			String nombre2, String apellidos, String categoria, String usuariofinal, String descripcion,
			String solucion, String fechasolucion, String fechaapertura, String afectadocliente,
			String diagnosticofinal, String tiempofallacliente, String tiempofallaenlace, String resumen,String proactivoReactivo, String afectacion,
			String ztiempoFallaTer, String ztiempoFallaProv)
			throws Exception;

	/**
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public JSONObject getAllTicketStatusValidation(String token , String organization) throws Exception;

	/**
	 * @param token
	 * @param folioincidencia
	 * @return
	 * @throws Exception
	 */
	public JSONObject updateTicketPorValidar(String token, BigDecimal folioincidencia) throws Exception;

	public JSONObject agregarComentario(String token, String incidente, String comentario) throws Exception;
	
	public JSONObject getAllStatus(String token)throws Exception;
	
	public JSONObject getUpdateStatus(String token, String incidente, String status, String region)throws Exception;
	
	public JSONObject getCatRegiones(String token) throws Exception;
}