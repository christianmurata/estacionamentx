package app.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Estacionamento {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	@NotNull(message = "Nome não pode ser NULL")
	private String nome;
	
	public Estacionamento(String nome) {
		this.setNome(nome);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Estacionamento [id=" + id + ", nome=" + nome + "]";
	}	
	
}