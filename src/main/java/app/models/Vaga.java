package app.models;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

@Entity
public class Vaga {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull(message="O número da vaga é obrigatório")
	private int numero;
	
	@NotNull(message="A categoria da vaga é obrigatória")
	private String categoria;
	
	@Column(columnDefinition="tinyint(1) default 1")
	private boolean disponivel = true;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "estacionamento_id", referencedColumnName = "id")
	private Estacionamento estacionamento;
	
	public Vaga() {}
	
	public Vaga(int numero, String categoria, Estacionamento estacionamento) {
		this.numero = numero;
		this.categoria = categoria;
		this.estacionamento = estacionamento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
	
	public Estacionamento getEstacionamento() {
		return estacionamento;
	}

	public void setEstacionamento(Estacionamento estacionamento) {
		this.estacionamento = estacionamento;
	}
	
}
