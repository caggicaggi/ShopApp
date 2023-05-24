package shop_app.service;


import shop_app.dto.RegistrazioneDTO;
import shop_app.dto.UtenteDTO;


public interface UtenteService {
	

    public UtenteDTO login( UtenteDTO utenteDT0) throws Exception;

    public int signup( RegistrazioneDTO registrazioneDTO) throws Exception;
    
    public RegistrazioneDTO cryptoPassword( RegistrazioneDTO registrazioneDTO) throws Exception;

	public int getIdUtente(String surname, String email, String phoneNumber);

}
