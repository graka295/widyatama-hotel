package com.widyatama.widytamahotel.services;

import java.util.List;

import com.widyatama.widytamahotel.dto.CategoryDto.ReqForm;
import com.widyatama.widytamahotel.model.CategoryRoom;

public interface CategoryServices {
	public CategoryRoom ValidateName(String name);
	public List<CategoryRoom> FindAll();
	public CategoryRoom Create(ReqForm data);
	public CategoryRoom FindByID(Integer id);
	public void DeleteByID(Integer id);
}
