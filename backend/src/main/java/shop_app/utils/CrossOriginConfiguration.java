package shop_app.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrossOriginConfiguration {
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				
				registry.addMapping("/utente/signin").allowedOrigins("http://localhost:8080")
				.allowedMethods("POST").maxAge(3600);
				
				registry.addMapping("/utente/signup").allowedOrigins("http://localhost:8080")
				.allowedMethods("POST").maxAge(3600);
				
				registry.addMapping("/cart/removeProductInCart").allowedOrigins("http://localhost:8080")
				.allowedMethods("DELETE").maxAge(3600);
				
				registry.addMapping("/cart/addProductInCart").allowedOrigins("http://localhost:8080")
				.allowedMethods("POST").maxAge(3600);
				
				registry.addMapping("/wishList/removeProductInWishList").allowedOrigins("http://localhost:8080")
				.allowedMethods("DELETE").maxAge(3600);
				
				registry.addMapping("/wishList/addProductInWishList").allowedOrigins("http://localhost:8080")
				.allowedMethods("POST").maxAge(3600);
			}
		};
	}

}
