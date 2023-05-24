package shop_app.repository;

import java.util.List;

import shop_app.dto.WishListDTO;

public interface WishListRepository {
	
    public List<WishListDTO> getListOfWishListProduct(int idUtente) throws Exception;

}
