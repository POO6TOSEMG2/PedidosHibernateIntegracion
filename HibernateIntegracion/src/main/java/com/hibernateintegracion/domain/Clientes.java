package com.hibernateintegracion.domain;
// Generated 14-dic-2015 18:48:25 by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Clientes generated by hbm2java
 */

@Entity
@Table(name="clientes")
public class Clientes implements java.io.Serializable {

	@Id
	private int codigo;
	@Column(name="cedula")
	private String cedula;
	@Column(name="nombres")
	private String nombres;
	
	public Clientes() {
	}

	public Clientes(int codigo) {
		this.codigo = codigo;
	}

	public Clientes(int codigo, String cedula, String nombres) {
		this.codigo = codigo;
		this.cedula = cedula;
		this.nombres = nombres;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

}
