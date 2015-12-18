package com.hibernateintegracion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hibernateintegracion.dao.ClientesDAO;
import com.hibernateintegracion.domain.Clientes;

public class ClienteController {
	
	@Autowired
	ClientesDAO clientesDAO;
	
	public void insertar(Clientes objCliente){
		clientesDAO.insertar(objCliente);
	}
	
	public void cambiar(Clientes objCliente){
		clientesDAO.cambiar(objCliente);
	}
	
	public void eliminar(Clientes objCliente){
		clientesDAO.eliminar(objCliente);
	}
	
	public List<Clientes> consultarClientes(){
		return clientesDAO.consultarClientes();
	}
}
