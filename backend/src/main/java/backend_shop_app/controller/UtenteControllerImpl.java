package backend_shop_app.controller;

import backend_shop_app.dto.AuthRequestDTO;
import backend_shop_app.dto.CartDTO;
import backend_shop_app.dto.ProductDTO;
import backend_shop_app.dto.UserDTO;
import backend_shop_app.dto.WishListDTO;
import backend_shop_app.service.CartService;
import backend_shop_app.service.CustomUserDetailsService;
import backend_shop_app.service.JsonCreateService;
import backend_shop_app.service.ProductService;
import backend_shop_app.service.WishListService;
import backend_shop_app.util.JwtUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


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

    @GetMapping("/testJwt")
    public String welcome() {
        return "Correct Jwt, weeeelccccommmmmeeee !!";
    }
    
    @PostMapping("/signin")
    public ResponseEntity<String> generateToken(@RequestBody AuthRequestDTO authRequest) throws Exception {
    	//dichiaro le liste da inserire nel json di return
    	List<ProductDTO> listOfProduct = new ArrayList<>();
    	List<Integer> listOfIdWishList = new ArrayList<>();
    	List<CartDTO> listOfIdCart= new ArrayList<>();
    	//recupero l'utente
    	UserDTO userDTO = customUserDetailsService.getUserDTO(authRequest);
    	//setto il Salt per indicare che il salt dovrà essere generato
    	userDTO.setSalt("Load");
    	//recupero le liste e controllo se la password è corretta
    	try {
			userDTO=customUserDetailsService.cryptoPassword(userDTO);
		} catch (Exception e) {
    		return new ResponseEntity<String>("Incorrect Email",HttpStatus.BAD_REQUEST);
		}
    	if(userDTO.getPassword().equals("Incorrect Password")) {
    		return new ResponseEntity<String>("Incorrect Password",HttpStatus.BAD_REQUEST);
    	}
    	listOfProduct = productService.getListOfProduct();
    	listOfIdWishList = wishListService.getListOfWishList(userDTO.getIdutente());
    	listOfIdCart = cartService.getListOfIdCart(userDTO.getIdutente());
    	//creo il json da mandare come return
    	Map<String,List<JSONObject>> jsonToSend = jsonCreateService.
    			createJsonToSendSignIn(jwtUtil.generateToken(userDTO.getEmail()),listOfProduct,userDTO.getIdutente()
    					,listOfIdWishList,listOfIdCart);
        String response = jsonCreateService.parse(jsonToSend);
        return new ResponseEntity<String>(response,HttpStatus.OK);
    }
    
  
	@PostMapping("/signup")
    public ResponseEntity<String> signup( @RequestBody UserDTO userDTO) throws Exception {
		//dichiaro lista di prodotti da generare
		List<ProductDTO> listOfProduct = new ArrayList<>();
        try {
        	//setto Salt a Generate per indica che dovrà essere recuperato
        	userDTO.setSalt("Generate");
        	//crypto la password
        	UserDTO user=customUserDetailsService.cryptoPassword(userDTO);
        	//salvo l'utente nella tabella
        	customUserDetailsService.signup(user);
        	//recuper la lista dei prodotti da mandare come return
        	listOfProduct = productService.getListOfProduct();
        } catch (Exception ex) {
            throw new Exception(ex.toString());
        }
        //creo il json da mandare come return
        Map<String,List<JSONObject>> jsonToSend = jsonCreateService.
    			createJsonToSendSignUp(jwtUtil.generateToken(userDTO.getEmail()),listOfProduct,userDTO.getIdutente());
        String response = jsonCreateService.parse(jsonToSend);
        return new ResponseEntity<String>(response,HttpStatus.OK);
    }
	
	
}
