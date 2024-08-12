package com.ejercicio.MSCuenta.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ejercicio.MSCuenta.model.ClienteDTO;
import com.ejercicio.MSCuenta.model.Cuenta;
import com.ejercicio.MSCuenta.model.CuentaDTO;
import com.ejercicio.MSCuenta.repository.CuentaRepository;
import com.ejercicio.MSCuenta.repository.ToClienteFromCuenta;
import com.ejercicio.MSCuenta.utils.EjercicioUtil;

@Service
public class CuentaService {

	@Autowired
	private CuentaRepository cuentaRepository;

	@Autowired
	private ToClienteFromCuenta toClienteFromCuenta;

	public List<Cuenta> findAll() {
		try {
			List<Cuenta> cuentas = new ArrayList<Cuenta>();

			cuentaRepository.findAll().forEach(cuentas::add);

			if (cuentas.isEmpty()) {
				return null;
			}
			return cuentas;
		} catch (Exception e) {
			return null;
		}
	}

	public Optional<Cuenta> findById(long id) {
		return cuentaRepository.findById(id);
	}

	public Cuenta save(Long clienteId, CuentaDTO cuentaDTO) {
		ResponseEntity<ClienteDTO> clienteDTO;
		try {
			clienteDTO = toClienteFromCuenta.getClientById(clienteId);
		} catch (Exception e) {
			return null;
		}
		try {
			Cuenta _cuenta = null;
			if (HttpStatus.OK.equals(clienteDTO.getStatusCode())) {
				_cuenta = cuentaRepository.save(new Cuenta(cuentaDTO.getNumeroCuenta(), cuentaDTO.getTipoCuenta(),
						cuentaDTO.getSaldoInicial(), cuentaDTO.getEstado(), clienteId));
			}
			return _cuenta;
		} catch (Exception e) {
			return null;
		}
	}

	public Cuenta update(Long id, CuentaDTO cuentaDTO) {
		Optional<Cuenta> cuentaData = findById(id);

		if (cuentaData.isPresent()) {
			Cuenta _cuenta = cuentaData.get();
			_cuenta.setNumeroCuenta(
					EjercicioUtil.esCampoLleno(cuentaDTO.getNumeroCuenta()) ? cuentaDTO.getNumeroCuenta()
							: _cuenta.getNumeroCuenta());
			_cuenta.setTipoCuenta(EjercicioUtil.esCampoLleno(cuentaDTO.getTipoCuenta()) ? cuentaDTO.getTipoCuenta()
					: _cuenta.getTipoCuenta());
			_cuenta.setSaldoInicial(
					EjercicioUtil.esCampoLleno(cuentaDTO.getSaldoInicial()) ? cuentaDTO.getSaldoInicial()
							: _cuenta.getSaldoInicial());
			_cuenta.setEstado(
					EjercicioUtil.esCampoLleno(cuentaDTO.getEstado()) ? cuentaDTO.getEstado() : _cuenta.getEstado());
			return cuentaRepository.save(_cuenta);
		} else {
			return null;
		}
	}

	public void deleteById(long id) {
		cuentaRepository.deleteById(id);
	}

	public List<Cuenta> findByClientId(long clienteId) {
		return cuentaRepository.findByClientId(clienteId);
	}

	public void deleteByClientId(long clientId) {
		cuentaRepository.deleteAll(cuentaRepository.findByClientId(clientId));
	}

}
