package com.widyatama.widytamahotel.services;

import com.widyatama.widytamahotel.model.Users;

public interface UserServices {
	public Users FindByID(Integer id);
	public Users Create(Users data);
}
