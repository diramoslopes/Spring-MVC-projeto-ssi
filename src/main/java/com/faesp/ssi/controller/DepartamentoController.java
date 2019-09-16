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

import com.faesp.ssi.model.Departamento;
import com.faesp.ssi.repository.Departamentos;
import com.faesp.ssi.repository.filter.DepartamentoFilter;
import com.faesp.ssi.service.DepartamentoService;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {
	
	private static final String CADASTRO_VIEW = "CadastroDepartamento";
	
	@Autowired
	private Departamentos departamentos;
	
	@Autowired
	private DepartamentoService departamentoService;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Departamento());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Departamento departamento, Errors errors, RedirectAttributes attributes) {
		
		if(errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		departamentoService.salvar(departamento);
		attributes.addFlashAttribute("mensagem", "Departamneto salvo com sucesso");
		return "redirect:/departamentos/novo";
	}
	
	@RequestMapping
	public ModelAndView busca(@ModelAttribute("filtro") DepartamentoFilter filtro) {
		List<Departamento> todosDepartamentos = departamentoService.filtrar(filtro);
		
		ModelAndView mv = new ModelAndView("BuscaDepartamento");
		mv.addObject("todosDepartamentos", todosDepartamentos);
		return mv;
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable Long codigo) {
		Optional<Departamento> departamento = departamentos.findById(codigo);
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("departamento", departamento.get());
		return mv;
	}
	
	@RequestMapping("/excluir/{codigo}")
	public String excluir(@PathVariable Long codigo) {
		Optional<Departamento> departamento = departamentos.findById(codigo);
		departamentoService.excluir(departamento.get().getId());
		return "redirect:/departamentos";
	}
}