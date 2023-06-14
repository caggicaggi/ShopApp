package backend_shop_app.service;

import java.util.List;

import backend_shop_app.dto.request.WishListRequestDTO;

public interface WishListServiceImpl {
	
	/**
	 *  Retrieve the list of products in the wishlist for the given user ID
	 *
	 * @param idUtente       the user ID
	 * @return               the list of products in wishList with a specific idutente
	 */
	public List<Integer> getListOfWishList(int idutente) throws Exception ;
	
	/**
	 *  Add products to the wishlist
	 *
	 * @param wishListRequestDTO       list of products
	 * @return  an integer representing the number of products placed
	 */
	public int addProductInWishList(List<WishListRequestDTO> wishListRequestDTO) throws Exception;
	
	/**
	 * Remove products from the wishlist
	 *
	 * @param wishListRequestDTO       list of products
	 * @return  an integer representing the number of products deleted
	 */
	public int removeProductInWishList(List<WishListRequestDTO> wishListRequestDTO) throws Exception ;
	
	/**
	 *  Deletes all wish list items based on the provided user ID
	 *
	 * @param idUtente       id of user
	 * @return  an integer representing the number of products deleted
	 */
	public int removeAllProductInWishList(int idutente) throws Exception ;
}
