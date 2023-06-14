package backend_shop_app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Class for validated email 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpVerificationDTO {

	private String email;
}
