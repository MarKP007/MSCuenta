package com.ejercicio.MSCuenta.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejercicio.MSCuenta.controller.exceptions.SaldoNoDisponibleException;
import com.ejercicio.MSCuenta.model.Cuenta;
import com.ejercicio.MSCuenta.model.Movimientos;
import com.ejercicio.MSCuenta.model.MovimientosDTO;
import com.ejercicio.MSCuenta.repository.CuentaRepository;
import com.ejercicio.MSCuenta.repository.MovimientoRepository;
import com.ejercicio.MSCuenta.utils.EjercicioUtil;

@Service
public class MovimientoService {

	@Autowired
	private MovimientoRepository movimientoRepository;

	@Autowired
	private CuentaRepository cuentaRepository;

	public List<Movimientos> findAll() {
		try {
			List<Movimientos> movimientos = new ArrayList<Movimientos>();

			movimientoRepository.findAll().forEach(movimientos::add);

			if (movimientos.isEmpty()) {
				return null;
			}

			return movimientos;
		} catch (Exception e) {
			return null;
		}
	}

	public Optional<Movimientos> findById(long id) {
		return movimientoRepository.findById(id);
	}

	public Movimientos save(Long cuentaId, MovimientosDTO movimiento) {
		Optional<Cuenta> cuentaeData = cuentaRepository.findById(cuentaId);
		Cuenta cuenta;
		if (cuentaeData.isPresent()) {
			cuenta = cuentaeData.get();
		} else {
			return null;
		}

		cuenta.setSaldoInicial(cuenta.getSaldoInicial() + movimiento.getValor());
		if (cuenta.getSaldoInicial() < 0) {
			throw new SaldoNoDisponibleException("");
		}
		cuentaRepository.save(cuenta);

		String tipoMovimiento = movimiento.getValor() < 0 ? "RETIRO" : "DEPOSITO";
		try {
			Movimientos _movimiento = movimientoRepository.save(new Movimientos(new Date(), tipoMovimiento,
					movimiento.getValor(), cuenta.getSaldoInicial(), cuenta.getId()));
			_movimiento.setCuentaId(cuenta.getId());
			return _movimiento;
		} catch (Exception e) {
			return null;
		}
	}

	public Movimientos update(Long id, MovimientosDTO movimiento) {
		Optional<Movimientos> movimientoData = movimientoRepository.findById(id);

		if (movimientoData.isPresent()) {
			Movimientos _movimiento = movimientoData.get();
			_movimiento.setFecha(
					EjercicioUtil.esCampoLleno(movimiento.getFecha()) ? movimiento.getFecha() : _movimiento.getFecha());
			_movimiento.setTipoMovimiento(
					EjercicioUtil.esCampoLleno(movimiento.getTipoMovimiento()) ? movimiento.getTipoMovimiento()
							: _movimiento.getTipoMovimiento());
			_movimiento.setValor(
					EjercicioUtil.esCampoLleno(movimiento.getValor()) ? movimiento.getValor() : _movimiento.getValor());
			_movimiento.setSaldo(
					EjercicioUtil.esCampoLleno(movimiento.getSaldo()) ? movimiento.getSaldo() : _movimiento.getSaldo());
			return movimientoRepository.save(_movimiento);
		} else {
			return null;
		}
	}

	public void deleteById(long id) {
		movimientoRepository.deleteById(id);
	}

	public List<Movimientos> findByIdAndFechaBetween(long id, Date startDate, Date endDate) {
		return movimientoRepository.findByIdAndFechaBetween(id, startDate, endDate);
	}

}
