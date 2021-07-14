package com.widyatama.widytamahotel.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.widyatama.widytamahotel.dto.ReservationDto.ReqFilter;
import com.widyatama.widytamahotel.dto.ReservationDto.ReqForm;
import com.widyatama.widytamahotel.dto.ReservationDto.ResForm;
import com.widyatama.widytamahotel.model.CategoryRoom;
import com.widyatama.widytamahotel.model.Reservation;
import com.widyatama.widytamahotel.services.CategoryServices;
import com.widyatama.widytamahotel.services.ReservationServices;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	private final ReservationServices reservationServices;
	private final CategoryServices categoryServices;
	public DashboardController(ReservationServices reservationServices, CategoryServices categoryServices) {
		super();
		this.reservationServices = reservationServices;
		this.categoryServices = categoryServices;
	}
	
	@GetMapping("")
	public String Index(Model model,HttpSession session) {
		String id = (String) session.getAttribute("id");
		String firstname = (String) session.getAttribute("firstname");
		if (id == null) {
			return ("redirect:/auth/logout");
		}
		List<Reservation> data = reservationServices.FindAll();
		List<CategoryRoom> category = categoryServices.FindAll();
		model.addAttribute("category", category);
		model.addAttribute("firstname", firstname);
		model.addAttribute("id", id);
		model.addAttribute("data", data);
		return "dashboard/index";
	}
	
	@GetMapping("/create")
	public String Create(Model model,HttpSession session) {
		String id = (String) session.getAttribute("id");
		String firstname = (String) session.getAttribute("firstname");
		if (id == null) {
			return ("redirect:/auth/logout");
		}
		List<CategoryRoom> category = categoryServices.FindAll();
		model.addAttribute("firstname", firstname);
		model.addAttribute("id", id);
		model.addAttribute("category", category);
		return "dashboard/create";
	
	}
	@RequestMapping(value= "/do-create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> DoCreate(@Valid @RequestBody ReqForm reqForm,Model model) {
		ResForm res = new ResForm();
		Reservation validateDate = reservationServices.ValidatDate(reqForm.getRoomId(),reqForm.getFromDate(),reqForm.getToDate(),reqForm.getFromDate(),reqForm.getToDate());
		if (validateDate.getId() != 0) {
			res.setCode("400");
			List<String> messages = new ArrayList<>();
			messages.add("Room already has been reserved, from "+new SimpleDateFormat("dd-MM-yyyy").format(validateDate.getFromDate())+" to "+new SimpleDateFormat("dd-MM-yyyy").format(validateDate.getToDate()));
			res.setMessage(messages);
			return ResponseEntity.badRequest().body(res);
		}
		Reservation data = reservationServices.Create(reqForm);
		return ResponseEntity.ok().body(res);
	}
	@RequestMapping(value= "/do-update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> DUpdate(@Valid @RequestBody ReqForm reqForm,Model model) {
		ResForm res = new ResForm();
		Reservation validateDate = reservationServices.ValidatDate(reqForm.getRoomId(),reqForm.getFromDate(),reqForm.getToDate(),reqForm.getFromDate(),reqForm.getToDate());
		if (validateDate.getId() != 0 && validateDate.getId() != reqForm.getId()) {
			res.setCode("400");
			List<String> messages = new ArrayList<>();
			messages.add("Room already has been reserved, from "+new SimpleDateFormat("dd-MM-yyyy").format(validateDate.getFromDate())+" to "+new SimpleDateFormat("dd-MM-yyyy").format(validateDate.getToDate()));
			res.setMessage(messages);
			return ResponseEntity.badRequest().body(res);
		}
		Reservation data = reservationServices.Create(reqForm);
		return ResponseEntity.ok().body(res);
	}
	@GetMapping("/update")
	public String Update(Model model,HttpSession session,@RequestParam Integer idData) {
		String id = (String) session.getAttribute("id");
		String firstname = (String) session.getAttribute("firstname");
		if (id == null) {
			return ("redirect:/auth/logout");
		}
		Reservation data = reservationServices.FindByID(idData);
		if (data.getId() == 0) {
			
		}
		List<CategoryRoom> category = categoryServices.FindAll();
		model.addAttribute("firstname", firstname);
		model.addAttribute("id", id);
		model.addAttribute("data", data);
		model.addAttribute("category", category);
		return "dashboard/update";
	}
	
	@GetMapping("/detail")
	public String Detail(Model model,HttpSession session,@RequestParam String idData) {
		String id = (String) session.getAttribute("id");
		String firstname = (String) session.getAttribute("firstname");
		if (id == null) {
			return ("redirect:/auth/logout");
		}
		Reservation data = reservationServices.FindByID(Integer.parseInt(idData));
		if (data.getId() == 0) {
			
		}
		System.out.println("ID DATA : "+data.getId());
		model.addAttribute("firstname", firstname);
		model.addAttribute("id", id);
		model.addAttribute("data", data);
		return "dashboard/detail";
	}
	@RequestMapping(value= "/do-filter", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> DoFilter(@Valid @RequestBody ReqFilter reqForm,Model model) {
		List<Reservation> res = reservationServices.FindAllFilter(reqForm.getCategory(),reqForm.getRoomId(),reqForm.getFromDate(),reqForm.getToDate(),reqForm.getFromDate(),reqForm.getToDate());
		return ResponseEntity.ok().body(res);
	}
	
	@GetMapping("/delete")
	public String Delete(Model model,HttpSession session,@RequestParam Integer idData) {
		String id = (String) session.getAttribute("id");
		if (id == null) {
			return ("redirect:/auth/logout");
		}
		reservationServices.DeleteByID(idData);
		return "redirect:/dashboard";
	}
}
