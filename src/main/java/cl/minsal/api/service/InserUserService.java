package cl.minsal.api.service;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import cl.minsal.api.model.Paciente;
import cl.minsal.api.model.Usuario;
import cl.minsal.api.util.HibernateUtility;

public class InserUserService {
	
	public static boolean userExist(String username){

		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		boolean userExist= true;
		try{
			Session session = sessionFactory.openSession(); 
	        Query q = session.createQuery("from Usuario u where u.usuario= '" + username + "'");
	        Paciente paciente = (Paciente) q.uniqueResult();
	        if(paciente==null){
	        	userExist= false;
	        }
		}catch(Exception e){
			e.printStackTrace();
		}
        return userExist;
	}
	
	public static boolean inserUser(Usuario user){

		try{
			SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
			Session session = sessionFactory.openSession(); 
			Transaction tx1 = session.beginTransaction();
			session.save(user);
	        tx1.commit();
	        session.close();
	        return true;
	        
		}catch(Exception e){
			e.printStackTrace();
		}
        return false;
	}
	
}
