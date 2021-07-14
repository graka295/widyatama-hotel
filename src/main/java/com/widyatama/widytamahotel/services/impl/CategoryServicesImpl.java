package com.widyatama.widytamahotel.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.widyatama.widytamahotel.dto.CategoryDto.ReqForm;
import com.widyatama.widytamahotel.model.CategoryRoom;
import com.widyatama.widytamahotel.repository.CategoryRoomRespository;
import com.widyatama.widytamahotel.services.CategoryServices;

@Component
public class CategoryServicesImpl implements CategoryServices {
	@Autowired
	CategoryRoomRespository categoryRoomRespository;
	
	@Override
	public CategoryRoom ValidateName(String name) {
		CategoryRoom res = new CategoryRoom();
		Optional<CategoryRoom> category = categoryRoomRespository.findByName(name);
		category.ifPresentOrElse((data) -> {
			res.setId(data.getId());
		}, () -> {
			res.setId((long) 0);
		});
		return res;
	}

	@Override
	public CategoryRoom Create(ReqForm data) {
		CategoryRoom res = new CategoryRoom();
		res.setName(data.getName());
		res.setDesc(data.getDesc());
		res.setPrice(data.getPrice());
		res.setId(data.getId());
		categoryRoomRespository.save(res);
		return res;
	}

	@Override
	public List<CategoryRoom> FindAll() {
		List<CategoryRoom> res = categoryRoomRespository.findAll();
		return res;
	}

	@Override
	public CategoryRoom FindByID(Integer id) {
		CategoryRoom res = new CategoryRoom();
		Optional<CategoryRoom> category = categoryRoomRespository.findById(Long.valueOf(id));
		category.ifPresentOrElse((data) -> {
			res.setId(data.getId());
			res.setName(data.getName());
			res.setPrice(data.getPrice());
			res.setDesc(data.getDesc());
		}, () -> {
			res.setId((long) 0);
		});
		return res;
	}

	@Override
	public void DeleteByID(Integer id) {
		categoryRoomRespository.deleteById(Long.valueOf(id));
	}
}
