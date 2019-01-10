package cl.minsal.api.service;

import java.util.List;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.fasterxml.jackson.databind.node.ObjectNode;

import cl.minsal.api.model.Establecimiento;
import cl.minsal.api.model.Paciente;
import cl.minsal.api.model.Usuario;
import cl.minsal.api.object.UserLoginResponse;
import cl.minsal.api.transfer.JwtUserDto;
import cl.minsal.api.util.HibernateUtility;
import cl.minsal.api.util.JwtUtil;

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
	
	public static Usuario correctPassword(String username, String password, ObjectNode message){
		try{
			SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
			Session session =  sessionFactory.openSession();
			Query query = session.createQuery("from Usuario u where u.usuario='"+ username +"'");
			Usuario user = (Usuario) query.uniqueResult();
			if(user==null){
				message.put("message", "La combinación usuario y contraseña no es correcta");
			}
			else if(password.equals(user.getPassword())){
				return user;
			}
			

		}catch(Exception e){
			e.printStackTrace();
			message.put("message", "El servidor no está disponible");
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
	
	public static boolean deleteUser(Integer id){
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
	
	public static UserLoginResponse fillUserResponse (Usuario user){
		JwtUtil jwtutil = new JwtUtil();
		JwtUserDto userDto = new JwtUserDto();
		Long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
		userDto.setId(id);
		userDto.setRole(user.getRole());
		userDto.setUsername(user.getUsuario());
		String token = jwtutil.generateToken(userDto);
		UserLoginResponse userRes = new UserLoginResponse();
		userRes.setUsername(user.getUsuario());
		userRes.setId(user.getId_usuario());
		userRes.setToken(token);
		userRes.setRole(user.getRole());
		userRes.setCodigo_establecimiento(user.getEstablecimiento().getCodigo_establecimiento());
		
		return userRes;
		
	}
	
	private static Usuario getUserQuery(Integer id){
		
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("from Usuario u where u.id_usuario='" + id + "'");
        Usuario usuario =  (Usuario) q.uniqueResult();	 
        session.close();
        return usuario;
	}
	
	
	public static boolean instExist(Integer codigo_institucion){
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("select count (*) from Establecimiento e where e.codigo_establecimiento='" + codigo_institucion + "'");
        boolean instExist =  (Long) q.uniqueResult()>0; 
        session.close();
        return instExist;
	}
	
	public static boolean userValidation(String username, Integer codigo_institucion, ObjectNode message){
		if(userExist(username)){
			message.put("message", "El nombre de usuario ingresado ya existe");
			return false;
		}else if(!instExist(codigo_institucion)){
			message.put("message", "El código de institución ingresado no es válido");
			return false;
		}
		return true;
	}	
	
	public static List<Establecimiento> getEstablecimientos(){
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session =  sessionFactory.openSession();
		Query query = session.createQuery("from Establecimiento");
		List<Establecimiento> establecimientos = query.list();
		return establecimientos;
	}
      
	
}
