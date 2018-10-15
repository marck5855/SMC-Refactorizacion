package mx.com.tp.smc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="operator")
public class OperatorController {
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public String homeAdminPage() {
		return "redirect:home";
	}	
}
