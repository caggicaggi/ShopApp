package shop_app.repository;

import shop_app.dto.RegistrazioneDTO;
import shop_app.dto.UtenteDTO;


public interface UtenteRepository {

	
    public UtenteDTO login( String email, String password) throws Exception;
    
    public int signup( RegistrazioneDTO registrazioneDTO) throws Exception;
    
    public String getSalt( String email) throws Exception;

	public int getIdUtente(String surname, String email, String phoneNumber);

}
