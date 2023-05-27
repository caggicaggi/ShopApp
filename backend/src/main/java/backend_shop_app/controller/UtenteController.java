package backend_shop_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import backend_shop_app.dto.AuthRequestDTO;
import backend_shop_app.dto.UserDTO;

public interface UtenteController {
	
	public ResponseEntity<String> generateToken( @RequestBody AuthRequestDTO authRequest) throws Exception ;
	
	public ResponseEntity<String> signup( @RequestBody UserDTO userDTO) throws Exception ;
}
