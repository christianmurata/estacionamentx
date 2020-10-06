package app.controllers;

import app.models.Estacionamento;
import app.repositories.EstacionamentoRepository;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Validated
@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoController {
	
	@Autowired
	EstacionamentoRepository estacionamentoRepository;
	
	@PostMapping("/criar")
    public Estacionamento create(@RequestBody Map<String, String> body) {
        String nome = body.get("nome");

    	return estacionamentoRepository.save(new Estacionamento(nome));
    }
	
}
