package backend_shop_app_test.ControllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import java.nio.charset.Charset;
import org.springframework.http.MediaType;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import backend_shop_app.controller.UtenteControllerImpl;
import backend_shop_app.dto.UserDTO;
import backend_shop_app.service.CartService;
import backend_shop_app.service.CustomUserDetailsService;
import backend_shop_app.service.JsonCreateService;
import backend_shop_app.service.ProductService;
import backend_shop_app.service.WishListService;
import backend_shop_app.util.JwtUtil;


public class UtenteControllerTest {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private static final String ENDPOINT_LOGIN = "/signin";
	
	private static final String ENDPOINT_GOOGLE = "/google";

	private static final String ENDPOINT_SIGNUP = "/signup";
	
	private static final String ENDPOINT_GETPHONENUMBER = "/getPhoneNumber";
	
	private static final String ENDPOINT_UPDATEPASSWORD = "/updatePassword";

	private MockMvc mockMvc;
	
	@InjectMocks
	private UtenteControllerImpl controller;
	
	@Mock
    private CustomUserDetailsService customUserDetailsService;
	
	@Mock
    private ProductService productService;
    
	@Mock
    private WishListService wishListService;
    
	@Mock
    private CartService cartService;
    
	@Mock
    private JsonCreateService jsonCreateService;
	
