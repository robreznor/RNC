package cl.minsal.api.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.minsal.api.object.UserLoginResponse;

@RestController
public class AuthController {
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String test(){
		return "test";
	}
	
    @RequestMapping(value="/users/authenticate", method=RequestMethod.POST, consumes = "application/json")
    public @ResponseBody UserLoginResponse login(@RequestBody UserLoginResponse userReq, UserLoginResponse userRes) {
//    	CustomAuthenticationProvider auth = new CustomAuthenticationProvider();
//    	auth.authenticate(userReq);
    	return null;
//    	if(userReq.getUsername().equals("rob") && userReq.getPassword().equals("teenage")){
//    		userRes.setUsername("rob");
//    		userRes.setId(1);
//    		userRes.setFirstName("rob");
//    		userRes.setLastName("maclean");
//    		userRes.setToken(UUID.randomUUID().toString());
//    		
//            return userRes;
//    	}else{
//    		return null;
//    	}	
    }
     
    @RequestMapping("/api/user")
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
    
    @RequestMapping(value = "/getuser", method = RequestMethod.GET) 
    public String getUser() {
                
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if (!(authentication instanceof AnonymousAuthenticationToken)) {
    		authentication.getAuthorities();
    		authentication.getCredentials();
    		authentication.getDetails();
    		authentication.getPrincipal();
    	    String currentUserName = authentication.getName();
    	    return currentUserName;
    	}
        return "welcome";

    }

}
