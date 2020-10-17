package app.controllers;

import app.models.Vaga;
import app.models.Ticket;
import app.models.Entrada;
import app.models.Estacionamento;

import app.exceptions.BadRequestException;
import app.exceptions.EstacionamentoNotFoundException;

import app.forms.SaidaForm;

import app.repositories.EstacionamentoRepository;
import app.repositories.TicketRepository;
import app.repositories.VagaRepository;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

@Controller
@RequestMapping("/saida")
public class SaidaController {
	
	@Autowired
	VagaRepository vagaRepository;
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	EstacionamentoRepository estacionamentoRepository;

	@GetMapping("/{slug}")
	public String saida(@PathVariable String slug, Model saida, SaidaForm saidaForm) {		
		saida.addAttribute("estacionamento", this.carregar(slug));
		
		return "saida/saida";
	}
	
	@PostMapping("/{slug}")
	public String carregar(@PathVariable String slug, Model saida, @Valid SaidaForm saidaForm, BindingResult bindingResult) {		
		saida.addAttribute("estacionamento", this.carregar(slug));
		
		if(bindingResult.hasErrors()) {
			return "saida/saida";
		}
		
		Integer ticket = saidaForm.getTicket();
		Optional <Ticket> ticketValidation = ticketRepository.findById(ticket);
		
		if(ticketValidation.isEmpty()) {
			bindingResult.rejectValue("ticket", "error.ticket", "Ticket não encontrado");
			
			return "saida/saida";
		}
		
		if(!ticketValidation.get().isStatus()) {
			bindingResult.rejectValue("ticket", "error.ticket", "Ticket não encontrado");
			
			return "saida/saida";
		}
		
		return "redirect:/saida/" + slug + "/" + ticket;
	}

	@GetMapping("/{slug}/{ticket}")
	public String dadosTicket(@PathVariable String slug, @PathVariable String ticket, Model ticketView) {
		Estacionamento estacionamento = this.carregar(slug);
		
		int numero = Integer.parseInt(ticket);
		
		Optional<Ticket> checkTicket = ticketRepository.findByNumeroAndEstacionamentoIdAndStatusTrue(numero, estacionamento.getId());
		
		if(checkTicket.isEmpty())
			throw new BadRequestException("Ticket não Encontrado");
		
		Ticket dadosTicket = checkTicket.get();
		
		Entrada entrada = dadosTicket.getEntrada();
		Vaga vaga = entrada.getVaga();
		
		Date pago = new Date();
		Date emitido = dadosTicket.getEmitido();
		
		Long diff = pago.getTime() - emitido.getTime();
		Long horas = diff / (60*60*1000);
		Long minutos = (diff / (60*1000)) - (horas * 60);
		
		Float tempo = (float) (diff / (60*1000)) / 60;
		Float valor = estacionamento.getValor() * tempo; 
		
		ticketView.addAttribute("ticket", dadosTicket);
		ticketView.addAttribute("estacionamento", estacionamento);
		
		ticketView.addAttribute("vaga", vaga);
		ticketView.addAttribute("valor", valor);
		ticketView.addAttribute("horas", this.horasFormatadas(horas, minutos));
		
		return "saida/ticket";
	}
	
	@PostMapping("/{slug}/{ticket}")
	public String desocupar(@PathVariable String slug, @PathVariable String ticket, Model ticketView) {
		Estacionamento estacionamento = this.carregar(slug);
		
		int numero = Integer.parseInt(ticket);
		
		Optional<Ticket> checkTicket = ticketRepository.findByNumeroAndEstacionamentoIdAndStatusTrue(numero, estacionamento.getId());
		
		if(checkTicket.isEmpty())
			throw new BadRequestException("Ticket não Encontrado");
		
		// paga e desativa o ticket
		Ticket dadosTicket = checkTicket.get();
		
		dadosTicket.setStatus(false);
		dadosTicket.setPago(new Date());
		
		ticketRepository.save(dadosTicket);

		// despocupa a vaga
		Entrada entrada = dadosTicket.getEntrada();
		Vaga vaga = entrada.getVaga();
		
		vaga.setDisponivel(true);
		vagaRepository.save(vaga);
		
		// retorna para a saida
		ticketView.addAttribute("estacionamento", estacionamento);
		
		return "saida/sucesso";
	}
	
	
	
	public Estacionamento carregar(String slug) {
		Optional <Estacionamento> estacionamento = estacionamentoRepository.findBySlug(slug);
	    
	    if(estacionamento.isEmpty())
	    	throw new EstacionamentoNotFoundException("Estacionamento não encontrado");
	
	    return estacionamento.get();
	}
	
	public String horasFormatadas(Long horas, Long minutos) {
		String txtHoras = (horas < 10) ? "0" + horas : "" + horas;
		String txtMinutos = (minutos < 10) ? "0" + minutos : "" + minutos;
			
		return txtHoras + ":" + txtMinutos;
	}
	
}
