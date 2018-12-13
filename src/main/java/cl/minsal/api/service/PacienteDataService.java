package cl.minsal.api.service;

import cl.minsal.api.object.Diagnostico;
import cl.minsal.api.object.PacienteData;
import cl.minsal.api.object.PacienteSearch;
import cl.minsal.api.object.PrimeraConsulta;
import cl.minsal.api.object.diagnostic;
import cl.minsal.api.object.documentoDeOrigen;
import cl.minsal.api.object.institucionOrigen;
import cl.minsal.api.object.tratamiento;
import cl.minsal.api.object.tratamientoIndicado;
import cl.minsal.api.util.HibernateUtility;
import cl.minsal.api.model.Paciente;
import cl.minsal.api.model.Tratamiento;
import cl.minsal.api.object.ResolucionTratamiento;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;




public class PacienteDataService {
	
	public static PrimeraConsulta getPrimeraConsulta(Integer id){
		
		try{
			SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
			Session session = sessionFactory.openSession(); 
	        Query q = session.createQuery("from Paciente p where p.id= '" + id + "'");
	        Paciente paciente = (Paciente) q.uniqueResult();
	        
	        PrimeraConsulta primeraConsulta = new PrimeraConsulta();
	        primeraConsulta.setHeader("Primera Consulta");
	        primeraConsulta.setTipo("primeraConsulta");
	        primeraConsulta.setFecha("");
	        
	        institucionOrigen institucion = new institucionOrigen();
//	        institucion.setEstablecimiento(establecimiento);
//	        primeraConsulta.setInstitucionOrigen();
//	        primeraConsulta.setDocumentoDeOrigen(documentoDeOrigen);
//	        primeraConsulta.setInstitucionOrigen(institucionOrigen);
	        session.close();
	        return primeraConsulta;   
		}catch(Exception e){
			e.printStackTrace();
			 return null;
		}
       
	}
	
	public static Set<Diagnostico> getDiagnosticos(Integer id){
		
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Paciente paciente = new Paciente();
		
		try{
			Session session = sessionFactory.openSession(); 
	        Query q = session.createQuery("from Paciente p where p.id= '" + id + "'");
	        paciente = (Paciente) q.uniqueResult();
			
	        Set<Diagnostico> diagnosticosResp = new HashSet<Diagnostico>(); 
	        Set<cl.minsal.api.model.Diagnostico> diagnosticos = paciente.getDiagnostico();
	        for(cl.minsal.api.model.Diagnostico diagnos: diagnosticos){
	    	
		    	Diagnostico diagnostico = new Diagnostico();
		    	diagnostic diagnostic = new diagnostic();
		    	documentoDeOrigen documentoDeOrigen = new documentoDeOrigen();
		    	
		    	String diagnosticoCIE10 = diagnos.getDiagnostico_cie10();
		    	DateFormat dateFormatDiag = new SimpleDateFormat("dd-MM-yyyy");
		    	DateFormat dateFormatReg = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		    	String fecha = dateFormatDiag.format(diagnos.getFecha_diagnostico());
		    	String diagnosticoCommite = diagnos.getDiagnostico_comite();
		    	String estadio = diagnos.getEstadio();
		    	String TNM = diagnos.getTnm();
		
		    	Timestamp ts=new Timestamp(System.currentTimeMillis()); 
		    	String fechaRegistro = dateFormatReg.format(ts);
		
		    	documentoDeOrigen.setEstablecimientoCreadorDelDocumento("");
		    	documentoDeOrigen.setServicioSalud("");
		    	diagnostico.setDocumentoDeOrigen(documentoDeOrigen);
		    	diagnostic.setDiagnosticoCIE10(diagnosticoCIE10);
		    	diagnostic.setDiagnosticoCommite(diagnosticoCommite);
		    	diagnostic.setEstadio(estadio);
		    	diagnostic.setTNM(TNM);
		    	
		    	diagnostico.setDiagnostico(diagnostic);
		    	diagnostico.setDocumentoDeOrigen(documentoDeOrigen);
		    	diagnostico.setFecha(fecha);
		    	diagnostico.setFechaRegistro(fechaRegistro);
		    	diagnostico.setHeader("Diagnostico");
		    	institucionOrigen institucionOrigen = new institucionOrigen();
		    	
		    	diagnostico.setInstitucionOrigen(institucionOrigen);
		    	diagnostico.setTipo("diagnostico");
		    	diagnosticosResp.add(diagnostico);  
	        }
	        session.close();
	        return diagnosticosResp;
		}catch(Exception e){
        	e.printStackTrace();
        }
		
		return null;
	}
	
