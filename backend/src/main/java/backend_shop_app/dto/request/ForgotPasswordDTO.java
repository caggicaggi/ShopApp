package backend_shop_app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/*
 * Class for update password request front-end
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ForgotPasswordDTO {
	
	private String email;
	
	private String password;

}
