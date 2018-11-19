package cl.minsal.api.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import cl.minsal.api.model.Actividad_economica;
import cl.minsal.api.model.Antecedentes;
import cl.minsal.api.model.Beneficiario_fonasa;
import cl.minsal.api.model.Comuna;
import cl.minsal.api.model.Diagnostico;
import cl.minsal.api.model.Ecog;
import cl.minsal.api.model.Estado_conyugal;
import cl.minsal.api.model.Genero;
import cl.minsal.api.model.Instruccion;
import cl.minsal.api.model.Intencion_tratamiento;
import cl.minsal.api.model.Localizacion;
import cl.minsal.api.model.Medico;
import cl.minsal.api.model.Motivo_presentacion;
import cl.minsal.api.model.Ocupacion;
import cl.minsal.api.model.Paciente;
import cl.minsal.api.model.Pais;
import cl.minsal.api.model.Prevision;
import cl.minsal.api.model.Provincia;
import cl.minsal.api.model.Pueblo_originario;
import cl.minsal.api.model.Region;
import cl.minsal.api.model.Religion_culto;
import cl.minsal.api.model.Resolucion_comite;
import cl.minsal.api.model.Tipo_tratamiento;
import cl.minsal.api.model.Tipos_comite;
import cl.minsal.api.model.Tratamiento;
import cl.minsal.api.object.ValidationMessages;
import cl.minsal.api.util.HibernateUtility;
import cl.minsal.api.util.PacienteValidator;

public class InsertPacienteService {
	
	private ValidationMessages messages;
	private Timestamp timestamp;
	
	public InsertPacienteService(){
		TimeZone.setDefault(TimeZone.getTimeZone("Chile/Continental"));
	}
	
	public ValidationMessages getMessages() {
		return messages;
	}

	private Paciente InsertPaciente(String[] pacienteData, Paciente paciente, Session session) throws ParseException{

        if(paciente == null){
        	paciente = new Paciente();  
    		paciente.setNombre(pacienteData[0]);
        	paciente.setRut(Integer.parseInt(pacienteData[3]));
        	paciente.setDverificador(pacienteData[4]);
        	
        	if(!pacienteData[1].equals("")) paciente.setApellido1(pacienteData[1]);
    		if(!pacienteData[2].equals("")) paciente.setApellido2(pacienteData[2]);
    		if(!pacienteData[5].equals("")){
    			SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
            	java.util.Date fecha_nacimiento_date= fecha.parse(pacienteData[5]);
            	java.sql.Date fecha_nacimiento = new java.sql.Date(fecha_nacimiento_date.getTime());
    			paciente.setFecha_nacimiento(fecha_nacimiento);
    		}
    		
    		Query q = null;   		 	
    		
    		if(!pacienteData[6].equals("")){ 
        		q = session.createQuery("from Genero gen where gen.codigo= '" + pacienteData[6].split("-")[1] + "'");
        		Genero genero = (Genero) q.uniqueResult();
    			paciente.setGenero(genero);
    		}
    		if(!pacienteData[8].equals("")){
    			q = session.createQuery("from Pais pais where pais.codigo= '" + pacienteData[8].split("-")[1] + "'");
        		Pais pais = (Pais) q.uniqueResult();
    			paciente.setPais(pais);
    		}
    		if(!pacienteData[9].equals("")){
    			q = session.createQuery("from Pueblo_originario pueblo where pueblo.codigo= '" + pacienteData[9].split("-")[1] + "'");
        		Pueblo_originario pueblo_originario = (Pueblo_originario) q.uniqueResult();
    			paciente.setPueblo_originario(pueblo_originario);
    		}
    		if(!pacienteData[10].equals("")){
    			 q = session.createQuery("from Estado_conyugal estado where estado.codigo= '" + pacienteData[10].split("-")[1] + "'");
	    		Estado_conyugal estado_conyugal = (Estado_conyugal) q.uniqueResult();
    			paciente.setEstado_conyugal(estado_conyugal);
    		}
    		if(!pacienteData[11].equals("")){
    			q = session.createQuery("from Religion_culto religion where religion.codigo= '" + pacienteData[11].split("-")[1] + "'");
        		Religion_culto religion = (Religion_culto) q.uniqueResult();
    			paciente.setReligion(religion);
    		}
    		if(!pacienteData[12].equals("")){
    			q = session.createQuery("from Instruccion ins where ins.codigo= '" + pacienteData[12].split("-")[1] + "'");
        		Instruccion instruccion = (Instruccion) q.uniqueResult();
    			paciente.setInstruccion(instruccion);
    		}
    		if(!pacienteData[13].equals("")){
    			 q = session.createQuery("from Ocupacion ocup where ocup.codigo= '" + pacienteData[13].split("-")[1] + "'");
	    		Ocupacion ocupacion = (Ocupacion) q.uniqueResult();
    			paciente.setOcupacion(ocupacion);
    		}
    		
    		if(!pacienteData[14].equals("")){
    			q = session.createQuery("from Actividad_economica a where a.codigo= '" + pacienteData[14].split("-")[1] + "'");
    	        Actividad_economica actividad_economica = (Actividad_economica) q.uniqueResult();  
    			paciente.setActividad_economica(actividad_economica);
    		}
    		if(!pacienteData[15].equals("")){
    			q = session.createQuery("from Prevision prev where prev.codigo= '" + pacienteData[15].split("-")[1] + "'");
    	        Prevision prevision = (Prevision) q.uniqueResult();
    			paciente.setPrevision(prevision);
    		}
    		if(!pacienteData[16].equals("")){
    			q = session.createQuery("from Beneficiario_fonasa fonasa where fonasa.codigo= '" + pacienteData[16].split("-")[1] + "'");
    	        Beneficiario_fonasa beneficiario_fonasa = (Beneficiario_fonasa) q.uniqueResult();
    			paciente.setBeneficiario_fonasa(beneficiario_fonasa);
    		}
    		paciente.setFecha_registro(this.timestamp);

        }
    	return paciente;
	}
	
