package cl.minsal.api.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import cl.minsal.api.model.Paciente;


public class Utils {

	public static int calculateAge(Date birthDate, Date currentDate) {                                                                                                                    
	    int d1 = dateToInteger(birthDate);                           
	    int d2 = dateToInteger(currentDate);                        
	    int age = (d2 - d1) / 10000;                                                       
	    return age;                                                                        
	}
	
	public static Date olderDate(Date date1, Date date2){                         
		 int d1 = dateToInteger(date1);                           
		 int d2 = dateToInteger(date2);
		 if(d1<d2){
			 return date1;
		 }else{
			 return date2;
		 }
	}
	
	public static Date newerDate(Date date1, Date date2){
		int d1 = dateToInteger(date1);                           
		 int d2 = dateToInteger(date2);
		 if(d1>d2){
			 return date1;
		 }else{
			 return date2;
		 }
	}
	
	public static String dateToString(Date date){
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
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
	
	private static Integer dateToInteger(Date date){
		DateFormat formatter = new SimpleDateFormat("yyyyMMdd");                           
	    int integerDate = Integer.parseInt(formatter.format(date)); 
	    return integerDate;
	}
	
	public static Date stringToDate(String stringDate){
		try{
			System.out.println("string date"+stringDate);
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date = formatter.parse(stringDate);
			System.out.println("date format "+date);
			return date;
		}catch(ParseException e){
			e.printStackTrace();
			return null;
		}		
	}
	
	@Test
	public void test() {
		try{
			Date current = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
	        String dateInString = "21-Nov-2020";
	        String stringDate = "1800-05-12";
	        Date date = formatter.parse(dateInString);
	        System.out.println("String to date: "+Utils.stringToDate(stringDate));
	        System.out.println("Date to string: "+Utils.dateToString(current));
	        System.out.println("Fecha actual "+ current);
	        System.out.println("Fecha ingresada "+ date); 
			System.out.println("Fecha más actual "+newerDate(date, current));
			System.out.println("Fecha más antigua "+olderDate(date, current));
		}catch(ParseException e){
			e.printStackTrace();
		}	
	}
}
