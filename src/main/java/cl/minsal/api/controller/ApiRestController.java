package cl.minsal.api.controller;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import cl.minsal.api.model.Paciente;
import cl.minsal.api.model.PacienteSearch;
import cl.minsal.api.object.FileBucket;
import cl.minsal.api.object.PacienteData;
import cl.minsal.api.object.ValidationMessages;
import cl.minsal.api.service.InsertPacienteService;
import cl.minsal.api.util.FileValidator;

import java.text.DateFormat;
import java.text.ParseException;


import java.text.SimpleDateFormat;
import java.util.List;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
public class ApiRestController {
	
    @Autowired
    FileValidator fileValidator;

    @InitBinder("fileBucket")
    protected void initBinderFileBucket(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }
    
    @ModelAttribute
    public void setVaryResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type");
    }    

//    @RequestMapping(value="/", method=RequestMethod.GET)
//	public ModelAndView index(){   
//		return new ModelAndView("redirect:/subir_archivo");
//	}
    
//    @RequestMapping(value="/subir_archivo", method=RequestMethod.GET)
//	public ModelAndView uploadFile(){
//		return new ModelAndView("upload_file");
//	}
	
	@RequestMapping(value="/api/paciente_search", method=RequestMethod.GET)
    public List<Paciente> pacienteSearch() {
		
		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("from Paciente");
        List<Paciente> pacientes = q.list();
        sessionFactory.close();
        return pacientes;
    }
//	
//	@RequestMapping(value="/paciente", method=RequestMethod.GET)
//    public List<Paciente> pacientes() {
//		
//		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        Query q = session.createQuery("from Paciente");
//        List<Paciente> pacientes = q.list();
//        sessionFactory.close();
//        return pacientes;
//    }
//	
	@RequestMapping(value="/api/paciente/{id}", method=RequestMethod.GET)
	private Paciente search(@PathVariable Integer id){
		
		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("from Paciente p where p.id= '" + id + "'");
        Paciente paciente = (Paciente) q.uniqueResult();
        sessionFactory.close();
		return paciente;
	}
	
	@RequestMapping(value="/api/paciente_data/{id}", method=RequestMethod.GET)
	private PacienteData paciente_data(@PathVariable Integer id){
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("from Paciente p where p.id= '" + id + "'");
        Paciente paciente = (Paciente) q.uniqueResult();
        PacienteData paciente_data = new PacienteData();
        paciente_data.setNombre(paciente.getNombre() + ' ' + paciente.getApellido1() + ' ' + paciente.getApellido2());
        paciente_data.setRut(Integer.toString(paciente.getRut()) + '-' + paciente.getDverificador());
        paciente_data.setSexo(paciente.getGenero());
        paciente_data.setFechaNacimiento(dateFormat.format(paciente.getFecha_nacimiento()));
        //paciente_data.setFechaPrimeraConsulta(dateFormat.format(paciente.getAntecedentes().getFecha_primera_consulta()));
		return paciente_data;
	}
//	
//	@RequestMapping(value="/paciente_primera_consulta/{id}", method=RequestMethod.GET)
//	private Paciente paciente_primera_consulta(@PathVariable Integer id){
//		
//		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
//        Session session = sessionFactory.openSession(); 
//        Query q = session.createQuery("from Paciente p where p.id= '" + id + "'");
//        List<Paciente> pacientes = q.list();
//        
//		return pacientes.get(0);
//	}
//	
//	@RequestMapping(value="/paciente_diagnostico/{id}", method=RequestMethod.GET)
//	private Paciente paciente_diagnostico(@PathVariable Integer id){
//		
//		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        Query q = session.createQuery("from Paciente p where p.id= '" + id + "'");
//        List<Paciente> pacientes = q.list();
//        sessionFactory.close();
//		return pacientes.get(0);
//	}
//	
//	@RequestMapping(value="/tratamiento/{id}", method=RequestMethod.GET)
//	private Paciente paciente_tratamiento(@PathVariable Integer id){
//		
//		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        Query q = session.createQuery("from Paciente p where p.id= '" + id + "'");
//        List<Paciente> pacientes = q.list();
//        sessionFactory.close();
//		return pacientes.get(0);
//	}
	
//	@RequestMapping(value="/diagnostico/{id}", method=RequestMethod.GET)
//	private Diagnostico paciente_tratamiento(@PathVariable Integer id){
//		
//		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        Query q = session.createQuery("from Diagnostico p where p.id= '" + id + "'");
//        List<Diagnostico> diagnostico = q.list();
//        sessionFactory.close();
//		return diagnostico.get(0);
//	}
	
	@RequestMapping(value = "/api/file_upload", method = RequestMethod.POST)
    public ValidationMessages singleFileUpload(@Valid FileBucket fileBucket,
            BindingResult result, ValidationMessages validadorResponse) throws IOException, ParseException {
		
        if (result.hasErrors()) {
            //model.addAttribute("error", "The id selected is out of Range, please select another id within range");
            return validadorResponse;
        } else {
            try {           	
            	InputStreamReader file = new InputStreamReader(fileBucket.getFile().getInputStream(), "UTF8");
            	InsertPacienteService insertar = new InsertPacienteService();
            	System.out.println("Insertando datos");
                insertar.InsertData(file);
                validadorResponse = insertar.getMessages();
            } catch (IOException e) {
                e.printStackTrace();
            } 
            return validadorResponse;
        }
    }
}
