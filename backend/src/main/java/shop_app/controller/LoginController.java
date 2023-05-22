package shop_app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import shop_app.controller.impl.LoginControllerImpl;
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
public class LoginController implements LoginControllerImpl {
	
	@Autowired
	UtenteService utenteService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	WishListService wishListService;
	
	@Autowired
	CartService cartService;
	
	@Override
	public ResponseEntity<ResponseLoginDTO> login(@RequestBody UtenteDTO utenteDTO) throws Exception {
		try {
			List<String> ret = new ArrayList<>();
			HashMap<String,String> map= utenteService.login(utenteDTO);
			
			if (map.size()==1) {
				if(map.get("ERROR").equals("Incorrect email") || map.get("ERROR").equals("Incorrect password")) {
					ret.add("ERROR: Incorrect email");
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			}
			List<ProductDTO> listOfProduct = productService.getListOfProduct();
			
			List<WishListDTO> listOfWishListProduct = wishListService.getListOfWishListProduct(Integer.parseInt(map.get("IDUTENTE")));

			List<CartDTO> listOfCartProduct = cartService.getListCartProduct(Integer.parseInt(map.get("IDUTENTE")));

			ResponseLoginDTO response = new ResponseLoginDTO(map.get("RESULT"), map.get("IDUTENTE"),
					listOfProduct,listOfWishListProduct,listOfCartProduct);
			
			return new ResponseEntity<>(response,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
