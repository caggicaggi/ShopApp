package backend_shop_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import backend_shop_app.dto.WishListRequestDTO;
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
	public ResponseEntity<String> addProductInWishList(List<WishListRequestDTO> wishListRequestDTO) throws Exception {
		for (int i = 0; i < wishListRequestDTO.size(); i++) {
			// Check if any required field is missing in the request
			if(wishListRequestDTO.get(i).getIdProduct() == 0 || wishListRequestDTO.get(i).getIdUtente() ==0)
		        return new ResponseEntity<String>("Some field are missing ",HttpStatus.BAD_REQUEST);
		}
		// Call the service to add products to the wishlist
		int countElement = wishListService.addProductInWishList(wishListRequestDTO);
		
		if(countElement==0)
	        return new ResponseEntity<String>("An error occurred",HttpStatus.INTERNAL_SERVER_ERROR);
		
        return new ResponseEntity<String>(countElement + " Product correctly inserted in wishlist ",HttpStatus.OK);
	}
    
	/**
	 * Endpoint to add one or more products to the wishlist.
	 * 
	 * @param wishListRequestDTO list of products to be added to the wishlist
	 * @return ResponseEntity with a confirmation message or an error message if an exception occurs
	 * @throws Exception if an error occurs while adding the products to the wishlist
	 */
    @DeleteMapping("/remove")
	public ResponseEntity<String> removeProductInWishList(List<WishListRequestDTO> wishListRequestDTO)
			throws Exception {
		for (int i = 0; i < wishListRequestDTO.size(); i++) {
			// Check if any required field is missing in the request
			if(wishListRequestDTO.get(i).getIdProduct() == 0 || wishListRequestDTO.get(i).getIdUtente() ==0)
		        return new ResponseEntity<String>("Some field are missing ",HttpStatus.BAD_REQUEST);
		}
		// Call the service to remove products from the wishlist
		int countElement = wishListService.removeProductInWishList(wishListRequestDTO);
		
		if(countElement==0)
	        return new ResponseEntity<String>("An error occurred",HttpStatus.INTERNAL_SERVER_ERROR);
		
        return new ResponseEntity<String>(countElement + " Product correctly removed from wishlist ",HttpStatus.OK);
	}

}
