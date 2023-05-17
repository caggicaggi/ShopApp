package shop_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import shop_app.controller.impl.LoginControllerImpl;
import shop_app.dto.UtenteDTO;
import shop_app.service.impl.UtenteService;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController implements LoginControllerImpl {
	
	@Autowired
	UtenteService utenteService;
	
	@Override
	public ResponseEntity<HttpStatus.Series> login(@RequestBody UtenteDTO utenteDTO) throws Exception {
		System.out.println("PASSWORD: "+utenteDTO.getPassword());
		System.out.println("USERNAME: "+utenteDTO.getEmail());
		
		UtenteDTO utente = utenteService.login(utenteDTO);

		if(utente.getEmail() == null || utente.getPassword() == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
