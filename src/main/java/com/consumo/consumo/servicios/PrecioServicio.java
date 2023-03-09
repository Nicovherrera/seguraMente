package com.consumo.consumo.servicios;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.consumo.consumo.entidades.Precios;
import com.consumo.consumo.errores.ErrorServicio;
import com.consumo.consumo.repositorios.PreciosRepo;

@Service
public class PrecioServicio {
	
	@Autowired
	PreciosRepo precioRepo;
	
	@Transactional
	public void crearRegistro(Date fechaCompra, String nombreProveedro, String produco,
			String descripcion, Double precio) throws Exception {
		
		validar(nombreProveedro,produco, descripcion, precio);
		
		Precios producto = new Precios();
		
		producto.setFechaCompra(fechaCompra);
		producto.setFechaCompraAnt(fechaUltimaCompra(produco, fechaCompra));
		producto.setNombreProveedro(nombreProveedro.toUpperCase());
		producto.setProducto(produco.toUpperCase());
		producto.setPrecio(precio);
		producto.setPrecioCompAnt(precioAnterior(produco));
		producto.setDescripcion(descripcion);
		producto.setInflacion(inflacionMensual(producto));
		producto.setEstado(ultimaCarga(produco));
		
		precioRepo.save(producto);
		
	}
	
	@Transactional
	public Double precioAnterior (String produco) {
		
		List <Precios> preciosL= precioRepo.buscarPrecioxNombre(produco);
		
		for(Precios p:preciosL) {
			if(p.getFechaCompraAnt().before(new Date())) {
				p.setPrecioCompAnt(p.getPrecio());
			}return p.getPrecioCompAnt();
		}
		return 0.0;
	}
	
	@Transactional
	public Date fechaUltimaCompra(String produco, Date fechaCompra) {

		List<Precios> preciosL = precioRepo.buscarPrecioxNombre(produco);

		for (Precios p : preciosL) {

			if (p.getFechaCompra().before(fechaCompra)) {
				p.setFechaCompraAnt(p.getFechaCompra());
				p.setPrecioCompAnt(p.getPrecio());
			}return p.getFechaCompraAnt();
		}
		return new Date(); 
	}
	
	@Transactional
	public Double inflacionMensual(Precios producto) {
		
		SimpleDateFormat d1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat d2 = new SimpleDateFormat("yyyy-MM-dd");

		String fechaUlti = d1.format(producto.getFechaCompraAnt());
		String fechaActual = d2.format(producto.getFechaCompra());

		LocalDate fuc = LocalDate.parse(fechaUlti, DateTimeFormatter.ISO_LOCAL_DATE);
		LocalDate fac = LocalDate.parse(fechaActual, DateTimeFormatter.ISO_LOCAL_DATE);

		long dif = ChronoUnit.DAYS.between(fuc, fac);
		
		System.out.println("Diferencia de Días: "+ dif);

		Double variacion = ((producto.getPrecioCompAnt() / producto.getPrecio()) - 1) * -100;
		
		System.out.println("Precio Anterior: "+ producto.getPrecioCompAnt());
		System.out.println("Precio Actual: "+ producto.getPrecio());
		
		System.out.println("Variacion: "+ variacion);
		
		if (dif!=0 && dif>0) {
			Double inflacion=((variacion/dif)*30);
			return inflacion;
			}else if (dif<=0){	
				return variacion-100;
			}else {
				return variacion;
			}
	}
	
	public List<Precios> preciosRegistrados () {
		return precioRepo.ordenDatePrecios();
	}
	
	@Transactional
	public void eliminarPrecio(String id){
		
		Precios precioEliminado=precioRepo.buscarPrecioxId(id);
		
		List<Precios>precioEstado=precioRepo.buscarPrecioxNombre(precioEliminado.getProducto());
		
		if(precioEstado.size()>1){
		Precios precioCambiado = precioEstado.get(1);
		precioCambiado.setEstado(true);
		}
		
		precioRepo.deleteById(id);
	}
	
	@Transactional
	public Boolean ultimaCarga (String produco) {
		
		List <Precios> listaPrecios = precioRepo.buscarPrecioxNombre(produco);
		
		Boolean estadoC=true;
		
		for (Precios precioL : listaPrecios) {
            
          if (precioL.getFechaCompra().before(Date.from(Instant.now()))) {
          precioL.setEstado(false);
          }
         }
		return estadoC;
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
