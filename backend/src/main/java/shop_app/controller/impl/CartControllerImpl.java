package shop_app.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import shop_app.controller.CartController;
import shop_app.dto.CartDTO;
import shop_app.service.CartService;

@RestController
public class CartControllerImpl implements CartController{
	
	@Autowired
	CartService cartService;
	
	@Override
	public ResponseEntity<String> removeProductInCart(List<CartDTO> ListCartDTO) throws Exception {
		if (ListCartDTO.get(0).equals(null)) {
			return new ResponseEntity<>("An error was occured, is a empty list. ",HttpStatus.BAD_REQUEST);
		}
		int checkInsert = cartService.removeProductInCart(ListCartDTO);
		
		if(checkInsert == 0)
			return new ResponseEntity<>("An error was occured ",HttpStatus.INTERNAL_SERVER_ERROR);
		
		return new ResponseEntity<>("product correctly removed from cart ",HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> addProductInCart(List<CartDTO> ListCartDTO) throws Exception {
		if (ListCartDTO.get(0).equals(null)) {
			return new ResponseEntity<>("An error was occured, is a empty list. ",HttpStatus.BAD_REQUEST);
		}
		int checkInsert = cartService.addPorductInCart(ListCartDTO);
		
		if(checkInsert == 0)
			return new ResponseEntity<>("An error was occured ",HttpStatus.INTERNAL_SERVER_ERROR);
		
		return new ResponseEntity<>("product correctly inserted in cart ",HttpStatus.OK);
	}

}
