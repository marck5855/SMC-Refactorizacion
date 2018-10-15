package mx.com.tp.smc.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.tp.smc.entity.Enviroment;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/environment")
public class EnvironmentContext {
	
	@Autowired
	private ServletContext servletContext;

	@RequestMapping(value = "/get/", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Enviroment> getEnvironment(@RequestParam("name") String name) {
		try {
			Enviroment respEnvironment = new Enviroment();
			
			String env = servletContext.getInitParameter(name);
		    System.out.println(">> EnvironmentContext.getEnvironment env: " + env + " <<");
			if (null != env && !env.equals("")) {
				respEnvironment.setStatus("OK");
				respEnvironment.setEnviroment(env);
			} else {
				respEnvironment.setStatus("NOK");
				respEnvironment.setEnviroment("");
			}
			return new ResponseEntity<Enviroment>(respEnvironment, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Excepcion >> " + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<Enviroment>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
