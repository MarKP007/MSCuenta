package com.ejercicio.MSCuenta.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.ejercicio.MSCuenta.model.Cuenta;
import com.ejercicio.MSCuenta.model.CuentaDTO;
import com.ejercicio.MSCuenta.model.Movimientos;
import com.ejercicio.MSCuenta.model.MovimientosDTO;

@Mapper
public interface MapStructMapper {

	MapStructMapper INSTANCE = Mappers.getMapper(MapStructMapper.class);

	CuentaDTO mapCuentaToCuentaDTO(Cuenta cuenta);

	MovimientosDTO mapMovimientosToMovimientosDTO(Movimientos movimientos);
}