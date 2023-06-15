package backend_shop_app.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import backend_shop_app.dto.ProductDTO;

/*
 * This interface extends the JpaRepository interface provided by Spring Data JPA 
 * to handle CRUD operations on the CartDTO entity. It provides methods to retrieve 
 * the list of cart items for a given user ID and to delete cart items
 *  based on the product ID, user ID, and quantity
 */
@Transactional
public interface ProductRepository extends JpaRepository<ProductDTO,Integer>{
	
	/**
	 *  Retrieves the list of information about products 
	 *  
	 *  @return the list of Product in db
	 */
    List<ProductDTO> findAll();


}
