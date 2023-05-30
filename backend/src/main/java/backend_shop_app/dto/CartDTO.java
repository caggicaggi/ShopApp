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
 * Class for cart 
 */
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Table(name = "cart") 
@Entity 
public class CartDTO {
	
    @Id // Marks this field as the primary key of the entity
    @GeneratedValue(strategy = GenerationType.AUTO) // Generates the value for the primary key automatically
    private int idcart; // Unique identifier for the cart

    private int idproduct; // Identifier for the product associated with the cart

    private int idutente; // Identifier for the user associated with the cart

    private int quantity; // Quantity of the product in the cart
}
