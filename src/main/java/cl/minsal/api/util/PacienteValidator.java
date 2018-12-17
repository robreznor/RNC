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

		ValidationsUtil.pacienteValidation(pacienteData, this.messages);
		count ++;
		if(this.messages.getValidation()){
			this.messages.setTitle("Su archivo ha sido cargado cargado con Exito! "+ count +" Registro(s) insertados");
		}else{
			this.messages.setTitle("La carga no se pudo realizar por los siguientes motivos:");
		}
	}
}
