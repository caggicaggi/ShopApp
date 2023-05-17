package shop_app.service.impl;

import org.springframework.web.bind.annotation.RequestBody;

import shop_app.dto.RegistrazioneDTO;
import shop_app.dto.UtenteDTO;


public interface UtenteService {
	

    public UtenteDTO login(@RequestBody UtenteDTO utenteDT0) throws Exception;
    

    public int signup(@RequestBody RegistrazioneDTO registrazioneDTO) throws Exception;
}