	public static Set<ResolucionTratamiento> getTratamientos(Integer id){
		
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Paciente paciente = new Paciente();
		
		try{
			Session session = sessionFactory.openSession(); 
	        Query q = session.createQuery("from Paciente p where p.id= '" + id + "'");
	        paciente = (Paciente) q.uniqueResult();
			
	        Set<ResolucionTratamiento> tratamientosResp = new HashSet<ResolucionTratamiento>(); 
	        Set<cl.minsal.api.model.Diagnostico> diagnosticos = paciente.getDiagnostico();
	        for(cl.minsal.api.model.Diagnostico diagnos: diagnosticos){
	        	
	        	Set<Tratamiento> tratamientos = diagnos.getTratamiento();
	        	
	        	for(Tratamiento tratamiento: tratamientos){
	        		
	        		ResolucionTratamiento tratamientoResp = new ResolucionTratamiento();
	        		
	        		documentoDeOrigen documentoDeOrigen = new documentoDeOrigen();
	        		institucionOrigen institucionOrigen = new institucionOrigen();
	        		tratamiento tratam = new tratamiento();
	        		tratamientoIndicado tratamientoIndicado = new tratamientoIndicado();
	        		String establecimientoCreadorDelDocumento = "";
	        		String servicioSalud = "";
	        		String establecimiento = "";
	        		String servicioDeSalud = "";
	        		
	        		if(tratamiento.getMedico().getEstablecimiento()!=null){
	        			establecimientoCreadorDelDocumento = tratamiento.getMedico().getEstablecimiento().getNombre_establecimiento();
		        		servicioSalud = tratamiento.getMedico().getEstablecimiento().getServicio_salud().getNombre();
		        		establecimiento = tratamiento.getMedico().getEstablecimiento().getNombre_establecimiento();
				    	servicioDeSalud = tratamiento.getMedico().getEstablecimiento().getServicio_salud().getNombre();
	        		}
	        		
	        		String resolucionComite = "";
	        		
	        		if(tratamiento.getResolucion_comite()!=null){
	        			resolucionComite = tratamiento.getResolucion_comite().getDescripcion();
	        		}
	        		
	        		DateFormat dateFormatTrat = new SimpleDateFormat("dd-MM-yyyy"); 				
			    	DateFormat dateFormatReg = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			    	String fecha = dateFormatTrat.format(diagnos.getFecha_comite());
			    	String fechaRegistro = dateFormatReg.format(tratamiento.getFecha_registro());
			    	String header = "Resolución Tratamiento";
			    	String tipo = "tratamiento";
			    	String[] nombre = getNombreMedico(tratamiento.getMedico().getNombre_medico(), tratamiento.getMedico().getApellido1(), tratamiento.getMedico().getApellido2());
			    	String descripcionTratamiento = tratamiento.getDescripcion_tratamiento();
			    	String participantesComite = "";
			    	String nombreMedicoDerivador = nombre[0] + nombre[1] + nombre[2];
			    	String responsablesTratamiento = "";
			    	String intencion = tratamiento.getIntencion_tratamiento().getNombre();
			    	String estado = "";
			    	String fechaIntencion = dateFormatTrat.format(tratamiento.getFecha_intencion());
			    	String tipoTratInd = "";
			    	tratamientoIndicado.setEstado(estado);
			    	tratamientoIndicado.setFechaIntencion(fechaIntencion);
			    	tratamientoIndicado.setIntencion(intencion);
			    	tratamientoIndicado.setTipo(tipoTratInd);
			    	institucionOrigen.setEstablecimiento(establecimiento);
			    	institucionOrigen.setNombreMedicoDerivador(nombreMedicoDerivador);
			    	institucionOrigen.setServicioDeSalud(servicioDeSalud);
	        		documentoDeOrigen.setEstablecimientoCreadorDelDocumento(establecimientoCreadorDelDocumento);
	        		documentoDeOrigen.setServicioSalud(servicioSalud);
	        		tratam.setDescripcionTratamiento(descripcionTratamiento);
	        		tratam.setParticipantesComite(participantesComite);
	        		tratam.setResolucionComite(resolucionComite);
	        		tratam.setResponsablesTratamiento(responsablesTratamiento);
	        		tratamientoResp.setDocumentoDeOrigen(documentoDeOrigen);
	        		
	        		tratamientoResp.setInstitucionOrigen(institucionOrigen);
	        		tratamientoResp.setFecha(fecha);
	        		tratamientoResp.setFechaRegistro(fechaRegistro);
	        		tratamientoResp.setHeader(header);
	        		tratamientoResp.setTipo(tipo);
	        		tratamientoResp.setTratamiento(tratam);
	        		tratamientoResp.setTratamientoIndicado(tratamientoIndicado);
	        		tratamientosResp.add(tratamientoResp);
	        	}
	        }
	        session.close();
	        return tratamientosResp;
        }catch(Exception e){
        	e.printStackTrace();
        }
		
		return null;
	}
	
