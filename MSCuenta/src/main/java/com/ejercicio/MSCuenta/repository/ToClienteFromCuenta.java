package com.ejercicio.MSCuenta.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ejercicio.MSCuenta.configuration.FeignCuentaConfig;
import com.ejercicio.MSCuenta.model.ClienteDTO;
import com.ejercicio.MSCuenta.utils.CampoEntidad;

@FeignClient(name = "Cliente", url = "${cliente_url}", configuration = FeignCuentaConfig.class)
public interface ToClienteFromCuenta {

	@GetMapping("/clientes/{id}")
	ResponseEntity<ClienteDTO> getClientById(@PathVariable(CampoEntidad.ID) long id);
}