	private Diagnostico InsertDiagnostico(String[] pacienteData, Paciente paciente) throws ParseException{
		
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
        Session session = sessionFactory.openSession();
		Query q = session.createQuery("from Tipos_comite tipo where tipo.codigo= '" + pacienteData[21].split("_")[1] + "'");
		Tipos_comite tipo_comite = (Tipos_comite) q.uniqueResult();	
		q = session.createQuery("from Ecog ecog where ecog.codigo= '" + pacienteData[26].split("_")[1] + "'");
		Ecog ecog = (Ecog) q.uniqueResult();
		
		System.out.println("insertando diagnostico");
		Diagnostico diagnostico = new Diagnostico();
		SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date fecha_diagnostico_date= fecha.parse(pacienteData[22]);
    	java.util.Date fecha_comite_date= fecha.parse(pacienteData[23]);
    	java.sql.Date fecha_diagnostico = new java.sql.Date(fecha_diagnostico_date.getTime()); 
    	java.sql.Date fecha_comite = new java.sql.Date(fecha_comite_date.getTime());
    	
		diagnostico.setTipo_comite(tipo_comite);
		diagnostico.setFecha_diagnostico(fecha_diagnostico);
		diagnostico.setFecha_comite(fecha_comite);
		diagnostico.setDiagnostico_comite(pacienteData[24]);
		diagnostico.setDiagnostico_cie10(pacienteData[25]);
		diagnostico.setEcog(ecog);
		if(pacienteData.length>35){
			diagnostico.setEstadio(pacienteData[34]+pacienteData[35]);
		}else{
			diagnostico.setEstadio(pacienteData[34]);
		}
		
		diagnostico.setPaciente(paciente);
		diagnostico.setTnm(pacienteData[28]+pacienteData[29]+pacienteData[30]+pacienteData[31]+pacienteData[32]+pacienteData[33]);
		diagnostico.setFecha_registro(this.timestamp);
		session.close();
		return diagnostico;
	}
	
