package app.models;

import java.util.Date;
import java.time.LocalDateTime;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Ticket {

	@Id
	private int numero;
	
	@CreationTimestamp
	private Date emitido;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date pago;
	
	@Column(columnDefinition="tinyint(1) default 1")
	private boolean status = true;
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "entrada_id", referencedColumnName = "id")
	private Entrada entrada;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "estacionamento_id", referencedColumnName = "id")
	private Estacionamento estacionamento;
	
	public Ticket() {
		
	}
	
	public Ticket(Entrada entrada, Estacionamento estacionamento) {
		this.entrada = entrada;
		this.estacionamento = estacionamento;
		
		this.numero = this.gerar();
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Date getEmitido() {
		return emitido;
	}

	public void setEmitido(Date emitido) {
		this.emitido = emitido;
	}

	public Date getPago() {
		return pago;
	}

	public void setPago(Date pago) {
		this.pago = pago;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Estacionamento getEstacionamento() {
		return estacionamento;
	}

	public void setEstacionamento(Estacionamento estacionamento) {
		this.estacionamento = estacionamento;
	}

	public Entrada getEntrada() {
		return entrada;
	}

	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
	}
	
	public int gerar() {
		LocalDateTime time = LocalDateTime.now();
		
		int numero = time.getMinute() + time.getSecond();
		String codigo = time.getDayOfMonth() + "" + time.getMonthValue() + "" + numero;
		
		return Integer.parseInt(codigo);
	}
	
}
