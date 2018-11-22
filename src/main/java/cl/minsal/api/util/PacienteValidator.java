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
	
	public Integer getCount(){
		return this.count;
	}
	
	public void main(){
		
	}

	public void pacienteValidation(String [] pacienteData){
		String rut = " [rut: "+pacienteData[3]+"]";

		if(pacienteData[3].equals("")){
			//model.addAttribute("error.paciente.rut","El rut es un campo requerido");
			this.messages.addMessage("El rut es un campo requerido");
			this.messages.setValidation(false);
			rut = "";
		}
		if(pacienteData[4].equals("")){
			this.messages.addMessage("El digito verificador es un campo requerido" + rut);
			this.messages.setValidation(false);
		}
		if(pacienteData[0].equals("")){
			this.messages.addMessage("El nombre del paciente es un campo requerido" + rut);
			this.messages.setValidation(false);
		}
		if(pacienteData[1].equals("")){
			this.messages.addMessage("El primer apellido  del paciente es un campo requerido" + rut);
			this.messages.setValidation(false);
		}
		if(pacienteData[2].equals("")){
			this.messages.addMessage("El segundo apellido del paciente es un campo requerido" + rut);
			this.messages.setValidation(false);
		}
		if(pacienteData[21].equals("")){
			this.messages.addMessage("El tipo de comite es un campo requerido" + rut);
			this.messages.setValidation(false);
		}
		if(pacienteData[22].equals("")){
			this.messages.addMessage("La fecha de diagnostico es un campo requerido" + rut);
			this.messages.setValidation(false);
		}
		if(pacienteData[23].equals("")){
			this.messages.addMessage("La fecha de comité es un campo requerido" + rut);
			this.messages.setValidation(false);
		}
		if(pacienteData[25].equals("")){
			this.messages.addMessage("El diagnóstico CIE10 es un campo requerido" + rut);
			this.messages.setValidation(false);
		}
		if(pacienteData[26].equals("")){
			this.messages.addMessage("ECOG es un campo requerido" + rut);
			this.messages.setValidation(false);
		}
		if(pacienteData[28].equals("") || pacienteData[30].equals("") || pacienteData[31].equals("") || pacienteData[32].equals("") || pacienteData[33].equals("")){
			this.messages.addMessage("El campo TNM está incompleto" + rut);
			this.messages.setValidation(false);
		}
		if(pacienteData[34].equals("")){
			this.messages.addMessage("El campo estadio está incompleto" + rut);
			this.messages.setValidation(false);
		}
		String[] tratamiento = {"primer","segundo","tercero","cuarto"};
		int contador = 0;
		for(int i=36; i<55;i=i+6)
		if(!(pacienteData[i].equals("") || pacienteData[i+1].equals("") || pacienteData[i+2].equals("") || pacienteData[i+3].equals("") || pacienteData[i+4].equals(""))){
			String message = "Al" + tratamiento[contador++] + "tratamiento ingresado le falta el campo " + rut;
			if(pacienteData[i].equals(message)){
				this.messages.addMessage(message + "nombre médico tratante");
				this.messages.setValidation(false);
			}
			if(pacienteData[i+1].equals("")){
				this.messages.addMessage(message + "tipo de tratamiento");
				this.messages.setValidation(false);
			}
			if(pacienteData[i+2].equals("")){
				this.messages.addMessage(message + "intención de tratamiento");
				this.messages.setValidation(false);
			}
			if(pacienteData[i+3].equals("")){
				this.messages.addMessage(message + "fecha intención");
				this.messages.setValidation(false);
			}
			if(pacienteData[i+4].equals("")){
				this.messages.addMessage(message + "resolución comité");
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
