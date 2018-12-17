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
            	configuration.configure("hibernate.cfg.xml");
                ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                configuration.getProperties()).buildServiceRegistry();
                factory = configuration.buildSessionFactory(serviceRegistry);
            }
            return factory;
    		
    	}catch(HibernateException e){
    		e.printStackTrace();
    	}
        return null;
    }
}
