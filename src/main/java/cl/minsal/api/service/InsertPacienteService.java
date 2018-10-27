package cl.minsal.api.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.ui.ModelMap;

import cl.minsal.api.model.Antecedentes;
import cl.minsal.api.model.Diagnostico;
import cl.minsal.api.model.Localizacion;
import cl.minsal.api.model.Medico;
import cl.minsal.api.model.Paciente;
import cl.minsal.api.model.Tratamiento;
import cl.minsal.api.util.PacienteValidator;

public class InsertPacienteService {
	
	private ModelMap model;
	private Timestamp timestamp;
	private Boolean validation;
	
	public ModelMap getModel() {
		return model;
	}

	public Boolean getValidation() {
		return validation;
	}

	public Paciente InsertPaciente(String[] pacienteData, Paciente paciente) throws ParseException{

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
    		if(!pacienteData[6].equals("")) paciente.setGenero(Integer.parseInt(pacienteData[6].split("-")[1]));
    		if(!pacienteData[8].equals("")) paciente.setNacionalidad(Integer.parseInt(pacienteData[8].split("-")[1]));
    		if(!pacienteData[9].equals("")) paciente.setPueblo_originario(Integer.parseInt(pacienteData[9].split("-")[1]));
    		if(!pacienteData[10].equals("")) paciente.setEstado_conyugal(Integer.parseInt(pacienteData[10].split("-")[1]));
    		if(!pacienteData[11].equals("")) paciente.setReligion(Integer.parseInt(pacienteData[11].split("-")[1]));
    		if(!pacienteData[12].equals("")) paciente.setNivel_instruccion(Integer.parseInt(pacienteData[12].split("-")[1]));
    		if(!pacienteData[13].equals("")) paciente.setOcupacion(Integer.parseInt(pacienteData[13].split("-")[1]));
    		if(!pacienteData[14].equals("")) paciente.setActividad_economica(Integer.parseInt(pacienteData[14].split("-")[1]));
    		if(!pacienteData[15].equals("")) paciente.setPrevision(Integer.parseInt(pacienteData[15].split("-")[1]));
    		if(!pacienteData[16].equals("")) paciente.setBeneficiario_fonasa(Integer.parseInt(pacienteData[16].split("-")[1]));
    		paciente.setFecha_registro(this.timestamp);
        }
    	return paciente;
	}
	
	public Diagnostico InsertDiagnostico(String[] pacienteData, Paciente paciente) throws ParseException{
		
		System.out.println("insertando diagnostico");
		Diagnostico diagnostico = new Diagnostico();
		SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date fecha_diagnostico_date= fecha.parse(pacienteData[22]);
    	java.util.Date fecha_comite_date= fecha.parse(pacienteData[23]);
    	java.sql.Date fecha_diagnostico = new java.sql.Date(fecha_diagnostico_date.getTime()); 
    	java.sql.Date fecha_comite = new java.sql.Date(fecha_comite_date.getTime());
    		
		diagnostico.setTipo_comite(Integer.parseInt(pacienteData[21].split("_")[1]));
		diagnostico.setFecha_diagnostico(fecha_diagnostico);
		diagnostico.setFecha_comite(fecha_comite);
		diagnostico.setDiagnostico_comite(pacienteData[24]);
		diagnostico.setDiagnostico_cie10(pacienteData[25]);
		diagnostico.setEcog(Integer.parseInt(pacienteData[26].split("_")[1]));
		diagnostico.setEstadio(pacienteData[34]+pacienteData[35]);
		diagnostico.setPaciente(paciente);
		diagnostico.setTnm(pacienteData[28]+pacienteData[29]+pacienteData[30]+pacienteData[31]+pacienteData[32]+pacienteData[33]);
		diagnostico.setFecha_registro(this.timestamp);
		return diagnostico;
	}
	
	public Set<Tratamiento> InsertTratamiento(String[] pacienteData, Diagnostico diagnostico) throws ParseException{
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
						
			tratamiento.setTipo_tratamiento(Integer.parseInt(pacienteData[37].split("-")[1]));
			tratamiento.setIntencion_tratamiento(Integer.parseInt(pacienteData[38].split("-")[1]));
			tratamiento.setFecha_intencion(fecha_intencion);
			tratamiento.setDescripcion_tratamiento(pacienteData[41]);
			tratamiento.setMedico(medico);
			tratamiento.setDiagnostico(diagnostico);
			tratamiento.setResolucion_comite(Integer.parseInt(pacienteData[40].split("-")[1]));
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
						
			tratamiento.setTipo_tratamiento(Integer.parseInt(pacienteData[43].split("-")[1]));
			tratamiento.setIntencion_tratamiento(Integer.parseInt(pacienteData[44].split("-")[1]));
			tratamiento.setFecha_intencion(fecha_intencion);
			tratamiento.setDescripcion_tratamiento(pacienteData[54]);
			tratamiento.setMedico(medico);
			tratamiento.setDiagnostico(diagnostico);
			tratamiento.setResolucion_comite(Integer.parseInt(pacienteData[46].split("-")[1]));
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
						
			tratamiento.setTipo_tratamiento(Integer.parseInt(pacienteData[49].split("-")[1]));
			tratamiento.setIntencion_tratamiento(Integer.parseInt(pacienteData[50].split("-")[1]));
			tratamiento.setFecha_intencion(fecha_intencion);
			tratamiento.setDescripcion_tratamiento(pacienteData[53]);
			tratamiento.setMedico(medico);
			tratamiento.setDiagnostico(diagnostico);
			tratamiento.setResolucion_comite(Integer.parseInt(pacienteData[52].split("-")[1]));
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
						
			tratamiento.setTipo_tratamiento(Integer.parseInt(pacienteData[55].split("-")[1]));
			tratamiento.setIntencion_tratamiento(Integer.parseInt(pacienteData[56].split("-")[1]));
			tratamiento.setFecha_intencion(fecha_intencion);

			tratamiento.setDescripcion_tratamiento(pacienteData[59]);

			
			tratamiento.setMedico(medico);
			tratamiento.setDiagnostico(diagnostico);
			tratamiento.setResolucion_comite(Integer.parseInt(pacienteData[58].split("-")[1]));
			tratamiento.setFecha_registro(this.timestamp);
			tratamientos.add(tratamiento);
		}
		
		return tratamientos;
	
	}
	
	public Antecedentes InsertAntecedentes(String[] pacienteData) throws ParseException{
		
		SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
		Antecedentes antecedente = new Antecedentes();
		antecedente.setFecha_registro(this.timestamp);
//		java.util.Date fecha_intencion_date= fecha.parse(pacienteData[39]);
//		java.sql.Date fecha_intencion = new java.sql.Date(fecha_intencion_date.getTime());
//		antecedente.setFecha_primera_consulta(fecha_intencion);
		
//		antecedente.setMotivo_presentacion(Integer.parseInt(pacienteData[46].split("-")[1]));
		
		return antecedente;
	}
	
	public Localizacion InsertLocalizacion(String[] pacienteData) throws ParseException {
		
		Localizacion localizacion = new Localizacion();
    	
    	localizacion.setRegion(Integer.parseInt(pacienteData[17].split("_")[1]));
    	localizacion.setProvincia(Integer.parseInt(pacienteData[18].split("_")[1]));
    	localizacion.setComuna(Integer.parseInt(pacienteData[19].split("-")[1]));
    	localizacion.setDireccion(pacienteData[20]);
    	
    	return localizacion;
	}
	public void InsertData(InputStreamReader csvFile) throws ParseException {

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        this.validation = true;
        Date date = new Date();
        this.timestamp = new Timestamp(date.getTime());
        
        br = new BufferedReader(csvFile);
        int count = 0;
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        PacienteValidator validador = new PacienteValidator();
        String[] read_line;
        String[] pacienteData = new String[59]; 
        try {
			while (((line = br.readLine()) != null && this.validation)) {   
					
				if(count>3){        

			        read_line = line.split(cvsSplitBy);
			        Transaction tx1 = session.beginTransaction();
			        for(int i=0;i<pacienteData.length;i++){
			        	if(i<read_line.length){
			        		pacienteData[i] = read_line[i];			        
			        	}else{
			        		pacienteData[i] = "";
			        	}			        	
			        }
			        validador.pacienteValidation(pacienteData);
			        this.validation = validador.getValidation();
			        if(this.validation){ 
			         	System.out.println("Insertando paciente");
			         	Integer rut = Integer.parseInt(pacienteData[3]);
			    		Query q = session.createQuery("from Paciente p where p.rut= '" + rut + "'");
			            Paciente paciente_req = (Paciente) q.uniqueResult();
			        	Paciente paciente = InsertPaciente(pacienteData, paciente_req);
			        	Diagnostico diagnostico = this.InsertDiagnostico(pacienteData, paciente);
			        	Set<Tratamiento> tratamientos = this.InsertTratamiento(pacienteData, diagnostico);
			        	Antecedentes antecedente = this.InsertAntecedentes(pacienteData);
			        	Localizacion localizacion = this.InsertLocalizacion(pacienteData);
			        	session.save(localizacion);
			        	paciente.setLocalizacion(localizacion);
			        	session.save(paciente);
			        	session.save(antecedente);
			        	diagnostico.setAntecedentes(antecedente);
			        	session.save(diagnostico);
			        	
			        	for(Tratamiento tratamiento: tratamientos){
			        		System.out.print(tratamiento.getMedico());
			        		session.save(tratamiento.getMedico());
			        		session.save(tratamiento);
			        	}
		        	tx1.commit();
			        }
				}
				count++;               
			}
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sessionFactory.close();
			this.model = validador.getModel();
		}     
	}
}
