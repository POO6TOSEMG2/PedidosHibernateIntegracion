package com.hibernateintegracion.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StaleStateException;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.hibernateintegracion.dao.AlmacenesDAO;
import com.hibernateintegracion.domain.Almacenes;
import com.hibernateintegracion.persistence.NewHibernateUtil;

@Repository
public class PostgresAlmacenesDAO implements AlmacenesDAO {
	
	@Override
	public void insertar(Almacenes objAlmacenes) {
		Session session = new NewHibernateUtil().getSession();
		Transaction tr = session.beginTransaction();
		session.save(objAlmacenes);
		tr.commit();

	}

	@Override
	public void cambiar(Almacenes objAlmacenes) {
		Session session = new NewHibernateUtil().getSession();
		Transaction tr = session.beginTransaction();
		Almacenes loadPersistence = (Almacenes) session.load(Almacenes.class, objAlmacenes.getCodigo());
		
		try{
			if(session.contains(loadPersistence)){
				session.update(objAlmacenes);	
			}
			tr.commit();
			
		} catch (StaleStateException e){
			tr.rollback();
		}
		
	}

	@Override
	public void eliminar(Almacenes objAlmacenes) {
		Session session = new NewHibernateUtil().getSession();
		Transaction tr = session.beginTransaction();
		Almacenes loadPersistence = (Almacenes) session.load(Almacenes.class, objAlmacenes.getCodigo());
		
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
	public List<Almacenes> obtenerAlmacenes() {
		Session session = new NewHibernateUtil().getSession();
		Query query = session.createQuery("from Almacenes");				
		return (List<Almacenes>)query.list();
	}

}
