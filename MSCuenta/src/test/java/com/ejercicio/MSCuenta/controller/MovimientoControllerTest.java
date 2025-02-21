/**
 * 
 */
package com.ejercicio.MSCuenta.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ejercicio.MSCuenta.model.Movimientos;
import com.ejercicio.MSCuenta.model.MovimientosDTO;
import com.ejercicio.MSCuenta.service.CuentaService;
import com.ejercicio.MSCuenta.service.MovimientoService;
import com.ejercicio.MSCuenta.service.enums.TipoMovimientoEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 
 */
@SpringBootTest
@AutoConfigureMockMvc
class MovimientoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	CuentaService cuentaService;

	@MockBean
	MovimientoService movimientoService;

	@DynamicPropertySource
	static void dynamicProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url",
				() -> "jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true;databaseName=cuentasBDD");
		registry.add("spring.datasource.username", () -> "sa");
		registry.add("spring.datasource.password", () -> "MarKP-007");
		registry.add("server.port", () -> "8081");
		registry.add("cliente_url", () -> "http://localhost:8080");
	}

	@Test
	void testObtenerTodosMovimientosOk() throws Exception {
		List<Movimientos> listaMovimientos = new ArrayList<>();
		Movimientos movimientos = new Movimientos(1L, new Date(2025, 1, 1), TipoMovimientoEnum.DEPOSITO.toString(), 200,
				500, 1L);
		listaMovimientos.add(movimientos);
		when(movimientoService.findAll()).thenReturn(listaMovimientos);
		mockMvc.perform(MockMvcRequestBuilders.get("/movimientos").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpectAll(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	void testObtenerTodosMovimientosFail() throws Exception {
		when(movimientoService.findAll()).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.get("/movimientos").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	void testObtenerMovimientoPorIdOk() throws Exception {
		Movimientos movimientos = new Movimientos(1L, new Date(2025, 1, 1), TipoMovimientoEnum.DEPOSITO.toString(), 200,
				500, 1L);
		when(movimientoService.findById(1L)).thenReturn(Optional.of(movimientos));
		mockMvc.perform(MockMvcRequestBuilders.get("/movimientos/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpectAll(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	void testObtenerMovimientoPorIdFail() throws Exception {
		when(movimientoService.findById(1L)).thenReturn(Optional.empty());
		mockMvc.perform(MockMvcRequestBuilders.get("/movimientos/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	void testCrearMovimientoOk() throws Exception {
		MovimientosDTO movimientosDTO = new MovimientosDTO(1L, new Date(2025, 1, 1),
				TipoMovimientoEnum.DEPOSITO.toString(), 200, 500, 1L);
		Movimientos movimientos = new Movimientos(1L, new Date(2025, 1, 1), TipoMovimientoEnum.DEPOSITO.toString(), 200,
				500, 1L);
		when(movimientoService.save(1L, movimientosDTO)).thenReturn(movimientos);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(movimientosDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/movimientos/1").contentType(MediaType.APPLICATION_JSON)
				.content(requestJson)).andExpect(status().isCreated())
				.andExpectAll(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	void testCrearMovimientoFail() throws Exception {
		MovimientosDTO movimientosDTO = new MovimientosDTO(1L, new Date(2025, 1, 1),
				TipoMovimientoEnum.DEPOSITO.toString(), 200, 500, 1L);
		when(movimientoService.save(1L, movimientosDTO)).thenReturn(null);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(movimientosDTO);

		mockMvc.perform(MockMvcRequestBuilders.post("/movimientos/1").contentType(MediaType.APPLICATION_JSON)
				.content(requestJson)).andExpect(status().isNotFound());
	}

	@Test
	void testUpdateCuentaOK() throws Exception {
		MovimientosDTO movimientosDTO = new MovimientosDTO(1L, new Date(2025, 1, 1),
				TipoMovimientoEnum.DEPOSITO.toString(), 200, 500, 1L);
		Movimientos movimientos = new Movimientos(1L, new Date(2025, 1, 1), TipoMovimientoEnum.DEPOSITO.toString(), 200,
				500, 1L);
		when(movimientoService.update(1L, movimientosDTO)).thenReturn(movimientos);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(movimientosDTO);

		mockMvc.perform(MockMvcRequestBuilders.put("/movimientos/1").contentType(MediaType.APPLICATION_JSON)
				.content(requestJson)).andExpect(status().isOk())
				.andExpectAll(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	void testUpdateCuentaFail() throws Exception {
		MovimientosDTO movimientosDTO = new MovimientosDTO(1L, new Date(2025, 1, 1),
				TipoMovimientoEnum.DEPOSITO.toString(), 200, 500, 1L);
		when(movimientoService.update(1L, movimientosDTO)).thenReturn(null);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(movimientosDTO);

		mockMvc.perform(MockMvcRequestBuilders.put("/movimientos/1").contentType(MediaType.APPLICATION_JSON)
				.content(requestJson)).andExpect(status().isNotFound());
	}

	@Test
	void testDeleteCuentaOK() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/movimientos/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void testDeleteCuentaFail() throws Exception {
		Mockito.doThrow(new UncheckedIOException(new IOException("Exception"))).when(movimientoService)
				.deleteById(9999L);
		mockMvc.perform(MockMvcRequestBuilders.delete("/movimientos/9999").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	void testGetMovimientosOk() throws Exception {
		when(movimientoService.findByIdAndFechaBetween(1L, new Date(2024, 11, 26), new Date(2025, 12, 26)))
				.thenReturn(new ArrayList<>());
		mockMvc.perform(
				MockMvcRequestBuilders.get("/movimientos/movimientos?startDate=2024-11-26&endDate=2025-12-26&id=1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
