package shop_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtenteDTO {
	private int idUtente;
	private String email;
	private String password;
	private String salt;
	private String phoneNumber;
}
