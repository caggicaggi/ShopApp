package backend_shop_app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Class for signup with google request front-end
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestGoogleDTO {

	private String email;
	private String name;
	private String surname;
}
