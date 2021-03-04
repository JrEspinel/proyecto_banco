package com.abaco.service;

import java.util.List;
import java.util.Optional;

import com.abaco.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.abaco.repository.ClienteRepository;

@Service
public class ClienteServiceImp implements ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public List<Cliente> listarTodo() {
		return clienteRepository.findAll();
	}

	@Override
	public void guardar(Cliente cliente) {
		clienteRepository.save(cliente);
	}

	@Override
	public Optional<Cliente> buscarPorCedula(String cedula) {

		return clienteRepository.findById(cedula);
	}

	@Override
	public void eliminarPorCedula(String cedula) {
		clienteRepository.deleteById(cedula);
	}

}
