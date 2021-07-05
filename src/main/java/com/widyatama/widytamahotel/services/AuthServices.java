package com.widyatama.widytamahotel.services;

import com.widyatama.widytamahotel.dto.LoginDto.ResSuccessDto;

public interface AuthServices {
	public ResSuccessDto login(String email,String password);
}
