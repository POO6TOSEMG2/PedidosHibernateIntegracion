package com.hibernateintegracion.domain;
// Generated 14-dic-2015 18:48:25 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * LineaPedidos generated by hbm2java
 */

@Entity
@Table(name="linea_pedidos")
public class LineaPedidos implements java.io.Serializable {

	@Id
	private int numero_linea;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="numero_pedido")
	private Pedidos numero_pedido;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="codigo_producto")
	private Productos codigo_producto;
	@Column(name="cantidad")
	private Integer cantidad;
	@Column(name="subtotal")
	private Double subtotal;

	public LineaPedidos() {
	}

	public LineaPedidos(int numeroLinea) {
		this.numero_linea = numeroLinea;
	}

	public LineaPedidos(Pedidos pedidos, Productos productos, Integer cantidad, Double subtotal) {
		this.numero_pedido = pedidos;
		this.codigo_producto = productos;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
	}
	
	public LineaPedidos(int numeroLinea, Pedidos pedidos, Productos productos, Integer cantidad, Double subtotal) {
		this.numero_linea = numeroLinea;
		this.numero_pedido = pedidos;
		this.codigo_producto = productos;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
	}

	public int getNumeroLinea() {
		return this.numero_linea;
	}

	public void setNumeroLinea(int numeroLinea) {
		this.numero_linea = numeroLinea;
	}

	public Pedidos getPedidos() {
		return this.numero_pedido;
	}

	public void setPedidos(Pedidos pedidos) {
		this.numero_pedido = pedidos;
	}

	public Productos getProductos() {
		return this.codigo_producto;
	}

	public void setProductos(Productos productos) {
		this.codigo_producto = productos;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

}