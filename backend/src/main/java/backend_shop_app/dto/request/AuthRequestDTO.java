package backend_shop_app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/*
 * Class for signin request front-end
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDTO {

    private String email; 
    private String password; 
}
