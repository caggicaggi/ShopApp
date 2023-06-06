package backend_shop_app.config;

import backend_shop_app.filter.JwtFilter;
import backend_shop_app.service.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Configure the authentication manager to use the custom user details service
        auth.userDetailsService(userDetailsService);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        // Use NoOpPasswordEncoder as the password encoder (for demonstration purposes only)
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Expose the authentication manager bean
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	// Disable CSRF protection for the /signup endpoint
    	http.csrf().disable().authorizeRequests().antMatchers("/signup")
        .permitAll();
    	
    	// Disable CSRF protection for the /google endpoint
    	http.csrf().disable().authorizeRequests().antMatchers("/google")
        .permitAll();
    	
    	// Disable CSRF protection for the /google endpoint
    	http.csrf().disable().authorizeRequests().antMatchers("/updatePassword")
        .permitAll();
    	
    	// Disable CSRF protection for the /getPhoneNumber endpoint
    	http.csrf().disable().authorizeRequests().antMatchers("/getPhoneNumber")
        .permitAll();
    	
        http.csrf().disable().authorizeRequests().antMatchers("/signin")
                .permitAll().anyRequest().authenticated()
                .and().exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        // Add the JwtFilter before the UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);;
    }
}
