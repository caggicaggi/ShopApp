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
	
	/**
	 *  Find wishlist items by user ID
	 *  
	 *  @param idUtente       the user ID
	 *  @return the list of products in wishList
	 */
	public List<WishListDTO> findIdproductByIdutente(int idutente);
	
	/**
	 *  Delete wishlist items by product ID and user ID
	 *  @param idUtente       the user ID
	 *  @param idproduct      the product ID
	 */
	public void deleteAllByIdproductAndIdutente(int idproduct, int idutente);
	
	
	/**
	 *  Deletes all wish list items based on the provided user ID
	 *  
	 *	@param idUtente       the user ID
	 *	@return  an integer representing the number of products deleted
	 */
	public int deleteAllByIdutente(int idutente);
}
