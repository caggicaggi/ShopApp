package backend_shop_app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishListRequestDTO {
	
    private int idProduct;
    private int idUtente;
    
}
