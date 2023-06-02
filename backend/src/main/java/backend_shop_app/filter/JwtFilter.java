package backend_shop_app.filter;

import backend_shop_app.service.CustomUserDetailsService;
import backend_shop_app.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private CustomUserDetailsService service;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
    	
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String token = null;
        String email = null;
        
        // Check if the Authorization header is present and starts with "Bearer "
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Extract the token from the header
            token = authorizationHeader.substring(7);
            
            // Extract the email from the token using JwtUtil
            email = jwtUtil.extractEmail(token);
        }
        
        // Check if the email is not null and if there is no existing authentication in the SecurityContextHolder
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        	// Only enter this if block for token verification
        	
        	// Load user details by email using the CustomUserDetailsService
        	UserDetails userDetails = service.loadUserByUsername(email);

            if (jwtUtil.validateToken(token, userDetails)) {
            	// If the token is valid, create an authentication token with the user details
            	// and set it in the security context
            	
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                
                // Set additional details for the authentication token
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                
                // Set the authentication token in the SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        
        
        // Continue the filter chain
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
