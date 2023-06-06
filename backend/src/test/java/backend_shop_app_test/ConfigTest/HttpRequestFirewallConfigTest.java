package backend_shop_app_test.ConfigTest;

import java.nio.charset.Charset;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import backend_shop_app.config.HttpRequestFirewallConfig;
import backend_shop_app.controller.CartControllerImpl;
import backend_shop_app.controller.UtenteControllerImpl;

public class HttpRequestFirewallConfigTest {
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	@InjectMocks
	UtenteControllerImpl controller;
	
	@Before
	public void init() throws Exception {
		MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

    @Test
    public void testHttpRequestFirewallConfiguration() {
    	try {
        String urlWithSemicolon = "http://localhost:9192/signup;";
        String urlWithEncodedSlash = "http://localhost:" + "9192" + "/test/end%2Fpoint";
        String urlWithEncodedPercent = "http://localhost:" + "9192" + "/test/end%25point";
        
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(urlWithSemicolon).contentType(APPLICATION_JSON_UTF8)
				.content("");
			
        mockMvc.perform(request)
 			.andExpect(MockMvcResultMatchers.status().is(200)).toString();
        
		} catch (Exception e) {
			System.out.println(e);
		}	
    }
}
