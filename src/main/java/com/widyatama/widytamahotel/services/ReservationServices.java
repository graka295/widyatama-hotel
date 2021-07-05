package com.widyatama.widytamahotel.services;

import java.util.Date;

import com.widyatama.widytamahotel.dto.ReservationDto.ReqForm;
import com.widyatama.widytamahotel.model.Reservation;
import com.widyatama.widytamahotel.model.Room;

public interface ReservationServices {
	public Reservation Create(ReqForm data);
	public Reservation ValidatDate(Long roomID,Date from,Date to,Date from2,Date to2);
}