	private Set<Tratamiento> InsertTratamiento(String[] pacienteData, Diagnostico diagnostico, Session session) throws ParseException{
		System.out.println("insertando tratamiento");
		Set<Tratamiento> tratamientos = new HashSet<Tratamiento>();
		SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");

		if(!pacienteData[40].equals("")){
			Tratamiento tratamiento = new Tratamiento();
			Medico medico = new Medico();
			medico.setNombre_medico(pacienteData[36]);
			medico.setFecha_registro(timestamp);
			
			java.util.Date fecha_intencion_date= fecha.parse(pacienteData[39]);
			java.sql.Date fecha_intencion = new java.sql.Date(fecha_intencion_date.getTime());
			
			Query q = session.createQuery("from Tipo_tratamiento tratamiento where tratamiento.codigo= '" + pacienteData[37].split("-")[1] + "'");
			Tipo_tratamiento tipo_tratamiento = (Tipo_tratamiento) q.uniqueResult();
	        q = session.createQuery("from Intencion_tratamiento intencion where intencion.codigo= '" + pacienteData[38].split("-")[1] + "'");
	        Intencion_tratamiento intencion_tratamiento = (Intencion_tratamiento) q.uniqueResult();
	        q = session.createQuery("from Resolucion_comite res where res.codigo_resolucion= '" + pacienteData[40].split("-")[1] + "'");
	        Resolucion_comite resolucion_comite = (Resolucion_comite) q.uniqueResult();
	        
			tratamiento.setTipo_tratamiento(tipo_tratamiento);
			tratamiento.setIntencion_tratamiento(intencion_tratamiento);
			tratamiento.setFecha_intencion(fecha_intencion);
			tratamiento.setDescripcion_tratamiento(pacienteData[41]);
			tratamiento.setMedico(medico);
			tratamiento.setDiagnostico(diagnostico);
			tratamiento.setResolucion_comite(resolucion_comite);
			tratamiento.setFecha_registro(this.timestamp);
			tratamientos.add(tratamiento);
		}
		
		if(!pacienteData[46].equals("")){
			Tratamiento tratamiento = new Tratamiento();
			Medico medico = new Medico();
			medico.setNombre_medico(pacienteData[42]);
			medico.setFecha_registro(timestamp);
			
			java.util.Date fecha_intencion_date= fecha.parse(pacienteData[45]);
			java.sql.Date fecha_intencion = new java.sql.Date(fecha_intencion_date.getTime());
						
			Query q = session.createQuery("from Tipo_tratamiento tratamiento where tratamiento.codigo= '" + pacienteData[43].split("-")[1] + "'");
			Tipo_tratamiento tipo_tratamiento = (Tipo_tratamiento) q.uniqueResult();
	        q = session.createQuery("from Intencion_tratamiento intencion where intencion.codigo= '" + pacienteData[44].split("-")[1] + "'");
	        Intencion_tratamiento intencion_tratamiento = (Intencion_tratamiento) q.uniqueResult();
	        q = session.createQuery("from Resolucion_comite res where res.codigo_resolucion= '" + pacienteData[46].split("-")[1] + "'");
	        Resolucion_comite resolucion_comite = (Resolucion_comite) q.uniqueResult();
	        
	        
			tratamiento.setTipo_tratamiento(tipo_tratamiento);
			tratamiento.setIntencion_tratamiento(intencion_tratamiento);
			tratamiento.setFecha_intencion(fecha_intencion);
			tratamiento.setDescripcion_tratamiento(pacienteData[54]);
			tratamiento.setMedico(medico);
			tratamiento.setDiagnostico(diagnostico);
			tratamiento.setResolucion_comite(resolucion_comite);
			tratamiento.setFecha_registro(this.timestamp);
			tratamientos.add(tratamiento);
		}
		if(!pacienteData[52].equals("")){
			Tratamiento tratamiento = new Tratamiento();
			Medico medico = new Medico();
			medico.setNombre_medico(pacienteData[48]);
			medico.setFecha_registro(timestamp);
			
			java.util.Date fecha_intencion_date= fecha.parse(pacienteData[51]);
			java.sql.Date fecha_intencion = new java.sql.Date(fecha_intencion_date.getTime());
					
			Query q = session.createQuery("from Tipo_tratamiento tratamiento where tratamiento.codigo= '" + pacienteData[49].split("-")[1] + "'");
			Tipo_tratamiento tipo_tratamiento = (Tipo_tratamiento) q.uniqueResult();
	        q = session.createQuery("from Intencion_tratamiento intencion where intencion.codigo= '" + pacienteData[50].split("-")[1] + "'");
	        Intencion_tratamiento intencion_tratamiento = (Intencion_tratamiento) q.uniqueResult();
	        q = session.createQuery("from Resolucion_comite res where res.codigo_resolucion= '" + pacienteData[52].split("-")[1] + "'");
	        Resolucion_comite resolucion_comite = (Resolucion_comite) q.uniqueResult();
	        
			tratamiento.setTipo_tratamiento(tipo_tratamiento);
			tratamiento.setIntencion_tratamiento(intencion_tratamiento);
			tratamiento.setFecha_intencion(fecha_intencion);
			tratamiento.setDescripcion_tratamiento(pacienteData[53]);
			tratamiento.setMedico(medico);
			tratamiento.setDiagnostico(diagnostico);
			tratamiento.setResolucion_comite(resolucion_comite);
			tratamiento.setFecha_registro(this.timestamp);
			tratamientos.add(tratamiento);
		}
		if(!pacienteData[58].equals("")){
			Tratamiento tratamiento = new Tratamiento();
			Medico medico = new Medico();
			medico.setNombre_medico(pacienteData[54]);
			medico.setFecha_registro(timestamp);
			
			java.util.Date fecha_intencion_date= fecha.parse(pacienteData[57]);
			java.sql.Date fecha_intencion = new java.sql.Date(fecha_intencion_date.getTime());
			
			Query q = session.createQuery("from Tipo_tratamiento tratamiento where tratamiento.codigo= '" + pacienteData[55].split("-")[1] + "'");
			Tipo_tratamiento tipo_tratamiento = (Tipo_tratamiento) q.uniqueResult();
	        q = session.createQuery("from Intencion_tratamiento intencion where intencion.codigo= '" + pacienteData[56].split("-")[1] + "'");
	        Intencion_tratamiento intencion_tratamiento = (Intencion_tratamiento) q.uniqueResult();
	        q = session.createQuery("from Resolucion_comite res where res.codigo_resolucion= '" + pacienteData[58].split("-")[1] + "'");
	        Resolucion_comite resolucion_comite = (Resolucion_comite) q.uniqueResult();
	        
			tratamiento.setTipo_tratamiento(tipo_tratamiento);
			tratamiento.setIntencion_tratamiento(intencion_tratamiento);
			tratamiento.setFecha_intencion(fecha_intencion);
			tratamiento.setDescripcion_tratamiento(pacienteData[59]);
			tratamiento.setMedico(medico);
			tratamiento.setDiagnostico(diagnostico);
			tratamiento.setResolucion_comite(resolucion_comite);
			tratamiento.setFecha_registro(this.timestamp);
			tratamientos.add(tratamiento);
		}
		
		return tratamientos;
	
	}
	
