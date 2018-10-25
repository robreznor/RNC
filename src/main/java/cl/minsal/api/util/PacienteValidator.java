package cl.minsal.api.util;

import org.springframework.ui.ModelMap;

public class PacienteValidator {
	
	private ModelMap model;
	private Boolean validation_fail;
	
	public PacienteValidator(){
		model = new ModelMap();
		this.validation_fail = false;
	}

	public ModelMap getModel() {
		return model;
	}

	public Boolean getValidation_fail() {
		return validation_fail;
	}

	public void pacienteValidation(String [] pacienteData){
		
		if(pacienteData[3].equals("")){
			model.addAttribute("error.paciente.rut","El rut es un campo requerido");
			this.validation_fail = true;
		}
		if(pacienteData[4].equals("")){
			model.addAttribute("error.paciente.dv","El digito verificador es un campo requerido");
			this.validation_fail = true;
		}
		if(pacienteData[0].equals("")){
			model.addAttribute("error.paciente.nombre","El nombre del paciente es un campo requerido");
			this.validation_fail = true;
		}
		if(pacienteData[1].equals("")){
			model.addAttribute("error.paciente.apellido1","El primer apellido del paciente es un campo requerido");
			this.validation_fail = true;
		}
		if(pacienteData[2].equals("")){
			model.addAttribute("error.paciente.apellido","El segundo apellido del paciente es un campo requerido");
			this.validation_fail = true;
		}
		
	}
}
