package shop_app.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import shop_app.dto.RegistrazioneDTO;
import shop_app.dto.UtenteDTO;


public interface UtenteServiceImpl {
	

    @PostMapping(value="/login")
    public ResponseEntity<?> login(@RequestBody UtenteDTO utenteDT0) throws Exception;
    

    @PostMapping(value="/signup")
    public ResponseEntity<?> signup(@RequestBody RegistrazioneDTO registrazioneDTO) throws Exception;
}
