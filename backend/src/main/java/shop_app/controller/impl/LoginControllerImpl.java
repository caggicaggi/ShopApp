package shop_app.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import shop_app.controller.LoginController;
import shop_app.dto.CartDTO;
import shop_app.dto.ProductDTO;
import shop_app.dto.ResponseLoginDTO;
import shop_app.dto.UtenteDTO;
import shop_app.dto.WishListDTO;
import shop_app.service.CartService;
import shop_app.service.ProductService;
import shop_app.service.UtenteService;
import shop_app.service.WishListService;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginControllerImpl implements LoginController {
	
	@Autowired
	UtenteService utenteService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	WishListService wishListService;
	
	@Autowired
	CartService cartService;
	
	@Override
	public ResponseEntity<ResponseLoginDTO> singin(@RequestBody UtenteDTO utenteDTO) throws Exception {
		
			UtenteDTO utente= utenteService.login(utenteDTO);
			
			if (utente == null)
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
			try {
				List<ProductDTO> listOfProduct = productService.getListOfProduct();
				
				List<Integer> listOfWishListProduct = wishListService.getListOfWishListProduct(utente.getIdUtente());
	
				List<Integer> listOfCartProduct = cartService.getListCartProduct(utente.getIdUtente());
	
				ResponseLoginDTO response = new ResponseLoginDTO("Correct Credential",utente.getIdUtente()+" ",
						listOfProduct,listOfWishListProduct,listOfCartProduct);
				
				return new ResponseEntity<>(response,HttpStatus.OK);
				
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
}
