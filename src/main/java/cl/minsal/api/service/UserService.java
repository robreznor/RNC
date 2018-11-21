package cl.minsal.api.service;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import cl.minsal.api.model.Paciente;
import cl.minsal.api.model.Usuario;
import cl.minsal.api.util.HibernateUtility;

public class UserService {
	
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
	
	public static List<Usuario> getUsers(){
		
		try{
			SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	        Session session = sessionFactory.openSession();
	        Query q = session.createQuery("from Usuario");
	        List<Usuario> usuarios =  q.list();	
	        session.close();
	        
	        return usuarios;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}	
		
	}

	public static Usuario getUser(Integer id){
		
		try{			
	        return getUserQuery(id);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}	
	}
	
	public static boolean DeleteUser(Integer id){
		try{
			SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	        Session session = sessionFactory.openSession();
	        Transaction tx1 = session.beginTransaction();
			Usuario user = getUserQuery(id);
			session.delete(user);
	        tx1.commit();
	        return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}		
	}
//	
//	public static Usuario getUpdateUser(){
//		
//	}
//
//	public static Usuario getUpdateUser(){
//	
//	}
//
//	public static Usuario getUpdateUser(){
//		
//	}
	
	private static Usuario getUserQuery(Integer id){
		
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("from Usuario u where u.id_usuario='"+id+"'");
        Usuario usuario =  (Usuario) q.uniqueResult();	 
        session.close();
        return usuario;
	}
	
}
