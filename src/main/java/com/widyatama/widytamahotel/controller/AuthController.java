package com.widyatama.widytamahotel.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.MediaType;

import com.widyatama.widytamahotel.dto.LoginDto.ReqForm;
import com.widyatama.widytamahotel.dto.LoginDto.ResBadRequest;
import com.widyatama.widytamahotel.dto.LoginDto.ResSuccessDto;
import com.widyatama.widytamahotel.model.Users;
import com.widyatama.widytamahotel.services.AuthServices;
import com.widyatama.widytamahotel.services.impl.UserServicesImpl;

@Controller
@RequestMapping("/auth")
public class AuthController {

	private final AuthServices authServices;
	private final UserServicesImpl userServicesImpl;
	public AuthController(AuthServices authServices,UserServicesImpl userServicesImpl) {
		super();
		this.authServices = authServices;
		this.userServicesImpl = userServicesImpl;
	}

	@GetMapping("/login")
	public String Login(Model model) {
		return "auth/login";
	}
	
	@GetMapping("/logout")
	public String Logout(Model model,HttpSession session) {
		session.removeAttribute("id");
		return ("redirect:/auth/login");
	}
	
	@PostMapping("/login")
	public String LoginPost(@ModelAttribute ReqForm reqForm,Model model,HttpSession session) {
		ResSuccessDto response = authServices.login(reqForm.getEmail(), reqForm.getPassword());
		if (response.getId() != 0){
			session.setAttribute("id", String.valueOf(response.getId()));
			session.setAttribute("firstName", String.valueOf(response.getFirstName()));
			return ("redirect:/dashboard");	
		}else {
			return ("redirect:/auth/login");
		}
	}
	
	
	@RequestMapping(value= "/do-login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> Dologin(@Valid @RequestBody ReqForm reqForm,Model model) {
		ResSuccessDto response = authServices.login(reqForm.getEmail(), reqForm.getPassword());
		System.out.print(response.getId());
		if (response.getId() != 0){
			return ResponseEntity.ok().body(response);	
		}else {
			ResBadRequest res = new ResBadRequest();
			res.setCode("400");
			List<String> messages = new ArrayList<>();
			messages.add("Login failed, Please recheck username and password");
			res.setMessage(messages);
			return ResponseEntity.badRequest().body(res);
		}
	}
	
	@GetMapping("/profile")
	public String Profile(Model model,HttpSession session) {
		String id = (String) session.getAttribute("id");
		if (id == null) {
			return ("redirect:/auth/logout");
		}
		Users data = userServicesImpl.FindByID(Integer.parseInt(id));
		if (data.getId() == 0) {
			return ("redirect:/auth/logout");
		}
		model.addAttribute("data", data);
		return "user/profile";
	}
}
