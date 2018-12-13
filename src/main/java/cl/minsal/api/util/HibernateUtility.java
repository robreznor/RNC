package cl.minsal.api.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtility {

	public static SessionFactory factory;
	//to disallow creating objects by other classes.

    private HibernateUtility() {
    }

    public static synchronized SessionFactory getSessionFactory() {
    	try{
    		if (factory == null) {
            	Configuration configuration = new Configuration();
            	System.out.println("1");
            	configuration.configure("hibernate.cfg.xml");
            	System.out.println("2");
                ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                configuration.getProperties()).buildServiceRegistry();
                System.out.println("3");
                factory = configuration.buildSessionFactory(serviceRegistry);
                System.out.println("4");
            }
            return factory;
    		
    	}catch(HibernateException e){
    		e.printStackTrace();
    	}
        return null;
    }
}
