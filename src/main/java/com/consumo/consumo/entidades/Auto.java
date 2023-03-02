package com.consumo.consumo.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Auto {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	private String marca;
	private Integer anio;
	private String modelo;
	private Integer kmActuales;
	private Double consumo;
	private String mail;
        
	public Auto() {
		super();
	}
	public Auto(String id, Date fecha, String marca, Integer anio, String modelo, Integer kmActuales,
			Double consumo, String mail) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.marca = marca;
		this.anio = anio;
		this.modelo = modelo;
		this.kmActuales = kmActuales;
		this.consumo = consumo;
		this.mail = mail;
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Integer getKmActuales() {
		return kmActuales;
	}
	public void setKmActuales(Integer kmActuales) {
		this.kmActuales = kmActuales;
	}
	public Double getConsumo() {
		return consumo;
	}
	public void setConsumo(Double consumo) {
		this.consumo = consumo;
	}
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return "Aunto [id=" + id + ", fecha=" + fecha + ", marca=" + marca + ", anio=" + anio + ", modelo=" + modelo
				+ ", kmActuales=" + kmActuales + ", kmRecorridos=" + consumo + "]";
	}
	

}
