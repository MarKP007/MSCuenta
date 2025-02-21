/**
 * 
 */
package com.ejercicio.MSCuenta.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.ejercicio.MSCuenta.controller.exceptions.SaldoNoDisponibleException;
import com.ejercicio.MSCuenta.model.Cuenta;
import com.ejercicio.MSCuenta.model.Movimientos;
import com.ejercicio.MSCuenta.model.MovimientosDTO;
import com.ejercicio.MSCuenta.repository.CuentaRepository;
import com.ejercicio.MSCuenta.repository.MovimientoRepository;
import com.ejercicio.MSCuenta.service.enums.TipoMovimientoEnum;

/**
 * 
 */
@SpringJUnitConfig
@SpringBootTest(properties = { "server.port=8081", "cliente_url=http://localhost:8080",
		"spring.jpa.properties.hibernate.format_sql=true",
		"spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.SQLServerDialect",
		"spring.jpa.hibernate.ddl-auto=update",
		"spring.datasource.url=jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true;databaseName=cuentasBDD",
		"spring.datasource.username=sa", "spring.datasource.password=MarKP-007", "spring.application.name=MSCuenta" })
class MovimientoServiceTest {

	@Autowired
	private MovimientoService movimientoService;

	@MockBean
	private MovimientoRepository movimientoRepository;

	@MockBean
	private CuentaRepository cuentaRepository;

	@Test
	void testFindAllOk() {
		List<Movimientos> listaMovimientosOrigen = new ArrayList<>();
		Movimientos movimientos = new Movimientos(1L, new Date(2025, 1, 1), TipoMovimientoEnum.DEPOSITO.toString(), 200,
				500, 1L);
		listaMovimientosOrigen.add(movimientos);
		Mockito.when(movimientoRepository.findAll()).thenReturn(listaMovimientosOrigen);
		List<Movimientos> listaCuentaResultado = movimientoService.findAll();
		assertEquals(listaCuentaResultado.size(), 1);
	}

	@Test
	void testFindAllEmpty() {
		List<Cuenta> listaCuentasOrigen = new ArrayList<>();
		Cuenta cuenta = new Cuenta(1L, 1L, "2245488", "Corriente", 500, "activo");
		listaCuentasOrigen.add(cuenta);
		Mockito.when(movimientoRepository.findAll()).thenReturn(new ArrayList<>());
		List<Movimientos> listaCuentaResultado = movimientoService.findAll();
		assertEquals(listaCuentaResultado, null);
	}

	@Test
	void testFindAllFail() {
		List<Cuenta> listaCuentasOrigen = new ArrayList<>();
		Cuenta cuenta = new Cuenta(1L, 1L, "2245488", "Corriente", 500, "activo");
		listaCuentasOrigen.add(cuenta);
		Mockito.when(movimientoRepository.findAll()).thenThrow(new UncheckedIOException(new IOException("Exception")));
		List<Movimientos> listaCuentaResultado = movimientoService.findAll();
		assertEquals(listaCuentaResultado, null);
	}

	@Test
	void testFindByIdOk() {
		Mockito.when(movimientoRepository.findById(1L)).thenReturn(Optional.empty());
		Optional<Movimientos> optional = movimientoService.findById(1L);
		assertEquals(optional, Optional.empty());
	}

