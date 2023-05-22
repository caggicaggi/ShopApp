package shop_app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop_app.dto.CartDTO;
import shop_app.repository.CartRepository;
import shop_app.service.CartService;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	CartRepository cartRepository;
	
	@Override
	public List<CartDTO> getListCartProduct(int idUtente) throws Exception {
		return cartRepository.getListCartProduct(idUtente);
	}

}
