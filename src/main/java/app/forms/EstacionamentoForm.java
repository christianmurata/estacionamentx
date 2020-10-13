package app.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;

public class EstacionamentoForm {
	@NotNull
	@NotEmpty (message = "Nome não pode ser NULL")
	private String nome;
	
	@NotNull(message = "Valor não pode ser NULL")
	private Float valor;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}
	
}
