package com.faesp.ssi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@RequestMapping
	public String menu() {
		return "menu";
	}
}
