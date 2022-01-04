package ifrn.pi.eventos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ifrn.pi.eventos.models.Evento;
import ifrn.pi.eventos.models.Usuario;
import ifrn.pi.eventos.repositories.LoginRepository;

@Controller
public class indexController {
	
	@Autowired
	private LoginRepository lr;

	@GetMapping("/login")
	public String index() {
		return "Login";
	}

	@GetMapping("/prevenc")
	public String preven() {
		return "eventos/prevencoes";
	}

	@GetMapping("/form")
	public String form(Evento evento) {
		return "eventos/formEvento";
	}
	
	@RequestMapping("/form2")
	public String form2() {
		return "eventos/formUsuario";
	}
	
	@PostMapping("/login")
	public String cadastrado(Usuario user){
		System.out.print("deu certo!");
		lr.save(user);
		return "Login";
	}
	
	@GetMapping("/")
	public String ti() {
		return "eventos/telaInicial";
	}
	
	@RequestMapping("/faleConosco")
	public String fc() {
		return "eventos/faleConosco";
	}
}