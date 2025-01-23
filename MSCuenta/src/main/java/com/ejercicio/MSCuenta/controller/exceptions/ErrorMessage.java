package com.ejercicio.MSCuenta.controller.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class ErrorMessage.
 */
public class ErrorMessage {

	/** The exception. */
	private String exception;

	/** The message. */
	private String message;

	/** The path. */
	private String path;

	/**
	 * Instantiates a new error message.
	 *
	 * @param exception the exception
	 * @param path      the path
	 */
	public ErrorMessage(Exception exception, String path) {
		this.exception = exception.getClass().getSimpleName();
		this.message = exception.getMessage();
		this.path = path;
	}

	/**
	 * Gets the exception.
	 *
	 * @return the exception
	 */
	public String getException() {
		return exception;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "ErrorMessage [exception=" + exception + ", message=" + message + ", path=" + path + "]";
	}

}
