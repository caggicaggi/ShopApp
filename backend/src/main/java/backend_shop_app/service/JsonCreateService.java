package backend_shop_app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import backend_shop_app.dto.CartDTO;
import backend_shop_app.dto.ProductDTO;

/*
 * Classe per la costruzione dei Json da inviare al front-end
 */

@Service
public class JsonCreateService {
	

	public Map<String,List<JSONObject>> createJsonToSendSignUp(String token,List<ProductDTO> listOfProduct, int idUtente) {
		//creo l'hashmap di ritorno
		Map<String,List<JSONObject>> entities = new HashMap();
		
		//inserisco il token JWT
		List<JSONObject> entityTokenJW = new ArrayList();
		JSONObject tokenJsonObject = new JSONObject();
		tokenJsonObject.put("value", token);
		entityTokenJW.add(tokenJsonObject);
        entities.put("tokenJWT",entityTokenJW);
        
        //inserisco l'id Utente
		List<JSONObject> entityIdUtente = new ArrayList();
		JSONObject idUtenteJsonObject = new JSONObject();
		idUtenteJsonObject.put("value", idUtente);
		entityIdUtente.add(idUtenteJsonObject);
        entities.put("idUtente",entityIdUtente);
        
        //ciclo la lista dei prodotti per  creare una lista di JSONObject
		List<JSONObject> producList = new ArrayList();
		 for (ProductDTO productDTO : listOfProduct) {
		        JSONObject entity = new JSONObject();
		        entity.put("idProduct",productDTO.getIdproduct());
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
		        producList.add(entity);
		}
		 //metto la lista nella mappa
		 entities.put("ProductDTO", producList);
		 
		 return entities;
	}
	
	public Map<String,List<JSONObject>> createJsonToSendSignIn(String token,List<ProductDTO> listOfProduct,
			int idUtente, List<Integer> listOfIdWishList, List<CartDTO> listOfIdCart) {
		//creo l'hashmap di ritorno
		Map<String,List<JSONObject>> entities = new HashMap();
		
		//inserisco il token JWT
		List<JSONObject> entityTokenJW = new ArrayList();
		JSONObject tokenJsonObject = new JSONObject();
		tokenJsonObject.put("value", token);
		entityTokenJW.add(tokenJsonObject);
        entities.put("tokenJWT",entityTokenJW);
        
        //inserisco l'id Utente
		List<JSONObject> entityIdUtente = new ArrayList();
		JSONObject idUtenteJsonObject = new JSONObject();
		idUtenteJsonObject.put("value", idUtente);
		entityIdUtente.add(idUtenteJsonObject);
        entities.put("idUtente",entityIdUtente);
        
        //ciclo la lista dei prodotti per  creare una lista di JSONObject
		List<JSONObject> producList = new ArrayList();
		 for (ProductDTO productDTO : listOfProduct) {
		        JSONObject entity = new JSONObject();
		        entity.put("idProduct",productDTO.getIdproduct());
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
		        producList.add(entity);
		}
		 //metto la lista nella mappa
		 entities.put("ProductDTO", producList);
		 
	    //ciclo la lista dei prodotti nel carrello per  creare una lista di JSONObject
		List<JSONObject> cartList = new ArrayList();
		 for (CartDTO cartDTO : listOfIdCart) {
		        JSONObject entity = new JSONObject();
		        entity.put("idProduct",cartDTO.getIdproduct());
		        entity.put("quantity",cartDTO.getQuantity());
		        cartList.add(entity);
		}
		 //metto la lista nell'hashmap
		 entities.put("CartDTO", cartList);

		//ciclo la lista dei prodotti nella wishList  per  creare una lista di JSONObject
		List<JSONObject> wishList = new ArrayList();
		 for (Integer idProductInWishList : listOfIdWishList) {
		        JSONObject entity = new JSONObject();
		        entity.put("idProduct", idProductInWishList);
		        wishList.add(entity);
		}
		 //metto la lista nell'hashmap
		 entities.put("WishListDTO", wishList);

		 return entities;
	}
}
