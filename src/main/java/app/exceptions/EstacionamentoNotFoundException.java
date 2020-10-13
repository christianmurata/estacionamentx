package app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Estacionamento Not Found")
public class EstacionamentoNotFoundException extends RuntimeException {
	
	private String message;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EstacionamentoNotFoundException(String message) {
		super(message);
		
		this.message = message;		
	}

	@Override
	public String getMessage() {
		return this.message;
	}
	
}
