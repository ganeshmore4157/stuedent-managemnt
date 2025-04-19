package com.ganesh.student_managment.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String requestToken = request.getHeader("Authorization");
        String username = null;
        String token = null;

        if(requestToken != null && requestToken.startsWith("Bearer")) {
            token = requestToken.replace("Bearer ", "");
            try {
                username = jwtTokenHelper.extractUsername(token);
            } catch (IllegalArgumentException e) {
                logger.error("unable to username from token");
            } catch(ExpiredJwtException e) {
                logger.error("Jwt token has expired");
            } catch(MalformedJwtException e) {
                logger.error("Invalid jwt");
            } catch(SignatureException e) {
                logger.error("Invalid jwt token");
            }
        }else {
            logger.error("Token is null or token does not starts with Bearer");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(userDetails.isEnabled()) {
                if (jwtTokenHelper.isTokenValid(token, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                } else {
                    logger.error("Invalid Jwt Token");
                }
            } else {
                logger.error("User account is not enabled");
            }
        } else {
            logger.error("Username is null Or SecurityContext is not null");
        }
        filterChain.doFilter(request, response);
    }

}
