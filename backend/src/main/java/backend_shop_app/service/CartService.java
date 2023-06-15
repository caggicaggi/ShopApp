package backend_shop_app.service;

import java.util.List;

import backend_shop_app.dto.CartDTO;
import backend_shop_app.dto.request.CartRequestDTO;

public interface CartService {
	
	/**
	 *  Retrieves the list of products in the cart for the given user ID
	 *  
	 * @param idUtente       the user ID
	 *  @return list of products in cart
	 */
	public List<CartDTO> getListOfIdCart(int idutente) throws Exception;
	
	/**
	 *  Adds products to the cart based on the provided CartRequestDTO list
	 *  
	 *  @param List<CartRequestDTO>		list of product
	 *  @return number of products added in cart
	 */
	public int addProductInCart(List<CartRequestDTO> cartRequestDTO) throws Exception;

	/**
	 *  Removes products from the cart based on the provided CartRequestDTO list
	 *  
	 *  @param List<CartRequestDTO> 		list of product
	 *  @return number of products removed in cart
	 */
	public int removeProductInCart(List<CartRequestDTO> cartRequestDTO) throws Exception ;
	
	/**
	 *  Deletes all wish list items based on the provided user ID
	 *  
	 * 	@param idUtente       the user ID
	 *  @return number of products removed in cart
	 */
	public int removeAllProductInCart(int idutente) throws Exception ;

}
