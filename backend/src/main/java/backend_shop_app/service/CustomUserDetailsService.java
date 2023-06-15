package backend_shop_app.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import backend_shop_app.dto.UserDTO;
import backend_shop_app.dto.request.AuthRequestDTO;

public interface CustomUserDetailsService {

	/**
	 *  Load user details by email
	 *
	 * @param 	email			the user's email
	 * @return  the list of products 
	 */
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
    

    /**
	 *  Get user information by email
	 *  
	 *  @param 	email			the user's email
	 *  @return the UserDTO present in db or null if UserDTO doesn't exist
	 */
    public UserDTO getUserInformation(String email) throws UsernameNotFoundException;

    /**
	 *  Update user password
	 *  
	 *  @param 	userDTO			the userDTO contains new password
	 *  @return the UserDTO present in db or null if UserDTO doesn't exist
	 */
    public UserDTO updatePassword(UserDTO userDTO) throws UsernameNotFoundException;
    
    /**
	 *  Sign up a new user 
	 *  
	 *  @param userDTO			the userDTO to inser in db
	 *  @return nothing
	 */
    public void signup(UserDTO userDTO);
    
    /**
	 *  Get the count of users with a specific email
	 *  
	 *  @param 	userDTO			the user's information
	 *  @return an int: 1 if email exist in db or 0 if not exist 
	 */
    public int getEmail(UserDTO userDTO);
    
    /**
	 *  Manage user credentials (login, authentication)
	 *  
	 *  @param 	userDTO			the user's information
	 *  @return the new user DTO with the new Passoword or check login and return user
	 */
    public UserDTO manageCredential(UserDTO userDTO) throws Exception;

    /**
	 *  Get UserDTO object from AuthRequestDTO
	 *  
	 *  @param 	authRequest		the AuthRequest's information
	 *  @return userDTO set by AuthRequestDTO
	 */
    public UserDTO getUserDTO(AuthRequestDTO authRequest);
}