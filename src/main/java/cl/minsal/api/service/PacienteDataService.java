package cl.minsal.api.service;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import org.hibernate.cfg.AnnotationConfiguration;

import cl.minsal.api.model.Paciente;
import cl.minsal.api.object.Diagnostico;
import cl.minsal.api.object.PrimeraConsulta;
import cl.minsal.api.object.diagnostic;
import cl.minsal.api.object.documentoDeOrigen;
import cl.minsal.api.object.institucionOrigen;
import cl.minsal.api.util.HibernateUtility;
public class PacienteDataService {
	
	public static PrimeraConsulta getPrimeraConsulta(Integer id){
		
		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
		//SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
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
    	//String institucionOrigen = diagnos
    	//documentoDeOrigen.setEstablecimientoCreadorDelDocumento();
    	//documentoDeOrigen.setServicioSalud(servicioSalud);
    	//diagnostic.setDocumentoDeOrigen(documentoDeOrigen);
    	diagnostic.setDiagnosticoCIE10(diagnosticoCIE10);
    	diagnostic.setDiagnosticoCommite(diagnosticoCommite);
    	diagnostic.setEstadio(estadio);
    	diagnostic.setTNM(TNM);
    	
    	diagnostico.setDiagnostico(diagnostic);
    	diagnostico.setDocumentoDeOrigen(documentoDeOrigen);
    	diagnostico.setFecha(fecha);
    	diagnostico.setFechaRegistro(fechaRegistro);
    	diagnostico.setHeader("Diagnostico");
    	//diagnostico.setInstitucionOrigen(institucionOrigen);
    	diagnostico.setTipo("diagnostico");
    	diagnosticosResp.add(diagnostico);     	
        	
        }
        
        return diagnosticosResp;
        
	}
}
