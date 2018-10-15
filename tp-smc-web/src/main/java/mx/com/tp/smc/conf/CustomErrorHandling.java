package mx.com.tp.smc.conf;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import mx.com.tp.smc.model.ErrorModel;

@ControllerAdvice
public class CustomErrorHandling {
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView noHandlerFound(NoHandlerFoundException ex) {
		ModelAndView model = new ModelAndView("error");
		ErrorModel error = new ErrorModel();
		error.setPagename("Error HTTP 404");
		error.setMessage("Recurso no encontrado");
		error.setDetail(ex.getRequestURL());	
		model.addObject("model", error);
		return model;
	}	
}
