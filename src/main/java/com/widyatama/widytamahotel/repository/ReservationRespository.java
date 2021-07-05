package com.widyatama.widytamahotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.widyatama.widytamahotel.model.Reservation;

@Repository
public interface ReservationRespository extends JpaRepository<Reservation,Long> {

}
