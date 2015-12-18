package com.hibernateintegracion.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StaleStateException;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.hibernateintegracion.dao.ClientesDAO;
import com.hibernateintegracion.domain.Almacenes;
import com.hibernateintegracion.domain.Clientes;
import com.hibernateintegracion.persistence.NewHibernateUtil;

@Repository
public class PostgresClientesDAO implements ClientesDAO {

	@Override
	public void insertar(Clientes objCliente) {
		Session session = new NewHibernateUtil().getSession();
		Transaction tr = session.beginTransaction();
		session.save(objCliente);
		tr.commit();

	}

	@Override
	public void cambiar(Clientes objCliente) {
		Session session = new NewHibernateUtil().getSession();
		Transaction tr = session.beginTransaction();
		Clientes loadPersistence = (Clientes) session.load(Clientes.class, objCliente.getCodigo());
		
		try{
			if(session.contains(loadPersistence)){
				session.update(objCliente);	
			}
			tr.commit();
			
		} catch (StaleStateException e){
			tr.rollback();
		}
				
	}

	@Override
	public void eliminar(Clientes objCliente) {
		Session session = new NewHibernateUtil().getSession();
		Transaction tr = session.beginTransaction();
		Clientes loadPersistence = (Clientes) session.load(Clientes.class, objCliente.getCodigo());
		
		try{
			if(session.contains(loadPersistence)){
				session.delete(loadPersistence);	
			}
			tr.commit();
			
		} catch (StaleStateException e){
			tr.rollback();
		}
	}

	@Override
	public List<Clientes> consultarClientes() {
		Session session = new NewHibernateUtil().getSession();
		Query query = session.createQuery("from Clientes");				
		return (List<Clientes>)query.list();
	}

}
