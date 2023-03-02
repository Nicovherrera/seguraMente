package com.consumo.consumo.servicios;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consumo.consumo.entidades.Precios;
import com.consumo.consumo.errores.ErrorServicio;
import com.consumo.consumo.repositorios.PreciosRepo;

@Service
public class PrecioServicio {
	
	@Autowired
	PreciosRepo precioRepo;
	
	public void crearRegistro(Date fechaCompra, String nombreProveedro, String produco,
			String descripcion, Double precio) throws Exception {
		
		validar(nombreProveedro,produco, descripcion, precio);
		
		Precios producto = new Precios();
		
		producto.setFechaCompra(fechaCompra);
		producto.setFechaCompraAnt(fechaCompra);
		producto.setNombreProveedro(nombreProveedro.toUpperCase());
		producto.setProduco(produco.toUpperCase());
		producto.setPrecio(precio);
		producto.setDescripcion(descripcion);
		producto.setInflacion(null);
		producto.setEstado(true);
		
		precioRepo.save(producto);
		
	} 
	
	public List<Precios> preciosRegistrados () {
		return precioRepo.findAll();
	}
	
	public void validar (String nombreProveedor, String producto, String descripcion, Double precio) throws Exception {
		
		if (nombreProveedor.isEmpty()|| nombreProveedor==null) {
			throw new ErrorServicio("El nombre del Proveedor no puede estar vacio o nulo");
		}
		if (producto.isEmpty() || producto==null) {
			throw new ErrorServicio("El nombre del Producto no puede estar vacio o nulo");
		}
		if (descripcion.isEmpty() || descripcion==null) {
			
		}
		if (precio==null || precio <= 0) {
			throw new ErrorServicio("El precio del producto no puede estar nulo ni menor o igual que 0");
		}
		
	}

}
