package backend_shop_app_test.UtilTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import backend_shop_app.controller.CartControllerImpl;
import backend_shop_app.controller.UtenteControllerImpl;
import backend_shop_app.service.CartServiceImpl;
import backend_shop_app.util.JwtUtil;


public class JwtUtilTest {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	  @Autowired
	  private MockMvc mockMvc;
	  
	  @InjectMocks
	  private JwtUtil jwtUtil;
	  
	  @InjectMocks
	  CartControllerImpl cartControllerImpl;
	  
	  @Mock
	  CartServiceImpl cartService;
		
	  @Before
		public void init() throws Exception {
			MockitoAnnotations.initMocks(this);
			mockMvc = MockMvcBuilders.standaloneSetup(cartControllerImpl).setControllerAdvice().build();
			when(cartService.addProductInCart(any())).thenReturn(2);
		}
	  
	  @Test
	  public void testJwtGeneration() throws Exception {
			ReflectionTestUtils.setField(jwtUtil, "secret", "SecretToTest");
	        String password = "prova";
	        String token = jwtUtil.generateToken(password);
	        
	        JSONObject JSONObject = new JSONObject();
			JSONObject.put("idUtente", 1);
			JSONObject.put("idProduct", 1);
			JSONObject.put("quantity", 1);

			JSONObject JSONObject2 = new JSONObject();
			JSONObject2.put("idUtente", 2);
			JSONObject2.put("idProduct", 2);
			JSONObject2.put("quantity", 1);
			
	        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/cart/add").contentType(APPLICATION_JSON_UTF8)
					.content("["+JSONObject.toString()+","+JSONObject2.toString()+"]").header("Barer ", token);
				
			mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().is(200));	
			
	    }
	  
}
