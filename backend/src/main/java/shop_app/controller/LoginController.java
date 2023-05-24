package shop_app.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import shop_app.dto.ResponseLoginDTO;
import shop_app.dto.UtenteDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/utente")
public interface LoginController {
	
	@PostMapping(value = "/signin")
    public ResponseEntity<ResponseLoginDTO> singin(@RequestBody UtenteDTO utenteDTO) throws Exception;
}
