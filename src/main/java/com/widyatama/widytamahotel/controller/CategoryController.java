package com.widyatama.widytamahotel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.widyatama.widytamahotel.dto.CategoryDto.ReqForm;
import com.widyatama.widytamahotel.dto.CategoryDto.ResForm;
import com.widyatama.widytamahotel.model.CategoryRoom;
import com.widyatama.widytamahotel.services.CategoryServices;

@Controller
@RequestMapping("/category")
public class CategoryController {
	private final CategoryServices categoryServices;
	
	public CategoryController(CategoryServices categoryServices) {
		super();
		this.categoryServices = categoryServices;
	}

	@GetMapping("")
	public String Index(Model model,HttpSession session) {
		String id = (String) session.getAttribute("id");
		String firstname = (String) session.getAttribute("firstname");
		if (id == null) {
			return ("redirect:/auth/logout");
		}
		List<CategoryRoom> data = categoryServices.FindAll();
		model.addAttribute("firstname", firstname);
		model.addAttribute("id", id);
		model.addAttribute("data", data);
		return "category/index";
	}
	
	@GetMapping("/create")
	public String Create(Model model,HttpSession session) {
		String id = (String) session.getAttribute("id");
		String firstname = (String) session.getAttribute("firstname");
		if (id == null) {
			return ("redirect:/auth/logout");
		}
		model.addAttribute("firstname", firstname);
		model.addAttribute("id", id);
		return "category/create";
	}
	
	@RequestMapping(value= "/do-create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> DoCreate(@Valid @RequestBody ReqForm reqForm,Model model) {
		ResForm res = new ResForm();
		CategoryRoom name = categoryServices.ValidateName(reqForm.getName());
		if (name.getId() != 0) {
			res.setCode("400");
			List<String> messages = new ArrayList<>();
			messages.add("Name already exist");
			res.setMessage(messages);
			return ResponseEntity.badRequest().body(res);
		}
		CategoryRoom data = categoryServices.Create(reqForm);
		return ResponseEntity.ok().body(res);
	}
	
	@GetMapping("/update")
	public String Update(Model model,HttpSession session,@RequestParam Integer idData) {
		String id = (String) session.getAttribute("id");
		String firstname = (String) session.getAttribute("firstname");
		if (id == null) {
			return ("redirect:/auth/logout");
		}
		CategoryRoom data = categoryServices.FindByID(idData);
		if (data.getId() == 0) {
			
		}
		model.addAttribute("firstname", firstname);
		model.addAttribute("id", id);
		model.addAttribute("data", data);
		return "category/update";
	}
	
	@GetMapping("/detail")
	public String Detail(Model model,HttpSession session,@RequestParam String idData) {
		String id = (String) session.getAttribute("id");
		String firstname = (String) session.getAttribute("firstname");
		if (id == null) {
			return ("redirect:/auth/logout");
		}
		CategoryRoom data = categoryServices.FindByID(Integer.parseInt(idData));
		if (data.getId() == 0) {
			
		}
		System.out.println("ID DATA : "+data.getId());
		model.addAttribute("firstname", firstname);
		model.addAttribute("id", id);
		model.addAttribute("data", data);
		return "category/detail";
	}

	@RequestMapping(value= "/do-update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> DoUpdate(@Valid @RequestBody ReqForm reqForm,Model model) {
		ResForm res = new ResForm();
		CategoryRoom name = categoryServices.ValidateName(reqForm.getName());
		if (name.getId() != 0 && name.getId() != reqForm.getId()) {
			res.setCode("400");
			List<String> messages = new ArrayList<>();
			messages.add("Name already exist");
			res.setMessage(messages);
			return ResponseEntity.badRequest().body(res);
		}
		CategoryRoom data = categoryServices.Create(reqForm);
		return ResponseEntity.ok().body(res);
	}
}
