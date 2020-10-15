package app.models;

import app.utils.Slug;

import java.util.Set;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

@Entity
public class Estacionamento {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	@NotNull(message = "O nome do Estacionamento é origatório")
	private String nome;
	
	@NotNull
	@Column(unique = true)
	private String slug;
	
	@NotNull(message = "O valor cobrado por hora é obrigatório")
	@NumberFormat(pattern = "#,###.###")
	private Float valor;
	
	@OneToMany(mappedBy="estacionamento")
	private Set<Vaga> vaga;
	
	public Estacionamento() {}
	
	public Estacionamento(String nome, Float valor) {
		this.setNome(nome);
		this.setValor(valor);
		this.setSlug(this.Slugfy());
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

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public String Slugfy() {
		return new Slug(nome).make();
	}

	@Override
	public String toString() {
		return "Estacionamento [id=" + id + ", nome=" + nome + "]";
	}
	
}