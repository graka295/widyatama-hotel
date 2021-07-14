package com.widyatama.widytamahotel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.widyatama.widytamahotel.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {

	Optional<Users> findByEmailAndPassword(String email, String password);
}
