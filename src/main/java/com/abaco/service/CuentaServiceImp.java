package com.abaco.service;

import com.abaco.model.Cliente;
import com.abaco.model.Cuenta;
import com.abaco.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaServiceImp implements CuentaService {

    @Autowired
    CuentaRepository cuentaRepository;


    @Override
    public List<Cuenta> listarTodo() {
        return cuentaRepository.findAll();
    }


	@Override
	public void guardar(Cuenta cuenta) {
		cuentaRepository.save(cuenta);
		
	}


	@Override
	public void eliminarPorCuenta(String numeroCuenta) {
		cuentaRepository.deleteById(numeroCuenta);
	}


	@Override
	public Optional<Cuenta> buscarPorId(String numeroCuenta) {
		return cuentaRepository.findById(numeroCuenta);		
	}


	@Override
	public List<Cuenta> listarPorEstado(boolean estado) {
		return cuentaRepository.findByEstado(estado);
	}


	@Override
	public List<Cuenta> buscarPorCedula(Cliente clienteCedula) {
		
		return cuentaRepository.findByClienteCedula(clienteCedula);
	}

}
