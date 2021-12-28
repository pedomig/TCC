package projeto.pi.tcc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PrevencController {
	
	@RequestMapping("/Prevenc")
	public String Prevenc() {
		return "Prevec";
	}
}
