package ifrn.pi.tcc.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ifrn.pi.tcc.models.BoletimRegional;
import ifrn.pi.tcc.models.FaleConosco;
import ifrn.pi.tcc.models.Solucao;
import ifrn.pi.tcc.models.Vacinacao;
import ifrn.pi.tcc.repositories.ConteudoRepository;
import ifrn.pi.tcc.repositories.FaleConoscoRepository;
import ifrn.pi.tcc.repositories.SolucaoRepository;
import ifrn.pi.tcc.repositories.VacinacaoRepository;

@Controller
public class ConteudoController {

	@Autowired
	private ConteudoRepository cr;

	@Autowired
	private FaleConoscoRepository fcr;

	@Autowired
	private VacinacaoRepository vcr;

	@Autowired
	private SolucaoRepository sr;

	// Boletim Regional

	@GetMapping("/formBoletim")
	public String form(BoletimRegional boletimRg) {
		return "conteudo/formBoletim";
	}

	@PostMapping("/boletimAdd")
	public String salvar(@Valid BoletimRegional boletimRg, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return form(boletimRg);
		}

		System.out.println(boletimRg);
		cr.save(boletimRg);
		attributes.addFlashAttribute("mensagem", "Boletim salvo com sucesso!");

		return "redirect:/boletimRg";
	}

	@GetMapping("/boletimRg")
	public ModelAndView listarBoletim() {
		List<BoletimRegional> br = cr.findAll();
		ModelAndView mv = new ModelAndView("/conteudo/boletimRg");
		mv.addObject("boletimRegional", br);
		return mv;
	}

	@GetMapping("/{id}/removerBr")
	public String apagarBoletim(@PathVariable Long id, RedirectAttributes attributes) {

		Optional<BoletimRegional> opt = cr.findById(id);

		if (!opt.isEmpty()) {
			BoletimRegional br = opt.get();
			cr.delete(br);
			attributes.addFlashAttribute("mensagem", "Boletim Removido com Sucesso!");
		}

		return "redirect:/boletimRg";
	}

	@GetMapping("/{id}/editBr")
	public ModelAndView editBr(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<BoletimRegional> opt = cr.findById(id);
		if (opt.isEmpty()) {
			md.setViewName("redirect:/boletimRg");
			return md;
		}
		BoletimRegional br = opt.get();
		md.setViewName("conteudo/formBoletim");
		md.addObject("boletimRg", br);

		return md;
	}

	// Fale Conosco

	@GetMapping("/formFaleConosco")
	public String formFC(FaleConosco fc) {
		return "conteudo/faleConosco";
	}

	@PostMapping("/faleConoscoAdd")
	public String salvarFC(@Valid FaleConosco fc, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return formFC(fc);
		}

		System.out.println(fc);
		fcr.save(fc);
		attributes.addFlashAttribute("mensagem", "Dúvida Enviada com Sucesso! Sua Dúvida Será Respondida no Email!");

		return "redirect:/formFaleConosco";
	}

	@GetMapping("/duvidas")
	public ModelAndView listarDuvidas() {
		List<FaleConosco> fc = fcr.findAll();
		ModelAndView mv = new ModelAndView("/conteudo/duvidas");
		mv.addObject("faleConosco", fc);
		return mv;
	}

	@GetMapping("/{id}/removerDuvidas")
	public String apagarDuvidas(@PathVariable Long id, RedirectAttributes attributes) {

		Optional<FaleConosco> opt = fcr.findById(id);

		if (!opt.isEmpty()) {
			FaleConosco fc = opt.get();
			fcr.delete(fc);
			attributes.addFlashAttribute("mensagem", "Dúvida Removida com Sucesso!");
		}

		return "redirect:/duvidas";
	}

	@GetMapping("/{id}")
	public ModelAndView detalharDuvidas(@PathVariable Long id, Solucao s) {
		ModelAndView mvd = new ModelAndView();
		Optional<FaleConosco> opt = fcr.findById(id);

		if (opt.isEmpty()) {
			mvd.setViewName("redirect:/telaInicial");
			return mvd;
		}

		mvd.setViewName("conteudo/detalhesDuvidas");
		FaleConosco fc = opt.get();
		mvd.addObject("fc", fc);

		List<Solucao> ss = sr.findByFc(fc);
		mvd.addObject("sl", ss);

		return mvd;
	}

	@PostMapping("/{idResposta}")
	public ModelAndView adicionarResposta(@PathVariable Long idFc, @Valid Solucao s, BindingResult result,
			RedirectAttributes attributes) {
		ModelAndView ar = new ModelAndView();
		if (result.hasErrors()) {
			return detalharDuvidas(idFc, s);
		}
		Optional<FaleConosco> opt = fcr.findById(idFc);
		if (opt.isEmpty()) {
			ar.setViewName("redirect:/telaInicial");
			return ar;
		}
		attributes.addFlashAttribute("mensagem", "Resposta Adicionada com Sucesso");

		FaleConosco fc = opt.get();
		s.setFc(fc);
		sr.save(s);
		ar.addObject("fc", fc);
		ar.setViewName("redirect:/{idResposta}");
		return ar;
	}

	@GetMapping("/{idResposta}/resposta/{idSolucao}/selecionar")
	public ModelAndView selecionarSolucao(@PathVariable Long idFc, @PathVariable Long idRep) {
		ModelAndView ss = new ModelAndView();
		Optional<FaleConosco> optFc = fcr.findById(idFc);
		Optional<Solucao> optSl = sr.findById(idRep);

		if (optFc.isEmpty() || optSl.isEmpty()) {
			ss.setViewName("redirect:/telaInicial");
			return ss;
		}

		FaleConosco fc = optFc.get();
		Solucao sl = optSl.get();

		if (fc.getId() != sl.getFc().getId()) {
			ss.setViewName("redirect:/eventos");
			return ss;
		}

		ss.setViewName("conteudo/detalhesDuvidas");
		ss.addObject("sl", sl);
		ss.addObject("fc", fc);
		ss.addObject("sl", sr.findByFc(fc));

		return ss;
	}
	
	@GetMapping("/{id}/apagarDuvida")
	public String apagarDuvida(@PathVariable Long id) {

		Optional<FaleConosco> opt = fcr.findById(id);

		if (!opt.isEmpty()) {

			FaleConosco fc = opt.get();
			fcr.delete(fc);
		}

		return "redirect:/duvidas";
	}

	@GetMapping("/{id}/apagarResposta")
	public String apagarResposta(@PathVariable Long id) {

		Optional<Solucao> opt = sr.findById(id);

		if (!opt.isEmpty()) {

			Solucao s = opt.get();
			sr.delete(s);
		}

		return "redirect:/duvidas";
	}

	// Vacinação

	@GetMapping("/formVacinacao")
	public String formVc(Vacinacao vc) {
		return "conteudo/formVacinacao";
	}

	@PostMapping("/formVacinacaoAdd")
	public String salvarFC(@Valid Vacinacao vc, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return formVc(vc);
		}

		System.out.println(vc);
		vcr.save(vc);
		attributes.addFlashAttribute("mensagem", "Relatório de Vacinação Adicionado com Sucesso!");

		return "redirect:/vacinacao";
	}

	@GetMapping("/vacinacao")
	public ModelAndView listarVacinacao() {
		List<Vacinacao> vc = vcr.findAll();
		ModelAndView mv = new ModelAndView("/conteudo/vacinacao");
		mv.addObject("vacinacao", vc);
		return mv;
	}

	@GetMapping("/{id}/removerVc")
	public String apagarVacinacao(@PathVariable Long id, RedirectAttributes attributes) {

		Optional<Vacinacao> opt = vcr.findById(id);

		if (!opt.isEmpty()) {
			Vacinacao vc = opt.get();
			vcr.delete(vc);
			attributes.addFlashAttribute("mensagem", "Relatório Vacinal Removido com Sucesso!");
		}

		return "redirect:/vacinacao";
	}

	@GetMapping("/{id}/edit")
	public ModelAndView ediVc(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Vacinacao> opt = vcr.findById(id);
		if (opt.isEmpty()) {
			md.setViewName("redirect:/vacinacao");
			return md;
		}
		Vacinacao vc = opt.get();
		md.setViewName("conteudo/formVacinacao");
		md.addObject("vacinacao", vc);

		return md;
	}
}