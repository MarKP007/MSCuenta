package com.ejercicio.MSCuenta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * The Class CuentaDTO.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDTO {

	/** The id. */
	private long id;

	/** The client id. */
	private Long clientId;

	/** The numero cuenta. */
	private String numeroCuenta;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The saldo inicial. */
	private double saldoInicial;

	/** The estado. */
	private String estado;

}
