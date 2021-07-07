package com.widyatama.widytamahotel.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.widyatama.widytamahotel.model.Room;
import com.widyatama.widytamahotel.model.Users;
import com.widyatama.widytamahotel.repository.UsersRepository;
import com.widyatama.widytamahotel.services.UserServices;

@Component
public class UserServicesImpl implements UserServices{
	@Autowired
	UsersRepository usersRepository;
	
	@Override
	public Users FindByID(Integer id) {
		Users res = new Users();
		Optional<Users> dataUsers = usersRepository.findById(Long.valueOf(id));
		dataUsers.ifPresentOrElse((data) -> {
			res.setEmail(data.getEmail());
			res.setFirstName(data.getFirstName());
			res.setLastName(data.getLastName());
			res.setId(data.getId());
		}, () -> {
			res.setId((long) 0);
		});
		return res;
	}

	@Override
	public Users Create(Users data) {
		usersRepository.save(data);
		return data;
	}

}
