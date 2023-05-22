package shop_app.service;

import java.util.List;

import shop_app.dto.WishListDTO;

public interface WishListService {
	
	 public List<WishListDTO> getListOfWishListProduct(int idUtente) throws Exception;


}
