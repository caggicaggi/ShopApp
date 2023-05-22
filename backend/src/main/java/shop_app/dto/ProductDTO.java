package shop_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	int idProduct;
	String title;
	String descriprtion;
	String images1;
	String images2;
	String images3; 
	int rating;
	int price;
	String popular;
	String isAvailable; 
	String category;
}
