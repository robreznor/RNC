package cl.minsal.api.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cl.minsal.api.model.Paciente;


public class Utils {

	public static int calculateAge(Date birthDate, Date currentDate) {                                                                                                                    
	    int d1 = dateToInteger(birthDate, "yyyyMMdd");                           
	    int d2 = dateToInteger(currentDate, "yyyyMMdd");                        
	    int age = (d2 - d1) / 10000;                                                       
	    return age;                                                                        
	}
	
	public static Date olderDate(Date date1, Date date2){                         
		 int d1 = dateToInteger(date1, "yyyyMMdd");                           
		 int d2 = dateToInteger(date2, "yyyyMMdd");
		 if(d1<d2){
			 return date1;
		 }else{
			 return date2;
		 }
	}
	
	public static Date newerDate(Date date1, Date date2){
		int d1 = dateToInteger(date1, "yyyyMMdd");                           
		 int d2 = dateToInteger(date2, "yyyyMMdd");
		 if(d1>d2){
			 return date1;
		 }else{
			 return date2;
		 }
	}
	
	public static String dateToString(Date date, String format){
		DateFormat dateFormat = new SimpleDateFormat(format);
		String stringDate = dateFormat.format(date);
		return stringDate;
	}
	
	public static List<Paciente> getPacientes(){
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("From Paciente");
		List<Paciente> pacientes = query.list();
		return pacientes;
	}
	
	public static Integer dateToInteger(Date date, String format){
		DateFormat formatter = new SimpleDateFormat(format);                           
	    int integerDate = Integer.parseInt(formatter.format(date)); 
	    return integerDate;
	}
	
	public static Date stringToDate(String stringDate, String format){
		try{
			DateFormat formatter = new SimpleDateFormat(format);
			Date date = formatter.parse(stringDate);
			return date;
		}catch(ParseException e){
			e.printStackTrace();
			return null;
		}		
	}
	
	
}
