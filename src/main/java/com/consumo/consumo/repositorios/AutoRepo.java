package com.consumo.consumo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.consumo.consumo.entidades.Auto;


public interface AutoRepo extends JpaRepository<Auto,String> {
	
	@Query("SELECT a FROM Auto a WHERE a.mail= :mail")
    public Auto buscarPorMail (@Param("mail") String mail);

}
