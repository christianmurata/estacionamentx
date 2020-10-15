package app.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;

public class EstacionamentoForm {
	@NotNull
	@NotEmpty(message = "O nome do Estacionamento é origatório")
	private String nome;
	
	@NotNull(message = "O valor cobrado por hora é obrigatório")
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
