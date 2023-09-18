package com.fundplex.mainrestapi.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fundplex.mainrestapi.helper.JwtUtil;
import com.fundplex.mainrestapi.services.CustomUserDetailService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);

            try {
                username = this.jwtUtil.extractUsername(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get Jwt Token");
            } catch (ExpiredJwtException e) {
                System.out.println("Token has expired");
            } catch (MalformedJwtException e) {
                System.out.println("Invalid Jwt");
            } catch (SignatureException e) {

                System.out.println("Signature Mismatched");
            }
        } else {
            System.out.println("Jwt token does not begin with bearer");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

            if (this.jwtUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            } else {
                System.out.println("Invalid Token");
            }

        } else {
            System.out.println("username is null");
        }
        filterChain.doFilter(request, response);
    }

}

// @Autowired
// public CustomUserDetailService customUserDetailService;
// @Autowired
// private JwtUtil jwtUtil;

// private static final Logger logger =
// LoggerFactory.getLogger(JwtAuthenticationFilter.class);

// @Override
// protected void doFilterInternal(HttpServletRequest request,
// HttpServletResponse response, FilterChain filterChain)
// throws ServletException, IOException {
// try {
// String jwt = parseJwt(request);
// if (jwt != null && jwtUtil.validateJwtToken(jwt)) {
// String username = jwtUtil.extractUsername(jwt);

// UserDetails userDetails =
// customUserDetailService.loadUserByUsername(username);
// UsernamePasswordAuthenticationToken authentication = new
// UsernamePasswordAuthenticationToken(
// userDetails, null, userDetails.getAuthorities());
// authentication.setDetails(new
// WebAuthenticationDetailsSource().buildDetails(request));

// SecurityContextHolder.getContext().setAuthentication(authentication);
// }
// } catch (Exception e) {
// logger.error("Cannot set user authentication: {}", e);
// }

// filterChain.doFilter(request, response);
// }

// private String parseJwt(HttpServletRequest request) {
// String headerAuth = request.getHeader("Authorization");

// if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
// return headerAuth.substring(7, headerAuth.length());
// }

// return null;
// }

// @Override
// protected void doFilterInternal(HttpServletRequest request,
// HttpServletResponse response, FilterChain filterChain)
// throws ServletException, IOException {
// String requestTokenHeader = request.getHeader("Authorization");
// String username = null;
// String jwtToken = null;
// if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
// jwtToken = requestTokenHeader.substring(7);

// try {
// username = this.jwtUtil.extractUsername(jwtToken);
// } catch (IllegalArgumentException e) {
// System.out.println("Unable to get Jwt Token");
// } catch (ExpiredJwtException e) {
// System.out.println("Token has expired");
// } catch (MalformedJwtException e) {
// System.out.println("Invalid Jwt");
// }
// } else {
// System.out.println("Jwt token does not begin with bearer");
// }

// if (username != null &&
// SecurityContextHolder.getContext().getAuthentication() == null) {
// UserDetails userDetails =
// customUserDetailService.loadUserByUsername(username);

// if (this.jwtUtil.validateToken(jwtToken, userDetails)) {
// UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
// UsernamePasswordAuthenticationToken(
// userDetails, null, userDetails.getAuthorities());
// usernamePasswordAuthenticationToken
// .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
// SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

// } else {
// System.out.println("Invalid Token");
// }

// } else {
// System.out.println("username is null");
// }
// filterChain.doFilter(request, response);
// }

// }
