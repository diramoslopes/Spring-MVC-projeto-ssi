package com.faesp.ssi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faesp.ssi.model.Impressora;
import com.faesp.ssi.repository.Impressoras;
import com.faesp.ssi.repository.filter.ImpressoraFilter;
import com.faesp.ssi.service.ImpressoraService;

@Controller
@RequestMapping("/impressoras")
public class ImpressoraController {
	
	public static final String CADASTRO_VIEW = "CadastroImpressora";
	
	@Autowired
	private Impressoras impressoras;
	
	@Autowired
	private ImpressoraService impressoraService;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Impressora());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Impressora impressora, Errors errors, RedirectAttributes attributes) {
		
		if(errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		impressoraService.salvar(impressora);
		attributes.addFlashAttribute("mensagem", "Impressora salva com sucesso!");
		return "redirect:/impressoras/novo";
	}
	
	@RequestMapping
	public ModelAndView busca(@ModelAttribute("filtro") ImpressoraFilter filtro) {
		List<Impressora> todasImpressoras = impressoraService.filtrar(filtro);
		
		ModelAndView mv = new ModelAndView("BuscaImpressora");
		mv.addObject("todasImpressoras", todasImpressoras);
		return mv;
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable Long codigo) {
		Optional<Impressora> impressora = impressoras.findById(codigo);
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("impressora", impressora.get());
		return mv;
	}
	
	@RequestMapping("/excluir/{codigo}")
	public String excluir(@PathVariable Long codigo) {
		Optional<Impressora> impressora = impressoras.findById(codigo);
		impressoraService.excluir(impressora.get().getId());
		return "redirect:/impressoras";
	}
}