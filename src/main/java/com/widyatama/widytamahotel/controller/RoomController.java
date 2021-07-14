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

import com.widyatama.widytamahotel.dto.RoomDto.ReqForm;
import com.widyatama.widytamahotel.dto.RoomDto.ResForm;
import com.widyatama.widytamahotel.model.CategoryRoom;
import com.widyatama.widytamahotel.model.Room;
import com.widyatama.widytamahotel.services.CategoryServices;
import com.widyatama.widytamahotel.services.RoomServices;

@Controller
@RequestMapping("/room")
public class RoomController {
	private final RoomServices roomServices;
	private final CategoryServices categoryServices;
	
	public RoomController(RoomServices roomServices, CategoryServices categoryServices) {
		super();
		this.roomServices = roomServices;
		this.categoryServices = categoryServices;
	}
	
	@GetMapping("/detail")
	public String Detail(Model model,HttpSession session,@RequestParam String idData) {
		String id = (String) session.getAttribute("id");
		String firstname = (String) session.getAttribute("firstname");
		if (id == null) {
			return ("redirect:/auth/logout");
		}
		Room data = roomServices.FindByID(Integer.parseInt(idData));
		if (data.getId() == 0) {
			
		}
		System.out.println("ID DATA : "+data.getId());
		model.addAttribute("firstname", firstname);
		model.addAttribute("id", id);
		model.addAttribute("data", data);
		return "room/detail";
	}

	@GetMapping("")
	public String Index(Model model,HttpSession session) {
		String id = (String) session.getAttribute("id");
		String firstname = (String) session.getAttribute("firstname");
		if (id == null) {
			return ("redirect:/auth/logout");
		}
		List<Room> data = roomServices.FindAll();
		model.addAttribute("firstname", firstname);
		model.addAttribute("id", id);
		model.addAttribute("data", data);
		return "room/index";
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
		return "room/create";
	}
	
	
	@RequestMapping(value= "/do-create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> DoCreate(@Valid @RequestBody ReqForm reqForm,Model model) {
		ResForm res = new ResForm();
		Room name = roomServices.ValidateName(reqForm.getName());
		if (name.getId() != 0) {
			res.setCode("400");
			List<String> messages = new ArrayList<>();
			messages.add("Name already exist");
			res.setMessage(messages);
			return ResponseEntity.badRequest().body(res);
		}
		Room data = roomServices.Create(reqForm);
		return ResponseEntity.ok().body(res);
	}
	

	@GetMapping("/update")
	public String Update(Model model,HttpSession session,@RequestParam Integer idData) {
		String id = (String) session.getAttribute("id");
		String firstname = (String) session.getAttribute("firstname");
		if (id == null) {
			return ("redirect:/auth/logout");
		}
		Room data = roomServices.FindByID(idData);
		if (data.getId() == 0) {
			
		}
		List<CategoryRoom> category = categoryServices.FindAll();
		model.addAttribute("firstname", firstname);
		model.addAttribute("id", id);
		model.addAttribute("data", data);
		model.addAttribute("category", category);
		return "room/update";
	}
	
	@RequestMapping(value= "/do-update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> DoUpdate(@Valid @RequestBody ReqForm reqForm,Model model) {
		ResForm res = new ResForm();
		Room name = roomServices.ValidateName(reqForm.getName());
		if (name.getId() != 0 && name.getId() != reqForm.getId()) {
			res.setCode("400");
			List<String> messages = new ArrayList<>();
			messages.add("Name already exist");
			res.setMessage(messages);
			return ResponseEntity.badRequest().body(res);
		}
		Room data = roomServices.Create(reqForm);
		return ResponseEntity.ok().body(res);
	}
	
	@GetMapping("/get-by-category")
	public ResponseEntity<?> GetByCategory(Model model,HttpSession session,@RequestParam Integer idCategory) {
		List<Room> data = roomServices.findByCategory(idCategory);
		return ResponseEntity.ok().body(data);
	}
	
	
	@GetMapping("/delete")
	public String Delete(Model model,HttpSession session,@RequestParam Integer idData) {
		String id = (String) session.getAttribute("id");
		if (id == null) {
			return ("redirect:/auth/logout");
		}
		roomServices.DeleteByID(idData);
		return "redirect:/room";
	}	
}
