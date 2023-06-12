package backend_shop_app.service;

import backend_shop_app.dto.UserDTO;
import backend_shop_app.dto.request.AuthRequestDTO;
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


/**
 * User Management Class
 */
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
    	// Check if the email exists in the database
    	UserDTO user = userRepository.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
    
    public UserDTO getUserInformation(String email) throws UsernameNotFoundException {
    	// Check if the email exists in the database
    	UserDTO user = userRepository.findByEmail(email);
        return user;
    }
    
    public UserDTO updatePassword(UserDTO userDTO) throws UsernameNotFoundException {
		String newSalt = userDTO.getSalt();
    	UserDTO user = userRepository.findByEmail(userDTO.getEmail());
    	userDTO.setAddress(user.getAddress());
    	userDTO.setName(user.getName());
    	userDTO.setSalt(newSalt);
    	userDTO.setPhonenumber(user.getPhonenumber());
    	userDTO.setSurname(user.getSurname());
    	userRepository.delete(user);
    	userRepository.save(userDTO);
        return user;
    }
    
    public void signup(UserDTO userDTO)  {
    	// Call the repository to save the userDTO object in the database
    	userRepository.save(userDTO);
    }
    
    public int getEmail(UserDTO userDTO)  {
    	// Call the repository to get the userDTO object in the database
    	userDTO = userRepository.findByEmail(userDTO.getEmail());
    	if(userDTO == null)
    		return 0;
    	return 1;
    }
    
	public UserDTO manageCredential(UserDTO userDTO) throws Exception {
		try {
		String salt = new String();
		UserDTO user = new UserDTO();
		// Check if the salt needs to be generated or retrieved
		if(!userDTO.getSalt().equals("Load")) {
			// Generate a random alphanumeric string as salt
			salt = getAlphaNumericString(9);
			// Generate the encrypted password using SHA-256 algorithm
			String password = generatePassword(userDTO.getPassword()+salt+secret);
			userDTO.setSalt(salt);
			userDTO.setPassword(password);
			return userDTO;
		} else {
	    	user = userRepository.findByEmail(userDTO.getEmail());
	    	salt = user.getSalt();
	    	userDTO.setIdutente(user.getIdutente());
		}
		// Generate encrypted password for comparison
		String password = generatePassword(userDTO.getPassword()+salt+secret);
		userDTO.setSalt(salt);
		userDTO.setPassword(password);
		userDTO.setPhonenumber(user.getPhonenumber());
		
		if(user.getPassword().equals(password)) {
	            // Perform authentication using Spring Security's AuthenticationManager
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
	  // A generic string containing uppercase letters, lowercase letters, and digits	
	  String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
	  StringBuilder sb = new StringBuilder(n);
	  // Loop to create a random string (salt)
	  for (int i = 0; i < n; i++) {
	   int index = (int)(AlphaNumericString.length() * Math.random());
	   sb.append(AlphaNumericString.charAt(index));
	  }
	  return sb.toString();
	}

	static String generatePassword(String passwordAndSecretAndSalt) throws NoSuchAlgorithmException {
		// Return the SHA-256 encrypted password
		return Hashing.sha256().hashString(passwordAndSecretAndSalt, StandardCharsets.UTF_8).toString();
	}
   
	public UserDTO getUserDTO(AuthRequestDTO authRequest) {
		// Create a UserDTO object and set its fields based on the provided AuthRequestDTO
	    	UserDTO userDTO = new UserDTO();
	    	userDTO.setEmail(authRequest.getEmail());
	    	userDTO.setPassword(authRequest.getPassword());
	    	userDTO.setSalt("Load");
	    	return userDTO;
	}
}
