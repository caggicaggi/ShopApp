package backend_shop_app.service;

import java.util.List;

import org.json.JSONObject;

import backend_shop_app.dto.CartDTO;
import backend_shop_app.dto.ProductDTO;
import backend_shop_app.dto.UserDTO;

public interface JsonCreateService {
	
	/**
	 * Creates a JSON object to send for sign-up.
	 *
	 * @param token          the JWT token
	 * @param listOfProduct  the list of ProductDTO objects
	 * @param idUtente       the user ID
	 * @return               the JSON object to send
	 */
	public JSONObject createJsonToSendSignUp(String token, List<ProductDTO> listOfProduct, int idUtente);
	
	/**
	 * Creates a JSON object to send for sign-in.
	 *
	 * @param token              the JWT token
	 * @param listOfProduct      the list of ProductDTO objects
	 * @param idUtente           the user ID
	 * @param listOfIdWishList   the list of wish list IDs
	 * @param listOfIdCart       the list of cart IDs and quantities
	 * @return                   the JSON object to send
	 */
	public JSONObject createJsonToSendSignIn(String token, List<ProductDTO> listOfProduct,
			UserDTO userDTO, List<Integer> listOfIdWishList, List<CartDTO> listOfIdCart);
	
	/**
	 * Creates a JSON object to send for sign-in.
	 *
	 * @param email              the email of user
	 * @return                   the JSON object to send
	 */
	public JSONObject createJsonToForgotPassword(String email);
}
