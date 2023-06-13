package backend_shop_app.service;

import java.util.List;

import org.json.JSONObject;

import backend_shop_app.dto.CartDTO;
import backend_shop_app.dto.ProductDTO;
import backend_shop_app.dto.UserDTO;

public interface JsonCreateServiceImpl {

	public JSONObject createJsonToSendSignUp(String token, List<ProductDTO> listOfProduct, int idUtente);
	
	public JSONObject createJsonToSendSignIn(String token, List<ProductDTO> listOfProduct,
			UserDTO userDTO, List<Integer> listOfIdWishList, List<CartDTO> listOfIdCart);
	
	public JSONObject createJsonToForgotPassword(String email);
}
