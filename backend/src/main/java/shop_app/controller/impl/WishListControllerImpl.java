package shop_app.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import shop_app.controller.WishListController;
import shop_app.dto.WishListDTO;
import shop_app.service.WishListService;

@RestController
public class WishListControllerImpl implements WishListController {
	
	@Autowired
	WishListService wishListService;

	@Override
	public ResponseEntity<String> removeProductInWishList(WishListDTO wishListDTO) throws Exception {
		if (wishListDTO.getIdProduct()== 0 || wishListDTO.getIdUtente() == 0) {
			return new ResponseEntity<>("An error was occured, some fields are missing. ",HttpStatus.BAD_REQUEST);
		}
		int checkInsert = wishListService.removeProductInWishList(wishListDTO);
		
		if(checkInsert == 0)
			return new ResponseEntity<>("An error was occured ",HttpStatus.INTERNAL_SERVER_ERROR);
		
		return new ResponseEntity<>("product correctly removed from wishList  ",HttpStatus.OK);
	}


	@Override
	public ResponseEntity<String> addProductInWishList(WishListDTO wishListDTO) throws Exception {
		if (wishListDTO.getIdProduct()== 0 || wishListDTO.getIdUtente() == 0) {
			return new ResponseEntity<>("An error was occured, some fields are missing. ",HttpStatus.BAD_REQUEST);
		}
		int checkInsert = wishListService.addPorductInWishList(wishListDTO);
		
		if(checkInsert == 0)
			return new ResponseEntity<>("An error was occured ",HttpStatus.INTERNAL_SERVER_ERROR);
		
		return new ResponseEntity<>("product correctly added in wishList ",HttpStatus.OK);
	}


}
