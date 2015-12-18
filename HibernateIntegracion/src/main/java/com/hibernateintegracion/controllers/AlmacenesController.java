package com.hibernateintegracion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hibernateintegracion.dao.AlmacenesDAO;
import com.hibernateintegracion.domain.Almacenes;

public class AlmacenesController {

	@Autowired
	AlmacenesDAO almacenesDAO;
	
	public void insertar(Almacenes objAlmacenes) {
		almacenesDAO.insertar(objAlmacenes);
	}

	public void cambiar(Almacenes objAlmacenes) {
		almacenesDAO.cambiar(objAlmacenes);
	}

	public void eliminar(Almacenes objAlmacenes) {
		almacenesDAO.eliminar(objAlmacenes);
	}

	public List<Almacenes> obtenerAlmacenes() {
		return almacenesDAO.obtenerAlmacenes();
	}
		
}
