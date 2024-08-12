package com.ejercicio.MSCuenta.mapstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapStructConfig {

	@Bean
	public MapStructMapper mapStructMapper() {
		return MapStructMapper.INSTANCE;
	}

}
