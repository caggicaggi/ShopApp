package backend_shop_app.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import backend_shop_app.dto.UserDTO;

/*
 * This interface extends the JpaRepository interface provided by Spring Data JPA 
 * to handle CRUD operations on the CartDTO entity. It provides methods to retrieve 
 * the list of cart items for a given user ID and to delete cart items
 *  based on the product ID, user ID, and quantity
 */
@Transactional
public interface UserRepository extends JpaRepository<UserDTO,Integer> {
	
	/**
	 *  Search user given an email
	 *  
	 *  @param  idUtente       the user ID
	 *  @return if exist, the user in db
	 */
    UserDTO findByEmail(String email);

}
