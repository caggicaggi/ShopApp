package shop_app.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import shop_app.dto.RegistrazioneDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/utente")
public interface RegistrazioneController {
	
	@PostMapping(value="/signup")
    public ResponseEntity<String> signup(@RequestBody RegistrazioneDTO registrazioneDTO)throws Exception;
}
