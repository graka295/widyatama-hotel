package com.widyatama.widytamahotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import groovyjarjarpicocli.CommandLine.Model;

@Controller
public class DefaultController {
	@GetMapping("")
	public String index(Model model) {            
	    return "redirect:/dashboard";
	}
}
