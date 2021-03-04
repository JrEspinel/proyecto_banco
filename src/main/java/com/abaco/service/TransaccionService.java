package com.abaco.service;

import java.util.List;

import com.abaco.model.Transaccion;

public interface TransaccionService {
	
	public List<Transaccion> listarTodo();
	public void guardar(Transaccion transaccion);

}
