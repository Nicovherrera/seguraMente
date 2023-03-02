package com.consumo.consumo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.consumo.consumo.entidades.Precios;

@Repository
public interface PreciosRepo extends JpaRepository <Precios,String> {

}
