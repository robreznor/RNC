package cl.minsal.api.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import cl.minsal.api.model.Paciente;
import cl.minsal.api.object.PrimeraConsulta;
public class PacientePrimeraConsultaService {
	@Test
	public static PrimeraConsulta getPrimeraConsulta(Integer id){
		
		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession(); 
        Query q = session.createQuery("from Paciente p where p.id= '" + id + "'");
        Paciente paciente = (Paciente) q.uniqueResult();
        
        PrimeraConsulta primeraConsulta = new PrimeraConsulta();
        primeraConsulta.setHeader("Primera Consulta");
        primeraConsulta.setTipo("primeraConsulta");
        primeraConsulta
        
        
	}
}
