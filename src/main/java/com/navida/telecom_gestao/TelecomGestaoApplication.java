package com.navida.telecom_gestao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TelecomGestaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelecomGestaoApplication.class, args);
	}

}
