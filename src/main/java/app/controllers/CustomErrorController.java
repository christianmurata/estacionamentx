package app.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.boot.web.servlet.error.ErrorController;

@Controller
public class CustomErrorController implements ErrorController {

	@RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model erro) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		Integer statusCode = Integer.valueOf(status.toString());
	    
	    if (status != null) {	    
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	        	erro.addAttribute("status", statusCode);
	        	erro.addAttribute("mensagem", "Página não Encontrada");
	        	
	            return "errors/custom";
	        }
	        
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	        	erro.addAttribute("status", statusCode);
	        	erro.addAttribute("mensagem", "Erro interno, algo de errado não está certo.");
	        	
	            return "errors/custom";
	        }
	    }
	    
	    erro.addAttribute("status", statusCode);
    	erro.addAttribute("mensagem", "Erro inesperado, algo de errado não está certo.");
		
		//do something like logging
        return "errors/custom";
    }
	
	@Override
    public String getErrorPath() {
        return null;
    }
	
}