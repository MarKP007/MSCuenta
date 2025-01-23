package com.ejercicio.MSCuenta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * The Class ClienteDTO.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

	/** The id. */
	private long id;

	/** The clientid. */
	private long clientid;

	/** The nombre. */
	private String nombre;

	/** The genero. */
	private String genero;

	/** The edad. */
	private int edad;

	/** The identificacion. */
	private String identificacion;

	/** The direccion. */
	private String direccion;

	/** The telefono. */
	private String telefono;

	/** The contrasena. */
	private String contrasena;

	/** The estado. */
	private String estado;

}
