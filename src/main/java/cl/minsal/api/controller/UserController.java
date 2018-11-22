package cl.minsal.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import cl.minsal.api.model.Usuario;
import cl.minsal.api.object.UserLoginRequest;
import cl.minsal.api.object.UserLoginResponse;
import cl.minsal.api.service.UserService;

@RestController
public class UserController {
	
	@RequestMapping(value="/login", method=RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Object> login(@RequestBody UserLoginRequest userReq){
		
		String username = userReq.getUsername();
		String password = userReq.getPassword();
		Usuario user = UserService.correctPassword(username, password);
		if(user!=null){
			UserLoginResponse userRes = UserService.fillUserResponse(user);
			return new ResponseEntity<Object>(userRes, HttpStatus.OK );
		}

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode message = mapper.createObjectNode();
        message.put("message", "La combinación usuario y contraseña no es correcta");
		return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value="/register", method=RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Object> register(@RequestBody Usuario user){
		
		boolean userExist = UserService.userExist(user.getUsuario());
		ObjectMapper mapper = new ObjectMapper();
        ObjectNode message = mapper.createObjectNode();
		if(userExist){	
	        message.put("message", "El nombre de usuario ingresado ya existe");
			return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
		}
		boolean insertSuccess = UserService.inserUser(user);
		if(insertSuccess){
			return new ResponseEntity<Object>(HttpStatus.OK);
		}else{
			message.put("message", "Hubo un error al intentar registrar el usuario, vuelva a intentarlo");
			return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
		}
    
	}
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public List<Usuario> getUsers(){
		return UserService.getUsers();
	}
	
	@RequestMapping(value="/users/{id}", method=RequestMethod.GET)
	public Usuario getUser(@PathVariable Integer id){
		return UserService.getUser(id);
	}
	
	@RequestMapping(value="/users/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteUser(@PathVariable Integer id){
		if(UserService.deleteUser(id)){
			return new ResponseEntity<Object>(HttpStatus.OK);
		}else{
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
