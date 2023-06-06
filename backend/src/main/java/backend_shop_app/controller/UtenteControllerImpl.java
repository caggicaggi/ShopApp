package backend_shop_app.controller;

import backend_shop_app.dto.CartDTO;
import backend_shop_app.dto.ProductDTO;
import backend_shop_app.dto.UserDTO;
import backend_shop_app.dto.request.AuthRequestDTO;
import backend_shop_app.dto.request.AuthRequestGoogleDTO;
import backend_shop_app.dto.request.ForgotPasswordDTO;
import backend_shop_app.service.CartService;
import backend_shop_app.service.CustomUserDetailsService;
import backend_shop_app.service.JsonCreateService;
import backend_shop_app.service.ProductService;
import backend_shop_app.service.WishListService;
import backend_shop_app.util.JwtUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/*
 ************ UTENTE CONTROLLER MANAGEMENT ************
 */
@RestController
public class UtenteControllerImpl implements UtenteController {

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
 	 * Endpoint to singin in application.
 	 * 
 	 * @param AuthRequestDTO contains the params to do check in db
 	 * @return ResponseEntity with a confirmation message or an error message if an exception occurs
 	 * @throws Exception if an error occurs while adding the user in db
 	 */
    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody AuthRequestDTO authRequest) throws Exception {
    	// Declare the lists to include in the return JSON
    	List<ProductDTO> listOfProduct = new ArrayList<>();
    	List<Integer> listOfIdWishList = new ArrayList<>();
    	List<CartDTO> listOfIdCart = new ArrayList<>();
    	
		// Check if all required fields are present or correct
		if ( isFieldNull(authRequest) || !isValidEmail(authRequest.getEmail())) {
			return new ResponseEntity<String>("Email is not valid", HttpStatus.BAD_REQUEST);
		}
		
    	// Retrieve the user
    	UserDTO userDTO = customUserDetailsService.getUserDTO(authRequest);
    	
    	// Set the Salt to indicate that the salt should be generated
    	userDTO.setSalt("Load");
    	
    	// Retrieve the lists and check if the password is correct
    	try {
			userDTO = customUserDetailsService.cryptoPassword(userDTO);
		} catch (Exception e) {
    		return new ResponseEntity<String>("Incorrect Email", HttpStatus.BAD_REQUEST);
		}
    	
    	if (userDTO.getPassword().equals("Incorrect Password")) {
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
    	
        return new ResponseEntity<String>(jsonToSend.toString(), HttpStatus.OK);
    }
    
    /*
	 * Endpoint to singup in application.
	 * 
	 * @param UserDTO contains the params to insert in db
	 * @return ResponseEntity with a confirmation message or an error message if an exception occurs
	 * @throws Exception if an error occurs while adding the user in db
	 */
    @PostMapping("/google")
    public ResponseEntity<String> singUpGoogle(@RequestBody  AuthRequestGoogleDTO authRequest) throws Exception {
    	// Declare the lists to include in the return JSON
    	List<ProductDTO> listOfProduct = new ArrayList<>();
    	List<Integer> listOfIdWishList = new ArrayList<>();
    	List<CartDTO> listOfIdCart = new ArrayList<>();
    	UserDTO userDTO = new UserDTO();
		// Check if all required fields are present or correct
		if ( isFieldNull(authRequest) || !isValidEmail(authRequest.getEmail())) {
			return new ResponseEntity<String>("Email is not valid", HttpStatus.BAD_REQUEST);
		}
    	
    	// Retrieve the lists of product, wishList and cart
    	try {
			 userDTO = customUserDetailsService.getUserInformation(authRequest.getEmail());
			 
			 if ( isFieldNullGoogleSingIn(userDTO) ) {
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
    		return new ResponseEntity<String>("Incorrect Email", HttpStatus.BAD_REQUEST);
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
    	
        return new ResponseEntity<String>(jsonToSend.toString(), HttpStatus.OK);
    }
    
    /*
	 * Endpoint to singup in application.
	 * 
	 * @param UserDTO contains the params to insert in db
	 * @return ResponseEntity with a confirmation message or an error message if an exception occurs
	 * @throws Exception if an error occurs while adding the user in db
	 */
	@PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDTO userDTO) throws Exception {
		// Declare a list of products to generate
		List<ProductDTO> listOfProduct = new ArrayList<>();
		
		// Check if all required fields are present or correct
		if(userDTO.getIdutente()!= 0) {
			return new ResponseEntity<String>("There is an error, you can't send idutente", HttpStatus.BAD_REQUEST);
		}
		
		//check if email is valid
		if ( isFieldNull(userDTO) || !isValidEmail(userDTO.getEmail())) {
			return new ResponseEntity<String>("Email is not valid", HttpStatus.BAD_REQUEST);
		}
		
		//check if password is valid
		if (isFieldPasswordNull(userDTO) || userDTO.getPassword().isEmpty() ) {
			return new ResponseEntity<String>("Password is not valid", HttpStatus.BAD_REQUEST);
		}
		
		//check if Name is valid
	    if (isFieldNull(userDTO.getName()) || userDTO.getName().isEmpty()) {
			return new ResponseEntity<String>("Name is not valid", HttpStatus.BAD_REQUEST);
	    }
	    
		//check if Surname is valid
	    if (isFieldNull(userDTO.getSurname()) || userDTO.getSurname().isEmpty()) {
			return new ResponseEntity<String>("Surname is not valid", HttpStatus.BAD_REQUEST);
	    }
	    
		//check if Address is valid
	    if (isFieldNull(userDTO.getAddress()) || userDTO.getAddress().isEmpty()) {
			return new ResponseEntity<String>("Address is not valid", HttpStatus.BAD_REQUEST);
	    }
	    
		//check if PhoneNumber is valid
	    if (!isValidPhoneNumber(userDTO.getPhonenumber())) {
			return new ResponseEntity<String>("PhoneNumber is not valid", HttpStatus.BAD_REQUEST);
	    }
	    
		//check if email already exist
		int checkEmail = customUserDetailsService.getEmail(userDTO);
		
		if(checkEmail == 1)
	        return new ResponseEntity<String>("Email already exist", HttpStatus.BAD_REQUEST);

        try {
        	// Set Salt to "Generate" to indicate that it should be retrieved
        	userDTO.setSalt("Generate");
        	
        	// Crypt the password
        	UserDTO user = customUserDetailsService.cryptoPassword(userDTO);
        	
        	// Save the user in the table
        	customUserDetailsService.signup(user);
        	
        	// Retrieve the list of products to be sent as the return value
        	listOfProduct = productService.getListOfProduct();
        } catch (Exception ex) {
            throw new Exception(ex.toString());
        }
        
        // Create the JSON to be sent as the return value
        JSONObject jsonToSend = jsonCreateService.createJsonToSendSignUp(
        		jwtUtil.generateToken(userDTO.getEmail()), listOfProduct, userDTO.getIdutente());
        
        return new ResponseEntity<String>(jsonToSend.toString(), HttpStatus.OK);
    }
	
	
	/**
 	 * Endpoint to get phone number.
 	 * 
 	 * @param ForgotPasswordDTO contains the email 
 	 * @return ResponseEntity with a confirmation message or an error message if an exception occurs and the phone number
 	 * @throws Exception if an error occurs
 	 */
	
    @GetMapping("/getPhoneNumber")
    public ResponseEntity<String> getPhoneNumber(@RequestBody ForgotPasswordDTO forgotPasswordDTO) throws Exception {
    	UserDTO userDTO= new UserDTO();
		// Check if all required fields are present or correct
		if ( isFieldNull(forgotPasswordDTO) || !isValidEmail(forgotPasswordDTO.getEmail())) {
			return new ResponseEntity<String>("Email is not valid", HttpStatus.BAD_REQUEST);
		}
    	
    	// Get user information to return email
    	try {
    		userDTO = customUserDetailsService.getUserInformation(forgotPasswordDTO.getEmail());
		} catch (Exception e) {
    		return new ResponseEntity<String>("Incorrect Email", HttpStatus.BAD_REQUEST);
		}
    	
    	// Create the JSON to be sent as the return value
    	JSONObject jsonToSend = jsonCreateService.createJsonToForgotPassword(userDTO.getPhonenumber());
    	
        return new ResponseEntity<String>(jsonToSend.toString(), HttpStatus.OK);
    }
 
	 
	/**
 	 * Endpoint to update password.
 	 * 
 	 * @param AuthRequestDTO contains the params to do check in db
 	 * @return ResponseEntity with a confirmation message or an error message if an exception occurs
 	 * @throws Exception if an error occurs while adding the user in db
 	 */
	
    @PostMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody ForgotPasswordDTO forgotPasswordDTO) throws Exception {
    	UserDTO userDTO= new UserDTO();
		// Check if all required fields are present or correct
		if ( isFieldNull(forgotPasswordDTO) ) {
			return new ResponseEntity<String>("Email is not valid", HttpStatus.BAD_REQUEST);
		}
    	
		// Retrieve the lists and check if the password is correct
    	try {
    		userDTO.setEmail(forgotPasswordDTO.getEmail());
    		userDTO.setPassword(forgotPasswordDTO.getPassword());
    		userDTO.setSalt("Generate");
			userDTO = customUserDetailsService.cryptoPassword(userDTO);
			// Save the user in the table
        	customUserDetailsService.updatePassword(userDTO);
        	
		} catch (Exception e) {
    		return new ResponseEntity<String>("Incorrect Email", HttpStatus.BAD_REQUEST);
		}
    	
    	if (userDTO.getPassword().equals("Incorrect Password")) {
    		return new ResponseEntity<String>("Incorrect Password", HttpStatus.BAD_REQUEST);
    	}
    	
        return new ResponseEntity<String>("Password changed", HttpStatus.OK);
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
	 * check if a email of authRequestDTO is null
	 */
	public static boolean isFieldNull(AuthRequestDTO authRequestDTO) {
	    return authRequestDTO.getEmail() == null;
	}
	
	/*
	 * check if a email of ForgotPasswordDTO is null
	 */
	public static boolean isFieldNull(ForgotPasswordDTO forgotPasswordDTO) {
	    return forgotPasswordDTO == null;
	}
	
	/*
	 * check if a email of userDTO is null
	 */
	public static boolean isFieldNull(UserDTO userDTO) {
	    return userDTO.getEmail() == null;
	}
	
	/*
	 * check if a email of userDTO is null
	 */
	public static boolean isFieldNull(AuthRequestGoogleDTO userDTO) {
	    return userDTO == null;
	}
	
	/*
	 * check if a email of userDTO is null
	 */
	public static boolean isFieldNullGoogleSingIn(UserDTO userDTO) {
	    return userDTO == null;
	}
	
	
	/*
	 * check if a field is null
	 */
	public static boolean isFieldNull(String input) {
	    return input == null;
	}
	
	/*
	 * check if a field is null
	 */
	public static boolean isFieldPasswordNull(UserDTO userDTO) {
	    return userDTO.getPassword() == null;
	}
	
	
}
