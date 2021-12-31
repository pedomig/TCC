package ifrn.pi.eventos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ifrn.pi.eventos.models.Evento;

@Controller

public class indexController {

	@RequestMapping("/")
	public String index() {
		return "redirect:/eventos";
	}

	@GetMapping("/prevenc")
	public String preven() {
		return "eventos/prevencoes";
	}

	@GetMapping("/form")
	public String form(Evento evento) {
		return "eventos/formEvento";
	}
	
	@RequestMapping("/telaInicial")
	public String ti() {
		return "eventos/telaInicial";
	}
	
	@RequestMapping("/faleConosco")
	public String fc() {
		return "eventos/faleConosco";
	}
	@RequestMapping("Login")
	public String login() {
		return "eventos/Login";
	}
}