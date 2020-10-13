package app.controllers;

import app.forms.IndexForm;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {
	
	@GetMapping("/")
    public String main(IndexForm indexForm) {
        return "index";
    }
	
	@PostMapping("/")
	public String acessar(@Valid IndexForm indexForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "index";
		}
		
		return "redirect:/estacionamento/" + indexForm.getEstacionamento();
	}
	
}