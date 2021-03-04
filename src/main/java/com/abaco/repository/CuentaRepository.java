package com.abaco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abaco.model.Cliente;
import com.abaco.model.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, String> {
	
	public List<Cuenta> findByEstado(Boolean estado);
	
	public List<Cuenta> findByClienteCedula(Cliente clienteCedula);

}
