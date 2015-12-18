package com.hibernateintegracion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hibernateintegracion.dao.LineaPedidosDAO;
import com.hibernateintegracion.domain.LineaPedidos;

public class LineaPedidoController {
	
	@Autowired
	LineaPedidosDAO lineaPedidosDAO;
	
	public void insertar(LineaPedidos linea){
		lineaPedidosDAO.insertar(linea);
	}
	
	public void eliminar(LineaPedidos linea){
		lineaPedidosDAO.eliminar(linea);
	}
	
	public List<LineaPedidos> obtenerLineasReservadasPedidos(LineaPedidos linea){
		return lineaPedidosDAO.obtenerLineasReservadasPedidos(linea);
	}
	
}
