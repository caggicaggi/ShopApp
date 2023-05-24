package shop_app.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishListDTO {
	
	private int idWishList;
	private int idProduct;
	private int idUtente;
	
}
