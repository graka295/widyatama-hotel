package com.widyatama.widytamahotel.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.widyatama.widytamahotel.dto.ReservationDto.ReqForm;
import com.widyatama.widytamahotel.model.Room;
import com.widyatama.widytamahotel.repository.ReservationRespository;
import com.widyatama.widytamahotel.model.Reservation;
import com.widyatama.widytamahotel.services.ReservationServices;

@Component
public class ReservationServicesImpl implements ReservationServices {

	@Autowired
	ReservationRespository reservationRespository;
	
	@Override
	public Reservation Create(ReqForm data) {
		Reservation reservation = new Reservation();
		reservation.setId(data.getId());
		reservation.setName(data.getName());
		reservation.setNoHp(data.getNoHp());
		reservation.setNik(data.getNik());
		reservation.setBirthDate(data.getBirthDate());
		reservation.setNumberOfChildren(data.getNumberOfChildren());
		reservation.setNumberOfPerson(data.getNumberOfPerson());
		reservation.setFromDate(data.getFromDate());
		reservation.setToDate(data.getToDate());
		Room resCategory = new Room();
		resCategory.setId(data.getId());
		reservation.setRoom(resCategory);
		reservationRespository.save(reservation);
		return reservation;
	}

}
