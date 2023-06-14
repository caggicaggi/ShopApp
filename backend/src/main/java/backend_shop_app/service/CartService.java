package backend_shop_app.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import backend_shop_app.dto.CartDTO;
import backend_shop_app.dto.request.CartRequestDTO;
import backend_shop_app.repository.CartRespository;

/**
 * Cart Management Class
 */
@Service
public class CartService implements CartServiceImpl {
	
	@Autowired
	CartRespository cartRepository;
	
	/*
	 *  Retrieves the list of products in the cart for the given user ID
	 */
	public List<CartDTO> getListOfIdCart(int idutente) throws Exception {
		List<CartDTO> listCart = cartRepository.findAllByIdutente(idutente);
		return listCart;
	}
	
	/*
	 *  Adds products to the cart based on the provided CartRequestDTO list
	 *  if the product is already present, the quantity is updated
	 */
	public int addProductInCart(List<CartRequestDTO> cartRequestDTO) throws Exception {
		int countElement = 0;
		for (int i = 0; i < cartRequestDTO.size(); i++) {
			CartDTO cartDTO = new CartDTO();
			CartDTO cartDTOInDB = new CartDTO();
			cartDTO.setIdproduct(cartRequestDTO.get(i).getIdProduct());
			cartDTO.setIdutente(cartRequestDTO.get(i).getIdUtente());
			cartDTO.setQuantity(cartRequestDTO.get(i).getQuantity());
			try {
				//check if the product is in the cart
				cartDTOInDB = cartRepository.findAllByIdproductAndIdutente(cartDTO.getIdproduct(),cartDTO.getIdutente());
				//if object is null, i can add the product
				if(isObjectIsNull(cartDTOInDB))
				{
					cartRepository.save(cartDTO);
					countElement++;
				} else {
					//delete the previous products
					cartRepository.delete(cartDTOInDB);
					//if object is not null increment only quantity
					cartDTO.setQuantity(cartDTOInDB.getQuantity()+cartDTO.getQuantity());
				    cartRepository.save(cartDTO);
					countElement++;
				}
			} catch (Exception e) {
				throw new Exception("Error occurred while adding products to the cart.");
			}
		}
		return countElement;
	}
	
	/*
	 *  Removes products from the cart based on the provided CartRequestDTO list
	 */
	public int removeProductInCart(List<CartRequestDTO> cartRequestDTO) throws Exception {
		int countElement = 0;
		for (int i = 0; i < cartRequestDTO.size(); i++) {
			try {
				cartRepository.deleteAllByIdproductAndIdutente(
						cartRequestDTO.get(i).getIdProduct(),
						cartRequestDTO.get(i).getIdUtente());
				countElement++;
			} catch (Exception e) {
				throw new Exception("Error occurred while removing products from the cart.");
			}

		}
		return countElement;
	}
	
	/*
	 *  Deletes all wish list items based on the provided user ID
	 */
	public int removeAllProductInCart(int idutente) throws Exception {
		int countElement = 0;
			try {
				cartRepository.deleteAllByIdutente(idutente);
				countElement++;
			} catch (Exception e) {
				throw new Exception("Error occurred while removing products from the cart.");
			}
		return countElement;
	}
	
	
	
	/*
	 * check if a field is null
	 */
	public static boolean isObjectIsNull(CartDTO cartDTO) {
	    return cartDTO == null;
	}

}
