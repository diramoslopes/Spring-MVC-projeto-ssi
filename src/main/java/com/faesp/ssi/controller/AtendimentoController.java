package com.faesp.ssi.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faesp.ssi.model.Atendimento;
import com.faesp.ssi.model.StatusAtendimento;
import com.faesp.ssi.repository.Atendimentos;
import com.faesp.ssi.repository.filter.AtendimentoFilter;
import com.faesp.ssi.service.AtendimentoService;

@Controller
@RequestMapping("/atendimentos")
public class AtendimentoController {
	
	private static final String CADASTRO_VIEW = "CadastroAtendimento";
	
	@Autowired
	private Atendimentos atendimentos;
	
	@Autowired
	private AtendimentoService atendimentoService;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Atendimento());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Atendimento atendimento, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		try {
			atendimentoService.salvar(atendimento);
			attributes.addFlashAttribute("mensagem", "Atendimento salvo com sucesso!");
			return "redirect:/atendimentos/novo";
		} catch (DataIntegrityViolationException e) {
			errors.rejectValue("data", null, "Formato de data invalido");
			return CADASTRO_VIEW;
		}
	}
	
	@RequestMapping
	public ModelAndView busca(@ModelAttribute("filtro") AtendimentoFilter filtro) {
		List<Atendimento> todosAtendimentos = atendimentoService.filtrar(filtro);
		
		ModelAndView mv = new ModelAndView("BuscaAtendimento");
		mv.addObject("todosAtendimentos", todosAtendimentos);
		return mv;
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable Long codigo) {
		Optional<Atendimento> atendimento = atendimentos.findById(codigo);
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("atendimento", atendimento.get());
		return mv;
	}
	
	@RequestMapping("/excluir/{codigo}")
	public String excluir(@PathVariable Long codigo) {
		Optional<Atendimento> atendimento = atendimentos.findById(codigo);
		atendimentos.deleteById(atendimento.get().getId());
		return "redirect:/atendimentos";
	}
	
	@RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long codigo, RedirectAttributes attributes) {
		atendimentoService.excluir(codigo);
		attributes.addFlashAttribute("mensagem", "Titulo excluido com sucesso!");
		return "redirect:/atendimentos";
	}
	
	@RequestMapping(value = "/{codigo}/resolver", method = RequestMethod.PUT)
	public @ResponseBody String resolver(@PathVariable Long codigo) {
		return atendimentoService.resolver(codigo);
	}
	
	@ModelAttribute("todosOsStatus")
	public List<StatusAtendimento> todosOsStatus(){
		return Arrays.asList(StatusAtendimento.values());
	}
}