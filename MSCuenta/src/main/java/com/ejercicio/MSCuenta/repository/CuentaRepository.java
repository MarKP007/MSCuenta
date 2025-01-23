package com.ejercicio.MSCuenta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejercicio.MSCuenta.model.Cuenta;

// TODO: Auto-generated Javadoc
/**
 * The Interface CuentaRepository.
 */
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

	/**
	 * Find by client id.
	 *
	 * @param clienteId the cliente id
	 * @return the list
	 */
	List<Cuenta> findByClientId(long clienteId);

	/**
	 * Delete by client id.
	 *
	 * @param clientId the client id
	 */
	void deleteByClientId(long clientId);

}
