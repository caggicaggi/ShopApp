package backend_shop_app.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.web.cors.CorsConfiguration;

import com.google.common.base.Predicate;


@Configuration
public class HttpRequestFirewallConfig {
	
	@Bean
	public HttpFirewall getHttpFirewall() {
	    // Create a new instance of StrictHttpFirewall.
	    StrictHttpFirewall strictHttpFirewall = new StrictHttpFirewall();
	    
	    /* Rejects unauthorized HTTP methods.
	    * This is specified to block tampering with HTTP verbs and XST attacks.
	    */
	    // example: Rejects the call with fetch.;
	    List<String> allowedMethods = Arrays.asList("POST", "PUT", "DELETE","GET");
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

	    // Rejects unauthorized hosts. 
	    // strictHttpFirewall.setAllowedHostnames();
	    
	    /*
	    List<String> allowedHostnames = Arrays.asList("localhost");
	    Predicate<String> hostnamePredicate = allowedHostnames::contains;
	    strictHttpFirewall.setAllowedHostnames(hostnamePredicate);
	    */
	    
	    /*
	    CorsConfiguration corsConfiguration = new CorsConfiguration();
	    corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:9192/signin"));
	    */
	    
	    return strictHttpFirewall;
	}


}
