package shop_app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import shop_app.dto.CartDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/cart")
public interface CartController {
	
	@DeleteMapping(value="/removeProductInCart")
    public ResponseEntity<String> removeProductInCart(@RequestBody List<CartDTO> ListCartDTO)throws Exception;

	@PostMapping(value="/addProductInCart")
    public ResponseEntity<String> addProductInCart(@RequestBody List<CartDTO> ListCartDTO)throws Exception;

}
