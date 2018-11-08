package cl.minsal.api.controller;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import cl.minsal.api.model.Paciente;
import cl.minsal.api.service.InsertPacienteService;
import cl.minsal.api.service.PacienteDataService;
import cl.minsal.api.util.FileValidator;

import java.security.Principal;
import java.text.ParseException;
import java.util.Set;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;

import cl.minsal.api.object.*;

@RestController
public class ApiRestController {
	
    @Autowired
    FileValidator fileValidator;
	
	@RequestMapping(value="/api/paciente_search", method=RequestMethod.GET)
    public Set<PacienteSearch> pacienteSearch() {
    	Set<PacienteSearch> pacientes = PacienteDataService.getPacientes();
		return pacientes;
    }

	@RequestMapping(value="/api/paciente/{id}", method=RequestMethod.GET)
	private Paciente search(@PathVariable Integer id){
		Paciente paciente = PacienteDataService.getPaciente(id);
		return paciente;
	}
	
	@RequestMapping(value="/api/paciente_data/{id}", method=RequestMethod.GET)
	private PacienteData paciente_data(@PathVariable Integer id){
		PacienteData paciente_data = PacienteDataService.getPacienteData(id);
		return paciente_data;
	}
	
//	@RequestMapping(value="/api/paciente_primera_consulta/{id}", method=RequestMethod.GET)
//	private Paciente paciente_primera_consulta(@PathVariable Integer id){
//		
//		SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
//        Session session = sessionFactory.openSession(); 
//        Query q = session.createQuery("from Paciente p where p.id= '" + id + "'");
//        List<Paciente> pacientes = q.list();
//        
//		return pacientes.get(0);
//	}
	
	@RequestMapping(value = "/api/file_upload", method = RequestMethod.POST)
    public ValidationMessages singleFileUpload(@Valid FileBucket fileBucket,
            BindingResult result, ValidationMessages validadorResponse) throws IOException, ParseException {
		
        if (result.hasErrors()) {
        	validadorResponse.setValidation(false);
        	validadorResponse.addMessage("");
        	validadorResponse.setTitle("");
            return validadorResponse;
        } else {
            try {
            	ValidationMessages fileValidation = FileValidator.validate(fileBucket);
            	if(fileValidation.getValidation()){
            		InputStreamReader file = new InputStreamReader(fileBucket.getFile().getInputStream(), "UTF8");
                	InsertPacienteService insertar = new InsertPacienteService();
                	System.out.println("Insertando datos");
                    insertar.InsertData(file);
                    validadorResponse = insertar.getMessages();
            	}else {
            		validadorResponse = fileValidation;
            	}
            	
            } catch (IOException e) {
                e.printStackTrace();
                validadorResponse.setValidation(false);
            	validadorResponse.setTitle("Hubo un error al procesar su archivo");
                return validadorResponse; 
            } 
            return validadorResponse;
        }
    }
	
	@RequestMapping(value="/api/diagnosticos/{id}", method=RequestMethod.GET)
    public Set<Diagnostico> DiagnosticosPaciente(@PathVariable Integer id) {
		Set<Diagnostico> diagnosticos = PacienteDataService.getDiagnosticos(id);
		return diagnosticos;
	}
	
	@RequestMapping(value="/api/tratamientos/{id}", method=RequestMethod.GET)
    public Set<ResolucionTratamiento> TratamientosPaciente(@PathVariable Integer id) {
		Set<ResolucionTratamiento> tratamientos = PacienteDataService.getTratamientos(id);
		return tratamientos;
	}
	
//    @RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
//    public ModelAndView homePage(ModelMap model) {
//    	ModelAndView mav = new ModelAndView("welcome");
//        mav.addObject("greeting", "Hi, Welcome to mysite");
//        return mav;
//    }
// 
//    @RequestMapping(value = "/admin", method = RequestMethod.GET)
//    public ModelAndView adminPage(ModelMap model) {
//        ModelAndView mav = new ModelAndView("admin");
//        mav.addObject("user", getPrincipal());
//        return mav;
//    }
//     
//    @RequestMapping(value = "/db", method = RequestMethod.GET)
//    public ModelAndView dbaPage(ModelMap model) {
//    	ModelAndView mav = new ModelAndView("dba");
//        mav.addObject("user", getPrincipal()); 
//        return mav;
//    }
// 
//    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
//    public ModelAndView accessDeniedPage(ModelMap model) {
//    	ModelAndView mav = new ModelAndView("accessDenied");
//        mav.addObject("user", getPrincipal());
//        return mav;
//    }
// 
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public ModelAndView loginPage() {
//    	ModelAndView mav = new ModelAndView("login");
//        mav.addObject("user", getPrincipal());
//        return mav;
//    }
// 
//    @RequestMapping(value="/logout", method = RequestMethod.GET)
//    public ModelAndView logoutPage (HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null){    
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//        }
//        return new ModelAndView("redirect:/login?logout");
//    }
// 
//    private String getPrincipal(){
//        String userName = null;
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
// 
//        if (principal instanceof UserDetails) {
//            userName = ((UserDetails)principal).getUsername();
//        } else {
//            userName = principal.toString();
//        }
//        return userName;
//    }
    
    @RequestMapping("/users/authenticate")
    public boolean login(@RequestBody User user) {
        return
          user.getUsername().equals("user") && user.getPassword().equals("password");
    }
     
    @RequestMapping("/user")
    public Object user(HttpServletRequest request) {
        final String authToken = request.getHeader("Authorization")
          .substring("Basic".length()).trim();
//        return () ->  new String(Base64.getDecoder()
//          .decode(authToken)).split(":")[0];

        return (new Object(){
        	
        	@Override
        	public String toString() {
        		try {
					String retorno = new String(DatatypeConverter.parseBase64Binary(authToken), "UTF-8").split(":")[0];
					return retorno;
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		return null;
        	}	
        });
                
    }
	
}
