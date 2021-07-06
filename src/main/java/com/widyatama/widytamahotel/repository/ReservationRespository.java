package com.widyatama.widytamahotel.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.widyatama.widytamahotel.model.Reservation;
import com.widyatama.widytamahotel.model.Room;

@Repository
public interface ReservationRespository extends JpaRepository<Reservation,Long> {
	@Query(value ="select * from reservation reservatio0_ where reservatio0_.room_id = ?1 AND (reservatio0_.from_data between ?2 and ?3 or reservatio0_.to_data between ?4 and ?5 ) limit 1",
			nativeQuery = true)
	Optional<Reservation> findTopByRoomAndFromDateBetweenOrToDateBetween(Room roomID,Date from,Date to,Date from2,Date to2);
}
