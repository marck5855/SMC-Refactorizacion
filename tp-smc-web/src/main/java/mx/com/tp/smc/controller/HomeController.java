package mx.com.tp.smc.controller;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import mx.com.tp.smc.conf.Constant;
import mx.com.tp.smc.entity.CatMenu;
import mx.com.tp.smc.entity.Menus;
import mx.com.tp.smc.entity.TicketConcilied;
import mx.com.tp.smc.mgr.ConsultMgr;
import mx.com.tp.smc.mgr.FileMgr;
import mx.com.tp.smc.mgr.HomeMgr;
import mx.com.tp.smc.mgr.LinksMgr;
import mx.com.tp.smc.mgr.MenuMgr;
import mx.com.tp.smc.mgr.RolMgr;
import mx.com.tp.smc.mgr.UserMgr;
import mx.com.tp.smc.model.CatMenuModel;
import mx.com.tp.smc.model.CatRegionesModel;
import mx.com.tp.smc.model.CategoriesModel;
import mx.com.tp.smc.model.FileModel;
import mx.com.tp.smc.model.HomeModel;
import mx.com.tp.smc.model.LinksModel;
import mx.com.tp.smc.model.MainModel;
import mx.com.tp.smc.model.MenuRolModel;
import mx.com.tp.smc.model.MenusModel;
import mx.com.tp.smc.model.OrganizationModel;
import mx.com.tp.smc.model.OrigenHomeModel;
import mx.com.tp.smc.model.PointModel;
import mx.com.tp.smc.model.RoleModel;
import mx.com.tp.smc.model.StatusModel;
import mx.com.tp.smc.model.TicketCloseModel;
import mx.com.tp.smc.model.TicketConciliedModel;
import mx.com.tp.smc.model.TicketModel;
import mx.com.tp.smc.model.UserModel;
import mx.com.tp.smc.util.DateUtils;
import mx.com.tp.smc.util.FileValidator;
import mx.com.tp.smc.util.TemplateBuilder;
import mx.com.tp.smc.util.UserHolder;


@Controller
@RequestMapping(value = "/home")
public class HomeController {
	final static Logger log = Logger.getLogger(HomeController.class);

	@Autowired
	private HomeMgr mgr;
	@Autowired
	private UserMgr mgr2;
	@Autowired
	private FileMgr mgr3;
	@Autowired
	private ConsultMgr mgr4;

	@Autowired
	private RolMgr rolMgr;

	@Autowired
	private MenuMgr menuMgr;

	@Autowired
	private LinksMgr linksMgr;

