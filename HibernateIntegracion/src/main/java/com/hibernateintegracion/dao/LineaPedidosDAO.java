package com.hibernateintegracion.dao;

import java.util.List;

import com.hibernateintegracion.domain.LineaPedidos;

public interface LineaPedidosDAO {
	public void insertar(LineaPedidos linea);
	public void eliminar(LineaPedidos linea);
	public List<LineaPedidos> obtenerLineasReservadasPedidos(LineaPedidos linea);
}
