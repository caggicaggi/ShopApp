package backend_shop_app.controller;

import backend_shop_app.dto.AuthRequestDTO;
import backend_shop_app.dto.CartDTO;
import backend_shop_app.dto.ProductDTO;
import backend_shop_app.dto.UserDTO;
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
    public ResponseEntity<String> generateToken(AuthRequestDTO authRequest) throws Exception {
    	// Declare the lists to include in the return JSON
    	List<ProductDTO> listOfProduct = new ArrayList<>();
    	List<Integer> listOfIdWishList = new ArrayList<>();
    	List<CartDTO> listOfIdCart = new ArrayList<>();
    	
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
    			jwtUtil.generateToken(userDTO.getEmail()), listOfProduct, userDTO.getIdutente(),
    			listOfIdWishList, listOfIdCart);
    	
        return new ResponseEntity<String>(jsonToSend.toString(), HttpStatus.OK);
    }
    
    /**
	 * Endpoint to singup in application.
	 * 
	 * @param UserDTO contains the params to insert in db
	 * @return ResponseEntity with a confirmation message or an error message if an exception occurs
	 * @throws Exception if an error occurs while adding the user in db
	 */
	@PostMapping("/signup")
    public ResponseEntity<String> signup(UserDTO userDTO) throws Exception {
		// Declare a list of products to generate
		List<ProductDTO> listOfProduct = new ArrayList<>();
		
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
}
