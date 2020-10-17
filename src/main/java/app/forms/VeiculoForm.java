package app.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;

public class VeiculoForm {
	
	@NotNull 
	@NotEmpty (message="A marca do veículo é obrigatória")
	private String marca;
	
	@NotNull 
	@NotEmpty (message="O modelo do veículo é obrigatório")
	private String modelo;

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}	
	
}
