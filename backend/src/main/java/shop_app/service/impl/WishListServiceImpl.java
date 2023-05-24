package shop_app.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop_app.dto.WishListDTO;
import shop_app.repository.WishListRepository;
import shop_app.service.WishListService;

@Service
public class WishListServiceImpl implements WishListService  {
	

	@Autowired
	WishListRepository wishListRepository;
	
    public List<Integer> getListOfWishListProduct(int idUtente) throws Exception {
    	return wishListRepository.getListOfWishListProduct(idUtente);
    }

	@Override
	public int removeProductInWishList(WishListDTO wishListDTO) throws Exception {
		return wishListRepository.removeProductInWishList(wishListDTO);
	}

	@Override
	public int addPorductInWishList(WishListDTO wishListDTO) throws Exception {
		return wishListRepository.addPorductInWishList(wishListDTO);
	}

}
