package com.hibernateintegracion.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StaleStateException;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.hibernateintegracion.dao.PedidosDAO;
import com.hibernateintegracion.domain.Almacenes;
import com.hibernateintegracion.domain.Clientes;
import com.hibernateintegracion.domain.Pedidos;
import com.hibernateintegracion.persistence.NewHibernateUtil;

@Repository
public class PostgresPedidosDAO implements PedidosDAO {

	@Override
	public void insertar(Pedidos pedido) {
		Session session = new NewHibernateUtil().getSession();
		Transaction tr = session.beginTransaction();
		session.save(pedido);
		tr.commit();

	}

	@Override
	public void actualizar(Pedidos pedido) {
		Session session = new NewHibernateUtil().getSession();
		Transaction tr = session.beginTransaction();
		Pedidos loadPersistence = (Pedidos) session.load(Pedidos.class, pedido.getNumero());
		
		try{
			if(session.contains(loadPersistence)){
				session.update(pedido);	
			}
			tr.commit();
			
		} catch (StaleStateException e){
			tr.rollback();
		}

	}

	@Override
	public void cambiarEstado(Pedidos pedido) {
		Session session = new NewHibernateUtil().getSession();
		Transaction tr = session.beginTransaction();
		Pedidos loadPersistence = (Pedidos) session.load(Pedidos.class, pedido.getNumero());
		
		try{
			if(session.contains(loadPersistence)){
				session.update(pedido);	
			}
			tr.commit();
			
		} catch (StaleStateException e){
			tr.rollback();
		}
		
	}

	@Override
	public List<Pedidos> pedidosReservados() {
		
		List<Pedidos> pedidosReservados = new ArrayList<Pedidos>();
		
		Session session = new NewHibernateUtil().getSession();
		Query query = session.createQuery("FROM Pedidos c, Almacenes a, Clientes b "
				+ "WHERE a.codigo = c.codigo_almacen AND b.codigo = c.codigo_cliente "
				+ "AND c.estado LIKE 'R%'");
		
		List lista = query.list();
		
		Iterator itr = lista.iterator();
		
		while(itr.hasNext()){
		   Object[] obj = (Object[]) itr.next();
		   
		   Pedidos pedidos = (Pedidos) obj[0];		   
		   Almacenes almacenes = (Almacenes) obj[1];
		   Clientes clientes = (Clientes) obj[2];
		   
		   pedidos.setAlmacenes(almacenes);
		   pedidos.setClientes(clientes);
		   
		   pedidosReservados.add(pedidos);
		}
		
		return pedidosReservados;
	}

}
