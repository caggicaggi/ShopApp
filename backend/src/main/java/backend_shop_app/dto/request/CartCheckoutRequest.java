package backend_shop_app.dto.request;

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
	
    private int idUtente;  

}
