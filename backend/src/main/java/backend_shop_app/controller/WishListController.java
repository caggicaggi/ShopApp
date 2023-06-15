package backend_shop_app.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import backend_shop_app.dto.request.CartCheckoutRequest;
import backend_shop_app.dto.request.WishListRequestDTO;

@RequestMapping(value= "/wishList")
@CrossOrigin("*")
public interface WishListController {
	
	/**
	 * Method to add one or more products to the wishlist.
	 * 
	 * @param wishListRequestDTO list of products to be added to the wishList
	 * @return ResponseEntity with a confirmation message or an error message if an exception occurs
	 * @throws Exception if an error occurs while adding the products to the wishList
	 */
    @PutMapping("/add")
	public ResponseEntity<String> addProductInWishList( List<WishListRequestDTO> wishListRequestDTO) 
			throws Exception ;
	
	/**
	 * Method to remove one or more products to the wishlist.
	 * 
	 * @param wishListRequestDTO list of products to be added to the wish List
	 * @return ResponseEntity with a confirmation message or an error message if an exception occurs
	 * @throws Exception if an error occurs while adding the products to the wish List
	 */
    @DeleteMapping("/remove")
	public ResponseEntity<String> removeProductInWishList( List<WishListRequestDTO> wishListRequestDTO) 
			throws Exception ;
	
	/**
	 * Endpoint to remove all products from the wish list.
	 * 
	 * @param user id
	 * @return ResponseEntity with a confirmation message or an error message if an exception occurs
	 * @throws Exception if an error occurs while removing the products from wish List
	 */
	@DeleteMapping("/checkout")
	public ResponseEntity<String> removeAllProductFromWishList( CartCheckoutRequest idUtente) 
			throws Exception ;

}
