package edu.utn.seminario.motosnorte.helper;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryHelper {
	
	 private static SessionFactory instance = null;

//	   private HibernateUtil(){
//	        Configuration configuration = new Configuration();
//	        configuration.configure();
//	        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//	        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//	   }
	   public static SessionFactory getInstance(){
	        if(instance == null){
	            instance  = new Configuration().configure()
						.buildSessionFactory();
	        }
	        return instance;
	   }

//	   public static SessionFactory getSessionFactory() {
//	        return in;
//	   }

}
