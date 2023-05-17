package shop_app.repository;

import org.springframework.web.bind.annotation.RequestBody;

import shop_app.dto.RegistrazioneDTO;
import shop_app.dto.UtenteDTO;


public interface UtenteRepository {

	
    public UtenteDTO login(@RequestBody String email, String password) throws Exception;
    

    public int signup(@RequestBody RegistrazioneDTO registrazioneDTO) throws Exception;
}
