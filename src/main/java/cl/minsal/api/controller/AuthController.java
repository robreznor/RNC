package cl.minsal.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
import cl.minsal.api.service.UserService;
import cl.minsal.api.transfer.JwtUserDto;
import cl.minsal.api.util.JwtUtil;

@RestController
public class AuthController {
	
	@RequestMapping(value="/login", method=RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Object> login(@RequestBody UserLoginRequest userReq, UserLoginResponse userRes ){
		
		String username = userReq.getUsername();
		String password = userReq.getPassword();
		Usuario user = UserService.correctPassword(username, password);
		if(user!=null){
			JwtUtil jwtutil = new JwtUtil();
			JwtUserDto userDto = new JwtUserDto();
			Long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
			userDto.setId(id);
			userDto.setRole(user.getRole());
			userDto.setUsername(user.getUsuario());
			String token = jwtutil.generateToken(userDto);
			userRes.setUsername(user.getUsuario());
			userRes.setId(1);
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
		ObjectMapper mapper = new ObjectMapper();
        ObjectNode message = mapper.createObjectNode();
		if(userExist){	
	        message.put("message", "El nombre de usuario ingresado ya existe");
			return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
		}
		boolean insertSuccess = InserUserService.inserUser(user);
		if(insertSuccess){
			return new ResponseEntity<Object>(HttpStatus.OK);
		}else{
			message.put("message", "Hubo un error al intentar registrar el usuario, vuelva a intentarlo");
			return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
		}
		
        
	}
	
}
