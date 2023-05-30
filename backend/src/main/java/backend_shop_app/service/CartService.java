package backend_shop_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend_shop_app.dto.CartDTO;
import backend_shop_app.dto.CartRequestDTO;
import backend_shop_app.repository.CartRespository;

/**
 * Cart Management Class
 */
@Service
public class CartService {
	
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
	 */
	public int addProductInCart(List<CartRequestDTO> cartRequestDTO) throws Exception {
		int countElement = 0;
		for (int i = 0; i < cartRequestDTO.size(); i++) {
			CartDTO cartDTO = new CartDTO();
			cartDTO.setIdproduct(cartRequestDTO.get(i).getIdProduct());
			cartDTO.setIdutente(cartRequestDTO.get(i).getIdUtente());
			cartDTO.setQuantity(cartRequestDTO.get(i).getQuantity());
			try {
				cartRepository.save(cartDTO);
				countElement++;
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
				cartRepository.deleteAllByIdproductAndIdutenteAndQuantity(
						cartRequestDTO.get(i).getIdProduct(),
						cartRequestDTO.get(i).getIdUtente(),
						cartRequestDTO.get(i).getQuantity());
				countElement++;
			} catch (Exception e) {
				System.out.println(e.getCause());
				throw new Exception("Error occurred while removing products from the cart.");
			}

		}
		return countElement;
	}
}
