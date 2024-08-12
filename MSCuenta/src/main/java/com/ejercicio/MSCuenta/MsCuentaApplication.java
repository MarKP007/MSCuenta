package com.ejercicio.MSCuenta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsCuentaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCuentaApplication.class, args);
	}

}
