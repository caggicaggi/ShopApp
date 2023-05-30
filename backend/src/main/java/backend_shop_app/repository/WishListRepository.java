package backend_shop_app.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import backend_shop_app.dto.WishListDTO;

/*
 * This interface extends the JpaRepository interface provided by Spring Data JPA 
 * to handle CRUD operations on the CartDTO entity. It provides methods to retrieve 
 * the list of cart items for a given user ID and to delete cart items
 *  based on the product ID, user ID, and quantity
 */
@Transactional
public interface WishListRepository extends JpaRepository<WishListDTO,Integer>{
	
	/*
	 *  Find wishlist items by user ID
	 */
	public List<WishListDTO> findIdproductByIdutente(int idutente);
	
	/*
	 *  Delete wishlist items by product ID and user ID
	 */
	public void deleteAllByIdproductAndIdutente(int idproduct, int idutente);
	
}
