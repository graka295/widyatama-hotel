package com.widyatama.widytamahotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class WidytamaHotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(WidytamaHotelApplication.class, args);
	}

}
