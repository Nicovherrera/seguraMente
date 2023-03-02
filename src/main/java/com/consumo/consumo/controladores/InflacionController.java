package com.consumo.consumo.controladores;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.consumo.consumo.entidades.Precios;
import com.consumo.consumo.errores.ErrorServicio;
import com.consumo.consumo.servicios.PrecioServicio;

@Controller
@RequestMapping("/")
public class InflacionController {
	
	@Autowired
	private PrecioServicio precioS;
	
	@GetMapping("/calculoInflacion")
	public String calculoDeInflacion (ModelMap listaPrecios) {
		
		List<Precios> listaP=precioS.preciosRegistrados();
		
		listaPrecios.put("listaP", listaP);
		
		return "calculaInflacion.html";
	}
	
	@PostMapping("/registroPrecio")
	public String registroPrecio (ModelMap mapa, @RequestParam String fechaCompra, @RequestParam String nombreProveedro, @RequestParam String produco,
			String descripcion, @RequestParam Double precio) throws Exception {
		
		try {
			
			SimpleDateFormat fechaFormateada = new SimpleDateFormat("yyyy-MM-dd");
			Date fechaCompraF = fechaFormateada.parse(fechaCompra);
			
			precioS.crearRegistro(fechaCompraF, nombreProveedro, produco, descripcion, precio);
			return "redirect:/calculoInflacion";
		}catch(ErrorServicio e) {
			mapa.put("errorR", e.getMessage());
			
			mapa.put("nombreProveedro", e.getMessage());
			mapa.put("produco", e.getMessage());
			mapa.put("precio", e.getMessage());
			return "calculaInflacion.html";	
		}
	}

}
