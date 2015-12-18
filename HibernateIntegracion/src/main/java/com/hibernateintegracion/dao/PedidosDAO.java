package com.hibernateintegracion.dao;

import java.util.List;

import com.hibernateintegracion.domain.Pedidos;

public interface PedidosDAO {
	public void insertar(Pedidos pedido);
	public void actualizar(Pedidos pedido);
	public void cambiarEstado(Pedidos pedido);
	List<Pedidos> pedidosReservados();
}
