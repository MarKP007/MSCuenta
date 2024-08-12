package com.ejercicio.MSCuenta.mapstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejercicio.MSCuenta.model.Cuenta;
import com.ejercicio.MSCuenta.model.CuentaDTO;
import com.ejercicio.MSCuenta.model.Movimientos;
import com.ejercicio.MSCuenta.model.MovimientosDTO;

@Service
public class MapStructService {

	private final MapStructMapper mapStructMapper;

	@Autowired
	public MapStructService(MapStructMapper mapStructMapper) {
		this.mapStructMapper = mapStructMapper;
	}

	public CuentaDTO mapCuentaToCuentaDTO(Cuenta cuenta) {
		return mapStructMapper.mapCuentaToCuentaDTO(cuenta);
	}

	public MovimientosDTO mapMovimientosToMovimientosDTO(Movimientos movimientos) {
		return mapStructMapper.mapMovimientosToMovimientosDTO(movimientos);
	}
}
