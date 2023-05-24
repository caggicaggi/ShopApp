package shop_app.repository;

import java.util.List;

import shop_app.dto.CartDTO;

public interface CartRepository {
	
    public List<Integer> getListCartProduct(int idUtente) throws Exception;

    public int removeProductInCart(List<CartDTO> cartDTO) throws Exception;

    public int addPorductInCart(List<CartDTO> cartDTO) throws Exception;
}