	private Antecedentes InsertAntecedentes(String[] pacienteData, Session session) throws ParseException{
		
		SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
		Antecedentes antecedente = new Antecedentes();
		antecedente.setFecha_registro(this.timestamp);
//		java.util.Date fecha_intencion_date= fecha.parse(pacienteData[39]);
//		java.sql.Date fecha_intencion = new java.sql.Date(fecha_intencion_date.getTime());
//		antecedente.setFecha_primera_consulta(fecha_intencion);
		
//		Query q = session.createQuery("from Motivo_presentacion motivo where motivo.codigo= '" + pacienteData[46].split("-")[1] + "'");
//		Motivo_presentacion motivo_presentacion = (Motivo_presentacion) q.uniqueResult();
//		
//		antecedente.setMotivo_presentacion(motivo_presentacion);
		
		return antecedente;
	}
	
	private Localizacion InsertLocalizacion(String[] pacienteData, Session session) throws ParseException {
		
		Localizacion localizacion = new Localizacion();
    	
		Query q = session.createQuery("from Region region where region.codigo= '" + pacienteData[17].split("_")[1] + "'");
		Region region = (Region) q.uniqueResult();
		q = session.createQuery("from Provincia prov where prov.codigo= '" + pacienteData[18].split("_")[1] + "'");
		Provincia provincia = (Provincia) q.uniqueResult();
		q = session.createQuery("from Comuna com where com.codigo= '" + pacienteData[19].split("-")[1] + "'");
		Comuna comuna = (Comuna) q.uniqueResult();
    	localizacion.setRegion(region);
    	localizacion.setProvincia(provincia);
    	localizacion.setComuna(comuna);
    	localizacion.setDireccion(pacienteData[20]);
    	session.close();
    	return localizacion;
	}
	
