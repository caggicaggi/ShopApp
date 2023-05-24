package shop_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtenteDTO {
	String idUtente;
	String email;
	String password;
	String salt;
	String phoneNumber;
}
