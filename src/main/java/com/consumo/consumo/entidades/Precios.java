package com.consumo.consumo.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Precios {
	
	@Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid",strategy="uuid2")
	private String id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCompra;
	private Date fechaCompraAnt;
	private String nombreProveedro;
	private String producto;
	private String descripcion;
	private Double precio;
	private Double precioCompAnt;
	private Double inflacion;
	private Boolean estado;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	public Date getFechaCompraAnt() {
		return fechaCompraAnt;
	}
	public void setFechaCompraAnt(Date fechaCompraAnt) {
		this.fechaCompraAnt = fechaCompraAnt;
	}
	public String getNombreProveedro() {
		return nombreProveedro;
	}
	public void setNombreProveedro(String nombreProveedro) {
		this.nombreProveedro = nombreProveedro;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String produco) {
		this.producto = produco;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Double getPrecioCompAnt() {
		return precioCompAnt;
	}
	public void setPrecioCompAnt(Double precioCompAnt) {
		this.precioCompAnt = precioCompAnt;
	}
	public Double getInflacion() {
		return inflacion;
	}
	public void setInflacion(Double inflacion) {
		this.inflacion = inflacion;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return "Precios [id=" + id + ", fechaCompra=" + fechaCompra + ", fechaCompraAnt=" + fechaCompraAnt
				+ ", nombreProveedro=" + nombreProveedro + ", producto=" + producto + ", descripcion=" + descripcion
				+ ", precio=" + precio + ", precioCompAnt=" + precioCompAnt + ", inflacion=" + inflacion + ", estado="
				+ estado + "]";
	}
	
	
	
}
