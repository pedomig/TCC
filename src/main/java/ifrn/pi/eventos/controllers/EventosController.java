package ifrn.pi.eventos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

import javax.validation.Valid;

import ifrn.pi.eventos.models.Convidado;
import ifrn.pi.eventos.models.Evento;
import ifrn.pi.eventos.models.Usuario;
import ifrn.pi.eventos.repositories.ConvidadoRepository;
import ifrn.pi.eventos.repositories.EventoRepository;

@Controller
@RequestMapping("/eventos")
public class EventosController {

	@Autowired
	private EventoRepository er;
	@Autowired
	private ConvidadoRepository cr;

	/*
	 * @GetMapping("/faleConosco") public String fc(Evento e) { return
	 * "eventos/faleConosco"; }
	 * 
	 * @GetMapping("/login") public String login() { return "eventos/login"; }
	 */

	@PostMapping
	public String salvar(@Valid Evento evento, BindingResult result, RedirectAttributes attributes) {

		/*
		 * if (result.hasErrors()) { return fc(evento); }
		 */

		System.out.println(evento);
		er.save(evento);
		attributes.addFlashAttribute("mensagem", "Evento salvo com sucesso!");

		return "redirect:/eventos";
	}

	@GetMapping
	public ModelAndView listar() {
		List<Evento> eventos = er.findAll();
		ModelAndView mv = new ModelAndView("/eventos/lista");
		mv.addObject("eventos", eventos);
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView detalhar(@PathVariable Long id, Convidado convidado) {
		ModelAndView md = new ModelAndView();
		Optional<Evento> opt = er.findById(id);

		if (opt.isEmpty()) {
			md.setViewName("redirect:/eventos");
			return md;
		}

		md.setViewName("eventos/detalhes");
		Evento evento = opt.get();
		md.addObject("evento", evento);

		List<Convidado> convidados = cr.findByEvento(evento);
		md.addObject("convidados", convidados);

		return md;
	}

	@PostMapping("/{idEvento}")
	public ModelAndView salvarConvidado(@PathVariable Long idEvento, @Valid Convidado convidado, BindingResult result,
			RedirectAttributes attributes) {
		ModelAndView md = new ModelAndView();
		if (result.hasErrors()) {
			return detalhar(idEvento, convidado);
		}

		System.out.println("Id do evento: " + idEvento);
		System.out.println(convidado);
		Optional<Evento> opt = er.findById(idEvento);
		if (opt.isEmpty()) {
			md.setViewName("redirect:/eventos");
			return md;
		}
		attributes.addFlashAttribute("mensagem", "Convidado salvo com sucesso");

		Evento evento = opt.get();
		convidado.setEvento(evento);
		cr.save(convidado);
		md.addObject("evento", evento);
		md.setViewName("redirect:/eventos/{idEvento}");

		return md;
	}

	@GetMapping("/{id}/selecionar")
	public ModelAndView selecionarEvento(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Evento> opt = er.findById(id);
		if (opt.isEmpty()) {
			md.setViewName("redirect:/eventos");
			return md;
		}
		Evento evento = opt.get();
		md.setViewName("eventos/formEvento");
		md.addObject("evento", evento);

		return md;

	}

	@GetMapping("/{idEvento}/convidados/{idConvidado}/selecionar")
	public ModelAndView selecionarConvidado(@PathVariable Long idEvento, @PathVariable Long idConvidado) {
		ModelAndView md = new ModelAndView();
		Optional<Evento> optEvento = er.findById(idEvento);
		Optional<Convidado> optConvidado = cr.findById(idConvidado);

		if (optEvento.isEmpty() || optConvidado.isEmpty()) {
			md.setViewName("redirect:/eventos");
			return md;
		}

		Evento evento = optEvento.get();
		Convidado convidado = optConvidado.get();

		if (evento.getId() != convidado.getEvento().getId()) {
			md.setViewName("redirect:/eventos");
			return md;
		}

		md.setViewName("eventos/detalhes");
		md.addObject("convidado", convidado);
		md.addObject("evento", evento);
		md.addObject("convidados", cr.findByEvento(evento));

		return md;
	}

	@GetMapping("/{id}/remover")
	public String apagarEvento(@PathVariable Long id, RedirectAttributes attributes) {

		Optional<Evento> opt = er.findById(id);

		if (!opt.isEmpty()) {
			Evento evento = opt.get();

			List<Convidado> convidados = cr.findByEvento(evento);

			cr.deleteAll(convidados);

			er.delete(evento);
			attributes.addFlashAttribute("mensagem", "Evento removido com sucesso!");
		}

		return "redirect:/eventos";
	}

	@GetMapping("/{id}/apagar")
	public String apagarConvidado(@PathVariable Long id) {

		Optional<Convidado> opt = cr.findById(id);

		if (!opt.isEmpty()) {

			Convidado convidado = opt.get();
			cr.delete(convidado);
		}

		return "redirect:/eventos";
	}

}