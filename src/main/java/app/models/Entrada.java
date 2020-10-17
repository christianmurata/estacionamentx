package app.models;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;

@Entity
public class Entrada {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "vaga_id", referencedColumnName = "id")
	private Vaga vaga;
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "veiculo_id", referencedColumnName = "id")
	private Veiculo veiculo;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "estacionamento_id", referencedColumnName = "id")
	private Estacionamento estacionamento;
	
	public Entrada() {
		
	}
	
	public Entrada(Vaga vaga, Veiculo veiculo, Estacionamento estacionamento) {
		this.vaga = vaga;
		this.veiculo = veiculo;
		this.estacionamento = estacionamento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

	public Estacionamento getEstacionamento() {
		return estacionamento;
	}

	public void setEstacionamento(Estacionamento estacionamento) {
		this.estacionamento = estacionamento;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
}
