package com.consumo.consumo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.consumo.consumo.entidades.Precios;

@Repository
public interface PreciosRepo extends JpaRepository <Precios,String> {
	
	@Query("SELECT p FROM Precios p WHERE p.producto= :producto ORDER BY p.fechaCompra DESC")
	public List<Precios> buscarPrecioxNombre (@Param("producto") String producto);
	
	@Query("SELECT p FROM Precios p WHERE p.estado=1 ORDER BY p.fechaCompra DESC")
	   public List <Precios> ordenDatePrecios ();
	
	@Query("SELECT p FROM Precios p WHERE p.id= :id")
    public Precios buscarPrecioxId (@Param("id") String id);
}  
