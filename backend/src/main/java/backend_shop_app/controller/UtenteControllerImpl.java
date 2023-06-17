package backend_shop_app.controller;

import backend_shop_app.dto.CartDTO;
import backend_shop_app.dto.ProductDTO;
import backend_shop_app.dto.UserDTO;
import backend_shop_app.dto.request.AuthRequestDTO;
import backend_shop_app.dto.request.AuthRequestGoogleDTO;
import backend_shop_app.dto.request.ForgotPasswordDTO;
import backend_shop_app.dto.request.OtpVerificationDTO;
import backend_shop_app.service.CartService;
import backend_shop_app.service.CustomUserDetailsService;
import backend_shop_app.service.JsonCreateService;
import backend_shop_app.service.ProductService;
import backend_shop_app.service.WishListService;
import backend_shop_app.util.JwtUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/*
 ************ UTENTE CONTROLLER MANAGEMENT ************
 */
@RestController
@CrossOrigin("*")
public class UtenteControllerImpl implements UtenteController {
	
    private static final Logger logger = LoggerFactory.getLogger(UtenteControllerImpl.class);

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private WishListService wishListService;
    
    @Autowired
    private CartService cartService;
    
    @Autowired
    private JsonCreateService jsonCreateService;
    
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    /**
 	 * Endpoint to signin in application.
 	 * 
 	 * @param AuthRequestDTO contains the params to do check in db
 	 * @return ResponseEntity with a confirmation message or an error message if an exception occurs
 	 * @throws Exception if an error occurs while adding the user in db
 	 */
    @Override
    public ResponseEntity<String> signin(AuthRequestDTO authRequest) throws Exception {
		logger.info("START ELABORATION ENDPOINT - signin - /signin");
    	// Declare the lists to include in the return JSON
    	List<ProductDTO> listOfProduct = new ArrayList<>();
    	List<Integer> listOfIdWishList = new ArrayList<>();
    	List<CartDTO> listOfIdCart = new ArrayList<>();
		// Check if all required fields are present or correct
		if ( isFieldNull(authRequest) || !isValidEmail(authRequest.getEmail())) {
			logger.error("ENDPOINT - signin - invalid email");
			return new ResponseEntity<String>("Email is not valid", HttpStatus.BAD_REQUEST);
		}
		
    	// Retrieve the user
    	UserDTO userDTO = customUserDetailsService.getUserDTO(authRequest);
    	
    	// Set the Salt to indicate that the salt should be generated
    	userDTO.setSalt("Load");
    	
    	// Retrieve the lists and check if the password is correct
    	try {
			userDTO = customUserDetailsService.manageCredential(userDTO);
		} catch (Exception e) {
			logger.error("ENDPOINT - signin - incorrect email");
    		return new ResponseEntity<String>("Incorrect Email", HttpStatus.BAD_REQUEST);
		}
    	
    	if (userDTO.getPassword().equals("Incorrect Password")) {
			logger.error("ENDPOINT - signin - incorrect password");
    		return new ResponseEntity<String>("Incorrect Password", HttpStatus.BAD_REQUEST);
    	}
    	
    	// Retrieve the list of products
    	listOfProduct = productService.getListOfProduct();
    	
    	// Retrieve the list of wish list items for the user
    	listOfIdWishList = wishListService.getListOfWishList(userDTO.getIdutente());
    	
    	// Retrieve the list of cart items for the user
    	listOfIdCart = cartService.getListOfIdCart(userDTO.getIdutente());
    	
    	// Create the JSON to be sent as the return value
    	JSONObject jsonToSend = jsonCreateService.createJsonToSendSignIn(
    			jwtUtil.generateToken(userDTO.getEmail()), listOfProduct, userDTO,
    			listOfIdWishList, listOfIdCart);

		logger.info("END ELABORATION ENDPOINT - signin - /signin");
        return new ResponseEntity<String>(jsonToSend.toString(), HttpStatus.OK);
    }
    
