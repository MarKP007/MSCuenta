package com.ejercicio.MSCuenta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDTO {

	private long id;
	private Long clientId;
	private String numeroCuenta;
	private String tipoCuenta;
	private double saldoInicial;
	private String estado;

}
