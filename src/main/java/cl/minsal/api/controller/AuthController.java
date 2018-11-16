package cl.minsal.api.controller;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import cl.minsal.api.model.Usuario;
import cl.minsal.api.object.UserLoginRequest;
import cl.minsal.api.object.UserLoginResponse;
import cl.minsal.api.service.InserUserService;
import cl.minsal.api.transfer.JwtUserDto;
import cl.minsal.api.util.JwtUtil;

@RestController
public class AuthController {
	
	@RequestMapping(value="/login", method=RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Object> login(@RequestBody UserLoginRequest userReq, UserLoginResponse userRes ){
		
		String username = userReq.getUsername();
		String password = userReq.getPassword();
		if(username.equals("rob") && password.equals("teenage")){
			JwtUtil jwtutil = new JwtUtil();
			JwtUserDto user = new JwtUserDto();
			Long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
			user.setId(id);
			user.setRole("user");
			userRes.setUsername(username);
			String token = jwtutil.generateToken(user);
			user.setUsername(username);
			userRes.setId(1);
			userRes.setFirstName("rob");
			userRes.setLastName("reznor");
			userRes.setToken(token);
			return new ResponseEntity<Object>(userRes, HttpStatus.OK );
		}

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode message = mapper.createObjectNode();
        message.put("message", "La combinación usuario y contraseña no es correcta");
		return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/test", method=RequestMethod.POST, consumes="application/json")
	public void test(@RequestBody UserLoginRequest userReq){
		System.out.print(userReq.getUsername());
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Object> register(@RequestBody Usuario user){
		System.out.print(user.getPassword());

		boolean userExist = InserUserService.userExist(user.getUsuario());
		if(userExist){
			
		}
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode message = mapper.createObjectNode();
        message.put("message", "La combinación usuario y contraseña no es correcta");
		return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
	}
	
}
