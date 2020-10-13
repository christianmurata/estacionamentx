package app.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;

public class IndexForm {
	@NotNull 
	@NotEmpty (message="O nome do estacionamento é obrigatório")
	private String estacionamento;

	public String getEstacionamento() {
		return estacionamento;
	}

	public void setEstacionamento(String estacionamento) {
		this.estacionamento = estacionamento;
	}
	
}
