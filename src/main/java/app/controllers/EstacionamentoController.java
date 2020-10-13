package app.controllers;

import app.models.Estacionamento;
import app.repositories.EstacionamentoRepository;

import app.exceptions.EstacionamentoNotFoundException;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Validated
@Controller
@RequestMapping("/estacionamento")
public class EstacionamentoController {
	
	@Autowired
	EstacionamentoRepository estacionamentoRepository;
	
	@GetMapping("/")
	public String redirect() {
		return "redirect:/";
	}
	
	@GetMapping("/{slug}")
	public String index(@PathVariable String slug, Model dashboard) {
	    dashboard.addAttribute("estacionamento", this.carregar(slug));
	    
		return "dashboard";
	}
	
	@GetMapping("/{slug}/vagas")
	public String vagas(@PathVariable String slug, Model vagas) {	
		vagas.addAttribute("estacionamento", this.carregar(slug));
		
		return "vagas";
	}
	
	public Estacionamento carregar(String slug) {
		Optional <Estacionamento> estacionamento = estacionamentoRepository.findBySlug(slug);
	    
	    if(estacionamento.isEmpty())
	    	throw new EstacionamentoNotFoundException("Estacionamento não encontrado");
	
	    return estacionamento.get();
	}
	
}
