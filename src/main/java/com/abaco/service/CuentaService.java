package com.abaco.service;


import com.abaco.model.Cliente;
import com.abaco.model.Cuenta;

import java.util.List;
import java.util.Optional;

public interface CuentaService {

    public List<Cuenta> listarTodo();
    public void guardar(Cuenta cuenta);
    public void eliminarPorCuenta(String numeroCuenta);
    public Optional<Cuenta> buscarPorId(String numeroCuenta);
    public List<Cuenta> listarPorEstado(boolean estado);
    public List<Cuenta> buscarPorCedula(Cliente cedulaCliente);

}
