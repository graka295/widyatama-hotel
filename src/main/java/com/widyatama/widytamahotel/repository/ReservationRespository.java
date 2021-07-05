package com.widyatama.widytamahotel.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.widyatama.widytamahotel.model.Reservation;
import com.widyatama.widytamahotel.model.Room;

@Repository
public interface ReservationRespository extends JpaRepository<Reservation,Long> {
	Optional<Reservation> findByRoomAndFromDateBetweenOrToDateBetween(Room roomID,Date from,Date to,Date from2,Date to2);
}
