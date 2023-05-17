package shop_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import shop_app.controller.impl.RegistrazioneControllerImpl;
import shop_app.dto.RegistrazioneDTO;
import shop_app.service.impl.UtenteService;

@RestController
public class RegistrazioneController implements RegistrazioneControllerImpl {
	
	@Autowired
	UtenteService utenteService;
	
	@Override
	public ResponseEntity<HttpStatus.Series> signup(RegistrazioneDTO registrazioneDTO) throws Exception {
		
		//criptare password
		
		int checkInsert= utenteService.signup(registrazioneDTO);
		
		if (checkInsert == 0) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
		}
	

}
