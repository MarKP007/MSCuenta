package com.ejercicio.MSCuenta.controller.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class SaldoNoDisponibleException.
 */
public class SaldoNoDisponibleException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6703995596852287652L;

	/** The Constant DESCRIPTION. */
	private static final String DESCRIPTION = "Saldo no disponible";

	/**
	 * Instantiates a new saldo no disponible exception.
	 *
	 * @param detail the detail
	 */
	public SaldoNoDisponibleException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}

}
