package ifrn.pi.eventos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class indexController {
	
	@RequestMapping("/")
	public String index(){
		return "redirect:/eventos";
	}
	
	@RequestMapping("eventos/vacinacao")
	public String vacin(){
		return "vacinacao";
	}
}