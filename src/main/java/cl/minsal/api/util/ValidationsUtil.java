package cl.minsal.api.util;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import cl.minsal.api.model.Genero;
import cl.minsal.api.model.Usuario;
import cl.minsal.api.object.ValidationMessages;

public class ValidationsUtil {
	
	public static void pacienteValidation(String [] pacienteData, ValidationMessages messages){
		emptyFieldValidation(pacienteData, messages);
		formatValidation(pacienteData, messages);
	}
	
	private static void emptyFieldValidation(String [] pacienteData, ValidationMessages messages){
	
		String rut = " [rut: "+pacienteData[3]+"-"+pacienteData[4]+"]";
	
		if(pacienteData[3].equals("")){
			messages.addMessage("El rut es un campo requerido");
			messages.setValidation(false);
			rut = "";
		}
		if(pacienteData[4].equals("")){
			messages.addMessage("El digito verificador es un campo requerido" + rut);
			messages.setValidation(false);
		}
		if(pacienteData[0].equals("")){
			messages.addMessage("El nombre del paciente es un campo requerido" + rut);
			messages.setValidation(false);
		}
		if(pacienteData[1].equals("")){
			messages.addMessage("El primer apellido  del paciente es un campo requerido" + rut);
			messages.setValidation(false);
		}
		if(pacienteData[2].equals("")){
			messages.addMessage("El segundo apellido del paciente es un campo requerido" + rut);
			messages.setValidation(false);
		}
		if(pacienteData[21].equals("")){
			messages.addMessage("El tipo de comite es un campo requerido" + rut);
			messages.setValidation(false);
		}
		if(pacienteData[22].equals("")){
			messages.addMessage("La fecha de diagnostico es un campo requerido" + rut);
			messages.setValidation(false);
		}
		if(pacienteData[23].equals("")){
			messages.addMessage("La fecha de comité es un campo requerido" + rut);
			messages.setValidation(false);
		}
		if(pacienteData[25].equals("")){
			messages.addMessage("El diagnóstico CIE10 es un campo requerido" + rut);
			messages.setValidation(false);
		}
		if(pacienteData[26].equals("")){
			messages.addMessage("ECOG es un campo requerido" + rut);
			messages.setValidation(false);
		}
		if(pacienteData[28].equals("") || pacienteData[30].equals("") || pacienteData[31].equals("") || pacienteData[32].equals("") || pacienteData[33].equals("")){
			messages.addMessage("El campo TNM está incompleto" + rut);
			messages.setValidation(false);
		}
		if(pacienteData[34].equals("")){
			messages.addMessage("El campo estadio está incompleto" + rut);
			messages.setValidation(false);
		}
		String[] tratamiento = {"primer","segundo","tercero","cuarto"};
		int contador = 0;
		for(int i=36; i<55;i=i+6)
		if(!(pacienteData[i].equals("") || pacienteData[i+1].equals("") || pacienteData[i+2].equals("") || pacienteData[i+3].equals("") || pacienteData[i+4].equals(""))){
			String message = "Al" + tratamiento[contador++] + "tratamiento ingresado le falta el campo " + rut;
			if(pacienteData[i].equals(message)){
				messages.addMessage(message + "nombre médico tratante");
				messages.setValidation(false);
			}
			if(pacienteData[i+1].equals("")){
				messages.addMessage(message + "tipo de tratamiento");
				messages.setValidation(false);
			}
			if(pacienteData[i+2].equals("")){
				messages.addMessage(message + "intención de tratamiento");
				messages.setValidation(false);
			}
			if(pacienteData[i+3].equals("")){
				messages.addMessage(message + "fecha intención");
				messages.setValidation(false);
			}
			if(pacienteData[i+4].equals("")){
				messages.addMessage(message + "resolución comité");
				messages.setValidation(false);
			}
			
		} 
	}
	
