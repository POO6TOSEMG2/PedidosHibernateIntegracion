package com.hibernateintegracion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hibernateintegracion.dao.PedidosDAO;
import com.hibernateintegracion.domain.Pedidos;

public class PedidosController {

	@Autowired
	PedidosDAO pedidosDAO;
	
	public void insertar(Pedidos pedido) {
		pedidosDAO.insertar(pedido);
	}

	public void actualizar(Pedidos pedido) {
		pedidosDAO.actualizar(pedido);
	}

	public void cambiarEstado(Pedidos pedido) {
		pedidosDAO.cambiarEstado(pedido);
	}

	public List<Pedidos> pedidosReservados() {
		return pedidosDAO.pedidosReservados();
	}
	
}
