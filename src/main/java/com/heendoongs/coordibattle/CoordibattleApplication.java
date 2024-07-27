package com.heendoongs.coordibattle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CoordibattleApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoordibattleApplication.class, args);
	}

}
