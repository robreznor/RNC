package cl.minsal.api.service;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cl.minsal.api.model.Diagnostico;
import cl.minsal.api.model.Paciente;
import cl.minsal.api.object.BarPlot;
import cl.minsal.api.util.HibernateUtility;

public class PlotService {
	
	public static BarPlot cancerByType(){
		
		try{
			SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
			Session session = sessionFactory.openSession();
			Query query = session.createQuery("From Paciente");
			List<Paciente> pacientes = query.list();
			BarPlot barPlot = getBarPlotData(pacientes);
			return barPlot;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		

	}
	
	private static BarPlot getBarPlotData(List<Paciente> pacientes){
		BarPlot barPlot = new BarPlot();
		Integer index = -1;
		Integer currentValue = 0;
		String label = "";
		for(Paciente paciente: pacientes){
			Set<Diagnostico> diagnosticos = (Set<Diagnostico>) paciente.getDiagnostico();
			for(Diagnostico diagnostico: diagnosticos){
				label = diagnostico.getDiagnostico_cie10();
				if(barPlot.getLabels()!=null){
					index = barPlot.getLabels().indexOf(label);
				}
				if(index!=-1){
					currentValue = barPlot.getVal().get(index);
					barPlot.getVal().set(index, currentValue+1);
				}else{
					barPlot.addLabel(label);
					barPlot.addVal(1);
				}	
			}
		}
		return barPlot;
		
	}
}
