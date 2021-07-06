package com.widyatama.widytamahotel.services.impl;

import java.util.Date;
import java.util.Optional;

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
		reservation.setPrice(data.getPrice());
		Room resCategory = new Room();
		resCategory.setId(data.getRoomId());
		reservation.setRoom(resCategory);
		reservationRespository.save(reservation);
		return reservation;
	}

	@Override
	public Reservation ValidatDate(Long roomID, Date from, Date to, Date from2, Date to2) {
		 Reservation res = new Reservation();
		 Room resCategory = new Room();
		 resCategory.setId(roomID);
		 Optional<Reservation> reservation = reservationRespository.findTopByRoomAndFromDateBetweenOrToDateBetween( resCategory,  from,  to,  from2,  to2);
		 reservation.ifPresentOrElse((data) -> {
				res.setId(data.getId());
				res.setFromDate(data.getFromDate());
				res.setToDate(data.getToDate());
				res.setRoom(data.getRoom());
			}, () -> {
				res.setId((long) 0);
			});
		 return res;
	}

	@Override
	public Reservation FindByID(Integer id) {
		Reservation res = new Reservation();
		Optional<Reservation> category = reservationRespository.findById(Long.valueOf(id));
		category.ifPresentOrElse((data) -> {
			res.setRoom(data.getRoom());
			res.setId(data.getId());
			res.setName(data.getName());
			res.setNoHp(data.getNoHp());
			res.setNik(data.getNik());
			res.setBirthDate(data.getBirthDate());
			res.setNumberOfChildren(data.getNumberOfChildren());
			res.setNumberOfPerson(data.getNumberOfPerson());
			res.setFromDate(data.getFromDate());
			res.setToDate(data.getToDate());
			res.setPrice(data.getPrice());
		}, () -> {
			res.setId((long) 0);
		});
		return res;
	}

}
