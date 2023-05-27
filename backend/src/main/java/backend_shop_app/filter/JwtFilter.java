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
        
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            email = jwtUtil.extractEmail(token);
        }
        /*
        System.out.println("SECURITY: " +SecurityContextHolder.getContext().getAuthentication()==null);
        System.out.println("EMAIL BOOLEAN: "+email != null);
        System.out.println("TOTAL IF: "+email != null && SecurityContextHolder.getContext().getAuthentication() == null);
        
        HttpServletRequest request = (HttpServletRequest) httpServletRequest;
        String path = request.getRequestURI().substring(request.getContextPath().length());
        System.out.println("PATH: " + path);
        System.out.println("EMAIL VALUE: " +email);
        */
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null ) {
        	//si entra in questo if solo in caso di check del token
        	UserDetails userDetails = service.loadUserByUsername(email);

            if (jwtUtil.validateToken(token, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
