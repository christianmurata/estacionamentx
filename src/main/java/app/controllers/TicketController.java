package app.controllers;

import app.models.Ticket;
import app.models.Estacionamento;

import app.repositories.TicketRepository;
import app.repositories.EstacionamentoRepository;

import app.exceptions.BadRequestException;
import app.exceptions.EstacionamentoNotFoundException;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ticket")
public class TicketController {
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	EstacionamentoRepository estacionamentoRepository;

	@GetMapping("/{slug}/{ticket}")
	public String function(@PathVariable String slug, @PathVariable String ticket, Model ticketView) {
		Estacionamento estacionamento = this.carregar(slug);
		
		int numero = Integer.parseInt(ticket);
		
		Optional<Ticket> checkTicket = ticketRepository.findByNumeroAndEstacionamentoIdAndStatusTrue(numero, estacionamento.getId());
		
		if(checkTicket.isEmpty())
			throw new BadRequestException("Ticket não Encontrado");
		
		Ticket dadosTicket = checkTicket.get();
		
		ticketView.addAttribute("estacionamento", estacionamento);
		
		ticketView.addAttribute("ticket", dadosTicket);
		ticketView.addAttribute("vaga", dadosTicket.getEntrada().getVaga());
		
		return "entrada/ticket";
	}
	
	public Estacionamento carregar(String slug) {
		Optional <Estacionamento> estacionamento = estacionamentoRepository.findBySlug(slug);
	    
	    if(estacionamento.isEmpty())
	    	throw new EstacionamentoNotFoundException("Estacionamento não encontrado");
	
	    return estacionamento.get();
	}
	
}
