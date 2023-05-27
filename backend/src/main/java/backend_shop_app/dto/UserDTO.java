package backend_shop_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "utente")
public class UserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idutente;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String address;
    private String phonenumber;
    private String salt;
}