	@Mock
	private JwtUtil jwtUtil;
	
	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
		//mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new ErrorHandler()).build();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
     // Define the behavior of the mock object
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("bomba@gmail.com");
        userDTO.setPassword("bomba");
        JSONObject jsonToSend = new JSONObject();
        when(customUserDetailsService.getUserDTO(any())).thenReturn(userDTO);
        when(customUserDetailsService.cryptoPassword(any())).thenReturn(userDTO);
        when(jsonCreateService.createJsonToSendSignIn(any(), any(), any(),
    			any(), any())).thenReturn(jsonToSend);
        when(jsonCreateService.createJsonToSendSignUp(any(), any(),anyInt()))
        	.thenReturn(jsonToSend);
        when(jsonCreateService.createJsonToForgotPassword(anyString())).thenReturn(jsonToSend);
        when(customUserDetailsService.getUserInformation(any())).thenReturn(userDTO);
        when(customUserDetailsService.cryptoPassword(any())).thenReturn(userDTO);

	}
	
	@Test
	public void testLoginEndpoint_Error() {

		try {			
			// Create an JSONobject for email
			JSONObject JSONObject = new JSONObject();
			JSONObject.put("email", "bombagmail.com");
			JSONObject.put("password", "bomba");
			
			MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(ENDPOINT_LOGIN).contentType(APPLICATION_JSON_UTF8)
					.content(JSONObject.toString());
				
			mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().is(400));	
			
			JSONObject.put("email", "bombagmail.com");
			JSONObject.put("password", "");
			
			MockHttpServletRequestBuilder requestBadPassword  = MockMvcRequestBuilders.post(ENDPOINT_LOGIN).contentType(APPLICATION_JSON_UTF8)
					.content(JSONObject.toString());
				
			mockMvc.perform(requestBadPassword)
			.andExpect(MockMvcResultMatchers.status().is(400));	
			
			JSONObject.put("email", "");
			JSONObject.put("password", "bomba");
			
			MockHttpServletRequestBuilder requestBadEmail = MockMvcRequestBuilders.post(ENDPOINT_LOGIN).contentType(APPLICATION_JSON_UTF8)
					.content(JSONObject.toString());
				
			mockMvc.perform(requestBadEmail)
			.andExpect(MockMvcResultMatchers.status().is(400));	
		
		}catch(Exception e) {
		}
	}
	
	@Test
	public void testLoginEndpoint_Ok() {

		try {			
			// Create an JSONobject for email
			JSONObject JSONObject = new JSONObject();
			JSONObject.put("email", "bomba@gmail.com");
			JSONObject.put("password", "bomba");
			
			MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(ENDPOINT_LOGIN).contentType(APPLICATION_JSON_UTF8)
					.content(JSONObject.toString());
				
			mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().is(200));	
		
		}catch(Exception e) {
		}
	}
	
	@Test
	public void testsingUpGoogleEndpoint_Ok() {

		try {			
			// Create an JSONobject for email
			JSONObject JSONObject = new JSONObject();
			JSONObject.put("email", "bomba@gmail.com");
			JSONObject.put("name", "bomba");
			JSONObject.put("surname", "bomba");

			MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(ENDPOINT_GOOGLE).contentType(APPLICATION_JSON_UTF8)
					.content(JSONObject.toString());
				
			mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().is(200));	
		
		}catch(Exception e) {
		}
	}
	
	@Test
	public void testsingUpGoogleEndpoint_Ok2() {

		try {			
			when(customUserDetailsService.getUserInformation(any())).thenReturn(null) ;

			// Create an JSONobject for email
			JSONObject JSONObject = new JSONObject();
			JSONObject.put("email", "bomba@gmail.com");
			JSONObject.put("name", "bomba");
			JSONObject.put("surname", "bomba");

			MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(ENDPOINT_GOOGLE).contentType(APPLICATION_JSON_UTF8)
					.content(JSONObject.toString());
				
			mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().is(200));	
		
		}catch(Exception e) {
		}
	}
	
	@Test
	public void testsingUpEndpoint() {

		try {			
			// Create an JSONobject for email
			JSONObject JSONObject = new JSONObject();
			JSONObject.put("email", "bomba@gmail.com");
			JSONObject.put("password", "bomba");
			JSONObject.put("address", "via bomba 13");
			JSONObject.put("phonenumber", "3920872956");
			JSONObject.put("name", "Marco");
			JSONObject.put("surname", "Verdi");

			MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(ENDPOINT_SIGNUP).contentType(APPLICATION_JSON_UTF8)
					.content(JSONObject.toString());
				
			mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().is(200));	
		
		}catch(Exception e) {
		}
	}
	
	@Test
	public void testsingUpEndpoint_Error() {

		try {			
			// Create an JSONobject for email
			JSONObject JSONObject = new JSONObject();

			MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(ENDPOINT_SIGNUP).contentType(APPLICATION_JSON_UTF8)
					.content(JSONObject.toString());
				
			mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().is(400));	
		
		}catch(Exception e) {
		}
		try {			
			// Create an JSONobject for email
			JSONObject JSONObject = new JSONObject();
			JSONObject.put("email", "");
			JSONObject.put("password", "bomba");
			JSONObject.put("address", "via bomba 13");
			JSONObject.put("phonenumber", "3920872956");
			JSONObject.put("name", "Marco");
			JSONObject.put("surname", "Verdi");

			MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(ENDPOINT_SIGNUP).contentType(APPLICATION_JSON_UTF8)
					.content(JSONObject.toString());
				
			mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().is(400));	
		
		}catch(Exception e) {
		}
		try {			
			// Create an JSONobject
			JSONObject JSONObject = new JSONObject();
			JSONObject.put("email", "bomba@gmail.com");
			JSONObject.put("password", "");
			JSONObject.put("address", "via bomba 13");
			JSONObject.put("phonenumber", "3920872956");
			JSONObject.put("name", "Marco");
			JSONObject.put("surname", "Verdi");

			MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(ENDPOINT_SIGNUP).contentType(APPLICATION_JSON_UTF8)
					.content(JSONObject.toString());
				
			mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().is(400));	
		
		}catch(Exception e) {
		}
		try {			
			// Create an JSONobject
			JSONObject JSONObject = new JSONObject();
			JSONObject.put("email", "bomba@gmail.com");
			JSONObject.put("password", "bomba");
			JSONObject.put("address", "");
			JSONObject.put("phonenumber", "3920872956");
			JSONObject.put("name", "Marco");
			JSONObject.put("surname", "Verdi");

			MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(ENDPOINT_SIGNUP).contentType(APPLICATION_JSON_UTF8)
					.content(JSONObject.toString());
				
			mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().is(400));	
		
		}catch(Exception e) {
		}
		try {			
			// Create an JSONobject for email
			JSONObject JSONObject = new JSONObject();
			JSONObject.put("email", "bomba@gmail.com");
			JSONObject.put("password", "bomba");
			JSONObject.put("address", "via bomba 13");
			JSONObject.put("phonenumber", "392431520872956");
			JSONObject.put("name", "Marco");
			JSONObject.put("surname", "Verdi");

			MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(ENDPOINT_SIGNUP).contentType(APPLICATION_JSON_UTF8)
					.content(JSONObject.toString());
				
			mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().is(400));	
		
		}catch(Exception e) {
		}
		try {			
			// Create an JSONobject for email
			JSONObject JSONObject = new JSONObject();
			JSONObject.put("email", "bomba@gmail.com");
			JSONObject.put("password", "bomba");
			JSONObject.put("address", "via bomba 13");
			JSONObject.put("phonenumber", "3920872956");
			JSONObject.put("name", "");
			JSONObject.put("surname", "Verdi");

			MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(ENDPOINT_SIGNUP).contentType(APPLICATION_JSON_UTF8)
					.content(JSONObject.toString());
				
			mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().is(400));	
		
		}catch(Exception e) {
		}
		try {			
			// Create an JSONobject for email
			JSONObject JSONObject = new JSONObject();
			JSONObject.put("email", "bomba@gmail.com");
			JSONObject.put("password", "bomba");
			JSONObject.put("address", "via bomba 13");
			JSONObject.put("phonenumber", "3920872956");
			JSONObject.put("name", "Marco");
			JSONObject.put("surname", "");

			MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(ENDPOINT_SIGNUP).contentType(APPLICATION_JSON_UTF8)
					.content(JSONObject.toString());
				
			mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().is(400));	
		
		}catch(Exception e) {
		}
		try {			
			// Create an JSONobject for email
			JSONObject JSONObject = new JSONObject();
			JSONObject.put("idutente", 2);
			JSONObject.put("email", "bomba@gmail.com");
			JSONObject.put("password", "bomba");
			JSONObject.put("address", "via bomba 13");
			JSONObject.put("phonenumber", "3920872956");
			JSONObject.put("name", "Marco");
			JSONObject.put("surname", "");

			MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(ENDPOINT_SIGNUP).contentType(APPLICATION_JSON_UTF8)
					.content(JSONObject.toString());
				
			mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().is(400));	
		
		}catch(Exception e) {
		}
	}
	
	@Test
	public void testgetPhoneNumberEndpoint() {

		try {			
			// Create an JSONobject for email
			JSONObject JSONObject = new JSONObject();
			JSONObject.put("email", "bomba@gmail.com");
			JSONObject.put("password", "bomba");

			MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(ENDPOINT_GETPHONENUMBER).contentType(APPLICATION_JSON_UTF8)
					.content(JSONObject.toString());
				
			mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().is(200));	
		
		}catch(Exception e) {
		}
	}

	@Test
	public void testupdatePasswordEndpoint() {

		try {			
			// Create an JSONobject for email
			JSONObject JSONObject = new JSONObject();
			JSONObject.put("email", "bomba@gmail.com");
			JSONObject.put("password", "bomba");

			MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(ENDPOINT_UPDATEPASSWORD).contentType(APPLICATION_JSON_UTF8)
					.content(JSONObject.toString());
				
			mockMvc.perform(request)
			.andExpect(MockMvcResultMatchers.status().is(200));	
		
		}catch(Exception e) {
		}
	}
}
