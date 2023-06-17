package backend_shop_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import backend_shop_app.dto.UserDTO;
import backend_shop_app.dto.request.AuthRequestDTO;
import backend_shop_app.dto.request.AuthRequestGoogleDTO;
import backend_shop_app.dto.request.ForgotPasswordDTO;
import backend_shop_app.dto.request.OtpVerificationDTO;

@RequestMapping(value= "/utente")
@CrossOrigin("*")
public interface UtenteController {
	
	/**
	 * Generates a token for authentication.
	 *
	 * @param authRequest the authentication request DTO
	 * @return the response entity with the generated token
	 * @throws Exception if an error occurs during token generation
	 */
	@PostMapping(value = "/signin" )
	public ResponseEntity<String> signin(@RequestBody AuthRequestDTO authRequest) throws Exception;
	
	/**
	 * Handles the sign-up request.
	 *
	 * @param userDTO the user DTO containing sign-up information
	 * @return the response entity indicating the success or failure of the sign-up process
	 * @throws Exception if an error occurs during sign-up
	 */
    @PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody UserDTO userDTO) throws Exception;
	
	/**
	 * Update password.
	 *
	 * @param ForgotPasswordDTO the user DTO containing new password
	 * @return the response entity indicating the success or failure of the updateProcess process
	 * @throws Exception if an error occurs
	 */
    @PostMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody ForgotPasswordDTO forgotPasswordDTO)  throws Exception;
    
	/**
	 * Handles the singInGoogle request.
	 *
	 * @param AuthRequestGoogleDTO the user DTO containing sign-in information with google
	 * @return the response entity indicating the success or failure of the sign-up process
	 * @throws Exception if an error occurs during sign-in
	 */
	@PostMapping("/google")
    public ResponseEntity<String> singUpGoogle(@RequestBody  AuthRequestGoogleDTO authRequest) throws Exception;
    
    /**
	 * Verify email
	 *
	 * @param OtpVerificationDTO the opt DTO containing email
	 * @return the response entity which indicates whether the mail exists or not
	 * @throws Exception if an error occurs 
	 */
    @GetMapping("/mailForOtp")
    public ResponseEntity<String> mailForOtp(OtpVerificationDTO otpVerificationDTO) throws Exception;
}
