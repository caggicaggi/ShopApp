package shop_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import shop_app.controller.impl.LoginControllerImpl;
import shop_app.dto.UtenteDTO;
import shop_app.service.UtenteService;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController implements LoginControllerImpl {
	
	@Autowired
	UtenteService utenteService;
	
	@Override
	public ResponseEntity<String> login(@RequestBody UtenteDTO utenteDTO) throws Exception {
		String error = utenteService.login(utenteDTO);

		if(error.equals("Incorrect email"))
			return new ResponseEntity<>("Incorrect email",HttpStatus.BAD_REQUEST);
		
		if(error.equals("Incorrect password"))
			return new ResponseEntity<>("Incorrect password",HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
