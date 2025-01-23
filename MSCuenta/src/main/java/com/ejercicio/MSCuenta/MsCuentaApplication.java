package com.ejercicio.MSCuenta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

// TODO: Auto-generated Javadoc
/**
 * The Class MsCuentaApplication.
 */
@SpringBootApplication
@EnableFeignClients
public class MsCuentaApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(MsCuentaApplication.class, args);
	}

}
