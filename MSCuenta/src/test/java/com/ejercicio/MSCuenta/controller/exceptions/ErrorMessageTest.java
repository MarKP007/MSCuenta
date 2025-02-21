/**
 * 
 */
package com.ejercicio.MSCuenta.controller.exceptions;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * 
 */
class ErrorMessageTest {

	@Test
	void test() {
		ErrorMessage errorMessage = new ErrorMessage(new Exception("asdasd"), "");
		assertNotNull(errorMessage.getException());
		assertNotNull(errorMessage.getMessage());
		assertNotNull(errorMessage.getPath());
		assertNotNull(errorMessage.toString());
	}

}
