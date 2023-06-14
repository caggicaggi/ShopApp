package backend_shop_app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Class for cart request from front-end 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequestDTO {

	    private int idProduct; 
	    private int idUtente; 
	    private int quantity; 
}
