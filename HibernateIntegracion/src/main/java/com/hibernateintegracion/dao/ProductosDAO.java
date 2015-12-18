package com.hibernateintegracion.dao;

import java.util.List;

import com.hibernateintegracion.domain.Productos;

public interface ProductosDAO {
	void insertar (Productos objProducto);
	void cambiar (Productos objProducto);
	void eliminar (Productos objProducto);
	List<Productos> obtenerProductos();
	
}
