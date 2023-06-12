package backend_shop_app.service;

import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import backend_shop_app.dto.CartDTO;
import backend_shop_app.dto.ProductDTO;
import backend_shop_app.dto.UserDTO;

/*
 * JSON management class
 */
@Service
public class JsonCreateService {
	
	/**
	 * Creates a JSON object to send for sign-up.
	 *
	 * @param token          the JWT token
	 * @param listOfProduct  the list of ProductDTO objects
	 * @param idUtente       the user ID
	 * @return               the JSON object to send
	 */
	public JSONObject createJsonToSendSignUp(String token, List<ProductDTO> listOfProduct, int idUtente) {
		// Create the JSON object
		JSONObject json = new JSONObject();

		// Create and add the tokenJWT array
		JSONArray tokenJWTArray = new JSONArray();
		JSONObject tokenJWTObject = new JSONObject();
		tokenJWTObject.put("value", token);
		tokenJWTArray.put(tokenJWTObject);
		json.put("tokenJWT", tokenJWTArray);

		// Create and add the idUtente array
		JSONArray idUtenteArray = new JSONArray();
		JSONObject idUtenteObject = new JSONObject();
		idUtenteObject.put("value", idUtente);
		idUtenteArray.put(idUtenteObject);
		json.put("idUtente", idUtenteArray);

		// Create and add the ProductDTO array
		JSONArray productDTOArray = new JSONArray();
		for (ProductDTO productDTO : listOfProduct) {
			JSONObject entity = new JSONObject();
			entity.put("idProduct", productDTO.getIdproduct());
			entity.put("title", productDTO.getTitle());
			entity.put("description", productDTO.getDescriprtion());
			entity.put("images1", productDTO.getImages1());
			entity.put("images2", productDTO.getImages2());
			entity.put("images3", productDTO.getImages3());
			entity.put("rating", productDTO.getRating());
			entity.put("price", productDTO.getPrice());
			entity.put("isPopular", productDTO.getIspopular());
			entity.put("isAvailable", productDTO.getIsavailable());
			entity.put("category", productDTO.getCategory());
			productDTOArray.put(entity);
		}

		json.put("ProductDTO", productDTOArray);
		return json;
	}

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
			UserDTO userDTO, List<Integer> listOfIdWishList, List<CartDTO> listOfIdCart) {

		// Create the JSON object
		JSONObject json = new JSONObject();

		// Create and add the tokenJWT array
		JSONArray tokenJWTArray = new JSONArray();
		JSONObject tokenJWTObject = new JSONObject();
		tokenJWTObject.put("value", token);
		tokenJWTArray.put(tokenJWTObject);
		json.put("tokenJWT", tokenJWTArray);

		// Create and add the idUtente array
		JSONArray idUtenteArray = new JSONArray();
		JSONObject idUtenteObject = new JSONObject();
		idUtenteObject.put("value", userDTO.getIdutente());
		idUtenteArray.put(idUtenteObject);
		json.put("idUtente", idUtenteArray);
		
		if (!userDTO.getPhonenumber().equals("Login with google")) {
			// Create and add the idUtente array
			JSONArray PhoneNumeberArray = new JSONArray();
			JSONObject PhoneNumeberObject = new JSONObject();
			PhoneNumeberObject.put("value", userDTO.getPhonenumber());
			PhoneNumeberArray.put(PhoneNumeberObject);
			json.put("phoneNumber", PhoneNumeberArray);
		}
		// Create and add the ProductDTO array
		JSONArray productDTOArray = new JSONArray();
		for (ProductDTO productDTO : listOfProduct) {
			JSONObject entity = new JSONObject();
			entity.put("idProduct", productDTO.getIdproduct());
			entity.put("title", productDTO.getTitle());
			entity.put("description", productDTO.getDescriprtion());
			entity.put("images1", productDTO.getImages1());
			entity.put("images2", productDTO.getImages2());
			entity.put("images3", productDTO.getImages3());
			entity.put("rating", productDTO.getRating());
			entity.put("price", productDTO.getPrice());
			entity.put("isPopular", productDTO.getIspopular());
			entity.put("isAvailable", productDTO.getIsavailable());
			entity.put("category", productDTO.getCategory());
			productDTOArray.put(entity);
		}

		// Create and add the cartDTO array
		JSONArray cartDTO = new JSONArray();
		for (CartDTO cartDTOIdAndQuantity : listOfIdCart) {
			JSONObject entity = new JSONObject();
			entity.put("idProduct", cartDTOIdAndQuantity.getIdproduct());
			entity.put("quantity", cartDTOIdAndQuantity.getQuantity());
			cartDTO.put(entity);
		}

		// Create and add the wishListDTO array
		JSONArray wishListDTO = new JSONArray();
		for (Integer wishListId : listOfIdWishList) {
			JSONObject entity = new JSONObject();
			entity.put("idProduct", wishListId);
			wishListDTO.put(entity);
		}

		json.put("ProductDTO", productDTOArray);
		json.put("WishListDTO", wishListDTO);
		json.put("CartDTO", cartDTO);

		return json;
	}
	
	public JSONObject createJsonToForgotPassword(String email) {
		// Create the JSON object
		JSONObject json = new JSONObject();

		// Create and add the tokenJWT array
		JSONArray emailArray = new JSONArray();
		JSONObject emailObjcet = new JSONObject();
		emailObjcet.put("value", email);
		emailArray.put(emailObjcet);
		json.put("email", emailArray);

		return json;
	}

}
