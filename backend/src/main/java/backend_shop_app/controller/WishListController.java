package backend_shop_app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import backend_shop_app.dto.WishListRequestDTO;

public interface WishListController {
	
	/**
	 * Method to add one or more products to the wishlist.
	 * 
	 * @param wishListRequestDTO list of products to be added to the wishList
	 * @return ResponseEntity with a confirmation message or an error message if an exception occurs
	 * @throws Exception if an error occurs while adding the products to the wishList
	 */
	public ResponseEntity<String> addProductInWishList(@RequestBody List<WishListRequestDTO> wishListRequestDTO) 
			throws Exception ;
	
	/**
	 * Method to remove one or more products to the wishlist.
	 * 
	 * @param wishListRequestDTO list of products to be added to the wishList
	 * @return ResponseEntity with a confirmation message or an error message if an exception occurs
	 * @throws Exception if an error occurs while adding the products to the wishList
	 */
	public ResponseEntity<String> removeProductInWishList(@RequestBody List<WishListRequestDTO> wishListRequestDTO) 
			throws Exception ;

}
