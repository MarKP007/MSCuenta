package com.ejercicio.MSCuenta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cuenta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cuenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "clientid")
	private Long clientId;

	@Column(name = "numerocuenta")
	private String numeroCuenta;

	@Column(name = "tipocuenta")
	private String tipoCuenta;

	@Column(name = "saldoinicial")
	private double saldoInicial;

	@Column(name = "estado")
	private String estado;

	public Cuenta(String numeroCuenta, String tipoCuenta, double saldoInicial, String estado, long clientId) {
		this.clientId = clientId;
		this.numeroCuenta = numeroCuenta;
		this.tipoCuenta = tipoCuenta;
		this.saldoInicial = saldoInicial;
		this.estado = estado;
	}

}