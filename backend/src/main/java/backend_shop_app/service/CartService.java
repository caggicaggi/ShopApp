package backend_shop_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend_shop_app.dto.CartDTO;
import backend_shop_app.repository.CartRespository;

@Service
public class CartService {
	
	@Autowired
	CartRespository cartRepository;
	
	public List<CartDTO> getListOfIdCart(int idutente) throws Exception {
		//chiamo il repository per prendere la lista dei prodotti nel cart dato l'id utente
		List<CartDTO> listCart = cartRepository.findAllByIdutente( idutente);
		return listCart;
	}
}
