package com.hilbert.loansimapi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ValidateJWTFilter extends BasicAuthenticationFilter {

    public static final String HEADER_ATTR = "Authorization";
    public static final String ATTR_PREFIX = "Bearer ";

    public ValidateJWTFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String attr = request.getHeader(HEADER_ATTR);

        if (attr == null){
            chain.doFilter(request, response);
            return;
        }

        if(!attr.startsWith(ATTR_PREFIX)){
            chain.doFilter(request, response);
            return;
        }

        String token = attr.replace(ATTR_PREFIX, "");

        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) throws TokenExpiredException {

        try {
            String client = JWT.require(Algorithm.HMAC512(AuthenticateJWTFilter.TOKEN_PASSWORD))
                    .build()
                    .verify(token)
                    .getSubject();

            if(client == null) {
                return null;
            }

            return new UsernamePasswordAuthenticationToken(client, null, new ArrayList<>());
        } catch (Exception e){
            throw new JWTVerificationException("The Token has expired", e);
        }
    }

}
