package shop_app.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.Entity;
import shop_app.controller.RegistrazioneController;
import shop_app.dto.ProductDTO;
import shop_app.dto.RegistrazioneDTO;
import shop_app.service.ProductService;
import shop_app.service.UtenteService;

@RestController
public class RegistrazioneControllerImpl implements RegistrazioneController {
	
	@Autowired
	UtenteService utenteService;
	
	@Autowired
	ProductService productService;
	
	@Override
	public ResponseEntity<String> signup(RegistrazioneDTO registrazioneDTO) throws Exception {
		
		if (registrazioneDTO.getName() == null || registrazioneDTO.getSurname() == null ||
				registrazioneDTO.getAddress()==null || registrazioneDTO.getPhoneNumber() ==null
				|| registrazioneDTO.getEmail() == null || registrazioneDTO.getPassword() == null ) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		int checkInsert= utenteService.signup(registrazioneDTO);
		
		if (checkInsert == 0) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (checkInsert == 2) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<ProductDTO> listOfProduct = productService.getListOfProduct() ;
		
		int idUtente = utenteService.getIdUtente(registrazioneDTO.getSurname(),
				registrazioneDTO.getEmail(),registrazioneDTO.getPhoneNumber());
		
		if (idUtente == 0) 
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		List<JSONObject> entities = createJsonToSend(listOfProduct,idUtente);
		     
        return new ResponseEntity<String>(entities.toString(),HttpStatus.OK);
	}

	
	
	
	private List<JSONObject> createJsonToSend(List<ProductDTO> listOfProduct, int idUtente) {
		List<JSONObject> entities = new ArrayList<JSONObject>();
		JSONObject entityIdUtente = new JSONObject();
        entityIdUtente.put("idUtente", idUtente);
        entities.add(entityIdUtente);
        
		 for (ProductDTO productDTO : listOfProduct) {
		        JSONObject entity = new JSONObject();
		        entity.put("idProduct",productDTO.getIdProduct());
		        entity.put("title", productDTO.getTitle());
		        entity.put("description", productDTO.getDescriprtion());
		        entity.put("images1", productDTO.getImages1());
		        entity.put("images2", productDTO.getImages2());
		        entity.put("images3", productDTO.getImages3());
		        entity.put("rating", productDTO.getRating());
		        entity.put("price", productDTO.getPrice());
		        entity.put("isPopular", productDTO.getIsPopular());
		        entity.put("isAvailable", productDTO.getIsAvailable());
		        entity.put("category", productDTO.getCategory());
		        entities.add(entity);
		}
		 return entities;
	}
	
	
	

}
