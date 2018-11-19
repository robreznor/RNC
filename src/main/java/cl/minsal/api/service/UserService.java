package cl.minsal.api.service;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cl.minsal.api.model.Usuario;
import cl.minsal.api.util.HibernateUtility;

public class UserService {
	
	public static Usuario correctPassword(String username, String password){
		try{
			SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
			Session session =  sessionFactory.openSession();
			Query query = session.createQuery("from Usuario u where u.usuario='"+ username +"'");
			Usuario user = (Usuario) query.uniqueResult();
			if(password.equals(user.getPassword())){
				return user;
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
