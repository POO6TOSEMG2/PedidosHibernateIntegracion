package com.hibernateintegracion.dao;

import java.util.List;

import com.hibernateintegracion.domain.LineaPedidos;
import com.hibernateintegracion.domain.Productos;

public interface StockDAO {
	public boolean solicitarSalidaProducto(Productos solicitudSalidaProducto);
	public boolean solicitarDevolucionProducto(List<LineaPedidos> lineasActivas);
}