    /*
	 * Endpoint to singup in application.
	 * 
	 * @param AuthRequestGoogleDTO contains the params to insert in db
	 * @return ResponseEntity with a confirmation message or an error message if an exception occurs
	 * @throws Exception if an error occurs while adding the user in db
	 */
    public ResponseEntity<String> singUpGoogle(  AuthRequestGoogleDTO authRequest) throws Exception {
		logger.info("START ELABORATION ENDPOINT - singUpGoogle - /google");
    	// Declare the lists to include in the return JSON
    	List<ProductDTO> listOfProduct = new ArrayList<>();
    	List<Integer> listOfIdWishList = new ArrayList<>();
    	List<CartDTO> listOfIdCart = new ArrayList<>();
    	UserDTO userDTO = new UserDTO();
		// Check if all required fields are present or correct
		if ( isFieldNull(authRequest) || !isValidEmail(authRequest.getEmail())) {
			logger.error("ENDPOINT - singUpGoogle - invalid email");
			return new ResponseEntity<String>("Email is not valid", HttpStatus.BAD_REQUEST);
		}
    	
    	// check if the email exists or is to be inserted in the db
    	try {
			 userDTO = customUserDetailsService.getUserInformation(authRequest.getEmail());
			 
			 if ( isFieldNull(userDTO) ) {
				 	 userDTO = new UserDTO();
				 	 userDTO.setEmail(authRequest.getEmail());
					 userDTO.setPassword("Login with google");
					 userDTO.setAddress("Login with google");
					 userDTO.setPhonenumber("Login with google");
					 userDTO.setSalt("Login with google");
					 userDTO.setSurname(authRequest.getSurname());
					 userDTO.setName(authRequest.getName());
				 	// Save the user in the table
		        	customUserDetailsService.signup(userDTO);
				}
		} catch (Exception e) {
			logger.error("ENDPOINT - singUpGoogle - an error was occured " + e.toString());
            return new ResponseEntity<String>("An error was occured", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    	
    	
    	// Retrieve the list of products
    	listOfProduct = productService.getListOfProduct();
    	
    	// Retrieve the list of wish list items for the user
    	listOfIdWishList = wishListService.getListOfWishList(userDTO.getIdutente());
    	
    	// Retrieve the list of cart items for the user
    	listOfIdCart = cartService.getListOfIdCart(userDTO.getIdutente());
    	
    	// Create the JSON to be sent as the return value
    	JSONObject jsonToSend = jsonCreateService.createJsonToSendSignIn(
    			jwtUtil.generateToken(userDTO.getEmail()), listOfProduct, userDTO,
    			listOfIdWishList, listOfIdCart);
    	
		logger.info("END ELABORATION ENDPOINT - singUpGoogle - /google");
        return new ResponseEntity<String>(jsonToSend.toString(), HttpStatus.OK);
    }
    
    /*
	 * Endpoint to singup in application.
	 * 
	 * @param UserDTO contains the params to insert in db
	 * @return ResponseEntity with a confirmation message or an error message if an exception occurs
	 * @throws Exception if an error occurs while adding the user in db
	 */
    public ResponseEntity<String> signup( UserDTO userDTO) throws Exception {
		logger.info("START ELABORATION ENDPOINT - signup - /signup");
		// Declare a list of products to generate
		List<ProductDTO> listOfProduct = new ArrayList<>();
		// Check if all required fields are present or correct
		if(userDTO.getIdutente()!= 0) {
			logger.error("ENDPOINT - signup - found idutente");
			return new ResponseEntity<String>("There is an error, you can't send idutente", HttpStatus.BAD_REQUEST);
		}
		
		//check if email is valid
		if ( isFieldNull(userDTO) || !isValidEmail(userDTO.getEmail())) {
			logger.error("ENDPOINT - signup - invalid email");
			return new ResponseEntity<String>("Email is not valid", HttpStatus.BAD_REQUEST);
		}
		
		//check if password is valid
		if (isFieldPasswordNull(userDTO) || userDTO.getPassword().isEmpty() ) {
			logger.error("ENDPOINT - signup - invalid password");
			return new ResponseEntity<String>("Password is not valid", HttpStatus.BAD_REQUEST);
		}
		
		//check if Name is valid
	    if (isFieldNull(userDTO.getName()) || userDTO.getName().isEmpty()) {
			logger.error("ENDPOINT - signup - invalid name");
			return new ResponseEntity<String>("Name is not valid", HttpStatus.BAD_REQUEST);
	    }
	    
		//check if Surname is valid
	    if (isFieldNull(userDTO.getSurname()) || userDTO.getSurname().isEmpty()) {
			logger.error("ENDPOINT - signup - invalid surname");
			return new ResponseEntity<String>("Surname is not valid", HttpStatus.BAD_REQUEST);
	    }
	    
		//check if Address is valid
	    if (isFieldNull(userDTO.getAddress()) || userDTO.getAddress().isEmpty()) {
			logger.error("ENDPOINT - signup - invalid address");
			return new ResponseEntity<String>("Address is not valid", HttpStatus.BAD_REQUEST);
	    }
	    
		//check if PhoneNumber is valid
	    if (!isValidPhoneNumber(userDTO.getPhonenumber())) {
			logger.error("ENDPOINT - signup - invalid phoneNumber");
			return new ResponseEntity<String>("PhoneNumber is not valid", HttpStatus.BAD_REQUEST);
	    }
	    
		//check if email already exist
		int checkEmail = customUserDetailsService.getEmail(userDTO);
		
		if(checkEmail == 1) {
			logger.error("ENDPOINT - signup - email already exist");
	        return new ResponseEntity<String>("Email already exist", HttpStatus.BAD_REQUEST);
		}
        try {
        	// Set Salt to "Generate" to indicate that it should be retrieved
        	userDTO.setSalt("Generate");
        	
        	// Manage credential 
        	UserDTO user = customUserDetailsService.manageCredential(userDTO);
        	
        	// Save the user in the table
        	customUserDetailsService.signup(user);
        	
        	// Retrieve the list of products to be sent as the return value
        	listOfProduct = productService.getListOfProduct();
        } catch (Exception ex) {
			logger.error("ENDPOINT - signup - error in call to services: " + ex.toString());
            return new ResponseEntity<String>("An error was occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        // Create the JSON to be sent as the return value
        JSONObject jsonToSend = jsonCreateService.createJsonToSendSignUp(
        		jwtUtil.generateToken(userDTO.getEmail()), listOfProduct, userDTO.getIdutente());
        
		logger.info("END ELABORATION ENDPOINT - signup - /signup");
        return new ResponseEntity<String>(jsonToSend.toString(), HttpStatus.OK);
    }
 
	 
	/**
 	 * Endpoint to update password.
 	 * 
 	 * @param AuthRequestDTO contains the params to do check in db
 	 * @return ResponseEntity with a confirmation message or an error message if an exception occurs
 	 * @throws Exception if an error occurs while adding the user in db
 	 */
	
    public ResponseEntity<String> updatePassword(  ForgotPasswordDTO forgotPasswordDTO) throws Exception {
		logger.info("START ELABORATION ENDPOINT - updatePassword - /updatePassword");
    	UserDTO userDTO= new UserDTO();
		// Check if all required fields are present or correct
		if ( isFieldNull(forgotPasswordDTO) ) {
    		logger.error("ENDPOINT - updatePassword - This email is not valid ");
			return new ResponseEntity<String>("Email is not valid", HttpStatus.BAD_REQUEST);
		}
    	
		// Retrieve the lists and check if the password is correct
    	try {
    		userDTO.setEmail(forgotPasswordDTO.getEmail());
    		userDTO.setPassword(forgotPasswordDTO.getPassword());
    		userDTO.setSalt("Generate");
			userDTO = customUserDetailsService.manageCredential(userDTO);
			// Save the user in the table
        	customUserDetailsService.updatePassword(userDTO);
        	
		} catch (Exception e) {
    		logger.error("ENDPOINT - updatePassword - This email is not correct ");
    		return new ResponseEntity<String>("Incorrect Email", HttpStatus.BAD_REQUEST);
		}
    	
    	if (userDTO.getPassword().equals("Incorrect Password")) {
    		logger.error("ENDPOINT - updatePassword - This password is not correct ");
    		return new ResponseEntity<String>("Incorrect Password", HttpStatus.BAD_REQUEST);
    	}
    	
		logger.info("END ELABORATION ENDPOINT - updatePassword - /updatePassword");
        return new ResponseEntity<String>("Password changed", HttpStatus.OK);
    }
    
    /**
 	 * Endpoint to verify email in application.
 	 * 
 	 * @param OtpVerificationDTO contains the params to do check in db
 	 * @return ResponseEntity with a confirmation message or an error message if an exception occurs
 	 * @throws Exception if an error occurs while adding the user in db
 	 */
    public ResponseEntity<String> mailForOtp(  OtpVerificationDTO otpVerificationDTO) throws Exception {
		logger.info("START ELABORATION ENDPOINT - mailForOtp - /mailForOtp");
		// Check if all required fields are present or correct
		if ( isFieldNull(otpVerificationDTO) || !isValidEmail(otpVerificationDTO.getEmail())) {
    		logger.error("ENDPOINT - mailForOtp - This email is not valid ");
			return new ResponseEntity<String>("Email is not valid", HttpStatus.BAD_REQUEST);
		}
		
    	// Retrieve the user
    	UserDTO userDTO = customUserDetailsService.getUserInformation(otpVerificationDTO.getEmail());
    	
    	// check if the email is present
    	if (userDTO==null) {
    		logger.error("ENDPOINT - mailForOtp - This email doesn't exits ");
            return new ResponseEntity<String>("This email doesn't exits", HttpStatus.BAD_REQUEST);
		}
    	
		logger.info("END ELABORATION ENDPOINT - mailForOtp - /mailForOtp");
        return new ResponseEntity<String>("This email is registered", HttpStatus.OK);
    }


	/*
	 *  Metodo di validazione dell'email:
	 *	-Può contenere lettere maiuscole e minuscole (A-Z, a-z)
	 *	-Può contenere numeri (0-9)
	 *	-Può contenere i seguenti caratteri speciali: +, _, ., -
	 *	-Deve essere seguito da un simbolo "@" e almeno un carattere alfanumerico o un punto
	 *	-Dopo l'@" deve essere presente un dominio composto da caratteri alfanumerici o punti
	 */
	private static boolean isValidEmail(String email) {
		String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
	    return email.matches(emailRegex);
	}
	
	
	/*
	 * check if the phonenumber is correct
	 */
	private static boolean isValidPhoneNumber(String phoneNumber) {
	    // Remove any spaces or special characters from the phone number
	    String cleanedPhoneNumber;
		try {
			cleanedPhoneNumber = phoneNumber.replaceAll("[\\s()-]+", "");
		} catch (Exception e) {
		    return !(phoneNumber == null);
		}
	   
	    // Check if the phone number consists only of digits and has a valid length
	    return cleanedPhoneNumber.matches("\\d{10}") || cleanedPhoneNumber.matches("\\d{11}");
	}
	

	
	/*
	 * check if a field is null
	 */
	public static boolean isFieldNull(Object input) {
	    return input == null;
	}
	
	/*
	 * check if a email of authRequestDTO is null
	 */
	public static boolean isFieldNullEmail(AuthRequestDTO authRequestDTO) {
	    return authRequestDTO.getEmail() == null;
	}
	
	/*
	 * check if a Password of userDTO is null
	 */
	public static boolean isFieldPasswordNull(UserDTO userDTO) {
	    return userDTO.getPassword() == null;
	}
	
	
}
