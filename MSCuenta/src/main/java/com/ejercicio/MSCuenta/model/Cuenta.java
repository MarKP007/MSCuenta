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

// TODO: Auto-generated Javadoc
/**
 * The Class Cuenta.
 */
@Entity
@Table(name = "cuenta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cuenta {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/** The client id. */
	@Column(name = "clientid")
	private Long clientId;

	/** The numero cuenta. */
	@Column(name = "numerocuenta")
	private String numeroCuenta;

	/** The tipo cuenta. */
	@Column(name = "tipocuenta")
	private String tipoCuenta;

	/** The saldo inicial. */
	@Column(name = "saldoinicial")
	private double saldoInicial;

	/** The estado. */
	@Column(name = "estado")
	private String estado;

	/**
	 * Instantiates a new cuenta.
	 *
	 * @param numeroCuenta the numero cuenta
	 * @param tipoCuenta   the tipo cuenta
	 * @param saldoInicial the saldo inicial
	 * @param estado       the estado
	 * @param clientId     the client id
	 */
	public Cuenta(String numeroCuenta, String tipoCuenta, double saldoInicial, String estado, long clientId) {
		this.clientId = clientId;
		this.numeroCuenta = numeroCuenta;
		this.tipoCuenta = tipoCuenta;
		this.saldoInicial = saldoInicial;
		this.estado = estado;
	}

}