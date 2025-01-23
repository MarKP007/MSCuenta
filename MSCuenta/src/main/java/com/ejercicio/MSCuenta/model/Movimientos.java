package com.ejercicio.MSCuenta.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * The Class Movimientos.
 */
@Entity
@Table(name = "movimientos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movimientos {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/** The fecha. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha")
	private Date fecha;

	/** The tipo movimiento. */
	@Column(name = "tipomovimiento")
	private String tipoMovimiento;

	/** The valor. */
	@Column(name = "valor")
	private double valor;

	/** The saldo. */
	@Column(name = "saldo")
	private double saldo;

	/** The cuenta id. */
	@Column(name = "cuentaid")
	private long cuentaId;

	/**
	 * Instantiates a new movimientos.
	 *
	 * @param fecha          the fecha
	 * @param tipoMovimiento the tipo movimiento
	 * @param valor          the valor
	 * @param saldo          the saldo
	 * @param cuentaId       the cuenta id
	 */
	public Movimientos(Date fecha, String tipoMovimiento, double valor, double saldo, long cuentaId) {
		this.fecha = fecha;
		this.tipoMovimiento = tipoMovimiento;
		this.valor = valor;
		this.saldo = saldo;
		this.cuentaId = cuentaId;
	}

}