	private static String[] getNombreMedico(String nombre, String apellido1, String apellido2){
		String[] response = {"","",""};
		if(nombre!=null){
			response[0] = nombre;
		}
		if(apellido1!=null){
			response[1] = " "+apellido1;
		}
		if(apellido2!=null){
			response[2] = " "+apellido2;
		}
		return response;
	}
	
	public static PacienteData getPacienteData(Integer id){
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		try{	
		    Session session = sessionFactory.openSession();
		    Query q = session.createQuery("from Paciente p where p.id= '" + id + "'");
		    Paciente paciente = (Paciente) q.uniqueResult();
		    PacienteData paciente_data = new PacienteData();
		    paciente_data.setNombre(paciente.getNombre() + ' ' + paciente.getApellido1() + ' ' + paciente.getApellido2());
		    paciente_data.setRut(Integer.toString(paciente.getRut()) + '-' + paciente.getDverificador());
		    paciente_data.setSexo(paciente.getGenero().getNombre());
		    paciente_data.setFechaNacimiento(dateFormat.format(paciente.getFecha_nacimiento()));
		    //paciente_data.setFechaPrimeraConsulta(dateFormat.format(paciente.getAntecedentes().getFecha_primera_consulta()));
		    session.close();
		    return paciente_data;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static Paciente getPaciente(Integer id){
		
		Paciente paciente = new Paciente();

		try{
			SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	        Session session = sessionFactory.openSession();
	        Query q = session.createQuery("from Paciente where p.id= '" + id + "'");
	        paciente = (Paciente) q.uniqueResult();	
	        session.close();
		}catch(Exception e){
			e.printStackTrace();
		}	
		return paciente;	
	}
	
	public static Set<PacienteSearch> getPacientes(){
		
		try{
			SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	        Session session = sessionFactory.openSession();

	        Query q = session.createQuery("select id, rut, nombre, apellido1, apellido2 from Paciente");
	        Set<PacienteSearch> pacientes = new HashSet<PacienteSearch>();

	        for(int i =0; i< q.list().size(); i++){
	        	Object[] obj = (Object[]) q.list().get(i);
	        	System.out.print(obj[0]);
	        	PacienteSearch paciente = new PacienteSearch((Integer)obj[0], (Integer)obj[1], (String)obj[2], (String)obj[3], (String)obj[4]);
	        	pacientes.add(paciente);
	        }   
	        session.close();
	        return pacientes;
		}catch(HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
