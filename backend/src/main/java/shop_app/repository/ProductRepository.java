package shop_app.repository;

import java.util.List;

import shop_app.dto.ProductDTO;
import shop_app.dto.WishListDTO;

public interface ProductRepository {
	
	public List<ProductDTO> getListOfProduct() throws Exception;
	
	public List<WishListDTO> getListOfWishListProduct() throws Exception;

}
