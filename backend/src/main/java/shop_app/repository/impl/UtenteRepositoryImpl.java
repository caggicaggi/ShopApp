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
	
	@Autowired 
	public UtenteRepositoryImpl(DataSource dataSource) {
	    setDataSource(dataSource);
	}
	
	private static final UtenteMapper rowMapper = new UtenteMapper();
	private static final String IDUTENTE="idUtente";
	private static final String EMAIL="email";
	private static final String PASSWORD="password";
	private static final String NAME="name";
	private static final String SURNAME="surname";
	private static final String ADDRESS="address";
	private static final String PHONENUMBER="phoneNumber";

	private final String QUERY_LOGIN_EMAIL = "SELECT email FROM utente WHERE email = :email ;";  
	
	private final String QUERY_LOGIN_PASSWORD = "SELECT password FROM utente WHERE password = :password ;"; 
	
	private final String INSERT_UTENTE = "INSERT INTO utente "
			+ " (email,password,name,surname,address,phoneNumber) "
			+ " VALUES (:email, :password, :name, :surname, :address, :phoneNumber) ";

	
    private MapSqlParameterSource getMapSqlParameterSourceUtente(UtenteDTO utenteDTO, int id) {

		MapSqlParameterSource args = new MapSqlParameterSource();
		
		args.addValue(IDUTENTE, id);
		args.addValue(EMAIL, utenteDTO.getEmail());
		args.addValue(PASSWORD, utenteDTO.getPassword());
		return args;

	}
    private MapSqlParameterSource getMapSqlParameterSourceRegistrazioeneDTO(RegistrazioneDTO registrazioneDTO) {

		MapSqlParameterSource args = new MapSqlParameterSource();
		
		args.addValue(EMAIL, registrazioneDTO.getEmail());
		args.addValue(PASSWORD, registrazioneDTO.getPassword());
		args.addValue(NAME, registrazioneDTO.getName());
		args.addValue(SURNAME, registrazioneDTO.getSurname());
		args.addValue(ADDRESS, registrazioneDTO.getAddress());
		args.addValue(PHONENUMBER, registrazioneDTO.getPhoneNumber());
		return args;

	}
	
    
	@Override
	public int signup(RegistrazioneDTO registrazioneDTO) throws Exception {
		System.out.println("INIZIO METODO SIGNUP");
		try {

				MapSqlParameterSource args = getMapSqlParameterSourceRegistrazioeneDTO(registrazioneDTO);
				int checkInsert = getNamedParameterJdbcTemplate().update(INSERT_UTENTE, args);
				System.out.println("FINE METODO SIGNUP");		
				return checkInsert;
		}catch(Exception e) {
			throw e;
		}
	}

	@Override
	public UtenteDTO login(String email, String password) throws Exception  {
		System.out.println("INIZIO METODO LOGIN");
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
			System.out.println("FINE METODO LOGIN");
			return utenteDTO;
		}
	}