	public void InsertData(InputStreamReader csvFile) throws ParseException {

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        Date date = new Date();
        this.timestamp = new Timestamp(date.getTime());
        
        br = new BufferedReader(csvFile);
        int count = 0;
        PacienteValidator validador = new PacienteValidator();
        String[] read_line;
        String[] pacienteData = new String[60]; 
        List<String[]> allPacienteData = new ArrayList<String[]>();
        Boolean error = false;
        SessionFactory sessionFactory = HibernateUtility.getSessionFactory(); 
        
        try {
        	while (((line = br.readLine()) != null && validador.getMessages().getValidation())) {   
        		if(count>3){
        			read_line = line.split(cvsSplitBy);
        			if(read_line.length>33){
				        for(int i=0;i<pacienteData.length;i++){
				        	if(i<read_line.length){
				        		pacienteData[i] = read_line[i];	
				        		
				        	}else{
				        		pacienteData[i] = "";
				        	}			        	
				        }
			        
			        	validador.pacienteValidation(pacienteData);
			        	if(validador.getMessages().getValidation()){
			        		String[] copy = new String[60];
			        		for(int i=0;i<pacienteData.length;i++){
			        			copy[i] = pacienteData[i];
			        		}
			        		allPacienteData.add(copy);
			        	}    	
			        }
        		}
        		count++;
        	}
        	if(validador.getMessages().getValidation()){
        			
	        	for(String[] data: allPacienteData){
	        		Session session = sessionFactory.openSession();
		         	System.out.println("Insertando paciente");
		         	Integer rut = Integer.parseInt(data[3]);
		         	Transaction tx1 = session.beginTransaction();
		    		Query q = session.createQuery("from Paciente p where p.rut= '" + rut + "'");
		            Paciente paciente_req = (Paciente) q.uniqueResult();
		        	Paciente paciente = InsertPaciente(data, paciente_req, session);
		        	
		        	Diagnostico diagnostico = this.InsertDiagnostico(data, paciente);
		        	Set<Tratamiento> tratamientos = this.InsertTratamiento(data, diagnostico, session);
		        	Antecedentes antecedente = this.InsertAntecedentes(data, session);
		        	Localizacion localizacion = this.InsertLocalizacion(data, session);    
		        	session.save(localizacion);
		        	paciente.setLocalizacion(localizacion);
		        	session.save(paciente);
		        	session.save(antecedente);
		        	diagnostico.setAntecedentes(antecedente);
		        	session.save(diagnostico);
		        	
		        	for(Tratamiento tratamiento: tratamientos){
		        		session.save(tratamiento.getMedico());
		        		session.save(tratamiento);
		        	}
		        	
		        	tx1.commit();  	    
	        	}
        	}	
		} catch (HibernateException e) {
			error = true;
			e.printStackTrace();
		} catch (IOException e) {
			error = true;
			e.printStackTrace();
		} 
        
        if(error){
        	ValidationMessages message = new ValidationMessages();
        	message.setTitle("Hubo un problema al procesar su archivo");
        	message.setValidation(false);
        	this.messages = message;
        }else{
        	this.messages = validador.getMessages();
        }
	}
}
