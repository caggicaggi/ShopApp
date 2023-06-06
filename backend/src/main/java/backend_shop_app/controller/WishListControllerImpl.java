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
import backend_shop_app.dto.request.WishListRequestDTO;
import backend_shop_app.service.WishListService;


/*
 ************ WISHLIST CONTROLLER MANAGEMENT ************ 
 */
@RestController
@RequestMapping(value= "/wishList")
public class WishListControllerImpl implements WishListController {
	
	@Autowired
	WishListService wishListService;
	
	/**
	 * Endpoint to add one or more products to the wishlist.
	 * 
	 * @param wishListRequestDTO list of products to be added to the wishlist
	 * @return ResponseEntity with a confirmation message or an error message if an exception occurs
	 * @throws Exception if an error occurs while adding the products to the wishlist
	 */
    @PutMapping("/add")
	public ResponseEntity<String> addProductInWishList(@RequestBody List<WishListRequestDTO> wishListRequestDTO) throws Exception {
		// Check if all required fields are present or correct
		for (int i = 0; i < wishListRequestDTO.size(); i++) {
			if(wishListRequestDTO.get(i).getIdProduct() <= 0 
					|| wishListRequestDTO.get(i).getIdUtente() <=0 
					||!isInteger(wishListRequestDTO.get(i).getIdUtente()+"") 
					|| !isInteger(wishListRequestDTO.get(i).getIdUtente()+""))
		        return new ResponseEntity<String>("Some field are missing or incorrect ",HttpStatus.BAD_REQUEST);
		}
		
		// Call the service to add products to the wishlist
		int countElement = wishListService.addProductInWishList(wishListRequestDTO);
		
		if(countElement==0)
	        return new ResponseEntity<String>("An error occurred",HttpStatus.INTERNAL_SERVER_ERROR);
		
        return new ResponseEntity<String>(countElement + " Product correctly inserted in the wishlist ",HttpStatus.OK);
	}
    
	/**
	 * Endpoint to add one or more products to the wishlist.
	 * 
	 * @param wishListRequestDTO list of products to be added to the wishlist
	 * @return ResponseEntity with a confirmation message or an error message if an exception occurs
	 * @throws Exception if an error occurs while adding the products to the wishlist
	 */
    @DeleteMapping("/remove")
	public ResponseEntity<String> removeProductInWishList(@RequestBody List<WishListRequestDTO> wishListRequestDTO)
			throws Exception {
		for (int i = 0; i < wishListRequestDTO.size(); i++) {
			// Check if all required fields are present or correct
			if(wishListRequestDTO.get(i).getIdProduct() <= 0 
					|| wishListRequestDTO.get(i).getIdUtente() <=0
					||!isInteger(wishListRequestDTO.get(i).getIdUtente()+"") 
					|| !isInteger(wishListRequestDTO.get(i).getIdUtente()+""))
		        return new ResponseEntity<String>("Some field are missing or incorrect ",HttpStatus.BAD_REQUEST);
		}
		// Call the service to remove products from the wishlist
		int countElement = wishListService.removeProductInWishList(wishListRequestDTO);
		
		if(countElement==0)
	        return new ResponseEntity<String>("An error occurred",HttpStatus.INTERNAL_SERVER_ERROR);
		
        return new ResponseEntity<String>(countElement + " Product correctly removed from the wishlist ",HttpStatus.OK);
	}

    /**
	 * Endpoint to remove all products from the wish list.
	 * 
	 * @param user id
	 * @return ResponseEntity with a confirmation message or an error message if an exception occurs
	 * @throws Exception if an error occurs while removing the products from the wish list
	 */
	@DeleteMapping("/checkout")
	public ResponseEntity<String> removeAllProductFromWishList(@RequestBody CartCheckoutRequest cartCheckoutRequest)
			throws Exception {
		// Check if all required fields are present or correct
		if (cartCheckoutRequest.getIdUtente() <= 0 || !isInteger(cartCheckoutRequest.getIdUtente()+""))
			return new ResponseEntity<String>("IdUtente are missing", HttpStatus.BAD_REQUEST);
		// Call the cartService to remove the products from the cart
		int countElement = wishListService.removeAllProductInWishList(cartCheckoutRequest.getIdUtente());

		if (countElement == 0)
			return new ResponseEntity<String>("An error occurred,this User may not have any products in wishList", HttpStatus.BAD_REQUEST);

		return new ResponseEntity<String>("All products correctly removed from the wish list", HttpStatus.OK);
	}
	
	/*
	 * check if input is valid integer
	 * @param String 
	 * @return true if is a string or false if are not
	 */
	private static boolean isInteger(String input) {
	    try {
	        Integer.parseInt(input);
	        return true; // The input is a valid integer
	    } catch (NumberFormatException e) {
	        return false; // The input is not a valid integer
	    }
	}
}
