package backend_shop_app.service;

import java.util.List;

import backend_shop_app.dto.CartDTO;
import backend_shop_app.dto.request.CartRequestDTO;

public interface CartServiceImpl {
	
	/*
	 *  Retrieves the list of products in the cart for the given user ID
	 */
	public List<CartDTO> getListOfIdCart(int idutente) throws Exception;
	
	/*
	 *  Adds products to the cart based on the provided CartRequestDTO list
	 *  if the product is already present, the quantity is updated
	 */
	public int addProductInCart(List<CartRequestDTO> cartRequestDTO) throws Exception;

	/*
	 *  Removes products from the cart based on the provided CartRequestDTO list
	 */
	public int removeProductInCart(List<CartRequestDTO> cartRequestDTO) throws Exception ;
	
	/*
	 *  Deletes all wish list items based on the provided user ID
	 */
	public int removeAllProductInCart(int idutente) throws Exception ;

}
