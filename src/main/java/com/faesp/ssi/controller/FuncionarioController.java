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

import com.faesp.ssi.model.Funcionario;
import com.faesp.ssi.repository.Funcionarios;
import com.faesp.ssi.repository.filter.FuncionarioFilter;
import com.faesp.ssi.service.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	private static final String CADASTRO_VIEW = "CadastroFuncionario";
	
	@Autowired
	private Funcionarios funcionarios;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Funcionario());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Funcionario funcionario, Errors errors, RedirectAttributes attributes) {

		if(errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		funcionarioService.salvar(funcionario);
		attributes.addFlashAttribute("mensagem", "Funcionario salvo com sucesso!");
		return "redirect:/funcionarios/novo";
	}
	
	@RequestMapping
	public ModelAndView busca(@ModelAttribute("filtro") FuncionarioFilter filtro) {
		List<Funcionario> todosFuncionarios = funcionarioService.filtrar(filtro);
		
		ModelAndView mv = new ModelAndView("BuscaFuncionario");
		mv.addObject("todosFuncionarios", todosFuncionarios);
		return mv;
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable Long codigo) {
		Optional<Funcionario> funcionario = funcionarios.findById(codigo);
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("funcionario", funcionario.get());
		return mv;
	}
	
	@RequestMapping("/excluir/{codigo}")
	public String excluir(@PathVariable Long codigo) {
		Optional<Funcionario> funcionario = funcionarios.findById(codigo);
		funcionarioService.excluir(funcionario.get().getId());
		return "redirect:/funcionarios";
	}
}
