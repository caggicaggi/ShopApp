package backend_shop_app.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;


@Configuration
public class HttpRequestFirewallConfig {
	
	@Bean
	public HttpFirewall getHttpFirewall() {
	    // Create a new instance of StrictHttpFirewall.
	    StrictHttpFirewall strictHttpFirewall = new StrictHttpFirewall();
	    
	    /* Rejects unauthorized HTTP methods.
	    * This is specified to block tampering with HTTP verbs and XST attacks.
	    */
	    
	    List<String> allowedMethods = Arrays.asList("POST", "PUT", "DELETE");
	    strictHttpFirewall.setAllowedHttpMethods(allowedMethods);
	    
	    /*
	    Rejects URLs that are not normalized to avoid bypassing security constraints.
	    There is no way to disable it as it is considered extremely risky to disable this constraint.
	    Some options to allow this behavior are to normalize the request before the firewall
	    or use DefaultHttpFirewall instead.
	    
	    Rejects URLs that contain characters that are not printable ASCII characters.
	    There is no way to disable it as it is considered extremely risky to disable this constraint.
	    */
	    
	    // Rejects URLs that contain semicolons. 
	    // example: http://localhost:9192/signin;
	    strictHttpFirewall.setAllowSemicolon(false);
	    
	    // Rejects URLs that contain an URL-encoded slash. 
	    // example: http://localhost:9192//signin
	    strictHttpFirewall.setAllowUrlEncodedSlash(false);
	    
	    // Rejects URLs that contain a backslash. 
	    // example: http://localhost:9192/\signin
	    strictHttpFirewall.setAllowBackSlash(false);
	    
	    // Rejects URLs that contain an URL-encoded percent.
	    // example: http://localhost:9192/signin%25
	    strictHttpFirewall.setAllowUrlEncodedPercent(false);
	    
	    strictHttpFirewall.setAllowedHostnames(host -> host.equals("localhost") );

	    return strictHttpFirewall;
	}


}
