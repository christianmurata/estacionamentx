package app.forms;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;

public class VagaForm {

	@Min(value = 1, message = "O número deve ser maior ou igual à 1")
	@NotNull (message = "O número da vaga é obrigatório")
	private Integer numero;
	
	@NotNull
	@NotEmpty (message="A categoria da vaga é obrigatória")
	private String categoria;

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
}
