package com.widyatama.widytamahotel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.widyatama.widytamahotel.model.CategoryRoom;

@Repository
public interface CategoryRoomRespository extends JpaRepository<CategoryRoom,Long> {
	Optional<CategoryRoom> findByName(String name);
}
