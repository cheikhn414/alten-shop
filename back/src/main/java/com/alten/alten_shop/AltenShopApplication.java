package com.alten.alten_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AltenShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(AltenShopApplication.class, args);
	}

}
