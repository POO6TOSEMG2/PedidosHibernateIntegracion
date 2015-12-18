package com.hibernateintegracion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hibernateintegracion.dao.StockDAO;
import com.hibernateintegracion.domain.LineaPedidos;
import com.hibernateintegracion.domain.Productos;

public class StockController {
	
	@Autowired
	StockDAO stockDAO;
	
	public boolean solicitarSalidaProducto(Productos pro)
	{
		return stockDAO.solicitarSalidaProducto(pro);
	}
	
	public boolean solicitarDevolucionProducto(List<LineaPedidos> lineasActivas)
	{
		return stockDAO.solicitarDevolucionProducto(lineasActivas);
	}
}
