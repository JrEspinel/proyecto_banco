package com.abaco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abaco.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String>{
	
	public Cliente findByCedula(String cedula);

}
