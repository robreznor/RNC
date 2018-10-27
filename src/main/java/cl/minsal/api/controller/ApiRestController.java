package cl.minsal.api.controller;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import java.text.ParseException;

import cl.minsal.api.object.FileBucket;
import cl.minsal.api.service.InsertPacienteService;
import cl.minsal.api.util.FileValidator;
import cl.minsal.api.util.PacienteValidator;
import cl.minsal.api.model.Diagnostico;
import cl.minsal.api.model.Paciente;
import cl.minsal.api.object.PacienteSearch;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.validation.Valid;
import javax.validation.Validator;

@RestController
public class ApiRestController {
	
    @Autowired
    FileValidator fileValidator;

    @InitBinder("fileBucket")
    protected void initBinderFileBucket(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }

    @RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(){
		return new ModelAndView("redirect:/subir_archivo");
	}
    
    @RequestMapping(value="/subir_archivo", method=RequestMethod.GET)
	public ModelAndView uploadFile(){
		return new ModelAndView("upload_file");
	}
	
//	@RequestMapping(value="/paciente_search", method=RequestMethod.GET)
//    public List<PacienteSearch> pacienteSearch() {
//		
//		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        Query q = session.createQuery("from PacienteSearch");
//        List<PacienteSearch> pacientes = q.list();
//        sessionFactory.close();
//        return pacientes;
//    }
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
//	@RequestMapping(value="/paciente/{id}", method=RequestMethod.GET)
//	private Paciente search(@PathVariable Integer id){
//		
//		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        Query q = session.createQuery("from Paciente p where p.id= '" + id + "'");
//        List<Paciente> pacientes = q.list();
//        sessionFactory.close();
//        if(pacientes.size()>0){
//        	return pacientes.get(0);
//        }
//		return null;
//	}
	
//	@RequestMapping(value="/paciente_data/{id}", method=RequestMethod.GET)
//	private PacienteData paciente_data(@PathVariable Integer id){
//		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
//		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        Query q = session.createQuery("from Paciente p where p.id= '" + id + "'");
//        List<Paciente> pacientes = q.list();
//        Paciente paciente = pacientes.get(0);
//        PacienteData paciente_data = new PacienteData();
//        paciente_data.setNombre(paciente.getNombre() + ' ' + paciente.getApellido1() + ' ' + paciente.getApellido2());
//        paciente_data.setRut(Integer.toString(paciente.getRut()) + '-' + paciente.getDigito_verificador());
//        paciente_data.setSexo(paciente.getGenero());
//        paciente_data.setFechaNacimiento(dateFormat.format(paciente.getFecha_nacimiento()));
//        paciente_data.setFechaPrimeraConsulta(dateFormat.format(paciente.getAntecedentes().getFecha_primera_consulta()));
//		return paciente_data;
//	}
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
	
	@RequestMapping(value = "/file_upload", method = RequestMethod.POST)
    public ModelAndView singleFileUpload(@Valid FileBucket fileBucket,
            BindingResult result, ModelMap model) throws IOException, ParseException {
		
        if (result.hasErrors()) {
            model.addAttribute("error", "The id selected is out of Range, please select another id within range");
            return new ModelAndView("redirect:/subir_archivo", model);
        } else {
            try {
            	System.out.println(fileBucket.getFile().getOriginalFilename());
            	InputStreamReader file = new InputStreamReader(fileBucket.getFile().getInputStream(), "UTF8");
            	InsertPacienteService insertar = new InsertPacienteService();
            	System.out.println("Insertando datos");
                insertar.InsertData(file);
                model = insertar.getModel();
            } catch (IOException e) {
                e.printStackTrace();
            } 
            System.out.println(model);
            return new ModelAndView("redirect:/subir_archivo", model);
        }
    }
}
