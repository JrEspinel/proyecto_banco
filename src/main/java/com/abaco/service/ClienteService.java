package com.abaco.service;

import com.abaco.model.Cliente;

import java.util.List;
import java.util.Optional;


public interface ClienteService {
	public List<Cliente> listarTodo();

	public void guardar(Cliente cliente);
	public Optional<Cliente> buscarPorCedula(String cedula);
	public void eliminarPorCedula(String cedula);
}
