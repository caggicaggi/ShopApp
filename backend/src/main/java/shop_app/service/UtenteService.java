package shop_app.service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import shop_app.dto.RegistrazioneDTO;
import shop_app.dto.UtenteDTO;
import shop_app.service.impl.UtenteServiceImpl;

@Service
public class UtenteService implements UtenteServiceImpl {

	@Override
	public ResponseEntity<?> login(UtenteDTO utenteDT0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> signup(RegistrazioneDTO registrazioneDTO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
