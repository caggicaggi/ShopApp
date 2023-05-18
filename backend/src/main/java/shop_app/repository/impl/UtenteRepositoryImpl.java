package shop_app.repository.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import shop_app.dto.RegistrazioneDTO;
import shop_app.dto.UtenteDTO;
import shop_app.repository.UtenteMapper;
import shop_app.repository.UtenteRepository;
import shop_app.utils.GenericDao;

@Repository
public class UtenteRepositoryImpl extends GenericDao implements UtenteRepository{
	
	public UtenteRepositoryImpl(DataSource dataSource) {
	    setDataSource(dataSource);
	}
	
	private static final UtenteMapper rowMapper = new UtenteMapper();
	
	//private static final String IDUTENTE="idUtente";
	private static final String EMAIL="email";
	private static final String PASSWORD="password";
	private static final String NAME="name";
	private static final String SURNAME="surname";
	private static final String ADDRESS="address";
	private static final String PHONENUMBER="phoneNumber";
	private static final String SALT="salt";

	private final String QUERY_LOGIN_EMAIL = "SELECT email FROM utente WHERE email = :email ;";  
	
	private final String QUERY_LOGIN_PASSWORD = "SELECT password FROM utente WHERE password = :password ;"; 
	
	private final String QUERY_GET_SALT = "SELECT salt FROM utente WHERE email = :email ;"; 
	
	private final String QUERY_INSERT_UTENTE = "INSERT INTO utente "
			+ " (email,password,name,surname,address,phoneNumber,salt) "
			+ " VALUES (:email, :password, :name, :surname, :address, :phoneNumber, :salt) ;";

	
    /*private MapSqlParameterSource getMapSqlParameterSourceUtente(UtenteDTO utenteDTO, int id) {

		MapSqlParameterSource args = new MapSqlParameterSource();
		
		args.addValue(IDUTENTE, id);
		args.addValue(EMAIL, utenteDTO.getEmail());
		args.addValue(PASSWORD, utenteDTO.getPassword());
		return args;

	}
    */
    private MapSqlParameterSource getMapSqlParameterSourceRegistrazioeneDTO(RegistrazioneDTO registrazioneDTO) {

		MapSqlParameterSource args = new MapSqlParameterSource();
		
		args.addValue(EMAIL, registrazioneDTO.getEmail());
		args.addValue(PASSWORD, registrazioneDTO.getPassword());
		args.addValue(NAME, registrazioneDTO.getName());
		args.addValue(SURNAME, registrazioneDTO.getSurname());
		args.addValue(ADDRESS, registrazioneDTO.getAddress());
		args.addValue(PHONENUMBER, registrazioneDTO.getPhoneNumber());
		args.addValue(SALT, registrazioneDTO.getSalt());
		return args;

	}
	
    
	@Override
	public int signup(RegistrazioneDTO registrazioneDTO) throws Exception {
		System.out.println("START METODO SIGNUP");
		int checkInsert=0;
		try {
				System.out.println(registrazioneDTO.getSalt());
				MapSqlParameterSource args = getMapSqlParameterSourceRegistrazioeneDTO(registrazioneDTO);
				checkInsert = getNamedParameterJdbcTemplate().update(QUERY_INSERT_UTENTE, args);
			
		}catch(Exception e) {
			System.out.println("ERRORE CHIAMATA REGISTRAZIONE -- SIGNUP --- " + e);
		}
		System.out.println("END METODO SIGNUP");		
		return checkInsert;
	}

	@Override
	public UtenteDTO login(String email, String password) throws Exception  {
		System.out.println("START ELABORATION METHOD LOGIN");
		UtenteDTO utenteDTO = new UtenteDTO();
			try {
				MapSqlParameterSource args = new MapSqlParameterSource();
				args.addValue(EMAIL, email);
				args.addValue(PASSWORD, password);
				
				utenteDTO.setEmail(getNamedParameterJdbcTemplate().queryForObject(QUERY_LOGIN_EMAIL, args,(rs,rowNum)-> rs.getString(1)));
			} catch (Exception e) {
				System.out.println("ERRORE CHIAMATA RECUPERO EMAIL -- LOGIN --- " + e);
			}
			try {
				MapSqlParameterSource args = new MapSqlParameterSource();
				args.addValue(PASSWORD, password);
				utenteDTO.setPassword(getNamedParameterJdbcTemplate().queryForObject(QUERY_LOGIN_PASSWORD, args,(rs,rowNum)-> rs.getString(1)));

			} catch (Exception e) {
				System.out.println("ERRORE CHIAMATA RECUPERO PASSWORD -- LOGIN --- " + e);
			}
			System.out.println("END ELABORATION METHOD  LOGIN");
			return utenteDTO;
		}
	
	
	@Override
	public String getSalt(String email) throws Exception {
		System.out.println("START ELABORATION METHOD  GETSALT");
		String salt = null;
		try {
				MapSqlParameterSource args = new MapSqlParameterSource();
				args.addValue(EMAIL, email);
				salt = getNamedParameterJdbcTemplate().queryForObject(QUERY_GET_SALT, args,(rs,rowNum)-> rs.getString(1));
		}catch(Exception e) {
			System.out.println("ERRORE CHIAMATA RECUPERO SALT -- GETSALT --- " + e);
		}
		System.out.println("END ELABORATION METHOD  GESTSALT");		
		return salt;
	}
	}
