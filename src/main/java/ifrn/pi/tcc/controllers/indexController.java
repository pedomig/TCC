package ifrn.pi.tcc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController{
	
	@RequestMapping("/")
	public String index(){
		return "redirect:/telaInicial";
	}
	
	@RequestMapping("/telaInicial")
	public String ti(){
		return "conteudo/telaInicial";
	}
	
	@RequestMapping("/preven")
	public String prenv(){
		return "conteudo/prevencoes";
	}
}