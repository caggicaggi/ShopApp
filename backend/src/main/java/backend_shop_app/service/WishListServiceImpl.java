package backend_shop_app.service;

import java.util.List;

import backend_shop_app.dto.request.WishListRequestDTO;

public interface WishListServiceImpl {
	
	public List<Integer> getListOfWishList(int idutente) throws Exception ;
	
	public int addProductInWishList(List<WishListRequestDTO> wishListRequestDTO) throws Exception;
	
	public int removeProductInWishList(List<WishListRequestDTO> wishListRequestDTO) throws Exception ;
	
	public int removeAllProductInWishList(int idutente) throws Exception ;
}
