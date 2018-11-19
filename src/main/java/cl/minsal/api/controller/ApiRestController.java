package cl.minsal.api.controller;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;

import cl.minsal.api.model.Paciente;
import cl.minsal.api.service.InsertPacienteService;
import cl.minsal.api.service.PacienteDataService;
import cl.minsal.api.util.FileValidator;

import java.text.ParseException;
import java.util.Set;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import cl.minsal.api.object.*;

@RestController
public class ApiRestController {
	
    @Autowired
    FileValidator fileValidator;
	
    @PreAuthorize ("hasRole('ROLE_WRITE')")
    @Secured("ROLE_VIEWER")
	@RequestMapping(value="/api/paciente_search", method=RequestMethod.GET)
    public Set<PacienteSearch> pacienteSearch() {
    	Set<PacienteSearch> pacientes = PacienteDataService.getPacientes();
		return pacientes;
    }

	@RequestMapping(value="/api/paciente/{id}", method=RequestMethod.GET)
	private Paciente search(@PathVariable Integer id){
		Paciente paciente = PacienteDataService.getPaciente(id);
		return paciente;
	}
	
	@RequestMapping(value="/api/paciente_data/{id}", method=RequestMethod.GET)
	private PacienteData paciente_data(@PathVariable Integer id){
		PacienteData paciente_data = PacienteDataService.getPacienteData(id);
		return paciente_data;
	}
	
	@RequestMapping(value = "/api/file_upload", method = RequestMethod.POST)
    public ValidationMessages singleFileUpload(@Valid FileBucket fileBucket,
            BindingResult result, ValidationMessages validadorResponse, HttpServletRequest request) throws IOException, ParseException {
		

        if (result.hasErrors()) {
        	
        	validadorResponse.setValidation(false);
        	validadorResponse.addMessage("");
        	validadorResponse.setTitle("");
            return validadorResponse;
        } else {
            try {
            	System.out.println(request.getAuthType());
        		System.out.println(request.getHeader("Authorization"));
        		System.out.println(request.getHeaderNames());
            	ValidationMessages fileValidation = FileValidator.validate(fileBucket);
            	if(fileValidation.getValidation()){
            		InputStreamReader file = new InputStreamReader(fileBucket.getFile().getInputStream(), "UTF8");
                	InsertPacienteService insertar = new InsertPacienteService();
                	System.out.println("Insertando datos");
                    insertar.InsertData(file);
                    validadorResponse = insertar.getMessages();
            	}else {
            		validadorResponse = fileValidation;
            	}
            	
            } catch (IOException e) {
                e.printStackTrace();
                validadorResponse.setValidation(false);
            	validadorResponse.setTitle("Hubo un error al procesar su archivo");
                return validadorResponse; 
            } 
            return validadorResponse;
        }
    }
	
	@RequestMapping(value="/api/diagnosticos/{id}", method=RequestMethod.GET)
    public Set<Diagnostico> DiagnosticosPaciente(@PathVariable Integer id) {
		Set<Diagnostico> diagnosticos = PacienteDataService.getDiagnosticos(id);
		return diagnosticos;
	}
	
	@RequestMapping(value="/api/tratamientos/{id}", method=RequestMethod.GET)
    public Set<ResolucionTratamiento> TratamientosPaciente(@PathVariable Integer id) {
		Set<ResolucionTratamiento> tratamientos = PacienteDataService.getTratamientos(id);
		return tratamientos;
	}
	
}
