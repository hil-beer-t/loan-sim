package com.hilbert.loansimapi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hilbert.loansimapi.data.ClientDetailData;
import com.hilbert.loansimapi.models.Client;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticateJWTFilter extends UsernamePasswordAuthenticationFilter {

    // 10 MINUTES
    public static final int TOKEN_EXPIRES_IN = 600_000;

    // TODO: HIDE TOKEN_PASSWORD
    public static final String TOKEN_PASSWORD = "6c62361f-893e-4936-b216-de2f412b69dc";

    private final AuthenticationManager authenticationManager;

    public AuthenticateJWTFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            Client client = new ObjectMapper().readValue(request.getInputStream(), Client.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    client.getEmail(),
                    client.getPassword(),
                    new ArrayList<>()
            ));
        } catch (IOException e) {
            throw new RuntimeException("User authentication have failed", e);
        }
    }

    // TODO: Exception 'javax.servlet.ServletException' is never thrown in the method
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        ClientDetailData clientDetailData = (ClientDetailData) authResult.getPrincipal();

        String token = JWT.create().
                withSubject(clientDetailData.getUsername()).
                withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRES_IN)).
                sign(Algorithm.HMAC512(TOKEN_PASSWORD));

        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
