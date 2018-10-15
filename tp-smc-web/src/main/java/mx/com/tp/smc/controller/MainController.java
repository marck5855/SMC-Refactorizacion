package mx.com.tp.smc.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import mx.com.tp.smc.model.MainModel;
import mx.com.tp.smc.util.TemplateBuilder;
import mx.com.tp.smc.util.UserHolder;

@Controller //(value="MainControllerWeb")
public class MainController {
	
	@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public String indexPage(ModelMap model) {
		model.addAttribute("user", UserHolder.getUsername());
		return "redirect:login";
	}
	
	@RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		MainModel main = new MainModel();
		main.setUsername(UserHolder.getUsername());
		main.setPagename("Acceso no autorizado");
		main.setCategory(TemplateBuilder.buildCategory("#", "Acceso denegado"));
		model.addAttribute("model", main);
		return "accessdenied";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		System.out.println("ESTOY AQUI EN LOGIN ");
		return "login";
	}

	@RequestMapping(value="/cerrado", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		System.out.println("ESTOY AQUI ==");
		String var = "redirect:/login?logout";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).ñ
			
			archivo = new File("/opt/apache-tomcat-9.0.1/temp/" + "rolesCreados.txt");
			//archivo = new File("/home/implementacion/apache-tomcat-9.0.1/temp/" + "rolesCreados.txt");
			System.out.println("=== VALIDA URL DEL ARCHIVO DEL SERVIDOR ===" + archivo);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			String linea;
			while ((linea = br.readLine()) != null) {
				if(null != linea && !linea.equals("")) {
					if(linea.equals("ROLCREADO")) {
						// Ejecutar Shell
						// Runtime r = Runtime.getRuntime();
						// String comand = "sh /opt/shell/run.ksh";//"C:\\opt\\shell\\run.bat";
						// Process p = r.exec(comand);
						var = "redirect:/redirectP";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta
			// una excepcion.
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return var;
	}
}