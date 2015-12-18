package com.hibernateintegracion.dao;

import java.util.List;

import com.hibernateintegracion.domain.Clientes;

public interface ClientesDAO {
	void insertar(Clientes objCliente);
	void cambiar(Clientes objCliente);
	void eliminar(Clientes objCliente);
	List<Clientes> consultarClientes();
}
