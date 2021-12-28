package projeto.pi.tcc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TIController {
	
	@RequestMapping("/TI")
	public String TI() {
		System.out.println("chamou o método TI");
		return "TI";
	}
}
