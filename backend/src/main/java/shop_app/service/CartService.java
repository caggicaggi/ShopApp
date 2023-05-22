package shop_app.service;

import java.util.List;

import shop_app.dto.CartDTO;

public interface CartService {
	
    public List<CartDTO> getListCartProduct(int idUtente) throws Exception;

}
