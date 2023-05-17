package shop_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrazioneDTO {
	String surname;
	String name;
	String password;
	String email;
	String phoneNumber;
	String address;
}
