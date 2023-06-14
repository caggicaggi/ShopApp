package backend_shop_app.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import backend_shop_app.dto.UserDTO;
import backend_shop_app.dto.request.AuthRequestDTO;

public interface CustomUserDetailsServiceImpl {
	
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException ;
    
    public UserDTO getUserInformation(String email) throws UsernameNotFoundException ;

    public UserDTO updatePassword(UserDTO userDTO) throws UsernameNotFoundException ;
    	
    public void signup(UserDTO userDTO);
    
    public int getEmail(UserDTO userDTO);  
    
	public UserDTO manageCredential(UserDTO userDTO) throws Exception ;

    	public UserDTO getUserDTO(AuthRequestDTO authRequest);
}
