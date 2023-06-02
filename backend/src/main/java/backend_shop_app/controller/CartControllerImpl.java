package backend_shop_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import backend_shop_app.dto.CartCheckoutRequest;
import backend_shop_app.dto.CartRequestDTO;
import backend_shop_app.service.CartService;


/*
 ************ CART CONTROLLER MANAGEMENT  ************ 
 */
@RestController
@RequestMapping(value= "/cart")
public class CartControllerImpl implements CartController {

	@Autowired
	CartService cartService;

	/**
	 * Endpoint to add one or more products to the shopping cart.
	 * 
	 * @param cartRequestDTO list of products to be added to the cart
	 * @return ResponseEntity with a confirmation message or an error message if an exception occurs
	 * @throws Exception if an error occurs while adding the products to the cart
	 */
	@PutMapping("/add")
	public ResponseEntity<String> addProductInCart(List<CartRequestDTO> cartRequestDTO) throws Exception {
		// Check if all required fields are present
		for (int i = 0; i < cartRequestDTO.size(); i++) {
			if ( cartRequestDTO.get(i).getIdProduct() <= 0 
					|| cartRequestDTO.get(i).getIdUtente() <= 0
					|| cartRequestDTO.get(i).getQuantity() <= 0 
					|| !isInteger(cartRequestDTO.get(i).getIdProduct()+"")
					|| !isInteger(cartRequestDTO.get(i).getIdUtente()+"")
					|| !isInteger(cartRequestDTO.get(i).getQuantity()+""))
				return new ResponseEntity<String>("Some fields are missing or incorrect", HttpStatus.BAD_REQUEST);
		}
		// Call the cartService to add the products to the cart
		int countElement = cartService.addProductInCart(cartRequestDTO);

		if (countElement == 0)
			return new ResponseEntity<String>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);

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
	public ResponseEntity<String> removeProductFromCart(List<CartRequestDTO> cartRequestDTO)
			throws Exception {
		// Check if all required fields are present or correct
		for (int i = 0; i < cartRequestDTO.size(); i++) {
			if (cartRequestDTO.get(i).getIdProduct() <= 0 
					|| cartRequestDTO.get(i).getIdUtente() <= 0
					|| cartRequestDTO.get(i).getQuantity() <= 0 
					|| !isInteger(cartRequestDTO.get(i).getIdProduct()+"")
					|| !isInteger(cartRequestDTO.get(i).getIdUtente()+"")
					|| !isInteger(cartRequestDTO.get(i).getQuantity()+""))
				return new ResponseEntity<String>("Some fields are missing or incorrect", HttpStatus.BAD_REQUEST);
		}
		// Call the cartService to remove the products from the cart
		int countElement = cartService.removeProductInCart(cartRequestDTO);

		if (countElement == 0)
			return new ResponseEntity<String>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);

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
	public ResponseEntity<String> removeAllProductFromCart(CartCheckoutRequest cartCheckoutRequest)
			throws Exception {
		// Check if all required fields are present or correct
		if (cartCheckoutRequest.getIdUtente() <= 0 || !isInteger(cartCheckoutRequest.getIdUtente()+""))
			return new ResponseEntity<String>("IdUtente are missing or incorrect", HttpStatus.BAD_REQUEST);
		// Call the cartService to remove the products from the cart
		int countElement = cartService.removeAllProductInCart(cartCheckoutRequest.getIdUtente());

		if (countElement == 0)
			return new ResponseEntity<String>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);

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
