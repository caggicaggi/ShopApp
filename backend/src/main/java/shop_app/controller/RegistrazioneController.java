package shop_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import shop_app.controller.impl.RegistrazioneControllerImpl;
import shop_app.dto.RegistrazioneDTO;
import shop_app.service.UtenteService;

@RestController
public class RegistrazioneController implements RegistrazioneControllerImpl {
	
	@Autowired
	UtenteService utenteService;
	
	@Override
	public ResponseEntity<String> signup(RegistrazioneDTO registrazioneDTO) throws Exception {
		
		registrazioneDTO = utenteService.cryptoPassword(registrazioneDTO);
		
		int checkInsert= utenteService.signup(registrazioneDTO);
		
		if (checkInsert == 0) {
			return new ResponseEntity<>("An error was occured, user not register",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Correctly registered user",HttpStatus.OK);
		}
	

}
