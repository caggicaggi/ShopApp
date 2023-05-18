package shop_app.controller.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import shop_app.dto.RegistrazioneDTO;



public interface RegistrazioneControllerImpl {
	
	@PostMapping(value="/signup")
    public ResponseEntity<String> signup(@RequestBody RegistrazioneDTO registrazioneDTO)throws Exception;
}
