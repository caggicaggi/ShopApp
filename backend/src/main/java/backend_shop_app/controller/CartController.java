package backend_shop_app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import backend_shop_app.dto.request.CartCheckoutRequest;
import backend_shop_app.dto.request.CartRequestDTO;

@RequestMapping(value= "/cart")
@CrossOrigin("*")
public interface CartController {
	
	/**
	 * Method to add one or more products to the shopping cart.
	 * 
	 * @param cartRequestDTO list of products to be added to the cart
	 * @return ResponseEntity with a confirmation message or an error message if an exception occurs
	 * @throws Exception if an error occurs while adding the products to the cart
	 */
	@PutMapping("/add")
	public ResponseEntity<String> addProductInCart( List<CartRequestDTO> cartRequestDTO) 
			throws Exception ;

	/**
	 * Method to remove one or more products from the shopping cart.
	 * 
	 * @param cartRequestDTO list of products to be removed from the cart
	 * @return ResponseEntity with a confirmation message or an error message if an exception occurs
	 * @throws Exception if an error occurs while removing the products from the cart
	 */
	@DeleteMapping("/remove")
	public ResponseEntity<String> removeProductFromCart( List<CartRequestDTO> cartRequestDTO) 
			throws Exception ;
	
	/**
	 * Endpoint to remove all products from the shopping cart.
	 * 
	 * @param user id
	 * @return ResponseEntity with a confirmation message or an error message if an exception occurs
	 * @throws Exception if an error occurs while removing the products from the cart
	 */
	@DeleteMapping("/checkout")
	public ResponseEntity<String> removeAllProductFromCart( CartCheckoutRequest idUtente) 
			throws Exception ;

}
