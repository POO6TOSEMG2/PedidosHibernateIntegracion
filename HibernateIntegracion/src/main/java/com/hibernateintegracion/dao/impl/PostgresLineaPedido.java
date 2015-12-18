package com.hibernateintegracion.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StaleStateException;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.hibernateintegracion.dao.LineaPedidosDAO;
import com.hibernateintegracion.domain.Almacenes;
import com.hibernateintegracion.domain.Clientes;
import com.hibernateintegracion.domain.LineaPedidos;
import com.hibernateintegracion.domain.Pedidos;
import com.hibernateintegracion.domain.Productos;
import com.hibernateintegracion.persistence.NewHibernateUtil;

@Repository
public class PostgresLineaPedido implements LineaPedidosDAO {

	@Override
	public void insertar(LineaPedidos linea) {
		Session session = new NewHibernateUtil().getSession();
		Transaction tr = session.beginTransaction();
		session.save(linea);
		tr.commit();
	}

	@Override
	public void eliminar(LineaPedidos linea) {
		Session session = new NewHibernateUtil().getSession();
		Transaction tr = session.beginTransaction();
		
		LineaPedidos loadPersistence = (LineaPedidos) session.load(LineaPedidos.class, linea.getNumeroLinea());
		
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
	public List<LineaPedidos> obtenerLineasReservadasPedidos(LineaPedidos linea) {
				
		List<LineaPedidos> LineasReservadasPedidos = new ArrayList<LineaPedidos>();
		Session session = new NewHibernateUtil().getSession();

		Query query = session.createQuery("from LineaPedidos c, Productos p, Pedidos d "
				+ "WHERE p.codigo = c.codigo_producto "
				+ "AND d.numero = c.numero_pedido AND c.numero_pedido =:pe")
				.setParameter("pe", linea.getPedidos());
				
		List lista = query.list();
		Iterator itr = lista.iterator();
		while(itr.hasNext()){
			Object[] obj = (Object[]) itr.next();
							
			LineaPedidos lineaP = (LineaPedidos) obj[0];
			Productos productos = (Productos) obj[1];
			Pedidos pedidos = (Pedidos) obj[2];
			
			lineaP.setProductos(productos);
			lineaP.setPedidos(pedidos);
			
			LineasReservadasPedidos.add(lineaP); 
		}
		
		return LineasReservadasPedidos;
	}

}
