package backend_shop_app.service;

import java.util.List;

import backend_shop_app.dto.CartDTO;
import backend_shop_app.dto.request.CartRequestDTO;

public interface CartServiceImpl {
	
	public List<CartDTO> getListOfIdCart(int idutente) throws Exception;
	
	public int addProductInCart(List<CartRequestDTO> cartRequestDTO) throws Exception;

	public int removeProductInCart(List<CartRequestDTO> cartRequestDTO) throws Exception ;
	
	public int removeAllProductInCart(int idutente) throws Exception ;

}
