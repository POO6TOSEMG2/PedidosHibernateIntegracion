package com.hibernateintegracion.dao;

import java.util.List;

import com.hibernateintegracion.domain.Almacenes;

public interface AlmacenesDAO {
	public void insertar(Almacenes objAlmacenes);
	public void cambiar(Almacenes objAlmacenes);
	public void eliminar(Almacenes objAlmacenes);
	public List<Almacenes> obtenerAlmacenes();
}
