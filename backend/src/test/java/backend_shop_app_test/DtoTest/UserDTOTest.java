package backend_shop_app_test.DtoTest;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import backend_shop_app.dto.UserDTO;

public class UserDTOTest {

    @Test
    public void testUserDTOProperties() {
        UserDTO userDTO = new UserDTO();
        userDTO.setIdutente(1);
        userDTO.setEmail("john.doe@example.com");
        userDTO.setPassword("password123");
        userDTO.setName("John");
        userDTO.setSurname("Doe");
        userDTO.setAddress("123 Main St");
        userDTO.setPhonenumber("555-1234");
        userDTO.setSalt("abcd1234");

        assertEquals(1, userDTO.getIdutente());
        assertEquals("john.doe@example.com", userDTO.getEmail());
        assertEquals("password123", userDTO.getPassword());
        assertEquals("John", userDTO.getName());
        assertEquals("Doe", userDTO.getSurname());
        assertEquals("123 Main St", userDTO.getAddress());
        assertEquals("555-1234", userDTO.getPhonenumber());
        assertEquals("abcd1234", userDTO.getSalt());
    }

    @Test
    public void testUserDTOMissingFields() {
        UserDTO userDTO = new UserDTO();
        userDTO.setIdutente(1);
        userDTO.setEmail("john.doe@example.com");

        assertEquals(1, userDTO.getIdutente());
        assertEquals("john.doe@example.com", userDTO.getEmail());
        assertNull(userDTO.getPassword());
        assertNull(userDTO.getName());
        assertNull(userDTO.getSurname());
        assertNull(userDTO.getAddress());
        assertNull(userDTO.getPhonenumber());
        assertNull(userDTO.getSalt());
    }

    @Test
    public void testUserDTOEmptyFields() {
        UserDTO userDTO = new UserDTO();
        userDTO.setIdutente(1);
        userDTO.setEmail("");
        userDTO.setPassword("");
        userDTO.setName("");
        userDTO.setSurname("");
        userDTO.setAddress("");
        userDTO.setPhonenumber("");
        userDTO.setSalt("");

        assertEquals(1, userDTO.getIdutente());
        assertEquals("", userDTO.getEmail());
        assertEquals("", userDTO.getPassword());
        assertEquals("", userDTO.getName());
        assertEquals("", userDTO.getSurname());
        assertEquals("", userDTO.getAddress());
        assertEquals("", userDTO.getPhonenumber());
        assertEquals("", userDTO.getSalt());
    }
}
