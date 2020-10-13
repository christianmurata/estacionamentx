package app.controllers;

import app.models.Estacionamento;
import app.forms.EstacionamentoForm;
import app.repositories.EstacionamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CadastroController {
	
	@Autowired
	EstacionamentoRepository estacionamentoRepository;
	
	@GetMapping("/cadastro")
	public String cadastrar(EstacionamentoForm estacionamentoform) {
		return "cadastro";
	}
	
	@PostMapping("/cadastro")
	public String acessar(@Valid EstacionamentoForm estacionamentoForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "cadastro";
		}
		
		String nome = estacionamentoForm.getNome();
		Float valor = estacionamentoForm.getValor();
		
		try {
			Estacionamento novoEstacionamento = estacionamentoRepository.save(new Estacionamento(nome, valor));
			
			return "redirect:/estacionamento/" + novoEstacionamento.getSlug();
		
		} catch (RuntimeException erro) {
			return erro.getMessage();
		}
	}
}
