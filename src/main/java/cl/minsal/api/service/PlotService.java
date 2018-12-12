package cl.minsal.api.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cl.minsal.api.model.Diagnostico;
import cl.minsal.api.model.Paciente;
import cl.minsal.api.object.BarPlot;
import cl.minsal.api.util.HibernateUtility;
import cl.minsal.api.util.Utils;

public class PlotService {
	
	public static BarPlot cancerByType(){
		
		try{
			List<Paciente> pacientes = Utils.getPacientes();
			BarPlot barPlot = getcancerByTypeData(pacientes);
			return barPlot;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		

	}
	
	private static BarPlot getcancerByTypeData(List<Paciente> pacientes){
		BarPlot barPlot = new BarPlot();
		String label = "";
		for(Paciente paciente: pacientes){
			Set<Diagnostico> diagnosticos = (Set<Diagnostico>) paciente.getDiagnostico();
			for(Diagnostico diagnostico: diagnosticos){
				label = diagnostico.getDiagnostico_cie10();
				Date date = diagnostico.getFecha_diagnostico();	
				if(date!=null){
					Date olderDate = Utils.olderDate(date, Utils.stringToDate(barPlot.getDateStart(), "dd-MM-yyyy"));
					Date newerDate = Utils.newerDate(date, Utils.stringToDate(barPlot.getDateEnd(), "dd-MM-yyyy"));
					barPlot.setDateStart(Utils.dateToString(olderDate, "dd-MM-yyyy"));
					barPlot.setDateEnd(Utils.dateToString(newerDate, "dd-MM-yyyy"));
					setBarPlot(barPlot, label);
				}
			}
		}
		return barPlot;	
	}
	
	public static BarPlot cancerByAge(){
		
		try{	
			List<Paciente> pacientes = Utils.getPacientes();
			BarPlot barPlot = getCancerByAgeData(pacientes);
			return barPlot;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	
	}
	
	public static BarPlot getCancerByAgeData(List<Paciente> pacientes){
		List<String> labels = Arrays.asList("Rango edad [0-10]", "Rango [11-20]", "Rango [21-30]", "Rango [31-40]", "Rango [41-50]", "Rango [51-60]", "Rango [61-70]"
				, "Rango [71-80]", "Rango [81-90]", "Rango [91-100]", "Rango [Mayor a 100]");
		Integer age = 0;
		BarPlot barPlot = new BarPlot();
		for(Paciente paciente: pacientes){
			Date birthDate = paciente.getFecha_nacimiento();
			Date currentDate = new Date();
			age = Utils.calculateAge(birthDate, currentDate);
			String label = labels.get(getAgeRange(age));
			setBarPlot(barPlot, label);
		}
		barPlot = orderAgeRange(barPlot, labels);
		
		return barPlot;
	}
	
	public static BarPlot orderAgeRange(BarPlot barPlot, List<String> labels){
		BarPlot copyBarPlot = new BarPlot();
		for(int i=0;i<labels.size();i++){
			Integer index = barPlot.getLabels().indexOf(labels.get(i));
			if(index!=-1){
				copyBarPlot.addLabel(barPlot.getLabels().get(index));
				copyBarPlot.addVal(barPlot.getVal().get(index));
			}
		}
		return copyBarPlot;
	}
	
	public static Integer getAgeRange(Integer edad){
		
		if(edad<=10){
			return 0;
		}else if(edad<=20){
			return 1;
		}else if(edad<=30){
			return 2;
		}else if(edad<=40){
			return 3;
		}else if(edad<=50){
			return 4;
		}else if(edad<=60){
			return 5;
		}else if(edad<=70){
			return 6;
		}else if(edad<=80){
			return 7;
		}else if(edad<=90){
			return 8;
		}else if(edad<=100){
			return 9;
		}else{
			return 10;
		}		
		
	}
	
	public static void setBarPlot(BarPlot barPlot, String label){
		Integer index = -1;
		if(barPlot.getLabels()!=null){
			index = barPlot.getLabels().indexOf(label);
		}
		if(index!=-1){
			Integer currentValue = barPlot.getVal().get(index);
			barPlot.getVal().set(index, currentValue+1);
		}else{
			barPlot.addLabel(label);
			barPlot.addVal(1);
		}
	}
	
	
}
