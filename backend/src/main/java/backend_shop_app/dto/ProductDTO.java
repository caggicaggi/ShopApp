package backend_shop_app.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class ProductDTO {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int idproduct;
	private String title;
	private String descriprtion;
	private String images1;
	private String images2;
	private String images3; 
	private int rating;
	private int price;
	private String ispopular;
	private String isavailable; 
	private String category;
}