package ifrn.pi.tcc.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ifrn.pi.tcc.models.BoletimRegional;
import ifrn.pi.tcc.models.Papel;
import ifrn.pi.tcc.models.Usuario;
import ifrn.pi.tcc.models.Vacinacao;
import ifrn.pi.tcc.repositories.LoginRepository;
import ifrn.pi.tcc.repositories.PapeisRepository;

@Controller
public class UsuarioController {
	
	@Autowired
	private LoginRepository lr;
	
	@Autowired
	private PapeisRepository pr;
	
	@GetMapping("/login")
	public String login() {
		return "conteudo/login";
	}
	
	@GetMapping("/formUser")
	public String formUser(Usuario user) {
		return "conteudo/formUsuario";
	}

	@PostMapping("/formUserAdd")
	public String salvarUser(@Valid Usuario user, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return formUser(user);
		}
		List<Papel> papeis = pr.findByTipo('c');
		user.setPapeis(papeis);
		user.setPapel("Cidadão");
		user.setSenha(new BCryptPasswordEncoder().encode(user.getSenha()));
		lr.save(user);
		attributes.addFlashAttribute("mensagem", "Cadastrado com Sucesso!");
		return "redirect:/login";
	}
	
	@GetMapping("/painelUser")
	public ModelAndView listarUsers() {
		List<Usuario> us = lr.findAll();
		ModelAndView mv = new ModelAndView("/conteudo/painelUsers");
		mv.addObject("usuario", us);
		return mv;
	}
	
	@GetMapping("/{id}/removerUser")
	public String apagarUser(@PathVariable Long id, RedirectAttributes attributes) {

		Optional<Usuario> opt = lr.findById(id);

		if (!opt.isEmpty()) {
			Usuario user = opt.get();
			lr.delete(user);
			attributes.addFlashAttribute("mensagem", "Usuario Removido com Sucesso!");
		}

		return "redirect:/painelUser";
	}
	
	@GetMapping("/promover")
	public String editBr(@PathVariable Long id) {
		Optional<Usuario> opt = lr.findById(id);
		if (opt.isEmpty()) {
			return "redirect:/telaInicial";
		}
		List<Papel> papeis = pr.findByTipo('b');
		Usuario user = opt.get();
		user.setPapeis(papeis);
		user.setPapel("Cidadão");
		lr.save(user);

		return "redirect:/painelUser";
	}
}
