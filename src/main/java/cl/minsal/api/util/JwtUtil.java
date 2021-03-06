package cl.minsal.api.util;

import java.security.Key;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import cl.minsal.api.transfer.JwtUserDto;

public class JwtUtil {

    private static Key secret;

    /**
     * Tries to parse specified String as a JWT token. If successful, returns User object with username, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
     * 
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token is invalid.
     */
    public JwtUserDto parseToken(String token) {
        try {
        	if(secret==null){
        		return null;
        	}
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            JwtUserDto u = new JwtUserDto();
            u.setUsername(body.getSubject());
            u.setId(Long.parseLong((String) body.get("userId")));
            u.setRole((String) body.get("role"));

            return u;

        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

    /**
     * Generates a JWT token containing username as subject, and userId and role as additional claims. These properties are taken from the specified
     * User object. Tokens validity is infinite.
     * 
     * @param u the user for which the token will be generated
     * @return the JWT token
     */
    public String generateToken(JwtUserDto u) {
        Claims claims = Jwts.claims().setSubject(u.getUsername());
        claims.put("userId", u.getId() + "");
        claims.put("role", u.getRole());
        
        secret = MacProvider.generateKey(SignatureAlgorithm.HS256);
 
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

}
