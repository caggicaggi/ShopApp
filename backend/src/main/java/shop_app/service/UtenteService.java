package shop_app.service;


import java.util.HashMap;
import shop_app.dto.RegistrazioneDTO;
import shop_app.dto.UtenteDTO;


public interface UtenteService {
	

    public HashMap<String,String> login( UtenteDTO utenteDT0) throws Exception;

    public int signup( RegistrazioneDTO registrazioneDTO) throws Exception;
    
    public RegistrazioneDTO cryptoPassword( RegistrazioneDTO registrazioneDTO) throws Exception;

}
