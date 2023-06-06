package backend_shop_app_test.serviceTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;

import backend_shop_app.dto.CartDTO;
import backend_shop_app.dto.ProductDTO;
import backend_shop_app.dto.UserDTO;
import backend_shop_app.service.JsonCreateService;

public class JsonServiceTest {
	 	
	 	@InjectMocks
	 	private JsonCreateService jsonCreateService;
	 	
		@Before
		public void init() throws Exception {
			MockitoAnnotations.initMocks(this);
		}
		
	    @Test
	    public void testValidJsonResponse() throws Exception {
	        String expectedJson = "{"+"email"+":"+"["+"{"+"value"+":"+"email@gmail.com"+"}]}";
	        
	        String actualJson = jsonCreateService.createJsonToForgotPassword("email@gmail.com").toString();
	        JSONAssert.assertEquals(expectedJson, actualJson, true);
	    }
	  
	    
	    @Test
	    public void testValidJsonResponse2() throws Exception {
	    	List<ProductDTO> listOfProduct = new ArrayList<>();
	    	ProductDTO productDto = new ProductDTO();
	    	productDto.setCategory("category");
	    	productDto.setDescriprtion("description");
	    	productDto.setIdproduct(1);
	    	productDto.setImages1("images1");
	    	productDto.setImages2("images2");
	    	productDto.setImages3("images3");
	    	productDto.setIsavailable("isAvailable");
	    	productDto.setIspopular("isPopular");
	    	productDto.setPrice(9);
	    	productDto.setRating(12);
	    	productDto.setTitle("title");
	    	listOfProduct.add(productDto);
	    	
	    	List<Integer> listOfIdWishList = new ArrayList<>();
	    	List<CartDTO> listOfIdCart = new ArrayList<>();
	    	String token="ANKNVEnsvfnjSNEdsvknfADNJN";
	    	UserDTO userDTO = new UserDTO();
	    	userDTO.setPhonenumber("0733880074");
	    	
	        String expectedJson = "{\"phoneNumber\":[{\"value\":\"0733880074\"}],"
	        		+ "\"tokenJWT\":[{\"value\":\"ANKNVEnsvfnjSNEdsvknfADNJN\"}],\"WishListDTO\":[],"
	        		+ "\"idUtente\":[{\"value\":0}],\"CartDTO\":[],\"ProductDTO\":[{\"isAvailable\":\"isAvailable\","
	        		+ "\"price\":9,\"rating\":12,\"idProduct\":1,\"description\":\"description\",\"isPopular\":\"isPopular\","
	        		+ "\"title\":\"title\",\"images1\":\"images1\",\"category\":\"category\","
	        		+ "\"images3\":\"images3\",\"images2\":\"images2\"}]}\r\n";
	        
	        String actualJson =  jsonCreateService.createJsonToSendSignIn(token, listOfProduct,
					userDTO, listOfIdWishList, listOfIdCart).toString();
	        JSONAssert.assertEquals(expectedJson, actualJson, true);
	    }
	    
	    @Test
	    public void testValidJsonResponse3() throws Exception {
	    	List<ProductDTO> listOfProduct = new ArrayList<>();
	    	ProductDTO productDto = new ProductDTO();
	    	productDto.setCategory("category");
	    	productDto.setDescriprtion("description");
	    	productDto.setIdproduct(1);
	    	productDto.setImages1("images1");
	    	productDto.setImages2("images2");
	    	productDto.setImages3("images3");
	    	productDto.setIsavailable("isAvailable");
	    	productDto.setIspopular("isPopular");
	    	productDto.setPrice(9);
	    	productDto.setRating(12);
	    	productDto.setTitle("title");
	    	listOfProduct.add(productDto);
	    	
	    	String token="ANKNVEnsvfnjSNEdsvknfADNJN";
	    	int idUtente = 1;
	    	
	        String expectedJson = "{\"tokenJWT\":[{\"value\":\"ANKNVEnsvfnjSNEdsvknfADNJN\"}],\"idUtente\":[{\"value\":1}],"
	        		+ "\"ProductDTO\":[{\"isAvailable\":\"isAvailable\",\"price\":9,\"rating\":12,\"idProduct\":1,"
	        		+ "\"description\":\"description\",\"isPopular\":\"isPopular\",\"title\":\"title\",\"images1\":\"images1\","
	        		+ "\"category\":\"category\",\"images3\":\"images3\",\"images2\":\"images2\"}]}\r\n";
	        
	        String actualJson =  jsonCreateService.createJsonToSendSignUp(token, listOfProduct, idUtente).toString();

	        JSONAssert.assertEquals(expectedJson, actualJson, true);
	    }
}
