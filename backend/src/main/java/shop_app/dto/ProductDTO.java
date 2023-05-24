package shop_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	private int idProduct;
	private String title;
	private String descriprtion;
	private String images1;
	private String images2;
	private String images3; 
	private int rating;
	private int price;
	private String isPopular;
	private String isAvailable; 
	private String category;
}
