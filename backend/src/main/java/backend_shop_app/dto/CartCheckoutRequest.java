package backend_shop_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Class for checkout request front-end
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartCheckoutRequest {
	
    private int idUtente; // Identifier for the user 

}
