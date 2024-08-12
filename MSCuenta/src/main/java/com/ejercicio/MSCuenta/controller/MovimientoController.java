package com.ejercicio.MSCuenta.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ejercicio.MSCuenta.mapstruct.MapStructService;
import com.ejercicio.MSCuenta.model.Movimientos;
import com.ejercicio.MSCuenta.model.MovimientosDTO;
import com.ejercicio.MSCuenta.service.CuentaService;
import com.ejercicio.MSCuenta.service.MovimientoService;
import com.ejercicio.MSCuenta.utils.EjercicioUtil;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

	@Autowired
	CuentaService cuentaService;

	@Autowired
	MovimientoService movimientoService;

	@Autowired
	MapStructService mapStructService;

	@GetMapping
	public ResponseEntity<List<MovimientosDTO>> obtenerTodosMovimientos() {
		List<Movimientos> movimientos = movimientoService.findAll();
		if (movimientos != null) {
			return new ResponseEntity<>(
					EjercicioUtil.convertList(movimientos, m -> mapStructService.mapMovimientosToMovimientosDTO(m)),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MovimientosDTO> obtenerMovimientoPorId(@PathVariable("id") long id) {
		Optional<Movimientos> movimientoData = movimientoService.findById(id);
		if (movimientoData.isPresent()) {
			return new ResponseEntity<>(mapStructService.mapMovimientosToMovimientosDTO(movimientoData.get()),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/{cuentaId}")
	public ResponseEntity<MovimientosDTO> crearMovimiento(@PathVariable Long cuentaId,
			@RequestBody MovimientosDTO movimientoDTO) {
		Movimientos movimiento = movimientoService.save(cuentaId, movimientoDTO);
		if (movimiento != null) {
			return new ResponseEntity<>(mapStructService.mapMovimientosToMovimientosDTO(movimiento),
					HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{id}")
	public ResponseEntity<MovimientosDTO> updateMovimiento(@PathVariable("id") long id,
			@RequestBody MovimientosDTO movimientoDTO) {
		Movimientos movimientos = movimientoService.update(id, movimientoDTO);
		if (movimientos != null) {
			return new ResponseEntity<>(mapStructService.mapMovimientosToMovimientosDTO(movimientos), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> eliminarMovimiento(@PathVariable("id") long id) {
		try {
			movimientoService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/movimientos")
	public List<Movimientos> getMovimientos(@RequestParam long id,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
		return movimientoService.findByIdAndFechaBetween(id, startDate, endDate);
	}

}