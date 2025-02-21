/**
 * 
 */
package com.ejercicio.MSCuenta.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
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

import com.ejercicio.MSCuenta.model.Cuenta;
import com.ejercicio.MSCuenta.model.CuentaDTO;
import com.ejercicio.MSCuenta.service.CuentaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 
 */
@SpringBootTest
@AutoConfigureMockMvc
class CuentaControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	CuentaService cuentaService;

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
	void testGetAllResponseOKWithListNotEmpty() throws Exception {
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = new Cuenta(1L, 1L, "2245488", "Corriente", 500, "activo");
		cuentas.add(cuenta);
		when(cuentaService.findAll()).thenReturn(cuentas);
		mockMvc.perform(MockMvcRequestBuilders.get("/cuentas").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpectAll(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	void testGetAllResponseBADWithListNull() throws Exception {
		when(cuentaService.findAll()).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.get("/cuentas").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}

	@Test
	void testGetResponseOKWhenClientExits() throws Exception {
		Cuenta cuenta = new Cuenta(1L, 1L, "2245488", "Corriente", 500, "activo");
		when(cuentaService.findById(9999L)).thenReturn(Optional.of(cuenta));
		mockMvc.perform(MockMvcRequestBuilders.get("/cuentas/9999").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpectAll(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	void testGetResponseOKWhenClientNotExits() throws Exception {
		when(cuentaService.findById(9999L)).thenReturn(Optional.empty());
		mockMvc.perform(MockMvcRequestBuilders.get("/cuentas/9999").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	void testGetCuentasByClienteIdOk() throws Exception {
		List<Cuenta> listaCuentas = new ArrayList<>();
		Cuenta cuenta = new Cuenta(1L, 1L, "2245488", "Corriente", 500, "activo");
		listaCuentas.add(cuenta);

		when(cuentaService.findByClientId(1L)).thenReturn(listaCuentas);
		mockMvc.perform(MockMvcRequestBuilders.get("/cuentas/cliente/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpectAll(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	void testGetCuentasByClienteIdEmpty() throws Exception {
		List<Cuenta> listaCuentas = new ArrayList<>();
		Cuenta cuenta = new Cuenta(1L, 1L, "2245488", "Corriente", 500, "activo");
		listaCuentas.add(cuenta);

		when(cuentaService.findByClientId(1L)).thenReturn(new ArrayList<>());
		mockMvc.perform(MockMvcRequestBuilders.get("/cuentas/cliente/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpectAll(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	void testCrearCuentaOK() throws Exception {
		CuentaDTO cuentaDTO = new CuentaDTO(1L, 1L, "2245488", "Corriente", 500, "activo");
		Cuenta cuenta = new Cuenta(1L, 1L, "2245488", "Corriente", 500, "activo");
		when(cuentaService.save(1L, cuentaDTO)).thenReturn(cuenta);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(cuentaDTO);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/cuentas/1").contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andExpect(status().isCreated())
				.andExpectAll(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	void testCrearCuentaFail() throws Exception {
		CuentaDTO cuentaDTO = new CuentaDTO(1L, 1L, "2245488", "Corriente", 500, "activo");
		when(cuentaService.save(1L, cuentaDTO)).thenReturn(null);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(cuentaDTO);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/cuentas/1").contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andExpect(status().isNotFound());
	}

	@Test
	void testUpdateCuentaOK() throws Exception {
		CuentaDTO cuentaDTO = new CuentaDTO(1L, 1L, "2245488", "Corriente", 500, "activo");
		Cuenta cuenta = new Cuenta(1L, 1L, "2245488", "Corriente", 500, "activo");
		when(cuentaService.update(1L, cuentaDTO)).thenReturn(cuenta);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(cuentaDTO);

		mockMvc.perform(
				MockMvcRequestBuilders.put("/cuentas/1").contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andExpect(status().isOk())
				.andExpectAll(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	void testUpdateCuentaFail() throws Exception {
		CuentaDTO cuentaDTO = new CuentaDTO(1L, 1L, "2245488", "Corriente", 500, "activo");
		when(cuentaService.update(1L, cuentaDTO)).thenReturn(null);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(cuentaDTO);

		mockMvc.perform(
				MockMvcRequestBuilders.put("/cuentas/1").contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andExpect(status().isNotFound());
	}

	@Test
	void testDeleteCuentaOK() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/cuentas/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void testDeleteCuentaFail() throws Exception {
		Mockito.doThrow(new UncheckedIOException(new IOException("Exception"))).when(cuentaService).deleteById(9999L);
		mockMvc.perform(MockMvcRequestBuilders.delete("/cuentas/9999").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	void testDeleteByClienteIdOK() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/cuentas/cliente/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void testDeleteByClienteIdFail() throws Exception {
		Mockito.doThrow(new UncheckedIOException(new IOException("Exception"))).when(cuentaService)
				.deleteByClientId(1L);
		mockMvc.perform(MockMvcRequestBuilders.delete("/cuentas/cliente/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

}
