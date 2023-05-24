package shop_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import shop_app.dto.WishListDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/wishList")
public interface WishListController {

	@DeleteMapping(value="/removeProductInWishList")
    public ResponseEntity<String> removeProductInWishList(@RequestBody WishListDTO cartDTO)throws Exception;

	@PostMapping(value="/addProductInWishList")
    public ResponseEntity<String> addProductInWishList(@RequestBody WishListDTO cartDTO)throws Exception;
}
