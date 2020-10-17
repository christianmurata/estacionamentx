package app.controllers;

import app.models.Estacionamento;
import app.repositories.EstacionamentoRepository;

import app.forms.IndexForm;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {
	
	@Autowired
	EstacionamentoRepository estacionamentoRepository;
	
	@GetMapping("/")
    public String main(IndexForm indexForm) {
        return "index";
    }
	
	@PostMapping("/")
	public String acessar(@Valid IndexForm indexForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "index";
		}
		
		String slug = indexForm.getEstacionamento();
		
		Optional<Estacionamento> estacionamento = estacionamentoRepository.findBySlug(slug);
		
		if(estacionamento.isEmpty()) {
			bindingResult.rejectValue("estacionamento", "error.estacionamento", "Estacionamento não encontrado");
			
			return "index";
		}
		
		return "redirect:/estacionamento/" + indexForm.getEstacionamento();
	}
	
}