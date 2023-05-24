package shop_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrazioneDTO {
	
	private String surname;
	private String name;
	private String password;
	private String email;
	private String phoneNumber;
	private String address;
	private String salt;
	
}
