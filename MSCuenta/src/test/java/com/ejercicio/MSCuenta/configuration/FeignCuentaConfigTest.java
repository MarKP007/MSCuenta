/**
 * 
 */
package com.ejercicio.MSCuenta.configuration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * 
 */
class FeignCuentaConfigTest {

	@Test
	void test() {
		FeignCuentaConfig fcg = new FeignCuentaConfig();
		assertNotNull(fcg.feignLoggerLevel());
	}

}
