package shop_app.service;

import java.util.List;

import shop_app.dto.CartDTO;

public interface CartService {
	
    public List<Integer> getListCartProduct(int idUtente) throws Exception;
    
    public int removeProductInCart(List<CartDTO> listCartDTO) throws Exception;

    public int addPorductInCart(List<CartDTO> listCartDTO) throws Exception;

}
