package com.ejercicio.MSCuenta.mapstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejercicio.MSCuenta.model.Cuenta;
import com.ejercicio.MSCuenta.model.CuentaDTO;
import com.ejercicio.MSCuenta.model.Movimientos;
import com.ejercicio.MSCuenta.model.MovimientosDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class MapStructService.
 */
@Service
public class MapStructService {

	/** The map struct mapper. */
	private final MapStructMapper mapStructMapper;

	/**
	 * Instantiates a new map struct service.
	 *
	 * @param mapStructMapper the map struct mapper
	 */
	@Autowired
	public MapStructService(MapStructMapper mapStructMapper) {
		this.mapStructMapper = mapStructMapper;
	}

	/**
	 * Map cuenta to cuenta DTO.
	 *
	 * @param cuenta the cuenta
	 * @return the cuenta DTO
	 */
	public CuentaDTO mapCuentaToCuentaDTO(Cuenta cuenta) {
		return mapStructMapper.mapCuentaToCuentaDTO(cuenta);
	}

	/**
	 * Map movimientos to movimientos DTO.
	 *
	 * @param movimientos the movimientos
	 * @return the movimientos DTO
	 */
	public MovimientosDTO mapMovimientosToMovimientosDTO(Movimientos movimientos) {
		return mapStructMapper.mapMovimientosToMovimientosDTO(movimientos);
	}
}
