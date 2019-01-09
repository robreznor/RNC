package cl.minsal.api.util;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

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
		String message = "Al " + tratamiento[contador++] + " tratamiento ingresado le falta el campo ";
		for(int i=36; i<55;i=i+6)
		if(!(pacienteData[i].equals("") && pacienteData[i+1].equals("") && pacienteData[i+2].equals("") && pacienteData[i+3].equals("") && pacienteData[i+4].equals(""))){
			
			if(pacienteData[i].equals("")){
				messages.addMessage(message + "nombre médico tratante" + rut);
				messages.setValidation(false);
			}
			if(pacienteData[i+1].equals("")){
				messages.addMessage(message + "tipo de tratamiento" + rut);
				messages.setValidation(false);
			}
			if(pacienteData[i+2].equals("")){
				messages.addMessage(message + "intención de tratamiento" + rut);
				messages.setValidation(false);
			}
			if(pacienteData[i+3].equals("")){
				messages.addMessage(message + "fecha intención" + rut);
				messages.setValidation(false);
			}
			if(pacienteData[i+4].equals("")){
				messages.addMessage(message + "resolución comité" + rut);
				messages.setValidation(false);
			}
			
		} 
	}
	
	private static void formatValidation(String [] pacienteData, ValidationMessages messages){
		
		String rut = (pacienteData[3].equals(""))? "": " [rut: "+pacienteData[3]+"-"+pacienteData[4]+"]";
		if(!pacienteData[0].equals("")){
			if(!firstNameValidation(pacienteData[0])){
				messages.addMessage("El nombre "+pacienteData[0]+" no tiene un formato válido, el máximo permitido son 50 caracteres y debe contener solo letras" + rut);
				messages.setValidation(false);
			}
		}
		if(!pacienteData[1].equals("")){
			if(!lastNameValidation(pacienteData[1])){
				messages.addMessage("El primer apellido "+pacienteData[1]+" no tiene un formato válido, el máximo permitido son 30 caracteres, se aceptan letras y los caracteres '-" + rut);
				messages.setValidation(false);
			}
		}
		if(!pacienteData[2].equals("")){
			if(!lastNameValidation(pacienteData[2])){
				messages.addMessage("El segundo apellido " + pacienteData[2] + " no tiene un formato válido, el máximo permitido son 30 caracteres, se aceptan letras y los caracteres '-" + rut);
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
				messages.addMessage("La fecha de nacimiento: " + pacienteData[5] + " no tiene un formato válido. Recuerde que el formato válido es: Año-Mes-Día" + rut);
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
		if(!pacienteData[11].equals("")){
			if(pacienteData[11].split("-").length < 2){
				messages.addMessage(pacienteData[11] + " no es un código de religión válido" + rut);
				messages.setValidation(false);
			}else  if(!religionValidation(pacienteData[11].split("-")[1])){
				messages.addMessage(pacienteData[11] + " no es un código de religión válido" + rut);
				messages.setValidation(false);
			}
		}
		if(!pacienteData[12].equals("")){
			if(pacienteData[12].split("-").length < 2){
				messages.addMessage(pacienteData[12] + " no es un código de nivel de instrucción válido" + rut);
				messages.setValidation(false);
			}else  if(!instructionLevelValidation(pacienteData[12].split("-")[1])){
				messages.addMessage(pacienteData[12] + " no es un código de nivel de instrucción válido" + rut);
				messages.setValidation(false);
			}
		}
		if(!pacienteData[13].equals("")){
			if(pacienteData[13].split("-").length < 2){
				messages.addMessage(pacienteData[13] + " no es un código de ocupación válido" + rut);
				messages.setValidation(false);
			}else  if(!ocupationValidation(pacienteData[13].split("-")[1])){
				messages.addMessage(pacienteData[13] + " no es un código de ocupación válido" + rut);
				messages.setValidation(false);
			}
		}
		
		if(!pacienteData[14].equals("")){
			if(pacienteData[14].split("-").length < 2){
				messages.addMessage(pacienteData[14] + " no es un código de actividad económica válido" + rut);
				messages.setValidation(false);
			}else  if(!economicActivityValidation(pacienteData[14].split("-")[1])){
				messages.addMessage(pacienteData[14] + " no es un código de actividad económica válido" + rut);
				messages.setValidation(false);
			}
		}
		
		if(!pacienteData[15].equals("")){
			if(pacienteData[15].split("-").length < 2){
				messages.addMessage(pacienteData[15] + " no es un código de previsión válido" + rut);
				messages.setValidation(false);
			}else  if(!previtionValidation(pacienteData[15].split("-")[1])){
				messages.addMessage(pacienteData[15] + " no es un código de previsión válido" + rut);
				messages.setValidation(false);
			}
		}
		if(!pacienteData[16].equals("")){
			if(pacienteData[16].split("-").length < 2){
				messages.addMessage(pacienteData[16] + " no es un código de beneficiario fonasa válido" + rut);
				messages.setValidation(false);
			}else  if(!fonasaBenValidation(pacienteData[16].split("-")[1])){
				messages.addMessage(pacienteData[16] + " no es un código de beneficiario fonasa válido" + rut);
				messages.setValidation(false);
			}
		}
		if(!pacienteData[17].equals("")){
			if(pacienteData[17].split("_").length < 2){
				messages.addMessage(pacienteData[17] + " no es un código de región válido" + rut);
				messages.setValidation(false);
			}else  if(!regionValidation(pacienteData[17].split("_")[1])){
				messages.addMessage(pacienteData[17] + " no es un código de región válido" + rut);
				messages.setValidation(false);
			}
		}
		
		if(!pacienteData[18].equals("")){
			if(pacienteData[18].split("_").length < 2){
				messages.addMessage(pacienteData[18] + " no es un código de provincia válido" + rut);
				messages.setValidation(false);
			}else  if(!provinceValidation(pacienteData[18].split("_")[1])){
				messages.addMessage(pacienteData[18] + " no es un código de provincia válido" + rut);
				messages.setValidation(false);
			}
		}
		
		if(!pacienteData[19].equals("")){
			if(pacienteData[19].split("-").length < 2){
				messages.addMessage(pacienteData[19] + " no es un código de comuna válido" + rut);
				messages.setValidation(false);
			}else  if(!communeValidation(pacienteData[19].split("-")[1])){
				messages.addMessage(pacienteData[19] + " no es un código de comuna válido" + rut);
				messages.setValidation(false);
			}
		}
		if(!pacienteData[21].equals("")){
			if(pacienteData[21].split("_").length < 2){
				messages.addMessage(pacienteData[21] + " no es un código de tipo de comité válido" + rut);
				messages.setValidation(false);
			}else  if(!committeeValidation(pacienteData[21].split("_")[1])){
				messages.addMessage(pacienteData[21] + " no es un código de tipo de comité válido" + rut);
				messages.setValidation(false);
			}
		}
		
		if(!pacienteData[22].equals("")){
			String format = "yyyy-MM-dd";
			String maxDate = Utils.dateToString(new Date(), "yyyy-MM-dd");
			String minDate = "1900-01-01";
			if(!DateValidator.isThisDateValid(pacienteData[22], format)){
				messages.addMessage("La fecha de diagnóstico " + pacienteData[22] + " no tiene un formato válido. Recuerde que el formato válido es: Año-Mes-Día" + rut);
				messages.setValidation(false);
			}else if(!DateValidator.isThisDateInRange(pacienteData[22], format, minDate, maxDate)){
				messages.addMessage("La fecha " + pacienteData[22] + " no se encuentra en el rango válido. La fecha de diagnóstico debe estar en el rango: " + minDate + " - " + maxDate + rut);
				messages.setValidation(false);	
			}		
		}
		
		if(!pacienteData[23].equals("")){
			String format = "yyyy-MM-dd";
			String maxDate = Utils.dateToString(new Date(), "yyyy-MM-dd");
			String minDate = "1900-01-01";
			if(!DateValidator.isThisDateValid(pacienteData[23], format)){
				messages.addMessage("La fecha de comité " + pacienteData[23] + " no tiene un formato válido. Recuerde que el formato válido es: Año-Mes-Día" + rut);
				messages.setValidation(false);
			}else if(!DateValidator.isThisDateInRange(pacienteData[23], format, minDate, maxDate)){
				messages.addMessage("La fecha " + pacienteData[23] + " no se encuentra en el rango válido. La fecha de comité debe estar en el rango: " + minDate + " - " + maxDate + rut);
				messages.setValidation(false);	
			}		
		}
		if(!pacienteData[25].equals("")){
			if(pacienteData[25].length()>7){
				messages.addMessage(pacienteData[25] + " no es un diagnóstico CIE10 con formato válido. Recuerde que el formato válido debe tener como máximo 7 caracteres, por ejemplo: C00-C14" + rut);
				messages.setValidation(false);
			}
		}
		if(!pacienteData[26].equals("")){
			if(pacienteData[26].split("_").length < 2){
				messages.addMessage(pacienteData[26] + " no es un código de ECOG válido" + rut);
				messages.setValidation(false);
			}else  if(!ecogValidation(pacienteData[26].split("_")[1])){
				messages.addMessage(pacienteData[26] + " no es un código de ECOG válido" + rut);
				messages.setValidation(false);
			}
		}
		if(!pacienteData[28].equals("")){
			if(!tTNMValidation(pacienteData[28], pacienteData[29])){
				messages.addMessage(pacienteData[28] + pacienteData[29] + " en T de TNM no es un código válido" + rut);
				messages.setValidation(false);
			}
		}
		if(!pacienteData[30].equals("") && !pacienteData[31].equals("")){
			if(!nTNMValidation(pacienteData[30], pacienteData[31])){
				messages.addMessage(pacienteData[30] + pacienteData[31] + " en N de TNM no es un código válido" + rut);
				messages.setValidation(false);
			}
		}
		if(!pacienteData[32].equals("") && !pacienteData[33].equals("")){
			if(!mTNMValidation(pacienteData[32], pacienteData[33])){
				messages.addMessage(pacienteData[32] + pacienteData[33] + " en M de TNM no es un código válido" + rut);
				messages.setValidation(false);
			}
		}
		
		if(!pacienteData[34].equals("")){
			if(!estadioLevelValidation(pacienteData[34])){
				messages.addMessage("El nivel: " + pacienteData[34] + " en estadio no es un código válido" + rut);
				messages.setValidation(false);
			}	
			if(!estadioOptionValidation(pacienteData[35])){
				messages.addMessage("La opción: " + pacienteData[35] + " en estadio no es un código válido" + rut);
				messages.setValidation(false);
			}
		}	
		for(int i=36; i<55; i=i+6){		
			if(!pacienteData[i].equals("")){
				if(!pacienteData[i].matches("[a-zA-záéíóúÁÉÍÓÚñÑ]+([ '-][a-zA-ZáéíóúÁÉÍÓÚñÑ]+)*")){
					messages.addMessage(pacienteData[i] + " no tiene un formato de médico tratante válido" + rut);
					messages.setValidation(false);
				}
			}	
			if(!pacienteData[i+1].equals("")){
				if(pacienteData[i+2].split("-").length < 2){
					messages.addMessage(pacienteData[i+1] + " no es un código de tipo de tratamiento válido" + rut);
					messages.setValidation(false);
				}else if(!treatmentTypeValidation(pacienteData[i+1].split("-")[1])){
					messages.addMessage(pacienteData[i+1] + " no es un código de tipo de tratamiento válido" + rut);
					messages.setValidation(false);
				}
			}	
			if(!pacienteData[i+2].equals("")){
				if(pacienteData[i+2].split("-").length < 2){
					messages.addMessage(pacienteData[i+2] + " no es un código de Intención de tratamiento válido. Códigos válidos: Paliativo-1, Curativo-2" + rut);
					messages.setValidation(false);
				}else  if(!treatmentTypeValidation(pacienteData[i+2].split("-")[1])){
					messages.addMessage(pacienteData[i+2] + " no es un código de Intención de tratamiento válido. Códigos válidos: Paliativo-1, Curativo-2" + rut);
					messages.setValidation(false);
				}
			}
			/*Nota: checkear la fecha mínima*/
			if(!pacienteData[i+3].equals("")){
				String format = "yyyy-MM-dd";
				if(!DateValidator.isThisDateValid(pacienteData[i+3], format)){
					messages.addMessage("La fecha de intención de tratamiento " + pacienteData[i+3] + " no tiene un formato válido. Recuerde que el formato válido es: Año-Mes-Día" + rut);
					messages.setValidation(false);
				}		
			}
			
			if(!pacienteData[i+4].equals("")){
				if(pacienteData[i+4].split("-").length < 2){
					messages.addMessage(pacienteData[i+4] + " no es un código de resolución de comite válido" + rut);
					messages.setValidation(false);
				}else  if(!treatmentResolutionValidation(pacienteData[i+4].split("-")[1])){
					messages.addMessage(pacienteData[i+4] + " no es un código de resolución de comite válido" + rut);
					messages.setValidation(false);
				}
			}
		}
	}
	
	private static boolean rutValidation(String rut, char dv){
		boolean validation = false;
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
				validation = true;
			}
		 
		} catch (java.lang.NumberFormatException e) {
		} catch (Exception e) {
		}
		return validation;
	}
	
	private static boolean firstNameValidation(String name){
		return (name.length()<=50 && name.matches( "[a-zA-ZáéíóúÁÉÍÓÚñÑ]+([.][ ][a-zA-ZáéíóúÁÉÍÓÚñÑ]+)*"));
	}
	
	private static boolean lastNameValidation(String lastname){
		return (lastname.length()<=30 && lastname.matches("[a-zA-záéíóúÁÉÍÓÚñÑ]+([ '-][a-zA-ZáéíóúÁÉÍÓÚñÑ]+)*" ));
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
	
	private static boolean religionValidation(String religion){
		String query = "from Religion_culto r where r.codigo='" + religion + "'";
		return codeIsValid(query);
	}
	
	private static boolean instructionLevelValidation(String instruction){
		String query = "from Instruccion i where i.codigo='" + instruction + "'";
		return codeIsValid(query);
	}
	
	private static boolean ocupationValidation(String ocupation){
		String query = "from Ocupacion o where o.codigo='" + ocupation + "'";
		return codeIsValid(query);
	}
	
	private static boolean previtionValidation(String prevition){
		String query = "from Prevision p where p.codigo='" + prevition + "'";
		return codeIsValid(query);
	}
	
	private static boolean fonasaBenValidation(String fonasaBen){
		String query = "from Beneficiario_fonasa b where b.codigo='" + fonasaBen + "'";
		return codeIsValid(query);
	}
	
	private static boolean regionValidation(String region){
		String query = "from Region r where r.codigo='" + region + "'";
		return codeIsValid(query);
	}
	
	private static boolean provinceValidation(String province){
		String query = "from Provincia p where p.codigo='" + province + "'";
		return codeIsValid(query);
	}
	
	private static boolean communeValidation(String commune){
		String query = "from Comuna c where c.codigo='" + commune + "'";
		return codeIsValid(query);
	}
	
	private static boolean healthServiceValidation(String servicioSalud){
		String query = "from Servicio_salud s where s.codigo='" + servicioSalud + "'";
		return codeIsValid(query);
	}
	
	private static boolean economicActivityValidation(String econAct){
		String query = "from Actividad_economica a where a.codigo='" + econAct + "'";
		return codeIsValid(query);
	}
	
	private static boolean committeeValidation(String committee){
		String query = "from Tipos_comite t where t.codigo='" + committee + "'";
		return codeIsValid(query);
	}
	
	private static boolean ecogValidation(String ecog){
		String query = "from Ecog e where e.codigo='" + ecog + "'";
		return codeIsValid(query);
	}
	
	private static boolean treatmentTypeValidation(String tipoTratamiento){
		String query = "from Tipo_tratamiento t where t.codigo='" + tipoTratamiento + "'";
		return codeIsValid(query);
	}
	
	private static boolean treatmentResolutionValidation(String treatRes){
		String query = "from Resolucion_comite t where t.codigo_resolucion='" + treatRes + "'";
		return codeIsValid(query);
	}
	
	private static boolean tTNMValidation(String t0, String t1){
		boolean t0bool = false;
		boolean t1bool = false;
		if(t0.equals("0") || t0.equals("1") ||t0.equals("2") || t0.equals("3") || t0.equals("4") || t0.toLowerCase().equals("x") || t0.equals("Tis")){
			t0bool = true;
		}
		if(t1.equals("") || t1.toLowerCase().equals("a") || t1.toLowerCase().equals("b")){
			t1bool = true;
		}
		return (t0bool && t1bool);
	}

	private static boolean nTNMValidation(String n0, String n1){
		boolean n0bool = false;
		boolean n1bool = false;
		if(n0.toLowerCase().equals("n") ){
			n0bool = true;
		}
		if(n1.equals("0") || n1.equals("1") || n1.equals("2") || n1.equals("3") || n1.toLowerCase().equals("x")){
			n1bool = true;
		}
		return (n0bool && n1bool);
	}
	
	private static boolean mTNMValidation(String m0, String m1){
		boolean m0bool = (m0.toLowerCase().equals("m"));
		boolean m1bool = (m1.equals("0") || m1.equals("1") || m1.toLowerCase().equals("x"));
		return (m0bool && m1bool);
	}
	
	public static boolean estadioLevelValidation(String level){
		return (level.toUpperCase().equals("I") || level.toUpperCase().equals("II") || level.toUpperCase().equals("III") || level.toUpperCase().equals("IV") || level.toUpperCase().equals("X"));
	}
	
	public static boolean estadioOptionValidation(String option){
		return (option.equals("") || option.toLowerCase().equals("a") || option.toLowerCase().equals("b") || option.toLowerCase().equals("c"));
	}
	
	public static boolean treatmentIntentionValidation(String treatIntention){
		return (treatIntention.equals("1") || treatIntention.equals("2"));
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
		assertEquals(true, firstNameValidation("sanhuesa"));
		assertEquals(true, firstNameValidation("ñoÑo"));
		assertEquals(true, firstNameValidation("juanito "));
		assertEquals(true, firstNameValidation("dr. robert"));
		assertEquals(false, firstNameValidation("roberto-"));
		assertEquals(false, firstNameValidation("roberto*{^^^}}"));
		assertEquals(true, lastNameValidation("mac lean"));
		assertEquals(true, lastNameValidation("mac-leán"));
		assertEquals(false, lastNameValidation("mac**lean"));
		assertEquals(true, lastNameValidation("gómez"));

	}
}
