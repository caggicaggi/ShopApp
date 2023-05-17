package shop_app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop_app.dto.RegistrazioneDTO;
import shop_app.dto.UtenteDTO;
import shop_app.repository.UtenteRepository;
import shop_app.service.impl.UtenteService;

@Service
public class UtenteServiceImpl implements UtenteService {
	
	@Autowired
	UtenteRepository utenteRepository;

	@Override
	public UtenteDTO login(UtenteDTO utenteDT0) throws Exception {
		return utenteRepository.login(utenteDT0.getEmail(),utenteDT0.getPassword());
	}

	@Override
	public int signup(RegistrazioneDTO registrazioneDTO) throws Exception {
		 return utenteRepository.signup(registrazioneDTO);
	}
	
}
