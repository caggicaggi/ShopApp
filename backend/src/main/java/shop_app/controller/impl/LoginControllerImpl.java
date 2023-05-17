package shop_app.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import shop_app.dto.UtenteDTO;


public interface LoginControllerImpl {
	
	@PostMapping(value = "/login")
    public ResponseEntity<HttpStatus.Series> login(@RequestBody UtenteDTO utenteDTO) throws Exception;
}
