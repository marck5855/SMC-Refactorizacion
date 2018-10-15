package mx.com.tp.smc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mx.com.tp.smc.mgr.HomeMgr;
import mx.com.tp.smc.model.TicketModel;
import mx.com.tp.smc.util.FileValidator;
import mx.com.tp.smc.util.TemplateBuilder;
import mx.com.tp.smc.util.UserHolder;

@Controller
@RequestMapping(value = "/manageUsers")
public class FileController {

	@Autowired
	private HomeMgr mgr;
	
	@Autowired
	FileValidator fileValidator;

	@RequestMapping(value = "/informs/uploadReporteEjecutivo", method = RequestMethod.GET)
	public String uploadReporteEjecutivo(ModelMap model) {
		TicketModel ticket = mgr.getAllIncidents();
		ticket.setUsername(UserHolder.getUsername());
		ticket.setPagename("Descargar PDF");
		ticket.setCategory(TemplateBuilder.buildCategory("/portaltpe/home/informs", "Incidentes Historicos"));
		ticket.setSubCategory(TemplateBuilder.buildCategory("#", ""));
		model.addAttribute("model", ticket);
		return "uploadReporteEjecutivo";
	}
	
}
