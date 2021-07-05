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

import com.widyatama.widytamahotel.dto.ReservationDto.ReqForm;
import com.widyatama.widytamahotel.dto.ReservationDto.ResForm;
import com.widyatama.widytamahotel.model.Reservation;
import com.widyatama.widytamahotel.services.ReservationServices;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	private final ReservationServices reservationServices;
	public DashboardController(ReservationServices reservationServices) {
		super();
		this.reservationServices = reservationServices;
	}
	
	@GetMapping("")
	public String Index(Model model,HttpSession session) {
		String id = (String) session.getAttribute("id");
		if (id == null) {
			return ("redirect:/auth/logout");
		}
		return "dashboard/index";
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
}
