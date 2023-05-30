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
 * Class for wishList 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wishlist")
@Entity
public class WishListDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int idwishlist; // Unique identifier for the product in wishList
    
	private int idproduct; // Identifier for user's email
	
	private int idutente; // Identifier for user's email
	
}
