package backend_shop_app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend_shop_app.dto.WishListDTO;
import backend_shop_app.repository.WishListRepository;

@Service
public class WishListService {
	
	@Autowired
	WishListRepository wishListRepository;
	
	public List<Integer> getListOfWishList(int idutente) throws Exception {
		List<WishListDTO> wishList = wishListRepository.findIdproductByIdutente( idutente);
		List<Integer> listOfIdProductInWishList = new ArrayList<>();
		for (WishListDTO wishListDTO : wishList) {
			listOfIdProductInWishList.add(wishListDTO.getIdproduct());
		}
		return listOfIdProductInWishList;
	}
}
