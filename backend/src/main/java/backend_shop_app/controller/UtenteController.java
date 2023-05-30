package backend_shop_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import backend_shop_app.dto.AuthRequestDTO;
import backend_shop_app.dto.UserDTO;

public interface UtenteController {
	
	/**
	 * Generates a token for authentication.
	 *
	 * @param authRequest the authentication request DTO
	 * @return the response entity with the generated token
	 * @throws Exception if an error occurs during token generation
	 */
	public ResponseEntity<String> generateToken(AuthRequestDTO authRequest) throws Exception;
	
	/**
	 * Handles the sign-up request.
	 *
	 * @param userDTO the user DTO containing sign-up information
	 * @return the response entity indicating the success or failure of the sign-up process
	 * @throws Exception if an error occurs during sign-up
	 */
	public ResponseEntity<String> signup(UserDTO userDTO) throws Exception;
}
