package com.widyatama.widytamahotel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.widyatama.widytamahotel.model.CategoryRoom;
import com.widyatama.widytamahotel.model.Room;

@Repository
public interface RoomRespository extends JpaRepository<Room,Long> {
	Optional<Room> findByName(String name);
	List<Room> findByCategory(CategoryRoom category);
}
