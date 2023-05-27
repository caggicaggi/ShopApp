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
@Table(name = "cart")
@Entity
public class CartDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int idcart;
	private int idproduct;
	private int idutente;
	private int quantity;
}