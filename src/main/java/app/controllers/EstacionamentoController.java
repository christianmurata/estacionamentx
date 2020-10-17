package app.controllers;

import app.models.Vaga;
import app.models.Estacionamento;

import app.forms.VagaForm;

import app.repositories.VagaRepository;
import app.repositories.EstacionamentoRepository;

import app.exceptions.NotFoundException;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@Controller
@RequestMapping("/estacionamento")
public class EstacionamentoController {
	
	@Autowired
	VagaRepository vagaRepository;
	
	@Autowired
	EstacionamentoRepository estacionamentoRepository;
	
	@GetMapping("/")
	public String redirect() {
		return "redirect:/";
	}
	
	@GetMapping("/{slug}")
	public String inicio(@PathVariable String slug, Model dashboard) {
		Estacionamento estacionamento = this.carregar(slug);
		
	    dashboard.addAttribute("estacionamento", estacionamento);
	    dashboard.addAttribute("vagasTotal", vagaRepository.countByEstacionamentoId(estacionamento.getId()));
	    dashboard.addAttribute("vagasOcupadas", vagaRepository.countByEstacionamentoIdAndDisponivel(estacionamento.getId(), false));
	    dashboard.addAttribute("vagasDisponiveis", vagaRepository.countByEstacionamentoIdAndDisponivel(estacionamento.getId(), true));
	    
		return "dashboard";
	}
	
	@GetMapping("/{slug}/vagas")
	public String vagas(@PathVariable String slug, Model vagas, VagaForm vagaForm) {	
		this.build(vagas, this.carregar(slug));
		
		return "vagas";
	}
	
	@PostMapping("/{slug}/vagas")
	public String adicionar(@PathVariable String slug, Model vagas, @Valid VagaForm vagaForm, BindingResult bindingResult) {
		Estacionamento estacionamento  = this.carregar(slug);
		
		this.build(vagas, estacionamento);
		
		if (bindingResult.hasErrors()) {
			return "vagas";
		}
		
		int numero = vagaForm.getNumero();
		String categoria = vagaForm.getCategoria();
		
		Optional<Vaga> vaga = vagaRepository.findOneByEstacionamentoIdAndNumero(estacionamento.getId(), numero);
		
		if(!vaga.isEmpty()) {
			bindingResult.rejectValue("numero", "error.numero", "Já existe uma vaga com esse número");
			
			return "vagas";
		}
		
		vagaRepository.save(new Vaga(numero, categoria, estacionamento));
		
		return "redirect:/estacionamento/" + slug + "/vagas";
	}
	
	public void build(Model model, Estacionamento estacionamento) {
		List <Vaga> listVagas = vagaRepository.findAllByEstacionamentoIdOrderByNumero(estacionamento.getId());
		
		model.addAttribute("vagas", listVagas);
		model.addAttribute("estacionamento", estacionamento);
	}
	
	public Estacionamento carregar(String slug) {
		Optional <Estacionamento> estacionamento = estacionamentoRepository.findBySlug(slug);
	    
	    if(estacionamento.isEmpty())
	    	throw new NotFoundException("Estacionamento não encontrado");
	
	    return estacionamento.get();
	}
	
}