	private static void formatValidation(String [] pacienteData, ValidationMessages messages){
		
		String rut = (pacienteData[3].equals(""))? "": " [rut: "+pacienteData[3]+"-"+pacienteData[4]+"]";
		if(!pacienteData[0].equals("")){
			if(!firstNameValidation(pacienteData[0])){
				messages.addMessage("El nombre "+pacienteData[0]+" no tiene un largo válido, el máximo permitido son 50 caracteres" + rut);
				messages.setValidation(false);
			}
		}
		if(!pacienteData[1].equals("")){
			if(!lastNameValidation(pacienteData[1])){
				messages.addMessage("El primer apellido "+pacienteData[1]+" no tiene un largo válido, el máximo permitido son 30 caracteres" + rut);
				messages.setValidation(false);
			}
		}
		if(!pacienteData[2].equals("")){
			if(!lastNameValidation(pacienteData[2])){
				messages.addMessage("El segundo apellido " + pacienteData[2] + " no tiene un largo válido, el máximo permitido son 30 caracteres" + rut);
				messages.setValidation(false);
			}
		}
		if(!pacienteData[3].equals("") && !pacienteData[4].equals("")){
			if(!rutValidation(pacienteData[3], pacienteData[4].charAt(0))){
				messages.addMessage("El rut " + pacienteData[3] + "-" + pacienteData[4] + " no es válido");
				messages.setValidation(false);
			}	
		}
		
		if(!pacienteData[5].equals("")){
			String format = "yyyy-MM-dd";
			String maxDate = Utils.dateToString(new Date(), "yyyy-MM-dd");
			String minDate = "1900-01-01";
			if(!DateValidator.isThisDateValid(pacienteData[5], format)){
				messages.addMessage("La fecha " + pacienteData[5] + " no tiene un formato válido. Recuerde que el formato válido es: Año-Mes-Día" + rut);
				messages.setValidation(false);
			}else if(!DateValidator.isThisDateInRange(pacienteData[5], format, null, maxDate)){
				messages.addMessage("La fecha " + pacienteData[5] + " no se encuentra en el rango válido. La fecha de nacimiento debe estar en el rango: " + minDate + " - " + maxDate + rut);
				messages.setValidation(false);	
			}		
		}
		
		if(!pacienteData[6].equals("")){
			if(pacienteData[6].split("-").length < 2){
				messages.addMessage(pacienteData[6] + " no es un código de genero válido" + rut);
				messages.setValidation(false);
			}else  if(!genderValidation(pacienteData[6].split("-")[1])){
				messages.addMessage(pacienteData[6] + " no es un código de genero válido" + rut);
				messages.setValidation(false);
			}
		}
		
		if(!pacienteData[8].equals("")){
			if(pacienteData[8].split("-").length < 2){
				messages.addMessage(pacienteData[8] + " no es un código de nacionalidad válido" + rut);
				messages.setValidation(false);
			}else  if(!nationalityValidation(pacienteData[8].split("-")[1])){
				messages.addMessage(pacienteData[8] + " no es un código de nacionalidad válido" + rut);
				messages.setValidation(false);
			}
		}
		
		if(!pacienteData[9].equals("")){
			if(pacienteData[9].split("-").length < 2){
				messages.addMessage(pacienteData[9] + " no es un código de pueblo originario válido" + rut);
				messages.setValidation(false);
			}else  if(!origTownValidation(pacienteData[9].split("-")[1])){
				messages.addMessage(pacienteData[9] + " no es un código de originario válido" + rut);
				messages.setValidation(false);
			}
		}
		
		if(!pacienteData[10].equals("")){
			if(pacienteData[10].split("-").length < 2){
				messages.addMessage(pacienteData[10] + " no es un código de estado conyugal válido" + rut);
				messages.setValidation(false);
			}else  if(!conjugalStateValidation(pacienteData[10].split("-")[1])){
				messages.addMessage(pacienteData[10] + " no es un código de estado conyugal válido" + rut);
				messages.setValidation(false);
			}
		}

	}
	
	private static boolean rutValidation(String rut, char dv){
		boolean validacion = false;
		try {
			rut =  rut.toUpperCase();
			rut = rut.replace(".", "");
			rut = rut.replace("-", "");
			int rutAux = Integer.parseInt(rut);
			 
			int m = 0, s = 1;
			for (; rutAux != 0; rutAux /= 10) {
				s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
			}
			if (dv == (char) (s != 0 ? s + 47 : 75)) {
				validacion = true;
			}
		 
		} catch (java.lang.NumberFormatException e) {
		} catch (Exception e) {
		}
		return validacion;
	}
	
	private static boolean firstNameValidation(String name){
		return (name.length()<=50);
	}
	
	private static boolean lastNameValidation(String lastname){
		return (lastname.length()<=30);
	}
	
	private static boolean genderValidation(String gender){
		String query = "from Genero g where g.codigo='"+gender+"'";
		return codeIsValid(query);
	}
	
	private static boolean nationalityValidation(String nation){
		String query = "from Pais p where p.codigo='" + nation + "'";
		return codeIsValid(query);
	}
	
	private static boolean origTownValidation(String town){
		String query = "from Pueblo_originario p where p.codigo='" + town + "'";
		return codeIsValid(query);
	}
	
	private static boolean conjugalStateValidation(String conjugal){
		String query = "from Estado_conyugal e where e.codigo='" + conjugal + "'";
		return codeIsValid(query);
	}
	
	public static boolean emptyRow(String[] pacienteData){
		
		for(int i=0;i<pacienteData.length-1;i++){
			if(i!=4 && i!=30){
				if(!pacienteData[i].equals("")){
					return false;
				}
			}
		}
		return true;
	}
	
	private static boolean codeIsValid(String query){

		try{
			SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	        Session session = sessionFactory.openSession();
	        Query q = session.createQuery(query);
	        Object genero =  (Object) q.uniqueResult();	
	        session.close();
	        if(genero != null) return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;	
        
	}
	@Test
	public void test(){
		assertEquals(true,codeIsValid("from Genero u where u.codigo='1'"));
		assertEquals(true, lastNameValidation("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
		assertEquals(true,rutValidation("12.199.237",'K'));
		assertEquals(true, DateValidator.isThisDateValid("11/12/2018","dd/MM/yyyy"));
		assertEquals(true, DateValidator.isThisDateInRange("11-12-2018", "dd-MM-yyyy", "10-05-2014", Utils.dateToString(new Date(), "dd-MM-yyyy")));
	}
}
