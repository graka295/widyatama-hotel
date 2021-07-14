package com.widyatama.widytamahotel.services;

import java.util.List;

import com.widyatama.widytamahotel.dto.RoomDto.ReqForm;
import com.widyatama.widytamahotel.model.CategoryRoom;
import com.widyatama.widytamahotel.model.Room;

public interface RoomServices {
	public List<Room> FindAll();
	public Room Create(ReqForm data);
	public Room ValidateName(String name);
	public Room FindByID(Integer id);
	public void DeleteByID(Integer id);
	public List<Room> findByCategory(Integer category);
}
