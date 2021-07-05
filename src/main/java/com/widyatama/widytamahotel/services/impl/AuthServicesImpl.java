package com.widyatama.widytamahotel.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.widyatama.widytamahotel.dto.LoginDto.ResSuccessDto;
//import com.widyatama.widytamahotel.exception.ResourceNotFoundException;
import com.widyatama.widytamahotel.model.Users;
import com.widyatama.widytamahotel.repository.UsersRepository;
import com.widyatama.widytamahotel.services.AuthServices;

@Component
public class AuthServicesImpl implements AuthServices {

	@Autowired
	private UsersRepository usersRepository; 
	@Override
	public ResSuccessDto login(String email, String password) {
		ResSuccessDto res = new ResSuccessDto();
		Optional<Users> users = usersRepository.findByEmailAndPassword(email, password);
		users.ifPresentOrElse((data) -> {
			res.setEmail(data.getEmail());
			res.setFirstName(data.getFirstName());
			res.setLastName(data.getLastName());
			res.setId(data.getId());
		}, () -> {
			res.setId((long) 0);
		});
		return res;
	}

}
