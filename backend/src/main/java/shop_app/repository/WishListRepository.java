package shop_app.repository;

import java.util.List;

import shop_app.dto.WishListDTO;

public interface WishListRepository {
	
    public List<Integer> getListOfWishListProduct(int idUtente) throws Exception;
    
    public int removeProductInWishList(WishListDTO wishListDTO) throws Exception;

    public int addPorductInWishList(WishListDTO wishListDTO) throws Exception;
}
