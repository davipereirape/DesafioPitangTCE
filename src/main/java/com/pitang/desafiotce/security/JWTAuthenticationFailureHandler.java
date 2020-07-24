package com.pitang.desafiotce.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * @author Davi Pereira <pereiradavipe@gmail.com>
 * @since July of 2020
 * 
 * Filter to configure authentication failure.
 *
 */
public class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {
	 
	/**
	 * Used to configure information in case of authentication fail. 
	 */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        response.setStatus(401);
        response.setContentType("application/json"); 
        response.getWriter().append(json());
    }
     
    private String json() {
        return "{\"status\": 401, "
            + "\"error\": \"Not authorized\", "
            + "\"message\": \"Invalid login or password\", "
            + "\"errorCode\": \"1\"}";
    } 
}
