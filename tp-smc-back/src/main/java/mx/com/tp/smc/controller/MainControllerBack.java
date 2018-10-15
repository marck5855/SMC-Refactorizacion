package mx.com.tp.smc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.Api;

@RestController
@Api(value="main")
@RequestMapping(value = "/")
public class MainControllerBack {

	//Welcome page
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView root() {
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		return model;
	}
	
	//Welcome page
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		return model;
	}
	
	//Welcome page
	@RequestMapping(value = "/index/", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		return model;
	}
	
	
}
