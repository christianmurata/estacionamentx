package app.controllers;

import app.models.*;
import app.repositories.*;

import app.forms.VeiculoForm;

import app.exceptions.NotFoundException;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@Controller
@RequestMapping("/entrada")
public class EntradaController {
	
	@Autowired
	VagaRepository vagaRepository;
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	VeiculoRepository veiculoRepository;
	
	@Autowired
	EntradaRepository entradaRepository;
	
	@Autowired
	EstacionamentoRepository estacionamentoRepository;
	
	@GetMapping("/{slug}")
	public String entrada(@PathVariable String slug, Model entrada) {		
		entrada.addAttribute("estacionamento", this.carregar(slug));
		
		return "entrada/entrada";
	}
	
	@GetMapping("/{slug}/{categoria}")
	public String vagas(@PathVariable String slug, @PathVariable String categoria, Model vagas)  {
		if(!categoria.equals("carro") && !categoria.equals("moto"))
	    	throw new NotFoundException("Categoria não encontrada");
		
		Estacionamento estacionamento  = this.carregar(slug);
		List <Vaga> listVagas = vagaRepository.findAllByEstacionamentoIdAndCategoriaOrderByNumero(estacionamento.getId(), categoria);
		
		vagas.addAttribute("vagas", listVagas);
		vagas.addAttribute("estacionamento", estacionamento);
			
		return "entrada/vagas";
	}
	
	@GetMapping("/{slug}/{categoria}/{vaga}")
	public String veiculo(@PathVariable String slug, @PathVariable String categoria, 
			@PathVariable String vaga, Model veiculo, VeiculoForm veiculoForm)  {
		
		if(!categoria.equals("carro") && !categoria.equals("moto"))
	    	throw new NotFoundException("Categoria não encontrada");
		
		Estacionamento estacionamento  = this.carregar(slug);
		
		veiculo.addAttribute("vaga", vaga);
		veiculo.addAttribute("categoria", categoria);
		veiculo.addAttribute("estacionamento", estacionamento);
			
		return "entrada/veiculo";
	}
	
	@PostMapping("/{slug}/{categoria}/{vaga}")
	public String emitirTicket(@PathVariable String slug, @PathVariable String categoria, 
			@PathVariable String vaga, Model veiculo, @Valid VeiculoForm veiculoForm, BindingResult bindingResult) {
		
		if(!categoria.equals("carro") && !categoria.equals("moto"))
	    	throw new NotFoundException("Categoria não encontrada");
		
		Estacionamento estacionamento  = this.carregar(slug);
		
		veiculo.addAttribute("vaga", vaga);
		veiculo.addAttribute("categoria", categoria);
		veiculo.addAttribute("estacionamento", estacionamento);
		
		if(bindingResult.hasErrors()) {			
			return "entrada/veiculo";
		}
		
		Optional<Vaga> vagaSelecionada = vagaRepository.findById(Integer.parseInt(vaga));
		
		if(vagaSelecionada.isEmpty()) {
	    	throw new NotFoundException("Vaga não encontrada");
		}
		
		if(!vagaSelecionada.get().isDisponivel()) {
	    	throw new NotFoundException("A vaga selecionada não está disponível");
		}
		
		String marca = veiculoForm.getMarca();
		String modelo = veiculoForm.getMarca();
		
		Veiculo novoVeiculo = veiculoRepository.save(
			new Veiculo(marca, modelo)
		);
		
		Entrada entrada = entradaRepository.save(
			new Entrada(
				vagaSelecionada.get(),
				novoVeiculo,
				estacionamento
			)
		);
		
		Ticket ticket = ticketRepository.save(
			new Ticket(
				entrada, 
				estacionamento
			)
		);
		
		Vaga ocupada = vagaSelecionada.get();
		
		ocupada.setDisponivel(false);
		vagaRepository.save(ocupada);
		
//		veiculo.addAttribute("vagaNumero", ocupada.getNumero());
		
		return "redirect:/ticket/" + slug + "/" + ticket.getNumero();
	}
	
	public Estacionamento carregar(String slug) {
		Optional <Estacionamento> estacionamento = estacionamentoRepository.findBySlug(slug);
	    
	    if(estacionamento.isEmpty())
	    	throw new NotFoundException("Estacionamento não encontrado");
	
	    return estacionamento.get();
	}
	
}
