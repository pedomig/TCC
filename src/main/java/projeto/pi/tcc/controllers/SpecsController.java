package projeto.pi.tcc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpecsController {
	
	@RequestMapping("/")
	public String Specs() {
		return "Specs";
	}
}
