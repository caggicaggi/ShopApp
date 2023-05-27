package backend_shop_app.service;

import backend_shop_app.dto.AuthRequestDTO;
import backend_shop_app.dto.UserDTO;
import backend_shop_app.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Value("${password.secret}")
	private String secret;
	
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	//controllo esistenza dell'email
    	UserDTO user = userRepository.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
    
    public void signup(UserDTO userDTO)  {
    	//chiamo il repository per fare l'insert
    	userRepository.save(userDTO);
    }

	public UserDTO cryptoPassword(UserDTO userDTO) throws Exception {
		try {
		String salt = new String();
		UserDTO user = new UserDTO();
		//controllo se devo generare o recuperare il SALT
		if(!userDTO.getSalt().equals("Load")) {
			salt = getAlphaNumericString(9);
			String password = generatePassword(userDTO.getPassword()+salt+secret);
			userDTO.setSalt(salt);
			userDTO.setPassword(password);
			return userDTO;
		} else {
	    	user = userRepository.findByEmail(userDTO.getEmail());
	    	salt = user.getSalt();
	    	userDTO.setIdutente(user.getIdutente());
		}
		//genero password criptata per confronto
		String password = generatePassword(userDTO.getPassword()+salt+secret);
		userDTO.setSalt(salt);
		userDTO.setPassword(password);;
		System.out.println("VERIFICO PASSWORD");
		System.out.println(user.getPassword().equals(password));
		if(user.getPassword().equals(password)) {
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(user.getEmail(), password)
	            );
	        } else{
	        	userDTO.setPassword("Incorrect Password");
	        }
		}catch (Exception ex) {
            throw new Exception("Incorrect Email");
        } 
		
		return userDTO;
	}
	
	
	private static String getAlphaNumericString(int n)
	 {
	  //stringa generica	
	  String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
	  StringBuilder sb = new StringBuilder(n);
	  //ciclo per creare stringa (salt) casuale
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
   
	public UserDTO getUserDTO(AuthRequestDTO authRequest) {
		//ritorno UserDTO con tutti i campi settati
	    	UserDTO userDTO = new UserDTO();
	    	userDTO.setEmail(authRequest.getEmail());
	    	userDTO.setPassword(authRequest.getPassword());
	    	userDTO.setSalt("Load");
	    	return userDTO;
		}
}
