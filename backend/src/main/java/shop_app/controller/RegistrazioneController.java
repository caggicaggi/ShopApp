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
		
		if (registrazioneDTO.getName() == null || registrazioneDTO.getSurname() == null ||
				registrazioneDTO.getAddress()==null || registrazioneDTO.getPhoneNumber() ==null
				|| registrazioneDTO.getEmail() == null || registrazioneDTO.getPassword() == null ) {
			return new ResponseEntity<>("An error was occured, some fields are missing. ",HttpStatus.BAD_REQUEST);
		}
		
		int checkInsert= utenteService.signup(registrazioneDTO);
		
		if (checkInsert == 0) {
			return new ResponseEntity<>("An error was occured, user not register. ",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (checkInsert == 2) {
			return new ResponseEntity<>("An error was occured, email already used. ",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Correctly registered user. ",HttpStatus.OK);
		}
	

}
