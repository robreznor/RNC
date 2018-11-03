package cl.minsal.api.util;


import cl.minsal.api.object.ValidationMessages;

public class PacienteValidator {
	
	private ValidationMessages messages;
	private Integer count;
	
	public PacienteValidator(){
		messages = new ValidationMessages();
		count = 0;
	}

	public ValidationMessages getMessages() {
		return messages;
	}
	
	public void main(){
		
	}

	public void pacienteValidation(String [] pacienteData){
		String rut = " [rut: "+pacienteData[3]+"]";

		if(pacienteData[3].equals("")){
			//model.addAttribute("error.paciente.rut","El rut es un campo requerido");
			this.messages.setMessage("El rut es un campo requerido");
			this.messages.setValidation(false);
			rut = "";
		}
		if(pacienteData[4].equals("")){
			this.messages.setMessage("El digito verificador es un campo requerido" + rut);
			this.messages.setValidation(false);
		}
		if(pacienteData[0].equals("")){
			this.messages.setMessage("El nombre del paciente es un campo requerido" + rut);
			this.messages.setValidation(false);
		}
		if(pacienteData[1].equals("")){
			this.messages.setMessage("El primer apellido del paciente es un campo requerido" + rut);
			this.messages.setValidation(false);
		}
		if(pacienteData[2].equals("")){
			this.messages.setMessage("El segundo apellido del paciente es un campo requerido" + rut);
			this.messages.setValidation(false);
		}
		if(pacienteData[21].equals("")){
			this.messages.setMessage("El tipo de comite es un campo requerido" + rut);
			this.messages.setValidation(false);
		}
		if(pacienteData[22].equals("")){
			this.messages.setMessage("La fecha de diagnostico es un campo requerido" + rut);
			this.messages.setValidation(false);
		}
		if(pacienteData[23].equals("")){
			this.messages.setMessage("La fecha de comité es un campo requerido" + rut);
			this.messages.setValidation(false);
		}
		if(pacienteData[25].equals("")){
			this.messages.setMessage("El diagnóstico CIE10 es un campo requerido" + rut);
			this.messages.setValidation(false);
		}
		if(pacienteData[26].equals("")){
			this.messages.setMessage("ECOG es un campo requerido" + rut);
			this.messages.setValidation(false);
		}
		if(pacienteData[28].equals("") || pacienteData[29].equals("") || pacienteData[30].equals("") || pacienteData[31].equals("") || pacienteData[32].equals("") || pacienteData[33].equals("")){
			this.messages.setMessage("El campo TNM está incompleto" + rut);
			this.messages.setValidation(false);
		}
		if(pacienteData[34].equals("") || pacienteData[35].equals("") ){
			this.messages.setMessage("El campo estadio está incompleto" + rut);
			this.messages.setValidation(false);
		}
		String[] tratamiento = {"primer","segundo","tercero","cuarto"};
		int contador = 0;
		for(int i=36; i<55;i=i+6)
		if(!(pacienteData[i].equals("") || pacienteData[i+1].equals("") || pacienteData[i+2].equals("") || pacienteData[i+3].equals("") || pacienteData[i+4].equals(""))){
			String message = "Al" + tratamiento[contador++] + "tratamiento ingresado le falta el campo " + rut;
			if(pacienteData[i].equals(message)){
				this.messages.setMessage(message + "nombre médico tratante");
				this.messages.setValidation(false);
			}
			if(pacienteData[i+1].equals("")){
				this.messages.setMessage(message + "tipo de tratamiento");
				this.messages.setValidation(false);
			}
			if(pacienteData[i+2].equals("")){
				this.messages.setMessage(message + "intención de tratamiento");
				this.messages.setValidation(false);
			}
			if(pacienteData[i+3].equals("")){
				this.messages.setMessage(message + "fecha intención");
				this.messages.setValidation(false);
			}
			if(pacienteData[i+4].equals("")){
				this.messages.setMessage(message + "resolución comité");
				this.messages.setValidation(false);
			}
			
		} 
		
		count ++;
		if(this.messages.getValidation()){
			this.messages.setTitle("Su archivo ha sido cargado cargado con Exito! "+ count +" Registro(s) insertados");
		}else{
			this.messages.setTitle("La carga no se pudo realizar, por los siguientes motivos:");
		}
	}
}
