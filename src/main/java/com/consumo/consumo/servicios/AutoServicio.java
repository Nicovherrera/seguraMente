package com.consumo.consumo.servicios;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.consumo.consumo.entidades.Auto;
import com.consumo.consumo.errores.ErrorServicio;
import com.consumo.consumo.repositorios.AutoRepo;

@Service
public class AutoServicio {
	
	@Autowired
	private AutoRepo autoRepo;
	
	@Transactional
	public void registroAuto (String marca, Integer anio, String modelo, Integer kmActuales, String mail) {
		
		Auto auto=new Auto();
		
		auto.setFecha(new Date());
		auto.setMarca(marca.toUpperCase());
		auto.setAnio(anio);
		auto.setModelo(modelo.toUpperCase());
		auto.setKmActuales(kmActuales);
		auto.setMail(mail);
		
		autoRepo.save(auto);
	}
	
	public void calcularConsumo(String mail, Integer newKmActuales, Integer litros) throws ErrorServicio {
		
		validarConsumo(mail,newKmActuales,litros);
		
		Auto cmoAuto= autoRepo.buscarPorMail(mail);
		
		if (cmoAuto!=null) {
			
			cmoAuto.setConsumo((((double)litros/((double)newKmActuales-cmoAuto.getKmActuales()))*100));
			
			autoRepo.save(cmoAuto);
			
		}else{
			throw new ErrorServicio("No existe registro con ese mail.");
		}
	}
	
	public Auto buscarAutoPorMail(String mail) {
		return autoRepo.buscarPorMail(mail);
	}
	
	public void validarConsumo(String mail, Integer newKmActuales, Integer litros) throws ErrorServicio {
		
		if(mail.isEmpty()|| mail==null) {
			throw new ErrorServicio ("El mail no puede estar vacio ni ser nulo");
		}
		if (newKmActuales==null) {
			throw new ErrorServicio("El mail no puede estar vacio ni ser nulo");
		}else {
			Auto kmAuto= autoRepo.buscarPorMail(mail);
			if(kmAuto.getKmActuales()>newKmActuales) {
				throw new ErrorServicio("El Kilometraje actual no puede ser menor al registrado anteriormente. Vamos a intentarlo nuevamente volve a ingresar tu mail");
			}
		}
		if (litros==null) {
			throw new ErrorServicio("Los litros no pueden ser nulos");
		}
		
	}

}
