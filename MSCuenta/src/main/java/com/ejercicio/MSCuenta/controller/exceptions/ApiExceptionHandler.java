package com.ejercicio.MSCuenta.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.http.HttpServletRequest;

// TODO: Auto-generated Javadoc
/**
 * The Class ApiExceptionHandler.
 */
@ControllerAdvice
public class ApiExceptionHandler {

	/**
	 * Saldo nodisponible error.
	 *
	 * @param request   the request
	 * @param exception the exception
	 * @return the error message
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	@ResponseBody
	public ErrorMessage saldoNodisponibleError(HttpServletRequest request, Exception exception) {
		return new ErrorMessage(exception, request.getRequestURI());
	}

}
