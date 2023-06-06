package backend_shop_app.controller;

import org.springframework.http.ResponseEntity;

import backend_shop_app.dto.UserDTO;
import backend_shop_app.dto.request.AuthRequestDTO;
import backend_shop_app.dto.request.AuthRequestGoogleDTO;
import backend_shop_app.dto.request.ForgotPasswordDTO;

public interface UtenteController {
	
	/**
	 * Generates a token for authentication.
	 *
	 * @param authRequest the authentication request DTO
	 * @return the response entity with the generated token
	 * @throws Exception if an error occurs during token generation
	 */
	public ResponseEntity<String> signin(AuthRequestDTO authRequest) throws Exception;
	
	/**
	 * Handles the sign-up request.
	 *
	 * @param userDTO the user DTO containing sign-up information
	 * @return the response entity indicating the success or failure of the sign-up process
	 * @throws Exception if an error occurs during sign-up
	 */
	public ResponseEntity<String> signup(UserDTO userDTO) throws Exception;
	
	/**
	 * Update password.
	 *
	 * @param ForgotPasswordDTO the user DTO containing new password
	 * @return the response entity indicating the success or failure of the updateProcess process
	 * @throws Exception if an error occurs
	 */
    public ResponseEntity<String> updatePassword( ForgotPasswordDTO forgotPasswordDTO)  throws Exception;
    
	/**
	 * Handles the singInGoogle request.
	 *
	 * @param AuthRequestGoogleDTO the user DTO containing sign-in information with google
	 * @return the response entity indicating the success or failure of the sign-up process
	 * @throws Exception if an error occurs during sign-in
	 */
    public ResponseEntity<String> singUpGoogle(  AuthRequestGoogleDTO authRequest) throws Exception;
    
	/**
	 * Handles the sign-up request.
	 *
	 * @param userDTO the user DTO containing sign-up information
	 * @return the response entity indicating the success or failure of the sign-up process
	 * @throws Exception if an error occurs during sign-up
	 */
    public ResponseEntity<String> getPhoneNumber(ForgotPasswordDTO forgotPasswordDTO) throws Exception;
}
