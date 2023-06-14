package backend_shop_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import backend_shop_app.dto.request.CartCheckoutRequest;
import backend_shop_app.dto.request.CartRequestDTO;
import backend_shop_app.service.CartService;
import backend_shop_app.service.CartServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 ************ CART CONTROLLER MANAGEMENT  ************ 
 */
@RestController
@RequestMapping(value= "/cart")
public class CartControllerImpl implements CartController {
	
    private static final Logger logger = LoggerFactory.getLogger(CartControllerImpl.class);

	@Autowired
	CartServiceImpl cartServiceImpl;

	/**
	 * Endpoint to add one or more products to the shopping cart.
	 * 
	 * @param cartRequestDTO list of products to be added to the cart
	 * @return ResponseEntity with a confirmation message or an error message if an exception occurs
	 * @throws Exception if an error occurs while adding the products to the cart
	 */
	@PutMapping("/add")
	public ResponseEntity<String> addProductInCart(@RequestBody List<CartRequestDTO> cartRequestDTO) throws Exception {
		logger.info("START ELABORATION ENDPOINT - addPrductInCart - /cart/add");
		// Check if all required fields are present
		for (int i = 0; i < cartRequestDTO.size(); i++) {
			if ( cartRequestDTO.get(i).getIdProduct() <= 0 
					|| cartRequestDTO.get(i).getIdUtente() <= 0
					|| cartRequestDTO.get(i).getQuantity() <= 0 
					|| !isInteger(cartRequestDTO.get(i).getIdProduct()+"")
					|| !isInteger(cartRequestDTO.get(i).getIdUtente()+"")
					|| !isInteger(cartRequestDTO.get(i).getQuantity()+"")) {
				logger.info("ENDPOINT - addPrductInCart - some fields are missing or incorrect");
				return new ResponseEntity<String>("Some fields are missing or incorrect", HttpStatus.BAD_REQUEST);
			}
		}
		// Call the cartService to add the products to the cart
		int countElement = cartServiceImpl.addProductInCart(cartRequestDTO);

		//check if front-end send element but it not added
		if (countElement == 0 && cartRequestDTO.size()>0) {
			logger.info("ENDPOINT - addPrductInCart - the list is empty");
			return new ResponseEntity<String>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("END ELABORATION ENDPOINT - addPrductInCart - /cart/add");
		return new ResponseEntity<String>(countElement + " product(s) correctly inserted in the cart", HttpStatus.OK);
	}

	/**
	 * Endpoint to remove one or more products from the shopping cart.
	 * 
	 * @param cartRequestDTO list of products to be removed from the cart
	 * @return ResponseEntity with a confirmation message or an error message if an exception occurs
	 * @throws Exception if an error occurs while removing the products from the cart
	 */
	@DeleteMapping("/remove")
	public ResponseEntity<String> removeProductFromCart(@RequestBody List<CartRequestDTO> cartRequestDTO)
			throws Exception {
		logger.info("START ELABORATION ENDPOINT - removeProductFromCart - /cart/remove");
		// Check if all required fields are present or correct
		for (int i = 0; i < cartRequestDTO.size(); i++) {
			if (cartRequestDTO.get(i).getIdProduct() <= 0 
					|| cartRequestDTO.get(i).getIdUtente() <= 0
					|| cartRequestDTO.get(i).getQuantity() <= 0 
					|| !isInteger(cartRequestDTO.get(i).getIdProduct()+"")
					|| !isInteger(cartRequestDTO.get(i).getIdUtente()+"")
					|| !isInteger(cartRequestDTO.get(i).getQuantity()+"")) {
				logger.info("ENDPOINT - removeProductFromCart - some fields are missing or incorrect");
				return new ResponseEntity<String>("Some fields are missing or incorrect", HttpStatus.BAD_REQUEST);
			}
		}
		// Call the cartService to remove the products from the cart
		int countElement = cartServiceImpl.removeProductInCart(cartRequestDTO);

		//check if front-end send element but it not added
		if (countElement == 0 && cartRequestDTO.size()>0) {
			logger.info("ENDPOINT - removeProductFromCart - the list is empty");
			return new ResponseEntity<String>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("END ELABORATION ENDPOINT - removeProductFromCart - /cart/remove");
		return new ResponseEntity<String>(countElement + " product(s) correctly removed from the cart", HttpStatus.OK);
	}
	
	/**
	 * Endpoint to remove all products from the shopping cart.
	 * 
	 * @param user id
	 * @return ResponseEntity with a confirmation message or an error message if an exception occurs
	 * @throws Exception if an error occurs while removing the products from the cart
	 */
	@DeleteMapping("/checkout")
	public ResponseEntity<String> removeAllProductFromCart(@RequestBody CartCheckoutRequest cartCheckoutRequest)
			throws Exception {
		logger.info("START ELABORATION ENDPOINT - removeProductFromCart - /cart/checkout");
		// Check if all required fields are present or correct
		if (cartCheckoutRequest.getIdUtente() <= 0 || !isInteger(cartCheckoutRequest.getIdUtente()+"")) {
			logger.info("ENDPOINT - removeProductFromCart - IdUtente are missing or incorrect");
			return new ResponseEntity<String>("IdUtente are missing or incorrect", HttpStatus.BAD_REQUEST);
		}
		// Call the cartService to remove the products from the cart
		int countElement = cartServiceImpl.removeAllProductInCart(cartCheckoutRequest.getIdUtente());

		if (countElement == 0) {
			logger.info("ENDPOINT - removeProductFromCart - with this IdUtente there are no products in the cart");
			return new ResponseEntity<String>("An error occurred,this User may not have any products in cart", HttpStatus.BAD_REQUEST);
		}
		logger.info("END ELABORATION ENDPOINT - removeProductFromCart - /cart/checkout");
		return new ResponseEntity<String>("All products correctly removed from the cart", HttpStatus.OK);
	}
	
	/*
	 * check if input is valid integer
	 * @param String 
	 * @return true if is a string or false if are not
	 */
	public static boolean isInteger(String input) {
	    try {
	        Integer.parseInt(input);
	        return true; // The input is a valid integer
	    } catch (NumberFormatException e) {
	        return false; // The input is not a valid integer
	    }
	}
}
