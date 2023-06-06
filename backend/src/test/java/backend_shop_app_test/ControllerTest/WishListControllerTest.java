package backend_shop_app_test.ControllerTest;

import org.json.JSONObject;
import org.junit.Test;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import backend_shop_app.controller.WishListControllerImpl;
import backend_shop_app.service.WishListService;

public class WishListControllerTest {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private static final String ENDPOINT_ADD = "/wishList/add";

	private static final String ENDPOINT_REMOVE = "/wishList/remove";

	private static final String ENDPOINT_CHECKOUT = "/wishList/checkout";
	
	private MockMvc mockMvc;
	
	@Mock
    private WishListService wishListService;
	
	@InjectMocks
	WishListControllerImpl controller;
	
	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
		//mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new ErrorHandler()).build();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        // Define the behavior of the mock object
        when(wishListService.addProductInWishList(any())).thenReturn(1);
        when(wishListService.removeProductInWishList(any())).thenReturn(1);
        when(wishListService.removeAllProductInWishList(anyInt())).thenReturn(1);
	}
	
	@Test
	public void testaddProductInWishListEndpoint_Ok() {

		try {			
			// Create an JSONobject 
			JSONObject JSONObject = new JSONObject();
			JSONObject.put("idUtente", 1);
			JSONObject.put("idProduct", 1);
			JSONObject JSONObject2 = new JSONObject();
			JSONObject2.put("idUtente", 2);
			JSONObject2.put("idProduct", 2);
			
			MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(ENDPOINT_ADD).contentType(APPLICATION_JSON_UTF8)
					.content("["+JSONObject.toString()+","+JSONObject2.toString()+"]");
				
			mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().is(200));	
		
		}catch(Exception e) {
		}
	}
	
	@Test
	public void testaddProductInWishListEndpoint_Error() {

		try {
	        when(wishListService.addProductInWishList(any())).thenReturn(0);

			// Create an JSONobject 
			JSONObject JSONObject = new JSONObject();
			JSONObject.put("idUtente", 1);
			JSONObject.put("idProduct", 1);
			JSONObject JSONObject2 = new JSONObject();
			JSONObject2.put("idUtente", 2);
			JSONObject2.put("idProduct", 2);
			
			MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(ENDPOINT_ADD).contentType(APPLICATION_JSON_UTF8)
					.content("["+JSONObject.toString()+","+JSONObject2.toString()+"]");
				
			mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().is(500));	
		
		}catch(Exception e) {
		}
	}
	
	
	@Test
	public void testremoveProductInWishListEndpoint_OK() {

		try {

			// Create an JSONobject 
			JSONObject JSONObject = new JSONObject();
			JSONObject.put("idUtente", 1);
			JSONObject.put("idProduct", 1);
			JSONObject JSONObject2 = new JSONObject();
			JSONObject2.put("idUtente", 2);
			JSONObject2.put("idProduct", 2);
			
			MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(ENDPOINT_REMOVE).contentType(APPLICATION_JSON_UTF8)
					.content("["+JSONObject.toString()+","+JSONObject2.toString()+"]");
				
			mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().is(200));	
		
		}catch(Exception e) {
		}
	}
	
	@Test
	public void testremoveProductInWishListEndpoint_Error() {

		try {
	        when(wishListService.removeProductInWishList(any())).thenReturn(0);

			// Create an JSONobject 
			JSONObject JSONObject = new JSONObject();
			JSONObject.put("idUtente", 1);
			JSONObject.put("idProduct", 1);
			JSONObject JSONObject2 = new JSONObject();
			JSONObject2.put("idUtente", 2);
			JSONObject2.put("idProduct", 2);
			
			MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(ENDPOINT_REMOVE).contentType(APPLICATION_JSON_UTF8)
					.content("["+JSONObject.toString()+","+JSONObject2.toString()+"]");
				
			mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().is(500));	
		
		}catch(Exception e) {
		}
}
	@Test
	public void testremoveAllProductFromWishListEndpoint_Ok() {

			try {

				// Create an JSONobject 
				JSONObject JSONObject = new JSONObject();
				JSONObject.put("idUtente", 12);
				
				MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(ENDPOINT_CHECKOUT).contentType(APPLICATION_JSON_UTF8)
						.content(JSONObject.toString());
					
				mockMvc.perform(request)
				.andExpect(MockMvcResultMatchers.status().is(200));	
			
			}catch(Exception e) {
			}
	}
	
	@Test
	public void testremoveAllProductFromWishListEndpoint_Error() {

			try {				
		        when(wishListService.removeAllProductInWishList(anyInt())).thenReturn(0);

				// Create an JSONobject 
				JSONObject JSONObject = new JSONObject();
				JSONObject.put("idUtente", 1);
				
				MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(ENDPOINT_CHECKOUT).contentType(APPLICATION_JSON_UTF8)
						.content(JSONObject.toString());
					
				mockMvc.perform(request)
				.andExpect(MockMvcResultMatchers.status().is(400));	
			
			}catch(Exception e) {
			}
	}
}
