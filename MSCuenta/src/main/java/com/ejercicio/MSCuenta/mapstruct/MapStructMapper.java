package com.ejercicio.MSCuenta.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ejercicio.MSCuenta.model.Cuenta;
import com.ejercicio.MSCuenta.model.CuentaDTO;
import com.ejercicio.MSCuenta.model.Movimientos;
import com.ejercicio.MSCuenta.model.MovimientosDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface MapStructMapper.
 */
@Mapper
public interface MapStructMapper {

	/** The instance. */
	MapStructMapper INSTANCE = Mappers.getMapper(MapStructMapper.class);

	/**
	 * Map cuenta to cuenta DTO.
	 *
	 * @param cuenta the cuenta
	 * @return the cuenta DTO
	 */
	CuentaDTO mapCuentaToCuentaDTO(Cuenta cuenta);

	/**
	 * Map movimientos to movimientos DTO.
	 *
	 * @param movimientos the movimientos
	 * @return the movimientos DTO
	 */
	MovimientosDTO mapMovimientosToMovimientosDTO(Movimientos movimientos);
}