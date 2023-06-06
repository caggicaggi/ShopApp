package backend_shop_app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/*
 * Class for login request front-end
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDTO {

    private String email; // Identifier for user's email
    
    private String password; // Identifier for user's password
}
