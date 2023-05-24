package shop_app.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseLoginDTO {
	
	String result;
	String idUtente;
	List<ProductDTO> listOfProduct;
	List<WishListDTO> wishListDTO;
	List<CartDTO> cartDTO;
}
