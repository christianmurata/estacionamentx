package app.exceptions;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.ClassUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {


  @ExceptionHandler(IOException.class)
  public String handleIOException(IOException ex, HttpServletRequest request) {
	  return ClassUtils.getShortName(ex.getClass());
  }
  
  @ExceptionHandler(NumberFormatException.class)
  public ModelAndView errorFormat(NumberFormatException ex, HttpServletRequest request) {
	  	ModelAndView notFound = new ModelAndView("errors/404");
	    
	  	notFound.addObject("message", ex.getMessage());
	    
	    return notFound;
  }
  
  @ExceptionHandler(EstacionamentoNotFoundException.class)
  public ModelAndView errorEstacionamento(EstacionamentoNotFoundException ex, HttpServletRequest request) {
	  	ModelAndView notFound = new ModelAndView("errors/404");
	    
	  	notFound.addObject("message", ex.getMessage());
	    
	    return notFound;
  }
  
}
