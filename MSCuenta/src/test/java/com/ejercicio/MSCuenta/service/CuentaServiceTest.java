/**
 * 
 */
package com.ejercicio.MSCuenta.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.ejercicio.MSCuenta.model.Cuenta;
import com.ejercicio.MSCuenta.model.CuentaDTO;
import com.ejercicio.MSCuenta.repository.CuentaRepository;
import com.ejercicio.MSCuenta.repository.ToClienteFromCuenta;

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
class CuentaServiceTest {

	@Autowired
	private CuentaService cuentaService;

	@MockBean
	private CuentaRepository cuentaRepository;

	@MockBean
	private ToClienteFromCuenta toClienteFromCuenta;

	@Test
	void testFindAllOk() {
		List<Cuenta> listaCuentasOrigen = new ArrayList<>();
		Cuenta cuenta = new Cuenta(1L, 1L, "2245488", "Corriente", 500, "activo");
		listaCuentasOrigen.add(cuenta);
		Mockito.when(cuentaRepository.findAll()).thenReturn(listaCuentasOrigen);
		List<Cuenta> listaCuentaResultado = cuentaService.findAll();
		assertEquals(listaCuentaResultado.size(), 1);
	}

	@Test
	void testFindAllEmpty() {
		List<Cuenta> listaCuentasOrigen = new ArrayList<>();
		Cuenta cuenta = new Cuenta(1L, 1L, "2245488", "Corriente", 500, "activo");
		listaCuentasOrigen.add(cuenta);
		Mockito.when(cuentaRepository.findAll()).thenReturn(new ArrayList<>());
		List<Cuenta> listaCuentaResultado = cuentaService.findAll();
		assertEquals(listaCuentaResultado, null);
	}

	@Test
	void testFindAllFail() {
		List<Cuenta> listaCuentasOrigen = new ArrayList<>();
		Cuenta cuenta = new Cuenta(1L, 1L, "2245488", "Corriente", 500, "activo");
		listaCuentasOrigen.add(cuenta);
		Mockito.when(cuentaRepository.findAll()).thenThrow(new UncheckedIOException(new IOException("Exception")));
		List<Cuenta> listaCuentaResultado = cuentaService.findAll();
		assertEquals(listaCuentaResultado, null);
	}

	@Test
	void testFindByIdOk() {
		Mockito.when(cuentaRepository.findById(1L)).thenReturn(Optional.empty());
		Optional<Cuenta> optional = cuentaService.findById(1L);
		assertEquals(optional, Optional.empty());
	}

