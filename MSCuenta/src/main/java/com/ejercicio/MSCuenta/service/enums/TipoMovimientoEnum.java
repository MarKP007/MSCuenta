package com.ejercicio.MSCuenta.service.enums;

public enum TipoMovimientoEnum {
	
	RETIRO("Movimiento tipo retiro"),
	DEPOSITO("Movimiento tipo deposito.");
	
	private final String description;

	TipoMovimientoEnum(String description){
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}
