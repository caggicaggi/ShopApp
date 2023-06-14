package backend_shop_app.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Class for Product 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class ProductDTO {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int idproduct; // Identifier for the product - PK
    
	private String title; // Identifier for the product's title
	
	private String descriprtion; // Identifier for the product's descriprtion
	
	private String images1; // Identifier for the product's images1 to print in front-end
	
	private String images2; // Identifier for the product's images2 to print in front-end
	
	private String images3;  // Identifier for the product's images3 to print in front-end
	
	private int rating;  // Identifier for the product's rating
	
	private int price; // Identifier for the product's price
	
	private String ispopular; // Identifier for the product's popularity
	
	private String isavailable; // Identifier for the product's availability
	
	private String category;// Identifier for the product's category
}