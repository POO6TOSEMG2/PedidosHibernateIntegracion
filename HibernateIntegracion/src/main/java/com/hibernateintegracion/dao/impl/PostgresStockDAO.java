package com.hibernateintegracion.dao.impl;

import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StaleStateException;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.hibernateintegracion.dao.StockDAO;
import com.hibernateintegracion.domain.Almacenes;
import com.hibernateintegracion.domain.Clientes;
import com.hibernateintegracion.domain.LineaPedidos;
import com.hibernateintegracion.domain.Pedidos;
import com.hibernateintegracion.domain.Productos;
import com.hibernateintegracion.persistence.NewHibernateUtil;

@Repository
public class PostgresStockDAO implements StockDAO {

	/** DEBE MANDARSE EL OBJETO COMPLETO PARA UNA ACTUALIZACION COMPLETA **/
	private void actualizarStock(Productos solicitudProductos, int nStock){
		
		// Ojo debe mandarse el objeto completo sino salen campos en blanco...	
		Session session = new NewHibernateUtil().getSession();
		Transaction tr = session.beginTransaction();
		
		Productos loadPersistence = (Productos) session.load(Productos.class, solicitudProductos.getCodigo());
		solicitudProductos.setStock(nStock);
		
		try{
			if(session.contains(loadPersistence)){
				session.update(solicitudProductos);	
			}
			
			tr.commit();
			
		} catch (StaleStateException e){
			tr.rollback();
		}
				
	}
	
	@Override
	public boolean solicitarSalidaProducto(Productos solicitudSalidaProducto) {
		// Obtener stock de producto
		
		Session session = new NewHibernateUtil().getSession();
		
		Query query = session.createQuery("FROM Productos c WHERE c.codigo =:idPro")
				.setParameter("idPro", solicitudSalidaProducto.getCodigo());
		
		List list = query.list();
		
		Productos pr = (Productos) list.get(0);
		int numeroExistenciaProducto = pr.getCodigo();
		
		if (numeroExistenciaProducto > solicitudSalidaProducto.getStock()){
			int nuevoStock = numeroExistenciaProducto - solicitudSalidaProducto.getStock();				
			actualizarStock(solicitudSalidaProducto, nuevoStock);	
			return true;
		} else{
			JOptionPane.showMessageDialog(null, "No hay existencia suficiente...");
		}
		
		return false;
	}

	@Override
	public boolean solicitarDevolucionProducto(List<LineaPedidos> lineasActivas) {
		
		Session session = new NewHibernateUtil().getSession();
		Productos restaurarStockProducto = new Productos();
		
		for (LineaPedidos lped: lineasActivas){
			Query query = session.createQuery("FROM Productos c WHERE c.codigo =:idPro")
					.setParameter("idPro", lped.getProductos().getCodigo());
			
			List list = query.list();				
			
			Iterator itr = list.iterator();
			
			while(itr.hasNext()){
				Object[] obj = (Object[]) itr.next();
				System.out.println(obj[0]);
			}
			
		}
		
		/*
		for(Linea_Pedido lped: lineasActivas){
								
				while (rs.next()) {
					restaurarStockProducto = new Producto();
					int restablecerStock = lped.getCantidad() + rs.getInt(1);		
					restaurarStockProducto.setCodigoProducto(lped.getProducto().getCodigoProducto());
					restaurarStockProducto.setStockProducto(restablecerStock);
													
					actualizarStock(restaurarStockProducto);					
				}
			}
		
		*/
		
		return false;
	}

}
