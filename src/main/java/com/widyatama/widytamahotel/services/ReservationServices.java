package com.widyatama.widytamahotel.services;

import java.util.Date;
import java.util.List;

import com.widyatama.widytamahotel.dto.ReservationDto.ReqForm;
import com.widyatama.widytamahotel.model.Reservation;

public interface ReservationServices {
	public Reservation Create(ReqForm data);
	public Reservation ValidatDate(Long roomID,Date from,Date to,Date from2,Date to2);
	public Reservation FindByID(Integer id);
	public List<Reservation> FindAll();
	public List<Reservation> FindAllFilter(Long category,Long roomID, Date from, Date to, Date from2, Date to2);
	public void DeleteByID(Integer id);
}
