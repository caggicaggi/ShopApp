package backend_shop_app.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend_shop_app.dto.CartDTO;


/*
 * This interface extends the JpaRepository interface provided by Spring Data JPA 
 * to handle CRUD operations on the CartDTO entity. It provides methods to retrieve 
 * the list of cart items for a given user ID and to delete cart items
 *  based on the product ID, user ID, and quantity
 */
@Transactional
public interface CartRespository extends JpaRepository<CartDTO,Integer>{
	
	/**
	 *  Retrieves the list of products in the cart for the given user ID
	 *  @param  idUtente       the user ID
	 *  @return the list of products in cart
	 */
	public List<CartDTO> findAllByIdutente(int idutente);
	
	/**
	 *  Deletes all cart items based on the provided product ID, user ID, and quantity
	 *  
	 *  @param  idUtente        the user ID
	 *  @param  idproduct       the idproduct ID
	 */
	public void deleteAllByIdproductAndIdutente(int idproduct, int idutente);
	
	/**
	 *  Get determinate product with idProduct and idUtente
	 *  
	 *  @param  idUtente        the user ID
	 *  @param  idproduct       the idproduct ID
	 *  @return the list of products in cart
	 */
	public CartDTO findAllByIdproductAndIdutente (int idproduct, int idutente);
	
	/**
	 *  Deletes all cart items based on the provided product ID
	 *  
	 *  @param  idUtente        the user ID
	 */
	public void deleteAllByIdutente(int idutente);
}
