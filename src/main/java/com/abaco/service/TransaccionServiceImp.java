package com.abaco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abaco.model.Transaccion;
import com.abaco.repository.TransaccionRepository;

@Service
public class TransaccionServiceImp implements TransaccionService{
	
	@Autowired
	TransaccionRepository transaccionRepository;

	@Override
	public List<Transaccion> listarTodo() {
		
		return transaccionRepository.findAll();
	}

	@Override
	public void guardar(Transaccion transaccion) {
		transaccionRepository.save(transaccion);
	} 
	
	
}
