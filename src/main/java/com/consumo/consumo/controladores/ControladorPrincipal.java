package com.consumo.consumo.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.consumo.consumo.entidades.Auto;
import com.consumo.consumo.errores.ErrorServicio;
import com.consumo.consumo.servicios.AutoServicio;

@Controller
@RequestMapping("/")
public class ControladorPrincipal {
	
	@Autowired
	private AutoServicio autoServicio;
	
	@GetMapping("/")
	public String inicio () {
		return "index.html";
	}
	
	@PostMapping("/consumo")
	public String inicioConMail (ModelMap mapa, @RequestParam String mail) {
		
		Auto auto = autoServicio.buscarAutoPorMail(mail);
		if(auto!= null) {
			if(auto.getConsumo()==null) {
				mapa.put("autoEncontrado", "Con este mail, registraste "+auto.getKmActuales()+" km anteriormente.");
				mapa.put("autoCargado", auto);	
			}else {
				mapa.put("consumoEncontrado", "Tu auto consume: ");
				mapa.put("consumoAutoEncontrado", auto);
			}
			
			return "index.html";
		}else {
			
			mapa.addAttribute("mensaje", "Con tu mail, aún no hay registros. Llená el tanque y registrá el kilometraje de tu auto.");
			mapa.put("mail", mail);
			return "index.html";
		}
			
	}
	
	@PostMapping("/datosKm")
	public String datosKm (ModelMap mapa, @RequestParam String marca, @RequestParam Integer anio, @RequestParam String modelo, @RequestParam Integer kilometros, @RequestParam String mail) {
				
		autoServicio.registroAuto(marca, anio, modelo, kilometros, mail);
		
		mapa.addAttribute("msjExito", "Excelente! tu auto tiene "+" "+ kilometros+ " km actualmente. ");
		mapa.addAttribute("mail",mail);
		mapa.addAttribute("marca",marca);
		mapa.addAttribute("kilometros",kilometros);
		
		return "index.html";
	}
	
	@PostMapping("/calculoDeConsumo")
	public String calculoDeConsumo(ModelMap consumo, @RequestParam Integer kilometraje, @RequestParam Integer litros, @RequestParam String mail) throws ErrorServicio {
		
		try {
			autoServicio.calcularConsumo(mail, kilometraje, litros);	
		}catch(ErrorServicio e) {
			consumo.addAttribute("error", e.getMessage());
			return "index.html";
		}
		
		
		Auto autoConsumo = autoServicio.buscarAutoPorMail(mail);
		
		if (autoConsumo.getConsumo()!=null ) {
			consumo.addAttribute("consumo", autoConsumo);
			consumo.addAttribute("consumoMsj", "Tu auto consume: ");
		}
		
		return "index.html";
	}
	
	@GetMapping("/iniciarSesion")
	public String iniciarSesion () {
		return "iniciarSesion.html";
	}
	
	@GetMapping("/registro")
	public String registro () {
		return "registro.html";
	}
	
}
