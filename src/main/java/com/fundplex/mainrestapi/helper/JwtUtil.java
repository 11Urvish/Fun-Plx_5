package com.fundplex.mainrestapi.helper;

import org.springframework.stereotype.Component;

import com.fundplex.mainrestapi.Model.User;
import com.fundplex.mainrestapi.payloads.JwtUserDto;
import com.fundplex.mainrestapi.payloads.UserDetailsDto;
import com.fundplex.mainrestapi.payloads.UserDetailsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    // public static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60;
    private String SECRET_KEY = "confidential";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        JwtUserDto jwtUserDto = new JwtUserDto(user.getId(), user.getEmail());
        claims.put("data", jwtUserDto);
        return createToken(claims, user.getEmail());
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() +
                        1000 * 60 * 60 * 24))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) &&
                !isTokenExpired(token));
    }

    public UserDetailsDto getidAndEmailFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        Map<String, Object> data = claims.get("data", HashMap.class);
        return new UserDetailsDto(data);
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    // public boolean validateJwtToken(String authToken, UserDetails userDetails) {
    // try {
    // Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken);
    // return true;
    // } catch (SignatureException e) {
    // logger.error("Invalid JWT signature: {}", e.getMessage());
    // } catch (MalformedJwtException e) {
    // logger.error("Invalid JWT token: {}", e.getMessage());
    // } catch (ExpiredJwtException e) {
    // logger.error("JWT token is expired: {}", e.getMessage());
    // } catch (UnsupportedJwtException e) {
    // logger.error("JWT token is unsupported: {}", e.getMessage());
    // } catch (IllegalArgumentException e) {
    // logger.error("JWT claims string is empty: {}", e.getMessage());
    // }

    // return false;
    // }
}
