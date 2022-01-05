package ifrn.pi.eventos.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@GetMapping("/form2")
	public String form2(Usuario user) {
		return "eventos/formUsuario";
	}
	
	@PostMapping("/loginCadastrado")
	public String salvar(@Valid Usuario user, BindingResult result, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return form2(user);
		}
		lr.save(user);
		attributes.addFlashAttribute("mensagem", "Usuario Cadastrado com sucesso!");
		
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