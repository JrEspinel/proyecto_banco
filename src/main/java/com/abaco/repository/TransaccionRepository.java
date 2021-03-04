package com.abaco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abaco.model.Transaccion;

public interface TransaccionRepository extends JpaRepository<Transaccion, Integer>{

}
