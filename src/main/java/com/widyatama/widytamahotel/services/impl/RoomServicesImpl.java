package com.widyatama.widytamahotel.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.widyatama.widytamahotel.dto.RoomDto.ReqForm;
import com.widyatama.widytamahotel.model.CategoryRoom;
import com.widyatama.widytamahotel.model.Room;
import com.widyatama.widytamahotel.repository.RoomRespository;
import com.widyatama.widytamahotel.services.RoomServices;

@Component
public class RoomServicesImpl implements RoomServices{

	@Autowired
	RoomRespository roomRespository;
	
	@Override
	public List<Room> FindAll() {
		List<Room> res = roomRespository.findAll();
		return res;
	}

	@Override
	public Room Create(ReqForm data) {
		Room res = new Room();
		res.setName(data.getName());
		res.setDesc(data.getDesc());
		res.setId(data.getId());
		CategoryRoom resCategory = new CategoryRoom();
		resCategory.setId(data.getCategory());
		res.setCategory(resCategory);
		roomRespository.save(res);
		return res;
	}

	@Override
	public Room ValidateName(String name) {
		Room res = new Room();
		Optional<Room> category = roomRespository.findByName(name);
		category.ifPresentOrElse((data) -> {
			res.setId(data.getId());
		}, () -> {
			res.setId((long) 0);
		});
		return res;
	}

	@Override
	public Room FindByID(Integer id) {
		Room res = new Room();
		Optional<Room> category = roomRespository.findById(Long.valueOf(id));
		category.ifPresentOrElse((data) -> {
			res.setId(data.getId());
			res.setName(data.getName());
			res.setDesc(data.getDesc());
			res.setCategory(data.getCategory());
		}, () -> {
			res.setId((long) 0);
		});
		return res;
	}

	@Override
	public void DeleteByID(Integer id) {
		roomRespository.deleteById(Long.valueOf(id));
	}

	@Override
	public List<Room> findByCategory(Integer category) {
		CategoryRoom resCategory = new CategoryRoom();
		resCategory.setId(Long.valueOf(category));
		return roomRespository.findByCategory(resCategory);
	}

}
