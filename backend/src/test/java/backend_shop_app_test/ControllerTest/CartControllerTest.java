package backend_shop_app_test.ControllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import java.nio.charset.Charset;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import backend_shop_app.controller.CartControllerImpl;
import backend_shop_app.service.CartServiceImpl;

public class CartControllerTest {

	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private static final String ENDPOINT_ADD = "/cart/add";

	private static final String ENDPOINT_REMOVE = "/cart/remove";

	private static final String ENDPOINT_CHECKOUT = "/cart/checkout";
	
	private MockMvc mockMvc;
	
	@Mock
    private CartServiceImpl cartService;
	
	@InjectMocks
	CartControllerImpl controller;
	
	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
		//mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new ErrorHandler()).build();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        // Define the behavior of the mock object
        when(cartService.addProductInCart(any())).thenReturn(1);
        when(cartService.removeProductInCart(any())).thenReturn(1);
        when(cartService.removeAllProductInCart(anyInt())).thenReturn(1);
	}
	
	@Test
	public void testaddProductInCartEndpoint_Ok() {

		try {			
			// Create an JSONobject 
			JSONObject JSONObject = new JSONObject();
			JSONObject.put("idUtente", 1);
			JSONObject.put("idProduct", 1);
			JSONObject.put("quantity", 1);

			JSONObject JSONObject2 = new JSONObject();
			JSONObject2.put("idUtente", 2);
			JSONObject2.put("idProduct", 2);
			JSONObject2.put("quantity", 1);

			
			MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(ENDPOINT_ADD).contentType(APPLICATION_JSON_UTF8)
					.content("["+JSONObject.toString()+","+JSONObject2.toString()+"]");
				
			mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().is(200));	
		
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void testaddProductInCartEndpoint_Error() {

		try {
	        when(cartService.addProductInCart(any())).thenReturn(0);

			// Create an JSONobject 
			JSONObject JSONObject = new JSONObject();
			JSONObject.put("idUtente", 1);
			JSONObject.put("idProduct", 1);
			JSONObject.put("quantity", 1);

			JSONObject JSONObject2 = new JSONObject();
			JSONObject2.put("idUtente", 2);
			JSONObject2.put("idProduct", 2);
			JSONObject2.put("quantity", 1);

			
			MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(ENDPOINT_ADD).contentType(APPLICATION_JSON_UTF8)
					.content("["+JSONObject+","+JSONObject2+"]");
				
			mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().is(500));	
		
		}catch(Exception e) {
		}
	}
	
	
	@Test
	public void testremoveProductInCartEndpoint_OK() {

		try {

			// Create an JSONobject 
			JSONObject JSONObject = new JSONObject();
			JSONObject.put("idUtente", 1);
			JSONObject.put("idProduct", 1);
			JSONObject.put("quantity", 1);

			JSONObject JSONObject2 = new JSONObject();
			JSONObject2.put("idUtente", 2);
			JSONObject2.put("idProduct", 2);
			JSONObject2.put("quantity", 1);

			MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(ENDPOINT_REMOVE).contentType(APPLICATION_JSON_UTF8)
					.content("["+JSONObject.toString()+","+JSONObject2.toString()+"]");
				
			mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().is(200));	
		
		}catch(Exception e) {
		}
	}
	
	@Test
	public void testremoveProductInCartEndpoint_Error() {

		try {
	        when(cartService.removeProductInCart(any())).thenReturn(0);

			// Create an JSONobject 
			JSONObject JSONObject = new JSONObject();
			JSONObject.put("idUtente", 1);
			JSONObject.put("idProduct", 1);
			JSONObject.put("quantity", 1);

			JSONObject JSONObject2 = new JSONObject();
			JSONObject2.put("idUtente", 2);
			JSONObject2.put("idProduct", 2);
			JSONObject2.put("quantity", 1);

			MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(ENDPOINT_REMOVE).contentType(APPLICATION_JSON_UTF8)
					.content("["+JSONObject.toString()+","+JSONObject2.toString()+"]");
				
			mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().is(500));	
		
		}catch(Exception e) {
		}
}
	@Test
	public void testremoveAllProductFromCartEndpoint_Ok() {

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
	public void testremoveAllProductFromCartEndpoint_Error() {

			try {				
		        when(cartService.removeAllProductInCart(anyInt())).thenReturn(0);

				// Create an JSONobject 
				JSONObject JSONObject = new JSONObject();
				JSONObject.put("idUtente", 0);
				
				MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(ENDPOINT_CHECKOUT).contentType(APPLICATION_JSON_UTF8)
						.content(JSONObject.toString());
					
				mockMvc.perform(request)
				.andExpect(MockMvcResultMatchers.status().is(400));	
			
			}catch(Exception e) {
			}
	}

}
