package com.ejercicio.MSCuenta.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimientosDTO {

	private long id;
	private Date fecha;
	private String tipoMovimiento;
	private double valor;
	private double saldo;
	private long cuentaId;

}
