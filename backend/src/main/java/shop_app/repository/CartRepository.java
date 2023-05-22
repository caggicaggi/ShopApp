package shop_app.repository;

import java.util.List;

import shop_app.dto.CartDTO;

public interface CartRepository {
	
    public List<CartDTO> getListCartProduct(int idUtente) throws Exception;

}
