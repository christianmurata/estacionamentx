package app.forms;

import javax.validation.constraints.NotNull;

public class SaidaForm {

	@NotNull (message="O número do Ticket é obrigatório")
	private Integer ticket;

	public Integer getTicket() {
		return ticket;
	}

	public void setTicket(Integer ticket) {
		this.ticket = ticket;
	}
	
}
