package shop_app.service.impl;


import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;

import shop_app.dto.RegistrazioneDTO;
import shop_app.dto.UtenteDTO;
import shop_app.repository.UtenteRepository;
import shop_app.service.UtenteService;
@Service
public class UtenteServiceImpl implements UtenteService{
	
	@Value("${password.secret}")
	private String secret;
	
	@Autowired
	UtenteRepository utenteRepository;

	@Override
	public UtenteDTO login(UtenteDTO utenteDTO) throws Exception {
		//recupero salt
		String salt = utenteRepository.getSalt(utenteDTO.getEmail());
		//genero password criptata per confronto
		String password = generatePassword(utenteDTO.getPassword()+salt+secret);
		//recupero password criptata in db
		utenteDTO= utenteRepository.login(utenteDTO.getEmail(),password);
		
		if (utenteDTO.getPassword() == null || utenteDTO.getEmail() == null) {
			return null;
		}
		//controllo password criptata in db e password inserita criptata
		if (utenteDTO.getPassword().equals(password) ) {
			return utenteDTO;
		}
		return null;
	}

	@Override
	public int signup(RegistrazioneDTO registrazioneDTO) throws Exception {
		cryptoPassword(registrazioneDTO);
		 return utenteRepository.signup(registrazioneDTO);
	}

	@Override
	public RegistrazioneDTO cryptoPassword(RegistrazioneDTO registrazioneDTO) throws Exception {
		//creo salt randomico
		String salt = getAlphaNumericString(9);
		//creo password criptata
		String password = generatePassword(registrazioneDTO.getPassword()+salt+secret);
		registrazioneDTO.setSalt(salt);
		registrazioneDTO.setPassword(password);

		return registrazioneDTO;
	}
	
	static String getAlphaNumericString(int n)
	 {
	  //stringa generica	
	  String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
	  StringBuilder sb = new StringBuilder(n);
	  //ciclo per creare stringa (salt)
	  for (int i = 0; i < n; i++) {
	   int index = (int)(AlphaNumericString.length()
	      * Math.random());
	   sb.append(AlphaNumericString
	      .charAt(index));
	  }
	  return sb.toString();
	 }
	
	static String generatePassword(String passwordAndSecretAndSalt) throws NoSuchAlgorithmException {
		//ritorno password criptata con sha256
		return  Hashing.sha256().hashString(passwordAndSecretAndSalt, StandardCharsets.UTF_8) .toString();
	}

	@Override
	public int getIdUtente(String surname, String email, String phoneNumber) {
		 return utenteRepository.getIdUtente(surname,email,phoneNumber);
	}
}
