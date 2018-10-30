package cl.minsal.api.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtility {

	public static SessionFactory factory;
	//to disallow creating objects by other classes.

    private HibernateUtility() {
    }

    public static synchronized SessionFactory getSessionFactory() {

        if (factory == null) {
        	factory = new AnnotationConfiguration().configure().buildSessionFactory();
        }
        return factory;
    }
}
