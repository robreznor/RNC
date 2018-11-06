package cl.minsal.api.service;

import cl.minsal.api.object.Diagnostico;
import cl.minsal.api.object.PacienteData;
import cl.minsal.api.object.PacienteSearch;
import cl.minsal.api.object.PrimeraConsulta;
import cl.minsal.api.object.diagnostic;
import cl.minsal.api.object.documentoDeOrigen;
import cl.minsal.api.object.institucionOrigen;
import cl.minsal.api.util.HibernateUtility;
import cl.minsal.api.model.Paciente;

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
		
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
        Session session = sessionFactory.openSession(); 
        Query q = session.createQuery("from Paciente p where p.id= '" + id + "'");
        Paciente paciente = (Paciente) q.uniqueResult();
        
        PrimeraConsulta primeraConsulta = new PrimeraConsulta();
        primeraConsulta.setHeader("Primera Consulta");
        primeraConsulta.setTipo("primeraConsulta");
        primeraConsulta.setFecha("");
        
        institucionOrigen institucion = new institucionOrigen();
//        institucion.setEstablecimiento(establecimiento);
//        primeraConsulta.setInstitucionOrigen();
//        primeraConsulta.setDocumentoDeOrigen(documentoDeOrigen);
//        primeraConsulta.setInstitucionOrigen(institucionOrigen);
        
        return primeraConsulta;     
	}
	
	public static Set<Diagnostico> getDiagnosticos(Integer id){
		
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		Paciente paciente = new Paciente();
		
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
        
        return diagnosticosResp;
        
	}
	public static PacienteData getPacienteData(Integer id){
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    Query q = session.createQuery("from Paciente p where p.id= '" + id + "'");
	    Paciente paciente = (Paciente) q.uniqueResult();
	    PacienteData paciente_data = new PacienteData();
	    paciente_data.setNombre(paciente.getNombre() + ' ' + paciente.getApellido1() + ' ' + paciente.getApellido2());
	    paciente_data.setRut(Integer.toString(paciente.getRut()) + '-' + paciente.getDverificador());
	    paciente_data.setSexo(paciente.getGenero().getNombre());
	    paciente_data.setFechaNacimiento(dateFormat.format(paciente.getFecha_nacimiento()));
	    //paciente_data.setFechaPrimeraConsulta(dateFormat.format(paciente.getAntecedentes().getFecha_primera_consulta()));
	    
	    return paciente_data;
	}
	public static Paciente getPaciente(Integer id){
		
		Paciente paciente = new Paciente();
		SessionFactory sessionFactory = null;
		try{
			sessionFactory = HibernateUtility.getSessionFactory();
	        Session session = sessionFactory.openSession();
	        Query q = session.createQuery("from Paciente where p.id= '" + id + "'");
	        paciente = (Paciente) q.uniqueResult();
	        sessionFactory.close();	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(sessionFactory!=null){
				sessionFactory.close();
			}
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
	        sessionFactory.close();
	        return pacientes;
		}catch(HibernateException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
