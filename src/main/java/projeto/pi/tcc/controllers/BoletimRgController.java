package projeto.pi.tcc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoletimRgController {
	
	@RequestMapping("/")
	public String BoletimRg() {
		return "BoletimRg";
	}
}