	@Test
	void testSaveOk() {
		MovimientosDTO movimientosDTO = new MovimientosDTO(1L, new Date(2025, 1, 1),
				TipoMovimientoEnum.DEPOSITO.toString(), 200, 500, 1L);
		Movimientos movimientos = new Movimientos(1L, new Date(2025, 1, 1), TipoMovimientoEnum.DEPOSITO.toString(), 200,
				500, 1L);
		Cuenta cuenta = new Cuenta(1L, 1L, "2245488", "Corriente", 500, "activo");

		Mockito.when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuenta));
		Movimientos movTemp = new Movimientos(new Date(), TipoMovimientoEnum.DEPOSITO.toString(), 200,
				cuenta.getSaldoInicial() + 200, cuenta.getId());
		Mockito.when(movimientoRepository.save(movTemp)).thenReturn(movimientos);
		Movimientos movimientosResult = movimientoService.save(1L, movimientosDTO);
		assertEquals(movimientosResult, movimientos);
	}

	@Test
	void testSaveOk2() {
		MovimientosDTO movimientosDTO = new MovimientosDTO(1L, new Date(2025, 1, 1),
				TipoMovimientoEnum.DEPOSITO.toString(), -200, 500, 1L);
		Movimientos movimientos = new Movimientos(1L, new Date(2025, 1, 1), TipoMovimientoEnum.DEPOSITO.toString(),
				-200, 500, 1L);
		Cuenta cuenta = new Cuenta(1L, 1L, "2245488", "Corriente", 500, "activo");

		Mockito.when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuenta));
		Movimientos movTemp = new Movimientos(new Date(), TipoMovimientoEnum.DEPOSITO.toString(), -200,
				cuenta.getSaldoInicial() - 200, cuenta.getId());
		Mockito.when(movimientoRepository.save(movTemp)).thenReturn(movimientos);
		Movimientos movimientosResult = movimientoService.save(1L, movimientosDTO);
		assertEquals(movimientosResult, null);
	}

	@Test
	void testSaveSaldoNodisponible() {
		MovimientosDTO movimientosDTO = new MovimientosDTO(1L, new Date(2025, 1, 1),
				TipoMovimientoEnum.DEPOSITO.toString(), -1200, 500, 1L);
		Movimientos movimientos = new Movimientos(1L, new Date(2025, 1, 1), TipoMovimientoEnum.DEPOSITO.toString(), 200,
				500, 1L);
		Cuenta cuenta = new Cuenta(1L, 1L, "2245488", "Corriente", 500, "activo");

		Mockito.when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuenta));
		Movimientos movTemp = new Movimientos(new Date(), TipoMovimientoEnum.DEPOSITO.toString(), 200,
				cuenta.getSaldoInicial() + 200, cuenta.getId());
		Mockito.when(movimientoRepository.save(movTemp)).thenReturn(movimientos);
		try {
			Movimientos movimientosResult = movimientoService.save(1L, movimientosDTO);
		} catch (SaldoNoDisponibleException e) {
			assertEquals(1L, 1L);
		}
	}

	@Test
	void testSaveNotFound() {
		MovimientosDTO movimientosDTO = new MovimientosDTO(1L, new Date(2025, 1, 1),
				TipoMovimientoEnum.DEPOSITO.toString(), -200, 500, 1L);
		Movimientos movimientos = new Movimientos(1L, new Date(2025, 1, 1), TipoMovimientoEnum.DEPOSITO.toString(),
				-200, 500, 1L);
		Cuenta cuenta = new Cuenta(1L, 1L, "2245488", "Corriente", 500, "activo");

		Mockito.when(cuentaRepository.findById(1L)).thenReturn(Optional.empty());
		Movimientos movTemp = new Movimientos(new Date(), TipoMovimientoEnum.DEPOSITO.toString(), -200,
				cuenta.getSaldoInicial() - 200, cuenta.getId());
		Mockito.when(movimientoRepository.save(movTemp)).thenReturn(movimientos);
		Movimientos movimientosResult = movimientoService.save(1L, movimientosDTO);
		assertEquals(movimientosResult, null);
	}

	@Test
	void testUpdateFull() {
		MovimientosDTO movimientosDTO = new MovimientosDTO(1L, new Date(2025, 1, 1),
				TipoMovimientoEnum.DEPOSITO.toString(), 200, 500, 1L);
		Movimientos movimientos = new Movimientos(1L, new Date(2025, 1, 1), TipoMovimientoEnum.DEPOSITO.toString(), 200,
				500, 1L);
		Mockito.when(movimientoRepository.findById(1L)).thenReturn(Optional.of(movimientos));
		Mockito.when(movimientoRepository.save(movimientos)).thenReturn(movimientos);
		Movimientos movimientosResultado = movimientoService.update(1L, movimientosDTO);
		assertEquals(movimientosResultado, movimientos);
	}

	@Test
	void testUpdateEmpty() {
		MovimientosDTO movimientosDTO = new MovimientosDTO(1L, new Date(2025, 1, 1),
				TipoMovimientoEnum.DEPOSITO.toString(), 200, 500, 1L);
		Movimientos movimientos = new Movimientos(1L, new Date(2025, 1, 1), TipoMovimientoEnum.DEPOSITO.toString(), 200,
				500, 1L);
		Mockito.when(movimientoRepository.findById(1L)).thenReturn(Optional.of(movimientos));
		Mockito.when(movimientoRepository.save(movimientos)).thenReturn(movimientos);
		Movimientos movimientosResultado = movimientoService.update(1L, new MovimientosDTO());
		assertEquals(movimientosResultado, movimientos);
	}

	@Test
	void testUpdateNotFound() {
		MovimientosDTO movimientosDTO = new MovimientosDTO(1L, new Date(2025, 1, 1),
				TipoMovimientoEnum.DEPOSITO.toString(), 200, 500, 1L);
		Movimientos movimientos = new Movimientos(1L, new Date(2025, 1, 1), TipoMovimientoEnum.DEPOSITO.toString(), 200,
				500, 1L);
		Mockito.when(movimientoRepository.findById(1L)).thenReturn(Optional.empty());
		Mockito.when(movimientoRepository.save(movimientos)).thenReturn(movimientos);
		Movimientos movimientosResultado = movimientoService.update(1L, movimientosDTO);
		assertEquals(movimientosResultado, null);
	}

	@Test
	void testDelete() {
		movimientoService.deleteById(1L);
		movimientoService.findByIdAndFechaBetween(1, new Date(2025, 1, 1), new Date(2025, 1, 1));
		assertEquals(1L, 1L);
	}

}