	@Autowired
	FileValidator fileValidator;
	
//	Marco
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) throws IOException, ClassNotFoundException, SQLException {

		HomeModel home = mgr.getHomeGraph();
		home.setMonth(DateUtils.getMonth());
		home.setUsername(UserHolder.getUsername());
		home.setPagename("Home");
		model.addAttribute("model", home);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		OrganizationModel ticket = mgr2.getOrganizationByUserCMDB(auth.getName());
		System.out.println("=== EL VALOR DE LA ORGABIZACION ES ===" + ticket.getTickets().get(0).getOrganizationName());
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Home");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/manageUsers", "Administrar Usuarios"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", ""));
		model.addAttribute("model2", ticket);

		String usuario = ticket.getUsername();

		Long n = getMenusXrol(model);
		System.out.println("=== REGRESO VCALOR == " + n);

		OrigenHomeModel origen = mgr2.getOrigenHome(usuario);

		model.addAttribute("org", origen);

		if (n == 22) {
			return "homeMonitor";
		} else {
			return "home";
		}
	}

	@RequestMapping(value = { "", "capturaPantalla" }, method = RequestMethod.POST)
	public String capturaPantalla(ModelMap model, @RequestParam("btnImpresionPantalla") String boton) {
		// Obtenemos el tamaño del Rectangulo
		Rectangle rectangleTam = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());

		try {
			Robot robot = new Robot();
			// tomamos la captura de pantalla(screenshot)
			BufferedImage bufferedImage = robot.createScreenCapture(rectangleTam);

			String nombreFichero = System.getProperty("user.home") + File.separatorChar + "caputura.jpg";
			System.out.println("Generando el fichero: " + nombreFichero);
			FileOutputStream out = new FileOutputStream(nombreFichero);

			// Escribe la imagen a fichero
			ImageIO.write(bufferedImage, "jpg", out);

		} catch (AWTException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "home";
	}

	@RequestMapping(value = "/incidents", method = RequestMethod.GET)
	public String incidentsPage(ModelMap model) {
		MainModel main = new MainModel();
		main.setUsername(UserHolder.getUsername());
		main.setPagename("Incidentes");
		main.setCategory(TemplateBuilder.buildCategory("#", "Incidentes"));
		model.addAttribute("model", main);
		return "incidents";
	}

	@RequestMapping(value = "/incidents/table", method = RequestMethod.GET)
	public String incidentsTable(ModelMap model) {
		TicketModel ticket = mgr.getAllIncidents();
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Consulta de Incidentes");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/incidents", "Incidentes"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Consulta"));
		model.addAttribute("model", ticket);

		/**/
		getMenusXrol(model);
		/**/

		return "table";
	}

	@RequestMapping(value = "/incidents/cambioStatus", method = RequestMethod.GET)
	public String cambioStatus(ModelMap model) {
		TicketModel ticket = mgr.getAllIncidents();
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Consulta de Incidentes");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/incidents", "Incidentes"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Consulta"));
		model.addAttribute("model", ticket);

		/**/
		getMenusXrol(model);
		/**/

		StatusModel status = mgr.getAllStatus();
		model.addAttribute("modelstatus", status);

		CatRegionesModel region = mgr.getCatRegiones();
		model.addAttribute("region", region);

		return "cambioEstatus";
	}

	@RequestMapping(value = "/incidents/comentario", method = RequestMethod.GET)
	public String comentario(ModelMap model) {

		TicketModel ticket = mgr.getAllIncidents();
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Consulta de Incidentes");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/incidents", "Incidentes"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Consulta"));

		/**/
		getMenusXrol(model);
		/**/

		if (ticket.getTickets() != null) {
			for (int i = 0; i < ticket.getTickets().size(); i++) {
				String num = ticket.getTickets().get(i).getRefNum();
				TicketModel comment = mgr.getAllComment(num);

				for (int j = 0; j < comment.getTickets().size(); j++) {

					System.out.println(
							"=== INSIDENTE COMENTARIO === " + num + "" + comment.getTickets().get(j).getDescription());
					String des = comment.getTickets().get(j).getDescription();
					String fec = comment.getTickets().get(j).getDateInsert();

					ticket.getTickets().get(i).setDescription(des);
					ticket.getTickets().get(i).setDateInsert(fec);
				}

			}
		}
		System.out.println("=== VALOR === " + ticket);
		model.addAttribute("model", ticket);
		return "gestionComentarios";
	}

	@RequestMapping(value = "/incidents/AgregarComentario", method = RequestMethod.POST)
	public String agregarComentario(ModelMap model, @RequestParam("btnComent") String btnComent,
			@RequestParam("incidente") Integer incidente, @RequestParam("comentario") String comentario) {

		String incident = incidente.toString();

		TicketModel ticket = mgr.getAgregarComentarios(incident, comentario);
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Consulta de Incidentes");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/incidents", "Incidentes"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Consulta"));
		model.addAttribute("model", ticket);

		/**/
		getMenusXrol(model);
		/**/

		return "table";
	}

	@RequestMapping(value = "/incidents/CambiarStatus", method = RequestMethod.POST)
	public String agregarComentario(ModelMap model, @RequestParam("btnStatus") String btnStatus,
			@RequestParam("selectStatus") String selectStatus, @RequestParam("incidente") String incidente,
			@RequestParam("region") String region) {
		System.out.println("== EL VALOR DEL SELECT ES ? === " + selectStatus);
		System.out.println("== EL VALOR DEL INCIDENTE ES ? === " + incidente);
		System.out.println("== EL VALOR DE LA REGION ES ? === " + region);

		StatusModel status = mgr.getUpdateStatus(incidente, selectStatus, region);
		System.out.println("== paso metodo  getUpdateStatus=== ");
		TicketModel ticket = mgr.getAllIncidents();
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Consulta de Incidentes");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/incidents", "Incidentes"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Consulta"));
		model.addAttribute("model", ticket);

		/**/
		getMenusXrol(model);
		/**/

		StatusModel st = mgr.getAllStatus();
		model.addAttribute("modelstatus", st);

		return "cambioEstatus";
	}

	@RequestMapping(value = "incidents/exportarpdf", method = RequestMethod.POST)
	public String exportarPdf(ModelMap model, @RequestParam("btnExportarPDF") String boton)
			throws DocumentException, MalformedURLException, IOException {
		System.out.println("=== ENTRE A EXPORTAR EL PDF ===");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		OrganizationModel org = mgr2.getOrganizationByUser(auth.getName());
		System.out.println("=== VALORES DE ORG ===" + org);
		TicketCloseModel ticket = mgr4.getAllClosedTickets(org.getTickets().get(0).getOrganizationId());

		Document documento = new Document();
		PdfWriter writer = null;
		String nombrepdf = System.getProperty("user.home") + File.separatorChar
				+ "REPORTE_TICKETS_MENSUALES_CERRADOS.pdf";
		FileOutputStream ficheroPdf = new FileOutputStream(nombrepdf);
		PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(10);

		documento.addTitle("REPORTE DE TICKETS MENSUALES CERRADOS ");
		documento.open();

		// Image imagen =
		// Image.getInstance("\\main\\webapp\\static\\images\\logo_enl_gris.png");
		// imagen.setAlignment(Image.ALIGN_CENTER);
		// documento.add(imagen);

		Paragraph vacio1 = new Paragraph();
		vacio1.add("\n\n");
		documento.add(vacio1);

		Paragraph titulo = new Paragraph();
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		titulo.setFont(FontFactory.getFont("Times New Roman", 18, Font.BOLD, BaseColor.RED));
		titulo.add("TICKETS MENSUALES CERRADOS");

		documento.add(titulo);

		Paragraph saltolinea1 = new Paragraph();
		saltolinea1.add("\n\n");
		documento.add(saltolinea1);

		// Añadimos una tabla de 7 columnas.
		PdfPTable tabla = new PdfPTable(7);
		// Datos de porcentaje a la tabla (tamaño ancho).
		tabla.setWidthPercentage(100);
		// Datos del ancho de cada columna.
		tabla.setWidths(new float[] { 15, 20, 10, 10, 30, 10, 10 });

		// Añadimos los títulos a la tabla.
		Paragraph columna1 = new Paragraph("NUM. INICIDENTE");
		columna1.setAlignment(Paragraph.ALIGN_CENTER);
		columna1.getFont().setStyle(Font.BOLD);
		columna1.getFont().setSize(8);
		tabla.addCell(columna1);

		Paragraph columna2 = new Paragraph("USUARIO FINAL");
		columna2.setAlignment(Paragraph.ALIGN_CENTER);
		columna2.getFont().setStyle(Font.BOLD);
		columna2.getFont().setSize(8);
		tabla.addCell(columna2);

		Paragraph columna3 = new Paragraph("ID SERVICIO");
		columna3.setAlignment(Paragraph.ALIGN_CENTER);
		columna3.getFont().setStyle(Font.BOLD);
		columna3.getFont().setSize(8);
		tabla.addCell(columna3);

		Paragraph columna4 = new Paragraph("TICKET");
		columna4.setAlignment(Paragraph.ALIGN_CENTER);
		columna4.getFont().setStyle(Font.BOLD);
		columna4.getFont().setSize(8);
		tabla.addCell(columna4);

		Paragraph columna5 = new Paragraph("AFECTACION ENLACE");
		columna5.setAlignment(Paragraph.ALIGN_CENTER);
		columna5.getFont().setStyle(Font.BOLD);
		columna5.getFont().setSize(8);
		tabla.addCell(columna5);

		Paragraph columna6 = new Paragraph("ESTATUS");
		columna6.setAlignment(Paragraph.ALIGN_CENTER);
		columna6.getFont().setStyle(Font.BOLD);
		columna6.getFont().setSize(8);
		tabla.addCell(columna6);

		Paragraph columna7 = new Paragraph("HORA ALTA TICKET");
		columna7.setAlignment(Paragraph.ALIGN_CENTER);
		columna7.getFont().setStyle(Font.BOLD);
		columna7.getFont().setSize(8);
		tabla.addCell(columna7);

		if (ticket != null) {
			for (int i = 0; i < ticket.getTickets().size(); i++) {

				tabla.addCell(ticket.getTickets().get(i).getFolioIncidencia().toString());
				tabla.addCell(ticket.getTickets().get(i).getFolioTitulo3());
				tabla.addCell(ticket.getTickets().get(i).getFolioIncidencia().toString());
				tabla.addCell(ticket.getTickets().get(i).getTiempoImputableTpe());
				tabla.addCell(ticket.getTickets().get(i).getFolioStatus());
				tabla.addCell(ticket.getTickets().get(i).getTiempoImputableCte());
				tabla.addCell(ticket.getTickets().get(i).getFolioAbierto());
			}
		} else {
			tabla.addCell("");
			tabla.addCell("");
			tabla.addCell("");
			tabla.addCell("");
			tabla.addCell("");
			tabla.addCell("");
			tabla.addCell("");
		}

		documento.add(tabla);

		documento.close();

		// writer.close();

		/**/
		getMenusXrol(model);
		/**/

		return "ticketsCerradosMensuales";
	}

	@RequestMapping(value = "/informs", method = RequestMethod.GET)
	public String informsPage(ModelMap model) {
		MainModel main = new MainModel();
		main.setUsername(UserHolder.getUsername());
		main.setPagename("Informes");
		main.setCategory(TemplateBuilder.buildCategory("#", "Informes"));
		model.addAttribute("model", main);
		return "informs";
	}

	@RequestMapping(value = "/informs/ticketIncident", method = RequestMethod.GET)
	public String informsIncident(ModelMap model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		OrganizationModel ticket = mgr2.getOrganizationByUser(auth.getName());
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Incidentes");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Informes"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Incidentes"));
		model.addAttribute("model", ticket);

		/**/
		getMenusXrol(model);
		/**/

		return "ticketIncident";
	}

	@RequestMapping(value = "/incidents/tableIncidentConciliado", method = RequestMethod.GET)
	public String informsIncidentConciliados(ModelMap model) {
		TicketModel ticket = mgr.getAllIncidents();
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Incidentes Conciliados");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/incidents", "Incidentes"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Incidentes Conciliados"));
		model.addAttribute("model", ticket);
		return "tableIncidentConciliado";
	}

	@RequestMapping(value = "/incidents/porconciliar", method = RequestMethod.GET)
	public String getAllNotConcilied(ModelMap model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String organization = mgr.getOrganizationByUser(auth.getName());

		TicketConciliedModel ticket = mgr.getAllTicketNotConcilied(organization);
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Incidentes por Conciliar");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Incidentes"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Por Conciliar"));
		model.addAttribute("model", ticket);
		return "porconciliar";
	}

	// Conciliar por parte del área administrativa de Isidro
	@RequestMapping(value = "/incidents/ajustesconciliacion", method = RequestMethod.GET)
	public String getAllSettingsConciliation(ModelMap model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String organization = mgr.getOrganizationByUserID(auth.getName());

		System.out.println("ORGANIZACION " + organization);

		TicketConciliedModel ticket = mgr.getAllTicketStatusValidation(organization);
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Validados por Conciliar");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Incidentes"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Validados por Conciliar"));
		model.addAttribute("model", ticket);

		getMenusXrol(model);

		return "ajustesconciliacion";
	}

	// Ajusstes por validar para el área de calidad administrativa de Isidro
	@RequestMapping(value = "/incidents/ajustesvalidacion", method = RequestMethod.GET)
	public String getAllSettingsConciliation2(ModelMap model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String organization = mgr.getOrganizationByUserID(auth.getName());

		TicketConciliedModel ticket = mgr.getAllTicketNotConcilied(organization);
		System.out.println("VALORES DEL TICKET CONTROLLER" + ticket);
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Cerrados por Validar");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Incidentes"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Cerrados por Validar"));
		model.addAttribute("model", ticket);

		getMenusXrol(model);

		return "ajustesvalidacion";
	}

	// Ajusstes por validar para el área de calidad administrativa de Isidro
	@RequestMapping(value = "/incidents/ajustesvalidacioni", method = RequestMethod.GET)
	public String getAllSettingsConciliation2i(ModelMap model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String organization = mgr.getOrganizationByUserID(auth.getName());

		TicketConciliedModel ticket = mgr.getAllTicketNotConcilied(organization);
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Cerrados por Validar");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Incidentes"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Cerrados por Validar"));
		model.addAttribute("model", ticket);

		getMenusXrol(model);

		return "ajustesvalidacioni";
	}

	@RequestMapping(value = "/incidents/updateajustesvalidacion", method = RequestMethod.POST)
	public String updateTicketNoConciliedValidation(ModelMap model, @RequestParam("btnConciliado") String boton,
			@RequestParam("folioincidencia") BigDecimal folioincidencia, @RequestParam("nombre1") String nombre1,
			@RequestParam("nombre2") String nombre2, @RequestParam("apellidos") String apellidos,
			@RequestParam("categoria") String categoria, @RequestParam("usuariofinal") String usuariofinal,
			@RequestParam("descripcion") String descripcion, @RequestParam("solucion") String solucion,
			@RequestParam("fechasolucion") String fechasolucion, @RequestParam("fechaapertura") String fechaapertura,
			@RequestParam("afectadocliente") String afectadocliente,
			@RequestParam("diagnosticofinal") String diagnosticofinal,
			@RequestParam("tiempoImputableCte") String tiempofallacliente,
			@RequestParam("tiempoImputableTpe") String tiempofallaenlace, @RequestParam("resumen") String resumen,
			@RequestParam("proactivoReactivo") String proactivoReactivo, @RequestParam("afectacion") String afectacion,
			@RequestParam("ztiempoFallaTer") String ztiempoFallaTer,
			@RequestParam("ztiempoFallaProv") String ztiempoFallaProv) {

		System.out.println("=== TIEMPO FALLA TERCEROS  Y PROV ===" + ztiempoFallaTer + " - " + ztiempoFallaProv);
		System.out.println("<========= VALORES ==========> " + boton);
		if (boton.equals("Guardar Ajustes")) {
			TicketConciliedModel ticket = mgr.updateTicketNotConcilied(folioincidencia, nombre1, nombre2, apellidos,
					categoria, usuariofinal, descripcion, solucion, fechasolucion, fechaapertura, afectadocliente,
					diagnosticofinal, tiempofallacliente, tiempofallaenlace, resumen, proactivoReactivo, afectacion,
					ztiempoFallaTer, ztiempoFallaProv);
			ticket.setUsername(UserHolder.getUsername());
			ticket.setPagename("Ajustes y Validacion");
			ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Incidentes"));
			ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Ajustes y Validacion"));
			model.addAttribute("model", ticket);
		}

		if (boton.equals("Enviar a Validación") || boton.equals("Validar")) {

			TicketConciliedModel ticket = mgr.updateStatusTicketNotConciliedValidation(folioincidencia, nombre1,
					nombre2, apellidos, categoria, usuariofinal, descripcion, solucion, fechasolucion, fechaapertura,
					afectadocliente, diagnosticofinal, tiempofallacliente, tiempofallaenlace, resumen,
					proactivoReactivo, afectacion, ztiempoFallaTer, ztiempoFallaProv);
			ticket.setUsername(UserHolder.getUsername());
			ticket.setPagename("Ajustes y Conciliación");
			ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Incidentes"));
			ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Ajustes y Validacion"));
			model.addAttribute("model", ticket);
		}

		return "ajustesvalidacion";
	}

	@RequestMapping(value = "/incidents/updateajustesconciliacion", method = RequestMethod.POST)
	public String updateTicketNoConcilied(ModelMap model, @RequestParam("btnConciliado") String boton,
			@RequestParam("folioincidencia") BigDecimal folioincidencia, @RequestParam("nombre1") String nombre1,
			@RequestParam("nombre2") String nombre2, @RequestParam("apellidos") String apellidos,
			@RequestParam("categoria") String categoria, @RequestParam("usuariofinal") String usuariofinal,
			@RequestParam("descripcion") String descripcion, @RequestParam("solucion") String solucion,
			@RequestParam("fechasolucion") String fechasolucion, @RequestParam("fechaapertura") String fechaapertura,
			@RequestParam("afectadocliente") String afectadocliente,
			@RequestParam("diagnosticofinal") String diagnosticofinal,
			@RequestParam("tiempoImputableCte") String tiempofallacliente,
			@RequestParam("tiempoImputableTpe") String tiempofallaenlace, @RequestParam("resumen") String resumen) {

		if (boton.equals("Conciliar")) {
			TicketConciliedModel ticket = mgr.updateStatusTicketNotConcilied(folioincidencia);
			ticket.setUsername(UserHolder.getUsername());
			ticket.setPagename("Ajustes y Conciliación");
			ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Incidentes"));
			ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Ajustes y Conciliacición"));
			model.addAttribute("model", ticket);

		} else if (boton.equals("Regresar a Calidad")) {
			TicketConciliedModel ticket = mgr.updateTicketPorValidar(folioincidencia);
			ticket.setUsername(UserHolder.getUsername());
			ticket.setPagename("Ajustes y Conciliación");
			ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Incidentes"));
			ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Ajustes y Validacion"));
			model.addAttribute("model", ticket);
		}

		return "ajustesconciliacion";
	}

	@RequestMapping(value = "/incidents/downloadExcel", method = RequestMethod.POST)
	public String downloadExcel(ModelMap model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String organization = mgr.getOrganizationByUserID(auth.getName());

		try {
			TicketConciliedModel ticket = mgr.getAllTicketStatusValidation(organization);
			ticket.setUsername(UserHolder.getUsername());
			ticket.setPagename("Ajustes y Conciliación");
			ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Incidentes"));
			ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Ajustes y Conciliacición"));
			model.addAttribute("model", ticket);
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Excel Sheet");
			HSSFRow rowhead = sheet.createRow((short) 0);
			rowhead.createCell((short) 0).setCellValue("NOMBRE SOLICITANTE");
			rowhead.createCell((short) 1).setCellValue("NUMERO DE INCIDENTE");
			rowhead.createCell((short) 2).setCellValue("RESUMEN");
			rowhead.createCell((short) 3).setCellValue("FECHA APERTURA");
			rowhead.createCell((short) 4).setCellValue("FECHA SOLUCION");
			rowhead.createCell((short) 5).setCellValue("AFECTADO/CLIENTE");
			rowhead.createCell((short) 6).setCellValue("USUARIO FINAL");
			rowhead.createCell((short) 7).setCellValue("DESCRIPCION");
			rowhead.createCell((short) 8).setCellValue("SOLUCION");
			rowhead.createCell((short) 9).setCellValue("CATEGORIA");
			rowhead.createCell((short) 10).setCellValue("DIAGNOSTICO FINAL");
			rowhead.createCell((short) 11).setCellValue("PROACTIVO/REACTIVO");
			rowhead.createCell((short) 12).setCellValue("AFECTACION AL SERVICIO");
			rowhead.createCell((short) 13).setCellValue("TIEMPO FALLA PROVEEDOR");
			rowhead.createCell((short) 14).setCellValue("TIEMPO FALLA TERCEROS");
			String nombreSolicitante = "";

			int index = 1;
			for (TicketConcilied ticketConcilied : ticket.getTickets()) {
				nombreSolicitante = ticketConcilied.getNomSolicitante() + ticketConcilied.getNom2Solicitante()
						+ ticketConcilied.getApeSolicitante();

				HSSFRow row = sheet.createRow((short) index);
				row.createCell((short) 0).setCellValue(nombreSolicitante);
				row.createCell((short) 1).setCellValue(ticketConcilied.getFolioIncidencia());
				row.createCell((short) 2).setCellValue(ticketConcilied.getResumen());
				row.createCell((short) 3).setCellValue(ticketConcilied.getFolioAbierto());
				row.createCell((short) 4).setCellValue(ticketConcilied.getFolioCerrado());
				row.createCell((short) 5).setCellValue(ticketConcilied.getFolioTitulo3());
				row.createCell((short) 6).setCellValue(ticketConcilied.getFolioTitulo());
				row.createCell((short) 7).setCellValue(ticketConcilied.getDescripcion());
				row.createCell((short) 8).setCellValue(ticketConcilied.getSolucion());
				row.createCell((short) 9).setCellValue(ticketConcilied.getCategoria());
				row.createCell((short) 10).setCellValue(ticketConcilied.getDiagFinal());
				row.createCell((short) 11).setCellValue(ticketConcilied.getZesProactivoReactivo());
				row.createCell((short) 12).setCellValue(ticketConcilied.getAfectacion());
				row.createCell((short) 13).setCellValue(ticketConcilied.getZtiempoFallaProv());
				row.createCell((short) 14).setCellValue(ticketConcilied.getZtiempoFallaTerceros());

				index++;

			}
			FileOutputStream fileOut = new FileOutputStream("c:\\excelFile.xls");
			wb.write(fileOut);
			fileOut.close();
			Runtime.getRuntime().exec("cmd /c start " + "c:\\excelFile.xls");

		} catch (Exception e) {
		}

		return "ajustesconciliacion";
	}

	@RequestMapping(value = "/informs/reportIncident", method = RequestMethod.GET)
	public String reportincidents(ModelMap model) throws ClassNotFoundException, IOException, SQLException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		OrganizationModel ticket = mgr2.getOrganizationByUser(auth.getName());
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Performance");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Informes"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Performance"));
		model.addAttribute("model", ticket);

		String usuario = ticket.getUsername();

		getMenusXrol(model);

		OrigenHomeModel origen = mgr2.getOrigenHome(usuario);
		model.addAttribute("org", origen);
		return "reporteIncidentes";
	}

	// INICIO CHRISTIAN J. NETA SYSTEMS
	@RequestMapping(value = "/informs/performance", method = RequestMethod.GET)
	public String informsPerformance(ModelMap model) throws ClassNotFoundException, IOException, SQLException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("=== RECUPERA EL USUARIO === " + auth.getName());
		OrganizationModel ticket = mgr2.getOrganizationByUser(auth.getName());
		System.out.println("=== RECUPERA LA ORGANIZACION (CLIENTE) " + ticket.getTickets().size());
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Performance");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Informes"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Performance"));
		model.addAttribute("model", ticket);

		String organization = mgr.getOrganizationByUser(auth.getName());
		System.out.println(
				"============ PERFORNAMCE PUNTAS POR ORGANIZACION (CLIENTE) ====================" + organization);
		PointModel ticket2 = mgr.getPointByOrganizationDH(organization);
		ticket2.setUsername(UserHolder.getUsername());
		ticket2.setPagename("Performance");
		ticket2.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Incidentes"));
		ticket2.setSubCategory(TemplateBuilder.buildCategory("#", "Performance"));
		model.addAttribute("model2", ticket2);
		// System.out.println("VALOR DEL MODEL2 -" +
		// ticket2.getTickets().get(0).getIpPunta());

		/**/
		getMenusXrol(model);
		/**/

		PointModel ipTicket = mgr.getIPByOrganization(organization);
		ipTicket.setUsername(UserHolder.getUsername());
		ipTicket.setPagename("Performance");
		ipTicket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Informes"));
		ipTicket.setSubCategory(TemplateBuilder.buildCategory("#", "Performance"));
		model.addAttribute("modelip", ipTicket);
		System.out.println("MODELIP -TICKETS" + ipTicket.getTicketsCMDB().size());

		String usuario = ticket.getUsername();
		System.out.println("=== VALOR DEL USUARIO === " + usuario);
		OrigenHomeModel origen = mgr2.getOrigenHome(usuario);
		model.addAttribute("org", origen);
		System.out.println("*****************************valor de origen para los Dashbords******************** "
				+ origen.getOrigenHome().get(0).getIdHome());

		return "performance";
	}

	@RequestMapping(value = "/informs/performanceph", method = RequestMethod.GET)
	public String informsPerformanceph(ModelMap model) throws ClassNotFoundException, IOException, SQLException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String organization = mgr.getOrganizationByUser(auth.getName());

		OrganizationModel ticket = mgr2.getOrganizationByUser(auth.getName());
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Performance");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Informes"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Performance"));
		ticket.getTickets().get(0).setOrganizationName(organization);
		model.addAttribute("model", ticket);

		PointModel ticket2 = mgr.getPointByOrganizationDH(organization);
		ticket2.setUsername(UserHolder.getUsername());
		ticket2.setPagename("Performance");
		ticket2.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Incidentes"));
		ticket2.setSubCategory(TemplateBuilder.buildCategory("#", "Performance"));
		model.addAttribute("model2", ticket2);

		/**/
		getMenusXrol(model);
		/**/

		System.out.println("=== VALOR PARA LOS GRAFICOS DE PH === " + organization);
		PointModel ipTicket = mgr.getIPByOrganization(organization);
		ipTicket.setUsername(UserHolder.getUsername());
		ipTicket.setPagename("Performance");
		ipTicket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Informes"));
		ipTicket.setSubCategory(TemplateBuilder.buildCategory("#", "Performance"));
		model.addAttribute("modelip", ipTicket);

		String usuario = ticket.getUsername();
		OrigenHomeModel origen = mgr2.getOrigenHome(usuario);
		model.addAttribute("org", origen);
		System.out.println("=== DASHBOARD === " + origen.getOrigenHome().get(0).getUrlDetalle());

		return "performanceph";
	}

	@RequestMapping(value = "/informs/performanceTop", method = RequestMethod.GET)
	public String informsPerformanceTop(ModelMap model) throws ClassNotFoundException, IOException, SQLException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String organization = mgr.getOrganizationByUser(auth.getName());

		OrganizationModel ticket = mgr2.getOrganizationByUser(auth.getName());
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Performance");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Informes"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Top Mayor Afectacion"));
		ticket.getTickets().get(0).setOrganizationName(organization);
		model.addAttribute("model", ticket);

		PointModel ticket2 = mgr.getPointByOrganizationDH(organization);
		ticket2.setUsername(UserHolder.getUsername());
		ticket2.setPagename("Performance");
		ticket2.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Incidentes"));
		ticket2.setSubCategory(TemplateBuilder.buildCategory("#", "Performance"));
		model.addAttribute("model2", ticket2);

		/**/
		getMenusXrol(model);
		/**/

		System.out.println("=== VALOR PARA LOS GRAFICOS DE PH === " + organization);
		PointModel ipTicket = mgr.getIPByOrganization(organization);
		ipTicket.setUsername(UserHolder.getUsername());
		ipTicket.setPagename("Performance");
		ipTicket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Informes"));
		ipTicket.setSubCategory(TemplateBuilder.buildCategory("#", "Performance"));
		model.addAttribute("modelip", ipTicket);

		String usuario = ticket.getUsername();
		OrigenHomeModel origen = mgr2.getOrigenHome(usuario);
		model.addAttribute("org", origen);
		System.out.println("=== DASHBOARD === " + origen.getOrigenHome().get(0).getUrlDetalle());

		return "performanceTop";
	}

	@RequestMapping(value = "/informs/performanceDatos", method = RequestMethod.GET)
	public String informsPerformanceDatos(ModelMap model) throws ClassNotFoundException, IOException, SQLException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String organization = mgr.getOrganizationByUser(auth.getName());

		OrganizationModel ticket = mgr2.getOrganizationByUser(auth.getName());
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Performance");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Informes"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Kpis Datos"));
		ticket.getTickets().get(0).setOrganizationName(organization);
		model.addAttribute("model", ticket);
		System.out.println("Holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		PointModel ticket2 = mgr.getPointByOrganizationDH(organization);
		System.out.println("Holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		ticket2.setUsername(UserHolder.getUsername());
		ticket2.setPagename("Performance");
		ticket2.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Incidentes"));
		ticket2.setSubCategory(TemplateBuilder.buildCategory("#", "Performance"));
		model.addAttribute("model2", ticket2);

		/**/
		getMenusXrol(model);
		/**/

		System.out.println("=== VALOR PARA LOS GRAFICOS DE PH === " + organization);
		PointModel ipTicket = mgr.getIPByOrganization(organization);
		ipTicket.setUsername(UserHolder.getUsername());
		ipTicket.setPagename("Performance");
		ipTicket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Informes"));
		ipTicket.setSubCategory(TemplateBuilder.buildCategory("#", "Performance"));
		model.addAttribute("modelip", ipTicket);

		String usuario = ticket.getUsername();
		OrigenHomeModel origen = mgr2.getOrigenHome(usuario);
		model.addAttribute("org", origen);
		System.out.println("=== DASHBOARD === " + origen.getOrigenHome().get(0).getUrlDetalle());

		return "performanceDatos";
	}

	@RequestMapping(value = "/informs/performanceVoz", method = RequestMethod.GET)
	public String informsPerformanceVoz(ModelMap model) throws ClassNotFoundException, IOException, SQLException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String organization = mgr.getOrganizationByUser(auth.getName());

		OrganizationModel ticket = mgr2.getOrganizationByUser(auth.getName());
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Performance");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Informes"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Kpis Voz"));
		ticket.getTickets().get(0).setOrganizationName(organization);
		model.addAttribute("model", ticket);

		PointModel ticket2 = mgr.getPointByOrganizationDH(organization);
		ticket2.setUsername(UserHolder.getUsername());
		ticket2.setPagename("Performance");
		ticket2.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Incidentes"));
		ticket2.setSubCategory(TemplateBuilder.buildCategory("#", "Performance"));
		model.addAttribute("model2", ticket2);

		/**/
		getMenusXrol(model);
		/**/

		System.out.println("=== VALOR PARA LOS GRAFICOS DE PH === " + organization);
		PointModel ipTicket = mgr.getIPByOrganization(organization);
		ipTicket.setUsername(UserHolder.getUsername());
		ipTicket.setPagename("Performance");
		ipTicket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Informes"));
		ipTicket.setSubCategory(TemplateBuilder.buildCategory("#", "Performance"));
		model.addAttribute("modelip", ipTicket);

		String usuario = ticket.getUsername();
		OrigenHomeModel origen = mgr2.getOrigenHome(usuario);
		model.addAttribute("org", origen);
		System.out.println("=== DASHBOARD === " + origen.getOrigenHome().get(0).getUrlDetalle());

		return "performanceVoz";
	}

	@RequestMapping(value = "/informs/performancePH", method = RequestMethod.GET)
	public String informsPerformancePH(ModelMap model) throws ClassNotFoundException, IOException, SQLException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("=== RECUPERA EL USUARIO === " + auth.getName());
		OrganizationModel ticket = mgr2.getOrganizationByUser(auth.getName());
		System.out.println("=== RECUPERA LA ORGANIZACION (CLIENTE) " + ticket.getTickets().size());
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Performance");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Informes"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Performance"));
		model.addAttribute("model", ticket);

		String organization = mgr.getOrganizationByUser(auth.getName());
		System.out.println(
				"============ PERFORNAMCE PUNTAS POR ORGANIZACION (CLIENTE) ====================" + organization);
		PointModel ticket2 = mgr.getPointByOrganizationDH(organization);
		ticket2.setUsername(UserHolder.getUsername());
		ticket2.setPagename("Performance");
		ticket2.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Incidentes"));
		ticket2.setSubCategory(TemplateBuilder.buildCategory("#", "Performance"));
		model.addAttribute("model2", ticket2);
		// System.out.println("VALOR DEL MODEL2 -" +
		// ticket2.getTickets().get(0).getIpPunta());

		/**/
		getMenusXrol(model);
		/**/

		PointModel ipTicket = mgr.getIPByOrganization(organization);
		ipTicket.setUsername(UserHolder.getUsername());
		ipTicket.setPagename("Performance");
		ipTicket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Informes"));
		ipTicket.setSubCategory(TemplateBuilder.buildCategory("#", "Performance"));
		model.addAttribute("modelip", ipTicket);
		System.out.println("MODELIP -TICKETS" + ipTicket.getTicketsCMDB().size());

		String usuario = ticket.getUsername();
		System.out.println("=== VALOR DEL USUARIO === " + usuario);
		OrigenHomeModel origen = mgr2.getOrigenHome(usuario);
		model.addAttribute("org", origen);
		System.out.println("*****************************valor de origen para los Dashbords******************** "
				+ origen.getOrigenHome().get(0).getIdHome());

		return "performancePH";
	}
	// FIN CHRISTIAN J. NETA SYSTEMS

	@RequestMapping(value = "/informs/performances", method = RequestMethod.POST)
	public String informsPerformance2(ModelMap model, @RequestParam("punta") String point)
			throws ClassNotFoundException, IOException, SQLException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		OrganizationModel ticket = mgr2.getOrganizationByUser(auth.getName());
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Performance");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Informes"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Performance"));
		model.addAttribute("model", ticket);

		String organization = mgr.getOrganizationByUser(auth.getName());
		PointModel ticket2 = mgr.getPointByOrganizationDH(organization);

		if (ticket2 == null) {
			return null;
		}

		ticket2.setUsername(UserHolder.getUsername());
		ticket2.setPagename("Performance");
		ticket2.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Informes"));
		ticket2.setSubCategory(TemplateBuilder.buildCategory("#", "Performance"));
		model.addAttribute("model2", ticket2);

		PointModel ticket3 = mgr.getPointName(point, organization);
		ticket3.setUsername(UserHolder.getUsername());
		ticket3.setPagename("Performance");
		ticket3.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Informes"));
		ticket3.setSubCategory(TemplateBuilder.buildCategory("#", "Registrar Incidencia"));
		model.addAttribute("model5", ticket3);

		String usuario = ticket3.getUsername();
		/**/
		getMenusXrol(model);
		/**/

		OrigenHomeModel origen = mgr2.getOrigenHome(usuario);
		model.addAttribute("org", origen);
		System.out.println("valor de origen para los Dashbords  -" + origen);

		return "performance";
	}

	@RequestMapping(value = "/informs/performancess", method = RequestMethod.GET)
	public String informsPerformancess(ModelMap model) throws ClassNotFoundException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		OrganizationModel ticket = mgr2.getOrganizationByUser(auth.getName());
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Performance");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Informes"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Performance"));
		model.addAttribute("model", ticket);

		OrganizationModel ticket4 = mgr2.getOrganizationByUser(auth.getName());
		ticket4.setUsername(UserHolder.getUsername());
		ticket4.setPagename("Performance");
		ticket4.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Informes"));
		ticket4.setSubCategory(TemplateBuilder.buildCategory("#", "Performance"));
		model.addAttribute("model4", ticket4);

		String organization = mgr.getOrganizationByUser(auth.getName());
		PointModel ticket2 = mgr.getPointByOrganizationDH(organization);
		ticket2.setUsername(UserHolder.getUsername());
		ticket2.setPagename("Performance");
		ticket2.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Informes"));
		ticket2.setSubCategory(TemplateBuilder.buildCategory("#", "Performance"));
		model.addAttribute("model2", ticket2);

		/*
		 * PointModel ticket3 = mgr.getIdPoint(point);
		 * ticket3.setUsername(UserHolder.getUsername());
		 * ticket3.setPagename("Performance");
		 * ticket3.setCategory(TemplateBuilder.buildCategory( "/portaltpe/home/informs",
		 * "Informes")); ticket3.setSubCategory(TemplateBuilder.buildCategory("#",
		 * "Registrar Incidencia")); model.addAttribute("model3", ticket3);
		 */
		return "performance";
	}

	@RequestMapping(value = "/informs/slas", method = RequestMethod.GET)
	public String informsSLA(ModelMap model) {
		TicketModel ticket = mgr.getAllIncidents();
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("SLA's");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "SLA's"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "SLA's"));
		model.addAttribute("model", ticket);
		return "slas";
	}

	@RequestMapping(value = "/informs/uploadReporteEjecutivo", method = RequestMethod.GET)
	public String uploadReporteEjecutivo(ModelMap model) throws ClassNotFoundException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String organization = mgr.getOrganizationByUserID(auth.getName());

		try {
			FileModel pdf = mgr.getListaArchivos(organization);
			pdf.setUsername(UserHolder.getUsername());
			pdf.setPagename("Documentos del Proyecto");
			pdf.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Incidentes"));
			pdf.setSubCategory(TemplateBuilder.buildCategory("#", "Documentos del Proyecto"));
			model.addAttribute("archivo", pdf);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TicketModel ticket = mgr.getAllIncidents();
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Documentos del Proyecto");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Incidentes"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Documentos del Proyecto"));
		model.addAttribute("model", ticket);

		// #################################################################
		// Descargar PDF
		Connection conexion = null;
		Statement stmt = null;
		try {
			System.out.println("-------------------------------------1");
			
			SqlRowSet rec = jdbcTemplate.queryForRowSet(Constant.SQL_ARCHIVO, organization);
			while ((rec != null) && (rec.next())) {
				System.out.println("-------------------------------------2");
				System.out.println("ARCHIVO_NOMBRE: " + rec.getString("ARCHIVO_NOMBRE"));
				String path = "/opt/apache-tomcat-9.0.1/webapps/portaltpe/static/tmp/pdf/";
				if (new File(path + rec.getString("IDARCHIVO") + "_" + rec.getString("ARCHIVO_NOMBRE"))
						.exists() != true) {
					System.out.println("-------------------------------------2");
					// Proceder a crear Archivo
					System.out.println(
							"Crear Archivo: " + rec.getString("IDARCHIVO") + "_" + rec.getString("ARCHIVO_NOMBRE"));
					InputStream inputFileBd = ((ResultSet) rec).getBlob("ARCHIVO_ARCHIVO").getBinaryStream();
					byte[] bytesBd = IOUtils.toByteArray(inputFileBd);
					FileCopyUtils.copy(bytesBd, new java.io.File(
							path + rec.getString("IDARCHIVO") + "_" + rec.getString("ARCHIVO_NOMBRE")));
				}
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// #################################################################
		getMenusXrol(model);

		return "uploadReporteEjecutivo";
	}

	@RequestMapping(value = "/informs/muestraReporte", method = RequestMethod.POST)
	public String muestraPdf(@RequestParam("idArchivo") String idArchivo, ModelMap model)
			throws SQLException, IOException, ClassNotFoundException {
		System.out.println("ENTRE A DESCARGAR EL PDF" + idArchivo);
		/**/
		getMenusXrol(model);
		/**/
		return "uploadReporteEjecutivo";
	}

	@RequestMapping(value = "/incidents/register", method = RequestMethod.GET)
	public String getPuntas(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String organization = mgr.getOrganizationByUser(auth.getName());
		System.out.println("=== VALOR DE LAS ORGANIZACIONES === " + organization.toString());
		PointModel ticket2 = mgr.getPointByOrganization(organization);

		ticket2.setPagename("Ajustes y Conciliación");
		ticket2.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Incidentes"));
		ticket2.setSubCategory(TemplateBuilder.buildCategory("#", "Registrar Incidencia"));
		ticket2.setTenant("Seleccione...");
		ticket2.setUserId("");
		ticket2.setDisabled("disabled");

		ticket2.setUsername(UserHolder.getUsername());
		model.addAttribute("model2", ticket2);

		PointModel ticket = new PointModel();
		ticket.setUsername(UserHolder.getUsername());
		model.addAttribute("model", ticket);
		CategoriesModel ticket3 = mgr.getCategories("CARE");
		model.addAttribute("categoria", ticket3);

		/**/
		getMenusXrol(model);
		/**/

		return "register";
	}

	@RequestMapping(value = "/incidents/registerC", method = RequestMethod.GET)
	public String getCategories(ModelMap model, @RequestParam("tenant") String tenant,
			@RequestParam("userId") String userId, @RequestParam("hidetel") String tel,
			@RequestParam("hidemail") String email, @RequestParam("hidehora") String horario,
			@RequestParam("hidedesc") String descripcion, @RequestParam("hidepersona") String persona) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String organization = mgr.getOrganizationByUser(auth.getName());
		PointModel ticket2 = mgr.getPointByOrganization(organization);
		System.out.println("VALOR DEL OBJ " + ticket2);
		ticket2.setUsername(UserHolder.getUsername());
		ticket2.setPagename("Ajustes y Conciliación");
		ticket2.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Incidentes"));
		ticket2.setSubCategory(TemplateBuilder.buildCategory("#", "Registrar Incidencia"));
		ticket2.setTenant(tenant);
		ticket2.setUserId(userId);
		ticket2.setTel(tel);
		ticket2.setEmail(email);
		ticket2.setHorario(horario);
		ticket2.setDescripcion(descripcion);
		ticket2.setPersona(persona);

		model.addAttribute("model2", ticket2);

//		String ten = ticket2.getTicketsCMDB().get(0).getTenant();
//		
//		CategoriesModel ticket = mgr.getCategories(ten);
		CategoriesModel ticket = mgr.getCategories("CARE");
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Ajustes y Conciliación");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Incidentes"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Registrar Incidencia"));
		model.addAttribute("model", ticket);

		CatRegionesModel region = mgr.getCatRegiones();
		model.addAttribute("region", region);
		model.addAttribute("tel", tel);
		model.addAttribute("email", email);
		model.addAttribute("horario", horario);
		model.addAttribute("descripcion", descripcion);
		model.addAttribute("persona", persona);

		/**/
		getMenusXrol(model);
		/**/

		return "register";
	}

	@RequestMapping(value = "/incidents/addincident", method = RequestMethod.POST)
	public String addIncident(ModelMap model, @RequestParam("punta") String aplicant,
			@RequestParam("categoria") String idCategory, @RequestParam("descripcion") String description,
			@RequestParam("puntaUserId") String puntaUserId, @RequestParam("persona") String persona,
			@RequestParam("tel") String tel, @RequestParam("email") String email,
			@RequestParam("horario") String horario, @RequestParam("insertar") String insertar,
			@RequestParam("usuario") String usuario) {

		description = description + "<" + usuario + ">";
		String cadena = "PERSONA:" + persona + "TEL:" + tel + "CORREO" + email + "HRA:" + horario + "DESCRIP:"
				+ description;

		System.out.println("VALOR DEL APLICANT : ===  >" + aplicant);
		System.out.println("VALOR DE LA CADENA DE DESCRIPCION ES : === >" + cadena);
		System.out.println("VALOR DEL FILE  ES : === >" + insertar);
		System.out.println("DESCRIPCION ==================>" + description);
		System.out.println("USUARIO ==================>" + usuario);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		TicketConciliedModel ticket = mgr.addIncident(aplicant, idCategory,
				"Usuario de SMC: " + auth.getName() + " - " + cadena, insertar);
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Ajustes y Conciliación");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Incidentes"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Registrar Incidencia"));
		model.addAttribute("model5", ticket);
		PointModel ticket2 = new PointModel();
		model.addAttribute("punta", "");
		model.addAttribute("model", ticket2);

		/**/
		getMenusXrol(model);
		/**/
		getPuntas(model);
		return "register";
	}

	@RequestMapping(value = "/manageUsers/users", method = RequestMethod.GET) // metodo que estaba haciendo
	public String users(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("===================== usuario =============" + auth.getName());
		String organization = mgr.getOrganizationByUser(auth.getName());
		System.out.println("===================== organizacion =============" + organization);

		UserModel noUser = mgr2.getNoUsuarios(auth.getName());
		model.addAttribute("noUser", noUser);
		System.out.println("<======== OBTENEMOS SI PUEDE O NO PUEDE REGISTRAR USUARIOS ========>"
				+ noUser.getTickets().toString());

		UserModel ticket = mgr2.getUsers(auth.getName(), organization, UserHolder.getUsername());
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Administrar Usuarios");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/manageUsers", "Administrar Usuarios"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Permisos de Usuario"));
		model.addAttribute("model", ticket);
		// }

		log.info("Metodo obtiene roles por organizacion");
		RoleModel ticket2 = mgr2.getRolesByOrganizacion(organization);
		ticket2.setUsername(UserHolder.getUsername());
		ticket2.setPagename("Administrar Usuarios");
		ticket2.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/manageUsers", "Administrar Usuarios"));
		ticket2.setSubCategory(TemplateBuilder.buildCategory("#", ""));
		model.addAttribute("model2", ticket2);

		System.out.println("********HomeController / valor de Organizacion*********** " + organization);
		OrganizationModel ticket3 = mgr2.getOrganizations();
		ticket2.setUsername(UserHolder.getUsername());
		ticket2.setPagename("Administrar Usuarios");
		ticket2.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/manageUsers", "Administrar Usuarios"));
		ticket2.setSubCategory(TemplateBuilder.buildCategory("#", ""));
		model.addAttribute("model5", ticket3);

		/**/
		if (model != null) {
			getMenusXrol(model);
		}
		/**/

		return "users";
	}

	@RequestMapping(value = "/manageUsers/usersadd", method = RequestMethod.POST)
	public String usersadd(ModelMap model, @RequestParam("username") String username, @RequestParam("name") String name,
			@RequestParam("organization") String organization, @RequestParam("password") String password,
			@RequestParam("role") BigDecimal idrole, @RequestParam("noUsr") String noUsr)
			throws ClassNotFoundException {

		UserModel usr = mgr2.validaUsuario(username);
//		System.out.println("=== EL VALOR QUE RETORNA ES === " + usr);
//		if(usr == null ) {

		System.out.println("<===================== EL VALOR DE NO. DE USUARIOS =====================>" + noUsr);

		UserModel ticket = mgr2.insertUsers(username, name, organization, password, idrole, UserHolder.getUsername(),
				noUsr);
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Administrar Usuarios");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/manageUsers", "Administrar Usuarios"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Permisos de Usuario"));
		model.addAttribute("model", ticket);
//		}else {
//			usr.setUsername(UserHolder.getUsername());
//			usr.setPagename("Administrar Usuarios");
//			usr.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/manageUsers", "Administrar Usuarios"));
//			usr.setSubCategory(TemplateBuilder.buildCategory("#", "Permisos de Usuario"));
//			model.addAttribute("model", usr);
//		}
		/**/
		getMenusXrol(model);
		/**/
		return "users";
	}

	@RequestMapping(value = "/manageUsers/usersupdate", method = RequestMethod.POST)
	public String usersupdate(ModelMap model, @RequestParam("username") String username,
			@RequestParam("name") String name, @RequestParam("organization") String organization,
			@RequestParam("password") String password) {
		UserModel ticket = mgr2.updateUsers(username, name, organization, password);
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Administrar Usuarios");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/manageUsers", "Administrar Usuarios"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Permisos de Usuario"));
		model.addAttribute("model", ticket);
		/**/
		getMenusXrol(model);
		/**/
		return "users";
	}

	@RequestMapping(value = "/manageUsers/usersdelete", method = RequestMethod.POST)
	public String usersupdate(ModelMap model, @RequestParam("username") String username) {
		UserModel ticket = mgr2.deleteUsers(username);
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Administrar Usuarios");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/manageUsers", "Administrar Usuarios"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Permisos de Usuario"));
		model.addAttribute("model", ticket);
		/**/
		getMenusXrol(model);
		/**/
		return "users";
	}

	@RequestMapping(value = "/manageUsers/roleusersupdate", method = RequestMethod.POST)
	public String usersupdate(ModelMap model, @RequestParam("username") String username,
			@RequestParam("role") BigDecimal idrol) {
		UserModel ticket = mgr2.updateRoleUser(username, idrol);
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Administrar Usuarios");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/manageUsers", "Administrar Usuarios"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Permisos de Usuario"));
		model.addAttribute("model", ticket);
		/**/
		getMenusXrol(model);
		/**/
		return "users";
	}

	@RequestMapping(value = "/manageUsers/roles", method = RequestMethod.GET)
	public String registroRoles(ModelMap model) {

		// RoleModel ticket2 = rolMgr.getRoles();
		RoleModel ticket2 = rolMgr.getRolesSMDB();

		ticket2.setUsername(UserHolder.getUsername());
		ticket2.setPagename("Administrar Usuarios");
		ticket2.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/manageUsers", "Administrar Usuarios"));
		ticket2.setSubCategory(TemplateBuilder.buildCategory("#", "Registro de Roles"));
		model.addAttribute("model", ticket2);

		// CatOrganizacionModel organizaciones = rolMgr.getAllOrganizations();
		System.out.println("holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa----10");
		OrganizationModel organizaciones = mgr2.getOrganizations(); // ORGANIZACIONES PROVENIENTES DE LA CMDB
		System.out.println("holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa----20" + organizaciones);

		model.addAttribute("model2", organizaciones);

		getMenusXrol(model);
		CatMenuModel menusList = menuMgr.getPrincipalMenus();
		

		// CatMenuModel submenuList = new CatMenuModel();

		// CatMenuModel menusList = menuMgr.getPrincipalMenus();
		// System.out.println("VALOR DE LOS PERMISOS " + menusList.getMenus().size());
		//
		// ArrayList<CatMenu> menusArray = new ArrayList<CatMenu>();
		//
		// CatMenuModel menusModel = new CatMenuModel();

		//
		// menusArray = menusList.getMenus();
		//
		// ArrayList<CatMenu> submenus = new ArrayList<CatMenu>();
		//
		// ArrayList<CatMenu> menus = new ArrayList<CatMenu>();
		// System.out.println("VALOR DE MENU" + menus.get(0).getNombreMenu());
		//
		// menusModel.setMenus(menus);
		// submenusModel.setMenus(submenus);

		model.addAttribute("model5", menusList);
		// model.addAttribute("model6", submenusModel);

		OrigenHomeModel origenHome = mgr.getOrigenHome();
		origenHome.setUsername(UserHolder.getUsername());
		origenHome.setPagename("Administrar Usuarios");
		origenHome.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/manageUsers", "Administrar Usuarios"));
		origenHome.setSubCategory(TemplateBuilder.buildCategory("#", "Registro de Roles"));
		model.addAttribute("model8", origenHome);

//		RoleModel slalist = rolMgr.getconfigSla();
//		model.addAttribute("model9", slalist);

		return "roles";
	}

	@RequestMapping(value = "/manageUsers/rolesadd", method = RequestMethod.POST)
	public String rolesadd(ModelMap model, @RequestParam("rol") String rol,
			@RequestParam("descripcion") String descripcion, @RequestParam("idOrganizacion") String idOrganizacion) {

		System.out.println("=== INSERTA ROL === " + rol + " === " + descripcion + " === " + idOrganizacion);

		RoleModel ticket = rolMgr.insertRoles(rol, descripcion, idOrganizacion);
		System.out.println("VALOR DEL ROLE AL ADD " + ticket);
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Administrar Usuarios");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/manageUsers", "Administrar Usuarios"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Registro de Roles"));
		model.addAttribute("model", ticket);
		return "roles";
	}

	@RequestMapping(value = "/manageUsers/roldelete", method = RequestMethod.POST) // metodo para eliminar rol
	public String roldelete(ModelMap model, @RequestParam("idRole") BigDecimal idRol) {
		RoleModel rol = rolMgr.deleteRol(idRol);
		rol.setUsername(UserHolder.getUsername());
		rol.setPagename("Administrar Usuarios");
		rol.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/manageUsers", "Administrar Usuarios"));
		rol.setSubCategory(TemplateBuilder.buildCategory("#", "Registro de Roles"));
		model.addAttribute("modelDelete", rol);
		return "roles";
	}

	@RequestMapping(value = "/manageUsers/permisosadd", method = RequestMethod.POST)
	public String permisosadd(ModelMap model, @RequestParam("idRole") Long idRol,
			@RequestParam(required = false) List<Long> idMenu, @RequestParam(required = false) List<Long> idMenuSub,
			@RequestParam("idHome") Long idHome) {

		System.out.println(
				"permisosadd: IdMenu: " + idMenu + " idMenuSub: " + idMenuSub + " idRol" + idRol + " idHome" + idHome);

		MenuRolModel menuRol = menuMgr.permisosAdd(idRol, idMenu, idMenuSub, idHome);
		menuRol.setUsername(UserHolder.getUsername());
		menuRol.setPagename("Administrar Usuarios");
		menuRol.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/manageUsers", "Administrar Usuarios"));
		menuRol.setSubCategory(TemplateBuilder.buildCategory("#", "Registro de Roles"));
		model.addAttribute("model7", menuRol);

		RoleModel ticket2 = rolMgr.getRoles();
		ticket2.setUsername(UserHolder.getUsername());
		ticket2.setPagename("Administrar Usuarios");
		ticket2.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/manageUsers", "Administrar Usuarios"));
		ticket2.setSubCategory(TemplateBuilder.buildCategory("#", "Registro de Roles"));
		model.addAttribute("model", ticket2);

		// CatOrganizacionModel organizaciones = rolMgr.getAllOrganizations();
		// model.addAttribute("model2", organizaciones);
		OrganizationModel organizaciones = mgr2.getOrganizations(); // ORGANIZACIONES PROVENIENTES DE LA CMDB
		model.addAttribute("model2", organizaciones);

		getMenusXrol(model);

		return "roles";
	}

	@RequestMapping(value = "/manageUsers/detalleRol", method = RequestMethod.POST)
	public String detalleRol(ModelMap model, @RequestParam("idRole") Long idRol) {

		System.out.println("**Si entro a detalleRol**" + idRol);

		MenusModel menusList = rolMgr.getMenusByRol(idRol);

		ArrayList<Menus> menusArray = new ArrayList<Menus>();

		MenusModel submenusModel = new MenusModel();
		MenusModel menusModel = new MenusModel();

		menusArray = menusList.getMenus();

		ArrayList<Menus> submenus = new ArrayList<Menus>();
		ArrayList<Menus> menus = new ArrayList<Menus>();

		for (int i = 0; i < menusArray.size(); i++) {

			Menus submenu = new Menus();
			Menus menu = new Menus();

			if (menusArray.get(i).getIdSubMenu() == null) {

				menu.setIdMenu(menusArray.get(i).getIdMenu());
				menu.setNombreMenu(menusArray.get(i).getNombreMenu());
				menu.setCheckedMenu(true);
				menu.setIdHome(menusArray.get(i).getIdHome());

				menus.add(menu);

			} else if (menusArray.get(i).getIdSubMenu() > 0) {

				submenu.setIdMenu(menusArray.get(i).getIdMenu());
				submenu.setIdSubMenu(menusArray.get(i).getIdSubMenu());
				submenu.setNombreMenu(menusArray.get(i).getNombreMenu());
				submenu.setCheckedMenu(true);

				submenus.add(submenu);

			}
		}

		menusModel.setMenus(menus);
		submenusModel.setMenus(submenus);

		model.addAttribute("model9", menusModel);
		model.addAttribute("model10", submenusModel);

		getMenusXrol(model);

		return "roles";
	}

//	@RequestMapping(value = "/manageUsers/configSla", method = RequestMethod.POST)
//	private String configSla(ModelMap model, @RequestParam("org") String org, @RequestParam("lat") Integer lat,
//			@RequestParam("paq") Integer paq, @RequestParam("dis") Integer dis) {
//		System.out.println("valores entregados al metodo " + org + "/" + lat + "/" + paq + "/" + dis + "/");
//		RoleModel slalist = rolMgr.getUpdateSla(org, lat, paq, dis);
//
//		RoleModel slalista = rolMgr.getconfigSla();
//		model.addAttribute("model9", slalista);
//
//		RoleModel ticket2 = rolMgr.getRolesSMDB();
//
//		ticket2.setUsername(UserHolder.getUsername());
//		ticket2.setPagename("Administrar Usuarios");
//		ticket2.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/manageUsers", "Administrar Usuarios"));
//		ticket2.setSubCategory(TemplateBuilder.buildCategory("#", "Registro de Roles"));
//		model.addAttribute("model", ticket2);
//
//		OrganizationModel organizaciones = mgr2.getOrganizations(); // ORGANIZACIONES PROVENIENTES DE LA CMDB
//		model.addAttribute("model2", organizaciones);
//
//		getMenusXrol(model);
//
//		CatMenuModel menusList = menuMgr.getPrincipalMenus();
//		model.addAttribute("model5", menusList);
//
//		OrigenHomeModel origenHome = mgr.getOrigenHome();
//		origenHome.setUsername(UserHolder.getUsername());
//		origenHome.setPagename("Administrar Usuarios");
//		origenHome.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/manageUsers", "Administrar Usuarios"));
//		origenHome.setSubCategory(TemplateBuilder.buildCategory("#", "Registro de Roles"));
//		model.addAttribute("model8", origenHome);
//
//		return "roles";
//	}

	private Long getMenusXrol(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserModel userModel = mgr2.getRolByUser(auth.getName());
		String rolRole = userModel.getTickets().get(0).getRole();
		RoleModel rolModel = rolMgr.getIdRol(rolRole);

		Long idmenu = new Long(0);
		if (rolModel.getRoles().get(0).getIdRole() != null) {
			// System.out.println("<==================== QUE VALOR TRAE EL ROL ?
			// ========================> " + rolModel);
			Long idRol = rolModel.getRoles().get(0).getIdRole();

			MenusModel menus = rolMgr.getMenusByRol(idRol);

			ArrayList<Menus> menusArray = new ArrayList<Menus>();

			CatMenuModel submenusModel = new CatMenuModel();

			menusArray = menus.getMenus();

			ArrayList<CatMenu> submenus = new ArrayList<CatMenu>();

			for (int i = 0; i < menusArray.size(); i++) {

				Menus objMenu = (Menus) menusArray.get(i);

				CatMenu submenu = new CatMenu();

				submenu.setIdSubMenu(objMenu.getIdSubMenu());
				submenu.setTituloPrincipal(objMenu.getTituloPrincipal());
				// submenu.setNombreMenu(objMenu.getNombreMenu());
				submenu.setPath(objMenu.getPath());
				submenu.setDescripcionMenu(objMenu.getDescripcionMenu());
				submenu.setIcono(objMenu.getIcono());

				submenus.add(submenu);
			}
			submenusModel.setMenus(submenus);

			menus.setUsername(UserHolder.getUsername());
			menus.setPagename("");
			menus.setCategory(TemplateBuilder.buildCategory("#", ""));

			model.addAttribute("model3", menus);

			model.addAttribute("model4", submenusModel);
			idmenu = menus.getMenus().get(0).getIdMenu();
		} else {
			model.addAttribute("model4", null);

		}

		return idmenu;

	}

	@RequestMapping(value = "/other/links", method = RequestMethod.GET)
	public String registroLinks(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String organization = mgr.getOrganizationByUser(auth.getName());

		LinksModel linksModel = linksMgr.getLinks(organization);
		linksModel.setUsername(UserHolder.getUsername());
		linksModel.setPagename("Otros Sitios");
		linksModel.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/other", "Otros Sitios"));
		linksModel.setSubCategory(TemplateBuilder.buildCategory("#", "Registro de Links"));
		model.addAttribute("model", linksModel);

		getMenusXrol(model);
		return "links";
	}

	@RequestMapping(value = "/other/linkadd", method = RequestMethod.POST)
	public String linkadd(ModelMap model, @RequestParam("nombreLink") String nombreLink,
			@RequestParam("link") String link, @RequestParam("descripcionLink") String descripcionLink) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String organization = mgr.getOrganizationByUser(auth.getName());

		LinksModel links = linksMgr.insertLinks(nombreLink, link, organization, descripcionLink);
		links.setUsername(UserHolder.getUsername());
		links.setPagename("Otros Sitios");
		links.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/other", "Otros Sitios"));
		links.setSubCategory(TemplateBuilder.buildCategory("#", "Registro de Links"));
		model.addAttribute("model1", links);

		LinksModel linksModel = linksMgr.getLinks();
		linksModel.setUsername(UserHolder.getUsername());
		linksModel.setPagename("Otros Sitios");
		linksModel.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/other", "Otros Sitios"));
		linksModel.setSubCategory(TemplateBuilder.buildCategory("#", "Registro de Links"));
		model.addAttribute("model", linksModel);

		getMenusXrol(model);

		return "links";
	}

	@RequestMapping(value = "/other/otherPlaces", method = RequestMethod.GET)
	public String otrosSitios(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String organization = mgr.getOrganizationByUser(auth.getName());

		LinksModel linksModel = linksMgr.getLinks(organization);
		linksModel.setUsername(UserHolder.getUsername());
		linksModel.setPagename("Otros Sitios");
		linksModel.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/other", "Otros Sitios"));
		linksModel.setSubCategory(TemplateBuilder.buildCategory("#", "Links de Monitoreo"));
		model.addAttribute("model", linksModel);

		getMenusXrol(model);
		return "otherPlaces";
	}

	@RequestMapping(value = "/manageUsers/cargarPDF", method = RequestMethod.GET)
	public ModelAndView uploadPage(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String organization = mgr.getOrganizationByUserID(auth.getName());

		System.out.println("ENTRE AL METODO QUE CARGA LA JSP DE CARGAR Documento");
		ModelAndView model1 = new ModelAndView("cargarPDF");
		model1.addObject("formUpload", new FileModel());

		log.info("Metodo obtiene carpetas de la organizacion");
		FileModel carpetas = mgr3.getListaRutas(auth.getName(), organization);
		carpetas.setPagename("Cargar Documentos");
		carpetas.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/manageUsers", "Administrar Usuarios"));
		carpetas.setSubCategory(TemplateBuilder.buildCategory("#", "Cargar Documentos"));
		model.addAttribute("carpeta", carpetas);

		FileModel archivo = new FileModel();
		archivo.setUsername(UserHolder.getUsername());
		archivo.setPagename("Cargar Documentos");
		archivo.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/manageUsers", "Administrar Usuarios"));
		archivo.setSubCategory(TemplateBuilder.buildCategory("#", "Cargar Documentos"));
		model.addAttribute("model", archivo);
		getMenusXrol(model);
		return model1;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView upload(@ModelAttribute("formUpload") FileModel fileUpload, BindingResult result, ModelMap model)
			throws IOException {
		System.out.println("ENTRE A LA CARGA DEL PDF" + fileUpload.getFiles().length);
		fileValidator.validate(fileUpload, result);

		FileModel archivo = new FileModel();
		archivo.setUsername(UserHolder.getUsername());
		archivo.setPagename("Cargar Documentos");
		archivo.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/manageUsers", "Administrar Usuarios"));
		archivo.setSubCategory(TemplateBuilder.buildCategory("#", "Cargar Documentos"));
		model.addAttribute("model", archivo);

		getMenusXrol(model);
		if (result.hasErrors()) {

			return new ModelAndView("cargarPDF");
		}
		return new ModelAndView("cargarPDF", "list", processUpload(fileUpload, model));
	}

	private List<String> processUpload(FileModel files, ModelMap model) throws IOException {
		List<String> fileNames = new ArrayList<String>();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String organization = mgr.getOrganizationByUserID(auth.getName());

		System.out.println("== VALOR DE LA ORGANIZACION PARA OBTENER EL ROL ===== " + organization);

		RoleModel ticket2 = mgr2.getRolesByOrganizacion(organization);
		ticket2.setUsername(UserHolder.getUsername());
		ticket2.setPagename("Administrar Usuarios");
		ticket2.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/manageUsers", "Administrar Usuarios"));
		ticket2.setSubCategory(TemplateBuilder.buildCategory("#", ""));
		// model.addAttribute("model2", ticket2);

		TicketModel ticket = mgr.getAllIncidents();
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Descargar PDF");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Informes"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", "Descarga PDF"));
		// model.addAttribute("model", ticket);

		long rol = ticket2.getRoles().get(0).getIdRole();
		String usr = ticket.getUsername();

		Calendar fecha = Calendar.getInstance();
		int anio = fecha.get(Calendar.YEAR);
		int mes = fecha.get(Calendar.MONTH) + 1;
		int dia = fecha.get(Calendar.DAY_OF_MONTH);

		String fechaCarga = fecha.get(Calendar.YEAR) + "-" + fecha.get(Calendar.MONTH) + "-"
				+ fecha.get(Calendar.DAY_OF_MONTH);

		CommonsMultipartFile[] commonsMultipartFiles = files.getFiles();
		for (CommonsMultipartFile multipartFile : commonsMultipartFiles) {

			String archivo = multipartFile.getOriginalFilename();
			String[] splitArchivo = archivo.split("\\.");
			// -------------
			FileModel filemodel = new FileModel();
			filemodel.setUsername(UserHolder.getUsername());
			filemodel.setPagename("Cargar PDF");
			filemodel.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/manageUsers", "Cargar PDF"));
			filemodel.setSubCategory(TemplateBuilder.buildCategory("#", ""));
			System.out.println("VALOR DE MODEL : " + filemodel.getPagename());
			// -------------
			if (splitArchivo.length >= 2) {
				String extencionArchivo = splitArchivo[splitArchivo.length - 1];
				if (null != extencionArchivo && (extencionArchivo.toLowerCase().equals("xlsx")
						|| extencionArchivo.toLowerCase().equals("xls") || extencionArchivo.toLowerCase().equals("docx")
						|| extencionArchivo.toLowerCase().equals("doc") || extencionArchivo.toLowerCase().equals("ppt")
						|| extencionArchivo.toLowerCase().equals("pptx") || extencionArchivo.toLowerCase().equals("pdf")
						|| extencionArchivo.toLowerCase().equals("txt"))) {

					/*
					 * Validacion de existencia de duplicidad de archivo Nueva funcionalidad por
					 * incidente - "Validar duplicidad de documentos"
					 */
					int fileExist = mgr3.getFileExist(UserHolder.getUsername(), organization, archivo);
					log.info("Existe archivo: " + fileExist);
					if (fileExist == 0) {
						log.info("Guardar archivo: " + archivo);
						int numCarpetas = mgr3.getExisteRuta(auth.getName(), files.getPath(), organization);
						// Guarda ruta carpeta en BD
						if (files.getPath().equals("seleccionar") || files.getPath().equals("cnueva")) {
							model.addAttribute("folderError", 0);
							log.info("Selecciona una carpeta");
							FileModel carpetasUpload = mgr3.getListaRutas(auth.getName(), organization);
							carpetasUpload.setPagename("Cargar Documentos");
							carpetasUpload.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/manageUsers",
									"Administrar Usuarios"));
							carpetasUpload.setSubCategory(TemplateBuilder.buildCategory("#", "Cargar Documentos"));
							model.addAttribute("carpeta", carpetasUpload);
						} else if (numCarpetas >= 10) {
							model.addAttribute("folderNum", 1);
							log.info("Selecciona una carpeta existente");
							FileModel carpetasUpload = mgr3.getListaRutas(auth.getName(), organization);
							carpetasUpload.setPagename("Cargar Documentos");
							carpetasUpload.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/manageUsers",
									"Administrar Usuarios"));
							carpetasUpload.setSubCategory(TemplateBuilder.buildCategory("#", "Cargar Documentos"));
							model.addAttribute("carpeta", carpetasUpload);
						} else {
							log.info("entro ruta: " + archivo);
							int idCarpeta = mgr3.guardarRuta(archivo, files.getPath(), files.getNivel(), organization);
							log.info("idCarpeta: " + files.getPath());
							// Guardar Archivo en BD ------
							int res = mgr3.guardarFile(multipartFile.getInputStream(), rol, usr, archivo, anio, mes,
									fechaCarga, organization, idCarpeta);
							// FileCopyUtils.copy(multipartFile.getBytes(), new
							// File("/opt/apache-tomcat-9.0.1/webapps/portaltpe/static/tmp/pdf/" +
							// multipartFile.getOriginalFilename()));
							ArrayList<mx.com.tp.smc.entity.File> archivoArray = new ArrayList<>();
							mx.com.tp.smc.entity.File fileObj = new mx.com.tp.smc.entity.File();
							fileObj.setSucces("" + res);
							archivoArray.add(fileObj);
							filemodel.setArchivo(archivoArray);
							model.addAttribute("fileModelSuccess", filemodel);
							System.out.println("Archivo cargado [" + archivo + "]"
									+ (res == 1 ? (" correctamente. - " + new Date())
											: (" con errores. - " + new Date())));

							log.info("Metodo obtiene carpetas de la organizacion upload");
							FileModel carpetas = mgr3.getListaRutas(auth.getName(), organization);
							carpetas.setPagename("Cargar Documentos");
							carpetas.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/manageUsers",
									"Administrar Usuarios"));
							carpetas.setSubCategory(TemplateBuilder.buildCategory("#", "Cargar Documentos"));
							model.addAttribute("carpeta", carpetas);
						}
						// ----------------------------
					} else {
						model.addAttribute("fileModelExist", fileExist);
						log.info("Archivo [" + archivo + "] con nombre duplicado");
					}

				}
			}
			fileNames.add(multipartFile.getOriginalFilename());

		}
		return fileNames;
	}

	@RequestMapping(value = "/informs/uploadReporteEjecutivo", method = RequestMethod.POST)
	public String dowload(ModelMap model) throws ClassNotFoundException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String organization = mgr.getOrganizationByUser(auth.getName());

		System.out.println("Entro a metodo dowload");
		FileModel pdf = mgr.getListaArchivos(organization);
		pdf.setUsername(UserHolder.getUsername());
		pdf.setPagename("Documentos del Proyecto");
		pdf.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Informes"));
		pdf.setSubCategory(TemplateBuilder.buildCategory("#", "Documentos del Proyecto"));
		model.addAttribute("archivo", pdf);
		getMenusXrol(model);
		return "DescargarArchivo";
	}

	// FIN CHRISTIAN J. -NETA SYSTEMS //

}
