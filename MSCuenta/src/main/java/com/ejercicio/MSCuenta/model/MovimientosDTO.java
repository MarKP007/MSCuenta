package com.ejercicio.MSCuenta.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * The Class MovimientosDTO.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimientosDTO {

	/** The id. */
	private long id;

	/** The fecha. */
	private Date fecha;

	/** The tipo movimiento. */
	private String tipoMovimiento;

	/** The valor. */
	private double valor;

	/** The saldo. */
	private double saldo;

	/** The cuenta id. */
	private long cuentaId;

}
