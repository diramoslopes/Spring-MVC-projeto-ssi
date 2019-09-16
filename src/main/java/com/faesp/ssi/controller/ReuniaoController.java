package com.faesp.ssi.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faesp.ssi.model.Reuniao;
import com.faesp.ssi.model.StatusReuniao;
import com.faesp.ssi.repository.Reunioes;
import com.faesp.ssi.repository.filter.ReuniaoFilter;
import com.faesp.ssi.service.ReuniaoService;

@Controller
@RequestMapping("/reunioes")
public class ReuniaoController {
	
	private static final String CADASTRO_VIEW = "CadastrarReuniao";
	
	@Autowired
	private Reunioes reunioes;
	
	@Autowired
	private ReuniaoService reuniaoService;
	
	@RequestMapping("/agendar")
	public ModelAndView agendar() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Reuniao());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Valid Reuniao reuniao, Errors errors, RedirectAttributes attributes) {
		if(errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		reuniaoService.salvar(reuniao);
		attributes.addFlashAttribute("mensagem", "Reunião salva com sucesso!");
		return "redirect:/reunioes/agendar";
	}
	
	@RequestMapping
	public ModelAndView busca(@ModelAttribute("filtro") ReuniaoFilter filtro) {
		List<Reuniao> todasReunioes = reuniaoService.filtrar(filtro);
		
		ModelAndView mv = new ModelAndView("BuscaReunioes");
		mv.addObject("todasReunioes", todasReunioes);
		return mv;
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable Long codigo) {
		Optional<Reuniao> reuniao = reunioes.findById(codigo);
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("reuniao", reuniao.get()); 
		return mv;
	}
	
	@RequestMapping("/excluir/{codigo}")
	public String excluir(@PathVariable Long codigo) {
		Optional<Reuniao> reuniao = reunioes.findById(codigo);
		reuniaoService.excluir(reuniao.get().getId());
		return "redirect:/reunioes";
	}
	
	@RequestMapping(value = "/{codigo}/realizar" ,  method = RequestMethod.PUT)
	public @ResponseBody String realizar(@PathVariable Long codigo) {
		return reuniaoService.realizar(codigo);
	}
	
	@ModelAttribute("todosOsStatus")
	public List<StatusReuniao> todosOsStatus(){
		return Arrays.asList(StatusReuniao.values());
	}
}
