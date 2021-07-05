package com.widyatama.widytamahotel.services;

import com.widyatama.widytamahotel.dto.ReservationDto.ReqForm;
import com.widyatama.widytamahotel.model.Reservation;

public interface ReservationServices {
	public Reservation Create(ReqForm data);
}
