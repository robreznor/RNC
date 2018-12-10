package cl.minsal.api.util;

import cl.minsal.api.object.ValidationMessages;

public class ValidationsUtil {

	public static void emptyFieldValidation(String [] pacienteData, ValidationMessages messages){
	
		String rut = " [rut: "+pacienteData[3]+"]";
	
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
}
