package projeto.pi.tcc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import projeto.pi.models.Usuario;
import projeto.pi.repositorys.Bd_LoginRepository;

@Controller
public class SitesController {
	
	@Autowired
	private Bd_LoginRepository lr;

	@RequestMapping("/TI")
	public String TI() {
		System.out.println("chamou o método TI");
		return "TI";
	}

	@RequestMapping("/Specs")
	public String Specs() {
		return "Specs";
	}

	@RequestMapping("/Prevenc")
	public String Prevenc() {
		return "Prevec";
	}

	@RequestMapping("/FaleConosco")
	public String FaleConosco() {
		return "FaleConsco";
	}

	@RequestMapping("/BoletimRg")
	public String BoletimRg() {
		return "BoletimRg";
	}
	
	@PostMapping("/Cadastrado")
	public String AdicionarBD(Usuario user) {
		lr.save(user);
		return "Cadastrado";
	}
}