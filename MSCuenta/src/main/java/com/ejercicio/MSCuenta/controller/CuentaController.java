package com.ejercicio.MSCuenta.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejercicio.MSCuenta.mapstruct.MapStructService;
import com.ejercicio.MSCuenta.model.Cuenta;
import com.ejercicio.MSCuenta.model.CuentaDTO;
import com.ejercicio.MSCuenta.service.CuentaService;
import com.ejercicio.MSCuenta.utils.CampoEntidad;
import com.ejercicio.MSCuenta.utils.EjercicioUtil;

import jakarta.validation.Valid;

// TODO: Auto-generated Javadoc
/**
 * The Class CuentaController.
 */
@RestController
@RequestMapping("/cuentas")
public class CuentaController {

	/** The cuenta service. */
	@Autowired
	CuentaService cuentaService;

	/** The map struct service. */
	@Autowired
	MapStructService mapStructService;

	/**
	 * Obtener todos cuentas.
	 *
	 * @return the response entity
	 */
	@GetMapping
	public ResponseEntity<List<CuentaDTO>> obtenerTodosCuentas() {
		List<Cuenta> cuentas = cuentaService.findAll();
		if (cuentas != null) {
			return new ResponseEntity<>(
					EjercicioUtil.convertList(cuentas, c -> mapStructService.mapCuentaToCuentaDTO(c)), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Obtener cuenta por id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("/{id}")
	public ResponseEntity<CuentaDTO> obtenerCuentaPorId(@PathVariable(CampoEntidad.ID) long id) {
		Optional<Cuenta> cuentaData = cuentaService.findById(id);
		if (cuentaData.isPresent()) {
			return new ResponseEntity<>(mapStructService.mapCuentaToCuentaDTO(cuentaData.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Gets the cuentas by cliente id.
	 *
	 * @param clienteId the cliente id
	 * @return the cuentas by cliente id
	 */
	@GetMapping("/cliente/{clienteId}")
	public List<CuentaDTO> getCuentasByClienteId(@PathVariable long clienteId) {
		List<Cuenta> listaCuenta = cuentaService.findByClientId(clienteId);
		if (listaCuenta.isEmpty()) {
			return new ArrayList<>();
		}
		return EjercicioUtil.convertList(listaCuenta, c -> mapStructService.mapCuentaToCuentaDTO(c));
	}

	/**
	 * Crear cuenta.
	 *
	 * @param clienteId the cliente id
	 * @param cuenta    the cuenta
	 * @return the response entity
	 */
	@PostMapping("/{clienteId}")
	public ResponseEntity<CuentaDTO> crearCuenta(@PathVariable Long clienteId, @Valid @RequestBody CuentaDTO cuenta) {
		Cuenta _cuenta = cuentaService.save(clienteId, cuenta);
		if (_cuenta != null) {
			return new ResponseEntity<>(mapStructService.mapCuentaToCuentaDTO(_cuenta), HttpStatus.CREATED);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	/**
	 * Update cuenta.
	 *
	 * @param id        the id
	 * @param cuentaDTO the cuenta DTO
	 * @return the response entity
	 */
	@PutMapping("/{id}")
	public ResponseEntity<CuentaDTO> updateCuenta(@PathVariable(CampoEntidad.ID) long id,
			@RequestBody CuentaDTO cuentaDTO) {
		Cuenta cuenta = cuentaService.update(id, cuentaDTO);
		if (cuenta != null) {
			return new ResponseEntity<>(mapStructService.mapCuentaToCuentaDTO(cuenta), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Eliminar cuenta.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> eliminarCuenta(@PathVariable(CampoEntidad.ID) long id) {
		try {
			cuentaService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Delete by client id.
	 *
	 * @param clientId the client id
	 * @return the response entity
	 */
	@DeleteMapping("/cliente/{clientId}")
	public ResponseEntity<HttpStatus> deleteByClientId(@PathVariable long clientId) {
		try {
			cuentaService.deleteByClientId(clientId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}