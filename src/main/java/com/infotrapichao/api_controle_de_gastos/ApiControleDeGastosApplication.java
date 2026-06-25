package com.infotrapichao.api_controle_de_gastos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApiControleDeGastosApplication {

	static void main(String[] args) {
		SpringApplication.run(ApiControleDeGastosApplication.class, args);
	}

}
