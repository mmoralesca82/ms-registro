package com.grupo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsRegistroApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsRegistroApplication.class, args);
	}

}
