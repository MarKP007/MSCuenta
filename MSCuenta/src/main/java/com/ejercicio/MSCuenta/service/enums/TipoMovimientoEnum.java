package com.ejercicio.MSCuenta.service.enums;

// TODO: Auto-generated Javadoc
/**
 * The Enum TipoMovimientoEnum.
 */
public enum TipoMovimientoEnum {

	/** The retiro. */
	RETIRO("Movimiento tipo retiro"),

	/** The deposito. */
	DEPOSITO("Movimiento tipo deposito.");

	/** The description. */
	private final String description;

	/**
	 * Instantiates a new tipo movimiento enum.
	 *
	 * @param description the description
	 */
	TipoMovimientoEnum(String description) {
		this.description = description;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
}
