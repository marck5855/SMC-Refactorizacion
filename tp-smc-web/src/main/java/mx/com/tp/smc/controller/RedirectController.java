package mx.com.tp.smc.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/redirectP")
public class RedirectController {

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String redireccionar(ModelMap model) throws IOException, ClassNotFoundException, SQLException {

		return "totalplay";
	}

}