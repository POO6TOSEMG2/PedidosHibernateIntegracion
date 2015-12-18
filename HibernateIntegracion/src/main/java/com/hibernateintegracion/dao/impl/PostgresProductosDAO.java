package com.hibernateintegracion.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StaleStateException;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.hibernateintegracion.dao.ProductosDAO;
import com.hibernateintegracion.domain.Clientes;
import com.hibernateintegracion.domain.Productos;
import com.hibernateintegracion.persistence.NewHibernateUtil;

@Repository
public class PostgresProductosDAO implements ProductosDAO {

	@Override
	public void insertar(Productos objProducto) {
		Session session = new NewHibernateUtil().getSession();
		Transaction tr = session.beginTransaction();
		session.save(objProducto);
		tr.commit();
		
	}

	@Override
	public void cambiar(Productos objProducto) {
		Session session = new NewHibernateUtil().getSession();
		Transaction tr = session.beginTransaction();
		Productos loadPersistence = (Productos) session.load(Productos.class, objProducto.getCodigo());
		
		try{
			if(session.contains(loadPersistence)){
				session.update(objProducto);	
			}
			tr.commit();
			
		} catch (StaleStateException e){
			tr.rollback();
		}
		
	}

	@Override
	public void eliminar(Productos objProducto) {
		Session session = new NewHibernateUtil().getSession();
		Transaction tr = session.beginTransaction();
		Productos loadPersistence = (Productos) session.load(Productos.class, objProducto.getCodigo());
		
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
	public List<Productos> obtenerProductos() {
		Session session = new NewHibernateUtil().getSession();
		Query query = session.createQuery("from Productos");				
		return (List<Productos>)query.list();
	}

}
