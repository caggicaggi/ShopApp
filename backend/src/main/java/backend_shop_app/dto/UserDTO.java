package backend_shop_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * Class for singup 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "utente")
public class UserDTO {
    @Id // Marks this field as the primary key of the entity
    @GeneratedValue(strategy = GenerationType.AUTO) // Generates the value for the primary key automatically
    private int idutente; // Unique identifier for the user - PK
    
    private String email; // Identifier for user's email
    
    private String password; // Identifier for user's password
    
    private String name; // Identifier for user's name
    
    private String surname; // Identifier for user's surname
    
    private String address; // Identifier for user's address
    
    private String phonenumber; // Identifier for user's phonenumber
    
    private String salt;// Identifier for user's salt
}