	@Test
	void testSaveOk() {
		CuentaDTO cuentaDTO = new CuentaDTO(1L, 1L, "2245488", "Corriente", 500, "activo");
		Cuenta cuenta = new Cuenta(1L, 1L, "2245488", "Corriente", 500, "activo");
		Mockito.when(toClienteFromCuenta.getClientById(1L)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
		Mockito.when(cuentaRepository.save(cuenta)).thenReturn(new Cuenta(cuentaDTO.getNumeroCuenta(),
				cuentaDTO.getTipoCuenta(), cuentaDTO.getSaldoInicial(), cuentaDTO.getEstado(), 1L));
		Cuenta cuentaResultado = cuentaService.save(1L, cuentaDTO);
		assertEquals(cuentaResultado, null);
	}

	@Test
	void testSaveOk1() {
		CuentaDTO cuentaDTO = new CuentaDTO(1L, 1L, "2245488", "Corriente", 500, "activo");
		Cuenta cuenta = new Cuenta(1L, 1L, "2245488", "Corriente", 500, "activo");
		Mockito.when(toClienteFromCuenta.getClientById(1L)).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		Mockito.when(cuentaRepository.save(cuenta)).thenReturn(new Cuenta(cuentaDTO.getNumeroCuenta(),
				cuentaDTO.getTipoCuenta(), cuentaDTO.getSaldoInicial(), cuentaDTO.getEstado(), 1L));
		Cuenta cuentaResultado = cuentaService.save(1L, cuentaDTO);
		assertEquals(cuentaResultado, null);
	}

	@Test
	void testSaveFail() {
		CuentaDTO cuentaDTO = new CuentaDTO(1L, 1L, "2245488", "Corriente", 500, "activo");
		Cuenta cuenta = new Cuenta(1L, 1L, "2245488", "Corriente", 500, "activo");
		Mockito.when(toClienteFromCuenta.getClientById(1L))
				.thenThrow(new UncheckedIOException(new IOException("Exception")));
		Mockito.when(cuentaRepository.save(cuenta)).thenReturn(new Cuenta(cuentaDTO.getNumeroCuenta(),
				cuentaDTO.getTipoCuenta(), cuentaDTO.getSaldoInicial(), cuentaDTO.getEstado(), 1L));
		Cuenta cuentaResultado = cuentaService.save(1L, cuentaDTO);
		assertEquals(cuentaResultado, null);
	}

	@Test
	void testSaveFail2() {
		CuentaDTO cuentaDTO = new CuentaDTO(1L, 1L, "2245488", "Corriente", 500, "activo");
		Cuenta cuenta = new Cuenta(1L, 1L, "2245488", "Corriente", 500, "activo");
		Mockito.when(toClienteFromCuenta.getClientById(1L)).thenReturn(new ResponseEntity<>(HttpStatus.OK));
		Mockito.when(cuentaRepository.save(new Cuenta(cuentaDTO.getNumeroCuenta(), cuentaDTO.getTipoCuenta(),
				cuentaDTO.getSaldoInicial(), cuentaDTO.getEstado(), 1L)))
				.thenThrow(new UncheckedIOException(new IOException("Exception")));
		Cuenta cuentaResultado = cuentaService.save(1L, cuentaDTO);
		assertEquals(cuentaResultado, null);
	}

	@Test
	void testUpdateEmpty() {
		CuentaDTO cuentaDTO = new CuentaDTO(1L, 1L, "2245488", "Corriente", 500, "activo");
		Cuenta cuenta = new Cuenta(1L, 1L, "2245488", "Corriente", 500, "activo");
		Mockito.when(cuentaRepository.findById(1L)).thenReturn(Optional.empty());
		Mockito.when(toClienteFromCuenta.getClientById(1L)).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		Mockito.when(cuentaRepository.save(cuenta)).thenReturn(cuenta);
		Cuenta cuentaResultado = cuentaService.update(1L, cuentaDTO);
		assertEquals(cuentaResultado, null);
	}

	@Test
	void testUpdateOk() {
		CuentaDTO cuentaDTO = new CuentaDTO(1L, 1L, "2245488", "Corriente", 500, "activo");
		Cuenta cuenta = new Cuenta(1L, 1L, "2245488", "Corriente", 500, "activo");
		Mockito.when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuenta));
		Mockito.when(toClienteFromCuenta.getClientById(1L)).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		Mockito.when(cuentaRepository.save(cuenta)).thenReturn(cuenta);
		Cuenta cuentaResultado = cuentaService.update(1L, cuentaDTO);
		assertEquals(cuentaResultado, cuenta);
	}

	@Test
	void testUpdateOk2() {
		Cuenta cuenta = new Cuenta(1L, 1L, "2245488", "Corriente", 500, "activo");
		Mockito.when(cuentaRepository.findById(1L)).thenReturn(Optional.of(cuenta));
		Mockito.when(toClienteFromCuenta.getClientById(1L)).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		Mockito.when(cuentaRepository.save(cuenta)).thenReturn(cuenta);
		Cuenta cuentaResultado = cuentaService.update(1L, new CuentaDTO());
		assertEquals(cuentaResultado, cuenta);
	}

	@Test
	void testdeleteById() {
		cuentaService.deleteById(1L);
		cuentaService.deleteByClientId(1L);
		assertEquals(1L, 1L);
	}

	@Test
	void testFindByClientId() {
		Mockito.when(cuentaRepository.findByClientId(1L)).thenReturn(new ArrayList<>());
		cuentaService.findByClientId(1L);
		assertEquals(1L, 1L);
	}

}
