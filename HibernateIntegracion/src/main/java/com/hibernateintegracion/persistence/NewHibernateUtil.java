package com.hibernateintegracion.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class NewHibernateUtil {
	private static SessionFactory factory;
	
	static{
		Configuration configuration = new AnnotationConfiguration();
		configuration.configure("/com/hibernateintegracion/persistence/hibernate.cfg.xml");
		factory = configuration.buildSessionFactory();
	}
	
	public static Session getSession(){
		return factory.openSession();
	}
}
