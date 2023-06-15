package backend_shop_app.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import backend_shop_app.dto.WishListDTO;
import backend_shop_app.dto.request.WishListRequestDTO;
import backend_shop_app.repository.WishListRepository;

/*
 * WishList management class
 */
@Service
public class WishListServiceImpl implements WishListService{
	
	@Autowired
	WishListRepository wishListRepository;
	

	/**
	 *  Retrieve the list of products in the wishlist for the given user ID
	 *
	 * @param idUtente       the user ID
	 * @return               the list of products in wishList with a specific idutente
	 */
	public List<Integer> getListOfWishList(int idutente) throws Exception {
		List<WishListDTO> wishList = wishListRepository.findIdproductByIdutente(idutente);
		List<Integer> listOfIdProductInWishList = new ArrayList<>();
		for (WishListDTO wishListDTO : wishList) {
			listOfIdProductInWishList.add(wishListDTO.getIdproduct());
		}
		return listOfIdProductInWishList;
	}
	
	/**
	 *  Add products to the wishlist
	 *
	 * @param wishListRequestDTO       list of products
	 * @return  an integer representing the number of products placed
	 */
	public int addProductInWishList(List<WishListRequestDTO> wishListRequestDTO) throws Exception {
		int countElement = 0;
		for (int i = 0; i < wishListRequestDTO.size(); i++) {
			WishListDTO wishListDTO = new WishListDTO();
			wishListDTO.setIdproduct(wishListRequestDTO.get(i).getIdProduct());
			wishListDTO.setIdutente(wishListRequestDTO.get(i).getIdUtente());
			try {
				wishListRepository.save(wishListDTO);
				countElement++;
			} catch (Exception e) {
				throw new Exception("An error occurred while adding products to the wishlist.");
			}
		}
		return countElement;
	}
	
	/**
	 * Remove products from the wishlist
	 *
	 * @param wishListRequestDTO       list of products
	 * @return  an integer representing the number of products deleted
	 */
	public int removeProductInWishList(List<WishListRequestDTO> wishListRequestDTO) throws Exception {
		int countElement = 0;
		for (int i = 0; i < wishListRequestDTO.size(); i++) {
			try {
				wishListRepository.deleteAllByIdproductAndIdutente(wishListRequestDTO.get(i).getIdProduct(),
						wishListRequestDTO.get(i).getIdUtente());
				countElement++;
			} catch (Exception e) {
				throw new Exception("An error occurred while removing products from the wishlist.");
			}
		}
		return countElement;
	}
	
	
	/**
	 *  Deletes all wish list items based on the provided user ID
	 *  
	 * @param idUtente       the user ID
	 * @return  an integer representing the number of products deleted
	 */
	public int removeAllProductInWishList(int idutente) throws Exception {
		int countElement = 0;
			try {
				countElement =wishListRepository.deleteAllByIdutente(idutente);
			} catch (Exception e) {
				throw new Exception("Error occurred while removing products from the cart.");
			}
		return countElement;
	}
}
