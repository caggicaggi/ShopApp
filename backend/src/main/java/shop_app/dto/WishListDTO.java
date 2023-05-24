package shop_app.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishListDTO {
	
	int idWishList;
	int idProduct;
	int idUtente;
	
}
