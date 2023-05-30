package backend_shop_app.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {
	
    // Secret key used for signing the JWT
	@Value("${jwt.secret}")
	private String secret;
    
    // Expiration time of the JWT in seconds
    static int EXPIRATION_IN_SECONDS = 120000;
    
    /**
     * Extracts the email (subject) from the JWT token.
     * 
     * @param token The JWT token
     * @return The email extracted from the token
     */
	    public String extractEmail(String token) {
	        return extractClaim(token, Claims::getSubject);
	    }

    /**
     * Extracts the expiration date from the JWT token.
     * 
     * @param token The JWT token
     * @return The expiration date extracted from the token
     */
	    public Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }
    
    /**
     * Extracts a specific claim from the JWT token using the provided resolver function.
     * 
     * @param token          The JWT token
     * @param claimsResolver The function to resolve the specific claim
     * @return The resolved claim value
     */
	    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimsResolver.apply(claims);
	    }
    
    /**
     * Extracts all claims from the JWT token.
     * 
     * @param token The JWT token
     * @return All claims extracted from the token
     */
	    private Claims extractAllClaims(String token) {
	        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	    }
    
    /**
     * Checks if the JWT token is expired.
     * 
     * @param token The JWT token
     * @return True if the token is expired, false otherwise
     */
	    private Boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }
    
    /**
     * Generates a new JWT token for the given username.
     * 
     * @param username The username for which the token is generated
     * @return The generated JWT token
     */
    
	    public String generateToken(String username) {
	        Map<String, Object> claims = new HashMap<>();
	        return createToken(claims, username);
	    }
	    
    /**
     * Creates a new JWT token with the specified claims and subject.
     * 
     * @param claims  The claims to include in the token
     * @param subject The subject (usually username or email) for which the token is issued
     * @return The created JWT token
     */
	    private String createToken(Map<String, Object> claims, String subject) {
	
	        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_IN_SECONDS))
	                .signWith(SignatureAlgorithm.HS256, secret).compact();
	    }
    
    /**
     * Validates the JWT token against the user details.
     * 
     * @param token       The JWT token to validate
     * @param userDetails The user details of the corresponding user
     * @return True if the token is valid, false otherwise
     */
	    public Boolean validateToken(String token, UserDetails userDetails) {
	        final String email = extractEmail(token);
	        //username = email
	        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }
}

